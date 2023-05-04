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

package com.weprode.nero.preference.service.persistence.impl;

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

import com.weprode.nero.preference.exception.NoSuchMobileDeviceException;
import com.weprode.nero.preference.model.MobileDevice;
import com.weprode.nero.preference.model.MobileDeviceTable;
import com.weprode.nero.preference.model.impl.MobileDeviceImpl;
import com.weprode.nero.preference.model.impl.MobileDeviceModelImpl;
import com.weprode.nero.preference.service.persistence.MobileDevicePersistence;
import com.weprode.nero.preference.service.persistence.MobileDeviceUtil;
import com.weprode.nero.preference.service.persistence.impl.constants.PreferencePersistenceConstants;

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
 * The persistence implementation for the mobile device service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {MobileDevicePersistence.class, BasePersistence.class})
public class MobileDevicePersistenceImpl
	extends BasePersistenceImpl<MobileDevice>
	implements MobileDevicePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>MobileDeviceUtil</code> to access the mobile device persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		MobileDeviceImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchBymobileDeviceId;
	private FinderPath _finderPathCountBymobileDeviceId;

	/**
	 * Returns the mobile device where mobileDeviceId = &#63; or throws a <code>NoSuchMobileDeviceException</code> if it could not be found.
	 *
	 * @param mobileDeviceId the mobile device ID
	 * @return the matching mobile device
	 * @throws NoSuchMobileDeviceException if a matching mobile device could not be found
	 */
	@Override
	public MobileDevice findBymobileDeviceId(long mobileDeviceId)
		throws NoSuchMobileDeviceException {

		MobileDevice mobileDevice = fetchBymobileDeviceId(mobileDeviceId);

		if (mobileDevice == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("mobileDeviceId=");
			sb.append(mobileDeviceId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchMobileDeviceException(sb.toString());
		}

		return mobileDevice;
	}

	/**
	 * Returns the mobile device where mobileDeviceId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param mobileDeviceId the mobile device ID
	 * @return the matching mobile device, or <code>null</code> if a matching mobile device could not be found
	 */
	@Override
	public MobileDevice fetchBymobileDeviceId(long mobileDeviceId) {
		return fetchBymobileDeviceId(mobileDeviceId, true);
	}

	/**
	 * Returns the mobile device where mobileDeviceId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param mobileDeviceId the mobile device ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching mobile device, or <code>null</code> if a matching mobile device could not be found
	 */
	@Override
	public MobileDevice fetchBymobileDeviceId(
		long mobileDeviceId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {mobileDeviceId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchBymobileDeviceId, finderArgs);
		}

		if (result instanceof MobileDevice) {
			MobileDevice mobileDevice = (MobileDevice)result;

			if (mobileDeviceId != mobileDevice.getMobileDeviceId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_MOBILEDEVICE_WHERE);

			sb.append(_FINDER_COLUMN_MOBILEDEVICEID_MOBILEDEVICEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(mobileDeviceId);

				List<MobileDevice> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchBymobileDeviceId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {mobileDeviceId};
							}

							_log.warn(
								"MobileDevicePersistenceImpl.fetchBymobileDeviceId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					MobileDevice mobileDevice = list.get(0);

					result = mobileDevice;

					cacheResult(mobileDevice);
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
			return (MobileDevice)result;
		}
	}

	/**
	 * Removes the mobile device where mobileDeviceId = &#63; from the database.
	 *
	 * @param mobileDeviceId the mobile device ID
	 * @return the mobile device that was removed
	 */
	@Override
	public MobileDevice removeBymobileDeviceId(long mobileDeviceId)
		throws NoSuchMobileDeviceException {

		MobileDevice mobileDevice = findBymobileDeviceId(mobileDeviceId);

		return remove(mobileDevice);
	}

	/**
	 * Returns the number of mobile devices where mobileDeviceId = &#63;.
	 *
	 * @param mobileDeviceId the mobile device ID
	 * @return the number of matching mobile devices
	 */
	@Override
	public int countBymobileDeviceId(long mobileDeviceId) {
		FinderPath finderPath = _finderPathCountBymobileDeviceId;

		Object[] finderArgs = new Object[] {mobileDeviceId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_MOBILEDEVICE_WHERE);

			sb.append(_FINDER_COLUMN_MOBILEDEVICEID_MOBILEDEVICEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(mobileDeviceId);

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

	private static final String _FINDER_COLUMN_MOBILEDEVICEID_MOBILEDEVICEID_2 =
		"mobileDevice.mobileDeviceId = ?";

	private FinderPath _finderPathFetchBymanufaturerDeviceId;
	private FinderPath _finderPathCountBymanufaturerDeviceId;

	/**
	 * Returns the mobile device where manufaturerDeviceId = &#63; or throws a <code>NoSuchMobileDeviceException</code> if it could not be found.
	 *
	 * @param manufaturerDeviceId the manufaturer device ID
	 * @return the matching mobile device
	 * @throws NoSuchMobileDeviceException if a matching mobile device could not be found
	 */
	@Override
	public MobileDevice findBymanufaturerDeviceId(String manufaturerDeviceId)
		throws NoSuchMobileDeviceException {

		MobileDevice mobileDevice = fetchBymanufaturerDeviceId(
			manufaturerDeviceId);

		if (mobileDevice == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("manufaturerDeviceId=");
			sb.append(manufaturerDeviceId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchMobileDeviceException(sb.toString());
		}

		return mobileDevice;
	}

	/**
	 * Returns the mobile device where manufaturerDeviceId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param manufaturerDeviceId the manufaturer device ID
	 * @return the matching mobile device, or <code>null</code> if a matching mobile device could not be found
	 */
	@Override
	public MobileDevice fetchBymanufaturerDeviceId(String manufaturerDeviceId) {
		return fetchBymanufaturerDeviceId(manufaturerDeviceId, true);
	}

	/**
	 * Returns the mobile device where manufaturerDeviceId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param manufaturerDeviceId the manufaturer device ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching mobile device, or <code>null</code> if a matching mobile device could not be found
	 */
	@Override
	public MobileDevice fetchBymanufaturerDeviceId(
		String manufaturerDeviceId, boolean useFinderCache) {

		manufaturerDeviceId = Objects.toString(manufaturerDeviceId, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {manufaturerDeviceId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchBymanufaturerDeviceId, finderArgs);
		}

		if (result instanceof MobileDevice) {
			MobileDevice mobileDevice = (MobileDevice)result;

			if (!Objects.equals(
					manufaturerDeviceId,
					mobileDevice.getManufaturerDeviceId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_MOBILEDEVICE_WHERE);

			boolean bindManufaturerDeviceId = false;

			if (manufaturerDeviceId.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_MANUFATURERDEVICEID_MANUFATURERDEVICEID_3);
			}
			else {
				bindManufaturerDeviceId = true;

				sb.append(
					_FINDER_COLUMN_MANUFATURERDEVICEID_MANUFATURERDEVICEID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindManufaturerDeviceId) {
					queryPos.add(manufaturerDeviceId);
				}

				List<MobileDevice> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchBymanufaturerDeviceId, finderArgs,
							list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {manufaturerDeviceId};
							}

							_log.warn(
								"MobileDevicePersistenceImpl.fetchBymanufaturerDeviceId(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					MobileDevice mobileDevice = list.get(0);

					result = mobileDevice;

					cacheResult(mobileDevice);
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
			return (MobileDevice)result;
		}
	}

	/**
	 * Removes the mobile device where manufaturerDeviceId = &#63; from the database.
	 *
	 * @param manufaturerDeviceId the manufaturer device ID
	 * @return the mobile device that was removed
	 */
	@Override
	public MobileDevice removeBymanufaturerDeviceId(String manufaturerDeviceId)
		throws NoSuchMobileDeviceException {

		MobileDevice mobileDevice = findBymanufaturerDeviceId(
			manufaturerDeviceId);

		return remove(mobileDevice);
	}

	/**
	 * Returns the number of mobile devices where manufaturerDeviceId = &#63;.
	 *
	 * @param manufaturerDeviceId the manufaturer device ID
	 * @return the number of matching mobile devices
	 */
	@Override
	public int countBymanufaturerDeviceId(String manufaturerDeviceId) {
		manufaturerDeviceId = Objects.toString(manufaturerDeviceId, "");

		FinderPath finderPath = _finderPathCountBymanufaturerDeviceId;

		Object[] finderArgs = new Object[] {manufaturerDeviceId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_MOBILEDEVICE_WHERE);

			boolean bindManufaturerDeviceId = false;

			if (manufaturerDeviceId.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_MANUFATURERDEVICEID_MANUFATURERDEVICEID_3);
			}
			else {
				bindManufaturerDeviceId = true;

				sb.append(
					_FINDER_COLUMN_MANUFATURERDEVICEID_MANUFATURERDEVICEID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindManufaturerDeviceId) {
					queryPos.add(manufaturerDeviceId);
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
		_FINDER_COLUMN_MANUFATURERDEVICEID_MANUFATURERDEVICEID_2 =
			"mobileDevice.manufaturerDeviceId = ?";

	private static final String
		_FINDER_COLUMN_MANUFATURERDEVICEID_MANUFATURERDEVICEID_3 =
			"(mobileDevice.manufaturerDeviceId IS NULL OR mobileDevice.manufaturerDeviceId = '')";

	private FinderPath _finderPathWithPaginationFindByuserId;
	private FinderPath _finderPathWithoutPaginationFindByuserId;
	private FinderPath _finderPathCountByuserId;

	/**
	 * Returns all the mobile devices where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching mobile devices
	 */
	@Override
	public List<MobileDevice> findByuserId(long userId) {
		return findByuserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the mobile devices where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileDeviceModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of mobile devices
	 * @param end the upper bound of the range of mobile devices (not inclusive)
	 * @return the range of matching mobile devices
	 */
	@Override
	public List<MobileDevice> findByuserId(long userId, int start, int end) {
		return findByuserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the mobile devices where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileDeviceModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of mobile devices
	 * @param end the upper bound of the range of mobile devices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching mobile devices
	 */
	@Override
	public List<MobileDevice> findByuserId(
		long userId, int start, int end,
		OrderByComparator<MobileDevice> orderByComparator) {

		return findByuserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the mobile devices where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileDeviceModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of mobile devices
	 * @param end the upper bound of the range of mobile devices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching mobile devices
	 */
	@Override
	public List<MobileDevice> findByuserId(
		long userId, int start, int end,
		OrderByComparator<MobileDevice> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByuserId;
				finderArgs = new Object[] {userId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByuserId;
			finderArgs = new Object[] {userId, start, end, orderByComparator};
		}

		List<MobileDevice> list = null;

		if (useFinderCache) {
			list = (List<MobileDevice>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (MobileDevice mobileDevice : list) {
					if (userId != mobileDevice.getUserId()) {
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

			sb.append(_SQL_SELECT_MOBILEDEVICE_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(MobileDeviceModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<MobileDevice>)QueryUtil.list(
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
	 * Returns the first mobile device in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile device
	 * @throws NoSuchMobileDeviceException if a matching mobile device could not be found
	 */
	@Override
	public MobileDevice findByuserId_First(
			long userId, OrderByComparator<MobileDevice> orderByComparator)
		throws NoSuchMobileDeviceException {

		MobileDevice mobileDevice = fetchByuserId_First(
			userId, orderByComparator);

		if (mobileDevice != null) {
			return mobileDevice;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchMobileDeviceException(sb.toString());
	}

	/**
	 * Returns the first mobile device in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile device, or <code>null</code> if a matching mobile device could not be found
	 */
	@Override
	public MobileDevice fetchByuserId_First(
		long userId, OrderByComparator<MobileDevice> orderByComparator) {

		List<MobileDevice> list = findByuserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last mobile device in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile device
	 * @throws NoSuchMobileDeviceException if a matching mobile device could not be found
	 */
	@Override
	public MobileDevice findByuserId_Last(
			long userId, OrderByComparator<MobileDevice> orderByComparator)
		throws NoSuchMobileDeviceException {

		MobileDevice mobileDevice = fetchByuserId_Last(
			userId, orderByComparator);

		if (mobileDevice != null) {
			return mobileDevice;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchMobileDeviceException(sb.toString());
	}

	/**
	 * Returns the last mobile device in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile device, or <code>null</code> if a matching mobile device could not be found
	 */
	@Override
	public MobileDevice fetchByuserId_Last(
		long userId, OrderByComparator<MobileDevice> orderByComparator) {

		int count = countByuserId(userId);

		if (count == 0) {
			return null;
		}

		List<MobileDevice> list = findByuserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the mobile devices before and after the current mobile device in the ordered set where userId = &#63;.
	 *
	 * @param mobileDeviceId the primary key of the current mobile device
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next mobile device
	 * @throws NoSuchMobileDeviceException if a mobile device with the primary key could not be found
	 */
	@Override
	public MobileDevice[] findByuserId_PrevAndNext(
			long mobileDeviceId, long userId,
			OrderByComparator<MobileDevice> orderByComparator)
		throws NoSuchMobileDeviceException {

		MobileDevice mobileDevice = findByPrimaryKey(mobileDeviceId);

		Session session = null;

		try {
			session = openSession();

			MobileDevice[] array = new MobileDeviceImpl[3];

			array[0] = getByuserId_PrevAndNext(
				session, mobileDevice, userId, orderByComparator, true);

			array[1] = mobileDevice;

			array[2] = getByuserId_PrevAndNext(
				session, mobileDevice, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected MobileDevice getByuserId_PrevAndNext(
		Session session, MobileDevice mobileDevice, long userId,
		OrderByComparator<MobileDevice> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_MOBILEDEVICE_WHERE);

		sb.append(_FINDER_COLUMN_USERID_USERID_2);

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
			sb.append(MobileDeviceModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(mobileDevice)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<MobileDevice> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the mobile devices where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByuserId(long userId) {
		for (MobileDevice mobileDevice :
				findByuserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(mobileDevice);
		}
	}

	/**
	 * Returns the number of mobile devices where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching mobile devices
	 */
	@Override
	public int countByuserId(long userId) {
		FinderPath finderPath = _finderPathCountByuserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_MOBILEDEVICE_WHERE);

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
		"mobileDevice.userId = ?";

	public MobileDevicePersistenceImpl() {
		setModelClass(MobileDevice.class);

		setModelImplClass(MobileDeviceImpl.class);
		setModelPKClass(long.class);

		setTable(MobileDeviceTable.INSTANCE);
	}

	/**
	 * Caches the mobile device in the entity cache if it is enabled.
	 *
	 * @param mobileDevice the mobile device
	 */
	@Override
	public void cacheResult(MobileDevice mobileDevice) {
		entityCache.putResult(
			MobileDeviceImpl.class, mobileDevice.getPrimaryKey(), mobileDevice);

		finderCache.putResult(
			_finderPathFetchBymobileDeviceId,
			new Object[] {mobileDevice.getMobileDeviceId()}, mobileDevice);

		finderCache.putResult(
			_finderPathFetchBymanufaturerDeviceId,
			new Object[] {mobileDevice.getManufaturerDeviceId()}, mobileDevice);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the mobile devices in the entity cache if it is enabled.
	 *
	 * @param mobileDevices the mobile devices
	 */
	@Override
	public void cacheResult(List<MobileDevice> mobileDevices) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (mobileDevices.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (MobileDevice mobileDevice : mobileDevices) {
			if (entityCache.getResult(
					MobileDeviceImpl.class, mobileDevice.getPrimaryKey()) ==
						null) {

				cacheResult(mobileDevice);
			}
		}
	}

	/**
	 * Clears the cache for all mobile devices.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MobileDeviceImpl.class);

		finderCache.clearCache(MobileDeviceImpl.class);
	}

	/**
	 * Clears the cache for the mobile device.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MobileDevice mobileDevice) {
		entityCache.removeResult(MobileDeviceImpl.class, mobileDevice);
	}

	@Override
	public void clearCache(List<MobileDevice> mobileDevices) {
		for (MobileDevice mobileDevice : mobileDevices) {
			entityCache.removeResult(MobileDeviceImpl.class, mobileDevice);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(MobileDeviceImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(MobileDeviceImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		MobileDeviceModelImpl mobileDeviceModelImpl) {

		Object[] args = new Object[] {
			mobileDeviceModelImpl.getMobileDeviceId()
		};

		finderCache.putResult(
			_finderPathCountBymobileDeviceId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchBymobileDeviceId, args, mobileDeviceModelImpl);

		args = new Object[] {mobileDeviceModelImpl.getManufaturerDeviceId()};

		finderCache.putResult(
			_finderPathCountBymanufaturerDeviceId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchBymanufaturerDeviceId, args, mobileDeviceModelImpl);
	}

	/**
	 * Creates a new mobile device with the primary key. Does not add the mobile device to the database.
	 *
	 * @param mobileDeviceId the primary key for the new mobile device
	 * @return the new mobile device
	 */
	@Override
	public MobileDevice create(long mobileDeviceId) {
		MobileDevice mobileDevice = new MobileDeviceImpl();

		mobileDevice.setNew(true);
		mobileDevice.setPrimaryKey(mobileDeviceId);

		return mobileDevice;
	}

	/**
	 * Removes the mobile device with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mobileDeviceId the primary key of the mobile device
	 * @return the mobile device that was removed
	 * @throws NoSuchMobileDeviceException if a mobile device with the primary key could not be found
	 */
	@Override
	public MobileDevice remove(long mobileDeviceId)
		throws NoSuchMobileDeviceException {

		return remove((Serializable)mobileDeviceId);
	}

	/**
	 * Removes the mobile device with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the mobile device
	 * @return the mobile device that was removed
	 * @throws NoSuchMobileDeviceException if a mobile device with the primary key could not be found
	 */
	@Override
	public MobileDevice remove(Serializable primaryKey)
		throws NoSuchMobileDeviceException {

		Session session = null;

		try {
			session = openSession();

			MobileDevice mobileDevice = (MobileDevice)session.get(
				MobileDeviceImpl.class, primaryKey);

			if (mobileDevice == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMobileDeviceException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(mobileDevice);
		}
		catch (NoSuchMobileDeviceException noSuchEntityException) {
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
	protected MobileDevice removeImpl(MobileDevice mobileDevice) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(mobileDevice)) {
				mobileDevice = (MobileDevice)session.get(
					MobileDeviceImpl.class, mobileDevice.getPrimaryKeyObj());
			}

			if (mobileDevice != null) {
				session.delete(mobileDevice);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (mobileDevice != null) {
			clearCache(mobileDevice);
		}

		return mobileDevice;
	}

	@Override
	public MobileDevice updateImpl(MobileDevice mobileDevice) {
		boolean isNew = mobileDevice.isNew();

		if (!(mobileDevice instanceof MobileDeviceModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(mobileDevice.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					mobileDevice);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in mobileDevice proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom MobileDevice implementation " +
					mobileDevice.getClass());
		}

		MobileDeviceModelImpl mobileDeviceModelImpl =
			(MobileDeviceModelImpl)mobileDevice;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(mobileDevice);
			}
			else {
				mobileDevice = (MobileDevice)session.merge(mobileDevice);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			MobileDeviceImpl.class, mobileDeviceModelImpl, false, true);

		cacheUniqueFindersCache(mobileDeviceModelImpl);

		if (isNew) {
			mobileDevice.setNew(false);
		}

		mobileDevice.resetOriginalValues();

		return mobileDevice;
	}

	/**
	 * Returns the mobile device with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the mobile device
	 * @return the mobile device
	 * @throws NoSuchMobileDeviceException if a mobile device with the primary key could not be found
	 */
	@Override
	public MobileDevice findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMobileDeviceException {

		MobileDevice mobileDevice = fetchByPrimaryKey(primaryKey);

		if (mobileDevice == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMobileDeviceException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return mobileDevice;
	}

	/**
	 * Returns the mobile device with the primary key or throws a <code>NoSuchMobileDeviceException</code> if it could not be found.
	 *
	 * @param mobileDeviceId the primary key of the mobile device
	 * @return the mobile device
	 * @throws NoSuchMobileDeviceException if a mobile device with the primary key could not be found
	 */
	@Override
	public MobileDevice findByPrimaryKey(long mobileDeviceId)
		throws NoSuchMobileDeviceException {

		return findByPrimaryKey((Serializable)mobileDeviceId);
	}

	/**
	 * Returns the mobile device with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param mobileDeviceId the primary key of the mobile device
	 * @return the mobile device, or <code>null</code> if a mobile device with the primary key could not be found
	 */
	@Override
	public MobileDevice fetchByPrimaryKey(long mobileDeviceId) {
		return fetchByPrimaryKey((Serializable)mobileDeviceId);
	}

	/**
	 * Returns all the mobile devices.
	 *
	 * @return the mobile devices
	 */
	@Override
	public List<MobileDevice> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the mobile devices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileDeviceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of mobile devices
	 * @param end the upper bound of the range of mobile devices (not inclusive)
	 * @return the range of mobile devices
	 */
	@Override
	public List<MobileDevice> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the mobile devices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileDeviceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of mobile devices
	 * @param end the upper bound of the range of mobile devices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of mobile devices
	 */
	@Override
	public List<MobileDevice> findAll(
		int start, int end, OrderByComparator<MobileDevice> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the mobile devices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileDeviceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of mobile devices
	 * @param end the upper bound of the range of mobile devices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of mobile devices
	 */
	@Override
	public List<MobileDevice> findAll(
		int start, int end, OrderByComparator<MobileDevice> orderByComparator,
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

		List<MobileDevice> list = null;

		if (useFinderCache) {
			list = (List<MobileDevice>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_MOBILEDEVICE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_MOBILEDEVICE;

				sql = sql.concat(MobileDeviceModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<MobileDevice>)QueryUtil.list(
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
	 * Removes all the mobile devices from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MobileDevice mobileDevice : findAll()) {
			remove(mobileDevice);
		}
	}

	/**
	 * Returns the number of mobile devices.
	 *
	 * @return the number of mobile devices
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_MOBILEDEVICE);

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
		return "mobileDeviceId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_MOBILEDEVICE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MobileDeviceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the mobile device persistence.
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

		_finderPathFetchBymobileDeviceId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchBymobileDeviceId",
			new String[] {Long.class.getName()},
			new String[] {"mobileDeviceId"}, true);

		_finderPathCountBymobileDeviceId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBymobileDeviceId",
			new String[] {Long.class.getName()},
			new String[] {"mobileDeviceId"}, false);

		_finderPathFetchBymanufaturerDeviceId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchBymanufaturerDeviceId",
			new String[] {String.class.getName()},
			new String[] {"manufaturerDeviceId"}, true);

		_finderPathCountBymanufaturerDeviceId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBymanufaturerDeviceId", new String[] {String.class.getName()},
			new String[] {"manufaturerDeviceId"}, false);

		_finderPathWithPaginationFindByuserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByuserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"userId"}, true);

		_finderPathWithoutPaginationFindByuserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByuserId",
			new String[] {Long.class.getName()}, new String[] {"userId"}, true);

		_finderPathCountByuserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByuserId",
			new String[] {Long.class.getName()}, new String[] {"userId"},
			false);

		_setMobileDeviceUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setMobileDeviceUtilPersistence(null);

		entityCache.removeCache(MobileDeviceImpl.class.getName());
	}

	private void _setMobileDeviceUtilPersistence(
		MobileDevicePersistence mobileDevicePersistence) {

		try {
			Field field = MobileDeviceUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, mobileDevicePersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = PreferencePersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = PreferencePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = PreferencePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_MOBILEDEVICE =
		"SELECT mobileDevice FROM MobileDevice mobileDevice";

	private static final String _SQL_SELECT_MOBILEDEVICE_WHERE =
		"SELECT mobileDevice FROM MobileDevice mobileDevice WHERE ";

	private static final String _SQL_COUNT_MOBILEDEVICE =
		"SELECT COUNT(mobileDevice) FROM MobileDevice mobileDevice";

	private static final String _SQL_COUNT_MOBILEDEVICE_WHERE =
		"SELECT COUNT(mobileDevice) FROM MobileDevice mobileDevice WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "mobileDevice.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No MobileDevice exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No MobileDevice exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		MobileDevicePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private MobileDeviceModelArgumentsResolver
		_mobileDeviceModelArgumentsResolver;

}