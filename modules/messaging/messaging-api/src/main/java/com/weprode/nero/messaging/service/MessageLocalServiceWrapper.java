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
 * Provides a wrapper for {@link MessageLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MessageLocalService
 * @generated
 */
public class MessageLocalServiceWrapper
	implements MessageLocalService, ServiceWrapper<MessageLocalService> {

	public MessageLocalServiceWrapper(MessageLocalService messageLocalService) {
		_messageLocalService = messageLocalService;
	}

	@Override
	public com.weprode.nero.messaging.model.Message addMessage(
		long folderId, long senderId, java.util.Date sendDate, long threadId,
		String messageSubject, String messageContent, boolean isNew, int type,
		long sendMessageId) {

		return _messageLocalService.addMessage(
			folderId, senderId, sendDate, threadId, messageSubject,
			messageContent, isNew, type, sendMessageId);
	}

	/**
	 * Adds the message to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param message the message
	 * @return the message that was added
	 */
	@Override
	public com.weprode.nero.messaging.model.Message addMessage(
		com.weprode.nero.messaging.model.Message message) {

		return _messageLocalService.addMessage(message);
	}

	@Override
	public int countMessages(long folderId) {
		return _messageLocalService.countMessages(folderId);
	}

	@Override
	public int countUnreadMessages(long folderId) {
		return _messageLocalService.countUnreadMessages(folderId);
	}

	/**
	 * Creates a new message with the primary key. Does not add the message to the database.
	 *
	 * @param messageId the primary key for the new message
	 * @return the new message
	 */
	@Override
	public com.weprode.nero.messaging.model.Message createMessage(
		long messageId) {

		return _messageLocalService.createMessage(messageId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messageId the primary key of the message
	 * @return the message that was removed
	 * @throws PortalException if a message with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.messaging.model.Message deleteMessage(
			long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageLocalService.deleteMessage(messageId);
	}

	/**
	 * Deletes the message from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param message the message
	 * @return the message that was removed
	 */
	@Override
	public com.weprode.nero.messaging.model.Message deleteMessage(
		com.weprode.nero.messaging.model.Message message) {

		return _messageLocalService.deleteMessage(message);
	}

	@Override
	public void deleteMessageAndDependencies(long messageId)
		throws com.liferay.portal.kernel.exception.SystemException,
			   com.weprode.nero.messaging.exception.NoSuchMessageException {

		_messageLocalService.deleteMessageAndDependencies(messageId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public void deleteUserMessages(com.liferay.portal.kernel.model.User user)
		throws com.liferay.portal.kernel.exception.SystemException {

		_messageLocalService.deleteUserMessages(user);
	}

	@Override
	public void deleteUsersMessages(
			java.util.List<com.liferay.portal.kernel.model.User> userList)
		throws com.liferay.portal.kernel.exception.SystemException {

		_messageLocalService.deleteUsersMessages(userList);
	}

	@Override
	public void deleteUsersMessages(String userIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		_messageLocalService.deleteUsersMessages(userIds);
	}

	@Override
	public void deleteUsersPersonalFolders(
		java.util.List<com.liferay.portal.kernel.model.User> userList) {

		_messageLocalService.deleteUsersPersonalFolders(userList);
	}

	@Override
	public void deleteUsersPersonalFolders(String userIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		_messageLocalService.deleteUsersPersonalFolders(userIds);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _messageLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _messageLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _messageLocalService.dynamicQuery();
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

		return _messageLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.messaging.model.impl.MessageModelImpl</code>.
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

		return _messageLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.messaging.model.impl.MessageModelImpl</code>.
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

		return _messageLocalService.dynamicQuery(
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

		return _messageLocalService.dynamicQueryCount(dynamicQuery);
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

		return _messageLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.messaging.model.Message fetchMessage(
		long messageId) {

		return _messageLocalService.fetchMessage(messageId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _messageLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.weprode.nero.messaging.model.Message>
		getAllFolderMessages(long folderId) {

		return _messageLocalService.getAllFolderMessages(folderId);
	}

	@Override
	public java.util.List<com.weprode.nero.messaging.model.Message>
		getFolderMessages(
			long folderId, int start, int end, boolean unreadOnly) {

		return _messageLocalService.getFolderMessages(
			folderId, start, end, unreadOnly);
	}

	@Override
	public java.util.List<com.weprode.nero.messaging.model.Message>
		getFolderMessages(
			long folderId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator odc) {

		return _messageLocalService.getFolderMessages(
			folderId, start, end, odc);
	}

	@Override
	public java.util.List<com.weprode.nero.messaging.model.Message>
		getFolderThreadMessages(long folderId, long threadId) {

		return _messageLocalService.getFolderThreadMessages(folderId, threadId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _messageLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Get the most recent message before the specified Date, which not belong to a threadId referenced before
	 * (This message will be the source of a new thread)
	 */
	@Override
	public com.weprode.nero.messaging.model.Message
		getLastOutThreadedMessageBeforeDate(
			long folderId, java.util.Date fromDate, boolean unReadOnly) {

		return _messageLocalService.getLastOutThreadedMessageBeforeDate(
			folderId, fromDate, unReadOnly);
	}

	@Override
	public com.weprode.nero.messaging.model.MessagingThread getLastThread(
		long userId, long folderId, java.util.Date fromDate,
		boolean unReadOnly) {

		return _messageLocalService.getLastThread(
			userId, folderId, fromDate, unReadOnly);
	}

	@Override
	public java.util.List<com.weprode.nero.messaging.model.MessagingThread>
		getLastThreads(
			long userId, long folderId, java.util.Date fromDate, int nbThreads,
			boolean unReadOnly) {

		return _messageLocalService.getLastThreads(
			userId, folderId, fromDate, nbThreads, unReadOnly);
	}

	/**
	 * Returns the message with the primary key.
	 *
	 * @param messageId the primary key of the message
	 * @return the message
	 * @throws PortalException if a message with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.messaging.model.Message getMessage(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageLocalService.getMessage(messageId);
	}

	/**
	 * Fetch message recipients
	 */
	@Override
	public java.util.List<com.liferay.portal.kernel.model.User>
		getMessageRecipients(Long[] userIds) {

		return _messageLocalService.getMessageRecipients(userIds);
	}

	/**
	 * Returns a range of all the messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.messaging.model.impl.MessageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of messages
	 * @param end the upper bound of the range of messages (not inclusive)
	 * @return the range of messages
	 */
	@Override
	public java.util.List<com.weprode.nero.messaging.model.Message> getMessages(
		int start, int end) {

		return _messageLocalService.getMessages(start, end);
	}

	@Override
	public java.util.List<com.weprode.nero.messaging.model.Message>
			getMessagesByFolder(long folderId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _messageLocalService.getMessagesByFolder(folderId, userId);
	}

	@Override
	public java.util.List<com.weprode.nero.messaging.model.Message>
		getMessagesByMessageSendId(long sendMessageId) {

		return _messageLocalService.getMessagesByMessageSendId(sendMessageId);
	}

	/**
	 * Returns the number of messages.
	 *
	 * @return the number of messages
	 */
	@Override
	public int getMessagesCount() {
		return _messageLocalService.getMessagesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _messageLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messageLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.weprode.nero.messaging.model.Message>
		getRecipientsMessages(
			com.weprode.nero.messaging.model.Message message) {

		return _messageLocalService.getRecipientsMessages(message);
	}

	@Override
	public com.weprode.nero.messaging.model.Message getThreadLastMessage(
		long folderId, long threadId) {

		return _messageLocalService.getThreadLastMessage(folderId, threadId);
	}

	@Override
	public com.weprode.nero.messaging.model.MessagingThread getUserThread(
			long userId, long threadId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _messageLocalService.getUserThread(userId, threadId);
	}

	@Override
	public java.util.List<com.weprode.nero.messaging.model.Message>
		getUserThreadMessages(long userId, long threadId) {

		return _messageLocalService.getUserThreadMessages(userId, threadId);
	}

	@Override
	public boolean performMailForwards(
			com.liferay.portal.kernel.model.User receiver, String subject,
			String content, String senderName,
			java.util.List<java.io.File> attachmentFileList)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _messageLocalService.performMailForwards(
			receiver, subject, content, senderName, attachmentFileList);
	}

	@Override
	public org.json.JSONObject saveDraft(
		long senderId, long draftMessageId, String subject, String content,
		java.util.List<Long> recipientIds, java.util.List<Long> attachFileIds,
		boolean isSupport) {

		return _messageLocalService.saveDraft(
			senderId, draftMessageId, subject, content, recipientIds,
			attachFileIds, isSupport);
	}

	@Override
	public boolean sendAutoReply(
		long receiverId, com.liferay.portal.kernel.model.User initialSender,
		long originMessageId) {

		return _messageLocalService.sendAutoReply(
			receiverId, initialSender, originMessageId);
	}

	@Override
	public boolean sendMessage(
		long senderId, java.util.List<Long> recipientList, String subject,
		String content) {

		return _messageLocalService.sendMessage(
			senderId, recipientList, subject, content);
	}

	@Override
	public boolean sendMessage(
		long senderId, java.util.List<Long> recipientList, String subject,
		String content, int type) {

		return _messageLocalService.sendMessage(
			senderId, recipientList, subject, content, type);
	}

	@Override
	public boolean sendMessage(
		long senderId, java.util.List<Long> recipientList, String subject,
		String content, int type, java.util.List<Long> attachFileIds,
		long draftMessageId, long originMessageId) {

		return _messageLocalService.sendMessage(
			senderId, recipientList, subject, content, type, attachFileIds,
			draftMessageId, originMessageId);
	}

	@Override
	public boolean sendSupportMessage(
		com.liferay.portal.kernel.model.User sender,
		java.util.List<Long> recipientList, String subject, String content,
		java.util.List<Long> attachFileIds, long draftMessageId,
		long originMessageId) {

		return _messageLocalService.sendSupportMessage(
			sender, recipientList, subject, content, attachFileIds,
			draftMessageId, originMessageId);
	}

	@Override
	public boolean setMessageAnswered(long messageId) {
		return _messageLocalService.setMessageAnswered(messageId);
	}

	@Override
	public boolean setMessageAsRead(long messageId, boolean isRead) {
		return _messageLocalService.setMessageAsRead(messageId, isRead);
	}

	@Override
	public boolean setMessageForwarded(long messageId) {
		return _messageLocalService.setMessageForwarded(messageId);
	}

	/**
	 * Updates the message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param message the message
	 * @return the message that was updated
	 */
	@Override
	public com.weprode.nero.messaging.model.Message updateMessage(
		com.weprode.nero.messaging.model.Message message) {

		return _messageLocalService.updateMessage(message);
	}

	@Override
	public MessageLocalService getWrappedService() {
		return _messageLocalService;
	}

	@Override
	public void setWrappedService(MessageLocalService messageLocalService) {
		_messageLocalService = messageLocalService;
	}

	private MessageLocalService _messageLocalService;

}