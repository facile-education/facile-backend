package com.weprode.nero.document.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.JSONProxy;
import com.weprode.nero.commons.constants.JSONConstants;
import org.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.weprode.nero.document.service.base.ClipboardServiceBaseImpl;

import com.weprode.nero.document.utils.ClipboardUtil;
import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		"json.web.service.context.name=document",
		"json.web.service.context.path=Clipboard"
	},
	service = AopService.class
)
public class ClipboardServiceImpl extends ClipboardServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(ClipboardServiceImpl.class);

	@JSONWebService(method = "GET")
	public JSONObject copy(String folderIds, String fileIds, long targetFolderId, int mode) throws PrincipalException {
		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		long userId = getGuestOrUserId();

		// Permissions are checker deeper
		logger.info("User " + userId + " copies files " + fileIds + " and folders " + folderIds + " to targetFolderId " + targetFolderId);
		return ClipboardUtil.copy(userId, targetFolderId, folderIds, fileIds, mode);
	}

	@JSONWebService(method = "GET")
	public JSONObject move (String folderIds, String fileIds, long targetFolderId, int mode) throws PrincipalException {
		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		long userId = getGuestOrUserId();

		// Permissions are checker deeper
		logger.info("User " + userId + " cuts files " + fileIds + " and folders " + folderIds + " to targetFolderId " + targetFolderId);
		return ClipboardUtil.move(userId, targetFolderId, folderIds, fileIds, mode);
	}

}