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

package com.weprode.facile.statistic.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.facile.statistic.model.LoolStat;
import com.weprode.facile.statistic.model.LoolStatModel;

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
 * The base model implementation for the LoolStat service. Represents a row in the &quot;Statistics_LoolStat&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>LoolStatModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LoolStatImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LoolStatImpl
 * @generated
 */
public class LoolStatModelImpl
	extends BaseModelImpl<LoolStat> implements LoolStatModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a lool stat model instance should use the <code>LoolStat</code> interface instead.
	 */
	public static final String TABLE_NAME = "Statistics_LoolStat";

	public static final Object[][] TABLE_COLUMNS = {
		{"statId", Types.BIGINT}, {"objectId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"saveAction", Types.BOOLEAN},
		{"actionDate", Types.TIMESTAMP}, {"type_", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("statId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("objectId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("saveAction", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("actionDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Statistics_LoolStat (statId LONG not null primary key,objectId LONG,userId LONG,saveAction BOOLEAN,actionDate DATE null,type_ INTEGER)";

	public static final String TABLE_SQL_DROP =
		"drop table Statistics_LoolStat";

	public static final String ORDER_BY_JPQL = " ORDER BY loolStat.statId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Statistics_LoolStat.statId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long STATID_COLUMN_BITMASK = 2L;

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

	public LoolStatModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _statId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setStatId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _statId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return LoolStat.class;
	}

	@Override
	public String getModelClassName() {
		return LoolStat.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<LoolStat, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<LoolStat, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LoolStat, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((LoolStat)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<LoolStat, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<LoolStat, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(LoolStat)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<LoolStat, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<LoolStat, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<LoolStat, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<LoolStat, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<LoolStat, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<LoolStat, Object>>();
		Map<String, BiConsumer<LoolStat, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<LoolStat, ?>>();

		attributeGetterFunctions.put("statId", LoolStat::getStatId);
		attributeSetterBiConsumers.put(
			"statId", (BiConsumer<LoolStat, Long>)LoolStat::setStatId);
		attributeGetterFunctions.put("objectId", LoolStat::getObjectId);
		attributeSetterBiConsumers.put(
			"objectId", (BiConsumer<LoolStat, Long>)LoolStat::setObjectId);
		attributeGetterFunctions.put("userId", LoolStat::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<LoolStat, Long>)LoolStat::setUserId);
		attributeGetterFunctions.put("saveAction", LoolStat::getSaveAction);
		attributeSetterBiConsumers.put(
			"saveAction",
			(BiConsumer<LoolStat, Boolean>)LoolStat::setSaveAction);
		attributeGetterFunctions.put("actionDate", LoolStat::getActionDate);
		attributeSetterBiConsumers.put(
			"actionDate", (BiConsumer<LoolStat, Date>)LoolStat::setActionDate);
		attributeGetterFunctions.put("type", LoolStat::getType);
		attributeSetterBiConsumers.put(
			"type", (BiConsumer<LoolStat, Integer>)LoolStat::setType);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getStatId() {
		return _statId;
	}

	@Override
	public void setStatId(long statId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_statId = statId;
	}

	@Override
	public long getObjectId() {
		return _objectId;
	}

	@Override
	public void setObjectId(long objectId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_objectId = objectId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalUserId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("userId"));
	}

	@Override
	public boolean getSaveAction() {
		return _saveAction;
	}

	@Override
	public boolean isSaveAction() {
		return _saveAction;
	}

	@Override
	public void setSaveAction(boolean saveAction) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_saveAction = saveAction;
	}

	@Override
	public Date getActionDate() {
		return _actionDate;
	}

	@Override
	public void setActionDate(Date actionDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_actionDate = actionDate;
	}

	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_type = type;
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
			0, LoolStat.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public LoolStat toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, LoolStat>
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
		LoolStatImpl loolStatImpl = new LoolStatImpl();

		loolStatImpl.setStatId(getStatId());
		loolStatImpl.setObjectId(getObjectId());
		loolStatImpl.setUserId(getUserId());
		loolStatImpl.setSaveAction(isSaveAction());
		loolStatImpl.setActionDate(getActionDate());
		loolStatImpl.setType(getType());

		loolStatImpl.resetOriginalValues();

		return loolStatImpl;
	}

	@Override
	public LoolStat cloneWithOriginalValues() {
		LoolStatImpl loolStatImpl = new LoolStatImpl();

		loolStatImpl.setStatId(this.<Long>getColumnOriginalValue("statId"));
		loolStatImpl.setObjectId(this.<Long>getColumnOriginalValue("objectId"));
		loolStatImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		loolStatImpl.setSaveAction(
			this.<Boolean>getColumnOriginalValue("saveAction"));
		loolStatImpl.setActionDate(
			this.<Date>getColumnOriginalValue("actionDate"));
		loolStatImpl.setType(this.<Integer>getColumnOriginalValue("type_"));

		return loolStatImpl;
	}

	@Override
	public int compareTo(LoolStat loolStat) {
		long primaryKey = loolStat.getPrimaryKey();

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

		if (!(object instanceof LoolStat)) {
			return false;
		}

		LoolStat loolStat = (LoolStat)object;

		long primaryKey = loolStat.getPrimaryKey();

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
	public CacheModel<LoolStat> toCacheModel() {
		LoolStatCacheModel loolStatCacheModel = new LoolStatCacheModel();

		loolStatCacheModel.statId = getStatId();

		loolStatCacheModel.objectId = getObjectId();

		loolStatCacheModel.userId = getUserId();

		loolStatCacheModel.saveAction = isSaveAction();

		Date actionDate = getActionDate();

		if (actionDate != null) {
			loolStatCacheModel.actionDate = actionDate.getTime();
		}
		else {
			loolStatCacheModel.actionDate = Long.MIN_VALUE;
		}

		loolStatCacheModel.type = getType();

		return loolStatCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<LoolStat, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<LoolStat, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LoolStat, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((LoolStat)this);

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

		private static final Function<InvocationHandler, LoolStat>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					LoolStat.class, ModelWrapper.class);

	}

	private long _statId;
	private long _objectId;
	private long _userId;
	private boolean _saveAction;
	private Date _actionDate;
	private int _type;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<LoolStat, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((LoolStat)this);
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

		_columnOriginalValues.put("statId", _statId);
		_columnOriginalValues.put("objectId", _objectId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("saveAction", _saveAction);
		_columnOriginalValues.put("actionDate", _actionDate);
		_columnOriginalValues.put("type_", _type);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("type_", "type");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("statId", 1L);

		columnBitmasks.put("objectId", 2L);

		columnBitmasks.put("userId", 4L);

		columnBitmasks.put("saveAction", 8L);

		columnBitmasks.put("actionDate", 16L);

		columnBitmasks.put("type_", 32L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private LoolStat _escapedModel;

}