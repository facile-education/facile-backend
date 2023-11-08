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

package com.weprode.facile.help.model.impl;

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

import com.weprode.facile.help.model.HelpRelation;
import com.weprode.facile.help.model.HelpRelationModel;

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
 * The base model implementation for the HelpRelation service. Represents a row in the &quot;Help_HelpRelation&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>HelpRelationModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link HelpRelationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpRelationImpl
 * @generated
 */
@JSON(strict = true)
public class HelpRelationModelImpl
	extends BaseModelImpl<HelpRelation> implements HelpRelationModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a help relation model instance should use the <code>HelpRelation</code> interface instead.
	 */
	public static final String TABLE_NAME = "Help_HelpRelation";

	public static final Object[][] TABLE_COLUMNS = {
		{"relationId", Types.BIGINT}, {"itemId", Types.BIGINT},
		{"relatedItemId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("relationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("itemId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("relatedItemId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Help_HelpRelation (relationId LONG not null primary key,itemId LONG,relatedItemId LONG)";

	public static final String TABLE_SQL_DROP = "drop table Help_HelpRelation";

	public static final String ORDER_BY_JPQL =
		" ORDER BY helpRelation.relationId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Help_HelpRelation.relationId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ITEMID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long RELATIONID_COLUMN_BITMASK = 2L;

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

	public HelpRelationModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _relationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setRelationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _relationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return HelpRelation.class;
	}

	@Override
	public String getModelClassName() {
		return HelpRelation.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<HelpRelation, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<HelpRelation, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<HelpRelation, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((HelpRelation)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<HelpRelation, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<HelpRelation, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(HelpRelation)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<HelpRelation, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<HelpRelation, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<HelpRelation, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<HelpRelation, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<HelpRelation, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<HelpRelation, Object>>();
		Map<String, BiConsumer<HelpRelation, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<HelpRelation, ?>>();

		attributeGetterFunctions.put("relationId", HelpRelation::getRelationId);
		attributeSetterBiConsumers.put(
			"relationId",
			(BiConsumer<HelpRelation, Long>)HelpRelation::setRelationId);
		attributeGetterFunctions.put("itemId", HelpRelation::getItemId);
		attributeSetterBiConsumers.put(
			"itemId", (BiConsumer<HelpRelation, Long>)HelpRelation::setItemId);
		attributeGetterFunctions.put(
			"relatedItemId", HelpRelation::getRelatedItemId);
		attributeSetterBiConsumers.put(
			"relatedItemId",
			(BiConsumer<HelpRelation, Long>)HelpRelation::setRelatedItemId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getRelationId() {
		return _relationId;
	}

	@Override
	public void setRelationId(long relationId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_relationId = relationId;
	}

	@JSON
	@Override
	public long getItemId() {
		return _itemId;
	}

	@Override
	public void setItemId(long itemId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_itemId = itemId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalItemId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("itemId"));
	}

	@JSON
	@Override
	public long getRelatedItemId() {
		return _relatedItemId;
	}

	@Override
	public void setRelatedItemId(long relatedItemId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_relatedItemId = relatedItemId;
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
			0, HelpRelation.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public HelpRelation toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, HelpRelation>
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
		HelpRelationImpl helpRelationImpl = new HelpRelationImpl();

		helpRelationImpl.setRelationId(getRelationId());
		helpRelationImpl.setItemId(getItemId());
		helpRelationImpl.setRelatedItemId(getRelatedItemId());

		helpRelationImpl.resetOriginalValues();

		return helpRelationImpl;
	}

	@Override
	public HelpRelation cloneWithOriginalValues() {
		HelpRelationImpl helpRelationImpl = new HelpRelationImpl();

		helpRelationImpl.setRelationId(
			this.<Long>getColumnOriginalValue("relationId"));
		helpRelationImpl.setItemId(this.<Long>getColumnOriginalValue("itemId"));
		helpRelationImpl.setRelatedItemId(
			this.<Long>getColumnOriginalValue("relatedItemId"));

		return helpRelationImpl;
	}

	@Override
	public int compareTo(HelpRelation helpRelation) {
		long primaryKey = helpRelation.getPrimaryKey();

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

		if (!(object instanceof HelpRelation)) {
			return false;
		}

		HelpRelation helpRelation = (HelpRelation)object;

		long primaryKey = helpRelation.getPrimaryKey();

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
	public CacheModel<HelpRelation> toCacheModel() {
		HelpRelationCacheModel helpRelationCacheModel =
			new HelpRelationCacheModel();

		helpRelationCacheModel.relationId = getRelationId();

		helpRelationCacheModel.itemId = getItemId();

		helpRelationCacheModel.relatedItemId = getRelatedItemId();

		return helpRelationCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<HelpRelation, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<HelpRelation, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<HelpRelation, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((HelpRelation)this);

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

		private static final Function<InvocationHandler, HelpRelation>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					HelpRelation.class, ModelWrapper.class);

	}

	private long _relationId;
	private long _itemId;
	private long _relatedItemId;

	public <T> T getColumnValue(String columnName) {
		Function<HelpRelation, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((HelpRelation)this);
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

		_columnOriginalValues.put("relationId", _relationId);
		_columnOriginalValues.put("itemId", _itemId);
		_columnOriginalValues.put("relatedItemId", _relatedItemId);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("relationId", 1L);

		columnBitmasks.put("itemId", 2L);

		columnBitmasks.put("relatedItemId", 4L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private HelpRelation _escapedModel;

}