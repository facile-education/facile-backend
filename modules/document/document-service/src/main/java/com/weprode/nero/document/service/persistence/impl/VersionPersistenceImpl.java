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

package com.weprode.nero.document.service.persistence.impl;

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
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.nero.document.exception.NoSuchVersionException;
import com.weprode.nero.document.model.Version;
import com.weprode.nero.document.model.VersionTable;
import com.weprode.nero.document.model.impl.VersionImpl;
import com.weprode.nero.document.model.impl.VersionModelImpl;
import com.weprode.nero.document.service.persistence.VersionPersistence;
import com.weprode.nero.document.service.persistence.VersionUtil;
import com.weprode.nero.document.service.persistence.impl.constants.DocumentPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
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
 * The persistence implementation for the version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {VersionPersistence.class, BasePersistence.class})
public class VersionPersistenceImpl
	extends BasePersistenceImpl<Version> implements VersionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>VersionUtil</code> to access the version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		VersionImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchBydlFileEntryId_versionNumber;
	private FinderPath _finderPathCountBydlFileEntryId_versionNumber;

	/**
	 * Returns the version where dlFileEntryId = &#63; and versionNumber = &#63; or throws a <code>NoSuchVersionException</code> if it could not be found.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param versionNumber the version number
	 * @return the matching version
	 * @throws NoSuchVersionException if a matching version could not be found
	 */
	@Override
	public Version findBydlFileEntryId_versionNumber(
			long dlFileEntryId, double versionNumber)
		throws NoSuchVersionException {

		Version version = fetchBydlFileEntryId_versionNumber(
			dlFileEntryId, versionNumber);

		if (version == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("dlFileEntryId=");
			sb.append(dlFileEntryId);

			sb.append(", versionNumber=");
			sb.append(versionNumber);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchVersionException(sb.toString());
		}

		return version;
	}

	/**
	 * Returns the version where dlFileEntryId = &#63; and versionNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param versionNumber the version number
	 * @return the matching version, or <code>null</code> if a matching version could not be found
	 */
	@Override
	public Version fetchBydlFileEntryId_versionNumber(
		long dlFileEntryId, double versionNumber) {

		return fetchBydlFileEntryId_versionNumber(
			dlFileEntryId, versionNumber, true);
	}

	/**
	 * Returns the version where dlFileEntryId = &#63; and versionNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param versionNumber the version number
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching version, or <code>null</code> if a matching version could not be found
	 */
	@Override
	public Version fetchBydlFileEntryId_versionNumber(
		long dlFileEntryId, double versionNumber, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {dlFileEntryId, versionNumber};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchBydlFileEntryId_versionNumber, finderArgs);
		}

		if (result instanceof Version) {
			Version version = (Version)result;

			if ((dlFileEntryId != version.getDlFileEntryId()) ||
				(versionNumber != version.getVersionNumber())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_VERSION_WHERE);

			sb.append(
				_FINDER_COLUMN_DLFILEENTRYID_VERSIONNUMBER_DLFILEENTRYID_2);

			sb.append(
				_FINDER_COLUMN_DLFILEENTRYID_VERSIONNUMBER_VERSIONNUMBER_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(dlFileEntryId);

				queryPos.add(versionNumber);

				List<Version> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchBydlFileEntryId_versionNumber,
							finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									dlFileEntryId, versionNumber
								};
							}

							_log.warn(
								"VersionPersistenceImpl.fetchBydlFileEntryId_versionNumber(long, double, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Version version = list.get(0);

					result = version;

					cacheResult(version);
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
			return (Version)result;
		}
	}

	/**
	 * Removes the version where dlFileEntryId = &#63; and versionNumber = &#63; from the database.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param versionNumber the version number
	 * @return the version that was removed
	 */
	@Override
	public Version removeBydlFileEntryId_versionNumber(
			long dlFileEntryId, double versionNumber)
		throws NoSuchVersionException {

		Version version = findBydlFileEntryId_versionNumber(
			dlFileEntryId, versionNumber);

		return remove(version);
	}

	/**
	 * Returns the number of versions where dlFileEntryId = &#63; and versionNumber = &#63;.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param versionNumber the version number
	 * @return the number of matching versions
	 */
	@Override
	public int countBydlFileEntryId_versionNumber(
		long dlFileEntryId, double versionNumber) {

		FinderPath finderPath = _finderPathCountBydlFileEntryId_versionNumber;

		Object[] finderArgs = new Object[] {dlFileEntryId, versionNumber};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_VERSION_WHERE);

			sb.append(
				_FINDER_COLUMN_DLFILEENTRYID_VERSIONNUMBER_DLFILEENTRYID_2);

			sb.append(
				_FINDER_COLUMN_DLFILEENTRYID_VERSIONNUMBER_VERSIONNUMBER_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(dlFileEntryId);

				queryPos.add(versionNumber);

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
		_FINDER_COLUMN_DLFILEENTRYID_VERSIONNUMBER_DLFILEENTRYID_2 =
			"version.dlFileEntryId = ? AND ";

	private static final String
		_FINDER_COLUMN_DLFILEENTRYID_VERSIONNUMBER_VERSIONNUMBER_2 =
			"version.versionNumber = ?";

	private FinderPath _finderPathWithPaginationFindBydlFileEntryId;
	private FinderPath _finderPathWithoutPaginationFindBydlFileEntryId;
	private FinderPath _finderPathCountBydlFileEntryId;

	/**
	 * Returns all the versions where dlFileEntryId = &#63;.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @return the matching versions
	 */
	@Override
	public List<Version> findBydlFileEntryId(long dlFileEntryId) {
		return findBydlFileEntryId(
			dlFileEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the versions where dlFileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VersionModelImpl</code>.
	 * </p>
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param start the lower bound of the range of versions
	 * @param end the upper bound of the range of versions (not inclusive)
	 * @return the range of matching versions
	 */
	@Override
	public List<Version> findBydlFileEntryId(
		long dlFileEntryId, int start, int end) {

		return findBydlFileEntryId(dlFileEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the versions where dlFileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VersionModelImpl</code>.
	 * </p>
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param start the lower bound of the range of versions
	 * @param end the upper bound of the range of versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching versions
	 */
	@Override
	public List<Version> findBydlFileEntryId(
		long dlFileEntryId, int start, int end,
		OrderByComparator<Version> orderByComparator) {

		return findBydlFileEntryId(
			dlFileEntryId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the versions where dlFileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VersionModelImpl</code>.
	 * </p>
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param start the lower bound of the range of versions
	 * @param end the upper bound of the range of versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching versions
	 */
	@Override
	public List<Version> findBydlFileEntryId(
		long dlFileEntryId, int start, int end,
		OrderByComparator<Version> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBydlFileEntryId;
				finderArgs = new Object[] {dlFileEntryId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBydlFileEntryId;
			finderArgs = new Object[] {
				dlFileEntryId, start, end, orderByComparator
			};
		}

		List<Version> list = null;

		if (useFinderCache) {
			list = (List<Version>)finderCache.getResult(finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (Version version : list) {
					if (dlFileEntryId != version.getDlFileEntryId()) {
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

			sb.append(_SQL_SELECT_VERSION_WHERE);

			sb.append(_FINDER_COLUMN_DLFILEENTRYID_DLFILEENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(VersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(dlFileEntryId);

				list = (List<Version>)QueryUtil.list(
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
	 * Returns the first version in the ordered set where dlFileEntryId = &#63;.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching version
	 * @throws NoSuchVersionException if a matching version could not be found
	 */
	@Override
	public Version findBydlFileEntryId_First(
			long dlFileEntryId, OrderByComparator<Version> orderByComparator)
		throws NoSuchVersionException {

		Version version = fetchBydlFileEntryId_First(
			dlFileEntryId, orderByComparator);

		if (version != null) {
			return version;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("dlFileEntryId=");
		sb.append(dlFileEntryId);

		sb.append("}");

		throw new NoSuchVersionException(sb.toString());
	}

	/**
	 * Returns the first version in the ordered set where dlFileEntryId = &#63;.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching version, or <code>null</code> if a matching version could not be found
	 */
	@Override
	public Version fetchBydlFileEntryId_First(
		long dlFileEntryId, OrderByComparator<Version> orderByComparator) {

		List<Version> list = findBydlFileEntryId(
			dlFileEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last version in the ordered set where dlFileEntryId = &#63;.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching version
	 * @throws NoSuchVersionException if a matching version could not be found
	 */
	@Override
	public Version findBydlFileEntryId_Last(
			long dlFileEntryId, OrderByComparator<Version> orderByComparator)
		throws NoSuchVersionException {

		Version version = fetchBydlFileEntryId_Last(
			dlFileEntryId, orderByComparator);

		if (version != null) {
			return version;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("dlFileEntryId=");
		sb.append(dlFileEntryId);

		sb.append("}");

		throw new NoSuchVersionException(sb.toString());
	}

	/**
	 * Returns the last version in the ordered set where dlFileEntryId = &#63;.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching version, or <code>null</code> if a matching version could not be found
	 */
	@Override
	public Version fetchBydlFileEntryId_Last(
		long dlFileEntryId, OrderByComparator<Version> orderByComparator) {

		int count = countBydlFileEntryId(dlFileEntryId);

		if (count == 0) {
			return null;
		}

		List<Version> list = findBydlFileEntryId(
			dlFileEntryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the versions before and after the current version in the ordered set where dlFileEntryId = &#63;.
	 *
	 * @param fileVersionId the primary key of the current version
	 * @param dlFileEntryId the dl file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next version
	 * @throws NoSuchVersionException if a version with the primary key could not be found
	 */
	@Override
	public Version[] findBydlFileEntryId_PrevAndNext(
			long fileVersionId, long dlFileEntryId,
			OrderByComparator<Version> orderByComparator)
		throws NoSuchVersionException {

		Version version = findByPrimaryKey(fileVersionId);

		Session session = null;

		try {
			session = openSession();

			Version[] array = new VersionImpl[3];

			array[0] = getBydlFileEntryId_PrevAndNext(
				session, version, dlFileEntryId, orderByComparator, true);

			array[1] = version;

			array[2] = getBydlFileEntryId_PrevAndNext(
				session, version, dlFileEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Version getBydlFileEntryId_PrevAndNext(
		Session session, Version version, long dlFileEntryId,
		OrderByComparator<Version> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_VERSION_WHERE);

		sb.append(_FINDER_COLUMN_DLFILEENTRYID_DLFILEENTRYID_2);

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
			sb.append(VersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(dlFileEntryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(version)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Version> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the versions where dlFileEntryId = &#63; from the database.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 */
	@Override
	public void removeBydlFileEntryId(long dlFileEntryId) {
		for (Version version :
				findBydlFileEntryId(
					dlFileEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(version);
		}
	}

	/**
	 * Returns the number of versions where dlFileEntryId = &#63;.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @return the number of matching versions
	 */
	@Override
	public int countBydlFileEntryId(long dlFileEntryId) {
		FinderPath finderPath = _finderPathCountBydlFileEntryId;

		Object[] finderArgs = new Object[] {dlFileEntryId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_VERSION_WHERE);

			sb.append(_FINDER_COLUMN_DLFILEENTRYID_DLFILEENTRYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(dlFileEntryId);

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

	private static final String _FINDER_COLUMN_DLFILEENTRYID_DLFILEENTRYID_2 =
		"version.dlFileEntryId = ?";

	public VersionPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("comment", "comment_");

		setDBColumnNames(dbColumnNames);

		setModelClass(Version.class);

		setModelImplClass(VersionImpl.class);
		setModelPKClass(long.class);

		setTable(VersionTable.INSTANCE);
	}

	/**
	 * Caches the version in the entity cache if it is enabled.
	 *
	 * @param version the version
	 */
	@Override
	public void cacheResult(Version version) {
		entityCache.putResult(
			VersionImpl.class, version.getPrimaryKey(), version);

		finderCache.putResult(
			_finderPathFetchBydlFileEntryId_versionNumber,
			new Object[] {
				version.getDlFileEntryId(), version.getVersionNumber()
			},
			version);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the versions in the entity cache if it is enabled.
	 *
	 * @param versions the versions
	 */
	@Override
	public void cacheResult(List<Version> versions) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (versions.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Version version : versions) {
			if (entityCache.getResult(
					VersionImpl.class, version.getPrimaryKey()) == null) {

				cacheResult(version);
			}
		}
	}

	/**
	 * Clears the cache for all versions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VersionImpl.class);

		finderCache.clearCache(VersionImpl.class);
	}

	/**
	 * Clears the cache for the version.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Version version) {
		entityCache.removeResult(VersionImpl.class, version);
	}

	@Override
	public void clearCache(List<Version> versions) {
		for (Version version : versions) {
			entityCache.removeResult(VersionImpl.class, version);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(VersionImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(VersionImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(VersionModelImpl versionModelImpl) {
		Object[] args = new Object[] {
			versionModelImpl.getDlFileEntryId(),
			versionModelImpl.getVersionNumber()
		};

		finderCache.putResult(
			_finderPathCountBydlFileEntryId_versionNumber, args,
			Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchBydlFileEntryId_versionNumber, args,
			versionModelImpl);
	}

	/**
	 * Creates a new version with the primary key. Does not add the version to the database.
	 *
	 * @param fileVersionId the primary key for the new version
	 * @return the new version
	 */
	@Override
	public Version create(long fileVersionId) {
		Version version = new VersionImpl();

		version.setNew(true);
		version.setPrimaryKey(fileVersionId);

		return version;
	}

	/**
	 * Removes the version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fileVersionId the primary key of the version
	 * @return the version that was removed
	 * @throws NoSuchVersionException if a version with the primary key could not be found
	 */
	@Override
	public Version remove(long fileVersionId) throws NoSuchVersionException {
		return remove((Serializable)fileVersionId);
	}

	/**
	 * Removes the version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the version
	 * @return the version that was removed
	 * @throws NoSuchVersionException if a version with the primary key could not be found
	 */
	@Override
	public Version remove(Serializable primaryKey)
		throws NoSuchVersionException {

		Session session = null;

		try {
			session = openSession();

			Version version = (Version)session.get(
				VersionImpl.class, primaryKey);

			if (version == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVersionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(version);
		}
		catch (NoSuchVersionException noSuchEntityException) {
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
	protected Version removeImpl(Version version) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(version)) {
				version = (Version)session.get(
					VersionImpl.class, version.getPrimaryKeyObj());
			}

			if (version != null) {
				session.delete(version);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (version != null) {
			clearCache(version);
		}

		return version;
	}

	@Override
	public Version updateImpl(Version version) {
		boolean isNew = version.isNew();

		if (!(version instanceof VersionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(version.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(version);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in version proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Version implementation " +
					version.getClass());
		}

		VersionModelImpl versionModelImpl = (VersionModelImpl)version;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(version);
			}
			else {
				version = (Version)session.merge(version);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(VersionImpl.class, versionModelImpl, false, true);

		cacheUniqueFindersCache(versionModelImpl);

		if (isNew) {
			version.setNew(false);
		}

		version.resetOriginalValues();

		return version;
	}

	/**
	 * Returns the version with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the version
	 * @return the version
	 * @throws NoSuchVersionException if a version with the primary key could not be found
	 */
	@Override
	public Version findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVersionException {

		Version version = fetchByPrimaryKey(primaryKey);

		if (version == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVersionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return version;
	}

	/**
	 * Returns the version with the primary key or throws a <code>NoSuchVersionException</code> if it could not be found.
	 *
	 * @param fileVersionId the primary key of the version
	 * @return the version
	 * @throws NoSuchVersionException if a version with the primary key could not be found
	 */
	@Override
	public Version findByPrimaryKey(long fileVersionId)
		throws NoSuchVersionException {

		return findByPrimaryKey((Serializable)fileVersionId);
	}

	/**
	 * Returns the version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fileVersionId the primary key of the version
	 * @return the version, or <code>null</code> if a version with the primary key could not be found
	 */
	@Override
	public Version fetchByPrimaryKey(long fileVersionId) {
		return fetchByPrimaryKey((Serializable)fileVersionId);
	}

	/**
	 * Returns all the versions.
	 *
	 * @return the versions
	 */
	@Override
	public List<Version> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of versions
	 * @param end the upper bound of the range of versions (not inclusive)
	 * @return the range of versions
	 */
	@Override
	public List<Version> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of versions
	 * @param end the upper bound of the range of versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of versions
	 */
	@Override
	public List<Version> findAll(
		int start, int end, OrderByComparator<Version> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of versions
	 * @param end the upper bound of the range of versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of versions
	 */
	@Override
	public List<Version> findAll(
		int start, int end, OrderByComparator<Version> orderByComparator,
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

		List<Version> list = null;

		if (useFinderCache) {
			list = (List<Version>)finderCache.getResult(finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_VERSION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_VERSION;

				sql = sql.concat(VersionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Version>)QueryUtil.list(
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
	 * Removes all the versions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Version version : findAll()) {
			remove(version);
		}
	}

	/**
	 * Returns the number of versions.
	 *
	 * @return the number of versions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_VERSION);

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
		return "fileVersionId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_VERSION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return VersionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the version persistence.
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

		_finderPathFetchBydlFileEntryId_versionNumber = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchBydlFileEntryId_versionNumber",
			new String[] {Long.class.getName(), Double.class.getName()},
			new String[] {"dlFileEntryId", "versionNumber"}, true);

		_finderPathCountBydlFileEntryId_versionNumber = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBydlFileEntryId_versionNumber",
			new String[] {Long.class.getName(), Double.class.getName()},
			new String[] {"dlFileEntryId", "versionNumber"}, false);

		_finderPathWithPaginationFindBydlFileEntryId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBydlFileEntryId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"dlFileEntryId"}, true);

		_finderPathWithoutPaginationFindBydlFileEntryId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBydlFileEntryId",
			new String[] {Long.class.getName()}, new String[] {"dlFileEntryId"},
			true);

		_finderPathCountBydlFileEntryId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBydlFileEntryId",
			new String[] {Long.class.getName()}, new String[] {"dlFileEntryId"},
			false);

		_setVersionUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setVersionUtilPersistence(null);

		entityCache.removeCache(VersionImpl.class.getName());
	}

	private void _setVersionUtilPersistence(
		VersionPersistence versionPersistence) {

		try {
			Field field = VersionUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, versionPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = DocumentPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = DocumentPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = DocumentPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_VERSION =
		"SELECT version FROM Version version";

	private static final String _SQL_SELECT_VERSION_WHERE =
		"SELECT version FROM Version version WHERE ";

	private static final String _SQL_COUNT_VERSION =
		"SELECT COUNT(version) FROM Version version";

	private static final String _SQL_COUNT_VERSION_WHERE =
		"SELECT COUNT(version) FROM Version version WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "version.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Version exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Version exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		VersionPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"comment"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private VersionModelArgumentsResolver _versionModelArgumentsResolver;

}