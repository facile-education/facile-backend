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

import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.model.CDTSessionModel;
import com.weprode.nero.schedule.model.CDTSessionSoap;

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
 * The base model implementation for the CDTSession service. Represents a row in the &quot;Schedule_CDTSession&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CDTSessionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CDTSessionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CDTSessionImpl
 * @generated
 */
@JSON(strict = true)
public class CDTSessionModelImpl
	extends BaseModelImpl<CDTSession> implements CDTSessionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cdt session model instance should use the <code>CDTSession</code> interface instead.
	 */
	public static final String TABLE_NAME = "Schedule_CDTSession";

	public static final Object[][] TABLE_COLUMNS = {
		{"sessionId", Types.BIGINT}, {"sessionStart", Types.TIMESTAMP},
		{"sessionEnd", Types.TIMESTAMP}, {"weekId", Types.BIGINT},
		{"published", Types.BOOLEAN}, {"title", Types.VARCHAR},
		{"fullCoursName", Types.VARCHAR}, {"description", Types.VARCHAR},
		{"room", Types.VARCHAR}, {"subject", Types.VARCHAR},
		{"schoolId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"isManual", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("sessionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("sessionStart", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("sessionEnd", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("weekId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("published", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fullCoursName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("room", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("subject", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("schoolId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("isManual", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Schedule_CDTSession (sessionId LONG not null primary key,sessionStart DATE null,sessionEnd DATE null,weekId LONG,published BOOLEAN,title VARCHAR(250) null,fullCoursName VARCHAR(75) null,description STRING null,room VARCHAR(75) null,subject VARCHAR(75) null,schoolId LONG,groupId LONG,isManual BOOLEAN)";

	public static final String TABLE_SQL_DROP =
		"drop table Schedule_CDTSession";

	public static final String ORDER_BY_JPQL =
		" ORDER BY cdtSession.sessionId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Schedule_CDTSession.sessionId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SESSIONID_COLUMN_BITMASK = 2L;

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
	public static CDTSession toModel(CDTSessionSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CDTSession model = new CDTSessionImpl();

		model.setSessionId(soapModel.getSessionId());
		model.setSessionStart(soapModel.getSessionStart());
		model.setSessionEnd(soapModel.getSessionEnd());
		model.setWeekId(soapModel.getWeekId());
		model.setPublished(soapModel.isPublished());
		model.setTitle(soapModel.getTitle());
		model.setFullCoursName(soapModel.getFullCoursName());
		model.setDescription(soapModel.getDescription());
		model.setRoom(soapModel.getRoom());
		model.setSubject(soapModel.getSubject());
		model.setSchoolId(soapModel.getSchoolId());
		model.setGroupId(soapModel.getGroupId());
		model.setIsManual(soapModel.isIsManual());

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
	public static List<CDTSession> toModels(CDTSessionSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<CDTSession> models = new ArrayList<CDTSession>(soapModels.length);

		for (CDTSessionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public CDTSessionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _sessionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSessionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _sessionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CDTSession.class;
	}

	@Override
	public String getModelClassName() {
		return CDTSession.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CDTSession, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CDTSession, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CDTSession, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((CDTSession)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CDTSession, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CDTSession, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CDTSession)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CDTSession, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CDTSession, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CDTSession>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CDTSession.class.getClassLoader(), CDTSession.class,
			ModelWrapper.class);

		try {
			Constructor<CDTSession> constructor =
				(Constructor<CDTSession>)proxyClass.getConstructor(
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

	private static final Map<String, Function<CDTSession, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CDTSession, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CDTSession, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<CDTSession, Object>>();
		Map<String, BiConsumer<CDTSession, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<CDTSession, ?>>();

		attributeGetterFunctions.put("sessionId", CDTSession::getSessionId);
		attributeSetterBiConsumers.put(
			"sessionId",
			(BiConsumer<CDTSession, Long>)CDTSession::setSessionId);
		attributeGetterFunctions.put(
			"sessionStart", CDTSession::getSessionStart);
		attributeSetterBiConsumers.put(
			"sessionStart",
			(BiConsumer<CDTSession, Date>)CDTSession::setSessionStart);
		attributeGetterFunctions.put("sessionEnd", CDTSession::getSessionEnd);
		attributeSetterBiConsumers.put(
			"sessionEnd",
			(BiConsumer<CDTSession, Date>)CDTSession::setSessionEnd);
		attributeGetterFunctions.put("weekId", CDTSession::getWeekId);
		attributeSetterBiConsumers.put(
			"weekId", (BiConsumer<CDTSession, Long>)CDTSession::setWeekId);
		attributeGetterFunctions.put("published", CDTSession::getPublished);
		attributeSetterBiConsumers.put(
			"published",
			(BiConsumer<CDTSession, Boolean>)CDTSession::setPublished);
		attributeGetterFunctions.put("title", CDTSession::getTitle);
		attributeSetterBiConsumers.put(
			"title", (BiConsumer<CDTSession, String>)CDTSession::setTitle);
		attributeGetterFunctions.put(
			"fullCoursName", CDTSession::getFullCoursName);
		attributeSetterBiConsumers.put(
			"fullCoursName",
			(BiConsumer<CDTSession, String>)CDTSession::setFullCoursName);
		attributeGetterFunctions.put("description", CDTSession::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<CDTSession, String>)CDTSession::setDescription);
		attributeGetterFunctions.put("room", CDTSession::getRoom);
		attributeSetterBiConsumers.put(
			"room", (BiConsumer<CDTSession, String>)CDTSession::setRoom);
		attributeGetterFunctions.put("subject", CDTSession::getSubject);
		attributeSetterBiConsumers.put(
			"subject", (BiConsumer<CDTSession, String>)CDTSession::setSubject);
		attributeGetterFunctions.put("schoolId", CDTSession::getSchoolId);
		attributeSetterBiConsumers.put(
			"schoolId", (BiConsumer<CDTSession, Long>)CDTSession::setSchoolId);
		attributeGetterFunctions.put("groupId", CDTSession::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId", (BiConsumer<CDTSession, Long>)CDTSession::setGroupId);
		attributeGetterFunctions.put("isManual", CDTSession::getIsManual);
		attributeSetterBiConsumers.put(
			"isManual",
			(BiConsumer<CDTSession, Boolean>)CDTSession::setIsManual);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
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

	@JSON
	@Override
	public Date getSessionStart() {
		return _sessionStart;
	}

	@Override
	public void setSessionStart(Date sessionStart) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sessionStart = sessionStart;
	}

	@JSON
	@Override
	public Date getSessionEnd() {
		return _sessionEnd;
	}

	@Override
	public void setSessionEnd(Date sessionEnd) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sessionEnd = sessionEnd;
	}

	@JSON
	@Override
	public long getWeekId() {
		return _weekId;
	}

	@Override
	public void setWeekId(long weekId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_weekId = weekId;
	}

	@JSON
	@Override
	public boolean getPublished() {
		return _published;
	}

	@JSON
	@Override
	public boolean isPublished() {
		return _published;
	}

	@Override
	public void setPublished(boolean published) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_published = published;
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
	public String getFullCoursName() {
		if (_fullCoursName == null) {
			return "";
		}
		else {
			return _fullCoursName;
		}
	}

	@Override
	public void setFullCoursName(String fullCoursName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_fullCoursName = fullCoursName;
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_description = description;
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

	@JSON
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalGroupId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("groupId"));
	}

	@JSON
	@Override
	public boolean getIsManual() {
		return _isManual;
	}

	@JSON
	@Override
	public boolean isIsManual() {
		return _isManual;
	}

	@Override
	public void setIsManual(boolean isManual) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_isManual = isManual;
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
			0, CDTSession.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CDTSession toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CDTSession>
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
		CDTSessionImpl cdtSessionImpl = new CDTSessionImpl();

		cdtSessionImpl.setSessionId(getSessionId());
		cdtSessionImpl.setSessionStart(getSessionStart());
		cdtSessionImpl.setSessionEnd(getSessionEnd());
		cdtSessionImpl.setWeekId(getWeekId());
		cdtSessionImpl.setPublished(isPublished());
		cdtSessionImpl.setTitle(getTitle());
		cdtSessionImpl.setFullCoursName(getFullCoursName());
		cdtSessionImpl.setDescription(getDescription());
		cdtSessionImpl.setRoom(getRoom());
		cdtSessionImpl.setSubject(getSubject());
		cdtSessionImpl.setSchoolId(getSchoolId());
		cdtSessionImpl.setGroupId(getGroupId());
		cdtSessionImpl.setIsManual(isIsManual());

		cdtSessionImpl.resetOriginalValues();

		return cdtSessionImpl;
	}

	@Override
	public CDTSession cloneWithOriginalValues() {
		CDTSessionImpl cdtSessionImpl = new CDTSessionImpl();

		cdtSessionImpl.setSessionId(
			this.<Long>getColumnOriginalValue("sessionId"));
		cdtSessionImpl.setSessionStart(
			this.<Date>getColumnOriginalValue("sessionStart"));
		cdtSessionImpl.setSessionEnd(
			this.<Date>getColumnOriginalValue("sessionEnd"));
		cdtSessionImpl.setWeekId(this.<Long>getColumnOriginalValue("weekId"));
		cdtSessionImpl.setPublished(
			this.<Boolean>getColumnOriginalValue("published"));
		cdtSessionImpl.setTitle(this.<String>getColumnOriginalValue("title"));
		cdtSessionImpl.setFullCoursName(
			this.<String>getColumnOriginalValue("fullCoursName"));
		cdtSessionImpl.setDescription(
			this.<String>getColumnOriginalValue("description"));
		cdtSessionImpl.setRoom(this.<String>getColumnOriginalValue("room"));
		cdtSessionImpl.setSubject(
			this.<String>getColumnOriginalValue("subject"));
		cdtSessionImpl.setSchoolId(
			this.<Long>getColumnOriginalValue("schoolId"));
		cdtSessionImpl.setGroupId(this.<Long>getColumnOriginalValue("groupId"));
		cdtSessionImpl.setIsManual(
			this.<Boolean>getColumnOriginalValue("isManual"));

		return cdtSessionImpl;
	}

	@Override
	public int compareTo(CDTSession cdtSession) {
		long primaryKey = cdtSession.getPrimaryKey();

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

		if (!(object instanceof CDTSession)) {
			return false;
		}

		CDTSession cdtSession = (CDTSession)object;

		long primaryKey = cdtSession.getPrimaryKey();

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
	public CacheModel<CDTSession> toCacheModel() {
		CDTSessionCacheModel cdtSessionCacheModel = new CDTSessionCacheModel();

		cdtSessionCacheModel.sessionId = getSessionId();

		Date sessionStart = getSessionStart();

		if (sessionStart != null) {
			cdtSessionCacheModel.sessionStart = sessionStart.getTime();
		}
		else {
			cdtSessionCacheModel.sessionStart = Long.MIN_VALUE;
		}

		Date sessionEnd = getSessionEnd();

		if (sessionEnd != null) {
			cdtSessionCacheModel.sessionEnd = sessionEnd.getTime();
		}
		else {
			cdtSessionCacheModel.sessionEnd = Long.MIN_VALUE;
		}

		cdtSessionCacheModel.weekId = getWeekId();

		cdtSessionCacheModel.published = isPublished();

		cdtSessionCacheModel.title = getTitle();

		String title = cdtSessionCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			cdtSessionCacheModel.title = null;
		}

		cdtSessionCacheModel.fullCoursName = getFullCoursName();

		String fullCoursName = cdtSessionCacheModel.fullCoursName;

		if ((fullCoursName != null) && (fullCoursName.length() == 0)) {
			cdtSessionCacheModel.fullCoursName = null;
		}

		cdtSessionCacheModel.description = getDescription();

		String description = cdtSessionCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			cdtSessionCacheModel.description = null;
		}

		cdtSessionCacheModel.room = getRoom();

		String room = cdtSessionCacheModel.room;

		if ((room != null) && (room.length() == 0)) {
			cdtSessionCacheModel.room = null;
		}

		cdtSessionCacheModel.subject = getSubject();

		String subject = cdtSessionCacheModel.subject;

		if ((subject != null) && (subject.length() == 0)) {
			cdtSessionCacheModel.subject = null;
		}

		cdtSessionCacheModel.schoolId = getSchoolId();

		cdtSessionCacheModel.groupId = getGroupId();

		cdtSessionCacheModel.isManual = isIsManual();

		return cdtSessionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CDTSession, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CDTSession, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CDTSession, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((CDTSession)this);

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
		Map<String, Function<CDTSession, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CDTSession, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CDTSession, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((CDTSession)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, CDTSession>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _sessionId;
	private Date _sessionStart;
	private Date _sessionEnd;
	private long _weekId;
	private boolean _published;
	private String _title;
	private String _fullCoursName;
	private String _description;
	private String _room;
	private String _subject;
	private long _schoolId;
	private long _groupId;
	private boolean _isManual;

	public <T> T getColumnValue(String columnName) {
		Function<CDTSession, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CDTSession)this);
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

		_columnOriginalValues.put("sessionId", _sessionId);
		_columnOriginalValues.put("sessionStart", _sessionStart);
		_columnOriginalValues.put("sessionEnd", _sessionEnd);
		_columnOriginalValues.put("weekId", _weekId);
		_columnOriginalValues.put("published", _published);
		_columnOriginalValues.put("title", _title);
		_columnOriginalValues.put("fullCoursName", _fullCoursName);
		_columnOriginalValues.put("description", _description);
		_columnOriginalValues.put("room", _room);
		_columnOriginalValues.put("subject", _subject);
		_columnOriginalValues.put("schoolId", _schoolId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("isManual", _isManual);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("sessionId", 1L);

		columnBitmasks.put("sessionStart", 2L);

		columnBitmasks.put("sessionEnd", 4L);

		columnBitmasks.put("weekId", 8L);

		columnBitmasks.put("published", 16L);

		columnBitmasks.put("title", 32L);

		columnBitmasks.put("fullCoursName", 64L);

		columnBitmasks.put("description", 128L);

		columnBitmasks.put("room", 256L);

		columnBitmasks.put("subject", 512L);

		columnBitmasks.put("schoolId", 1024L);

		columnBitmasks.put("groupId", 2048L);

		columnBitmasks.put("isManual", 4096L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CDTSession _escapedModel;

}