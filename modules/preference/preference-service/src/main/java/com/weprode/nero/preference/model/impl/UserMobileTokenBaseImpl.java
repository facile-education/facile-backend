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

package com.weprode.nero.preference.model.impl;

import com.weprode.nero.preference.model.UserMobileToken;
import com.weprode.nero.preference.service.UserMobileTokenLocalServiceUtil;

/**
 * The extended model base implementation for the UserMobileToken service. Represents a row in the &quot;Preference_UserMobileToken&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserMobileTokenImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserMobileTokenImpl
 * @see UserMobileToken
 * @generated
 */
public abstract class UserMobileTokenBaseImpl
	extends UserMobileTokenModelImpl implements UserMobileToken {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user mobile token model instance should use the <code>UserMobileToken</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			UserMobileTokenLocalServiceUtil.addUserMobileToken(this);
		}
		else {
			UserMobileTokenLocalServiceUtil.updateUserMobileToken(this);
		}
	}

}