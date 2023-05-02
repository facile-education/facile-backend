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
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.help.service.http.HelpLinkServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class HelpLinkSoap implements Serializable {

	public static HelpLinkSoap toSoapModel(HelpLink model) {
		HelpLinkSoap soapModel = new HelpLinkSoap();

		soapModel.setLinkId(model.getLinkId());
		soapModel.setItemId(model.getItemId());
		soapModel.setLinkName(model.getLinkName());
		soapModel.setLinkUrl(model.getLinkUrl());

		return soapModel;
	}

	public static HelpLinkSoap[] toSoapModels(HelpLink[] models) {
		HelpLinkSoap[] soapModels = new HelpLinkSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HelpLinkSoap[][] toSoapModels(HelpLink[][] models) {
		HelpLinkSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HelpLinkSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HelpLinkSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HelpLinkSoap[] toSoapModels(List<HelpLink> models) {
		List<HelpLinkSoap> soapModels = new ArrayList<HelpLinkSoap>(
			models.size());

		for (HelpLink model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HelpLinkSoap[soapModels.size()]);
	}

	public HelpLinkSoap() {
	}

	public long getPrimaryKey() {
		return _linkId;
	}

	public void setPrimaryKey(long pk) {
		setLinkId(pk);
	}

	public long getLinkId() {
		return _linkId;
	}

	public void setLinkId(long linkId) {
		_linkId = linkId;
	}

	public long getItemId() {
		return _itemId;
	}

	public void setItemId(long itemId) {
		_itemId = itemId;
	}

	public String getLinkName() {
		return _linkName;
	}

	public void setLinkName(String linkName) {
		_linkName = linkName;
	}

	public String getLinkUrl() {
		return _linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		_linkUrl = linkUrl;
	}

	private long _linkId;
	private long _itemId;
	private String _linkName;
	private String _linkUrl;

}