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

package com.weprode.nero.progression.service.persistence.impl;

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

import com.weprode.nero.progression.exception.NoSuchFolderException;
import com.weprode.nero.progression.model.ProgressionFolder;
import com.weprode.nero.progression.model.ProgressionFolderTable;
import com.weprode.nero.progression.model.impl.ProgressionFolderImpl;
import com.weprode.nero.progression.model.impl.ProgressionFolderModelImpl;
import com.weprode.nero.progression.service.persistence.ProgressionFolderPersistence;
import com.weprode.nero.progression.service.persistence.ProgressionFolderUtil;
import com.weprode.nero.progression.service.persistence.impl.constants.ProgressionPersistenceConstants;

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
 * The persistence implementation for the progression folder service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	service = {ProgressionFolderPersistence.class, BasePersistence.class}
)
public class ProgressionFolderPersistenceImpl
	extends BasePersistenceImpl<ProgressionFolder>
	implements ProgressionFolderPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ProgressionFolderUtil</code> to access the progression folder persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ProgressionFolderImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByprogressionFolderId;
	private FinderPath _finderPathCountByprogressionFolderId;

	/**
	 * Returns the progression folder where progressionFolderId = &#63; or throws a <code>NoSuchFolderException</code> if it could not be found.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @return the matching progression folder
	 * @throws NoSuchFolderException if a matching progression folder could not be found
	 */
	@Override
	public ProgressionFolder findByprogressionFolderId(long progressionFolderId)
		throws NoSuchFolderException {

		ProgressionFolder progressionFolder = fetchByprogressionFolderId(
			progressionFolderId);

		if (progressionFolder == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("progressionFolderId=");
			sb.append(progressionFolderId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchFolderException(sb.toString());
		}

		return progressionFolder;
	}

	/**
	 * Returns the progression folder where progressionFolderId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @return the matching progression folder, or <code>null</code> if a matching progression folder could not be found
	 */
	@Override
	public ProgressionFolder fetchByprogressionFolderId(
		long progressionFolderId) {

		return fetchByprogressionFolderId(progressionFolderId, true);
	}

	/**
	 * Returns the progression folder where progressionFolderId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching progression folder, or <code>null</code> if a matching progression folder could not be found
	 */
	@Override
	public ProgressionFolder fetchByprogressionFolderId(
		long progressionFolderId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {progressionFolderId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByprogressionFolderId, finderArgs);
		}

		if (result instanceof ProgressionFolder) {
			ProgressionFolder progressionFolder = (ProgressionFolder)result;

			if (progressionFolderId !=
					progressionFolder.getProgressionFolderId()) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_PROGRESSIONFOLDER_WHERE);

			sb.append(_FINDER_COLUMN_PROGRESSIONFOLDERID_PROGRESSIONFOLDERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(progressionFolderId);

				List<ProgressionFolder> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByprogressionFolderId, finderArgs,
							list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {progressionFolderId};
							}

							_log.warn(
								"ProgressionFolderPersistenceImpl.fetchByprogressionFolderId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ProgressionFolder progressionFolder = list.get(0);

					result = progressionFolder;

					cacheResult(progressionFolder);
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
			return (ProgressionFolder)result;
		}
	}

	/**
	 * Removes the progression folder where progressionFolderId = &#63; from the database.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @return the progression folder that was removed
	 */
	@Override
	public ProgressionFolder removeByprogressionFolderId(
			long progressionFolderId)
		throws NoSuchFolderException {

		ProgressionFolder progressionFolder = findByprogressionFolderId(
			progressionFolderId);

		return remove(progressionFolder);
	}

	/**
	 * Returns the number of progression folders where progressionFolderId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @return the number of matching progression folders
	 */
	@Override
	public int countByprogressionFolderId(long progressionFolderId) {
		FinderPath finderPath = _finderPathCountByprogressionFolderId;

		Object[] finderArgs = new Object[] {progressionFolderId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PROGRESSIONFOLDER_WHERE);

			sb.append(_FINDER_COLUMN_PROGRESSIONFOLDERID_PROGRESSIONFOLDERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(progressionFolderId);

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
		_FINDER_COLUMN_PROGRESSIONFOLDERID_PROGRESSIONFOLDERID_2 =
			"progressionFolder.progressionFolderId = ?";

	private FinderPath
		_finderPathWithPaginationFindByparentFolderId_progressionId;
	private FinderPath
		_finderPathWithoutPaginationFindByparentFolderId_progressionId;
	private FinderPath _finderPathCountByparentFolderId_progressionId;

	/**
	 * Returns all the progression folders where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @return the matching progression folders
	 */
	@Override
	public List<ProgressionFolder> findByparentFolderId_progressionId(
		long parentFolderId, long progressionId) {

		return findByparentFolderId_progressionId(
			parentFolderId, progressionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the progression folders where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionFolderModelImpl</code>.
	 * </p>
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @param start the lower bound of the range of progression folders
	 * @param end the upper bound of the range of progression folders (not inclusive)
	 * @return the range of matching progression folders
	 */
	@Override
	public List<ProgressionFolder> findByparentFolderId_progressionId(
		long parentFolderId, long progressionId, int start, int end) {

		return findByparentFolderId_progressionId(
			parentFolderId, progressionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the progression folders where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionFolderModelImpl</code>.
	 * </p>
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @param start the lower bound of the range of progression folders
	 * @param end the upper bound of the range of progression folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching progression folders
	 */
	@Override
	public List<ProgressionFolder> findByparentFolderId_progressionId(
		long parentFolderId, long progressionId, int start, int end,
		OrderByComparator<ProgressionFolder> orderByComparator) {

		return findByparentFolderId_progressionId(
			parentFolderId, progressionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the progression folders where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionFolderModelImpl</code>.
	 * </p>
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @param start the lower bound of the range of progression folders
	 * @param end the upper bound of the range of progression folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching progression folders
	 */
	@Override
	public List<ProgressionFolder> findByparentFolderId_progressionId(
		long parentFolderId, long progressionId, int start, int end,
		OrderByComparator<ProgressionFolder> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByparentFolderId_progressionId;
				finderArgs = new Object[] {parentFolderId, progressionId};
			}
		}
		else if (useFinderCache) {
			finderPath =
				_finderPathWithPaginationFindByparentFolderId_progressionId;
			finderArgs = new Object[] {
				parentFolderId, progressionId, start, end, orderByComparator
			};
		}

		List<ProgressionFolder> list = null;

		if (useFinderCache) {
			list = (List<ProgressionFolder>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (ProgressionFolder progressionFolder : list) {
					if ((parentFolderId !=
							progressionFolder.getParentFolderId()) ||
						(progressionId !=
							progressionFolder.getProgressionId())) {

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

			sb.append(_SQL_SELECT_PROGRESSIONFOLDER_WHERE);

			sb.append(
				_FINDER_COLUMN_PARENTFOLDERID_PROGRESSIONID_PARENTFOLDERID_2);

			sb.append(
				_FINDER_COLUMN_PARENTFOLDERID_PROGRESSIONID_PROGRESSIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ProgressionFolderModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(parentFolderId);

				queryPos.add(progressionId);

				list = (List<ProgressionFolder>)QueryUtil.list(
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
	 * Returns the first progression folder in the ordered set where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression folder
	 * @throws NoSuchFolderException if a matching progression folder could not be found
	 */
	@Override
	public ProgressionFolder findByparentFolderId_progressionId_First(
			long parentFolderId, long progressionId,
			OrderByComparator<ProgressionFolder> orderByComparator)
		throws NoSuchFolderException {

		ProgressionFolder progressionFolder =
			fetchByparentFolderId_progressionId_First(
				parentFolderId, progressionId, orderByComparator);

		if (progressionFolder != null) {
			return progressionFolder;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("parentFolderId=");
		sb.append(parentFolderId);

		sb.append(", progressionId=");
		sb.append(progressionId);

		sb.append("}");

		throw new NoSuchFolderException(sb.toString());
	}

	/**
	 * Returns the first progression folder in the ordered set where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression folder, or <code>null</code> if a matching progression folder could not be found
	 */
	@Override
	public ProgressionFolder fetchByparentFolderId_progressionId_First(
		long parentFolderId, long progressionId,
		OrderByComparator<ProgressionFolder> orderByComparator) {

		List<ProgressionFolder> list = findByparentFolderId_progressionId(
			parentFolderId, progressionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last progression folder in the ordered set where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression folder
	 * @throws NoSuchFolderException if a matching progression folder could not be found
	 */
	@Override
	public ProgressionFolder findByparentFolderId_progressionId_Last(
			long parentFolderId, long progressionId,
			OrderByComparator<ProgressionFolder> orderByComparator)
		throws NoSuchFolderException {

		ProgressionFolder progressionFolder =
			fetchByparentFolderId_progressionId_Last(
				parentFolderId, progressionId, orderByComparator);

		if (progressionFolder != null) {
			return progressionFolder;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("parentFolderId=");
		sb.append(parentFolderId);

		sb.append(", progressionId=");
		sb.append(progressionId);

		sb.append("}");

		throw new NoSuchFolderException(sb.toString());
	}

	/**
	 * Returns the last progression folder in the ordered set where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression folder, or <code>null</code> if a matching progression folder could not be found
	 */
	@Override
	public ProgressionFolder fetchByparentFolderId_progressionId_Last(
		long parentFolderId, long progressionId,
		OrderByComparator<ProgressionFolder> orderByComparator) {

		int count = countByparentFolderId_progressionId(
			parentFolderId, progressionId);

		if (count == 0) {
			return null;
		}

		List<ProgressionFolder> list = findByparentFolderId_progressionId(
			parentFolderId, progressionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the progression folders before and after the current progression folder in the ordered set where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param progressionFolderId the primary key of the current progression folder
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next progression folder
	 * @throws NoSuchFolderException if a progression folder with the primary key could not be found
	 */
	@Override
	public ProgressionFolder[] findByparentFolderId_progressionId_PrevAndNext(
			long progressionFolderId, long parentFolderId, long progressionId,
			OrderByComparator<ProgressionFolder> orderByComparator)
		throws NoSuchFolderException {

		ProgressionFolder progressionFolder = findByPrimaryKey(
			progressionFolderId);

		Session session = null;

		try {
			session = openSession();

			ProgressionFolder[] array = new ProgressionFolderImpl[3];

			array[0] = getByparentFolderId_progressionId_PrevAndNext(
				session, progressionFolder, parentFolderId, progressionId,
				orderByComparator, true);

			array[1] = progressionFolder;

			array[2] = getByparentFolderId_progressionId_PrevAndNext(
				session, progressionFolder, parentFolderId, progressionId,
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

	protected ProgressionFolder getByparentFolderId_progressionId_PrevAndNext(
		Session session, ProgressionFolder progressionFolder,
		long parentFolderId, long progressionId,
		OrderByComparator<ProgressionFolder> orderByComparator,
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

		sb.append(_SQL_SELECT_PROGRESSIONFOLDER_WHERE);

		sb.append(_FINDER_COLUMN_PARENTFOLDERID_PROGRESSIONID_PARENTFOLDERID_2);

		sb.append(_FINDER_COLUMN_PARENTFOLDERID_PROGRESSIONID_PROGRESSIONID_2);

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
			sb.append(ProgressionFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(parentFolderId);

		queryPos.add(progressionId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						progressionFolder)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProgressionFolder> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the progression folders where parentFolderId = &#63; and progressionId = &#63; from the database.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 */
	@Override
	public void removeByparentFolderId_progressionId(
		long parentFolderId, long progressionId) {

		for (ProgressionFolder progressionFolder :
				findByparentFolderId_progressionId(
					parentFolderId, progressionId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(progressionFolder);
		}
	}

	/**
	 * Returns the number of progression folders where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @return the number of matching progression folders
	 */
	@Override
	public int countByparentFolderId_progressionId(
		long parentFolderId, long progressionId) {

		FinderPath finderPath = _finderPathCountByparentFolderId_progressionId;

		Object[] finderArgs = new Object[] {parentFolderId, progressionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_PROGRESSIONFOLDER_WHERE);

			sb.append(
				_FINDER_COLUMN_PARENTFOLDERID_PROGRESSIONID_PARENTFOLDERID_2);

			sb.append(
				_FINDER_COLUMN_PARENTFOLDERID_PROGRESSIONID_PROGRESSIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(parentFolderId);

				queryPos.add(progressionId);

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
		_FINDER_COLUMN_PARENTFOLDERID_PROGRESSIONID_PARENTFOLDERID_2 =
			"progressionFolder.parentFolderId = ? AND ";

	private static final String
		_FINDER_COLUMN_PARENTFOLDERID_PROGRESSIONID_PROGRESSIONID_2 =
			"progressionFolder.progressionId = ?";

	private FinderPath _finderPathWithPaginationFindByparentFolderId;
	private FinderPath _finderPathWithoutPaginationFindByparentFolderId;
	private FinderPath _finderPathCountByparentFolderId;

	/**
	 * Returns all the progression folders where parentFolderId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @return the matching progression folders
	 */
	@Override
	public List<ProgressionFolder> findByparentFolderId(long parentFolderId) {
		return findByparentFolderId(
			parentFolderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the progression folders where parentFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionFolderModelImpl</code>.
	 * </p>
	 *
	 * @param parentFolderId the parent folder ID
	 * @param start the lower bound of the range of progression folders
	 * @param end the upper bound of the range of progression folders (not inclusive)
	 * @return the range of matching progression folders
	 */
	@Override
	public List<ProgressionFolder> findByparentFolderId(
		long parentFolderId, int start, int end) {

		return findByparentFolderId(parentFolderId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the progression folders where parentFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionFolderModelImpl</code>.
	 * </p>
	 *
	 * @param parentFolderId the parent folder ID
	 * @param start the lower bound of the range of progression folders
	 * @param end the upper bound of the range of progression folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching progression folders
	 */
	@Override
	public List<ProgressionFolder> findByparentFolderId(
		long parentFolderId, int start, int end,
		OrderByComparator<ProgressionFolder> orderByComparator) {

		return findByparentFolderId(
			parentFolderId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the progression folders where parentFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionFolderModelImpl</code>.
	 * </p>
	 *
	 * @param parentFolderId the parent folder ID
	 * @param start the lower bound of the range of progression folders
	 * @param end the upper bound of the range of progression folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching progression folders
	 */
	@Override
	public List<ProgressionFolder> findByparentFolderId(
		long parentFolderId, int start, int end,
		OrderByComparator<ProgressionFolder> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByparentFolderId;
				finderArgs = new Object[] {parentFolderId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByparentFolderId;
			finderArgs = new Object[] {
				parentFolderId, start, end, orderByComparator
			};
		}

		List<ProgressionFolder> list = null;

		if (useFinderCache) {
			list = (List<ProgressionFolder>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (ProgressionFolder progressionFolder : list) {
					if (parentFolderId !=
							progressionFolder.getParentFolderId()) {

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

			sb.append(_SQL_SELECT_PROGRESSIONFOLDER_WHERE);

			sb.append(_FINDER_COLUMN_PARENTFOLDERID_PARENTFOLDERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ProgressionFolderModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(parentFolderId);

				list = (List<ProgressionFolder>)QueryUtil.list(
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
	 * Returns the first progression folder in the ordered set where parentFolderId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression folder
	 * @throws NoSuchFolderException if a matching progression folder could not be found
	 */
	@Override
	public ProgressionFolder findByparentFolderId_First(
			long parentFolderId,
			OrderByComparator<ProgressionFolder> orderByComparator)
		throws NoSuchFolderException {

		ProgressionFolder progressionFolder = fetchByparentFolderId_First(
			parentFolderId, orderByComparator);

		if (progressionFolder != null) {
			return progressionFolder;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("parentFolderId=");
		sb.append(parentFolderId);

		sb.append("}");

		throw new NoSuchFolderException(sb.toString());
	}

	/**
	 * Returns the first progression folder in the ordered set where parentFolderId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression folder, or <code>null</code> if a matching progression folder could not be found
	 */
	@Override
	public ProgressionFolder fetchByparentFolderId_First(
		long parentFolderId,
		OrderByComparator<ProgressionFolder> orderByComparator) {

		List<ProgressionFolder> list = findByparentFolderId(
			parentFolderId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last progression folder in the ordered set where parentFolderId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression folder
	 * @throws NoSuchFolderException if a matching progression folder could not be found
	 */
	@Override
	public ProgressionFolder findByparentFolderId_Last(
			long parentFolderId,
			OrderByComparator<ProgressionFolder> orderByComparator)
		throws NoSuchFolderException {

		ProgressionFolder progressionFolder = fetchByparentFolderId_Last(
			parentFolderId, orderByComparator);

		if (progressionFolder != null) {
			return progressionFolder;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("parentFolderId=");
		sb.append(parentFolderId);

		sb.append("}");

		throw new NoSuchFolderException(sb.toString());
	}

	/**
	 * Returns the last progression folder in the ordered set where parentFolderId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression folder, or <code>null</code> if a matching progression folder could not be found
	 */
	@Override
	public ProgressionFolder fetchByparentFolderId_Last(
		long parentFolderId,
		OrderByComparator<ProgressionFolder> orderByComparator) {

		int count = countByparentFolderId(parentFolderId);

		if (count == 0) {
			return null;
		}

		List<ProgressionFolder> list = findByparentFolderId(
			parentFolderId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the progression folders before and after the current progression folder in the ordered set where parentFolderId = &#63;.
	 *
	 * @param progressionFolderId the primary key of the current progression folder
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next progression folder
	 * @throws NoSuchFolderException if a progression folder with the primary key could not be found
	 */
	@Override
	public ProgressionFolder[] findByparentFolderId_PrevAndNext(
			long progressionFolderId, long parentFolderId,
			OrderByComparator<ProgressionFolder> orderByComparator)
		throws NoSuchFolderException {

		ProgressionFolder progressionFolder = findByPrimaryKey(
			progressionFolderId);

		Session session = null;

		try {
			session = openSession();

			ProgressionFolder[] array = new ProgressionFolderImpl[3];

			array[0] = getByparentFolderId_PrevAndNext(
				session, progressionFolder, parentFolderId, orderByComparator,
				true);

			array[1] = progressionFolder;

			array[2] = getByparentFolderId_PrevAndNext(
				session, progressionFolder, parentFolderId, orderByComparator,
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

	protected ProgressionFolder getByparentFolderId_PrevAndNext(
		Session session, ProgressionFolder progressionFolder,
		long parentFolderId,
		OrderByComparator<ProgressionFolder> orderByComparator,
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

		sb.append(_SQL_SELECT_PROGRESSIONFOLDER_WHERE);

		sb.append(_FINDER_COLUMN_PARENTFOLDERID_PARENTFOLDERID_2);

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
			sb.append(ProgressionFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(parentFolderId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						progressionFolder)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProgressionFolder> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the progression folders where parentFolderId = &#63; from the database.
	 *
	 * @param parentFolderId the parent folder ID
	 */
	@Override
	public void removeByparentFolderId(long parentFolderId) {
		for (ProgressionFolder progressionFolder :
				findByparentFolderId(
					parentFolderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(progressionFolder);
		}
	}

	/**
	 * Returns the number of progression folders where parentFolderId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @return the number of matching progression folders
	 */
	@Override
	public int countByparentFolderId(long parentFolderId) {
		FinderPath finderPath = _finderPathCountByparentFolderId;

		Object[] finderArgs = new Object[] {parentFolderId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PROGRESSIONFOLDER_WHERE);

			sb.append(_FINDER_COLUMN_PARENTFOLDERID_PARENTFOLDERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(parentFolderId);

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

	private static final String _FINDER_COLUMN_PARENTFOLDERID_PARENTFOLDERID_2 =
		"progressionFolder.parentFolderId = ?";

	public ProgressionFolderPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("order", "order_");

		setDBColumnNames(dbColumnNames);

		setModelClass(ProgressionFolder.class);

		setModelImplClass(ProgressionFolderImpl.class);
		setModelPKClass(long.class);

		setTable(ProgressionFolderTable.INSTANCE);
	}

	/**
	 * Caches the progression folder in the entity cache if it is enabled.
	 *
	 * @param progressionFolder the progression folder
	 */
	@Override
	public void cacheResult(ProgressionFolder progressionFolder) {
		entityCache.putResult(
			ProgressionFolderImpl.class, progressionFolder.getPrimaryKey(),
			progressionFolder);

		finderCache.putResult(
			_finderPathFetchByprogressionFolderId,
			new Object[] {progressionFolder.getProgressionFolderId()},
			progressionFolder);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the progression folders in the entity cache if it is enabled.
	 *
	 * @param progressionFolders the progression folders
	 */
	@Override
	public void cacheResult(List<ProgressionFolder> progressionFolders) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (progressionFolders.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ProgressionFolder progressionFolder : progressionFolders) {
			if (entityCache.getResult(
					ProgressionFolderImpl.class,
					progressionFolder.getPrimaryKey()) == null) {

				cacheResult(progressionFolder);
			}
		}
	}

	/**
	 * Clears the cache for all progression folders.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProgressionFolderImpl.class);

		finderCache.clearCache(ProgressionFolderImpl.class);
	}

	/**
	 * Clears the cache for the progression folder.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProgressionFolder progressionFolder) {
		entityCache.removeResult(
			ProgressionFolderImpl.class, progressionFolder);
	}

	@Override
	public void clearCache(List<ProgressionFolder> progressionFolders) {
		for (ProgressionFolder progressionFolder : progressionFolders) {
			entityCache.removeResult(
				ProgressionFolderImpl.class, progressionFolder);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ProgressionFolderImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ProgressionFolderImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		ProgressionFolderModelImpl progressionFolderModelImpl) {

		Object[] args = new Object[] {
			progressionFolderModelImpl.getProgressionFolderId()
		};

		finderCache.putResult(
			_finderPathCountByprogressionFolderId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByprogressionFolderId, args,
			progressionFolderModelImpl);
	}

	/**
	 * Creates a new progression folder with the primary key. Does not add the progression folder to the database.
	 *
	 * @param progressionFolderId the primary key for the new progression folder
	 * @return the new progression folder
	 */
	@Override
	public ProgressionFolder create(long progressionFolderId) {
		ProgressionFolder progressionFolder = new ProgressionFolderImpl();

		progressionFolder.setNew(true);
		progressionFolder.setPrimaryKey(progressionFolderId);

		return progressionFolder;
	}

	/**
	 * Removes the progression folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param progressionFolderId the primary key of the progression folder
	 * @return the progression folder that was removed
	 * @throws NoSuchFolderException if a progression folder with the primary key could not be found
	 */
	@Override
	public ProgressionFolder remove(long progressionFolderId)
		throws NoSuchFolderException {

		return remove((Serializable)progressionFolderId);
	}

	/**
	 * Removes the progression folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the progression folder
	 * @return the progression folder that was removed
	 * @throws NoSuchFolderException if a progression folder with the primary key could not be found
	 */
	@Override
	public ProgressionFolder remove(Serializable primaryKey)
		throws NoSuchFolderException {

		Session session = null;

		try {
			session = openSession();

			ProgressionFolder progressionFolder =
				(ProgressionFolder)session.get(
					ProgressionFolderImpl.class, primaryKey);

			if (progressionFolder == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFolderException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(progressionFolder);
		}
		catch (NoSuchFolderException noSuchEntityException) {
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
	protected ProgressionFolder removeImpl(
		ProgressionFolder progressionFolder) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(progressionFolder)) {
				progressionFolder = (ProgressionFolder)session.get(
					ProgressionFolderImpl.class,
					progressionFolder.getPrimaryKeyObj());
			}

			if (progressionFolder != null) {
				session.delete(progressionFolder);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (progressionFolder != null) {
			clearCache(progressionFolder);
		}

		return progressionFolder;
	}

	@Override
	public ProgressionFolder updateImpl(ProgressionFolder progressionFolder) {
		boolean isNew = progressionFolder.isNew();

		if (!(progressionFolder instanceof ProgressionFolderModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(progressionFolder.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					progressionFolder);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in progressionFolder proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ProgressionFolder implementation " +
					progressionFolder.getClass());
		}

		ProgressionFolderModelImpl progressionFolderModelImpl =
			(ProgressionFolderModelImpl)progressionFolder;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(progressionFolder);
			}
			else {
				progressionFolder = (ProgressionFolder)session.merge(
					progressionFolder);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ProgressionFolderImpl.class, progressionFolderModelImpl, false,
			true);

		cacheUniqueFindersCache(progressionFolderModelImpl);

		if (isNew) {
			progressionFolder.setNew(false);
		}

		progressionFolder.resetOriginalValues();

		return progressionFolder;
	}

	/**
	 * Returns the progression folder with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the progression folder
	 * @return the progression folder
	 * @throws NoSuchFolderException if a progression folder with the primary key could not be found
	 */
	@Override
	public ProgressionFolder findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFolderException {

		ProgressionFolder progressionFolder = fetchByPrimaryKey(primaryKey);

		if (progressionFolder == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFolderException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return progressionFolder;
	}

	/**
	 * Returns the progression folder with the primary key or throws a <code>NoSuchFolderException</code> if it could not be found.
	 *
	 * @param progressionFolderId the primary key of the progression folder
	 * @return the progression folder
	 * @throws NoSuchFolderException if a progression folder with the primary key could not be found
	 */
	@Override
	public ProgressionFolder findByPrimaryKey(long progressionFolderId)
		throws NoSuchFolderException {

		return findByPrimaryKey((Serializable)progressionFolderId);
	}

	/**
	 * Returns the progression folder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param progressionFolderId the primary key of the progression folder
	 * @return the progression folder, or <code>null</code> if a progression folder with the primary key could not be found
	 */
	@Override
	public ProgressionFolder fetchByPrimaryKey(long progressionFolderId) {
		return fetchByPrimaryKey((Serializable)progressionFolderId);
	}

	/**
	 * Returns all the progression folders.
	 *
	 * @return the progression folders
	 */
	@Override
	public List<ProgressionFolder> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the progression folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionFolderModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progression folders
	 * @param end the upper bound of the range of progression folders (not inclusive)
	 * @return the range of progression folders
	 */
	@Override
	public List<ProgressionFolder> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the progression folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionFolderModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progression folders
	 * @param end the upper bound of the range of progression folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of progression folders
	 */
	@Override
	public List<ProgressionFolder> findAll(
		int start, int end,
		OrderByComparator<ProgressionFolder> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the progression folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionFolderModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progression folders
	 * @param end the upper bound of the range of progression folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of progression folders
	 */
	@Override
	public List<ProgressionFolder> findAll(
		int start, int end,
		OrderByComparator<ProgressionFolder> orderByComparator,
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

		List<ProgressionFolder> list = null;

		if (useFinderCache) {
			list = (List<ProgressionFolder>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_PROGRESSIONFOLDER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_PROGRESSIONFOLDER;

				sql = sql.concat(ProgressionFolderModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ProgressionFolder>)QueryUtil.list(
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
	 * Removes all the progression folders from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProgressionFolder progressionFolder : findAll()) {
			remove(progressionFolder);
		}
	}

	/**
	 * Returns the number of progression folders.
	 *
	 * @return the number of progression folders
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_PROGRESSIONFOLDER);

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
		return "progressionFolderId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_PROGRESSIONFOLDER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ProgressionFolderModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the progression folder persistence.
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

		_finderPathFetchByprogressionFolderId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByprogressionFolderId",
			new String[] {Long.class.getName()},
			new String[] {"progressionFolderId"}, true);

		_finderPathCountByprogressionFolderId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByprogressionFolderId", new String[] {Long.class.getName()},
			new String[] {"progressionFolderId"}, false);

		_finderPathWithPaginationFindByparentFolderId_progressionId =
			new FinderPath(
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByparentFolderId_progressionId",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				},
				new String[] {"parentFolderId", "progressionId"}, true);

		_finderPathWithoutPaginationFindByparentFolderId_progressionId =
			new FinderPath(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByparentFolderId_progressionId",
				new String[] {Long.class.getName(), Long.class.getName()},
				new String[] {"parentFolderId", "progressionId"}, true);

		_finderPathCountByparentFolderId_progressionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByparentFolderId_progressionId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"parentFolderId", "progressionId"}, false);

		_finderPathWithPaginationFindByparentFolderId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByparentFolderId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"parentFolderId"}, true);

		_finderPathWithoutPaginationFindByparentFolderId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByparentFolderId",
			new String[] {Long.class.getName()},
			new String[] {"parentFolderId"}, true);

		_finderPathCountByparentFolderId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByparentFolderId",
			new String[] {Long.class.getName()},
			new String[] {"parentFolderId"}, false);

		_setProgressionFolderUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setProgressionFolderUtilPersistence(null);

		entityCache.removeCache(ProgressionFolderImpl.class.getName());
	}

	private void _setProgressionFolderUtilPersistence(
		ProgressionFolderPersistence progressionFolderPersistence) {

		try {
			Field field = ProgressionFolderUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, progressionFolderPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = ProgressionPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = ProgressionPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = ProgressionPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_PROGRESSIONFOLDER =
		"SELECT progressionFolder FROM ProgressionFolder progressionFolder";

	private static final String _SQL_SELECT_PROGRESSIONFOLDER_WHERE =
		"SELECT progressionFolder FROM ProgressionFolder progressionFolder WHERE ";

	private static final String _SQL_COUNT_PROGRESSIONFOLDER =
		"SELECT COUNT(progressionFolder) FROM ProgressionFolder progressionFolder";

	private static final String _SQL_COUNT_PROGRESSIONFOLDER_WHERE =
		"SELECT COUNT(progressionFolder) FROM ProgressionFolder progressionFolder WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "progressionFolder.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ProgressionFolder exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ProgressionFolder exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ProgressionFolderPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"order"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private ProgressionFolderModelArgumentsResolver
		_progressionFolderModelArgumentsResolver;

}