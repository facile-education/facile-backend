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

package com.weprode.facile.organization.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for UserOrgs. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Marc Salvat
 * @see UserOrgsLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface UserOrgsLocalService extends BaseLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.facile.organization.service.impl.UserOrgsLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the user orgs local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link UserOrgsLocalServiceUtil} if injection and service tracking are not available.
	 */
	public boolean affectManuallyUserToOrg(long userId, Organization org);

	public Long countOrgMembers(long orgId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Organization> getAffectedClasses(User user, long roleId);

	/**
	 * Returns the list of user's schools including school complex
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Organization> getAllUserSchoolsIncludingSchoolComplex(
		User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Organization getEtabRatachement(User user);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Organization> getRoleAffectedClasses(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getSchoolTeachers(long schoolId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String getStudentClassName(User student);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String getStudentVolee(User student);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Organization> getUserClasses(User user, boolean withArchive);

	/**
	 * Returns all classes and groups the user is member of
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Organization> getUserClasses(
		User user, boolean withArchive, long schoolId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Organization> getUserClassesAndCours(
		User user, boolean withArchive);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Organization> getUserClassesAndCours(
		User user, boolean withArchive, long schoolId);

	/**
	 * Returns all classes and groups the user is member of, for a given school
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Organization> getUserCours(
		User user, boolean withArchive, long schoolId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Organization> getUserOrganizations(
		long userId, List<Integer> types, Boolean withArchives, long schoolId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Organization> getUserSchools(User user);

	/**
	 * Returns all subject organizations the user is member of
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Organization> getUserSubjectOrganizations(
		User user, long schoolId, boolean withArchive);

	/**
	 * Return all schools that can see a user (In admin services for example)
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Organization> getUserVisibilitySchools(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Organization> getUserVolees(
		User user, boolean withArchive, long schoolId);

}