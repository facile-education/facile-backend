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

package com.weprode.facile.preference.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the NotifyConfig service. Represents a row in the &quot;Preference_NotifyConfig&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see NotifyConfigModel
 * @generated
 */
@ImplementationClassName(
	"com.weprode.facile.preference.model.impl.NotifyConfigImpl"
)
@ProviderType
public interface NotifyConfig extends NotifyConfigModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.weprode.facile.preference.model.impl.NotifyConfigImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<NotifyConfig, Long> NOTIFY_CONFIG_ID_ACCESSOR =
		new Accessor<NotifyConfig, Long>() {

			@Override
			public Long get(NotifyConfig notifyConfig) {
				return notifyConfig.getNotifyConfigId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<NotifyConfig> getTypeClass() {
				return NotifyConfig.class;
			}

		};

}