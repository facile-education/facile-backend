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

package com.weprode.nero.school.life.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.nero.school.life.model.SchoollifeSession;
import com.weprode.nero.school.life.model.SchoollifeSessionModel;

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
 * The base model implementation for the SchoollifeSession service. Represents a row in the &quot;Schoollife_SchoollifeSession&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SchoollifeSessionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SchoollifeSessionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SchoollifeSessionImpl
 * @generated
 */
@JSON(strict = true)
public class SchoollifeSessionModelImpl
	extends BaseModelImpl<SchoollifeSession> implements SchoollifeSessionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a schoollife session model instance should use the <code>SchoollifeSession</code> interface instead.
	 */
	public static final String TABLE_NAME = "Schoollife_SchoollifeSession";

	public static final Object[][] TABLE_COLUMNS = {
		{"schoollifeSessionId", Types.BIGINT},
		{"schoollifeSlotId", Types.BIGINT}, {"schoolId", Types.BIGINT},
		{"type_", Types.INTEGER}, {"weekNb", Types.INTEGER},
		{"startDate", Types.TIMESTAMP}, {"endDate", Types.TIMESTAMP},
		{"rollCalled", Types.BOOLEAN},
		{"absenceNotificationSent", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("schoollifeSessionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("schoollifeSlotId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("schoolId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("weekNb", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("startDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("endDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("rollCalled", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("absenceNotificationSent", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Schoollife_SchoollifeSession (schoollifeSessionId LONG not null primary key,schoollifeSlotId LONG,schoolId LONG,type_ INTEGER,weekNb INTEGER,startDate DATE null,endDate DATE null,rollCalled BOOLEAN,absenceNotificationSent BOOLEAN)";

	public static final String TABLE_SQL_DROP =
		"drop table Schoollife_SchoollifeSession";

	public static final String ORDER_BY_JPQL =
		" ORDER BY schoollifeSession.schoollifeSessionId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Schoollife_SchoollifeSession.schoollifeSessionId ASC";

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
	public static final long SCHOOLLIFESLOTID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TYPE_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SCHOOLLIFESESSIONID_COLUMN_BITMASK = 8L;

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

	public SchoollifeSessionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _schoollifeSessionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSchoollifeSessionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _schoollifeSessionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SchoollifeSession.class;
	}

	@Override
	public String getModelClassName() {
		return SchoollifeSession.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SchoollifeSession, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SchoollifeSession, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SchoollifeSession, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((SchoollifeSession)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SchoollifeSession, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SchoollifeSession, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SchoollifeSession)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SchoollifeSession, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SchoollifeSession, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<SchoollifeSession, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<SchoollifeSession, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<SchoollifeSession, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<SchoollifeSession, Object>>();
		Map<String, BiConsumer<SchoollifeSession, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<SchoollifeSession, ?>>();

		attributeGetterFunctions.put(
			"schoollifeSessionId", SchoollifeSession::getSchoollifeSessionId);
		attributeSetterBiConsumers.put(
			"schoollifeSessionId",
			(BiConsumer<SchoollifeSession, Long>)
				SchoollifeSession::setSchoollifeSessionId);
		attributeGetterFunctions.put(
			"schoollifeSlotId", SchoollifeSession::getSchoollifeSlotId);
		attributeSetterBiConsumers.put(
			"schoollifeSlotId",
			(BiConsumer<SchoollifeSession, Long>)
				SchoollifeSession::setSchoollifeSlotId);
		attributeGetterFunctions.put(
			"schoolId", SchoollifeSession::getSchoolId);
		attributeSetterBiConsumers.put(
			"schoolId",
			(BiConsumer<SchoollifeSession, Long>)
				SchoollifeSession::setSchoolId);
		attributeGetterFunctions.put("type", SchoollifeSession::getType);
		attributeSetterBiConsumers.put(
			"type",
			(BiConsumer<SchoollifeSession, Integer>)SchoollifeSession::setType);
		attributeGetterFunctions.put("weekNb", SchoollifeSession::getWeekNb);
		attributeSetterBiConsumers.put(
			"weekNb",
			(BiConsumer<SchoollifeSession, Integer>)
				SchoollifeSession::setWeekNb);
		attributeGetterFunctions.put(
			"startDate", SchoollifeSession::getStartDate);
		attributeSetterBiConsumers.put(
			"startDate",
			(BiConsumer<SchoollifeSession, Date>)
				SchoollifeSession::setStartDate);
		attributeGetterFunctions.put("endDate", SchoollifeSession::getEndDate);
		attributeSetterBiConsumers.put(
			"endDate",
			(BiConsumer<SchoollifeSession, Date>)SchoollifeSession::setEndDate);
		attributeGetterFunctions.put(
			"rollCalled", SchoollifeSession::getRollCalled);
		attributeSetterBiConsumers.put(
			"rollCalled",
			(BiConsumer<SchoollifeSession, Boolean>)
				SchoollifeSession::setRollCalled);
		attributeGetterFunctions.put(
			"absenceNotificationSent",
			SchoollifeSession::getAbsenceNotificationSent);
		attributeSetterBiConsumers.put(
			"absenceNotificationSent",
			(BiConsumer<SchoollifeSession, Boolean>)
				SchoollifeSession::setAbsenceNotificationSent);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getSchoollifeSessionId() {
		return _schoollifeSessionId;
	}

	@Override
	public void setSchoollifeSessionId(long schoollifeSessionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_schoollifeSessionId = schoollifeSessionId;
	}

	@JSON
	@Override
	public long getSchoollifeSlotId() {
		return _schoollifeSlotId;
	}

	@Override
	public void setSchoollifeSlotId(long schoollifeSlotId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_schoollifeSlotId = schoollifeSlotId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalSchoollifeSlotId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("schoollifeSlotId"));
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
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_type = type;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalType() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("type_"));
	}

	@JSON
	@Override
	public int getWeekNb() {
		return _weekNb;
	}

	@Override
	public void setWeekNb(int weekNb) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_weekNb = weekNb;
	}

	@JSON
	@Override
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_startDate = startDate;
	}

	@JSON
	@Override
	public Date getEndDate() {
		return _endDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_endDate = endDate;
	}

	@JSON
	@Override
	public boolean getRollCalled() {
		return _rollCalled;
	}

	@JSON
	@Override
	public boolean isRollCalled() {
		return _rollCalled;
	}

	@Override
	public void setRollCalled(boolean rollCalled) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_rollCalled = rollCalled;
	}

	@JSON
	@Override
	public boolean getAbsenceNotificationSent() {
		return _absenceNotificationSent;
	}

	@JSON
	@Override
	public boolean isAbsenceNotificationSent() {
		return _absenceNotificationSent;
	}

	@Override
	public void setAbsenceNotificationSent(boolean absenceNotificationSent) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_absenceNotificationSent = absenceNotificationSent;
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
			0, SchoollifeSession.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SchoollifeSession toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SchoollifeSession>
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
		SchoollifeSessionImpl schoollifeSessionImpl =
			new SchoollifeSessionImpl();

		schoollifeSessionImpl.setSchoollifeSessionId(getSchoollifeSessionId());
		schoollifeSessionImpl.setSchoollifeSlotId(getSchoollifeSlotId());
		schoollifeSessionImpl.setSchoolId(getSchoolId());
		schoollifeSessionImpl.setType(getType());
		schoollifeSessionImpl.setWeekNb(getWeekNb());
		schoollifeSessionImpl.setStartDate(getStartDate());
		schoollifeSessionImpl.setEndDate(getEndDate());
		schoollifeSessionImpl.setRollCalled(isRollCalled());
		schoollifeSessionImpl.setAbsenceNotificationSent(
			isAbsenceNotificationSent());

		schoollifeSessionImpl.resetOriginalValues();

		return schoollifeSessionImpl;
	}

	@Override
	public SchoollifeSession cloneWithOriginalValues() {
		SchoollifeSessionImpl schoollifeSessionImpl =
			new SchoollifeSessionImpl();

		schoollifeSessionImpl.setSchoollifeSessionId(
			this.<Long>getColumnOriginalValue("schoollifeSessionId"));
		schoollifeSessionImpl.setSchoollifeSlotId(
			this.<Long>getColumnOriginalValue("schoollifeSlotId"));
		schoollifeSessionImpl.setSchoolId(
			this.<Long>getColumnOriginalValue("schoolId"));
		schoollifeSessionImpl.setType(
			this.<Integer>getColumnOriginalValue("type_"));
		schoollifeSessionImpl.setWeekNb(
			this.<Integer>getColumnOriginalValue("weekNb"));
		schoollifeSessionImpl.setStartDate(
			this.<Date>getColumnOriginalValue("startDate"));
		schoollifeSessionImpl.setEndDate(
			this.<Date>getColumnOriginalValue("endDate"));
		schoollifeSessionImpl.setRollCalled(
			this.<Boolean>getColumnOriginalValue("rollCalled"));
		schoollifeSessionImpl.setAbsenceNotificationSent(
			this.<Boolean>getColumnOriginalValue("absenceNotificationSent"));

		return schoollifeSessionImpl;
	}

	@Override
	public int compareTo(SchoollifeSession schoollifeSession) {
		long primaryKey = schoollifeSession.getPrimaryKey();

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

		if (!(object instanceof SchoollifeSession)) {
			return false;
		}

		SchoollifeSession schoollifeSession = (SchoollifeSession)object;

		long primaryKey = schoollifeSession.getPrimaryKey();

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
	public CacheModel<SchoollifeSession> toCacheModel() {
		SchoollifeSessionCacheModel schoollifeSessionCacheModel =
			new SchoollifeSessionCacheModel();

		schoollifeSessionCacheModel.schoollifeSessionId =
			getSchoollifeSessionId();

		schoollifeSessionCacheModel.schoollifeSlotId = getSchoollifeSlotId();

		schoollifeSessionCacheModel.schoolId = getSchoolId();

		schoollifeSessionCacheModel.type = getType();

		schoollifeSessionCacheModel.weekNb = getWeekNb();

		Date startDate = getStartDate();

		if (startDate != null) {
			schoollifeSessionCacheModel.startDate = startDate.getTime();
		}
		else {
			schoollifeSessionCacheModel.startDate = Long.MIN_VALUE;
		}

		Date endDate = getEndDate();

		if (endDate != null) {
			schoollifeSessionCacheModel.endDate = endDate.getTime();
		}
		else {
			schoollifeSessionCacheModel.endDate = Long.MIN_VALUE;
		}

		schoollifeSessionCacheModel.rollCalled = isRollCalled();

		schoollifeSessionCacheModel.absenceNotificationSent =
			isAbsenceNotificationSent();

		return schoollifeSessionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SchoollifeSession, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SchoollifeSession, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SchoollifeSession, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(SchoollifeSession)this);

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

		private static final Function<InvocationHandler, SchoollifeSession>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					SchoollifeSession.class, ModelWrapper.class);

	}

	private long _schoollifeSessionId;
	private long _schoollifeSlotId;
	private long _schoolId;
	private int _type;
	private int _weekNb;
	private Date _startDate;
	private Date _endDate;
	private boolean _rollCalled;
	private boolean _absenceNotificationSent;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<SchoollifeSession, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((SchoollifeSession)this);
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

		_columnOriginalValues.put("schoollifeSessionId", _schoollifeSessionId);
		_columnOriginalValues.put("schoollifeSlotId", _schoollifeSlotId);
		_columnOriginalValues.put("schoolId", _schoolId);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("weekNb", _weekNb);
		_columnOriginalValues.put("startDate", _startDate);
		_columnOriginalValues.put("endDate", _endDate);
		_columnOriginalValues.put("rollCalled", _rollCalled);
		_columnOriginalValues.put(
			"absenceNotificationSent", _absenceNotificationSent);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("type_", "type");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("schoollifeSessionId", 1L);

		columnBitmasks.put("schoollifeSlotId", 2L);

		columnBitmasks.put("schoolId", 4L);

		columnBitmasks.put("type_", 8L);

		columnBitmasks.put("weekNb", 16L);

		columnBitmasks.put("startDate", 32L);

		columnBitmasks.put("endDate", 64L);

		columnBitmasks.put("rollCalled", 128L);

		columnBitmasks.put("absenceNotificationSent", 256L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private SchoollifeSession _escapedModel;

}