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

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.nero.school.life.model.SessionStudent;
import com.weprode.nero.school.life.model.SessionStudentModel;
import com.weprode.nero.school.life.service.persistence.SessionStudentPK;

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
 * The base model implementation for the SessionStudent service. Represents a row in the &quot;Schoollife_SessionStudent&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SessionStudentModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SessionStudentImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SessionStudentImpl
 * @generated
 */
@JSON(strict = true)
public class SessionStudentModelImpl
	extends BaseModelImpl<SessionStudent> implements SessionStudentModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a session student model instance should use the <code>SessionStudent</code> interface instead.
	 */
	public static final String TABLE_NAME = "Schoollife_SessionStudent";

	public static final Object[][] TABLE_COLUMNS = {
		{"schoollifeSessionId", Types.BIGINT}, {"studentId", Types.BIGINT},
		{"sourceTeacherId", Types.BIGINT}, {"isPresent", Types.BOOLEAN},
		{"notifyParents", Types.BOOLEAN}, {"comment_", Types.VARCHAR},
		{"subject", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("schoollifeSessionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("studentId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("sourceTeacherId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("isPresent", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("notifyParents", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("comment_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("subject", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Schoollife_SessionStudent (schoollifeSessionId LONG not null,studentId LONG not null,sourceTeacherId LONG,isPresent BOOLEAN,notifyParents BOOLEAN,comment_ STRING null,subject VARCHAR(300) null,primary key (schoollifeSessionId, studentId))";

	public static final String TABLE_SQL_DROP =
		"drop table Schoollife_SessionStudent";

	public static final String ORDER_BY_JPQL =
		" ORDER BY sessionStudent.id.schoollifeSessionId ASC, sessionStudent.id.studentId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Schoollife_SessionStudent.schoollifeSessionId ASC, Schoollife_SessionStudent.studentId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SCHOOLLIFESESSIONID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long STUDENTID_COLUMN_BITMASK = 2L;

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
	public SessionStudentPK getPrimaryKey() {
		return new SessionStudentPK(_schoollifeSessionId, _studentId);
	}

	@Override
	public void setPrimaryKey(SessionStudentPK primaryKey) {
		setSchoollifeSessionId(primaryKey.schoollifeSessionId);
		setStudentId(primaryKey.studentId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new SessionStudentPK(_schoollifeSessionId, _studentId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((SessionStudentPK)primaryKeyObj);
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
			"schoollifeSessionId", SessionStudent::getSchoollifeSessionId);
		attributeSetterBiConsumers.put(
			"schoollifeSessionId",
			(BiConsumer<SessionStudent, Long>)
				SessionStudent::setSchoollifeSessionId);
		attributeGetterFunctions.put("studentId", SessionStudent::getStudentId);
		attributeSetterBiConsumers.put(
			"studentId",
			(BiConsumer<SessionStudent, Long>)SessionStudent::setStudentId);
		attributeGetterFunctions.put(
			"sourceTeacherId", SessionStudent::getSourceTeacherId);
		attributeSetterBiConsumers.put(
			"sourceTeacherId",
			(BiConsumer<SessionStudent, Long>)
				SessionStudent::setSourceTeacherId);
		attributeGetterFunctions.put("isPresent", SessionStudent::getIsPresent);
		attributeSetterBiConsumers.put(
			"isPresent",
			(BiConsumer<SessionStudent, Boolean>)SessionStudent::setIsPresent);
		attributeGetterFunctions.put(
			"notifyParents", SessionStudent::getNotifyParents);
		attributeSetterBiConsumers.put(
			"notifyParents",
			(BiConsumer<SessionStudent, Boolean>)
				SessionStudent::setNotifyParents);
		attributeGetterFunctions.put("comment", SessionStudent::getComment);
		attributeSetterBiConsumers.put(
			"comment",
			(BiConsumer<SessionStudent, String>)SessionStudent::setComment);
		attributeGetterFunctions.put("subject", SessionStudent::getSubject);
		attributeSetterBiConsumers.put(
			"subject",
			(BiConsumer<SessionStudent, String>)SessionStudent::setSubject);

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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalSchoollifeSessionId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("schoollifeSessionId"));
	}

	@JSON
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

	@JSON
	@Override
	public long getSourceTeacherId() {
		return _sourceTeacherId;
	}

	@Override
	public void setSourceTeacherId(long sourceTeacherId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sourceTeacherId = sourceTeacherId;
	}

	@JSON
	@Override
	public boolean getIsPresent() {
		return _isPresent;
	}

	@JSON
	@Override
	public boolean isIsPresent() {
		return _isPresent;
	}

	@Override
	public void setIsPresent(boolean isPresent) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_isPresent = isPresent;
	}

	@JSON
	@Override
	public boolean getNotifyParents() {
		return _notifyParents;
	}

	@JSON
	@Override
	public boolean isNotifyParents() {
		return _notifyParents;
	}

	@Override
	public void setNotifyParents(boolean notifyParents) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_notifyParents = notifyParents;
	}

	@JSON
	@Override
	public String getComment() {
		if (_comment == null) {
			return "";
		}
		else {
			return _comment;
		}
	}

	@Override
	public void setComment(String comment) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_comment = comment;
	}

	@JSON
	@Override
	public String getSubject() {
		if (_subject == null) {
			return "";
		}
		else {
			return _subject;
		}
	}

	@Override
	public void setSubject(String subject) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_subject = subject;
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

		sessionStudentImpl.setSchoollifeSessionId(getSchoollifeSessionId());
		sessionStudentImpl.setStudentId(getStudentId());
		sessionStudentImpl.setSourceTeacherId(getSourceTeacherId());
		sessionStudentImpl.setIsPresent(isIsPresent());
		sessionStudentImpl.setNotifyParents(isNotifyParents());
		sessionStudentImpl.setComment(getComment());
		sessionStudentImpl.setSubject(getSubject());

		sessionStudentImpl.resetOriginalValues();

		return sessionStudentImpl;
	}

	@Override
	public SessionStudent cloneWithOriginalValues() {
		SessionStudentImpl sessionStudentImpl = new SessionStudentImpl();

		sessionStudentImpl.setSchoollifeSessionId(
			this.<Long>getColumnOriginalValue("schoollifeSessionId"));
		sessionStudentImpl.setStudentId(
			this.<Long>getColumnOriginalValue("studentId"));
		sessionStudentImpl.setSourceTeacherId(
			this.<Long>getColumnOriginalValue("sourceTeacherId"));
		sessionStudentImpl.setIsPresent(
			this.<Boolean>getColumnOriginalValue("isPresent"));
		sessionStudentImpl.setNotifyParents(
			this.<Boolean>getColumnOriginalValue("notifyParents"));
		sessionStudentImpl.setComment(
			this.<String>getColumnOriginalValue("comment_"));
		sessionStudentImpl.setSubject(
			this.<String>getColumnOriginalValue("subject"));

		return sessionStudentImpl;
	}

	@Override
	public int compareTo(SessionStudent sessionStudent) {
		SessionStudentPK primaryKey = sessionStudent.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
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

		SessionStudentPK primaryKey = sessionStudent.getPrimaryKey();

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
	public CacheModel<SessionStudent> toCacheModel() {
		SessionStudentCacheModel sessionStudentCacheModel =
			new SessionStudentCacheModel();

		sessionStudentCacheModel.sessionStudentPK = getPrimaryKey();

		sessionStudentCacheModel.schoollifeSessionId = getSchoollifeSessionId();

		sessionStudentCacheModel.studentId = getStudentId();

		sessionStudentCacheModel.sourceTeacherId = getSourceTeacherId();

		sessionStudentCacheModel.isPresent = isIsPresent();

		sessionStudentCacheModel.notifyParents = isNotifyParents();

		sessionStudentCacheModel.comment = getComment();

		String comment = sessionStudentCacheModel.comment;

		if ((comment != null) && (comment.length() == 0)) {
			sessionStudentCacheModel.comment = null;
		}

		sessionStudentCacheModel.subject = getSubject();

		String subject = sessionStudentCacheModel.subject;

		if ((subject != null) && (subject.length() == 0)) {
			sessionStudentCacheModel.subject = null;
		}

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

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, SessionStudent>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					SessionStudent.class, ModelWrapper.class);

	}

	private long _schoollifeSessionId;
	private long _studentId;
	private long _sourceTeacherId;
	private boolean _isPresent;
	private boolean _notifyParents;
	private String _comment;
	private String _subject;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

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

		_columnOriginalValues.put("schoollifeSessionId", _schoollifeSessionId);
		_columnOriginalValues.put("studentId", _studentId);
		_columnOriginalValues.put("sourceTeacherId", _sourceTeacherId);
		_columnOriginalValues.put("isPresent", _isPresent);
		_columnOriginalValues.put("notifyParents", _notifyParents);
		_columnOriginalValues.put("comment_", _comment);
		_columnOriginalValues.put("subject", _subject);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("comment_", "comment");

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

		columnBitmasks.put("studentId", 2L);

		columnBitmasks.put("sourceTeacherId", 4L);

		columnBitmasks.put("isPresent", 8L);

		columnBitmasks.put("notifyParents", 16L);

		columnBitmasks.put("comment_", 32L);

		columnBitmasks.put("subject", 64L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private SessionStudent _escapedModel;

}