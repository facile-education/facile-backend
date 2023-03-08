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

package com.weprode.nero.user.model.impl;

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

import com.weprode.nero.user.model.UserContact;
import com.weprode.nero.user.model.UserContactModel;

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
 * The base model implementation for the UserContact service. Represents a row in the &quot;User_UserContact&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>UserContactModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserContactImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserContactImpl
 * @generated
 */
public class UserContactModelImpl
	extends BaseModelImpl<UserContact> implements UserContactModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user contact model instance should use the <code>UserContact</code> interface instead.
	 */
	public static final String TABLE_NAME = "User_UserContact";

	public static final Object[][] TABLE_COLUMNS = {
		{"contactId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"middleNames", Types.VARCHAR}, {"birthName", Types.VARCHAR},
		{"address", Types.VARCHAR}, {"isAddressAuthorized", Types.BOOLEAN},
		{"mail", Types.VARCHAR}, {"isMailAuthorized", Types.BOOLEAN},
		{"mobilePhone", Types.VARCHAR}, {"mobilePhoneSMS", Types.VARCHAR},
		{"homePhone", Types.VARCHAR}, {"proPhone", Types.VARCHAR},
		{"familyLink", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("contactId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("middleNames", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("birthName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("address", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("isAddressAuthorized", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("mail", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("isMailAuthorized", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("mobilePhone", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("mobilePhoneSMS", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("homePhone", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("proPhone", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("familyLink", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table User_UserContact (contactId LONG not null primary key,userId LONG,middleNames VARCHAR(75) null,birthName VARCHAR(75) null,address VARCHAR(75) null,isAddressAuthorized BOOLEAN,mail VARCHAR(75) null,isMailAuthorized BOOLEAN,mobilePhone VARCHAR(75) null,mobilePhoneSMS VARCHAR(75) null,homePhone VARCHAR(75) null,proPhone VARCHAR(75) null,familyLink VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table User_UserContact";

	public static final String ORDER_BY_JPQL =
		" ORDER BY userContact.contactId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY User_UserContact.contactId ASC";

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
	public static final long CONTACTID_COLUMN_BITMASK = 2L;

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

	public UserContactModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _contactId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setContactId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _contactId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return UserContact.class;
	}

	@Override
	public String getModelClassName() {
		return UserContact.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<UserContact, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<UserContact, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserContact, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((UserContact)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<UserContact, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<UserContact, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(UserContact)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<UserContact, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<UserContact, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, UserContact>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			UserContact.class.getClassLoader(), UserContact.class,
			ModelWrapper.class);

		try {
			Constructor<UserContact> constructor =
				(Constructor<UserContact>)proxyClass.getConstructor(
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

	private static final Map<String, Function<UserContact, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<UserContact, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<UserContact, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<UserContact, Object>>();
		Map<String, BiConsumer<UserContact, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<UserContact, ?>>();

		attributeGetterFunctions.put("contactId", UserContact::getContactId);
		attributeSetterBiConsumers.put(
			"contactId",
			(BiConsumer<UserContact, Long>)UserContact::setContactId);
		attributeGetterFunctions.put("userId", UserContact::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<UserContact, Long>)UserContact::setUserId);
		attributeGetterFunctions.put(
			"middleNames", UserContact::getMiddleNames);
		attributeSetterBiConsumers.put(
			"middleNames",
			(BiConsumer<UserContact, String>)UserContact::setMiddleNames);
		attributeGetterFunctions.put("birthName", UserContact::getBirthName);
		attributeSetterBiConsumers.put(
			"birthName",
			(BiConsumer<UserContact, String>)UserContact::setBirthName);
		attributeGetterFunctions.put("address", UserContact::getAddress);
		attributeSetterBiConsumers.put(
			"address",
			(BiConsumer<UserContact, String>)UserContact::setAddress);
		attributeGetterFunctions.put(
			"isAddressAuthorized", UserContact::getIsAddressAuthorized);
		attributeSetterBiConsumers.put(
			"isAddressAuthorized",
			(BiConsumer<UserContact, Boolean>)
				UserContact::setIsAddressAuthorized);
		attributeGetterFunctions.put("mail", UserContact::getMail);
		attributeSetterBiConsumers.put(
			"mail", (BiConsumer<UserContact, String>)UserContact::setMail);
		attributeGetterFunctions.put(
			"isMailAuthorized", UserContact::getIsMailAuthorized);
		attributeSetterBiConsumers.put(
			"isMailAuthorized",
			(BiConsumer<UserContact, Boolean>)UserContact::setIsMailAuthorized);
		attributeGetterFunctions.put(
			"mobilePhone", UserContact::getMobilePhone);
		attributeSetterBiConsumers.put(
			"mobilePhone",
			(BiConsumer<UserContact, String>)UserContact::setMobilePhone);
		attributeGetterFunctions.put(
			"mobilePhoneSMS", UserContact::getMobilePhoneSMS);
		attributeSetterBiConsumers.put(
			"mobilePhoneSMS",
			(BiConsumer<UserContact, String>)UserContact::setMobilePhoneSMS);
		attributeGetterFunctions.put("homePhone", UserContact::getHomePhone);
		attributeSetterBiConsumers.put(
			"homePhone",
			(BiConsumer<UserContact, String>)UserContact::setHomePhone);
		attributeGetterFunctions.put("proPhone", UserContact::getProPhone);
		attributeSetterBiConsumers.put(
			"proPhone",
			(BiConsumer<UserContact, String>)UserContact::setProPhone);
		attributeGetterFunctions.put("familyLink", UserContact::getFamilyLink);
		attributeSetterBiConsumers.put(
			"familyLink",
			(BiConsumer<UserContact, String>)UserContact::setFamilyLink);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getContactId() {
		return _contactId;
	}

	@Override
	public void setContactId(long contactId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_contactId = contactId;
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
	public String getMiddleNames() {
		if (_middleNames == null) {
			return "";
		}
		else {
			return _middleNames;
		}
	}

	@Override
	public void setMiddleNames(String middleNames) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_middleNames = middleNames;
	}

	@Override
	public String getBirthName() {
		if (_birthName == null) {
			return "";
		}
		else {
			return _birthName;
		}
	}

	@Override
	public void setBirthName(String birthName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_birthName = birthName;
	}

	@Override
	public String getAddress() {
		if (_address == null) {
			return "";
		}
		else {
			return _address;
		}
	}

	@Override
	public void setAddress(String address) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_address = address;
	}

	@Override
	public boolean getIsAddressAuthorized() {
		return _isAddressAuthorized;
	}

	@Override
	public boolean isIsAddressAuthorized() {
		return _isAddressAuthorized;
	}

	@Override
	public void setIsAddressAuthorized(boolean isAddressAuthorized) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_isAddressAuthorized = isAddressAuthorized;
	}

	@Override
	public String getMail() {
		if (_mail == null) {
			return "";
		}
		else {
			return _mail;
		}
	}

	@Override
	public void setMail(String mail) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_mail = mail;
	}

	@Override
	public boolean getIsMailAuthorized() {
		return _isMailAuthorized;
	}

	@Override
	public boolean isIsMailAuthorized() {
		return _isMailAuthorized;
	}

	@Override
	public void setIsMailAuthorized(boolean isMailAuthorized) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_isMailAuthorized = isMailAuthorized;
	}

	@Override
	public String getMobilePhone() {
		if (_mobilePhone == null) {
			return "";
		}
		else {
			return _mobilePhone;
		}
	}

	@Override
	public void setMobilePhone(String mobilePhone) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_mobilePhone = mobilePhone;
	}

	@Override
	public String getMobilePhoneSMS() {
		if (_mobilePhoneSMS == null) {
			return "";
		}
		else {
			return _mobilePhoneSMS;
		}
	}

	@Override
	public void setMobilePhoneSMS(String mobilePhoneSMS) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_mobilePhoneSMS = mobilePhoneSMS;
	}

	@Override
	public String getHomePhone() {
		if (_homePhone == null) {
			return "";
		}
		else {
			return _homePhone;
		}
	}

	@Override
	public void setHomePhone(String homePhone) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_homePhone = homePhone;
	}

	@Override
	public String getProPhone() {
		if (_proPhone == null) {
			return "";
		}
		else {
			return _proPhone;
		}
	}

	@Override
	public void setProPhone(String proPhone) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_proPhone = proPhone;
	}

	@Override
	public String getFamilyLink() {
		if (_familyLink == null) {
			return "";
		}
		else {
			return _familyLink;
		}
	}

	@Override
	public void setFamilyLink(String familyLink) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_familyLink = familyLink;
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
			0, UserContact.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public UserContact toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, UserContact>
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
		UserContactImpl userContactImpl = new UserContactImpl();

		userContactImpl.setContactId(getContactId());
		userContactImpl.setUserId(getUserId());
		userContactImpl.setMiddleNames(getMiddleNames());
		userContactImpl.setBirthName(getBirthName());
		userContactImpl.setAddress(getAddress());
		userContactImpl.setIsAddressAuthorized(isIsAddressAuthorized());
		userContactImpl.setMail(getMail());
		userContactImpl.setIsMailAuthorized(isIsMailAuthorized());
		userContactImpl.setMobilePhone(getMobilePhone());
		userContactImpl.setMobilePhoneSMS(getMobilePhoneSMS());
		userContactImpl.setHomePhone(getHomePhone());
		userContactImpl.setProPhone(getProPhone());
		userContactImpl.setFamilyLink(getFamilyLink());

		userContactImpl.resetOriginalValues();

		return userContactImpl;
	}

	@Override
	public UserContact cloneWithOriginalValues() {
		UserContactImpl userContactImpl = new UserContactImpl();

		userContactImpl.setContactId(
			this.<Long>getColumnOriginalValue("contactId"));
		userContactImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		userContactImpl.setMiddleNames(
			this.<String>getColumnOriginalValue("middleNames"));
		userContactImpl.setBirthName(
			this.<String>getColumnOriginalValue("birthName"));
		userContactImpl.setAddress(
			this.<String>getColumnOriginalValue("address"));
		userContactImpl.setIsAddressAuthorized(
			this.<Boolean>getColumnOriginalValue("isAddressAuthorized"));
		userContactImpl.setMail(this.<String>getColumnOriginalValue("mail"));
		userContactImpl.setIsMailAuthorized(
			this.<Boolean>getColumnOriginalValue("isMailAuthorized"));
		userContactImpl.setMobilePhone(
			this.<String>getColumnOriginalValue("mobilePhone"));
		userContactImpl.setMobilePhoneSMS(
			this.<String>getColumnOriginalValue("mobilePhoneSMS"));
		userContactImpl.setHomePhone(
			this.<String>getColumnOriginalValue("homePhone"));
		userContactImpl.setProPhone(
			this.<String>getColumnOriginalValue("proPhone"));
		userContactImpl.setFamilyLink(
			this.<String>getColumnOriginalValue("familyLink"));

		return userContactImpl;
	}

	@Override
	public int compareTo(UserContact userContact) {
		long primaryKey = userContact.getPrimaryKey();

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

		if (!(object instanceof UserContact)) {
			return false;
		}

		UserContact userContact = (UserContact)object;

		long primaryKey = userContact.getPrimaryKey();

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
	public CacheModel<UserContact> toCacheModel() {
		UserContactCacheModel userContactCacheModel =
			new UserContactCacheModel();

		userContactCacheModel.contactId = getContactId();

		userContactCacheModel.userId = getUserId();

		userContactCacheModel.middleNames = getMiddleNames();

		String middleNames = userContactCacheModel.middleNames;

		if ((middleNames != null) && (middleNames.length() == 0)) {
			userContactCacheModel.middleNames = null;
		}

		userContactCacheModel.birthName = getBirthName();

		String birthName = userContactCacheModel.birthName;

		if ((birthName != null) && (birthName.length() == 0)) {
			userContactCacheModel.birthName = null;
		}

		userContactCacheModel.address = getAddress();

		String address = userContactCacheModel.address;

		if ((address != null) && (address.length() == 0)) {
			userContactCacheModel.address = null;
		}

		userContactCacheModel.isAddressAuthorized = isIsAddressAuthorized();

		userContactCacheModel.mail = getMail();

		String mail = userContactCacheModel.mail;

		if ((mail != null) && (mail.length() == 0)) {
			userContactCacheModel.mail = null;
		}

		userContactCacheModel.isMailAuthorized = isIsMailAuthorized();

		userContactCacheModel.mobilePhone = getMobilePhone();

		String mobilePhone = userContactCacheModel.mobilePhone;

		if ((mobilePhone != null) && (mobilePhone.length() == 0)) {
			userContactCacheModel.mobilePhone = null;
		}

		userContactCacheModel.mobilePhoneSMS = getMobilePhoneSMS();

		String mobilePhoneSMS = userContactCacheModel.mobilePhoneSMS;

		if ((mobilePhoneSMS != null) && (mobilePhoneSMS.length() == 0)) {
			userContactCacheModel.mobilePhoneSMS = null;
		}

		userContactCacheModel.homePhone = getHomePhone();

		String homePhone = userContactCacheModel.homePhone;

		if ((homePhone != null) && (homePhone.length() == 0)) {
			userContactCacheModel.homePhone = null;
		}

		userContactCacheModel.proPhone = getProPhone();

		String proPhone = userContactCacheModel.proPhone;

		if ((proPhone != null) && (proPhone.length() == 0)) {
			userContactCacheModel.proPhone = null;
		}

		userContactCacheModel.familyLink = getFamilyLink();

		String familyLink = userContactCacheModel.familyLink;

		if ((familyLink != null) && (familyLink.length() == 0)) {
			userContactCacheModel.familyLink = null;
		}

		return userContactCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<UserContact, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<UserContact, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserContact, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((UserContact)this);

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
		Map<String, Function<UserContact, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<UserContact, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserContact, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((UserContact)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, UserContact>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _contactId;
	private long _userId;
	private String _middleNames;
	private String _birthName;
	private String _address;
	private boolean _isAddressAuthorized;
	private String _mail;
	private boolean _isMailAuthorized;
	private String _mobilePhone;
	private String _mobilePhoneSMS;
	private String _homePhone;
	private String _proPhone;
	private String _familyLink;

	public <T> T getColumnValue(String columnName) {
		Function<UserContact, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((UserContact)this);
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

		_columnOriginalValues.put("contactId", _contactId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("middleNames", _middleNames);
		_columnOriginalValues.put("birthName", _birthName);
		_columnOriginalValues.put("address", _address);
		_columnOriginalValues.put("isAddressAuthorized", _isAddressAuthorized);
		_columnOriginalValues.put("mail", _mail);
		_columnOriginalValues.put("isMailAuthorized", _isMailAuthorized);
		_columnOriginalValues.put("mobilePhone", _mobilePhone);
		_columnOriginalValues.put("mobilePhoneSMS", _mobilePhoneSMS);
		_columnOriginalValues.put("homePhone", _homePhone);
		_columnOriginalValues.put("proPhone", _proPhone);
		_columnOriginalValues.put("familyLink", _familyLink);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("contactId", 1L);

		columnBitmasks.put("userId", 2L);

		columnBitmasks.put("middleNames", 4L);

		columnBitmasks.put("birthName", 8L);

		columnBitmasks.put("address", 16L);

		columnBitmasks.put("isAddressAuthorized", 32L);

		columnBitmasks.put("mail", 64L);

		columnBitmasks.put("isMailAuthorized", 128L);

		columnBitmasks.put("mobilePhone", 256L);

		columnBitmasks.put("mobilePhoneSMS", 512L);

		columnBitmasks.put("homePhone", 1024L);

		columnBitmasks.put("proPhone", 2048L);

		columnBitmasks.put("familyLink", 4096L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private UserContact _escapedModel;

}