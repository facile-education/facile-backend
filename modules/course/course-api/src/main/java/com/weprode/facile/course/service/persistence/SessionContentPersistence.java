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

package com.weprode.facile.course.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.course.exception.NoSuchSessionContentException;
import com.weprode.facile.course.model.SessionContent;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the session content service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SessionContentUtil
 * @generated
 */
@ProviderType
public interface SessionContentPersistence
	extends BasePersistence<SessionContent> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SessionContentUtil} to access the session content persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the session content in the entity cache if it is enabled.
	 *
	 * @param sessionContent the session content
	 */
	public void cacheResult(SessionContent sessionContent);

	/**
	 * Caches the session contents in the entity cache if it is enabled.
	 *
	 * @param sessionContents the session contents
	 */
	public void cacheResult(java.util.List<SessionContent> sessionContents);

	/**
	 * Creates a new session content with the primary key. Does not add the session content to the database.
	 *
	 * @param sessionId the primary key for the new session content
	 * @return the new session content
	 */
	public SessionContent create(long sessionId);

	/**
	 * Removes the session content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sessionId the primary key of the session content
	 * @return the session content that was removed
	 * @throws NoSuchSessionContentException if a session content with the primary key could not be found
	 */
	public SessionContent remove(long sessionId)
		throws NoSuchSessionContentException;

	public SessionContent updateImpl(SessionContent sessionContent);

	/**
	 * Returns the session content with the primary key or throws a <code>NoSuchSessionContentException</code> if it could not be found.
	 *
	 * @param sessionId the primary key of the session content
	 * @return the session content
	 * @throws NoSuchSessionContentException if a session content with the primary key could not be found
	 */
	public SessionContent findByPrimaryKey(long sessionId)
		throws NoSuchSessionContentException;

	/**
	 * Returns the session content with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sessionId the primary key of the session content
	 * @return the session content, or <code>null</code> if a session content with the primary key could not be found
	 */
	public SessionContent fetchByPrimaryKey(long sessionId);

	/**
	 * Returns all the session contents.
	 *
	 * @return the session contents
	 */
	public java.util.List<SessionContent> findAll();

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
	public java.util.List<SessionContent> findAll(int start, int end);

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
	public java.util.List<SessionContent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionContent>
			orderByComparator);

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
	public java.util.List<SessionContent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionContent>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the session contents from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of session contents.
	 *
	 * @return the number of session contents
	 */
	public int countAll();

}