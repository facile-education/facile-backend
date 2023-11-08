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
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.facile.organization.exception.NoSuchOrgMappingException;
import com.weprode.facile.organization.model.OrgMapping;
import com.weprode.facile.organization.model.OrgMappingTable;
import com.weprode.facile.organization.model.impl.OrgMappingImpl;
import com.weprode.facile.organization.model.impl.OrgMappingModelImpl;
import com.weprode.facile.organization.service.persistence.OrgMappingPersistence;
import com.weprode.facile.organization.service.persistence.OrgMappingUtil;
import com.weprode.facile.organization.service.persistence.impl.constants.OrganizationPersistenceConstants;

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
 * The persistence implementation for the org mapping service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marc Salvat
 * @generated
 */
@Component(service = {OrgMappingPersistence.class, BasePersistence.class})
public class OrgMappingPersistenceImpl
	extends BasePersistenceImpl<OrgMapping> implements OrgMappingPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>OrgMappingUtil</code> to access the org mapping persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		OrgMappingImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByorganisationId;
	private FinderPath _finderPathCountByorganisationId;

	/**
	 * Returns the org mapping where organizationId = &#63; or throws a <code>NoSuchOrgMappingException</code> if it could not be found.
	 *
	 * @param organizationId the organization ID
	 * @return the matching org mapping
	 * @throws NoSuchOrgMappingException if a matching org mapping could not be found
	 */
	@Override
	public OrgMapping findByorganisationId(long organizationId)
		throws NoSuchOrgMappingException {

		OrgMapping orgMapping = fetchByorganisationId(organizationId);

		if (orgMapping == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("organizationId=");
			sb.append(organizationId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchOrgMappingException(sb.toString());
		}

		return orgMapping;
	}

	/**
	 * Returns the org mapping where organizationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param organizationId the organization ID
	 * @return the matching org mapping, or <code>null</code> if a matching org mapping could not be found
	 */
	@Override
	public OrgMapping fetchByorganisationId(long organizationId) {
		return fetchByorganisationId(organizationId, true);
	}

	/**
	 * Returns the org mapping where organizationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param organizationId the organization ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching org mapping, or <code>null</code> if a matching org mapping could not be found
	 */
	@Override
	public OrgMapping fetchByorganisationId(
		long organizationId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {organizationId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByorganisationId, finderArgs, this);
		}

		if (result instanceof OrgMapping) {
			OrgMapping orgMapping = (OrgMapping)result;

			if (organizationId != orgMapping.getOrganizationId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_ORGMAPPING_WHERE);

			sb.append(_FINDER_COLUMN_ORGANISATIONID_ORGANIZATIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(organizationId);

				List<OrgMapping> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByorganisationId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {organizationId};
							}

							_log.warn(
								"OrgMappingPersistenceImpl.fetchByorganisationId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					OrgMapping orgMapping = list.get(0);

					result = orgMapping;

					cacheResult(orgMapping);
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
			return (OrgMapping)result;
		}
	}

	/**
	 * Removes the org mapping where organizationId = &#63; from the database.
	 *
	 * @param organizationId the organization ID
	 * @return the org mapping that was removed
	 */
	@Override
	public OrgMapping removeByorganisationId(long organizationId)
		throws NoSuchOrgMappingException {

		OrgMapping orgMapping = findByorganisationId(organizationId);

		return remove(orgMapping);
	}

	/**
	 * Returns the number of org mappings where organizationId = &#63;.
	 *
	 * @param organizationId the organization ID
	 * @return the number of matching org mappings
	 */
	@Override
	public int countByorganisationId(long organizationId) {
		FinderPath finderPath = _finderPathCountByorganisationId;

		Object[] finderArgs = new Object[] {organizationId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ORGMAPPING_WHERE);

			sb.append(_FINDER_COLUMN_ORGANISATIONID_ORGANIZATIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(organizationId);

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

	private static final String _FINDER_COLUMN_ORGANISATIONID_ORGANIZATIONID_2 =
		"orgMapping.organizationId = ?";

	public OrgMappingPersistenceImpl() {
		setModelClass(OrgMapping.class);

		setModelImplClass(OrgMappingImpl.class);
		setModelPKClass(String.class);

		setTable(OrgMappingTable.INSTANCE);
	}

	/**
	 * Caches the org mapping in the entity cache if it is enabled.
	 *
	 * @param orgMapping the org mapping
	 */
	@Override
	public void cacheResult(OrgMapping orgMapping) {
		entityCache.putResult(
			OrgMappingImpl.class, orgMapping.getPrimaryKey(), orgMapping);

		finderCache.putResult(
			_finderPathFetchByorganisationId,
			new Object[] {orgMapping.getOrganizationId()}, orgMapping);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the org mappings in the entity cache if it is enabled.
	 *
	 * @param orgMappings the org mappings
	 */
	@Override
	public void cacheResult(List<OrgMapping> orgMappings) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (orgMappings.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (OrgMapping orgMapping : orgMappings) {
			if (entityCache.getResult(
					OrgMappingImpl.class, orgMapping.getPrimaryKey()) == null) {

				cacheResult(orgMapping);
			}
		}
	}

	/**
	 * Clears the cache for all org mappings.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OrgMappingImpl.class);

		finderCache.clearCache(OrgMappingImpl.class);
	}

	/**
	 * Clears the cache for the org mapping.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OrgMapping orgMapping) {
		entityCache.removeResult(OrgMappingImpl.class, orgMapping);
	}

	@Override
	public void clearCache(List<OrgMapping> orgMappings) {
		for (OrgMapping orgMapping : orgMappings) {
			entityCache.removeResult(OrgMappingImpl.class, orgMapping);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(OrgMappingImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(OrgMappingImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		OrgMappingModelImpl orgMappingModelImpl) {

		Object[] args = new Object[] {orgMappingModelImpl.getOrganizationId()};

		finderCache.putResult(
			_finderPathCountByorganisationId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByorganisationId, args, orgMappingModelImpl);
	}

	/**
	 * Creates a new org mapping with the primary key. Does not add the org mapping to the database.
	 *
	 * @param entStructureUAI the primary key for the new org mapping
	 * @return the new org mapping
	 */
	@Override
	public OrgMapping create(String entStructureUAI) {
		OrgMapping orgMapping = new OrgMappingImpl();

		orgMapping.setNew(true);
		orgMapping.setPrimaryKey(entStructureUAI);

		return orgMapping;
	}

	/**
	 * Removes the org mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entStructureUAI the primary key of the org mapping
	 * @return the org mapping that was removed
	 * @throws NoSuchOrgMappingException if a org mapping with the primary key could not be found
	 */
	@Override
	public OrgMapping remove(String entStructureUAI)
		throws NoSuchOrgMappingException {

		return remove((Serializable)entStructureUAI);
	}

	/**
	 * Removes the org mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the org mapping
	 * @return the org mapping that was removed
	 * @throws NoSuchOrgMappingException if a org mapping with the primary key could not be found
	 */
	@Override
	public OrgMapping remove(Serializable primaryKey)
		throws NoSuchOrgMappingException {

		Session session = null;

		try {
			session = openSession();

			OrgMapping orgMapping = (OrgMapping)session.get(
				OrgMappingImpl.class, primaryKey);

			if (orgMapping == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOrgMappingException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(orgMapping);
		}
		catch (NoSuchOrgMappingException noSuchEntityException) {
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
	protected OrgMapping removeImpl(OrgMapping orgMapping) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(orgMapping)) {
				orgMapping = (OrgMapping)session.get(
					OrgMappingImpl.class, orgMapping.getPrimaryKeyObj());
			}

			if (orgMapping != null) {
				session.delete(orgMapping);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (orgMapping != null) {
			clearCache(orgMapping);
		}

		return orgMapping;
	}

	@Override
	public OrgMapping updateImpl(OrgMapping orgMapping) {
		boolean isNew = orgMapping.isNew();

		if (!(orgMapping instanceof OrgMappingModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(orgMapping.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(orgMapping);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in orgMapping proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom OrgMapping implementation " +
					orgMapping.getClass());
		}

		OrgMappingModelImpl orgMappingModelImpl =
			(OrgMappingModelImpl)orgMapping;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(orgMapping);
			}
			else {
				orgMapping = (OrgMapping)session.merge(orgMapping);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			OrgMappingImpl.class, orgMappingModelImpl, false, true);

		cacheUniqueFindersCache(orgMappingModelImpl);

		if (isNew) {
			orgMapping.setNew(false);
		}

		orgMapping.resetOriginalValues();

		return orgMapping;
	}

	/**
	 * Returns the org mapping with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the org mapping
	 * @return the org mapping
	 * @throws NoSuchOrgMappingException if a org mapping with the primary key could not be found
	 */
	@Override
	public OrgMapping findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOrgMappingException {

		OrgMapping orgMapping = fetchByPrimaryKey(primaryKey);

		if (orgMapping == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOrgMappingException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return orgMapping;
	}

	/**
	 * Returns the org mapping with the primary key or throws a <code>NoSuchOrgMappingException</code> if it could not be found.
	 *
	 * @param entStructureUAI the primary key of the org mapping
	 * @return the org mapping
	 * @throws NoSuchOrgMappingException if a org mapping with the primary key could not be found
	 */
	@Override
	public OrgMapping findByPrimaryKey(String entStructureUAI)
		throws NoSuchOrgMappingException {

		return findByPrimaryKey((Serializable)entStructureUAI);
	}

	/**
	 * Returns the org mapping with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entStructureUAI the primary key of the org mapping
	 * @return the org mapping, or <code>null</code> if a org mapping with the primary key could not be found
	 */
	@Override
	public OrgMapping fetchByPrimaryKey(String entStructureUAI) {
		return fetchByPrimaryKey((Serializable)entStructureUAI);
	}

	/**
	 * Returns all the org mappings.
	 *
	 * @return the org mappings
	 */
	@Override
	public List<OrgMapping> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the org mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org mappings
	 * @param end the upper bound of the range of org mappings (not inclusive)
	 * @return the range of org mappings
	 */
	@Override
	public List<OrgMapping> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the org mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org mappings
	 * @param end the upper bound of the range of org mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of org mappings
	 */
	@Override
	public List<OrgMapping> findAll(
		int start, int end, OrderByComparator<OrgMapping> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the org mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org mappings
	 * @param end the upper bound of the range of org mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of org mappings
	 */
	@Override
	public List<OrgMapping> findAll(
		int start, int end, OrderByComparator<OrgMapping> orderByComparator,
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

		List<OrgMapping> list = null;

		if (useFinderCache) {
			list = (List<OrgMapping>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ORGMAPPING);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ORGMAPPING;

				sql = sql.concat(OrgMappingModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<OrgMapping>)QueryUtil.list(
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
	 * Removes all the org mappings from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OrgMapping orgMapping : findAll()) {
			remove(orgMapping);
		}
	}

	/**
	 * Returns the number of org mappings.
	 *
	 * @return the number of org mappings
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ORGMAPPING);

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
		return "entStructureUAI";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ORGMAPPING;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return OrgMappingModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the org mapping persistence.
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

		_finderPathFetchByorganisationId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByorganisationId",
			new String[] {Long.class.getName()},
			new String[] {"organizationId"}, true);

		_finderPathCountByorganisationId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByorganisationId",
			new String[] {Long.class.getName()},
			new String[] {"organizationId"}, false);

		_setOrgMappingUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setOrgMappingUtilPersistence(null);

		entityCache.removeCache(OrgMappingImpl.class.getName());
	}

	private void _setOrgMappingUtilPersistence(
		OrgMappingPersistence orgMappingPersistence) {

		try {
			Field field = OrgMappingUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, orgMappingPersistence);
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

	private static final String _SQL_SELECT_ORGMAPPING =
		"SELECT orgMapping FROM OrgMapping orgMapping";

	private static final String _SQL_SELECT_ORGMAPPING_WHERE =
		"SELECT orgMapping FROM OrgMapping orgMapping WHERE ";

	private static final String _SQL_COUNT_ORGMAPPING =
		"SELECT COUNT(orgMapping) FROM OrgMapping orgMapping";

	private static final String _SQL_COUNT_ORGMAPPING_WHERE =
		"SELECT COUNT(orgMapping) FROM OrgMapping orgMapping WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "orgMapping.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No OrgMapping exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No OrgMapping exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		OrgMappingPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}