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

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.school.life.model.Renvoi;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for Renvoi. This utility wraps
 * <code>com.weprode.nero.school.life.service.impl.RenvoiLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see RenvoiLocalService
 * @generated
 */
public class RenvoiLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.school.life.service.impl.RenvoiLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static Renvoi addRenvoi(
		long schoollifeSessionId, long teacherId, long sourceTeacherId,
		long studentId, long sourceSessionId, long sourceSchoollifeSessionId,
		java.util.Date registrationDate) {

		return getService().addRenvoi(
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
	public static Renvoi addRenvoi(Renvoi renvoi) {
		return getService().addRenvoi(renvoi);
	}

	public static org.json.JSONObject convertRenvoiToJson(Renvoi renvoi) {
		return getService().convertRenvoiToJson(renvoi);
	}

	public static org.json.JSONObject convertSchoolRenvoi(Renvoi schoolRenvoi) {
		return getService().convertSchoolRenvoi(schoolRenvoi);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new renvoi with the primary key. Does not add the renvoi to the database.
	 *
	 * @param renvoiPK the primary key for the new renvoi
	 * @return the new renvoi
	 */
	public static Renvoi createRenvoi(
		com.weprode.nero.school.life.service.persistence.RenvoiPK renvoiPK) {

		return getService().createRenvoi(renvoiPK);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
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
	public static Renvoi deleteRenvoi(Renvoi renvoi) {
		return getService().deleteRenvoi(renvoi);
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
	public static Renvoi deleteRenvoi(
			com.weprode.nero.school.life.service.persistence.RenvoiPK renvoiPK)
		throws PortalException {

		return getService().deleteRenvoi(renvoiPK);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static Renvoi fetchRenvoi(
		com.weprode.nero.school.life.service.persistence.RenvoiPK renvoiPK) {

		return getService().fetchRenvoi(renvoiPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<Renvoi> getDoyenSchoolRenvois(
		com.liferay.portal.kernel.model.User user, java.util.Date minDate,
		java.util.Date maxDate) {

		return getService().getDoyenSchoolRenvois(user, minDate, maxDate);
	}

	public static List<Renvoi> getGroupRenvois(
		com.liferay.portal.kernel.model.User user, List<Long> groupIds,
		java.util.Date minDate, java.util.Date maxDate) {

		return getService().getGroupRenvois(user, groupIds, minDate, maxDate);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the renvoi with the primary key.
	 *
	 * @param renvoiPK the primary key of the renvoi
	 * @return the renvoi
	 * @throws PortalException if a renvoi with the primary key could not be found
	 */
	public static Renvoi getRenvoi(
			com.weprode.nero.school.life.service.persistence.RenvoiPK renvoiPK)
		throws PortalException {

		return getService().getRenvoi(renvoiPK);
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
	public static List<Renvoi> getRenvois(int start, int end) {
		return getService().getRenvois(start, end);
	}

	/**
	 * Returns the number of renvois.
	 *
	 * @return the number of renvois
	 */
	public static int getRenvoisCount() {
		return getService().getRenvoisCount();
	}

	public static List<Renvoi> getSchoolRenvois(
		long schoolId, java.util.Date minDate, java.util.Date maxDate) {

		return getService().getSchoolRenvois(schoolId, minDate, maxDate);
	}

	public static List<Renvoi> getTeacherPendingRenvois(long teacherId) {
		return getService().getTeacherPendingRenvois(teacherId);
	}

	public static boolean removeRenvoi(
		long schoollifeSessionId, long studentId) {

		return getService().removeRenvoi(schoollifeSessionId, studentId);
	}

	public static boolean setReason(
		long schoollifeSessionId, long studentId, String reason) {

		return getService().setReason(schoollifeSessionId, studentId, reason);
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
	public static Renvoi updateRenvoi(Renvoi renvoi) {
		return getService().updateRenvoi(renvoi);
	}

	public static RenvoiLocalService getService() {
		return _service;
	}

	private static volatile RenvoiLocalService _service;

}