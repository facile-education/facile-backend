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

package com.weprode.nero.about.service.persistence.impl;

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

import com.weprode.nero.about.exception.NoSuchUserReadVersionNoteException;
import com.weprode.nero.about.model.UserReadVersionNote;
import com.weprode.nero.about.model.UserReadVersionNoteTable;
import com.weprode.nero.about.model.impl.UserReadVersionNoteImpl;
import com.weprode.nero.about.model.impl.UserReadVersionNoteModelImpl;
import com.weprode.nero.about.service.persistence.UserReadVersionNotePersistence;
import com.weprode.nero.about.service.persistence.UserReadVersionNoteUtil;
import com.weprode.nero.about.service.persistence.impl.constants.AboutPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the user read version note service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	service = {UserReadVersionNotePersistence.class, BasePersistence.class}
)
public class UserReadVersionNotePersistenceImpl
	extends BasePersistenceImpl<UserReadVersionNote>
	implements UserReadVersionNotePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>UserReadVersionNoteUtil</code> to access the user read version note persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		UserReadVersionNoteImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByhasReadLastVersionNote;
	private FinderPath _finderPathWithoutPaginationFindByhasReadLastVersionNote;
	private FinderPath _finderPathCountByhasReadLastVersionNote;

	/**
	 * Returns all the user read version notes where hasReadLastVersionNote = &#63;.
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @return the matching user read version notes
	 */
	@Override
	public List<UserReadVersionNote> findByhasReadLastVersionNote(
		boolean hasReadLastVersionNote) {

		return findByhasReadLastVersionNote(
			hasReadLastVersionNote, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user read version notes where hasReadLastVersionNote = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserReadVersionNoteModelImpl</code>.
	 * </p>
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @param start the lower bound of the range of user read version notes
	 * @param end the upper bound of the range of user read version notes (not inclusive)
	 * @return the range of matching user read version notes
	 */
	@Override
	public List<UserReadVersionNote> findByhasReadLastVersionNote(
		boolean hasReadLastVersionNote, int start, int end) {

		return findByhasReadLastVersionNote(
			hasReadLastVersionNote, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user read version notes where hasReadLastVersionNote = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserReadVersionNoteModelImpl</code>.
	 * </p>
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @param start the lower bound of the range of user read version notes
	 * @param end the upper bound of the range of user read version notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user read version notes
	 */
	@Override
	public List<UserReadVersionNote> findByhasReadLastVersionNote(
		boolean hasReadLastVersionNote, int start, int end,
		OrderByComparator<UserReadVersionNote> orderByComparator) {

		return findByhasReadLastVersionNote(
			hasReadLastVersionNote, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user read version notes where hasReadLastVersionNote = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserReadVersionNoteModelImpl</code>.
	 * </p>
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @param start the lower bound of the range of user read version notes
	 * @param end the upper bound of the range of user read version notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user read version notes
	 */
	@Override
	public List<UserReadVersionNote> findByhasReadLastVersionNote(
		boolean hasReadLastVersionNote, int start, int end,
		OrderByComparator<UserReadVersionNote> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByhasReadLastVersionNote;
				finderArgs = new Object[] {hasReadLastVersionNote};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByhasReadLastVersionNote;
			finderArgs = new Object[] {
				hasReadLastVersionNote, start, end, orderByComparator
			};
		}

		List<UserReadVersionNote> list = null;

		if (useFinderCache) {
			list = (List<UserReadVersionNote>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (UserReadVersionNote userReadVersionNote : list) {
					if (hasReadLastVersionNote !=
							userReadVersionNote.isHasReadLastVersionNote()) {

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

			sb.append(_SQL_SELECT_USERREADVERSIONNOTE_WHERE);

			sb.append(
				_FINDER_COLUMN_HASREADLASTVERSIONNOTE_HASREADLASTVERSIONNOTE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(UserReadVersionNoteModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(hasReadLastVersionNote);

				list = (List<UserReadVersionNote>)QueryUtil.list(
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
	 * Returns the first user read version note in the ordered set where hasReadLastVersionNote = &#63;.
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user read version note
	 * @throws NoSuchUserReadVersionNoteException if a matching user read version note could not be found
	 */
	@Override
	public UserReadVersionNote findByhasReadLastVersionNote_First(
			boolean hasReadLastVersionNote,
			OrderByComparator<UserReadVersionNote> orderByComparator)
		throws NoSuchUserReadVersionNoteException {

		UserReadVersionNote userReadVersionNote =
			fetchByhasReadLastVersionNote_First(
				hasReadLastVersionNote, orderByComparator);

		if (userReadVersionNote != null) {
			return userReadVersionNote;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("hasReadLastVersionNote=");
		sb.append(hasReadLastVersionNote);

		sb.append("}");

		throw new NoSuchUserReadVersionNoteException(sb.toString());
	}

	/**
	 * Returns the first user read version note in the ordered set where hasReadLastVersionNote = &#63;.
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user read version note, or <code>null</code> if a matching user read version note could not be found
	 */
	@Override
	public UserReadVersionNote fetchByhasReadLastVersionNote_First(
		boolean hasReadLastVersionNote,
		OrderByComparator<UserReadVersionNote> orderByComparator) {

		List<UserReadVersionNote> list = findByhasReadLastVersionNote(
			hasReadLastVersionNote, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user read version note in the ordered set where hasReadLastVersionNote = &#63;.
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user read version note
	 * @throws NoSuchUserReadVersionNoteException if a matching user read version note could not be found
	 */
	@Override
	public UserReadVersionNote findByhasReadLastVersionNote_Last(
			boolean hasReadLastVersionNote,
			OrderByComparator<UserReadVersionNote> orderByComparator)
		throws NoSuchUserReadVersionNoteException {

		UserReadVersionNote userReadVersionNote =
			fetchByhasReadLastVersionNote_Last(
				hasReadLastVersionNote, orderByComparator);

		if (userReadVersionNote != null) {
			return userReadVersionNote;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("hasReadLastVersionNote=");
		sb.append(hasReadLastVersionNote);

		sb.append("}");

		throw new NoSuchUserReadVersionNoteException(sb.toString());
	}

	/**
	 * Returns the last user read version note in the ordered set where hasReadLastVersionNote = &#63;.
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user read version note, or <code>null</code> if a matching user read version note could not be found
	 */
	@Override
	public UserReadVersionNote fetchByhasReadLastVersionNote_Last(
		boolean hasReadLastVersionNote,
		OrderByComparator<UserReadVersionNote> orderByComparator) {

		int count = countByhasReadLastVersionNote(hasReadLastVersionNote);

		if (count == 0) {
			return null;
		}

		List<UserReadVersionNote> list = findByhasReadLastVersionNote(
			hasReadLastVersionNote, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user read version notes before and after the current user read version note in the ordered set where hasReadLastVersionNote = &#63;.
	 *
	 * @param userId the primary key of the current user read version note
	 * @param hasReadLastVersionNote the has read last version note
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user read version note
	 * @throws NoSuchUserReadVersionNoteException if a user read version note with the primary key could not be found
	 */
	@Override
	public UserReadVersionNote[] findByhasReadLastVersionNote_PrevAndNext(
			long userId, boolean hasReadLastVersionNote,
			OrderByComparator<UserReadVersionNote> orderByComparator)
		throws NoSuchUserReadVersionNoteException {

		UserReadVersionNote userReadVersionNote = findByPrimaryKey(userId);

		Session session = null;

		try {
			session = openSession();

			UserReadVersionNote[] array = new UserReadVersionNoteImpl[3];

			array[0] = getByhasReadLastVersionNote_PrevAndNext(
				session, userReadVersionNote, hasReadLastVersionNote,
				orderByComparator, true);

			array[1] = userReadVersionNote;

			array[2] = getByhasReadLastVersionNote_PrevAndNext(
				session, userReadVersionNote, hasReadLastVersionNote,
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

	protected UserReadVersionNote getByhasReadLastVersionNote_PrevAndNext(
		Session session, UserReadVersionNote userReadVersionNote,
		boolean hasReadLastVersionNote,
		OrderByComparator<UserReadVersionNote> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_USERREADVERSIONNOTE_WHERE);

		sb.append(
			_FINDER_COLUMN_HASREADLASTVERSIONNOTE_HASREADLASTVERSIONNOTE_2);

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
			sb.append(UserReadVersionNoteModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(hasReadLastVersionNote);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						userReadVersionNote)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<UserReadVersionNote> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user read version notes where hasReadLastVersionNote = &#63; from the database.
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 */
	@Override
	public void removeByhasReadLastVersionNote(boolean hasReadLastVersionNote) {
		for (UserReadVersionNote userReadVersionNote :
				findByhasReadLastVersionNote(
					hasReadLastVersionNote, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(userReadVersionNote);
		}
	}

	/**
	 * Returns the number of user read version notes where hasReadLastVersionNote = &#63;.
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @return the number of matching user read version notes
	 */
	@Override
	public int countByhasReadLastVersionNote(boolean hasReadLastVersionNote) {
		FinderPath finderPath = _finderPathCountByhasReadLastVersionNote;

		Object[] finderArgs = new Object[] {hasReadLastVersionNote};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_USERREADVERSIONNOTE_WHERE);

			sb.append(
				_FINDER_COLUMN_HASREADLASTVERSIONNOTE_HASREADLASTVERSIONNOTE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(hasReadLastVersionNote);

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

	private static final String
		_FINDER_COLUMN_HASREADLASTVERSIONNOTE_HASREADLASTVERSIONNOTE_2 =
			"userReadVersionNote.hasReadLastVersionNote = ?";

	public UserReadVersionNotePersistenceImpl() {
		setModelClass(UserReadVersionNote.class);

		setModelImplClass(UserReadVersionNoteImpl.class);
		setModelPKClass(long.class);

		setTable(UserReadVersionNoteTable.INSTANCE);
	}

	/**
	 * Caches the user read version note in the entity cache if it is enabled.
	 *
	 * @param userReadVersionNote the user read version note
	 */
	@Override
	public void cacheResult(UserReadVersionNote userReadVersionNote) {
		entityCache.putResult(
			UserReadVersionNoteImpl.class, userReadVersionNote.getPrimaryKey(),
			userReadVersionNote);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the user read version notes in the entity cache if it is enabled.
	 *
	 * @param userReadVersionNotes the user read version notes
	 */
	@Override
	public void cacheResult(List<UserReadVersionNote> userReadVersionNotes) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (userReadVersionNotes.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (UserReadVersionNote userReadVersionNote : userReadVersionNotes) {
			if (entityCache.getResult(
					UserReadVersionNoteImpl.class,
					userReadVersionNote.getPrimaryKey()) == null) {

				cacheResult(userReadVersionNote);
			}
		}
	}

	/**
	 * Clears the cache for all user read version notes.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UserReadVersionNoteImpl.class);

		finderCache.clearCache(UserReadVersionNoteImpl.class);
	}

	/**
	 * Clears the cache for the user read version note.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UserReadVersionNote userReadVersionNote) {
		entityCache.removeResult(
			UserReadVersionNoteImpl.class, userReadVersionNote);
	}

	@Override
	public void clearCache(List<UserReadVersionNote> userReadVersionNotes) {
		for (UserReadVersionNote userReadVersionNote : userReadVersionNotes) {
			entityCache.removeResult(
				UserReadVersionNoteImpl.class, userReadVersionNote);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(UserReadVersionNoteImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(UserReadVersionNoteImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new user read version note with the primary key. Does not add the user read version note to the database.
	 *
	 * @param userId the primary key for the new user read version note
	 * @return the new user read version note
	 */
	@Override
	public UserReadVersionNote create(long userId) {
		UserReadVersionNote userReadVersionNote = new UserReadVersionNoteImpl();

		userReadVersionNote.setNew(true);
		userReadVersionNote.setPrimaryKey(userId);

		return userReadVersionNote;
	}

	/**
	 * Removes the user read version note with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userId the primary key of the user read version note
	 * @return the user read version note that was removed
	 * @throws NoSuchUserReadVersionNoteException if a user read version note with the primary key could not be found
	 */
	@Override
	public UserReadVersionNote remove(long userId)
		throws NoSuchUserReadVersionNoteException {

		return remove((Serializable)userId);
	}

	/**
	 * Removes the user read version note with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user read version note
	 * @return the user read version note that was removed
	 * @throws NoSuchUserReadVersionNoteException if a user read version note with the primary key could not be found
	 */
	@Override
	public UserReadVersionNote remove(Serializable primaryKey)
		throws NoSuchUserReadVersionNoteException {

		Session session = null;

		try {
			session = openSession();

			UserReadVersionNote userReadVersionNote =
				(UserReadVersionNote)session.get(
					UserReadVersionNoteImpl.class, primaryKey);

			if (userReadVersionNote == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserReadVersionNoteException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(userReadVersionNote);
		}
		catch (NoSuchUserReadVersionNoteException noSuchEntityException) {
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
	protected UserReadVersionNote removeImpl(
		UserReadVersionNote userReadVersionNote) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(userReadVersionNote)) {
				userReadVersionNote = (UserReadVersionNote)session.get(
					UserReadVersionNoteImpl.class,
					userReadVersionNote.getPrimaryKeyObj());
			}

			if (userReadVersionNote != null) {
				session.delete(userReadVersionNote);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (userReadVersionNote != null) {
			clearCache(userReadVersionNote);
		}

		return userReadVersionNote;
	}

	@Override
	public UserReadVersionNote updateImpl(
		UserReadVersionNote userReadVersionNote) {

		boolean isNew = userReadVersionNote.isNew();

		if (!(userReadVersionNote instanceof UserReadVersionNoteModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(userReadVersionNote.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					userReadVersionNote);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in userReadVersionNote proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom UserReadVersionNote implementation " +
					userReadVersionNote.getClass());
		}

		UserReadVersionNoteModelImpl userReadVersionNoteModelImpl =
			(UserReadVersionNoteModelImpl)userReadVersionNote;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(userReadVersionNote);
			}
			else {
				userReadVersionNote = (UserReadVersionNote)session.merge(
					userReadVersionNote);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			UserReadVersionNoteImpl.class, userReadVersionNoteModelImpl, false,
			true);

		if (isNew) {
			userReadVersionNote.setNew(false);
		}

		userReadVersionNote.resetOriginalValues();

		return userReadVersionNote;
	}

	/**
	 * Returns the user read version note with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user read version note
	 * @return the user read version note
	 * @throws NoSuchUserReadVersionNoteException if a user read version note with the primary key could not be found
	 */
	@Override
	public UserReadVersionNote findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUserReadVersionNoteException {

		UserReadVersionNote userReadVersionNote = fetchByPrimaryKey(primaryKey);

		if (userReadVersionNote == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUserReadVersionNoteException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return userReadVersionNote;
	}

	/**
	 * Returns the user read version note with the primary key or throws a <code>NoSuchUserReadVersionNoteException</code> if it could not be found.
	 *
	 * @param userId the primary key of the user read version note
	 * @return the user read version note
	 * @throws NoSuchUserReadVersionNoteException if a user read version note with the primary key could not be found
	 */
	@Override
	public UserReadVersionNote findByPrimaryKey(long userId)
		throws NoSuchUserReadVersionNoteException {

		return findByPrimaryKey((Serializable)userId);
	}

	/**
	 * Returns the user read version note with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userId the primary key of the user read version note
	 * @return the user read version note, or <code>null</code> if a user read version note with the primary key could not be found
	 */
	@Override
	public UserReadVersionNote fetchByPrimaryKey(long userId) {
		return fetchByPrimaryKey((Serializable)userId);
	}

	/**
	 * Returns all the user read version notes.
	 *
	 * @return the user read version notes
	 */
	@Override
	public List<UserReadVersionNote> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user read version notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserReadVersionNoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user read version notes
	 * @param end the upper bound of the range of user read version notes (not inclusive)
	 * @return the range of user read version notes
	 */
	@Override
	public List<UserReadVersionNote> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the user read version notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserReadVersionNoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user read version notes
	 * @param end the upper bound of the range of user read version notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user read version notes
	 */
	@Override
	public List<UserReadVersionNote> findAll(
		int start, int end,
		OrderByComparator<UserReadVersionNote> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user read version notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserReadVersionNoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user read version notes
	 * @param end the upper bound of the range of user read version notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user read version notes
	 */
	@Override
	public List<UserReadVersionNote> findAll(
		int start, int end,
		OrderByComparator<UserReadVersionNote> orderByComparator,
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

		List<UserReadVersionNote> list = null;

		if (useFinderCache) {
			list = (List<UserReadVersionNote>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_USERREADVERSIONNOTE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_USERREADVERSIONNOTE;

				sql = sql.concat(UserReadVersionNoteModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<UserReadVersionNote>)QueryUtil.list(
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
	 * Removes all the user read version notes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UserReadVersionNote userReadVersionNote : findAll()) {
			remove(userReadVersionNote);
		}
	}

	/**
	 * Returns the number of user read version notes.
	 *
	 * @return the number of user read version notes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_USERREADVERSIONNOTE);

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
		return "userId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_USERREADVERSIONNOTE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return UserReadVersionNoteModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the user read version note persistence.
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

		_finderPathWithPaginationFindByhasReadLastVersionNote = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByhasReadLastVersionNote",
			new String[] {
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"hasReadLastVersionNote"}, true);

		_finderPathWithoutPaginationFindByhasReadLastVersionNote =
			new FinderPath(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByhasReadLastVersionNote",
				new String[] {Boolean.class.getName()},
				new String[] {"hasReadLastVersionNote"}, true);

		_finderPathCountByhasReadLastVersionNote = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByhasReadLastVersionNote",
			new String[] {Boolean.class.getName()},
			new String[] {"hasReadLastVersionNote"}, false);

		_setUserReadVersionNoteUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setUserReadVersionNoteUtilPersistence(null);

		entityCache.removeCache(UserReadVersionNoteImpl.class.getName());
	}

	private void _setUserReadVersionNoteUtilPersistence(
		UserReadVersionNotePersistence userReadVersionNotePersistence) {

		try {
			Field field = UserReadVersionNoteUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, userReadVersionNotePersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = AboutPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = AboutPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = AboutPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_USERREADVERSIONNOTE =
		"SELECT userReadVersionNote FROM UserReadVersionNote userReadVersionNote";

	private static final String _SQL_SELECT_USERREADVERSIONNOTE_WHERE =
		"SELECT userReadVersionNote FROM UserReadVersionNote userReadVersionNote WHERE ";

	private static final String _SQL_COUNT_USERREADVERSIONNOTE =
		"SELECT COUNT(userReadVersionNote) FROM UserReadVersionNote userReadVersionNote";

	private static final String _SQL_COUNT_USERREADVERSIONNOTE_WHERE =
		"SELECT COUNT(userReadVersionNote) FROM UserReadVersionNote userReadVersionNote WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "userReadVersionNote.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No UserReadVersionNote exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No UserReadVersionNote exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		UserReadVersionNotePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private UserReadVersionNoteModelArgumentsResolver
		_userReadVersionNoteModelArgumentsResolver;

}