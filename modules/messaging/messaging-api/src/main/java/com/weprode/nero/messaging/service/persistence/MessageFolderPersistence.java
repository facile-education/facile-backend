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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.messaging.exception.NoSuchMessageFolderException;
import com.weprode.nero.messaging.model.MessageFolder;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the message folder service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageFolderUtil
 * @generated
 */
@ProviderType
public interface MessageFolderPersistence
	extends BasePersistence<MessageFolder> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MessageFolderUtil} to access the message folder persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the message folders where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching message folders
	 */
	public java.util.List<MessageFolder> findByuserId(long userId);

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
	public java.util.List<MessageFolder> findByuserId(
		long userId, int start, int end);

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
	public java.util.List<MessageFolder> findByuserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MessageFolder>
			orderByComparator);

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
	public java.util.List<MessageFolder> findByuserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MessageFolder>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first message folder in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message folder
	 * @throws NoSuchMessageFolderException if a matching message folder could not be found
	 */
	public MessageFolder findByuserId_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<MessageFolder>
				orderByComparator)
		throws NoSuchMessageFolderException;

	/**
	 * Returns the first message folder in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message folder, or <code>null</code> if a matching message folder could not be found
	 */
	public MessageFolder fetchByuserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<MessageFolder>
			orderByComparator);

	/**
	 * Returns the last message folder in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message folder
	 * @throws NoSuchMessageFolderException if a matching message folder could not be found
	 */
	public MessageFolder findByuserId_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<MessageFolder>
				orderByComparator)
		throws NoSuchMessageFolderException;

	/**
	 * Returns the last message folder in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message folder, or <code>null</code> if a matching message folder could not be found
	 */
	public MessageFolder fetchByuserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<MessageFolder>
			orderByComparator);

	/**
	 * Returns the message folders before and after the current message folder in the ordered set where userId = &#63;.
	 *
	 * @param folderId the primary key of the current message folder
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next message folder
	 * @throws NoSuchMessageFolderException if a message folder with the primary key could not be found
	 */
	public MessageFolder[] findByuserId_PrevAndNext(
			long folderId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<MessageFolder>
				orderByComparator)
		throws NoSuchMessageFolderException;

	/**
	 * Removes all the message folders where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByuserId(long userId);

	/**
	 * Returns the number of message folders where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching message folders
	 */
	public int countByuserId(long userId);

	/**
	 * Returns all the message folders where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @return the matching message folders
	 */
	public java.util.List<MessageFolder> findByuserId_type(
		long userId, int type);

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
	public java.util.List<MessageFolder> findByuserId_type(
		long userId, int type, int start, int end);

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
	public java.util.List<MessageFolder> findByuserId_type(
		long userId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MessageFolder>
			orderByComparator);

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
	public java.util.List<MessageFolder> findByuserId_type(
		long userId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MessageFolder>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first message folder in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message folder
	 * @throws NoSuchMessageFolderException if a matching message folder could not be found
	 */
	public MessageFolder findByuserId_type_First(
			long userId, int type,
			com.liferay.portal.kernel.util.OrderByComparator<MessageFolder>
				orderByComparator)
		throws NoSuchMessageFolderException;

	/**
	 * Returns the first message folder in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message folder, or <code>null</code> if a matching message folder could not be found
	 */
	public MessageFolder fetchByuserId_type_First(
		long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<MessageFolder>
			orderByComparator);

	/**
	 * Returns the last message folder in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message folder
	 * @throws NoSuchMessageFolderException if a matching message folder could not be found
	 */
	public MessageFolder findByuserId_type_Last(
			long userId, int type,
			com.liferay.portal.kernel.util.OrderByComparator<MessageFolder>
				orderByComparator)
		throws NoSuchMessageFolderException;

	/**
	 * Returns the last message folder in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message folder, or <code>null</code> if a matching message folder could not be found
	 */
	public MessageFolder fetchByuserId_type_Last(
		long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<MessageFolder>
			orderByComparator);

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
	public MessageFolder[] findByuserId_type_PrevAndNext(
			long folderId, long userId, int type,
			com.liferay.portal.kernel.util.OrderByComparator<MessageFolder>
				orderByComparator)
		throws NoSuchMessageFolderException;

	/**
	 * Removes all the message folders where userId = &#63; and type = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param type the type
	 */
	public void removeByuserId_type(long userId, int type);

	/**
	 * Returns the number of message folders where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @return the number of matching message folders
	 */
	public int countByuserId_type(long userId, int type);

	/**
	 * Returns all the message folders where userId = &#63; and parentFolderId = &#63;.
	 *
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 * @return the matching message folders
	 */
	public java.util.List<MessageFolder> findByuserId_parentFolderId(
		long userId, long parentFolderId);

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
	public java.util.List<MessageFolder> findByuserId_parentFolderId(
		long userId, long parentFolderId, int start, int end);

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
	public java.util.List<MessageFolder> findByuserId_parentFolderId(
		long userId, long parentFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MessageFolder>
			orderByComparator);

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
	public java.util.List<MessageFolder> findByuserId_parentFolderId(
		long userId, long parentFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MessageFolder>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first message folder in the ordered set where userId = &#63; and parentFolderId = &#63;.
	 *
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message folder
	 * @throws NoSuchMessageFolderException if a matching message folder could not be found
	 */
	public MessageFolder findByuserId_parentFolderId_First(
			long userId, long parentFolderId,
			com.liferay.portal.kernel.util.OrderByComparator<MessageFolder>
				orderByComparator)
		throws NoSuchMessageFolderException;

	/**
	 * Returns the first message folder in the ordered set where userId = &#63; and parentFolderId = &#63;.
	 *
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message folder, or <code>null</code> if a matching message folder could not be found
	 */
	public MessageFolder fetchByuserId_parentFolderId_First(
		long userId, long parentFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<MessageFolder>
			orderByComparator);

	/**
	 * Returns the last message folder in the ordered set where userId = &#63; and parentFolderId = &#63;.
	 *
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message folder
	 * @throws NoSuchMessageFolderException if a matching message folder could not be found
	 */
	public MessageFolder findByuserId_parentFolderId_Last(
			long userId, long parentFolderId,
			com.liferay.portal.kernel.util.OrderByComparator<MessageFolder>
				orderByComparator)
		throws NoSuchMessageFolderException;

	/**
	 * Returns the last message folder in the ordered set where userId = &#63; and parentFolderId = &#63;.
	 *
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message folder, or <code>null</code> if a matching message folder could not be found
	 */
	public MessageFolder fetchByuserId_parentFolderId_Last(
		long userId, long parentFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<MessageFolder>
			orderByComparator);

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
	public MessageFolder[] findByuserId_parentFolderId_PrevAndNext(
			long folderId, long userId, long parentFolderId,
			com.liferay.portal.kernel.util.OrderByComparator<MessageFolder>
				orderByComparator)
		throws NoSuchMessageFolderException;

	/**
	 * Removes all the message folders where userId = &#63; and parentFolderId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 */
	public void removeByuserId_parentFolderId(long userId, long parentFolderId);

	/**
	 * Returns the number of message folders where userId = &#63; and parentFolderId = &#63;.
	 *
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 * @return the number of matching message folders
	 */
	public int countByuserId_parentFolderId(long userId, long parentFolderId);

	/**
	 * Caches the message folder in the entity cache if it is enabled.
	 *
	 * @param messageFolder the message folder
	 */
	public void cacheResult(MessageFolder messageFolder);

	/**
	 * Caches the message folders in the entity cache if it is enabled.
	 *
	 * @param messageFolders the message folders
	 */
	public void cacheResult(java.util.List<MessageFolder> messageFolders);

	/**
	 * Creates a new message folder with the primary key. Does not add the message folder to the database.
	 *
	 * @param folderId the primary key for the new message folder
	 * @return the new message folder
	 */
	public MessageFolder create(long folderId);

	/**
	 * Removes the message folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param folderId the primary key of the message folder
	 * @return the message folder that was removed
	 * @throws NoSuchMessageFolderException if a message folder with the primary key could not be found
	 */
	public MessageFolder remove(long folderId)
		throws NoSuchMessageFolderException;

	public MessageFolder updateImpl(MessageFolder messageFolder);

	/**
	 * Returns the message folder with the primary key or throws a <code>NoSuchMessageFolderException</code> if it could not be found.
	 *
	 * @param folderId the primary key of the message folder
	 * @return the message folder
	 * @throws NoSuchMessageFolderException if a message folder with the primary key could not be found
	 */
	public MessageFolder findByPrimaryKey(long folderId)
		throws NoSuchMessageFolderException;

	/**
	 * Returns the message folder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param folderId the primary key of the message folder
	 * @return the message folder, or <code>null</code> if a message folder with the primary key could not be found
	 */
	public MessageFolder fetchByPrimaryKey(long folderId);

	/**
	 * Returns all the message folders.
	 *
	 * @return the message folders
	 */
	public java.util.List<MessageFolder> findAll();

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
	public java.util.List<MessageFolder> findAll(int start, int end);

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
	public java.util.List<MessageFolder> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MessageFolder>
			orderByComparator);

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
	public java.util.List<MessageFolder> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MessageFolder>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the message folders from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of message folders.
	 *
	 * @return the number of message folders
	 */
	public int countAll();

}