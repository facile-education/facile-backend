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

package com.weprode.nero.statistic.model.impl;

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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.nero.statistic.model.UserLogin;
import com.weprode.nero.statistic.model.UserLoginModel;

import java.io.Serializable;

import java.lang.reflect.Constructor;
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
 * The base model implementation for the UserLogin service. Represents a row in the &quot;Statistics_UserLogin&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>UserLoginModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserLoginImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserLoginImpl
 * @generated
 */
public class UserLoginModelImpl
	extends BaseModelImpl<UserLogin> implements UserLoginModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user login model instance should use the <code>UserLogin</code> interface instead.
	 */
	public static final String TABLE_NAME = "Statistics_UserLogin";

	public static final Object[][] TABLE_COLUMNS = {
		{"userLoginId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"loginDate", Types.TIMESTAMP}, {"role_", Types.INTEGER},
		{"schoolId", Types.BIGINT}, {"isMobileApp", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("userLoginId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("loginDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("role_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("schoolId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("isMobileApp", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Statistics_UserLogin (userLoginId LONG not null primary key,userId LONG,loginDate DATE null,role_ INTEGER,schoolId LONG,isMobileApp BOOLEAN)";

	public static final String TABLE_SQL_DROP =
		"drop table Statistics_UserLogin";

	public static final String ORDER_BY_JPQL =
		" ORDER BY userLogin.userLoginId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Statistics_UserLogin.userLoginId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ROLE_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SCHOOLID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERLOGINID_COLUMN_BITMASK = 8L;

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

	public UserLoginModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _userLoginId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setUserLoginId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userLoginId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return UserLogin.class;
	}

	@Override
	public String getModelClassName() {
		return UserLogin.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<UserLogin, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<UserLogin, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserLogin, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((UserLogin)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<UserLogin, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<UserLogin, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(UserLogin)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<UserLogin, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<UserLogin, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, UserLogin>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			UserLogin.class.getClassLoader(), UserLogin.class,
			ModelWrapper.class);

		try {
			Constructor<UserLogin> constructor =
				(Constructor<UserLogin>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<UserLogin, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<UserLogin, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<UserLogin, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<UserLogin, Object>>();
		Map<String, BiConsumer<UserLogin, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<UserLogin, ?>>();

		attributeGetterFunctions.put("userLoginId", UserLogin::getUserLoginId);
		attributeSetterBiConsumers.put(
			"userLoginId",
			(BiConsumer<UserLogin, Long>)UserLogin::setUserLoginId);
		attributeGetterFunctions.put("userId", UserLogin::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<UserLogin, Long>)UserLogin::setUserId);
		attributeGetterFunctions.put("loginDate", UserLogin::getLoginDate);
		attributeSetterBiConsumers.put(
			"loginDate", (BiConsumer<UserLogin, Date>)UserLogin::setLoginDate);
		attributeGetterFunctions.put("role", UserLogin::getRole);
		attributeSetterBiConsumers.put(
			"role", (BiConsumer<UserLogin, Integer>)UserLogin::setRole);
		attributeGetterFunctions.put("schoolId", UserLogin::getSchoolId);
		attributeSetterBiConsumers.put(
			"schoolId", (BiConsumer<UserLogin, Long>)UserLogin::setSchoolId);
		attributeGetterFunctions.put("isMobileApp", UserLogin::getIsMobileApp);
		attributeSetterBiConsumers.put(
			"isMobileApp",
			(BiConsumer<UserLogin, Boolean>)UserLogin::setIsMobileApp);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getUserLoginId() {
		return _userLoginId;
	}

	@Override
	public void setUserLoginId(long userLoginId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userLoginId = userLoginId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalUserId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("userId"));
	}

	@Override
	public Date getLoginDate() {
		return _loginDate;
	}

	@Override
	public void setLoginDate(Date loginDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_loginDate = loginDate;
	}

	@Override
	public int getRole() {
		return _role;
	}

	@Override
	public void setRole(int role) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_role = role;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalRole() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("role_"));
	}

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

	@Override
	public boolean getIsMobileApp() {
		return _isMobileApp;
	}

	@Override
	public boolean isIsMobileApp() {
		return _isMobileApp;
	}

	@Override
	public void setIsMobileApp(boolean isMobileApp) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_isMobileApp = isMobileApp;
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
			0, UserLogin.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public UserLogin toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, UserLogin>
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
		UserLoginImpl userLoginImpl = new UserLoginImpl();

		userLoginImpl.setUserLoginId(getUserLoginId());
		userLoginImpl.setUserId(getUserId());
		userLoginImpl.setLoginDate(getLoginDate());
		userLoginImpl.setRole(getRole());
		userLoginImpl.setSchoolId(getSchoolId());
		userLoginImpl.setIsMobileApp(isIsMobileApp());

		userLoginImpl.resetOriginalValues();

		return userLoginImpl;
	}

	@Override
	public UserLogin cloneWithOriginalValues() {
		UserLoginImpl userLoginImpl = new UserLoginImpl();

		userLoginImpl.setUserLoginId(
			this.<Long>getColumnOriginalValue("userLoginId"));
		userLoginImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		userLoginImpl.setLoginDate(
			this.<Date>getColumnOriginalValue("loginDate"));
		userLoginImpl.setRole(this.<Integer>getColumnOriginalValue("role_"));
		userLoginImpl.setSchoolId(
			this.<Long>getColumnOriginalValue("schoolId"));
		userLoginImpl.setIsMobileApp(
			this.<Boolean>getColumnOriginalValue("isMobileApp"));

		return userLoginImpl;
	}

	@Override
	public int compareTo(UserLogin userLogin) {
		long primaryKey = userLogin.getPrimaryKey();

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

		if (!(object instanceof UserLogin)) {
			return false;
		}

		UserLogin userLogin = (UserLogin)object;

		long primaryKey = userLogin.getPrimaryKey();

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
	public CacheModel<UserLogin> toCacheModel() {
		UserLoginCacheModel userLoginCacheModel = new UserLoginCacheModel();

		userLoginCacheModel.userLoginId = getUserLoginId();

		userLoginCacheModel.userId = getUserId();

		Date loginDate = getLoginDate();

		if (loginDate != null) {
			userLoginCacheModel.loginDate = loginDate.getTime();
		}
		else {
			userLoginCacheModel.loginDate = Long.MIN_VALUE;
		}

		userLoginCacheModel.role = getRole();

		userLoginCacheModel.schoolId = getSchoolId();

		userLoginCacheModel.isMobileApp = isIsMobileApp();

		return userLoginCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<UserLogin, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<UserLogin, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserLogin, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((UserLogin)this);

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

	@Override
	public String toXmlString() {
		Map<String, Function<UserLogin, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<UserLogin, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserLogin, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((UserLogin)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, UserLogin>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _userLoginId;
	private long _userId;
	private Date _loginDate;
	private int _role;
	private long _schoolId;
	private boolean _isMobileApp;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<UserLogin, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((UserLogin)this);
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

		_columnOriginalValues.put("userLoginId", _userLoginId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("loginDate", _loginDate);
		_columnOriginalValues.put("role_", _role);
		_columnOriginalValues.put("schoolId", _schoolId);
		_columnOriginalValues.put("isMobileApp", _isMobileApp);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("role_", "role");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("userLoginId", 1L);

		columnBitmasks.put("userId", 2L);

		columnBitmasks.put("loginDate", 4L);

		columnBitmasks.put("role_", 8L);

		columnBitmasks.put("schoolId", 16L);

		columnBitmasks.put("isMobileApp", 32L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private UserLogin _escapedModel;

}