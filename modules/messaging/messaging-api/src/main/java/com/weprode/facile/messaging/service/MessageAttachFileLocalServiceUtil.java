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

package com.weprode.facile.messaging.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.messaging.model.MessageAttachFile;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for MessageAttachFile. This utility wraps
 * <code>com.weprode.facile.messaging.service.impl.MessageAttachFileLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see MessageAttachFileLocalService
 * @generated
 */
public class MessageAttachFileLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.messaging.service.impl.MessageAttachFileLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static MessageAttachFile addAttachFile(long messageId, long fileId) {
		return getService().addAttachFile(messageId, fileId);
	}

	/**
	 * Adds the message attach file to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageAttachFileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messageAttachFile the message attach file
	 * @return the message attach file that was added
	 */
	public static MessageAttachFile addMessageAttachFile(
		MessageAttachFile messageAttachFile) {

		return getService().addMessageAttachFile(messageAttachFile);
	}

	public static int countAttachedFiles(long messageId) {
		return getService().countAttachedFiles(messageId);
	}

	/**
	 * Creates a new message attach file with the primary key. Does not add the message attach file to the database.
	 *
	 * @param messageAttachFilePK the primary key for the new message attach file
	 * @return the new message attach file
	 */
	public static MessageAttachFile createMessageAttachFile(
		com.weprode.facile.messaging.service.persistence.MessageAttachFilePK
			messageAttachFilePK) {

		return getService().createMessageAttachFile(messageAttachFilePK);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	public static void deleteAttachFiles(long messageId)
		throws PortalException, SystemException {

		getService().deleteAttachFiles(messageId);
	}

	/**
	 * Deletes the message attach file from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageAttachFileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messageAttachFile the message attach file
	 * @return the message attach file that was removed
	 */
	public static MessageAttachFile deleteMessageAttachFile(
		MessageAttachFile messageAttachFile) {

		return getService().deleteMessageAttachFile(messageAttachFile);
	}

	/**
	 * Deletes the message attach file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageAttachFileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messageAttachFilePK the primary key of the message attach file
	 * @return the message attach file that was removed
	 * @throws PortalException if a message attach file with the primary key could not be found
	 */
	public static MessageAttachFile deleteMessageAttachFile(
			com.weprode.facile.messaging.service.persistence.MessageAttachFilePK
				messageAttachFilePK)
		throws PortalException {

		return getService().deleteMessageAttachFile(messageAttachFilePK);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.messaging.model.impl.MessageAttachFileModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.messaging.model.impl.MessageAttachFileModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static MessageAttachFile fetchMessageAttachFile(
		com.weprode.facile.messaging.service.persistence.MessageAttachFilePK
			messageAttachFilePK) {

		return getService().fetchMessageAttachFile(messageAttachFilePK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the message attach file with the primary key.
	 *
	 * @param messageAttachFilePK the primary key of the message attach file
	 * @return the message attach file
	 * @throws PortalException if a message attach file with the primary key could not be found
	 */
	public static MessageAttachFile getMessageAttachFile(
			com.weprode.facile.messaging.service.persistence.MessageAttachFilePK
				messageAttachFilePK)
		throws PortalException {

		return getService().getMessageAttachFile(messageAttachFilePK);
	}

	public static List<Long> getMessageAttachFileIds(long messageId) {
		return getService().getMessageAttachFileIds(messageId);
	}

	/**
	 * Returns a range of all the message attach files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.messaging.model.impl.MessageAttachFileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of message attach files
	 * @param end the upper bound of the range of message attach files (not inclusive)
	 * @return the range of message attach files
	 */
	public static List<MessageAttachFile> getMessageAttachFiles(
		int start, int end) {

		return getService().getMessageAttachFiles(start, end);
	}

	/**
	 * Returns the number of message attach files.
	 *
	 * @return the number of message attach files
	 */
	public static int getMessageAttachFilesCount() {
		return getService().getMessageAttachFilesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the message attach file in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageAttachFileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messageAttachFile the message attach file
	 * @return the message attach file that was updated
	 */
	public static MessageAttachFile updateMessageAttachFile(
		MessageAttachFile messageAttachFile) {

		return getService().updateMessageAttachFile(messageAttachFile);
	}

	public static MessageAttachFileLocalService getService() {
		return _service;
	}

	private static volatile MessageAttachFileLocalService _service;

}