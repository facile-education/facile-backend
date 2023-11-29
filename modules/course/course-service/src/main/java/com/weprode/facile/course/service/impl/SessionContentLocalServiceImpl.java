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

package com.weprode.facile.course.service.impl;

import com.liferay.document.library.kernel.exception.NoSuchFolderException;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.course.CourseConstants;
import com.weprode.facile.course.exception.UnauthorizedUrlException;
import com.weprode.facile.course.model.ContentBlock;
import com.weprode.facile.course.model.SessionContent;
import com.weprode.facile.course.service.ContentBlockLocalServiceUtil;
import com.weprode.facile.course.service.base.SessionContentLocalServiceBaseImpl;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.facile.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.schedule.model.CDTSession;
import com.weprode.facile.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.facile.schedule.service.SessionTeacherLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Cédric Lecarpentier
 */
@Component(
	property = "model.class.name=com.weprode.facile.course.model.SessionContent",
	service = AopService.class
)
public class SessionContentLocalServiceImpl extends SessionContentLocalServiceBaseImpl {

	private final Log logger = LogFactoryUtil.getLog(SessionContentLocalServiceImpl.class);

	public List<SessionContent> getCourseContents(User user, long courseId, Date minDate, Date maxDate) {

		List<SessionContent> courseContents = new ArrayList<>();

		List<SessionContent> sessionContents = sessionContentFinder.getCourseContents(courseId, minDate, maxDate);
		// Loop to filter with publication policy
		for (SessionContent content : sessionContents) {
			if (RoleUtilsLocalServiceUtil.isTeacher(user) || (!content.getIsDraft() && content.getPublicationDate().before(new Date()))) {
				courseContents.add(content);
			}
		}
		return courseContents;
	}

	@Indexable(type = IndexableType.REINDEX)
	public SessionContent addSessionContent(long sessionId, long teacherId, String title, Date publicationDate, boolean isDraft) throws SystemException {

		SessionContent sessionContent = sessionContentPersistence.create(sessionId);
		sessionContent.setCompanyId(PortalUtil.getDefaultCompanyId());
		sessionContent.setTeacherId(teacherId);
		sessionContent.setModificationDate(new Date());
		sessionContent.setTitle(title);
		sessionContent.setPublicationDate(publicationDate); // TODO: Check shift between browser date and server date (UTC+2 and UTC)
		sessionContent.setIsDraft(isDraft);
		sessionContent = sessionContentPersistence.update(sessionContent);

		// Set the modification date to the teacher for activity matching
		SessionTeacherLocalServiceUtil.updateModificationDate(teacherId, sessionId);

		// Create folder to avoid error when adding multiple files
		try {
			getSessionFolder(sessionContent.getSessionId());
		} catch (Exception e) {
			logger.error("Could not create folder when initializing sessionContent document folder for sessionId " + sessionContent.getSessionId(), e);
		}

		return sessionContent;
	}

	@Indexable(type = IndexableType.REINDEX)
	public SessionContent updateSessionContent (long teacherId, long sessionId, String title, JSONArray blocks, Date publicationDate, boolean isDraft) throws SystemException, PortalException, UnauthorizedUrlException, IOException {
		SessionContent sessionContent = sessionContentPersistence.findByPrimaryKey(sessionId);
		sessionContent.setModificationDate(new Date());
		sessionContent.setTitle(title);
		sessionContent.setTeacherId(teacherId);
		sessionContent.setPublicationDate(publicationDate); // TODO: Check shift between browser date and server date (UTC+2 and UTC)
		sessionContent.setIsDraft(isDraft);

		// Set the modification date to the teacher for activity matching
		SessionTeacherLocalServiceUtil.updateModificationDate(teacherId, sessionId);

		// Delete previous blocks
		ContentBlockLocalServiceUtil.deleteBlocksByItemId(sessionId);

		// Add blocks
		if (blocks != null) {
			for (int i = 0 ; i < blocks.length() ; i++) {
				JSONObject jsonBlock = blocks.getJSONObject(i);
				// This is a block creation
				ContentBlockLocalServiceUtil.addBlock(teacherId, sessionId,
						jsonBlock.getInt(JSONConstants.CONTENT_TYPE),
						jsonBlock.getString(JSONConstants.CONTENT_NAME),
						JSONConstants.getStringValue(jsonBlock, JSONConstants.CONTENT_VALUE, ""),
						JSONConstants.getLongValue(jsonBlock, JSONConstants.FILE_ID, 0));
			}
		}

		return sessionContentPersistence.update(sessionContent);
	}

	@Indexable(type = IndexableType.DELETE)
	public void deleteContent (long sessionId) throws PortalException, SystemException {

		// Delete folder corresponding to this session
		Folder courseFolder = FolderUtilsLocalServiceUtil.getGroupCourseFolder(sessionId);
		try {
			Folder courseItemFolder = FolderUtilsLocalServiceUtil.getFolderByName(courseFolder, String.valueOf(sessionId));
			DLAppServiceUtil.deleteFolder(courseItemFolder.getFolderId());
		} catch (Exception e) {
			// logger.info("Nothing to delete, " + e.getMessage());
		}
		sessionContentPersistence.remove(sessionId);

		// Delete the associated session contents
		ContentBlockLocalServiceUtil.deleteBlocksByItemId(sessionId);
	}

	// itemId can be either a sessionId or a homeworkId
	public String convertContentAsHtml (long sessionId) {

		StringBuilder itemHtml = new StringBuilder();

		// We use a copy to be able to sort it
		List<ContentBlock> blocks = new ArrayList<>(ContentBlockLocalServiceUtil.getContentsByItemId(sessionId));

		// Order contents
		blocks.sort(Comparator.comparingInt(ContentBlock::getOrder));

		boolean isH5pScriptImported = false;
		for (ContentBlock block : blocks) {
			// Append once the h5p script
			if (block.getBlockType() == CourseConstants.TYPE_H5P && !isH5pScriptImported) {
				itemHtml.insert(0, "<script src=\"https://h5p.eduge.ch/modules/contrib/h5p/vendor/h5p/h5p-core/js/h5p-resizer.js\" charset=\"UTF-8\"></script>");
				isH5pScriptImported = true;
			}
			itemHtml.append(ContentBlockLocalServiceUtil.convertBlockToHtml(block.getBlockId())).append("<br/><br/>");
		}

		return itemHtml.toString();
	}

	public Folder getSessionFolder(long sessionId) throws PortalException, SystemException {

		CDTSession session = CDTSessionLocalServiceUtil.getCDTSession(sessionId);
		Folder courseFolder = FolderUtilsLocalServiceUtil.getGroupCourseFolder(session.getGroupId());

		Folder sessionFolder = null;
		try {
			sessionFolder = FolderUtilsLocalServiceUtil.getFolderByName(courseFolder, String.valueOf(sessionId));
		} catch (NoSuchFolderException e) {
			sessionFolder = DLAppServiceUtil.addFolder(
					UUID.randomUUID().toString(),
					courseFolder.getGroupId(),
					courseFolder.getFolderId(),
					String.valueOf(sessionId),
					"Dossier de la séance " + sessionId,
					new ServiceContext()
			);
		} catch (Exception e) {
			logger.error("Error when fetching folder for sessionId " + sessionId, e);
		}
		// Apply default permissions so that students can VIEW
		PermissionUtilsLocalServiceUtil.addDefaultPermissionsFolder(sessionFolder);

		return sessionFolder;
	}

	public boolean hasSessionContent(long sessionId) {
		return sessionContentPersistence.fetchByPrimaryKey(sessionId) != null;
	}

}