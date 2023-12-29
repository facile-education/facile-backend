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

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.weprode.facile.document.service.DocumentUtilsLocalServiceUtil;
import com.weprode.facile.document.service.FileUtilsLocalServiceUtil;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.facile.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.facile.document.service.base.ThumbnailsLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.io.IOException;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.weprode.facile.document.model.Thumbnails",
	service = AopService.class
)
public class ThumbnailsLocalServiceImpl extends ThumbnailsLocalServiceBaseImpl {

	private final Log logger = LogFactoryUtil.getLog(ThumbnailsLocalServiceImpl.class);

	public FileEntry createThumbnailFile(long userId, long sourceFileId, String thumbnailName) throws PortalException, IOException {
		// Get original file
		FileEntry originalPicture = DLAppServiceUtil.getFileEntry(sourceFileId);

		// Get or create news thumbnail folder
		Folder thumbnailFolder = FolderUtilsLocalServiceUtil.getThumbnailFolder();

		// Copy (or move if original file belong to user tempFolder) file to thumbnail folder
		FileEntry thumbnail;
		if (DocumentUtilsLocalServiceUtil.belongToTmpFolder(originalPicture, userId)) {
			logger.info("Move fileEntry " + sourceFileId + "from temp folder to thumbnails folder, mode rename");
			thumbnail = DLAppServiceUtil.moveFileEntry(
					sourceFileId,
					thumbnailFolder.getFolderId(),
					new ServiceContext()
			);
		} else {
			thumbnail = FileUtilsLocalServiceUtil.copyFileEntry(
					userId,
					originalPicture.getFileEntryId(),
					thumbnailFolder.getFolderId(),
					true
			);
		}
		thumbnail = FileUtilsLocalServiceUtil.renameFile(userId, thumbnail, thumbnailName);  // Rename thumbnail with the newsId value
		PermissionUtilsLocalServiceUtil.setViewPermissionOnResource(thumbnail); // All ent users can view any thumbnail file
		return thumbnail;
	}

	public void deleteThumbnailFile(long thumbnailId) {
		try {
			DLAppServiceUtil.deleteFileEntry(thumbnailId);
		} catch (Exception e) {
			logger.error("Cannot delete file entry " + thumbnailId, e);
		}
	}
}