/**
 * Copyright (c) 2022-present - Weprode
 * This file is part of FACILE ENT Project.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License (LGPL) as
 * published by the Free Software Foundation (version 2.1 of the License).
 * See LICENCE.txt file
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */

package com.weprode.facile.document.service.impl;

import com.liferay.document.library.kernel.exception.DuplicateFileEntryException;
import com.liferay.document.library.kernel.exception.FileExtensionException;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.NoSuchResourcePermissionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.HtmlParserUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.URLCodec;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.commons.properties.NeroSystemProperties;
import com.weprode.facile.course.exception.UnauthorizedUrlException;
import com.weprode.facile.course.service.ContentBlockLocalServiceUtil;
import com.weprode.facile.document.constants.DocumentConstants;
import com.weprode.facile.document.constants.GeogebraConstants;
import com.weprode.facile.document.constants.LoolConstants;
import com.weprode.facile.document.constants.MindMapConstants;
import com.weprode.facile.document.constants.PermissionConstants;
import com.weprode.facile.document.constants.ScratchConstants;
import com.weprode.facile.document.model.Version;
import com.weprode.facile.document.service.ActivityLocalServiceUtil;
import com.weprode.facile.document.service.FileUtilsLocalServiceUtil;
import com.weprode.facile.document.service.LoolTokenLocalServiceUtil;
import com.weprode.facile.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.facile.document.service.VersionLocalServiceUtil;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.facile.document.service.base.FileUtilsLocalServiceBaseImpl;
import com.weprode.facile.document.utils.DLAppUtil;
import com.weprode.facile.document.utils.DocumentUtil;
import com.weprode.facile.document.utils.ENTDocumentConversionUtil;
import com.weprode.facile.document.utils.FileNameUtil;
import com.weprode.facile.document.utils.SupportedExtensions;
import com.weprode.facile.group.constants.ActivityConstants;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Safelist;
import org.jsoup.select.Elements;
import org.osgi.service.component.annotations.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component(
	property = "model.class.name=com.weprode.facile.document.model.FileUtils",
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

		if (PermissionUtilsLocalServiceUtil.hasUserFilePermission(userId, originFile, ActionKeys.VIEW) &&
				PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), destFolder, PermissionConstants.ADD_OBJECT) &&
				FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(userId, originFile.getFolderId())) { // Don't know why the two first conditions are not sufficient...
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

	public FileEntry moveFileEntry(long userId, long fileId, long destFolderId) throws SystemException, PortalException {
		return moveFileEntry(userId, fileId, destFolderId, DocumentConstants.MODE_RENAME);
	}

	public FileEntry moveFileEntry(long userId, long fileId, long destFolderId, int mode) throws SystemException, PortalException {
		final User user = UserLocalServiceUtil.getUser(userId);
		FileEntry originFile = DLAppServiceUtil.getFileEntry(fileId);

		if (originFile.getFolderId() == destFolderId) {
			logger.info("User " + user.getFullName() + " is moving a file into it's current folder -> do nothing.");
			return originFile;
		}

		logger.info("User " + user.getFullName() + " moves file " + fileId + " from folder " + originFile.getFolderId() + " to destination folder " + destFolderId + " in mode " + mode);
		Folder destFolder = DLAppServiceUtil.getFolder(destFolderId);

		if (PermissionUtilsLocalServiceUtil.hasUserFilePermission(userId, originFile, ActionKeys.VIEW) &&
				PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), originFile, ActionKeys.DELETE) &&
				PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), destFolder, PermissionConstants.ADD_OBJECT) &&
				FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(userId, originFile.getFolderId())) {

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
					logger.error("File " + originFile.getTitle() + " (" + fileId + ") already exists in target folder " + destFolderId);
					if (mode == DocumentConstants.MODE_NORMAL) {
						throw new DuplicateFileEntryException();
					} else if (mode == DocumentConstants.MODE_RENAME) {
						count++;
						suffix = " (" + count + ")";
						originFile = DLAppServiceUtil.updateFileEntry(
								originFile.getFileEntryId(),
								fileTitle + suffix + "." + originFile.getExtension(),
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

	public FileEntry renameFile (long userId, FileEntry originFile, String newName) throws SystemException, PortalException {
		if (PermissionUtilsLocalServiceUtil.hasUserFilePermission(userId, originFile, ActionKeys.UPDATE)) {     // handle this permission as the RENAME permission (update)
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
							newNamewithoutExtension + suffix + "." + originFile.getExtension(),
							originFile.getMimeType(),
							newNamewithoutExtension + suffix + "." + originFile.getExtension(),
							StringPool.BLANK, // urlTitle
							originFile.getDescription(),
							StringPool.BLANK, // changeLog
							DLVersionNumberIncrease.NONE,
							originFile.getContentStream(),
							originFile.getSize(),
							null,
							null,
							new ServiceContext()
					);
					finished = true;

					// Register activity
					DLFolder folder = DLFolderLocalServiceUtil.getFolder(renamedFile.getFolderId());
					ActivityLocalServiceUtil.addActivity(renamedFile.getFileEntryId(), renamedFile.getFolderId(), userId, renamedFile.getGroupId(), renamedFile.getTitle(), folder.getName(), ActivityConstants.TYPE_FILE_MODIFICATION);

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

	public void deleteFile(long userId, long fileId) throws PortalException, SystemException {
        logger.info("User " + userId + " deletes file " + fileId);

		final FileEntry file = DLAppServiceUtil.getFileEntry(fileId);

		if (PermissionUtilsLocalServiceUtil.hasUserFilePermission(userId, file, ActionKeys.DELETE)) {
			Folder parentFolder = file.getFolder();

			// Delete file on DB and FS
			DLAppServiceUtil.deleteFileEntry(fileId);

			// Update parentFolder lastPostDate because it lost a file
			DLFolderLocalServiceUtil.updateLastPostDate(parentFolder.getFolderId(), new Date());

			// Delete previous activity
			ActivityLocalServiceUtil.deleteFileActivity(file.getFileEntryId());

			// Delete Versions (view and download counts)
			VersionLocalServiceUtil.removeVersionByFileEntryId(file.getFileEntryId());
		} else {
			throw new NoSuchResourcePermissionException();
		}
	}

	public String getDisplayUrl (FileEntry file, long versionId, long userId, boolean readOnly) throws SystemException, PortalException {
		String documentURL;
		boolean isCreation = false;
		boolean isWritable = !readOnly && versionId == file.getLatestFileVersion().getFileVersionId();   // read only if there is not the latest version

		// Detect type of document
		String typeOfView = SupportedExtensions.getTypeOfView(file.getExtension());

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
				String token = "";
				if (userId != 0) {
					token = UUID.randomUUID().toString();
					LoolTokenLocalServiceUtil.createLoolToken(userId, token);
				}

				String versionName;
				try {
					Version fileVersion = VersionLocalServiceUtil.getVersion(versionId);
					versionName = String.valueOf(fileVersion.getVersionNumber());
				} catch (Exception e) {
					versionName = "";
				}

				String wopiParam = file.getFileEntryId() + "+" + versionName + "+" + readOnly;
				String hostUrl = PropsUtil.get(NeroSystemProperties.PORTAL_URL);
				documentURL = "/browser/dist/cool.html?WOPISrc="
						+ URLCodec.encodeURL(hostUrl)
						+ PortalUtil.getPathContext()
						+ "/c%2Fcommon%2Fwopi%2Ffiles%2F"
						+ URLCodec.encodeURL(wopiParam)
						+ "&access_token=" + token;

				break;
			default:
				documentURL =
						PortalUtil.getPathContext()
						+ "/documents/"
						+ file.getRepositoryId() + StringPool.SLASH
						+ file.getFolderId() + StringPool.SLASH
						+ file.getTitle() + StringPool.SLASH
						+ file.getUuid() + StringPool.QUESTION
						+ "version=" + file.getVersion();

				// Add parameter in URL in case of video/audio conversion is needed
				if (typeOfView.equals("Video") && !file.getExtension().equals("mp4")) {
					documentURL = documentURL + "&targetExtension=mp4";
				}
				break;
		}

		return documentURL;
	}

	public String getDownloadUrl (FileEntry file) {
		return 	PortalUtil.getPathContext()
				+ "/documents/"
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
				throw new FileExtensionException("Unknown lool type" + type);
		}

		return DLAppUtil.addFileEntry(user, folder, name + "." + type, byteArrayContent, DocumentConstants.MODE_RENAME);
	}

	public String sanitizeHTMLContent(String content) {
		// Do not allow style on a tag > click-jacking vulnerability (https://stackoverflow.com/questions/4546591/xss-attacks-and-style-attributes)
		String sanitizedContent;
		final String titleTag = "title";
		final String iframeTag = "iframe";
		final String styleTag = "style";
		final String bodyOpening = "<body";

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
		Elements iframes = doc.select(iframeTag);
		for (Element iframe : iframes) {
			try {
				if (!ContentBlockLocalServiceUtil.isEmbedUrlWhitelisted(iframe.attr("src"))) {
					iframe.remove();
				}
			} catch (UnauthorizedUrlException e) {
				logger.error(e);
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
            int bodyIndex = content.indexOf(bodyOpening);

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
			content = content.replace(bodyOpening, "<hbody");
			content = content.replace("</body", "</hbody");

			String postcontent = header + Jsoup.clean(content, PropsUtil.get(NeroSystemProperties.PORTAL_URL),
					Safelist.relaxed()
							.preserveRelativeLinks(true)
							.removeTags(titleTag)
							.addTags("hbody")
							.addTags("address")
							.addTags("center")
							.addTags(styleTag)
							.addTags("audio, video, source, code")
							.addTags(iframeTag)
							.addAttributes(":all", styleTag, "class", titleTag, "height", "width", "id", "aria-readonly")
							.addAttributes("img", "src")
							.addAttributes("audio", "controls", "src")
							.addAttributes("video", "controls", "src")
							.addAttributes("source", "type", "src")
							.addAttributes(iframeTag, "src", "height", "width", titleTag, "scrolling", "name", "frameborder", "longdesc", "allow", "allowfullscreen", styleTag)
							.addAttributes("a", "href", "target")
							.addAttributes("table", "border", "cellpadding", "cellspacing", "align")
							.addProtocols("img", "src", "http", "https", "data")
							.addProtocols("a", "href", "#")) + footer;

			postcontent = postcontent.replace("<hbody", bodyOpening);
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

	public JSONObject format(long userId, FileEntry fileEntry) throws PortalException, SystemException {
		int space = DocumentUtil.getSpace(fileEntry, userId);
		return format(userId, fileEntry, space);
	}

	public JSONObject format(long userId, FileEntry fileEntry, int space) throws PortalException, SystemException {
		return format(userId, fileEntry, space, false);
	}

	public JSONObject format(long userId, FileEntry fileEntry, int space, boolean withDetails) throws PortalException, SystemException {
		User user = UserLocalServiceUtil.getUser(userId);
		return format(user, fileEntry, space,withDetails);
	}

	public JSONObject format(User user, FileEntry fileEntry, int space, boolean withDetails) {
		JSONObject formattedFile = new JSONObject();

		addCommonsFields(formattedFile, fileEntry, user, withDetails);

		if (space == DocumentConstants.COLLABORATIVE) {
			addGroupFields(formattedFile, fileEntry, user);
		}

		return formattedFile;
	}

	public JSONObject formatWithOnlyMandatoryFields (long fileId) {
		try {
			return formatWithOnlyMandatoryFields (DLAppServiceUtil.getFileEntry(fileId));
		} catch (PortalException e) {
			logger.error(e);
			return new JSONObject();
		}
	}

	public JSONObject formatWithOnlyMandatoryFields (FileEntry fileEntry) {
		JSONObject formattedFolder = new JSONObject();

		addMandatoryFields(formattedFolder, fileEntry);

		return formattedFolder;
	}

	private void addMandatoryFields (JSONObject formattedFile, FileEntry fileEntry) {
		formattedFile.put(JSONConstants.ID, String.valueOf(fileEntry.getFileEntryId()));
		formattedFile.put(JSONConstants.NAME, fileEntry.getTitle());
		formattedFile.put(JSONConstants.TYPE, "File");
		formattedFile.put(JSONConstants.SIZE, (int) fileEntry.getSize());
		formattedFile.put(JSONConstants.EXTENSION, fileEntry.getExtension().toLowerCase());
		formattedFile.put(JSONConstants.LAST_MODIFIED_DATE, new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).format(fileEntry.getModifiedDate()));
		formattedFile.put(JSONConstants.URL, FileUtilsLocalServiceUtil.getDownloadUrl(fileEntry));
	}

	private void addCommonsFields(JSONObject formattedFile, FileEntry fileEntry, User user, boolean withDetails) {
		addMandatoryFields(formattedFile, fileEntry);

		// Permissions
		if (user != null) { // No specific user for News attachedFiles or what (maybe we should?)
			final JSONObject permissions = new JSONObject();
			permissions.put(ActionKeys.UPDATE, PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.UPDATE));
			permissions.put(ActionKeys.DELETE, PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.DELETE));
			permissions.put(ActionKeys.PERMISSIONS, PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.PERMISSIONS));
			formattedFile.put(JSONConstants.PERMISSIONS, permissions);
		} else {
			final JSONObject permissions = new JSONObject();
			permissions.put(ActionKeys.UPDATE, false);
			permissions.put(ActionKeys.DELETE, false);
			permissions.put(ActionKeys.PERMISSIONS, false);
			formattedFile.put(JSONConstants.PERMISSIONS, permissions);
		}

		if (withDetails) {
			formattedFile.put(JSONConstants.CREATION_DATE,
					new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).format(fileEntry.getCreateDate()));
			formattedFile.put(JSONConstants.CREATOR, fileEntry.getUserName());
			formattedFile.put(JSONConstants.VERSION, fileEntry.getVersion());
		}
	}

	private void addGroupFields(JSONObject formattedFile, FileEntry fileEntry, User user) {
		formattedFile.put(JSONConstants.IS_GROUP_FILE, true);
		final JSONObject permissions = new JSONObject();
		// Directors and school admins have all rights on institutional groups
		boolean hasFullPermissions = false;
		try {
			Group group = GroupLocalServiceUtil.getGroup(fileEntry.getGroupId());
			hasFullPermissions = group.isOrganization() && RoleUtilsLocalServiceUtil.isSchoolAdmin(user);
		} catch (Exception e) {
			logger.debug(e);
		}

		permissions.put(ActionKeys.UPDATE, hasFullPermissions || PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.UPDATE));
		permissions.put(ActionKeys.DELETE, hasFullPermissions || PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.DELETE));
		permissions.put(ActionKeys.PERMISSIONS, hasFullPermissions || PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.PERMISSIONS));
		formattedFile.put(JSONConstants.PERMISSIONS, permissions);
	}

}