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

package com.weprode.nero.messaging.model.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.nero.messaging.model.MessageAttachFile;
import com.weprode.nero.messaging.model.MessageAttachFileModel;
import com.weprode.nero.messaging.service.persistence.MessageAttachFilePK;

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
 * The base model implementation for the MessageAttachFile service. Represents a row in the &quot;Messaging_MessageAttachFile&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>MessageAttachFileModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MessageAttachFileImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageAttachFileImpl
 * @generated
 */
public class MessageAttachFileModelImpl
	extends BaseModelImpl<MessageAttachFile> implements MessageAttachFileModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a message attach file model instance should use the <code>MessageAttachFile</code> interface instead.
	 */
	public static final String TABLE_NAME = "Messaging_MessageAttachFile";

	public static final Object[][] TABLE_COLUMNS = {
		{"messageId", Types.BIGINT}, {"fileId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("messageId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Messaging_MessageAttachFile (messageId LONG not null,fileId LONG not null,primary key (messageId, fileId))";

	public static final String TABLE_SQL_DROP =
		"drop table Messaging_MessageAttachFile";

	public static final String ORDER_BY_JPQL =
		" ORDER BY messageAttachFile.id.messageId ASC, messageAttachFile.id.fileId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Messaging_MessageAttachFile.messageId ASC, Messaging_MessageAttachFile.fileId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long MESSAGEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long FILEID_COLUMN_BITMASK = 2L;

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

	public MessageAttachFileModelImpl() {
	}

	@Override
	public MessageAttachFilePK getPrimaryKey() {
		return new MessageAttachFilePK(_messageId, _fileId);
	}

	@Override
	public void setPrimaryKey(MessageAttachFilePK primaryKey) {
		setMessageId(primaryKey.messageId);
		setFileId(primaryKey.fileId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new MessageAttachFilePK(_messageId, _fileId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((MessageAttachFilePK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return MessageAttachFile.class;
	}

	@Override
	public String getModelClassName() {
		return MessageAttachFile.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<MessageAttachFile, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<MessageAttachFile, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MessageAttachFile, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((MessageAttachFile)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<MessageAttachFile, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<MessageAttachFile, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(MessageAttachFile)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<MessageAttachFile, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<MessageAttachFile, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, MessageAttachFile>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			MessageAttachFile.class.getClassLoader(), MessageAttachFile.class,
			ModelWrapper.class);

		try {
			Constructor<MessageAttachFile> constructor =
				(Constructor<MessageAttachFile>)proxyClass.getConstructor(
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

	private static final Map<String, Function<MessageAttachFile, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<MessageAttachFile, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<MessageAttachFile, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<MessageAttachFile, Object>>();
		Map<String, BiConsumer<MessageAttachFile, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<MessageAttachFile, ?>>();

		attributeGetterFunctions.put(
			"messageId", MessageAttachFile::getMessageId);
		attributeSetterBiConsumers.put(
			"messageId",
			(BiConsumer<MessageAttachFile, Long>)
				MessageAttachFile::setMessageId);
		attributeGetterFunctions.put("fileId", MessageAttachFile::getFileId);
		attributeSetterBiConsumers.put(
			"fileId",
			(BiConsumer<MessageAttachFile, Long>)MessageAttachFile::setFileId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getMessageId() {
		return _messageId;
	}

	@Override
	public void setMessageId(long messageId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_messageId = messageId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalMessageId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("messageId"));
	}

	@Override
	public long getFileId() {
		return _fileId;
	}

	@Override
	public void setFileId(long fileId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_fileId = fileId;
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
	public MessageAttachFile toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, MessageAttachFile>
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
		MessageAttachFileImpl messageAttachFileImpl =
			new MessageAttachFileImpl();

		messageAttachFileImpl.setMessageId(getMessageId());
		messageAttachFileImpl.setFileId(getFileId());

		messageAttachFileImpl.resetOriginalValues();

		return messageAttachFileImpl;
	}

	@Override
	public MessageAttachFile cloneWithOriginalValues() {
		MessageAttachFileImpl messageAttachFileImpl =
			new MessageAttachFileImpl();

		messageAttachFileImpl.setMessageId(
			this.<Long>getColumnOriginalValue("messageId"));
		messageAttachFileImpl.setFileId(
			this.<Long>getColumnOriginalValue("fileId"));

		return messageAttachFileImpl;
	}

	@Override
	public int compareTo(MessageAttachFile messageAttachFile) {
		MessageAttachFilePK primaryKey = messageAttachFile.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MessageAttachFile)) {
			return false;
		}

		MessageAttachFile messageAttachFile = (MessageAttachFile)object;

		MessageAttachFilePK primaryKey = messageAttachFile.getPrimaryKey();

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

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<MessageAttachFile> toCacheModel() {
		MessageAttachFileCacheModel messageAttachFileCacheModel =
			new MessageAttachFileCacheModel();

		messageAttachFileCacheModel.messageAttachFilePK = getPrimaryKey();

		messageAttachFileCacheModel.messageId = getMessageId();

		messageAttachFileCacheModel.fileId = getFileId();

		return messageAttachFileCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<MessageAttachFile, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<MessageAttachFile, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MessageAttachFile, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(MessageAttachFile)this);

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
		Map<String, Function<MessageAttachFile, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<MessageAttachFile, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MessageAttachFile, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((MessageAttachFile)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, MessageAttachFile>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _messageId;
	private long _fileId;

	public <T> T getColumnValue(String columnName) {
		Function<MessageAttachFile, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((MessageAttachFile)this);
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

		_columnOriginalValues.put("messageId", _messageId);
		_columnOriginalValues.put("fileId", _fileId);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("messageId", 1L);

		columnBitmasks.put("fileId", 2L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private MessageAttachFile _escapedModel;

}