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
 * Provides a wrapper for {@link MessageRecipientsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MessageRecipientsLocalService
 * @generated
 */
public class MessageRecipientsLocalServiceWrapper
	implements MessageRecipientsLocalService,
			   ServiceWrapper<MessageRecipientsLocalService> {

	public MessageRecipientsLocalServiceWrapper(
		MessageRecipientsLocalService messageRecipientsLocalService) {

		_messageRecipientsLocalService = messageRecipientsLocalService;
	}

	@Override
	public com.weprode.nero.messaging.model.MessageRecipients
			addMessageRecipients(
				long messageId, java.util.List<Long> recipientList)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _messageRecipientsLocalService.addMessageRecipients(
			messageId, recipientList);
	}

	/**
	 * Adds the message recipients to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageRecipientsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messageRecipients the message recipients
	 * @return the message recipients that was added
	 */
	@Override
	public com.weprode.nero.messaging.model.MessageRecipients
		addMessageRecipients(
			com.weprode.nero.messaging.model.MessageRecipients
				messageRecipients) {

		return _messageRecipientsLocalService.addMessageRecipients(
			messageRecipients);
	}

	/**
	 * Creates a new message recipients with the primary key. Does not add the message recipients to the database.
	 *
	 * @param messageId the primary key for the new message recipients
	 * @return the new message recipients
	 */
	@Override
	public com.weprode.nero.messaging.model.MessageRecipients
		createMessageRecipients(long messageId) {

		return _messageRecipientsLocalService.createMessageRecipients(
			messageId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageRecipientsLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the message recipients with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageRecipientsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messageId the primary key of the message recipients
	 * @return the message recipients that was removed
	 * @throws PortalException if a message recipients with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.messaging.model.MessageRecipients
			deleteMessageRecipients(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageRecipientsLocalService.deleteMessageRecipients(
			messageId);
	}

	/**
	 * Deletes the message recipients from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageRecipientsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messageRecipients the message recipients
	 * @return the message recipients that was removed
	 */
	@Override
	public com.weprode.nero.messaging.model.MessageRecipients
		deleteMessageRecipients(
			com.weprode.nero.messaging.model.MessageRecipients
				messageRecipients) {

		return _messageRecipientsLocalService.deleteMessageRecipients(
			messageRecipients);
	}

	@Override
	public boolean deleteMessageRecipientsByMessageId(long messageId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _messageRecipientsLocalService.
			deleteMessageRecipientsByMessageId(messageId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageRecipientsLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _messageRecipientsLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _messageRecipientsLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _messageRecipientsLocalService.dynamicQuery();
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

		return _messageRecipientsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.messaging.model.impl.MessageRecipientsModelImpl</code>.
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

		return _messageRecipientsLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.messaging.model.impl.MessageRecipientsModelImpl</code>.
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

		return _messageRecipientsLocalService.dynamicQuery(
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

		return _messageRecipientsLocalService.dynamicQueryCount(dynamicQuery);
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

		return _messageRecipientsLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.messaging.model.MessageRecipients
		fetchMessageRecipients(long messageId) {

		return _messageRecipientsLocalService.fetchMessageRecipients(messageId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _messageRecipientsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _messageRecipientsLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the message recipients with the primary key.
	 *
	 * @param messageId the primary key of the message recipients
	 * @return the message recipients
	 * @throws PortalException if a message recipients with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.messaging.model.MessageRecipients
			getMessageRecipients(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageRecipientsLocalService.getMessageRecipients(messageId);
	}

	@Override
	public String getMessageRecipientsAsString(long messageId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _messageRecipientsLocalService.getMessageRecipientsAsString(
			messageId);
	}

	/**
	 * Returns a range of all the message recipientses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.messaging.model.impl.MessageRecipientsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of message recipientses
	 * @param end the upper bound of the range of message recipientses (not inclusive)
	 * @return the range of message recipientses
	 */
	@Override
	public java.util.List<com.weprode.nero.messaging.model.MessageRecipients>
		getMessageRecipientses(int start, int end) {

		return _messageRecipientsLocalService.getMessageRecipientses(
			start, end);
	}

	/**
	 * Returns the number of message recipientses.
	 *
	 * @return the number of message recipientses
	 */
	@Override
	public int getMessageRecipientsesCount() {
		return _messageRecipientsLocalService.getMessageRecipientsesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _messageRecipientsLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageRecipientsLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User> getRecipients(
		long messageId) {

		return _messageRecipientsLocalService.getRecipients(messageId);
	}

	/**
	 * Updates the message recipients in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageRecipientsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messageRecipients the message recipients
	 * @return the message recipients that was updated
	 */
	@Override
	public com.weprode.nero.messaging.model.MessageRecipients
		updateMessageRecipients(
			com.weprode.nero.messaging.model.MessageRecipients
				messageRecipients) {

		return _messageRecipientsLocalService.updateMessageRecipients(
			messageRecipients);
	}

	@Override
	public MessageRecipientsLocalService getWrappedService() {
		return _messageRecipientsLocalService;
	}

	@Override
	public void setWrappedService(
		MessageRecipientsLocalService messageRecipientsLocalService) {

		_messageRecipientsLocalService = messageRecipientsLocalService;
	}

	private MessageRecipientsLocalService _messageRecipientsLocalService;

}