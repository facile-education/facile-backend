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

package com.weprode.nero.about.service;

/**
 * Provides the remote service utility for VersionNote. This utility wraps
 * <code>com.weprode.nero.about.service.impl.VersionNoteServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see VersionNoteService
 * @generated
 */
public class VersionNoteServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.about.service.impl.VersionNoteServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject createVersionNote(
		java.lang.String title, java.lang.String htmlContent) {

		return getService().createVersionNote(title, htmlContent);
	}

	public static org.json.JSONObject deleteVersionNote(long versionNoteId) {
		return getService().deleteVersionNote(versionNoteId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject getVersionNoteContent(
		java.lang.Long versionNoteId) {

		return getService().getVersionNoteContent(versionNoteId);
	}

	public static org.json.JSONObject getVersionNotes() {
		return getService().getVersionNotes();
	}

	public static org.json.JSONObject updateVersionNote(
		long versionNoteId, java.lang.String title,
		java.lang.String htmlContent) {

		return getService().updateVersionNote(
			versionNoteId, title, htmlContent);
	}

	public static VersionNoteService getService() {
		return _service;
	}

	private static volatile VersionNoteService _service;

}