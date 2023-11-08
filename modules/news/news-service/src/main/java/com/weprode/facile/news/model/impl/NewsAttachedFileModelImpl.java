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

package com.weprode.facile.news.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.facile.news.model.NewsAttachedFile;
import com.weprode.facile.news.model.NewsAttachedFileModel;

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
 * The base model implementation for the NewsAttachedFile service. Represents a row in the &quot;News_NewsAttachedFile&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>NewsAttachedFileModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link NewsAttachedFileImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsAttachedFileImpl
 * @generated
 */
public class NewsAttachedFileModelImpl
	extends BaseModelImpl<NewsAttachedFile> implements NewsAttachedFileModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a news attached file model instance should use the <code>NewsAttachedFile</code> interface instead.
	 */
	public static final String TABLE_NAME = "News_NewsAttachedFile";

	public static final Object[][] TABLE_COLUMNS = {
		{"newsFileId", Types.BIGINT}, {"newsId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"fileId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("newsFileId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("newsId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table News_NewsAttachedFile (newsFileId LONG not null primary key,newsId LONG,groupId LONG,fileId LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table News_NewsAttachedFile";

	public static final String ORDER_BY_JPQL =
		" ORDER BY newsAttachedFile.newsFileId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY News_NewsAttachedFile.newsFileId ASC";

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
	public static final long NEWSID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long NEWSFILEID_COLUMN_BITMASK = 4L;

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

	public NewsAttachedFileModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _newsFileId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setNewsFileId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _newsFileId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return NewsAttachedFile.class;
	}

	@Override
	public String getModelClassName() {
		return NewsAttachedFile.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<NewsAttachedFile, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<NewsAttachedFile, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<NewsAttachedFile, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((NewsAttachedFile)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<NewsAttachedFile, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<NewsAttachedFile, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(NewsAttachedFile)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<NewsAttachedFile, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<NewsAttachedFile, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<NewsAttachedFile, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<NewsAttachedFile, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<NewsAttachedFile, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<NewsAttachedFile, Object>>();
		Map<String, BiConsumer<NewsAttachedFile, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<NewsAttachedFile, ?>>();

		attributeGetterFunctions.put(
			"newsFileId", NewsAttachedFile::getNewsFileId);
		attributeSetterBiConsumers.put(
			"newsFileId",
			(BiConsumer<NewsAttachedFile, Long>)
				NewsAttachedFile::setNewsFileId);
		attributeGetterFunctions.put("newsId", NewsAttachedFile::getNewsId);
		attributeSetterBiConsumers.put(
			"newsId",
			(BiConsumer<NewsAttachedFile, Long>)NewsAttachedFile::setNewsId);
		attributeGetterFunctions.put("groupId", NewsAttachedFile::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<NewsAttachedFile, Long>)NewsAttachedFile::setGroupId);
		attributeGetterFunctions.put("fileId", NewsAttachedFile::getFileId);
		attributeSetterBiConsumers.put(
			"fileId",
			(BiConsumer<NewsAttachedFile, Long>)NewsAttachedFile::setFileId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getNewsFileId() {
		return _newsFileId;
	}

	@Override
	public void setNewsFileId(long newsFileId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_newsFileId = newsFileId;
	}

	@Override
	public long getNewsId() {
		return _newsId;
	}

	@Override
	public void setNewsId(long newsId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_newsId = newsId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalNewsId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("newsId"));
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
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, NewsAttachedFile.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public NewsAttachedFile toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, NewsAttachedFile>
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
		NewsAttachedFileImpl newsAttachedFileImpl = new NewsAttachedFileImpl();

		newsAttachedFileImpl.setNewsFileId(getNewsFileId());
		newsAttachedFileImpl.setNewsId(getNewsId());
		newsAttachedFileImpl.setGroupId(getGroupId());
		newsAttachedFileImpl.setFileId(getFileId());

		newsAttachedFileImpl.resetOriginalValues();

		return newsAttachedFileImpl;
	}

	@Override
	public NewsAttachedFile cloneWithOriginalValues() {
		NewsAttachedFileImpl newsAttachedFileImpl = new NewsAttachedFileImpl();

		newsAttachedFileImpl.setNewsFileId(
			this.<Long>getColumnOriginalValue("newsFileId"));
		newsAttachedFileImpl.setNewsId(
			this.<Long>getColumnOriginalValue("newsId"));
		newsAttachedFileImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		newsAttachedFileImpl.setFileId(
			this.<Long>getColumnOriginalValue("fileId"));

		return newsAttachedFileImpl;
	}

	@Override
	public int compareTo(NewsAttachedFile newsAttachedFile) {
		long primaryKey = newsAttachedFile.getPrimaryKey();

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

		if (!(object instanceof NewsAttachedFile)) {
			return false;
		}

		NewsAttachedFile newsAttachedFile = (NewsAttachedFile)object;

		long primaryKey = newsAttachedFile.getPrimaryKey();

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
	public CacheModel<NewsAttachedFile> toCacheModel() {
		NewsAttachedFileCacheModel newsAttachedFileCacheModel =
			new NewsAttachedFileCacheModel();

		newsAttachedFileCacheModel.newsFileId = getNewsFileId();

		newsAttachedFileCacheModel.newsId = getNewsId();

		newsAttachedFileCacheModel.groupId = getGroupId();

		newsAttachedFileCacheModel.fileId = getFileId();

		return newsAttachedFileCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<NewsAttachedFile, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<NewsAttachedFile, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<NewsAttachedFile, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(NewsAttachedFile)this);

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

		private static final Function<InvocationHandler, NewsAttachedFile>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					NewsAttachedFile.class, ModelWrapper.class);

	}

	private long _newsFileId;
	private long _newsId;
	private long _groupId;
	private long _fileId;

	public <T> T getColumnValue(String columnName) {
		Function<NewsAttachedFile, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((NewsAttachedFile)this);
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

		_columnOriginalValues.put("newsFileId", _newsFileId);
		_columnOriginalValues.put("newsId", _newsId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("fileId", _fileId);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("newsFileId", 1L);

		columnBitmasks.put("newsId", 2L);

		columnBitmasks.put("groupId", 4L);

		columnBitmasks.put("fileId", 8L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private NewsAttachedFile _escapedModel;

}