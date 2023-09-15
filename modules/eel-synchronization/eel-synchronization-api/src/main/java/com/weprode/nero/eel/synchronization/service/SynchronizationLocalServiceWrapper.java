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

package com.weprode.nero.eel.synchronization.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SynchronizationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SynchronizationLocalService
 * @generated
 */
public class SynchronizationLocalServiceWrapper
	implements ServiceWrapper<SynchronizationLocalService>,
			   SynchronizationLocalService {

	public SynchronizationLocalServiceWrapper(
		SynchronizationLocalService synchronizationLocalService) {

		_synchronizationLocalService = synchronizationLocalService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _synchronizationLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public void runGVEParentSynchronization(boolean sendReport) {
		_synchronizationLocalService.runGVEParentSynchronization(sendReport);
	}

	@Override
	public void runGVESynchronization(boolean sendReport) {
		_synchronizationLocalService.runGVESynchronization(sendReport);
	}

	@Override
	public SynchronizationLocalService getWrappedService() {
		return _synchronizationLocalService;
	}

	@Override
	public void setWrappedService(
		SynchronizationLocalService synchronizationLocalService) {

		_synchronizationLocalService = synchronizationLocalService;
	}

	private SynchronizationLocalService _synchronizationLocalService;

}