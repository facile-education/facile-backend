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

package com.weprode.nero.document.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.document.model.Version;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for Version. This utility wraps
 * <code>com.weprode.nero.document.service.impl.VersionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see VersionLocalService
 * @generated
 */
public class VersionLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.document.service.impl.VersionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static Version addVersion() throws SystemException {
		return getService().addVersion();
	}

	public static Version addVersion(
			long dlFileEntryId, double versionNb, String comment,
			long downloadCount, long viewCount)
		throws SystemException {

		return getService().addVersion(
			dlFileEntryId, versionNb, comment, downloadCount, viewCount);
	}

	public static Version addVersion(
			long dlFileEntryId, String versionNb, String comment,
			long downloadCount, long viewCount)
		throws SystemException {

		return getService().addVersion(
			dlFileEntryId, versionNb, comment, downloadCount, viewCount);
	}

	/**
	 * Adds the version to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param version the version
	 * @return the version that was added
	 */
	public static Version addVersion(Version version) {
		return getService().addVersion(version);
	}

	public static boolean createMajorVersion(
		com.liferay.portal.kernel.model.User user, long fileEntryId) {

		return getService().createMajorVersion(user, fileEntryId);
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
	 * Creates a new version with the primary key. Does not add the version to the database.
	 *
	 * @param fileVersionId the primary key for the new version
	 * @return the new version
	 */
	public static Version createVersion(long fileVersionId) {
		return getService().createVersion(fileVersionId);
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
	 * Deletes the version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fileVersionId the primary key of the version
	 * @return the version that was removed
	 * @throws PortalException if a version with the primary key could not be found
	 */
	public static Version deleteVersion(long fileVersionId)
		throws PortalException {

		return getService().deleteVersion(fileVersionId);
	}

	public static boolean deleteVersion(
		com.liferay.portal.kernel.model.User user, long fileEntryId,
		String versionNb) {

		return getService().deleteVersion(user, fileEntryId, versionNb);
	}

	/**
	 * Deletes the version from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param version the version
	 * @return the version that was removed
	 */
	public static Version deleteVersion(Version version) {
		return getService().deleteVersion(version);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.document.model.impl.VersionModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.document.model.impl.VersionModelImpl</code>.
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

	public static Version fetchVersion(long fileVersionId) {
		return getService().fetchVersion(fileVersionId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static org.json.JSONObject getFileVersions(
		com.liferay.portal.kernel.model.User user, long fileId) {

		return getService().getFileVersions(user, fileId);
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
	 * Returns the version with the primary key.
	 *
	 * @param fileVersionId the primary key of the version
	 * @return the version
	 * @throws PortalException if a version with the primary key could not be found
	 */
	public static Version getVersion(long fileVersionId)
		throws PortalException {

		return getService().getVersion(fileVersionId);
	}

	public static List<Version> getVersionByFileEntryId(long dlFileEntryId)
		throws SystemException {

		return getService().getVersionByFileEntryId(dlFileEntryId);
	}

	public static Version getVersionByFileEntryId(
			long dlFileEntryId, double versionNb)
		throws PortalException, SystemException {

		return getService().getVersionByFileEntryId(dlFileEntryId, versionNb);
	}

	public static Version getVersionByFileEntryId(
			long dlFileEntryId, String versionNb)
		throws PortalException, SystemException {

		return getService().getVersionByFileEntryId(dlFileEntryId, versionNb);
	}

	/**
	 * Returns a range of all the versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.document.model.impl.VersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of versions
	 * @param end the upper bound of the range of versions (not inclusive)
	 * @return the range of versions
	 */
	public static List<Version> getVersions(int start, int end) {
		return getService().getVersions(start, end);
	}

	/**
	 * Returns the number of versions.
	 *
	 * @return the number of versions
	 */
	public static int getVersionsCount() {
		return getService().getVersionsCount();
	}

	public static void incrementDownloadCount(Version fileVersion)
		throws PortalException, SystemException {

		getService().incrementDownloadCount(fileVersion);
	}

	public static void incrementViewCount(Version fileVersion)
		throws PortalException, SystemException {

		getService().incrementViewCount(fileVersion);
	}

	public static void removeVersionByFileEntryId(long dlFileEntryId)
		throws SystemException {

		getService().removeVersionByFileEntryId(dlFileEntryId);
	}

	public static void removeVersionByFileEntryId(
			long dlFileEntryId, double versionNb)
		throws PortalException, SystemException {

		getService().removeVersionByFileEntryId(dlFileEntryId, versionNb);
	}

	public static void removeVersionByFileEntryId(
			long dlFileEntryId, String versionNb)
		throws PortalException, SystemException {

		getService().removeVersionByFileEntryId(dlFileEntryId, versionNb);
	}

	public static boolean restoreVersion(long fileVersionId) {
		return getService().restoreVersion(fileVersionId);
	}

	public static boolean saveVersionDescription(
		long fileVersionId, String description) {

		return getService().saveVersionDescription(fileVersionId, description);
	}

	public static Version update(
			long dlFileEntryId, double versionNb, String comment,
			long downloadCount, long viewCount)
		throws PortalException, SystemException {

		return getService().update(
			dlFileEntryId, versionNb, comment, downloadCount, viewCount);
	}

	public static Version update(
			long dlFileEntryId, String versionNb, String comment,
			long downloadCount, long viewCount)
		throws PortalException, SystemException {

		return getService().update(
			dlFileEntryId, versionNb, comment, downloadCount, viewCount);
	}

	/**
	 * Updates the version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param version the version
	 * @return the version that was updated
	 */
	public static Version updateVersion(Version version) {
		return getService().updateVersion(version);
	}

	public static VersionLocalService getService() {
		return _service;
	}

	private static volatile VersionLocalService _service;

}