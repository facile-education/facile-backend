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

import com.weprode.nero.messaging.exception.NoSuchMessageContentException;
import com.weprode.nero.messaging.model.MessageContent;
import com.weprode.nero.messaging.model.MessageContentTable;
import com.weprode.nero.messaging.model.impl.MessageContentImpl;
import com.weprode.nero.messaging.model.impl.MessageContentModelImpl;
import com.weprode.nero.messaging.service.persistence.MessageContentPersistence;
import com.weprode.nero.messaging.service.persistence.MessageContentUtil;
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
 * The persistence implementation for the message content service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {MessageContentPersistence.class, BasePersistence.class})
public class MessageContentPersistenceImpl
	extends BasePersistenceImpl<MessageContent>
	implements MessageContentPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>MessageContentUtil</code> to access the message content persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		MessageContentImpl.class.getName();

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
	 * Returns the message content where messageId = &#63; or throws a <code>NoSuchMessageContentException</code> if it could not be found.
	 *
	 * @param messageId the message ID
	 * @return the matching message content
	 * @throws NoSuchMessageContentException if a matching message content could not be found
	 */
	@Override
	public MessageContent findBymessageId(long messageId)
		throws NoSuchMessageContentException {

		MessageContent messageContent = fetchBymessageId(messageId);

		if (messageContent == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("messageId=");
			sb.append(messageId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchMessageContentException(sb.toString());
		}

		return messageContent;
	}

	/**
	 * Returns the message content where messageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param messageId the message ID
	 * @return the matching message content, or <code>null</code> if a matching message content could not be found
	 */
	@Override
	public MessageContent fetchBymessageId(long messageId) {
		return fetchBymessageId(messageId, true);
	}

	/**
	 * Returns the message content where messageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param messageId the message ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching message content, or <code>null</code> if a matching message content could not be found
	 */
	@Override
	public MessageContent fetchBymessageId(
		long messageId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {messageId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchBymessageId, finderArgs);
		}

		if (result instanceof MessageContent) {
			MessageContent messageContent = (MessageContent)result;

			if (messageId != messageContent.getMessageId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_MESSAGECONTENT_WHERE);

			sb.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(messageId);

				List<MessageContent> list = query.list();

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
								"MessageContentPersistenceImpl.fetchBymessageId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					MessageContent messageContent = list.get(0);

					result = messageContent;

					cacheResult(messageContent);
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
			return (MessageContent)result;
		}
	}

	/**
	 * Removes the message content where messageId = &#63; from the database.
	 *
	 * @param messageId the message ID
	 * @return the message content that was removed
	 */
	@Override
	public MessageContent removeBymessageId(long messageId)
		throws NoSuchMessageContentException {

		MessageContent messageContent = findBymessageId(messageId);

		return remove(messageContent);
	}

	/**
	 * Returns the number of message contents where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @return the number of matching message contents
	 */
	@Override
	public int countBymessageId(long messageId) {
		FinderPath finderPath = _finderPathCountBymessageId;

		Object[] finderArgs = new Object[] {messageId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_MESSAGECONTENT_WHERE);

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
		"messageContent.messageId = ?";

	public MessageContentPersistenceImpl() {
		setModelClass(MessageContent.class);

		setModelImplClass(MessageContentImpl.class);
		setModelPKClass(long.class);

		setTable(MessageContentTable.INSTANCE);
	}

	/**
	 * Caches the message content in the entity cache if it is enabled.
	 *
	 * @param messageContent the message content
	 */
	@Override
	public void cacheResult(MessageContent messageContent) {
		entityCache.putResult(
			MessageContentImpl.class, messageContent.getPrimaryKey(),
			messageContent);

		finderCache.putResult(
			_finderPathFetchBymessageId,
			new Object[] {messageContent.getMessageId()}, messageContent);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the message contents in the entity cache if it is enabled.
	 *
	 * @param messageContents the message contents
	 */
	@Override
	public void cacheResult(List<MessageContent> messageContents) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (messageContents.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (MessageContent messageContent : messageContents) {
			if (entityCache.getResult(
					MessageContentImpl.class, messageContent.getPrimaryKey()) ==
						null) {

				cacheResult(messageContent);
			}
		}
	}

	/**
	 * Clears the cache for all message contents.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MessageContentImpl.class);

		finderCache.clearCache(MessageContentImpl.class);
	}

	/**
	 * Clears the cache for the message content.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MessageContent messageContent) {
		entityCache.removeResult(MessageContentImpl.class, messageContent);
	}

	@Override
	public void clearCache(List<MessageContent> messageContents) {
		for (MessageContent messageContent : messageContents) {
			entityCache.removeResult(MessageContentImpl.class, messageContent);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(MessageContentImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(MessageContentImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		MessageContentModelImpl messageContentModelImpl) {

		Object[] args = new Object[] {messageContentModelImpl.getMessageId()};

		finderCache.putResult(
			_finderPathCountBymessageId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchBymessageId, args, messageContentModelImpl);
	}

	/**
	 * Creates a new message content with the primary key. Does not add the message content to the database.
	 *
	 * @param messageId the primary key for the new message content
	 * @return the new message content
	 */
	@Override
	public MessageContent create(long messageId) {
		MessageContent messageContent = new MessageContentImpl();

		messageContent.setNew(true);
		messageContent.setPrimaryKey(messageId);

		return messageContent;
	}

	/**
	 * Removes the message content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param messageId the primary key of the message content
	 * @return the message content that was removed
	 * @throws NoSuchMessageContentException if a message content with the primary key could not be found
	 */
	@Override
	public MessageContent remove(long messageId)
		throws NoSuchMessageContentException {

		return remove((Serializable)messageId);
	}

	/**
	 * Removes the message content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the message content
	 * @return the message content that was removed
	 * @throws NoSuchMessageContentException if a message content with the primary key could not be found
	 */
	@Override
	public MessageContent remove(Serializable primaryKey)
		throws NoSuchMessageContentException {

		Session session = null;

		try {
			session = openSession();

			MessageContent messageContent = (MessageContent)session.get(
				MessageContentImpl.class, primaryKey);

			if (messageContent == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMessageContentException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(messageContent);
		}
		catch (NoSuchMessageContentException noSuchEntityException) {
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
	protected MessageContent removeImpl(MessageContent messageContent) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(messageContent)) {
				messageContent = (MessageContent)session.get(
					MessageContentImpl.class,
					messageContent.getPrimaryKeyObj());
			}

			if (messageContent != null) {
				session.delete(messageContent);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (messageContent != null) {
			clearCache(messageContent);
		}

		return messageContent;
	}

	@Override
	public MessageContent updateImpl(MessageContent messageContent) {
		boolean isNew = messageContent.isNew();

		if (!(messageContent instanceof MessageContentModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(messageContent.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					messageContent);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in messageContent proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom MessageContent implementation " +
					messageContent.getClass());
		}

		MessageContentModelImpl messageContentModelImpl =
			(MessageContentModelImpl)messageContent;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(messageContent);
			}
			else {
				messageContent = (MessageContent)session.merge(messageContent);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			MessageContentImpl.class, messageContentModelImpl, false, true);

		cacheUniqueFindersCache(messageContentModelImpl);

		if (isNew) {
			messageContent.setNew(false);
		}

		messageContent.resetOriginalValues();

		return messageContent;
	}

	/**
	 * Returns the message content with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the message content
	 * @return the message content
	 * @throws NoSuchMessageContentException if a message content with the primary key could not be found
	 */
	@Override
	public MessageContent findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMessageContentException {

		MessageContent messageContent = fetchByPrimaryKey(primaryKey);

		if (messageContent == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMessageContentException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return messageContent;
	}

	/**
	 * Returns the message content with the primary key or throws a <code>NoSuchMessageContentException</code> if it could not be found.
	 *
	 * @param messageId the primary key of the message content
	 * @return the message content
	 * @throws NoSuchMessageContentException if a message content with the primary key could not be found
	 */
	@Override
	public MessageContent findByPrimaryKey(long messageId)
		throws NoSuchMessageContentException {

		return findByPrimaryKey((Serializable)messageId);
	}

	/**
	 * Returns the message content with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param messageId the primary key of the message content
	 * @return the message content, or <code>null</code> if a message content with the primary key could not be found
	 */
	@Override
	public MessageContent fetchByPrimaryKey(long messageId) {
		return fetchByPrimaryKey((Serializable)messageId);
	}

	/**
	 * Returns all the message contents.
	 *
	 * @return the message contents
	 */
	@Override
	public List<MessageContent> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<MessageContent> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<MessageContent> findAll(
		int start, int end,
		OrderByComparator<MessageContent> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<MessageContent> findAll(
		int start, int end, OrderByComparator<MessageContent> orderByComparator,
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

		List<MessageContent> list = null;

		if (useFinderCache) {
			list = (List<MessageContent>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_MESSAGECONTENT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_MESSAGECONTENT;

				sql = sql.concat(MessageContentModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<MessageContent>)QueryUtil.list(
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
	 * Removes all the message contents from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MessageContent messageContent : findAll()) {
			remove(messageContent);
		}
	}

	/**
	 * Returns the number of message contents.
	 *
	 * @return the number of message contents
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_MESSAGECONTENT);

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
		return _SQL_SELECT_MESSAGECONTENT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MessageContentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the message content persistence.
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

		_setMessageContentUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setMessageContentUtilPersistence(null);

		entityCache.removeCache(MessageContentImpl.class.getName());
	}

	private void _setMessageContentUtilPersistence(
		MessageContentPersistence messageContentPersistence) {

		try {
			Field field = MessageContentUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, messageContentPersistence);
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

	private static final String _SQL_SELECT_MESSAGECONTENT =
		"SELECT messageContent FROM MessageContent messageContent";

	private static final String _SQL_SELECT_MESSAGECONTENT_WHERE =
		"SELECT messageContent FROM MessageContent messageContent WHERE ";

	private static final String _SQL_COUNT_MESSAGECONTENT =
		"SELECT COUNT(messageContent) FROM MessageContent messageContent";

	private static final String _SQL_COUNT_MESSAGECONTENT_WHERE =
		"SELECT COUNT(messageContent) FROM MessageContent messageContent WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "messageContent.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No MessageContent exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No MessageContent exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		MessageContentPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private MessageContentModelArgumentsResolver
		_messageContentModelArgumentsResolver;

}