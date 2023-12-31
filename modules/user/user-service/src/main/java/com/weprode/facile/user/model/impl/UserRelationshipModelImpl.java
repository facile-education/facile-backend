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

package com.weprode.facile.user.model.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.facile.user.model.UserRelationship;
import com.weprode.facile.user.model.UserRelationshipModel;
import com.weprode.facile.user.service.persistence.UserRelationshipPK;

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
 * The base model implementation for the UserRelationship service. Represents a row in the &quot;User_UserRelationship&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>UserRelationshipModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserRelationshipImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserRelationshipImpl
 * @generated
 */
public class UserRelationshipModelImpl
	extends BaseModelImpl<UserRelationship> implements UserRelationshipModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user relationship model instance should use the <code>UserRelationship</code> interface instead.
	 */
	public static final String TABLE_NAME = "User_UserRelationship";

	public static final Object[][] TABLE_COLUMNS = {
		{"childUserId", Types.BIGINT}, {"parentUserId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("childUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("parentUserId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table User_UserRelationship (childUserId LONG not null,parentUserId LONG not null,primary key (childUserId, parentUserId))";

	public static final String TABLE_SQL_DROP =
		"drop table User_UserRelationship";

	public static final String ORDER_BY_JPQL =
		" ORDER BY userRelationship.id.childUserId ASC, userRelationship.id.parentUserId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY User_UserRelationship.childUserId ASC, User_UserRelationship.parentUserId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CHILDUSERID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PARENTUSERID_COLUMN_BITMASK = 2L;

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

	public UserRelationshipModelImpl() {
	}

	@Override
	public UserRelationshipPK getPrimaryKey() {
		return new UserRelationshipPK(_childUserId, _parentUserId);
	}

	@Override
	public void setPrimaryKey(UserRelationshipPK primaryKey) {
		setChildUserId(primaryKey.childUserId);
		setParentUserId(primaryKey.parentUserId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new UserRelationshipPK(_childUserId, _parentUserId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((UserRelationshipPK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return UserRelationship.class;
	}

	@Override
	public String getModelClassName() {
		return UserRelationship.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<UserRelationship, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<UserRelationship, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserRelationship, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((UserRelationship)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<UserRelationship, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<UserRelationship, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(UserRelationship)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<UserRelationship, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<UserRelationship, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<UserRelationship, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<UserRelationship, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<UserRelationship, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<UserRelationship, Object>>();
		Map<String, BiConsumer<UserRelationship, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<UserRelationship, ?>>();

		attributeGetterFunctions.put(
			"childUserId", UserRelationship::getChildUserId);
		attributeSetterBiConsumers.put(
			"childUserId",
			(BiConsumer<UserRelationship, Long>)
				UserRelationship::setChildUserId);
		attributeGetterFunctions.put(
			"parentUserId", UserRelationship::getParentUserId);
		attributeSetterBiConsumers.put(
			"parentUserId",
			(BiConsumer<UserRelationship, Long>)
				UserRelationship::setParentUserId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getChildUserId() {
		return _childUserId;
	}

	@Override
	public void setChildUserId(long childUserId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_childUserId = childUserId;
	}

	@Override
	public String getChildUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getChildUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setChildUserUuid(String childUserUuid) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalChildUserId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("childUserId"));
	}

	@Override
	public long getParentUserId() {
		return _parentUserId;
	}

	@Override
	public void setParentUserId(long parentUserId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_parentUserId = parentUserId;
	}

	@Override
	public String getParentUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getParentUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setParentUserUuid(String parentUserUuid) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalParentUserId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("parentUserId"));
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
	public UserRelationship toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, UserRelationship>
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
		UserRelationshipImpl userRelationshipImpl = new UserRelationshipImpl();

		userRelationshipImpl.setChildUserId(getChildUserId());
		userRelationshipImpl.setParentUserId(getParentUserId());

		userRelationshipImpl.resetOriginalValues();

		return userRelationshipImpl;
	}

	@Override
	public UserRelationship cloneWithOriginalValues() {
		UserRelationshipImpl userRelationshipImpl = new UserRelationshipImpl();

		userRelationshipImpl.setChildUserId(
			this.<Long>getColumnOriginalValue("childUserId"));
		userRelationshipImpl.setParentUserId(
			this.<Long>getColumnOriginalValue("parentUserId"));

		return userRelationshipImpl;
	}

	@Override
	public int compareTo(UserRelationship userRelationship) {
		UserRelationshipPK primaryKey = userRelationship.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof UserRelationship)) {
			return false;
		}

		UserRelationship userRelationship = (UserRelationship)object;

		UserRelationshipPK primaryKey = userRelationship.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
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
	public CacheModel<UserRelationship> toCacheModel() {
		UserRelationshipCacheModel userRelationshipCacheModel =
			new UserRelationshipCacheModel();

		userRelationshipCacheModel.userRelationshipPK = getPrimaryKey();

		userRelationshipCacheModel.childUserId = getChildUserId();

		userRelationshipCacheModel.parentUserId = getParentUserId();

		return userRelationshipCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<UserRelationship, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<UserRelationship, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserRelationship, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(UserRelationship)this);

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

		private static final Function<InvocationHandler, UserRelationship>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					UserRelationship.class, ModelWrapper.class);

	}

	private long _childUserId;
	private long _parentUserId;

	public <T> T getColumnValue(String columnName) {
		Function<UserRelationship, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((UserRelationship)this);
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

		_columnOriginalValues.put("childUserId", _childUserId);
		_columnOriginalValues.put("parentUserId", _parentUserId);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("childUserId", 1L);

		columnBitmasks.put("parentUserId", 2L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private UserRelationship _escapedModel;

}