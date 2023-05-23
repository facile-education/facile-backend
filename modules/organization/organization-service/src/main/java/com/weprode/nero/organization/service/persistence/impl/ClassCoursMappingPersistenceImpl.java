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

import com.weprode.nero.organization.exception.NoSuchClassCoursMappingException;
import com.weprode.nero.organization.model.ClassCoursMapping;
import com.weprode.nero.organization.model.ClassCoursMappingTable;
import com.weprode.nero.organization.model.impl.ClassCoursMappingImpl;
import com.weprode.nero.organization.model.impl.ClassCoursMappingModelImpl;
import com.weprode.nero.organization.service.persistence.ClassCoursMappingPersistence;
import com.weprode.nero.organization.service.persistence.ClassCoursMappingUtil;
import com.weprode.nero.organization.service.persistence.impl.constants.OrganizationPersistenceConstants;

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
 * The persistence implementation for the class cours mapping service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marc Salvat
 * @generated
 */
@Component(
	service = {ClassCoursMappingPersistence.class, BasePersistence.class}
)
public class ClassCoursMappingPersistenceImpl
	extends BasePersistenceImpl<ClassCoursMapping>
	implements ClassCoursMappingPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ClassCoursMappingUtil</code> to access the class cours mapping persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ClassCoursMappingImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByclassOrgId;
	private FinderPath _finderPathWithoutPaginationFindByclassOrgId;
	private FinderPath _finderPathCountByclassOrgId;

	/**
	 * Returns all the class cours mappings where classOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @return the matching class cours mappings
	 */
	@Override
	public List<ClassCoursMapping> findByclassOrgId(long classOrgId) {
		return findByclassOrgId(
			classOrgId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the class cours mappings where classOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param classOrgId the class org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @return the range of matching class cours mappings
	 */
	@Override
	public List<ClassCoursMapping> findByclassOrgId(
		long classOrgId, int start, int end) {

		return findByclassOrgId(classOrgId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the class cours mappings where classOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param classOrgId the class org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching class cours mappings
	 */
	@Override
	public List<ClassCoursMapping> findByclassOrgId(
		long classOrgId, int start, int end,
		OrderByComparator<ClassCoursMapping> orderByComparator) {

		return findByclassOrgId(
			classOrgId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the class cours mappings where classOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param classOrgId the class org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching class cours mappings
	 */
	@Override
	public List<ClassCoursMapping> findByclassOrgId(
		long classOrgId, int start, int end,
		OrderByComparator<ClassCoursMapping> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByclassOrgId;
				finderArgs = new Object[] {classOrgId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByclassOrgId;
			finderArgs = new Object[] {
				classOrgId, start, end, orderByComparator
			};
		}

		List<ClassCoursMapping> list = null;

		if (useFinderCache) {
			list = (List<ClassCoursMapping>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (ClassCoursMapping classCoursMapping : list) {
					if (classOrgId != classCoursMapping.getClassOrgId()) {
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

			sb.append(_SQL_SELECT_CLASSCOURSMAPPING_WHERE);

			sb.append(_FINDER_COLUMN_CLASSORGID_CLASSORGID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ClassCoursMappingModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classOrgId);

				list = (List<ClassCoursMapping>)QueryUtil.list(
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
	 * Returns the first class cours mapping in the ordered set where classOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching class cours mapping
	 * @throws NoSuchClassCoursMappingException if a matching class cours mapping could not be found
	 */
	@Override
	public ClassCoursMapping findByclassOrgId_First(
			long classOrgId,
			OrderByComparator<ClassCoursMapping> orderByComparator)
		throws NoSuchClassCoursMappingException {

		ClassCoursMapping classCoursMapping = fetchByclassOrgId_First(
			classOrgId, orderByComparator);

		if (classCoursMapping != null) {
			return classCoursMapping;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classOrgId=");
		sb.append(classOrgId);

		sb.append("}");

		throw new NoSuchClassCoursMappingException(sb.toString());
	}

	/**
	 * Returns the first class cours mapping in the ordered set where classOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching class cours mapping, or <code>null</code> if a matching class cours mapping could not be found
	 */
	@Override
	public ClassCoursMapping fetchByclassOrgId_First(
		long classOrgId,
		OrderByComparator<ClassCoursMapping> orderByComparator) {

		List<ClassCoursMapping> list = findByclassOrgId(
			classOrgId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last class cours mapping in the ordered set where classOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching class cours mapping
	 * @throws NoSuchClassCoursMappingException if a matching class cours mapping could not be found
	 */
	@Override
	public ClassCoursMapping findByclassOrgId_Last(
			long classOrgId,
			OrderByComparator<ClassCoursMapping> orderByComparator)
		throws NoSuchClassCoursMappingException {

		ClassCoursMapping classCoursMapping = fetchByclassOrgId_Last(
			classOrgId, orderByComparator);

		if (classCoursMapping != null) {
			return classCoursMapping;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classOrgId=");
		sb.append(classOrgId);

		sb.append("}");

		throw new NoSuchClassCoursMappingException(sb.toString());
	}

	/**
	 * Returns the last class cours mapping in the ordered set where classOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching class cours mapping, or <code>null</code> if a matching class cours mapping could not be found
	 */
	@Override
	public ClassCoursMapping fetchByclassOrgId_Last(
		long classOrgId,
		OrderByComparator<ClassCoursMapping> orderByComparator) {

		int count = countByclassOrgId(classOrgId);

		if (count == 0) {
			return null;
		}

		List<ClassCoursMapping> list = findByclassOrgId(
			classOrgId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the class cours mappings before and after the current class cours mapping in the ordered set where classOrgId = &#63;.
	 *
	 * @param mappingId the primary key of the current class cours mapping
	 * @param classOrgId the class org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next class cours mapping
	 * @throws NoSuchClassCoursMappingException if a class cours mapping with the primary key could not be found
	 */
	@Override
	public ClassCoursMapping[] findByclassOrgId_PrevAndNext(
			long mappingId, long classOrgId,
			OrderByComparator<ClassCoursMapping> orderByComparator)
		throws NoSuchClassCoursMappingException {

		ClassCoursMapping classCoursMapping = findByPrimaryKey(mappingId);

		Session session = null;

		try {
			session = openSession();

			ClassCoursMapping[] array = new ClassCoursMappingImpl[3];

			array[0] = getByclassOrgId_PrevAndNext(
				session, classCoursMapping, classOrgId, orderByComparator,
				true);

			array[1] = classCoursMapping;

			array[2] = getByclassOrgId_PrevAndNext(
				session, classCoursMapping, classOrgId, orderByComparator,
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

	protected ClassCoursMapping getByclassOrgId_PrevAndNext(
		Session session, ClassCoursMapping classCoursMapping, long classOrgId,
		OrderByComparator<ClassCoursMapping> orderByComparator,
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

		sb.append(_SQL_SELECT_CLASSCOURSMAPPING_WHERE);

		sb.append(_FINDER_COLUMN_CLASSORGID_CLASSORGID_2);

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
			sb.append(ClassCoursMappingModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(classOrgId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						classCoursMapping)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ClassCoursMapping> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the class cours mappings where classOrgId = &#63; from the database.
	 *
	 * @param classOrgId the class org ID
	 */
	@Override
	public void removeByclassOrgId(long classOrgId) {
		for (ClassCoursMapping classCoursMapping :
				findByclassOrgId(
					classOrgId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(classCoursMapping);
		}
	}

	/**
	 * Returns the number of class cours mappings where classOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @return the number of matching class cours mappings
	 */
	@Override
	public int countByclassOrgId(long classOrgId) {
		FinderPath finderPath = _finderPathCountByclassOrgId;

		Object[] finderArgs = new Object[] {classOrgId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CLASSCOURSMAPPING_WHERE);

			sb.append(_FINDER_COLUMN_CLASSORGID_CLASSORGID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classOrgId);

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

	private static final String _FINDER_COLUMN_CLASSORGID_CLASSORGID_2 =
		"classCoursMapping.classOrgId = ?";

	private FinderPath _finderPathWithPaginationFindBycoursOrgId;
	private FinderPath _finderPathWithoutPaginationFindBycoursOrgId;
	private FinderPath _finderPathCountBycoursOrgId;

	/**
	 * Returns all the class cours mappings where coursOrgId = &#63;.
	 *
	 * @param coursOrgId the cours org ID
	 * @return the matching class cours mappings
	 */
	@Override
	public List<ClassCoursMapping> findBycoursOrgId(long coursOrgId) {
		return findBycoursOrgId(
			coursOrgId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the class cours mappings where coursOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param coursOrgId the cours org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @return the range of matching class cours mappings
	 */
	@Override
	public List<ClassCoursMapping> findBycoursOrgId(
		long coursOrgId, int start, int end) {

		return findBycoursOrgId(coursOrgId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the class cours mappings where coursOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param coursOrgId the cours org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching class cours mappings
	 */
	@Override
	public List<ClassCoursMapping> findBycoursOrgId(
		long coursOrgId, int start, int end,
		OrderByComparator<ClassCoursMapping> orderByComparator) {

		return findBycoursOrgId(
			coursOrgId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the class cours mappings where coursOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param coursOrgId the cours org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching class cours mappings
	 */
	@Override
	public List<ClassCoursMapping> findBycoursOrgId(
		long coursOrgId, int start, int end,
		OrderByComparator<ClassCoursMapping> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBycoursOrgId;
				finderArgs = new Object[] {coursOrgId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBycoursOrgId;
			finderArgs = new Object[] {
				coursOrgId, start, end, orderByComparator
			};
		}

		List<ClassCoursMapping> list = null;

		if (useFinderCache) {
			list = (List<ClassCoursMapping>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (ClassCoursMapping classCoursMapping : list) {
					if (coursOrgId != classCoursMapping.getCoursOrgId()) {
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

			sb.append(_SQL_SELECT_CLASSCOURSMAPPING_WHERE);

			sb.append(_FINDER_COLUMN_COURSORGID_COURSORGID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ClassCoursMappingModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(coursOrgId);

				list = (List<ClassCoursMapping>)QueryUtil.list(
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
	 * Returns the first class cours mapping in the ordered set where coursOrgId = &#63;.
	 *
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching class cours mapping
	 * @throws NoSuchClassCoursMappingException if a matching class cours mapping could not be found
	 */
	@Override
	public ClassCoursMapping findBycoursOrgId_First(
			long coursOrgId,
			OrderByComparator<ClassCoursMapping> orderByComparator)
		throws NoSuchClassCoursMappingException {

		ClassCoursMapping classCoursMapping = fetchBycoursOrgId_First(
			coursOrgId, orderByComparator);

		if (classCoursMapping != null) {
			return classCoursMapping;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("coursOrgId=");
		sb.append(coursOrgId);

		sb.append("}");

		throw new NoSuchClassCoursMappingException(sb.toString());
	}

	/**
	 * Returns the first class cours mapping in the ordered set where coursOrgId = &#63;.
	 *
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching class cours mapping, or <code>null</code> if a matching class cours mapping could not be found
	 */
	@Override
	public ClassCoursMapping fetchBycoursOrgId_First(
		long coursOrgId,
		OrderByComparator<ClassCoursMapping> orderByComparator) {

		List<ClassCoursMapping> list = findBycoursOrgId(
			coursOrgId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last class cours mapping in the ordered set where coursOrgId = &#63;.
	 *
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching class cours mapping
	 * @throws NoSuchClassCoursMappingException if a matching class cours mapping could not be found
	 */
	@Override
	public ClassCoursMapping findBycoursOrgId_Last(
			long coursOrgId,
			OrderByComparator<ClassCoursMapping> orderByComparator)
		throws NoSuchClassCoursMappingException {

		ClassCoursMapping classCoursMapping = fetchBycoursOrgId_Last(
			coursOrgId, orderByComparator);

		if (classCoursMapping != null) {
			return classCoursMapping;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("coursOrgId=");
		sb.append(coursOrgId);

		sb.append("}");

		throw new NoSuchClassCoursMappingException(sb.toString());
	}

	/**
	 * Returns the last class cours mapping in the ordered set where coursOrgId = &#63;.
	 *
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching class cours mapping, or <code>null</code> if a matching class cours mapping could not be found
	 */
	@Override
	public ClassCoursMapping fetchBycoursOrgId_Last(
		long coursOrgId,
		OrderByComparator<ClassCoursMapping> orderByComparator) {

		int count = countBycoursOrgId(coursOrgId);

		if (count == 0) {
			return null;
		}

		List<ClassCoursMapping> list = findBycoursOrgId(
			coursOrgId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the class cours mappings before and after the current class cours mapping in the ordered set where coursOrgId = &#63;.
	 *
	 * @param mappingId the primary key of the current class cours mapping
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next class cours mapping
	 * @throws NoSuchClassCoursMappingException if a class cours mapping with the primary key could not be found
	 */
	@Override
	public ClassCoursMapping[] findBycoursOrgId_PrevAndNext(
			long mappingId, long coursOrgId,
			OrderByComparator<ClassCoursMapping> orderByComparator)
		throws NoSuchClassCoursMappingException {

		ClassCoursMapping classCoursMapping = findByPrimaryKey(mappingId);

		Session session = null;

		try {
			session = openSession();

			ClassCoursMapping[] array = new ClassCoursMappingImpl[3];

			array[0] = getBycoursOrgId_PrevAndNext(
				session, classCoursMapping, coursOrgId, orderByComparator,
				true);

			array[1] = classCoursMapping;

			array[2] = getBycoursOrgId_PrevAndNext(
				session, classCoursMapping, coursOrgId, orderByComparator,
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

	protected ClassCoursMapping getBycoursOrgId_PrevAndNext(
		Session session, ClassCoursMapping classCoursMapping, long coursOrgId,
		OrderByComparator<ClassCoursMapping> orderByComparator,
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

		sb.append(_SQL_SELECT_CLASSCOURSMAPPING_WHERE);

		sb.append(_FINDER_COLUMN_COURSORGID_COURSORGID_2);

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
			sb.append(ClassCoursMappingModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(coursOrgId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						classCoursMapping)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ClassCoursMapping> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the class cours mappings where coursOrgId = &#63; from the database.
	 *
	 * @param coursOrgId the cours org ID
	 */
	@Override
	public void removeBycoursOrgId(long coursOrgId) {
		for (ClassCoursMapping classCoursMapping :
				findBycoursOrgId(
					coursOrgId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(classCoursMapping);
		}
	}

	/**
	 * Returns the number of class cours mappings where coursOrgId = &#63;.
	 *
	 * @param coursOrgId the cours org ID
	 * @return the number of matching class cours mappings
	 */
	@Override
	public int countBycoursOrgId(long coursOrgId) {
		FinderPath finderPath = _finderPathCountBycoursOrgId;

		Object[] finderArgs = new Object[] {coursOrgId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CLASSCOURSMAPPING_WHERE);

			sb.append(_FINDER_COLUMN_COURSORGID_COURSORGID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(coursOrgId);

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

	private static final String _FINDER_COLUMN_COURSORGID_COURSORGID_2 =
		"classCoursMapping.coursOrgId = ?";

	private FinderPath _finderPathWithPaginationFindByclassOrgId_CoursOrgId;
	private FinderPath _finderPathWithoutPaginationFindByclassOrgId_CoursOrgId;
	private FinderPath _finderPathCountByclassOrgId_CoursOrgId;

	/**
	 * Returns all the class cours mappings where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @return the matching class cours mappings
	 */
	@Override
	public List<ClassCoursMapping> findByclassOrgId_CoursOrgId(
		long classOrgId, long coursOrgId) {

		return findByclassOrgId_CoursOrgId(
			classOrgId, coursOrgId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the class cours mappings where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @return the range of matching class cours mappings
	 */
	@Override
	public List<ClassCoursMapping> findByclassOrgId_CoursOrgId(
		long classOrgId, long coursOrgId, int start, int end) {

		return findByclassOrgId_CoursOrgId(
			classOrgId, coursOrgId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the class cours mappings where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching class cours mappings
	 */
	@Override
	public List<ClassCoursMapping> findByclassOrgId_CoursOrgId(
		long classOrgId, long coursOrgId, int start, int end,
		OrderByComparator<ClassCoursMapping> orderByComparator) {

		return findByclassOrgId_CoursOrgId(
			classOrgId, coursOrgId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the class cours mappings where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching class cours mappings
	 */
	@Override
	public List<ClassCoursMapping> findByclassOrgId_CoursOrgId(
		long classOrgId, long coursOrgId, int start, int end,
		OrderByComparator<ClassCoursMapping> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByclassOrgId_CoursOrgId;
				finderArgs = new Object[] {classOrgId, coursOrgId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByclassOrgId_CoursOrgId;
			finderArgs = new Object[] {
				classOrgId, coursOrgId, start, end, orderByComparator
			};
		}

		List<ClassCoursMapping> list = null;

		if (useFinderCache) {
			list = (List<ClassCoursMapping>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (ClassCoursMapping classCoursMapping : list) {
					if ((classOrgId != classCoursMapping.getClassOrgId()) ||
						(coursOrgId != classCoursMapping.getCoursOrgId())) {

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

			sb.append(_SQL_SELECT_CLASSCOURSMAPPING_WHERE);

			sb.append(_FINDER_COLUMN_CLASSORGID_COURSORGID_CLASSORGID_2);

			sb.append(_FINDER_COLUMN_CLASSORGID_COURSORGID_COURSORGID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ClassCoursMappingModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classOrgId);

				queryPos.add(coursOrgId);

				list = (List<ClassCoursMapping>)QueryUtil.list(
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
	 * Returns the first class cours mapping in the ordered set where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching class cours mapping
	 * @throws NoSuchClassCoursMappingException if a matching class cours mapping could not be found
	 */
	@Override
	public ClassCoursMapping findByclassOrgId_CoursOrgId_First(
			long classOrgId, long coursOrgId,
			OrderByComparator<ClassCoursMapping> orderByComparator)
		throws NoSuchClassCoursMappingException {

		ClassCoursMapping classCoursMapping =
			fetchByclassOrgId_CoursOrgId_First(
				classOrgId, coursOrgId, orderByComparator);

		if (classCoursMapping != null) {
			return classCoursMapping;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classOrgId=");
		sb.append(classOrgId);

		sb.append(", coursOrgId=");
		sb.append(coursOrgId);

		sb.append("}");

		throw new NoSuchClassCoursMappingException(sb.toString());
	}

	/**
	 * Returns the first class cours mapping in the ordered set where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching class cours mapping, or <code>null</code> if a matching class cours mapping could not be found
	 */
	@Override
	public ClassCoursMapping fetchByclassOrgId_CoursOrgId_First(
		long classOrgId, long coursOrgId,
		OrderByComparator<ClassCoursMapping> orderByComparator) {

		List<ClassCoursMapping> list = findByclassOrgId_CoursOrgId(
			classOrgId, coursOrgId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last class cours mapping in the ordered set where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching class cours mapping
	 * @throws NoSuchClassCoursMappingException if a matching class cours mapping could not be found
	 */
	@Override
	public ClassCoursMapping findByclassOrgId_CoursOrgId_Last(
			long classOrgId, long coursOrgId,
			OrderByComparator<ClassCoursMapping> orderByComparator)
		throws NoSuchClassCoursMappingException {

		ClassCoursMapping classCoursMapping = fetchByclassOrgId_CoursOrgId_Last(
			classOrgId, coursOrgId, orderByComparator);

		if (classCoursMapping != null) {
			return classCoursMapping;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classOrgId=");
		sb.append(classOrgId);

		sb.append(", coursOrgId=");
		sb.append(coursOrgId);

		sb.append("}");

		throw new NoSuchClassCoursMappingException(sb.toString());
	}

	/**
	 * Returns the last class cours mapping in the ordered set where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching class cours mapping, or <code>null</code> if a matching class cours mapping could not be found
	 */
	@Override
	public ClassCoursMapping fetchByclassOrgId_CoursOrgId_Last(
		long classOrgId, long coursOrgId,
		OrderByComparator<ClassCoursMapping> orderByComparator) {

		int count = countByclassOrgId_CoursOrgId(classOrgId, coursOrgId);

		if (count == 0) {
			return null;
		}

		List<ClassCoursMapping> list = findByclassOrgId_CoursOrgId(
			classOrgId, coursOrgId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the class cours mappings before and after the current class cours mapping in the ordered set where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * @param mappingId the primary key of the current class cours mapping
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next class cours mapping
	 * @throws NoSuchClassCoursMappingException if a class cours mapping with the primary key could not be found
	 */
	@Override
	public ClassCoursMapping[] findByclassOrgId_CoursOrgId_PrevAndNext(
			long mappingId, long classOrgId, long coursOrgId,
			OrderByComparator<ClassCoursMapping> orderByComparator)
		throws NoSuchClassCoursMappingException {

		ClassCoursMapping classCoursMapping = findByPrimaryKey(mappingId);

		Session session = null;

		try {
			session = openSession();

			ClassCoursMapping[] array = new ClassCoursMappingImpl[3];

			array[0] = getByclassOrgId_CoursOrgId_PrevAndNext(
				session, classCoursMapping, classOrgId, coursOrgId,
				orderByComparator, true);

			array[1] = classCoursMapping;

			array[2] = getByclassOrgId_CoursOrgId_PrevAndNext(
				session, classCoursMapping, classOrgId, coursOrgId,
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

	protected ClassCoursMapping getByclassOrgId_CoursOrgId_PrevAndNext(
		Session session, ClassCoursMapping classCoursMapping, long classOrgId,
		long coursOrgId, OrderByComparator<ClassCoursMapping> orderByComparator,
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

		sb.append(_SQL_SELECT_CLASSCOURSMAPPING_WHERE);

		sb.append(_FINDER_COLUMN_CLASSORGID_COURSORGID_CLASSORGID_2);

		sb.append(_FINDER_COLUMN_CLASSORGID_COURSORGID_COURSORGID_2);

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
			sb.append(ClassCoursMappingModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(classOrgId);

		queryPos.add(coursOrgId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						classCoursMapping)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ClassCoursMapping> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the class cours mappings where classOrgId = &#63; and coursOrgId = &#63; from the database.
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 */
	@Override
	public void removeByclassOrgId_CoursOrgId(
		long classOrgId, long coursOrgId) {

		for (ClassCoursMapping classCoursMapping :
				findByclassOrgId_CoursOrgId(
					classOrgId, coursOrgId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(classCoursMapping);
		}
	}

	/**
	 * Returns the number of class cours mappings where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @return the number of matching class cours mappings
	 */
	@Override
	public int countByclassOrgId_CoursOrgId(long classOrgId, long coursOrgId) {
		FinderPath finderPath = _finderPathCountByclassOrgId_CoursOrgId;

		Object[] finderArgs = new Object[] {classOrgId, coursOrgId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_CLASSCOURSMAPPING_WHERE);

			sb.append(_FINDER_COLUMN_CLASSORGID_COURSORGID_CLASSORGID_2);

			sb.append(_FINDER_COLUMN_CLASSORGID_COURSORGID_COURSORGID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classOrgId);

				queryPos.add(coursOrgId);

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
		_FINDER_COLUMN_CLASSORGID_COURSORGID_CLASSORGID_2 =
			"classCoursMapping.classOrgId = ? AND ";

	private static final String
		_FINDER_COLUMN_CLASSORGID_COURSORGID_COURSORGID_2 =
			"classCoursMapping.coursOrgId = ?";

	public ClassCoursMappingPersistenceImpl() {
		setModelClass(ClassCoursMapping.class);

		setModelImplClass(ClassCoursMappingImpl.class);
		setModelPKClass(long.class);

		setTable(ClassCoursMappingTable.INSTANCE);
	}

	/**
	 * Caches the class cours mapping in the entity cache if it is enabled.
	 *
	 * @param classCoursMapping the class cours mapping
	 */
	@Override
	public void cacheResult(ClassCoursMapping classCoursMapping) {
		entityCache.putResult(
			ClassCoursMappingImpl.class, classCoursMapping.getPrimaryKey(),
			classCoursMapping);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the class cours mappings in the entity cache if it is enabled.
	 *
	 * @param classCoursMappings the class cours mappings
	 */
	@Override
	public void cacheResult(List<ClassCoursMapping> classCoursMappings) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (classCoursMappings.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ClassCoursMapping classCoursMapping : classCoursMappings) {
			if (entityCache.getResult(
					ClassCoursMappingImpl.class,
					classCoursMapping.getPrimaryKey()) == null) {

				cacheResult(classCoursMapping);
			}
		}
	}

	/**
	 * Clears the cache for all class cours mappings.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ClassCoursMappingImpl.class);

		finderCache.clearCache(ClassCoursMappingImpl.class);
	}

	/**
	 * Clears the cache for the class cours mapping.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ClassCoursMapping classCoursMapping) {
		entityCache.removeResult(
			ClassCoursMappingImpl.class, classCoursMapping);
	}

	@Override
	public void clearCache(List<ClassCoursMapping> classCoursMappings) {
		for (ClassCoursMapping classCoursMapping : classCoursMappings) {
			entityCache.removeResult(
				ClassCoursMappingImpl.class, classCoursMapping);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ClassCoursMappingImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ClassCoursMappingImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new class cours mapping with the primary key. Does not add the class cours mapping to the database.
	 *
	 * @param mappingId the primary key for the new class cours mapping
	 * @return the new class cours mapping
	 */
	@Override
	public ClassCoursMapping create(long mappingId) {
		ClassCoursMapping classCoursMapping = new ClassCoursMappingImpl();

		classCoursMapping.setNew(true);
		classCoursMapping.setPrimaryKey(mappingId);

		return classCoursMapping;
	}

	/**
	 * Removes the class cours mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mappingId the primary key of the class cours mapping
	 * @return the class cours mapping that was removed
	 * @throws NoSuchClassCoursMappingException if a class cours mapping with the primary key could not be found
	 */
	@Override
	public ClassCoursMapping remove(long mappingId)
		throws NoSuchClassCoursMappingException {

		return remove((Serializable)mappingId);
	}

	/**
	 * Removes the class cours mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the class cours mapping
	 * @return the class cours mapping that was removed
	 * @throws NoSuchClassCoursMappingException if a class cours mapping with the primary key could not be found
	 */
	@Override
	public ClassCoursMapping remove(Serializable primaryKey)
		throws NoSuchClassCoursMappingException {

		Session session = null;

		try {
			session = openSession();

			ClassCoursMapping classCoursMapping =
				(ClassCoursMapping)session.get(
					ClassCoursMappingImpl.class, primaryKey);

			if (classCoursMapping == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchClassCoursMappingException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(classCoursMapping);
		}
		catch (NoSuchClassCoursMappingException noSuchEntityException) {
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
	protected ClassCoursMapping removeImpl(
		ClassCoursMapping classCoursMapping) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(classCoursMapping)) {
				classCoursMapping = (ClassCoursMapping)session.get(
					ClassCoursMappingImpl.class,
					classCoursMapping.getPrimaryKeyObj());
			}

			if (classCoursMapping != null) {
				session.delete(classCoursMapping);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (classCoursMapping != null) {
			clearCache(classCoursMapping);
		}

		return classCoursMapping;
	}

	@Override
	public ClassCoursMapping updateImpl(ClassCoursMapping classCoursMapping) {
		boolean isNew = classCoursMapping.isNew();

		if (!(classCoursMapping instanceof ClassCoursMappingModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(classCoursMapping.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					classCoursMapping);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in classCoursMapping proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ClassCoursMapping implementation " +
					classCoursMapping.getClass());
		}

		ClassCoursMappingModelImpl classCoursMappingModelImpl =
			(ClassCoursMappingModelImpl)classCoursMapping;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(classCoursMapping);
			}
			else {
				classCoursMapping = (ClassCoursMapping)session.merge(
					classCoursMapping);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ClassCoursMappingImpl.class, classCoursMappingModelImpl, false,
			true);

		if (isNew) {
			classCoursMapping.setNew(false);
		}

		classCoursMapping.resetOriginalValues();

		return classCoursMapping;
	}

	/**
	 * Returns the class cours mapping with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the class cours mapping
	 * @return the class cours mapping
	 * @throws NoSuchClassCoursMappingException if a class cours mapping with the primary key could not be found
	 */
	@Override
	public ClassCoursMapping findByPrimaryKey(Serializable primaryKey)
		throws NoSuchClassCoursMappingException {

		ClassCoursMapping classCoursMapping = fetchByPrimaryKey(primaryKey);

		if (classCoursMapping == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchClassCoursMappingException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return classCoursMapping;
	}

	/**
	 * Returns the class cours mapping with the primary key or throws a <code>NoSuchClassCoursMappingException</code> if it could not be found.
	 *
	 * @param mappingId the primary key of the class cours mapping
	 * @return the class cours mapping
	 * @throws NoSuchClassCoursMappingException if a class cours mapping with the primary key could not be found
	 */
	@Override
	public ClassCoursMapping findByPrimaryKey(long mappingId)
		throws NoSuchClassCoursMappingException {

		return findByPrimaryKey((Serializable)mappingId);
	}

	/**
	 * Returns the class cours mapping with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param mappingId the primary key of the class cours mapping
	 * @return the class cours mapping, or <code>null</code> if a class cours mapping with the primary key could not be found
	 */
	@Override
	public ClassCoursMapping fetchByPrimaryKey(long mappingId) {
		return fetchByPrimaryKey((Serializable)mappingId);
	}

	/**
	 * Returns all the class cours mappings.
	 *
	 * @return the class cours mappings
	 */
	@Override
	public List<ClassCoursMapping> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the class cours mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @return the range of class cours mappings
	 */
	@Override
	public List<ClassCoursMapping> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the class cours mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of class cours mappings
	 */
	@Override
	public List<ClassCoursMapping> findAll(
		int start, int end,
		OrderByComparator<ClassCoursMapping> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the class cours mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of class cours mappings
	 */
	@Override
	public List<ClassCoursMapping> findAll(
		int start, int end,
		OrderByComparator<ClassCoursMapping> orderByComparator,
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

		List<ClassCoursMapping> list = null;

		if (useFinderCache) {
			list = (List<ClassCoursMapping>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_CLASSCOURSMAPPING);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_CLASSCOURSMAPPING;

				sql = sql.concat(ClassCoursMappingModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ClassCoursMapping>)QueryUtil.list(
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
	 * Removes all the class cours mappings from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ClassCoursMapping classCoursMapping : findAll()) {
			remove(classCoursMapping);
		}
	}

	/**
	 * Returns the number of class cours mappings.
	 *
	 * @return the number of class cours mappings
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_CLASSCOURSMAPPING);

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
		return "mappingId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_CLASSCOURSMAPPING;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ClassCoursMappingModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the class cours mapping persistence.
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

		_finderPathWithPaginationFindByclassOrgId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByclassOrgId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"classOrgId"}, true);

		_finderPathWithoutPaginationFindByclassOrgId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByclassOrgId",
			new String[] {Long.class.getName()}, new String[] {"classOrgId"},
			true);

		_finderPathCountByclassOrgId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByclassOrgId",
			new String[] {Long.class.getName()}, new String[] {"classOrgId"},
			false);

		_finderPathWithPaginationFindBycoursOrgId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBycoursOrgId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"coursOrgId"}, true);

		_finderPathWithoutPaginationFindBycoursOrgId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBycoursOrgId",
			new String[] {Long.class.getName()}, new String[] {"coursOrgId"},
			true);

		_finderPathCountBycoursOrgId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBycoursOrgId",
			new String[] {Long.class.getName()}, new String[] {"coursOrgId"},
			false);

		_finderPathWithPaginationFindByclassOrgId_CoursOrgId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByclassOrgId_CoursOrgId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"classOrgId", "coursOrgId"}, true);

		_finderPathWithoutPaginationFindByclassOrgId_CoursOrgId =
			new FinderPath(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByclassOrgId_CoursOrgId",
				new String[] {Long.class.getName(), Long.class.getName()},
				new String[] {"classOrgId", "coursOrgId"}, true);

		_finderPathCountByclassOrgId_CoursOrgId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByclassOrgId_CoursOrgId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"classOrgId", "coursOrgId"}, false);

		_setClassCoursMappingUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setClassCoursMappingUtilPersistence(null);

		entityCache.removeCache(ClassCoursMappingImpl.class.getName());
	}

	private void _setClassCoursMappingUtilPersistence(
		ClassCoursMappingPersistence classCoursMappingPersistence) {

		try {
			Field field = ClassCoursMappingUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, classCoursMappingPersistence);
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

	private static final String _SQL_SELECT_CLASSCOURSMAPPING =
		"SELECT classCoursMapping FROM ClassCoursMapping classCoursMapping";

	private static final String _SQL_SELECT_CLASSCOURSMAPPING_WHERE =
		"SELECT classCoursMapping FROM ClassCoursMapping classCoursMapping WHERE ";

	private static final String _SQL_COUNT_CLASSCOURSMAPPING =
		"SELECT COUNT(classCoursMapping) FROM ClassCoursMapping classCoursMapping";

	private static final String _SQL_COUNT_CLASSCOURSMAPPING_WHERE =
		"SELECT COUNT(classCoursMapping) FROM ClassCoursMapping classCoursMapping WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "classCoursMapping.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ClassCoursMapping exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ClassCoursMapping exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ClassCoursMappingPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private ClassCoursMappingModelArgumentsResolver
		_classCoursMappingModelArgumentsResolver;

}