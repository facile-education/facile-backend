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

import com.weprode.nero.document.service.ScratchServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>ScratchServiceUtil</code> service
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
 * @see ScratchServiceHttp
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ScratchServiceSoap {

	/**
	 * Returns the scratch files in the user's schoolbag
	 *
	 * @return JSONObject with all user's scratch files
	 */
	public static org.json.JSONObject getScratchFiles(long userId)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				ScratchServiceUtil.getScratchFiles(userId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	/**
	 * Returns the content of the given scratch file
	 *
	 * @return JSONObject - the scratch file name and content
	 */
	public static org.json.JSONObject getScratchFile(long fileVersionId)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue = ScratchServiceUtil.getScratchFile(
				fileVersionId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	/**
	 * This method saves a scratch file in the user's schoolbag
	 *
	 * @param params - The map containing userId, fileEntryId, fileName and content
	 * @return JSONObject success or not
	 */
	public static org.json.JSONObject saveScratchFile(String params)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				ScratchServiceUtil.saveScratchFile(params);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ScratchServiceSoap.class);

}