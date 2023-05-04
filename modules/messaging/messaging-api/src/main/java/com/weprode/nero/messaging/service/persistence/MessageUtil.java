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

package com.weprode.nero.messaging.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.messaging.model.Message;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the message service. This utility wraps <code>com.weprode.nero.messaging.service.persistence.impl.MessagePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagePersistence
 * @generated
 */
public class MessageUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Message message) {
		getPersistence().clearCache(message);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, Message> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Message> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Message> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Message> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Message> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Message update(Message message) {
		return getPersistence().update(message);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Message update(
		Message message, ServiceContext serviceContext) {

		return getPersistence().update(message, serviceContext);
	}

	/**
	 * Returns all the messages where sendMessageId = &#63;.
	 *
	 * @param sendMessageId the send message ID
	 * @return the matching messages
	 */
	public static List<Message> findBysendMessageId(long sendMessageId) {
		return getPersistence().findBysendMessageId(sendMessageId);
	}

	/**
	 * Returns a range of all the messages where sendMessageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageModelImpl</code>.
	 * </p>
	 *
	 * @param sendMessageId the send message ID
	 * @param start the lower bound of the range of messages
	 * @param end the upper bound of the range of messages (not inclusive)
	 * @return the range of matching messages
	 */
	public static List<Message> findBysendMessageId(
		long sendMessageId, int start, int end) {

		return getPersistence().findBysendMessageId(sendMessageId, start, end);
	}

	/**
	 * Returns an ordered range of all the messages where sendMessageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageModelImpl</code>.
	 * </p>
	 *
	 * @param sendMessageId the send message ID
	 * @param start the lower bound of the range of messages
	 * @param end the upper bound of the range of messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching messages
	 */
	public static List<Message> findBysendMessageId(
		long sendMessageId, int start, int end,
		OrderByComparator<Message> orderByComparator) {

		return getPersistence().findBysendMessageId(
			sendMessageId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the messages where sendMessageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageModelImpl</code>.
	 * </p>
	 *
	 * @param sendMessageId the send message ID
	 * @param start the lower bound of the range of messages
	 * @param end the upper bound of the range of messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching messages
	 */
	public static List<Message> findBysendMessageId(
		long sendMessageId, int start, int end,
		OrderByComparator<Message> orderByComparator, boolean useFinderCache) {

		return getPersistence().findBysendMessageId(
			sendMessageId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first message in the ordered set where sendMessageId = &#63;.
	 *
	 * @param sendMessageId the send message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message
	 * @throws NoSuchMessageException if a matching message could not be found
	 */
	public static Message findBysendMessageId_First(
			long sendMessageId, OrderByComparator<Message> orderByComparator)
		throws com.weprode.nero.messaging.exception.NoSuchMessageException {

		return getPersistence().findBysendMessageId_First(
			sendMessageId, orderByComparator);
	}

	/**
	 * Returns the first message in the ordered set where sendMessageId = &#63;.
	 *
	 * @param sendMessageId the send message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message, or <code>null</code> if a matching message could not be found
	 */
	public static Message fetchBysendMessageId_First(
		long sendMessageId, OrderByComparator<Message> orderByComparator) {

		return getPersistence().fetchBysendMessageId_First(
			sendMessageId, orderByComparator);
	}

	/**
	 * Returns the last message in the ordered set where sendMessageId = &#63;.
	 *
	 * @param sendMessageId the send message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message
	 * @throws NoSuchMessageException if a matching message could not be found
	 */
	public static Message findBysendMessageId_Last(
			long sendMessageId, OrderByComparator<Message> orderByComparator)
		throws com.weprode.nero.messaging.exception.NoSuchMessageException {

		return getPersistence().findBysendMessageId_Last(
			sendMessageId, orderByComparator);
	}

	/**
	 * Returns the last message in the ordered set where sendMessageId = &#63;.
	 *
	 * @param sendMessageId the send message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message, or <code>null</code> if a matching message could not be found
	 */
	public static Message fetchBysendMessageId_Last(
		long sendMessageId, OrderByComparator<Message> orderByComparator) {

		return getPersistence().fetchBysendMessageId_Last(
			sendMessageId, orderByComparator);
	}

	/**
	 * Returns the messages before and after the current message in the ordered set where sendMessageId = &#63;.
	 *
	 * @param messageId the primary key of the current message
	 * @param sendMessageId the send message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next message
	 * @throws NoSuchMessageException if a message with the primary key could not be found
	 */
	public static Message[] findBysendMessageId_PrevAndNext(
			long messageId, long sendMessageId,
			OrderByComparator<Message> orderByComparator)
		throws com.weprode.nero.messaging.exception.NoSuchMessageException {

		return getPersistence().findBysendMessageId_PrevAndNext(
			messageId, sendMessageId, orderByComparator);
	}

	/**
	 * Removes all the messages where sendMessageId = &#63; from the database.
	 *
	 * @param sendMessageId the send message ID
	 */
	public static void removeBysendMessageId(long sendMessageId) {
		getPersistence().removeBysendMessageId(sendMessageId);
	}

	/**
	 * Returns the number of messages where sendMessageId = &#63;.
	 *
	 * @param sendMessageId the send message ID
	 * @return the number of matching messages
	 */
	public static int countBysendMessageId(long sendMessageId) {
		return getPersistence().countBysendMessageId(sendMessageId);
	}

	/**
	 * Returns all the messages where folderId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @return the matching messages
	 */
	public static List<Message> findByfolderId(long folderId) {
		return getPersistence().findByfolderId(folderId);
	}

	/**
	 * Returns a range of all the messages where folderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageModelImpl</code>.
	 * </p>
	 *
	 * @param folderId the folder ID
	 * @param start the lower bound of the range of messages
	 * @param end the upper bound of the range of messages (not inclusive)
	 * @return the range of matching messages
	 */
	public static List<Message> findByfolderId(
		long folderId, int start, int end) {

		return getPersistence().findByfolderId(folderId, start, end);
	}

	/**
	 * Returns an ordered range of all the messages where folderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageModelImpl</code>.
	 * </p>
	 *
	 * @param folderId the folder ID
	 * @param start the lower bound of the range of messages
	 * @param end the upper bound of the range of messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching messages
	 */
	public static List<Message> findByfolderId(
		long folderId, int start, int end,
		OrderByComparator<Message> orderByComparator) {

		return getPersistence().findByfolderId(
			folderId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the messages where folderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageModelImpl</code>.
	 * </p>
	 *
	 * @param folderId the folder ID
	 * @param start the lower bound of the range of messages
	 * @param end the upper bound of the range of messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching messages
	 */
	public static List<Message> findByfolderId(
		long folderId, int start, int end,
		OrderByComparator<Message> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByfolderId(
			folderId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first message in the ordered set where folderId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message
	 * @throws NoSuchMessageException if a matching message could not be found
	 */
	public static Message findByfolderId_First(
			long folderId, OrderByComparator<Message> orderByComparator)
		throws com.weprode.nero.messaging.exception.NoSuchMessageException {

		return getPersistence().findByfolderId_First(
			folderId, orderByComparator);
	}

	/**
	 * Returns the first message in the ordered set where folderId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message, or <code>null</code> if a matching message could not be found
	 */
	public static Message fetchByfolderId_First(
		long folderId, OrderByComparator<Message> orderByComparator) {

		return getPersistence().fetchByfolderId_First(
			folderId, orderByComparator);
	}

	/**
	 * Returns the last message in the ordered set where folderId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message
	 * @throws NoSuchMessageException if a matching message could not be found
	 */
	public static Message findByfolderId_Last(
			long folderId, OrderByComparator<Message> orderByComparator)
		throws com.weprode.nero.messaging.exception.NoSuchMessageException {

		return getPersistence().findByfolderId_Last(
			folderId, orderByComparator);
	}

	/**
	 * Returns the last message in the ordered set where folderId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message, or <code>null</code> if a matching message could not be found
	 */
	public static Message fetchByfolderId_Last(
		long folderId, OrderByComparator<Message> orderByComparator) {

		return getPersistence().fetchByfolderId_Last(
			folderId, orderByComparator);
	}

	/**
	 * Returns the messages before and after the current message in the ordered set where folderId = &#63;.
	 *
	 * @param messageId the primary key of the current message
	 * @param folderId the folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next message
	 * @throws NoSuchMessageException if a message with the primary key could not be found
	 */
	public static Message[] findByfolderId_PrevAndNext(
			long messageId, long folderId,
			OrderByComparator<Message> orderByComparator)
		throws com.weprode.nero.messaging.exception.NoSuchMessageException {

		return getPersistence().findByfolderId_PrevAndNext(
			messageId, folderId, orderByComparator);
	}

	/**
	 * Removes all the messages where folderId = &#63; from the database.
	 *
	 * @param folderId the folder ID
	 */
	public static void removeByfolderId(long folderId) {
		getPersistence().removeByfolderId(folderId);
	}

	/**
	 * Returns the number of messages where folderId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @return the number of matching messages
	 */
	public static int countByfolderId(long folderId) {
		return getPersistence().countByfolderId(folderId);
	}

	/**
	 * Returns all the messages where threadId = &#63;.
	 *
	 * @param threadId the thread ID
	 * @return the matching messages
	 */
	public static List<Message> findBythreadId(long threadId) {
		return getPersistence().findBythreadId(threadId);
	}

	/**
	 * Returns a range of all the messages where threadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageModelImpl</code>.
	 * </p>
	 *
	 * @param threadId the thread ID
	 * @param start the lower bound of the range of messages
	 * @param end the upper bound of the range of messages (not inclusive)
	 * @return the range of matching messages
	 */
	public static List<Message> findBythreadId(
		long threadId, int start, int end) {

		return getPersistence().findBythreadId(threadId, start, end);
	}

	/**
	 * Returns an ordered range of all the messages where threadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageModelImpl</code>.
	 * </p>
	 *
	 * @param threadId the thread ID
	 * @param start the lower bound of the range of messages
	 * @param end the upper bound of the range of messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching messages
	 */
	public static List<Message> findBythreadId(
		long threadId, int start, int end,
		OrderByComparator<Message> orderByComparator) {

		return getPersistence().findBythreadId(
			threadId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the messages where threadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageModelImpl</code>.
	 * </p>
	 *
	 * @param threadId the thread ID
	 * @param start the lower bound of the range of messages
	 * @param end the upper bound of the range of messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching messages
	 */
	public static List<Message> findBythreadId(
		long threadId, int start, int end,
		OrderByComparator<Message> orderByComparator, boolean useFinderCache) {

		return getPersistence().findBythreadId(
			threadId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first message in the ordered set where threadId = &#63;.
	 *
	 * @param threadId the thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message
	 * @throws NoSuchMessageException if a matching message could not be found
	 */
	public static Message findBythreadId_First(
			long threadId, OrderByComparator<Message> orderByComparator)
		throws com.weprode.nero.messaging.exception.NoSuchMessageException {

		return getPersistence().findBythreadId_First(
			threadId, orderByComparator);
	}

	/**
	 * Returns the first message in the ordered set where threadId = &#63;.
	 *
	 * @param threadId the thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message, or <code>null</code> if a matching message could not be found
	 */
	public static Message fetchBythreadId_First(
		long threadId, OrderByComparator<Message> orderByComparator) {

		return getPersistence().fetchBythreadId_First(
			threadId, orderByComparator);
	}

	/**
	 * Returns the last message in the ordered set where threadId = &#63;.
	 *
	 * @param threadId the thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message
	 * @throws NoSuchMessageException if a matching message could not be found
	 */
	public static Message findBythreadId_Last(
			long threadId, OrderByComparator<Message> orderByComparator)
		throws com.weprode.nero.messaging.exception.NoSuchMessageException {

		return getPersistence().findBythreadId_Last(
			threadId, orderByComparator);
	}

	/**
	 * Returns the last message in the ordered set where threadId = &#63;.
	 *
	 * @param threadId the thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message, or <code>null</code> if a matching message could not be found
	 */
	public static Message fetchBythreadId_Last(
		long threadId, OrderByComparator<Message> orderByComparator) {

		return getPersistence().fetchBythreadId_Last(
			threadId, orderByComparator);
	}

	/**
	 * Returns the messages before and after the current message in the ordered set where threadId = &#63;.
	 *
	 * @param messageId the primary key of the current message
	 * @param threadId the thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next message
	 * @throws NoSuchMessageException if a message with the primary key could not be found
	 */
	public static Message[] findBythreadId_PrevAndNext(
			long messageId, long threadId,
			OrderByComparator<Message> orderByComparator)
		throws com.weprode.nero.messaging.exception.NoSuchMessageException {

		return getPersistence().findBythreadId_PrevAndNext(
			messageId, threadId, orderByComparator);
	}

	/**
	 * Removes all the messages where threadId = &#63; from the database.
	 *
	 * @param threadId the thread ID
	 */
	public static void removeBythreadId(long threadId) {
		getPersistence().removeBythreadId(threadId);
	}

	/**
	 * Returns the number of messages where threadId = &#63;.
	 *
	 * @param threadId the thread ID
	 * @return the number of matching messages
	 */
	public static int countBythreadId(long threadId) {
		return getPersistence().countBythreadId(threadId);
	}

	/**
	 * Returns all the messages where folderId = &#63; and threadId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param threadId the thread ID
	 * @return the matching messages
	 */
	public static List<Message> findByfolderId_threadId(
		long folderId, long threadId) {

		return getPersistence().findByfolderId_threadId(folderId, threadId);
	}

	/**
	 * Returns a range of all the messages where folderId = &#63; and threadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageModelImpl</code>.
	 * </p>
	 *
	 * @param folderId the folder ID
	 * @param threadId the thread ID
	 * @param start the lower bound of the range of messages
	 * @param end the upper bound of the range of messages (not inclusive)
	 * @return the range of matching messages
	 */
	public static List<Message> findByfolderId_threadId(
		long folderId, long threadId, int start, int end) {

		return getPersistence().findByfolderId_threadId(
			folderId, threadId, start, end);
	}

	/**
	 * Returns an ordered range of all the messages where folderId = &#63; and threadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageModelImpl</code>.
	 * </p>
	 *
	 * @param folderId the folder ID
	 * @param threadId the thread ID
	 * @param start the lower bound of the range of messages
	 * @param end the upper bound of the range of messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching messages
	 */
	public static List<Message> findByfolderId_threadId(
		long folderId, long threadId, int start, int end,
		OrderByComparator<Message> orderByComparator) {

		return getPersistence().findByfolderId_threadId(
			folderId, threadId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the messages where folderId = &#63; and threadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageModelImpl</code>.
	 * </p>
	 *
	 * @param folderId the folder ID
	 * @param threadId the thread ID
	 * @param start the lower bound of the range of messages
	 * @param end the upper bound of the range of messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching messages
	 */
	public static List<Message> findByfolderId_threadId(
		long folderId, long threadId, int start, int end,
		OrderByComparator<Message> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByfolderId_threadId(
			folderId, threadId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first message in the ordered set where folderId = &#63; and threadId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param threadId the thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message
	 * @throws NoSuchMessageException if a matching message could not be found
	 */
	public static Message findByfolderId_threadId_First(
			long folderId, long threadId,
			OrderByComparator<Message> orderByComparator)
		throws com.weprode.nero.messaging.exception.NoSuchMessageException {

		return getPersistence().findByfolderId_threadId_First(
			folderId, threadId, orderByComparator);
	}

	/**
	 * Returns the first message in the ordered set where folderId = &#63; and threadId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param threadId the thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message, or <code>null</code> if a matching message could not be found
	 */
	public static Message fetchByfolderId_threadId_First(
		long folderId, long threadId,
		OrderByComparator<Message> orderByComparator) {

		return getPersistence().fetchByfolderId_threadId_First(
			folderId, threadId, orderByComparator);
	}

	/**
	 * Returns the last message in the ordered set where folderId = &#63; and threadId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param threadId the thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message
	 * @throws NoSuchMessageException if a matching message could not be found
	 */
	public static Message findByfolderId_threadId_Last(
			long folderId, long threadId,
			OrderByComparator<Message> orderByComparator)
		throws com.weprode.nero.messaging.exception.NoSuchMessageException {

		return getPersistence().findByfolderId_threadId_Last(
			folderId, threadId, orderByComparator);
	}

	/**
	 * Returns the last message in the ordered set where folderId = &#63; and threadId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param threadId the thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message, or <code>null</code> if a matching message could not be found
	 */
	public static Message fetchByfolderId_threadId_Last(
		long folderId, long threadId,
		OrderByComparator<Message> orderByComparator) {

		return getPersistence().fetchByfolderId_threadId_Last(
			folderId, threadId, orderByComparator);
	}

	/**
	 * Returns the messages before and after the current message in the ordered set where folderId = &#63; and threadId = &#63;.
	 *
	 * @param messageId the primary key of the current message
	 * @param folderId the folder ID
	 * @param threadId the thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next message
	 * @throws NoSuchMessageException if a message with the primary key could not be found
	 */
	public static Message[] findByfolderId_threadId_PrevAndNext(
			long messageId, long folderId, long threadId,
			OrderByComparator<Message> orderByComparator)
		throws com.weprode.nero.messaging.exception.NoSuchMessageException {

		return getPersistence().findByfolderId_threadId_PrevAndNext(
			messageId, folderId, threadId, orderByComparator);
	}

	/**
	 * Removes all the messages where folderId = &#63; and threadId = &#63; from the database.
	 *
	 * @param folderId the folder ID
	 * @param threadId the thread ID
	 */
	public static void removeByfolderId_threadId(long folderId, long threadId) {
		getPersistence().removeByfolderId_threadId(folderId, threadId);
	}

	/**
	 * Returns the number of messages where folderId = &#63; and threadId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param threadId the thread ID
	 * @return the number of matching messages
	 */
	public static int countByfolderId_threadId(long folderId, long threadId) {
		return getPersistence().countByfolderId_threadId(folderId, threadId);
	}

	/**
	 * Returns all the messages where folderId = &#63; and isNew = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param isNew the is new
	 * @return the matching messages
	 */
	public static List<Message> findByfolderId_isNew(
		long folderId, boolean isNew) {

		return getPersistence().findByfolderId_isNew(folderId, isNew);
	}

	/**
	 * Returns a range of all the messages where folderId = &#63; and isNew = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageModelImpl</code>.
	 * </p>
	 *
	 * @param folderId the folder ID
	 * @param isNew the is new
	 * @param start the lower bound of the range of messages
	 * @param end the upper bound of the range of messages (not inclusive)
	 * @return the range of matching messages
	 */
	public static List<Message> findByfolderId_isNew(
		long folderId, boolean isNew, int start, int end) {

		return getPersistence().findByfolderId_isNew(
			folderId, isNew, start, end);
	}

	/**
	 * Returns an ordered range of all the messages where folderId = &#63; and isNew = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageModelImpl</code>.
	 * </p>
	 *
	 * @param folderId the folder ID
	 * @param isNew the is new
	 * @param start the lower bound of the range of messages
	 * @param end the upper bound of the range of messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching messages
	 */
	public static List<Message> findByfolderId_isNew(
		long folderId, boolean isNew, int start, int end,
		OrderByComparator<Message> orderByComparator) {

		return getPersistence().findByfolderId_isNew(
			folderId, isNew, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the messages where folderId = &#63; and isNew = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageModelImpl</code>.
	 * </p>
	 *
	 * @param folderId the folder ID
	 * @param isNew the is new
	 * @param start the lower bound of the range of messages
	 * @param end the upper bound of the range of messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching messages
	 */
	public static List<Message> findByfolderId_isNew(
		long folderId, boolean isNew, int start, int end,
		OrderByComparator<Message> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByfolderId_isNew(
			folderId, isNew, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first message in the ordered set where folderId = &#63; and isNew = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param isNew the is new
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message
	 * @throws NoSuchMessageException if a matching message could not be found
	 */
	public static Message findByfolderId_isNew_First(
			long folderId, boolean isNew,
			OrderByComparator<Message> orderByComparator)
		throws com.weprode.nero.messaging.exception.NoSuchMessageException {

		return getPersistence().findByfolderId_isNew_First(
			folderId, isNew, orderByComparator);
	}

	/**
	 * Returns the first message in the ordered set where folderId = &#63; and isNew = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param isNew the is new
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message, or <code>null</code> if a matching message could not be found
	 */
	public static Message fetchByfolderId_isNew_First(
		long folderId, boolean isNew,
		OrderByComparator<Message> orderByComparator) {

		return getPersistence().fetchByfolderId_isNew_First(
			folderId, isNew, orderByComparator);
	}

	/**
	 * Returns the last message in the ordered set where folderId = &#63; and isNew = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param isNew the is new
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message
	 * @throws NoSuchMessageException if a matching message could not be found
	 */
	public static Message findByfolderId_isNew_Last(
			long folderId, boolean isNew,
			OrderByComparator<Message> orderByComparator)
		throws com.weprode.nero.messaging.exception.NoSuchMessageException {

		return getPersistence().findByfolderId_isNew_Last(
			folderId, isNew, orderByComparator);
	}

	/**
	 * Returns the last message in the ordered set where folderId = &#63; and isNew = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param isNew the is new
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message, or <code>null</code> if a matching message could not be found
	 */
	public static Message fetchByfolderId_isNew_Last(
		long folderId, boolean isNew,
		OrderByComparator<Message> orderByComparator) {

		return getPersistence().fetchByfolderId_isNew_Last(
			folderId, isNew, orderByComparator);
	}

	/**
	 * Returns the messages before and after the current message in the ordered set where folderId = &#63; and isNew = &#63;.
	 *
	 * @param messageId the primary key of the current message
	 * @param folderId the folder ID
	 * @param isNew the is new
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next message
	 * @throws NoSuchMessageException if a message with the primary key could not be found
	 */
	public static Message[] findByfolderId_isNew_PrevAndNext(
			long messageId, long folderId, boolean isNew,
			OrderByComparator<Message> orderByComparator)
		throws com.weprode.nero.messaging.exception.NoSuchMessageException {

		return getPersistence().findByfolderId_isNew_PrevAndNext(
			messageId, folderId, isNew, orderByComparator);
	}

	/**
	 * Removes all the messages where folderId = &#63; and isNew = &#63; from the database.
	 *
	 * @param folderId the folder ID
	 * @param isNew the is new
	 */
	public static void removeByfolderId_isNew(long folderId, boolean isNew) {
		getPersistence().removeByfolderId_isNew(folderId, isNew);
	}

	/**
	 * Returns the number of messages where folderId = &#63; and isNew = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param isNew the is new
	 * @return the number of matching messages
	 */
	public static int countByfolderId_isNew(long folderId, boolean isNew) {
		return getPersistence().countByfolderId_isNew(folderId, isNew);
	}

	/**
	 * Caches the message in the entity cache if it is enabled.
	 *
	 * @param message the message
	 */
	public static void cacheResult(Message message) {
		getPersistence().cacheResult(message);
	}

	/**
	 * Caches the messages in the entity cache if it is enabled.
	 *
	 * @param messages the messages
	 */
	public static void cacheResult(List<Message> messages) {
		getPersistence().cacheResult(messages);
	}

	/**
	 * Creates a new message with the primary key. Does not add the message to the database.
	 *
	 * @param messageId the primary key for the new message
	 * @return the new message
	 */
	public static Message create(long messageId) {
		return getPersistence().create(messageId);
	}

	/**
	 * Removes the message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param messageId the primary key of the message
	 * @return the message that was removed
	 * @throws NoSuchMessageException if a message with the primary key could not be found
	 */
	public static Message remove(long messageId)
		throws com.weprode.nero.messaging.exception.NoSuchMessageException {

		return getPersistence().remove(messageId);
	}

	public static Message updateImpl(Message message) {
		return getPersistence().updateImpl(message);
	}

	/**
	 * Returns the message with the primary key or throws a <code>NoSuchMessageException</code> if it could not be found.
	 *
	 * @param messageId the primary key of the message
	 * @return the message
	 * @throws NoSuchMessageException if a message with the primary key could not be found
	 */
	public static Message findByPrimaryKey(long messageId)
		throws com.weprode.nero.messaging.exception.NoSuchMessageException {

		return getPersistence().findByPrimaryKey(messageId);
	}

	/**
	 * Returns the message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param messageId the primary key of the message
	 * @return the message, or <code>null</code> if a message with the primary key could not be found
	 */
	public static Message fetchByPrimaryKey(long messageId) {
		return getPersistence().fetchByPrimaryKey(messageId);
	}

	/**
	 * Returns all the messages.
	 *
	 * @return the messages
	 */
	public static List<Message> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of messages
	 * @param end the upper bound of the range of messages (not inclusive)
	 * @return the range of messages
	 */
	public static List<Message> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of messages
	 * @param end the upper bound of the range of messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of messages
	 */
	public static List<Message> findAll(
		int start, int end, OrderByComparator<Message> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of messages
	 * @param end the upper bound of the range of messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of messages
	 */
	public static List<Message> findAll(
		int start, int end, OrderByComparator<Message> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the messages from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of messages.
	 *
	 * @return the number of messages
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static MessagePersistence getPersistence() {
		return _persistence;
	}

	private static volatile MessagePersistence _persistence;

}