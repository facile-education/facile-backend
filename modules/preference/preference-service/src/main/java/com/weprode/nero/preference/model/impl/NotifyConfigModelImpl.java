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

package com.weprode.nero.preference.model.impl;

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

import com.weprode.nero.preference.model.NotifyConfig;
import com.weprode.nero.preference.model.NotifyConfigModel;

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
 * The base model implementation for the NotifyConfig service. Represents a row in the &quot;Preference_NotifyConfig&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>NotifyConfigModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link NotifyConfigImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NotifyConfigImpl
 * @generated
 */
public class NotifyConfigModelImpl
	extends BaseModelImpl<NotifyConfig> implements NotifyConfigModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a notify config model instance should use the <code>NotifyConfig</code> interface instead.
	 */
	public static final String TABLE_NAME = "Preference_NotifyConfig";

	public static final Object[][] TABLE_COLUMNS = {
		{"notifyConfigId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"activate", Types.BOOLEAN}, {"notifyCasier", Types.BOOLEAN},
		{"notifyActu", Types.BOOLEAN}, {"notifyGrpDoc", Types.BOOLEAN},
		{"notifyAgenda", Types.BOOLEAN}, {"notifySync", Types.BOOLEAN},
		{"digestPeriod", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("notifyConfigId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("activate", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("notifyCasier", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("notifyActu", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("notifyGrpDoc", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("notifyAgenda", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("notifySync", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("digestPeriod", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Preference_NotifyConfig (notifyConfigId LONG not null primary key,userId LONG,activate BOOLEAN,notifyCasier BOOLEAN,notifyActu BOOLEAN,notifyGrpDoc BOOLEAN,notifyAgenda BOOLEAN,notifySync BOOLEAN,digestPeriod INTEGER)";

	public static final String TABLE_SQL_DROP =
		"drop table Preference_NotifyConfig";

	public static final String ORDER_BY_JPQL =
		" ORDER BY notifyConfig.notifyConfigId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Preference_NotifyConfig.notifyConfigId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ACTIVATE_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long DIGESTPERIOD_COLUMN_BITMASK = 2L;

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
	public static final long NOTIFYCONFIGID_COLUMN_BITMASK = 8L;

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

	public NotifyConfigModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _notifyConfigId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setNotifyConfigId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _notifyConfigId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return NotifyConfig.class;
	}

	@Override
	public String getModelClassName() {
		return NotifyConfig.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<NotifyConfig, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<NotifyConfig, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<NotifyConfig, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((NotifyConfig)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<NotifyConfig, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<NotifyConfig, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(NotifyConfig)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<NotifyConfig, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<NotifyConfig, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<NotifyConfig, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<NotifyConfig, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<NotifyConfig, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<NotifyConfig, Object>>();
		Map<String, BiConsumer<NotifyConfig, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<NotifyConfig, ?>>();

		attributeGetterFunctions.put(
			"notifyConfigId", NotifyConfig::getNotifyConfigId);
		attributeSetterBiConsumers.put(
			"notifyConfigId",
			(BiConsumer<NotifyConfig, Long>)NotifyConfig::setNotifyConfigId);
		attributeGetterFunctions.put("userId", NotifyConfig::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<NotifyConfig, Long>)NotifyConfig::setUserId);
		attributeGetterFunctions.put("activate", NotifyConfig::getActivate);
		attributeSetterBiConsumers.put(
			"activate",
			(BiConsumer<NotifyConfig, Boolean>)NotifyConfig::setActivate);
		attributeGetterFunctions.put(
			"notifyCasier", NotifyConfig::getNotifyCasier);
		attributeSetterBiConsumers.put(
			"notifyCasier",
			(BiConsumer<NotifyConfig, Boolean>)NotifyConfig::setNotifyCasier);
		attributeGetterFunctions.put("notifyActu", NotifyConfig::getNotifyActu);
		attributeSetterBiConsumers.put(
			"notifyActu",
			(BiConsumer<NotifyConfig, Boolean>)NotifyConfig::setNotifyActu);
		attributeGetterFunctions.put(
			"notifyGrpDoc", NotifyConfig::getNotifyGrpDoc);
		attributeSetterBiConsumers.put(
			"notifyGrpDoc",
			(BiConsumer<NotifyConfig, Boolean>)NotifyConfig::setNotifyGrpDoc);
		attributeGetterFunctions.put(
			"notifyAgenda", NotifyConfig::getNotifyAgenda);
		attributeSetterBiConsumers.put(
			"notifyAgenda",
			(BiConsumer<NotifyConfig, Boolean>)NotifyConfig::setNotifyAgenda);
		attributeGetterFunctions.put("notifySync", NotifyConfig::getNotifySync);
		attributeSetterBiConsumers.put(
			"notifySync",
			(BiConsumer<NotifyConfig, Boolean>)NotifyConfig::setNotifySync);
		attributeGetterFunctions.put(
			"digestPeriod", NotifyConfig::getDigestPeriod);
		attributeSetterBiConsumers.put(
			"digestPeriod",
			(BiConsumer<NotifyConfig, Integer>)NotifyConfig::setDigestPeriod);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getNotifyConfigId() {
		return _notifyConfigId;
	}

	@Override
	public void setNotifyConfigId(long notifyConfigId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_notifyConfigId = notifyConfigId;
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
	public boolean getActivate() {
		return _activate;
	}

	@Override
	public boolean isActivate() {
		return _activate;
	}

	@Override
	public void setActivate(boolean activate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_activate = activate;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalActivate() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("activate"));
	}

	@Override
	public boolean getNotifyCasier() {
		return _notifyCasier;
	}

	@Override
	public boolean isNotifyCasier() {
		return _notifyCasier;
	}

	@Override
	public void setNotifyCasier(boolean notifyCasier) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_notifyCasier = notifyCasier;
	}

	@Override
	public boolean getNotifyActu() {
		return _notifyActu;
	}

	@Override
	public boolean isNotifyActu() {
		return _notifyActu;
	}

	@Override
	public void setNotifyActu(boolean notifyActu) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_notifyActu = notifyActu;
	}

	@Override
	public boolean getNotifyGrpDoc() {
		return _notifyGrpDoc;
	}

	@Override
	public boolean isNotifyGrpDoc() {
		return _notifyGrpDoc;
	}

	@Override
	public void setNotifyGrpDoc(boolean notifyGrpDoc) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_notifyGrpDoc = notifyGrpDoc;
	}

	@Override
	public boolean getNotifyAgenda() {
		return _notifyAgenda;
	}

	@Override
	public boolean isNotifyAgenda() {
		return _notifyAgenda;
	}

	@Override
	public void setNotifyAgenda(boolean notifyAgenda) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_notifyAgenda = notifyAgenda;
	}

	@Override
	public boolean getNotifySync() {
		return _notifySync;
	}

	@Override
	public boolean isNotifySync() {
		return _notifySync;
	}

	@Override
	public void setNotifySync(boolean notifySync) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_notifySync = notifySync;
	}

	@Override
	public int getDigestPeriod() {
		return _digestPeriod;
	}

	@Override
	public void setDigestPeriod(int digestPeriod) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_digestPeriod = digestPeriod;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalDigestPeriod() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("digestPeriod"));
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
			0, NotifyConfig.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public NotifyConfig toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, NotifyConfig>
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
		NotifyConfigImpl notifyConfigImpl = new NotifyConfigImpl();

		notifyConfigImpl.setNotifyConfigId(getNotifyConfigId());
		notifyConfigImpl.setUserId(getUserId());
		notifyConfigImpl.setActivate(isActivate());
		notifyConfigImpl.setNotifyCasier(isNotifyCasier());
		notifyConfigImpl.setNotifyActu(isNotifyActu());
		notifyConfigImpl.setNotifyGrpDoc(isNotifyGrpDoc());
		notifyConfigImpl.setNotifyAgenda(isNotifyAgenda());
		notifyConfigImpl.setNotifySync(isNotifySync());
		notifyConfigImpl.setDigestPeriod(getDigestPeriod());

		notifyConfigImpl.resetOriginalValues();

		return notifyConfigImpl;
	}

	@Override
	public NotifyConfig cloneWithOriginalValues() {
		NotifyConfigImpl notifyConfigImpl = new NotifyConfigImpl();

		notifyConfigImpl.setNotifyConfigId(
			this.<Long>getColumnOriginalValue("notifyConfigId"));
		notifyConfigImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		notifyConfigImpl.setActivate(
			this.<Boolean>getColumnOriginalValue("activate"));
		notifyConfigImpl.setNotifyCasier(
			this.<Boolean>getColumnOriginalValue("notifyCasier"));
		notifyConfigImpl.setNotifyActu(
			this.<Boolean>getColumnOriginalValue("notifyActu"));
		notifyConfigImpl.setNotifyGrpDoc(
			this.<Boolean>getColumnOriginalValue("notifyGrpDoc"));
		notifyConfigImpl.setNotifyAgenda(
			this.<Boolean>getColumnOriginalValue("notifyAgenda"));
		notifyConfigImpl.setNotifySync(
			this.<Boolean>getColumnOriginalValue("notifySync"));
		notifyConfigImpl.setDigestPeriod(
			this.<Integer>getColumnOriginalValue("digestPeriod"));

		return notifyConfigImpl;
	}

	@Override
	public int compareTo(NotifyConfig notifyConfig) {
		long primaryKey = notifyConfig.getPrimaryKey();

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

		if (!(object instanceof NotifyConfig)) {
			return false;
		}

		NotifyConfig notifyConfig = (NotifyConfig)object;

		long primaryKey = notifyConfig.getPrimaryKey();

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
	public CacheModel<NotifyConfig> toCacheModel() {
		NotifyConfigCacheModel notifyConfigCacheModel =
			new NotifyConfigCacheModel();

		notifyConfigCacheModel.notifyConfigId = getNotifyConfigId();

		notifyConfigCacheModel.userId = getUserId();

		notifyConfigCacheModel.activate = isActivate();

		notifyConfigCacheModel.notifyCasier = isNotifyCasier();

		notifyConfigCacheModel.notifyActu = isNotifyActu();

		notifyConfigCacheModel.notifyGrpDoc = isNotifyGrpDoc();

		notifyConfigCacheModel.notifyAgenda = isNotifyAgenda();

		notifyConfigCacheModel.notifySync = isNotifySync();

		notifyConfigCacheModel.digestPeriod = getDigestPeriod();

		return notifyConfigCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<NotifyConfig, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<NotifyConfig, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<NotifyConfig, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((NotifyConfig)this);

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

		private static final Function<InvocationHandler, NotifyConfig>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					NotifyConfig.class, ModelWrapper.class);

	}

	private long _notifyConfigId;
	private long _userId;
	private boolean _activate;
	private boolean _notifyCasier;
	private boolean _notifyActu;
	private boolean _notifyGrpDoc;
	private boolean _notifyAgenda;
	private boolean _notifySync;
	private int _digestPeriod;

	public <T> T getColumnValue(String columnName) {
		Function<NotifyConfig, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((NotifyConfig)this);
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

		_columnOriginalValues.put("notifyConfigId", _notifyConfigId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("activate", _activate);
		_columnOriginalValues.put("notifyCasier", _notifyCasier);
		_columnOriginalValues.put("notifyActu", _notifyActu);
		_columnOriginalValues.put("notifyGrpDoc", _notifyGrpDoc);
		_columnOriginalValues.put("notifyAgenda", _notifyAgenda);
		_columnOriginalValues.put("notifySync", _notifySync);
		_columnOriginalValues.put("digestPeriod", _digestPeriod);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("notifyConfigId", 1L);

		columnBitmasks.put("userId", 2L);

		columnBitmasks.put("activate", 4L);

		columnBitmasks.put("notifyCasier", 8L);

		columnBitmasks.put("notifyActu", 16L);

		columnBitmasks.put("notifyGrpDoc", 32L);

		columnBitmasks.put("notifyAgenda", 64L);

		columnBitmasks.put("notifySync", 128L);

		columnBitmasks.put("digestPeriod", 256L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private NotifyConfig _escapedModel;

}