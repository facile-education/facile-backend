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

package com.weprode.nero.course.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.course.model.SessionContent;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the session content service. This utility wraps <code>com.weprode.nero.course.service.persistence.impl.SessionContentPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SessionContentPersistence
 * @generated
 */
public class SessionContentUtil {

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
	public static void clearCache(SessionContent sessionContent) {
		getPersistence().clearCache(sessionContent);
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
	public static Map<Serializable, SessionContent> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SessionContent> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SessionContent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SessionContent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SessionContent> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SessionContent update(SessionContent sessionContent) {
		return getPersistence().update(sessionContent);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SessionContent update(
		SessionContent sessionContent, ServiceContext serviceContext) {

		return getPersistence().update(sessionContent, serviceContext);
	}

	/**
	 * Caches the session content in the entity cache if it is enabled.
	 *
	 * @param sessionContent the session content
	 */
	public static void cacheResult(SessionContent sessionContent) {
		getPersistence().cacheResult(sessionContent);
	}

	/**
	 * Caches the session contents in the entity cache if it is enabled.
	 *
	 * @param sessionContents the session contents
	 */
	public static void cacheResult(List<SessionContent> sessionContents) {
		getPersistence().cacheResult(sessionContents);
	}

	/**
	 * Creates a new session content with the primary key. Does not add the session content to the database.
	 *
	 * @param sessionId the primary key for the new session content
	 * @return the new session content
	 */
	public static SessionContent create(long sessionId) {
		return getPersistence().create(sessionId);
	}

	/**
	 * Removes the session content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sessionId the primary key of the session content
	 * @return the session content that was removed
	 * @throws NoSuchSessionContentException if a session content with the primary key could not be found
	 */
	public static SessionContent remove(long sessionId)
		throws com.weprode.nero.course.exception.NoSuchSessionContentException {

		return getPersistence().remove(sessionId);
	}

	public static SessionContent updateImpl(SessionContent sessionContent) {
		return getPersistence().updateImpl(sessionContent);
	}

	/**
	 * Returns the session content with the primary key or throws a <code>NoSuchSessionContentException</code> if it could not be found.
	 *
	 * @param sessionId the primary key of the session content
	 * @return the session content
	 * @throws NoSuchSessionContentException if a session content with the primary key could not be found
	 */
	public static SessionContent findByPrimaryKey(long sessionId)
		throws com.weprode.nero.course.exception.NoSuchSessionContentException {

		return getPersistence().findByPrimaryKey(sessionId);
	}

	/**
	 * Returns the session content with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sessionId the primary key of the session content
	 * @return the session content, or <code>null</code> if a session content with the primary key could not be found
	 */
	public static SessionContent fetchByPrimaryKey(long sessionId) {
		return getPersistence().fetchByPrimaryKey(sessionId);
	}

	/**
	 * Returns all the session contents.
	 *
	 * @return the session contents
	 */
	public static List<SessionContent> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the session contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionContentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session contents
	 * @param end the upper bound of the range of session contents (not inclusive)
	 * @return the range of session contents
	 */
	public static List<SessionContent> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the session contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionContentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session contents
	 * @param end the upper bound of the range of session contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of session contents
	 */
	public static List<SessionContent> findAll(
		int start, int end,
		OrderByComparator<SessionContent> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the session contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionContentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session contents
	 * @param end the upper bound of the range of session contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of session contents
	 */
	public static List<SessionContent> findAll(
		int start, int end, OrderByComparator<SessionContent> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the session contents from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of session contents.
	 *
	 * @return the number of session contents
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SessionContentPersistence getPersistence() {
		return _persistence;
	}

	private static volatile SessionContentPersistence _persistence;

}