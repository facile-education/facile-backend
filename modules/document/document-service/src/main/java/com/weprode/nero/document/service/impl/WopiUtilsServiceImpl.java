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

import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.document.library.kernel.service.persistence.DLFileEntryUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.Base64;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.document.model.LoolToken;
import com.weprode.nero.document.service.LoolTokenLocalServiceUtil;
import com.weprode.nero.document.service.base.WopiUtilsServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=document",
		"json.web.service.context.path=WopiUtils"
	},
	service = AopService.class
)
public class WopiUtilsServiceImpl extends WopiUtilsServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(WopiUtilsServiceImpl.class);

	@JSONWebService(method = "GET")
	public JSONObject getFileAction(HttpServletRequest request, HttpServletResponse response, String accessToken, String wopiParam) throws PortalException {
		logger.info("Start execute GetWopiFileAction with params " + wopiParam);

		// Return code must be 401 if access_token is unknown on ENT side
		LoolToken loolToken;
		try {
			loolToken = LoolTokenLocalServiceUtil.getLoolToken(accessToken);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}

		if (loolToken == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}

		long userId = loolToken.getUserId();
		if (userId == 0L) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}

		// Schoolbag or messaging
		// Extract parameter fileEntryId and version
		String[] otherParamTab = wopiParam.split("\\+");
		if (otherParamTab.length >= 2) {
			long fileEntryId = Long.parseLong(otherParamTab[0]);
			String version = otherParamTab[1];
			DLFileEntryUtil.clearCache(DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryId));
			if (version.isEmpty()) {
				DLFileVersion lastVersion = DLFileVersionLocalServiceUtil.getLatestFileVersion(fileEntryId,false);
				version = lastVersion.getVersion();
			}

			// Differentiate schoolbag from internal messaging
			boolean isIM = otherParamTab.length == 4 && otherParamTab[3].equals("im");

			getFile(fileEntryId, version, userId, isIM, request, response);
		}

		return null;
	}

	@JSONWebService(method = "POST")
	public JSONObject getFileAction(File file, HttpServletResponse response, String accessToken, String wopiParam) throws PortalException {
		logger.info("Start execute GetWopiFileAction with params " + wopiParam);

		// Return code must be 401 if access_token is unknown on ENT side
		LoolToken loolToken;
		try {
			loolToken = LoolTokenLocalServiceUtil.getLoolToken(accessToken);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}

		if (loolToken == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}

		Long userId = loolToken.getUserId();
		if (userId == 0L) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}

		// Schoolbag or messaging
		// Extract parameter fileEntryId and version
		String[] otherParamTab = wopiParam.split("\\+");
		if (otherParamTab.length >= 2) {
			long fileEntryId = Long.parseLong(otherParamTab[0]);
			String version = otherParamTab[1];
			DLFileEntryUtil.clearCache(DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryId));
			if (version.isEmpty()) {
				DLFileVersion lastVersion = DLFileVersionLocalServiceUtil.getLatestFileVersion(fileEntryId,false);
				version = lastVersion.getVersion();
			}

			saveFile(fileEntryId, version, userId, file);
		}

		return null;
	}

	@JSONWebService(method = "GET")
	public JSONObject getFileInfo(HttpServletResponse response, String accessToken, String wopiParam) throws PortalException, IOException {
		logger.info("Start execute GetWopiFileInfoAction with params=" + wopiParam);

		// Return code must be 401 if access_token is unknown on ENT side
		LoolToken loolToken;
		try {
			loolToken = LoolTokenLocalServiceUtil.getLoolToken(accessToken);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}

		if (loolToken == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}

		long userId = loolToken.getUserId();
		if (userId == 0L) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}

		User user = UserLocalServiceUtil.getUser(userId);
		JSONObject fileInfo = JSONFactoryUtil.createJSONObject();
		fileInfo.put(JSONConstants.WOPI_OWNER_ID, user.getUserId());
		fileInfo.put(JSONConstants.WOPI_USER_ID, user.getUserId());
		fileInfo.put(JSONConstants.WOPI_USER_INFO, user.getScreenName());
		fileInfo.put(JSONConstants.WOPI_USER_FRIENDLY_NAME, user.getFullName());

		// Schoolbag or messaging
		// wopiParam value example is 123456+1.0+true
		String[] otherParamTab = wopiParam.split("\\+");
		if (otherParamTab.length > 2) {
			try {
				long fileEntryId = Long.parseLong(otherParamTab[0]);
				String version = otherParamTab[1];
				boolean isReadOnly = true;
				if (otherParamTab[2] != null && !otherParamTab[2].equals("")) {
					isReadOnly = Boolean.parseBoolean(otherParamTab[2]);
				}
				fileInfo.put(JSONConstants.WOPI_USER_CAN_WRITE, !isReadOnly);
				fileInfo.put(JSONConstants.WOPI_SUPPORTS_UPDATE, !isReadOnly);

				DLFileVersion lastVersion;
				try{
					DLFileEntryUtil.clearCache(DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryId));
					lastVersion = DLFileVersionLocalServiceUtil.getLatestFileVersion(fileEntryId, false);
				} catch (Exception e){
					logger.error( "Error on retrive last dlFileVersion of file: " + fileEntryId, e);
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
					return null;
				}

				if(version.isEmpty()){
					version = lastVersion.getVersion();
				}

				FileEntry fileEntry;
				try {
					fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);
					fileInfo.put(JSONConstants.WOPI_BASE_FILE_NAME, fileEntry.getTitle());
					fileInfo.put(JSONConstants.WOPI_OWNER_ID, fileEntry.getUserName());
					fileInfo.put(JSONConstants.WOPI_SIZE, fileEntry.getSize());

					// Modified date must be UTC and formatted in "2009-06-15T13:45:30.0000000Z"
					SimpleDateFormat wopiFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");

					// To UTC TimeZone
					TimeZone utcTz = TimeZone.getTimeZone("UTC");
					wopiFormat.setTimeZone(utcTz);

					Date date = fileEntry.getModifiedDate();

					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					calendar.setTimeZone(utcTz);

					fileInfo.put(JSONConstants.WOPI_LAST_MODIFIED_TIME, wopiFormat.format(calendar.getTime()));

				} catch (Exception e){
					logger.error( "Error on retrive dlFileEntry : " + fileEntryId, e);
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
					return null;
				}

				fileInfo.put(JSONConstants.WOPI_SHA256, getHash256(fileEntry.getFileEntryId(), version));
				fileInfo.put(JSONConstants.WOPI_VERSION, version);

			} catch (Exception e){
				logger.error( "Error on parsing wopi parameter ");
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		}

		return fileInfo;
	}

	/**
	 * Get schoolbag file content given its fileEntryId and version
	 * and send this content into the servlet response
	 */
	private void getFile(Long fileEntryId, String version, Long userId, boolean isIM, HttpServletRequest request, HttpServletResponse response)	{

		try (InputStream is = DLFileEntryLocalServiceUtil.getFileAsStream(fileEntryId, version)) {

			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);
			response.setHeader("X-WOPI-ItemVersion", version);
			User user = UserLocalServiceUtil.getUser(userId);
			long size = fileEntry.getSize();
			logger.info("User " + user.getFullName() + " is viewing cart LOOL document with size "+ size);

			// TODO Statistics
			/* if (isIM) {
				LoolStatLocalServiceUtil.addLoolStat(fileEntryId, userId, false, LOOL_STAT_TYPE_IM);
			} else {
				LoolStatLocalServiceUtil.addLoolStat(fileEntryId, userId, false, LOOL_STAT_TYPE_DOC);
			}*/

			ServletResponseUtil.sendFile(request, response, fileEntry.getTitle(), is,
					(int) size, fileEntry.getMimeType(), "");
		} catch (Exception e) {
			logger.error("Error when opening schoolbag file for fileEntryId="+fileEntryId+ " and version="+version, e);
		}
	}

	private void saveFile(Long fileEntryId, String version, Long userId, File file) {
		logger.info("start file saving : " + fileEntryId  + " with version " + version  + " for user : " + userId);

		try (InputStream is = new FileInputStream(file)) {

			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);
			// Set default permissions
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setAddGroupPermissions(true);

			FileEntry feUpdate = DLAppServiceUtil.updateFileEntry(
					fileEntry.getFileEntryId(),
					fileEntry.getTitle(),
					fileEntry.getMimeType(),
					fileEntry.getTitle(),
					StringPool.BLANK, // urlTitle
					fileEntry.getDescription(),
					StringPool.BLANK, // changeLog
					DLVersionNumberIncrease.MINOR,
					is,
					fileEntry.getSize(),
					null,
					null,
					serviceContext);

			logger.info("End file saving : " + fileEntryId + " to " + feUpdate.getVersion() + " for user : " + userId);
			// TODO Statistics
			// LoolStatLocalServiceUtil.addLoolStat(fileEntryId, userId, true, LOOL_STAT_TYPE_DOC);
		} catch (Exception e) {
			logger.error("Error when saving schoolbag file for fileEntryId="+fileEntryId+ " and version="+version);
		}
	}

	private static String getHash256(long entryId, String version) {
		String value = StringPool.BLANK;

		try (InputStream is = DLFileEntryLocalServiceUtil.getFileAsStream(entryId, version)) {
			byte[] buffer = new byte[1024];
			int numRead;
			MessageDigest complete = MessageDigest.getInstance("SHA-256");
			do {
				numRead = is.read(buffer);
				if (numRead > 0) {
					complete.update(buffer, 0, numRead);
				}
			} while (numRead != -1);

			value = Base64.encode(complete.digest());
		} catch (NoSuchAlgorithmException | IOException | PortalException e) {
			logger.error(e);
		}

		return value;
	}
}