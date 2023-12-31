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
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.facile.user.model.Affectation;
import com.weprode.facile.user.model.AffectationModel;

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
 * The base model implementation for the Affectation service. Represents a row in the &quot;User_Affectation&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>AffectationModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AffectationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AffectationImpl
 * @generated
 */
@JSON(strict = true)
public class AffectationModelImpl
	extends BaseModelImpl<Affectation> implements AffectationModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a affectation model instance should use the <code>Affectation</code> interface instead.
	 */
	public static final String TABLE_NAME = "User_Affectation";

	public static final Object[][] TABLE_COLUMNS = {
		{"affectationId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"orgId", Types.BIGINT}, {"schoolId", Types.BIGINT},
		{"type_", Types.INTEGER}, {"adminUserId", Types.BIGINT},
		{"affectationDate", Types.TIMESTAMP},
		{"expirationDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("affectationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("orgId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("schoolId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("adminUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("affectationDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("expirationDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table User_Affectation (affectationId LONG not null primary key,userId LONG,orgId LONG,schoolId LONG,type_ INTEGER,adminUserId LONG,affectationDate DATE null,expirationDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table User_Affectation";

	public static final String ORDER_BY_JPQL =
		" ORDER BY affectation.affectationId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY User_Affectation.affectationId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ORGID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SCHOOLID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long AFFECTATIONID_COLUMN_BITMASK = 8L;

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

	public AffectationModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _affectationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAffectationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _affectationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Affectation.class;
	}

	@Override
	public String getModelClassName() {
		return Affectation.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Affectation, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Affectation, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Affectation, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((Affectation)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Affectation, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Affectation, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Affectation)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Affectation, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Affectation, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<Affectation, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Affectation, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Affectation, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Affectation, Object>>();
		Map<String, BiConsumer<Affectation, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Affectation, ?>>();

		attributeGetterFunctions.put(
			"affectationId", Affectation::getAffectationId);
		attributeSetterBiConsumers.put(
			"affectationId",
			(BiConsumer<Affectation, Long>)Affectation::setAffectationId);
		attributeGetterFunctions.put("userId", Affectation::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<Affectation, Long>)Affectation::setUserId);
		attributeGetterFunctions.put("orgId", Affectation::getOrgId);
		attributeSetterBiConsumers.put(
			"orgId", (BiConsumer<Affectation, Long>)Affectation::setOrgId);
		attributeGetterFunctions.put("schoolId", Affectation::getSchoolId);
		attributeSetterBiConsumers.put(
			"schoolId",
			(BiConsumer<Affectation, Long>)Affectation::setSchoolId);
		attributeGetterFunctions.put("type", Affectation::getType);
		attributeSetterBiConsumers.put(
			"type", (BiConsumer<Affectation, Integer>)Affectation::setType);
		attributeGetterFunctions.put(
			"adminUserId", Affectation::getAdminUserId);
		attributeSetterBiConsumers.put(
			"adminUserId",
			(BiConsumer<Affectation, Long>)Affectation::setAdminUserId);
		attributeGetterFunctions.put(
			"affectationDate", Affectation::getAffectationDate);
		attributeSetterBiConsumers.put(
			"affectationDate",
			(BiConsumer<Affectation, Date>)Affectation::setAffectationDate);
		attributeGetterFunctions.put(
			"expirationDate", Affectation::getExpirationDate);
		attributeSetterBiConsumers.put(
			"expirationDate",
			(BiConsumer<Affectation, Date>)Affectation::setExpirationDate);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getAffectationId() {
		return _affectationId;
	}

	@Override
	public void setAffectationId(long affectationId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_affectationId = affectationId;
	}

	@JSON
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

	@JSON
	@Override
	public long getOrgId() {
		return _orgId;
	}

	@Override
	public void setOrgId(long orgId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_orgId = orgId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalOrgId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("orgId"));
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

	@JSON
	@Override
	public long getAdminUserId() {
		return _adminUserId;
	}

	@Override
	public void setAdminUserId(long adminUserId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_adminUserId = adminUserId;
	}

	@Override
	public String getAdminUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getAdminUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setAdminUserUuid(String adminUserUuid) {
	}

	@JSON
	@Override
	public Date getAffectationDate() {
		return _affectationDate;
	}

	@Override
	public void setAffectationDate(Date affectationDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_affectationDate = affectationDate;
	}

	@JSON
	@Override
	public Date getExpirationDate() {
		return _expirationDate;
	}

	@Override
	public void setExpirationDate(Date expirationDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_expirationDate = expirationDate;
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
			0, Affectation.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Affectation toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Affectation>
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
		AffectationImpl affectationImpl = new AffectationImpl();

		affectationImpl.setAffectationId(getAffectationId());
		affectationImpl.setUserId(getUserId());
		affectationImpl.setOrgId(getOrgId());
		affectationImpl.setSchoolId(getSchoolId());
		affectationImpl.setType(getType());
		affectationImpl.setAdminUserId(getAdminUserId());
		affectationImpl.setAffectationDate(getAffectationDate());
		affectationImpl.setExpirationDate(getExpirationDate());

		affectationImpl.resetOriginalValues();

		return affectationImpl;
	}

	@Override
	public Affectation cloneWithOriginalValues() {
		AffectationImpl affectationImpl = new AffectationImpl();

		affectationImpl.setAffectationId(
			this.<Long>getColumnOriginalValue("affectationId"));
		affectationImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		affectationImpl.setOrgId(this.<Long>getColumnOriginalValue("orgId"));
		affectationImpl.setSchoolId(
			this.<Long>getColumnOriginalValue("schoolId"));
		affectationImpl.setType(this.<Integer>getColumnOriginalValue("type_"));
		affectationImpl.setAdminUserId(
			this.<Long>getColumnOriginalValue("adminUserId"));
		affectationImpl.setAffectationDate(
			this.<Date>getColumnOriginalValue("affectationDate"));
		affectationImpl.setExpirationDate(
			this.<Date>getColumnOriginalValue("expirationDate"));

		return affectationImpl;
	}

	@Override
	public int compareTo(Affectation affectation) {
		long primaryKey = affectation.getPrimaryKey();

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

		if (!(object instanceof Affectation)) {
			return false;
		}

		Affectation affectation = (Affectation)object;

		long primaryKey = affectation.getPrimaryKey();

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
	public CacheModel<Affectation> toCacheModel() {
		AffectationCacheModel affectationCacheModel =
			new AffectationCacheModel();

		affectationCacheModel.affectationId = getAffectationId();

		affectationCacheModel.userId = getUserId();

		affectationCacheModel.orgId = getOrgId();

		affectationCacheModel.schoolId = getSchoolId();

		affectationCacheModel.type = getType();

		affectationCacheModel.adminUserId = getAdminUserId();

		Date affectationDate = getAffectationDate();

		if (affectationDate != null) {
			affectationCacheModel.affectationDate = affectationDate.getTime();
		}
		else {
			affectationCacheModel.affectationDate = Long.MIN_VALUE;
		}

		Date expirationDate = getExpirationDate();

		if (expirationDate != null) {
			affectationCacheModel.expirationDate = expirationDate.getTime();
		}
		else {
			affectationCacheModel.expirationDate = Long.MIN_VALUE;
		}

		return affectationCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Affectation, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Affectation, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Affectation, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((Affectation)this);

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

		private static final Function<InvocationHandler, Affectation>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					Affectation.class, ModelWrapper.class);

	}

	private long _affectationId;
	private long _userId;
	private long _orgId;
	private long _schoolId;
	private int _type;
	private long _adminUserId;
	private Date _affectationDate;
	private Date _expirationDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<Affectation, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((Affectation)this);
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

		_columnOriginalValues.put("affectationId", _affectationId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("orgId", _orgId);
		_columnOriginalValues.put("schoolId", _schoolId);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("adminUserId", _adminUserId);
		_columnOriginalValues.put("affectationDate", _affectationDate);
		_columnOriginalValues.put("expirationDate", _expirationDate);
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

		columnBitmasks.put("affectationId", 1L);

		columnBitmasks.put("userId", 2L);

		columnBitmasks.put("orgId", 4L);

		columnBitmasks.put("schoolId", 8L);

		columnBitmasks.put("type_", 16L);

		columnBitmasks.put("adminUserId", 32L);

		columnBitmasks.put("affectationDate", 64L);

		columnBitmasks.put("expirationDate", 128L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private Affectation _escapedModel;

}