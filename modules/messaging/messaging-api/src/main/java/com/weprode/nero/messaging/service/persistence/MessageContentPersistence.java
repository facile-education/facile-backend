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

import com.weprode.nero.messaging.exception.NoSuchMessageContentException;
import com.weprode.nero.messaging.model.MessageContent;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the message content service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageContentUtil
 * @generated
 */
@ProviderType
public interface MessageContentPersistence
	extends BasePersistence<MessageContent> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MessageContentUtil} to access the message content persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the message content where messageId = &#63; or throws a <code>NoSuchMessageContentException</code> if it could not be found.
	 *
	 * @param messageId the message ID
	 * @return the matching message content
	 * @throws NoSuchMessageContentException if a matching message content could not be found
	 */
	public MessageContent findBymessageId(long messageId)
		throws NoSuchMessageContentException;

	/**
	 * Returns the message content where messageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param messageId the message ID
	 * @return the matching message content, or <code>null</code> if a matching message content could not be found
	 */
	public MessageContent fetchBymessageId(long messageId);

	/**
	 * Returns the message content where messageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param messageId the message ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching message content, or <code>null</code> if a matching message content could not be found
	 */
	public MessageContent fetchBymessageId(
		long messageId, boolean useFinderCache);

	/**
	 * Removes the message content where messageId = &#63; from the database.
	 *
	 * @param messageId the message ID
	 * @return the message content that was removed
	 */
	public MessageContent removeBymessageId(long messageId)
		throws NoSuchMessageContentException;

	/**
	 * Returns the number of message contents where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @return the number of matching message contents
	 */
	public int countBymessageId(long messageId);

	/**
	 * Caches the message content in the entity cache if it is enabled.
	 *
	 * @param messageContent the message content
	 */
	public void cacheResult(MessageContent messageContent);

	/**
	 * Caches the message contents in the entity cache if it is enabled.
	 *
	 * @param messageContents the message contents
	 */
	public void cacheResult(java.util.List<MessageContent> messageContents);

	/**
	 * Creates a new message content with the primary key. Does not add the message content to the database.
	 *
	 * @param messageId the primary key for the new message content
	 * @return the new message content
	 */
	public MessageContent create(long messageId);

	/**
	 * Removes the message content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param messageId the primary key of the message content
	 * @return the message content that was removed
	 * @throws NoSuchMessageContentException if a message content with the primary key could not be found
	 */
	public MessageContent remove(long messageId)
		throws NoSuchMessageContentException;

	public MessageContent updateImpl(MessageContent messageContent);

	/**
	 * Returns the message content with the primary key or throws a <code>NoSuchMessageContentException</code> if it could not be found.
	 *
	 * @param messageId the primary key of the message content
	 * @return the message content
	 * @throws NoSuchMessageContentException if a message content with the primary key could not be found
	 */
	public MessageContent findByPrimaryKey(long messageId)
		throws NoSuchMessageContentException;

	/**
	 * Returns the message content with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param messageId the primary key of the message content
	 * @return the message content, or <code>null</code> if a message content with the primary key could not be found
	 */
	public MessageContent fetchByPrimaryKey(long messageId);

	/**
	 * Returns all the message contents.
	 *
	 * @return the message contents
	 */
	public java.util.List<MessageContent> findAll();

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
	public java.util.List<MessageContent> findAll(int start, int end);

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
	public java.util.List<MessageContent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MessageContent>
			orderByComparator);

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
	public java.util.List<MessageContent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MessageContent>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the message contents from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of message contents.
	 *
	 * @return the number of message contents
	 */
	public int countAll();

}