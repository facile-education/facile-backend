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

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.model.CommerceShipmentModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
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
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
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
 * The base model implementation for the CommerceShipment service. Represents a row in the &quot;CommerceShipment&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceShipmentModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceShipmentImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceShipmentImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceShipmentModelImpl
	extends BaseModelImpl<CommerceShipment> implements CommerceShipmentModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce shipment model instance should use the <code>CommerceShipment</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceShipment";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"externalReferenceCode", Types.VARCHAR},
		{"commerceShipmentId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"commerceAccountId", Types.BIGINT},
		{"commerceAddressId", Types.BIGINT},
		{"commerceShippingMethodId", Types.BIGINT}, {"carrier", Types.VARCHAR},
		{"expectedDate", Types.TIMESTAMP}, {"shippingDate", Types.TIMESTAMP},
		{"shippingOptionName", Types.CLOB}, {"trackingNumber", Types.VARCHAR},
		{"trackingURL", Types.VARCHAR}, {"status", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceShipmentId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceAccountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceAddressId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceShippingMethodId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("carrier", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("expectedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("shippingDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("shippingOptionName", Types.CLOB);
		TABLE_COLUMNS_MAP.put("trackingNumber", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("trackingURL", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceShipment (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,externalReferenceCode VARCHAR(75) null,commerceShipmentId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceAccountId LONG,commerceAddressId LONG,commerceShippingMethodId LONG,carrier VARCHAR(75) null,expectedDate DATE null,shippingDate DATE null,shippingOptionName TEXT null,trackingNumber VARCHAR(75) null,trackingURL STRING null,status INTEGER)";

	public static final String TABLE_SQL_DROP = "drop table CommerceShipment";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceShipment.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceShipment.createDate DESC";

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
	public static final long COMMERCEADDRESSID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long STATUS_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CREATEDATE_COLUMN_BITMASK = 64L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.model.CommerceShipment"));

	public CommerceShipmentModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceShipmentId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceShipmentId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceShipmentId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceShipment.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceShipment.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceShipment, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceShipment, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceShipment, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CommerceShipment)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceShipment, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceShipment, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceShipment)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceShipment, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceShipment, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<CommerceShipment, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CommerceShipment, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceShipment, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<CommerceShipment, Object>>();
		Map<String, BiConsumer<CommerceShipment, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<CommerceShipment, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", CommerceShipment::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<CommerceShipment, Long>)
				CommerceShipment::setMvccVersion);
		attributeGetterFunctions.put("uuid", CommerceShipment::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<CommerceShipment, String>)CommerceShipment::setUuid);
		attributeGetterFunctions.put(
			"externalReferenceCode",
			CommerceShipment::getExternalReferenceCode);
		attributeSetterBiConsumers.put(
			"externalReferenceCode",
			(BiConsumer<CommerceShipment, String>)
				CommerceShipment::setExternalReferenceCode);
		attributeGetterFunctions.put(
			"commerceShipmentId", CommerceShipment::getCommerceShipmentId);
		attributeSetterBiConsumers.put(
			"commerceShipmentId",
			(BiConsumer<CommerceShipment, Long>)
				CommerceShipment::setCommerceShipmentId);
		attributeGetterFunctions.put("groupId", CommerceShipment::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<CommerceShipment, Long>)CommerceShipment::setGroupId);
		attributeGetterFunctions.put(
			"companyId", CommerceShipment::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CommerceShipment, Long>)CommerceShipment::setCompanyId);
		attributeGetterFunctions.put("userId", CommerceShipment::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CommerceShipment, Long>)CommerceShipment::setUserId);
		attributeGetterFunctions.put("userName", CommerceShipment::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CommerceShipment, String>)
				CommerceShipment::setUserName);
		attributeGetterFunctions.put(
			"createDate", CommerceShipment::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CommerceShipment, Date>)
				CommerceShipment::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", CommerceShipment::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CommerceShipment, Date>)
				CommerceShipment::setModifiedDate);
		attributeGetterFunctions.put(
			"commerceAccountId", CommerceShipment::getCommerceAccountId);
		attributeSetterBiConsumers.put(
			"commerceAccountId",
			(BiConsumer<CommerceShipment, Long>)
				CommerceShipment::setCommerceAccountId);
		attributeGetterFunctions.put(
			"commerceAddressId", CommerceShipment::getCommerceAddressId);
		attributeSetterBiConsumers.put(
			"commerceAddressId",
			(BiConsumer<CommerceShipment, Long>)
				CommerceShipment::setCommerceAddressId);
		attributeGetterFunctions.put(
			"commerceShippingMethodId",
			CommerceShipment::getCommerceShippingMethodId);
		attributeSetterBiConsumers.put(
			"commerceShippingMethodId",
			(BiConsumer<CommerceShipment, Long>)
				CommerceShipment::setCommerceShippingMethodId);
		attributeGetterFunctions.put("carrier", CommerceShipment::getCarrier);
		attributeSetterBiConsumers.put(
			"carrier",
			(BiConsumer<CommerceShipment, String>)CommerceShipment::setCarrier);
		attributeGetterFunctions.put(
			"expectedDate", CommerceShipment::getExpectedDate);
		attributeSetterBiConsumers.put(
			"expectedDate",
			(BiConsumer<CommerceShipment, Date>)
				CommerceShipment::setExpectedDate);
		attributeGetterFunctions.put(
			"shippingDate", CommerceShipment::getShippingDate);
		attributeSetterBiConsumers.put(
			"shippingDate",
			(BiConsumer<CommerceShipment, Date>)
				CommerceShipment::setShippingDate);
		attributeGetterFunctions.put(
			"shippingOptionName", CommerceShipment::getShippingOptionName);
		attributeSetterBiConsumers.put(
			"shippingOptionName",
			(BiConsumer<CommerceShipment, String>)
				CommerceShipment::setShippingOptionName);
		attributeGetterFunctions.put(
			"trackingNumber", CommerceShipment::getTrackingNumber);
		attributeSetterBiConsumers.put(
			"trackingNumber",
			(BiConsumer<CommerceShipment, String>)
				CommerceShipment::setTrackingNumber);
		attributeGetterFunctions.put(
			"trackingURL", CommerceShipment::getTrackingURL);
		attributeSetterBiConsumers.put(
			"trackingURL",
			(BiConsumer<CommerceShipment, String>)
				CommerceShipment::setTrackingURL);
		attributeGetterFunctions.put("status", CommerceShipment::getStatus);
		attributeSetterBiConsumers.put(
			"status",
			(BiConsumer<CommerceShipment, Integer>)CommerceShipment::setStatus);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
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

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_uuid = uuid;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUuid() {
		return getColumnOriginalValue("uuid_");
	}

	@JSON
	@Override
	public String getExternalReferenceCode() {
		if (_externalReferenceCode == null) {
			return "";
		}
		else {
			return _externalReferenceCode;
		}
	}

	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_externalReferenceCode = externalReferenceCode;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalExternalReferenceCode() {
		return getColumnOriginalValue("externalReferenceCode");
	}

	@JSON
	@Override
	public long getCommerceShipmentId() {
		return _commerceShipmentId;
	}

	@Override
	public void setCommerceShipmentId(long commerceShipmentId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceShipmentId = commerceShipmentId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("companyId"));
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

	@JSON
	@Override
	public long getCommerceAddressId() {
		return _commerceAddressId;
	}

	@Override
	public void setCommerceAddressId(long commerceAddressId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceAddressId = commerceAddressId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommerceAddressId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commerceAddressId"));
	}

	@JSON
	@Override
	public long getCommerceShippingMethodId() {
		return _commerceShippingMethodId;
	}

	@Override
	public void setCommerceShippingMethodId(long commerceShippingMethodId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceShippingMethodId = commerceShippingMethodId;
	}

	@JSON
	@Override
	public String getCarrier() {
		if (_carrier == null) {
			return "";
		}
		else {
			return _carrier;
		}
	}

	@Override
	public void setCarrier(String carrier) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_carrier = carrier;
	}

	@JSON
	@Override
	public Date getExpectedDate() {
		return _expectedDate;
	}

	@Override
	public void setExpectedDate(Date expectedDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_expectedDate = expectedDate;
	}

	@JSON
	@Override
	public Date getShippingDate() {
		return _shippingDate;
	}

	@Override
	public void setShippingDate(Date shippingDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_shippingDate = shippingDate;
	}

	@JSON
	@Override
	public String getShippingOptionName() {
		if (_shippingOptionName == null) {
			return "";
		}
		else {
			return _shippingOptionName;
		}
	}

	@Override
	public void setShippingOptionName(String shippingOptionName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_shippingOptionName = shippingOptionName;
	}

	@JSON
	@Override
	public String getTrackingNumber() {
		if (_trackingNumber == null) {
			return "";
		}
		else {
			return _trackingNumber;
		}
	}

	@Override
	public void setTrackingNumber(String trackingNumber) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_trackingNumber = trackingNumber;
	}

	@JSON
	@Override
	public String getTrackingURL() {
		if (_trackingURL == null) {
			return "";
		}
		else {
			return _trackingURL;
		}
	}

	@Override
	public void setTrackingURL(String trackingURL) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_trackingURL = trackingURL;
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_status = status;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalStatus() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("status"));
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(CommerceShipment.class.getName()));
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
			getCompanyId(), CommerceShipment.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceShipment toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceShipment>
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
		CommerceShipmentImpl commerceShipmentImpl = new CommerceShipmentImpl();

		commerceShipmentImpl.setMvccVersion(getMvccVersion());
		commerceShipmentImpl.setUuid(getUuid());
		commerceShipmentImpl.setExternalReferenceCode(
			getExternalReferenceCode());
		commerceShipmentImpl.setCommerceShipmentId(getCommerceShipmentId());
		commerceShipmentImpl.setGroupId(getGroupId());
		commerceShipmentImpl.setCompanyId(getCompanyId());
		commerceShipmentImpl.setUserId(getUserId());
		commerceShipmentImpl.setUserName(getUserName());
		commerceShipmentImpl.setCreateDate(getCreateDate());
		commerceShipmentImpl.setModifiedDate(getModifiedDate());
		commerceShipmentImpl.setCommerceAccountId(getCommerceAccountId());
		commerceShipmentImpl.setCommerceAddressId(getCommerceAddressId());
		commerceShipmentImpl.setCommerceShippingMethodId(
			getCommerceShippingMethodId());
		commerceShipmentImpl.setCarrier(getCarrier());
		commerceShipmentImpl.setExpectedDate(getExpectedDate());
		commerceShipmentImpl.setShippingDate(getShippingDate());
		commerceShipmentImpl.setShippingOptionName(getShippingOptionName());
		commerceShipmentImpl.setTrackingNumber(getTrackingNumber());
		commerceShipmentImpl.setTrackingURL(getTrackingURL());
		commerceShipmentImpl.setStatus(getStatus());

		commerceShipmentImpl.resetOriginalValues();

		return commerceShipmentImpl;
	}

	@Override
	public CommerceShipment cloneWithOriginalValues() {
		CommerceShipmentImpl commerceShipmentImpl = new CommerceShipmentImpl();

		commerceShipmentImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		commerceShipmentImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		commerceShipmentImpl.setExternalReferenceCode(
			this.<String>getColumnOriginalValue("externalReferenceCode"));
		commerceShipmentImpl.setCommerceShipmentId(
			this.<Long>getColumnOriginalValue("commerceShipmentId"));
		commerceShipmentImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		commerceShipmentImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		commerceShipmentImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		commerceShipmentImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		commerceShipmentImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		commerceShipmentImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		commerceShipmentImpl.setCommerceAccountId(
			this.<Long>getColumnOriginalValue("commerceAccountId"));
		commerceShipmentImpl.setCommerceAddressId(
			this.<Long>getColumnOriginalValue("commerceAddressId"));
		commerceShipmentImpl.setCommerceShippingMethodId(
			this.<Long>getColumnOriginalValue("commerceShippingMethodId"));
		commerceShipmentImpl.setCarrier(
			this.<String>getColumnOriginalValue("carrier"));
		commerceShipmentImpl.setExpectedDate(
			this.<Date>getColumnOriginalValue("expectedDate"));
		commerceShipmentImpl.setShippingDate(
			this.<Date>getColumnOriginalValue("shippingDate"));
		commerceShipmentImpl.setShippingOptionName(
			this.<String>getColumnOriginalValue("shippingOptionName"));
		commerceShipmentImpl.setTrackingNumber(
			this.<String>getColumnOriginalValue("trackingNumber"));
		commerceShipmentImpl.setTrackingURL(
			this.<String>getColumnOriginalValue("trackingURL"));
		commerceShipmentImpl.setStatus(
			this.<Integer>getColumnOriginalValue("status"));

		return commerceShipmentImpl;
	}

	@Override
	public int compareTo(CommerceShipment commerceShipment) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), commerceShipment.getCreateDate());

		value = value * -1;

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

		if (!(object instanceof CommerceShipment)) {
			return false;
		}

		CommerceShipment commerceShipment = (CommerceShipment)object;

		long primaryKey = commerceShipment.getPrimaryKey();

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

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceShipment> toCacheModel() {
		CommerceShipmentCacheModel commerceShipmentCacheModel =
			new CommerceShipmentCacheModel();

		commerceShipmentCacheModel.mvccVersion = getMvccVersion();

		commerceShipmentCacheModel.uuid = getUuid();

		String uuid = commerceShipmentCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			commerceShipmentCacheModel.uuid = null;
		}

		commerceShipmentCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			commerceShipmentCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			commerceShipmentCacheModel.externalReferenceCode = null;
		}

		commerceShipmentCacheModel.commerceShipmentId = getCommerceShipmentId();

		commerceShipmentCacheModel.groupId = getGroupId();

		commerceShipmentCacheModel.companyId = getCompanyId();

		commerceShipmentCacheModel.userId = getUserId();

		commerceShipmentCacheModel.userName = getUserName();

		String userName = commerceShipmentCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceShipmentCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceShipmentCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceShipmentCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceShipmentCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceShipmentCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceShipmentCacheModel.commerceAccountId = getCommerceAccountId();

		commerceShipmentCacheModel.commerceAddressId = getCommerceAddressId();

		commerceShipmentCacheModel.commerceShippingMethodId =
			getCommerceShippingMethodId();

		commerceShipmentCacheModel.carrier = getCarrier();

		String carrier = commerceShipmentCacheModel.carrier;

		if ((carrier != null) && (carrier.length() == 0)) {
			commerceShipmentCacheModel.carrier = null;
		}

		Date expectedDate = getExpectedDate();

		if (expectedDate != null) {
			commerceShipmentCacheModel.expectedDate = expectedDate.getTime();
		}
		else {
			commerceShipmentCacheModel.expectedDate = Long.MIN_VALUE;
		}

		Date shippingDate = getShippingDate();

		if (shippingDate != null) {
			commerceShipmentCacheModel.shippingDate = shippingDate.getTime();
		}
		else {
			commerceShipmentCacheModel.shippingDate = Long.MIN_VALUE;
		}

		commerceShipmentCacheModel.shippingOptionName = getShippingOptionName();

		String shippingOptionName =
			commerceShipmentCacheModel.shippingOptionName;

		if ((shippingOptionName != null) &&
			(shippingOptionName.length() == 0)) {

			commerceShipmentCacheModel.shippingOptionName = null;
		}

		commerceShipmentCacheModel.trackingNumber = getTrackingNumber();

		String trackingNumber = commerceShipmentCacheModel.trackingNumber;

		if ((trackingNumber != null) && (trackingNumber.length() == 0)) {
			commerceShipmentCacheModel.trackingNumber = null;
		}

		commerceShipmentCacheModel.trackingURL = getTrackingURL();

		String trackingURL = commerceShipmentCacheModel.trackingURL;

		if ((trackingURL != null) && (trackingURL.length() == 0)) {
			commerceShipmentCacheModel.trackingURL = null;
		}

		commerceShipmentCacheModel.status = getStatus();

		return commerceShipmentCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceShipment, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceShipment, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceShipment, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(CommerceShipment)this);

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
		Map<String, Function<CommerceShipment, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommerceShipment, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceShipment, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((CommerceShipment)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, CommerceShipment>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					CommerceShipment.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private String _uuid;
	private String _externalReferenceCode;
	private long _commerceShipmentId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceAccountId;
	private long _commerceAddressId;
	private long _commerceShippingMethodId;
	private String _carrier;
	private Date _expectedDate;
	private Date _shippingDate;
	private String _shippingOptionName;
	private String _trackingNumber;
	private String _trackingURL;
	private int _status;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CommerceShipment, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceShipment)this);
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
		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put(
			"externalReferenceCode", _externalReferenceCode);
		_columnOriginalValues.put("commerceShipmentId", _commerceShipmentId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("commerceAccountId", _commerceAccountId);
		_columnOriginalValues.put("commerceAddressId", _commerceAddressId);
		_columnOriginalValues.put(
			"commerceShippingMethodId", _commerceShippingMethodId);
		_columnOriginalValues.put("carrier", _carrier);
		_columnOriginalValues.put("expectedDate", _expectedDate);
		_columnOriginalValues.put("shippingDate", _shippingDate);
		_columnOriginalValues.put("shippingOptionName", _shippingOptionName);
		_columnOriginalValues.put("trackingNumber", _trackingNumber);
		_columnOriginalValues.put("trackingURL", _trackingURL);
		_columnOriginalValues.put("status", _status);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("uuid_", 2L);

		columnBitmasks.put("externalReferenceCode", 4L);

		columnBitmasks.put("commerceShipmentId", 8L);

		columnBitmasks.put("groupId", 16L);

		columnBitmasks.put("companyId", 32L);

		columnBitmasks.put("userId", 64L);

		columnBitmasks.put("userName", 128L);

		columnBitmasks.put("createDate", 256L);

		columnBitmasks.put("modifiedDate", 512L);

		columnBitmasks.put("commerceAccountId", 1024L);

		columnBitmasks.put("commerceAddressId", 2048L);

		columnBitmasks.put("commerceShippingMethodId", 4096L);

		columnBitmasks.put("carrier", 8192L);

		columnBitmasks.put("expectedDate", 16384L);

		columnBitmasks.put("shippingDate", 32768L);

		columnBitmasks.put("shippingOptionName", 65536L);

		columnBitmasks.put("trackingNumber", 131072L);

		columnBitmasks.put("trackingURL", 262144L);

		columnBitmasks.put("status", 524288L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceShipment _escapedModel;

}