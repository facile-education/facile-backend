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

import com.weprode.nero.messaging.model.MessageContent;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the message content service. This utility wraps <code>com.weprode.nero.messaging.service.persistence.impl.MessageContentPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageContentPersistence
 * @generated
 */
public class MessageContentUtil {

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
	public static void clearCache(MessageContent messageContent) {
		getPersistence().clearCache(messageContent);
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
	public static Map<Serializable, MessageContent> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MessageContent> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MessageContent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MessageContent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MessageContent> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MessageContent update(MessageContent messageContent) {
		return getPersistence().update(messageContent);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MessageContent update(
		MessageContent messageContent, ServiceContext serviceContext) {

		return getPersistence().update(messageContent, serviceContext);
	}

	/**
	 * Returns the message content where messageId = &#63; or throws a <code>NoSuchMessageContentException</code> if it could not be found.
	 *
	 * @param messageId the message ID
	 * @return the matching message content
	 * @throws NoSuchMessageContentException if a matching message content could not be found
	 */
	public static MessageContent findBymessageId(long messageId)
		throws com.weprode.nero.messaging.exception.
			NoSuchMessageContentException {

		return getPersistence().findBymessageId(messageId);
	}

	/**
	 * Returns the message content where messageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param messageId the message ID
	 * @return the matching message content, or <code>null</code> if a matching message content could not be found
	 */
	public static MessageContent fetchBymessageId(long messageId) {
		return getPersistence().fetchBymessageId(messageId);
	}

	/**
	 * Returns the message content where messageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param messageId the message ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching message content, or <code>null</code> if a matching message content could not be found
	 */
	public static MessageContent fetchBymessageId(
		long messageId, boolean useFinderCache) {

		return getPersistence().fetchBymessageId(messageId, useFinderCache);
	}

	/**
	 * Removes the message content where messageId = &#63; from the database.
	 *
	 * @param messageId the message ID
	 * @return the message content that was removed
	 */
	public static MessageContent removeBymessageId(long messageId)
		throws com.weprode.nero.messaging.exception.
			NoSuchMessageContentException {

		return getPersistence().removeBymessageId(messageId);
	}

	/**
	 * Returns the number of message contents where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @return the number of matching message contents
	 */
	public static int countBymessageId(long messageId) {
		return getPersistence().countBymessageId(messageId);
	}

	/**
	 * Caches the message content in the entity cache if it is enabled.
	 *
	 * @param messageContent the message content
	 */
	public static void cacheResult(MessageContent messageContent) {
		getPersistence().cacheResult(messageContent);
	}

	/**
	 * Caches the message contents in the entity cache if it is enabled.
	 *
	 * @param messageContents the message contents
	 */
	public static void cacheResult(List<MessageContent> messageContents) {
		getPersistence().cacheResult(messageContents);
	}

	/**
	 * Creates a new message content with the primary key. Does not add the message content to the database.
	 *
	 * @param messageId the primary key for the new message content
	 * @return the new message content
	 */
	public static MessageContent create(long messageId) {
		return getPersistence().create(messageId);
	}

	/**
	 * Removes the message content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param messageId the primary key of the message content
	 * @return the message content that was removed
	 * @throws NoSuchMessageContentException if a message content with the primary key could not be found
	 */
	public static MessageContent remove(long messageId)
		throws com.weprode.nero.messaging.exception.
			NoSuchMessageContentException {

		return getPersistence().remove(messageId);
	}

	public static MessageContent updateImpl(MessageContent messageContent) {
		return getPersistence().updateImpl(messageContent);
	}

	/**
	 * Returns the message content with the primary key or throws a <code>NoSuchMessageContentException</code> if it could not be found.
	 *
	 * @param messageId the primary key of the message content
	 * @return the message content
	 * @throws NoSuchMessageContentException if a message content with the primary key could not be found
	 */
	public static MessageContent findByPrimaryKey(long messageId)
		throws com.weprode.nero.messaging.exception.
			NoSuchMessageContentException {

		return getPersistence().findByPrimaryKey(messageId);
	}

	/**
	 * Returns the message content with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param messageId the primary key of the message content
	 * @return the message content, or <code>null</code> if a message content with the primary key could not be found
	 */
	public static MessageContent fetchByPrimaryKey(long messageId) {
		return getPersistence().fetchByPrimaryKey(messageId);
	}

	/**
	 * Returns all the message contents.
	 *
	 * @return the message contents
	 */
	public static List<MessageContent> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the message contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageContentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of message contents
	 * @param end the upper bound of the range of message contents (not inclusive)
	 * @return the range of message contents
	 */
	public static List<MessageContent> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the message contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageContentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of message contents
	 * @param end the upper bound of the range of message contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of message contents
	 */
	public static List<MessageContent> findAll(
		int start, int end,
		OrderByComparator<MessageContent> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the message contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageContentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of message contents
	 * @param end the upper bound of the range of message contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of message contents
	 */
	public static List<MessageContent> findAll(
		int start, int end, OrderByComparator<MessageContent> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the message contents from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of message contents.
	 *
	 * @return the number of message contents
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static MessageContentPersistence getPersistence() {
		return _persistence;
	}

	private static volatile MessageContentPersistence _persistence;

}