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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.messaging.exception.NoSuchMessageAttachFileException;
import com.weprode.facile.messaging.model.MessageAttachFile;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the message attach file service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageAttachFileUtil
 * @generated
 */
@ProviderType
public interface MessageAttachFilePersistence
	extends BasePersistence<MessageAttachFile> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MessageAttachFileUtil} to access the message attach file persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the message attach files where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @return the matching message attach files
	 */
	public java.util.List<MessageAttachFile> findBymessageId(long messageId);

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
	public java.util.List<MessageAttachFile> findBymessageId(
		long messageId, int start, int end);

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
	public java.util.List<MessageAttachFile> findBymessageId(
		long messageId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MessageAttachFile>
			orderByComparator);

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
	public java.util.List<MessageAttachFile> findBymessageId(
		long messageId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MessageAttachFile>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first message attach file in the ordered set where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message attach file
	 * @throws NoSuchMessageAttachFileException if a matching message attach file could not be found
	 */
	public MessageAttachFile findBymessageId_First(
			long messageId,
			com.liferay.portal.kernel.util.OrderByComparator<MessageAttachFile>
				orderByComparator)
		throws NoSuchMessageAttachFileException;

	/**
	 * Returns the first message attach file in the ordered set where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message attach file, or <code>null</code> if a matching message attach file could not be found
	 */
	public MessageAttachFile fetchBymessageId_First(
		long messageId,
		com.liferay.portal.kernel.util.OrderByComparator<MessageAttachFile>
			orderByComparator);

	/**
	 * Returns the last message attach file in the ordered set where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message attach file
	 * @throws NoSuchMessageAttachFileException if a matching message attach file could not be found
	 */
	public MessageAttachFile findBymessageId_Last(
			long messageId,
			com.liferay.portal.kernel.util.OrderByComparator<MessageAttachFile>
				orderByComparator)
		throws NoSuchMessageAttachFileException;

	/**
	 * Returns the last message attach file in the ordered set where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message attach file, or <code>null</code> if a matching message attach file could not be found
	 */
	public MessageAttachFile fetchBymessageId_Last(
		long messageId,
		com.liferay.portal.kernel.util.OrderByComparator<MessageAttachFile>
			orderByComparator);

	/**
	 * Returns the message attach files before and after the current message attach file in the ordered set where messageId = &#63;.
	 *
	 * @param messageAttachFilePK the primary key of the current message attach file
	 * @param messageId the message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next message attach file
	 * @throws NoSuchMessageAttachFileException if a message attach file with the primary key could not be found
	 */
	public MessageAttachFile[] findBymessageId_PrevAndNext(
			MessageAttachFilePK messageAttachFilePK, long messageId,
			com.liferay.portal.kernel.util.OrderByComparator<MessageAttachFile>
				orderByComparator)
		throws NoSuchMessageAttachFileException;

	/**
	 * Removes all the message attach files where messageId = &#63; from the database.
	 *
	 * @param messageId the message ID
	 */
	public void removeBymessageId(long messageId);

	/**
	 * Returns the number of message attach files where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @return the number of matching message attach files
	 */
	public int countBymessageId(long messageId);

	/**
	 * Caches the message attach file in the entity cache if it is enabled.
	 *
	 * @param messageAttachFile the message attach file
	 */
	public void cacheResult(MessageAttachFile messageAttachFile);

	/**
	 * Caches the message attach files in the entity cache if it is enabled.
	 *
	 * @param messageAttachFiles the message attach files
	 */
	public void cacheResult(
		java.util.List<MessageAttachFile> messageAttachFiles);

	/**
	 * Creates a new message attach file with the primary key. Does not add the message attach file to the database.
	 *
	 * @param messageAttachFilePK the primary key for the new message attach file
	 * @return the new message attach file
	 */
	public MessageAttachFile create(MessageAttachFilePK messageAttachFilePK);

	/**
	 * Removes the message attach file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param messageAttachFilePK the primary key of the message attach file
	 * @return the message attach file that was removed
	 * @throws NoSuchMessageAttachFileException if a message attach file with the primary key could not be found
	 */
	public MessageAttachFile remove(MessageAttachFilePK messageAttachFilePK)
		throws NoSuchMessageAttachFileException;

	public MessageAttachFile updateImpl(MessageAttachFile messageAttachFile);

	/**
	 * Returns the message attach file with the primary key or throws a <code>NoSuchMessageAttachFileException</code> if it could not be found.
	 *
	 * @param messageAttachFilePK the primary key of the message attach file
	 * @return the message attach file
	 * @throws NoSuchMessageAttachFileException if a message attach file with the primary key could not be found
	 */
	public MessageAttachFile findByPrimaryKey(
			MessageAttachFilePK messageAttachFilePK)
		throws NoSuchMessageAttachFileException;

	/**
	 * Returns the message attach file with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param messageAttachFilePK the primary key of the message attach file
	 * @return the message attach file, or <code>null</code> if a message attach file with the primary key could not be found
	 */
	public MessageAttachFile fetchByPrimaryKey(
		MessageAttachFilePK messageAttachFilePK);

	/**
	 * Returns all the message attach files.
	 *
	 * @return the message attach files
	 */
	public java.util.List<MessageAttachFile> findAll();

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
	public java.util.List<MessageAttachFile> findAll(int start, int end);

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
	public java.util.List<MessageAttachFile> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MessageAttachFile>
			orderByComparator);

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
	public java.util.List<MessageAttachFile> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MessageAttachFile>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the message attach files from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of message attach files.
	 *
	 * @return the number of message attach files
	 */
	public int countAll();

	public Set<String> getCompoundPKColumnNames();

}