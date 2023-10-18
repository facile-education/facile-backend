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
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.nero.messaging.exception.NoSuchMessageRecipientsException;
import com.weprode.nero.messaging.model.MessageRecipients;
import com.weprode.nero.messaging.model.MessageRecipientsTable;
import com.weprode.nero.messaging.model.impl.MessageRecipientsImpl;
import com.weprode.nero.messaging.model.impl.MessageRecipientsModelImpl;
import com.weprode.nero.messaging.service.persistence.MessageRecipientsPersistence;
import com.weprode.nero.messaging.service.persistence.MessageRecipientsUtil;
import com.weprode.nero.messaging.service.persistence.impl.constants.MessagingPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the message recipients service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	service = {MessageRecipientsPersistence.class, BasePersistence.class}
)
public class MessageRecipientsPersistenceImpl
	extends BasePersistenceImpl<MessageRecipients>
	implements MessageRecipientsPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>MessageRecipientsUtil</code> to access the message recipients persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		MessageRecipientsImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchBymessageId;
	private FinderPath _finderPathCountBymessageId;

	/**
	 * Returns the message recipients where messageId = &#63; or throws a <code>NoSuchMessageRecipientsException</code> if it could not be found.
	 *
	 * @param messageId the message ID
	 * @return the matching message recipients
	 * @throws NoSuchMessageRecipientsException if a matching message recipients could not be found
	 */
	@Override
	public MessageRecipients findBymessageId(long messageId)
		throws NoSuchMessageRecipientsException {

		MessageRecipients messageRecipients = fetchBymessageId(messageId);

		if (messageRecipients == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("messageId=");
			sb.append(messageId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchMessageRecipientsException(sb.toString());
		}

		return messageRecipients;
	}

	/**
	 * Returns the message recipients where messageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param messageId the message ID
	 * @return the matching message recipients, or <code>null</code> if a matching message recipients could not be found
	 */
	@Override
	public MessageRecipients fetchBymessageId(long messageId) {
		return fetchBymessageId(messageId, true);
	}

	/**
	 * Returns the message recipients where messageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param messageId the message ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching message recipients, or <code>null</code> if a matching message recipients could not be found
	 */
	@Override
	public MessageRecipients fetchBymessageId(
		long messageId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {messageId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchBymessageId, finderArgs, this);
		}

		if (result instanceof MessageRecipients) {
			MessageRecipients messageRecipients = (MessageRecipients)result;

			if (messageId != messageRecipients.getMessageId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_MESSAGERECIPIENTS_WHERE);

			sb.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(messageId);

				List<MessageRecipients> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchBymessageId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {messageId};
							}

							_log.warn(
								"MessageRecipientsPersistenceImpl.fetchBymessageId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					MessageRecipients messageRecipients = list.get(0);

					result = messageRecipients;

					cacheResult(messageRecipients);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (MessageRecipients)result;
		}
	}

	/**
	 * Removes the message recipients where messageId = &#63; from the database.
	 *
	 * @param messageId the message ID
	 * @return the message recipients that was removed
	 */
	@Override
	public MessageRecipients removeBymessageId(long messageId)
		throws NoSuchMessageRecipientsException {

		MessageRecipients messageRecipients = findBymessageId(messageId);

		return remove(messageRecipients);
	}

	/**
	 * Returns the number of message recipientses where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @return the number of matching message recipientses
	 */
	@Override
	public int countBymessageId(long messageId) {
		FinderPath finderPath = _finderPathCountBymessageId;

		Object[] finderArgs = new Object[] {messageId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_MESSAGERECIPIENTS_WHERE);

			sb.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(messageId);

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

	private static final String _FINDER_COLUMN_MESSAGEID_MESSAGEID_2 =
		"messageRecipients.messageId = ?";

	public MessageRecipientsPersistenceImpl() {
		setModelClass(MessageRecipients.class);

		setModelImplClass(MessageRecipientsImpl.class);
		setModelPKClass(long.class);

		setTable(MessageRecipientsTable.INSTANCE);
	}

	/**
	 * Caches the message recipients in the entity cache if it is enabled.
	 *
	 * @param messageRecipients the message recipients
	 */
	@Override
	public void cacheResult(MessageRecipients messageRecipients) {
		entityCache.putResult(
			MessageRecipientsImpl.class, messageRecipients.getPrimaryKey(),
			messageRecipients);

		finderCache.putResult(
			_finderPathFetchBymessageId,
			new Object[] {messageRecipients.getMessageId()}, messageRecipients);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the message recipientses in the entity cache if it is enabled.
	 *
	 * @param messageRecipientses the message recipientses
	 */
	@Override
	public void cacheResult(List<MessageRecipients> messageRecipientses) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (messageRecipientses.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (MessageRecipients messageRecipients : messageRecipientses) {
			if (entityCache.getResult(
					MessageRecipientsImpl.class,
					messageRecipients.getPrimaryKey()) == null) {

				cacheResult(messageRecipients);
			}
		}
	}

	/**
	 * Clears the cache for all message recipientses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MessageRecipientsImpl.class);

		finderCache.clearCache(MessageRecipientsImpl.class);
	}

	/**
	 * Clears the cache for the message recipients.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MessageRecipients messageRecipients) {
		entityCache.removeResult(
			MessageRecipientsImpl.class, messageRecipients);
	}

	@Override
	public void clearCache(List<MessageRecipients> messageRecipientses) {
		for (MessageRecipients messageRecipients : messageRecipientses) {
			entityCache.removeResult(
				MessageRecipientsImpl.class, messageRecipients);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(MessageRecipientsImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(MessageRecipientsImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		MessageRecipientsModelImpl messageRecipientsModelImpl) {

		Object[] args = new Object[] {
			messageRecipientsModelImpl.getMessageId()
		};

		finderCache.putResult(
			_finderPathCountBymessageId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchBymessageId, args, messageRecipientsModelImpl);
	}

	/**
	 * Creates a new message recipients with the primary key. Does not add the message recipients to the database.
	 *
	 * @param messageId the primary key for the new message recipients
	 * @return the new message recipients
	 */
	@Override
	public MessageRecipients create(long messageId) {
		MessageRecipients messageRecipients = new MessageRecipientsImpl();

		messageRecipients.setNew(true);
		messageRecipients.setPrimaryKey(messageId);

		return messageRecipients;
	}

	/**
	 * Removes the message recipients with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param messageId the primary key of the message recipients
	 * @return the message recipients that was removed
	 * @throws NoSuchMessageRecipientsException if a message recipients with the primary key could not be found
	 */
	@Override
	public MessageRecipients remove(long messageId)
		throws NoSuchMessageRecipientsException {

		return remove((Serializable)messageId);
	}

	/**
	 * Removes the message recipients with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the message recipients
	 * @return the message recipients that was removed
	 * @throws NoSuchMessageRecipientsException if a message recipients with the primary key could not be found
	 */
	@Override
	public MessageRecipients remove(Serializable primaryKey)
		throws NoSuchMessageRecipientsException {

		Session session = null;

		try {
			session = openSession();

			MessageRecipients messageRecipients =
				(MessageRecipients)session.get(
					MessageRecipientsImpl.class, primaryKey);

			if (messageRecipients == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMessageRecipientsException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(messageRecipients);
		}
		catch (NoSuchMessageRecipientsException noSuchEntityException) {
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
	protected MessageRecipients removeImpl(
		MessageRecipients messageRecipients) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(messageRecipients)) {
				messageRecipients = (MessageRecipients)session.get(
					MessageRecipientsImpl.class,
					messageRecipients.getPrimaryKeyObj());
			}

			if (messageRecipients != null) {
				session.delete(messageRecipients);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (messageRecipients != null) {
			clearCache(messageRecipients);
		}

		return messageRecipients;
	}

	@Override
	public MessageRecipients updateImpl(MessageRecipients messageRecipients) {
		boolean isNew = messageRecipients.isNew();

		if (!(messageRecipients instanceof MessageRecipientsModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(messageRecipients.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					messageRecipients);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in messageRecipients proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom MessageRecipients implementation " +
					messageRecipients.getClass());
		}

		MessageRecipientsModelImpl messageRecipientsModelImpl =
			(MessageRecipientsModelImpl)messageRecipients;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(messageRecipients);
			}
			else {
				messageRecipients = (MessageRecipients)session.merge(
					messageRecipients);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			MessageRecipientsImpl.class, messageRecipientsModelImpl, false,
			true);

		cacheUniqueFindersCache(messageRecipientsModelImpl);

		if (isNew) {
			messageRecipients.setNew(false);
		}

		messageRecipients.resetOriginalValues();

		return messageRecipients;
	}

	/**
	 * Returns the message recipients with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the message recipients
	 * @return the message recipients
	 * @throws NoSuchMessageRecipientsException if a message recipients with the primary key could not be found
	 */
	@Override
	public MessageRecipients findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMessageRecipientsException {

		MessageRecipients messageRecipients = fetchByPrimaryKey(primaryKey);

		if (messageRecipients == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMessageRecipientsException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return messageRecipients;
	}

	/**
	 * Returns the message recipients with the primary key or throws a <code>NoSuchMessageRecipientsException</code> if it could not be found.
	 *
	 * @param messageId the primary key of the message recipients
	 * @return the message recipients
	 * @throws NoSuchMessageRecipientsException if a message recipients with the primary key could not be found
	 */
	@Override
	public MessageRecipients findByPrimaryKey(long messageId)
		throws NoSuchMessageRecipientsException {

		return findByPrimaryKey((Serializable)messageId);
	}

	/**
	 * Returns the message recipients with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param messageId the primary key of the message recipients
	 * @return the message recipients, or <code>null</code> if a message recipients with the primary key could not be found
	 */
	@Override
	public MessageRecipients fetchByPrimaryKey(long messageId) {
		return fetchByPrimaryKey((Serializable)messageId);
	}

	/**
	 * Returns all the message recipientses.
	 *
	 * @return the message recipientses
	 */
	@Override
	public List<MessageRecipients> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<MessageRecipients> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<MessageRecipients> findAll(
		int start, int end,
		OrderByComparator<MessageRecipients> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<MessageRecipients> findAll(
		int start, int end,
		OrderByComparator<MessageRecipients> orderByComparator,
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

		List<MessageRecipients> list = null;

		if (useFinderCache) {
			list = (List<MessageRecipients>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_MESSAGERECIPIENTS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_MESSAGERECIPIENTS;

				sql = sql.concat(MessageRecipientsModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<MessageRecipients>)QueryUtil.list(
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
	 * Removes all the message recipientses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MessageRecipients messageRecipients : findAll()) {
			remove(messageRecipients);
		}
	}

	/**
	 * Returns the number of message recipientses.
	 *
	 * @return the number of message recipientses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_MESSAGERECIPIENTS);

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
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "messageId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_MESSAGERECIPIENTS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MessageRecipientsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the message recipients persistence.
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

		_finderPathFetchBymessageId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchBymessageId",
			new String[] {Long.class.getName()}, new String[] {"messageId"},
			true);

		_finderPathCountBymessageId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBymessageId",
			new String[] {Long.class.getName()}, new String[] {"messageId"},
			false);

		_setMessageRecipientsUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setMessageRecipientsUtilPersistence(null);

		entityCache.removeCache(MessageRecipientsImpl.class.getName());
	}

	private void _setMessageRecipientsUtilPersistence(
		MessageRecipientsPersistence messageRecipientsPersistence) {

		try {
			Field field = MessageRecipientsUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, messageRecipientsPersistence);
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

	private static final String _SQL_SELECT_MESSAGERECIPIENTS =
		"SELECT messageRecipients FROM MessageRecipients messageRecipients";

	private static final String _SQL_SELECT_MESSAGERECIPIENTS_WHERE =
		"SELECT messageRecipients FROM MessageRecipients messageRecipients WHERE ";

	private static final String _SQL_COUNT_MESSAGERECIPIENTS =
		"SELECT COUNT(messageRecipients) FROM MessageRecipients messageRecipients";

	private static final String _SQL_COUNT_MESSAGERECIPIENTS_WHERE =
		"SELECT COUNT(messageRecipients) FROM MessageRecipients messageRecipients WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "messageRecipients.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No MessageRecipients exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No MessageRecipients exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		MessageRecipientsPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}