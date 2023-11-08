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

package com.weprode.facile.messaging.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.messaging.model.MessageRecipients;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the message recipients service. This utility wraps <code>com.weprode.facile.messaging.service.persistence.impl.MessageRecipientsPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageRecipientsPersistence
 * @generated
 */
public class MessageRecipientsUtil {

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
	public static void clearCache(MessageRecipients messageRecipients) {
		getPersistence().clearCache(messageRecipients);
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
	public static Map<Serializable, MessageRecipients> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MessageRecipients> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MessageRecipients> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MessageRecipients> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MessageRecipients> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MessageRecipients update(
		MessageRecipients messageRecipients) {

		return getPersistence().update(messageRecipients);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MessageRecipients update(
		MessageRecipients messageRecipients, ServiceContext serviceContext) {

		return getPersistence().update(messageRecipients, serviceContext);
	}

	/**
	 * Returns the message recipients where messageId = &#63; or throws a <code>NoSuchMessageRecipientsException</code> if it could not be found.
	 *
	 * @param messageId the message ID
	 * @return the matching message recipients
	 * @throws NoSuchMessageRecipientsException if a matching message recipients could not be found
	 */
	public static MessageRecipients findBymessageId(long messageId)
		throws com.weprode.facile.messaging.exception.
			NoSuchMessageRecipientsException {

		return getPersistence().findBymessageId(messageId);
	}

	/**
	 * Returns the message recipients where messageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param messageId the message ID
	 * @return the matching message recipients, or <code>null</code> if a matching message recipients could not be found
	 */
	public static MessageRecipients fetchBymessageId(long messageId) {
		return getPersistence().fetchBymessageId(messageId);
	}

	/**
	 * Returns the message recipients where messageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param messageId the message ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching message recipients, or <code>null</code> if a matching message recipients could not be found
	 */
	public static MessageRecipients fetchBymessageId(
		long messageId, boolean useFinderCache) {

		return getPersistence().fetchBymessageId(messageId, useFinderCache);
	}

	/**
	 * Removes the message recipients where messageId = &#63; from the database.
	 *
	 * @param messageId the message ID
	 * @return the message recipients that was removed
	 */
	public static MessageRecipients removeBymessageId(long messageId)
		throws com.weprode.facile.messaging.exception.
			NoSuchMessageRecipientsException {

		return getPersistence().removeBymessageId(messageId);
	}

	/**
	 * Returns the number of message recipientses where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @return the number of matching message recipientses
	 */
	public static int countBymessageId(long messageId) {
		return getPersistence().countBymessageId(messageId);
	}

	/**
	 * Caches the message recipients in the entity cache if it is enabled.
	 *
	 * @param messageRecipients the message recipients
	 */
	public static void cacheResult(MessageRecipients messageRecipients) {
		getPersistence().cacheResult(messageRecipients);
	}

	/**
	 * Caches the message recipientses in the entity cache if it is enabled.
	 *
	 * @param messageRecipientses the message recipientses
	 */
	public static void cacheResult(
		List<MessageRecipients> messageRecipientses) {

		getPersistence().cacheResult(messageRecipientses);
	}

	/**
	 * Creates a new message recipients with the primary key. Does not add the message recipients to the database.
	 *
	 * @param messageId the primary key for the new message recipients
	 * @return the new message recipients
	 */
	public static MessageRecipients create(long messageId) {
		return getPersistence().create(messageId);
	}

	/**
	 * Removes the message recipients with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param messageId the primary key of the message recipients
	 * @return the message recipients that was removed
	 * @throws NoSuchMessageRecipientsException if a message recipients with the primary key could not be found
	 */
	public static MessageRecipients remove(long messageId)
		throws com.weprode.facile.messaging.exception.
			NoSuchMessageRecipientsException {

		return getPersistence().remove(messageId);
	}

	public static MessageRecipients updateImpl(
		MessageRecipients messageRecipients) {

		return getPersistence().updateImpl(messageRecipients);
	}

	/**
	 * Returns the message recipients with the primary key or throws a <code>NoSuchMessageRecipientsException</code> if it could not be found.
	 *
	 * @param messageId the primary key of the message recipients
	 * @return the message recipients
	 * @throws NoSuchMessageRecipientsException if a message recipients with the primary key could not be found
	 */
	public static MessageRecipients findByPrimaryKey(long messageId)
		throws com.weprode.facile.messaging.exception.
			NoSuchMessageRecipientsException {

		return getPersistence().findByPrimaryKey(messageId);
	}

	/**
	 * Returns the message recipients with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param messageId the primary key of the message recipients
	 * @return the message recipients, or <code>null</code> if a message recipients with the primary key could not be found
	 */
	public static MessageRecipients fetchByPrimaryKey(long messageId) {
		return getPersistence().fetchByPrimaryKey(messageId);
	}

	/**
	 * Returns all the message recipientses.
	 *
	 * @return the message recipientses
	 */
	public static List<MessageRecipients> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the message recipientses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageRecipientsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of message recipientses
	 * @param end the upper bound of the range of message recipientses (not inclusive)
	 * @return the range of message recipientses
	 */
	public static List<MessageRecipients> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the message recipientses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageRecipientsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of message recipientses
	 * @param end the upper bound of the range of message recipientses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of message recipientses
	 */
	public static List<MessageRecipients> findAll(
		int start, int end,
		OrderByComparator<MessageRecipients> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the message recipientses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageRecipientsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of message recipientses
	 * @param end the upper bound of the range of message recipientses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of message recipientses
	 */
	public static List<MessageRecipients> findAll(
		int start, int end,
		OrderByComparator<MessageRecipients> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the message recipientses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of message recipientses.
	 *
	 * @return the number of message recipientses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static MessageRecipientsPersistence getPersistence() {
		return _persistence;
	}

	private static volatile MessageRecipientsPersistence _persistence;

}