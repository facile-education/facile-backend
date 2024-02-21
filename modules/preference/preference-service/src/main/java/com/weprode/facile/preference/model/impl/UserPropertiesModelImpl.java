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

package com.weprode.facile.preference.model.impl;

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

import com.weprode.facile.preference.model.UserProperties;
import com.weprode.facile.preference.model.UserPropertiesModel;

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
 * The base model implementation for the UserProperties service. Represents a row in the &quot;Preference_UserProperties&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>UserPropertiesModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserPropertiesImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserPropertiesImpl
 * @generated
 */
@JSON(strict = true)
public class UserPropertiesModelImpl
	extends BaseModelImpl<UserProperties> implements UserPropertiesModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user properties model instance should use the <code>UserProperties</code> interface instead.
	 */
	public static final String TABLE_NAME = "Preference_UserProperties";

	public static final Object[][] TABLE_COLUMNS = {
		{"userId", Types.BIGINT}, {"manualAccount", Types.BOOLEAN},
		{"hideMenu", Types.BOOLEAN}, {"themeColor", Types.VARCHAR},
		{"etabId", Types.BIGINT}, {"termsOfUseAgreedDate", Types.TIMESTAMP},
		{"lastSynchroDate", Types.TIMESTAMP},
		{"lastDashboardAccessDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("manualAccount", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("hideMenu", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("themeColor", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("etabId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("termsOfUseAgreedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("lastSynchroDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("lastDashboardAccessDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Preference_UserProperties (userId LONG not null primary key,manualAccount BOOLEAN,hideMenu BOOLEAN,themeColor VARCHAR(75) null,etabId LONG,termsOfUseAgreedDate DATE null,lastSynchroDate DATE null,lastDashboardAccessDate DATE null)";

	public static final String TABLE_SQL_DROP =
		"drop table Preference_UserProperties";

	public static final String ORDER_BY_JPQL =
		" ORDER BY userProperties.userId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Preference_UserProperties.userId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ETABID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long MANUALACCOUNT_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 4L;

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

	public UserPropertiesModelImpl() {
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
		return UserProperties.class;
	}

	@Override
	public String getModelClassName() {
		return UserProperties.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<UserProperties, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<UserProperties, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserProperties, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((UserProperties)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<UserProperties, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<UserProperties, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(UserProperties)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<UserProperties, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<UserProperties, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<UserProperties, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<UserProperties, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<UserProperties, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<UserProperties, Object>>();
		Map<String, BiConsumer<UserProperties, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<UserProperties, ?>>();

		attributeGetterFunctions.put("userId", UserProperties::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<UserProperties, Long>)UserProperties::setUserId);
		attributeGetterFunctions.put(
			"manualAccount", UserProperties::getManualAccount);
		attributeSetterBiConsumers.put(
			"manualAccount",
			(BiConsumer<UserProperties, Boolean>)
				UserProperties::setManualAccount);
		attributeGetterFunctions.put("hideMenu", UserProperties::getHideMenu);
		attributeSetterBiConsumers.put(
			"hideMenu",
			(BiConsumer<UserProperties, Boolean>)UserProperties::setHideMenu);
		attributeGetterFunctions.put(
			"themeColor", UserProperties::getThemeColor);
		attributeSetterBiConsumers.put(
			"themeColor",
			(BiConsumer<UserProperties, String>)UserProperties::setThemeColor);
		attributeGetterFunctions.put("etabId", UserProperties::getEtabId);
		attributeSetterBiConsumers.put(
			"etabId",
			(BiConsumer<UserProperties, Long>)UserProperties::setEtabId);
		attributeGetterFunctions.put(
			"termsOfUseAgreedDate", UserProperties::getTermsOfUseAgreedDate);
		attributeSetterBiConsumers.put(
			"termsOfUseAgreedDate",
			(BiConsumer<UserProperties, Date>)
				UserProperties::setTermsOfUseAgreedDate);
		attributeGetterFunctions.put(
			"lastSynchroDate", UserProperties::getLastSynchroDate);
		attributeSetterBiConsumers.put(
			"lastSynchroDate",
			(BiConsumer<UserProperties, Date>)
				UserProperties::setLastSynchroDate);
		attributeGetterFunctions.put(
			"lastDashboardAccessDate",
			UserProperties::getLastDashboardAccessDate);
		attributeSetterBiConsumers.put(
			"lastDashboardAccessDate",
			(BiConsumer<UserProperties, Date>)
				UserProperties::setLastDashboardAccessDate);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
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

	@JSON
	@Override
	public boolean getManualAccount() {
		return _manualAccount;
	}

	@JSON
	@Override
	public boolean isManualAccount() {
		return _manualAccount;
	}

	@Override
	public void setManualAccount(boolean manualAccount) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_manualAccount = manualAccount;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalManualAccount() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("manualAccount"));
	}

	@JSON
	@Override
	public boolean getHideMenu() {
		return _hideMenu;
	}

	@JSON
	@Override
	public boolean isHideMenu() {
		return _hideMenu;
	}

	@Override
	public void setHideMenu(boolean hideMenu) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_hideMenu = hideMenu;
	}

	@JSON
	@Override
	public String getThemeColor() {
		if (_themeColor == null) {
			return "";
		}
		else {
			return _themeColor;
		}
	}

	@Override
	public void setThemeColor(String themeColor) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_themeColor = themeColor;
	}

	@JSON
	@Override
	public long getEtabId() {
		return _etabId;
	}

	@Override
	public void setEtabId(long etabId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_etabId = etabId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalEtabId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("etabId"));
	}

	@JSON
	@Override
	public Date getTermsOfUseAgreedDate() {
		return _termsOfUseAgreedDate;
	}

	@Override
	public void setTermsOfUseAgreedDate(Date termsOfUseAgreedDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_termsOfUseAgreedDate = termsOfUseAgreedDate;
	}

	@JSON
	@Override
	public Date getLastSynchroDate() {
		return _lastSynchroDate;
	}

	@Override
	public void setLastSynchroDate(Date lastSynchroDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lastSynchroDate = lastSynchroDate;
	}

	@JSON
	@Override
	public Date getLastDashboardAccessDate() {
		return _lastDashboardAccessDate;
	}

	@Override
	public void setLastDashboardAccessDate(Date lastDashboardAccessDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lastDashboardAccessDate = lastDashboardAccessDate;
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
			0, UserProperties.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public UserProperties toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, UserProperties>
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
		UserPropertiesImpl userPropertiesImpl = new UserPropertiesImpl();

		userPropertiesImpl.setUserId(getUserId());
		userPropertiesImpl.setManualAccount(isManualAccount());
		userPropertiesImpl.setHideMenu(isHideMenu());
		userPropertiesImpl.setThemeColor(getThemeColor());
		userPropertiesImpl.setEtabId(getEtabId());
		userPropertiesImpl.setTermsOfUseAgreedDate(getTermsOfUseAgreedDate());
		userPropertiesImpl.setLastSynchroDate(getLastSynchroDate());
		userPropertiesImpl.setLastDashboardAccessDate(
			getLastDashboardAccessDate());

		userPropertiesImpl.resetOriginalValues();

		return userPropertiesImpl;
	}

	@Override
	public UserProperties cloneWithOriginalValues() {
		UserPropertiesImpl userPropertiesImpl = new UserPropertiesImpl();

		userPropertiesImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		userPropertiesImpl.setManualAccount(
			this.<Boolean>getColumnOriginalValue("manualAccount"));
		userPropertiesImpl.setHideMenu(
			this.<Boolean>getColumnOriginalValue("hideMenu"));
		userPropertiesImpl.setThemeColor(
			this.<String>getColumnOriginalValue("themeColor"));
		userPropertiesImpl.setEtabId(
			this.<Long>getColumnOriginalValue("etabId"));
		userPropertiesImpl.setTermsOfUseAgreedDate(
			this.<Date>getColumnOriginalValue("termsOfUseAgreedDate"));
		userPropertiesImpl.setLastSynchroDate(
			this.<Date>getColumnOriginalValue("lastSynchroDate"));
		userPropertiesImpl.setLastDashboardAccessDate(
			this.<Date>getColumnOriginalValue("lastDashboardAccessDate"));

		return userPropertiesImpl;
	}

	@Override
	public int compareTo(UserProperties userProperties) {
		long primaryKey = userProperties.getPrimaryKey();

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

		if (!(object instanceof UserProperties)) {
			return false;
		}

		UserProperties userProperties = (UserProperties)object;

		long primaryKey = userProperties.getPrimaryKey();

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
	public CacheModel<UserProperties> toCacheModel() {
		UserPropertiesCacheModel userPropertiesCacheModel =
			new UserPropertiesCacheModel();

		userPropertiesCacheModel.userId = getUserId();

		userPropertiesCacheModel.manualAccount = isManualAccount();

		userPropertiesCacheModel.hideMenu = isHideMenu();

		userPropertiesCacheModel.themeColor = getThemeColor();

		String themeColor = userPropertiesCacheModel.themeColor;

		if ((themeColor != null) && (themeColor.length() == 0)) {
			userPropertiesCacheModel.themeColor = null;
		}

		userPropertiesCacheModel.etabId = getEtabId();

		Date termsOfUseAgreedDate = getTermsOfUseAgreedDate();

		if (termsOfUseAgreedDate != null) {
			userPropertiesCacheModel.termsOfUseAgreedDate =
				termsOfUseAgreedDate.getTime();
		}
		else {
			userPropertiesCacheModel.termsOfUseAgreedDate = Long.MIN_VALUE;
		}

		Date lastSynchroDate = getLastSynchroDate();

		if (lastSynchroDate != null) {
			userPropertiesCacheModel.lastSynchroDate =
				lastSynchroDate.getTime();
		}
		else {
			userPropertiesCacheModel.lastSynchroDate = Long.MIN_VALUE;
		}

		Date lastDashboardAccessDate = getLastDashboardAccessDate();

		if (lastDashboardAccessDate != null) {
			userPropertiesCacheModel.lastDashboardAccessDate =
				lastDashboardAccessDate.getTime();
		}
		else {
			userPropertiesCacheModel.lastDashboardAccessDate = Long.MIN_VALUE;
		}

		return userPropertiesCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<UserProperties, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<UserProperties, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserProperties, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((UserProperties)this);

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

		private static final Function<InvocationHandler, UserProperties>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					UserProperties.class, ModelWrapper.class);

	}

	private long _userId;
	private boolean _manualAccount;
	private boolean _hideMenu;
	private String _themeColor;
	private long _etabId;
	private Date _termsOfUseAgreedDate;
	private Date _lastSynchroDate;
	private Date _lastDashboardAccessDate;

	public <T> T getColumnValue(String columnName) {
		Function<UserProperties, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((UserProperties)this);
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
		_columnOriginalValues.put("manualAccount", _manualAccount);
		_columnOriginalValues.put("hideMenu", _hideMenu);
		_columnOriginalValues.put("themeColor", _themeColor);
		_columnOriginalValues.put("etabId", _etabId);
		_columnOriginalValues.put(
			"termsOfUseAgreedDate", _termsOfUseAgreedDate);
		_columnOriginalValues.put("lastSynchroDate", _lastSynchroDate);
		_columnOriginalValues.put(
			"lastDashboardAccessDate", _lastDashboardAccessDate);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("userId", 1L);

		columnBitmasks.put("manualAccount", 2L);

		columnBitmasks.put("hideMenu", 4L);

		columnBitmasks.put("themeColor", 8L);

		columnBitmasks.put("etabId", 16L);

		columnBitmasks.put("termsOfUseAgreedDate", 32L);

		columnBitmasks.put("lastSynchroDate", 64L);

		columnBitmasks.put("lastDashboardAccessDate", 128L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private UserProperties _escapedModel;

}