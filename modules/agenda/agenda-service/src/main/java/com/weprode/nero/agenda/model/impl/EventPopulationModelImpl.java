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

package com.weprode.nero.agenda.model.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.nero.agenda.model.EventPopulation;
import com.weprode.nero.agenda.model.EventPopulationModel;
import com.weprode.nero.agenda.service.persistence.EventPopulationPK;

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
 * The base model implementation for the EventPopulation service. Represents a row in the &quot;Agenda_EventPopulation&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>EventPopulationModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link EventPopulationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EventPopulationImpl
 * @generated
 */
public class EventPopulationModelImpl
	extends BaseModelImpl<EventPopulation> implements EventPopulationModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a event population model instance should use the <code>EventPopulation</code> interface instead.
	 */
	public static final String TABLE_NAME = "Agenda_EventPopulation";

	public static final Object[][] TABLE_COLUMNS = {
		{"eventId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"roleId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("eventId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("roleId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Agenda_EventPopulation (eventId LONG not null,groupId LONG not null,roleId LONG not null,primary key (eventId, groupId, roleId))";

	public static final String TABLE_SQL_DROP =
		"drop table Agenda_EventPopulation";

	public static final String ORDER_BY_JPQL =
		" ORDER BY eventPopulation.id.eventId ASC, eventPopulation.id.groupId ASC, eventPopulation.id.roleId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Agenda_EventPopulation.eventId ASC, Agenda_EventPopulation.groupId ASC, Agenda_EventPopulation.roleId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long EVENTID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ROLEID_COLUMN_BITMASK = 4L;

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

	public EventPopulationModelImpl() {
	}

	@Override
	public EventPopulationPK getPrimaryKey() {
		return new EventPopulationPK(_eventId, _groupId, _roleId);
	}

	@Override
	public void setPrimaryKey(EventPopulationPK primaryKey) {
		setEventId(primaryKey.eventId);
		setGroupId(primaryKey.groupId);
		setRoleId(primaryKey.roleId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new EventPopulationPK(_eventId, _groupId, _roleId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((EventPopulationPK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return EventPopulation.class;
	}

	@Override
	public String getModelClassName() {
		return EventPopulation.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<EventPopulation, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<EventPopulation, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<EventPopulation, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((EventPopulation)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<EventPopulation, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<EventPopulation, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(EventPopulation)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<EventPopulation, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<EventPopulation, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<EventPopulation, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<EventPopulation, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<EventPopulation, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<EventPopulation, Object>>();
		Map<String, BiConsumer<EventPopulation, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<EventPopulation, ?>>();

		attributeGetterFunctions.put("eventId", EventPopulation::getEventId);
		attributeSetterBiConsumers.put(
			"eventId",
			(BiConsumer<EventPopulation, Long>)EventPopulation::setEventId);
		attributeGetterFunctions.put("groupId", EventPopulation::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<EventPopulation, Long>)EventPopulation::setGroupId);
		attributeGetterFunctions.put("roleId", EventPopulation::getRoleId);
		attributeSetterBiConsumers.put(
			"roleId",
			(BiConsumer<EventPopulation, Long>)EventPopulation::setRoleId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getEventId() {
		return _eventId;
	}

	@Override
	public void setEventId(long eventId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_eventId = eventId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalEventId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("eventId"));
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_groupId = groupId;
	}

	@Override
	public long getRoleId() {
		return _roleId;
	}

	@Override
	public void setRoleId(long roleId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_roleId = roleId;
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
	public EventPopulation toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, EventPopulation>
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
		EventPopulationImpl eventPopulationImpl = new EventPopulationImpl();

		eventPopulationImpl.setEventId(getEventId());
		eventPopulationImpl.setGroupId(getGroupId());
		eventPopulationImpl.setRoleId(getRoleId());

		eventPopulationImpl.resetOriginalValues();

		return eventPopulationImpl;
	}

	@Override
	public EventPopulation cloneWithOriginalValues() {
		EventPopulationImpl eventPopulationImpl = new EventPopulationImpl();

		eventPopulationImpl.setEventId(
			this.<Long>getColumnOriginalValue("eventId"));
		eventPopulationImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		eventPopulationImpl.setRoleId(
			this.<Long>getColumnOriginalValue("roleId"));

		return eventPopulationImpl;
	}

	@Override
	public int compareTo(EventPopulation eventPopulation) {
		EventPopulationPK primaryKey = eventPopulation.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EventPopulation)) {
			return false;
		}

		EventPopulation eventPopulation = (EventPopulation)object;

		EventPopulationPK primaryKey = eventPopulation.getPrimaryKey();

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
	public CacheModel<EventPopulation> toCacheModel() {
		EventPopulationCacheModel eventPopulationCacheModel =
			new EventPopulationCacheModel();

		eventPopulationCacheModel.eventPopulationPK = getPrimaryKey();

		eventPopulationCacheModel.eventId = getEventId();

		eventPopulationCacheModel.groupId = getGroupId();

		eventPopulationCacheModel.roleId = getRoleId();

		return eventPopulationCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<EventPopulation, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<EventPopulation, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<EventPopulation, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((EventPopulation)this);

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

		private static final Function<InvocationHandler, EventPopulation>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					EventPopulation.class, ModelWrapper.class);

	}

	private long _eventId;
	private long _groupId;
	private long _roleId;

	public <T> T getColumnValue(String columnName) {
		Function<EventPopulation, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((EventPopulation)this);
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

		_columnOriginalValues.put("eventId", _eventId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("roleId", _roleId);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("eventId", 1L);

		columnBitmasks.put("groupId", 2L);

		columnBitmasks.put("roleId", 4L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private EventPopulation _escapedModel;

}