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

package com.liferay.commerce.pricing.service.persistence.impl;

import com.liferay.commerce.pricing.model.CommercePricingClassCPDefinitionRel;
import com.liferay.commerce.pricing.service.persistence.CommercePricingClassCPDefinitionRelPersistence;
import com.liferay.commerce.pricing.service.persistence.impl.constants.CommercePersistenceConstants;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 * @generated
 */
public abstract class CommercePricingClassCPDefinitionRelFinderBaseImpl
	extends BasePersistenceImpl<CommercePricingClassCPDefinitionRel> {

	public CommercePricingClassCPDefinitionRelFinderBaseImpl() {
		setModelClass(CommercePricingClassCPDefinitionRel.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put(
			"CommercePricingClassCPDefinitionRelId",
			"CPricingClassCPDefinitionRelId");

		setDBColumnNames(dbColumnNames);
	}

	@Override
	public Set<String> getBadColumnNames() {
		return commercePricingClassCPDefinitionRelPersistence.
			getBadColumnNames();
	}

	@Override
	@Reference(
		target = CommercePersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = CommercePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = CommercePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected CommercePricingClassCPDefinitionRelPersistence
		commercePricingClassCPDefinitionRelPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		CommercePricingClassCPDefinitionRelFinderBaseImpl.class);

}