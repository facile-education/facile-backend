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

package com.weprode.nero.messaging.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;

import com.weprode.nero.messaging.exception.NoSuchMessageException;
import com.weprode.nero.messaging.model.Message;
import com.weprode.nero.messaging.model.MessageTable;
import com.weprode.nero.messaging.model.impl.MessageImpl;
import com.weprode.nero.messaging.model.impl.MessageModelImpl;
import com.weprode.nero.messaging.service.persistence.MessagePersistence;
import com.weprode.nero.messaging.service.persistence.MessageUtil;
import com.weprode.nero.messaging.service.persistence.impl.constants.MessagingPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {MessagePersistence.class, BasePersistence.class})
public class MessagePersistenceImpl
	extends BasePersistenceImpl<Message> implements MessagePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>MessageUtil</code> to access the message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		MessageImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindBysendMessageId;
	private FinderPath _finderPathWithoutPaginationFindBysendMessageId;
	private FinderPath _finderPathCountBysendMessageId;

	/**
	 * Returns all the messages where sendMessageId = &#63;.
	 *
	 * @param sendMessageId the send message ID
	 * @return the matching messages
	 */
	@Override
	public List<Message> findBysendMessageId(long sendMessageId) {
		return findBysendMessageId(
			sendMessageId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Message> findBysendMessageId(
		long sendMessageId, int start, int end) {

		return findBysendMessageId(sendMessageId, start, end, null);
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
	@Override
	public List<Message> findBysendMessageId(
		long sendMessageId, int start, int end,
		OrderByComparator<Message> orderByComparator) {

		return findBysendMessageId(
			sendMessageId, start, end, orderByComparator, true);
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
	@Override
	public List<Message> findBysendMessageId(
		long sendMessageId, int start, int end,
		OrderByComparator<Message> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBysendMessageId;
				finderArgs = new Object[] {sendMessageId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBysendMessageId;
			finderArgs = new Object[] {
				sendMessageId, start, end, orderByComparator
			};
		}

		List<Message> list = null;

		if (useFinderCache) {
			list = (List<Message>)finderCache.getResult(finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (Message message : list) {
					if (sendMessageId != message.getSendMessageId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_MESSAGE_WHERE);

			sb.append(_FINDER_COLUMN_SENDMESSAGEID_SENDMESSAGEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(MessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sendMessageId);

				list = (List<Message>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first message in the ordered set where sendMessageId = &#63;.
	 *
	 * @param sendMessageId the send message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message
	 * @throws NoSuchMessageException if a matching message could not be found
	 */
	@Override
	public Message findBysendMessageId_First(
			long sendMessageId, OrderByComparator<Message> orderByComparator)
		throws NoSuchMessageException {

		Message message = fetchBysendMessageId_First(
			sendMessageId, orderByComparator);

		if (message != null) {
			return message;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sendMessageId=");
		sb.append(sendMessageId);

		sb.append("}");

		throw new NoSuchMessageException(sb.toString());
	}

	/**
	 * Returns the first message in the ordered set where sendMessageId = &#63;.
	 *
	 * @param sendMessageId the send message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message, or <code>null</code> if a matching message could not be found
	 */
	@Override
	public Message fetchBysendMessageId_First(
		long sendMessageId, OrderByComparator<Message> orderByComparator) {

		List<Message> list = findBysendMessageId(
			sendMessageId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last message in the ordered set where sendMessageId = &#63;.
	 *
	 * @param sendMessageId the send message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message
	 * @throws NoSuchMessageException if a matching message could not be found
	 */
	@Override
	public Message findBysendMessageId_Last(
			long sendMessageId, OrderByComparator<Message> orderByComparator)
		throws NoSuchMessageException {

		Message message = fetchBysendMessageId_Last(
			sendMessageId, orderByComparator);

		if (message != null) {
			return message;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sendMessageId=");
		sb.append(sendMessageId);

		sb.append("}");

		throw new NoSuchMessageException(sb.toString());
	}

	/**
	 * Returns the last message in the ordered set where sendMessageId = &#63;.
	 *
	 * @param sendMessageId the send message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message, or <code>null</code> if a matching message could not be found
	 */
	@Override
	public Message fetchBysendMessageId_Last(
		long sendMessageId, OrderByComparator<Message> orderByComparator) {

		int count = countBysendMessageId(sendMessageId);

		if (count == 0) {
			return null;
		}

		List<Message> list = findBysendMessageId(
			sendMessageId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Message[] findBysendMessageId_PrevAndNext(
			long messageId, long sendMessageId,
			OrderByComparator<Message> orderByComparator)
		throws NoSuchMessageException {

		Message message = findByPrimaryKey(messageId);

		Session session = null;

		try {
			session = openSession();

			Message[] array = new MessageImpl[3];

			array[0] = getBysendMessageId_PrevAndNext(
				session, message, sendMessageId, orderByComparator, true);

			array[1] = message;

			array[2] = getBysendMessageId_PrevAndNext(
				session, message, sendMessageId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Message getBysendMessageId_PrevAndNext(
		Session session, Message message, long sendMessageId,
		OrderByComparator<Message> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_MESSAGE_WHERE);

		sb.append(_FINDER_COLUMN_SENDMESSAGEID_SENDMESSAGEID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(MessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(sendMessageId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(message)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Message> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the messages where sendMessageId = &#63; from the database.
	 *
	 * @param sendMessageId the send message ID
	 */
	@Override
	public void removeBysendMessageId(long sendMessageId) {
		for (Message message :
				findBysendMessageId(
					sendMessageId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(message);
		}
	}

	/**
	 * Returns the number of messages where sendMessageId = &#63;.
	 *
	 * @param sendMessageId the send message ID
	 * @return the number of matching messages
	 */
	@Override
	public int countBysendMessageId(long sendMessageId) {
		FinderPath finderPath = _finderPathCountBysendMessageId;

		Object[] finderArgs = new Object[] {sendMessageId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_MESSAGE_WHERE);

			sb.append(_FINDER_COLUMN_SENDMESSAGEID_SENDMESSAGEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sendMessageId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_SENDMESSAGEID_SENDMESSAGEID_2 =
		"message.sendMessageId = ?";

	private FinderPath _finderPathWithPaginationFindByfolderId;
	private FinderPath _finderPathWithoutPaginationFindByfolderId;
	private FinderPath _finderPathCountByfolderId;

	/**
	 * Returns all the messages where folderId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @return the matching messages
	 */
	@Override
	public List<Message> findByfolderId(long folderId) {
		return findByfolderId(
			folderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Message> findByfolderId(long folderId, int start, int end) {
		return findByfolderId(folderId, start, end, null);
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
	@Override
	public List<Message> findByfolderId(
		long folderId, int start, int end,
		OrderByComparator<Message> orderByComparator) {

		return findByfolderId(folderId, start, end, orderByComparator, true);
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
	@Override
	public List<Message> findByfolderId(
		long folderId, int start, int end,
		OrderByComparator<Message> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByfolderId;
				finderArgs = new Object[] {folderId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByfolderId;
			finderArgs = new Object[] {folderId, start, end, orderByComparator};
		}

		List<Message> list = null;

		if (useFinderCache) {
			list = (List<Message>)finderCache.getResult(finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (Message message : list) {
					if (folderId != message.getFolderId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_MESSAGE_WHERE);

			sb.append(_FINDER_COLUMN_FOLDERID_FOLDERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(MessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(folderId);

				list = (List<Message>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first message in the ordered set where folderId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message
	 * @throws NoSuchMessageException if a matching message could not be found
	 */
	@Override
	public Message findByfolderId_First(
			long folderId, OrderByComparator<Message> orderByComparator)
		throws NoSuchMessageException {

		Message message = fetchByfolderId_First(folderId, orderByComparator);

		if (message != null) {
			return message;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("folderId=");
		sb.append(folderId);

		sb.append("}");

		throw new NoSuchMessageException(sb.toString());
	}

	/**
	 * Returns the first message in the ordered set where folderId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message, or <code>null</code> if a matching message could not be found
	 */
	@Override
	public Message fetchByfolderId_First(
		long folderId, OrderByComparator<Message> orderByComparator) {

		List<Message> list = findByfolderId(folderId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last message in the ordered set where folderId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message
	 * @throws NoSuchMessageException if a matching message could not be found
	 */
	@Override
	public Message findByfolderId_Last(
			long folderId, OrderByComparator<Message> orderByComparator)
		throws NoSuchMessageException {

		Message message = fetchByfolderId_Last(folderId, orderByComparator);

		if (message != null) {
			return message;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("folderId=");
		sb.append(folderId);

		sb.append("}");

		throw new NoSuchMessageException(sb.toString());
	}

	/**
	 * Returns the last message in the ordered set where folderId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message, or <code>null</code> if a matching message could not be found
	 */
	@Override
	public Message fetchByfolderId_Last(
		long folderId, OrderByComparator<Message> orderByComparator) {

		int count = countByfolderId(folderId);

		if (count == 0) {
			return null;
		}

		List<Message> list = findByfolderId(
			folderId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Message[] findByfolderId_PrevAndNext(
			long messageId, long folderId,
			OrderByComparator<Message> orderByComparator)
		throws NoSuchMessageException {

		Message message = findByPrimaryKey(messageId);

		Session session = null;

		try {
			session = openSession();

			Message[] array = new MessageImpl[3];

			array[0] = getByfolderId_PrevAndNext(
				session, message, folderId, orderByComparator, true);

			array[1] = message;

			array[2] = getByfolderId_PrevAndNext(
				session, message, folderId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Message getByfolderId_PrevAndNext(
		Session session, Message message, long folderId,
		OrderByComparator<Message> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_MESSAGE_WHERE);

		sb.append(_FINDER_COLUMN_FOLDERID_FOLDERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(MessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(folderId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(message)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Message> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the messages where folderId = &#63; from the database.
	 *
	 * @param folderId the folder ID
	 */
	@Override
	public void removeByfolderId(long folderId) {
		for (Message message :
				findByfolderId(
					folderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(message);
		}
	}

	/**
	 * Returns the number of messages where folderId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @return the number of matching messages
	 */
	@Override
	public int countByfolderId(long folderId) {
		FinderPath finderPath = _finderPathCountByfolderId;

		Object[] finderArgs = new Object[] {folderId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_MESSAGE_WHERE);

			sb.append(_FINDER_COLUMN_FOLDERID_FOLDERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(folderId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_FOLDERID_FOLDERID_2 =
		"message.folderId = ?";

	private FinderPath _finderPathWithPaginationFindBythreadId;
	private FinderPath _finderPathWithoutPaginationFindBythreadId;
	private FinderPath _finderPathCountBythreadId;

	/**
	 * Returns all the messages where threadId = &#63;.
	 *
	 * @param threadId the thread ID
	 * @return the matching messages
	 */
	@Override
	public List<Message> findBythreadId(long threadId) {
		return findBythreadId(
			threadId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Message> findBythreadId(long threadId, int start, int end) {
		return findBythreadId(threadId, start, end, null);
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
	@Override
	public List<Message> findBythreadId(
		long threadId, int start, int end,
		OrderByComparator<Message> orderByComparator) {

		return findBythreadId(threadId, start, end, orderByComparator, true);
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
	@Override
	public List<Message> findBythreadId(
		long threadId, int start, int end,
		OrderByComparator<Message> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBythreadId;
				finderArgs = new Object[] {threadId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBythreadId;
			finderArgs = new Object[] {threadId, start, end, orderByComparator};
		}

		List<Message> list = null;

		if (useFinderCache) {
			list = (List<Message>)finderCache.getResult(finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (Message message : list) {
					if (threadId != message.getThreadId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_MESSAGE_WHERE);

			sb.append(_FINDER_COLUMN_THREADID_THREADID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(MessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(threadId);

				list = (List<Message>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first message in the ordered set where threadId = &#63;.
	 *
	 * @param threadId the thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message
	 * @throws NoSuchMessageException if a matching message could not be found
	 */
	@Override
	public Message findBythreadId_First(
			long threadId, OrderByComparator<Message> orderByComparator)
		throws NoSuchMessageException {

		Message message = fetchBythreadId_First(threadId, orderByComparator);

		if (message != null) {
			return message;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("threadId=");
		sb.append(threadId);

		sb.append("}");

		throw new NoSuchMessageException(sb.toString());
	}

	/**
	 * Returns the first message in the ordered set where threadId = &#63;.
	 *
	 * @param threadId the thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message, or <code>null</code> if a matching message could not be found
	 */
	@Override
	public Message fetchBythreadId_First(
		long threadId, OrderByComparator<Message> orderByComparator) {

		List<Message> list = findBythreadId(threadId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last message in the ordered set where threadId = &#63;.
	 *
	 * @param threadId the thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message
	 * @throws NoSuchMessageException if a matching message could not be found
	 */
	@Override
	public Message findBythreadId_Last(
			long threadId, OrderByComparator<Message> orderByComparator)
		throws NoSuchMessageException {

		Message message = fetchBythreadId_Last(threadId, orderByComparator);

		if (message != null) {
			return message;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("threadId=");
		sb.append(threadId);

		sb.append("}");

		throw new NoSuchMessageException(sb.toString());
	}

	/**
	 * Returns the last message in the ordered set where threadId = &#63;.
	 *
	 * @param threadId the thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message, or <code>null</code> if a matching message could not be found
	 */
	@Override
	public Message fetchBythreadId_Last(
		long threadId, OrderByComparator<Message> orderByComparator) {

		int count = countBythreadId(threadId);

		if (count == 0) {
			return null;
		}

		List<Message> list = findBythreadId(
			threadId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Message[] findBythreadId_PrevAndNext(
			long messageId, long threadId,
			OrderByComparator<Message> orderByComparator)
		throws NoSuchMessageException {

		Message message = findByPrimaryKey(messageId);

		Session session = null;

		try {
			session = openSession();

			Message[] array = new MessageImpl[3];

			array[0] = getBythreadId_PrevAndNext(
				session, message, threadId, orderByComparator, true);

			array[1] = message;

			array[2] = getBythreadId_PrevAndNext(
				session, message, threadId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Message getBythreadId_PrevAndNext(
		Session session, Message message, long threadId,
		OrderByComparator<Message> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_MESSAGE_WHERE);

		sb.append(_FINDER_COLUMN_THREADID_THREADID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(MessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(threadId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(message)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Message> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the messages where threadId = &#63; from the database.
	 *
	 * @param threadId the thread ID
	 */
	@Override
	public void removeBythreadId(long threadId) {
		for (Message message :
				findBythreadId(
					threadId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(message);
		}
	}

	/**
	 * Returns the number of messages where threadId = &#63;.
	 *
	 * @param threadId the thread ID
	 * @return the number of matching messages
	 */
	@Override
	public int countBythreadId(long threadId) {
		FinderPath finderPath = _finderPathCountBythreadId;

		Object[] finderArgs = new Object[] {threadId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_MESSAGE_WHERE);

			sb.append(_FINDER_COLUMN_THREADID_THREADID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(threadId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_THREADID_THREADID_2 =
		"message.threadId = ?";

	private FinderPath _finderPathWithPaginationFindByfolderId_threadId;
	private FinderPath _finderPathWithoutPaginationFindByfolderId_threadId;
	private FinderPath _finderPathCountByfolderId_threadId;

	/**
	 * Returns all the messages where folderId = &#63; and threadId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param threadId the thread ID
	 * @return the matching messages
	 */
	@Override
	public List<Message> findByfolderId_threadId(long folderId, long threadId) {
		return findByfolderId_threadId(
			folderId, threadId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Message> findByfolderId_threadId(
		long folderId, long threadId, int start, int end) {

		return findByfolderId_threadId(folderId, threadId, start, end, null);
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
	@Override
	public List<Message> findByfolderId_threadId(
		long folderId, long threadId, int start, int end,
		OrderByComparator<Message> orderByComparator) {

		return findByfolderId_threadId(
			folderId, threadId, start, end, orderByComparator, true);
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
	@Override
	public List<Message> findByfolderId_threadId(
		long folderId, long threadId, int start, int end,
		OrderByComparator<Message> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByfolderId_threadId;
				finderArgs = new Object[] {folderId, threadId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByfolderId_threadId;
			finderArgs = new Object[] {
				folderId, threadId, start, end, orderByComparator
			};
		}

		List<Message> list = null;

		if (useFinderCache) {
			list = (List<Message>)finderCache.getResult(finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (Message message : list) {
					if ((folderId != message.getFolderId()) ||
						(threadId != message.getThreadId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_MESSAGE_WHERE);

			sb.append(_FINDER_COLUMN_FOLDERID_THREADID_FOLDERID_2);

			sb.append(_FINDER_COLUMN_FOLDERID_THREADID_THREADID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(MessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(folderId);

				queryPos.add(threadId);

				list = (List<Message>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public Message findByfolderId_threadId_First(
			long folderId, long threadId,
			OrderByComparator<Message> orderByComparator)
		throws NoSuchMessageException {

		Message message = fetchByfolderId_threadId_First(
			folderId, threadId, orderByComparator);

		if (message != null) {
			return message;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("folderId=");
		sb.append(folderId);

		sb.append(", threadId=");
		sb.append(threadId);

		sb.append("}");

		throw new NoSuchMessageException(sb.toString());
	}

	/**
	 * Returns the first message in the ordered set where folderId = &#63; and threadId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param threadId the thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message, or <code>null</code> if a matching message could not be found
	 */
	@Override
	public Message fetchByfolderId_threadId_First(
		long folderId, long threadId,
		OrderByComparator<Message> orderByComparator) {

		List<Message> list = findByfolderId_threadId(
			folderId, threadId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Message findByfolderId_threadId_Last(
			long folderId, long threadId,
			OrderByComparator<Message> orderByComparator)
		throws NoSuchMessageException {

		Message message = fetchByfolderId_threadId_Last(
			folderId, threadId, orderByComparator);

		if (message != null) {
			return message;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("folderId=");
		sb.append(folderId);

		sb.append(", threadId=");
		sb.append(threadId);

		sb.append("}");

		throw new NoSuchMessageException(sb.toString());
	}

	/**
	 * Returns the last message in the ordered set where folderId = &#63; and threadId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param threadId the thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message, or <code>null</code> if a matching message could not be found
	 */
	@Override
	public Message fetchByfolderId_threadId_Last(
		long folderId, long threadId,
		OrderByComparator<Message> orderByComparator) {

		int count = countByfolderId_threadId(folderId, threadId);

		if (count == 0) {
			return null;
		}

		List<Message> list = findByfolderId_threadId(
			folderId, threadId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Message[] findByfolderId_threadId_PrevAndNext(
			long messageId, long folderId, long threadId,
			OrderByComparator<Message> orderByComparator)
		throws NoSuchMessageException {

		Message message = findByPrimaryKey(messageId);

		Session session = null;

		try {
			session = openSession();

			Message[] array = new MessageImpl[3];

			array[0] = getByfolderId_threadId_PrevAndNext(
				session, message, folderId, threadId, orderByComparator, true);

			array[1] = message;

			array[2] = getByfolderId_threadId_PrevAndNext(
				session, message, folderId, threadId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Message getByfolderId_threadId_PrevAndNext(
		Session session, Message message, long folderId, long threadId,
		OrderByComparator<Message> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_MESSAGE_WHERE);

		sb.append(_FINDER_COLUMN_FOLDERID_THREADID_FOLDERID_2);

		sb.append(_FINDER_COLUMN_FOLDERID_THREADID_THREADID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(MessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(folderId);

		queryPos.add(threadId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(message)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Message> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the messages where folderId = &#63; and threadId = &#63; from the database.
	 *
	 * @param folderId the folder ID
	 * @param threadId the thread ID
	 */
	@Override
	public void removeByfolderId_threadId(long folderId, long threadId) {
		for (Message message :
				findByfolderId_threadId(
					folderId, threadId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(message);
		}
	}

	/**
	 * Returns the number of messages where folderId = &#63; and threadId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param threadId the thread ID
	 * @return the number of matching messages
	 */
	@Override
	public int countByfolderId_threadId(long folderId, long threadId) {
		FinderPath finderPath = _finderPathCountByfolderId_threadId;

		Object[] finderArgs = new Object[] {folderId, threadId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_MESSAGE_WHERE);

			sb.append(_FINDER_COLUMN_FOLDERID_THREADID_FOLDERID_2);

			sb.append(_FINDER_COLUMN_FOLDERID_THREADID_THREADID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(folderId);

				queryPos.add(threadId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_FOLDERID_THREADID_FOLDERID_2 =
		"message.folderId = ? AND ";

	private static final String _FINDER_COLUMN_FOLDERID_THREADID_THREADID_2 =
		"message.threadId = ?";

	private FinderPath _finderPathWithPaginationFindByfolderId_isNew;
	private FinderPath _finderPathWithoutPaginationFindByfolderId_isNew;
	private FinderPath _finderPathCountByfolderId_isNew;

	/**
	 * Returns all the messages where folderId = &#63; and isNew = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param isNew the is new
	 * @return the matching messages
	 */
	@Override
	public List<Message> findByfolderId_isNew(long folderId, boolean isNew) {
		return findByfolderId_isNew(
			folderId, isNew, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Message> findByfolderId_isNew(
		long folderId, boolean isNew, int start, int end) {

		return findByfolderId_isNew(folderId, isNew, start, end, null);
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
	@Override
	public List<Message> findByfolderId_isNew(
		long folderId, boolean isNew, int start, int end,
		OrderByComparator<Message> orderByComparator) {

		return findByfolderId_isNew(
			folderId, isNew, start, end, orderByComparator, true);
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
	@Override
	public List<Message> findByfolderId_isNew(
		long folderId, boolean isNew, int start, int end,
		OrderByComparator<Message> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByfolderId_isNew;
				finderArgs = new Object[] {folderId, isNew};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByfolderId_isNew;
			finderArgs = new Object[] {
				folderId, isNew, start, end, orderByComparator
			};
		}

		List<Message> list = null;

		if (useFinderCache) {
			list = (List<Message>)finderCache.getResult(finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (Message message : list) {
					if ((folderId != message.getFolderId()) ||
						(isNew != message.isIsNew())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_MESSAGE_WHERE);

			sb.append(_FINDER_COLUMN_FOLDERID_ISNEW_FOLDERID_2);

			sb.append(_FINDER_COLUMN_FOLDERID_ISNEW_ISNEW_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(MessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(folderId);

				queryPos.add(isNew);

				list = (List<Message>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public Message findByfolderId_isNew_First(
			long folderId, boolean isNew,
			OrderByComparator<Message> orderByComparator)
		throws NoSuchMessageException {

		Message message = fetchByfolderId_isNew_First(
			folderId, isNew, orderByComparator);

		if (message != null) {
			return message;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("folderId=");
		sb.append(folderId);

		sb.append(", isNew=");
		sb.append(isNew);

		sb.append("}");

		throw new NoSuchMessageException(sb.toString());
	}

	/**
	 * Returns the first message in the ordered set where folderId = &#63; and isNew = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param isNew the is new
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message, or <code>null</code> if a matching message could not be found
	 */
	@Override
	public Message fetchByfolderId_isNew_First(
		long folderId, boolean isNew,
		OrderByComparator<Message> orderByComparator) {

		List<Message> list = findByfolderId_isNew(
			folderId, isNew, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Message findByfolderId_isNew_Last(
			long folderId, boolean isNew,
			OrderByComparator<Message> orderByComparator)
		throws NoSuchMessageException {

		Message message = fetchByfolderId_isNew_Last(
			folderId, isNew, orderByComparator);

		if (message != null) {
			return message;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("folderId=");
		sb.append(folderId);

		sb.append(", isNew=");
		sb.append(isNew);

		sb.append("}");

		throw new NoSuchMessageException(sb.toString());
	}

	/**
	 * Returns the last message in the ordered set where folderId = &#63; and isNew = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param isNew the is new
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message, or <code>null</code> if a matching message could not be found
	 */
	@Override
	public Message fetchByfolderId_isNew_Last(
		long folderId, boolean isNew,
		OrderByComparator<Message> orderByComparator) {

		int count = countByfolderId_isNew(folderId, isNew);

		if (count == 0) {
			return null;
		}

		List<Message> list = findByfolderId_isNew(
			folderId, isNew, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Message[] findByfolderId_isNew_PrevAndNext(
			long messageId, long folderId, boolean isNew,
			OrderByComparator<Message> orderByComparator)
		throws NoSuchMessageException {

		Message message = findByPrimaryKey(messageId);

		Session session = null;

		try {
			session = openSession();

			Message[] array = new MessageImpl[3];

			array[0] = getByfolderId_isNew_PrevAndNext(
				session, message, folderId, isNew, orderByComparator, true);

			array[1] = message;

			array[2] = getByfolderId_isNew_PrevAndNext(
				session, message, folderId, isNew, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Message getByfolderId_isNew_PrevAndNext(
		Session session, Message message, long folderId, boolean isNew,
		OrderByComparator<Message> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_MESSAGE_WHERE);

		sb.append(_FINDER_COLUMN_FOLDERID_ISNEW_FOLDERID_2);

		sb.append(_FINDER_COLUMN_FOLDERID_ISNEW_ISNEW_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(MessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(folderId);

		queryPos.add(isNew);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(message)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Message> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the messages where folderId = &#63; and isNew = &#63; from the database.
	 *
	 * @param folderId the folder ID
	 * @param isNew the is new
	 */
	@Override
	public void removeByfolderId_isNew(long folderId, boolean isNew) {
		for (Message message :
				findByfolderId_isNew(
					folderId, isNew, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(message);
		}
	}

	/**
	 * Returns the number of messages where folderId = &#63; and isNew = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param isNew the is new
	 * @return the number of matching messages
	 */
	@Override
	public int countByfolderId_isNew(long folderId, boolean isNew) {
		FinderPath finderPath = _finderPathCountByfolderId_isNew;

		Object[] finderArgs = new Object[] {folderId, isNew};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_MESSAGE_WHERE);

			sb.append(_FINDER_COLUMN_FOLDERID_ISNEW_FOLDERID_2);

			sb.append(_FINDER_COLUMN_FOLDERID_ISNEW_ISNEW_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(folderId);

				queryPos.add(isNew);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_FOLDERID_ISNEW_FOLDERID_2 =
		"message.folderId = ? AND ";

	private static final String _FINDER_COLUMN_FOLDERID_ISNEW_ISNEW_2 =
		"message.isNew = ?";

	public MessagePersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(Message.class);

		setModelImplClass(MessageImpl.class);
		setModelPKClass(long.class);

		setTable(MessageTable.INSTANCE);
	}

	/**
	 * Caches the message in the entity cache if it is enabled.
	 *
	 * @param message the message
	 */
	@Override
	public void cacheResult(Message message) {
		entityCache.putResult(
			MessageImpl.class, message.getPrimaryKey(), message);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the messages in the entity cache if it is enabled.
	 *
	 * @param messages the messages
	 */
	@Override
	public void cacheResult(List<Message> messages) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (messages.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Message message : messages) {
			if (entityCache.getResult(
					MessageImpl.class, message.getPrimaryKey()) == null) {

				cacheResult(message);
			}
		}
	}

	/**
	 * Clears the cache for all messages.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MessageImpl.class);

		finderCache.clearCache(MessageImpl.class);
	}

	/**
	 * Clears the cache for the message.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Message message) {
		entityCache.removeResult(MessageImpl.class, message);
	}

	@Override
	public void clearCache(List<Message> messages) {
		for (Message message : messages) {
			entityCache.removeResult(MessageImpl.class, message);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(MessageImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(MessageImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new message with the primary key. Does not add the message to the database.
	 *
	 * @param messageId the primary key for the new message
	 * @return the new message
	 */
	@Override
	public Message create(long messageId) {
		Message message = new MessageImpl();

		message.setNew(true);
		message.setPrimaryKey(messageId);

		return message;
	}

	/**
	 * Removes the message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param messageId the primary key of the message
	 * @return the message that was removed
	 * @throws NoSuchMessageException if a message with the primary key could not be found
	 */
	@Override
	public Message remove(long messageId) throws NoSuchMessageException {
		return remove((Serializable)messageId);
	}

	/**
	 * Removes the message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the message
	 * @return the message that was removed
	 * @throws NoSuchMessageException if a message with the primary key could not be found
	 */
	@Override
	public Message remove(Serializable primaryKey)
		throws NoSuchMessageException {

		Session session = null;

		try {
			session = openSession();

			Message message = (Message)session.get(
				MessageImpl.class, primaryKey);

			if (message == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMessageException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(message);
		}
		catch (NoSuchMessageException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Message removeImpl(Message message) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(message)) {
				message = (Message)session.get(
					MessageImpl.class, message.getPrimaryKeyObj());
			}

			if (message != null) {
				session.delete(message);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (message != null) {
			clearCache(message);
		}

		return message;
	}

	@Override
	public Message updateImpl(Message message) {
		boolean isNew = message.isNew();

		if (!(message instanceof MessageModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(message.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(message);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in message proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Message implementation " +
					message.getClass());
		}

		MessageModelImpl messageModelImpl = (MessageModelImpl)message;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(message);
			}
			else {
				message = (Message)session.merge(message);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(MessageImpl.class, messageModelImpl, false, true);

		if (isNew) {
			message.setNew(false);
		}

		message.resetOriginalValues();

		return message;
	}

	/**
	 * Returns the message with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the message
	 * @return the message
	 * @throws NoSuchMessageException if a message with the primary key could not be found
	 */
	@Override
	public Message findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMessageException {

		Message message = fetchByPrimaryKey(primaryKey);

		if (message == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMessageException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return message;
	}

	/**
	 * Returns the message with the primary key or throws a <code>NoSuchMessageException</code> if it could not be found.
	 *
	 * @param messageId the primary key of the message
	 * @return the message
	 * @throws NoSuchMessageException if a message with the primary key could not be found
	 */
	@Override
	public Message findByPrimaryKey(long messageId)
		throws NoSuchMessageException {

		return findByPrimaryKey((Serializable)messageId);
	}

	/**
	 * Returns the message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param messageId the primary key of the message
	 * @return the message, or <code>null</code> if a message with the primary key could not be found
	 */
	@Override
	public Message fetchByPrimaryKey(long messageId) {
		return fetchByPrimaryKey((Serializable)messageId);
	}

	/**
	 * Returns all the messages.
	 *
	 * @return the messages
	 */
	@Override
	public List<Message> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Message> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<Message> findAll(
		int start, int end, OrderByComparator<Message> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<Message> findAll(
		int start, int end, OrderByComparator<Message> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<Message> list = null;

		if (useFinderCache) {
			list = (List<Message>)finderCache.getResult(finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_MESSAGE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_MESSAGE;

				sql = sql.concat(MessageModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Message>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the messages from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Message message : findAll()) {
			remove(message);
		}
	}

	/**
	 * Returns the number of messages.
	 *
	 * @return the number of messages
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_MESSAGE);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "messageId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_MESSAGE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MessageModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the message persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindBysendMessageId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBysendMessageId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"sendMessageId"}, true);

		_finderPathWithoutPaginationFindBysendMessageId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBysendMessageId",
			new String[] {Long.class.getName()}, new String[] {"sendMessageId"},
			true);

		_finderPathCountBysendMessageId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBysendMessageId",
			new String[] {Long.class.getName()}, new String[] {"sendMessageId"},
			false);

		_finderPathWithPaginationFindByfolderId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByfolderId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"folderId"}, true);

		_finderPathWithoutPaginationFindByfolderId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByfolderId",
			new String[] {Long.class.getName()}, new String[] {"folderId"},
			true);

		_finderPathCountByfolderId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByfolderId",
			new String[] {Long.class.getName()}, new String[] {"folderId"},
			false);

		_finderPathWithPaginationFindBythreadId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBythreadId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"threadId"}, true);

		_finderPathWithoutPaginationFindBythreadId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBythreadId",
			new String[] {Long.class.getName()}, new String[] {"threadId"},
			true);

		_finderPathCountBythreadId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBythreadId",
			new String[] {Long.class.getName()}, new String[] {"threadId"},
			false);

		_finderPathWithPaginationFindByfolderId_threadId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByfolderId_threadId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"folderId", "threadId"}, true);

		_finderPathWithoutPaginationFindByfolderId_threadId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByfolderId_threadId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"folderId", "threadId"}, true);

		_finderPathCountByfolderId_threadId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByfolderId_threadId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"folderId", "threadId"}, false);

		_finderPathWithPaginationFindByfolderId_isNew = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByfolderId_isNew",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"folderId", "isNew"}, true);

		_finderPathWithoutPaginationFindByfolderId_isNew = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByfolderId_isNew",
			new String[] {Long.class.getName(), Boolean.class.getName()},
			new String[] {"folderId", "isNew"}, true);

		_finderPathCountByfolderId_isNew = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByfolderId_isNew",
			new String[] {Long.class.getName(), Boolean.class.getName()},
			new String[] {"folderId", "isNew"}, false);

		_setMessageUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setMessageUtilPersistence(null);

		entityCache.removeCache(MessageImpl.class.getName());
	}

	private void _setMessageUtilPersistence(
		MessagePersistence messagePersistence) {

		try {
			Field field = MessageUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, messagePersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = MessagingPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = MessagingPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = MessagingPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_MESSAGE =
		"SELECT message FROM Message message";

	private static final String _SQL_SELECT_MESSAGE_WHERE =
		"SELECT message FROM Message message WHERE ";

	private static final String _SQL_COUNT_MESSAGE =
		"SELECT COUNT(message) FROM Message message";

	private static final String _SQL_COUNT_MESSAGE_WHERE =
		"SELECT COUNT(message) FROM Message message WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "message.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Message exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Message exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		MessagePersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private MessageModelArgumentsResolver _messageModelArgumentsResolver;

}