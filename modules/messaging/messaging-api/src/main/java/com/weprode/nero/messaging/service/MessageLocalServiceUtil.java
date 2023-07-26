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

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.messaging.model.Message;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for Message. This utility wraps
 * <code>com.weprode.nero.messaging.service.impl.MessageLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see MessageLocalService
 * @generated
 */
public class MessageLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.messaging.service.impl.MessageLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static Message addMessage(
		long folderId, long senderId, java.util.Date sendDate, long threadId,
		String messageSubject, String messageContent, boolean isNew, int type,
		long sendMessageId) {

		return getService().addMessage(
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
	public static Message addMessage(Message message) {
		return getService().addMessage(message);
	}

	public static int countMessages(long folderId) {
		return getService().countMessages(folderId);
	}

	public static int countUnreadMessages(long folderId) {
		return getService().countUnreadMessages(folderId);
	}

	/**
	 * Creates a new message with the primary key. Does not add the message to the database.
	 *
	 * @param messageId the primary key for the new message
	 * @return the new message
	 */
	public static Message createMessage(long messageId) {
		return getService().createMessage(messageId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
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
	public static Message deleteMessage(long messageId) throws PortalException {
		return getService().deleteMessage(messageId);
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
	public static Message deleteMessage(Message message) {
		return getService().deleteMessage(message);
	}

	public static void deleteMessageAndDependencies(long messageId)
		throws com.weprode.nero.messaging.exception.NoSuchMessageException,
			   SystemException {

		getService().deleteMessageAndDependencies(messageId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static void deleteUserMessages(
			com.liferay.portal.kernel.model.User user)
		throws SystemException {

		getService().deleteUserMessages(user);
	}

	public static void deleteUsersMessages(
			List<com.liferay.portal.kernel.model.User> userList)
		throws SystemException {

		getService().deleteUsersMessages(userList);
	}

	public static void deleteUsersMessages(String userIds)
		throws PortalException, SystemException {

		getService().deleteUsersMessages(userIds);
	}

	public static void deleteUsersPersonalFolders(
		List<com.liferay.portal.kernel.model.User> userList) {

		getService().deleteUsersPersonalFolders(userList);
	}

	public static void deleteUsersPersonalFolders(String userIds)
		throws PortalException {

		getService().deleteUsersPersonalFolders(userIds);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.messaging.model.impl.MessageModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.messaging.model.impl.MessageModelImpl</code>.
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

	public static Message fetchMessage(long messageId) {
		return getService().fetchMessage(messageId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<Message> getAllFolderMessages(long folderId) {
		return getService().getAllFolderMessages(folderId);
	}

	public static List<Message> getFolderMessages(
		long folderId, int start, int end, boolean unreadOnly) {

		return getService().getFolderMessages(folderId, start, end, unreadOnly);
	}

	public static List<Message> getFolderMessages(
		long folderId, int start, int end, OrderByComparator odc) {

		return getService().getFolderMessages(folderId, start, end, odc);
	}

	public static List<Message> getFolderThreadMessages(
		long folderId, long threadId) {

		return getService().getFolderThreadMessages(folderId, threadId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the message with the primary key.
	 *
	 * @param messageId the primary key of the message
	 * @return the message
	 * @throws PortalException if a message with the primary key could not be found
	 */
	public static Message getMessage(long messageId) throws PortalException {
		return getService().getMessage(messageId);
	}

	/**
	 * Fetch message recipients
	 */
	public static List<com.liferay.portal.kernel.model.User>
		getMessageRecipients(Long[] userIds) {

		return getService().getMessageRecipients(userIds);
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
	public static List<Message> getMessages(int start, int end) {
		return getService().getMessages(start, end);
	}

	public static List<Message> getMessagesByFolder(long folderId, long userId)
		throws SystemException {

		return getService().getMessagesByFolder(folderId, userId);
	}

	public static List<Message> getMessagesByMessageSendId(long sendMessageId) {
		return getService().getMessagesByMessageSendId(sendMessageId);
	}

	/**
	 * Returns the number of messages.
	 *
	 * @return the number of messages
	 */
	public static int getMessagesCount() {
		return getService().getMessagesCount();
	}

	public static com.weprode.nero.messaging.model.MessagingThread
			getMessagingThread(long threadId)
		throws SystemException {

		return getService().getMessagingThread(threadId);
	}

	public static com.weprode.nero.messaging.model.MessagingThread
		getMostRecentThread(
			List<Long> addedThreadIds, long folderId, java.util.Date fromDate,
			boolean unReadOnly) {

		return getService().getMostRecentThread(
			addedThreadIds, folderId, fromDate, unReadOnly);
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

	public static List<Message> getRecipientsMessages(Message message) {
		return getService().getRecipientsMessages(message);
	}

	public static Message getThreadLastMessage(long folderId, long threadId) {
		return getService().getThreadLastMessage(folderId, threadId);
	}

	public static List<com.weprode.nero.messaging.model.MessagingThread>
		getThreads(
			long userId, long folderId, java.util.Date maxDate, int nbThreads,
			boolean unReadOnly) {

		return getService().getThreads(
			userId, folderId, maxDate, nbThreads, unReadOnly);
	}

	public static List<Message> getUserThreadMessages(
		long userId, long threadId) {

		return getService().getUserThreadMessages(userId, threadId);
	}

	public static boolean performMailForwards(
			com.liferay.portal.kernel.model.User receiver, String subject,
			String content, String senderName,
			List<java.io.File> attachmentFileList)
		throws SystemException {

		return getService().performMailForwards(
			receiver, subject, content, senderName, attachmentFileList);
	}

	public static org.json.JSONObject saveDraft(
		long senderId, long draftMessageId, String subject, String content,
		List<Long> recipientIds, List<Long> attachFileIds, boolean isSupport) {

		return getService().saveDraft(
			senderId, draftMessageId, subject, content, recipientIds,
			attachFileIds, isSupport);
	}

	public static boolean sendAutoReply(
		long receiverId, com.liferay.portal.kernel.model.User initialSender,
		long originMessageId) {

		return getService().sendAutoReply(
			receiverId, initialSender, originMessageId);
	}

	public static boolean sendMessage(
		long senderId, List<Long> recipientList, String subject,
		String content) {

		return getService().sendMessage(
			senderId, recipientList, subject, content);
	}

	public static boolean sendMessage(
		long senderId, List<Long> recipientList, String subject, String content,
		int type) {

		return getService().sendMessage(
			senderId, recipientList, subject, content, type);
	}

	public static boolean sendMessage(
		long senderId, List<Long> recipientList, String subject, String content,
		int type, List<Long> attachFileIds, long draftMessageId,
		long originMessageId) {

		return getService().sendMessage(
			senderId, recipientList, subject, content, type, attachFileIds,
			draftMessageId, originMessageId);
	}

	public static boolean sendSupportMessage(
		com.liferay.portal.kernel.model.User sender, List<Long> recipientList,
		String subject, String content, List<Long> attachFileIds,
		long draftMessageId, long originMessageId) {

		return getService().sendSupportMessage(
			sender, recipientList, subject, content, attachFileIds,
			draftMessageId, originMessageId);
	}

	public static boolean setMessageAnswered(long messageId) {
		return getService().setMessageAnswered(messageId);
	}

	public static boolean setMessageAsRead(long messageId, boolean isRead) {
		return getService().setMessageAsRead(messageId, isRead);
	}

	public static boolean setMessageForwarded(long messageId) {
		return getService().setMessageForwarded(messageId);
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
	public static Message updateMessage(Message message) {
		return getService().updateMessage(message);
	}

	public static MessageLocalService getService() {
		return _service;
	}

	private static volatile MessageLocalService _service;

}