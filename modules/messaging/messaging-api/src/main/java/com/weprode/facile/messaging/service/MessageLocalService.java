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
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.messaging.exception.NoSuchMessageException;
import com.weprode.facile.messaging.model.Message;
import com.weprode.facile.messaging.model.MessagingThread;

import java.io.File;
import java.io.Serializable;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for Message. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see MessageLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface MessageLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.facile.messaging.service.impl.MessageLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the message local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link MessageLocalServiceUtil} if injection and service tracking are not available.
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Message addMessage(
		long folderId, long senderId, Date sendDate, long threadId,
		String messageSubject, String messageContent, boolean isNew, int type,
		long sendMessageId);

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
	@Indexable(type = IndexableType.REINDEX)
	public Message addMessage(Message message);

	public int countMessages(long folderId);

	public int countUnreadMessages(long folderId);

	/**
	 * Creates a new message with the primary key. Does not add the message to the database.
	 *
	 * @param messageId the primary key for the new message
	 * @return the new message
	 */
	@Transactional(enabled = false)
	public Message createMessage(long messageId);

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

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
	@Indexable(type = IndexableType.DELETE)
	public Message deleteMessage(long messageId) throws PortalException;

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
	@Indexable(type = IndexableType.DELETE)
	public Message deleteMessage(Message message);

	@Indexable(type = IndexableType.DELETE)
	public void deleteMessageAndDependencies(long messageId)
		throws NoSuchMessageException, SystemException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	public void deleteUserMessages(User user) throws SystemException;

	public void deleteUsersMessages(List<User> userList) throws SystemException;

	public void deleteUsersMessages(String userIds)
		throws PortalException, SystemException;

	public void deleteUsersPersonalFolders(List<User> userList);

	public void deleteUsersPersonalFolders(String userIds)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> T dslQuery(DSLQuery dslQuery);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int dslQueryCount(DSLQuery dslQuery);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.messaging.model.impl.MessageModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.messaging.model.impl.MessageModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Message fetchMessage(long messageId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Message> getAllFolderMessages(long folderId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Message> getFolderMessages(
		long folderId, int start, int end, boolean unreadOnly);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Message> getFolderMessages(
		long folderId, int start, int end, OrderByComparator odc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Message> getFolderThreadMessages(long folderId, long threadId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the message with the primary key.
	 *
	 * @param messageId the primary key of the message
	 * @return the message
	 * @throws PortalException if a message with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Message getMessage(long messageId) throws PortalException;

	/**
	 * Fetch message recipients
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getMessageRecipients(Long[] userIds);

	/**
	 * Returns a range of all the messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.messaging.model.impl.MessageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of messages
	 * @param end the upper bound of the range of messages (not inclusive)
	 * @return the range of messages
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Message> getMessages(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Message> getMessagesByFolder(long folderId, long userId)
		throws SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Message> getMessagesByMessageSendId(long sendMessageId);

	/**
	 * Returns the number of messages.
	 *
	 * @return the number of messages
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getMessagesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MessagingThread getMessagingThread(long threadId)
		throws SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MessagingThread getMostRecentThread(
		List<Long> addedThreadIds, long folderId, Date fromDate,
		boolean unReadOnly);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Message> getRecipientsMessages(Message message);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Message getThreadLastMessage(long folderId, long threadId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MessagingThread> getThreads(
		long userId, long folderId, Date maxDate, int nbThreads,
		boolean unReadOnly);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Message> getUserThreadMessages(long userId, long threadId);

	public boolean performMailForwards(
			User receiver, String subject, String content, String senderName,
			List<File> attachmentFileList)
		throws SystemException;

	@Indexable(type = IndexableType.REINDEX)
	public JSONObject saveDraft(
		long senderId, long draftMessageId, String subject, String content,
		List<Long> recipientIds, List<Long> attachFileIds, long threadId,
		boolean isSupport);

	public boolean sendAutoReply(
		long receiverId, User initialSender, long originMessageId);

	public boolean sendMessage(
		long senderId, List<Long> recipientList, String subject,
		String content);

	public boolean sendMessage(
		long senderId, List<Long> recipientList, String subject, String content,
		int type);

	public boolean sendMessage(
		long senderId, List<Long> recipientList, String subject, String content,
		int type, List<Long> attachFileIds, long draftMessageId,
		long originMessageId);

	public boolean sendSupportMessage(
		User sender, List<Long> recipientList, String subject, String content,
		List<Long> attachFileIds, long draftMessageId, long originMessageId);

	public boolean setMessageAnswered(long messageId);

	public boolean setMessageAsRead(long messageId, boolean isRead);

	public boolean setMessageForwarded(long messageId);

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
	@Indexable(type = IndexableType.REINDEX)
	public Message updateMessage(Message message);

}