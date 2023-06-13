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
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.nero.schedule.model.SessionTeacher;
import com.weprode.nero.schedule.model.SessionTeacherModel;
import com.weprode.nero.schedule.model.SessionTeacherSoap;

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
 * The base model implementation for the SessionTeacher service. Represents a row in the &quot;Schedule_SessionTeacher&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SessionTeacherModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SessionTeacherImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SessionTeacherImpl
 * @generated
 */
@JSON(strict = true)
public class SessionTeacherModelImpl
	extends BaseModelImpl<SessionTeacher> implements SessionTeacherModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a session teacher model instance should use the <code>SessionTeacher</code> interface instead.
	 */
	public static final String TABLE_NAME = "Schedule_SessionTeacher";

	public static final Object[][] TABLE_COLUMNS = {
		{"sessionTeacherId", Types.BIGINT}, {"sessionId", Types.BIGINT},
		{"teacherId", Types.BIGINT}, {"status", Types.INTEGER},
		{"substituteId", Types.BIGINT}, {"modificationDate", Types.TIMESTAMP},
		{"privateNotes", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("sessionTeacherId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("sessionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("teacherId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("substituteId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("modificationDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("privateNotes", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Schedule_SessionTeacher (sessionTeacherId LONG not null primary key,sessionId LONG,teacherId LONG,status INTEGER,substituteId LONG,modificationDate DATE null,privateNotes VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table Schedule_SessionTeacher";

	public static final String ORDER_BY_JPQL =
		" ORDER BY sessionTeacher.sessionTeacherId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Schedule_SessionTeacher.sessionTeacherId ASC";

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
	public static final long SUBSTITUTEID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TEACHERID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SESSIONTEACHERID_COLUMN_BITMASK = 8L;

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
	public static SessionTeacher toModel(SessionTeacherSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		SessionTeacher model = new SessionTeacherImpl();

		model.setSessionTeacherId(soapModel.getSessionTeacherId());
		model.setSessionId(soapModel.getSessionId());
		model.setTeacherId(soapModel.getTeacherId());
		model.setStatus(soapModel.getStatus());
		model.setSubstituteId(soapModel.getSubstituteId());
		model.setModificationDate(soapModel.getModificationDate());
		model.setPrivateNotes(soapModel.getPrivateNotes());

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
	public static List<SessionTeacher> toModels(
		SessionTeacherSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<SessionTeacher> models = new ArrayList<SessionTeacher>(
			soapModels.length);

		for (SessionTeacherSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public SessionTeacherModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _sessionTeacherId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSessionTeacherId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _sessionTeacherId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SessionTeacher.class;
	}

	@Override
	public String getModelClassName() {
		return SessionTeacher.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SessionTeacher, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SessionTeacher, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SessionTeacher, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((SessionTeacher)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SessionTeacher, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SessionTeacher, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SessionTeacher)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SessionTeacher, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SessionTeacher, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, SessionTeacher>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			SessionTeacher.class.getClassLoader(), SessionTeacher.class,
			ModelWrapper.class);

		try {
			Constructor<SessionTeacher> constructor =
				(Constructor<SessionTeacher>)proxyClass.getConstructor(
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

	private static final Map<String, Function<SessionTeacher, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<SessionTeacher, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<SessionTeacher, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<SessionTeacher, Object>>();
		Map<String, BiConsumer<SessionTeacher, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<SessionTeacher, ?>>();

		attributeGetterFunctions.put(
			"sessionTeacherId", SessionTeacher::getSessionTeacherId);
		attributeSetterBiConsumers.put(
			"sessionTeacherId",
			(BiConsumer<SessionTeacher, Long>)
				SessionTeacher::setSessionTeacherId);
		attributeGetterFunctions.put("sessionId", SessionTeacher::getSessionId);
		attributeSetterBiConsumers.put(
			"sessionId",
			(BiConsumer<SessionTeacher, Long>)SessionTeacher::setSessionId);
		attributeGetterFunctions.put("teacherId", SessionTeacher::getTeacherId);
		attributeSetterBiConsumers.put(
			"teacherId",
			(BiConsumer<SessionTeacher, Long>)SessionTeacher::setTeacherId);
		attributeGetterFunctions.put("status", SessionTeacher::getStatus);
		attributeSetterBiConsumers.put(
			"status",
			(BiConsumer<SessionTeacher, Integer>)SessionTeacher::setStatus);
		attributeGetterFunctions.put(
			"substituteId", SessionTeacher::getSubstituteId);
		attributeSetterBiConsumers.put(
			"substituteId",
			(BiConsumer<SessionTeacher, Long>)SessionTeacher::setSubstituteId);
		attributeGetterFunctions.put(
			"modificationDate", SessionTeacher::getModificationDate);
		attributeSetterBiConsumers.put(
			"modificationDate",
			(BiConsumer<SessionTeacher, Date>)
				SessionTeacher::setModificationDate);
		attributeGetterFunctions.put(
			"privateNotes", SessionTeacher::getPrivateNotes);
		attributeSetterBiConsumers.put(
			"privateNotes",
			(BiConsumer<SessionTeacher, String>)
				SessionTeacher::setPrivateNotes);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getSessionTeacherId() {
		return _sessionTeacherId;
	}

	@Override
	public void setSessionTeacherId(long sessionTeacherId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sessionTeacherId = sessionTeacherId;
	}

	@JSON
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
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_status = status;
	}

	@JSON
	@Override
	public long getSubstituteId() {
		return _substituteId;
	}

	@Override
	public void setSubstituteId(long substituteId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_substituteId = substituteId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalSubstituteId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("substituteId"));
	}

	@JSON
	@Override
	public Date getModificationDate() {
		return _modificationDate;
	}

	@Override
	public void setModificationDate(Date modificationDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modificationDate = modificationDate;
	}

	@JSON
	@Override
	public String getPrivateNotes() {
		if (_privateNotes == null) {
			return "";
		}
		else {
			return _privateNotes;
		}
	}

	@Override
	public void setPrivateNotes(String privateNotes) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_privateNotes = privateNotes;
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
			0, SessionTeacher.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SessionTeacher toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SessionTeacher>
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
		SessionTeacherImpl sessionTeacherImpl = new SessionTeacherImpl();

		sessionTeacherImpl.setSessionTeacherId(getSessionTeacherId());
		sessionTeacherImpl.setSessionId(getSessionId());
		sessionTeacherImpl.setTeacherId(getTeacherId());
		sessionTeacherImpl.setStatus(getStatus());
		sessionTeacherImpl.setSubstituteId(getSubstituteId());
		sessionTeacherImpl.setModificationDate(getModificationDate());
		sessionTeacherImpl.setPrivateNotes(getPrivateNotes());

		sessionTeacherImpl.resetOriginalValues();

		return sessionTeacherImpl;
	}

	@Override
	public SessionTeacher cloneWithOriginalValues() {
		SessionTeacherImpl sessionTeacherImpl = new SessionTeacherImpl();

		sessionTeacherImpl.setSessionTeacherId(
			this.<Long>getColumnOriginalValue("sessionTeacherId"));
		sessionTeacherImpl.setSessionId(
			this.<Long>getColumnOriginalValue("sessionId"));
		sessionTeacherImpl.setTeacherId(
			this.<Long>getColumnOriginalValue("teacherId"));
		sessionTeacherImpl.setStatus(
			this.<Integer>getColumnOriginalValue("status"));
		sessionTeacherImpl.setSubstituteId(
			this.<Long>getColumnOriginalValue("substituteId"));
		sessionTeacherImpl.setModificationDate(
			this.<Date>getColumnOriginalValue("modificationDate"));
		sessionTeacherImpl.setPrivateNotes(
			this.<String>getColumnOriginalValue("privateNotes"));

		return sessionTeacherImpl;
	}

	@Override
	public int compareTo(SessionTeacher sessionTeacher) {
		long primaryKey = sessionTeacher.getPrimaryKey();

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

		if (!(object instanceof SessionTeacher)) {
			return false;
		}

		SessionTeacher sessionTeacher = (SessionTeacher)object;

		long primaryKey = sessionTeacher.getPrimaryKey();

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
	public CacheModel<SessionTeacher> toCacheModel() {
		SessionTeacherCacheModel sessionTeacherCacheModel =
			new SessionTeacherCacheModel();

		sessionTeacherCacheModel.sessionTeacherId = getSessionTeacherId();

		sessionTeacherCacheModel.sessionId = getSessionId();

		sessionTeacherCacheModel.teacherId = getTeacherId();

		sessionTeacherCacheModel.status = getStatus();

		sessionTeacherCacheModel.substituteId = getSubstituteId();

		Date modificationDate = getModificationDate();

		if (modificationDate != null) {
			sessionTeacherCacheModel.modificationDate =
				modificationDate.getTime();
		}
		else {
			sessionTeacherCacheModel.modificationDate = Long.MIN_VALUE;
		}

		sessionTeacherCacheModel.privateNotes = getPrivateNotes();

		String privateNotes = sessionTeacherCacheModel.privateNotes;

		if ((privateNotes != null) && (privateNotes.length() == 0)) {
			sessionTeacherCacheModel.privateNotes = null;
		}

		return sessionTeacherCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SessionTeacher, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SessionTeacher, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SessionTeacher, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((SessionTeacher)this);

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
		Map<String, Function<SessionTeacher, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<SessionTeacher, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SessionTeacher, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((SessionTeacher)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, SessionTeacher>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _sessionTeacherId;
	private long _sessionId;
	private long _teacherId;
	private int _status;
	private long _substituteId;
	private Date _modificationDate;
	private String _privateNotes;

	public <T> T getColumnValue(String columnName) {
		Function<SessionTeacher, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((SessionTeacher)this);
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

		_columnOriginalValues.put("sessionTeacherId", _sessionTeacherId);
		_columnOriginalValues.put("sessionId", _sessionId);
		_columnOriginalValues.put("teacherId", _teacherId);
		_columnOriginalValues.put("status", _status);
		_columnOriginalValues.put("substituteId", _substituteId);
		_columnOriginalValues.put("modificationDate", _modificationDate);
		_columnOriginalValues.put("privateNotes", _privateNotes);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("sessionTeacherId", 1L);

		columnBitmasks.put("sessionId", 2L);

		columnBitmasks.put("teacherId", 4L);

		columnBitmasks.put("status", 8L);

		columnBitmasks.put("substituteId", 16L);

		columnBitmasks.put("modificationDate", 32L);

		columnBitmasks.put("privateNotes", 64L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private SessionTeacher _escapedModel;

}