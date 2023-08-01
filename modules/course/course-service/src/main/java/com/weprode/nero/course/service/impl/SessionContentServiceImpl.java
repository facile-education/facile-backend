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

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.JSONProxy;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.course.exception.UnauthorizedUrlException;
import com.weprode.nero.course.model.ContentBlock;
import com.weprode.nero.course.model.SessionContent;
import com.weprode.nero.course.service.ContentBlockLocalServiceUtil;
import com.weprode.nero.course.service.SessionContentLocalServiceUtil;
import com.weprode.nero.course.service.base.SessionContentServiceBaseImpl;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.SessionTeacherLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Cédric Lecarpentier
 */
@Component(
	property = {
		"json.web.service.context.name=course",
		"json.web.service.context.path=SessionContent"
	},
	service = AopService.class
)
public class SessionContentServiceImpl extends SessionContentServiceBaseImpl {

	private final Log logger = LogFactoryUtil.getLog(SessionContentServiceImpl.class);

	// Called when creating the whole session content, with all blocks
	@JSONWebService(value = "add-session-content", method = "POST")
	public JSONObject addSessionContent(long sessionId, String title, String blocks, String publicationDate, boolean isDraft) throws PortalException {
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

		if (RoleUtilsLocalServiceUtil.isTeacher(user) && !SessionTeacherLocalServiceUtil.hasTeacherSession(user.getUserId(), sessionId)) {
			logger.error("User " + user.getFullName() + " tries to create content for session " + sessionId +" but does not own this session");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}
		logger.info("User " + user.getFullName() + " (id=" + user.getUserId() + ") creates content for session " + sessionId);

		Date publication;
		try {
			publication = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(publicationDate);
		} catch (ParseException e) {
			result.put(JSONConstants.SUCCESS, false);
			return result;
		}

		SessionContent courseItem = SessionContentLocalServiceUtil.addSessionContent(sessionId, user.getUserId(), title, publication, isDraft);

		try {
			// Add contents
			JSONArray jsonBlocks = new JSONArray(blocks);
			for (int i = 0; i < jsonBlocks.length(); i++) {
				JSONObject jsonBlock = jsonBlocks.getJSONObject(i);
				ContentBlockLocalServiceUtil.addBlock(user.getUserId(), sessionId,
						jsonBlock.getInt(JSONConstants.CONTENT_TYPE),
						jsonBlock.getString(JSONConstants.CONTENT_NAME),
						JSONConstants.getStringValue(jsonBlock, JSONConstants.CONTENT_VALUE, ""),
						JSONConstants.getLongValue(jsonBlock, JSONConstants.FILE_ID, 0));
			}

			result.put(JSONConstants.ITEM, courseItem.convertToJSON(user, true));
			result.put(JSONConstants.SUCCESS, true);
		} catch (UnauthorizedUrlException | IOException e) {
			logger.error("Error creating session content", e);
			throw new PortalException(); // To cancel the previous content creation
		}

		return result;
	}

	// Update the whole session content, when the previous content is published
	@JSONWebService(value = "update-session-content", method = "POST")
	public JSONObject updateSessionContent(long sessionId, String title, String blocks, String publicationDate, boolean isDraft) throws PortalException {
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

		if (RoleUtilsLocalServiceUtil.isTeacher(user) && !SessionTeacherLocalServiceUtil.hasTeacherSession(user.getUserId(), sessionId)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		Date publication;
		try {
			publication = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(publicationDate);
		} catch (ParseException e) {
			result.put(JSONConstants.SUCCESS, false);
			return result;
		}
		JSONArray jsonBlocks = new JSONArray(blocks);
		try {
			SessionContent updatedItem = SessionContentLocalServiceUtil.updateSessionContent(user.getUserId(), sessionId, title, jsonBlocks, publication, isDraft);
			result.put(JSONConstants.SESSION_CONTENT, updatedItem.convertToJSON(user, true));
			logger.info("User " + user.getFullName() + " (id=" + user.getUserId() + ") has updated course session " + sessionId);
			result.put(JSONConstants.SUCCESS, true);
		} catch (UnauthorizedUrlException | IOException e) {
			logger.error("Error updating session", e);
			throw new PortalException(); // To cancel the previous content creation
		}


		return result;
	}

	@JSONWebService(value = "delete-session-content", method = "DELETE")
	public JSONObject deleteSessionContent(long sessionId) {

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
			if (RoleUtilsLocalServiceUtil.isTeacher(user) && !SessionTeacherLocalServiceUtil.hasTeacherSession(user.getUserId(), sessionId)) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			SessionContentLocalServiceUtil.deleteSessionContent(sessionId);
			ContentBlockLocalServiceUtil.deleteBlocksByItemId(sessionId);
			logger.info("User "+user.getFullName()+" (id="+user.getUserId()+") has deleted content for session " + sessionId);
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Could not delete content for sessionId " + sessionId + " for " + user.getFullName() + " (id="+user.getUserId()+")", e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}


	// Called from the course tab, when all items are initially collapsed
	@JSONWebService(value = "get-session-contents", method = "GET")
	public JSONObject getSessionContents(long sessionId) {
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

		try {
			if (!CDTSessionLocalServiceUtil.hasUserSession(user, sessionId)) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			SessionContent content = SessionContentLocalServiceUtil.fetchSessionContent(sessionId);
			if (content != null) {
				CDTSession session = CDTSessionLocalServiceUtil.getCDTSession(sessionId);
				result.put(JSONConstants.SLOT_NUMBER, session.getSlot());
				result.put(JSONConstants.TITLE, content.getTitle());
				result.put(JSONConstants.IS_DRAFT, content.getIsDraft());
				result.put(JSONConstants.PUBLICATION_DATE, new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).format(content.getPublicationDate()));
			}

			JSONArray jsonContents = new JSONArray();
			List<ContentBlock> blocks = ContentBlockLocalServiceUtil.getContentsByItemId(sessionId);
			for (ContentBlock block : blocks) {
				jsonContents.put(block.convertToJSON());
			}
			result.put(JSONConstants.BLOCKS, jsonContents);
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Could not get content blocks for "+user.getFullName()+" (id="+user.getUserId()+") " + "and sessionId = " + sessionId, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(value = "get-session-preview", method = "GET")
	public JSONObject getSessionPreview(long sessionId) {
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

		try {
			if (!CDTSessionLocalServiceUtil.hasUserSession(user, sessionId)) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			result.put(JSONConstants.PREVIEW, SessionContentLocalServiceUtil.convertContentAsHtml(sessionId));
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Could not get preview content for " + user.getFullName() + " (id="+user.getUserId()+") " + "and session " + sessionId, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

}