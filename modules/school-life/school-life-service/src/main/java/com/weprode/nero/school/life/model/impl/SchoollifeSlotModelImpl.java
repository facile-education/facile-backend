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

import com.weprode.nero.school.life.model.SchoollifeSlot;
import com.weprode.nero.school.life.model.SchoollifeSlotModel;
import com.weprode.nero.school.life.model.SchoollifeSlotSoap;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the SchoollifeSlot service. Represents a row in the &quot;Schoollife_SchoollifeSlot&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SchoollifeSlotModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SchoollifeSlotImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SchoollifeSlotImpl
 * @generated
 */
@JSON(strict = true)
public class SchoollifeSlotModelImpl
	extends BaseModelImpl<SchoollifeSlot> implements SchoollifeSlotModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a schoollife slot model instance should use the <code>SchoollifeSlot</code> interface instead.
	 */
	public static final String TABLE_NAME = "Schoollife_SchoollifeSlot";

	public static final Object[][] TABLE_COLUMNS = {
		{"schoollifeSlotId", Types.BIGINT}, {"schoolId", Types.BIGINT},
		{"day", Types.INTEGER}, {"startHour", Types.VARCHAR},
		{"endHour", Types.VARCHAR}, {"teacherId", Types.BIGINT},
		{"type_", Types.INTEGER}, {"room", Types.VARCHAR},
		{"capacity", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("schoollifeSlotId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("schoolId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("day", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("startHour", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("endHour", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("teacherId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("room", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("capacity", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Schoollife_SchoollifeSlot (schoollifeSlotId LONG not null primary key,schoolId LONG,day INTEGER,startHour VARCHAR(75) null,endHour VARCHAR(75) null,teacherId LONG,type_ INTEGER,room VARCHAR(75) null,capacity INTEGER)";

	public static final String TABLE_SQL_DROP =
		"drop table Schoollife_SchoollifeSlot";

	public static final String ORDER_BY_JPQL =
		" ORDER BY schoollifeSlot.schoollifeSlotId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Schoollife_SchoollifeSlot.schoollifeSlotId ASC";

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
	public static final long TEACHERID_COLUMN_BITMASK = 2L;

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
	public static final long SCHOOLLIFESLOTID_COLUMN_BITMASK = 8L;

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

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static SchoollifeSlot toModel(SchoollifeSlotSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		SchoollifeSlot model = new SchoollifeSlotImpl();

		model.setSchoollifeSlotId(soapModel.getSchoollifeSlotId());
		model.setSchoolId(soapModel.getSchoolId());
		model.setDay(soapModel.getDay());
		model.setStartHour(soapModel.getStartHour());
		model.setEndHour(soapModel.getEndHour());
		model.setTeacherId(soapModel.getTeacherId());
		model.setType(soapModel.getType());
		model.setRoom(soapModel.getRoom());
		model.setCapacity(soapModel.getCapacity());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static List<SchoollifeSlot> toModels(
		SchoollifeSlotSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<SchoollifeSlot> models = new ArrayList<SchoollifeSlot>(
			soapModels.length);

		for (SchoollifeSlotSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public SchoollifeSlotModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _schoollifeSlotId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSchoollifeSlotId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _schoollifeSlotId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SchoollifeSlot.class;
	}

	@Override
	public String getModelClassName() {
		return SchoollifeSlot.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SchoollifeSlot, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SchoollifeSlot, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SchoollifeSlot, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((SchoollifeSlot)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SchoollifeSlot, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SchoollifeSlot, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SchoollifeSlot)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SchoollifeSlot, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SchoollifeSlot, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, SchoollifeSlot>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			SchoollifeSlot.class.getClassLoader(), SchoollifeSlot.class,
			ModelWrapper.class);

		try {
			Constructor<SchoollifeSlot> constructor =
				(Constructor<SchoollifeSlot>)proxyClass.getConstructor(
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

	private static final Map<String, Function<SchoollifeSlot, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<SchoollifeSlot, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<SchoollifeSlot, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<SchoollifeSlot, Object>>();
		Map<String, BiConsumer<SchoollifeSlot, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<SchoollifeSlot, ?>>();

		attributeGetterFunctions.put(
			"schoollifeSlotId", SchoollifeSlot::getSchoollifeSlotId);
		attributeSetterBiConsumers.put(
			"schoollifeSlotId",
			(BiConsumer<SchoollifeSlot, Long>)
				SchoollifeSlot::setSchoollifeSlotId);
		attributeGetterFunctions.put("schoolId", SchoollifeSlot::getSchoolId);
		attributeSetterBiConsumers.put(
			"schoolId",
			(BiConsumer<SchoollifeSlot, Long>)SchoollifeSlot::setSchoolId);
		attributeGetterFunctions.put("day", SchoollifeSlot::getDay);
		attributeSetterBiConsumers.put(
			"day", (BiConsumer<SchoollifeSlot, Integer>)SchoollifeSlot::setDay);
		attributeGetterFunctions.put("startHour", SchoollifeSlot::getStartHour);
		attributeSetterBiConsumers.put(
			"startHour",
			(BiConsumer<SchoollifeSlot, String>)SchoollifeSlot::setStartHour);
		attributeGetterFunctions.put("endHour", SchoollifeSlot::getEndHour);
		attributeSetterBiConsumers.put(
			"endHour",
			(BiConsumer<SchoollifeSlot, String>)SchoollifeSlot::setEndHour);
		attributeGetterFunctions.put("teacherId", SchoollifeSlot::getTeacherId);
		attributeSetterBiConsumers.put(
			"teacherId",
			(BiConsumer<SchoollifeSlot, Long>)SchoollifeSlot::setTeacherId);
		attributeGetterFunctions.put("type", SchoollifeSlot::getType);
		attributeSetterBiConsumers.put(
			"type",
			(BiConsumer<SchoollifeSlot, Integer>)SchoollifeSlot::setType);
		attributeGetterFunctions.put("room", SchoollifeSlot::getRoom);
		attributeSetterBiConsumers.put(
			"room",
			(BiConsumer<SchoollifeSlot, String>)SchoollifeSlot::setRoom);
		attributeGetterFunctions.put("capacity", SchoollifeSlot::getCapacity);
		attributeSetterBiConsumers.put(
			"capacity",
			(BiConsumer<SchoollifeSlot, Integer>)SchoollifeSlot::setCapacity);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
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
	public int getDay() {
		return _day;
	}

	@Override
	public void setDay(int day) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_day = day;
	}

	@JSON
	@Override
	public String getStartHour() {
		if (_startHour == null) {
			return "";
		}
		else {
			return _startHour;
		}
	}

	@Override
	public void setStartHour(String startHour) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_startHour = startHour;
	}

	@JSON
	@Override
	public String getEndHour() {
		if (_endHour == null) {
			return "";
		}
		else {
			return _endHour;
		}
	}

	@Override
	public void setEndHour(String endHour) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_endHour = endHour;
	}

	@JSON
	@Override
	public long getTeacherId() {
		return _teacherId;
	}

	@Override
	public void setTeacherId(long teacherId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_teacherId = teacherId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalTeacherId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("teacherId"));
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
	public String getRoom() {
		if (_room == null) {
			return "";
		}
		else {
			return _room;
		}
	}

	@Override
	public void setRoom(String room) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_room = room;
	}

	@JSON
	@Override
	public int getCapacity() {
		return _capacity;
	}

	@Override
	public void setCapacity(int capacity) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_capacity = capacity;
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
			0, SchoollifeSlot.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SchoollifeSlot toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SchoollifeSlot>
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
		SchoollifeSlotImpl schoollifeSlotImpl = new SchoollifeSlotImpl();

		schoollifeSlotImpl.setSchoollifeSlotId(getSchoollifeSlotId());
		schoollifeSlotImpl.setSchoolId(getSchoolId());
		schoollifeSlotImpl.setDay(getDay());
		schoollifeSlotImpl.setStartHour(getStartHour());
		schoollifeSlotImpl.setEndHour(getEndHour());
		schoollifeSlotImpl.setTeacherId(getTeacherId());
		schoollifeSlotImpl.setType(getType());
		schoollifeSlotImpl.setRoom(getRoom());
		schoollifeSlotImpl.setCapacity(getCapacity());

		schoollifeSlotImpl.resetOriginalValues();

		return schoollifeSlotImpl;
	}

	@Override
	public SchoollifeSlot cloneWithOriginalValues() {
		SchoollifeSlotImpl schoollifeSlotImpl = new SchoollifeSlotImpl();

		schoollifeSlotImpl.setSchoollifeSlotId(
			this.<Long>getColumnOriginalValue("schoollifeSlotId"));
		schoollifeSlotImpl.setSchoolId(
			this.<Long>getColumnOriginalValue("schoolId"));
		schoollifeSlotImpl.setDay(this.<Integer>getColumnOriginalValue("day"));
		schoollifeSlotImpl.setStartHour(
			this.<String>getColumnOriginalValue("startHour"));
		schoollifeSlotImpl.setEndHour(
			this.<String>getColumnOriginalValue("endHour"));
		schoollifeSlotImpl.setTeacherId(
			this.<Long>getColumnOriginalValue("teacherId"));
		schoollifeSlotImpl.setType(
			this.<Integer>getColumnOriginalValue("type_"));
		schoollifeSlotImpl.setRoom(this.<String>getColumnOriginalValue("room"));
		schoollifeSlotImpl.setCapacity(
			this.<Integer>getColumnOriginalValue("capacity"));

		return schoollifeSlotImpl;
	}

	@Override
	public int compareTo(SchoollifeSlot schoollifeSlot) {
		long primaryKey = schoollifeSlot.getPrimaryKey();

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

		if (!(object instanceof SchoollifeSlot)) {
			return false;
		}

		SchoollifeSlot schoollifeSlot = (SchoollifeSlot)object;

		long primaryKey = schoollifeSlot.getPrimaryKey();

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
	public CacheModel<SchoollifeSlot> toCacheModel() {
		SchoollifeSlotCacheModel schoollifeSlotCacheModel =
			new SchoollifeSlotCacheModel();

		schoollifeSlotCacheModel.schoollifeSlotId = getSchoollifeSlotId();

		schoollifeSlotCacheModel.schoolId = getSchoolId();

		schoollifeSlotCacheModel.day = getDay();

		schoollifeSlotCacheModel.startHour = getStartHour();

		String startHour = schoollifeSlotCacheModel.startHour;

		if ((startHour != null) && (startHour.length() == 0)) {
			schoollifeSlotCacheModel.startHour = null;
		}

		schoollifeSlotCacheModel.endHour = getEndHour();

		String endHour = schoollifeSlotCacheModel.endHour;

		if ((endHour != null) && (endHour.length() == 0)) {
			schoollifeSlotCacheModel.endHour = null;
		}

		schoollifeSlotCacheModel.teacherId = getTeacherId();

		schoollifeSlotCacheModel.type = getType();

		schoollifeSlotCacheModel.room = getRoom();

		String room = schoollifeSlotCacheModel.room;

		if ((room != null) && (room.length() == 0)) {
			schoollifeSlotCacheModel.room = null;
		}

		schoollifeSlotCacheModel.capacity = getCapacity();

		return schoollifeSlotCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SchoollifeSlot, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SchoollifeSlot, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SchoollifeSlot, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((SchoollifeSlot)this);

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
		Map<String, Function<SchoollifeSlot, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<SchoollifeSlot, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SchoollifeSlot, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((SchoollifeSlot)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, SchoollifeSlot>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _schoollifeSlotId;
	private long _schoolId;
	private int _day;
	private String _startHour;
	private String _endHour;
	private long _teacherId;
	private int _type;
	private String _room;
	private int _capacity;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<SchoollifeSlot, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((SchoollifeSlot)this);
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

		_columnOriginalValues.put("schoollifeSlotId", _schoollifeSlotId);
		_columnOriginalValues.put("schoolId", _schoolId);
		_columnOriginalValues.put("day", _day);
		_columnOriginalValues.put("startHour", _startHour);
		_columnOriginalValues.put("endHour", _endHour);
		_columnOriginalValues.put("teacherId", _teacherId);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("room", _room);
		_columnOriginalValues.put("capacity", _capacity);
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

		columnBitmasks.put("schoollifeSlotId", 1L);

		columnBitmasks.put("schoolId", 2L);

		columnBitmasks.put("day", 4L);

		columnBitmasks.put("startHour", 8L);

		columnBitmasks.put("endHour", 16L);

		columnBitmasks.put("teacherId", 32L);

		columnBitmasks.put("type_", 64L);

		columnBitmasks.put("room", 128L);

		columnBitmasks.put("capacity", 256L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private SchoollifeSlot _escapedModel;

}