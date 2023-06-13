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

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.JSONProxy;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.course.CourseConstants;
import com.weprode.nero.course.exception.UnauthorizedUrlException;
import com.weprode.nero.course.model.ContentBlock;
import com.weprode.nero.course.model.Homework;
import com.weprode.nero.course.service.ContentBlockLocalServiceUtil;
import com.weprode.nero.course.service.HomeworkLocalServiceUtil;
import com.weprode.nero.course.service.SessionContentLocalServiceUtil;
import com.weprode.nero.course.service.base.ContentBlockServiceBaseImpl;
import com.weprode.nero.document.service.FileUtilsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author Cédric Lecarpentier
 */
@Component(
	property = {
		"json.web.service.context.name=course",
		"json.web.service.context.path=ContentBlock"
	},
	service = AopService.class
)
public class ContentBlockServiceImpl extends ContentBlockServiceBaseImpl {

	private final Log logger = LogFactoryUtil.getLog(ContentBlockServiceImpl.class);

	@JSONWebService(value = "add-block", method = "POST")
	public JSONObject addBlock(long itemId, int blockType, String blockName, String blockValue, long fileEntryId) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			// Check ownership
			if (CDTSessionLocalServiceUtil.isSession(itemId) && !CDTSessionLocalServiceUtil.hasUserSession(user, itemId)) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			if (!CDTSessionLocalServiceUtil.isSession(itemId)){
				Homework homework = HomeworkLocalServiceUtil.getHomework(itemId);
				if (homework.getTeacherId() != user.getUserId()) {
					return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
				}
			}

			ContentBlock block = ContentBlockLocalServiceUtil.addBlock(user.getUserId(), itemId, blockType, blockName, blockValue, fileEntryId);
			result.put(JSONConstants.CONTENT, block.convertToJSON());
			result.put(JSONConstants.SUCCESS, true);

		} catch (UnauthorizedUrlException e) {
			result.put(JSONConstants.ERROR, e.getClass());
			result.put(JSONConstants.SUCCESS, false);
		} catch (Exception e) {
			logger.error("Could not add course item content for " + user.getFullName() + " (id=" + user.getUserId()+") " + "and itemId=" + itemId, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(value = "add-file-block", method = "POST")
	public JSONObject addFileBlock(long itemId, int blockType, String blockName, String fileName, File file) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			// Check ownership
			if (CDTSessionLocalServiceUtil.isSession(itemId) && !CDTSessionLocalServiceUtil.hasUserSession(user, itemId)) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			if (!CDTSessionLocalServiceUtil.isSession(itemId)){
				Homework homework = HomeworkLocalServiceUtil.getHomework(itemId);
				if (homework.getTeacherId() != user.getUserId()) {
					return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
				}
			}

			Folder courseItemFolder = SessionContentLocalServiceUtil.getSessionFolder(itemId);

			if (blockType == CourseConstants.TYPE_RECORD) {
				file = FileUtilsLocalServiceUtil.convertAudioToMP3(fileName, file);
			}

			try (FileInputStream inputStream = new FileInputStream(file)) {
				FileEntry uploadedFile = DLAppServiceUtil.addFileEntry(
						StringPool.BLANK, // externalReferenceCode
						courseItemFolder.getGroupId(),
						courseItemFolder.getFolderId(),
						fileName,
						MimeTypesUtil.getContentType(fileName),
						fileName,
						StringPool.BLANK, // urlTitle
						StringPool.BLANK, // description
						StringPool.BLANK, // changeLog
						inputStream,
						file.length(),
						null,
						null,
						new ServiceContext());

				long fileEntryId = uploadedFile.getFileEntryId();

				if (blockName.isEmpty()) {
					blockName = fileName;
				}
				ContentBlock block = ContentBlockLocalServiceUtil.addBlock(user.getUserId(), itemId, blockType, blockName, "", fileEntryId);
				result.put(JSONConstants.BLOCK, block.convertToJSON());
				result.put(JSONConstants.SUCCESS, true);
			}

		} catch (UnauthorizedUrlException e) {
			logger.error(e.getMessage());
			result.put(JSONConstants.ERROR, e.getClass());
			result.put(JSONConstants.SUCCESS, false);
		} catch (Exception e) {
			logger.error("Could not add course item content for " + user.getFullName() + " (id=" + user.getUserId()+") " + "and itemId=" + itemId, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(value = "update-block", method = "POST")
	public JSONObject updateBlock(long blockId, String blockName, String blockValue, int order) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			// Check ownership
			ContentBlock block = ContentBlockLocalServiceUtil.getContentBlock(blockId);
			if (CDTSessionLocalServiceUtil.isSession(block.getCourseItemId()) && !CDTSessionLocalServiceUtil.hasUserSession(user, block.getCourseItemId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			if (!CDTSessionLocalServiceUtil.isSession(block.getCourseItemId())) {
				Homework homework = HomeworkLocalServiceUtil.getHomework(block.getCourseItemId());
				if (homework.getTeacherId() != user.getUserId()) {
					return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
				}
			}

			block = ContentBlockLocalServiceUtil.updateBlock(blockId, blockName, blockValue, order);
			result.put(JSONConstants.BLOCK, block.convertToJSON());
			result.put(JSONConstants.SUCCESS, true);

		} catch (UnauthorizedUrlException e) {
			result.put(JSONConstants.ERROR, e.getClass());
			result.put(JSONConstants.SUCCESS, false);
		} catch (Exception e) {
			logger.error("Could not update course item block " + blockId + " for " + user.getFullName() + " (id=" + user.getUserId() + ")", e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(value = "delete-block", method = "DELETE")
	public JSONObject deleteBlock(long blockId) {

		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			// Check ownership
			ContentBlock block = ContentBlockLocalServiceUtil.getContentBlock(blockId);
			if (CDTSessionLocalServiceUtil.isSession(block.getCourseItemId()) && !CDTSessionLocalServiceUtil.hasUserSession(user, block.getCourseItemId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			if (!CDTSessionLocalServiceUtil.isSession(block.getCourseItemId())) {
				Homework homework = HomeworkLocalServiceUtil.getHomework(block.getCourseItemId());
				if (homework.getTeacherId() != user.getUserId()) {
					return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
				}
			}

			logger.info("User "+user.getFullName()+" (id="+user.getUserId()+") is about to delete blockId = " + blockId);
			ContentBlockLocalServiceUtil.deleteBlock(blockId);
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Could not delete blockId " + blockId + " for " + user.getFullName() + " (id="+user.getUserId()+")", e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

}