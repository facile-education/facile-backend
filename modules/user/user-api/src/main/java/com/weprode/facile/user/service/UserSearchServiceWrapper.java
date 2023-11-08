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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserSearchService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserSearchService
 * @generated
 */
public class UserSearchServiceWrapper
	implements ServiceWrapper<UserSearchService>, UserSearchService {

	public UserSearchServiceWrapper() {
		this(null);
	}

	public UserSearchServiceWrapper(UserSearchService userSearchService) {
		_userSearchService = userSearchService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userSearchService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getSchoollifeAgents(
		String search, long schoolId) {

		return _userSearchService.getSchoollifeAgents(search, schoolId);
	}

	@Override
	public org.json.JSONObject getSchoolMembers(long schoolId, String search) {
		return _userSearchService.getSchoolMembers(schoolId, search);
	}

	@Override
	public org.json.JSONObject getSchoolStudents(String search, long schoolId) {
		return _userSearchService.getSchoolStudents(search, schoolId);
	}

	@Override
	public org.json.JSONObject getSchoolStudentTeacherList(
		long schoolId, String search) {

		return _userSearchService.getSchoolStudentTeacherList(schoolId, search);
	}

	@Override
	public org.json.JSONObject getSchoolTeachers(long schoolId, String search) {
		return _userSearchService.getSchoolTeachers(schoolId, search);
	}

	@Override
	public UserSearchService getWrappedService() {
		return _userSearchService;
	}

	@Override
	public void setWrappedService(UserSearchService userSearchService) {
		_userSearchService = userSearchService;
	}

	private UserSearchService _userSearchService;

}