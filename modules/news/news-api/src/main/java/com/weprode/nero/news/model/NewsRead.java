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

package com.weprode.nero.news.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the NewsRead service. Represents a row in the &quot;News_NewsRead&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see NewsReadModel
 * @generated
 */
@ImplementationClassName("com.weprode.nero.news.model.impl.NewsReadImpl")
@ProviderType
public interface NewsRead extends NewsReadModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.weprode.nero.news.model.impl.NewsReadImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<NewsRead, Long> NEWS_ID_ACCESSOR =
		new Accessor<NewsRead, Long>() {

			@Override
			public Long get(NewsRead newsRead) {
				return newsRead.getNewsId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<NewsRead> getTypeClass() {
				return NewsRead.class;
			}

		};
	public static final Accessor<NewsRead, Long> USER_ID_ACCESSOR =
		new Accessor<NewsRead, Long>() {

			@Override
			public Long get(NewsRead newsRead) {
				return newsRead.getUserId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<NewsRead> getTypeClass() {
				return NewsRead.class;
			}

		};

}