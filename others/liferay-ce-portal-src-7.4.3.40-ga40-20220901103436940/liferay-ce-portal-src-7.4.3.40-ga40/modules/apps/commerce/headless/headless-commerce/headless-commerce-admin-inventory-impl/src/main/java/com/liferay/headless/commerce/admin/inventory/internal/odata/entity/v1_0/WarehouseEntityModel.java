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

package com.liferay.headless.commerce.admin.inventory.internal.odata.entity.v1_0;

import com.liferay.portal.odata.entity.BooleanEntityField;
import com.liferay.portal.odata.entity.DoubleEntityField;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.odata.entity.StringEntityField;

import java.util.Map;

/**
 * @author Alessio Antonio Rendina
 */
public class WarehouseEntityModel implements EntityModel {

	public WarehouseEntityModel() {
		_entityFieldsMap = EntityModel.toEntityFieldsMap(
			new BooleanEntityField("active", locale -> "active"),
			new StringEntityField("city", locale -> "city"),
			new StringEntityField(
				"countryISOCode", locale -> "countryTwoLettersISOCode"),
			new DoubleEntityField("latitude", locale -> "latitude"),
			new DoubleEntityField("longitude", locale -> "longitude"),
			new StringEntityField("name", locale -> "name"),
			new StringEntityField("regionISOCode", locale -> "regionCode"),
			new StringEntityField("street1", locale -> "street1"));
	}

	@Override
	public Map<String, EntityField> getEntityFieldsMap() {
		return _entityFieldsMap;
	}

	private final Map<String, EntityField> _entityFieldsMap;

}