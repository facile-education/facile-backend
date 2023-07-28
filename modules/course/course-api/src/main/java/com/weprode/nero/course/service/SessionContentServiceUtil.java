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

package com.weprode.nero.course.service;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * Provides the remote service utility for SessionContent. This utility wraps
 * <code>com.weprode.nero.course.service.impl.SessionContentServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see SessionContentService
 * @generated
 */
public class SessionContentServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.course.service.impl.SessionContentServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject addSessionContent(
			long sessionId, String title, String blocks, String publicationDate,
			boolean isDraft)
		throws PortalException {

		return getService().addSessionContent(
			sessionId, title, blocks, publicationDate, isDraft);
	}

	public static org.json.JSONObject deleteSessionContent(long sessionId) {
		return getService().deleteSessionContent(sessionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject getSessionContents(long sessionId) {
		return getService().getSessionContents(sessionId);
	}

	public static org.json.JSONObject getSessionPreview(long sessionId) {
		return getService().getSessionPreview(sessionId);
	}

	public static org.json.JSONObject updateSessionContent(
			long sessionId, String title, String blocks, String publicationDate,
			boolean isDraft)
		throws PortalException {

		return getService().updateSessionContent(
			sessionId, title, blocks, publicationDate, isDraft);
	}

	public static SessionContentService getService() {
		return _service;
	}

	private static volatile SessionContentService _service;

}