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

package com.weprode.nero.document.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.weprode.nero.document.service.FileUtilsServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>FileUtilsServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
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
 * @see FileUtilsServiceHttp
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class FileUtilsServiceSoap {

	public static org.json.JSONObject renameFile(long fileId, String fileName)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue = FileUtilsServiceUtil.renameFile(
				fileId, fileName);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject createGeogebraFile(
			long folderId, String name)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				FileUtilsServiceUtil.createGeogebraFile(folderId, name);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject createMindmapFile(
			long folderId, String name)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				FileUtilsServiceUtil.createMindmapFile(folderId, name);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject createScratchFile(
			long folderId, String name)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				FileUtilsServiceUtil.createScratchFile(folderId, name);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject createLoolFile(
			long folderId, String name, String type)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				FileUtilsServiceUtil.createLoolFile(folderId, name, type);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject createHTMLFile(long folderId, String name)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				FileUtilsServiceUtil.createHTMLFile(folderId, name);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject addLock(long fileId)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue = FileUtilsServiceUtil.addLock(
				fileId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject removeLock(long fileId)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue = FileUtilsServiceUtil.removeLock(
				fileId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject getResource(
			long fileId, long versionId, boolean readOnly)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue = FileUtilsServiceUtil.getResource(
				fileId, versionId, readOnly);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject removeLoolToken(String token)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				FileUtilsServiceUtil.removeLoolToken(token);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(FileUtilsServiceSoap.class);

}