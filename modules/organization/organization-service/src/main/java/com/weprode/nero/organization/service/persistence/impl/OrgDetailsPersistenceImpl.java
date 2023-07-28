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

package com.weprode.nero.organization.service.persistence.impl;

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

import com.weprode.nero.organization.exception.NoSuchOrgDetailsException;
import com.weprode.nero.organization.model.OrgDetails;
import com.weprode.nero.organization.model.OrgDetailsTable;
import com.weprode.nero.organization.model.impl.OrgDetailsImpl;
import com.weprode.nero.organization.model.impl.OrgDetailsModelImpl;
import com.weprode.nero.organization.service.persistence.OrgDetailsPersistence;
import com.weprode.nero.organization.service.persistence.OrgDetailsUtil;
import com.weprode.nero.organization.service.persistence.impl.constants.OrganizationPersistenceConstants;

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
 * The persistence implementation for the org details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marc Salvat
 * @generated
 */
@Component(service = {OrgDetailsPersistence.class, BasePersistence.class})
public class OrgDetailsPersistenceImpl
	extends BasePersistenceImpl<OrgDetails> implements OrgDetailsPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>OrgDetailsUtil</code> to access the org details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		OrgDetailsImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByschoolId_archive;
	private FinderPath _finderPathWithoutPaginationFindByschoolId_archive;
	private FinderPath _finderPathCountByschoolId_archive;

	/**
	 * Returns all the org detailses where schoolId = &#63; and isArchive = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @return the matching org detailses
	 */
	@Override
	public List<OrgDetails> findByschoolId_archive(
		long schoolId, boolean isArchive) {

		return findByschoolId_archive(
			schoolId, isArchive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the org detailses where schoolId = &#63; and isArchive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @param start the lower bound of the range of org detailses
	 * @param end the upper bound of the range of org detailses (not inclusive)
	 * @return the range of matching org detailses
	 */
	@Override
	public List<OrgDetails> findByschoolId_archive(
		long schoolId, boolean isArchive, int start, int end) {

		return findByschoolId_archive(schoolId, isArchive, start, end, null);
	}

	/**
	 * Returns an ordered range of all the org detailses where schoolId = &#63; and isArchive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @param start the lower bound of the range of org detailses
	 * @param end the upper bound of the range of org detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching org detailses
	 */
	@Override
	public List<OrgDetails> findByschoolId_archive(
		long schoolId, boolean isArchive, int start, int end,
		OrderByComparator<OrgDetails> orderByComparator) {

		return findByschoolId_archive(
			schoolId, isArchive, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the org detailses where schoolId = &#63; and isArchive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @param start the lower bound of the range of org detailses
	 * @param end the upper bound of the range of org detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching org detailses
	 */
	@Override
	public List<OrgDetails> findByschoolId_archive(
		long schoolId, boolean isArchive, int start, int end,
		OrderByComparator<OrgDetails> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByschoolId_archive;
				finderArgs = new Object[] {schoolId, isArchive};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByschoolId_archive;
			finderArgs = new Object[] {
				schoolId, isArchive, start, end, orderByComparator
			};
		}

		List<OrgDetails> list = null;

		if (useFinderCache) {
			list = (List<OrgDetails>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (OrgDetails orgDetails : list) {
					if ((schoolId != orgDetails.getSchoolId()) ||
						(isArchive != orgDetails.isIsArchive())) {

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

			sb.append(_SQL_SELECT_ORGDETAILS_WHERE);

			sb.append(_FINDER_COLUMN_SCHOOLID_ARCHIVE_SCHOOLID_2);

			sb.append(_FINDER_COLUMN_SCHOOLID_ARCHIVE_ISARCHIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(OrgDetailsModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(schoolId);

				queryPos.add(isArchive);

				list = (List<OrgDetails>)QueryUtil.list(
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
	 * Returns the first org details in the ordered set where schoolId = &#63; and isArchive = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org details
	 * @throws NoSuchOrgDetailsException if a matching org details could not be found
	 */
	@Override
	public OrgDetails findByschoolId_archive_First(
			long schoolId, boolean isArchive,
			OrderByComparator<OrgDetails> orderByComparator)
		throws NoSuchOrgDetailsException {

		OrgDetails orgDetails = fetchByschoolId_archive_First(
			schoolId, isArchive, orderByComparator);

		if (orgDetails != null) {
			return orgDetails;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("schoolId=");
		sb.append(schoolId);

		sb.append(", isArchive=");
		sb.append(isArchive);

		sb.append("}");

		throw new NoSuchOrgDetailsException(sb.toString());
	}

	/**
	 * Returns the first org details in the ordered set where schoolId = &#63; and isArchive = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org details, or <code>null</code> if a matching org details could not be found
	 */
	@Override
	public OrgDetails fetchByschoolId_archive_First(
		long schoolId, boolean isArchive,
		OrderByComparator<OrgDetails> orderByComparator) {

		List<OrgDetails> list = findByschoolId_archive(
			schoolId, isArchive, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last org details in the ordered set where schoolId = &#63; and isArchive = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org details
	 * @throws NoSuchOrgDetailsException if a matching org details could not be found
	 */
	@Override
	public OrgDetails findByschoolId_archive_Last(
			long schoolId, boolean isArchive,
			OrderByComparator<OrgDetails> orderByComparator)
		throws NoSuchOrgDetailsException {

		OrgDetails orgDetails = fetchByschoolId_archive_Last(
			schoolId, isArchive, orderByComparator);

		if (orgDetails != null) {
			return orgDetails;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("schoolId=");
		sb.append(schoolId);

		sb.append(", isArchive=");
		sb.append(isArchive);

		sb.append("}");

		throw new NoSuchOrgDetailsException(sb.toString());
	}

	/**
	 * Returns the last org details in the ordered set where schoolId = &#63; and isArchive = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org details, or <code>null</code> if a matching org details could not be found
	 */
	@Override
	public OrgDetails fetchByschoolId_archive_Last(
		long schoolId, boolean isArchive,
		OrderByComparator<OrgDetails> orderByComparator) {

		int count = countByschoolId_archive(schoolId, isArchive);

		if (count == 0) {
			return null;
		}

		List<OrgDetails> list = findByschoolId_archive(
			schoolId, isArchive, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the org detailses before and after the current org details in the ordered set where schoolId = &#63; and isArchive = &#63;.
	 *
	 * @param orgId the primary key of the current org details
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next org details
	 * @throws NoSuchOrgDetailsException if a org details with the primary key could not be found
	 */
	@Override
	public OrgDetails[] findByschoolId_archive_PrevAndNext(
			long orgId, long schoolId, boolean isArchive,
			OrderByComparator<OrgDetails> orderByComparator)
		throws NoSuchOrgDetailsException {

		OrgDetails orgDetails = findByPrimaryKey(orgId);

		Session session = null;

		try {
			session = openSession();

			OrgDetails[] array = new OrgDetailsImpl[3];

			array[0] = getByschoolId_archive_PrevAndNext(
				session, orgDetails, schoolId, isArchive, orderByComparator,
				true);

			array[1] = orgDetails;

			array[2] = getByschoolId_archive_PrevAndNext(
				session, orgDetails, schoolId, isArchive, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected OrgDetails getByschoolId_archive_PrevAndNext(
		Session session, OrgDetails orgDetails, long schoolId,
		boolean isArchive, OrderByComparator<OrgDetails> orderByComparator,
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

		sb.append(_SQL_SELECT_ORGDETAILS_WHERE);

		sb.append(_FINDER_COLUMN_SCHOOLID_ARCHIVE_SCHOOLID_2);

		sb.append(_FINDER_COLUMN_SCHOOLID_ARCHIVE_ISARCHIVE_2);

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
			sb.append(OrgDetailsModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(schoolId);

		queryPos.add(isArchive);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(orgDetails)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<OrgDetails> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the org detailses where schoolId = &#63; and isArchive = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 */
	@Override
	public void removeByschoolId_archive(long schoolId, boolean isArchive) {
		for (OrgDetails orgDetails :
				findByschoolId_archive(
					schoolId, isArchive, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(orgDetails);
		}
	}

	/**
	 * Returns the number of org detailses where schoolId = &#63; and isArchive = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @return the number of matching org detailses
	 */
	@Override
	public int countByschoolId_archive(long schoolId, boolean isArchive) {
		FinderPath finderPath = _finderPathCountByschoolId_archive;

		Object[] finderArgs = new Object[] {schoolId, isArchive};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_ORGDETAILS_WHERE);

			sb.append(_FINDER_COLUMN_SCHOOLID_ARCHIVE_SCHOOLID_2);

			sb.append(_FINDER_COLUMN_SCHOOLID_ARCHIVE_ISARCHIVE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(schoolId);

				queryPos.add(isArchive);

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

	private static final String _FINDER_COLUMN_SCHOOLID_ARCHIVE_SCHOOLID_2 =
		"orgDetails.schoolId = ? AND ";

	private static final String _FINDER_COLUMN_SCHOOLID_ARCHIVE_ISARCHIVE_2 =
		"orgDetails.isArchive = ?";

	private FinderPath _finderPathWithPaginationFindBytype;
	private FinderPath _finderPathWithoutPaginationFindBytype;
	private FinderPath _finderPathCountBytype;

	/**
	 * Returns all the org detailses where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching org detailses
	 */
	@Override
	public List<OrgDetails> findBytype(int type) {
		return findBytype(type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the org detailses where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of org detailses
	 * @param end the upper bound of the range of org detailses (not inclusive)
	 * @return the range of matching org detailses
	 */
	@Override
	public List<OrgDetails> findBytype(int type, int start, int end) {
		return findBytype(type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the org detailses where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of org detailses
	 * @param end the upper bound of the range of org detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching org detailses
	 */
	@Override
	public List<OrgDetails> findBytype(
		int type, int start, int end,
		OrderByComparator<OrgDetails> orderByComparator) {

		return findBytype(type, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the org detailses where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of org detailses
	 * @param end the upper bound of the range of org detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching org detailses
	 */
	@Override
	public List<OrgDetails> findBytype(
		int type, int start, int end,
		OrderByComparator<OrgDetails> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBytype;
				finderArgs = new Object[] {type};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBytype;
			finderArgs = new Object[] {type, start, end, orderByComparator};
		}

		List<OrgDetails> list = null;

		if (useFinderCache) {
			list = (List<OrgDetails>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (OrgDetails orgDetails : list) {
					if (type != orgDetails.getType()) {
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

			sb.append(_SQL_SELECT_ORGDETAILS_WHERE);

			sb.append(_FINDER_COLUMN_TYPE_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(OrgDetailsModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(type);

				list = (List<OrgDetails>)QueryUtil.list(
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
	 * Returns the first org details in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org details
	 * @throws NoSuchOrgDetailsException if a matching org details could not be found
	 */
	@Override
	public OrgDetails findBytype_First(
			int type, OrderByComparator<OrgDetails> orderByComparator)
		throws NoSuchOrgDetailsException {

		OrgDetails orgDetails = fetchBytype_First(type, orderByComparator);

		if (orgDetails != null) {
			return orgDetails;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("type=");
		sb.append(type);

		sb.append("}");

		throw new NoSuchOrgDetailsException(sb.toString());
	}

	/**
	 * Returns the first org details in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org details, or <code>null</code> if a matching org details could not be found
	 */
	@Override
	public OrgDetails fetchBytype_First(
		int type, OrderByComparator<OrgDetails> orderByComparator) {

		List<OrgDetails> list = findBytype(type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last org details in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org details
	 * @throws NoSuchOrgDetailsException if a matching org details could not be found
	 */
	@Override
	public OrgDetails findBytype_Last(
			int type, OrderByComparator<OrgDetails> orderByComparator)
		throws NoSuchOrgDetailsException {

		OrgDetails orgDetails = fetchBytype_Last(type, orderByComparator);

		if (orgDetails != null) {
			return orgDetails;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("type=");
		sb.append(type);

		sb.append("}");

		throw new NoSuchOrgDetailsException(sb.toString());
	}

	/**
	 * Returns the last org details in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org details, or <code>null</code> if a matching org details could not be found
	 */
	@Override
	public OrgDetails fetchBytype_Last(
		int type, OrderByComparator<OrgDetails> orderByComparator) {

		int count = countBytype(type);

		if (count == 0) {
			return null;
		}

		List<OrgDetails> list = findBytype(
			type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the org detailses before and after the current org details in the ordered set where type = &#63;.
	 *
	 * @param orgId the primary key of the current org details
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next org details
	 * @throws NoSuchOrgDetailsException if a org details with the primary key could not be found
	 */
	@Override
	public OrgDetails[] findBytype_PrevAndNext(
			long orgId, int type,
			OrderByComparator<OrgDetails> orderByComparator)
		throws NoSuchOrgDetailsException {

		OrgDetails orgDetails = findByPrimaryKey(orgId);

		Session session = null;

		try {
			session = openSession();

			OrgDetails[] array = new OrgDetailsImpl[3];

			array[0] = getBytype_PrevAndNext(
				session, orgDetails, type, orderByComparator, true);

			array[1] = orgDetails;

			array[2] = getBytype_PrevAndNext(
				session, orgDetails, type, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected OrgDetails getBytype_PrevAndNext(
		Session session, OrgDetails orgDetails, int type,
		OrderByComparator<OrgDetails> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ORGDETAILS_WHERE);

		sb.append(_FINDER_COLUMN_TYPE_TYPE_2);

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
			sb.append(OrgDetailsModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(type);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(orgDetails)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<OrgDetails> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the org detailses where type = &#63; from the database.
	 *
	 * @param type the type
	 */
	@Override
	public void removeBytype(int type) {
		for (OrgDetails orgDetails :
				findBytype(type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(orgDetails);
		}
	}

	/**
	 * Returns the number of org detailses where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching org detailses
	 */
	@Override
	public int countBytype(int type) {
		FinderPath finderPath = _finderPathCountBytype;

		Object[] finderArgs = new Object[] {type};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ORGDETAILS_WHERE);

			sb.append(_FINDER_COLUMN_TYPE_TYPE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

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

	private static final String _FINDER_COLUMN_TYPE_TYPE_2 =
		"orgDetails.type = ?";

	public OrgDetailsPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(OrgDetails.class);

		setModelImplClass(OrgDetailsImpl.class);
		setModelPKClass(long.class);

		setTable(OrgDetailsTable.INSTANCE);
	}

	/**
	 * Caches the org details in the entity cache if it is enabled.
	 *
	 * @param orgDetails the org details
	 */
	@Override
	public void cacheResult(OrgDetails orgDetails) {
		entityCache.putResult(
			OrgDetailsImpl.class, orgDetails.getPrimaryKey(), orgDetails);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the org detailses in the entity cache if it is enabled.
	 *
	 * @param orgDetailses the org detailses
	 */
	@Override
	public void cacheResult(List<OrgDetails> orgDetailses) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (orgDetailses.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (OrgDetails orgDetails : orgDetailses) {
			if (entityCache.getResult(
					OrgDetailsImpl.class, orgDetails.getPrimaryKey()) == null) {

				cacheResult(orgDetails);
			}
		}
	}

	/**
	 * Clears the cache for all org detailses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OrgDetailsImpl.class);

		finderCache.clearCache(OrgDetailsImpl.class);
	}

	/**
	 * Clears the cache for the org details.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OrgDetails orgDetails) {
		entityCache.removeResult(OrgDetailsImpl.class, orgDetails);
	}

	@Override
	public void clearCache(List<OrgDetails> orgDetailses) {
		for (OrgDetails orgDetails : orgDetailses) {
			entityCache.removeResult(OrgDetailsImpl.class, orgDetails);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(OrgDetailsImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(OrgDetailsImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new org details with the primary key. Does not add the org details to the database.
	 *
	 * @param orgId the primary key for the new org details
	 * @return the new org details
	 */
	@Override
	public OrgDetails create(long orgId) {
		OrgDetails orgDetails = new OrgDetailsImpl();

		orgDetails.setNew(true);
		orgDetails.setPrimaryKey(orgId);

		return orgDetails;
	}

	/**
	 * Removes the org details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param orgId the primary key of the org details
	 * @return the org details that was removed
	 * @throws NoSuchOrgDetailsException if a org details with the primary key could not be found
	 */
	@Override
	public OrgDetails remove(long orgId) throws NoSuchOrgDetailsException {
		return remove((Serializable)orgId);
	}

	/**
	 * Removes the org details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the org details
	 * @return the org details that was removed
	 * @throws NoSuchOrgDetailsException if a org details with the primary key could not be found
	 */
	@Override
	public OrgDetails remove(Serializable primaryKey)
		throws NoSuchOrgDetailsException {

		Session session = null;

		try {
			session = openSession();

			OrgDetails orgDetails = (OrgDetails)session.get(
				OrgDetailsImpl.class, primaryKey);

			if (orgDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOrgDetailsException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(orgDetails);
		}
		catch (NoSuchOrgDetailsException noSuchEntityException) {
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
	protected OrgDetails removeImpl(OrgDetails orgDetails) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(orgDetails)) {
				orgDetails = (OrgDetails)session.get(
					OrgDetailsImpl.class, orgDetails.getPrimaryKeyObj());
			}

			if (orgDetails != null) {
				session.delete(orgDetails);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (orgDetails != null) {
			clearCache(orgDetails);
		}

		return orgDetails;
	}

	@Override
	public OrgDetails updateImpl(OrgDetails orgDetails) {
		boolean isNew = orgDetails.isNew();

		if (!(orgDetails instanceof OrgDetailsModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(orgDetails.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(orgDetails);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in orgDetails proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom OrgDetails implementation " +
					orgDetails.getClass());
		}

		OrgDetailsModelImpl orgDetailsModelImpl =
			(OrgDetailsModelImpl)orgDetails;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(orgDetails);
			}
			else {
				orgDetails = (OrgDetails)session.merge(orgDetails);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			OrgDetailsImpl.class, orgDetailsModelImpl, false, true);

		if (isNew) {
			orgDetails.setNew(false);
		}

		orgDetails.resetOriginalValues();

		return orgDetails;
	}

	/**
	 * Returns the org details with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the org details
	 * @return the org details
	 * @throws NoSuchOrgDetailsException if a org details with the primary key could not be found
	 */
	@Override
	public OrgDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOrgDetailsException {

		OrgDetails orgDetails = fetchByPrimaryKey(primaryKey);

		if (orgDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOrgDetailsException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return orgDetails;
	}

	/**
	 * Returns the org details with the primary key or throws a <code>NoSuchOrgDetailsException</code> if it could not be found.
	 *
	 * @param orgId the primary key of the org details
	 * @return the org details
	 * @throws NoSuchOrgDetailsException if a org details with the primary key could not be found
	 */
	@Override
	public OrgDetails findByPrimaryKey(long orgId)
		throws NoSuchOrgDetailsException {

		return findByPrimaryKey((Serializable)orgId);
	}

	/**
	 * Returns the org details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param orgId the primary key of the org details
	 * @return the org details, or <code>null</code> if a org details with the primary key could not be found
	 */
	@Override
	public OrgDetails fetchByPrimaryKey(long orgId) {
		return fetchByPrimaryKey((Serializable)orgId);
	}

	/**
	 * Returns all the org detailses.
	 *
	 * @return the org detailses
	 */
	@Override
	public List<OrgDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the org detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org detailses
	 * @param end the upper bound of the range of org detailses (not inclusive)
	 * @return the range of org detailses
	 */
	@Override
	public List<OrgDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the org detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org detailses
	 * @param end the upper bound of the range of org detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of org detailses
	 */
	@Override
	public List<OrgDetails> findAll(
		int start, int end, OrderByComparator<OrgDetails> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the org detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org detailses
	 * @param end the upper bound of the range of org detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of org detailses
	 */
	@Override
	public List<OrgDetails> findAll(
		int start, int end, OrderByComparator<OrgDetails> orderByComparator,
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

		List<OrgDetails> list = null;

		if (useFinderCache) {
			list = (List<OrgDetails>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ORGDETAILS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ORGDETAILS;

				sql = sql.concat(OrgDetailsModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<OrgDetails>)QueryUtil.list(
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
	 * Removes all the org detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OrgDetails orgDetails : findAll()) {
			remove(orgDetails);
		}
	}

	/**
	 * Returns the number of org detailses.
	 *
	 * @return the number of org detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ORGDETAILS);

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
		return "orgId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ORGDETAILS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return OrgDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the org details persistence.
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

		_finderPathWithPaginationFindByschoolId_archive = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByschoolId_archive",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"schoolId", "isArchive"}, true);

		_finderPathWithoutPaginationFindByschoolId_archive = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByschoolId_archive",
			new String[] {Long.class.getName(), Boolean.class.getName()},
			new String[] {"schoolId", "isArchive"}, true);

		_finderPathCountByschoolId_archive = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByschoolId_archive",
			new String[] {Long.class.getName(), Boolean.class.getName()},
			new String[] {"schoolId", "isArchive"}, false);

		_finderPathWithPaginationFindBytype = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBytype",
			new String[] {
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"type_"}, true);

		_finderPathWithoutPaginationFindBytype = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBytype",
			new String[] {Integer.class.getName()}, new String[] {"type_"},
			true);

		_finderPathCountBytype = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBytype",
			new String[] {Integer.class.getName()}, new String[] {"type_"},
			false);

		_setOrgDetailsUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setOrgDetailsUtilPersistence(null);

		entityCache.removeCache(OrgDetailsImpl.class.getName());
	}

	private void _setOrgDetailsUtilPersistence(
		OrgDetailsPersistence orgDetailsPersistence) {

		try {
			Field field = OrgDetailsUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, orgDetailsPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = OrganizationPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = OrganizationPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = OrganizationPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_ORGDETAILS =
		"SELECT orgDetails FROM OrgDetails orgDetails";

	private static final String _SQL_SELECT_ORGDETAILS_WHERE =
		"SELECT orgDetails FROM OrgDetails orgDetails WHERE ";

	private static final String _SQL_COUNT_ORGDETAILS =
		"SELECT COUNT(orgDetails) FROM OrgDetails orgDetails";

	private static final String _SQL_COUNT_ORGDETAILS_WHERE =
		"SELECT COUNT(orgDetails) FROM OrgDetails orgDetails WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "orgDetails.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No OrgDetails exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No OrgDetails exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		OrgDetailsPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private OrgDetailsModelArgumentsResolver _orgDetailsModelArgumentsResolver;

}