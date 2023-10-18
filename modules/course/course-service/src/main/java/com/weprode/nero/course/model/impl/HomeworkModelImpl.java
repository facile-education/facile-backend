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

package com.weprode.nero.course.model.impl;

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

import com.weprode.nero.course.model.Homework;
import com.weprode.nero.course.model.HomeworkModel;

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
 * The base model implementation for the Homework service. Represents a row in the &quot;Course_Homework&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>HomeworkModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link HomeworkImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HomeworkImpl
 * @generated
 */
@JSON(strict = true)
public class HomeworkModelImpl
	extends BaseModelImpl<Homework> implements HomeworkModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a homework model instance should use the <code>Homework</code> interface instead.
	 */
	public static final String TABLE_NAME = "Course_Homework";

	public static final Object[][] TABLE_COLUMNS = {
		{"homeworkId", Types.BIGINT}, {"homeworkType", Types.INTEGER},
		{"courseId", Types.BIGINT}, {"teacherId", Types.BIGINT},
		{"title", Types.VARCHAR}, {"modificationDate", Types.TIMESTAMP},
		{"sourceSessionId", Types.BIGINT}, {"targetSessionId", Types.BIGINT},
		{"targetDate", Types.TIMESTAMP}, {"isCustomStudentList", Types.BOOLEAN},
		{"estimatedTime", Types.INTEGER}, {"publicationDate", Types.TIMESTAMP},
		{"isDraft", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("homeworkId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("homeworkType", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("courseId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("teacherId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("modificationDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("sourceSessionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("targetSessionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("targetDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("isCustomStudentList", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("estimatedTime", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("publicationDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("isDraft", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Course_Homework (homeworkId LONG not null primary key,homeworkType INTEGER,courseId LONG,teacherId LONG,title VARCHAR(255) null,modificationDate DATE null,sourceSessionId LONG,targetSessionId LONG,targetDate DATE null,isCustomStudentList BOOLEAN,estimatedTime INTEGER,publicationDate DATE null,isDraft BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table Course_Homework";

	public static final String ORDER_BY_JPQL =
		" ORDER BY homework.homeworkId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Course_Homework.homeworkId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COURSEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SOURCESESSIONID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TARGETSESSIONID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long HOMEWORKID_COLUMN_BITMASK = 8L;

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

	public HomeworkModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _homeworkId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setHomeworkId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _homeworkId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Homework.class;
	}

	@Override
	public String getModelClassName() {
		return Homework.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Homework, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Homework, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Homework, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Homework)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Homework, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Homework, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Homework)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Homework, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Homework, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<Homework, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Homework, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Homework, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Homework, Object>>();
		Map<String, BiConsumer<Homework, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Homework, ?>>();

		attributeGetterFunctions.put("homeworkId", Homework::getHomeworkId);
		attributeSetterBiConsumers.put(
			"homeworkId", (BiConsumer<Homework, Long>)Homework::setHomeworkId);
		attributeGetterFunctions.put("homeworkType", Homework::getHomeworkType);
		attributeSetterBiConsumers.put(
			"homeworkType",
			(BiConsumer<Homework, Integer>)Homework::setHomeworkType);
		attributeGetterFunctions.put("courseId", Homework::getCourseId);
		attributeSetterBiConsumers.put(
			"courseId", (BiConsumer<Homework, Long>)Homework::setCourseId);
		attributeGetterFunctions.put("teacherId", Homework::getTeacherId);
		attributeSetterBiConsumers.put(
			"teacherId", (BiConsumer<Homework, Long>)Homework::setTeacherId);
		attributeGetterFunctions.put("title", Homework::getTitle);
		attributeSetterBiConsumers.put(
			"title", (BiConsumer<Homework, String>)Homework::setTitle);
		attributeGetterFunctions.put(
			"modificationDate", Homework::getModificationDate);
		attributeSetterBiConsumers.put(
			"modificationDate",
			(BiConsumer<Homework, Date>)Homework::setModificationDate);
		attributeGetterFunctions.put(
			"sourceSessionId", Homework::getSourceSessionId);
		attributeSetterBiConsumers.put(
			"sourceSessionId",
			(BiConsumer<Homework, Long>)Homework::setSourceSessionId);
		attributeGetterFunctions.put(
			"targetSessionId", Homework::getTargetSessionId);
		attributeSetterBiConsumers.put(
			"targetSessionId",
			(BiConsumer<Homework, Long>)Homework::setTargetSessionId);
		attributeGetterFunctions.put("targetDate", Homework::getTargetDate);
		attributeSetterBiConsumers.put(
			"targetDate", (BiConsumer<Homework, Date>)Homework::setTargetDate);
		attributeGetterFunctions.put(
			"isCustomStudentList", Homework::getIsCustomStudentList);
		attributeSetterBiConsumers.put(
			"isCustomStudentList",
			(BiConsumer<Homework, Boolean>)Homework::setIsCustomStudentList);
		attributeGetterFunctions.put(
			"estimatedTime", Homework::getEstimatedTime);
		attributeSetterBiConsumers.put(
			"estimatedTime",
			(BiConsumer<Homework, Integer>)Homework::setEstimatedTime);
		attributeGetterFunctions.put(
			"publicationDate", Homework::getPublicationDate);
		attributeSetterBiConsumers.put(
			"publicationDate",
			(BiConsumer<Homework, Date>)Homework::setPublicationDate);
		attributeGetterFunctions.put("isDraft", Homework::getIsDraft);
		attributeSetterBiConsumers.put(
			"isDraft", (BiConsumer<Homework, Boolean>)Homework::setIsDraft);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getHomeworkId() {
		return _homeworkId;
	}

	@Override
	public void setHomeworkId(long homeworkId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_homeworkId = homeworkId;
	}

	@JSON
	@Override
	public int getHomeworkType() {
		return _homeworkType;
	}

	@Override
	public void setHomeworkType(int homeworkType) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_homeworkType = homeworkType;
	}

	@JSON
	@Override
	public long getCourseId() {
		return _courseId;
	}

	@Override
	public void setCourseId(long courseId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_courseId = courseId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCourseId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("courseId"));
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

	@JSON
	@Override
	public String getTitle() {
		if (_title == null) {
			return "";
		}
		else {
			return _title;
		}
	}

	@Override
	public void setTitle(String title) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_title = title;
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
	public long getSourceSessionId() {
		return _sourceSessionId;
	}

	@Override
	public void setSourceSessionId(long sourceSessionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sourceSessionId = sourceSessionId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalSourceSessionId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("sourceSessionId"));
	}

	@JSON
	@Override
	public long getTargetSessionId() {
		return _targetSessionId;
	}

	@Override
	public void setTargetSessionId(long targetSessionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_targetSessionId = targetSessionId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalTargetSessionId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("targetSessionId"));
	}

	@JSON
	@Override
	public Date getTargetDate() {
		return _targetDate;
	}

	@Override
	public void setTargetDate(Date targetDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_targetDate = targetDate;
	}

	@JSON
	@Override
	public boolean getIsCustomStudentList() {
		return _isCustomStudentList;
	}

	@JSON
	@Override
	public boolean isIsCustomStudentList() {
		return _isCustomStudentList;
	}

	@Override
	public void setIsCustomStudentList(boolean isCustomStudentList) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_isCustomStudentList = isCustomStudentList;
	}

	@JSON
	@Override
	public int getEstimatedTime() {
		return _estimatedTime;
	}

	@Override
	public void setEstimatedTime(int estimatedTime) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_estimatedTime = estimatedTime;
	}

	@JSON
	@Override
	public Date getPublicationDate() {
		return _publicationDate;
	}

	@Override
	public void setPublicationDate(Date publicationDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_publicationDate = publicationDate;
	}

	@JSON
	@Override
	public boolean getIsDraft() {
		return _isDraft;
	}

	@JSON
	@Override
	public boolean isIsDraft() {
		return _isDraft;
	}

	@Override
	public void setIsDraft(boolean isDraft) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_isDraft = isDraft;
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
			0, Homework.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Homework toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Homework>
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
		HomeworkImpl homeworkImpl = new HomeworkImpl();

		homeworkImpl.setHomeworkId(getHomeworkId());
		homeworkImpl.setHomeworkType(getHomeworkType());
		homeworkImpl.setCourseId(getCourseId());
		homeworkImpl.setTeacherId(getTeacherId());
		homeworkImpl.setTitle(getTitle());
		homeworkImpl.setModificationDate(getModificationDate());
		homeworkImpl.setSourceSessionId(getSourceSessionId());
		homeworkImpl.setTargetSessionId(getTargetSessionId());
		homeworkImpl.setTargetDate(getTargetDate());
		homeworkImpl.setIsCustomStudentList(isIsCustomStudentList());
		homeworkImpl.setEstimatedTime(getEstimatedTime());
		homeworkImpl.setPublicationDate(getPublicationDate());
		homeworkImpl.setIsDraft(isIsDraft());

		homeworkImpl.resetOriginalValues();

		return homeworkImpl;
	}

	@Override
	public Homework cloneWithOriginalValues() {
		HomeworkImpl homeworkImpl = new HomeworkImpl();

		homeworkImpl.setHomeworkId(
			this.<Long>getColumnOriginalValue("homeworkId"));
		homeworkImpl.setHomeworkType(
			this.<Integer>getColumnOriginalValue("homeworkType"));
		homeworkImpl.setCourseId(this.<Long>getColumnOriginalValue("courseId"));
		homeworkImpl.setTeacherId(
			this.<Long>getColumnOriginalValue("teacherId"));
		homeworkImpl.setTitle(this.<String>getColumnOriginalValue("title"));
		homeworkImpl.setModificationDate(
			this.<Date>getColumnOriginalValue("modificationDate"));
		homeworkImpl.setSourceSessionId(
			this.<Long>getColumnOriginalValue("sourceSessionId"));
		homeworkImpl.setTargetSessionId(
			this.<Long>getColumnOriginalValue("targetSessionId"));
		homeworkImpl.setTargetDate(
			this.<Date>getColumnOriginalValue("targetDate"));
		homeworkImpl.setIsCustomStudentList(
			this.<Boolean>getColumnOriginalValue("isCustomStudentList"));
		homeworkImpl.setEstimatedTime(
			this.<Integer>getColumnOriginalValue("estimatedTime"));
		homeworkImpl.setPublicationDate(
			this.<Date>getColumnOriginalValue("publicationDate"));
		homeworkImpl.setIsDraft(
			this.<Boolean>getColumnOriginalValue("isDraft"));

		return homeworkImpl;
	}

	@Override
	public int compareTo(Homework homework) {
		long primaryKey = homework.getPrimaryKey();

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

		if (!(object instanceof Homework)) {
			return false;
		}

		Homework homework = (Homework)object;

		long primaryKey = homework.getPrimaryKey();

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
	public CacheModel<Homework> toCacheModel() {
		HomeworkCacheModel homeworkCacheModel = new HomeworkCacheModel();

		homeworkCacheModel.homeworkId = getHomeworkId();

		homeworkCacheModel.homeworkType = getHomeworkType();

		homeworkCacheModel.courseId = getCourseId();

		homeworkCacheModel.teacherId = getTeacherId();

		homeworkCacheModel.title = getTitle();

		String title = homeworkCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			homeworkCacheModel.title = null;
		}

		Date modificationDate = getModificationDate();

		if (modificationDate != null) {
			homeworkCacheModel.modificationDate = modificationDate.getTime();
		}
		else {
			homeworkCacheModel.modificationDate = Long.MIN_VALUE;
		}

		homeworkCacheModel.sourceSessionId = getSourceSessionId();

		homeworkCacheModel.targetSessionId = getTargetSessionId();

		Date targetDate = getTargetDate();

		if (targetDate != null) {
			homeworkCacheModel.targetDate = targetDate.getTime();
		}
		else {
			homeworkCacheModel.targetDate = Long.MIN_VALUE;
		}

		homeworkCacheModel.isCustomStudentList = isIsCustomStudentList();

		homeworkCacheModel.estimatedTime = getEstimatedTime();

		Date publicationDate = getPublicationDate();

		if (publicationDate != null) {
			homeworkCacheModel.publicationDate = publicationDate.getTime();
		}
		else {
			homeworkCacheModel.publicationDate = Long.MIN_VALUE;
		}

		homeworkCacheModel.isDraft = isIsDraft();

		return homeworkCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Homework, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Homework, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Homework, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((Homework)this);

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

		private static final Function<InvocationHandler, Homework>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					Homework.class, ModelWrapper.class);

	}

	private long _homeworkId;
	private int _homeworkType;
	private long _courseId;
	private long _teacherId;
	private String _title;
	private Date _modificationDate;
	private long _sourceSessionId;
	private long _targetSessionId;
	private Date _targetDate;
	private boolean _isCustomStudentList;
	private int _estimatedTime;
	private Date _publicationDate;
	private boolean _isDraft;

	public <T> T getColumnValue(String columnName) {
		Function<Homework, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((Homework)this);
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

		_columnOriginalValues.put("homeworkId", _homeworkId);
		_columnOriginalValues.put("homeworkType", _homeworkType);
		_columnOriginalValues.put("courseId", _courseId);
		_columnOriginalValues.put("teacherId", _teacherId);
		_columnOriginalValues.put("title", _title);
		_columnOriginalValues.put("modificationDate", _modificationDate);
		_columnOriginalValues.put("sourceSessionId", _sourceSessionId);
		_columnOriginalValues.put("targetSessionId", _targetSessionId);
		_columnOriginalValues.put("targetDate", _targetDate);
		_columnOriginalValues.put("isCustomStudentList", _isCustomStudentList);
		_columnOriginalValues.put("estimatedTime", _estimatedTime);
		_columnOriginalValues.put("publicationDate", _publicationDate);
		_columnOriginalValues.put("isDraft", _isDraft);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("homeworkId", 1L);

		columnBitmasks.put("homeworkType", 2L);

		columnBitmasks.put("courseId", 4L);

		columnBitmasks.put("teacherId", 8L);

		columnBitmasks.put("title", 16L);

		columnBitmasks.put("modificationDate", 32L);

		columnBitmasks.put("sourceSessionId", 64L);

		columnBitmasks.put("targetSessionId", 128L);

		columnBitmasks.put("targetDate", 256L);

		columnBitmasks.put("isCustomStudentList", 512L);

		columnBitmasks.put("estimatedTime", 1024L);

		columnBitmasks.put("publicationDate", 2048L);

		columnBitmasks.put("isDraft", 4096L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private Homework _escapedModel;

}