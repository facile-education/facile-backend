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

package com.weprode.nero.schedule.model.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.nero.schedule.model.DailySchedule;
import com.weprode.nero.schedule.model.DailyScheduleModel;
import com.weprode.nero.schedule.service.persistence.DailySchedulePK;

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
 * The base model implementation for the DailySchedule service. Represents a row in the &quot;Schedule_DailySchedule&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>DailyScheduleModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DailyScheduleImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DailyScheduleImpl
 * @generated
 */
public class DailyScheduleModelImpl
	extends BaseModelImpl<DailySchedule> implements DailyScheduleModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a daily schedule model instance should use the <code>DailySchedule</code> interface instead.
	 */
	public static final String TABLE_NAME = "Schedule_DailySchedule";

	public static final Object[][] TABLE_COLUMNS = {
		{"schoolId", Types.BIGINT}, {"sessionId", Types.INTEGER},
		{"sessionStartHour", Types.VARCHAR}, {"sessionEndHour", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("schoolId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("sessionId", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("sessionStartHour", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("sessionEndHour", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Schedule_DailySchedule (schoolId LONG not null,sessionId INTEGER not null,sessionStartHour VARCHAR(75) null,sessionEndHour VARCHAR(75) null,primary key (schoolId, sessionId))";

	public static final String TABLE_SQL_DROP =
		"drop table Schedule_DailySchedule";

	public static final String ORDER_BY_JPQL =
		" ORDER BY dailySchedule.id.schoolId ASC, dailySchedule.id.sessionId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Schedule_DailySchedule.schoolId ASC, Schedule_DailySchedule.sessionId ASC";

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
	public static final long SESSIONID_COLUMN_BITMASK = 2L;

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

	public DailyScheduleModelImpl() {
	}

	@Override
	public DailySchedulePK getPrimaryKey() {
		return new DailySchedulePK(_schoolId, _sessionId);
	}

	@Override
	public void setPrimaryKey(DailySchedulePK primaryKey) {
		setSchoolId(primaryKey.schoolId);
		setSessionId(primaryKey.sessionId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new DailySchedulePK(_schoolId, _sessionId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((DailySchedulePK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return DailySchedule.class;
	}

	@Override
	public String getModelClassName() {
		return DailySchedule.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<DailySchedule, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<DailySchedule, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DailySchedule, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((DailySchedule)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<DailySchedule, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<DailySchedule, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(DailySchedule)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<DailySchedule, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<DailySchedule, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, DailySchedule>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			DailySchedule.class.getClassLoader(), DailySchedule.class,
			ModelWrapper.class);

		try {
			Constructor<DailySchedule> constructor =
				(Constructor<DailySchedule>)proxyClass.getConstructor(
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

	private static final Map<String, Function<DailySchedule, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<DailySchedule, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<DailySchedule, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<DailySchedule, Object>>();
		Map<String, BiConsumer<DailySchedule, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<DailySchedule, ?>>();

		attributeGetterFunctions.put("schoolId", DailySchedule::getSchoolId);
		attributeSetterBiConsumers.put(
			"schoolId",
			(BiConsumer<DailySchedule, Long>)DailySchedule::setSchoolId);
		attributeGetterFunctions.put("sessionId", DailySchedule::getSessionId);
		attributeSetterBiConsumers.put(
			"sessionId",
			(BiConsumer<DailySchedule, Integer>)DailySchedule::setSessionId);
		attributeGetterFunctions.put(
			"sessionStartHour", DailySchedule::getSessionStartHour);
		attributeSetterBiConsumers.put(
			"sessionStartHour",
			(BiConsumer<DailySchedule, String>)
				DailySchedule::setSessionStartHour);
		attributeGetterFunctions.put(
			"sessionEndHour", DailySchedule::getSessionEndHour);
		attributeSetterBiConsumers.put(
			"sessionEndHour",
			(BiConsumer<DailySchedule, String>)
				DailySchedule::setSessionEndHour);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
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
	public int getSessionId() {
		return _sessionId;
	}

	@Override
	public void setSessionId(int sessionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sessionId = sessionId;
	}

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
	public DailySchedule toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, DailySchedule>
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
		DailyScheduleImpl dailyScheduleImpl = new DailyScheduleImpl();

		dailyScheduleImpl.setSchoolId(getSchoolId());
		dailyScheduleImpl.setSessionId(getSessionId());
		dailyScheduleImpl.setSessionStartHour(getSessionStartHour());
		dailyScheduleImpl.setSessionEndHour(getSessionEndHour());

		dailyScheduleImpl.resetOriginalValues();

		return dailyScheduleImpl;
	}

	@Override
	public DailySchedule cloneWithOriginalValues() {
		DailyScheduleImpl dailyScheduleImpl = new DailyScheduleImpl();

		dailyScheduleImpl.setSchoolId(
			this.<Long>getColumnOriginalValue("schoolId"));
		dailyScheduleImpl.setSessionId(
			this.<Integer>getColumnOriginalValue("sessionId"));
		dailyScheduleImpl.setSessionStartHour(
			this.<String>getColumnOriginalValue("sessionStartHour"));
		dailyScheduleImpl.setSessionEndHour(
			this.<String>getColumnOriginalValue("sessionEndHour"));

		return dailyScheduleImpl;
	}

	@Override
	public int compareTo(DailySchedule dailySchedule) {
		DailySchedulePK primaryKey = dailySchedule.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DailySchedule)) {
			return false;
		}

		DailySchedule dailySchedule = (DailySchedule)object;

		DailySchedulePK primaryKey = dailySchedule.getPrimaryKey();

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
	public CacheModel<DailySchedule> toCacheModel() {
		DailyScheduleCacheModel dailyScheduleCacheModel =
			new DailyScheduleCacheModel();

		dailyScheduleCacheModel.dailySchedulePK = getPrimaryKey();

		dailyScheduleCacheModel.schoolId = getSchoolId();

		dailyScheduleCacheModel.sessionId = getSessionId();

		dailyScheduleCacheModel.sessionStartHour = getSessionStartHour();

		String sessionStartHour = dailyScheduleCacheModel.sessionStartHour;

		if ((sessionStartHour != null) && (sessionStartHour.length() == 0)) {
			dailyScheduleCacheModel.sessionStartHour = null;
		}

		dailyScheduleCacheModel.sessionEndHour = getSessionEndHour();

		String sessionEndHour = dailyScheduleCacheModel.sessionEndHour;

		if ((sessionEndHour != null) && (sessionEndHour.length() == 0)) {
			dailyScheduleCacheModel.sessionEndHour = null;
		}

		return dailyScheduleCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<DailySchedule, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<DailySchedule, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DailySchedule, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((DailySchedule)this);

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
		Map<String, Function<DailySchedule, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<DailySchedule, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DailySchedule, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((DailySchedule)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, DailySchedule>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _schoolId;
	private int _sessionId;
	private String _sessionStartHour;
	private String _sessionEndHour;

	public <T> T getColumnValue(String columnName) {
		Function<DailySchedule, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((DailySchedule)this);
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
		_columnOriginalValues.put("sessionId", _sessionId);
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

		columnBitmasks.put("sessionId", 2L);

		columnBitmasks.put("sessionStartHour", 4L);

		columnBitmasks.put("sessionEndHour", 8L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private DailySchedule _escapedModel;

}