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

package com.weprode.nero.user.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NewsAdminLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see NewsAdminLocalService
 * @generated
 */
public class NewsAdminLocalServiceWrapper
	implements NewsAdminLocalService, ServiceWrapper<NewsAdminLocalService> {

	public NewsAdminLocalServiceWrapper() {
		this(null);
	}

	public NewsAdminLocalServiceWrapper(
		NewsAdminLocalService newsAdminLocalService) {

		_newsAdminLocalService = newsAdminLocalService;
	}

	@Override
	public com.weprode.nero.user.model.NewsAdmin addDelegate()
		throws com.liferay.portal.kernel.exception.SystemException {

		return _newsAdminLocalService.addDelegate();
	}

	/**
	 * Adds the news admin to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsAdminLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsAdmin the news admin
	 * @return the news admin that was added
	 */
	@Override
	public com.weprode.nero.user.model.NewsAdmin addNewsAdmin(
		com.weprode.nero.user.model.NewsAdmin newsAdmin) {

		return _newsAdminLocalService.addNewsAdmin(newsAdmin);
	}

	@Override
	public boolean addSchoolDelegate(long userId, long schoolId) {
		return _newsAdminLocalService.addSchoolDelegate(userId, schoolId);
	}

	/**
	 * Creates a new news admin with the primary key. Does not add the news admin to the database.
	 *
	 * @param newsAdminId the primary key for the new news admin
	 * @return the new news admin
	 */
	@Override
	public com.weprode.nero.user.model.NewsAdmin createNewsAdmin(
		long newsAdminId) {

		return _newsAdminLocalService.createNewsAdmin(newsAdminId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsAdminLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the news admin with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsAdminLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsAdminId the primary key of the news admin
	 * @return the news admin that was removed
	 * @throws PortalException if a news admin with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.user.model.NewsAdmin deleteNewsAdmin(
			long newsAdminId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsAdminLocalService.deleteNewsAdmin(newsAdminId);
	}

	/**
	 * Deletes the news admin from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsAdminLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsAdmin the news admin
	 * @return the news admin that was removed
	 */
	@Override
	public com.weprode.nero.user.model.NewsAdmin deleteNewsAdmin(
		com.weprode.nero.user.model.NewsAdmin newsAdmin) {

		return _newsAdminLocalService.deleteNewsAdmin(newsAdmin);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsAdminLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _newsAdminLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _newsAdminLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _newsAdminLocalService.dynamicQuery();
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

		return _newsAdminLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.user.model.impl.NewsAdminModelImpl</code>.
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

		return _newsAdminLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.user.model.impl.NewsAdminModelImpl</code>.
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

		return _newsAdminLocalService.dynamicQuery(
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

		return _newsAdminLocalService.dynamicQueryCount(dynamicQuery);
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

		return _newsAdminLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.user.model.NewsAdmin fetchNewsAdmin(
		long newsAdminId) {

		return _newsAdminLocalService.fetchNewsAdmin(newsAdminId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _newsAdminLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _newsAdminLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the news admin with the primary key.
	 *
	 * @param newsAdminId the primary key of the news admin
	 * @return the news admin
	 * @throws PortalException if a news admin with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.user.model.NewsAdmin getNewsAdmin(long newsAdminId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsAdminLocalService.getNewsAdmin(newsAdminId);
	}

	/**
	 * Returns a range of all the news admins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.user.model.impl.NewsAdminModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news admins
	 * @param end the upper bound of the range of news admins (not inclusive)
	 * @return the range of news admins
	 */
	@Override
	public java.util.List<com.weprode.nero.user.model.NewsAdmin> getNewsAdmins(
		int start, int end) {

		return _newsAdminLocalService.getNewsAdmins(start, end);
	}

	/**
	 * Returns the number of news admins.
	 *
	 * @return the number of news admins
	 */
	@Override
	public int getNewsAdminsCount() {
		return _newsAdminLocalService.getNewsAdminsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _newsAdminLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsAdminLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User>
		getSchoolDelegates(Long schoolId) {

		return _newsAdminLocalService.getSchoolDelegates(schoolId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User>
		getSchoolDelegationCandidates(long schoolId, String filter) {

		return _newsAdminLocalService.getSchoolDelegationCandidates(
			schoolId, filter);
	}

	@Override
	public boolean isUserDelegate(com.liferay.portal.kernel.model.User user) {
		return _newsAdminLocalService.isUserDelegate(user);
	}

	@Override
	public boolean isUserSchoolDelegate(
		com.liferay.portal.kernel.model.User user, long schoolId) {

		return _newsAdminLocalService.isUserSchoolDelegate(user, schoolId);
	}

	@Override
	public boolean removeSchoolDelegate(long userId, long schoolId) {
		return _newsAdminLocalService.removeSchoolDelegate(userId, schoolId);
	}

	/**
	 * Updates the news admin in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsAdminLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsAdmin the news admin
	 * @return the news admin that was updated
	 */
	@Override
	public com.weprode.nero.user.model.NewsAdmin updateNewsAdmin(
		com.weprode.nero.user.model.NewsAdmin newsAdmin) {

		return _newsAdminLocalService.updateNewsAdmin(newsAdmin);
	}

	@Override
	public NewsAdminLocalService getWrappedService() {
		return _newsAdminLocalService;
	}

	@Override
	public void setWrappedService(NewsAdminLocalService newsAdminLocalService) {
		_newsAdminLocalService = newsAdminLocalService;
	}

	private NewsAdminLocalService _newsAdminLocalService;

}