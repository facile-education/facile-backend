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

import com.liferay.document.library.kernel.exception.FileExtensionException;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.lock.exception.NoSuchLockException;
import com.liferay.portal.lock.model.Lock;
import com.liferay.portal.lock.service.LockLocalServiceUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.document.constants.DocumentConstants;
import com.weprode.facile.document.model.EditionLock;
import com.weprode.facile.document.model.LoolToken;
import com.weprode.facile.document.service.EditionLockLocalServiceUtil;
import com.weprode.facile.document.service.FileUtilsLocalServiceUtil;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.facile.document.service.LoolTokenLocalServiceUtil;
import com.weprode.facile.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.facile.document.service.base.FileUtilsServiceBaseImpl;
import com.weprode.facile.document.utils.SupportedExtensions;
import com.weprode.facile.document.utils.UploadUtil;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.io.File;

@Component(
	property = {
		"json.web.service.context.name=document",
		"json.web.service.context.path=FileUtils"
	},
	service = AopService.class
)
public class FileUtilsServiceImpl extends FileUtilsServiceBaseImpl {

	private final Log logger = LogFactoryUtil.getLog(FileUtilsServiceImpl.class);

	@JSONWebService(method = "POST")
	public JSONObject uploadFile(long folderId, String fileName, File file, int mode) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), folderId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " uploads file to folder " + folderId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			Folder folder = DLAppServiceUtil.getFolder(folderId);
			if (!PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.VIEW) && !PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.ADD_DOCUMENT)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " uploads file to folder " + folderId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			return UploadUtil.uploadFile(user, folderId, fileName, file, mode);
		} catch (Exception e) {
			logger.error("Error uploading file "+ fileName + " to folderId " + folderId + " in mode " + mode, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(method = "POST")
	public JSONObject uploadTmpFile(String fileName, File file) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			Folder tmpFolder = FolderUtilsLocalServiceUtil.getUserTmpFolder(user.getUserId());
			int mode = DocumentConstants.MODE_REPLACE;

			return UploadUtil.uploadFile(user, tmpFolder.getFolderId(), fileName, file, mode, true);
		} catch (Exception e) {
			logger.error("Error uploading temp file "+ fileName, e);
			result.put("success", false);
		}

		return result;
	}

	@JSONWebService(method = "POST")
	public JSONObject renameFile(long fileId, String fileName) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			FileEntry fileToRename = DLAppServiceUtil.getFileEntry(fileId);
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), fileToRename.getFolderId())) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " renames file " + fileId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			FileEntry renamedFile = FileUtilsLocalServiceUtil.renameFile(user.getUserId(), fileToRename, fileName);
			result.put(JSONConstants.FILE, FileUtilsLocalServiceUtil.format(user.getUserId(), renamedFile));
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error renaming file " + fileId + " with name " + fileName, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}


	@JSONWebService(method = "POST")
	public JSONObject createAudioFile(long folderId, String name, File file) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), folderId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " creates audio file");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			Folder folder = DLAppServiceUtil.getFolder(folderId);
			if (!PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.VIEW) && !PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.ADD_DOCUMENT)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " creates audio file");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			return UploadUtil.uploadFile(user, folderId, name + ".mp3", file, DocumentConstants.MODE_RENAME);

		} catch (Exception e) {
			logger.error(e);
		}
		result.put(JSONConstants.SUCCESS, false);

		return result;
	}


	@JSONWebService(method = "POST")
	public JSONObject createGeogebraFile(long folderId, String name) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), folderId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " creates geogebra file");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			Folder folder = DLAppServiceUtil.getFolder(folderId);
			if (!PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.VIEW) && !PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.ADD_DOCUMENT)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " creates geogebra file");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			FileEntry geogebraFile = FileUtilsLocalServiceUtil.createGeogebraFile(user, folderId, name);

			result.put(JSONConstants.FILE, FileUtilsLocalServiceUtil.format(user.getUserId(), geogebraFile, DocumentConstants.PRIVATE));
			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error("Error creating geogebra file " + name + " in folder " + folderId, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(method = "POST")
	public JSONObject createMindmapFile(long folderId, String name) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), folderId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " creates mindmap file");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			Folder folder = DLAppServiceUtil.getFolder(folderId);
			if (!PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.VIEW) && !PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.ADD_DOCUMENT)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " creates mindmap file");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			FileEntry mindmapFile = FileUtilsLocalServiceUtil.createMindMapFile(user, folderId, name);

			result.put(JSONConstants.FILE, FileUtilsLocalServiceUtil.format(user.getUserId(), mindmapFile, DocumentConstants.PRIVATE));
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error creating mindmap file" + name + " in folder " + folderId, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(method = "POST")
	public JSONObject createScratchFile(long folderId, String name) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), folderId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " creates scratch file");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			Folder folder = DLAppServiceUtil.getFolder(folderId);
			if (!PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.VIEW) && !PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.ADD_DOCUMENT)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " creates scratch file");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			FileEntry scratchFile = FileUtilsLocalServiceUtil.createScratchFile(user, folderId, name);

			result.put(JSONConstants.FILE, FileUtilsLocalServiceUtil.format(user.getUserId(), scratchFile, DocumentConstants.PRIVATE));
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error creating scratch file " + name + " in folder " + folderId, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(method = "POST")
	public JSONObject createLoolFile(long folderId, String name, String type) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), folderId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " creates lool file");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			Folder folder = DLAppServiceUtil.getFolder(folderId);
			if (!PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.VIEW) && !PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.ADD_DOCUMENT)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " creates lool file");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			FileEntry loolFile = FileUtilsLocalServiceUtil.createLoolFile(user, folderId, name, type);

			result.put(JSONConstants.FILE, FileUtilsLocalServiceUtil.format(user.getUserId(), loolFile, DocumentConstants.PRIVATE));
			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error("Error creating lool file " + name + " in folder " + folderId + " with type " + type, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(method = "POST")
	public JSONObject createHTMLFile(long folderId, String name) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), folderId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " creates html file");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			Folder folder = DLAppServiceUtil.getFolder(folderId);
			if (!PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.VIEW) && !PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.ADD_DOCUMENT)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " creates html file");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			FileEntry htmlFile = FileUtilsLocalServiceUtil.createHtmlFile(user, folderId, name);

			result.put(JSONConstants.FILE, FileUtilsLocalServiceUtil.format(user.getUserId(), htmlFile, DocumentConstants.PRIVATE));
			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error("Error creating html file " + name + " in folder " + folderId, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(method = "POST")
	public JSONObject addLock(long fileId) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		try {
			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileId);
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), fileEntry.getFolderId())) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " adds a lock");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			if (!PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.VIEW) && !PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.UPDATE)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " adds a lock");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			EditionLock lock = EditionLockLocalServiceUtil.isFileEdited(fileId);
			if (lock != null) {
				if (user.getUserId() == lock.getUserId()) {
					logger.info("Locked file is already edited by same user");
					result.put(JSONConstants.SUCCESS, true);
				} else {
					result.put(JSONConstants.IS_WRITABLE, false);
					result.put(JSONConstants.USER_ID, lock.getUserId());
					User editor = UserLocalServiceUtil.getUser(lock.getUserId());
					result.put(JSONConstants.USER_NAME, editor.getFullName());
					result.put(JSONConstants.SUCCESS, false);
				}
				return result;
			} else {
				try {
					Lock lfrLock = LockLocalServiceUtil.getLock(DLFileEntry.class.getName(), fileId);
					if (Validator.isNotNull(lfrLock) && lfrLock.getUserId() != user.getUserId() &&
							DLAppServiceUtil.verifyFileEntryCheckOut(fileEntry.getRepositoryId(), fileEntry.getFileEntryId(), lfrLock.getUuid())) {

						result.put(JSONConstants.IS_WRITABLE, false);
						result.put(JSONConstants.SUCCESS, false);
						return result;
					}
				} catch (NoSuchLockException e) {
					// No Lfr lock, nothing to do here.
				}
			}

			EditionLockLocalServiceUtil.addEditionLock(fileId, user.getUserId());
			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			result.put(JSONConstants.SUCCESS, false);
			logger.error("Could not handle lock", e);
		}

		return result;
	}

	@JSONWebService(method = "DELETE")
	public JSONObject removeLock(long fileId) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileId);
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), fileEntry.getFolderId())) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " removes a lock");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			if (!PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.VIEW) && !PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.UPDATE)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " removes a lock");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			EditionLock lock = EditionLockLocalServiceUtil.isFileEdited(fileId);
			if (lock != null && lock.getUserId() == user.getUserId()) {
				logger.info("Removing lock on fileId " + fileId + " for userId " + user.getUserId());
				result.put(JSONConstants.SUCCESS, EditionLockLocalServiceUtil.removeEditionLock(fileId));
			} else {
				logger.info("Cannot remove lock on fileId " + fileId + " for userId " + user.getUserId());
				result.put(JSONConstants.SUCCESS, false);
			}

		} catch (Exception e) {
			result.put(JSONConstants.SUCCESS, false);
			logger.error("Could not handle lock", e);
		}

		return result;
	}

	@JSONWebService(method = "GET")
	public JSONObject getResource(long fileId, long versionId, boolean readOnly) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileId);
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), fileEntry.getFolderId())) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " gets resource " + fileId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			if (!PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.VIEW)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " gets resource " + fileId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			if (versionId == 0) {   // If the version is specified to 0, get the latest version
				versionId = fileEntry.getLatestFileVersion().getFileVersionId();
			}

			result.put(JSONConstants.FILE_NAME, fileEntry.getFileName());

			boolean isReadOnly = readOnly
					|| versionId != fileEntry.getLatestFileVersion().getFileVersionId()   // read only if there is not the latest version
					|| (FileUtilsLocalServiceUtil.isGroupFile(fileId) && !PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.UPDATE)); // Or if group file without UPDATE permission
			result.put(JSONConstants.FILE_VERSION_ID, versionId);
			result.put(JSONConstants.READ_ONLY, isReadOnly);

			// Get the type of view to manage how to display file in front (Lool document, pdf, image, video...)
			String typeOfView = SupportedExtensions.getTypeOfView(fileEntry.getExtension());
			result.put(JSONConstants.TYPE_OF_VIEW, typeOfView);

			// Get the file URL to display the document
			String documentURL = FileUtilsLocalServiceUtil.getDisplayUrl(fileEntry, versionId, user.getUserId(), readOnly);
			result.put(JSONConstants.FILE_URL, documentURL);

			result.put(JSONConstants.SUCCESS, true);

		} catch (PrincipalException e) {
			logger.error("User " + user.getUserId() + " does not have the permission to VIEW file " + fileId, e);
			result.put(JSONConstants.ERROR, "PermissionException");
			result.put(JSONConstants.SUCCESS, false);
		} catch (FileExtensionException e) {
			logger.error(e.getMessage());
			result.put(JSONConstants.ERROR, "UnsupportedFileExtension");
			result.put(JSONConstants.SUCCESS, false);
		} catch (Exception e) {
			logger.error("Error fetching resource " + fileId + " with version " + versionId + ", readOnly=" + readOnly + " " + e.getMessage());
			result.put(JSONConstants.ERROR, "invalidFile");
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(method = "DELETE")
	public JSONObject removeLoolToken(String token) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			LoolToken loolToken = LoolTokenLocalServiceUtil.getLoolToken(token);

			// Check if the current user is the owner of the token to delete
			if (user.getUserId() != loolToken.getUserId()) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " deletes token");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			// Comment lool token deletion because Wopi can still perform actions with this token
			// A cron deletes them daily
			//LoolTokenLocalServiceUtil.deleteLoolToken(loolToken.getLoolTokenId());
			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error("Error removing lool token " + token, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

}