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

package com.weprode.facile.user.model.impl;

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
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.facile.user.model.LDAPMapping;
import com.weprode.facile.user.model.LDAPMappingModel;

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
 * The base model implementation for the LDAPMapping service. Represents a row in the &quot;User_LDAPMapping&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>LDAPMappingModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LDAPMappingImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LDAPMappingImpl
 * @generated
 */
public class LDAPMappingModelImpl
	extends BaseModelImpl<LDAPMapping> implements LDAPMappingModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ldap mapping model instance should use the <code>LDAPMapping</code> interface instead.
	 */
	public static final String TABLE_NAME = "User_LDAPMapping";

	public static final Object[][] TABLE_COLUMNS = {
		{"UserId", Types.BIGINT}, {"EntPersonJointure", Types.VARCHAR},
		{"UID", Types.VARCHAR}, {"INE", Types.VARCHAR},
		{"EntEleveStructRattachId", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("UserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("EntPersonJointure", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("UID", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("INE", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("EntEleveStructRattachId", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table User_LDAPMapping (UserId LONG not null primary key,EntPersonJointure VARCHAR(75) null,UID VARCHAR(75) null,INE VARCHAR(75) null,EntEleveStructRattachId VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table User_LDAPMapping";

	public static final String ORDER_BY_JPQL =
		" ORDER BY ldapMapping.UserId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY User_LDAPMapping.UserId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ENTELEVESTRUCTRATTACHID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ENTPERSONJOINTURE_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 8L;

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

	public LDAPMappingModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _UserId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setUserId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _UserId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return LDAPMapping.class;
	}

	@Override
	public String getModelClassName() {
		return LDAPMapping.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<LDAPMapping, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<LDAPMapping, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LDAPMapping, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((LDAPMapping)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<LDAPMapping, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<LDAPMapping, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(LDAPMapping)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<LDAPMapping, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<LDAPMapping, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<LDAPMapping, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<LDAPMapping, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<LDAPMapping, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<LDAPMapping, Object>>();
		Map<String, BiConsumer<LDAPMapping, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<LDAPMapping, ?>>();

		attributeGetterFunctions.put("UserId", LDAPMapping::getUserId);
		attributeSetterBiConsumers.put(
			"UserId", (BiConsumer<LDAPMapping, Long>)LDAPMapping::setUserId);
		attributeGetterFunctions.put(
			"EntPersonJointure", LDAPMapping::getEntPersonJointure);
		attributeSetterBiConsumers.put(
			"EntPersonJointure",
			(BiConsumer<LDAPMapping, String>)LDAPMapping::setEntPersonJointure);
		attributeGetterFunctions.put("UID", LDAPMapping::getUID);
		attributeSetterBiConsumers.put(
			"UID", (BiConsumer<LDAPMapping, String>)LDAPMapping::setUID);
		attributeGetterFunctions.put("INE", LDAPMapping::getINE);
		attributeSetterBiConsumers.put(
			"INE", (BiConsumer<LDAPMapping, String>)LDAPMapping::setINE);
		attributeGetterFunctions.put(
			"EntEleveStructRattachId", LDAPMapping::getEntEleveStructRattachId);
		attributeSetterBiConsumers.put(
			"EntEleveStructRattachId",
			(BiConsumer<LDAPMapping, String>)
				LDAPMapping::setEntEleveStructRattachId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getUserId() {
		return _UserId;
	}

	@Override
	public void setUserId(long UserId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_UserId = UserId;
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
	public void setUserUuid(String UserUuid) {
	}

	@Override
	public String getEntPersonJointure() {
		if (_EntPersonJointure == null) {
			return "";
		}
		else {
			return _EntPersonJointure;
		}
	}

	@Override
	public void setEntPersonJointure(String EntPersonJointure) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_EntPersonJointure = EntPersonJointure;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalEntPersonJointure() {
		return getColumnOriginalValue("EntPersonJointure");
	}

	@Override
	public String getUID() {
		if (_UID == null) {
			return "";
		}
		else {
			return _UID;
		}
	}

	@Override
	public void setUID(String UID) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_UID = UID;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUID() {
		return getColumnOriginalValue("UID");
	}

	@Override
	public String getINE() {
		if (_INE == null) {
			return "";
		}
		else {
			return _INE;
		}
	}

	@Override
	public void setINE(String INE) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_INE = INE;
	}

	@Override
	public String getEntEleveStructRattachId() {
		if (_EntEleveStructRattachId == null) {
			return "";
		}
		else {
			return _EntEleveStructRattachId;
		}
	}

	@Override
	public void setEntEleveStructRattachId(String EntEleveStructRattachId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_EntEleveStructRattachId = EntEleveStructRattachId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalEntEleveStructRattachId() {
		return getColumnOriginalValue("EntEleveStructRattachId");
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
			0, LDAPMapping.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public LDAPMapping toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, LDAPMapping>
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
		LDAPMappingImpl ldapMappingImpl = new LDAPMappingImpl();

		ldapMappingImpl.setUserId(getUserId());
		ldapMappingImpl.setEntPersonJointure(getEntPersonJointure());
		ldapMappingImpl.setUID(getUID());
		ldapMappingImpl.setINE(getINE());
		ldapMappingImpl.setEntEleveStructRattachId(
			getEntEleveStructRattachId());

		ldapMappingImpl.resetOriginalValues();

		return ldapMappingImpl;
	}

	@Override
	public LDAPMapping cloneWithOriginalValues() {
		LDAPMappingImpl ldapMappingImpl = new LDAPMappingImpl();

		ldapMappingImpl.setUserId(this.<Long>getColumnOriginalValue("UserId"));
		ldapMappingImpl.setEntPersonJointure(
			this.<String>getColumnOriginalValue("EntPersonJointure"));
		ldapMappingImpl.setUID(this.<String>getColumnOriginalValue("UID"));
		ldapMappingImpl.setINE(this.<String>getColumnOriginalValue("INE"));
		ldapMappingImpl.setEntEleveStructRattachId(
			this.<String>getColumnOriginalValue("EntEleveStructRattachId"));

		return ldapMappingImpl;
	}

	@Override
	public int compareTo(LDAPMapping ldapMapping) {
		long primaryKey = ldapMapping.getPrimaryKey();

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

		if (!(object instanceof LDAPMapping)) {
			return false;
		}

		LDAPMapping ldapMapping = (LDAPMapping)object;

		long primaryKey = ldapMapping.getPrimaryKey();

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
	public CacheModel<LDAPMapping> toCacheModel() {
		LDAPMappingCacheModel ldapMappingCacheModel =
			new LDAPMappingCacheModel();

		ldapMappingCacheModel.UserId = getUserId();

		ldapMappingCacheModel.EntPersonJointure = getEntPersonJointure();

		String EntPersonJointure = ldapMappingCacheModel.EntPersonJointure;

		if ((EntPersonJointure != null) && (EntPersonJointure.length() == 0)) {
			ldapMappingCacheModel.EntPersonJointure = null;
		}

		ldapMappingCacheModel.UID = getUID();

		String UID = ldapMappingCacheModel.UID;

		if ((UID != null) && (UID.length() == 0)) {
			ldapMappingCacheModel.UID = null;
		}

		ldapMappingCacheModel.INE = getINE();

		String INE = ldapMappingCacheModel.INE;

		if ((INE != null) && (INE.length() == 0)) {
			ldapMappingCacheModel.INE = null;
		}

		ldapMappingCacheModel.EntEleveStructRattachId =
			getEntEleveStructRattachId();

		String EntEleveStructRattachId =
			ldapMappingCacheModel.EntEleveStructRattachId;

		if ((EntEleveStructRattachId != null) &&
			(EntEleveStructRattachId.length() == 0)) {

			ldapMappingCacheModel.EntEleveStructRattachId = null;
		}

		return ldapMappingCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<LDAPMapping, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<LDAPMapping, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LDAPMapping, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((LDAPMapping)this);

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

		private static final Function<InvocationHandler, LDAPMapping>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					LDAPMapping.class, ModelWrapper.class);

	}

	private long _UserId;
	private String _EntPersonJointure;

	private String _UID;

	private String _INE;

	private String _EntEleveStructRattachId;

	public <T> T getColumnValue(String columnName) {
		Function<LDAPMapping, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((LDAPMapping)this);
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

		_columnOriginalValues.put("UserId", _UserId);
		_columnOriginalValues.put("EntPersonJointure", _EntPersonJointure);
		_columnOriginalValues.put("UID", _UID);
		_columnOriginalValues.put("INE", _INE);
		_columnOriginalValues.put(
			"EntEleveStructRattachId", _EntEleveStructRattachId);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("UserId", 1L);

		columnBitmasks.put("EntPersonJointure", 2L);

		columnBitmasks.put("UID", 4L);

		columnBitmasks.put("INE", 8L);

		columnBitmasks.put("EntEleveStructRattachId", 16L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private LDAPMapping _escapedModel;

}