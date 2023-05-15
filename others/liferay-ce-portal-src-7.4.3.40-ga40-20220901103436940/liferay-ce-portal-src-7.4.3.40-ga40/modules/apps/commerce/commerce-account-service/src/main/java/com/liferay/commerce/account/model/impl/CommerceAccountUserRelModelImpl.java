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

package com.liferay.commerce.account.model.impl;

import com.liferay.commerce.account.model.CommerceAccountUserRel;
import com.liferay.commerce.account.model.CommerceAccountUserRelModel;
import com.liferay.commerce.account.service.persistence.CommerceAccountUserRelPK;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
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
 * The base model implementation for the CommerceAccountUserRel service. Represents a row in the &quot;CommerceAccountUserRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceAccountUserRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceAccountUserRelImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceAccountUserRelImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceAccountUserRelModelImpl
	extends BaseModelImpl<CommerceAccountUserRel>
	implements CommerceAccountUserRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce account user rel model instance should use the <code>CommerceAccountUserRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceAccountUserRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"commerceAccountId", Types.BIGINT},
		{"commerceAccountUserId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("commerceAccountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceAccountUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceAccountUserRel (commerceAccountId LONG not null,commerceAccountUserId LONG not null,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,primary key (commerceAccountId, commerceAccountUserId))";

	public static final String TABLE_SQL_DROP =
		"drop table CommerceAccountUserRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceAccountUserRel.userId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceAccountUserRel.userId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMMERCEACCOUNTID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMMERCEACCOUNTUSERID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 4L;

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

	public CommerceAccountUserRelModelImpl() {
	}

	@Override
	public CommerceAccountUserRelPK getPrimaryKey() {
		return new CommerceAccountUserRelPK(
			_commerceAccountId, _commerceAccountUserId);
	}

	@Override
	public void setPrimaryKey(CommerceAccountUserRelPK primaryKey) {
		setCommerceAccountId(primaryKey.commerceAccountId);
		setCommerceAccountUserId(primaryKey.commerceAccountUserId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new CommerceAccountUserRelPK(
			_commerceAccountId, _commerceAccountUserId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((CommerceAccountUserRelPK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceAccountUserRel.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceAccountUserRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceAccountUserRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceAccountUserRel, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceAccountUserRel, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CommerceAccountUserRel)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceAccountUserRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceAccountUserRel, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceAccountUserRel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceAccountUserRel, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceAccountUserRel, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<CommerceAccountUserRel, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CommerceAccountUserRel, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceAccountUserRel, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<CommerceAccountUserRel, Object>>();
		Map<String, BiConsumer<CommerceAccountUserRel, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<CommerceAccountUserRel, ?>>();

		attributeGetterFunctions.put(
			"commerceAccountId", CommerceAccountUserRel::getCommerceAccountId);
		attributeSetterBiConsumers.put(
			"commerceAccountId",
			(BiConsumer<CommerceAccountUserRel, Long>)
				CommerceAccountUserRel::setCommerceAccountId);
		attributeGetterFunctions.put(
			"commerceAccountUserId",
			CommerceAccountUserRel::getCommerceAccountUserId);
		attributeSetterBiConsumers.put(
			"commerceAccountUserId",
			(BiConsumer<CommerceAccountUserRel, Long>)
				CommerceAccountUserRel::setCommerceAccountUserId);
		attributeGetterFunctions.put(
			"companyId", CommerceAccountUserRel::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CommerceAccountUserRel, Long>)
				CommerceAccountUserRel::setCompanyId);
		attributeGetterFunctions.put(
			"userId", CommerceAccountUserRel::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CommerceAccountUserRel, Long>)
				CommerceAccountUserRel::setUserId);
		attributeGetterFunctions.put(
			"userName", CommerceAccountUserRel::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CommerceAccountUserRel, String>)
				CommerceAccountUserRel::setUserName);
		attributeGetterFunctions.put(
			"createDate", CommerceAccountUserRel::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CommerceAccountUserRel, Date>)
				CommerceAccountUserRel::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", CommerceAccountUserRel::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CommerceAccountUserRel, Date>)
				CommerceAccountUserRel::setModifiedDate);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getCommerceAccountId() {
		return _commerceAccountId;
	}

	@Override
	public void setCommerceAccountId(long commerceAccountId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceAccountId = commerceAccountId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommerceAccountId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commerceAccountId"));
	}

	@JSON
	@Override
	public long getCommerceAccountUserId() {
		return _commerceAccountUserId;
	}

	@Override
	public void setCommerceAccountUserId(long commerceAccountUserId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceAccountUserId = commerceAccountUserId;
	}

	@Override
	public String getCommerceAccountUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(
				getCommerceAccountUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setCommerceAccountUserUuid(String commerceAccountUserUuid) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommerceAccountUserId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commerceAccountUserId"));
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

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
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
	public CommerceAccountUserRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceAccountUserRel>
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
		CommerceAccountUserRelImpl commerceAccountUserRelImpl =
			new CommerceAccountUserRelImpl();

		commerceAccountUserRelImpl.setCommerceAccountId(getCommerceAccountId());
		commerceAccountUserRelImpl.setCommerceAccountUserId(
			getCommerceAccountUserId());
		commerceAccountUserRelImpl.setCompanyId(getCompanyId());
		commerceAccountUserRelImpl.setUserId(getUserId());
		commerceAccountUserRelImpl.setUserName(getUserName());
		commerceAccountUserRelImpl.setCreateDate(getCreateDate());
		commerceAccountUserRelImpl.setModifiedDate(getModifiedDate());

		commerceAccountUserRelImpl.resetOriginalValues();

		return commerceAccountUserRelImpl;
	}

	@Override
	public CommerceAccountUserRel cloneWithOriginalValues() {
		CommerceAccountUserRelImpl commerceAccountUserRelImpl =
			new CommerceAccountUserRelImpl();

		commerceAccountUserRelImpl.setCommerceAccountId(
			this.<Long>getColumnOriginalValue("commerceAccountId"));
		commerceAccountUserRelImpl.setCommerceAccountUserId(
			this.<Long>getColumnOriginalValue("commerceAccountUserId"));
		commerceAccountUserRelImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		commerceAccountUserRelImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		commerceAccountUserRelImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		commerceAccountUserRelImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		commerceAccountUserRelImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));

		return commerceAccountUserRelImpl;
	}

	@Override
	public int compareTo(CommerceAccountUserRel commerceAccountUserRel) {
		int value = 0;

		if (getUserId() < commerceAccountUserRel.getUserId()) {
			value = -1;
		}
		else if (getUserId() > commerceAccountUserRel.getUserId()) {
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

		if (!(object instanceof CommerceAccountUserRel)) {
			return false;
		}

		CommerceAccountUserRel commerceAccountUserRel =
			(CommerceAccountUserRel)object;

		CommerceAccountUserRelPK primaryKey =
			commerceAccountUserRel.getPrimaryKey();

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

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceAccountUserRel> toCacheModel() {
		CommerceAccountUserRelCacheModel commerceAccountUserRelCacheModel =
			new CommerceAccountUserRelCacheModel();

		commerceAccountUserRelCacheModel.commerceAccountUserRelPK =
			getPrimaryKey();

		commerceAccountUserRelCacheModel.commerceAccountId =
			getCommerceAccountId();

		commerceAccountUserRelCacheModel.commerceAccountUserId =
			getCommerceAccountUserId();

		commerceAccountUserRelCacheModel.companyId = getCompanyId();

		commerceAccountUserRelCacheModel.userId = getUserId();

		commerceAccountUserRelCacheModel.userName = getUserName();

		String userName = commerceAccountUserRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceAccountUserRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceAccountUserRelCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceAccountUserRelCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceAccountUserRelCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceAccountUserRelCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		return commerceAccountUserRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceAccountUserRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceAccountUserRel, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceAccountUserRel, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(CommerceAccountUserRel)this);

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
		Map<String, Function<CommerceAccountUserRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommerceAccountUserRel, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceAccountUserRel, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((CommerceAccountUserRel)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, CommerceAccountUserRel>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					CommerceAccountUserRel.class, ModelWrapper.class);

	}

	private long _commerceAccountId;
	private long _commerceAccountUserId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;

	public <T> T getColumnValue(String columnName) {
		Function<CommerceAccountUserRel, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceAccountUserRel)this);
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

		_columnOriginalValues.put("commerceAccountId", _commerceAccountId);
		_columnOriginalValues.put(
			"commerceAccountUserId", _commerceAccountUserId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("commerceAccountId", 1L);

		columnBitmasks.put("commerceAccountUserId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("userName", 16L);

		columnBitmasks.put("createDate", 32L);

		columnBitmasks.put("modifiedDate", 64L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceAccountUserRel _escapedModel;

}