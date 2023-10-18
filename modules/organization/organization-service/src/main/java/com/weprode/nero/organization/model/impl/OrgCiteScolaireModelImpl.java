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

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.nero.organization.model.OrgCiteScolaire;
import com.weprode.nero.organization.model.OrgCiteScolaireModel;

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
 * The base model implementation for the OrgCiteScolaire service. Represents a row in the &quot;Organization_OrgCiteScolaire&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>OrgCiteScolaireModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link OrgCiteScolaireImpl}.
 * </p>
 *
 * @author Marc Salvat
 * @see OrgCiteScolaireImpl
 * @generated
 */
public class OrgCiteScolaireModelImpl
	extends BaseModelImpl<OrgCiteScolaire> implements OrgCiteScolaireModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a org cite scolaire model instance should use the <code>OrgCiteScolaire</code> interface instead.
	 */
	public static final String TABLE_NAME = "Organization_OrgCiteScolaire";

	public static final Object[][] TABLE_COLUMNS = {
		{"parentENTStructureUAI", Types.VARCHAR},
		{"childENTStructureUAI", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("parentENTStructureUAI", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("childENTStructureUAI", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Organization_OrgCiteScolaire (parentENTStructureUAI VARCHAR(75) null,childENTStructureUAI VARCHAR(75) not null primary key)";

	public static final String TABLE_SQL_DROP =
		"drop table Organization_OrgCiteScolaire";

	public static final String ORDER_BY_JPQL =
		" ORDER BY orgCiteScolaire.childENTStructureUAI ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Organization_OrgCiteScolaire.childENTStructureUAI ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PARENTENTSTRUCTUREUAI_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CHILDENTSTRUCTUREUAI_COLUMN_BITMASK = 2L;

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

	public OrgCiteScolaireModelImpl() {
	}

	@Override
	public String getPrimaryKey() {
		return _childENTStructureUAI;
	}

	@Override
	public void setPrimaryKey(String primaryKey) {
		setChildENTStructureUAI(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _childENTStructureUAI;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((String)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return OrgCiteScolaire.class;
	}

	@Override
	public String getModelClassName() {
		return OrgCiteScolaire.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<OrgCiteScolaire, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<OrgCiteScolaire, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OrgCiteScolaire, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((OrgCiteScolaire)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<OrgCiteScolaire, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<OrgCiteScolaire, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(OrgCiteScolaire)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<OrgCiteScolaire, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<OrgCiteScolaire, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<OrgCiteScolaire, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<OrgCiteScolaire, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<OrgCiteScolaire, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<OrgCiteScolaire, Object>>();
		Map<String, BiConsumer<OrgCiteScolaire, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<OrgCiteScolaire, ?>>();

		attributeGetterFunctions.put(
			"parentENTStructureUAI", OrgCiteScolaire::getParentENTStructureUAI);
		attributeSetterBiConsumers.put(
			"parentENTStructureUAI",
			(BiConsumer<OrgCiteScolaire, String>)
				OrgCiteScolaire::setParentENTStructureUAI);
		attributeGetterFunctions.put(
			"childENTStructureUAI", OrgCiteScolaire::getChildENTStructureUAI);
		attributeSetterBiConsumers.put(
			"childENTStructureUAI",
			(BiConsumer<OrgCiteScolaire, String>)
				OrgCiteScolaire::setChildENTStructureUAI);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public String getParentENTStructureUAI() {
		if (_parentENTStructureUAI == null) {
			return "";
		}
		else {
			return _parentENTStructureUAI;
		}
	}

	@Override
	public void setParentENTStructureUAI(String parentENTStructureUAI) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_parentENTStructureUAI = parentENTStructureUAI;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalParentENTStructureUAI() {
		return getColumnOriginalValue("parentENTStructureUAI");
	}

	@Override
	public String getChildENTStructureUAI() {
		if (_childENTStructureUAI == null) {
			return "";
		}
		else {
			return _childENTStructureUAI;
		}
	}

	@Override
	public void setChildENTStructureUAI(String childENTStructureUAI) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_childENTStructureUAI = childENTStructureUAI;
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
	public OrgCiteScolaire toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, OrgCiteScolaire>
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
		OrgCiteScolaireImpl orgCiteScolaireImpl = new OrgCiteScolaireImpl();

		orgCiteScolaireImpl.setParentENTStructureUAI(
			getParentENTStructureUAI());
		orgCiteScolaireImpl.setChildENTStructureUAI(getChildENTStructureUAI());

		orgCiteScolaireImpl.resetOriginalValues();

		return orgCiteScolaireImpl;
	}

	@Override
	public OrgCiteScolaire cloneWithOriginalValues() {
		OrgCiteScolaireImpl orgCiteScolaireImpl = new OrgCiteScolaireImpl();

		orgCiteScolaireImpl.setParentENTStructureUAI(
			this.<String>getColumnOriginalValue("parentENTStructureUAI"));
		orgCiteScolaireImpl.setChildENTStructureUAI(
			this.<String>getColumnOriginalValue("childENTStructureUAI"));

		return orgCiteScolaireImpl;
	}

	@Override
	public int compareTo(OrgCiteScolaire orgCiteScolaire) {
		String primaryKey = orgCiteScolaire.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof OrgCiteScolaire)) {
			return false;
		}

		OrgCiteScolaire orgCiteScolaire = (OrgCiteScolaire)object;

		String primaryKey = orgCiteScolaire.getPrimaryKey();

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
	public CacheModel<OrgCiteScolaire> toCacheModel() {
		OrgCiteScolaireCacheModel orgCiteScolaireCacheModel =
			new OrgCiteScolaireCacheModel();

		orgCiteScolaireCacheModel.parentENTStructureUAI =
			getParentENTStructureUAI();

		String parentENTStructureUAI =
			orgCiteScolaireCacheModel.parentENTStructureUAI;

		if ((parentENTStructureUAI != null) &&
			(parentENTStructureUAI.length() == 0)) {

			orgCiteScolaireCacheModel.parentENTStructureUAI = null;
		}

		orgCiteScolaireCacheModel.childENTStructureUAI =
			getChildENTStructureUAI();

		String childENTStructureUAI =
			orgCiteScolaireCacheModel.childENTStructureUAI;

		if ((childENTStructureUAI != null) &&
			(childENTStructureUAI.length() == 0)) {

			orgCiteScolaireCacheModel.childENTStructureUAI = null;
		}

		return orgCiteScolaireCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<OrgCiteScolaire, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<OrgCiteScolaire, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OrgCiteScolaire, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((OrgCiteScolaire)this);

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

		private static final Function<InvocationHandler, OrgCiteScolaire>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					OrgCiteScolaire.class, ModelWrapper.class);

	}

	private String _parentENTStructureUAI;
	private String _childENTStructureUAI;

	public <T> T getColumnValue(String columnName) {
		Function<OrgCiteScolaire, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((OrgCiteScolaire)this);
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

		_columnOriginalValues.put(
			"parentENTStructureUAI", _parentENTStructureUAI);
		_columnOriginalValues.put(
			"childENTStructureUAI", _childENTStructureUAI);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("parentENTStructureUAI", 1L);

		columnBitmasks.put("childENTStructureUAI", 2L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private OrgCiteScolaire _escapedModel;

}