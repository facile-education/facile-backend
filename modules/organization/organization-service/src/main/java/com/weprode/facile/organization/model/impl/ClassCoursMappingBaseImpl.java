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

package com.weprode.facile.organization.model.impl;

import com.weprode.facile.organization.model.ClassCoursMapping;
import com.weprode.facile.organization.service.ClassCoursMappingLocalServiceUtil;

/**
 * The extended model base implementation for the ClassCoursMapping service. Represents a row in the &quot;Organization_ClassCoursMapping&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ClassCoursMappingImpl}.
 * </p>
 *
 * @author Marc Salvat
 * @see ClassCoursMappingImpl
 * @see ClassCoursMapping
 * @generated
 */
public abstract class ClassCoursMappingBaseImpl
	extends ClassCoursMappingModelImpl implements ClassCoursMapping {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a class cours mapping model instance should use the <code>ClassCoursMapping</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			ClassCoursMappingLocalServiceUtil.addClassCoursMapping(this);
		}
		else {
			ClassCoursMappingLocalServiceUtil.updateClassCoursMapping(this);
		}
	}

}