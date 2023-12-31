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

package com.weprode.facile.statistic.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link GeneralStatLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see GeneralStatLocalService
 * @generated
 */
public class GeneralStatLocalServiceWrapper
	implements GeneralStatLocalService,
			   ServiceWrapper<GeneralStatLocalService> {

	public GeneralStatLocalServiceWrapper() {
		this(null);
	}

	public GeneralStatLocalServiceWrapper(
		GeneralStatLocalService generalStatLocalService) {

		_generalStatLocalService = generalStatLocalService;
	}

	@Override
	public int countActiveUsers(
		java.util.Date startDate, java.util.Date endDate, long schoolId) {

		return _generalStatLocalService.countActiveUsers(
			startDate, endDate, schoolId);
	}

	@Override
	public java.util.Map<String, Integer> countFiles(
		java.util.Date startDate, java.util.Date endDate, long schoolId) {

		return _generalStatLocalService.countFiles(
			startDate, endDate, schoolId);
	}

	@Override
	public java.util.Map<Integer, Integer> countHomeworks(
		java.util.Date startDate, java.util.Date endDate, long schoolId) {

		return _generalStatLocalService.countHomeworks(
			startDate, endDate, schoolId);
	}

	@Override
	public int countMessages(
		java.util.Date startDate, java.util.Date endDate, long schoolId) {

		return _generalStatLocalService.countMessages(
			startDate, endDate, schoolId);
	}

	@Override
	public int countNews(
		java.util.Date startDate, java.util.Date endDate, long schoolId,
		boolean isSchoolNewsType) {

		return _generalStatLocalService.countNews(
			startDate, endDate, schoolId, isSchoolNewsType);
	}

	@Override
	public java.util.Map<Integer, Integer> countSchoolLifeStudents(
		java.util.Date startDate, java.util.Date endDate, long schoolId) {

		return _generalStatLocalService.countSchoolLifeStudents(
			startDate, endDate, schoolId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _generalStatLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public GeneralStatLocalService getWrappedService() {
		return _generalStatLocalService;
	}

	@Override
	public void setWrappedService(
		GeneralStatLocalService generalStatLocalService) {

		_generalStatLocalService = generalStatLocalService;
	}

	private GeneralStatLocalService _generalStatLocalService;

}