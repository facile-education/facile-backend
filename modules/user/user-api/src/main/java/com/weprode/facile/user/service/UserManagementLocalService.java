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

package com.weprode.facile.user.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for UserManagement. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see UserManagementLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface UserManagementLocalService extends BaseLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.facile.user.service.impl.UserManagementLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the user management local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link UserManagementLocalServiceUtil} if injection and service tracking are not available.
	 */
	public User createManualUser(
		String lastName, String firstName, String email, Date birthday,
		long roleId, long schoolId);

	public User createManualUser(
		String lastName, String firstName, String email, Date birthday,
		long roleId, long schoolId, boolean sendUserLogin);

	public User createManualUser(
		String lastName, String firstName, String email, Date birthday,
		long roleId, long schoolId, boolean sendUserLogin, String password);

	public User createUser(
		long companyId, String lastName, String firstName, String email,
		boolean isMale, Date birthday, boolean sendUserLogin, String password);

	public User createUser(
		long companyId, String lastName, String firstName, String email,
		Date birthday);

	public User createUser(
		long companyId, String lastName, String firstName, String email,
		Date birthday, boolean sendUserLogin);

	public String generatePassword();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	public void synchronizeUserSchool(long userId, long schoolId);

}