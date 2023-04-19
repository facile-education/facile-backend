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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.nero.schedule.model.SessionStudent;
import com.weprode.nero.schedule.model.SessionStudentModel;

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
 * The base model implementation for the SessionStudent service. Represents a row in the &quot;Schedule_SessionStudent&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SessionStudentModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SessionStudentImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SessionStudentImpl
 * @generated
 */
public class SessionStudentModelImpl
	extends BaseModelImpl<SessionStudent> implements SessionStudentModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a session student model instance should use the <code>SessionStudent</code> interface instead.
	 */
	public static final String TABLE_NAME = "Schedule_SessionStudent";

	public static final Object[][] TABLE_COLUMNS = {
		{"sessionStudentId", Types.BIGINT}, {"sessionId", Types.BIGINT},
		{"studentId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("sessionStudentId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("sessionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("studentId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Schedule_SessionStudent (sessionStudentId LONG not null primary key,sessionId LONG,studentId LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table Schedule_SessionStudent";

	public static final String ORDER_BY_JPQL =
		" ORDER BY sessionStudent.sessionStudentId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Schedule_SessionStudent.sessionStudentId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SESSIONID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long STUDENTID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SESSIONSTUDENTID_COLUMN_BITMASK = 4L;

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

	public SessionStudentModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _sessionStudentId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSessionStudentId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _sessionStudentId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SessionStudent.class;
	}

	@Override
	public String getModelClassName() {
		return SessionStudent.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SessionStudent, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SessionStudent, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SessionStudent, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((SessionStudent)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SessionStudent, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SessionStudent, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SessionStudent)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SessionStudent, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SessionStudent, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, SessionStudent>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			SessionStudent.class.getClassLoader(), SessionStudent.class,
			ModelWrapper.class);

		try {
			Constructor<SessionStudent> constructor =
				(Constructor<SessionStudent>)proxyClass.getConstructor(
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

	private static final Map<String, Function<SessionStudent, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<SessionStudent, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<SessionStudent, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<SessionStudent, Object>>();
		Map<String, BiConsumer<SessionStudent, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<SessionStudent, ?>>();

		attributeGetterFunctions.put(
			"sessionStudentId", SessionStudent::getSessionStudentId);
		attributeSetterBiConsumers.put(
			"sessionStudentId",
			(BiConsumer<SessionStudent, Long>)
				SessionStudent::setSessionStudentId);
		attributeGetterFunctions.put("sessionId", SessionStudent::getSessionId);
		attributeSetterBiConsumers.put(
			"sessionId",
			(BiConsumer<SessionStudent, Long>)SessionStudent::setSessionId);
		attributeGetterFunctions.put("studentId", SessionStudent::getStudentId);
		attributeSetterBiConsumers.put(
			"studentId",
			(BiConsumer<SessionStudent, Long>)SessionStudent::setStudentId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getSessionStudentId() {
		return _sessionStudentId;
	}

	@Override
	public void setSessionStudentId(long sessionStudentId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sessionStudentId = sessionStudentId;
	}

	@Override
	public long getSessionId() {
		return _sessionId;
	}

	@Override
	public void setSessionId(long sessionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sessionId = sessionId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalSessionId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("sessionId"));
	}

	@Override
	public long getStudentId() {
		return _studentId;
	}

	@Override
	public void setStudentId(long studentId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_studentId = studentId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalStudentId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("studentId"));
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
			0, SessionStudent.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SessionStudent toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SessionStudent>
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
		SessionStudentImpl sessionStudentImpl = new SessionStudentImpl();

		sessionStudentImpl.setSessionStudentId(getSessionStudentId());
		sessionStudentImpl.setSessionId(getSessionId());
		sessionStudentImpl.setStudentId(getStudentId());

		sessionStudentImpl.resetOriginalValues();

		return sessionStudentImpl;
	}

	@Override
	public SessionStudent cloneWithOriginalValues() {
		SessionStudentImpl sessionStudentImpl = new SessionStudentImpl();

		sessionStudentImpl.setSessionStudentId(
			this.<Long>getColumnOriginalValue("sessionStudentId"));
		sessionStudentImpl.setSessionId(
			this.<Long>getColumnOriginalValue("sessionId"));
		sessionStudentImpl.setStudentId(
			this.<Long>getColumnOriginalValue("studentId"));

		return sessionStudentImpl;
	}

	@Override
	public int compareTo(SessionStudent sessionStudent) {
		long primaryKey = sessionStudent.getPrimaryKey();

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

		if (!(object instanceof SessionStudent)) {
			return false;
		}

		SessionStudent sessionStudent = (SessionStudent)object;

		long primaryKey = sessionStudent.getPrimaryKey();

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
	public CacheModel<SessionStudent> toCacheModel() {
		SessionStudentCacheModel sessionStudentCacheModel =
			new SessionStudentCacheModel();

		sessionStudentCacheModel.sessionStudentId = getSessionStudentId();

		sessionStudentCacheModel.sessionId = getSessionId();

		sessionStudentCacheModel.studentId = getStudentId();

		return sessionStudentCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SessionStudent, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SessionStudent, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SessionStudent, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((SessionStudent)this);

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
		Map<String, Function<SessionStudent, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<SessionStudent, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SessionStudent, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((SessionStudent)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, SessionStudent>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _sessionStudentId;
	private long _sessionId;
	private long _studentId;

	public <T> T getColumnValue(String columnName) {
		Function<SessionStudent, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((SessionStudent)this);
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

		_columnOriginalValues.put("sessionStudentId", _sessionStudentId);
		_columnOriginalValues.put("sessionId", _sessionId);
		_columnOriginalValues.put("studentId", _studentId);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("sessionStudentId", 1L);

		columnBitmasks.put("sessionId", 2L);

		columnBitmasks.put("studentId", 4L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private SessionStudent _escapedModel;

}