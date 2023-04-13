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

package com.weprode.nero.application.model.impl;

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

import com.weprode.nero.application.model.Broadcast;
import com.weprode.nero.application.model.BroadcastModel;
import com.weprode.nero.application.model.BroadcastSoap;

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
 * The base model implementation for the Broadcast service. Represents a row in the &quot;Application_Broadcast&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>BroadcastModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link BroadcastImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BroadcastImpl
 * @generated
 */
@JSON(strict = true)
public class BroadcastModelImpl
	extends BaseModelImpl<Broadcast> implements BroadcastModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a broadcast model instance should use the <code>Broadcast</code> interface instead.
	 */
	public static final String TABLE_NAME = "Application_Broadcast";

	public static final Object[][] TABLE_COLUMNS = {
		{"broadcastId", Types.BIGINT}, {"schoolId", Types.BIGINT},
		{"applicationId", Types.BIGINT}, {"isBroadcasted", Types.BOOLEAN},
		{"applicationUrl", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("broadcastId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("schoolId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("applicationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("isBroadcasted", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("applicationUrl", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Application_Broadcast (broadcastId LONG not null primary key,schoolId LONG,applicationId LONG,isBroadcasted BOOLEAN,applicationUrl VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table Application_Broadcast";

	public static final String ORDER_BY_JPQL =
		" ORDER BY broadcast.broadcastId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Application_Broadcast.broadcastId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long APPLICATIONID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SCHOOLID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long BROADCASTID_COLUMN_BITMASK = 4L;

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
	public static Broadcast toModel(BroadcastSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Broadcast model = new BroadcastImpl();

		model.setBroadcastId(soapModel.getBroadcastId());
		model.setSchoolId(soapModel.getSchoolId());
		model.setApplicationId(soapModel.getApplicationId());
		model.setIsBroadcasted(soapModel.isIsBroadcasted());
		model.setApplicationUrl(soapModel.getApplicationUrl());

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
	public static List<Broadcast> toModels(BroadcastSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Broadcast> models = new ArrayList<Broadcast>(soapModels.length);

		for (BroadcastSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public BroadcastModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _broadcastId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setBroadcastId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _broadcastId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Broadcast.class;
	}

	@Override
	public String getModelClassName() {
		return Broadcast.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Broadcast, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Broadcast, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Broadcast, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Broadcast)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Broadcast, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Broadcast, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Broadcast)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Broadcast, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Broadcast, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Broadcast>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Broadcast.class.getClassLoader(), Broadcast.class,
			ModelWrapper.class);

		try {
			Constructor<Broadcast> constructor =
				(Constructor<Broadcast>)proxyClass.getConstructor(
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

	private static final Map<String, Function<Broadcast, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Broadcast, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Broadcast, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Broadcast, Object>>();
		Map<String, BiConsumer<Broadcast, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Broadcast, ?>>();

		attributeGetterFunctions.put("broadcastId", Broadcast::getBroadcastId);
		attributeSetterBiConsumers.put(
			"broadcastId",
			(BiConsumer<Broadcast, Long>)Broadcast::setBroadcastId);
		attributeGetterFunctions.put("schoolId", Broadcast::getSchoolId);
		attributeSetterBiConsumers.put(
			"schoolId", (BiConsumer<Broadcast, Long>)Broadcast::setSchoolId);
		attributeGetterFunctions.put(
			"applicationId", Broadcast::getApplicationId);
		attributeSetterBiConsumers.put(
			"applicationId",
			(BiConsumer<Broadcast, Long>)Broadcast::setApplicationId);
		attributeGetterFunctions.put(
			"isBroadcasted", Broadcast::getIsBroadcasted);
		attributeSetterBiConsumers.put(
			"isBroadcasted",
			(BiConsumer<Broadcast, Boolean>)Broadcast::setIsBroadcasted);
		attributeGetterFunctions.put(
			"applicationUrl", Broadcast::getApplicationUrl);
		attributeSetterBiConsumers.put(
			"applicationUrl",
			(BiConsumer<Broadcast, String>)Broadcast::setApplicationUrl);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getBroadcastId() {
		return _broadcastId;
	}

	@Override
	public void setBroadcastId(long broadcastId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_broadcastId = broadcastId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalSchoolId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("schoolId"));
	}

	@JSON
	@Override
	public long getApplicationId() {
		return _applicationId;
	}

	@Override
	public void setApplicationId(long applicationId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_applicationId = applicationId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalApplicationId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("applicationId"));
	}

	@JSON
	@Override
	public boolean getIsBroadcasted() {
		return _isBroadcasted;
	}

	@JSON
	@Override
	public boolean isIsBroadcasted() {
		return _isBroadcasted;
	}

	@Override
	public void setIsBroadcasted(boolean isBroadcasted) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_isBroadcasted = isBroadcasted;
	}

	@JSON
	@Override
	public String getApplicationUrl() {
		if (_applicationUrl == null) {
			return "";
		}
		else {
			return _applicationUrl;
		}
	}

	@Override
	public void setApplicationUrl(String applicationUrl) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_applicationUrl = applicationUrl;
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
			0, Broadcast.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Broadcast toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Broadcast>
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
		BroadcastImpl broadcastImpl = new BroadcastImpl();

		broadcastImpl.setBroadcastId(getBroadcastId());
		broadcastImpl.setSchoolId(getSchoolId());
		broadcastImpl.setApplicationId(getApplicationId());
		broadcastImpl.setIsBroadcasted(isIsBroadcasted());
		broadcastImpl.setApplicationUrl(getApplicationUrl());

		broadcastImpl.resetOriginalValues();

		return broadcastImpl;
	}

	@Override
	public Broadcast cloneWithOriginalValues() {
		BroadcastImpl broadcastImpl = new BroadcastImpl();

		broadcastImpl.setBroadcastId(
			this.<Long>getColumnOriginalValue("broadcastId"));
		broadcastImpl.setSchoolId(
			this.<Long>getColumnOriginalValue("schoolId"));
		broadcastImpl.setApplicationId(
			this.<Long>getColumnOriginalValue("applicationId"));
		broadcastImpl.setIsBroadcasted(
			this.<Boolean>getColumnOriginalValue("isBroadcasted"));
		broadcastImpl.setApplicationUrl(
			this.<String>getColumnOriginalValue("applicationUrl"));

		return broadcastImpl;
	}

	@Override
	public int compareTo(Broadcast broadcast) {
		long primaryKey = broadcast.getPrimaryKey();

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

		if (!(object instanceof Broadcast)) {
			return false;
		}

		Broadcast broadcast = (Broadcast)object;

		long primaryKey = broadcast.getPrimaryKey();

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
	public CacheModel<Broadcast> toCacheModel() {
		BroadcastCacheModel broadcastCacheModel = new BroadcastCacheModel();

		broadcastCacheModel.broadcastId = getBroadcastId();

		broadcastCacheModel.schoolId = getSchoolId();

		broadcastCacheModel.applicationId = getApplicationId();

		broadcastCacheModel.isBroadcasted = isIsBroadcasted();

		broadcastCacheModel.applicationUrl = getApplicationUrl();

		String applicationUrl = broadcastCacheModel.applicationUrl;

		if ((applicationUrl != null) && (applicationUrl.length() == 0)) {
			broadcastCacheModel.applicationUrl = null;
		}

		return broadcastCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Broadcast, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Broadcast, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Broadcast, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((Broadcast)this);

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
		Map<String, Function<Broadcast, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Broadcast, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Broadcast, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Broadcast)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Broadcast>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _broadcastId;
	private long _schoolId;
	private long _applicationId;
	private boolean _isBroadcasted;
	private String _applicationUrl;

	public <T> T getColumnValue(String columnName) {
		Function<Broadcast, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((Broadcast)this);
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

		_columnOriginalValues.put("broadcastId", _broadcastId);
		_columnOriginalValues.put("schoolId", _schoolId);
		_columnOriginalValues.put("applicationId", _applicationId);
		_columnOriginalValues.put("isBroadcasted", _isBroadcasted);
		_columnOriginalValues.put("applicationUrl", _applicationUrl);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("broadcastId", 1L);

		columnBitmasks.put("schoolId", 2L);

		columnBitmasks.put("applicationId", 4L);

		columnBitmasks.put("isBroadcasted", 8L);

		columnBitmasks.put("applicationUrl", 16L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private Broadcast _escapedModel;

}