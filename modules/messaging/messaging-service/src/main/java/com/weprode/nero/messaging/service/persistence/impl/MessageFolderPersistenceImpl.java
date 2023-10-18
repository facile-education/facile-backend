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

import com.weprode.nero.messaging.exception.NoSuchMessageFolderException;
import com.weprode.nero.messaging.model.MessageFolder;
import com.weprode.nero.messaging.model.MessageFolderTable;
import com.weprode.nero.messaging.model.impl.MessageFolderImpl;
import com.weprode.nero.messaging.model.impl.MessageFolderModelImpl;
import com.weprode.nero.messaging.service.persistence.MessageFolderPersistence;
import com.weprode.nero.messaging.service.persistence.MessageFolderUtil;
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
 * The persistence implementation for the message folder service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {MessageFolderPersistence.class, BasePersistence.class})
public class MessageFolderPersistenceImpl
	extends BasePersistenceImpl<MessageFolder>
	implements MessageFolderPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>MessageFolderUtil</code> to access the message folder persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		MessageFolderImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByuserId;
	private FinderPath _finderPathWithoutPaginationFindByuserId;
	private FinderPath _finderPathCountByuserId;

	/**
	 * Returns all the message folders where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching message folders
	 */
	@Override
	public List<MessageFolder> findByuserId(long userId) {
		return findByuserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<MessageFolder> findByuserId(long userId, int start, int end) {
		return findByuserId(userId, start, end, null);
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
	@Override
	public List<MessageFolder> findByuserId(
		long userId, int start, int end,
		OrderByComparator<MessageFolder> orderByComparator) {

		return findByuserId(userId, start, end, orderByComparator, true);
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
	@Override
	public List<MessageFolder> findByuserId(
		long userId, int start, int end,
		OrderByComparator<MessageFolder> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByuserId;
				finderArgs = new Object[] {userId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByuserId;
			finderArgs = new Object[] {userId, start, end, orderByComparator};
		}

		List<MessageFolder> list = null;

		if (useFinderCache) {
			list = (List<MessageFolder>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MessageFolder messageFolder : list) {
					if (userId != messageFolder.getUserId()) {
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

			sb.append(_SQL_SELECT_MESSAGEFOLDER_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(MessageFolderModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<MessageFolder>)QueryUtil.list(
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
	 * Returns the first message folder in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message folder
	 * @throws NoSuchMessageFolderException if a matching message folder could not be found
	 */
	@Override
	public MessageFolder findByuserId_First(
			long userId, OrderByComparator<MessageFolder> orderByComparator)
		throws NoSuchMessageFolderException {

		MessageFolder messageFolder = fetchByuserId_First(
			userId, orderByComparator);

		if (messageFolder != null) {
			return messageFolder;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchMessageFolderException(sb.toString());
	}

	/**
	 * Returns the first message folder in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message folder, or <code>null</code> if a matching message folder could not be found
	 */
	@Override
	public MessageFolder fetchByuserId_First(
		long userId, OrderByComparator<MessageFolder> orderByComparator) {

		List<MessageFolder> list = findByuserId(
			userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last message folder in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message folder
	 * @throws NoSuchMessageFolderException if a matching message folder could not be found
	 */
	@Override
	public MessageFolder findByuserId_Last(
			long userId, OrderByComparator<MessageFolder> orderByComparator)
		throws NoSuchMessageFolderException {

		MessageFolder messageFolder = fetchByuserId_Last(
			userId, orderByComparator);

		if (messageFolder != null) {
			return messageFolder;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchMessageFolderException(sb.toString());
	}

	/**
	 * Returns the last message folder in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message folder, or <code>null</code> if a matching message folder could not be found
	 */
	@Override
	public MessageFolder fetchByuserId_Last(
		long userId, OrderByComparator<MessageFolder> orderByComparator) {

		int count = countByuserId(userId);

		if (count == 0) {
			return null;
		}

		List<MessageFolder> list = findByuserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MessageFolder[] findByuserId_PrevAndNext(
			long folderId, long userId,
			OrderByComparator<MessageFolder> orderByComparator)
		throws NoSuchMessageFolderException {

		MessageFolder messageFolder = findByPrimaryKey(folderId);

		Session session = null;

		try {
			session = openSession();

			MessageFolder[] array = new MessageFolderImpl[3];

			array[0] = getByuserId_PrevAndNext(
				session, messageFolder, userId, orderByComparator, true);

			array[1] = messageFolder;

			array[2] = getByuserId_PrevAndNext(
				session, messageFolder, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected MessageFolder getByuserId_PrevAndNext(
		Session session, MessageFolder messageFolder, long userId,
		OrderByComparator<MessageFolder> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_MESSAGEFOLDER_WHERE);

		sb.append(_FINDER_COLUMN_USERID_USERID_2);

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
			sb.append(MessageFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						messageFolder)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<MessageFolder> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the message folders where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByuserId(long userId) {
		for (MessageFolder messageFolder :
				findByuserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(messageFolder);
		}
	}

	/**
	 * Returns the number of message folders where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching message folders
	 */
	@Override
	public int countByuserId(long userId) {
		FinderPath finderPath = _finderPathCountByuserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_MESSAGEFOLDER_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 =
		"messageFolder.userId = ?";

	private FinderPath _finderPathWithPaginationFindByuserId_type;
	private FinderPath _finderPathWithoutPaginationFindByuserId_type;
	private FinderPath _finderPathCountByuserId_type;

	/**
	 * Returns all the message folders where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @return the matching message folders
	 */
	@Override
	public List<MessageFolder> findByuserId_type(long userId, int type) {
		return findByuserId_type(
			userId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<MessageFolder> findByuserId_type(
		long userId, int type, int start, int end) {

		return findByuserId_type(userId, type, start, end, null);
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
	@Override
	public List<MessageFolder> findByuserId_type(
		long userId, int type, int start, int end,
		OrderByComparator<MessageFolder> orderByComparator) {

		return findByuserId_type(
			userId, type, start, end, orderByComparator, true);
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
	@Override
	public List<MessageFolder> findByuserId_type(
		long userId, int type, int start, int end,
		OrderByComparator<MessageFolder> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByuserId_type;
				finderArgs = new Object[] {userId, type};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByuserId_type;
			finderArgs = new Object[] {
				userId, type, start, end, orderByComparator
			};
		}

		List<MessageFolder> list = null;

		if (useFinderCache) {
			list = (List<MessageFolder>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MessageFolder messageFolder : list) {
					if ((userId != messageFolder.getUserId()) ||
						(type != messageFolder.getType())) {

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

			sb.append(_SQL_SELECT_MESSAGEFOLDER_WHERE);

			sb.append(_FINDER_COLUMN_USERID_TYPE_USERID_2);

			sb.append(_FINDER_COLUMN_USERID_TYPE_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(MessageFolderModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(type);

				list = (List<MessageFolder>)QueryUtil.list(
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
	 * Returns the first message folder in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message folder
	 * @throws NoSuchMessageFolderException if a matching message folder could not be found
	 */
	@Override
	public MessageFolder findByuserId_type_First(
			long userId, int type,
			OrderByComparator<MessageFolder> orderByComparator)
		throws NoSuchMessageFolderException {

		MessageFolder messageFolder = fetchByuserId_type_First(
			userId, type, orderByComparator);

		if (messageFolder != null) {
			return messageFolder;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", type=");
		sb.append(type);

		sb.append("}");

		throw new NoSuchMessageFolderException(sb.toString());
	}

	/**
	 * Returns the first message folder in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message folder, or <code>null</code> if a matching message folder could not be found
	 */
	@Override
	public MessageFolder fetchByuserId_type_First(
		long userId, int type,
		OrderByComparator<MessageFolder> orderByComparator) {

		List<MessageFolder> list = findByuserId_type(
			userId, type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MessageFolder findByuserId_type_Last(
			long userId, int type,
			OrderByComparator<MessageFolder> orderByComparator)
		throws NoSuchMessageFolderException {

		MessageFolder messageFolder = fetchByuserId_type_Last(
			userId, type, orderByComparator);

		if (messageFolder != null) {
			return messageFolder;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", type=");
		sb.append(type);

		sb.append("}");

		throw new NoSuchMessageFolderException(sb.toString());
	}

	/**
	 * Returns the last message folder in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message folder, or <code>null</code> if a matching message folder could not be found
	 */
	@Override
	public MessageFolder fetchByuserId_type_Last(
		long userId, int type,
		OrderByComparator<MessageFolder> orderByComparator) {

		int count = countByuserId_type(userId, type);

		if (count == 0) {
			return null;
		}

		List<MessageFolder> list = findByuserId_type(
			userId, type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MessageFolder[] findByuserId_type_PrevAndNext(
			long folderId, long userId, int type,
			OrderByComparator<MessageFolder> orderByComparator)
		throws NoSuchMessageFolderException {

		MessageFolder messageFolder = findByPrimaryKey(folderId);

		Session session = null;

		try {
			session = openSession();

			MessageFolder[] array = new MessageFolderImpl[3];

			array[0] = getByuserId_type_PrevAndNext(
				session, messageFolder, userId, type, orderByComparator, true);

			array[1] = messageFolder;

			array[2] = getByuserId_type_PrevAndNext(
				session, messageFolder, userId, type, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected MessageFolder getByuserId_type_PrevAndNext(
		Session session, MessageFolder messageFolder, long userId, int type,
		OrderByComparator<MessageFolder> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_MESSAGEFOLDER_WHERE);

		sb.append(_FINDER_COLUMN_USERID_TYPE_USERID_2);

		sb.append(_FINDER_COLUMN_USERID_TYPE_TYPE_2);

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
			sb.append(MessageFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		queryPos.add(type);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						messageFolder)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<MessageFolder> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the message folders where userId = &#63; and type = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param type the type
	 */
	@Override
	public void removeByuserId_type(long userId, int type) {
		for (MessageFolder messageFolder :
				findByuserId_type(
					userId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(messageFolder);
		}
	}

	/**
	 * Returns the number of message folders where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @return the number of matching message folders
	 */
	@Override
	public int countByuserId_type(long userId, int type) {
		FinderPath finderPath = _finderPathCountByuserId_type;

		Object[] finderArgs = new Object[] {userId, type};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_MESSAGEFOLDER_WHERE);

			sb.append(_FINDER_COLUMN_USERID_TYPE_USERID_2);

			sb.append(_FINDER_COLUMN_USERID_TYPE_TYPE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(type);

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

	private static final String _FINDER_COLUMN_USERID_TYPE_USERID_2 =
		"messageFolder.userId = ? AND ";

	private static final String _FINDER_COLUMN_USERID_TYPE_TYPE_2 =
		"messageFolder.type = ?";

	private FinderPath _finderPathWithPaginationFindByuserId_parentFolderId;
	private FinderPath _finderPathWithoutPaginationFindByuserId_parentFolderId;
	private FinderPath _finderPathCountByuserId_parentFolderId;

	/**
	 * Returns all the message folders where userId = &#63; and parentFolderId = &#63;.
	 *
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 * @return the matching message folders
	 */
	@Override
	public List<MessageFolder> findByuserId_parentFolderId(
		long userId, long parentFolderId) {

		return findByuserId_parentFolderId(
			userId, parentFolderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<MessageFolder> findByuserId_parentFolderId(
		long userId, long parentFolderId, int start, int end) {

		return findByuserId_parentFolderId(
			userId, parentFolderId, start, end, null);
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
	@Override
	public List<MessageFolder> findByuserId_parentFolderId(
		long userId, long parentFolderId, int start, int end,
		OrderByComparator<MessageFolder> orderByComparator) {

		return findByuserId_parentFolderId(
			userId, parentFolderId, start, end, orderByComparator, true);
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
	@Override
	public List<MessageFolder> findByuserId_parentFolderId(
		long userId, long parentFolderId, int start, int end,
		OrderByComparator<MessageFolder> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByuserId_parentFolderId;
				finderArgs = new Object[] {userId, parentFolderId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByuserId_parentFolderId;
			finderArgs = new Object[] {
				userId, parentFolderId, start, end, orderByComparator
			};
		}

		List<MessageFolder> list = null;

		if (useFinderCache) {
			list = (List<MessageFolder>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MessageFolder messageFolder : list) {
					if ((userId != messageFolder.getUserId()) ||
						(parentFolderId != messageFolder.getParentFolderId())) {

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

			sb.append(_SQL_SELECT_MESSAGEFOLDER_WHERE);

			sb.append(_FINDER_COLUMN_USERID_PARENTFOLDERID_USERID_2);

			sb.append(_FINDER_COLUMN_USERID_PARENTFOLDERID_PARENTFOLDERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(MessageFolderModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(parentFolderId);

				list = (List<MessageFolder>)QueryUtil.list(
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
	 * Returns the first message folder in the ordered set where userId = &#63; and parentFolderId = &#63;.
	 *
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message folder
	 * @throws NoSuchMessageFolderException if a matching message folder could not be found
	 */
	@Override
	public MessageFolder findByuserId_parentFolderId_First(
			long userId, long parentFolderId,
			OrderByComparator<MessageFolder> orderByComparator)
		throws NoSuchMessageFolderException {

		MessageFolder messageFolder = fetchByuserId_parentFolderId_First(
			userId, parentFolderId, orderByComparator);

		if (messageFolder != null) {
			return messageFolder;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", parentFolderId=");
		sb.append(parentFolderId);

		sb.append("}");

		throw new NoSuchMessageFolderException(sb.toString());
	}

	/**
	 * Returns the first message folder in the ordered set where userId = &#63; and parentFolderId = &#63;.
	 *
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching message folder, or <code>null</code> if a matching message folder could not be found
	 */
	@Override
	public MessageFolder fetchByuserId_parentFolderId_First(
		long userId, long parentFolderId,
		OrderByComparator<MessageFolder> orderByComparator) {

		List<MessageFolder> list = findByuserId_parentFolderId(
			userId, parentFolderId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MessageFolder findByuserId_parentFolderId_Last(
			long userId, long parentFolderId,
			OrderByComparator<MessageFolder> orderByComparator)
		throws NoSuchMessageFolderException {

		MessageFolder messageFolder = fetchByuserId_parentFolderId_Last(
			userId, parentFolderId, orderByComparator);

		if (messageFolder != null) {
			return messageFolder;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", parentFolderId=");
		sb.append(parentFolderId);

		sb.append("}");

		throw new NoSuchMessageFolderException(sb.toString());
	}

	/**
	 * Returns the last message folder in the ordered set where userId = &#63; and parentFolderId = &#63;.
	 *
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching message folder, or <code>null</code> if a matching message folder could not be found
	 */
	@Override
	public MessageFolder fetchByuserId_parentFolderId_Last(
		long userId, long parentFolderId,
		OrderByComparator<MessageFolder> orderByComparator) {

		int count = countByuserId_parentFolderId(userId, parentFolderId);

		if (count == 0) {
			return null;
		}

		List<MessageFolder> list = findByuserId_parentFolderId(
			userId, parentFolderId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MessageFolder[] findByuserId_parentFolderId_PrevAndNext(
			long folderId, long userId, long parentFolderId,
			OrderByComparator<MessageFolder> orderByComparator)
		throws NoSuchMessageFolderException {

		MessageFolder messageFolder = findByPrimaryKey(folderId);

		Session session = null;

		try {
			session = openSession();

			MessageFolder[] array = new MessageFolderImpl[3];

			array[0] = getByuserId_parentFolderId_PrevAndNext(
				session, messageFolder, userId, parentFolderId,
				orderByComparator, true);

			array[1] = messageFolder;

			array[2] = getByuserId_parentFolderId_PrevAndNext(
				session, messageFolder, userId, parentFolderId,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected MessageFolder getByuserId_parentFolderId_PrevAndNext(
		Session session, MessageFolder messageFolder, long userId,
		long parentFolderId, OrderByComparator<MessageFolder> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_MESSAGEFOLDER_WHERE);

		sb.append(_FINDER_COLUMN_USERID_PARENTFOLDERID_USERID_2);

		sb.append(_FINDER_COLUMN_USERID_PARENTFOLDERID_PARENTFOLDERID_2);

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
			sb.append(MessageFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		queryPos.add(parentFolderId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						messageFolder)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<MessageFolder> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the message folders where userId = &#63; and parentFolderId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 */
	@Override
	public void removeByuserId_parentFolderId(
		long userId, long parentFolderId) {

		for (MessageFolder messageFolder :
				findByuserId_parentFolderId(
					userId, parentFolderId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(messageFolder);
		}
	}

	/**
	 * Returns the number of message folders where userId = &#63; and parentFolderId = &#63;.
	 *
	 * @param userId the user ID
	 * @param parentFolderId the parent folder ID
	 * @return the number of matching message folders
	 */
	@Override
	public int countByuserId_parentFolderId(long userId, long parentFolderId) {
		FinderPath finderPath = _finderPathCountByuserId_parentFolderId;

		Object[] finderArgs = new Object[] {userId, parentFolderId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_MESSAGEFOLDER_WHERE);

			sb.append(_FINDER_COLUMN_USERID_PARENTFOLDERID_USERID_2);

			sb.append(_FINDER_COLUMN_USERID_PARENTFOLDERID_PARENTFOLDERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(parentFolderId);

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

	private static final String _FINDER_COLUMN_USERID_PARENTFOLDERID_USERID_2 =
		"messageFolder.userId = ? AND ";

	private static final String
		_FINDER_COLUMN_USERID_PARENTFOLDERID_PARENTFOLDERID_2 =
			"messageFolder.parentFolderId = ?";

	public MessageFolderPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(MessageFolder.class);

		setModelImplClass(MessageFolderImpl.class);
		setModelPKClass(long.class);

		setTable(MessageFolderTable.INSTANCE);
	}

	/**
	 * Caches the message folder in the entity cache if it is enabled.
	 *
	 * @param messageFolder the message folder
	 */
	@Override
	public void cacheResult(MessageFolder messageFolder) {
		entityCache.putResult(
			MessageFolderImpl.class, messageFolder.getPrimaryKey(),
			messageFolder);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the message folders in the entity cache if it is enabled.
	 *
	 * @param messageFolders the message folders
	 */
	@Override
	public void cacheResult(List<MessageFolder> messageFolders) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (messageFolders.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (MessageFolder messageFolder : messageFolders) {
			if (entityCache.getResult(
					MessageFolderImpl.class, messageFolder.getPrimaryKey()) ==
						null) {

				cacheResult(messageFolder);
			}
		}
	}

	/**
	 * Clears the cache for all message folders.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MessageFolderImpl.class);

		finderCache.clearCache(MessageFolderImpl.class);
	}

	/**
	 * Clears the cache for the message folder.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MessageFolder messageFolder) {
		entityCache.removeResult(MessageFolderImpl.class, messageFolder);
	}

	@Override
	public void clearCache(List<MessageFolder> messageFolders) {
		for (MessageFolder messageFolder : messageFolders) {
			entityCache.removeResult(MessageFolderImpl.class, messageFolder);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(MessageFolderImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(MessageFolderImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new message folder with the primary key. Does not add the message folder to the database.
	 *
	 * @param folderId the primary key for the new message folder
	 * @return the new message folder
	 */
	@Override
	public MessageFolder create(long folderId) {
		MessageFolder messageFolder = new MessageFolderImpl();

		messageFolder.setNew(true);
		messageFolder.setPrimaryKey(folderId);

		return messageFolder;
	}

	/**
	 * Removes the message folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param folderId the primary key of the message folder
	 * @return the message folder that was removed
	 * @throws NoSuchMessageFolderException if a message folder with the primary key could not be found
	 */
	@Override
	public MessageFolder remove(long folderId)
		throws NoSuchMessageFolderException {

		return remove((Serializable)folderId);
	}

	/**
	 * Removes the message folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the message folder
	 * @return the message folder that was removed
	 * @throws NoSuchMessageFolderException if a message folder with the primary key could not be found
	 */
	@Override
	public MessageFolder remove(Serializable primaryKey)
		throws NoSuchMessageFolderException {

		Session session = null;

		try {
			session = openSession();

			MessageFolder messageFolder = (MessageFolder)session.get(
				MessageFolderImpl.class, primaryKey);

			if (messageFolder == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMessageFolderException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(messageFolder);
		}
		catch (NoSuchMessageFolderException noSuchEntityException) {
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
	protected MessageFolder removeImpl(MessageFolder messageFolder) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(messageFolder)) {
				messageFolder = (MessageFolder)session.get(
					MessageFolderImpl.class, messageFolder.getPrimaryKeyObj());
			}

			if (messageFolder != null) {
				session.delete(messageFolder);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (messageFolder != null) {
			clearCache(messageFolder);
		}

		return messageFolder;
	}

	@Override
	public MessageFolder updateImpl(MessageFolder messageFolder) {
		boolean isNew = messageFolder.isNew();

		if (!(messageFolder instanceof MessageFolderModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(messageFolder.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					messageFolder);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in messageFolder proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom MessageFolder implementation " +
					messageFolder.getClass());
		}

		MessageFolderModelImpl messageFolderModelImpl =
			(MessageFolderModelImpl)messageFolder;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(messageFolder);
			}
			else {
				messageFolder = (MessageFolder)session.merge(messageFolder);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			MessageFolderImpl.class, messageFolderModelImpl, false, true);

		if (isNew) {
			messageFolder.setNew(false);
		}

		messageFolder.resetOriginalValues();

		return messageFolder;
	}

	/**
	 * Returns the message folder with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the message folder
	 * @return the message folder
	 * @throws NoSuchMessageFolderException if a message folder with the primary key could not be found
	 */
	@Override
	public MessageFolder findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMessageFolderException {

		MessageFolder messageFolder = fetchByPrimaryKey(primaryKey);

		if (messageFolder == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMessageFolderException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return messageFolder;
	}

	/**
	 * Returns the message folder with the primary key or throws a <code>NoSuchMessageFolderException</code> if it could not be found.
	 *
	 * @param folderId the primary key of the message folder
	 * @return the message folder
	 * @throws NoSuchMessageFolderException if a message folder with the primary key could not be found
	 */
	@Override
	public MessageFolder findByPrimaryKey(long folderId)
		throws NoSuchMessageFolderException {

		return findByPrimaryKey((Serializable)folderId);
	}

	/**
	 * Returns the message folder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param folderId the primary key of the message folder
	 * @return the message folder, or <code>null</code> if a message folder with the primary key could not be found
	 */
	@Override
	public MessageFolder fetchByPrimaryKey(long folderId) {
		return fetchByPrimaryKey((Serializable)folderId);
	}

	/**
	 * Returns all the message folders.
	 *
	 * @return the message folders
	 */
	@Override
	public List<MessageFolder> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<MessageFolder> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<MessageFolder> findAll(
		int start, int end,
		OrderByComparator<MessageFolder> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<MessageFolder> findAll(
		int start, int end, OrderByComparator<MessageFolder> orderByComparator,
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

		List<MessageFolder> list = null;

		if (useFinderCache) {
			list = (List<MessageFolder>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_MESSAGEFOLDER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_MESSAGEFOLDER;

				sql = sql.concat(MessageFolderModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<MessageFolder>)QueryUtil.list(
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
	 * Removes all the message folders from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MessageFolder messageFolder : findAll()) {
			remove(messageFolder);
		}
	}

	/**
	 * Returns the number of message folders.
	 *
	 * @return the number of message folders
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_MESSAGEFOLDER);

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
		return "folderId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_MESSAGEFOLDER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MessageFolderModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the message folder persistence.
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

		_finderPathWithPaginationFindByuserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByuserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"userId"}, true);

		_finderPathWithoutPaginationFindByuserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByuserId",
			new String[] {Long.class.getName()}, new String[] {"userId"}, true);

		_finderPathCountByuserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByuserId",
			new String[] {Long.class.getName()}, new String[] {"userId"},
			false);

		_finderPathWithPaginationFindByuserId_type = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByuserId_type",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"userId", "type_"}, true);

		_finderPathWithoutPaginationFindByuserId_type = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByuserId_type",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"userId", "type_"}, true);

		_finderPathCountByuserId_type = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByuserId_type",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"userId", "type_"}, false);

		_finderPathWithPaginationFindByuserId_parentFolderId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByuserId_parentFolderId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"userId", "parentFolderId"}, true);

		_finderPathWithoutPaginationFindByuserId_parentFolderId =
			new FinderPath(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByuserId_parentFolderId",
				new String[] {Long.class.getName(), Long.class.getName()},
				new String[] {"userId", "parentFolderId"}, true);

		_finderPathCountByuserId_parentFolderId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByuserId_parentFolderId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"userId", "parentFolderId"}, false);

		_setMessageFolderUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setMessageFolderUtilPersistence(null);

		entityCache.removeCache(MessageFolderImpl.class.getName());
	}

	private void _setMessageFolderUtilPersistence(
		MessageFolderPersistence messageFolderPersistence) {

		try {
			Field field = MessageFolderUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, messageFolderPersistence);
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

	private static final String _SQL_SELECT_MESSAGEFOLDER =
		"SELECT messageFolder FROM MessageFolder messageFolder";

	private static final String _SQL_SELECT_MESSAGEFOLDER_WHERE =
		"SELECT messageFolder FROM MessageFolder messageFolder WHERE ";

	private static final String _SQL_COUNT_MESSAGEFOLDER =
		"SELECT COUNT(messageFolder) FROM MessageFolder messageFolder";

	private static final String _SQL_COUNT_MESSAGEFOLDER_WHERE =
		"SELECT COUNT(messageFolder) FROM MessageFolder messageFolder WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "messageFolder.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No MessageFolder exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No MessageFolder exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		MessageFolderPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}