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

import com.weprode.nero.schedule.model.TeacherSubject;
import com.weprode.nero.schedule.model.TeacherSubjectModel;

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
 * The base model implementation for the TeacherSubject service. Represents a row in the &quot;Schedule_TeacherSubject&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>TeacherSubjectModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link TeacherSubjectImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TeacherSubjectImpl
 * @generated
 */
public class TeacherSubjectModelImpl
	extends BaseModelImpl<TeacherSubject> implements TeacherSubjectModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a teacher subject model instance should use the <code>TeacherSubject</code> interface instead.
	 */
	public static final String TABLE_NAME = "Schedule_TeacherSubject";

	public static final Object[][] TABLE_COLUMNS = {
		{"teacherSubjectId", Types.BIGINT}, {"teacherId", Types.BIGINT},
		{"subjectId", Types.BIGINT}, {"schoolId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("teacherSubjectId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("teacherId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("subjectId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("schoolId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Schedule_TeacherSubject (teacherSubjectId LONG not null primary key,teacherId LONG,subjectId LONG,schoolId LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table Schedule_TeacherSubject";

	public static final String ORDER_BY_JPQL =
		" ORDER BY teacherSubject.teacherSubjectId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Schedule_TeacherSubject.teacherSubjectId ASC";

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
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TEACHERSUBJECTID_COLUMN_BITMASK = 4L;

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

	public TeacherSubjectModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _teacherSubjectId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setTeacherSubjectId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _teacherSubjectId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return TeacherSubject.class;
	}

	@Override
	public String getModelClassName() {
		return TeacherSubject.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<TeacherSubject, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<TeacherSubject, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<TeacherSubject, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((TeacherSubject)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<TeacherSubject, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<TeacherSubject, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(TeacherSubject)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<TeacherSubject, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<TeacherSubject, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<TeacherSubject, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<TeacherSubject, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<TeacherSubject, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<TeacherSubject, Object>>();
		Map<String, BiConsumer<TeacherSubject, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<TeacherSubject, ?>>();

		attributeGetterFunctions.put(
			"teacherSubjectId", TeacherSubject::getTeacherSubjectId);
		attributeSetterBiConsumers.put(
			"teacherSubjectId",
			(BiConsumer<TeacherSubject, Long>)
				TeacherSubject::setTeacherSubjectId);
		attributeGetterFunctions.put("teacherId", TeacherSubject::getTeacherId);
		attributeSetterBiConsumers.put(
			"teacherId",
			(BiConsumer<TeacherSubject, Long>)TeacherSubject::setTeacherId);
		attributeGetterFunctions.put("subjectId", TeacherSubject::getSubjectId);
		attributeSetterBiConsumers.put(
			"subjectId",
			(BiConsumer<TeacherSubject, Long>)TeacherSubject::setSubjectId);
		attributeGetterFunctions.put("schoolId", TeacherSubject::getSchoolId);
		attributeSetterBiConsumers.put(
			"schoolId",
			(BiConsumer<TeacherSubject, Long>)TeacherSubject::setSchoolId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getTeacherSubjectId() {
		return _teacherSubjectId;
	}

	@Override
	public void setTeacherSubjectId(long teacherSubjectId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_teacherSubjectId = teacherSubjectId;
	}

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

	@Override
	public long getSubjectId() {
		return _subjectId;
	}

	@Override
	public void setSubjectId(long subjectId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_subjectId = subjectId;
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
			0, TeacherSubject.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public TeacherSubject toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, TeacherSubject>
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
		TeacherSubjectImpl teacherSubjectImpl = new TeacherSubjectImpl();

		teacherSubjectImpl.setTeacherSubjectId(getTeacherSubjectId());
		teacherSubjectImpl.setTeacherId(getTeacherId());
		teacherSubjectImpl.setSubjectId(getSubjectId());
		teacherSubjectImpl.setSchoolId(getSchoolId());

		teacherSubjectImpl.resetOriginalValues();

		return teacherSubjectImpl;
	}

	@Override
	public TeacherSubject cloneWithOriginalValues() {
		TeacherSubjectImpl teacherSubjectImpl = new TeacherSubjectImpl();

		teacherSubjectImpl.setTeacherSubjectId(
			this.<Long>getColumnOriginalValue("teacherSubjectId"));
		teacherSubjectImpl.setTeacherId(
			this.<Long>getColumnOriginalValue("teacherId"));
		teacherSubjectImpl.setSubjectId(
			this.<Long>getColumnOriginalValue("subjectId"));
		teacherSubjectImpl.setSchoolId(
			this.<Long>getColumnOriginalValue("schoolId"));

		return teacherSubjectImpl;
	}

	@Override
	public int compareTo(TeacherSubject teacherSubject) {
		long primaryKey = teacherSubject.getPrimaryKey();

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

		if (!(object instanceof TeacherSubject)) {
			return false;
		}

		TeacherSubject teacherSubject = (TeacherSubject)object;

		long primaryKey = teacherSubject.getPrimaryKey();

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
	public CacheModel<TeacherSubject> toCacheModel() {
		TeacherSubjectCacheModel teacherSubjectCacheModel =
			new TeacherSubjectCacheModel();

		teacherSubjectCacheModel.teacherSubjectId = getTeacherSubjectId();

		teacherSubjectCacheModel.teacherId = getTeacherId();

		teacherSubjectCacheModel.subjectId = getSubjectId();

		teacherSubjectCacheModel.schoolId = getSchoolId();

		return teacherSubjectCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<TeacherSubject, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<TeacherSubject, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<TeacherSubject, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((TeacherSubject)this);

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

		private static final Function<InvocationHandler, TeacherSubject>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					TeacherSubject.class, ModelWrapper.class);

	}

	private long _teacherSubjectId;
	private long _teacherId;
	private long _subjectId;
	private long _schoolId;

	public <T> T getColumnValue(String columnName) {
		Function<TeacherSubject, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((TeacherSubject)this);
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

		_columnOriginalValues.put("teacherSubjectId", _teacherSubjectId);
		_columnOriginalValues.put("teacherId", _teacherId);
		_columnOriginalValues.put("subjectId", _subjectId);
		_columnOriginalValues.put("schoolId", _schoolId);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("teacherSubjectId", 1L);

		columnBitmasks.put("teacherId", 2L);

		columnBitmasks.put("subjectId", 4L);

		columnBitmasks.put("schoolId", 8L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private TeacherSubject _escapedModel;

}