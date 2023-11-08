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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MessageAttachFileLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MessageAttachFileLocalService
 * @generated
 */
public class MessageAttachFileLocalServiceWrapper
	implements MessageAttachFileLocalService,
			   ServiceWrapper<MessageAttachFileLocalService> {

	public MessageAttachFileLocalServiceWrapper() {
		this(null);
	}

	public MessageAttachFileLocalServiceWrapper(
		MessageAttachFileLocalService messageAttachFileLocalService) {

		_messageAttachFileLocalService = messageAttachFileLocalService;
	}

	@Override
	public com.weprode.facile.messaging.model.MessageAttachFile addAttachFile(
		long messageId, long fileId) {

		return _messageAttachFileLocalService.addAttachFile(messageId, fileId);
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
	@Override
	public com.weprode.facile.messaging.model.MessageAttachFile
		addMessageAttachFile(
			com.weprode.facile.messaging.model.MessageAttachFile
				messageAttachFile) {

		return _messageAttachFileLocalService.addMessageAttachFile(
			messageAttachFile);
	}

	@Override
	public int countAttachedFiles(long messageId) {
		return _messageAttachFileLocalService.countAttachedFiles(messageId);
	}

	/**
	 * Creates a new message attach file with the primary key. Does not add the message attach file to the database.
	 *
	 * @param messageAttachFilePK the primary key for the new message attach file
	 * @return the new message attach file
	 */
	@Override
	public com.weprode.facile.messaging.model.MessageAttachFile
		createMessageAttachFile(
			com.weprode.facile.messaging.service.persistence.MessageAttachFilePK
				messageAttachFilePK) {

		return _messageAttachFileLocalService.createMessageAttachFile(
			messageAttachFilePK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageAttachFileLocalService.createPersistedModel(
			primaryKeyObj);
	}

	@Override
	public void deleteAttachFiles(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		_messageAttachFileLocalService.deleteAttachFiles(messageId);
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
	@Override
	public com.weprode.facile.messaging.model.MessageAttachFile
		deleteMessageAttachFile(
			com.weprode.facile.messaging.model.MessageAttachFile
				messageAttachFile) {

		return _messageAttachFileLocalService.deleteMessageAttachFile(
			messageAttachFile);
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
	@Override
	public com.weprode.facile.messaging.model.MessageAttachFile
			deleteMessageAttachFile(
				com.weprode.facile.messaging.service.persistence.
					MessageAttachFilePK messageAttachFilePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageAttachFileLocalService.deleteMessageAttachFile(
			messageAttachFilePK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageAttachFileLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _messageAttachFileLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _messageAttachFileLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _messageAttachFileLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _messageAttachFileLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _messageAttachFileLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _messageAttachFileLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _messageAttachFileLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _messageAttachFileLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.facile.messaging.model.MessageAttachFile
		fetchMessageAttachFile(
			com.weprode.facile.messaging.service.persistence.MessageAttachFilePK
				messageAttachFilePK) {

		return _messageAttachFileLocalService.fetchMessageAttachFile(
			messageAttachFilePK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _messageAttachFileLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _messageAttachFileLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the message attach file with the primary key.
	 *
	 * @param messageAttachFilePK the primary key of the message attach file
	 * @return the message attach file
	 * @throws PortalException if a message attach file with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.messaging.model.MessageAttachFile
			getMessageAttachFile(
				com.weprode.facile.messaging.service.persistence.
					MessageAttachFilePK messageAttachFilePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageAttachFileLocalService.getMessageAttachFile(
			messageAttachFilePK);
	}

	@Override
	public java.util.List<Long> getMessageAttachFileIds(long messageId) {
		return _messageAttachFileLocalService.getMessageAttachFileIds(
			messageId);
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
	@Override
	public java.util.List<com.weprode.facile.messaging.model.MessageAttachFile>
		getMessageAttachFiles(int start, int end) {

		return _messageAttachFileLocalService.getMessageAttachFiles(start, end);
	}

	/**
	 * Returns the number of message attach files.
	 *
	 * @return the number of message attach files
	 */
	@Override
	public int getMessageAttachFilesCount() {
		return _messageAttachFileLocalService.getMessageAttachFilesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _messageAttachFileLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageAttachFileLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public com.weprode.facile.messaging.model.MessageAttachFile
		updateMessageAttachFile(
			com.weprode.facile.messaging.model.MessageAttachFile
				messageAttachFile) {

		return _messageAttachFileLocalService.updateMessageAttachFile(
			messageAttachFile);
	}

	@Override
	public MessageAttachFileLocalService getWrappedService() {
		return _messageAttachFileLocalService;
	}

	@Override
	public void setWrappedService(
		MessageAttachFileLocalService messageAttachFileLocalService) {

		_messageAttachFileLocalService = messageAttachFileLocalService;
	}

	private MessageAttachFileLocalService _messageAttachFileLocalService;

}