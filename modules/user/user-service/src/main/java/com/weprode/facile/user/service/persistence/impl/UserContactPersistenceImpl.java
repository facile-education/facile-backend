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

package com.weprode.facile.user.service.persistence.impl;

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

import com.weprode.facile.user.exception.NoSuchContactException;
import com.weprode.facile.user.model.UserContact;
import com.weprode.facile.user.model.UserContactTable;
import com.weprode.facile.user.model.impl.UserContactImpl;
import com.weprode.facile.user.model.impl.UserContactModelImpl;
import com.weprode.facile.user.service.persistence.UserContactPersistence;
import com.weprode.facile.user.service.persistence.UserContactUtil;
import com.weprode.facile.user.service.persistence.impl.constants.UserPersistenceConstants;

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
 * The persistence implementation for the user contact service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {UserContactPersistence.class, BasePersistence.class})
public class UserContactPersistenceImpl
	extends BasePersistenceImpl<UserContact> implements UserContactPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>UserContactUtil</code> to access the user contact persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		UserContactImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByuserId;
	private FinderPath _finderPathCountByuserId;

	/**
	 * Returns the user contact where userId = &#63; or throws a <code>NoSuchContactException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching user contact
	 * @throws NoSuchContactException if a matching user contact could not be found
	 */
	@Override
	public UserContact findByuserId(long userId) throws NoSuchContactException {
		UserContact userContact = fetchByuserId(userId);

		if (userContact == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchContactException(sb.toString());
		}

		return userContact;
	}

	/**
	 * Returns the user contact where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching user contact, or <code>null</code> if a matching user contact could not be found
	 */
	@Override
	public UserContact fetchByuserId(long userId) {
		return fetchByuserId(userId, true);
	}

	/**
	 * Returns the user contact where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching user contact, or <code>null</code> if a matching user contact could not be found
	 */
	@Override
	public UserContact fetchByuserId(long userId, boolean useFinderCache) {
		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByuserId, finderArgs, this);
		}

		if (result instanceof UserContact) {
			UserContact userContact = (UserContact)result;

			if (userId != userContact.getUserId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_USERCONTACT_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				List<UserContact> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByuserId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {userId};
							}

							_log.warn(
								"UserContactPersistenceImpl.fetchByuserId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					UserContact userContact = list.get(0);

					result = userContact;

					cacheResult(userContact);
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
			return (UserContact)result;
		}
	}

	/**
	 * Removes the user contact where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the user contact that was removed
	 */
	@Override
	public UserContact removeByuserId(long userId)
		throws NoSuchContactException {

		UserContact userContact = findByuserId(userId);

		return remove(userContact);
	}

	/**
	 * Returns the number of user contacts where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching user contacts
	 */
	@Override
	public int countByuserId(long userId) {
		FinderPath finderPath = _finderPathCountByuserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_USERCONTACT_WHERE);

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
		"userContact.userId = ?";

	public UserContactPersistenceImpl() {
		setModelClass(UserContact.class);

		setModelImplClass(UserContactImpl.class);
		setModelPKClass(long.class);

		setTable(UserContactTable.INSTANCE);
	}

	/**
	 * Caches the user contact in the entity cache if it is enabled.
	 *
	 * @param userContact the user contact
	 */
	@Override
	public void cacheResult(UserContact userContact) {
		entityCache.putResult(
			UserContactImpl.class, userContact.getPrimaryKey(), userContact);

		finderCache.putResult(
			_finderPathFetchByuserId, new Object[] {userContact.getUserId()},
			userContact);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the user contacts in the entity cache if it is enabled.
	 *
	 * @param userContacts the user contacts
	 */
	@Override
	public void cacheResult(List<UserContact> userContacts) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (userContacts.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (UserContact userContact : userContacts) {
			if (entityCache.getResult(
					UserContactImpl.class, userContact.getPrimaryKey()) ==
						null) {

				cacheResult(userContact);
			}
		}
	}

	/**
	 * Clears the cache for all user contacts.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UserContactImpl.class);

		finderCache.clearCache(UserContactImpl.class);
	}

	/**
	 * Clears the cache for the user contact.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UserContact userContact) {
		entityCache.removeResult(UserContactImpl.class, userContact);
	}

	@Override
	public void clearCache(List<UserContact> userContacts) {
		for (UserContact userContact : userContacts) {
			entityCache.removeResult(UserContactImpl.class, userContact);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(UserContactImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(UserContactImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		UserContactModelImpl userContactModelImpl) {

		Object[] args = new Object[] {userContactModelImpl.getUserId()};

		finderCache.putResult(_finderPathCountByuserId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByuserId, args, userContactModelImpl);
	}

	/**
	 * Creates a new user contact with the primary key. Does not add the user contact to the database.
	 *
	 * @param contactId the primary key for the new user contact
	 * @return the new user contact
	 */
	@Override
	public UserContact create(long contactId) {
		UserContact userContact = new UserContactImpl();

		userContact.setNew(true);
		userContact.setPrimaryKey(contactId);

		return userContact;
	}

	/**
	 * Removes the user contact with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contactId the primary key of the user contact
	 * @return the user contact that was removed
	 * @throws NoSuchContactException if a user contact with the primary key could not be found
	 */
	@Override
	public UserContact remove(long contactId) throws NoSuchContactException {
		return remove((Serializable)contactId);
	}

	/**
	 * Removes the user contact with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user contact
	 * @return the user contact that was removed
	 * @throws NoSuchContactException if a user contact with the primary key could not be found
	 */
	@Override
	public UserContact remove(Serializable primaryKey)
		throws NoSuchContactException {

		Session session = null;

		try {
			session = openSession();

			UserContact userContact = (UserContact)session.get(
				UserContactImpl.class, primaryKey);

			if (userContact == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchContactException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(userContact);
		}
		catch (NoSuchContactException noSuchEntityException) {
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
	protected UserContact removeImpl(UserContact userContact) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(userContact)) {
				userContact = (UserContact)session.get(
					UserContactImpl.class, userContact.getPrimaryKeyObj());
			}

			if (userContact != null) {
				session.delete(userContact);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (userContact != null) {
			clearCache(userContact);
		}

		return userContact;
	}

	@Override
	public UserContact updateImpl(UserContact userContact) {
		boolean isNew = userContact.isNew();

		if (!(userContact instanceof UserContactModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(userContact.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(userContact);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in userContact proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom UserContact implementation " +
					userContact.getClass());
		}

		UserContactModelImpl userContactModelImpl =
			(UserContactModelImpl)userContact;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(userContact);
			}
			else {
				userContact = (UserContact)session.merge(userContact);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			UserContactImpl.class, userContactModelImpl, false, true);

		cacheUniqueFindersCache(userContactModelImpl);

		if (isNew) {
			userContact.setNew(false);
		}

		userContact.resetOriginalValues();

		return userContact;
	}

	/**
	 * Returns the user contact with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user contact
	 * @return the user contact
	 * @throws NoSuchContactException if a user contact with the primary key could not be found
	 */
	@Override
	public UserContact findByPrimaryKey(Serializable primaryKey)
		throws NoSuchContactException {

		UserContact userContact = fetchByPrimaryKey(primaryKey);

		if (userContact == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchContactException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return userContact;
	}

	/**
	 * Returns the user contact with the primary key or throws a <code>NoSuchContactException</code> if it could not be found.
	 *
	 * @param contactId the primary key of the user contact
	 * @return the user contact
	 * @throws NoSuchContactException if a user contact with the primary key could not be found
	 */
	@Override
	public UserContact findByPrimaryKey(long contactId)
		throws NoSuchContactException {

		return findByPrimaryKey((Serializable)contactId);
	}

	/**
	 * Returns the user contact with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contactId the primary key of the user contact
	 * @return the user contact, or <code>null</code> if a user contact with the primary key could not be found
	 */
	@Override
	public UserContact fetchByPrimaryKey(long contactId) {
		return fetchByPrimaryKey((Serializable)contactId);
	}

	/**
	 * Returns all the user contacts.
	 *
	 * @return the user contacts
	 */
	@Override
	public List<UserContact> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user contacts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserContactModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user contacts
	 * @param end the upper bound of the range of user contacts (not inclusive)
	 * @return the range of user contacts
	 */
	@Override
	public List<UserContact> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the user contacts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserContactModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user contacts
	 * @param end the upper bound of the range of user contacts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user contacts
	 */
	@Override
	public List<UserContact> findAll(
		int start, int end, OrderByComparator<UserContact> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user contacts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserContactModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user contacts
	 * @param end the upper bound of the range of user contacts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user contacts
	 */
	@Override
	public List<UserContact> findAll(
		int start, int end, OrderByComparator<UserContact> orderByComparator,
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

		List<UserContact> list = null;

		if (useFinderCache) {
			list = (List<UserContact>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_USERCONTACT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_USERCONTACT;

				sql = sql.concat(UserContactModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<UserContact>)QueryUtil.list(
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
	 * Removes all the user contacts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UserContact userContact : findAll()) {
			remove(userContact);
		}
	}

	/**
	 * Returns the number of user contacts.
	 *
	 * @return the number of user contacts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_USERCONTACT);

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
		return "contactId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_USERCONTACT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return UserContactModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the user contact persistence.
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

		_finderPathFetchByuserId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByuserId",
			new String[] {Long.class.getName()}, new String[] {"userId"}, true);

		_finderPathCountByuserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByuserId",
			new String[] {Long.class.getName()}, new String[] {"userId"},
			false);

		_setUserContactUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setUserContactUtilPersistence(null);

		entityCache.removeCache(UserContactImpl.class.getName());
	}

	private void _setUserContactUtilPersistence(
		UserContactPersistence userContactPersistence) {

		try {
			Field field = UserContactUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, userContactPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = UserPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = UserPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = UserPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_USERCONTACT =
		"SELECT userContact FROM UserContact userContact";

	private static final String _SQL_SELECT_USERCONTACT_WHERE =
		"SELECT userContact FROM UserContact userContact WHERE ";

	private static final String _SQL_COUNT_USERCONTACT =
		"SELECT COUNT(userContact) FROM UserContact userContact";

	private static final String _SQL_COUNT_USERCONTACT_WHERE =
		"SELECT COUNT(userContact) FROM UserContact userContact WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "userContact.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No UserContact exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No UserContact exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		UserContactPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}