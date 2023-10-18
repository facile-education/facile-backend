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

package com.weprode.nero.help.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HelpQuestionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see HelpQuestionLocalService
 * @generated
 */
public class HelpQuestionLocalServiceWrapper
	implements HelpQuestionLocalService,
			   ServiceWrapper<HelpQuestionLocalService> {

	public HelpQuestionLocalServiceWrapper() {
		this(null);
	}

	public HelpQuestionLocalServiceWrapper(
		HelpQuestionLocalService helpQuestionLocalService) {

		_helpQuestionLocalService = helpQuestionLocalService;
	}

	/**
	 * Adds the help question to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpQuestionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpQuestion the help question
	 * @return the help question that was added
	 */
	@Override
	public com.weprode.nero.help.model.HelpQuestion addHelpQuestion(
		com.weprode.nero.help.model.HelpQuestion helpQuestion) {

		return _helpQuestionLocalService.addHelpQuestion(helpQuestion);
	}

	@Override
	public com.weprode.nero.help.model.HelpQuestion addHelpQuestion(
		long itemId, String question, String answer) {

		return _helpQuestionLocalService.addHelpQuestion(
			itemId, question, answer);
	}

	/**
	 * Creates a new help question with the primary key. Does not add the help question to the database.
	 *
	 * @param questionId the primary key for the new help question
	 * @return the new help question
	 */
	@Override
	public com.weprode.nero.help.model.HelpQuestion createHelpQuestion(
		long questionId) {

		return _helpQuestionLocalService.createHelpQuestion(questionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpQuestionLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the help question from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpQuestionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpQuestion the help question
	 * @return the help question that was removed
	 */
	@Override
	public com.weprode.nero.help.model.HelpQuestion deleteHelpQuestion(
		com.weprode.nero.help.model.HelpQuestion helpQuestion) {

		return _helpQuestionLocalService.deleteHelpQuestion(helpQuestion);
	}

	/**
	 * Deletes the help question with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpQuestionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param questionId the primary key of the help question
	 * @return the help question that was removed
	 * @throws PortalException if a help question with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.help.model.HelpQuestion deleteHelpQuestion(
			long questionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpQuestionLocalService.deleteHelpQuestion(questionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpQuestionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _helpQuestionLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _helpQuestionLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _helpQuestionLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _helpQuestionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.help.model.impl.HelpQuestionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _helpQuestionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.help.model.impl.HelpQuestionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _helpQuestionLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _helpQuestionLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _helpQuestionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.help.model.HelpQuestion fetchHelpQuestion(
		long questionId) {

		return _helpQuestionLocalService.fetchHelpQuestion(questionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _helpQuestionLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the help question with the primary key.
	 *
	 * @param questionId the primary key of the help question
	 * @return the help question
	 * @throws PortalException if a help question with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.help.model.HelpQuestion getHelpQuestion(
			long questionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpQuestionLocalService.getHelpQuestion(questionId);
	}

	/**
	 * Returns a range of all the help questions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.help.model.impl.HelpQuestionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help questions
	 * @param end the upper bound of the range of help questions (not inclusive)
	 * @return the range of help questions
	 */
	@Override
	public java.util.List<com.weprode.nero.help.model.HelpQuestion>
		getHelpQuestions(int start, int end) {

		return _helpQuestionLocalService.getHelpQuestions(start, end);
	}

	@Override
	public java.util.List<com.weprode.nero.help.model.HelpQuestion>
		getHelpQuestions(long itemId) {

		return _helpQuestionLocalService.getHelpQuestions(itemId);
	}

	/**
	 * Returns the number of help questions.
	 *
	 * @return the number of help questions
	 */
	@Override
	public int getHelpQuestionsCount() {
		return _helpQuestionLocalService.getHelpQuestionsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _helpQuestionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _helpQuestionLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpQuestionLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean removeQuestionsForHelpItem(long itemId) {
		return _helpQuestionLocalService.removeQuestionsForHelpItem(itemId);
	}

	/**
	 * Updates the help question in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpQuestionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpQuestion the help question
	 * @return the help question that was updated
	 */
	@Override
	public com.weprode.nero.help.model.HelpQuestion updateHelpQuestion(
		com.weprode.nero.help.model.HelpQuestion helpQuestion) {

		return _helpQuestionLocalService.updateHelpQuestion(helpQuestion);
	}

	@Override
	public HelpQuestionLocalService getWrappedService() {
		return _helpQuestionLocalService;
	}

	@Override
	public void setWrappedService(
		HelpQuestionLocalService helpQuestionLocalService) {

		_helpQuestionLocalService = helpQuestionLocalService;
	}

	private HelpQuestionLocalService _helpQuestionLocalService;

}