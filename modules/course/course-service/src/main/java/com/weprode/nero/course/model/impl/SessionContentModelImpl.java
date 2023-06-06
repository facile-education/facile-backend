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
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.nero.course.model.SessionContent;
import com.weprode.nero.course.model.SessionContentModel;
import com.weprode.nero.course.model.SessionContentSoap;

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
 * The base model implementation for the SessionContent service. Represents a row in the &quot;Course_SessionContent&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SessionContentModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SessionContentImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SessionContentImpl
 * @generated
 */
@JSON(strict = true)
public class SessionContentModelImpl
	extends BaseModelImpl<SessionContent> implements SessionContentModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a session content model instance should use the <code>SessionContent</code> interface instead.
	 */
	public static final String TABLE_NAME = "Course_SessionContent";

	public static final Object[][] TABLE_COLUMNS = {
		{"sessionId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"teacherId", Types.BIGINT}, {"title", Types.VARCHAR},
		{"modificationDate", Types.TIMESTAMP},
		{"publicationDate", Types.TIMESTAMP}, {"isDraft", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("sessionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("teacherId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("modificationDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("publicationDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("isDraft", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Course_SessionContent (sessionId LONG not null primary key,companyId LONG,teacherId LONG,title VARCHAR(75) null,modificationDate DATE null,publicationDate DATE null,isDraft BOOLEAN)";

	public static final String TABLE_SQL_DROP =
		"drop table Course_SessionContent";

	public static final String ORDER_BY_JPQL =
		" ORDER BY sessionContent.sessionId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Course_SessionContent.sessionId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SESSIONID_COLUMN_BITMASK = 1L;

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
	public static SessionContent toModel(SessionContentSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		SessionContent model = new SessionContentImpl();

		model.setSessionId(soapModel.getSessionId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setTeacherId(soapModel.getTeacherId());
		model.setTitle(soapModel.getTitle());
		model.setModificationDate(soapModel.getModificationDate());
		model.setPublicationDate(soapModel.getPublicationDate());
		model.setIsDraft(soapModel.isIsDraft());

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
	public static List<SessionContent> toModels(
		SessionContentSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<SessionContent> models = new ArrayList<SessionContent>(
			soapModels.length);

		for (SessionContentSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public SessionContentModelImpl() {
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
		return SessionContent.class;
	}

	@Override
	public String getModelClassName() {
		return SessionContent.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SessionContent, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SessionContent, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SessionContent, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((SessionContent)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SessionContent, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SessionContent, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SessionContent)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SessionContent, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SessionContent, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, SessionContent>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			SessionContent.class.getClassLoader(), SessionContent.class,
			ModelWrapper.class);

		try {
			Constructor<SessionContent> constructor =
				(Constructor<SessionContent>)proxyClass.getConstructor(
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

	private static final Map<String, Function<SessionContent, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<SessionContent, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<SessionContent, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<SessionContent, Object>>();
		Map<String, BiConsumer<SessionContent, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<SessionContent, ?>>();

		attributeGetterFunctions.put("sessionId", SessionContent::getSessionId);
		attributeSetterBiConsumers.put(
			"sessionId",
			(BiConsumer<SessionContent, Long>)SessionContent::setSessionId);
		attributeGetterFunctions.put("companyId", SessionContent::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<SessionContent, Long>)SessionContent::setCompanyId);
		attributeGetterFunctions.put("teacherId", SessionContent::getTeacherId);
		attributeSetterBiConsumers.put(
			"teacherId",
			(BiConsumer<SessionContent, Long>)SessionContent::setTeacherId);
		attributeGetterFunctions.put("title", SessionContent::getTitle);
		attributeSetterBiConsumers.put(
			"title",
			(BiConsumer<SessionContent, String>)SessionContent::setTitle);
		attributeGetterFunctions.put(
			"modificationDate", SessionContent::getModificationDate);
		attributeSetterBiConsumers.put(
			"modificationDate",
			(BiConsumer<SessionContent, Date>)
				SessionContent::setModificationDate);
		attributeGetterFunctions.put(
			"publicationDate", SessionContent::getPublicationDate);
		attributeSetterBiConsumers.put(
			"publicationDate",
			(BiConsumer<SessionContent, Date>)
				SessionContent::setPublicationDate);
		attributeGetterFunctions.put("isDraft", SessionContent::getIsDraft);
		attributeSetterBiConsumers.put(
			"isDraft",
			(BiConsumer<SessionContent, Boolean>)SessionContent::setIsDraft);

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
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_companyId = companyId;
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
			getCompanyId(), SessionContent.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SessionContent toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SessionContent>
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
		SessionContentImpl sessionContentImpl = new SessionContentImpl();

		sessionContentImpl.setSessionId(getSessionId());
		sessionContentImpl.setCompanyId(getCompanyId());
		sessionContentImpl.setTeacherId(getTeacherId());
		sessionContentImpl.setTitle(getTitle());
		sessionContentImpl.setModificationDate(getModificationDate());
		sessionContentImpl.setPublicationDate(getPublicationDate());
		sessionContentImpl.setIsDraft(isIsDraft());

		sessionContentImpl.resetOriginalValues();

		return sessionContentImpl;
	}

	@Override
	public SessionContent cloneWithOriginalValues() {
		SessionContentImpl sessionContentImpl = new SessionContentImpl();

		sessionContentImpl.setSessionId(
			this.<Long>getColumnOriginalValue("sessionId"));
		sessionContentImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		sessionContentImpl.setTeacherId(
			this.<Long>getColumnOriginalValue("teacherId"));
		sessionContentImpl.setTitle(
			this.<String>getColumnOriginalValue("title"));
		sessionContentImpl.setModificationDate(
			this.<Date>getColumnOriginalValue("modificationDate"));
		sessionContentImpl.setPublicationDate(
			this.<Date>getColumnOriginalValue("publicationDate"));
		sessionContentImpl.setIsDraft(
			this.<Boolean>getColumnOriginalValue("isDraft"));

		return sessionContentImpl;
	}

	@Override
	public int compareTo(SessionContent sessionContent) {
		long primaryKey = sessionContent.getPrimaryKey();

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

		if (!(object instanceof SessionContent)) {
			return false;
		}

		SessionContent sessionContent = (SessionContent)object;

		long primaryKey = sessionContent.getPrimaryKey();

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
	public CacheModel<SessionContent> toCacheModel() {
		SessionContentCacheModel sessionContentCacheModel =
			new SessionContentCacheModel();

		sessionContentCacheModel.sessionId = getSessionId();

		sessionContentCacheModel.companyId = getCompanyId();

		sessionContentCacheModel.teacherId = getTeacherId();

		sessionContentCacheModel.title = getTitle();

		String title = sessionContentCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			sessionContentCacheModel.title = null;
		}

		Date modificationDate = getModificationDate();

		if (modificationDate != null) {
			sessionContentCacheModel.modificationDate =
				modificationDate.getTime();
		}
		else {
			sessionContentCacheModel.modificationDate = Long.MIN_VALUE;
		}

		Date publicationDate = getPublicationDate();

		if (publicationDate != null) {
			sessionContentCacheModel.publicationDate =
				publicationDate.getTime();
		}
		else {
			sessionContentCacheModel.publicationDate = Long.MIN_VALUE;
		}

		sessionContentCacheModel.isDraft = isIsDraft();

		return sessionContentCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SessionContent, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SessionContent, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SessionContent, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((SessionContent)this);

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
		Map<String, Function<SessionContent, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<SessionContent, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SessionContent, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((SessionContent)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, SessionContent>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _sessionId;
	private long _companyId;
	private long _teacherId;
	private String _title;
	private Date _modificationDate;
	private Date _publicationDate;
	private boolean _isDraft;

	public <T> T getColumnValue(String columnName) {
		Function<SessionContent, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((SessionContent)this);
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
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("teacherId", _teacherId);
		_columnOriginalValues.put("title", _title);
		_columnOriginalValues.put("modificationDate", _modificationDate);
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

		columnBitmasks.put("sessionId", 1L);

		columnBitmasks.put("companyId", 2L);

		columnBitmasks.put("teacherId", 4L);

		columnBitmasks.put("title", 8L);

		columnBitmasks.put("modificationDate", 16L);

		columnBitmasks.put("publicationDate", 32L);

		columnBitmasks.put("isDraft", 64L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private SessionContent _escapedModel;

}