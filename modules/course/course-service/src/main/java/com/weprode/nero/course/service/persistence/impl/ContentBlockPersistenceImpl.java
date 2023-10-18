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

package com.weprode.nero.course.service.persistence.impl;

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

import com.weprode.nero.course.exception.NoSuchContentBlockException;
import com.weprode.nero.course.model.ContentBlock;
import com.weprode.nero.course.model.ContentBlockTable;
import com.weprode.nero.course.model.impl.ContentBlockImpl;
import com.weprode.nero.course.model.impl.ContentBlockModelImpl;
import com.weprode.nero.course.service.persistence.ContentBlockPersistence;
import com.weprode.nero.course.service.persistence.ContentBlockUtil;
import com.weprode.nero.course.service.persistence.impl.constants.CoursePersistenceConstants;

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
 * The persistence implementation for the content block service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ContentBlockPersistence.class, BasePersistence.class})
public class ContentBlockPersistenceImpl
	extends BasePersistenceImpl<ContentBlock>
	implements ContentBlockPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ContentBlockUtil</code> to access the content block persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ContentBlockImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindBycourseItemId;
	private FinderPath _finderPathWithoutPaginationFindBycourseItemId;
	private FinderPath _finderPathCountBycourseItemId;

	/**
	 * Returns all the content blocks where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @return the matching content blocks
	 */
	@Override
	public List<ContentBlock> findBycourseItemId(long courseItemId) {
		return findBycourseItemId(
			courseItemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the content blocks where courseItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContentBlockModelImpl</code>.
	 * </p>
	 *
	 * @param courseItemId the course item ID
	 * @param start the lower bound of the range of content blocks
	 * @param end the upper bound of the range of content blocks (not inclusive)
	 * @return the range of matching content blocks
	 */
	@Override
	public List<ContentBlock> findBycourseItemId(
		long courseItemId, int start, int end) {

		return findBycourseItemId(courseItemId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the content blocks where courseItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContentBlockModelImpl</code>.
	 * </p>
	 *
	 * @param courseItemId the course item ID
	 * @param start the lower bound of the range of content blocks
	 * @param end the upper bound of the range of content blocks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching content blocks
	 */
	@Override
	public List<ContentBlock> findBycourseItemId(
		long courseItemId, int start, int end,
		OrderByComparator<ContentBlock> orderByComparator) {

		return findBycourseItemId(
			courseItemId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the content blocks where courseItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContentBlockModelImpl</code>.
	 * </p>
	 *
	 * @param courseItemId the course item ID
	 * @param start the lower bound of the range of content blocks
	 * @param end the upper bound of the range of content blocks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching content blocks
	 */
	@Override
	public List<ContentBlock> findBycourseItemId(
		long courseItemId, int start, int end,
		OrderByComparator<ContentBlock> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBycourseItemId;
				finderArgs = new Object[] {courseItemId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBycourseItemId;
			finderArgs = new Object[] {
				courseItemId, start, end, orderByComparator
			};
		}

		List<ContentBlock> list = null;

		if (useFinderCache) {
			list = (List<ContentBlock>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ContentBlock contentBlock : list) {
					if (courseItemId != contentBlock.getCourseItemId()) {
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

			sb.append(_SQL_SELECT_CONTENTBLOCK_WHERE);

			sb.append(_FINDER_COLUMN_COURSEITEMID_COURSEITEMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ContentBlockModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(courseItemId);

				list = (List<ContentBlock>)QueryUtil.list(
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
	 * Returns the first content block in the ordered set where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content block
	 * @throws NoSuchContentBlockException if a matching content block could not be found
	 */
	@Override
	public ContentBlock findBycourseItemId_First(
			long courseItemId,
			OrderByComparator<ContentBlock> orderByComparator)
		throws NoSuchContentBlockException {

		ContentBlock contentBlock = fetchBycourseItemId_First(
			courseItemId, orderByComparator);

		if (contentBlock != null) {
			return contentBlock;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("courseItemId=");
		sb.append(courseItemId);

		sb.append("}");

		throw new NoSuchContentBlockException(sb.toString());
	}

	/**
	 * Returns the first content block in the ordered set where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content block, or <code>null</code> if a matching content block could not be found
	 */
	@Override
	public ContentBlock fetchBycourseItemId_First(
		long courseItemId, OrderByComparator<ContentBlock> orderByComparator) {

		List<ContentBlock> list = findBycourseItemId(
			courseItemId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last content block in the ordered set where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content block
	 * @throws NoSuchContentBlockException if a matching content block could not be found
	 */
	@Override
	public ContentBlock findBycourseItemId_Last(
			long courseItemId,
			OrderByComparator<ContentBlock> orderByComparator)
		throws NoSuchContentBlockException {

		ContentBlock contentBlock = fetchBycourseItemId_Last(
			courseItemId, orderByComparator);

		if (contentBlock != null) {
			return contentBlock;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("courseItemId=");
		sb.append(courseItemId);

		sb.append("}");

		throw new NoSuchContentBlockException(sb.toString());
	}

	/**
	 * Returns the last content block in the ordered set where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content block, or <code>null</code> if a matching content block could not be found
	 */
	@Override
	public ContentBlock fetchBycourseItemId_Last(
		long courseItemId, OrderByComparator<ContentBlock> orderByComparator) {

		int count = countBycourseItemId(courseItemId);

		if (count == 0) {
			return null;
		}

		List<ContentBlock> list = findBycourseItemId(
			courseItemId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the content blocks before and after the current content block in the ordered set where courseItemId = &#63;.
	 *
	 * @param blockId the primary key of the current content block
	 * @param courseItemId the course item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next content block
	 * @throws NoSuchContentBlockException if a content block with the primary key could not be found
	 */
	@Override
	public ContentBlock[] findBycourseItemId_PrevAndNext(
			long blockId, long courseItemId,
			OrderByComparator<ContentBlock> orderByComparator)
		throws NoSuchContentBlockException {

		ContentBlock contentBlock = findByPrimaryKey(blockId);

		Session session = null;

		try {
			session = openSession();

			ContentBlock[] array = new ContentBlockImpl[3];

			array[0] = getBycourseItemId_PrevAndNext(
				session, contentBlock, courseItemId, orderByComparator, true);

			array[1] = contentBlock;

			array[2] = getBycourseItemId_PrevAndNext(
				session, contentBlock, courseItemId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ContentBlock getBycourseItemId_PrevAndNext(
		Session session, ContentBlock contentBlock, long courseItemId,
		OrderByComparator<ContentBlock> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_CONTENTBLOCK_WHERE);

		sb.append(_FINDER_COLUMN_COURSEITEMID_COURSEITEMID_2);

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
			sb.append(ContentBlockModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(courseItemId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(contentBlock)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ContentBlock> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the content blocks where courseItemId = &#63; from the database.
	 *
	 * @param courseItemId the course item ID
	 */
	@Override
	public void removeBycourseItemId(long courseItemId) {
		for (ContentBlock contentBlock :
				findBycourseItemId(
					courseItemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(contentBlock);
		}
	}

	/**
	 * Returns the number of content blocks where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @return the number of matching content blocks
	 */
	@Override
	public int countBycourseItemId(long courseItemId) {
		FinderPath finderPath = _finderPathCountBycourseItemId;

		Object[] finderArgs = new Object[] {courseItemId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CONTENTBLOCK_WHERE);

			sb.append(_FINDER_COLUMN_COURSEITEMID_COURSEITEMID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(courseItemId);

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

	private static final String _FINDER_COLUMN_COURSEITEMID_COURSEITEMID_2 =
		"contentBlock.courseItemId = ?";

	public ContentBlockPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("order", "order_");

		setDBColumnNames(dbColumnNames);

		setModelClass(ContentBlock.class);

		setModelImplClass(ContentBlockImpl.class);
		setModelPKClass(long.class);

		setTable(ContentBlockTable.INSTANCE);
	}

	/**
	 * Caches the content block in the entity cache if it is enabled.
	 *
	 * @param contentBlock the content block
	 */
	@Override
	public void cacheResult(ContentBlock contentBlock) {
		entityCache.putResult(
			ContentBlockImpl.class, contentBlock.getPrimaryKey(), contentBlock);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the content blocks in the entity cache if it is enabled.
	 *
	 * @param contentBlocks the content blocks
	 */
	@Override
	public void cacheResult(List<ContentBlock> contentBlocks) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (contentBlocks.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ContentBlock contentBlock : contentBlocks) {
			if (entityCache.getResult(
					ContentBlockImpl.class, contentBlock.getPrimaryKey()) ==
						null) {

				cacheResult(contentBlock);
			}
		}
	}

	/**
	 * Clears the cache for all content blocks.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ContentBlockImpl.class);

		finderCache.clearCache(ContentBlockImpl.class);
	}

	/**
	 * Clears the cache for the content block.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ContentBlock contentBlock) {
		entityCache.removeResult(ContentBlockImpl.class, contentBlock);
	}

	@Override
	public void clearCache(List<ContentBlock> contentBlocks) {
		for (ContentBlock contentBlock : contentBlocks) {
			entityCache.removeResult(ContentBlockImpl.class, contentBlock);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ContentBlockImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ContentBlockImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new content block with the primary key. Does not add the content block to the database.
	 *
	 * @param blockId the primary key for the new content block
	 * @return the new content block
	 */
	@Override
	public ContentBlock create(long blockId) {
		ContentBlock contentBlock = new ContentBlockImpl();

		contentBlock.setNew(true);
		contentBlock.setPrimaryKey(blockId);

		return contentBlock;
	}

	/**
	 * Removes the content block with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param blockId the primary key of the content block
	 * @return the content block that was removed
	 * @throws NoSuchContentBlockException if a content block with the primary key could not be found
	 */
	@Override
	public ContentBlock remove(long blockId)
		throws NoSuchContentBlockException {

		return remove((Serializable)blockId);
	}

	/**
	 * Removes the content block with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the content block
	 * @return the content block that was removed
	 * @throws NoSuchContentBlockException if a content block with the primary key could not be found
	 */
	@Override
	public ContentBlock remove(Serializable primaryKey)
		throws NoSuchContentBlockException {

		Session session = null;

		try {
			session = openSession();

			ContentBlock contentBlock = (ContentBlock)session.get(
				ContentBlockImpl.class, primaryKey);

			if (contentBlock == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchContentBlockException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(contentBlock);
		}
		catch (NoSuchContentBlockException noSuchEntityException) {
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
	protected ContentBlock removeImpl(ContentBlock contentBlock) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(contentBlock)) {
				contentBlock = (ContentBlock)session.get(
					ContentBlockImpl.class, contentBlock.getPrimaryKeyObj());
			}

			if (contentBlock != null) {
				session.delete(contentBlock);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (contentBlock != null) {
			clearCache(contentBlock);
		}

		return contentBlock;
	}

	@Override
	public ContentBlock updateImpl(ContentBlock contentBlock) {
		boolean isNew = contentBlock.isNew();

		if (!(contentBlock instanceof ContentBlockModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(contentBlock.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					contentBlock);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in contentBlock proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ContentBlock implementation " +
					contentBlock.getClass());
		}

		ContentBlockModelImpl contentBlockModelImpl =
			(ContentBlockModelImpl)contentBlock;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(contentBlock);
			}
			else {
				contentBlock = (ContentBlock)session.merge(contentBlock);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ContentBlockImpl.class, contentBlockModelImpl, false, true);

		if (isNew) {
			contentBlock.setNew(false);
		}

		contentBlock.resetOriginalValues();

		return contentBlock;
	}

	/**
	 * Returns the content block with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the content block
	 * @return the content block
	 * @throws NoSuchContentBlockException if a content block with the primary key could not be found
	 */
	@Override
	public ContentBlock findByPrimaryKey(Serializable primaryKey)
		throws NoSuchContentBlockException {

		ContentBlock contentBlock = fetchByPrimaryKey(primaryKey);

		if (contentBlock == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchContentBlockException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return contentBlock;
	}

	/**
	 * Returns the content block with the primary key or throws a <code>NoSuchContentBlockException</code> if it could not be found.
	 *
	 * @param blockId the primary key of the content block
	 * @return the content block
	 * @throws NoSuchContentBlockException if a content block with the primary key could not be found
	 */
	@Override
	public ContentBlock findByPrimaryKey(long blockId)
		throws NoSuchContentBlockException {

		return findByPrimaryKey((Serializable)blockId);
	}

	/**
	 * Returns the content block with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param blockId the primary key of the content block
	 * @return the content block, or <code>null</code> if a content block with the primary key could not be found
	 */
	@Override
	public ContentBlock fetchByPrimaryKey(long blockId) {
		return fetchByPrimaryKey((Serializable)blockId);
	}

	/**
	 * Returns all the content blocks.
	 *
	 * @return the content blocks
	 */
	@Override
	public List<ContentBlock> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the content blocks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContentBlockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of content blocks
	 * @param end the upper bound of the range of content blocks (not inclusive)
	 * @return the range of content blocks
	 */
	@Override
	public List<ContentBlock> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the content blocks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContentBlockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of content blocks
	 * @param end the upper bound of the range of content blocks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of content blocks
	 */
	@Override
	public List<ContentBlock> findAll(
		int start, int end, OrderByComparator<ContentBlock> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the content blocks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContentBlockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of content blocks
	 * @param end the upper bound of the range of content blocks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of content blocks
	 */
	@Override
	public List<ContentBlock> findAll(
		int start, int end, OrderByComparator<ContentBlock> orderByComparator,
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

		List<ContentBlock> list = null;

		if (useFinderCache) {
			list = (List<ContentBlock>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_CONTENTBLOCK);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_CONTENTBLOCK;

				sql = sql.concat(ContentBlockModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ContentBlock>)QueryUtil.list(
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
	 * Removes all the content blocks from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ContentBlock contentBlock : findAll()) {
			remove(contentBlock);
		}
	}

	/**
	 * Returns the number of content blocks.
	 *
	 * @return the number of content blocks
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_CONTENTBLOCK);

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
		return "blockId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_CONTENTBLOCK;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ContentBlockModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the content block persistence.
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

		_finderPathWithPaginationFindBycourseItemId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBycourseItemId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"courseItemId"}, true);

		_finderPathWithoutPaginationFindBycourseItemId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBycourseItemId",
			new String[] {Long.class.getName()}, new String[] {"courseItemId"},
			true);

		_finderPathCountBycourseItemId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBycourseItemId",
			new String[] {Long.class.getName()}, new String[] {"courseItemId"},
			false);

		_setContentBlockUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setContentBlockUtilPersistence(null);

		entityCache.removeCache(ContentBlockImpl.class.getName());
	}

	private void _setContentBlockUtilPersistence(
		ContentBlockPersistence contentBlockPersistence) {

		try {
			Field field = ContentBlockUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, contentBlockPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = CoursePersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = CoursePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = CoursePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_CONTENTBLOCK =
		"SELECT contentBlock FROM ContentBlock contentBlock";

	private static final String _SQL_SELECT_CONTENTBLOCK_WHERE =
		"SELECT contentBlock FROM ContentBlock contentBlock WHERE ";

	private static final String _SQL_COUNT_CONTENTBLOCK =
		"SELECT COUNT(contentBlock) FROM ContentBlock contentBlock";

	private static final String _SQL_COUNT_CONTENTBLOCK_WHERE =
		"SELECT COUNT(contentBlock) FROM ContentBlock contentBlock WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "contentBlock.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ContentBlock exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ContentBlock exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ContentBlockPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"order"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}