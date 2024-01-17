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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.facile.user.model.UserContact;
import com.weprode.facile.user.model.UserContactModel;

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
 * The base model implementation for the UserContact service. Represents a row in the &quot;User_UserContact&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>UserContactModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserContactImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserContactImpl
 * @generated
 */
public class UserContactModelImpl
	extends BaseModelImpl<UserContact> implements UserContactModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user contact model instance should use the <code>UserContact</code> interface instead.
	 */
	public static final String TABLE_NAME = "User_UserContact";

	public static final Object[][] TABLE_COLUMNS = {
		{"userId", Types.BIGINT}, {"address", Types.VARCHAR},
		{"mobilePhone", Types.VARCHAR}, {"homePhone", Types.VARCHAR},
		{"proPhone", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("address", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("mobilePhone", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("homePhone", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("proPhone", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table User_UserContact (userId LONG not null primary key,address VARCHAR(75) null,mobilePhone VARCHAR(75) null,homePhone VARCHAR(75) null,proPhone VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table User_UserContact";

	public static final String ORDER_BY_JPQL =
		" ORDER BY userContact.userId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY User_UserContact.userId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 1L;

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

	public UserContactModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _userId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setUserId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return UserContact.class;
	}

	@Override
	public String getModelClassName() {
		return UserContact.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<UserContact, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<UserContact, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserContact, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((UserContact)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<UserContact, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<UserContact, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(UserContact)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<UserContact, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<UserContact, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<UserContact, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<UserContact, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<UserContact, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<UserContact, Object>>();
		Map<String, BiConsumer<UserContact, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<UserContact, ?>>();

		attributeGetterFunctions.put("userId", UserContact::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<UserContact, Long>)UserContact::setUserId);
		attributeGetterFunctions.put("address", UserContact::getAddress);
		attributeSetterBiConsumers.put(
			"address",
			(BiConsumer<UserContact, String>)UserContact::setAddress);
		attributeGetterFunctions.put(
			"mobilePhone", UserContact::getMobilePhone);
		attributeSetterBiConsumers.put(
			"mobilePhone",
			(BiConsumer<UserContact, String>)UserContact::setMobilePhone);
		attributeGetterFunctions.put("homePhone", UserContact::getHomePhone);
		attributeSetterBiConsumers.put(
			"homePhone",
			(BiConsumer<UserContact, String>)UserContact::setHomePhone);
		attributeGetterFunctions.put("proPhone", UserContact::getProPhone);
		attributeSetterBiConsumers.put(
			"proPhone",
			(BiConsumer<UserContact, String>)UserContact::setProPhone);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

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

	@Override
	public String getAddress() {
		if (_address == null) {
			return "";
		}
		else {
			return _address;
		}
	}

	@Override
	public void setAddress(String address) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_address = address;
	}

	@Override
	public String getMobilePhone() {
		if (_mobilePhone == null) {
			return "";
		}
		else {
			return _mobilePhone;
		}
	}

	@Override
	public void setMobilePhone(String mobilePhone) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_mobilePhone = mobilePhone;
	}

	@Override
	public String getHomePhone() {
		if (_homePhone == null) {
			return "";
		}
		else {
			return _homePhone;
		}
	}

	@Override
	public void setHomePhone(String homePhone) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_homePhone = homePhone;
	}

	@Override
	public String getProPhone() {
		if (_proPhone == null) {
			return "";
		}
		else {
			return _proPhone;
		}
	}

	@Override
	public void setProPhone(String proPhone) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_proPhone = proPhone;
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
			0, UserContact.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public UserContact toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, UserContact>
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
		UserContactImpl userContactImpl = new UserContactImpl();

		userContactImpl.setUserId(getUserId());
		userContactImpl.setAddress(getAddress());
		userContactImpl.setMobilePhone(getMobilePhone());
		userContactImpl.setHomePhone(getHomePhone());
		userContactImpl.setProPhone(getProPhone());

		userContactImpl.resetOriginalValues();

		return userContactImpl;
	}

	@Override
	public UserContact cloneWithOriginalValues() {
		UserContactImpl userContactImpl = new UserContactImpl();

		userContactImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		userContactImpl.setAddress(
			this.<String>getColumnOriginalValue("address"));
		userContactImpl.setMobilePhone(
			this.<String>getColumnOriginalValue("mobilePhone"));
		userContactImpl.setHomePhone(
			this.<String>getColumnOriginalValue("homePhone"));
		userContactImpl.setProPhone(
			this.<String>getColumnOriginalValue("proPhone"));

		return userContactImpl;
	}

	@Override
	public int compareTo(UserContact userContact) {
		long primaryKey = userContact.getPrimaryKey();

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

		if (!(object instanceof UserContact)) {
			return false;
		}

		UserContact userContact = (UserContact)object;

		long primaryKey = userContact.getPrimaryKey();

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
	public CacheModel<UserContact> toCacheModel() {
		UserContactCacheModel userContactCacheModel =
			new UserContactCacheModel();

		userContactCacheModel.userId = getUserId();

		userContactCacheModel.address = getAddress();

		String address = userContactCacheModel.address;

		if ((address != null) && (address.length() == 0)) {
			userContactCacheModel.address = null;
		}

		userContactCacheModel.mobilePhone = getMobilePhone();

		String mobilePhone = userContactCacheModel.mobilePhone;

		if ((mobilePhone != null) && (mobilePhone.length() == 0)) {
			userContactCacheModel.mobilePhone = null;
		}

		userContactCacheModel.homePhone = getHomePhone();

		String homePhone = userContactCacheModel.homePhone;

		if ((homePhone != null) && (homePhone.length() == 0)) {
			userContactCacheModel.homePhone = null;
		}

		userContactCacheModel.proPhone = getProPhone();

		String proPhone = userContactCacheModel.proPhone;

		if ((proPhone != null) && (proPhone.length() == 0)) {
			userContactCacheModel.proPhone = null;
		}

		return userContactCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<UserContact, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<UserContact, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserContact, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((UserContact)this);

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

		private static final Function<InvocationHandler, UserContact>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					UserContact.class, ModelWrapper.class);

	}

	private long _userId;
	private String _address;
	private String _mobilePhone;
	private String _homePhone;
	private String _proPhone;

	public <T> T getColumnValue(String columnName) {
		Function<UserContact, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((UserContact)this);
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

		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("address", _address);
		_columnOriginalValues.put("mobilePhone", _mobilePhone);
		_columnOriginalValues.put("homePhone", _homePhone);
		_columnOriginalValues.put("proPhone", _proPhone);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("userId", 1L);

		columnBitmasks.put("address", 2L);

		columnBitmasks.put("mobilePhone", 4L);

		columnBitmasks.put("homePhone", 8L);

		columnBitmasks.put("proPhone", 16L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private UserContact _escapedModel;

}