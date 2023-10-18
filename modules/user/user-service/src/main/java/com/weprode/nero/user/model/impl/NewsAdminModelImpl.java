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

package com.weprode.nero.user.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.nero.user.model.NewsAdmin;
import com.weprode.nero.user.model.NewsAdminModel;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the NewsAdmin service. Represents a row in the &quot;User_NewsAdmin&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>NewsAdminModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link NewsAdminImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsAdminImpl
 * @generated
 */
@JSON(strict = true)
public class NewsAdminModelImpl
	extends BaseModelImpl<NewsAdmin> implements NewsAdminModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a news admin model instance should use the <code>NewsAdmin</code> interface instead.
	 */
	public static final String TABLE_NAME = "User_NewsAdmin";

	public static final Object[][] TABLE_COLUMNS = {
		{"newsAdminId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"schoolId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("newsAdminId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("schoolId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table User_NewsAdmin (newsAdminId LONG not null primary key,userId LONG,schoolId LONG)";

	public static final String TABLE_SQL_DROP = "drop table User_NewsAdmin";

	public static final String ORDER_BY_JPQL =
		" ORDER BY newsAdmin.newsAdminId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY User_NewsAdmin.newsAdminId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SCHOOLID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long NEWSADMINID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	public NewsAdminModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _newsAdminId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setNewsAdminId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _newsAdminId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return NewsAdmin.class;
	}

	@Override
	public String getModelClassName() {
		return NewsAdmin.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<NewsAdmin, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<NewsAdmin, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<NewsAdmin, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((NewsAdmin)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<NewsAdmin, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<NewsAdmin, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(NewsAdmin)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<NewsAdmin, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<NewsAdmin, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<NewsAdmin, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<NewsAdmin, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<NewsAdmin, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<NewsAdmin, Object>>();
		Map<String, BiConsumer<NewsAdmin, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<NewsAdmin, ?>>();

		attributeGetterFunctions.put("newsAdminId", NewsAdmin::getNewsAdminId);
		attributeSetterBiConsumers.put(
			"newsAdminId",
			(BiConsumer<NewsAdmin, Long>)NewsAdmin::setNewsAdminId);
		attributeGetterFunctions.put("userId", NewsAdmin::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<NewsAdmin, Long>)NewsAdmin::setUserId);
		attributeGetterFunctions.put("schoolId", NewsAdmin::getSchoolId);
		attributeSetterBiConsumers.put(
			"schoolId", (BiConsumer<NewsAdmin, Long>)NewsAdmin::setSchoolId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getNewsAdminId() {
		return _newsAdminId;
	}

	@Override
	public void setNewsAdminId(long newsAdminId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_newsAdminId = newsAdminId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalUserId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("userId"));
	}

	@JSON
	@Override
	public long getSchoolId() {
		return _schoolId;
	}

	@Override
	public void setSchoolId(long schoolId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_schoolId = schoolId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalSchoolId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("schoolId"));
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (!Objects.equals(
					entry.getValue(), getColumnValue(entry.getKey()))) {

				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, NewsAdmin.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public NewsAdmin toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, NewsAdmin>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		NewsAdminImpl newsAdminImpl = new NewsAdminImpl();

		newsAdminImpl.setNewsAdminId(getNewsAdminId());
		newsAdminImpl.setUserId(getUserId());
		newsAdminImpl.setSchoolId(getSchoolId());

		newsAdminImpl.resetOriginalValues();

		return newsAdminImpl;
	}

	@Override
	public NewsAdmin cloneWithOriginalValues() {
		NewsAdminImpl newsAdminImpl = new NewsAdminImpl();

		newsAdminImpl.setNewsAdminId(
			this.<Long>getColumnOriginalValue("newsAdminId"));
		newsAdminImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		newsAdminImpl.setSchoolId(
			this.<Long>getColumnOriginalValue("schoolId"));

		return newsAdminImpl;
	}

	@Override
	public int compareTo(NewsAdmin newsAdmin) {
		long primaryKey = newsAdmin.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof NewsAdmin)) {
			return false;
		}

		NewsAdmin newsAdmin = (NewsAdmin)object;

		long primaryKey = newsAdmin.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<NewsAdmin> toCacheModel() {
		NewsAdminCacheModel newsAdminCacheModel = new NewsAdminCacheModel();

		newsAdminCacheModel.newsAdminId = getNewsAdminId();

		newsAdminCacheModel.userId = getUserId();

		newsAdminCacheModel.schoolId = getSchoolId();

		return newsAdminCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<NewsAdmin, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<NewsAdmin, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<NewsAdmin, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((NewsAdmin)this);

			if (value == null) {
				sb.append("null");
			}
			else if (value instanceof Blob || value instanceof Date ||
					 value instanceof Map || value instanceof String) {

				sb.append(
					"\"" + StringUtil.replace(value.toString(), "\"", "'") +
						"\"");
			}
			else {
				sb.append(value);
			}

			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, NewsAdmin>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					NewsAdmin.class, ModelWrapper.class);

	}

	private long _newsAdminId;
	private long _userId;
	private long _schoolId;

	public <T> T getColumnValue(String columnName) {
		Function<NewsAdmin, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((NewsAdmin)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("newsAdminId", _newsAdminId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("schoolId", _schoolId);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("newsAdminId", 1L);

		columnBitmasks.put("userId", 2L);

		columnBitmasks.put("schoolId", 4L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private NewsAdmin _escapedModel;

}