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

package com.weprode.nero.help.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.help.model.HelpQuestion;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the help question service. This utility wraps <code>com.weprode.nero.help.service.persistence.impl.HelpQuestionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpQuestionPersistence
 * @generated
 */
public class HelpQuestionUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(HelpQuestion helpQuestion) {
		getPersistence().clearCache(helpQuestion);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, HelpQuestion> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<HelpQuestion> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HelpQuestion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HelpQuestion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<HelpQuestion> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static HelpQuestion update(HelpQuestion helpQuestion) {
		return getPersistence().update(helpQuestion);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static HelpQuestion update(
		HelpQuestion helpQuestion, ServiceContext serviceContext) {

		return getPersistence().update(helpQuestion, serviceContext);
	}

	/**
	 * Returns all the help questions where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the matching help questions
	 */
	public static List<HelpQuestion> findByitemId(long itemId) {
		return getPersistence().findByitemId(itemId);
	}

	/**
	 * Returns a range of all the help questions where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpQuestionModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help questions
	 * @param end the upper bound of the range of help questions (not inclusive)
	 * @return the range of matching help questions
	 */
	public static List<HelpQuestion> findByitemId(
		long itemId, int start, int end) {

		return getPersistence().findByitemId(itemId, start, end);
	}

	/**
	 * Returns an ordered range of all the help questions where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpQuestionModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help questions
	 * @param end the upper bound of the range of help questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help questions
	 */
	public static List<HelpQuestion> findByitemId(
		long itemId, int start, int end,
		OrderByComparator<HelpQuestion> orderByComparator) {

		return getPersistence().findByitemId(
			itemId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the help questions where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpQuestionModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help questions
	 * @param end the upper bound of the range of help questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching help questions
	 */
	public static List<HelpQuestion> findByitemId(
		long itemId, int start, int end,
		OrderByComparator<HelpQuestion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByitemId(
			itemId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first help question in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help question
	 * @throws NoSuchQuestionException if a matching help question could not be found
	 */
	public static HelpQuestion findByitemId_First(
			long itemId, OrderByComparator<HelpQuestion> orderByComparator)
		throws com.weprode.nero.help.exception.NoSuchQuestionException {

		return getPersistence().findByitemId_First(itemId, orderByComparator);
	}

	/**
	 * Returns the first help question in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help question, or <code>null</code> if a matching help question could not be found
	 */
	public static HelpQuestion fetchByitemId_First(
		long itemId, OrderByComparator<HelpQuestion> orderByComparator) {

		return getPersistence().fetchByitemId_First(itemId, orderByComparator);
	}

	/**
	 * Returns the last help question in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help question
	 * @throws NoSuchQuestionException if a matching help question could not be found
	 */
	public static HelpQuestion findByitemId_Last(
			long itemId, OrderByComparator<HelpQuestion> orderByComparator)
		throws com.weprode.nero.help.exception.NoSuchQuestionException {

		return getPersistence().findByitemId_Last(itemId, orderByComparator);
	}

	/**
	 * Returns the last help question in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help question, or <code>null</code> if a matching help question could not be found
	 */
	public static HelpQuestion fetchByitemId_Last(
		long itemId, OrderByComparator<HelpQuestion> orderByComparator) {

		return getPersistence().fetchByitemId_Last(itemId, orderByComparator);
	}

	/**
	 * Returns the help questions before and after the current help question in the ordered set where itemId = &#63;.
	 *
	 * @param questionId the primary key of the current help question
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help question
	 * @throws NoSuchQuestionException if a help question with the primary key could not be found
	 */
	public static HelpQuestion[] findByitemId_PrevAndNext(
			long questionId, long itemId,
			OrderByComparator<HelpQuestion> orderByComparator)
		throws com.weprode.nero.help.exception.NoSuchQuestionException {

		return getPersistence().findByitemId_PrevAndNext(
			questionId, itemId, orderByComparator);
	}

	/**
	 * Removes all the help questions where itemId = &#63; from the database.
	 *
	 * @param itemId the item ID
	 */
	public static void removeByitemId(long itemId) {
		getPersistence().removeByitemId(itemId);
	}

	/**
	 * Returns the number of help questions where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the number of matching help questions
	 */
	public static int countByitemId(long itemId) {
		return getPersistence().countByitemId(itemId);
	}

	/**
	 * Caches the help question in the entity cache if it is enabled.
	 *
	 * @param helpQuestion the help question
	 */
	public static void cacheResult(HelpQuestion helpQuestion) {
		getPersistence().cacheResult(helpQuestion);
	}

	/**
	 * Caches the help questions in the entity cache if it is enabled.
	 *
	 * @param helpQuestions the help questions
	 */
	public static void cacheResult(List<HelpQuestion> helpQuestions) {
		getPersistence().cacheResult(helpQuestions);
	}

	/**
	 * Creates a new help question with the primary key. Does not add the help question to the database.
	 *
	 * @param questionId the primary key for the new help question
	 * @return the new help question
	 */
	public static HelpQuestion create(long questionId) {
		return getPersistence().create(questionId);
	}

	/**
	 * Removes the help question with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param questionId the primary key of the help question
	 * @return the help question that was removed
	 * @throws NoSuchQuestionException if a help question with the primary key could not be found
	 */
	public static HelpQuestion remove(long questionId)
		throws com.weprode.nero.help.exception.NoSuchQuestionException {

		return getPersistence().remove(questionId);
	}

	public static HelpQuestion updateImpl(HelpQuestion helpQuestion) {
		return getPersistence().updateImpl(helpQuestion);
	}

	/**
	 * Returns the help question with the primary key or throws a <code>NoSuchQuestionException</code> if it could not be found.
	 *
	 * @param questionId the primary key of the help question
	 * @return the help question
	 * @throws NoSuchQuestionException if a help question with the primary key could not be found
	 */
	public static HelpQuestion findByPrimaryKey(long questionId)
		throws com.weprode.nero.help.exception.NoSuchQuestionException {

		return getPersistence().findByPrimaryKey(questionId);
	}

	/**
	 * Returns the help question with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param questionId the primary key of the help question
	 * @return the help question, or <code>null</code> if a help question with the primary key could not be found
	 */
	public static HelpQuestion fetchByPrimaryKey(long questionId) {
		return getPersistence().fetchByPrimaryKey(questionId);
	}

	/**
	 * Returns all the help questions.
	 *
	 * @return the help questions
	 */
	public static List<HelpQuestion> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the help questions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpQuestionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help questions
	 * @param end the upper bound of the range of help questions (not inclusive)
	 * @return the range of help questions
	 */
	public static List<HelpQuestion> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the help questions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpQuestionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help questions
	 * @param end the upper bound of the range of help questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of help questions
	 */
	public static List<HelpQuestion> findAll(
		int start, int end, OrderByComparator<HelpQuestion> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the help questions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpQuestionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help questions
	 * @param end the upper bound of the range of help questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of help questions
	 */
	public static List<HelpQuestion> findAll(
		int start, int end, OrderByComparator<HelpQuestion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the help questions from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of help questions.
	 *
	 * @return the number of help questions
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static HelpQuestionPersistence getPersistence() {
		return _persistence;
	}

	private static volatile HelpQuestionPersistence _persistence;

}