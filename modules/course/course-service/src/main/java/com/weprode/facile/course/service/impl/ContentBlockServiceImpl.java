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
import com.weprode.facile.commons.FacileLogger;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.course.CourseConstants;
import com.weprode.facile.course.exception.UnauthorizedUrlException;
import com.weprode.facile.course.model.ContentBlock;
import com.weprode.facile.course.model.Homework;
import com.weprode.facile.course.service.ContentBlockLocalServiceUtil;
import com.weprode.facile.course.service.HomeworkLocalServiceUtil;
import com.weprode.facile.course.service.SessionContentLocalServiceUtil;
import com.weprode.facile.course.service.base.ContentBlockServiceBaseImpl;
import com.weprode.facile.document.service.FileUtilsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.schedule.service.CDTSessionLocalServiceUtil;
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
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			FacileLogger.registerUser(user);
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " adds a block");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			// Check ownership
			if (CDTSessionLocalServiceUtil.isSession(itemId) && !CDTSessionLocalServiceUtil.hasUserSession(user, itemId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " adds a block to item " + itemId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			if (!CDTSessionLocalServiceUtil.isSession(itemId)){
				Homework homework = HomeworkLocalServiceUtil.getHomework(itemId);
				if (homework.getTeacherId() != user.getUserId()) {
					logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " adds a block to item " + itemId);
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
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		FacileLogger.registerUser(user);
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " adds a file block");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			// Check ownership
			if (CDTSessionLocalServiceUtil.isSession(itemId) && !CDTSessionLocalServiceUtil.hasUserSession(user, itemId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " adds a file block to item " + itemId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			if (!CDTSessionLocalServiceUtil.isSession(itemId)){
				Homework homework = HomeworkLocalServiceUtil.getHomework(itemId);
				if (homework.getTeacherId() != user.getUserId()) {
					logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " adds a file block to item " + itemId);
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
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			FacileLogger.registerUser(user);
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " updates a block");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			// Check ownership
			ContentBlock block = ContentBlockLocalServiceUtil.getContentBlock(blockId);
			if (CDTSessionLocalServiceUtil.isSession(block.getCourseItemId()) && !CDTSessionLocalServiceUtil.hasUserSession(user, block.getCourseItemId())) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " updates a block");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			if (!CDTSessionLocalServiceUtil.isSession(block.getCourseItemId())) {
				Homework homework = HomeworkLocalServiceUtil.getHomework(block.getCourseItemId());
				if (homework.getTeacherId() != user.getUserId()) {
					logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " updates a block");
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
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			FacileLogger.registerUser(user);
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " deletes a block");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			// Check ownership
			ContentBlock block = ContentBlockLocalServiceUtil.getContentBlock(blockId);
			if (CDTSessionLocalServiceUtil.isSession(block.getCourseItemId()) && !CDTSessionLocalServiceUtil.hasUserSession(user, block.getCourseItemId())) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " deletes a block");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			if (!CDTSessionLocalServiceUtil.isSession(block.getCourseItemId())) {
				Homework homework = HomeworkLocalServiceUtil.getHomework(block.getCourseItemId());
				if (homework.getTeacherId() != user.getUserId()) {
					logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " deletes a block");
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

	@JSONWebService(value = "is-embed-url-whitelisted", method = "GET")
	public JSONObject isEmbedUrlWhitelisted(String url) {
		JSONObject result = new JSONObject();

		result.put(JSONConstants.IS_ALLOWED, ContentBlockLocalServiceUtil.isEmbedUrlWhitelisted(url));
		result.put(JSONConstants.SUCCESS, true);

		return result;
	}

}