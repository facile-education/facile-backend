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

package com.weprode.facile.schedule.model.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.facile.schedule.model.SlotConfiguration;
import com.weprode.facile.schedule.model.SlotConfigurationModel;
import com.weprode.facile.schedule.service.persistence.SlotConfigurationPK;

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
 * The base model implementation for the SlotConfiguration service. Represents a row in the &quot;Schedule_SlotConfiguration&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SlotConfigurationModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SlotConfigurationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SlotConfigurationImpl
 * @generated
 */
@JSON(strict = true)
public class SlotConfigurationModelImpl
	extends BaseModelImpl<SlotConfiguration> implements SlotConfigurationModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a slot configuration model instance should use the <code>SlotConfiguration</code> interface instead.
	 */
	public static final String TABLE_NAME = "Schedule_SlotConfiguration";

	public static final Object[][] TABLE_COLUMNS = {
		{"schoolId", Types.BIGINT}, {"slotNumber", Types.INTEGER},
		{"sessionStartHour", Types.VARCHAR}, {"sessionEndHour", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("schoolId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("slotNumber", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("sessionStartHour", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("sessionEndHour", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Schedule_SlotConfiguration (schoolId LONG not null,slotNumber INTEGER not null,sessionStartHour VARCHAR(75) null,sessionEndHour VARCHAR(75) null,primary key (schoolId, slotNumber))";

	public static final String TABLE_SQL_DROP =
		"drop table Schedule_SlotConfiguration";

	public static final String ORDER_BY_JPQL =
		" ORDER BY slotConfiguration.id.schoolId ASC, slotConfiguration.id.slotNumber ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Schedule_SlotConfiguration.schoolId ASC, Schedule_SlotConfiguration.slotNumber ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SCHOOLID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SLOTNUMBER_COLUMN_BITMASK = 2L;

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

	public SlotConfigurationModelImpl() {
	}

	@Override
	public SlotConfigurationPK getPrimaryKey() {
		return new SlotConfigurationPK(_schoolId, _slotNumber);
	}

	@Override
	public void setPrimaryKey(SlotConfigurationPK primaryKey) {
		setSchoolId(primaryKey.schoolId);
		setSlotNumber(primaryKey.slotNumber);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new SlotConfigurationPK(_schoolId, _slotNumber);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((SlotConfigurationPK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return SlotConfiguration.class;
	}

	@Override
	public String getModelClassName() {
		return SlotConfiguration.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SlotConfiguration, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SlotConfiguration, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SlotConfiguration, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((SlotConfiguration)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SlotConfiguration, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SlotConfiguration, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SlotConfiguration)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SlotConfiguration, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SlotConfiguration, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<SlotConfiguration, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<SlotConfiguration, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<SlotConfiguration, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<SlotConfiguration, Object>>();
		Map<String, BiConsumer<SlotConfiguration, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<SlotConfiguration, ?>>();

		attributeGetterFunctions.put(
			"schoolId", SlotConfiguration::getSchoolId);
		attributeSetterBiConsumers.put(
			"schoolId",
			(BiConsumer<SlotConfiguration, Long>)
				SlotConfiguration::setSchoolId);
		attributeGetterFunctions.put(
			"slotNumber", SlotConfiguration::getSlotNumber);
		attributeSetterBiConsumers.put(
			"slotNumber",
			(BiConsumer<SlotConfiguration, Integer>)
				SlotConfiguration::setSlotNumber);
		attributeGetterFunctions.put(
			"sessionStartHour", SlotConfiguration::getSessionStartHour);
		attributeSetterBiConsumers.put(
			"sessionStartHour",
			(BiConsumer<SlotConfiguration, String>)
				SlotConfiguration::setSessionStartHour);
		attributeGetterFunctions.put(
			"sessionEndHour", SlotConfiguration::getSessionEndHour);
		attributeSetterBiConsumers.put(
			"sessionEndHour",
			(BiConsumer<SlotConfiguration, String>)
				SlotConfiguration::setSessionEndHour);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
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

	@JSON
	@Override
	public int getSlotNumber() {
		return _slotNumber;
	}

	@Override
	public void setSlotNumber(int slotNumber) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_slotNumber = slotNumber;
	}

	@JSON
	@Override
	public String getSessionStartHour() {
		if (_sessionStartHour == null) {
			return "";
		}
		else {
			return _sessionStartHour;
		}
	}

	@Override
	public void setSessionStartHour(String sessionStartHour) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sessionStartHour = sessionStartHour;
	}

	@JSON
	@Override
	public String getSessionEndHour() {
		if (_sessionEndHour == null) {
			return "";
		}
		else {
			return _sessionEndHour;
		}
	}

	@Override
	public void setSessionEndHour(String sessionEndHour) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sessionEndHour = sessionEndHour;
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
	public SlotConfiguration toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SlotConfiguration>
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
		SlotConfigurationImpl slotConfigurationImpl =
			new SlotConfigurationImpl();

		slotConfigurationImpl.setSchoolId(getSchoolId());
		slotConfigurationImpl.setSlotNumber(getSlotNumber());
		slotConfigurationImpl.setSessionStartHour(getSessionStartHour());
		slotConfigurationImpl.setSessionEndHour(getSessionEndHour());

		slotConfigurationImpl.resetOriginalValues();

		return slotConfigurationImpl;
	}

	@Override
	public SlotConfiguration cloneWithOriginalValues() {
		SlotConfigurationImpl slotConfigurationImpl =
			new SlotConfigurationImpl();

		slotConfigurationImpl.setSchoolId(
			this.<Long>getColumnOriginalValue("schoolId"));
		slotConfigurationImpl.setSlotNumber(
			this.<Integer>getColumnOriginalValue("slotNumber"));
		slotConfigurationImpl.setSessionStartHour(
			this.<String>getColumnOriginalValue("sessionStartHour"));
		slotConfigurationImpl.setSessionEndHour(
			this.<String>getColumnOriginalValue("sessionEndHour"));

		return slotConfigurationImpl;
	}

	@Override
	public int compareTo(SlotConfiguration slotConfiguration) {
		SlotConfigurationPK primaryKey = slotConfiguration.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SlotConfiguration)) {
			return false;
		}

		SlotConfiguration slotConfiguration = (SlotConfiguration)object;

		SlotConfigurationPK primaryKey = slotConfiguration.getPrimaryKey();

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
	public CacheModel<SlotConfiguration> toCacheModel() {
		SlotConfigurationCacheModel slotConfigurationCacheModel =
			new SlotConfigurationCacheModel();

		slotConfigurationCacheModel.slotConfigurationPK = getPrimaryKey();

		slotConfigurationCacheModel.schoolId = getSchoolId();

		slotConfigurationCacheModel.slotNumber = getSlotNumber();

		slotConfigurationCacheModel.sessionStartHour = getSessionStartHour();

		String sessionStartHour = slotConfigurationCacheModel.sessionStartHour;

		if ((sessionStartHour != null) && (sessionStartHour.length() == 0)) {
			slotConfigurationCacheModel.sessionStartHour = null;
		}

		slotConfigurationCacheModel.sessionEndHour = getSessionEndHour();

		String sessionEndHour = slotConfigurationCacheModel.sessionEndHour;

		if ((sessionEndHour != null) && (sessionEndHour.length() == 0)) {
			slotConfigurationCacheModel.sessionEndHour = null;
		}

		return slotConfigurationCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SlotConfiguration, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SlotConfiguration, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SlotConfiguration, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(SlotConfiguration)this);

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

		private static final Function<InvocationHandler, SlotConfiguration>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					SlotConfiguration.class, ModelWrapper.class);

	}

	private long _schoolId;
	private int _slotNumber;
	private String _sessionStartHour;
	private String _sessionEndHour;

	public <T> T getColumnValue(String columnName) {
		Function<SlotConfiguration, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((SlotConfiguration)this);
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

		_columnOriginalValues.put("schoolId", _schoolId);
		_columnOriginalValues.put("slotNumber", _slotNumber);
		_columnOriginalValues.put("sessionStartHour", _sessionStartHour);
		_columnOriginalValues.put("sessionEndHour", _sessionEndHour);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("schoolId", 1L);

		columnBitmasks.put("slotNumber", 2L);

		columnBitmasks.put("sessionStartHour", 4L);

		columnBitmasks.put("sessionEndHour", 8L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private SlotConfiguration _escapedModel;

}