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

package com.liferay.portal.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.ResourceActionModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

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
 * The base model implementation for the ResourceAction service. Represents a row in the &quot;ResourceAction&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>ResourceActionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ResourceActionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ResourceActionImpl
 * @generated
 */
public class ResourceActionModelImpl
	extends BaseModelImpl<ResourceAction> implements ResourceActionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a resource action model instance should use the <code>ResourceAction</code> interface instead.
	 */
	public static final String TABLE_NAME = "ResourceAction";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"resourceActionId", Types.BIGINT},
		{"name", Types.VARCHAR}, {"actionId", Types.VARCHAR},
		{"bitwiseValue", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("resourceActionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("actionId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("bitwiseValue", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table ResourceAction (mvccVersion LONG default 0 not null,resourceActionId LONG not null primary key,name VARCHAR(255) null,actionId VARCHAR(75) null,bitwiseValue LONG)";

	public static final String TABLE_SQL_DROP = "drop table ResourceAction";

	public static final String ORDER_BY_JPQL =
		" ORDER BY resourceAction.name ASC, resourceAction.bitwiseValue ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY ResourceAction.name ASC, ResourceAction.bitwiseValue ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean ENTITY_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean FINDER_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean COLUMN_BITMASK_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ACTIONID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long NAME_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long BITWISEVALUE_COLUMN_BITMASK = 4L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.ResourceAction"));

	public ResourceActionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _resourceActionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setResourceActionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _resourceActionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ResourceAction.class;
	}

	@Override
	public String getModelClassName() {
		return ResourceAction.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ResourceAction, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ResourceAction, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ResourceAction, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ResourceAction)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ResourceAction, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ResourceAction, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ResourceAction)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ResourceAction, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ResourceAction, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<ResourceAction, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ResourceAction, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ResourceAction, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<ResourceAction, Object>>();
		Map<String, BiConsumer<ResourceAction, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<ResourceAction, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", ResourceAction::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<ResourceAction, Long>)ResourceAction::setMvccVersion);
		attributeGetterFunctions.put(
			"resourceActionId", ResourceAction::getResourceActionId);
		attributeSetterBiConsumers.put(
			"resourceActionId",
			(BiConsumer<ResourceAction, Long>)
				ResourceAction::setResourceActionId);
		attributeGetterFunctions.put("name", ResourceAction::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<ResourceAction, String>)ResourceAction::setName);
		attributeGetterFunctions.put("actionId", ResourceAction::getActionId);
		attributeSetterBiConsumers.put(
			"actionId",
			(BiConsumer<ResourceAction, String>)ResourceAction::setActionId);
		attributeGetterFunctions.put(
			"bitwiseValue", ResourceAction::getBitwiseValue);
		attributeSetterBiConsumers.put(
			"bitwiseValue",
			(BiConsumer<ResourceAction, Long>)ResourceAction::setBitwiseValue);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_mvccVersion = mvccVersion;
	}

	@Override
	public long getResourceActionId() {
		return _resourceActionId;
	}

	@Override
	public void setResourceActionId(long resourceActionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_resourceActionId = resourceActionId;
	}

	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_name = name;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalName() {
		return getColumnOriginalValue("name");
	}

	@Override
	public String getActionId() {
		if (_actionId == null) {
			return "";
		}
		else {
			return _actionId;
		}
	}

	@Override
	public void setActionId(String actionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_actionId = actionId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalActionId() {
		return getColumnOriginalValue("actionId");
	}

	@Override
	public long getBitwiseValue() {
		return _bitwiseValue;
	}

	@Override
	public void setBitwiseValue(long bitwiseValue) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_bitwiseValue = bitwiseValue;
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
			0, ResourceAction.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ResourceAction toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ResourceAction>
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
		ResourceActionImpl resourceActionImpl = new ResourceActionImpl();

		resourceActionImpl.setMvccVersion(getMvccVersion());
		resourceActionImpl.setResourceActionId(getResourceActionId());
		resourceActionImpl.setName(getName());
		resourceActionImpl.setActionId(getActionId());
		resourceActionImpl.setBitwiseValue(getBitwiseValue());

		resourceActionImpl.resetOriginalValues();

		return resourceActionImpl;
	}

	@Override
	public ResourceAction cloneWithOriginalValues() {
		ResourceActionImpl resourceActionImpl = new ResourceActionImpl();

		resourceActionImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		resourceActionImpl.setResourceActionId(
			this.<Long>getColumnOriginalValue("resourceActionId"));
		resourceActionImpl.setName(this.<String>getColumnOriginalValue("name"));
		resourceActionImpl.setActionId(
			this.<String>getColumnOriginalValue("actionId"));
		resourceActionImpl.setBitwiseValue(
			this.<Long>getColumnOriginalValue("bitwiseValue"));

		return resourceActionImpl;
	}

	@Override
	public int compareTo(ResourceAction resourceAction) {
		int value = 0;

		value = getName().compareTo(resourceAction.getName());

		if (value != 0) {
			return value;
		}

		if (getBitwiseValue() < resourceAction.getBitwiseValue()) {
			value = -1;
		}
		else if (getBitwiseValue() > resourceAction.getBitwiseValue()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ResourceAction)) {
			return false;
		}

		ResourceAction resourceAction = (ResourceAction)object;

		long primaryKey = resourceAction.getPrimaryKey();

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
		return ENTITY_CACHE_ENABLED;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<ResourceAction> toCacheModel() {
		ResourceActionCacheModel resourceActionCacheModel =
			new ResourceActionCacheModel();

		resourceActionCacheModel.mvccVersion = getMvccVersion();

		resourceActionCacheModel.resourceActionId = getResourceActionId();

		resourceActionCacheModel.name = getName();

		String name = resourceActionCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			resourceActionCacheModel.name = null;
		}

		resourceActionCacheModel.actionId = getActionId();

		String actionId = resourceActionCacheModel.actionId;

		if ((actionId != null) && (actionId.length() == 0)) {
			resourceActionCacheModel.actionId = null;
		}

		resourceActionCacheModel.bitwiseValue = getBitwiseValue();

		return resourceActionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ResourceAction, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ResourceAction, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ResourceAction, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((ResourceAction)this);

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
		Map<String, Function<ResourceAction, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<ResourceAction, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ResourceAction, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((ResourceAction)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, ResourceAction>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					ResourceAction.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _resourceActionId;
	private String _name;
	private String _actionId;
	private long _bitwiseValue;

	public <T> T getColumnValue(String columnName) {
		Function<ResourceAction, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((ResourceAction)this);
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

		_columnOriginalValues.put("mvccVersion", _mvccVersion);
		_columnOriginalValues.put("resourceActionId", _resourceActionId);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("actionId", _actionId);
		_columnOriginalValues.put("bitwiseValue", _bitwiseValue);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("resourceActionId", 2L);

		columnBitmasks.put("name", 4L);

		columnBitmasks.put("actionId", 8L);

		columnBitmasks.put("bitwiseValue", 16L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private ResourceAction _escapedModel;

}