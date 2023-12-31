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

package com.weprode.facile.about.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.facile.about.model.VersionNote;
import com.weprode.facile.about.model.VersionNoteModel;

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
 * The base model implementation for the VersionNote service. Represents a row in the &quot;About_VersionNote&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>VersionNoteModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link VersionNoteImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VersionNoteImpl
 * @generated
 */
@JSON(strict = true)
public class VersionNoteModelImpl
	extends BaseModelImpl<VersionNote> implements VersionNoteModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a version note model instance should use the <code>VersionNote</code> interface instead.
	 */
	public static final String TABLE_NAME = "About_VersionNote";

	public static final Object[][] TABLE_COLUMNS = {
		{"versionNoteId", Types.BIGINT}, {"title", Types.VARCHAR},
		{"content", Types.VARCHAR}, {"versionNoteDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("versionNoteId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("content", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("versionNoteDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table About_VersionNote (versionNoteId LONG not null primary key,title VARCHAR(75) null,content VARCHAR(75) null,versionNoteDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table About_VersionNote";

	public static final String ORDER_BY_JPQL =
		" ORDER BY versionNote.versionNoteId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY About_VersionNote.versionNoteId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long VERSIONNOTEID_COLUMN_BITMASK = 1L;

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

	public VersionNoteModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _versionNoteId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setVersionNoteId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _versionNoteId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return VersionNote.class;
	}

	@Override
	public String getModelClassName() {
		return VersionNote.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<VersionNote, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<VersionNote, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<VersionNote, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((VersionNote)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<VersionNote, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<VersionNote, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(VersionNote)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<VersionNote, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<VersionNote, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<VersionNote, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<VersionNote, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<VersionNote, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<VersionNote, Object>>();
		Map<String, BiConsumer<VersionNote, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<VersionNote, ?>>();

		attributeGetterFunctions.put(
			"versionNoteId", VersionNote::getVersionNoteId);
		attributeSetterBiConsumers.put(
			"versionNoteId",
			(BiConsumer<VersionNote, Long>)VersionNote::setVersionNoteId);
		attributeGetterFunctions.put("title", VersionNote::getTitle);
		attributeSetterBiConsumers.put(
			"title", (BiConsumer<VersionNote, String>)VersionNote::setTitle);
		attributeGetterFunctions.put("content", VersionNote::getContent);
		attributeSetterBiConsumers.put(
			"content",
			(BiConsumer<VersionNote, String>)VersionNote::setContent);
		attributeGetterFunctions.put(
			"versionNoteDate", VersionNote::getVersionNoteDate);
		attributeSetterBiConsumers.put(
			"versionNoteDate",
			(BiConsumer<VersionNote, Date>)VersionNote::setVersionNoteDate);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getVersionNoteId() {
		return _versionNoteId;
	}

	@Override
	public void setVersionNoteId(long versionNoteId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_versionNoteId = versionNoteId;
	}

	@JSON
	@Override
	public String getTitle() {
		if (_title == null) {
			return "";
		}
		else {
			return _title;
		}
	}

	@Override
	public void setTitle(String title) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_title = title;
	}

	@JSON
	@Override
	public String getContent() {
		if (_content == null) {
			return "";
		}
		else {
			return _content;
		}
	}

	@Override
	public void setContent(String content) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_content = content;
	}

	@JSON
	@Override
	public Date getVersionNoteDate() {
		return _versionNoteDate;
	}

	@Override
	public void setVersionNoteDate(Date versionNoteDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_versionNoteDate = versionNoteDate;
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
			0, VersionNote.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public VersionNote toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, VersionNote>
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
		VersionNoteImpl versionNoteImpl = new VersionNoteImpl();

		versionNoteImpl.setVersionNoteId(getVersionNoteId());
		versionNoteImpl.setTitle(getTitle());
		versionNoteImpl.setContent(getContent());
		versionNoteImpl.setVersionNoteDate(getVersionNoteDate());

		versionNoteImpl.resetOriginalValues();

		return versionNoteImpl;
	}

	@Override
	public VersionNote cloneWithOriginalValues() {
		VersionNoteImpl versionNoteImpl = new VersionNoteImpl();

		versionNoteImpl.setVersionNoteId(
			this.<Long>getColumnOriginalValue("versionNoteId"));
		versionNoteImpl.setTitle(this.<String>getColumnOriginalValue("title"));
		versionNoteImpl.setContent(
			this.<String>getColumnOriginalValue("content"));
		versionNoteImpl.setVersionNoteDate(
			this.<Date>getColumnOriginalValue("versionNoteDate"));

		return versionNoteImpl;
	}

	@Override
	public int compareTo(VersionNote versionNote) {
		long primaryKey = versionNote.getPrimaryKey();

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

		if (!(object instanceof VersionNote)) {
			return false;
		}

		VersionNote versionNote = (VersionNote)object;

		long primaryKey = versionNote.getPrimaryKey();

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
	public CacheModel<VersionNote> toCacheModel() {
		VersionNoteCacheModel versionNoteCacheModel =
			new VersionNoteCacheModel();

		versionNoteCacheModel.versionNoteId = getVersionNoteId();

		versionNoteCacheModel.title = getTitle();

		String title = versionNoteCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			versionNoteCacheModel.title = null;
		}

		versionNoteCacheModel.content = getContent();

		String content = versionNoteCacheModel.content;

		if ((content != null) && (content.length() == 0)) {
			versionNoteCacheModel.content = null;
		}

		Date versionNoteDate = getVersionNoteDate();

		if (versionNoteDate != null) {
			versionNoteCacheModel.versionNoteDate = versionNoteDate.getTime();
		}
		else {
			versionNoteCacheModel.versionNoteDate = Long.MIN_VALUE;
		}

		return versionNoteCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<VersionNote, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<VersionNote, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<VersionNote, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((VersionNote)this);

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

		private static final Function<InvocationHandler, VersionNote>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					VersionNote.class, ModelWrapper.class);

	}

	private long _versionNoteId;
	private String _title;
	private String _content;
	private Date _versionNoteDate;

	public <T> T getColumnValue(String columnName) {
		Function<VersionNote, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((VersionNote)this);
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

		_columnOriginalValues.put("versionNoteId", _versionNoteId);
		_columnOriginalValues.put("title", _title);
		_columnOriginalValues.put("content", _content);
		_columnOriginalValues.put("versionNoteDate", _versionNoteDate);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("versionNoteId", 1L);

		columnBitmasks.put("title", 2L);

		columnBitmasks.put("content", 4L);

		columnBitmasks.put("versionNoteDate", 8L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private VersionNote _escapedModel;

}