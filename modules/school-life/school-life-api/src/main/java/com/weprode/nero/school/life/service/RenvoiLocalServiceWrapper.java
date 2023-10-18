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

package com.weprode.nero.school.life.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RenvoiLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RenvoiLocalService
 * @generated
 */
public class RenvoiLocalServiceWrapper
	implements RenvoiLocalService, ServiceWrapper<RenvoiLocalService> {

	public RenvoiLocalServiceWrapper() {
		this(null);
	}

	public RenvoiLocalServiceWrapper(RenvoiLocalService renvoiLocalService) {
		_renvoiLocalService = renvoiLocalService;
	}

	@Override
	public com.weprode.nero.school.life.model.Renvoi addRenvoi(
		long schoollifeSessionId, long teacherId, long sourceTeacherId,
		long studentId, long sourceSessionId, long sourceSchoollifeSessionId,
		java.util.Date registrationDate) {

		return _renvoiLocalService.addRenvoi(
			schoollifeSessionId, teacherId, sourceTeacherId, studentId,
			sourceSessionId, sourceSchoollifeSessionId, registrationDate);
	}

	/**
	 * Adds the renvoi to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RenvoiLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param renvoi the renvoi
	 * @return the renvoi that was added
	 */
	@Override
	public com.weprode.nero.school.life.model.Renvoi addRenvoi(
		com.weprode.nero.school.life.model.Renvoi renvoi) {

		return _renvoiLocalService.addRenvoi(renvoi);
	}

	@Override
	public org.json.JSONObject convertRenvoiToJson(
		com.weprode.nero.school.life.model.Renvoi renvoi) {

		return _renvoiLocalService.convertRenvoiToJson(renvoi);
	}

	@Override
	public org.json.JSONObject convertSchoolRenvoi(
		com.weprode.nero.school.life.model.Renvoi schoolRenvoi) {

		return _renvoiLocalService.convertSchoolRenvoi(schoolRenvoi);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _renvoiLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new renvoi with the primary key. Does not add the renvoi to the database.
	 *
	 * @param renvoiPK the primary key for the new renvoi
	 * @return the new renvoi
	 */
	@Override
	public com.weprode.nero.school.life.model.Renvoi createRenvoi(
		com.weprode.nero.school.life.service.persistence.RenvoiPK renvoiPK) {

		return _renvoiLocalService.createRenvoi(renvoiPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _renvoiLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the renvoi from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RenvoiLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param renvoi the renvoi
	 * @return the renvoi that was removed
	 */
	@Override
	public com.weprode.nero.school.life.model.Renvoi deleteRenvoi(
		com.weprode.nero.school.life.model.Renvoi renvoi) {

		return _renvoiLocalService.deleteRenvoi(renvoi);
	}

	/**
	 * Deletes the renvoi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RenvoiLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param renvoiPK the primary key of the renvoi
	 * @return the renvoi that was removed
	 * @throws PortalException if a renvoi with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.school.life.model.Renvoi deleteRenvoi(
			com.weprode.nero.school.life.service.persistence.RenvoiPK renvoiPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _renvoiLocalService.deleteRenvoi(renvoiPK);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _renvoiLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _renvoiLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _renvoiLocalService.dynamicQuery();
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

		return _renvoiLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.school.life.model.impl.RenvoiModelImpl</code>.
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

		return _renvoiLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.school.life.model.impl.RenvoiModelImpl</code>.
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

		return _renvoiLocalService.dynamicQuery(
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

		return _renvoiLocalService.dynamicQueryCount(dynamicQuery);
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

		return _renvoiLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.school.life.model.Renvoi fetchRenvoi(
		com.weprode.nero.school.life.service.persistence.RenvoiPK renvoiPK) {

		return _renvoiLocalService.fetchRenvoi(renvoiPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _renvoiLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.weprode.nero.school.life.model.Renvoi>
		getDoyenSchoolRenvois(
			com.liferay.portal.kernel.model.User user, java.util.Date minDate,
			java.util.Date maxDate) {

		return _renvoiLocalService.getDoyenSchoolRenvois(
			user, minDate, maxDate);
	}

	@Override
	public java.util.List<com.weprode.nero.school.life.model.Renvoi>
		getGroupRenvois(
			com.liferay.portal.kernel.model.User user,
			java.util.List<Long> groupIds, java.util.Date minDate,
			java.util.Date maxDate) {

		return _renvoiLocalService.getGroupRenvois(
			user, groupIds, minDate, maxDate);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _renvoiLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _renvoiLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _renvoiLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the renvoi with the primary key.
	 *
	 * @param renvoiPK the primary key of the renvoi
	 * @return the renvoi
	 * @throws PortalException if a renvoi with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.school.life.model.Renvoi getRenvoi(
			com.weprode.nero.school.life.service.persistence.RenvoiPK renvoiPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _renvoiLocalService.getRenvoi(renvoiPK);
	}

	/**
	 * Returns a range of all the renvois.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.school.life.model.impl.RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @return the range of renvois
	 */
	@Override
	public java.util.List<com.weprode.nero.school.life.model.Renvoi> getRenvois(
		int start, int end) {

		return _renvoiLocalService.getRenvois(start, end);
	}

	/**
	 * Returns the number of renvois.
	 *
	 * @return the number of renvois
	 */
	@Override
	public int getRenvoisCount() {
		return _renvoiLocalService.getRenvoisCount();
	}

	@Override
	public java.util.List<com.weprode.nero.school.life.model.Renvoi>
		getSchoolRenvois(
			long schoolId, java.util.Date minDate, java.util.Date maxDate) {

		return _renvoiLocalService.getSchoolRenvois(schoolId, minDate, maxDate);
	}

	@Override
	public java.util.List<com.weprode.nero.school.life.model.Renvoi>
		getTeacherPendingRenvois(long teacherId) {

		return _renvoiLocalService.getTeacherPendingRenvois(teacherId);
	}

	@Override
	public boolean removeRenvoi(long schoollifeSessionId, long studentId) {
		return _renvoiLocalService.removeRenvoi(schoollifeSessionId, studentId);
	}

	@Override
	public boolean setReason(
		long schoollifeSessionId, long studentId, String reason) {

		return _renvoiLocalService.setReason(
			schoollifeSessionId, studentId, reason);
	}

	/**
	 * Updates the renvoi in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RenvoiLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param renvoi the renvoi
	 * @return the renvoi that was updated
	 */
	@Override
	public com.weprode.nero.school.life.model.Renvoi updateRenvoi(
		com.weprode.nero.school.life.model.Renvoi renvoi) {

		return _renvoiLocalService.updateRenvoi(renvoi);
	}

	@Override
	public RenvoiLocalService getWrappedService() {
		return _renvoiLocalService;
	}

	@Override
	public void setWrappedService(RenvoiLocalService renvoiLocalService) {
		_renvoiLocalService = renvoiLocalService;
	}

	private RenvoiLocalService _renvoiLocalService;

}