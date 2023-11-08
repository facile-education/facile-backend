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

import com.weprode.facile.messaging.model.MessageAttachFile;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the message attach file service. This utility wraps <code>com.weprode.facile.messaging.service.persistence.impl.MessageAttachFilePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageAttachFilePersistence
 * @generated
 */
public class MessageAttachFileUtil {

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
	public static void clearCache(MessageAttachFile messageAttachFile) {
		getPersistence().clearCache(messageAttachFile);
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
	public static Map<Serializable, MessageAttachFile> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MessageAttachFile> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MessageAttachFile> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MessageAttachFile> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MessageAttachFile> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MessageAttachFile update(
		MessageAttachFile messageAttachFile) {

		return getPersistence().update(messageAttachFile);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MessageAttachFile update(
		MessageAttachFile messageAttachFile, ServiceContext serviceContext) {

		return getPersistence().update(messageAttachFile, serviceContext);
	}

	/**
	 * Returns all the message attach files where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @return the matching message attach files
	 */
	public static List<MessageAttachFile> findBymessageId(long messageId) {
		return getPersistence().findBymessageId(messageId);
	}

	/**
	 * Returns a range of all the message attach files where messageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageAttachFileModelImpl</code>.
	 * </p>
	 *
	 * @param messageId the message ID
	 * @param start the lower bound of the range of message attach files
	 * @param end the upper bound of the range of message attach files (not inclusive)
	 * @return the range of matching message attach files
	 */
	public static List<MessageAttachFile> findBymessageId(
		long messageId, int start, int end) {

		return getPersistence().findBymessageId(messageId, start, end);
	}

	/**
	 * Returns an ordered range of all the message attach files where messageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageAttachFileModelImpl</code>.
	 * </p>
	 *
	 * @param messageId the message ID
	 * @param start the lower bound of the range of message attach files
	 * @param end the upper bound of the range of message attach files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching message attach files
	 */
	public static List<MessageAttachFile> findBymessageId(
		long messageId, int start, int end,
		OrderByComparator<MessageAttachFile> orderByComparator) {

		return getPersistence().findBymessageId(
			messageId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the message attach files where messageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageAttachFileModelImpl</code>.
	 * </p>
	 *
	 * @param messageId the message ID
	 * @param start the lower bound of the range of message attach files
	 * @param end the upper bound of the range of message attach files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching message attach files
	 */
	public static List<MessageAttachFile> findBymessageId(
		long messageId, int start, int end,
		OrderByComparator<MessageAttachFile> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBymessageId(
			messageId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first message attach file in the ordered set where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message attach file
	 * @throws NoSuchMessageAttachFileException if a matching message attach file could not be found
	 */
	public static MessageAttachFile findBymessageId_First(
			long messageId,
			OrderByComparator<MessageAttachFile> orderByComparator)
		throws com.weprode.facile.messaging.exception.
			NoSuchMessageAttachFileException {

		return getPersistence().findBymessageId_First(
			messageId, orderByComparator);
	}

	/**
	 * Returns the first message attach file in the ordered set where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message attach file, or <code>null</code> if a matching message attach file could not be found
	 */
	public static MessageAttachFile fetchBymessageId_First(
		long messageId,
		OrderByComparator<MessageAttachFile> orderByComparator) {

		return getPersistence().fetchBymessageId_First(
			messageId, orderByComparator);
	}

	/**
	 * Returns the last message attach file in the ordered set where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message attach file
	 * @throws NoSuchMessageAttachFileException if a matching message attach file could not be found
	 */
	public static MessageAttachFile findBymessageId_Last(
			long messageId,
			OrderByComparator<MessageAttachFile> orderByComparator)
		throws com.weprode.facile.messaging.exception.
			NoSuchMessageAttachFileException {

		return getPersistence().findBymessageId_Last(
			messageId, orderByComparator);
	}

	/**
	 * Returns the last message attach file in the ordered set where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message attach file, or <code>null</code> if a matching message attach file could not be found
	 */
	public static MessageAttachFile fetchBymessageId_Last(
		long messageId,
		OrderByComparator<MessageAttachFile> orderByComparator) {

		return getPersistence().fetchBymessageId_Last(
			messageId, orderByComparator);
	}

	/**
	 * Returns the message attach files before and after the current message attach file in the ordered set where messageId = &#63;.
	 *
	 * @param messageAttachFilePK the primary key of the current message attach file
	 * @param messageId the message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next message attach file
	 * @throws NoSuchMessageAttachFileException if a message attach file with the primary key could not be found
	 */
	public static MessageAttachFile[] findBymessageId_PrevAndNext(
			MessageAttachFilePK messageAttachFilePK, long messageId,
			OrderByComparator<MessageAttachFile> orderByComparator)
		throws com.weprode.facile.messaging.exception.
			NoSuchMessageAttachFileException {

		return getPersistence().findBymessageId_PrevAndNext(
			messageAttachFilePK, messageId, orderByComparator);
	}

	/**
	 * Removes all the message attach files where messageId = &#63; from the database.
	 *
	 * @param messageId the message ID
	 */
	public static void removeBymessageId(long messageId) {
		getPersistence().removeBymessageId(messageId);
	}

	/**
	 * Returns the number of message attach files where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @return the number of matching message attach files
	 */
	public static int countBymessageId(long messageId) {
		return getPersistence().countBymessageId(messageId);
	}

	/**
	 * Caches the message attach file in the entity cache if it is enabled.
	 *
	 * @param messageAttachFile the message attach file
	 */
	public static void cacheResult(MessageAttachFile messageAttachFile) {
		getPersistence().cacheResult(messageAttachFile);
	}

	/**
	 * Caches the message attach files in the entity cache if it is enabled.
	 *
	 * @param messageAttachFiles the message attach files
	 */
	public static void cacheResult(List<MessageAttachFile> messageAttachFiles) {
		getPersistence().cacheResult(messageAttachFiles);
	}

	/**
	 * Creates a new message attach file with the primary key. Does not add the message attach file to the database.
	 *
	 * @param messageAttachFilePK the primary key for the new message attach file
	 * @return the new message attach file
	 */
	public static MessageAttachFile create(
		MessageAttachFilePK messageAttachFilePK) {

		return getPersistence().create(messageAttachFilePK);
	}

	/**
	 * Removes the message attach file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param messageAttachFilePK the primary key of the message attach file
	 * @return the message attach file that was removed
	 * @throws NoSuchMessageAttachFileException if a message attach file with the primary key could not be found
	 */
	public static MessageAttachFile remove(
			MessageAttachFilePK messageAttachFilePK)
		throws com.weprode.facile.messaging.exception.
			NoSuchMessageAttachFileException {

		return getPersistence().remove(messageAttachFilePK);
	}

	public static MessageAttachFile updateImpl(
		MessageAttachFile messageAttachFile) {

		return getPersistence().updateImpl(messageAttachFile);
	}

	/**
	 * Returns the message attach file with the primary key or throws a <code>NoSuchMessageAttachFileException</code> if it could not be found.
	 *
	 * @param messageAttachFilePK the primary key of the message attach file
	 * @return the message attach file
	 * @throws NoSuchMessageAttachFileException if a message attach file with the primary key could not be found
	 */
	public static MessageAttachFile findByPrimaryKey(
			MessageAttachFilePK messageAttachFilePK)
		throws com.weprode.facile.messaging.exception.
			NoSuchMessageAttachFileException {

		return getPersistence().findByPrimaryKey(messageAttachFilePK);
	}

	/**
	 * Returns the message attach file with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param messageAttachFilePK the primary key of the message attach file
	 * @return the message attach file, or <code>null</code> if a message attach file with the primary key could not be found
	 */
	public static MessageAttachFile fetchByPrimaryKey(
		MessageAttachFilePK messageAttachFilePK) {

		return getPersistence().fetchByPrimaryKey(messageAttachFilePK);
	}

	/**
	 * Returns all the message attach files.
	 *
	 * @return the message attach files
	 */
	public static List<MessageAttachFile> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the message attach files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageAttachFileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of message attach files
	 * @param end the upper bound of the range of message attach files (not inclusive)
	 * @return the range of message attach files
	 */
	public static List<MessageAttachFile> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the message attach files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageAttachFileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of message attach files
	 * @param end the upper bound of the range of message attach files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of message attach files
	 */
	public static List<MessageAttachFile> findAll(
		int start, int end,
		OrderByComparator<MessageAttachFile> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the message attach files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageAttachFileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of message attach files
	 * @param end the upper bound of the range of message attach files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of message attach files
	 */
	public static List<MessageAttachFile> findAll(
		int start, int end,
		OrderByComparator<MessageAttachFile> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the message attach files from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of message attach files.
	 *
	 * @return the number of message attach files
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static MessageAttachFilePersistence getPersistence() {
		return _persistence;
	}

	private static volatile MessageAttachFilePersistence _persistence;

}