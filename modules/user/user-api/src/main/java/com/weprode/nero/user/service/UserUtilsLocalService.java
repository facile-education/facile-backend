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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for UserUtils. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see UserUtilsLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface UserUtilsLocalService extends BaseLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.nero.user.service.impl.UserUtilsLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the user utils local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link UserUtilsLocalServiceUtil} if injection and service tracking are not available.
	 */
	public JSONArray convertUsersToJson(List<User> userList);

	public JSONObject convertUserToJson(User user);

	public String generateLogin(String lastName, String firstName);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getStudentMainTeachers(User student);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getUserConseillersSociaux(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getUserDoyens(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Long> getUserGroupIds(long userId);

	/**
	 * This method a list a user based on a list of userId
	 *
	 * @return List<User> the users object , empty array in case of error
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getUserListFromUserIdList(List<Long> userIds);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String getUserPicture(User user) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getUserPsychologues(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getUserTeachers(User user);

	/**
	 * Purges all expired users
	 */
	public boolean purgeExpiredUsers();

	public String updateUserPassword(
		User user, String newPassword, String confirmPassword,
		boolean resetPassword);

}