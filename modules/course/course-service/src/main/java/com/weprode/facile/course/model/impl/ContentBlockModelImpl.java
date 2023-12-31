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

package com.weprode.facile.course.model.impl;

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

import com.weprode.facile.course.model.ContentBlock;
import com.weprode.facile.course.model.ContentBlockModel;

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
 * The base model implementation for the ContentBlock service. Represents a row in the &quot;Course_ContentBlock&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>ContentBlockModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ContentBlockImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContentBlockImpl
 * @generated
 */
@JSON(strict = true)
public class ContentBlockModelImpl
	extends BaseModelImpl<ContentBlock> implements ContentBlockModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a content block model instance should use the <code>ContentBlock</code> interface instead.
	 */
	public static final String TABLE_NAME = "Course_ContentBlock";

	public static final Object[][] TABLE_COLUMNS = {
		{"blockId", Types.BIGINT}, {"courseItemId", Types.BIGINT},
		{"modificationDate", Types.TIMESTAMP}, {"blockName", Types.VARCHAR},
		{"blockValue", Types.VARCHAR}, {"fileEntryId", Types.BIGINT},
		{"blockType", Types.INTEGER}, {"order_", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("blockId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("courseItemId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("modificationDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("blockName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("blockValue", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("blockType", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("order_", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Course_ContentBlock (blockId LONG not null primary key,courseItemId LONG,modificationDate DATE null,blockName VARCHAR(75) null,blockValue VARCHAR(75) null,fileEntryId LONG,blockType INTEGER,order_ INTEGER)";

	public static final String TABLE_SQL_DROP =
		"drop table Course_ContentBlock";

	public static final String ORDER_BY_JPQL =
		" ORDER BY contentBlock.blockId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Course_ContentBlock.blockId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COURSEITEMID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long BLOCKID_COLUMN_BITMASK = 2L;

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

	public ContentBlockModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _blockId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setBlockId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _blockId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ContentBlock.class;
	}

	@Override
	public String getModelClassName() {
		return ContentBlock.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ContentBlock, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ContentBlock, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ContentBlock, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ContentBlock)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ContentBlock, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ContentBlock, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ContentBlock)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ContentBlock, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ContentBlock, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<ContentBlock, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ContentBlock, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ContentBlock, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<ContentBlock, Object>>();
		Map<String, BiConsumer<ContentBlock, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<ContentBlock, ?>>();

		attributeGetterFunctions.put("blockId", ContentBlock::getBlockId);
		attributeSetterBiConsumers.put(
			"blockId",
			(BiConsumer<ContentBlock, Long>)ContentBlock::setBlockId);
		attributeGetterFunctions.put(
			"courseItemId", ContentBlock::getCourseItemId);
		attributeSetterBiConsumers.put(
			"courseItemId",
			(BiConsumer<ContentBlock, Long>)ContentBlock::setCourseItemId);
		attributeGetterFunctions.put(
			"modificationDate", ContentBlock::getModificationDate);
		attributeSetterBiConsumers.put(
			"modificationDate",
			(BiConsumer<ContentBlock, Date>)ContentBlock::setModificationDate);
		attributeGetterFunctions.put("blockName", ContentBlock::getBlockName);
		attributeSetterBiConsumers.put(
			"blockName",
			(BiConsumer<ContentBlock, String>)ContentBlock::setBlockName);
		attributeGetterFunctions.put("blockValue", ContentBlock::getBlockValue);
		attributeSetterBiConsumers.put(
			"blockValue",
			(BiConsumer<ContentBlock, String>)ContentBlock::setBlockValue);
		attributeGetterFunctions.put(
			"fileEntryId", ContentBlock::getFileEntryId);
		attributeSetterBiConsumers.put(
			"fileEntryId",
			(BiConsumer<ContentBlock, Long>)ContentBlock::setFileEntryId);
		attributeGetterFunctions.put("blockType", ContentBlock::getBlockType);
		attributeSetterBiConsumers.put(
			"blockType",
			(BiConsumer<ContentBlock, Integer>)ContentBlock::setBlockType);
		attributeGetterFunctions.put("order", ContentBlock::getOrder);
		attributeSetterBiConsumers.put(
			"order", (BiConsumer<ContentBlock, Integer>)ContentBlock::setOrder);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getBlockId() {
		return _blockId;
	}

	@Override
	public void setBlockId(long blockId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_blockId = blockId;
	}

	@JSON
	@Override
	public long getCourseItemId() {
		return _courseItemId;
	}

	@Override
	public void setCourseItemId(long courseItemId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_courseItemId = courseItemId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCourseItemId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("courseItemId"));
	}

	@JSON
	@Override
	public Date getModificationDate() {
		return _modificationDate;
	}

	@Override
	public void setModificationDate(Date modificationDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modificationDate = modificationDate;
	}

	@JSON
	@Override
	public String getBlockName() {
		if (_blockName == null) {
			return "";
		}
		else {
			return _blockName;
		}
	}

	@Override
	public void setBlockName(String blockName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_blockName = blockName;
	}

	@JSON
	@Override
	public String getBlockValue() {
		if (_blockValue == null) {
			return "";
		}
		else {
			return _blockValue;
		}
	}

	@Override
	public void setBlockValue(String blockValue) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_blockValue = blockValue;
	}

	@JSON
	@Override
	public long getFileEntryId() {
		return _fileEntryId;
	}

	@Override
	public void setFileEntryId(long fileEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_fileEntryId = fileEntryId;
	}

	@JSON
	@Override
	public int getBlockType() {
		return _blockType;
	}

	@Override
	public void setBlockType(int blockType) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_blockType = blockType;
	}

	@JSON
	@Override
	public int getOrder() {
		return _order;
	}

	@Override
	public void setOrder(int order) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_order = order;
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
			0, ContentBlock.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ContentBlock toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ContentBlock>
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
		ContentBlockImpl contentBlockImpl = new ContentBlockImpl();

		contentBlockImpl.setBlockId(getBlockId());
		contentBlockImpl.setCourseItemId(getCourseItemId());
		contentBlockImpl.setModificationDate(getModificationDate());
		contentBlockImpl.setBlockName(getBlockName());
		contentBlockImpl.setBlockValue(getBlockValue());
		contentBlockImpl.setFileEntryId(getFileEntryId());
		contentBlockImpl.setBlockType(getBlockType());
		contentBlockImpl.setOrder(getOrder());

		contentBlockImpl.resetOriginalValues();

		return contentBlockImpl;
	}

	@Override
	public ContentBlock cloneWithOriginalValues() {
		ContentBlockImpl contentBlockImpl = new ContentBlockImpl();

		contentBlockImpl.setBlockId(
			this.<Long>getColumnOriginalValue("blockId"));
		contentBlockImpl.setCourseItemId(
			this.<Long>getColumnOriginalValue("courseItemId"));
		contentBlockImpl.setModificationDate(
			this.<Date>getColumnOriginalValue("modificationDate"));
		contentBlockImpl.setBlockName(
			this.<String>getColumnOriginalValue("blockName"));
		contentBlockImpl.setBlockValue(
			this.<String>getColumnOriginalValue("blockValue"));
		contentBlockImpl.setFileEntryId(
			this.<Long>getColumnOriginalValue("fileEntryId"));
		contentBlockImpl.setBlockType(
			this.<Integer>getColumnOriginalValue("blockType"));
		contentBlockImpl.setOrder(
			this.<Integer>getColumnOriginalValue("order_"));

		return contentBlockImpl;
	}

	@Override
	public int compareTo(ContentBlock contentBlock) {
		long primaryKey = contentBlock.getPrimaryKey();

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

		if (!(object instanceof ContentBlock)) {
			return false;
		}

		ContentBlock contentBlock = (ContentBlock)object;

		long primaryKey = contentBlock.getPrimaryKey();

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
	public CacheModel<ContentBlock> toCacheModel() {
		ContentBlockCacheModel contentBlockCacheModel =
			new ContentBlockCacheModel();

		contentBlockCacheModel.blockId = getBlockId();

		contentBlockCacheModel.courseItemId = getCourseItemId();

		Date modificationDate = getModificationDate();

		if (modificationDate != null) {
			contentBlockCacheModel.modificationDate =
				modificationDate.getTime();
		}
		else {
			contentBlockCacheModel.modificationDate = Long.MIN_VALUE;
		}

		contentBlockCacheModel.blockName = getBlockName();

		String blockName = contentBlockCacheModel.blockName;

		if ((blockName != null) && (blockName.length() == 0)) {
			contentBlockCacheModel.blockName = null;
		}

		contentBlockCacheModel.blockValue = getBlockValue();

		String blockValue = contentBlockCacheModel.blockValue;

		if ((blockValue != null) && (blockValue.length() == 0)) {
			contentBlockCacheModel.blockValue = null;
		}

		contentBlockCacheModel.fileEntryId = getFileEntryId();

		contentBlockCacheModel.blockType = getBlockType();

		contentBlockCacheModel.order = getOrder();

		return contentBlockCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ContentBlock, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ContentBlock, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ContentBlock, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((ContentBlock)this);

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

		private static final Function<InvocationHandler, ContentBlock>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					ContentBlock.class, ModelWrapper.class);

	}

	private long _blockId;
	private long _courseItemId;
	private Date _modificationDate;
	private String _blockName;
	private String _blockValue;
	private long _fileEntryId;
	private int _blockType;
	private int _order;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<ContentBlock, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((ContentBlock)this);
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

		_columnOriginalValues.put("blockId", _blockId);
		_columnOriginalValues.put("courseItemId", _courseItemId);
		_columnOriginalValues.put("modificationDate", _modificationDate);
		_columnOriginalValues.put("blockName", _blockName);
		_columnOriginalValues.put("blockValue", _blockValue);
		_columnOriginalValues.put("fileEntryId", _fileEntryId);
		_columnOriginalValues.put("blockType", _blockType);
		_columnOriginalValues.put("order_", _order);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("order_", "order");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("blockId", 1L);

		columnBitmasks.put("courseItemId", 2L);

		columnBitmasks.put("modificationDate", 4L);

		columnBitmasks.put("blockName", 8L);

		columnBitmasks.put("blockValue", 16L);

		columnBitmasks.put("fileEntryId", 32L);

		columnBitmasks.put("blockType", 64L);

		columnBitmasks.put("order_", 128L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private ContentBlock _escapedModel;

}