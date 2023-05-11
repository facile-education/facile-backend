/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.weprode.nero.document.service.impl;

import com.liferay.document.library.kernel.exception.DuplicateFileEntryException;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.NoSuchResourcePermissionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.kernel.util.Base64;
import com.weprode.nero.commons.properties.NeroSystemProperties;
import com.weprode.nero.document.constants.*;
import com.weprode.nero.document.model.Version;
import com.weprode.nero.document.service.*;
import com.weprode.nero.document.service.base.FileUtilsLocalServiceBaseImpl;

import com.weprode.nero.document.utils.DLAppUtil;
import com.weprode.nero.document.utils.ENTDocumentConversionUtil;
import com.weprode.nero.document.utils.FileNameUtil;
import com.weprode.nero.group.constants.ActivityConstants;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Safelist;
import org.jsoup.select.Elements;
import org.osgi.service.component.annotations.Component;

import java.io.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component(
	property = "model.class.name=com.weprode.nero.document.model.FileUtils",
	service = AopService.class
)
public class FileUtilsLocalServiceImpl extends FileUtilsLocalServiceBaseImpl {

	private final Log logger = LogFactoryUtil.getLog(FileUtilsLocalServiceImpl.class);

	private static final String TWITTER_SCRIPT = "https://platform.twitter.com/widgets.js";

	public FileEntry copyFileEntry(long userId, long fileId, long destFolderId, boolean copyFileContent)
			throws PortalException, SystemException, IOException {
		return copyFileEntry(userId, fileId, destFolderId, copyFileContent, DocumentConstants.MODE_RENAME);
	}

	public FileEntry copyFileEntry(long userId, long fileId, long destFolderId, boolean copyFileContent, int mode) throws PortalException, SystemException, IOException {
		final User user = UserLocalServiceUtil.getUser(userId);

		final FileEntry originFile = DLAppServiceUtil.getFileEntry(fileId);
		// Using LocalService here to avoid VIEW permission exception
		// Example : Sender (!= userId) is copying attached file to recipient folder
		final Folder destFolder = DLAppLocalServiceUtil.getFolder(destFolderId);

		if (PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), destFolder, PermissionConstants.ADD_OBJECT)) {
			boolean isSignet = false;

			// Ajout des permissions
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setAddGroupPermissions(true);

			FileEntry newFileEntry;
			InputStream is;

			if (copyFileContent) {
				// Use the getContentStream from FileEntryUtil because the same methods on
				// FileEntry check the permission, in some case this throw and error
				isSignet = originFile.getExtension().equals("html") && originFile.getMimeType().equals("text/bmk");
				is = DLFileEntryLocalServiceUtil.getFileAsStream(originFile.getFileEntryId(), originFile.getVersion());
			} else {
				// on sauve un fichier vide!!!
				is = new ByteArrayInputStream("".getBytes());
			}

			newFileEntry = DLAppUtil.addFileEntry(user, destFolder, originFile.getTitle(), is, mode);

			if (isSignet) {
				DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(newFileEntry.getFileEntryId());
				dlFileEntry.setMimeType("text/bmk");
				DLFileEntryLocalServiceUtil.updateDLFileEntry(dlFileEntry);
				newFileEntry = DLAppServiceUtil.getFileEntry(newFileEntry.getFileEntryId());
			}

			// Apply parent folder permissions
			PermissionUtilsLocalServiceUtil.setParentPermissionToFile(newFileEntry);

			return newFileEntry;
		} else {
			throw new NoSuchResourcePermissionException();
		}
	}

	public FileEntry moveFileEntry(long userId, long fileId, long destFolderId, int mode) throws SystemException, PortalException {
		final User user = UserLocalServiceUtil.getUser(userId);
		logger.info("User " + user.getFullName() + " moves file " + fileId + " to destination folder " + destFolderId + " in mode " + mode);
		final FileEntry originFile = DLAppServiceUtil.getFileEntry(fileId);
		Folder destFolder = DLAppServiceUtil.getFolder(destFolderId);

		if (PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), originFile, ActionKeys.DELETE)
				&& PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), destFolder, PermissionConstants.ADD_OBJECT)) {

			// Ajout des permissions
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setAddGroupPermissions(true);

			boolean finished = false;
			int count = 0;
			String fileTitle = FileNameUtil.getValidName(originFile.getTitle());

			//Check if filename contains extension
			if (fileTitle.endsWith(originFile.getExtension())) {
				// Add one to extension length to count the dot
				int substringEndIndex = fileTitle.length() - (originFile.getExtension().length() + 1);
				fileTitle = fileTitle.substring(0, substringEndIndex);
			}
			String suffix;

			while (!finished && count < DocumentConstants.NB_RENAMED_VERSIONS) {
				try {
					FileEntry fileEntry = DLAppServiceUtil.moveFileEntry(
							fileId,
							destFolderId,
							serviceContext);

					logger.info("Successfully moved file " + fileEntry.getTitle() + " (id " + fileEntry.getFileEntryId() + ")");

					// Apply parent folder permissions
					PermissionUtilsLocalServiceUtil.setParentPermissionToFile(fileEntry);

					finished = true;

					// Register activity
					DLFolder folder = DLFolderLocalServiceUtil.getFolder(fileEntry.getFolderId());
					ActivityLocalServiceUtil.addActivity(fileEntry.getFileEntryId(), fileEntry.getFolderId(), user.getUserId(), fileEntry.getGroupId(), fileEntry.getTitle(), folder.getName(), ActivityConstants.TYPE_FILE_MOVE);

				} catch (DuplicateFileEntryException e) {
					logger.error("File already exists in target folder");
					if (mode == DocumentConstants.MODE_NORMAL) {
						throw new DuplicateFileEntryException();
					} else if (mode == DocumentConstants.MODE_RENAME) {
						count++;
						suffix = " (" + count + ")";
						DLAppServiceUtil.updateFileEntry(
								originFile.getFileEntryId(),
								originFile.getTitle(),
								originFile.getMimeType(),
								fileTitle + suffix + "." + originFile.getExtension(),
								StringPool.BLANK, // urlTitle
								originFile.getDescription(),
								StringPool.BLANK, // changeLog
								DLVersionNumberIncrease.MINOR,
								originFile.getContentStream(),
								originFile.getSize(),
								null,
								null,
								serviceContext
						);
					} else if (mode == DocumentConstants.MODE_REPLACE) {
						boolean hasFound = false;

						// Find the problematic file and delete it!
						List<FileEntry> files = DLAppServiceUtil.getFileEntries(destFolder.getRepositoryId(), destFolder.getFolderId());
						for (FileEntry subFile : files) {
							if (subFile.getTitle().equals(originFile.getTitle())) {
								hasFound = true;
								FileUtilsLocalServiceUtil.deleteFile(user.getUserId(), subFile.getFileEntryId());
							}
						}

						if (!hasFound) {
							logger.error("Normally have to find the file with the duplicate FileName exception" + originFile.getTitle());
							throw new DuplicateFileEntryException();
						}
						count++;
					}
				}
			}

			return originFile;
		} else {
			throw new NoSuchResourcePermissionException();
		}
	}

	public FileEntry renameFile (User user, FileEntry originFile, String newName) throws SystemException, PortalException {
		if (PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), originFile, ActionKeys.UPDATE)) {     // handle this permission as the RENAME permission (update)
			boolean finished = false;
			int count = 0;
			String suffix = "";
			String newNamewithoutExtension = FileNameUtil.getValidName(newName);
			FileEntry renamedFile = null;

			// Check if filename contains extension
			if (newNamewithoutExtension.endsWith(originFile.getExtension())) {
				// Add one to extension length to count the dot
				int substringEndIndex = newNamewithoutExtension.length() - (originFile.getExtension().length() + 1);
				newNamewithoutExtension = newNamewithoutExtension.substring(0, substringEndIndex);
			}
			while (!finished && count < DocumentConstants.NB_RENAMED_VERSIONS) {
				try {
					renamedFile = DLAppServiceUtil.updateFileEntry(
							originFile.getFileEntryId(),
							originFile.getTitle(),
							originFile.getMimeType(),
							newNamewithoutExtension + suffix + "." + originFile.getExtension(),
							StringPool.BLANK, // urlTitle
							originFile.getDescription(),
							StringPool.BLANK, // changeLog
							DLVersionNumberIncrease.MINOR,
							originFile.getContentStream(),
							originFile.getSize(),
							null,
							null,
							new ServiceContext()
					);
					finished = true;

					// Register activity
					DLFolder folder = DLFolderLocalServiceUtil.getFolder(renamedFile.getFolderId());
					ActivityLocalServiceUtil.addActivity(renamedFile.getFileEntryId(), renamedFile.getFolderId(), user.getUserId(), renamedFile.getGroupId(), renamedFile.getTitle(), folder.getName(), ActivityConstants.TYPE_FILE_MODIFICATION);

				} catch (DuplicateFileEntryException e) {
					count++;
					suffix = " (" + count + ")";
				}
			}

			if (renamedFile == null) {
				throw new PortalException("Reach max renamed versions allowed");
			}

			return renamedFile;
		} else {
			throw new NoSuchResourcePermissionException();
		}
	}

	public String getDisplayUrl (FileEntry file, long versionId, String typeOfView, long userId, boolean readOnly) throws SystemException, PortalException {
		String documentURL;
		boolean isCreation = false;
		boolean isWritable = !readOnly && versionId == file.getLatestFileVersion().getFileVersionId();   // read only if there is not the latest version

		switch (typeOfView) {
			case "Geogebra":
				documentURL = GeogebraConstants.URL_GEOGEBRA + "/app.html"
						+ "?fileVersionId=" + versionId
						+ "&isCreation=" + isCreation;

				break;
			case "MindMap":
				String mode = isWritable ? "/editor.html" : "/viewmode.html";
				documentURL = MindMapConstants.URL_MINDMAP + mode
						+ "?fileVersionId=" + versionId;

				break;
			case "Scratch":
				documentURL = ScratchConstants.URL_SCRATCH + "/index.html"
						+ "?fileId=" + versionId
						+ "&userId=" + userId
						+ "&readonly=" + !isWritable;

				break;
			case "Office":
				String token = UUID.randomUUID().toString();
				LoolTokenLocalServiceUtil.createLoolToken(userId, token);
				// TODO Handle locked files?

				String versionName;
				try {
					Version fileVersion = VersionLocalServiceUtil.getVersion(versionId);
					versionName = String.valueOf(fileVersion.getVersionNumber());
				} catch (Exception e) {
					versionName = "";
				}

				String wopiParam = file.getFileEntryId() + "+" + versionName + "+" + readOnly;
				String hostUrl = PropsUtil.get(NeroSystemProperties.PORTAL_URL);
				documentURL = "/browser/dist/cool.html?WOPISrc=" + URLCodec.encodeURL(hostUrl) + "c%2Fcommon%2Fwopi%2Ffiles%2F" + URLCodec.encodeURL(wopiParam)
						+ "&access_token=" + token;

				break;
			default:
				Date d = new Date();
				String mainPath = ""; // TODO

				documentURL =
						mainPath + "/documents/"
								+ file.getRepositoryId() + StringPool.SLASH
								+ file.getFolderId() + StringPool.SLASH
								+ file.getTitle() + StringPool.SLASH
								+ file.getUuid() + StringPool.QUESTION
								+ "version=" + file.getVersion() + StringPool.AMPERSAND
								+ "t=" + d.getTime();

				// Add parameter in URL in case of video/audio conversion is needed
				if (typeOfView.equals("Video") && !file.getExtension().equals("mp4")) {
					documentURL = documentURL + "&targetExtension=mp4";
				}
				break;
		}

		return documentURL;
	}

	// TODO getDisplayUrl (cdtSession, fileName, typeOfView, readOnly) {} // (get url from cdt attached files (not handle by documents library but by file system)

	public String getDownloadUrl (FileEntry file) {
		return 	"/documents/"
				+ file.getRepositoryId() + StringPool.SLASH
				+ file.getFolderId() + StringPool.SLASH
				+ file.getTitle() + StringPool.SLASH
				+ file.getUuid() + StringPool.QUESTION
				+ "version=" + file.getVersion();
	}

	public File convertAudioToMP3 (String fileName, File audioFile) throws SystemException, IOException {
		InputStream is = new FileInputStream(audioFile);

		String[] splitFN = FileNameUtil.splitFileName(fileName);
		String originalTitle = splitFN[0];
		String extension = splitFN[1];
		String targetExtension = "mp3";

		// Do not use cache
		Map<String, String> parameters = new HashMap<>();
		parameters.put("forceConversion", "true");

		return ENTDocumentConversionUtil.convert(originalTitle, is, extension, targetExtension, parameters);
	}

	public void deleteFile(long userId, long fileId) throws PortalException, SystemException {
		final FileEntry file = DLAppServiceUtil.getFileEntry(fileId);

		if (PermissionUtilsLocalServiceUtil.hasUserFilePermission(userId, file, ActionKeys.DELETE)) {
			Folder parentFolder = file.getFolder();

			// Delete file on DB and FS
			DLAppServiceUtil.deleteFileEntry(fileId);

			// Update parentFolder lastPostDate because it lost a file
			DLFolderLocalServiceUtil.updateLastPostDate(parentFolder.getFolderId(), new Date());

			// Register activity
			ActivityLocalServiceUtil.addActivity(file.getFileEntryId(), file.getFolderId(), userId, file.getGroupId(), file.getTitle(), parentFolder.getName(), ActivityConstants.TYPE_FILE_DELETION);
		} else {
			throw new NoSuchResourcePermissionException();
		}
	}

	public FileEntry createGeogebraFile (User user, long folderId, String name) throws SystemException, PortalException {

		Folder folder = DLAppServiceUtil.getFolder(folderId);
		byte[] byteArray = Base64.decode(GeogebraConstants.GEOGEBRA_NEW_FILE);

		return DLAppUtil.addFileEntry(user, folder, name + ".ggb", byteArray, DocumentConstants.MODE_RENAME);
	}

	public FileEntry createMindMapFile (User user, long folderId, String name) throws SystemException, PortalException, IOException {

		// Create file
		Folder folder = DLAppServiceUtil.getFolder(folderId);
		String initFileContent = MindMapConstants.MINDMAP_NEW_FILE_START + name + MindMapConstants.MINDMAP_NEW_FILE_END;
		InputStream stream = new ByteArrayInputStream(initFileContent.getBytes(StandardCharsets.UTF_8));

		return DLAppUtil.addFileEntry(user, folder, name + ".mind", stream, DocumentConstants.MODE_RENAME);
	}

	public FileEntry createScratchFile (User user, long folderId, String name) throws PortalException, SystemException {

		// Create file
		Folder folder = DLAppServiceUtil.getFolder(folderId);
		byte[] byteArray = Base64.decode(ScratchConstants.SCRATCH_NEW_FILE);

		return DLAppUtil.addFileEntry(user, folder, name + ".sb3", byteArray, DocumentConstants.MODE_RENAME);
	}

	public FileEntry createHtmlFile (User user, long folderId, String name) throws PortalException, SystemException {

		// Create file
		Folder folder = DLAppServiceUtil.getFolder(folderId);
		byte[] byteArray = new byte[] {};

		return DLAppUtil.addFileEntry(user, folder, name + ".html", byteArray, DocumentConstants.MODE_RENAME);
	}

	public FileEntry createLoolFile (User user, long folderId, String name, String type) throws Exception {

		// Create file
		Folder folder = DLAppServiceUtil.getFolder(folderId);
		byte[] byteArrayContent;

		switch (type) {
			case LoolConstants.ODT_EXTENSION:
				byteArrayContent = Base64.decode(LoolConstants.ODT_NEW_FILE);
				break;
			case LoolConstants.ODS_EXTENSION:
				byteArrayContent = Base64.decode(LoolConstants.ODS_NEW_FILE);
				break;
			case LoolConstants.ODP_EXTENSION:
				byteArrayContent = Base64.decode(LoolConstants.ODP_NEW_FILE);
				break;
			default:
				throw new Exception("Unknown lool type" + type);
		}

		return DLAppUtil.addFileEntry(user, folder, name + "." + type, byteArrayContent, DocumentConstants.MODE_RENAME);
	}

	public String sanitizeHTMLContent(String content) {
		// Do not allow style on a tag > click-jacking vulnerability (https://stackoverflow.com/questions/4546591/xss-attacks-and-style-attributes)
		String sanitizedContent = "";

		// Look for links to prevent href null value (causing sanitize failure)
		Document doc = Jsoup.parse(content);
		Elements links = doc.select("a");
		for (Element link : links) {
			link.attr("href");
			if (link.attr("href").isEmpty()) {
				link.attr("href", "");
			}
		}

		// Check if iframe URL is in whitelist
		Elements iframes = doc.select("iframe");
		for (Element iframe : iframes) {
			if (!DocumentUtilsLocalServiceUtil.isEmbedUrlWhitelisted(iframe.attr("src"))) {
				iframe.remove();
			}
		}
		content = doc.html();

		boolean isTwitterWidget = false;
		Elements scripts = doc.select("script");

		for (Element script : scripts) {
			if (script.attr("src").contains(TWITTER_SCRIPT)) {
				isTwitterWidget = true;
				break;
			}
		}

		try {
			// Keep html meta info if existing
			String header = "";
			String bodyStartTag = "<body";
			int bodyIndex = content.indexOf(bodyStartTag);

			if (bodyIndex > -1) {
				header = content.substring(0, bodyIndex -1);
				content = content.substring(bodyIndex);
			}

			String footer = "";
			String bodyEndTag = "</body>";
			int endIndex = content.indexOf(bodyEndTag);
			if (endIndex > -1) {
				footer = content.substring(endIndex);
				content = content.substring(0, endIndex + footer.length());
			}

			// Cannot allow body element so we trick sanitizer with custom one
			content = content.replace("<body", "<hbody");
			content = content.replace("</body", "</hbody");

			String postcontent = header + Jsoup.clean(content, PropsUtil.get(NeroSystemProperties.PORTAL_URL),
					Safelist.relaxed()
							.preserveRelativeLinks(true)
							.removeTags("title")
							.addTags("hbody")
							.addTags("address")
							.addTags("center")
							.addTags("style")
							.addTags("audio, video, source, code")
							.addTags("iframe")
							.addAttributes(":all", "style", "class", "title", "height", "width", "id", "aria-readonly")
							.addAttributes("img", "src")
							.addAttributes("audio", "controls", "src")
							.addAttributes("video", "controls", "src")
							.addAttributes("source", "type", "src")
							.addAttributes("iframe", "src", "height", "width", "title", "scrolling", "name", "frameborder", "longdesc", "allow", "allowfullscreen", "style")
							.addAttributes("a", "href", "target")
							.addAttributes("table", "border", "cellpadding", "cellspacing", "align")
							.addProtocols("img", "src", "http", "https", "data")
							.addProtocols("a", "href", "#")) + footer;

			postcontent = postcontent.replace("<hbody", "<body");
			postcontent = postcontent.replace("</hbody", "</body");

			sanitizedContent = postcontent;

			if (isTwitterWidget) {
				sanitizedContent = sanitizedContent + "<script src=\"" + TWITTER_SCRIPT + "\" charset=\"utf-8\"></script>";
			}
		} catch (Exception e) {
			logger.error("Failed to sanitize content :\n" + content, e);
			sanitizedContent = HtmlParserUtil.extractText(content).trim();
		}

		return sanitizedContent;
	}


	// Returns true if the user is member of the group or org, in case of group folder
	public boolean isAllowedToAccessFolder(long userId, long folderId) {
		try {
			User user = UserLocalServiceUtil.getUser(userId);
			Folder folder = DLAppServiceUtil.getFolder(folderId);
			Group folderGroup = GroupLocalServiceUtil.getGroup(folder.getGroupId());
			if (folderGroup.isOrganization()
					&& !OrganizationLocalServiceUtil.hasUserOrganization(user.getUserId(), folderGroup.getClassPK())
					&& !RoleUtilsLocalServiceUtil.isDirectionMember(user)
					&& !RoleUtilsLocalServiceUtil.isDoyen(user, folderGroup.getClassPK())) {
				logger.info("User " + user.getUserId() + " tries to access folder " + folderId + " of org group " + folderGroup.getGroupId() + " but does not belong to it");
				return false;
			} else if (folderGroup.isRegularSite()
					&& !GroupLocalServiceUtil.hasUserGroup(user.getUserId(), folderGroup.getGroupId())
					&& !RoleUtilsLocalServiceUtil.isDirectionMember(user)) {
				logger.info("User " + user.getUserId() + " tries to access folder " + folderId + " of group " + folderGroup.getGroupId() + " but does not belong to it");
				return false;
			}
		} catch (Exception e) {
			logger.error("Error when determining if user " + userId + " is allowed to access folder " + folderId + " " + e.getMessage());
		}

		return true;
	}

	public boolean isGroupFile (long fileEntryId) {
		try {
			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);
			Group fileGroup = GroupLocalServiceUtil.getGroup(fileEntry.getGroupId());

			return fileGroup.isOrganization() || fileGroup.isRegularSite();
		} catch (Exception e) {
			logger.error("Error determining if file " + fileEntryId + " belongs to a group or not : " + e.getMessage());
		}

		return false;
	}

	public long getSizeInMegaOctet(long pSize){
		return pSize / (1024 * 1024);
	}

	/**
	 * Get file as Object Value Pair
	 */
	public ObjectValuePair<String, InputStream> getFileAsOVPStream(long companyId, long fileId, String fileName) throws PortalException, SystemException, IOException {
		String formattedTitle = "";

		ObjectValuePair<String, InputStream> ovp = new ObjectValuePair<>();
		if(fileId > 0){
			FileEntry fe = DLAppServiceUtil.getFileEntry(fileId);
			InputStream is = fe.getContentStream();
			formattedTitle = FileNameUtil.getValidName(fe.getTitle(), true, true);
			ovp.setValue(is);
		}
		// Copy attachment
		else {
			InputStream file = DLStoreUtil.getFileAsStream(companyId, CompanyConstants.SYSTEM, fileName);
			formattedTitle = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
			ovp.setValue(file);
		}
		ovp.setKey(formattedTitle);

		return ovp;
	}

}