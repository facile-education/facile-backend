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

package com.weprode.facile.help.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.help.exception.NoSuchQuestionException;
import com.weprode.facile.help.model.HelpQuestion;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the help question service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpQuestionUtil
 * @generated
 */
@ProviderType
public interface HelpQuestionPersistence extends BasePersistence<HelpQuestion> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HelpQuestionUtil} to access the help question persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the help questions where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the matching help questions
	 */
	public java.util.List<HelpQuestion> findByitemId(long itemId);

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
	public java.util.List<HelpQuestion> findByitemId(
		long itemId, int start, int end);

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
	public java.util.List<HelpQuestion> findByitemId(
		long itemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpQuestion>
			orderByComparator);

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
	public java.util.List<HelpQuestion> findByitemId(
		long itemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpQuestion>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first help question in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help question
	 * @throws NoSuchQuestionException if a matching help question could not be found
	 */
	public HelpQuestion findByitemId_First(
			long itemId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpQuestion>
				orderByComparator)
		throws NoSuchQuestionException;

	/**
	 * Returns the first help question in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help question, or <code>null</code> if a matching help question could not be found
	 */
	public HelpQuestion fetchByitemId_First(
		long itemId,
		com.liferay.portal.kernel.util.OrderByComparator<HelpQuestion>
			orderByComparator);

	/**
	 * Returns the last help question in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help question
	 * @throws NoSuchQuestionException if a matching help question could not be found
	 */
	public HelpQuestion findByitemId_Last(
			long itemId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpQuestion>
				orderByComparator)
		throws NoSuchQuestionException;

	/**
	 * Returns the last help question in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help question, or <code>null</code> if a matching help question could not be found
	 */
	public HelpQuestion fetchByitemId_Last(
		long itemId,
		com.liferay.portal.kernel.util.OrderByComparator<HelpQuestion>
			orderByComparator);

	/**
	 * Returns the help questions before and after the current help question in the ordered set where itemId = &#63;.
	 *
	 * @param questionId the primary key of the current help question
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help question
	 * @throws NoSuchQuestionException if a help question with the primary key could not be found
	 */
	public HelpQuestion[] findByitemId_PrevAndNext(
			long questionId, long itemId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpQuestion>
				orderByComparator)
		throws NoSuchQuestionException;

	/**
	 * Removes all the help questions where itemId = &#63; from the database.
	 *
	 * @param itemId the item ID
	 */
	public void removeByitemId(long itemId);

	/**
	 * Returns the number of help questions where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the number of matching help questions
	 */
	public int countByitemId(long itemId);

	/**
	 * Caches the help question in the entity cache if it is enabled.
	 *
	 * @param helpQuestion the help question
	 */
	public void cacheResult(HelpQuestion helpQuestion);

	/**
	 * Caches the help questions in the entity cache if it is enabled.
	 *
	 * @param helpQuestions the help questions
	 */
	public void cacheResult(java.util.List<HelpQuestion> helpQuestions);

	/**
	 * Creates a new help question with the primary key. Does not add the help question to the database.
	 *
	 * @param questionId the primary key for the new help question
	 * @return the new help question
	 */
	public HelpQuestion create(long questionId);

	/**
	 * Removes the help question with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param questionId the primary key of the help question
	 * @return the help question that was removed
	 * @throws NoSuchQuestionException if a help question with the primary key could not be found
	 */
	public HelpQuestion remove(long questionId) throws NoSuchQuestionException;

	public HelpQuestion updateImpl(HelpQuestion helpQuestion);

	/**
	 * Returns the help question with the primary key or throws a <code>NoSuchQuestionException</code> if it could not be found.
	 *
	 * @param questionId the primary key of the help question
	 * @return the help question
	 * @throws NoSuchQuestionException if a help question with the primary key could not be found
	 */
	public HelpQuestion findByPrimaryKey(long questionId)
		throws NoSuchQuestionException;

	/**
	 * Returns the help question with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param questionId the primary key of the help question
	 * @return the help question, or <code>null</code> if a help question with the primary key could not be found
	 */
	public HelpQuestion fetchByPrimaryKey(long questionId);

	/**
	 * Returns all the help questions.
	 *
	 * @return the help questions
	 */
	public java.util.List<HelpQuestion> findAll();

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
	public java.util.List<HelpQuestion> findAll(int start, int end);

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
	public java.util.List<HelpQuestion> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpQuestion>
			orderByComparator);

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
	public java.util.List<HelpQuestion> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpQuestion>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the help questions from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of help questions.
	 *
	 * @return the number of help questions
	 */
	public int countAll();

}