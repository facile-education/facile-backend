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
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.facile.schedule.model.CourseDetails;
import com.weprode.facile.schedule.model.CourseDetailsModel;

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
 * The base model implementation for the CourseDetails service. Represents a row in the &quot;Schedule_CourseDetails&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CourseDetailsModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CourseDetailsImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseDetailsImpl
 * @generated
 */
public class CourseDetailsModelImpl
	extends BaseModelImpl<CourseDetails> implements CourseDetailsModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a course details model instance should use the <code>CourseDetails</code> interface instead.
	 */
	public static final String TABLE_NAME = "Schedule_CourseDetails";

	public static final Object[][] TABLE_COLUMNS = {
		{"courseGroupId", Types.BIGINT}, {"color", Types.VARCHAR},
		{"subjectId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("courseGroupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("color", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("subjectId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Schedule_CourseDetails (courseGroupId LONG not null primary key,color VARCHAR(75) null,subjectId LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table Schedule_CourseDetails";

	public static final String ORDER_BY_JPQL =
		" ORDER BY courseDetails.courseGroupId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Schedule_CourseDetails.courseGroupId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COURSEGROUPID_COLUMN_BITMASK = 1L;

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

	public CourseDetailsModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _courseGroupId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCourseGroupId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _courseGroupId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CourseDetails.class;
	}

	@Override
	public String getModelClassName() {
		return CourseDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CourseDetails, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CourseDetails, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CourseDetails, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CourseDetails)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CourseDetails, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CourseDetails, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CourseDetails)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CourseDetails, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CourseDetails, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<CourseDetails, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CourseDetails, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CourseDetails, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<CourseDetails, Object>>();
		Map<String, BiConsumer<CourseDetails, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<CourseDetails, ?>>();

		attributeGetterFunctions.put(
			"courseGroupId", CourseDetails::getCourseGroupId);
		attributeSetterBiConsumers.put(
			"courseGroupId",
			(BiConsumer<CourseDetails, Long>)CourseDetails::setCourseGroupId);
		attributeGetterFunctions.put("color", CourseDetails::getColor);
		attributeSetterBiConsumers.put(
			"color",
			(BiConsumer<CourseDetails, String>)CourseDetails::setColor);
		attributeGetterFunctions.put("subjectId", CourseDetails::getSubjectId);
		attributeSetterBiConsumers.put(
			"subjectId",
			(BiConsumer<CourseDetails, Long>)CourseDetails::setSubjectId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getCourseGroupId() {
		return _courseGroupId;
	}

	@Override
	public void setCourseGroupId(long courseGroupId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_courseGroupId = courseGroupId;
	}

	@Override
	public String getColor() {
		if (_color == null) {
			return "";
		}
		else {
			return _color;
		}
	}

	@Override
	public void setColor(String color) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_color = color;
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
			0, CourseDetails.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CourseDetails toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CourseDetails>
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
		CourseDetailsImpl courseDetailsImpl = new CourseDetailsImpl();

		courseDetailsImpl.setCourseGroupId(getCourseGroupId());
		courseDetailsImpl.setColor(getColor());
		courseDetailsImpl.setSubjectId(getSubjectId());

		courseDetailsImpl.resetOriginalValues();

		return courseDetailsImpl;
	}

	@Override
	public CourseDetails cloneWithOriginalValues() {
		CourseDetailsImpl courseDetailsImpl = new CourseDetailsImpl();

		courseDetailsImpl.setCourseGroupId(
			this.<Long>getColumnOriginalValue("courseGroupId"));
		courseDetailsImpl.setColor(
			this.<String>getColumnOriginalValue("color"));
		courseDetailsImpl.setSubjectId(
			this.<Long>getColumnOriginalValue("subjectId"));

		return courseDetailsImpl;
	}

	@Override
	public int compareTo(CourseDetails courseDetails) {
		long primaryKey = courseDetails.getPrimaryKey();

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

		if (!(object instanceof CourseDetails)) {
			return false;
		}

		CourseDetails courseDetails = (CourseDetails)object;

		long primaryKey = courseDetails.getPrimaryKey();

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
	public CacheModel<CourseDetails> toCacheModel() {
		CourseDetailsCacheModel courseDetailsCacheModel =
			new CourseDetailsCacheModel();

		courseDetailsCacheModel.courseGroupId = getCourseGroupId();

		courseDetailsCacheModel.color = getColor();

		String color = courseDetailsCacheModel.color;

		if ((color != null) && (color.length() == 0)) {
			courseDetailsCacheModel.color = null;
		}

		courseDetailsCacheModel.subjectId = getSubjectId();

		return courseDetailsCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CourseDetails, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CourseDetails, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CourseDetails, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((CourseDetails)this);

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

		private static final Function<InvocationHandler, CourseDetails>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					CourseDetails.class, ModelWrapper.class);

	}

	private long _courseGroupId;
	private String _color;
	private long _subjectId;

	public <T> T getColumnValue(String columnName) {
		Function<CourseDetails, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CourseDetails)this);
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

		_columnOriginalValues.put("courseGroupId", _courseGroupId);
		_columnOriginalValues.put("color", _color);
		_columnOriginalValues.put("subjectId", _subjectId);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("courseGroupId", 1L);

		columnBitmasks.put("color", 2L);

		columnBitmasks.put("subjectId", 4L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CourseDetails _escapedModel;

}