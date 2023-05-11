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

import com.weprode.nero.news.service.persistence.NewsPopulationPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class NewsPopulationSoap implements Serializable {

	public static NewsPopulationSoap toSoapModel(NewsPopulation model) {
		NewsPopulationSoap soapModel = new NewsPopulationSoap();

		soapModel.setNewsId(model.getNewsId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setRoleId(model.getRoleId());

		return soapModel;
	}

	public static NewsPopulationSoap[] toSoapModels(NewsPopulation[] models) {
		NewsPopulationSoap[] soapModels = new NewsPopulationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NewsPopulationSoap[][] toSoapModels(
		NewsPopulation[][] models) {

		NewsPopulationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new NewsPopulationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NewsPopulationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NewsPopulationSoap[] toSoapModels(
		List<NewsPopulation> models) {

		List<NewsPopulationSoap> soapModels = new ArrayList<NewsPopulationSoap>(
			models.size());

		for (NewsPopulation model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NewsPopulationSoap[soapModels.size()]);
	}

	public NewsPopulationSoap() {
	}

	public NewsPopulationPK getPrimaryKey() {
		return new NewsPopulationPK(_newsId, _groupId, _roleId);
	}

	public void setPrimaryKey(NewsPopulationPK pk) {
		setNewsId(pk.newsId);
		setGroupId(pk.groupId);
		setRoleId(pk.roleId);
	}

	public long getNewsId() {
		return _newsId;
	}

	public void setNewsId(long newsId) {
		_newsId = newsId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getRoleId() {
		return _roleId;
	}

	public void setRoleId(long roleId) {
		_roleId = roleId;
	}

	private long _newsId;
	private long _groupId;
	private long _roleId;

}