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

package com.weprode.nero.schedule.service.persistence.impl;

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

import com.weprode.nero.schedule.exception.NoSuchSubjectGroupColorException;
import com.weprode.nero.schedule.model.SubjectGroupColor;
import com.weprode.nero.schedule.model.SubjectGroupColorTable;
import com.weprode.nero.schedule.model.impl.SubjectGroupColorImpl;
import com.weprode.nero.schedule.model.impl.SubjectGroupColorModelImpl;
import com.weprode.nero.schedule.service.persistence.SubjectGroupColorPersistence;
import com.weprode.nero.schedule.service.persistence.SubjectGroupColorUtil;
import com.weprode.nero.schedule.service.persistence.impl.constants.SchedulePersistenceConstants;

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
 * The persistence implementation for the subject group color service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	service = {SubjectGroupColorPersistence.class, BasePersistence.class}
)
public class SubjectGroupColorPersistenceImpl
	extends BasePersistenceImpl<SubjectGroupColor>
	implements SubjectGroupColorPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SubjectGroupColorUtil</code> to access the subject group color persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SubjectGroupColorImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindBygroupId;
	private FinderPath _finderPathWithoutPaginationFindBygroupId;
	private FinderPath _finderPathCountBygroupId;

	/**
	 * Returns all the subject group colors where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching subject group colors
	 */
	@Override
	public List<SubjectGroupColor> findBygroupId(long groupId) {
		return findBygroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the subject group colors where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @return the range of matching subject group colors
	 */
	@Override
	public List<SubjectGroupColor> findBygroupId(
		long groupId, int start, int end) {

		return findBygroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the subject group colors where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching subject group colors
	 */
	@Override
	public List<SubjectGroupColor> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<SubjectGroupColor> orderByComparator) {

		return findBygroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the subject group colors where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching subject group colors
	 */
	@Override
	public List<SubjectGroupColor> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<SubjectGroupColor> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBygroupId;
				finderArgs = new Object[] {groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBygroupId;
			finderArgs = new Object[] {groupId, start, end, orderByComparator};
		}

		List<SubjectGroupColor> list = null;

		if (useFinderCache) {
			list = (List<SubjectGroupColor>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (SubjectGroupColor subjectGroupColor : list) {
					if (groupId != subjectGroupColor.getGroupId()) {
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

			sb.append(_SQL_SELECT_SUBJECTGROUPCOLOR_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SubjectGroupColorModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<SubjectGroupColor>)QueryUtil.list(
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
	 * Returns the first subject group color in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subject group color
	 * @throws NoSuchSubjectGroupColorException if a matching subject group color could not be found
	 */
	@Override
	public SubjectGroupColor findBygroupId_First(
			long groupId,
			OrderByComparator<SubjectGroupColor> orderByComparator)
		throws NoSuchSubjectGroupColorException {

		SubjectGroupColor subjectGroupColor = fetchBygroupId_First(
			groupId, orderByComparator);

		if (subjectGroupColor != null) {
			return subjectGroupColor;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchSubjectGroupColorException(sb.toString());
	}

	/**
	 * Returns the first subject group color in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subject group color, or <code>null</code> if a matching subject group color could not be found
	 */
	@Override
	public SubjectGroupColor fetchBygroupId_First(
		long groupId, OrderByComparator<SubjectGroupColor> orderByComparator) {

		List<SubjectGroupColor> list = findBygroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last subject group color in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subject group color
	 * @throws NoSuchSubjectGroupColorException if a matching subject group color could not be found
	 */
	@Override
	public SubjectGroupColor findBygroupId_Last(
			long groupId,
			OrderByComparator<SubjectGroupColor> orderByComparator)
		throws NoSuchSubjectGroupColorException {

		SubjectGroupColor subjectGroupColor = fetchBygroupId_Last(
			groupId, orderByComparator);

		if (subjectGroupColor != null) {
			return subjectGroupColor;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchSubjectGroupColorException(sb.toString());
	}

	/**
	 * Returns the last subject group color in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subject group color, or <code>null</code> if a matching subject group color could not be found
	 */
	@Override
	public SubjectGroupColor fetchBygroupId_Last(
		long groupId, OrderByComparator<SubjectGroupColor> orderByComparator) {

		int count = countBygroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<SubjectGroupColor> list = findBygroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the subject group colors before and after the current subject group color in the ordered set where groupId = &#63;.
	 *
	 * @param subjectGroupColorId the primary key of the current subject group color
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next subject group color
	 * @throws NoSuchSubjectGroupColorException if a subject group color with the primary key could not be found
	 */
	@Override
	public SubjectGroupColor[] findBygroupId_PrevAndNext(
			long subjectGroupColorId, long groupId,
			OrderByComparator<SubjectGroupColor> orderByComparator)
		throws NoSuchSubjectGroupColorException {

		SubjectGroupColor subjectGroupColor = findByPrimaryKey(
			subjectGroupColorId);

		Session session = null;

		try {
			session = openSession();

			SubjectGroupColor[] array = new SubjectGroupColorImpl[3];

			array[0] = getBygroupId_PrevAndNext(
				session, subjectGroupColor, groupId, orderByComparator, true);

			array[1] = subjectGroupColor;

			array[2] = getBygroupId_PrevAndNext(
				session, subjectGroupColor, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SubjectGroupColor getBygroupId_PrevAndNext(
		Session session, SubjectGroupColor subjectGroupColor, long groupId,
		OrderByComparator<SubjectGroupColor> orderByComparator,
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

		sb.append(_SQL_SELECT_SUBJECTGROUPCOLOR_WHERE);

		sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			sb.append(SubjectGroupColorModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						subjectGroupColor)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SubjectGroupColor> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the subject group colors where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeBygroupId(long groupId) {
		for (SubjectGroupColor subjectGroupColor :
				findBygroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(subjectGroupColor);
		}
	}

	/**
	 * Returns the number of subject group colors where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching subject group colors
	 */
	@Override
	public int countBygroupId(long groupId) {
		FinderPath finderPath = _finderPathCountBygroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SUBJECTGROUPCOLOR_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"subjectGroupColor.groupId = ?";

	private FinderPath _finderPathWithPaginationFindBygroupId_subject;
	private FinderPath _finderPathWithoutPaginationFindBygroupId_subject;
	private FinderPath _finderPathCountBygroupId_subject;

	/**
	 * Returns all the subject group colors where groupId = &#63; and subject = &#63;.
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @return the matching subject group colors
	 */
	@Override
	public List<SubjectGroupColor> findBygroupId_subject(
		long groupId, String subject) {

		return findBygroupId_subject(
			groupId, subject, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the subject group colors where groupId = &#63; and subject = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @return the range of matching subject group colors
	 */
	@Override
	public List<SubjectGroupColor> findBygroupId_subject(
		long groupId, String subject, int start, int end) {

		return findBygroupId_subject(groupId, subject, start, end, null);
	}

	/**
	 * Returns an ordered range of all the subject group colors where groupId = &#63; and subject = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching subject group colors
	 */
	@Override
	public List<SubjectGroupColor> findBygroupId_subject(
		long groupId, String subject, int start, int end,
		OrderByComparator<SubjectGroupColor> orderByComparator) {

		return findBygroupId_subject(
			groupId, subject, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the subject group colors where groupId = &#63; and subject = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching subject group colors
	 */
	@Override
	public List<SubjectGroupColor> findBygroupId_subject(
		long groupId, String subject, int start, int end,
		OrderByComparator<SubjectGroupColor> orderByComparator,
		boolean useFinderCache) {

		subject = Objects.toString(subject, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBygroupId_subject;
				finderArgs = new Object[] {groupId, subject};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBygroupId_subject;
			finderArgs = new Object[] {
				groupId, subject, start, end, orderByComparator
			};
		}

		List<SubjectGroupColor> list = null;

		if (useFinderCache) {
			list = (List<SubjectGroupColor>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (SubjectGroupColor subjectGroupColor : list) {
					if ((groupId != subjectGroupColor.getGroupId()) ||
						!subject.equals(subjectGroupColor.getSubject())) {

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

			sb.append(_SQL_SELECT_SUBJECTGROUPCOLOR_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_SUBJECT_GROUPID_2);

			boolean bindSubject = false;

			if (subject.isEmpty()) {
				sb.append(_FINDER_COLUMN_GROUPID_SUBJECT_SUBJECT_3);
			}
			else {
				bindSubject = true;

				sb.append(_FINDER_COLUMN_GROUPID_SUBJECT_SUBJECT_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SubjectGroupColorModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				if (bindSubject) {
					queryPos.add(subject);
				}

				list = (List<SubjectGroupColor>)QueryUtil.list(
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
	 * Returns the first subject group color in the ordered set where groupId = &#63; and subject = &#63;.
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subject group color
	 * @throws NoSuchSubjectGroupColorException if a matching subject group color could not be found
	 */
	@Override
	public SubjectGroupColor findBygroupId_subject_First(
			long groupId, String subject,
			OrderByComparator<SubjectGroupColor> orderByComparator)
		throws NoSuchSubjectGroupColorException {

		SubjectGroupColor subjectGroupColor = fetchBygroupId_subject_First(
			groupId, subject, orderByComparator);

		if (subjectGroupColor != null) {
			return subjectGroupColor;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", subject=");
		sb.append(subject);

		sb.append("}");

		throw new NoSuchSubjectGroupColorException(sb.toString());
	}

	/**
	 * Returns the first subject group color in the ordered set where groupId = &#63; and subject = &#63;.
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subject group color, or <code>null</code> if a matching subject group color could not be found
	 */
	@Override
	public SubjectGroupColor fetchBygroupId_subject_First(
		long groupId, String subject,
		OrderByComparator<SubjectGroupColor> orderByComparator) {

		List<SubjectGroupColor> list = findBygroupId_subject(
			groupId, subject, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last subject group color in the ordered set where groupId = &#63; and subject = &#63;.
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subject group color
	 * @throws NoSuchSubjectGroupColorException if a matching subject group color could not be found
	 */
	@Override
	public SubjectGroupColor findBygroupId_subject_Last(
			long groupId, String subject,
			OrderByComparator<SubjectGroupColor> orderByComparator)
		throws NoSuchSubjectGroupColorException {

		SubjectGroupColor subjectGroupColor = fetchBygroupId_subject_Last(
			groupId, subject, orderByComparator);

		if (subjectGroupColor != null) {
			return subjectGroupColor;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", subject=");
		sb.append(subject);

		sb.append("}");

		throw new NoSuchSubjectGroupColorException(sb.toString());
	}

	/**
	 * Returns the last subject group color in the ordered set where groupId = &#63; and subject = &#63;.
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subject group color, or <code>null</code> if a matching subject group color could not be found
	 */
	@Override
	public SubjectGroupColor fetchBygroupId_subject_Last(
		long groupId, String subject,
		OrderByComparator<SubjectGroupColor> orderByComparator) {

		int count = countBygroupId_subject(groupId, subject);

		if (count == 0) {
			return null;
		}

		List<SubjectGroupColor> list = findBygroupId_subject(
			groupId, subject, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the subject group colors before and after the current subject group color in the ordered set where groupId = &#63; and subject = &#63;.
	 *
	 * @param subjectGroupColorId the primary key of the current subject group color
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next subject group color
	 * @throws NoSuchSubjectGroupColorException if a subject group color with the primary key could not be found
	 */
	@Override
	public SubjectGroupColor[] findBygroupId_subject_PrevAndNext(
			long subjectGroupColorId, long groupId, String subject,
			OrderByComparator<SubjectGroupColor> orderByComparator)
		throws NoSuchSubjectGroupColorException {

		subject = Objects.toString(subject, "");

		SubjectGroupColor subjectGroupColor = findByPrimaryKey(
			subjectGroupColorId);

		Session session = null;

		try {
			session = openSession();

			SubjectGroupColor[] array = new SubjectGroupColorImpl[3];

			array[0] = getBygroupId_subject_PrevAndNext(
				session, subjectGroupColor, groupId, subject, orderByComparator,
				true);

			array[1] = subjectGroupColor;

			array[2] = getBygroupId_subject_PrevAndNext(
				session, subjectGroupColor, groupId, subject, orderByComparator,
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

	protected SubjectGroupColor getBygroupId_subject_PrevAndNext(
		Session session, SubjectGroupColor subjectGroupColor, long groupId,
		String subject, OrderByComparator<SubjectGroupColor> orderByComparator,
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

		sb.append(_SQL_SELECT_SUBJECTGROUPCOLOR_WHERE);

		sb.append(_FINDER_COLUMN_GROUPID_SUBJECT_GROUPID_2);

		boolean bindSubject = false;

		if (subject.isEmpty()) {
			sb.append(_FINDER_COLUMN_GROUPID_SUBJECT_SUBJECT_3);
		}
		else {
			bindSubject = true;

			sb.append(_FINDER_COLUMN_GROUPID_SUBJECT_SUBJECT_2);
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
			sb.append(SubjectGroupColorModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (bindSubject) {
			queryPos.add(subject);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						subjectGroupColor)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SubjectGroupColor> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the subject group colors where groupId = &#63; and subject = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 */
	@Override
	public void removeBygroupId_subject(long groupId, String subject) {
		for (SubjectGroupColor subjectGroupColor :
				findBygroupId_subject(
					groupId, subject, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(subjectGroupColor);
		}
	}

	/**
	 * Returns the number of subject group colors where groupId = &#63; and subject = &#63;.
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @return the number of matching subject group colors
	 */
	@Override
	public int countBygroupId_subject(long groupId, String subject) {
		subject = Objects.toString(subject, "");

		FinderPath finderPath = _finderPathCountBygroupId_subject;

		Object[] finderArgs = new Object[] {groupId, subject};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_SUBJECTGROUPCOLOR_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_SUBJECT_GROUPID_2);

			boolean bindSubject = false;

			if (subject.isEmpty()) {
				sb.append(_FINDER_COLUMN_GROUPID_SUBJECT_SUBJECT_3);
			}
			else {
				bindSubject = true;

				sb.append(_FINDER_COLUMN_GROUPID_SUBJECT_SUBJECT_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				if (bindSubject) {
					queryPos.add(subject);
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

	private static final String _FINDER_COLUMN_GROUPID_SUBJECT_GROUPID_2 =
		"subjectGroupColor.groupId = ? AND ";

	private static final String _FINDER_COLUMN_GROUPID_SUBJECT_SUBJECT_2 =
		"subjectGroupColor.subject = ?";

	private static final String _FINDER_COLUMN_GROUPID_SUBJECT_SUBJECT_3 =
		"(subjectGroupColor.subject IS NULL OR subjectGroupColor.subject = '')";

	public SubjectGroupColorPersistenceImpl() {
		setModelClass(SubjectGroupColor.class);

		setModelImplClass(SubjectGroupColorImpl.class);
		setModelPKClass(long.class);

		setTable(SubjectGroupColorTable.INSTANCE);
	}

	/**
	 * Caches the subject group color in the entity cache if it is enabled.
	 *
	 * @param subjectGroupColor the subject group color
	 */
	@Override
	public void cacheResult(SubjectGroupColor subjectGroupColor) {
		entityCache.putResult(
			SubjectGroupColorImpl.class, subjectGroupColor.getPrimaryKey(),
			subjectGroupColor);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the subject group colors in the entity cache if it is enabled.
	 *
	 * @param subjectGroupColors the subject group colors
	 */
	@Override
	public void cacheResult(List<SubjectGroupColor> subjectGroupColors) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (subjectGroupColors.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SubjectGroupColor subjectGroupColor : subjectGroupColors) {
			if (entityCache.getResult(
					SubjectGroupColorImpl.class,
					subjectGroupColor.getPrimaryKey()) == null) {

				cacheResult(subjectGroupColor);
			}
		}
	}

	/**
	 * Clears the cache for all subject group colors.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SubjectGroupColorImpl.class);

		finderCache.clearCache(SubjectGroupColorImpl.class);
	}

	/**
	 * Clears the cache for the subject group color.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SubjectGroupColor subjectGroupColor) {
		entityCache.removeResult(
			SubjectGroupColorImpl.class, subjectGroupColor);
	}

	@Override
	public void clearCache(List<SubjectGroupColor> subjectGroupColors) {
		for (SubjectGroupColor subjectGroupColor : subjectGroupColors) {
			entityCache.removeResult(
				SubjectGroupColorImpl.class, subjectGroupColor);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SubjectGroupColorImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SubjectGroupColorImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new subject group color with the primary key. Does not add the subject group color to the database.
	 *
	 * @param subjectGroupColorId the primary key for the new subject group color
	 * @return the new subject group color
	 */
	@Override
	public SubjectGroupColor create(long subjectGroupColorId) {
		SubjectGroupColor subjectGroupColor = new SubjectGroupColorImpl();

		subjectGroupColor.setNew(true);
		subjectGroupColor.setPrimaryKey(subjectGroupColorId);

		return subjectGroupColor;
	}

	/**
	 * Removes the subject group color with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param subjectGroupColorId the primary key of the subject group color
	 * @return the subject group color that was removed
	 * @throws NoSuchSubjectGroupColorException if a subject group color with the primary key could not be found
	 */
	@Override
	public SubjectGroupColor remove(long subjectGroupColorId)
		throws NoSuchSubjectGroupColorException {

		return remove((Serializable)subjectGroupColorId);
	}

	/**
	 * Removes the subject group color with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the subject group color
	 * @return the subject group color that was removed
	 * @throws NoSuchSubjectGroupColorException if a subject group color with the primary key could not be found
	 */
	@Override
	public SubjectGroupColor remove(Serializable primaryKey)
		throws NoSuchSubjectGroupColorException {

		Session session = null;

		try {
			session = openSession();

			SubjectGroupColor subjectGroupColor =
				(SubjectGroupColor)session.get(
					SubjectGroupColorImpl.class, primaryKey);

			if (subjectGroupColor == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSubjectGroupColorException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(subjectGroupColor);
		}
		catch (NoSuchSubjectGroupColorException noSuchEntityException) {
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
	protected SubjectGroupColor removeImpl(
		SubjectGroupColor subjectGroupColor) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(subjectGroupColor)) {
				subjectGroupColor = (SubjectGroupColor)session.get(
					SubjectGroupColorImpl.class,
					subjectGroupColor.getPrimaryKeyObj());
			}

			if (subjectGroupColor != null) {
				session.delete(subjectGroupColor);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (subjectGroupColor != null) {
			clearCache(subjectGroupColor);
		}

		return subjectGroupColor;
	}

	@Override
	public SubjectGroupColor updateImpl(SubjectGroupColor subjectGroupColor) {
		boolean isNew = subjectGroupColor.isNew();

		if (!(subjectGroupColor instanceof SubjectGroupColorModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(subjectGroupColor.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					subjectGroupColor);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in subjectGroupColor proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SubjectGroupColor implementation " +
					subjectGroupColor.getClass());
		}

		SubjectGroupColorModelImpl subjectGroupColorModelImpl =
			(SubjectGroupColorModelImpl)subjectGroupColor;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(subjectGroupColor);
			}
			else {
				subjectGroupColor = (SubjectGroupColor)session.merge(
					subjectGroupColor);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SubjectGroupColorImpl.class, subjectGroupColorModelImpl, false,
			true);

		if (isNew) {
			subjectGroupColor.setNew(false);
		}

		subjectGroupColor.resetOriginalValues();

		return subjectGroupColor;
	}

	/**
	 * Returns the subject group color with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the subject group color
	 * @return the subject group color
	 * @throws NoSuchSubjectGroupColorException if a subject group color with the primary key could not be found
	 */
	@Override
	public SubjectGroupColor findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSubjectGroupColorException {

		SubjectGroupColor subjectGroupColor = fetchByPrimaryKey(primaryKey);

		if (subjectGroupColor == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSubjectGroupColorException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return subjectGroupColor;
	}

	/**
	 * Returns the subject group color with the primary key or throws a <code>NoSuchSubjectGroupColorException</code> if it could not be found.
	 *
	 * @param subjectGroupColorId the primary key of the subject group color
	 * @return the subject group color
	 * @throws NoSuchSubjectGroupColorException if a subject group color with the primary key could not be found
	 */
	@Override
	public SubjectGroupColor findByPrimaryKey(long subjectGroupColorId)
		throws NoSuchSubjectGroupColorException {

		return findByPrimaryKey((Serializable)subjectGroupColorId);
	}

	/**
	 * Returns the subject group color with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param subjectGroupColorId the primary key of the subject group color
	 * @return the subject group color, or <code>null</code> if a subject group color with the primary key could not be found
	 */
	@Override
	public SubjectGroupColor fetchByPrimaryKey(long subjectGroupColorId) {
		return fetchByPrimaryKey((Serializable)subjectGroupColorId);
	}

	/**
	 * Returns all the subject group colors.
	 *
	 * @return the subject group colors
	 */
	@Override
	public List<SubjectGroupColor> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the subject group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @return the range of subject group colors
	 */
	@Override
	public List<SubjectGroupColor> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the subject group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of subject group colors
	 */
	@Override
	public List<SubjectGroupColor> findAll(
		int start, int end,
		OrderByComparator<SubjectGroupColor> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the subject group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of subject group colors
	 */
	@Override
	public List<SubjectGroupColor> findAll(
		int start, int end,
		OrderByComparator<SubjectGroupColor> orderByComparator,
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

		List<SubjectGroupColor> list = null;

		if (useFinderCache) {
			list = (List<SubjectGroupColor>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SUBJECTGROUPCOLOR);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SUBJECTGROUPCOLOR;

				sql = sql.concat(SubjectGroupColorModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SubjectGroupColor>)QueryUtil.list(
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
	 * Removes all the subject group colors from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SubjectGroupColor subjectGroupColor : findAll()) {
			remove(subjectGroupColor);
		}
	}

	/**
	 * Returns the number of subject group colors.
	 *
	 * @return the number of subject group colors
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SUBJECTGROUPCOLOR);

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
		return "subjectGroupColorId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SUBJECTGROUPCOLOR;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SubjectGroupColorModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the subject group color persistence.
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

		_finderPathWithPaginationFindBygroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBygroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"groupId"}, true);

		_finderPathWithoutPaginationFindBygroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBygroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
			true);

		_finderPathCountBygroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBygroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
			false);

		_finderPathWithPaginationFindBygroupId_subject = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBygroupId_subject",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"groupId", "subject"}, true);

		_finderPathWithoutPaginationFindBygroupId_subject = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBygroupId_subject",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"groupId", "subject"}, true);

		_finderPathCountBygroupId_subject = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBygroupId_subject",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"groupId", "subject"}, false);

		_setSubjectGroupColorUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setSubjectGroupColorUtilPersistence(null);

		entityCache.removeCache(SubjectGroupColorImpl.class.getName());
	}

	private void _setSubjectGroupColorUtilPersistence(
		SubjectGroupColorPersistence subjectGroupColorPersistence) {

		try {
			Field field = SubjectGroupColorUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, subjectGroupColorPersistence);
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

	private static final String _SQL_SELECT_SUBJECTGROUPCOLOR =
		"SELECT subjectGroupColor FROM SubjectGroupColor subjectGroupColor";

	private static final String _SQL_SELECT_SUBJECTGROUPCOLOR_WHERE =
		"SELECT subjectGroupColor FROM SubjectGroupColor subjectGroupColor WHERE ";

	private static final String _SQL_COUNT_SUBJECTGROUPCOLOR =
		"SELECT COUNT(subjectGroupColor) FROM SubjectGroupColor subjectGroupColor";

	private static final String _SQL_COUNT_SUBJECTGROUPCOLOR_WHERE =
		"SELECT COUNT(subjectGroupColor) FROM SubjectGroupColor subjectGroupColor WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "subjectGroupColor.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SubjectGroupColor exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SubjectGroupColor exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SubjectGroupColorPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private SubjectGroupColorModelArgumentsResolver
		_subjectGroupColorModelArgumentsResolver;

}