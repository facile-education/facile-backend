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

package com.weprode.facile.access.service.persistence.impl;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUID;

import com.weprode.facile.access.exception.NoSuchProfileException;
import com.weprode.facile.access.model.AccessProfile;
import com.weprode.facile.access.model.AccessProfileTable;
import com.weprode.facile.access.model.impl.AccessProfileImpl;
import com.weprode.facile.access.model.impl.AccessProfileModelImpl;
import com.weprode.facile.access.service.persistence.AccessProfilePK;
import com.weprode.facile.access.service.persistence.AccessProfilePersistence;
import com.weprode.facile.access.service.persistence.AccessProfileUtil;
import com.weprode.facile.access.service.persistence.impl.constants.AccessPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.HashMap;
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
 * The persistence implementation for the access profile service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {AccessProfilePersistence.class, BasePersistence.class})
public class AccessProfilePersistenceImpl
	extends BasePersistenceImpl<AccessProfile>
	implements AccessProfilePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>AccessProfileUtil</code> to access the access profile persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		AccessProfileImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the access profiles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching access profiles
	 */
	@Override
	public List<AccessProfile> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the access profiles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @return the range of matching access profiles
	 */
	@Override
	public List<AccessProfile> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the access profiles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching access profiles
	 */
	@Override
	public List<AccessProfile> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AccessProfile> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the access profiles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching access profiles
	 */
	@Override
	public List<AccessProfile> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AccessProfile> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<AccessProfile> list = null;

		if (useFinderCache) {
			list = (List<AccessProfile>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccessProfile accessProfile : list) {
					if (!uuid.equals(accessProfile.getUuid())) {
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

			sb.append(_SQL_SELECT_ACCESSPROFILE_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AccessProfileModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<AccessProfile>)QueryUtil.list(
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
	 * Returns the first access profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access profile
	 * @throws NoSuchProfileException if a matching access profile could not be found
	 */
	@Override
	public AccessProfile findByUuid_First(
			String uuid, OrderByComparator<AccessProfile> orderByComparator)
		throws NoSuchProfileException {

		AccessProfile accessProfile = fetchByUuid_First(
			uuid, orderByComparator);

		if (accessProfile != null) {
			return accessProfile;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchProfileException(sb.toString());
	}

	/**
	 * Returns the first access profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access profile, or <code>null</code> if a matching access profile could not be found
	 */
	@Override
	public AccessProfile fetchByUuid_First(
		String uuid, OrderByComparator<AccessProfile> orderByComparator) {

		List<AccessProfile> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last access profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access profile
	 * @throws NoSuchProfileException if a matching access profile could not be found
	 */
	@Override
	public AccessProfile findByUuid_Last(
			String uuid, OrderByComparator<AccessProfile> orderByComparator)
		throws NoSuchProfileException {

		AccessProfile accessProfile = fetchByUuid_Last(uuid, orderByComparator);

		if (accessProfile != null) {
			return accessProfile;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchProfileException(sb.toString());
	}

	/**
	 * Returns the last access profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access profile, or <code>null</code> if a matching access profile could not be found
	 */
	@Override
	public AccessProfile fetchByUuid_Last(
		String uuid, OrderByComparator<AccessProfile> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<AccessProfile> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the access profiles before and after the current access profile in the ordered set where uuid = &#63;.
	 *
	 * @param accessProfilePK the primary key of the current access profile
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next access profile
	 * @throws NoSuchProfileException if a access profile with the primary key could not be found
	 */
	@Override
	public AccessProfile[] findByUuid_PrevAndNext(
			AccessProfilePK accessProfilePK, String uuid,
			OrderByComparator<AccessProfile> orderByComparator)
		throws NoSuchProfileException {

		uuid = Objects.toString(uuid, "");

		AccessProfile accessProfile = findByPrimaryKey(accessProfilePK);

		Session session = null;

		try {
			session = openSession();

			AccessProfile[] array = new AccessProfileImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, accessProfile, uuid, orderByComparator, true);

			array[1] = accessProfile;

			array[2] = getByUuid_PrevAndNext(
				session, accessProfile, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccessProfile getByUuid_PrevAndNext(
		Session session, AccessProfile accessProfile, String uuid,
		OrderByComparator<AccessProfile> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ACCESSPROFILE_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
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
			sb.append(AccessProfileModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						accessProfile)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AccessProfile> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the access profiles where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (AccessProfile accessProfile :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(accessProfile);
		}
	}

	/**
	 * Returns the number of access profiles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching access profiles
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ACCESSPROFILE_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
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

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"accessProfile.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(accessProfile.uuid IS NULL OR accessProfile.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByaccessId;
	private FinderPath _finderPathWithoutPaginationFindByaccessId;
	private FinderPath _finderPathCountByaccessId;

	/**
	 * Returns all the access profiles where accessId = &#63;.
	 *
	 * @param accessId the access ID
	 * @return the matching access profiles
	 */
	@Override
	public List<AccessProfile> findByaccessId(long accessId) {
		return findByaccessId(
			accessId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the access profiles where accessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param accessId the access ID
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @return the range of matching access profiles
	 */
	@Override
	public List<AccessProfile> findByaccessId(
		long accessId, int start, int end) {

		return findByaccessId(accessId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the access profiles where accessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param accessId the access ID
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching access profiles
	 */
	@Override
	public List<AccessProfile> findByaccessId(
		long accessId, int start, int end,
		OrderByComparator<AccessProfile> orderByComparator) {

		return findByaccessId(accessId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the access profiles where accessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param accessId the access ID
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching access profiles
	 */
	@Override
	public List<AccessProfile> findByaccessId(
		long accessId, int start, int end,
		OrderByComparator<AccessProfile> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByaccessId;
				finderArgs = new Object[] {accessId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByaccessId;
			finderArgs = new Object[] {accessId, start, end, orderByComparator};
		}

		List<AccessProfile> list = null;

		if (useFinderCache) {
			list = (List<AccessProfile>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccessProfile accessProfile : list) {
					if (accessId != accessProfile.getAccessId()) {
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

			sb.append(_SQL_SELECT_ACCESSPROFILE_WHERE);

			sb.append(_FINDER_COLUMN_ACCESSID_ACCESSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AccessProfileModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(accessId);

				list = (List<AccessProfile>)QueryUtil.list(
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
	 * Returns the first access profile in the ordered set where accessId = &#63;.
	 *
	 * @param accessId the access ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access profile
	 * @throws NoSuchProfileException if a matching access profile could not be found
	 */
	@Override
	public AccessProfile findByaccessId_First(
			long accessId, OrderByComparator<AccessProfile> orderByComparator)
		throws NoSuchProfileException {

		AccessProfile accessProfile = fetchByaccessId_First(
			accessId, orderByComparator);

		if (accessProfile != null) {
			return accessProfile;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accessId=");
		sb.append(accessId);

		sb.append("}");

		throw new NoSuchProfileException(sb.toString());
	}

	/**
	 * Returns the first access profile in the ordered set where accessId = &#63;.
	 *
	 * @param accessId the access ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access profile, or <code>null</code> if a matching access profile could not be found
	 */
	@Override
	public AccessProfile fetchByaccessId_First(
		long accessId, OrderByComparator<AccessProfile> orderByComparator) {

		List<AccessProfile> list = findByaccessId(
			accessId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last access profile in the ordered set where accessId = &#63;.
	 *
	 * @param accessId the access ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access profile
	 * @throws NoSuchProfileException if a matching access profile could not be found
	 */
	@Override
	public AccessProfile findByaccessId_Last(
			long accessId, OrderByComparator<AccessProfile> orderByComparator)
		throws NoSuchProfileException {

		AccessProfile accessProfile = fetchByaccessId_Last(
			accessId, orderByComparator);

		if (accessProfile != null) {
			return accessProfile;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accessId=");
		sb.append(accessId);

		sb.append("}");

		throw new NoSuchProfileException(sb.toString());
	}

	/**
	 * Returns the last access profile in the ordered set where accessId = &#63;.
	 *
	 * @param accessId the access ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access profile, or <code>null</code> if a matching access profile could not be found
	 */
	@Override
	public AccessProfile fetchByaccessId_Last(
		long accessId, OrderByComparator<AccessProfile> orderByComparator) {

		int count = countByaccessId(accessId);

		if (count == 0) {
			return null;
		}

		List<AccessProfile> list = findByaccessId(
			accessId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the access profiles before and after the current access profile in the ordered set where accessId = &#63;.
	 *
	 * @param accessProfilePK the primary key of the current access profile
	 * @param accessId the access ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next access profile
	 * @throws NoSuchProfileException if a access profile with the primary key could not be found
	 */
	@Override
	public AccessProfile[] findByaccessId_PrevAndNext(
			AccessProfilePK accessProfilePK, long accessId,
			OrderByComparator<AccessProfile> orderByComparator)
		throws NoSuchProfileException {

		AccessProfile accessProfile = findByPrimaryKey(accessProfilePK);

		Session session = null;

		try {
			session = openSession();

			AccessProfile[] array = new AccessProfileImpl[3];

			array[0] = getByaccessId_PrevAndNext(
				session, accessProfile, accessId, orderByComparator, true);

			array[1] = accessProfile;

			array[2] = getByaccessId_PrevAndNext(
				session, accessProfile, accessId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccessProfile getByaccessId_PrevAndNext(
		Session session, AccessProfile accessProfile, long accessId,
		OrderByComparator<AccessProfile> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ACCESSPROFILE_WHERE);

		sb.append(_FINDER_COLUMN_ACCESSID_ACCESSID_2);

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
			sb.append(AccessProfileModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(accessId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						accessProfile)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AccessProfile> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the access profiles where accessId = &#63; from the database.
	 *
	 * @param accessId the access ID
	 */
	@Override
	public void removeByaccessId(long accessId) {
		for (AccessProfile accessProfile :
				findByaccessId(
					accessId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(accessProfile);
		}
	}

	/**
	 * Returns the number of access profiles where accessId = &#63;.
	 *
	 * @param accessId the access ID
	 * @return the number of matching access profiles
	 */
	@Override
	public int countByaccessId(long accessId) {
		FinderPath finderPath = _finderPathCountByaccessId;

		Object[] finderArgs = new Object[] {accessId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ACCESSPROFILE_WHERE);

			sb.append(_FINDER_COLUMN_ACCESSID_ACCESSID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(accessId);

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

	private static final String _FINDER_COLUMN_ACCESSID_ACCESSID_2 =
		"accessProfile.id.accessId = ?";

	public AccessProfilePersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(AccessProfile.class);

		setModelImplClass(AccessProfileImpl.class);
		setModelPKClass(AccessProfilePK.class);

		setTable(AccessProfileTable.INSTANCE);
	}

	/**
	 * Caches the access profile in the entity cache if it is enabled.
	 *
	 * @param accessProfile the access profile
	 */
	@Override
	public void cacheResult(AccessProfile accessProfile) {
		entityCache.putResult(
			AccessProfileImpl.class, accessProfile.getPrimaryKey(),
			accessProfile);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the access profiles in the entity cache if it is enabled.
	 *
	 * @param accessProfiles the access profiles
	 */
	@Override
	public void cacheResult(List<AccessProfile> accessProfiles) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (accessProfiles.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (AccessProfile accessProfile : accessProfiles) {
			if (entityCache.getResult(
					AccessProfileImpl.class, accessProfile.getPrimaryKey()) ==
						null) {

				cacheResult(accessProfile);
			}
		}
	}

	/**
	 * Clears the cache for all access profiles.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AccessProfileImpl.class);

		finderCache.clearCache(AccessProfileImpl.class);
	}

	/**
	 * Clears the cache for the access profile.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccessProfile accessProfile) {
		entityCache.removeResult(AccessProfileImpl.class, accessProfile);
	}

	@Override
	public void clearCache(List<AccessProfile> accessProfiles) {
		for (AccessProfile accessProfile : accessProfiles) {
			entityCache.removeResult(AccessProfileImpl.class, accessProfile);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(AccessProfileImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(AccessProfileImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new access profile with the primary key. Does not add the access profile to the database.
	 *
	 * @param accessProfilePK the primary key for the new access profile
	 * @return the new access profile
	 */
	@Override
	public AccessProfile create(AccessProfilePK accessProfilePK) {
		AccessProfile accessProfile = new AccessProfileImpl();

		accessProfile.setNew(true);
		accessProfile.setPrimaryKey(accessProfilePK);

		String uuid = _portalUUID.generate();

		accessProfile.setUuid(uuid);

		return accessProfile;
	}

	/**
	 * Removes the access profile with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accessProfilePK the primary key of the access profile
	 * @return the access profile that was removed
	 * @throws NoSuchProfileException if a access profile with the primary key could not be found
	 */
	@Override
	public AccessProfile remove(AccessProfilePK accessProfilePK)
		throws NoSuchProfileException {

		return remove((Serializable)accessProfilePK);
	}

	/**
	 * Removes the access profile with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the access profile
	 * @return the access profile that was removed
	 * @throws NoSuchProfileException if a access profile with the primary key could not be found
	 */
	@Override
	public AccessProfile remove(Serializable primaryKey)
		throws NoSuchProfileException {

		Session session = null;

		try {
			session = openSession();

			AccessProfile accessProfile = (AccessProfile)session.get(
				AccessProfileImpl.class, primaryKey);

			if (accessProfile == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProfileException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(accessProfile);
		}
		catch (NoSuchProfileException noSuchEntityException) {
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
	protected AccessProfile removeImpl(AccessProfile accessProfile) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(accessProfile)) {
				accessProfile = (AccessProfile)session.get(
					AccessProfileImpl.class, accessProfile.getPrimaryKeyObj());
			}

			if (accessProfile != null) {
				session.delete(accessProfile);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (accessProfile != null) {
			clearCache(accessProfile);
		}

		return accessProfile;
	}

	@Override
	public AccessProfile updateImpl(AccessProfile accessProfile) {
		boolean isNew = accessProfile.isNew();

		if (!(accessProfile instanceof AccessProfileModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(accessProfile.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					accessProfile);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in accessProfile proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom AccessProfile implementation " +
					accessProfile.getClass());
		}

		AccessProfileModelImpl accessProfileModelImpl =
			(AccessProfileModelImpl)accessProfile;

		if (Validator.isNull(accessProfile.getUuid())) {
			String uuid = _portalUUID.generate();

			accessProfile.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(accessProfile);
			}
			else {
				accessProfile = (AccessProfile)session.merge(accessProfile);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			AccessProfileImpl.class, accessProfileModelImpl, false, true);

		if (isNew) {
			accessProfile.setNew(false);
		}

		accessProfile.resetOriginalValues();

		return accessProfile;
	}

	/**
	 * Returns the access profile with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the access profile
	 * @return the access profile
	 * @throws NoSuchProfileException if a access profile with the primary key could not be found
	 */
	@Override
	public AccessProfile findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProfileException {

		AccessProfile accessProfile = fetchByPrimaryKey(primaryKey);

		if (accessProfile == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProfileException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return accessProfile;
	}

	/**
	 * Returns the access profile with the primary key or throws a <code>NoSuchProfileException</code> if it could not be found.
	 *
	 * @param accessProfilePK the primary key of the access profile
	 * @return the access profile
	 * @throws NoSuchProfileException if a access profile with the primary key could not be found
	 */
	@Override
	public AccessProfile findByPrimaryKey(AccessProfilePK accessProfilePK)
		throws NoSuchProfileException {

		return findByPrimaryKey((Serializable)accessProfilePK);
	}

	/**
	 * Returns the access profile with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accessProfilePK the primary key of the access profile
	 * @return the access profile, or <code>null</code> if a access profile with the primary key could not be found
	 */
	@Override
	public AccessProfile fetchByPrimaryKey(AccessProfilePK accessProfilePK) {
		return fetchByPrimaryKey((Serializable)accessProfilePK);
	}

	/**
	 * Returns all the access profiles.
	 *
	 * @return the access profiles
	 */
	@Override
	public List<AccessProfile> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the access profiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @return the range of access profiles
	 */
	@Override
	public List<AccessProfile> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the access profiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of access profiles
	 */
	@Override
	public List<AccessProfile> findAll(
		int start, int end,
		OrderByComparator<AccessProfile> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the access profiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of access profiles
	 */
	@Override
	public List<AccessProfile> findAll(
		int start, int end, OrderByComparator<AccessProfile> orderByComparator,
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

		List<AccessProfile> list = null;

		if (useFinderCache) {
			list = (List<AccessProfile>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ACCESSPROFILE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ACCESSPROFILE;

				sql = sql.concat(AccessProfileModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<AccessProfile>)QueryUtil.list(
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
	 * Removes all the access profiles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AccessProfile accessProfile : findAll()) {
			remove(accessProfile);
		}
	}

	/**
	 * Returns the number of access profiles.
	 *
	 * @return the number of access profiles
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ACCESSPROFILE);

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
	public Set<String> getCompoundPKColumnNames() {
		return _compoundPKColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "accessProfilePK";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ACCESSPROFILE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AccessProfileModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the access profile persistence.
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

		_finderPathWithPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);

		_finderPathWithPaginationFindByaccessId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByaccessId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"accessId"}, true);

		_finderPathWithoutPaginationFindByaccessId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByaccessId",
			new String[] {Long.class.getName()}, new String[] {"accessId"},
			true);

		_finderPathCountByaccessId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByaccessId",
			new String[] {Long.class.getName()}, new String[] {"accessId"},
			false);

		_setAccessProfileUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setAccessProfileUtilPersistence(null);

		entityCache.removeCache(AccessProfileImpl.class.getName());
	}

	private void _setAccessProfileUtilPersistence(
		AccessProfilePersistence accessProfilePersistence) {

		try {
			Field field = AccessProfileUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, accessProfilePersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = AccessPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = AccessPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = AccessPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_ACCESSPROFILE =
		"SELECT accessProfile FROM AccessProfile accessProfile";

	private static final String _SQL_SELECT_ACCESSPROFILE_WHERE =
		"SELECT accessProfile FROM AccessProfile accessProfile WHERE ";

	private static final String _SQL_COUNT_ACCESSPROFILE =
		"SELECT COUNT(accessProfile) FROM AccessProfile accessProfile";

	private static final String _SQL_COUNT_ACCESSPROFILE_WHERE =
		"SELECT COUNT(accessProfile) FROM AccessProfile accessProfile WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "accessProfile.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No AccessProfile exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No AccessProfile exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		AccessProfilePersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});
	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"accessId", "roleId"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private PortalUUID _portalUUID;

}