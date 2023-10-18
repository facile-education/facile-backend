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

package com.weprode.nero.schedule.service.persistence.impl;

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

import com.weprode.nero.schedule.exception.NoSuchSubjectException;
import com.weprode.nero.schedule.model.Subject;
import com.weprode.nero.schedule.model.SubjectTable;
import com.weprode.nero.schedule.model.impl.SubjectImpl;
import com.weprode.nero.schedule.model.impl.SubjectModelImpl;
import com.weprode.nero.schedule.service.persistence.SubjectPersistence;
import com.weprode.nero.schedule.service.persistence.SubjectUtil;
import com.weprode.nero.schedule.service.persistence.impl.constants.SchedulePersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the subject service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {SubjectPersistence.class, BasePersistence.class})
public class SubjectPersistenceImpl
	extends BasePersistenceImpl<Subject> implements SubjectPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SubjectUtil</code> to access the subject persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SubjectImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByname;
	private FinderPath _finderPathWithoutPaginationFindByname;
	private FinderPath _finderPathCountByname;

	/**
	 * Returns all the subjects where name = &#63;.
	 *
	 * @param name the name
	 * @return the matching subjects
	 */
	@Override
	public List<Subject> findByname(String name) {
		return findByname(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the subjects where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of subjects
	 * @param end the upper bound of the range of subjects (not inclusive)
	 * @return the range of matching subjects
	 */
	@Override
	public List<Subject> findByname(String name, int start, int end) {
		return findByname(name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the subjects where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of subjects
	 * @param end the upper bound of the range of subjects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching subjects
	 */
	@Override
	public List<Subject> findByname(
		String name, int start, int end,
		OrderByComparator<Subject> orderByComparator) {

		return findByname(name, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the subjects where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of subjects
	 * @param end the upper bound of the range of subjects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching subjects
	 */
	@Override
	public List<Subject> findByname(
		String name, int start, int end,
		OrderByComparator<Subject> orderByComparator, boolean useFinderCache) {

		name = Objects.toString(name, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByname;
				finderArgs = new Object[] {name};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByname;
			finderArgs = new Object[] {name, start, end, orderByComparator};
		}

		List<Subject> list = null;

		if (useFinderCache) {
			list = (List<Subject>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Subject subject : list) {
					if (!name.equals(subject.getName())) {
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

			sb.append(_SQL_SELECT_SUBJECT_WHERE);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SubjectModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindName) {
					queryPos.add(name);
				}

				list = (List<Subject>)QueryUtil.list(
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
	 * Returns the first subject in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subject
	 * @throws NoSuchSubjectException if a matching subject could not be found
	 */
	@Override
	public Subject findByname_First(
			String name, OrderByComparator<Subject> orderByComparator)
		throws NoSuchSubjectException {

		Subject subject = fetchByname_First(name, orderByComparator);

		if (subject != null) {
			return subject;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("name=");
		sb.append(name);

		sb.append("}");

		throw new NoSuchSubjectException(sb.toString());
	}

	/**
	 * Returns the first subject in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subject, or <code>null</code> if a matching subject could not be found
	 */
	@Override
	public Subject fetchByname_First(
		String name, OrderByComparator<Subject> orderByComparator) {

		List<Subject> list = findByname(name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last subject in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subject
	 * @throws NoSuchSubjectException if a matching subject could not be found
	 */
	@Override
	public Subject findByname_Last(
			String name, OrderByComparator<Subject> orderByComparator)
		throws NoSuchSubjectException {

		Subject subject = fetchByname_Last(name, orderByComparator);

		if (subject != null) {
			return subject;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("name=");
		sb.append(name);

		sb.append("}");

		throw new NoSuchSubjectException(sb.toString());
	}

	/**
	 * Returns the last subject in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subject, or <code>null</code> if a matching subject could not be found
	 */
	@Override
	public Subject fetchByname_Last(
		String name, OrderByComparator<Subject> orderByComparator) {

		int count = countByname(name);

		if (count == 0) {
			return null;
		}

		List<Subject> list = findByname(
			name, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the subjects before and after the current subject in the ordered set where name = &#63;.
	 *
	 * @param subjectId the primary key of the current subject
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next subject
	 * @throws NoSuchSubjectException if a subject with the primary key could not be found
	 */
	@Override
	public Subject[] findByname_PrevAndNext(
			long subjectId, String name,
			OrderByComparator<Subject> orderByComparator)
		throws NoSuchSubjectException {

		name = Objects.toString(name, "");

		Subject subject = findByPrimaryKey(subjectId);

		Session session = null;

		try {
			session = openSession();

			Subject[] array = new SubjectImpl[3];

			array[0] = getByname_PrevAndNext(
				session, subject, name, orderByComparator, true);

			array[1] = subject;

			array[2] = getByname_PrevAndNext(
				session, subject, name, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Subject getByname_PrevAndNext(
		Session session, Subject subject, String name,
		OrderByComparator<Subject> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SUBJECT_WHERE);

		boolean bindName = false;

		if (name.isEmpty()) {
			sb.append(_FINDER_COLUMN_NAME_NAME_3);
		}
		else {
			bindName = true;

			sb.append(_FINDER_COLUMN_NAME_NAME_2);
		}

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
			sb.append(SubjectModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindName) {
			queryPos.add(name);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(subject)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Subject> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the subjects where name = &#63; from the database.
	 *
	 * @param name the name
	 */
	@Override
	public void removeByname(String name) {
		for (Subject subject :
				findByname(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(subject);
		}
	}

	/**
	 * Returns the number of subjects where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching subjects
	 */
	@Override
	public int countByname(String name) {
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathCountByname;

		Object[] finderArgs = new Object[] {name};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SUBJECT_WHERE);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindName) {
					queryPos.add(name);
				}

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

	private static final String _FINDER_COLUMN_NAME_NAME_2 = "subject.name = ?";

	private static final String _FINDER_COLUMN_NAME_NAME_3 =
		"(subject.name IS NULL OR subject.name = '')";

	public SubjectPersistenceImpl() {
		setModelClass(Subject.class);

		setModelImplClass(SubjectImpl.class);
		setModelPKClass(long.class);

		setTable(SubjectTable.INSTANCE);
	}

	/**
	 * Caches the subject in the entity cache if it is enabled.
	 *
	 * @param subject the subject
	 */
	@Override
	public void cacheResult(Subject subject) {
		entityCache.putResult(
			SubjectImpl.class, subject.getPrimaryKey(), subject);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the subjects in the entity cache if it is enabled.
	 *
	 * @param subjects the subjects
	 */
	@Override
	public void cacheResult(List<Subject> subjects) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (subjects.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Subject subject : subjects) {
			if (entityCache.getResult(
					SubjectImpl.class, subject.getPrimaryKey()) == null) {

				cacheResult(subject);
			}
		}
	}

	/**
	 * Clears the cache for all subjects.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SubjectImpl.class);

		finderCache.clearCache(SubjectImpl.class);
	}

	/**
	 * Clears the cache for the subject.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Subject subject) {
		entityCache.removeResult(SubjectImpl.class, subject);
	}

	@Override
	public void clearCache(List<Subject> subjects) {
		for (Subject subject : subjects) {
			entityCache.removeResult(SubjectImpl.class, subject);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SubjectImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SubjectImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new subject with the primary key. Does not add the subject to the database.
	 *
	 * @param subjectId the primary key for the new subject
	 * @return the new subject
	 */
	@Override
	public Subject create(long subjectId) {
		Subject subject = new SubjectImpl();

		subject.setNew(true);
		subject.setPrimaryKey(subjectId);

		return subject;
	}

	/**
	 * Removes the subject with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param subjectId the primary key of the subject
	 * @return the subject that was removed
	 * @throws NoSuchSubjectException if a subject with the primary key could not be found
	 */
	@Override
	public Subject remove(long subjectId) throws NoSuchSubjectException {
		return remove((Serializable)subjectId);
	}

	/**
	 * Removes the subject with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the subject
	 * @return the subject that was removed
	 * @throws NoSuchSubjectException if a subject with the primary key could not be found
	 */
	@Override
	public Subject remove(Serializable primaryKey)
		throws NoSuchSubjectException {

		Session session = null;

		try {
			session = openSession();

			Subject subject = (Subject)session.get(
				SubjectImpl.class, primaryKey);

			if (subject == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSubjectException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(subject);
		}
		catch (NoSuchSubjectException noSuchEntityException) {
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
	protected Subject removeImpl(Subject subject) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(subject)) {
				subject = (Subject)session.get(
					SubjectImpl.class, subject.getPrimaryKeyObj());
			}

			if (subject != null) {
				session.delete(subject);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (subject != null) {
			clearCache(subject);
		}

		return subject;
	}

	@Override
	public Subject updateImpl(Subject subject) {
		boolean isNew = subject.isNew();

		if (!(subject instanceof SubjectModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(subject.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(subject);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in subject proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Subject implementation " +
					subject.getClass());
		}

		SubjectModelImpl subjectModelImpl = (SubjectModelImpl)subject;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(subject);
			}
			else {
				subject = (Subject)session.merge(subject);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(SubjectImpl.class, subjectModelImpl, false, true);

		if (isNew) {
			subject.setNew(false);
		}

		subject.resetOriginalValues();

		return subject;
	}

	/**
	 * Returns the subject with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the subject
	 * @return the subject
	 * @throws NoSuchSubjectException if a subject with the primary key could not be found
	 */
	@Override
	public Subject findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSubjectException {

		Subject subject = fetchByPrimaryKey(primaryKey);

		if (subject == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSubjectException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return subject;
	}

	/**
	 * Returns the subject with the primary key or throws a <code>NoSuchSubjectException</code> if it could not be found.
	 *
	 * @param subjectId the primary key of the subject
	 * @return the subject
	 * @throws NoSuchSubjectException if a subject with the primary key could not be found
	 */
	@Override
	public Subject findByPrimaryKey(long subjectId)
		throws NoSuchSubjectException {

		return findByPrimaryKey((Serializable)subjectId);
	}

	/**
	 * Returns the subject with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param subjectId the primary key of the subject
	 * @return the subject, or <code>null</code> if a subject with the primary key could not be found
	 */
	@Override
	public Subject fetchByPrimaryKey(long subjectId) {
		return fetchByPrimaryKey((Serializable)subjectId);
	}

	/**
	 * Returns all the subjects.
	 *
	 * @return the subjects
	 */
	@Override
	public List<Subject> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the subjects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subjects
	 * @param end the upper bound of the range of subjects (not inclusive)
	 * @return the range of subjects
	 */
	@Override
	public List<Subject> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the subjects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subjects
	 * @param end the upper bound of the range of subjects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of subjects
	 */
	@Override
	public List<Subject> findAll(
		int start, int end, OrderByComparator<Subject> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the subjects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subjects
	 * @param end the upper bound of the range of subjects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of subjects
	 */
	@Override
	public List<Subject> findAll(
		int start, int end, OrderByComparator<Subject> orderByComparator,
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

		List<Subject> list = null;

		if (useFinderCache) {
			list = (List<Subject>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SUBJECT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SUBJECT;

				sql = sql.concat(SubjectModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Subject>)QueryUtil.list(
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
	 * Removes all the subjects from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Subject subject : findAll()) {
			remove(subject);
		}
	}

	/**
	 * Returns the number of subjects.
	 *
	 * @return the number of subjects
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SUBJECT);

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
		return "subjectId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SUBJECT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SubjectModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the subject persistence.
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

		_finderPathWithPaginationFindByname = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByname",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"name"}, true);

		_finderPathWithoutPaginationFindByname = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByname",
			new String[] {String.class.getName()}, new String[] {"name"}, true);

		_finderPathCountByname = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByname",
			new String[] {String.class.getName()}, new String[] {"name"},
			false);

		_setSubjectUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setSubjectUtilPersistence(null);

		entityCache.removeCache(SubjectImpl.class.getName());
	}

	private void _setSubjectUtilPersistence(
		SubjectPersistence subjectPersistence) {

		try {
			Field field = SubjectUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, subjectPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = SchedulePersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = SchedulePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = SchedulePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_SUBJECT =
		"SELECT subject FROM Subject subject";

	private static final String _SQL_SELECT_SUBJECT_WHERE =
		"SELECT subject FROM Subject subject WHERE ";

	private static final String _SQL_COUNT_SUBJECT =
		"SELECT COUNT(subject) FROM Subject subject";

	private static final String _SQL_COUNT_SUBJECT_WHERE =
		"SELECT COUNT(subject) FROM Subject subject WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "subject.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Subject exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Subject exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SubjectPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}