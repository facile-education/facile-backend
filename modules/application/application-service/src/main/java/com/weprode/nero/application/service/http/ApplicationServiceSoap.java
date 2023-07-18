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

package com.weprode.nero.application.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.weprode.nero.application.service.ApplicationServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>ApplicationServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.weprode.nero.application.model.ApplicationSoap</code>. If the method in the
 * service utility returns a
 * <code>com.weprode.nero.application.model.Application</code>, that is translated to a
 * <code>com.weprode.nero.application.model.ApplicationSoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationServiceHttp
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ApplicationServiceSoap {

	public static org.json.JSONObject getPortlets() throws RemoteException {
		try {
			org.json.JSONObject returnValue =
				ApplicationServiceUtil.getPortlets();

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject addApplication(
			String applicationName, String applicationKey, String category,
			long menuEntryId, String image, boolean hasCustomUrl,
			String globalUrl, boolean exportUser, boolean exportStudent,
			boolean exportParent, boolean exportTeacher, boolean exportOther,
			String defaultRoles, String authorizedSchools)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				ApplicationServiceUtil.addApplication(
					applicationName, applicationKey, category, menuEntryId,
					image, hasCustomUrl, globalUrl, exportUser, exportStudent,
					exportParent, exportTeacher, exportOther, defaultRoles,
					authorizedSchools);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject editApplication(
			long applicationId, String applicationName, String applicationKey,
			String category, long menuEntryId, String image,
			boolean hasCustomUrl, String globalUrl, boolean exportUser,
			boolean exportStudent, boolean exportParent, boolean exportTeacher,
			boolean exportOther, String defaultRoles, String authorizedSchools)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				ApplicationServiceUtil.editApplication(
					applicationId, applicationName, applicationKey, category,
					menuEntryId, image, hasCustomUrl, globalUrl, exportUser,
					exportStudent, exportParent, exportTeacher, exportOther,
					defaultRoles, authorizedSchools);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject removeApplication(long applicationId)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				ApplicationServiceUtil.removeApplication(applicationId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject getSchoolApplications(long schoolId)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				ApplicationServiceUtil.getSchoolApplications(schoolId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject export(
			long applicationId, long schoolId, String roleName)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue = ApplicationServiceUtil.export(
				applicationId, schoolId, roleName);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject getUserApplications()
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				ApplicationServiceUtil.getUserApplications();

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject getAllApplications()
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				ApplicationServiceUtil.getAllApplications();

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject getStatApplications(long schoolId)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				ApplicationServiceUtil.getStatApplications(schoolId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject getResourceUrls(long menuEntryId)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				ApplicationServiceUtil.getResourceUrls(menuEntryId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ApplicationServiceSoap.class);

}