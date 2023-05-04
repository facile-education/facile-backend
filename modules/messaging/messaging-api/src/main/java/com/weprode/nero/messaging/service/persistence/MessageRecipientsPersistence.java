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

import com.weprode.nero.messaging.exception.NoSuchMessageRecipientsException;
import com.weprode.nero.messaging.model.MessageRecipients;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the message recipients service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageRecipientsUtil
 * @generated
 */
@ProviderType
public interface MessageRecipientsPersistence
	extends BasePersistence<MessageRecipients> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MessageRecipientsUtil} to access the message recipients persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the message recipients where messageId = &#63; or throws a <code>NoSuchMessageRecipientsException</code> if it could not be found.
	 *
	 * @param messageId the message ID
	 * @return the matching message recipients
	 * @throws NoSuchMessageRecipientsException if a matching message recipients could not be found
	 */
	public MessageRecipients findBymessageId(long messageId)
		throws NoSuchMessageRecipientsException;

	/**
	 * Returns the message recipients where messageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param messageId the message ID
	 * @return the matching message recipients, or <code>null</code> if a matching message recipients could not be found
	 */
	public MessageRecipients fetchBymessageId(long messageId);

	/**
	 * Returns the message recipients where messageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param messageId the message ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching message recipients, or <code>null</code> if a matching message recipients could not be found
	 */
	public MessageRecipients fetchBymessageId(
		long messageId, boolean useFinderCache);

	/**
	 * Removes the message recipients where messageId = &#63; from the database.
	 *
	 * @param messageId the message ID
	 * @return the message recipients that was removed
	 */
	public MessageRecipients removeBymessageId(long messageId)
		throws NoSuchMessageRecipientsException;

	/**
	 * Returns the number of message recipientses where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @return the number of matching message recipientses
	 */
	public int countBymessageId(long messageId);

	/**
	 * Caches the message recipients in the entity cache if it is enabled.
	 *
	 * @param messageRecipients the message recipients
	 */
	public void cacheResult(MessageRecipients messageRecipients);

	/**
	 * Caches the message recipientses in the entity cache if it is enabled.
	 *
	 * @param messageRecipientses the message recipientses
	 */
	public void cacheResult(
		java.util.List<MessageRecipients> messageRecipientses);

	/**
	 * Creates a new message recipients with the primary key. Does not add the message recipients to the database.
	 *
	 * @param messageId the primary key for the new message recipients
	 * @return the new message recipients
	 */
	public MessageRecipients create(long messageId);

	/**
	 * Removes the message recipients with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param messageId the primary key of the message recipients
	 * @return the message recipients that was removed
	 * @throws NoSuchMessageRecipientsException if a message recipients with the primary key could not be found
	 */
	public MessageRecipients remove(long messageId)
		throws NoSuchMessageRecipientsException;

	public MessageRecipients updateImpl(MessageRecipients messageRecipients);

	/**
	 * Returns the message recipients with the primary key or throws a <code>NoSuchMessageRecipientsException</code> if it could not be found.
	 *
	 * @param messageId the primary key of the message recipients
	 * @return the message recipients
	 * @throws NoSuchMessageRecipientsException if a message recipients with the primary key could not be found
	 */
	public MessageRecipients findByPrimaryKey(long messageId)
		throws NoSuchMessageRecipientsException;

	/**
	 * Returns the message recipients with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param messageId the primary key of the message recipients
	 * @return the message recipients, or <code>null</code> if a message recipients with the primary key could not be found
	 */
	public MessageRecipients fetchByPrimaryKey(long messageId);

	/**
	 * Returns all the message recipientses.
	 *
	 * @return the message recipientses
	 */
	public java.util.List<MessageRecipients> findAll();

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
	public java.util.List<MessageRecipients> findAll(int start, int end);

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
	public java.util.List<MessageRecipients> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MessageRecipients>
			orderByComparator);

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
	public java.util.List<MessageRecipients> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MessageRecipients>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the message recipientses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of message recipientses.
	 *
	 * @return the number of message recipientses
	 */
	public int countAll();

}