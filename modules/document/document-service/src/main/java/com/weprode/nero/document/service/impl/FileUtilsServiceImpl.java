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
import com.weprode.nero.commons.JSONProxy;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.document.constants.DocumentConstants;
import com.weprode.nero.document.model.EditionLock;
import com.weprode.nero.document.model.LoolToken;
import com.weprode.nero.document.service.EditionLockLocalServiceUtil;
import com.weprode.nero.document.service.FileUtilsLocalServiceUtil;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.nero.document.service.LoolTokenLocalServiceUtil;
import com.weprode.nero.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.nero.document.service.base.FileUtilsServiceBaseImpl;
import com.weprode.nero.document.utils.DLAppJsonFactory;
import com.weprode.nero.document.utils.SupportedExtensions;
import com.weprode.nero.document.utils.UploadUtil;
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
			if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), folderId)) {
				logger.info("User " + user.getFullName() + " tries to upload a file into folder " + folderId + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			logger.info("User " + user.getFullName() + " uploads file " + fileName + " to folderId " + folderId + " in mode " + mode);
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
			if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			Folder tmpFolder = FolderUtilsLocalServiceUtil.getTmpFolder(user.getUserId());
			int mode = DocumentConstants.MODE_REPLACE;
			logger.info("User " + user.getFullName() + " uploads tmp file " + fileName + " to folderId " + tmpFolder.getFolderId() + " in mode " + mode);

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
			if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			FileEntry fileToRename = DLAppServiceUtil.getFileEntry(fileId);
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), fileToRename.getFolderId())) {
				logger.info("User " + user.getFullName() + " tries to rename file " + fileId + " in folder " + fileToRename.getFolderId() + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			logger.info("User " + user.getFullName() + " renames file " + fileId + " with name " + fileName);
			FileEntry renamedFile = FileUtilsLocalServiceUtil.renameFile(user.getUserId(), fileToRename, fileName);

			result.put(JSONConstants.FILE, DLAppJsonFactory.format(user.getUserId(), renamedFile));
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
			if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), folderId)) {
				logger.info("User " + user.getFullName() + " tries to create audio file in folder " + folderId + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			logger.info("User " + user.getFullName() + " creates audio file " + name + ".mp3 in folder " + folderId);

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
			if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), folderId)) {
				logger.info("User " + user.getFullName() + " tries to create geogebra file in folder " + folderId + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			logger.info("User " + user.getFullName() + " creates geogebra file " + name + " in folder " + folderId);

			FileEntry geogebraFile = FileUtilsLocalServiceUtil.createGeogebraFile(user, folderId, name);

			result.put(JSONConstants.FILE, DLAppJsonFactory.format(user.getUserId(), geogebraFile, DocumentConstants.PRIVATE));
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
			if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), folderId)) {
				logger.info("User " + user.getFullName() + " tries to create mindmap file in folder " + folderId + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			logger.info("User " + user.getFullName() + " creates mindmap file " + name + " in folder " + folderId);

			FileEntry mindmapFile = FileUtilsLocalServiceUtil.createMindMapFile(user, folderId, name);

			result.put(JSONConstants.FILE, DLAppJsonFactory.format(user.getUserId(), mindmapFile, DocumentConstants.PRIVATE));
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
			if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), folderId)) {
				logger.info("User " + user.getFullName() + " tries to create scratch file in folder " + folderId + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			logger.info("User " + user.getFullName() + " creates scratch file " + name + " in folder " + folderId);

			FileEntry scratchFile = FileUtilsLocalServiceUtil.createScratchFile(user, folderId, name);

			result.put(JSONConstants.FILE, DLAppJsonFactory.format(user.getUserId(), scratchFile, DocumentConstants.PRIVATE));
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
			if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), folderId)) {
				logger.info("User " + user.getFullName() + " tries to create lool file in folder " + folderId + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			logger.info("User " + user.getFullName() + " creates lool file " + name + " in folder " + folderId + " with type " + type);

			FileEntry loolFile = FileUtilsLocalServiceUtil.createLoolFile(user, folderId, name, type);

			result.put(JSONConstants.FILE, DLAppJsonFactory.format(user.getUserId(), loolFile, DocumentConstants.PRIVATE));
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
			if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), folderId)) {
				logger.info("User " + user.getFullName() + " tries to create html file in folder " + folderId + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			logger.info("User " + user.getFullName() + " creates html file " + name + " in folder " + folderId);

			FileEntry htmlFile = FileUtilsLocalServiceUtil.createHtmlFile(user, folderId, name);

			result.put(JSONConstants.FILE, DLAppJsonFactory.format(user.getUserId(), htmlFile, DocumentConstants.PRIVATE));
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
			if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		try {
			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileId);
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), fileEntry.getFolderId())) {
				logger.info("User " + user.getFullName() + " tries to add lock on file " + fileId + " in folder " + fileEntry.getFolderId() + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
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

			// Add new lock
			logger.info("Adding lock on fileId " + fileId + " for userId " + user.getUserId());
			EditionLockLocalServiceUtil.addEditionLock(fileId, user.getUserId());
			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			result.put(JSONConstants.SUCCESS, false);
			logger.error("Could not handle lock", e);
		}

		return result;
	}

	@JSONWebService(method = "GET")
	public JSONObject removeLock(long fileId) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileId);
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), fileEntry.getFolderId())) {
				logger.info("User " + user.getFullName() + " tries to unlock file " + fileId + " in folder " + fileEntry.getFolderId() + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
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
			if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileId);
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), fileEntry.getFolderId())) {
				logger.info("User " + user.getFullName() + " tries to get resource of fileEntryId " + fileId + " with version " + versionId + ", readOnly=" + readOnly + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			logger.info("User " + user.getUserId() + " fetches resource " + fileId + " with version " + versionId + ", readOnly=" + readOnly);

			if (versionId == 0) {   // If the version is specified to 0, get the latest version
				versionId = fileEntry.getLatestFileVersion().getFileVersionId();
			}

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

	@JSONWebService(method = "POST")
	public JSONObject removeLoolToken(String token) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			logger.info("User " + user.getFullName() + " removes LOOL token " + token);
			LoolToken loolToken = LoolTokenLocalServiceUtil.getLoolToken(token);

			// Check if the current user is the owner of the token to delete
			if (user.getUserId() == loolToken.getUserId()) {
				// Comment lool token deletion because Wopi can still perform actions with this token
				// TODO add a date to LoolTokens and cron their deletion at night
				//LoolTokenLocalServiceUtil.deleteLoolToken(loolToken.getLoolTokenId());
				result.put(JSONConstants.SUCCESS, true);
			} else {
				logger.error("User " + user.getFullName() + " is not allowed to delete token " + loolToken.getLoolTokenId());
			}
		} catch (Exception e) {
			logger.error("Error removing lool token " + token, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

}