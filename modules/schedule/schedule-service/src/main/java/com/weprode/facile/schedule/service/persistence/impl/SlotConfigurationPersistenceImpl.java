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

package com.weprode.facile.schedule.service.persistence.impl;

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

import com.weprode.facile.schedule.exception.NoSuchSlotConfigurationException;
import com.weprode.facile.schedule.model.SlotConfiguration;
import com.weprode.facile.schedule.model.SlotConfigurationTable;
import com.weprode.facile.schedule.model.impl.SlotConfigurationImpl;
import com.weprode.facile.schedule.model.impl.SlotConfigurationModelImpl;
import com.weprode.facile.schedule.service.persistence.SlotConfigurationPK;
import com.weprode.facile.schedule.service.persistence.SlotConfigurationPersistence;
import com.weprode.facile.schedule.service.persistence.SlotConfigurationUtil;
import com.weprode.facile.schedule.service.persistence.impl.constants.SchedulePersistenceConstants;

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
 * The persistence implementation for the slot configuration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	service = {SlotConfigurationPersistence.class, BasePersistence.class}
)
public class SlotConfigurationPersistenceImpl
	extends BasePersistenceImpl<SlotConfiguration>
	implements SlotConfigurationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SlotConfigurationUtil</code> to access the slot configuration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SlotConfigurationImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByschoolId;
	private FinderPath _finderPathWithoutPaginationFindByschoolId;
	private FinderPath _finderPathCountByschoolId;

	/**
	 * Returns all the slot configurations where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the matching slot configurations
	 */
	@Override
	public List<SlotConfiguration> findByschoolId(long schoolId) {
		return findByschoolId(
			schoolId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the slot configurations where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SlotConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of slot configurations
	 * @param end the upper bound of the range of slot configurations (not inclusive)
	 * @return the range of matching slot configurations
	 */
	@Override
	public List<SlotConfiguration> findByschoolId(
		long schoolId, int start, int end) {

		return findByschoolId(schoolId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the slot configurations where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SlotConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of slot configurations
	 * @param end the upper bound of the range of slot configurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching slot configurations
	 */
	@Override
	public List<SlotConfiguration> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<SlotConfiguration> orderByComparator) {

		return findByschoolId(schoolId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the slot configurations where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SlotConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of slot configurations
	 * @param end the upper bound of the range of slot configurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching slot configurations
	 */
	@Override
	public List<SlotConfiguration> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<SlotConfiguration> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByschoolId;
				finderArgs = new Object[] {schoolId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByschoolId;
			finderArgs = new Object[] {schoolId, start, end, orderByComparator};
		}

		List<SlotConfiguration> list = null;

		if (useFinderCache) {
			list = (List<SlotConfiguration>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SlotConfiguration slotConfiguration : list) {
					if (schoolId != slotConfiguration.getSchoolId()) {
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

			sb.append(_SQL_SELECT_SLOTCONFIGURATION_WHERE);

			sb.append(_FINDER_COLUMN_SCHOOLID_SCHOOLID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SlotConfigurationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(schoolId);

				list = (List<SlotConfiguration>)QueryUtil.list(
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
	 * Returns the first slot configuration in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching slot configuration
	 * @throws NoSuchSlotConfigurationException if a matching slot configuration could not be found
	 */
	@Override
	public SlotConfiguration findByschoolId_First(
			long schoolId,
			OrderByComparator<SlotConfiguration> orderByComparator)
		throws NoSuchSlotConfigurationException {

		SlotConfiguration slotConfiguration = fetchByschoolId_First(
			schoolId, orderByComparator);

		if (slotConfiguration != null) {
			return slotConfiguration;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("schoolId=");
		sb.append(schoolId);

		sb.append("}");

		throw new NoSuchSlotConfigurationException(sb.toString());
	}

	/**
	 * Returns the first slot configuration in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching slot configuration, or <code>null</code> if a matching slot configuration could not be found
	 */
	@Override
	public SlotConfiguration fetchByschoolId_First(
		long schoolId, OrderByComparator<SlotConfiguration> orderByComparator) {

		List<SlotConfiguration> list = findByschoolId(
			schoolId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last slot configuration in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching slot configuration
	 * @throws NoSuchSlotConfigurationException if a matching slot configuration could not be found
	 */
	@Override
	public SlotConfiguration findByschoolId_Last(
			long schoolId,
			OrderByComparator<SlotConfiguration> orderByComparator)
		throws NoSuchSlotConfigurationException {

		SlotConfiguration slotConfiguration = fetchByschoolId_Last(
			schoolId, orderByComparator);

		if (slotConfiguration != null) {
			return slotConfiguration;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("schoolId=");
		sb.append(schoolId);

		sb.append("}");

		throw new NoSuchSlotConfigurationException(sb.toString());
	}

	/**
	 * Returns the last slot configuration in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching slot configuration, or <code>null</code> if a matching slot configuration could not be found
	 */
	@Override
	public SlotConfiguration fetchByschoolId_Last(
		long schoolId, OrderByComparator<SlotConfiguration> orderByComparator) {

		int count = countByschoolId(schoolId);

		if (count == 0) {
			return null;
		}

		List<SlotConfiguration> list = findByschoolId(
			schoolId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the slot configurations before and after the current slot configuration in the ordered set where schoolId = &#63;.
	 *
	 * @param slotConfigurationPK the primary key of the current slot configuration
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next slot configuration
	 * @throws NoSuchSlotConfigurationException if a slot configuration with the primary key could not be found
	 */
	@Override
	public SlotConfiguration[] findByschoolId_PrevAndNext(
			SlotConfigurationPK slotConfigurationPK, long schoolId,
			OrderByComparator<SlotConfiguration> orderByComparator)
		throws NoSuchSlotConfigurationException {

		SlotConfiguration slotConfiguration = findByPrimaryKey(
			slotConfigurationPK);

		Session session = null;

		try {
			session = openSession();

			SlotConfiguration[] array = new SlotConfigurationImpl[3];

			array[0] = getByschoolId_PrevAndNext(
				session, slotConfiguration, schoolId, orderByComparator, true);

			array[1] = slotConfiguration;

			array[2] = getByschoolId_PrevAndNext(
				session, slotConfiguration, schoolId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SlotConfiguration getByschoolId_PrevAndNext(
		Session session, SlotConfiguration slotConfiguration, long schoolId,
		OrderByComparator<SlotConfiguration> orderByComparator,
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

		sb.append(_SQL_SELECT_SLOTCONFIGURATION_WHERE);

		sb.append(_FINDER_COLUMN_SCHOOLID_SCHOOLID_2);

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
			sb.append(SlotConfigurationModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(schoolId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						slotConfiguration)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SlotConfiguration> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the slot configurations where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 */
	@Override
	public void removeByschoolId(long schoolId) {
		for (SlotConfiguration slotConfiguration :
				findByschoolId(
					schoolId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(slotConfiguration);
		}
	}

	/**
	 * Returns the number of slot configurations where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching slot configurations
	 */
	@Override
	public int countByschoolId(long schoolId) {
		FinderPath finderPath = _finderPathCountByschoolId;

		Object[] finderArgs = new Object[] {schoolId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SLOTCONFIGURATION_WHERE);

			sb.append(_FINDER_COLUMN_SCHOOLID_SCHOOLID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(schoolId);

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

	private static final String _FINDER_COLUMN_SCHOOLID_SCHOOLID_2 =
		"slotConfiguration.id.schoolId = ?";

	public SlotConfigurationPersistenceImpl() {
		setModelClass(SlotConfiguration.class);

		setModelImplClass(SlotConfigurationImpl.class);
		setModelPKClass(SlotConfigurationPK.class);

		setTable(SlotConfigurationTable.INSTANCE);
	}

	/**
	 * Caches the slot configuration in the entity cache if it is enabled.
	 *
	 * @param slotConfiguration the slot configuration
	 */
	@Override
	public void cacheResult(SlotConfiguration slotConfiguration) {
		entityCache.putResult(
			SlotConfigurationImpl.class, slotConfiguration.getPrimaryKey(),
			slotConfiguration);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the slot configurations in the entity cache if it is enabled.
	 *
	 * @param slotConfigurations the slot configurations
	 */
	@Override
	public void cacheResult(List<SlotConfiguration> slotConfigurations) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (slotConfigurations.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SlotConfiguration slotConfiguration : slotConfigurations) {
			if (entityCache.getResult(
					SlotConfigurationImpl.class,
					slotConfiguration.getPrimaryKey()) == null) {

				cacheResult(slotConfiguration);
			}
		}
	}

	/**
	 * Clears the cache for all slot configurations.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SlotConfigurationImpl.class);

		finderCache.clearCache(SlotConfigurationImpl.class);
	}

	/**
	 * Clears the cache for the slot configuration.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SlotConfiguration slotConfiguration) {
		entityCache.removeResult(
			SlotConfigurationImpl.class, slotConfiguration);
	}

	@Override
	public void clearCache(List<SlotConfiguration> slotConfigurations) {
		for (SlotConfiguration slotConfiguration : slotConfigurations) {
			entityCache.removeResult(
				SlotConfigurationImpl.class, slotConfiguration);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SlotConfigurationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SlotConfigurationImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new slot configuration with the primary key. Does not add the slot configuration to the database.
	 *
	 * @param slotConfigurationPK the primary key for the new slot configuration
	 * @return the new slot configuration
	 */
	@Override
	public SlotConfiguration create(SlotConfigurationPK slotConfigurationPK) {
		SlotConfiguration slotConfiguration = new SlotConfigurationImpl();

		slotConfiguration.setNew(true);
		slotConfiguration.setPrimaryKey(slotConfigurationPK);

		return slotConfiguration;
	}

	/**
	 * Removes the slot configuration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param slotConfigurationPK the primary key of the slot configuration
	 * @return the slot configuration that was removed
	 * @throws NoSuchSlotConfigurationException if a slot configuration with the primary key could not be found
	 */
	@Override
	public SlotConfiguration remove(SlotConfigurationPK slotConfigurationPK)
		throws NoSuchSlotConfigurationException {

		return remove((Serializable)slotConfigurationPK);
	}

	/**
	 * Removes the slot configuration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the slot configuration
	 * @return the slot configuration that was removed
	 * @throws NoSuchSlotConfigurationException if a slot configuration with the primary key could not be found
	 */
	@Override
	public SlotConfiguration remove(Serializable primaryKey)
		throws NoSuchSlotConfigurationException {

		Session session = null;

		try {
			session = openSession();

			SlotConfiguration slotConfiguration =
				(SlotConfiguration)session.get(
					SlotConfigurationImpl.class, primaryKey);

			if (slotConfiguration == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSlotConfigurationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(slotConfiguration);
		}
		catch (NoSuchSlotConfigurationException noSuchEntityException) {
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
	protected SlotConfiguration removeImpl(
		SlotConfiguration slotConfiguration) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(slotConfiguration)) {
				slotConfiguration = (SlotConfiguration)session.get(
					SlotConfigurationImpl.class,
					slotConfiguration.getPrimaryKeyObj());
			}

			if (slotConfiguration != null) {
				session.delete(slotConfiguration);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (slotConfiguration != null) {
			clearCache(slotConfiguration);
		}

		return slotConfiguration;
	}

	@Override
	public SlotConfiguration updateImpl(SlotConfiguration slotConfiguration) {
		boolean isNew = slotConfiguration.isNew();

		if (!(slotConfiguration instanceof SlotConfigurationModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(slotConfiguration.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					slotConfiguration);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in slotConfiguration proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SlotConfiguration implementation " +
					slotConfiguration.getClass());
		}

		SlotConfigurationModelImpl slotConfigurationModelImpl =
			(SlotConfigurationModelImpl)slotConfiguration;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(slotConfiguration);
			}
			else {
				slotConfiguration = (SlotConfiguration)session.merge(
					slotConfiguration);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SlotConfigurationImpl.class, slotConfigurationModelImpl, false,
			true);

		if (isNew) {
			slotConfiguration.setNew(false);
		}

		slotConfiguration.resetOriginalValues();

		return slotConfiguration;
	}

	/**
	 * Returns the slot configuration with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the slot configuration
	 * @return the slot configuration
	 * @throws NoSuchSlotConfigurationException if a slot configuration with the primary key could not be found
	 */
	@Override
	public SlotConfiguration findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSlotConfigurationException {

		SlotConfiguration slotConfiguration = fetchByPrimaryKey(primaryKey);

		if (slotConfiguration == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSlotConfigurationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return slotConfiguration;
	}

	/**
	 * Returns the slot configuration with the primary key or throws a <code>NoSuchSlotConfigurationException</code> if it could not be found.
	 *
	 * @param slotConfigurationPK the primary key of the slot configuration
	 * @return the slot configuration
	 * @throws NoSuchSlotConfigurationException if a slot configuration with the primary key could not be found
	 */
	@Override
	public SlotConfiguration findByPrimaryKey(
			SlotConfigurationPK slotConfigurationPK)
		throws NoSuchSlotConfigurationException {

		return findByPrimaryKey((Serializable)slotConfigurationPK);
	}

	/**
	 * Returns the slot configuration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param slotConfigurationPK the primary key of the slot configuration
	 * @return the slot configuration, or <code>null</code> if a slot configuration with the primary key could not be found
	 */
	@Override
	public SlotConfiguration fetchByPrimaryKey(
		SlotConfigurationPK slotConfigurationPK) {

		return fetchByPrimaryKey((Serializable)slotConfigurationPK);
	}

	/**
	 * Returns all the slot configurations.
	 *
	 * @return the slot configurations
	 */
	@Override
	public List<SlotConfiguration> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the slot configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SlotConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of slot configurations
	 * @param end the upper bound of the range of slot configurations (not inclusive)
	 * @return the range of slot configurations
	 */
	@Override
	public List<SlotConfiguration> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the slot configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SlotConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of slot configurations
	 * @param end the upper bound of the range of slot configurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of slot configurations
	 */
	@Override
	public List<SlotConfiguration> findAll(
		int start, int end,
		OrderByComparator<SlotConfiguration> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the slot configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SlotConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of slot configurations
	 * @param end the upper bound of the range of slot configurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of slot configurations
	 */
	@Override
	public List<SlotConfiguration> findAll(
		int start, int end,
		OrderByComparator<SlotConfiguration> orderByComparator,
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

		List<SlotConfiguration> list = null;

		if (useFinderCache) {
			list = (List<SlotConfiguration>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SLOTCONFIGURATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SLOTCONFIGURATION;

				sql = sql.concat(SlotConfigurationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SlotConfiguration>)QueryUtil.list(
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
	 * Removes all the slot configurations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SlotConfiguration slotConfiguration : findAll()) {
			remove(slotConfiguration);
		}
	}

	/**
	 * Returns the number of slot configurations.
	 *
	 * @return the number of slot configurations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SLOTCONFIGURATION);

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
	public Set<String> getCompoundPKColumnNames() {
		return _compoundPKColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "slotConfigurationPK";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SLOTCONFIGURATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SlotConfigurationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the slot configuration persistence.
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

		_finderPathWithPaginationFindByschoolId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByschoolId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"schoolId"}, true);

		_finderPathWithoutPaginationFindByschoolId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByschoolId",
			new String[] {Long.class.getName()}, new String[] {"schoolId"},
			true);

		_finderPathCountByschoolId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByschoolId",
			new String[] {Long.class.getName()}, new String[] {"schoolId"},
			false);

		_setSlotConfigurationUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setSlotConfigurationUtilPersistence(null);

		entityCache.removeCache(SlotConfigurationImpl.class.getName());
	}

	private void _setSlotConfigurationUtilPersistence(
		SlotConfigurationPersistence slotConfigurationPersistence) {

		try {
			Field field = SlotConfigurationUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, slotConfigurationPersistence);
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

	private static final String _SQL_SELECT_SLOTCONFIGURATION =
		"SELECT slotConfiguration FROM SlotConfiguration slotConfiguration";

	private static final String _SQL_SELECT_SLOTCONFIGURATION_WHERE =
		"SELECT slotConfiguration FROM SlotConfiguration slotConfiguration WHERE ";

	private static final String _SQL_COUNT_SLOTCONFIGURATION =
		"SELECT COUNT(slotConfiguration) FROM SlotConfiguration slotConfiguration";

	private static final String _SQL_COUNT_SLOTCONFIGURATION_WHERE =
		"SELECT COUNT(slotConfiguration) FROM SlotConfiguration slotConfiguration WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "slotConfiguration.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SlotConfiguration exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SlotConfiguration exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SlotConfigurationPersistenceImpl.class);

	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"schoolId", "slotNumber"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}