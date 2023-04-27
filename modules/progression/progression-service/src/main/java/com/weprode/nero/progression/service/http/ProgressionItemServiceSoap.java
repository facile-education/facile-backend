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

package com.weprode.nero.progression.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.weprode.nero.progression.service.ProgressionItemServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>ProgressionItemServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.weprode.nero.progression.model.ProgressionItemSoap</code>. If the method in the
 * service utility returns a
 * <code>com.weprode.nero.progression.model.ProgressionItem</code>, that is translated to a
 * <code>com.weprode.nero.progression.model.ProgressionItemSoap</code>. Methods that SOAP
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
 * @see ProgressionItemServiceHttp
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ProgressionItemServiceSoap {

	public static String addItem(
			long progressionId, long folderId, boolean isHomework)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				ProgressionItemServiceUtil.addItem(
					progressionId, folderId, isHomework);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String updateItem(
			long itemId, long folderId, String name, int type, String duration,
			int order)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				ProgressionItemServiceUtil.updateItem(
					itemId, folderId, name, type, duration, order);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String deleteItem(long itemId) throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				ProgressionItemServiceUtil.deleteItem(itemId);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String addItemContent(
			long itemId, int contentType, String contentName,
			String contentValue, long fileEntryId, boolean isToBeCompleted)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				ProgressionItemServiceUtil.addItemContent(
					itemId, contentType, contentName, contentValue, fileEntryId,
					isToBeCompleted);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String updateItemContent(
			long contentId, String contentName, String contentValue, int order)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				ProgressionItemServiceUtil.updateItemContent(
					contentId, contentName, contentValue, order);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String deleteItemContent(long contentId)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				ProgressionItemServiceUtil.deleteItemContent(contentId);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String getItemContents(long itemId) throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				ProgressionItemServiceUtil.getItemContents(itemId);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String getItemPreview(long itemId) throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				ProgressionItemServiceUtil.getItemPreview(itemId);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String getSessionSpecificContents(long sessionId)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				ProgressionItemServiceUtil.getSessionSpecificContents(
					sessionId);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String getHomeworkSpecificContents(long homeworkId)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				ProgressionItemServiceUtil.getHomeworkSpecificContents(
					homeworkId);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String saveSessionSpecificItem(long sessionId)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				ProgressionItemServiceUtil.saveSessionSpecificItem(sessionId);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String saveHomeworkSpecificItem(long homeworkId)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				ProgressionItemServiceUtil.saveHomeworkSpecificItem(homeworkId);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ProgressionItemServiceSoap.class);

}