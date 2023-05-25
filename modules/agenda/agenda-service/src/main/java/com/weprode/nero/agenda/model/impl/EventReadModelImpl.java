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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.nero.agenda.model.EventRead;
import com.weprode.nero.agenda.model.EventReadModel;
import com.weprode.nero.agenda.service.persistence.EventReadPK;

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
 * The base model implementation for the EventRead service. Represents a row in the &quot;Agenda_EventRead&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>EventReadModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link EventReadImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EventReadImpl
 * @generated
 */
public class EventReadModelImpl
	extends BaseModelImpl<EventRead> implements EventReadModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a event read model instance should use the <code>EventRead</code> interface instead.
	 */
	public static final String TABLE_NAME = "Agenda_EventRead";

	public static final Object[][] TABLE_COLUMNS = {
		{"eventId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"readDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("eventId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("readDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Agenda_EventRead (eventId LONG not null,userId LONG not null,readDate DATE null,primary key (eventId, userId))";

	public static final String TABLE_SQL_DROP = "drop table Agenda_EventRead";

	public static final String ORDER_BY_JPQL =
		" ORDER BY eventRead.id.eventId ASC, eventRead.id.userId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Agenda_EventRead.eventId ASC, Agenda_EventRead.userId ASC";

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
	public static final long USERID_COLUMN_BITMASK = 2L;

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

	public EventReadModelImpl() {
	}

	@Override
	public EventReadPK getPrimaryKey() {
		return new EventReadPK(_eventId, _userId);
	}

	@Override
	public void setPrimaryKey(EventReadPK primaryKey) {
		setEventId(primaryKey.eventId);
		setUserId(primaryKey.userId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new EventReadPK(_eventId, _userId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((EventReadPK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return EventRead.class;
	}

	@Override
	public String getModelClassName() {
		return EventRead.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<EventRead, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<EventRead, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<EventRead, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((EventRead)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<EventRead, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<EventRead, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(EventRead)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<EventRead, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<EventRead, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, EventRead>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			EventRead.class.getClassLoader(), EventRead.class,
			ModelWrapper.class);

		try {
			Constructor<EventRead> constructor =
				(Constructor<EventRead>)proxyClass.getConstructor(
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

	private static final Map<String, Function<EventRead, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<EventRead, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<EventRead, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<EventRead, Object>>();
		Map<String, BiConsumer<EventRead, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<EventRead, ?>>();

		attributeGetterFunctions.put("eventId", EventRead::getEventId);
		attributeSetterBiConsumers.put(
			"eventId", (BiConsumer<EventRead, Long>)EventRead::setEventId);
		attributeGetterFunctions.put("userId", EventRead::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<EventRead, Long>)EventRead::setUserId);
		attributeGetterFunctions.put("readDate", EventRead::getReadDate);
		attributeSetterBiConsumers.put(
			"readDate", (BiConsumer<EventRead, Date>)EventRead::setReadDate);

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
	public Date getReadDate() {
		return _readDate;
	}

	@Override
	public void setReadDate(Date readDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_readDate = readDate;
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
	public EventRead toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, EventRead>
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
		EventReadImpl eventReadImpl = new EventReadImpl();

		eventReadImpl.setEventId(getEventId());
		eventReadImpl.setUserId(getUserId());
		eventReadImpl.setReadDate(getReadDate());

		eventReadImpl.resetOriginalValues();

		return eventReadImpl;
	}

	@Override
	public EventRead cloneWithOriginalValues() {
		EventReadImpl eventReadImpl = new EventReadImpl();

		eventReadImpl.setEventId(this.<Long>getColumnOriginalValue("eventId"));
		eventReadImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		eventReadImpl.setReadDate(
			this.<Date>getColumnOriginalValue("readDate"));

		return eventReadImpl;
	}

	@Override
	public int compareTo(EventRead eventRead) {
		EventReadPK primaryKey = eventRead.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EventRead)) {
			return false;
		}

		EventRead eventRead = (EventRead)object;

		EventReadPK primaryKey = eventRead.getPrimaryKey();

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
	public CacheModel<EventRead> toCacheModel() {
		EventReadCacheModel eventReadCacheModel = new EventReadCacheModel();

		eventReadCacheModel.eventReadPK = getPrimaryKey();

		eventReadCacheModel.eventId = getEventId();

		eventReadCacheModel.userId = getUserId();

		Date readDate = getReadDate();

		if (readDate != null) {
			eventReadCacheModel.readDate = readDate.getTime();
		}
		else {
			eventReadCacheModel.readDate = Long.MIN_VALUE;
		}

		return eventReadCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<EventRead, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<EventRead, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<EventRead, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((EventRead)this);

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
		Map<String, Function<EventRead, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<EventRead, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<EventRead, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((EventRead)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, EventRead>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _eventId;
	private long _userId;
	private Date _readDate;

	public <T> T getColumnValue(String columnName) {
		Function<EventRead, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((EventRead)this);
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
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("readDate", _readDate);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("eventId", 1L);

		columnBitmasks.put("userId", 2L);

		columnBitmasks.put("readDate", 4L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private EventRead _escapedModel;

}