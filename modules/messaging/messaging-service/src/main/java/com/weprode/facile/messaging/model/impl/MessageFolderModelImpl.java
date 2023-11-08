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

package com.weprode.facile.messaging.model.impl;

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

import com.weprode.facile.messaging.model.MessageFolder;
import com.weprode.facile.messaging.model.MessageFolderModel;

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
 * The base model implementation for the MessageFolder service. Represents a row in the &quot;Messaging_MessageFolder&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>MessageFolderModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MessageFolderImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageFolderImpl
 * @generated
 */
@JSON(strict = true)
public class MessageFolderModelImpl
	extends BaseModelImpl<MessageFolder> implements MessageFolderModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a message folder model instance should use the <code>MessageFolder</code> interface instead.
	 */
	public static final String TABLE_NAME = "Messaging_MessageFolder";

	public static final Object[][] TABLE_COLUMNS = {
		{"folderId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"folderName", Types.VARCHAR}, {"type_", Types.INTEGER},
		{"parentFolderId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("folderId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("folderName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("parentFolderId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Messaging_MessageFolder (folderId LONG not null primary key,userId LONG,folderName VARCHAR(75) null,type_ INTEGER,parentFolderId LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table Messaging_MessageFolder";

	public static final String ORDER_BY_JPQL =
		" ORDER BY messageFolder.folderId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Messaging_MessageFolder.folderId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PARENTFOLDERID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TYPE_COLUMN_BITMASK = 2L;

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
	public static final long FOLDERID_COLUMN_BITMASK = 8L;

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

	public MessageFolderModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _folderId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setFolderId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _folderId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return MessageFolder.class;
	}

	@Override
	public String getModelClassName() {
		return MessageFolder.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<MessageFolder, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<MessageFolder, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MessageFolder, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((MessageFolder)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<MessageFolder, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<MessageFolder, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(MessageFolder)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<MessageFolder, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<MessageFolder, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<MessageFolder, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<MessageFolder, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<MessageFolder, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<MessageFolder, Object>>();
		Map<String, BiConsumer<MessageFolder, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<MessageFolder, ?>>();

		attributeGetterFunctions.put("folderId", MessageFolder::getFolderId);
		attributeSetterBiConsumers.put(
			"folderId",
			(BiConsumer<MessageFolder, Long>)MessageFolder::setFolderId);
		attributeGetterFunctions.put("userId", MessageFolder::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<MessageFolder, Long>)MessageFolder::setUserId);
		attributeGetterFunctions.put(
			"folderName", MessageFolder::getFolderName);
		attributeSetterBiConsumers.put(
			"folderName",
			(BiConsumer<MessageFolder, String>)MessageFolder::setFolderName);
		attributeGetterFunctions.put("type", MessageFolder::getType);
		attributeSetterBiConsumers.put(
			"type", (BiConsumer<MessageFolder, Integer>)MessageFolder::setType);
		attributeGetterFunctions.put(
			"parentFolderId", MessageFolder::getParentFolderId);
		attributeSetterBiConsumers.put(
			"parentFolderId",
			(BiConsumer<MessageFolder, Long>)MessageFolder::setParentFolderId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getFolderId() {
		return _folderId;
	}

	@Override
	public void setFolderId(long folderId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_folderId = folderId;
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
	public String getFolderName() {
		if (_folderName == null) {
			return "";
		}
		else {
			return _folderName;
		}
	}

	@Override
	public void setFolderName(String folderName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_folderName = folderName;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalType() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("type_"));
	}

	@JSON
	@Override
	public long getParentFolderId() {
		return _parentFolderId;
	}

	@Override
	public void setParentFolderId(long parentFolderId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_parentFolderId = parentFolderId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalParentFolderId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("parentFolderId"));
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
			0, MessageFolder.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public MessageFolder toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, MessageFolder>
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
		MessageFolderImpl messageFolderImpl = new MessageFolderImpl();

		messageFolderImpl.setFolderId(getFolderId());
		messageFolderImpl.setUserId(getUserId());
		messageFolderImpl.setFolderName(getFolderName());
		messageFolderImpl.setType(getType());
		messageFolderImpl.setParentFolderId(getParentFolderId());

		messageFolderImpl.resetOriginalValues();

		return messageFolderImpl;
	}

	@Override
	public MessageFolder cloneWithOriginalValues() {
		MessageFolderImpl messageFolderImpl = new MessageFolderImpl();

		messageFolderImpl.setFolderId(
			this.<Long>getColumnOriginalValue("folderId"));
		messageFolderImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		messageFolderImpl.setFolderName(
			this.<String>getColumnOriginalValue("folderName"));
		messageFolderImpl.setType(
			this.<Integer>getColumnOriginalValue("type_"));
		messageFolderImpl.setParentFolderId(
			this.<Long>getColumnOriginalValue("parentFolderId"));

		return messageFolderImpl;
	}

	@Override
	public int compareTo(MessageFolder messageFolder) {
		long primaryKey = messageFolder.getPrimaryKey();

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

		if (!(object instanceof MessageFolder)) {
			return false;
		}

		MessageFolder messageFolder = (MessageFolder)object;

		long primaryKey = messageFolder.getPrimaryKey();

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
	public CacheModel<MessageFolder> toCacheModel() {
		MessageFolderCacheModel messageFolderCacheModel =
			new MessageFolderCacheModel();

		messageFolderCacheModel.folderId = getFolderId();

		messageFolderCacheModel.userId = getUserId();

		messageFolderCacheModel.folderName = getFolderName();

		String folderName = messageFolderCacheModel.folderName;

		if ((folderName != null) && (folderName.length() == 0)) {
			messageFolderCacheModel.folderName = null;
		}

		messageFolderCacheModel.type = getType();

		messageFolderCacheModel.parentFolderId = getParentFolderId();

		return messageFolderCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<MessageFolder, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<MessageFolder, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MessageFolder, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((MessageFolder)this);

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

		private static final Function<InvocationHandler, MessageFolder>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					MessageFolder.class, ModelWrapper.class);

	}

	private long _folderId;
	private long _userId;
	private String _folderName;
	private int _type;
	private long _parentFolderId;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<MessageFolder, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((MessageFolder)this);
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

		_columnOriginalValues.put("folderId", _folderId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("folderName", _folderName);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("parentFolderId", _parentFolderId);
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

		columnBitmasks.put("folderId", 1L);

		columnBitmasks.put("userId", 2L);

		columnBitmasks.put("folderName", 4L);

		columnBitmasks.put("type_", 8L);

		columnBitmasks.put("parentFolderId", 16L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private MessageFolder _escapedModel;

}