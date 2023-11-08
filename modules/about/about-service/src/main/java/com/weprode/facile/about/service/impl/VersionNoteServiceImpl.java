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

package com.weprode.facile.about.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.about.model.VersionNote;
import com.weprode.facile.about.service.UserReadVersionNoteLocalServiceUtil;
import com.weprode.facile.about.service.VersionNoteLocalServiceUtil;
import com.weprode.facile.about.service.base.VersionNoteServiceBaseImpl;

import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.document.service.FileUtilsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=about",
		"json.web.service.context.path=VersionNote"
	},
	service = AopService.class
)
public class VersionNoteServiceImpl extends VersionNoteServiceBaseImpl {
	private static final Log logger = LogFactoryUtil.getLog(VersionNoteServiceImpl.class);

	@JSONWebService(value = "get-version-notes", method = "GET")
	public JSONObject getVersionNotes () {
		SimpleDateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
		JSONObject result = new JSONObject();

		try {
			User user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			JSONArray versionsNotesJson = new JSONArray();
			List<VersionNote> versionNotes = VersionNoteLocalServiceUtil.getSortedVersionNotes();

			boolean isLastVersion = true; // The last version is the item version in the sorted list

			for (VersionNote versionNote : versionNotes) {
				JSONObject entVersionJson = new JSONObject();
				entVersionJson.put(JSONConstants.VERSION_NOTE_ID, versionNote.getVersionNoteId());
				entVersionJson.put(JSONConstants.TITLE, versionNote.getTitle());
				entVersionJson.put(JSONConstants.CREATION_DATE, df.format(versionNote.getVersionNoteDate()));
				entVersionJson.put(JSONConstants.LATEST, isLastVersion);
				versionsNotesJson.put(entVersionJson);

				isLastVersion = false;
			}
			result.put(JSONConstants.VERSION_NOTES, versionsNotesJson);
		} catch (Exception e) {
			logger.error(e);
			result.put(JSONConstants.SUCCESS, false);
			return result;
		}
		result.put(JSONConstants.SUCCESS, true);

		return result;
	}

	@JSONWebService(value = "get-version-note-content", method = "GET")
	public JSONObject getVersionNoteContent (Long versionNoteId) {
		JSONObject result = new JSONObject();

		try {
			User user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}

			VersionNote versionNote = VersionNoteLocalServiceUtil.getVersionNote(versionNoteId);

			if (VersionNoteLocalServiceUtil.isLastVersionNote(versionNote)) {
				UserReadVersionNoteLocalServiceUtil.setLastVersionNoteAsReadForUser(user.getUserId());
			}

			result.put(JSONConstants.VERSION_NOTE_CONTENT, versionNote.getContent());
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error when getting version note details", e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(value = "create-version-note", method = "POST")
	public JSONObject createVersionNote (String title, String htmlContent) {
		JSONObject result = new JSONObject();

		try {
			User user = getGuestOrUser();

			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			String sanitizedHtmlContent = FileUtilsLocalServiceUtil.sanitizeHTMLContent(htmlContent);

			VersionNoteLocalServiceUtil.addVersionNote(title, sanitizedHtmlContent);

			UserReadVersionNoteLocalServiceUtil.addNewVersionNote();

			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error(e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(value = "update-version-note", method = "POST")
	public JSONObject updateVersionNote (long versionNoteId, String title, String htmlContent) {
		JSONObject result = new JSONObject();

		try {
			User user = getGuestOrUser();

			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			String sanitizedHtmlContent = FileUtilsLocalServiceUtil.sanitizeHTMLContent(htmlContent);

			VersionNoteLocalServiceUtil.updateVersionNote(versionNoteId, title, sanitizedHtmlContent);
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error(e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(value = "delete-version-note", method = "DELETE")
	public JSONObject deleteVersionNote (long versionNoteId) {
		JSONObject result = new JSONObject();

		try {
			User user = getGuestOrUser();

			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			VersionNoteLocalServiceUtil.deleteVersionNote(versionNoteId);

			UserReadVersionNoteLocalServiceUtil.markAsReadForAllRegisteredUsers(); // reset all 'newVersion' notification

			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error(e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}
}