/**
 * Copyright (c) 2022-present - Weprode
 * This file is part of FACILE ENT Project.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License (LGPL) as
 * published by the Free Software Foundation (version 2.1 of the License).
 * See LICENCE.txt file
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */

package com.weprode.facile.authentication.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.PasswordPolicyLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.authentication.model.LoginLock;
import com.weprode.facile.authentication.service.base.LoginLockLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Cédric Lecarpentier
 */
@Component(
	property = "model.class.name=com.weprode.facile.authentication.model.LoginLock",
	service = AopService.class
)
public class LoginLockLocalServiceImpl extends LoginLockLocalServiceBaseImpl {

	public void addLoginAttempt(String login) {

		LoginLock loginLock = null;
		try {
			loginLock = loginLockPersistence.fetchByPrimaryKey(login);
		} catch (Exception e) {
			// No login lock exists for this login
		}
		if (loginLock == null) {
			loginLock = loginLockPersistence.create(login);
			loginLock.setFailedLoginAttempts(0);
		}
		try {
			loginLock.setFailedLoginAttempts(loginLock.getFailedLoginAttempts() + 1);
			if (loginLock.getFailedLoginAttempts() >= PasswordPolicyLocalServiceUtil.getDefaultPasswordPolicy(PortalUtil.getDefaultCompanyId()).getMaxFailure()) {
				loginLock.setIsLocked(true);
				long lockoutDuration = PasswordPolicyLocalServiceUtil.getDefaultPasswordPolicy(PortalUtil.getDefaultCompanyId()).getLockoutDuration();
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				cal.add(Calendar.SECOND, (int)lockoutDuration);
				loginLock.setLockEndDate(cal.getTime());
			}
			loginLockPersistence.update(loginLock);
		} catch (Exception e) {
			logger.error("Error while adding non-existing login attempt " + login, e);
		}
		logger.info("Added attempt for false login " + login);
	}

	final Log logger = LogFactoryUtil.getLog(LoginLockLocalServiceImpl.class);

}