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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.facile.schedule.model.ScheduleConfiguration;
import com.weprode.facile.schedule.model.ScheduleConfigurationModel;

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
 * The base model implementation for the ScheduleConfiguration service. Represents a row in the &quot;Schedule_ScheduleConfiguration&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>ScheduleConfigurationModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ScheduleConfigurationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ScheduleConfigurationImpl
 * @generated
 */
@JSON(strict = true)
public class ScheduleConfigurationModelImpl
	extends BaseModelImpl<ScheduleConfiguration>
	implements ScheduleConfigurationModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a schedule configuration model instance should use the <code>ScheduleConfiguration</code> interface instead.
	 */
	public static final String TABLE_NAME = "Schedule_ScheduleConfiguration";

	public static final Object[][] TABLE_COLUMNS = {
		{"configId", Types.BIGINT}, {"projectStartDate", Types.TIMESTAMP},
		{"schoolYearStartDate", Types.TIMESTAMP},
		{"schoolYearSemesterDate", Types.TIMESTAMP},
		{"schoolYearEndDate", Types.TIMESTAMP}, {"h1Weeks", Types.VARCHAR},
		{"h2Weeks", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("configId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("projectStartDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("schoolYearStartDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("schoolYearSemesterDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("schoolYearEndDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("h1Weeks", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("h2Weeks", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Schedule_ScheduleConfiguration (configId LONG not null primary key,projectStartDate DATE null,schoolYearStartDate DATE null,schoolYearSemesterDate DATE null,schoolYearEndDate DATE null,h1Weeks VARCHAR(75) null,h2Weeks VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table Schedule_ScheduleConfiguration";

	public static final String ORDER_BY_JPQL =
		" ORDER BY scheduleConfiguration.configId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Schedule_ScheduleConfiguration.configId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CONFIGID_COLUMN_BITMASK = 1L;

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

	public ScheduleConfigurationModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _configId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setConfigId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _configId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ScheduleConfiguration.class;
	}

	@Override
	public String getModelClassName() {
		return ScheduleConfiguration.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ScheduleConfiguration, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ScheduleConfiguration, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ScheduleConfiguration, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ScheduleConfiguration)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ScheduleConfiguration, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ScheduleConfiguration, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ScheduleConfiguration)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ScheduleConfiguration, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ScheduleConfiguration, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<ScheduleConfiguration, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ScheduleConfiguration, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ScheduleConfiguration, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<ScheduleConfiguration, Object>>();
		Map<String, BiConsumer<ScheduleConfiguration, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<ScheduleConfiguration, ?>>();

		attributeGetterFunctions.put(
			"configId", ScheduleConfiguration::getConfigId);
		attributeSetterBiConsumers.put(
			"configId",
			(BiConsumer<ScheduleConfiguration, Long>)
				ScheduleConfiguration::setConfigId);
		attributeGetterFunctions.put(
			"projectStartDate", ScheduleConfiguration::getProjectStartDate);
		attributeSetterBiConsumers.put(
			"projectStartDate",
			(BiConsumer<ScheduleConfiguration, Date>)
				ScheduleConfiguration::setProjectStartDate);
		attributeGetterFunctions.put(
			"schoolYearStartDate",
			ScheduleConfiguration::getSchoolYearStartDate);
		attributeSetterBiConsumers.put(
			"schoolYearStartDate",
			(BiConsumer<ScheduleConfiguration, Date>)
				ScheduleConfiguration::setSchoolYearStartDate);
		attributeGetterFunctions.put(
			"schoolYearSemesterDate",
			ScheduleConfiguration::getSchoolYearSemesterDate);
		attributeSetterBiConsumers.put(
			"schoolYearSemesterDate",
			(BiConsumer<ScheduleConfiguration, Date>)
				ScheduleConfiguration::setSchoolYearSemesterDate);
		attributeGetterFunctions.put(
			"schoolYearEndDate", ScheduleConfiguration::getSchoolYearEndDate);
		attributeSetterBiConsumers.put(
			"schoolYearEndDate",
			(BiConsumer<ScheduleConfiguration, Date>)
				ScheduleConfiguration::setSchoolYearEndDate);
		attributeGetterFunctions.put(
			"h1Weeks", ScheduleConfiguration::getH1Weeks);
		attributeSetterBiConsumers.put(
			"h1Weeks",
			(BiConsumer<ScheduleConfiguration, String>)
				ScheduleConfiguration::setH1Weeks);
		attributeGetterFunctions.put(
			"h2Weeks", ScheduleConfiguration::getH2Weeks);
		attributeSetterBiConsumers.put(
			"h2Weeks",
			(BiConsumer<ScheduleConfiguration, String>)
				ScheduleConfiguration::setH2Weeks);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getConfigId() {
		return _configId;
	}

	@Override
	public void setConfigId(long configId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_configId = configId;
	}

	@JSON
	@Override
	public Date getProjectStartDate() {
		return _projectStartDate;
	}

	@Override
	public void setProjectStartDate(Date projectStartDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_projectStartDate = projectStartDate;
	}

	@JSON
	@Override
	public Date getSchoolYearStartDate() {
		return _schoolYearStartDate;
	}

	@Override
	public void setSchoolYearStartDate(Date schoolYearStartDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_schoolYearStartDate = schoolYearStartDate;
	}

	@JSON
	@Override
	public Date getSchoolYearSemesterDate() {
		return _schoolYearSemesterDate;
	}

	@Override
	public void setSchoolYearSemesterDate(Date schoolYearSemesterDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_schoolYearSemesterDate = schoolYearSemesterDate;
	}

	@JSON
	@Override
	public Date getSchoolYearEndDate() {
		return _schoolYearEndDate;
	}

	@Override
	public void setSchoolYearEndDate(Date schoolYearEndDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_schoolYearEndDate = schoolYearEndDate;
	}

	@JSON
	@Override
	public String getH1Weeks() {
		if (_h1Weeks == null) {
			return "";
		}
		else {
			return _h1Weeks;
		}
	}

	@Override
	public void setH1Weeks(String h1Weeks) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_h1Weeks = h1Weeks;
	}

	@JSON
	@Override
	public String getH2Weeks() {
		if (_h2Weeks == null) {
			return "";
		}
		else {
			return _h2Weeks;
		}
	}

	@Override
	public void setH2Weeks(String h2Weeks) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_h2Weeks = h2Weeks;
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
			0, ScheduleConfiguration.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ScheduleConfiguration toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ScheduleConfiguration>
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
		ScheduleConfigurationImpl scheduleConfigurationImpl =
			new ScheduleConfigurationImpl();

		scheduleConfigurationImpl.setConfigId(getConfigId());
		scheduleConfigurationImpl.setProjectStartDate(getProjectStartDate());
		scheduleConfigurationImpl.setSchoolYearStartDate(
			getSchoolYearStartDate());
		scheduleConfigurationImpl.setSchoolYearSemesterDate(
			getSchoolYearSemesterDate());
		scheduleConfigurationImpl.setSchoolYearEndDate(getSchoolYearEndDate());
		scheduleConfigurationImpl.setH1Weeks(getH1Weeks());
		scheduleConfigurationImpl.setH2Weeks(getH2Weeks());

		scheduleConfigurationImpl.resetOriginalValues();

		return scheduleConfigurationImpl;
	}

	@Override
	public ScheduleConfiguration cloneWithOriginalValues() {
		ScheduleConfigurationImpl scheduleConfigurationImpl =
			new ScheduleConfigurationImpl();

		scheduleConfigurationImpl.setConfigId(
			this.<Long>getColumnOriginalValue("configId"));
		scheduleConfigurationImpl.setProjectStartDate(
			this.<Date>getColumnOriginalValue("projectStartDate"));
		scheduleConfigurationImpl.setSchoolYearStartDate(
			this.<Date>getColumnOriginalValue("schoolYearStartDate"));
		scheduleConfigurationImpl.setSchoolYearSemesterDate(
			this.<Date>getColumnOriginalValue("schoolYearSemesterDate"));
		scheduleConfigurationImpl.setSchoolYearEndDate(
			this.<Date>getColumnOriginalValue("schoolYearEndDate"));
		scheduleConfigurationImpl.setH1Weeks(
			this.<String>getColumnOriginalValue("h1Weeks"));
		scheduleConfigurationImpl.setH2Weeks(
			this.<String>getColumnOriginalValue("h2Weeks"));

		return scheduleConfigurationImpl;
	}

	@Override
	public int compareTo(ScheduleConfiguration scheduleConfiguration) {
		long primaryKey = scheduleConfiguration.getPrimaryKey();

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

		if (!(object instanceof ScheduleConfiguration)) {
			return false;
		}

		ScheduleConfiguration scheduleConfiguration =
			(ScheduleConfiguration)object;

		long primaryKey = scheduleConfiguration.getPrimaryKey();

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
	public CacheModel<ScheduleConfiguration> toCacheModel() {
		ScheduleConfigurationCacheModel scheduleConfigurationCacheModel =
			new ScheduleConfigurationCacheModel();

		scheduleConfigurationCacheModel.configId = getConfigId();

		Date projectStartDate = getProjectStartDate();

		if (projectStartDate != null) {
			scheduleConfigurationCacheModel.projectStartDate =
				projectStartDate.getTime();
		}
		else {
			scheduleConfigurationCacheModel.projectStartDate = Long.MIN_VALUE;
		}

		Date schoolYearStartDate = getSchoolYearStartDate();

		if (schoolYearStartDate != null) {
			scheduleConfigurationCacheModel.schoolYearStartDate =
				schoolYearStartDate.getTime();
		}
		else {
			scheduleConfigurationCacheModel.schoolYearStartDate =
				Long.MIN_VALUE;
		}

		Date schoolYearSemesterDate = getSchoolYearSemesterDate();

		if (schoolYearSemesterDate != null) {
			scheduleConfigurationCacheModel.schoolYearSemesterDate =
				schoolYearSemesterDate.getTime();
		}
		else {
			scheduleConfigurationCacheModel.schoolYearSemesterDate =
				Long.MIN_VALUE;
		}

		Date schoolYearEndDate = getSchoolYearEndDate();

		if (schoolYearEndDate != null) {
			scheduleConfigurationCacheModel.schoolYearEndDate =
				schoolYearEndDate.getTime();
		}
		else {
			scheduleConfigurationCacheModel.schoolYearEndDate = Long.MIN_VALUE;
		}

		scheduleConfigurationCacheModel.h1Weeks = getH1Weeks();

		String h1Weeks = scheduleConfigurationCacheModel.h1Weeks;

		if ((h1Weeks != null) && (h1Weeks.length() == 0)) {
			scheduleConfigurationCacheModel.h1Weeks = null;
		}

		scheduleConfigurationCacheModel.h2Weeks = getH2Weeks();

		String h2Weeks = scheduleConfigurationCacheModel.h2Weeks;

		if ((h2Weeks != null) && (h2Weeks.length() == 0)) {
			scheduleConfigurationCacheModel.h2Weeks = null;
		}

		return scheduleConfigurationCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ScheduleConfiguration, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ScheduleConfiguration, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ScheduleConfiguration, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(ScheduleConfiguration)this);

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

		private static final Function<InvocationHandler, ScheduleConfiguration>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					ScheduleConfiguration.class, ModelWrapper.class);

	}

	private long _configId;
	private Date _projectStartDate;
	private Date _schoolYearStartDate;
	private Date _schoolYearSemesterDate;
	private Date _schoolYearEndDate;
	private String _h1Weeks;
	private String _h2Weeks;

	public <T> T getColumnValue(String columnName) {
		Function<ScheduleConfiguration, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((ScheduleConfiguration)this);
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

		_columnOriginalValues.put("configId", _configId);
		_columnOriginalValues.put("projectStartDate", _projectStartDate);
		_columnOriginalValues.put("schoolYearStartDate", _schoolYearStartDate);
		_columnOriginalValues.put(
			"schoolYearSemesterDate", _schoolYearSemesterDate);
		_columnOriginalValues.put("schoolYearEndDate", _schoolYearEndDate);
		_columnOriginalValues.put("h1Weeks", _h1Weeks);
		_columnOriginalValues.put("h2Weeks", _h2Weeks);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("configId", 1L);

		columnBitmasks.put("projectStartDate", 2L);

		columnBitmasks.put("schoolYearStartDate", 4L);

		columnBitmasks.put("schoolYearSemesterDate", 8L);

		columnBitmasks.put("schoolYearEndDate", 16L);

		columnBitmasks.put("h1Weeks", 32L);

		columnBitmasks.put("h2Weeks", 64L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private ScheduleConfiguration _escapedModel;

}