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

package com.weprode.nero.course.service.impl;

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
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.course.CourseConstants;
import com.weprode.nero.course.exception.UnauthorizedUrlException;
import com.weprode.nero.course.model.ContentBlock;
import com.weprode.nero.course.model.SessionContent;
import com.weprode.nero.course.service.ContentBlockLocalServiceUtil;
import com.weprode.nero.course.service.base.SessionContentLocalServiceBaseImpl;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.SessionTeacherLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author Cédric Lecarpentier
 */
@Component(
	property = "model.class.name=com.weprode.nero.course.model.SessionContent",
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

		SessionContent sessionContent = sessionContentPersistence.create(counterLocalService.increment());
		sessionContent.setCompanyId(PortalUtil.getDefaultCompanyId());
		sessionContent.setTeacherId(teacherId);
		sessionContent.setModificationDate(new Date());
		sessionContent.setTitle(title);
		sessionContent.setPublicationDate(publicationDate);
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
		sessionContent.setPublicationDate(publicationDate);
		sessionContent.setIsDraft(isDraft);

		// Set the modification date to the teacher for activity matching
		SessionTeacherLocalServiceUtil.updateModificationDate(teacherId, sessionId);

		// Delete previous blocks
		ContentBlockLocalServiceUtil.deleteBlocksByItemId(sessionId);

		// Add blocks
		if (blocks != null) {
			for (int i = 0 ; i < blocks.length() ; i++) {
				JSONObject jsonBlock = blocks.getJSONObject(i);
				if (!jsonBlock.has(JSONConstants.BLOCK_ID)) {
					// This is a block creation
					ContentBlockLocalServiceUtil.addBlock(teacherId, sessionId,
							jsonBlock.getInt(JSONConstants.BLOCK_TYPE),
							jsonBlock.getString(JSONConstants.BLOCK_NAME),
							jsonBlock.getString(JSONConstants.BLOCK_VALUE),
							jsonBlock.getLong(JSONConstants.FILE_ENTRY_ID));
				} else {
					// This is a block update
					ContentBlockLocalServiceUtil.updateBlock(jsonBlock.getLong(JSONConstants.BLOCK_ID),
							jsonBlock.getString(JSONConstants.BLOCK_NAME),
							jsonBlock.getString(JSONConstants.BLOCK_VALUE),
							jsonBlock.getInt(JSONConstants.ORDER));
				}
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
					courseFolder.getGroupId(),
					courseFolder.getFolderId(),
					String.valueOf(sessionId),
					"",
					new ServiceContext()
			);
		} catch (Exception e) {
			logger.error("Error when fetching folder for sessionId " + sessionId, e);
		}

		return sessionFolder;
	}

	public boolean hasSessionContent(long sessionId) {
		return sessionContentPersistence.fetchByPrimaryKey(sessionId) != null;
	}

}