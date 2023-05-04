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

import com.weprode.nero.messaging.model.MessageFolder;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the message folder service. This utility wraps <code>com.weprode.nero.messaging.service.persistence.impl.MessageFolderPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageFolderPersistence
 * @generated
 */
public class MessageFolderUtil {

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
	public static void clearCache(MessageFolder messageFolder) {
		getPersistence().clearCache(messageFolder);
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
	public static Map<Serializable, MessageFolder> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MessageFolder> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MessageFolder> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MessageFolder> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MessageFolder> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MessageFolder update(MessageFolder messageFolder) {
		return getPersistence().update(messageFolder);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MessageFolder update(
		MessageFolder messageFolder, ServiceContext serviceContext) {

		return getPersistence().update(messageFolder, serviceContext);
	}

	/**
	 * Returns all the message folders where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching message folders
	 */
	public static List<MessageFolder> findByuserId(long userId) {
		return getPersistence().findByuserId(userId);
	}

	/**
	 * Returns a range of all the message folders where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageFolderModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of message folders
	 * @param end the upper bound of the range of message folders (not inclusive)
	 * @return the range of matching message folders
	 */
	public static List<MessageFolder> findByuserId(
		long userId, int start, int end) {

		return getPersistence().findByuserId(userId, start, end);
	}

	/**
	 * Returns an ordered range of all the message folders where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageFolderModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of message folders
	 * @param end the upper bound of the range of message folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching message folders
	 */
	public static List<MessageFolder> findByuserId(
		long userId, int start, int end,
		OrderByComparator<MessageFolder> orderByComparator) {

		return getPersistence().findByuserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the message folders where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageFolderModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of message folders
	 * @param end the upper bound of the range of message folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching message folders
	 */
	public static List<MessageFolder> findByuserId(
		long userId, int start, int end,
		OrderByComparator<MessageFolder> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByuserId(
			userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first message folder in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message folder
	 * @throws NoSuchMessageFolderException if a matching message folder could not be found
	 */
	public static MessageFolder findByuserId_First(
			long userId, OrderByComparator<MessageFolder> orderByComparator)
		throws com.weprode.nero.messaging.exception.
			NoSuchMessageFolderException {

		return getPersistence().findByuserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first message folder in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message folder, or <code>null</code> if a matching message folder could not be found
	 */
	public static MessageFolder fetchByuserId_First(
		long userId, OrderByComparator<MessageFolder> orderByComparator) {

		return getPersistence().fetchByuserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last message folder in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message folder
	 * @throws NoSuchMessageFolderException if a matching message folder could not be found
	 */
	public static MessageFolder findByuserId_Last(
			long userId, OrderByComparator<MessageFolder> orderByComparator)
		throws com.weprode.nero.messaging.exception.
			NoSuchMessageFolderException {

		return getPersistence().findByuserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last message folder in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message folder, or <code>null</code> if a matching message folder could not be found
	 */
	public static MessageFolder fetchByuserId_Last(
		long userId, OrderByComparator<MessageFolder> orderByComparator) {

		return getPersistence().fetchByuserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the message folders before and after the current message folder in the ordered set where userId = &#63;.
	 *
	 * @param folderId the primary key of the current message folder
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next message folder
	 * @throws NoSuchMessageFolderException if a message folder with the primary key could not be found
	 */
	public static MessageFolder[] findByuserId_PrevAndNext(
			long folderId, long userId,
			OrderByComparator<MessageFolder> orderByComparator)
		throws com.weprode.nero.messaging.exception.
			NoSuchMessageFolderException {

		return getPersistence().findByuserId_PrevAndNext(
			folderId, userId, orderByComparator);
	}

	/**
	 * Removes all the message folders where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByuserId(long userId) {
		getPersistence().removeByuserId(userId);
	}

	/**
	 * Returns the number of message folders where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching message folders
	 */
	public static int countByuserId(long userId) {
		return getPersistence().countByuserId(userId);
	}

	/**
	 * Returns all the message folders where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @return the matching message folders
	 */
	public static List<MessageFolder> findByuserId_type(long userId, int type) {
		return getPersistence().findByuserId_type(userId, type);
	}

	/**
	 * Returns a range of all the message folders where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageFolderModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param start the lower bound of the range of message folders
	 * @param end the upper bound of the range of message folders (not inclusive)
	 * @return the range of matching message folders
	 */
	public static List<MessageFolder> findByuserId_type(
		long userId, int type, int start, int end) {

		return getPersistence().findByuserId_type(userId, type, start, end);
	}

	/**
	 * Returns an ordered range of all the message folders where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageFolderModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param start the lower bound of the range of message folders
	 * @param end the upper bound of the range of message folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching message folders
	 */
	public static List<MessageFolder> findByuserId_type(
		long userId, int type, int start, int end,
		OrderByComparator<MessageFolder> orderByComparator) {

		return getPersistence().findByuserId_type(
			userId, type, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the message folders where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageFolderModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param start the lower bound of the range of message folders
	 * @param end the upper bound of the range of message folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching message folders
	 */
	public static List<MessageFolder> findByuserId_type(
		long userId, int type, int start, int end,
		OrderByComparator<MessageFolder> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByuserId_type(
			userId, type, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first message folder in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message folder
	 * @throws NoSuchMessageFolderException if a matching message folder could not be found
	 */
	public static MessageFolder findByuserId_type_First(
			long userId, int type,
			OrderByComparator<MessageFolder> orderByComparator)
		throws com.weprode.nero.messaging.exception.
			NoSuchMessageFolderException {

		return getPersistence().findByuserId_type_First(
			userId, type, orderByComparator);
	}

	/**
	 * Returns the first message folder in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message folder, or <code>null</code> if a matching message folder could not be found
	 */
	public static MessageFolder fetchByuserId_type_First(
		long userId, int type,
		OrderByComparator<MessageFolder> orderByComparator) {

		return getPersistence().fetchByuserId_type_First(
			userId, type, orderByComparator);
	}

	/**
	 * Returns the last message folder in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message folder
	 * @throws NoSuchMessageFolderException if a matching message folder could not be found
	 */
	public static MessageFolder findByuserId_type_Last(
			long userId, int type,
			OrderByComparator<MessageFolder> orderByComparator)
		throws com.weprode.nero.messaging.exception.
			NoSuchMessageFolderException {

		return getPersistence().findByuserId_type_Last(
			userId, type, orderByComparator);
	}

	/**
	 * Returns the last message folder in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message folder, or <code>null</code> if a matching message folder could not be found
	 */
	public static MessageFolder fetchByuserId_type_Last(
		long userId, int type,
		OrderByComparator<MessageFolder> orderByComparator) {

		return getPersistence().fetchByuserId_type_Last(
			userId, type, orderByComparator);
	}

	/**
	 * Returns the message folders before and after the current message folder in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param folderId the primary key of the current message folder
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next message folder
	 * @throws NoSuchMessageFolderException if a message folder with the primary key could not be found
	 */
	public static MessageFolder[] findByuserId_type_PrevAndNext(
			long folderId, long userId, int type,
			OrderByComparator<MessageFolder> orderByComparator)
		throws com.weprode.nero.messaging.exception.
			NoSuchMessageFolderException {

		return getPersistence().findByuserId_type_PrevAndNext(
			folderId, userId, type, orderByComparator);
	}

	/**
	 * Removes all the message folders where userId = &#63; and type = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param type the type
	 */
	public static void removeByuserId_type(long userId, int type) {
		getPersistence().removeByuserId_type(userId, type);
	}

	/**
	 * Returns the number of message folders where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @return the number of matching message folders
	 */
	public static int countByuserId_type(long userId, int type) {
		return getPersistence().countByuserId_type(userId, type);
	}

	/**
	 * Returns all the message folders where userId = &#63; and parentFolderId = &#63;.
	 *
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 * @return the matching message folders
	 */
	public static List<MessageFolder> findByuserId_parentFolderId(
		long userId, long parentFolderId) {

		return getPersistence().findByuserId_parentFolderId(
			userId, parentFolderId);
	}

	/**
	 * Returns a range of all the message folders where userId = &#63; and parentFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageFolderModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 * @param start the lower bound of the range of message folders
	 * @param end the upper bound of the range of message folders (not inclusive)
	 * @return the range of matching message folders
	 */
	public static List<MessageFolder> findByuserId_parentFolderId(
		long userId, long parentFolderId, int start, int end) {

		return getPersistence().findByuserId_parentFolderId(
			userId, parentFolderId, start, end);
	}

	/**
	 * Returns an ordered range of all the message folders where userId = &#63; and parentFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageFolderModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 * @param start the lower bound of the range of message folders
	 * @param end the upper bound of the range of message folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching message folders
	 */
	public static List<MessageFolder> findByuserId_parentFolderId(
		long userId, long parentFolderId, int start, int end,
		OrderByComparator<MessageFolder> orderByComparator) {

		return getPersistence().findByuserId_parentFolderId(
			userId, parentFolderId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the message folders where userId = &#63; and parentFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageFolderModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 * @param start the lower bound of the range of message folders
	 * @param end the upper bound of the range of message folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching message folders
	 */
	public static List<MessageFolder> findByuserId_parentFolderId(
		long userId, long parentFolderId, int start, int end,
		OrderByComparator<MessageFolder> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByuserId_parentFolderId(
			userId, parentFolderId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first message folder in the ordered set where userId = &#63; and parentFolderId = &#63;.
	 *
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message folder
	 * @throws NoSuchMessageFolderException if a matching message folder could not be found
	 */
	public static MessageFolder findByuserId_parentFolderId_First(
			long userId, long parentFolderId,
			OrderByComparator<MessageFolder> orderByComparator)
		throws com.weprode.nero.messaging.exception.
			NoSuchMessageFolderException {

		return getPersistence().findByuserId_parentFolderId_First(
			userId, parentFolderId, orderByComparator);
	}

	/**
	 * Returns the first message folder in the ordered set where userId = &#63; and parentFolderId = &#63;.
	 *
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message folder, or <code>null</code> if a matching message folder could not be found
	 */
	public static MessageFolder fetchByuserId_parentFolderId_First(
		long userId, long parentFolderId,
		OrderByComparator<MessageFolder> orderByComparator) {

		return getPersistence().fetchByuserId_parentFolderId_First(
			userId, parentFolderId, orderByComparator);
	}

	/**
	 * Returns the last message folder in the ordered set where userId = &#63; and parentFolderId = &#63;.
	 *
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message folder
	 * @throws NoSuchMessageFolderException if a matching message folder could not be found
	 */
	public static MessageFolder findByuserId_parentFolderId_Last(
			long userId, long parentFolderId,
			OrderByComparator<MessageFolder> orderByComparator)
		throws com.weprode.nero.messaging.exception.
			NoSuchMessageFolderException {

		return getPersistence().findByuserId_parentFolderId_Last(
			userId, parentFolderId, orderByComparator);
	}

	/**
	 * Returns the last message folder in the ordered set where userId = &#63; and parentFolderId = &#63;.
	 *
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message folder, or <code>null</code> if a matching message folder could not be found
	 */
	public static MessageFolder fetchByuserId_parentFolderId_Last(
		long userId, long parentFolderId,
		OrderByComparator<MessageFolder> orderByComparator) {

		return getPersistence().fetchByuserId_parentFolderId_Last(
			userId, parentFolderId, orderByComparator);
	}

	/**
	 * Returns the message folders before and after the current message folder in the ordered set where userId = &#63; and parentFolderId = &#63;.
	 *
	 * @param folderId the primary key of the current message folder
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next message folder
	 * @throws NoSuchMessageFolderException if a message folder with the primary key could not be found
	 */
	public static MessageFolder[] findByuserId_parentFolderId_PrevAndNext(
			long folderId, long userId, long parentFolderId,
			OrderByComparator<MessageFolder> orderByComparator)
		throws com.weprode.nero.messaging.exception.
			NoSuchMessageFolderException {

		return getPersistence().findByuserId_parentFolderId_PrevAndNext(
			folderId, userId, parentFolderId, orderByComparator);
	}

	/**
	 * Removes all the message folders where userId = &#63; and parentFolderId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 */
	public static void removeByuserId_parentFolderId(
		long userId, long parentFolderId) {

		getPersistence().removeByuserId_parentFolderId(userId, parentFolderId);
	}

	/**
	 * Returns the number of message folders where userId = &#63; and parentFolderId = &#63;.
	 *
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 * @return the number of matching message folders
	 */
	public static int countByuserId_parentFolderId(
		long userId, long parentFolderId) {

		return getPersistence().countByuserId_parentFolderId(
			userId, parentFolderId);
	}

	/**
	 * Caches the message folder in the entity cache if it is enabled.
	 *
	 * @param messageFolder the message folder
	 */
	public static void cacheResult(MessageFolder messageFolder) {
		getPersistence().cacheResult(messageFolder);
	}

	/**
	 * Caches the message folders in the entity cache if it is enabled.
	 *
	 * @param messageFolders the message folders
	 */
	public static void cacheResult(List<MessageFolder> messageFolders) {
		getPersistence().cacheResult(messageFolders);
	}

	/**
	 * Creates a new message folder with the primary key. Does not add the message folder to the database.
	 *
	 * @param folderId the primary key for the new message folder
	 * @return the new message folder
	 */
	public static MessageFolder create(long folderId) {
		return getPersistence().create(folderId);
	}

	/**
	 * Removes the message folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param folderId the primary key of the message folder
	 * @return the message folder that was removed
	 * @throws NoSuchMessageFolderException if a message folder with the primary key could not be found
	 */
	public static MessageFolder remove(long folderId)
		throws com.weprode.nero.messaging.exception.
			NoSuchMessageFolderException {

		return getPersistence().remove(folderId);
	}

	public static MessageFolder updateImpl(MessageFolder messageFolder) {
		return getPersistence().updateImpl(messageFolder);
	}

	/**
	 * Returns the message folder with the primary key or throws a <code>NoSuchMessageFolderException</code> if it could not be found.
	 *
	 * @param folderId the primary key of the message folder
	 * @return the message folder
	 * @throws NoSuchMessageFolderException if a message folder with the primary key could not be found
	 */
	public static MessageFolder findByPrimaryKey(long folderId)
		throws com.weprode.nero.messaging.exception.
			NoSuchMessageFolderException {

		return getPersistence().findByPrimaryKey(folderId);
	}

	/**
	 * Returns the message folder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param folderId the primary key of the message folder
	 * @return the message folder, or <code>null</code> if a message folder with the primary key could not be found
	 */
	public static MessageFolder fetchByPrimaryKey(long folderId) {
		return getPersistence().fetchByPrimaryKey(folderId);
	}

	/**
	 * Returns all the message folders.
	 *
	 * @return the message folders
	 */
	public static List<MessageFolder> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the message folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageFolderModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of message folders
	 * @param end the upper bound of the range of message folders (not inclusive)
	 * @return the range of message folders
	 */
	public static List<MessageFolder> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the message folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageFolderModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of message folders
	 * @param end the upper bound of the range of message folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of message folders
	 */
	public static List<MessageFolder> findAll(
		int start, int end,
		OrderByComparator<MessageFolder> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the message folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessageFolderModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of message folders
	 * @param end the upper bound of the range of message folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of message folders
	 */
	public static List<MessageFolder> findAll(
		int start, int end, OrderByComparator<MessageFolder> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the message folders from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of message folders.
	 *
	 * @return the number of message folders
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static MessageFolderPersistence getPersistence() {
		return _persistence;
	}

	private static volatile MessageFolderPersistence _persistence;

}