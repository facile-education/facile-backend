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

package com.weprode.nero.organization.model.impl;

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

import com.weprode.nero.organization.model.OrgMembership;
import com.weprode.nero.organization.model.OrgMembershipModel;

import java.io.Serializable;

import java.lang.reflect.Constructor;
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
 * The base model implementation for the OrgMembership service. Represents a row in the &quot;Organization_OrgMembership&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>OrgMembershipModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link OrgMembershipImpl}.
 * </p>
 *
 * @author Marc Salvat
 * @see OrgMembershipImpl
 * @generated
 */
public class OrgMembershipModelImpl
	extends BaseModelImpl<OrgMembership> implements OrgMembershipModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a org membership model instance should use the <code>OrgMembership</code> interface instead.
	 */
	public static final String TABLE_NAME = "Organization_OrgMembership";

	public static final Object[][] TABLE_COLUMNS = {
		{"orgMemberId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"startDate", Types.TIMESTAMP},
		{"endDate", Types.TIMESTAMP}, {"fullYear", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("orgMemberId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("startDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("endDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("fullYear", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Organization_OrgMembership (orgMemberId LONG not null primary key,groupId LONG,userId LONG,startDate DATE null,endDate DATE null,fullYear BOOLEAN)";

	public static final String TABLE_SQL_DROP =
		"drop table Organization_OrgMembership";

	public static final String ORDER_BY_JPQL =
		" ORDER BY orgMembership.orgMemberId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Organization_OrgMembership.orgMemberId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ORGMEMBERID_COLUMN_BITMASK = 4L;

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

	public OrgMembershipModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _orgMemberId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOrgMemberId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _orgMemberId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return OrgMembership.class;
	}

	@Override
	public String getModelClassName() {
		return OrgMembership.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<OrgMembership, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<OrgMembership, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OrgMembership, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((OrgMembership)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<OrgMembership, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<OrgMembership, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(OrgMembership)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<OrgMembership, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<OrgMembership, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, OrgMembership>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			OrgMembership.class.getClassLoader(), OrgMembership.class,
			ModelWrapper.class);

		try {
			Constructor<OrgMembership> constructor =
				(Constructor<OrgMembership>)proxyClass.getConstructor(
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

	private static final Map<String, Function<OrgMembership, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<OrgMembership, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<OrgMembership, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<OrgMembership, Object>>();
		Map<String, BiConsumer<OrgMembership, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<OrgMembership, ?>>();

		attributeGetterFunctions.put(
			"orgMemberId", OrgMembership::getOrgMemberId);
		attributeSetterBiConsumers.put(
			"orgMemberId",
			(BiConsumer<OrgMembership, Long>)OrgMembership::setOrgMemberId);
		attributeGetterFunctions.put("groupId", OrgMembership::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<OrgMembership, Long>)OrgMembership::setGroupId);
		attributeGetterFunctions.put("userId", OrgMembership::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<OrgMembership, Long>)OrgMembership::setUserId);
		attributeGetterFunctions.put("startDate", OrgMembership::getStartDate);
		attributeSetterBiConsumers.put(
			"startDate",
			(BiConsumer<OrgMembership, Date>)OrgMembership::setStartDate);
		attributeGetterFunctions.put("endDate", OrgMembership::getEndDate);
		attributeSetterBiConsumers.put(
			"endDate",
			(BiConsumer<OrgMembership, Date>)OrgMembership::setEndDate);
		attributeGetterFunctions.put("fullYear", OrgMembership::getFullYear);
		attributeSetterBiConsumers.put(
			"fullYear",
			(BiConsumer<OrgMembership, Boolean>)OrgMembership::setFullYear);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getOrgMemberId() {
		return _orgMemberId;
	}

	@Override
	public void setOrgMemberId(long orgMemberId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_orgMemberId = orgMemberId;
	}

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
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_startDate = startDate;
	}

	@Override
	public Date getEndDate() {
		return _endDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_endDate = endDate;
	}

	@Override
	public boolean getFullYear() {
		return _fullYear;
	}

	@Override
	public boolean isFullYear() {
		return _fullYear;
	}

	@Override
	public void setFullYear(boolean fullYear) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_fullYear = fullYear;
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
			0, OrgMembership.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public OrgMembership toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, OrgMembership>
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
		OrgMembershipImpl orgMembershipImpl = new OrgMembershipImpl();

		orgMembershipImpl.setOrgMemberId(getOrgMemberId());
		orgMembershipImpl.setGroupId(getGroupId());
		orgMembershipImpl.setUserId(getUserId());
		orgMembershipImpl.setStartDate(getStartDate());
		orgMembershipImpl.setEndDate(getEndDate());
		orgMembershipImpl.setFullYear(isFullYear());

		orgMembershipImpl.resetOriginalValues();

		return orgMembershipImpl;
	}

	@Override
	public OrgMembership cloneWithOriginalValues() {
		OrgMembershipImpl orgMembershipImpl = new OrgMembershipImpl();

		orgMembershipImpl.setOrgMemberId(
			this.<Long>getColumnOriginalValue("orgMemberId"));
		orgMembershipImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		orgMembershipImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		orgMembershipImpl.setStartDate(
			this.<Date>getColumnOriginalValue("startDate"));
		orgMembershipImpl.setEndDate(
			this.<Date>getColumnOriginalValue("endDate"));
		orgMembershipImpl.setFullYear(
			this.<Boolean>getColumnOriginalValue("fullYear"));

		return orgMembershipImpl;
	}

	@Override
	public int compareTo(OrgMembership orgMembership) {
		long primaryKey = orgMembership.getPrimaryKey();

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

		if (!(object instanceof OrgMembership)) {
			return false;
		}

		OrgMembership orgMembership = (OrgMembership)object;

		long primaryKey = orgMembership.getPrimaryKey();

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
	public CacheModel<OrgMembership> toCacheModel() {
		OrgMembershipCacheModel orgMembershipCacheModel =
			new OrgMembershipCacheModel();

		orgMembershipCacheModel.orgMemberId = getOrgMemberId();

		orgMembershipCacheModel.groupId = getGroupId();

		orgMembershipCacheModel.userId = getUserId();

		Date startDate = getStartDate();

		if (startDate != null) {
			orgMembershipCacheModel.startDate = startDate.getTime();
		}
		else {
			orgMembershipCacheModel.startDate = Long.MIN_VALUE;
		}

		Date endDate = getEndDate();

		if (endDate != null) {
			orgMembershipCacheModel.endDate = endDate.getTime();
		}
		else {
			orgMembershipCacheModel.endDate = Long.MIN_VALUE;
		}

		orgMembershipCacheModel.fullYear = isFullYear();

		return orgMembershipCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<OrgMembership, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<OrgMembership, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OrgMembership, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((OrgMembership)this);

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
		Map<String, Function<OrgMembership, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<OrgMembership, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OrgMembership, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((OrgMembership)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, OrgMembership>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _orgMemberId;
	private long _groupId;
	private long _userId;
	private Date _startDate;
	private Date _endDate;
	private boolean _fullYear;

	public <T> T getColumnValue(String columnName) {
		Function<OrgMembership, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((OrgMembership)this);
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

		_columnOriginalValues.put("orgMemberId", _orgMemberId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("startDate", _startDate);
		_columnOriginalValues.put("endDate", _endDate);
		_columnOriginalValues.put("fullYear", _fullYear);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("orgMemberId", 1L);

		columnBitmasks.put("groupId", 2L);

		columnBitmasks.put("userId", 4L);

		columnBitmasks.put("startDate", 8L);

		columnBitmasks.put("endDate", 16L);

		columnBitmasks.put("fullYear", 32L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private OrgMembership _escapedModel;

}