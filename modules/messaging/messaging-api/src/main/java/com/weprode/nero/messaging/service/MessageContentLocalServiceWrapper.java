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

package com.weprode.nero.messaging.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MessageContentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MessageContentLocalService
 * @generated
 */
public class MessageContentLocalServiceWrapper
	implements MessageContentLocalService,
			   ServiceWrapper<MessageContentLocalService> {

	public MessageContentLocalServiceWrapper(
		MessageContentLocalService messageContentLocalService) {

		_messageContentLocalService = messageContentLocalService;
	}

	@Override
	public com.weprode.nero.messaging.model.MessageContent addContent(
		long messageId, String messageContent) {

		return _messageContentLocalService.addContent(
			messageId, messageContent);
	}

	/**
	 * Adds the message content to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageContentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messageContent the message content
	 * @return the message content that was added
	 */
	@Override
	public com.weprode.nero.messaging.model.MessageContent addMessageContent(
		com.weprode.nero.messaging.model.MessageContent messageContent) {

		return _messageContentLocalService.addMessageContent(messageContent);
	}

	/**
	 * Creates a new message content with the primary key. Does not add the message content to the database.
	 *
	 * @param messageId the primary key for the new message content
	 * @return the new message content
	 */
	@Override
	public com.weprode.nero.messaging.model.MessageContent createMessageContent(
		long messageId) {

		return _messageContentLocalService.createMessageContent(messageId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageContentLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the message content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageContentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messageId the primary key of the message content
	 * @return the message content that was removed
	 * @throws PortalException if a message content with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.messaging.model.MessageContent deleteMessageContent(
			long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageContentLocalService.deleteMessageContent(messageId);
	}

	/**
	 * Deletes the message content from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageContentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messageContent the message content
	 * @return the message content that was removed
	 */
	@Override
	public com.weprode.nero.messaging.model.MessageContent deleteMessageContent(
		com.weprode.nero.messaging.model.MessageContent messageContent) {

		return _messageContentLocalService.deleteMessageContent(messageContent);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageContentLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _messageContentLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _messageContentLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _messageContentLocalService.dynamicQuery();
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

		return _messageContentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.messaging.model.impl.MessageContentModelImpl</code>.
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

		return _messageContentLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.messaging.model.impl.MessageContentModelImpl</code>.
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

		return _messageContentLocalService.dynamicQuery(
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

		return _messageContentLocalService.dynamicQueryCount(dynamicQuery);
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

		return _messageContentLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.messaging.model.MessageContent fetchMessageContent(
		long messageId) {

		return _messageContentLocalService.fetchMessageContent(messageId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _messageContentLocalService.getActionableDynamicQuery();
	}

	@Override
	public String getContent(long messageId) {
		return _messageContentLocalService.getContent(messageId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _messageContentLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the message content with the primary key.
	 *
	 * @param messageId the primary key of the message content
	 * @return the message content
	 * @throws PortalException if a message content with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.messaging.model.MessageContent getMessageContent(
			long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageContentLocalService.getMessageContent(messageId);
	}

	/**
	 * Returns a range of all the message contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.messaging.model.impl.MessageContentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of message contents
	 * @param end the upper bound of the range of message contents (not inclusive)
	 * @return the range of message contents
	 */
	@Override
	public java.util.List<com.weprode.nero.messaging.model.MessageContent>
		getMessageContents(int start, int end) {

		return _messageContentLocalService.getMessageContents(start, end);
	}

	/**
	 * Returns the number of message contents.
	 *
	 * @return the number of message contents
	 */
	@Override
	public int getMessageContentsCount() {
		return _messageContentLocalService.getMessageContentsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _messageContentLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageContentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the message content in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageContentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messageContent the message content
	 * @return the message content that was updated
	 */
	@Override
	public com.weprode.nero.messaging.model.MessageContent updateMessageContent(
		com.weprode.nero.messaging.model.MessageContent messageContent) {

		return _messageContentLocalService.updateMessageContent(messageContent);
	}

	@Override
	public MessageContentLocalService getWrappedService() {
		return _messageContentLocalService;
	}

	@Override
	public void setWrappedService(
		MessageContentLocalService messageContentLocalService) {

		_messageContentLocalService = messageContentLocalService;
	}

	private MessageContentLocalService _messageContentLocalService;

}