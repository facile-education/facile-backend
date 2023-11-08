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

import com.weprode.facile.user.exception.NoSuchLDAPMappingException;
import com.weprode.facile.user.model.LDAPMapping;
import com.weprode.facile.user.model.LDAPMappingTable;
import com.weprode.facile.user.model.impl.LDAPMappingImpl;
import com.weprode.facile.user.model.impl.LDAPMappingModelImpl;
import com.weprode.facile.user.service.persistence.LDAPMappingPersistence;
import com.weprode.facile.user.service.persistence.LDAPMappingUtil;
import com.weprode.facile.user.service.persistence.impl.constants.UserPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
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
 * The persistence implementation for the ldap mapping service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {LDAPMappingPersistence.class, BasePersistence.class})
public class LDAPMappingPersistenceImpl
	extends BasePersistenceImpl<LDAPMapping> implements LDAPMappingPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>LDAPMappingUtil</code> to access the ldap mapping persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		LDAPMappingImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByUID;
	private FinderPath _finderPathCountByUID;

	/**
	 * Returns the ldap mapping where UID = &#63; or throws a <code>NoSuchLDAPMappingException</code> if it could not be found.
	 *
	 * @param UID the uid
	 * @return the matching ldap mapping
	 * @throws NoSuchLDAPMappingException if a matching ldap mapping could not be found
	 */
	@Override
	public LDAPMapping findByUID(String UID) throws NoSuchLDAPMappingException {
		LDAPMapping ldapMapping = fetchByUID(UID);

		if (ldapMapping == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("UID=");
			sb.append(UID);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchLDAPMappingException(sb.toString());
		}

		return ldapMapping;
	}

	/**
	 * Returns the ldap mapping where UID = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param UID the uid
	 * @return the matching ldap mapping, or <code>null</code> if a matching ldap mapping could not be found
	 */
	@Override
	public LDAPMapping fetchByUID(String UID) {
		return fetchByUID(UID, true);
	}

	/**
	 * Returns the ldap mapping where UID = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param UID the uid
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ldap mapping, or <code>null</code> if a matching ldap mapping could not be found
	 */
	@Override
	public LDAPMapping fetchByUID(String UID, boolean useFinderCache) {
		UID = Objects.toString(UID, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {UID};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUID, finderArgs, this);
		}

		if (result instanceof LDAPMapping) {
			LDAPMapping ldapMapping = (LDAPMapping)result;

			if (!Objects.equals(UID, ldapMapping.getUID())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_LDAPMAPPING_WHERE);

			boolean bindUID = false;

			if (UID.isEmpty()) {
				sb.append(_FINDER_COLUMN_UID_UID_3);
			}
			else {
				bindUID = true;

				sb.append(_FINDER_COLUMN_UID_UID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUID) {
					queryPos.add(UID);
				}

				List<LDAPMapping> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUID, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {UID};
							}

							_log.warn(
								"LDAPMappingPersistenceImpl.fetchByUID(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					LDAPMapping ldapMapping = list.get(0);

					result = ldapMapping;

					cacheResult(ldapMapping);
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
			return (LDAPMapping)result;
		}
	}

	/**
	 * Removes the ldap mapping where UID = &#63; from the database.
	 *
	 * @param UID the uid
	 * @return the ldap mapping that was removed
	 */
	@Override
	public LDAPMapping removeByUID(String UID)
		throws NoSuchLDAPMappingException {

		LDAPMapping ldapMapping = findByUID(UID);

		return remove(ldapMapping);
	}

	/**
	 * Returns the number of ldap mappings where UID = &#63;.
	 *
	 * @param UID the uid
	 * @return the number of matching ldap mappings
	 */
	@Override
	public int countByUID(String UID) {
		UID = Objects.toString(UID, "");

		FinderPath finderPath = _finderPathCountByUID;

		Object[] finderArgs = new Object[] {UID};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LDAPMAPPING_WHERE);

			boolean bindUID = false;

			if (UID.isEmpty()) {
				sb.append(_FINDER_COLUMN_UID_UID_3);
			}
			else {
				bindUID = true;

				sb.append(_FINDER_COLUMN_UID_UID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUID) {
					queryPos.add(UID);
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

	private static final String _FINDER_COLUMN_UID_UID_2 =
		"ldapMapping.UID = ?";

	private static final String _FINDER_COLUMN_UID_UID_3 =
		"(ldapMapping.UID IS NULL OR ldapMapping.UID = '')";

	private FinderPath _finderPathWithPaginationFindByEntEleveStructRattachId;
	private FinderPath
		_finderPathWithoutPaginationFindByEntEleveStructRattachId;
	private FinderPath _finderPathCountByEntEleveStructRattachId;

	/**
	 * Returns all the ldap mappings where EntEleveStructRattachId = &#63;.
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @return the matching ldap mappings
	 */
	@Override
	public List<LDAPMapping> findByEntEleveStructRattachId(
		String EntEleveStructRattachId) {

		return findByEntEleveStructRattachId(
			EntEleveStructRattachId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the ldap mappings where EntEleveStructRattachId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LDAPMappingModelImpl</code>.
	 * </p>
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @param start the lower bound of the range of ldap mappings
	 * @param end the upper bound of the range of ldap mappings (not inclusive)
	 * @return the range of matching ldap mappings
	 */
	@Override
	public List<LDAPMapping> findByEntEleveStructRattachId(
		String EntEleveStructRattachId, int start, int end) {

		return findByEntEleveStructRattachId(
			EntEleveStructRattachId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ldap mappings where EntEleveStructRattachId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LDAPMappingModelImpl</code>.
	 * </p>
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @param start the lower bound of the range of ldap mappings
	 * @param end the upper bound of the range of ldap mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ldap mappings
	 */
	@Override
	public List<LDAPMapping> findByEntEleveStructRattachId(
		String EntEleveStructRattachId, int start, int end,
		OrderByComparator<LDAPMapping> orderByComparator) {

		return findByEntEleveStructRattachId(
			EntEleveStructRattachId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ldap mappings where EntEleveStructRattachId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LDAPMappingModelImpl</code>.
	 * </p>
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @param start the lower bound of the range of ldap mappings
	 * @param end the upper bound of the range of ldap mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ldap mappings
	 */
	@Override
	public List<LDAPMapping> findByEntEleveStructRattachId(
		String EntEleveStructRattachId, int start, int end,
		OrderByComparator<LDAPMapping> orderByComparator,
		boolean useFinderCache) {

		EntEleveStructRattachId = Objects.toString(EntEleveStructRattachId, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByEntEleveStructRattachId;
				finderArgs = new Object[] {EntEleveStructRattachId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByEntEleveStructRattachId;
			finderArgs = new Object[] {
				EntEleveStructRattachId, start, end, orderByComparator
			};
		}

		List<LDAPMapping> list = null;

		if (useFinderCache) {
			list = (List<LDAPMapping>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LDAPMapping ldapMapping : list) {
					if (!EntEleveStructRattachId.equals(
							ldapMapping.getEntEleveStructRattachId())) {

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

			sb.append(_SQL_SELECT_LDAPMAPPING_WHERE);

			boolean bindEntEleveStructRattachId = false;

			if (EntEleveStructRattachId.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_ENTELEVESTRUCTRATTACHID_ENTELEVESTRUCTRATTACHID_3);
			}
			else {
				bindEntEleveStructRattachId = true;

				sb.append(
					_FINDER_COLUMN_ENTELEVESTRUCTRATTACHID_ENTELEVESTRUCTRATTACHID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LDAPMappingModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindEntEleveStructRattachId) {
					queryPos.add(EntEleveStructRattachId);
				}

				list = (List<LDAPMapping>)QueryUtil.list(
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
	 * Returns the first ldap mapping in the ordered set where EntEleveStructRattachId = &#63;.
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ldap mapping
	 * @throws NoSuchLDAPMappingException if a matching ldap mapping could not be found
	 */
	@Override
	public LDAPMapping findByEntEleveStructRattachId_First(
			String EntEleveStructRattachId,
			OrderByComparator<LDAPMapping> orderByComparator)
		throws NoSuchLDAPMappingException {

		LDAPMapping ldapMapping = fetchByEntEleveStructRattachId_First(
			EntEleveStructRattachId, orderByComparator);

		if (ldapMapping != null) {
			return ldapMapping;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("EntEleveStructRattachId=");
		sb.append(EntEleveStructRattachId);

		sb.append("}");

		throw new NoSuchLDAPMappingException(sb.toString());
	}

	/**
	 * Returns the first ldap mapping in the ordered set where EntEleveStructRattachId = &#63;.
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ldap mapping, or <code>null</code> if a matching ldap mapping could not be found
	 */
	@Override
	public LDAPMapping fetchByEntEleveStructRattachId_First(
		String EntEleveStructRattachId,
		OrderByComparator<LDAPMapping> orderByComparator) {

		List<LDAPMapping> list = findByEntEleveStructRattachId(
			EntEleveStructRattachId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ldap mapping in the ordered set where EntEleveStructRattachId = &#63;.
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ldap mapping
	 * @throws NoSuchLDAPMappingException if a matching ldap mapping could not be found
	 */
	@Override
	public LDAPMapping findByEntEleveStructRattachId_Last(
			String EntEleveStructRattachId,
			OrderByComparator<LDAPMapping> orderByComparator)
		throws NoSuchLDAPMappingException {

		LDAPMapping ldapMapping = fetchByEntEleveStructRattachId_Last(
			EntEleveStructRattachId, orderByComparator);

		if (ldapMapping != null) {
			return ldapMapping;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("EntEleveStructRattachId=");
		sb.append(EntEleveStructRattachId);

		sb.append("}");

		throw new NoSuchLDAPMappingException(sb.toString());
	}

	/**
	 * Returns the last ldap mapping in the ordered set where EntEleveStructRattachId = &#63;.
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ldap mapping, or <code>null</code> if a matching ldap mapping could not be found
	 */
	@Override
	public LDAPMapping fetchByEntEleveStructRattachId_Last(
		String EntEleveStructRattachId,
		OrderByComparator<LDAPMapping> orderByComparator) {

		int count = countByEntEleveStructRattachId(EntEleveStructRattachId);

		if (count == 0) {
			return null;
		}

		List<LDAPMapping> list = findByEntEleveStructRattachId(
			EntEleveStructRattachId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ldap mappings before and after the current ldap mapping in the ordered set where EntEleveStructRattachId = &#63;.
	 *
	 * @param UserId the primary key of the current ldap mapping
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ldap mapping
	 * @throws NoSuchLDAPMappingException if a ldap mapping with the primary key could not be found
	 */
	@Override
	public LDAPMapping[] findByEntEleveStructRattachId_PrevAndNext(
			long UserId, String EntEleveStructRattachId,
			OrderByComparator<LDAPMapping> orderByComparator)
		throws NoSuchLDAPMappingException {

		EntEleveStructRattachId = Objects.toString(EntEleveStructRattachId, "");

		LDAPMapping ldapMapping = findByPrimaryKey(UserId);

		Session session = null;

		try {
			session = openSession();

			LDAPMapping[] array = new LDAPMappingImpl[3];

			array[0] = getByEntEleveStructRattachId_PrevAndNext(
				session, ldapMapping, EntEleveStructRattachId,
				orderByComparator, true);

			array[1] = ldapMapping;

			array[2] = getByEntEleveStructRattachId_PrevAndNext(
				session, ldapMapping, EntEleveStructRattachId,
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

	protected LDAPMapping getByEntEleveStructRattachId_PrevAndNext(
		Session session, LDAPMapping ldapMapping,
		String EntEleveStructRattachId,
		OrderByComparator<LDAPMapping> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_LDAPMAPPING_WHERE);

		boolean bindEntEleveStructRattachId = false;

		if (EntEleveStructRattachId.isEmpty()) {
			sb.append(
				_FINDER_COLUMN_ENTELEVESTRUCTRATTACHID_ENTELEVESTRUCTRATTACHID_3);
		}
		else {
			bindEntEleveStructRattachId = true;

			sb.append(
				_FINDER_COLUMN_ENTELEVESTRUCTRATTACHID_ENTELEVESTRUCTRATTACHID_2);
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
			sb.append(LDAPMappingModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindEntEleveStructRattachId) {
			queryPos.add(EntEleveStructRattachId);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(ldapMapping)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LDAPMapping> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ldap mappings where EntEleveStructRattachId = &#63; from the database.
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 */
	@Override
	public void removeByEntEleveStructRattachId(
		String EntEleveStructRattachId) {

		for (LDAPMapping ldapMapping :
				findByEntEleveStructRattachId(
					EntEleveStructRattachId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(ldapMapping);
		}
	}

	/**
	 * Returns the number of ldap mappings where EntEleveStructRattachId = &#63;.
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @return the number of matching ldap mappings
	 */
	@Override
	public int countByEntEleveStructRattachId(String EntEleveStructRattachId) {
		EntEleveStructRattachId = Objects.toString(EntEleveStructRattachId, "");

		FinderPath finderPath = _finderPathCountByEntEleveStructRattachId;

		Object[] finderArgs = new Object[] {EntEleveStructRattachId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LDAPMAPPING_WHERE);

			boolean bindEntEleveStructRattachId = false;

			if (EntEleveStructRattachId.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_ENTELEVESTRUCTRATTACHID_ENTELEVESTRUCTRATTACHID_3);
			}
			else {
				bindEntEleveStructRattachId = true;

				sb.append(
					_FINDER_COLUMN_ENTELEVESTRUCTRATTACHID_ENTELEVESTRUCTRATTACHID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindEntEleveStructRattachId) {
					queryPos.add(EntEleveStructRattachId);
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

	private static final String
		_FINDER_COLUMN_ENTELEVESTRUCTRATTACHID_ENTELEVESTRUCTRATTACHID_2 =
			"ldapMapping.EntEleveStructRattachId = ?";

	private static final String
		_FINDER_COLUMN_ENTELEVESTRUCTRATTACHID_ENTELEVESTRUCTRATTACHID_3 =
			"(ldapMapping.EntEleveStructRattachId IS NULL OR ldapMapping.EntEleveStructRattachId = '')";

	private FinderPath _finderPathFetchByEntPersonJointure;
	private FinderPath _finderPathCountByEntPersonJointure;

	/**
	 * Returns the ldap mapping where EntPersonJointure = &#63; or throws a <code>NoSuchLDAPMappingException</code> if it could not be found.
	 *
	 * @param EntPersonJointure the ent person jointure
	 * @return the matching ldap mapping
	 * @throws NoSuchLDAPMappingException if a matching ldap mapping could not be found
	 */
	@Override
	public LDAPMapping findByEntPersonJointure(String EntPersonJointure)
		throws NoSuchLDAPMappingException {

		LDAPMapping ldapMapping = fetchByEntPersonJointure(EntPersonJointure);

		if (ldapMapping == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("EntPersonJointure=");
			sb.append(EntPersonJointure);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchLDAPMappingException(sb.toString());
		}

		return ldapMapping;
	}

	/**
	 * Returns the ldap mapping where EntPersonJointure = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param EntPersonJointure the ent person jointure
	 * @return the matching ldap mapping, or <code>null</code> if a matching ldap mapping could not be found
	 */
	@Override
	public LDAPMapping fetchByEntPersonJointure(String EntPersonJointure) {
		return fetchByEntPersonJointure(EntPersonJointure, true);
	}

	/**
	 * Returns the ldap mapping where EntPersonJointure = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param EntPersonJointure the ent person jointure
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ldap mapping, or <code>null</code> if a matching ldap mapping could not be found
	 */
	@Override
	public LDAPMapping fetchByEntPersonJointure(
		String EntPersonJointure, boolean useFinderCache) {

		EntPersonJointure = Objects.toString(EntPersonJointure, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {EntPersonJointure};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByEntPersonJointure, finderArgs, this);
		}

		if (result instanceof LDAPMapping) {
			LDAPMapping ldapMapping = (LDAPMapping)result;

			if (!Objects.equals(
					EntPersonJointure, ldapMapping.getEntPersonJointure())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_LDAPMAPPING_WHERE);

			boolean bindEntPersonJointure = false;

			if (EntPersonJointure.isEmpty()) {
				sb.append(_FINDER_COLUMN_ENTPERSONJOINTURE_ENTPERSONJOINTURE_3);
			}
			else {
				bindEntPersonJointure = true;

				sb.append(_FINDER_COLUMN_ENTPERSONJOINTURE_ENTPERSONJOINTURE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindEntPersonJointure) {
					queryPos.add(EntPersonJointure);
				}

				List<LDAPMapping> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByEntPersonJointure, finderArgs,
							list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {EntPersonJointure};
							}

							_log.warn(
								"LDAPMappingPersistenceImpl.fetchByEntPersonJointure(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					LDAPMapping ldapMapping = list.get(0);

					result = ldapMapping;

					cacheResult(ldapMapping);
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
			return (LDAPMapping)result;
		}
	}

	/**
	 * Removes the ldap mapping where EntPersonJointure = &#63; from the database.
	 *
	 * @param EntPersonJointure the ent person jointure
	 * @return the ldap mapping that was removed
	 */
	@Override
	public LDAPMapping removeByEntPersonJointure(String EntPersonJointure)
		throws NoSuchLDAPMappingException {

		LDAPMapping ldapMapping = findByEntPersonJointure(EntPersonJointure);

		return remove(ldapMapping);
	}

	/**
	 * Returns the number of ldap mappings where EntPersonJointure = &#63;.
	 *
	 * @param EntPersonJointure the ent person jointure
	 * @return the number of matching ldap mappings
	 */
	@Override
	public int countByEntPersonJointure(String EntPersonJointure) {
		EntPersonJointure = Objects.toString(EntPersonJointure, "");

		FinderPath finderPath = _finderPathCountByEntPersonJointure;

		Object[] finderArgs = new Object[] {EntPersonJointure};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LDAPMAPPING_WHERE);

			boolean bindEntPersonJointure = false;

			if (EntPersonJointure.isEmpty()) {
				sb.append(_FINDER_COLUMN_ENTPERSONJOINTURE_ENTPERSONJOINTURE_3);
			}
			else {
				bindEntPersonJointure = true;

				sb.append(_FINDER_COLUMN_ENTPERSONJOINTURE_ENTPERSONJOINTURE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindEntPersonJointure) {
					queryPos.add(EntPersonJointure);
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

	private static final String
		_FINDER_COLUMN_ENTPERSONJOINTURE_ENTPERSONJOINTURE_2 =
			"ldapMapping.EntPersonJointure = ?";

	private static final String
		_FINDER_COLUMN_ENTPERSONJOINTURE_ENTPERSONJOINTURE_3 =
			"(ldapMapping.EntPersonJointure IS NULL OR ldapMapping.EntPersonJointure = '')";

	public LDAPMappingPersistenceImpl() {
		setModelClass(LDAPMapping.class);

		setModelImplClass(LDAPMappingImpl.class);
		setModelPKClass(long.class);

		setTable(LDAPMappingTable.INSTANCE);
	}

	/**
	 * Caches the ldap mapping in the entity cache if it is enabled.
	 *
	 * @param ldapMapping the ldap mapping
	 */
	@Override
	public void cacheResult(LDAPMapping ldapMapping) {
		entityCache.putResult(
			LDAPMappingImpl.class, ldapMapping.getPrimaryKey(), ldapMapping);

		finderCache.putResult(
			_finderPathFetchByUID, new Object[] {ldapMapping.getUID()},
			ldapMapping);

		finderCache.putResult(
			_finderPathFetchByEntPersonJointure,
			new Object[] {ldapMapping.getEntPersonJointure()}, ldapMapping);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the ldap mappings in the entity cache if it is enabled.
	 *
	 * @param ldapMappings the ldap mappings
	 */
	@Override
	public void cacheResult(List<LDAPMapping> ldapMappings) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (ldapMappings.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (LDAPMapping ldapMapping : ldapMappings) {
			if (entityCache.getResult(
					LDAPMappingImpl.class, ldapMapping.getPrimaryKey()) ==
						null) {

				cacheResult(ldapMapping);
			}
		}
	}

	/**
	 * Clears the cache for all ldap mappings.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LDAPMappingImpl.class);

		finderCache.clearCache(LDAPMappingImpl.class);
	}

	/**
	 * Clears the cache for the ldap mapping.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LDAPMapping ldapMapping) {
		entityCache.removeResult(LDAPMappingImpl.class, ldapMapping);
	}

	@Override
	public void clearCache(List<LDAPMapping> ldapMappings) {
		for (LDAPMapping ldapMapping : ldapMappings) {
			entityCache.removeResult(LDAPMappingImpl.class, ldapMapping);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(LDAPMappingImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(LDAPMappingImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		LDAPMappingModelImpl ldapMappingModelImpl) {

		Object[] args = new Object[] {ldapMappingModelImpl.getUID()};

		finderCache.putResult(_finderPathCountByUID, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByUID, args, ldapMappingModelImpl);

		args = new Object[] {ldapMappingModelImpl.getEntPersonJointure()};

		finderCache.putResult(
			_finderPathCountByEntPersonJointure, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByEntPersonJointure, args, ldapMappingModelImpl);
	}

	/**
	 * Creates a new ldap mapping with the primary key. Does not add the ldap mapping to the database.
	 *
	 * @param UserId the primary key for the new ldap mapping
	 * @return the new ldap mapping
	 */
	@Override
	public LDAPMapping create(long UserId) {
		LDAPMapping ldapMapping = new LDAPMappingImpl();

		ldapMapping.setNew(true);
		ldapMapping.setPrimaryKey(UserId);

		return ldapMapping;
	}

	/**
	 * Removes the ldap mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param UserId the primary key of the ldap mapping
	 * @return the ldap mapping that was removed
	 * @throws NoSuchLDAPMappingException if a ldap mapping with the primary key could not be found
	 */
	@Override
	public LDAPMapping remove(long UserId) throws NoSuchLDAPMappingException {
		return remove((Serializable)UserId);
	}

	/**
	 * Removes the ldap mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ldap mapping
	 * @return the ldap mapping that was removed
	 * @throws NoSuchLDAPMappingException if a ldap mapping with the primary key could not be found
	 */
	@Override
	public LDAPMapping remove(Serializable primaryKey)
		throws NoSuchLDAPMappingException {

		Session session = null;

		try {
			session = openSession();

			LDAPMapping ldapMapping = (LDAPMapping)session.get(
				LDAPMappingImpl.class, primaryKey);

			if (ldapMapping == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLDAPMappingException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(ldapMapping);
		}
		catch (NoSuchLDAPMappingException noSuchEntityException) {
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
	protected LDAPMapping removeImpl(LDAPMapping ldapMapping) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ldapMapping)) {
				ldapMapping = (LDAPMapping)session.get(
					LDAPMappingImpl.class, ldapMapping.getPrimaryKeyObj());
			}

			if (ldapMapping != null) {
				session.delete(ldapMapping);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (ldapMapping != null) {
			clearCache(ldapMapping);
		}

		return ldapMapping;
	}

	@Override
	public LDAPMapping updateImpl(LDAPMapping ldapMapping) {
		boolean isNew = ldapMapping.isNew();

		if (!(ldapMapping instanceof LDAPMappingModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(ldapMapping.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(ldapMapping);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in ldapMapping proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom LDAPMapping implementation " +
					ldapMapping.getClass());
		}

		LDAPMappingModelImpl ldapMappingModelImpl =
			(LDAPMappingModelImpl)ldapMapping;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(ldapMapping);
			}
			else {
				ldapMapping = (LDAPMapping)session.merge(ldapMapping);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			LDAPMappingImpl.class, ldapMappingModelImpl, false, true);

		cacheUniqueFindersCache(ldapMappingModelImpl);

		if (isNew) {
			ldapMapping.setNew(false);
		}

		ldapMapping.resetOriginalValues();

		return ldapMapping;
	}

	/**
	 * Returns the ldap mapping with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ldap mapping
	 * @return the ldap mapping
	 * @throws NoSuchLDAPMappingException if a ldap mapping with the primary key could not be found
	 */
	@Override
	public LDAPMapping findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLDAPMappingException {

		LDAPMapping ldapMapping = fetchByPrimaryKey(primaryKey);

		if (ldapMapping == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLDAPMappingException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return ldapMapping;
	}

	/**
	 * Returns the ldap mapping with the primary key or throws a <code>NoSuchLDAPMappingException</code> if it could not be found.
	 *
	 * @param UserId the primary key of the ldap mapping
	 * @return the ldap mapping
	 * @throws NoSuchLDAPMappingException if a ldap mapping with the primary key could not be found
	 */
	@Override
	public LDAPMapping findByPrimaryKey(long UserId)
		throws NoSuchLDAPMappingException {

		return findByPrimaryKey((Serializable)UserId);
	}

	/**
	 * Returns the ldap mapping with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param UserId the primary key of the ldap mapping
	 * @return the ldap mapping, or <code>null</code> if a ldap mapping with the primary key could not be found
	 */
	@Override
	public LDAPMapping fetchByPrimaryKey(long UserId) {
		return fetchByPrimaryKey((Serializable)UserId);
	}

	/**
	 * Returns all the ldap mappings.
	 *
	 * @return the ldap mappings
	 */
	@Override
	public List<LDAPMapping> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ldap mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LDAPMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ldap mappings
	 * @param end the upper bound of the range of ldap mappings (not inclusive)
	 * @return the range of ldap mappings
	 */
	@Override
	public List<LDAPMapping> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ldap mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LDAPMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ldap mappings
	 * @param end the upper bound of the range of ldap mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ldap mappings
	 */
	@Override
	public List<LDAPMapping> findAll(
		int start, int end, OrderByComparator<LDAPMapping> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ldap mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LDAPMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ldap mappings
	 * @param end the upper bound of the range of ldap mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of ldap mappings
	 */
	@Override
	public List<LDAPMapping> findAll(
		int start, int end, OrderByComparator<LDAPMapping> orderByComparator,
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

		List<LDAPMapping> list = null;

		if (useFinderCache) {
			list = (List<LDAPMapping>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_LDAPMAPPING);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_LDAPMAPPING;

				sql = sql.concat(LDAPMappingModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<LDAPMapping>)QueryUtil.list(
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
	 * Removes all the ldap mappings from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LDAPMapping ldapMapping : findAll()) {
			remove(ldapMapping);
		}
	}

	/**
	 * Returns the number of ldap mappings.
	 *
	 * @return the number of ldap mappings
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_LDAPMAPPING);

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
		return "UserId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_LDAPMAPPING;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LDAPMappingModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ldap mapping persistence.
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

		_finderPathFetchByUID = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByUID",
			new String[] {String.class.getName()}, new String[] {"UID"}, true);

		_finderPathCountByUID = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUID",
			new String[] {String.class.getName()}, new String[] {"UID"}, false);

		_finderPathWithPaginationFindByEntEleveStructRattachId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByEntEleveStructRattachId",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"EntEleveStructRattachId"}, true);

		_finderPathWithoutPaginationFindByEntEleveStructRattachId =
			new FinderPath(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByEntEleveStructRattachId",
				new String[] {String.class.getName()},
				new String[] {"EntEleveStructRattachId"}, true);

		_finderPathCountByEntEleveStructRattachId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByEntEleveStructRattachId",
			new String[] {String.class.getName()},
			new String[] {"EntEleveStructRattachId"}, false);

		_finderPathFetchByEntPersonJointure = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByEntPersonJointure",
			new String[] {String.class.getName()},
			new String[] {"EntPersonJointure"}, true);

		_finderPathCountByEntPersonJointure = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByEntPersonJointure", new String[] {String.class.getName()},
			new String[] {"EntPersonJointure"}, false);

		_setLDAPMappingUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setLDAPMappingUtilPersistence(null);

		entityCache.removeCache(LDAPMappingImpl.class.getName());
	}

	private void _setLDAPMappingUtilPersistence(
		LDAPMappingPersistence ldapMappingPersistence) {

		try {
			Field field = LDAPMappingUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, ldapMappingPersistence);
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

	private static final String _SQL_SELECT_LDAPMAPPING =
		"SELECT ldapMapping FROM LDAPMapping ldapMapping";

	private static final String _SQL_SELECT_LDAPMAPPING_WHERE =
		"SELECT ldapMapping FROM LDAPMapping ldapMapping WHERE ";

	private static final String _SQL_COUNT_LDAPMAPPING =
		"SELECT COUNT(ldapMapping) FROM LDAPMapping ldapMapping";

	private static final String _SQL_COUNT_LDAPMAPPING_WHERE =
		"SELECT COUNT(ldapMapping) FROM LDAPMapping ldapMapping WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "ldapMapping.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No LDAPMapping exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No LDAPMapping exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		LDAPMappingPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}