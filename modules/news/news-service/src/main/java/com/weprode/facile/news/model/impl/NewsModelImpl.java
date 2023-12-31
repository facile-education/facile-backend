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
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.facile.news.model.News;
import com.weprode.facile.news.model.NewsModel;

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
 * The base model implementation for the News service. Represents a row in the &quot;News_News&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>NewsModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link NewsImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsImpl
 * @generated
 */
@JSON(strict = true)
public class NewsModelImpl extends BaseModelImpl<News> implements NewsModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a news model instance should use the <code>News</code> interface instead.
	 */
	public static final String TABLE_NAME = "News_News";

	public static final Object[][] TABLE_COLUMNS = {
		{"newsId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"title", Types.VARCHAR}, {"content", Types.VARCHAR},
		{"authorId", Types.BIGINT}, {"isSchoolNews", Types.BOOLEAN},
		{"isImportant", Types.BOOLEAN}, {"expirationDate", Types.TIMESTAMP},
		{"publicationDate", Types.TIMESTAMP},
		{"modificationDate", Types.TIMESTAMP}, {"imageId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("newsId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("content", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("authorId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("isSchoolNews", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("isImportant", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("expirationDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("publicationDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modificationDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("imageId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table News_News (newsId LONG not null primary key,companyId LONG,title VARCHAR(75) null,content VARCHAR(75) null,authorId LONG,isSchoolNews BOOLEAN,isImportant BOOLEAN,expirationDate DATE null,publicationDate DATE null,modificationDate DATE null,imageId LONG)";

	public static final String TABLE_SQL_DROP = "drop table News_News";

	public static final String ORDER_BY_JPQL = " ORDER BY news.newsId ASC";

	public static final String ORDER_BY_SQL = " ORDER BY News_News.newsId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long AUTHORID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long NEWSID_COLUMN_BITMASK = 2L;

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

	public NewsModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _newsId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setNewsId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _newsId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return News.class;
	}

	@Override
	public String getModelClassName() {
		return News.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<News, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<News, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<News, Object> attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((News)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<News, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<News, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept((News)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<News, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<News, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<News, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<News, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<News, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<News, Object>>();
		Map<String, BiConsumer<News, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<News, ?>>();

		attributeGetterFunctions.put("newsId", News::getNewsId);
		attributeSetterBiConsumers.put(
			"newsId", (BiConsumer<News, Long>)News::setNewsId);
		attributeGetterFunctions.put("companyId", News::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<News, Long>)News::setCompanyId);
		attributeGetterFunctions.put("title", News::getTitle);
		attributeSetterBiConsumers.put(
			"title", (BiConsumer<News, String>)News::setTitle);
		attributeGetterFunctions.put("content", News::getContent);
		attributeSetterBiConsumers.put(
			"content", (BiConsumer<News, String>)News::setContent);
		attributeGetterFunctions.put("authorId", News::getAuthorId);
		attributeSetterBiConsumers.put(
			"authorId", (BiConsumer<News, Long>)News::setAuthorId);
		attributeGetterFunctions.put("isSchoolNews", News::getIsSchoolNews);
		attributeSetterBiConsumers.put(
			"isSchoolNews", (BiConsumer<News, Boolean>)News::setIsSchoolNews);
		attributeGetterFunctions.put("isImportant", News::getIsImportant);
		attributeSetterBiConsumers.put(
			"isImportant", (BiConsumer<News, Boolean>)News::setIsImportant);
		attributeGetterFunctions.put("expirationDate", News::getExpirationDate);
		attributeSetterBiConsumers.put(
			"expirationDate", (BiConsumer<News, Date>)News::setExpirationDate);
		attributeGetterFunctions.put(
			"publicationDate", News::getPublicationDate);
		attributeSetterBiConsumers.put(
			"publicationDate",
			(BiConsumer<News, Date>)News::setPublicationDate);
		attributeGetterFunctions.put(
			"modificationDate", News::getModificationDate);
		attributeSetterBiConsumers.put(
			"modificationDate",
			(BiConsumer<News, Date>)News::setModificationDate);
		attributeGetterFunctions.put("imageId", News::getImageId);
		attributeSetterBiConsumers.put(
			"imageId", (BiConsumer<News, Long>)News::setImageId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
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
	public long getAuthorId() {
		return _authorId;
	}

	@Override
	public void setAuthorId(long authorId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_authorId = authorId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalAuthorId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("authorId"));
	}

	@JSON
	@Override
	public boolean getIsSchoolNews() {
		return _isSchoolNews;
	}

	@JSON
	@Override
	public boolean isIsSchoolNews() {
		return _isSchoolNews;
	}

	@Override
	public void setIsSchoolNews(boolean isSchoolNews) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_isSchoolNews = isSchoolNews;
	}

	@JSON
	@Override
	public boolean getIsImportant() {
		return _isImportant;
	}

	@JSON
	@Override
	public boolean isIsImportant() {
		return _isImportant;
	}

	@Override
	public void setIsImportant(boolean isImportant) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_isImportant = isImportant;
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

	@JSON
	@Override
	public Date getPublicationDate() {
		return _publicationDate;
	}

	@Override
	public void setPublicationDate(Date publicationDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_publicationDate = publicationDate;
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
	public long getImageId() {
		return _imageId;
	}

	@Override
	public void setImageId(long imageId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_imageId = imageId;
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
			getCompanyId(), News.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public News toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, News>
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
		NewsImpl newsImpl = new NewsImpl();

		newsImpl.setNewsId(getNewsId());
		newsImpl.setCompanyId(getCompanyId());
		newsImpl.setTitle(getTitle());
		newsImpl.setContent(getContent());
		newsImpl.setAuthorId(getAuthorId());
		newsImpl.setIsSchoolNews(isIsSchoolNews());
		newsImpl.setIsImportant(isIsImportant());
		newsImpl.setExpirationDate(getExpirationDate());
		newsImpl.setPublicationDate(getPublicationDate());
		newsImpl.setModificationDate(getModificationDate());
		newsImpl.setImageId(getImageId());

		newsImpl.resetOriginalValues();

		return newsImpl;
	}

	@Override
	public News cloneWithOriginalValues() {
		NewsImpl newsImpl = new NewsImpl();

		newsImpl.setNewsId(this.<Long>getColumnOriginalValue("newsId"));
		newsImpl.setCompanyId(this.<Long>getColumnOriginalValue("companyId"));
		newsImpl.setTitle(this.<String>getColumnOriginalValue("title"));
		newsImpl.setContent(this.<String>getColumnOriginalValue("content"));
		newsImpl.setAuthorId(this.<Long>getColumnOriginalValue("authorId"));
		newsImpl.setIsSchoolNews(
			this.<Boolean>getColumnOriginalValue("isSchoolNews"));
		newsImpl.setIsImportant(
			this.<Boolean>getColumnOriginalValue("isImportant"));
		newsImpl.setExpirationDate(
			this.<Date>getColumnOriginalValue("expirationDate"));
		newsImpl.setPublicationDate(
			this.<Date>getColumnOriginalValue("publicationDate"));
		newsImpl.setModificationDate(
			this.<Date>getColumnOriginalValue("modificationDate"));
		newsImpl.setImageId(this.<Long>getColumnOriginalValue("imageId"));

		return newsImpl;
	}

	@Override
	public int compareTo(News news) {
		long primaryKey = news.getPrimaryKey();

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

		if (!(object instanceof News)) {
			return false;
		}

		News news = (News)object;

		long primaryKey = news.getPrimaryKey();

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
	public CacheModel<News> toCacheModel() {
		NewsCacheModel newsCacheModel = new NewsCacheModel();

		newsCacheModel.newsId = getNewsId();

		newsCacheModel.companyId = getCompanyId();

		newsCacheModel.title = getTitle();

		String title = newsCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			newsCacheModel.title = null;
		}

		newsCacheModel.content = getContent();

		String content = newsCacheModel.content;

		if ((content != null) && (content.length() == 0)) {
			newsCacheModel.content = null;
		}

		newsCacheModel.authorId = getAuthorId();

		newsCacheModel.isSchoolNews = isIsSchoolNews();

		newsCacheModel.isImportant = isIsImportant();

		Date expirationDate = getExpirationDate();

		if (expirationDate != null) {
			newsCacheModel.expirationDate = expirationDate.getTime();
		}
		else {
			newsCacheModel.expirationDate = Long.MIN_VALUE;
		}

		Date publicationDate = getPublicationDate();

		if (publicationDate != null) {
			newsCacheModel.publicationDate = publicationDate.getTime();
		}
		else {
			newsCacheModel.publicationDate = Long.MIN_VALUE;
		}

		Date modificationDate = getModificationDate();

		if (modificationDate != null) {
			newsCacheModel.modificationDate = modificationDate.getTime();
		}
		else {
			newsCacheModel.modificationDate = Long.MIN_VALUE;
		}

		newsCacheModel.imageId = getImageId();

		return newsCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<News, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<News, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<News, Object> attributeGetterFunction = entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((News)this);

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

		private static final Function<InvocationHandler, News>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					News.class, ModelWrapper.class);

	}

	private long _newsId;
	private long _companyId;
	private String _title;
	private String _content;
	private long _authorId;
	private boolean _isSchoolNews;
	private boolean _isImportant;
	private Date _expirationDate;
	private Date _publicationDate;
	private Date _modificationDate;
	private long _imageId;

	public <T> T getColumnValue(String columnName) {
		Function<News, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((News)this);
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

		_columnOriginalValues.put("newsId", _newsId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("title", _title);
		_columnOriginalValues.put("content", _content);
		_columnOriginalValues.put("authorId", _authorId);
		_columnOriginalValues.put("isSchoolNews", _isSchoolNews);
		_columnOriginalValues.put("isImportant", _isImportant);
		_columnOriginalValues.put("expirationDate", _expirationDate);
		_columnOriginalValues.put("publicationDate", _publicationDate);
		_columnOriginalValues.put("modificationDate", _modificationDate);
		_columnOriginalValues.put("imageId", _imageId);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("newsId", 1L);

		columnBitmasks.put("companyId", 2L);

		columnBitmasks.put("title", 4L);

		columnBitmasks.put("content", 8L);

		columnBitmasks.put("authorId", 16L);

		columnBitmasks.put("isSchoolNews", 32L);

		columnBitmasks.put("isImportant", 64L);

		columnBitmasks.put("expirationDate", 128L);

		columnBitmasks.put("publicationDate", 256L);

		columnBitmasks.put("modificationDate", 512L);

		columnBitmasks.put("imageId", 1024L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private News _escapedModel;

}