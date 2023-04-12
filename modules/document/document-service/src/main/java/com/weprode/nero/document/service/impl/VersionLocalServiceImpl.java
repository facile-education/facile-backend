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

import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.document.library.kernel.service.persistence.DLFileEntryUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.document.constants.DocumentConstants;
import com.weprode.nero.document.exception.NoSuchVersionException;
import com.weprode.nero.document.model.EditionLock;
import com.weprode.nero.document.model.Version;
import com.weprode.nero.document.service.EditionLockLocalServiceUtil;
import com.weprode.nero.document.service.FileUtilsLocalServiceUtil;
import com.weprode.nero.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.nero.document.service.VersionLocalServiceUtil;
import com.weprode.nero.document.service.base.VersionLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component(
	property = "model.class.name=com.weprode.nero.document.model.Version",
	service = AopService.class
)
public class VersionLocalServiceImpl extends VersionLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(VersionLocalServiceImpl.class);

	public JSONObject getFileVersions(User user, long fileId) {
		final JSONObject res = JSONFactoryUtil.createJSONObject();

		final JSONArray versionItems = JSONFactoryUtil.createJSONArray();

		try {
			logger.info("User " + user.getFullName() + " fetches the versions for fileId " + fileId);

			List<DLFileVersion> dlFileVersionList = DLFileVersionLocalServiceUtil.getFileVersions(fileId, WorkflowConstants.STATUS_ANY);

			for (DLFileVersion dlFileVersion : dlFileVersionList) {

				JSONObject curr = JSONFactoryUtil.createJSONObject();
				curr.put(JSONConstants.ID, dlFileVersion.getFileVersionId());
				curr.put(JSONConstants.NAME, dlFileVersion.getVersion());
				curr.put(JSONConstants.SIZE, (int) dlFileVersion.getSize());
				curr.put(JSONConstants.DATE, new SimpleDateFormat(DocumentConstants.DATE_FORMAT)
						.format(dlFileVersion.getCreateDate()));
				curr.put(JSONConstants.USER_NAME, dlFileVersion.getUserName());
				curr.put(JSONConstants.STATUS_BY_USER_NAME, dlFileVersion.getStatusByUserName());
				curr.put(JSONConstants.DESCRIPTION, dlFileVersion.getDescription());

				// Is current version ?
				boolean isCurrentVersion = DLFileVersionLocalServiceUtil.getLatestFileVersion(user.getUserId(), fileId).getFileVersionId() == dlFileVersion.getFileVersionId();
				curr.put(JSONConstants.IS_CURRENT_VERSION, isCurrentVersion);


				if (FileUtilsLocalServiceUtil.isGroupFile(fileId)) {
					Version fileVersion;
					try {
						fileVersion = VersionLocalServiceUtil.getVersionByFileEntryId(fileId, dlFileVersion.getFileVersionId());
					} catch (NoSuchVersionException e) {
						fileVersion = VersionLocalServiceUtil.addVersion(fileId, dlFileVersion.getFileVersionId(), "", 0, 0);
					}
					curr.put(JSONConstants.VIEW_COUNT, fileVersion.getViewCount());
					curr.put(JSONConstants.DOWNLOAD_COUNT, fileVersion.getDownloadCount());
				}

				versionItems.put(curr);
			}
		} catch (Exception e) {
			logger.error("Error in getFileVersions", e);
			res.put(JSONConstants.ERROR, e.getMessage());
			res.put(JSONConstants.SUCCESS, false);
		}
		res.put(JSONConstants.SUCCESS, true);
		res.put(JSONConstants.FILE_VERSIONS, versionItems);

		return res;
	}

	public boolean deleteVersion(User user, long fileEntryId, String versionNb) {
		try {
			logger.info("User " + user.getFullName() + " deletes the version " + versionNb + " for fileId " + fileEntryId);

			if (fileEntryId != 0) {
				FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);
				EditionLock lock = EditionLockLocalServiceUtil.isFileEdited(fileEntryId);
				boolean isLocked = (lock != null && lock.getUserId() != user.getUserId());

				if (PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.UPDATE) && !isLocked) {
					List<DLFileVersion> versionList = DLFileVersionLocalServiceUtil.getFileVersions(fileEntry.getFileEntryId(), WorkflowConstants.STATUS_ANY);
					
					if (versionList != null && versionList.size() > 1) {
						DLAppServiceUtil.deleteFileVersion(fileEntry.getFileEntryId(), versionNb);
					} else {
						// If only one version, delete the file itself
						FileUtilsLocalServiceUtil.deleteFile(user.getUserId(), fileEntry.getFileEntryId());
					}
				}
			}
			return true;

		} catch (Exception e) {
			logger.error("Error in deleteVersion", e);
		}

		return false;
	}

	public boolean restoreVersion (long fileVersionId) {

		try {
			// Restore version content and create a new major version
			DLFileVersion dlFileVersion = DLFileVersionLocalServiceUtil.getFileVersion(fileVersionId);

			// TODO get user permission to process the action or not
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setAddGroupPermissions(true);

			DLAppServiceUtil.updateFileEntry(
					dlFileVersion.getFileEntryId(),
					dlFileVersion.getTitle(),
					dlFileVersion.getMimeType(),
					dlFileVersion.getTitle(),
					StringPool.BLANK, // urlTitle
					dlFileVersion.getDescription(),
					StringPool.BLANK, // changeLog
					DLVersionNumberIncrease.MAJOR,
					dlFileVersion.getContentStream(false),
					dlFileVersion.getSize(),
					null,
					null,
					serviceContext
			);

			logger.info("Version " + fileVersionId + "  restored as the new latest version");

			return true;
		} catch (Exception e) {
			logger.error("Error while restoring versionId " + fileVersionId, e);
			return false;
		}
	}

	public boolean saveVersionDescription(long fileVersionId, String description) {

		try {
			DLFileVersion dlFileVersion = DLFileVersionLocalServiceUtil.getDLFileVersion(fileVersionId);
			dlFileVersion.setDescription(description);
			DLFileVersionLocalServiceUtil.updateDLFileVersion(dlFileVersion);
			
			return true;
		} catch (Exception e) {
			logger.error("Error while updating versionId " + fileVersionId + " comment", e);
		}

		return false;
	}

	public boolean createMajorVersion(User user, long fileEntryId) {
		try {
			FileEntry file = DLAppServiceUtil.getFileEntry(fileEntryId);

			logger.info("Closing file " + file.getTitle());

			// Check user permissions before creating version
			if (PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), file, ActionKeys.UPDATE)) {

				DLFileEntryUtil.clearCache(DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryId));
				logger.info("Creating major version for fileEntryId " + fileEntryId + " and user " + user.getUserId());
				DLFileVersion dlFileVersion = DLFileVersionLocalServiceUtil.getLatestFileVersion(user.getUserId(), fileEntryId);

				if (!dlFileVersion.getVersion().endsWith(".0") && !dlFileVersion.getVersion().equals(DLFileEntryConstants.PRIVATE_WORKING_COPY_VERSION)) {
					ServiceContext serviceContext = new ServiceContext();
					serviceContext.setAddGroupPermissions(true);

					DLAppServiceUtil.updateFileEntry(
							dlFileVersion.getFileEntryId(),
							dlFileVersion.getTitle(),
							dlFileVersion.getMimeType(),
							dlFileVersion.getTitle(),
							StringPool.BLANK, // urlTitle
							dlFileVersion.getDescription(),
							StringPool.BLANK, // changeLog
							DLVersionNumberIncrease.MAJOR,
							dlFileVersion.getContentStream(false),
							dlFileVersion.getSize(),
							null,
							null,
							serviceContext
					);

					// Delete minor file versions
					List<DLFileVersion> dlFileVersions = DLFileVersionLocalServiceUtil.getFileVersions(dlFileVersion.getFileEntryId(), WorkflowConstants.STATUS_ANY);
					for (DLFileVersion fileVersion : dlFileVersions) {
						if (!fileVersion.getVersion().endsWith(".0")) {
							DLAppServiceUtil.deleteFileVersion(fileEntryId, fileVersion.getVersion());
						}
					}
					DLFileEntryUtil.clearCache(DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryId));
				}
			}

			return true;
		} catch (Exception e) {
			logger.debug(e);
		}

		return false;
	}

	public void incrementDownloadCount (Version fileVersion) throws SystemException, PortalException {
		VersionLocalServiceUtil.update(
				fileVersion.getDlFileEntryId(),
				fileVersion.getVersionNumber(),
				fileVersion.getComment(),
				fileVersion.getDownloadCount() + 1,
				fileVersion.getViewCount()
		);
	}

	public void incrementViewCount (Version fileVersion) throws SystemException, PortalException {
		VersionLocalServiceUtil.update(
				fileVersion.getDlFileEntryId(),
				fileVersion.getVersionNumber(),
				fileVersion.getComment(),
				fileVersion.getDownloadCount(),
				fileVersion.getViewCount() + 1
		);
	}

	public Version addVersion() throws SystemException {
		final long fileVersionId = counterLocalService.increment();

		return versionPersistence.create(fileVersionId);
	}

	public Version addVersion(long dlFileEntryId, String versionNb, String comment, long downloadCount, long viewCount)
			throws SystemException {

		return addVersion(dlFileEntryId, Double.parseDouble(versionNb), comment, downloadCount, viewCount);
	}

	public Version addVersion(long dlFileEntryId, double versionNb, String comment, long downloadCount, long viewCount)
			throws SystemException {

		Version fileVersion = this.addVersion();

		fileVersion.setDlFileEntryId(dlFileEntryId);
		fileVersion.setVersionNumber(versionNb);
		fileVersion.setComment(comment);
		fileVersion.setDownloadCount(downloadCount);
		fileVersion.setViewCount(viewCount);

		versionPersistence.update(fileVersion);

		return fileVersion;
	}

	public Version update(long dlFileEntryId, String versionNb, String comment, long downloadCount, long viewCount) throws SystemException, PortalException {
		return update(dlFileEntryId, Double.parseDouble(versionNb), comment, downloadCount, viewCount);
	}

	public Version update(long dlFileEntryId, double versionNb, String comment, long downloadCount, long viewCount) throws SystemException, PortalException {
		Version fv;

		try {
			fv = versionPersistence.findBydlFileEntryId_versionNumber(dlFileEntryId, versionNb);
		} catch(Exception e){

			fv = addVersion(dlFileEntryId, versionNb, comment, downloadCount, viewCount);

			return fv;
		}

		fv.setComment(comment);
		fv.setDownloadCount(downloadCount);
		fv.setViewCount(viewCount);
		versionPersistence.update(fv);

		return fv;
	}

	public Version getVersionByFileEntryId(long dlFileEntryId, String versionNb) throws SystemException, PortalException {
		return getVersionByFileEntryId(dlFileEntryId, Double.parseDouble(versionNb));
	}

	public Version getVersionByFileEntryId(long dlFileEntryId, double versionNb) throws SystemException, PortalException {
		return versionPersistence.findBydlFileEntryId_versionNumber(dlFileEntryId, versionNb);
	}

	public void removeVersionByFileEntryId(long dlFileEntryId, String versionNb) throws SystemException, PortalException {
		removeVersionByFileEntryId(dlFileEntryId, Double.parseDouble(versionNb));
	}

	public void removeVersionByFileEntryId(long dlFileEntryId, double versionNb) throws SystemException, PortalException {
		versionPersistence.removeBydlFileEntryId_versionNumber(dlFileEntryId, versionNb);
	}

	public List<Version> getVersionByFileEntryId(long dlFileEntryId) throws SystemException {
		return versionPersistence.findBydlFileEntryId(dlFileEntryId);
	}

	public void removeVersionByFileEntryId(long dlFileEntryId) throws SystemException {
		versionPersistence.removeBydlFileEntryId(dlFileEntryId);
	}

}