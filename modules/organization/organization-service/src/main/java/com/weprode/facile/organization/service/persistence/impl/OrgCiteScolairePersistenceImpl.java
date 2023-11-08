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

package com.weprode.facile.organization.service.persistence.impl;

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

import com.weprode.facile.organization.exception.NoSuchOrgCiteScolaireException;
import com.weprode.facile.organization.model.OrgCiteScolaire;
import com.weprode.facile.organization.model.OrgCiteScolaireTable;
import com.weprode.facile.organization.model.impl.OrgCiteScolaireImpl;
import com.weprode.facile.organization.model.impl.OrgCiteScolaireModelImpl;
import com.weprode.facile.organization.service.persistence.OrgCiteScolairePersistence;
import com.weprode.facile.organization.service.persistence.OrgCiteScolaireUtil;
import com.weprode.facile.organization.service.persistence.impl.constants.OrganizationPersistenceConstants;

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
 * The persistence implementation for the org cite scolaire service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marc Salvat
 * @generated
 */
@Component(service = {OrgCiteScolairePersistence.class, BasePersistence.class})
public class OrgCiteScolairePersistenceImpl
	extends BasePersistenceImpl<OrgCiteScolaire>
	implements OrgCiteScolairePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>OrgCiteScolaireUtil</code> to access the org cite scolaire persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		OrgCiteScolaireImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByparentENTStructureUAI;
	private FinderPath _finderPathWithoutPaginationFindByparentENTStructureUAI;
	private FinderPath _finderPathCountByparentENTStructureUAI;

	/**
	 * Returns all the org cite scolaires where parentENTStructureUAI = &#63;.
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @return the matching org cite scolaires
	 */
	@Override
	public List<OrgCiteScolaire> findByparentENTStructureUAI(
		String parentENTStructureUAI) {

		return findByparentENTStructureUAI(
			parentENTStructureUAI, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the org cite scolaires where parentENTStructureUAI = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgCiteScolaireModelImpl</code>.
	 * </p>
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @param start the lower bound of the range of org cite scolaires
	 * @param end the upper bound of the range of org cite scolaires (not inclusive)
	 * @return the range of matching org cite scolaires
	 */
	@Override
	public List<OrgCiteScolaire> findByparentENTStructureUAI(
		String parentENTStructureUAI, int start, int end) {

		return findByparentENTStructureUAI(
			parentENTStructureUAI, start, end, null);
	}

	/**
	 * Returns an ordered range of all the org cite scolaires where parentENTStructureUAI = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgCiteScolaireModelImpl</code>.
	 * </p>
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @param start the lower bound of the range of org cite scolaires
	 * @param end the upper bound of the range of org cite scolaires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching org cite scolaires
	 */
	@Override
	public List<OrgCiteScolaire> findByparentENTStructureUAI(
		String parentENTStructureUAI, int start, int end,
		OrderByComparator<OrgCiteScolaire> orderByComparator) {

		return findByparentENTStructureUAI(
			parentENTStructureUAI, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the org cite scolaires where parentENTStructureUAI = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgCiteScolaireModelImpl</code>.
	 * </p>
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @param start the lower bound of the range of org cite scolaires
	 * @param end the upper bound of the range of org cite scolaires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching org cite scolaires
	 */
	@Override
	public List<OrgCiteScolaire> findByparentENTStructureUAI(
		String parentENTStructureUAI, int start, int end,
		OrderByComparator<OrgCiteScolaire> orderByComparator,
		boolean useFinderCache) {

		parentENTStructureUAI = Objects.toString(parentENTStructureUAI, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByparentENTStructureUAI;
				finderArgs = new Object[] {parentENTStructureUAI};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByparentENTStructureUAI;
			finderArgs = new Object[] {
				parentENTStructureUAI, start, end, orderByComparator
			};
		}

		List<OrgCiteScolaire> list = null;

		if (useFinderCache) {
			list = (List<OrgCiteScolaire>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OrgCiteScolaire orgCiteScolaire : list) {
					if (!parentENTStructureUAI.equals(
							orgCiteScolaire.getParentENTStructureUAI())) {

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

			sb.append(_SQL_SELECT_ORGCITESCOLAIRE_WHERE);

			boolean bindParentENTStructureUAI = false;

			if (parentENTStructureUAI.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_PARENTENTSTRUCTUREUAI_PARENTENTSTRUCTUREUAI_3);
			}
			else {
				bindParentENTStructureUAI = true;

				sb.append(
					_FINDER_COLUMN_PARENTENTSTRUCTUREUAI_PARENTENTSTRUCTUREUAI_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(OrgCiteScolaireModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindParentENTStructureUAI) {
					queryPos.add(parentENTStructureUAI);
				}

				list = (List<OrgCiteScolaire>)QueryUtil.list(
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
	 * Returns the first org cite scolaire in the ordered set where parentENTStructureUAI = &#63;.
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org cite scolaire
	 * @throws NoSuchOrgCiteScolaireException if a matching org cite scolaire could not be found
	 */
	@Override
	public OrgCiteScolaire findByparentENTStructureUAI_First(
			String parentENTStructureUAI,
			OrderByComparator<OrgCiteScolaire> orderByComparator)
		throws NoSuchOrgCiteScolaireException {

		OrgCiteScolaire orgCiteScolaire = fetchByparentENTStructureUAI_First(
			parentENTStructureUAI, orderByComparator);

		if (orgCiteScolaire != null) {
			return orgCiteScolaire;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("parentENTStructureUAI=");
		sb.append(parentENTStructureUAI);

		sb.append("}");

		throw new NoSuchOrgCiteScolaireException(sb.toString());
	}

	/**
	 * Returns the first org cite scolaire in the ordered set where parentENTStructureUAI = &#63;.
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org cite scolaire, or <code>null</code> if a matching org cite scolaire could not be found
	 */
	@Override
	public OrgCiteScolaire fetchByparentENTStructureUAI_First(
		String parentENTStructureUAI,
		OrderByComparator<OrgCiteScolaire> orderByComparator) {

		List<OrgCiteScolaire> list = findByparentENTStructureUAI(
			parentENTStructureUAI, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last org cite scolaire in the ordered set where parentENTStructureUAI = &#63;.
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org cite scolaire
	 * @throws NoSuchOrgCiteScolaireException if a matching org cite scolaire could not be found
	 */
	@Override
	public OrgCiteScolaire findByparentENTStructureUAI_Last(
			String parentENTStructureUAI,
			OrderByComparator<OrgCiteScolaire> orderByComparator)
		throws NoSuchOrgCiteScolaireException {

		OrgCiteScolaire orgCiteScolaire = fetchByparentENTStructureUAI_Last(
			parentENTStructureUAI, orderByComparator);

		if (orgCiteScolaire != null) {
			return orgCiteScolaire;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("parentENTStructureUAI=");
		sb.append(parentENTStructureUAI);

		sb.append("}");

		throw new NoSuchOrgCiteScolaireException(sb.toString());
	}

	/**
	 * Returns the last org cite scolaire in the ordered set where parentENTStructureUAI = &#63;.
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org cite scolaire, or <code>null</code> if a matching org cite scolaire could not be found
	 */
	@Override
	public OrgCiteScolaire fetchByparentENTStructureUAI_Last(
		String parentENTStructureUAI,
		OrderByComparator<OrgCiteScolaire> orderByComparator) {

		int count = countByparentENTStructureUAI(parentENTStructureUAI);

		if (count == 0) {
			return null;
		}

		List<OrgCiteScolaire> list = findByparentENTStructureUAI(
			parentENTStructureUAI, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the org cite scolaires before and after the current org cite scolaire in the ordered set where parentENTStructureUAI = &#63;.
	 *
	 * @param childENTStructureUAI the primary key of the current org cite scolaire
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next org cite scolaire
	 * @throws NoSuchOrgCiteScolaireException if a org cite scolaire with the primary key could not be found
	 */
	@Override
	public OrgCiteScolaire[] findByparentENTStructureUAI_PrevAndNext(
			String childENTStructureUAI, String parentENTStructureUAI,
			OrderByComparator<OrgCiteScolaire> orderByComparator)
		throws NoSuchOrgCiteScolaireException {

		parentENTStructureUAI = Objects.toString(parentENTStructureUAI, "");

		OrgCiteScolaire orgCiteScolaire = findByPrimaryKey(
			childENTStructureUAI);

		Session session = null;

		try {
			session = openSession();

			OrgCiteScolaire[] array = new OrgCiteScolaireImpl[3];

			array[0] = getByparentENTStructureUAI_PrevAndNext(
				session, orgCiteScolaire, parentENTStructureUAI,
				orderByComparator, true);

			array[1] = orgCiteScolaire;

			array[2] = getByparentENTStructureUAI_PrevAndNext(
				session, orgCiteScolaire, parentENTStructureUAI,
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

	protected OrgCiteScolaire getByparentENTStructureUAI_PrevAndNext(
		Session session, OrgCiteScolaire orgCiteScolaire,
		String parentENTStructureUAI,
		OrderByComparator<OrgCiteScolaire> orderByComparator,
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

		sb.append(_SQL_SELECT_ORGCITESCOLAIRE_WHERE);

		boolean bindParentENTStructureUAI = false;

		if (parentENTStructureUAI.isEmpty()) {
			sb.append(
				_FINDER_COLUMN_PARENTENTSTRUCTUREUAI_PARENTENTSTRUCTUREUAI_3);
		}
		else {
			bindParentENTStructureUAI = true;

			sb.append(
				_FINDER_COLUMN_PARENTENTSTRUCTUREUAI_PARENTENTSTRUCTUREUAI_2);
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
			sb.append(OrgCiteScolaireModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindParentENTStructureUAI) {
			queryPos.add(parentENTStructureUAI);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						orgCiteScolaire)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<OrgCiteScolaire> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the org cite scolaires where parentENTStructureUAI = &#63; from the database.
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 */
	@Override
	public void removeByparentENTStructureUAI(String parentENTStructureUAI) {
		for (OrgCiteScolaire orgCiteScolaire :
				findByparentENTStructureUAI(
					parentENTStructureUAI, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(orgCiteScolaire);
		}
	}

	/**
	 * Returns the number of org cite scolaires where parentENTStructureUAI = &#63;.
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @return the number of matching org cite scolaires
	 */
	@Override
	public int countByparentENTStructureUAI(String parentENTStructureUAI) {
		parentENTStructureUAI = Objects.toString(parentENTStructureUAI, "");

		FinderPath finderPath = _finderPathCountByparentENTStructureUAI;

		Object[] finderArgs = new Object[] {parentENTStructureUAI};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ORGCITESCOLAIRE_WHERE);

			boolean bindParentENTStructureUAI = false;

			if (parentENTStructureUAI.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_PARENTENTSTRUCTUREUAI_PARENTENTSTRUCTUREUAI_3);
			}
			else {
				bindParentENTStructureUAI = true;

				sb.append(
					_FINDER_COLUMN_PARENTENTSTRUCTUREUAI_PARENTENTSTRUCTUREUAI_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindParentENTStructureUAI) {
					queryPos.add(parentENTStructureUAI);
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
		_FINDER_COLUMN_PARENTENTSTRUCTUREUAI_PARENTENTSTRUCTUREUAI_2 =
			"orgCiteScolaire.parentENTStructureUAI = ?";

	private static final String
		_FINDER_COLUMN_PARENTENTSTRUCTUREUAI_PARENTENTSTRUCTUREUAI_3 =
			"(orgCiteScolaire.parentENTStructureUAI IS NULL OR orgCiteScolaire.parentENTStructureUAI = '')";

	public OrgCiteScolairePersistenceImpl() {
		setModelClass(OrgCiteScolaire.class);

		setModelImplClass(OrgCiteScolaireImpl.class);
		setModelPKClass(String.class);

		setTable(OrgCiteScolaireTable.INSTANCE);
	}

	/**
	 * Caches the org cite scolaire in the entity cache if it is enabled.
	 *
	 * @param orgCiteScolaire the org cite scolaire
	 */
	@Override
	public void cacheResult(OrgCiteScolaire orgCiteScolaire) {
		entityCache.putResult(
			OrgCiteScolaireImpl.class, orgCiteScolaire.getPrimaryKey(),
			orgCiteScolaire);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the org cite scolaires in the entity cache if it is enabled.
	 *
	 * @param orgCiteScolaires the org cite scolaires
	 */
	@Override
	public void cacheResult(List<OrgCiteScolaire> orgCiteScolaires) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (orgCiteScolaires.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (OrgCiteScolaire orgCiteScolaire : orgCiteScolaires) {
			if (entityCache.getResult(
					OrgCiteScolaireImpl.class,
					orgCiteScolaire.getPrimaryKey()) == null) {

				cacheResult(orgCiteScolaire);
			}
		}
	}

	/**
	 * Clears the cache for all org cite scolaires.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OrgCiteScolaireImpl.class);

		finderCache.clearCache(OrgCiteScolaireImpl.class);
	}

	/**
	 * Clears the cache for the org cite scolaire.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OrgCiteScolaire orgCiteScolaire) {
		entityCache.removeResult(OrgCiteScolaireImpl.class, orgCiteScolaire);
	}

	@Override
	public void clearCache(List<OrgCiteScolaire> orgCiteScolaires) {
		for (OrgCiteScolaire orgCiteScolaire : orgCiteScolaires) {
			entityCache.removeResult(
				OrgCiteScolaireImpl.class, orgCiteScolaire);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(OrgCiteScolaireImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(OrgCiteScolaireImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new org cite scolaire with the primary key. Does not add the org cite scolaire to the database.
	 *
	 * @param childENTStructureUAI the primary key for the new org cite scolaire
	 * @return the new org cite scolaire
	 */
	@Override
	public OrgCiteScolaire create(String childENTStructureUAI) {
		OrgCiteScolaire orgCiteScolaire = new OrgCiteScolaireImpl();

		orgCiteScolaire.setNew(true);
		orgCiteScolaire.setPrimaryKey(childENTStructureUAI);

		return orgCiteScolaire;
	}

	/**
	 * Removes the org cite scolaire with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param childENTStructureUAI the primary key of the org cite scolaire
	 * @return the org cite scolaire that was removed
	 * @throws NoSuchOrgCiteScolaireException if a org cite scolaire with the primary key could not be found
	 */
	@Override
	public OrgCiteScolaire remove(String childENTStructureUAI)
		throws NoSuchOrgCiteScolaireException {

		return remove((Serializable)childENTStructureUAI);
	}

	/**
	 * Removes the org cite scolaire with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the org cite scolaire
	 * @return the org cite scolaire that was removed
	 * @throws NoSuchOrgCiteScolaireException if a org cite scolaire with the primary key could not be found
	 */
	@Override
	public OrgCiteScolaire remove(Serializable primaryKey)
		throws NoSuchOrgCiteScolaireException {

		Session session = null;

		try {
			session = openSession();

			OrgCiteScolaire orgCiteScolaire = (OrgCiteScolaire)session.get(
				OrgCiteScolaireImpl.class, primaryKey);

			if (orgCiteScolaire == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOrgCiteScolaireException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(orgCiteScolaire);
		}
		catch (NoSuchOrgCiteScolaireException noSuchEntityException) {
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
	protected OrgCiteScolaire removeImpl(OrgCiteScolaire orgCiteScolaire) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(orgCiteScolaire)) {
				orgCiteScolaire = (OrgCiteScolaire)session.get(
					OrgCiteScolaireImpl.class,
					orgCiteScolaire.getPrimaryKeyObj());
			}

			if (orgCiteScolaire != null) {
				session.delete(orgCiteScolaire);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (orgCiteScolaire != null) {
			clearCache(orgCiteScolaire);
		}

		return orgCiteScolaire;
	}

	@Override
	public OrgCiteScolaire updateImpl(OrgCiteScolaire orgCiteScolaire) {
		boolean isNew = orgCiteScolaire.isNew();

		if (!(orgCiteScolaire instanceof OrgCiteScolaireModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(orgCiteScolaire.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					orgCiteScolaire);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in orgCiteScolaire proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom OrgCiteScolaire implementation " +
					orgCiteScolaire.getClass());
		}

		OrgCiteScolaireModelImpl orgCiteScolaireModelImpl =
			(OrgCiteScolaireModelImpl)orgCiteScolaire;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(orgCiteScolaire);
			}
			else {
				orgCiteScolaire = (OrgCiteScolaire)session.merge(
					orgCiteScolaire);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			OrgCiteScolaireImpl.class, orgCiteScolaireModelImpl, false, true);

		if (isNew) {
			orgCiteScolaire.setNew(false);
		}

		orgCiteScolaire.resetOriginalValues();

		return orgCiteScolaire;
	}

	/**
	 * Returns the org cite scolaire with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the org cite scolaire
	 * @return the org cite scolaire
	 * @throws NoSuchOrgCiteScolaireException if a org cite scolaire with the primary key could not be found
	 */
	@Override
	public OrgCiteScolaire findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOrgCiteScolaireException {

		OrgCiteScolaire orgCiteScolaire = fetchByPrimaryKey(primaryKey);

		if (orgCiteScolaire == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOrgCiteScolaireException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return orgCiteScolaire;
	}

	/**
	 * Returns the org cite scolaire with the primary key or throws a <code>NoSuchOrgCiteScolaireException</code> if it could not be found.
	 *
	 * @param childENTStructureUAI the primary key of the org cite scolaire
	 * @return the org cite scolaire
	 * @throws NoSuchOrgCiteScolaireException if a org cite scolaire with the primary key could not be found
	 */
	@Override
	public OrgCiteScolaire findByPrimaryKey(String childENTStructureUAI)
		throws NoSuchOrgCiteScolaireException {

		return findByPrimaryKey((Serializable)childENTStructureUAI);
	}

	/**
	 * Returns the org cite scolaire with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param childENTStructureUAI the primary key of the org cite scolaire
	 * @return the org cite scolaire, or <code>null</code> if a org cite scolaire with the primary key could not be found
	 */
	@Override
	public OrgCiteScolaire fetchByPrimaryKey(String childENTStructureUAI) {
		return fetchByPrimaryKey((Serializable)childENTStructureUAI);
	}

	/**
	 * Returns all the org cite scolaires.
	 *
	 * @return the org cite scolaires
	 */
	@Override
	public List<OrgCiteScolaire> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the org cite scolaires.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgCiteScolaireModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org cite scolaires
	 * @param end the upper bound of the range of org cite scolaires (not inclusive)
	 * @return the range of org cite scolaires
	 */
	@Override
	public List<OrgCiteScolaire> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the org cite scolaires.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgCiteScolaireModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org cite scolaires
	 * @param end the upper bound of the range of org cite scolaires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of org cite scolaires
	 */
	@Override
	public List<OrgCiteScolaire> findAll(
		int start, int end,
		OrderByComparator<OrgCiteScolaire> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the org cite scolaires.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgCiteScolaireModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org cite scolaires
	 * @param end the upper bound of the range of org cite scolaires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of org cite scolaires
	 */
	@Override
	public List<OrgCiteScolaire> findAll(
		int start, int end,
		OrderByComparator<OrgCiteScolaire> orderByComparator,
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

		List<OrgCiteScolaire> list = null;

		if (useFinderCache) {
			list = (List<OrgCiteScolaire>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ORGCITESCOLAIRE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ORGCITESCOLAIRE;

				sql = sql.concat(OrgCiteScolaireModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<OrgCiteScolaire>)QueryUtil.list(
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
	 * Removes all the org cite scolaires from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OrgCiteScolaire orgCiteScolaire : findAll()) {
			remove(orgCiteScolaire);
		}
	}

	/**
	 * Returns the number of org cite scolaires.
	 *
	 * @return the number of org cite scolaires
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ORGCITESCOLAIRE);

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
		return "childENTStructureUAI";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ORGCITESCOLAIRE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return OrgCiteScolaireModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the org cite scolaire persistence.
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

		_finderPathWithPaginationFindByparentENTStructureUAI = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByparentENTStructureUAI",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"parentENTStructureUAI"}, true);

		_finderPathWithoutPaginationFindByparentENTStructureUAI =
			new FinderPath(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByparentENTStructureUAI",
				new String[] {String.class.getName()},
				new String[] {"parentENTStructureUAI"}, true);

		_finderPathCountByparentENTStructureUAI = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByparentENTStructureUAI",
			new String[] {String.class.getName()},
			new String[] {"parentENTStructureUAI"}, false);

		_setOrgCiteScolaireUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setOrgCiteScolaireUtilPersistence(null);

		entityCache.removeCache(OrgCiteScolaireImpl.class.getName());
	}

	private void _setOrgCiteScolaireUtilPersistence(
		OrgCiteScolairePersistence orgCiteScolairePersistence) {

		try {
			Field field = OrgCiteScolaireUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, orgCiteScolairePersistence);
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

	private static final String _SQL_SELECT_ORGCITESCOLAIRE =
		"SELECT orgCiteScolaire FROM OrgCiteScolaire orgCiteScolaire";

	private static final String _SQL_SELECT_ORGCITESCOLAIRE_WHERE =
		"SELECT orgCiteScolaire FROM OrgCiteScolaire orgCiteScolaire WHERE ";

	private static final String _SQL_COUNT_ORGCITESCOLAIRE =
		"SELECT COUNT(orgCiteScolaire) FROM OrgCiteScolaire orgCiteScolaire";

	private static final String _SQL_COUNT_ORGCITESCOLAIRE_WHERE =
		"SELECT COUNT(orgCiteScolaire) FROM OrgCiteScolaire orgCiteScolaire WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "orgCiteScolaire.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No OrgCiteScolaire exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No OrgCiteScolaire exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		OrgCiteScolairePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}