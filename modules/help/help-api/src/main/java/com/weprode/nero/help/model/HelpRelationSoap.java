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

package com.weprode.nero.help.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.help.service.http.HelpRelationServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class HelpRelationSoap implements Serializable {

	public static HelpRelationSoap toSoapModel(HelpRelation model) {
		HelpRelationSoap soapModel = new HelpRelationSoap();

		soapModel.setRelationId(model.getRelationId());
		soapModel.setItemId(model.getItemId());
		soapModel.setRelatedItemId(model.getRelatedItemId());

		return soapModel;
	}

	public static HelpRelationSoap[] toSoapModels(HelpRelation[] models) {
		HelpRelationSoap[] soapModels = new HelpRelationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HelpRelationSoap[][] toSoapModels(HelpRelation[][] models) {
		HelpRelationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HelpRelationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HelpRelationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HelpRelationSoap[] toSoapModels(List<HelpRelation> models) {
		List<HelpRelationSoap> soapModels = new ArrayList<HelpRelationSoap>(
			models.size());

		for (HelpRelation model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HelpRelationSoap[soapModels.size()]);
	}

	public HelpRelationSoap() {
	}

	public long getPrimaryKey() {
		return _relationId;
	}

	public void setPrimaryKey(long pk) {
		setRelationId(pk);
	}

	public long getRelationId() {
		return _relationId;
	}

	public void setRelationId(long relationId) {
		_relationId = relationId;
	}

	public long getItemId() {
		return _itemId;
	}

	public void setItemId(long itemId) {
		_itemId = itemId;
	}

	public long getRelatedItemId() {
		return _relatedItemId;
	}

	public void setRelatedItemId(long relatedItemId) {
		_relatedItemId = relatedItemId;
	}

	private long _relationId;
	private long _itemId;
	private long _relatedItemId;

}