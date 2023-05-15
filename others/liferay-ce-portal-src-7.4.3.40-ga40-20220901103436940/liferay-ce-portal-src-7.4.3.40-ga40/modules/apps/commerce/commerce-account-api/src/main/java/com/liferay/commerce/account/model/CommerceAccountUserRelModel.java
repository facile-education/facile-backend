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

package com.liferay.commerce.account.model;

import com.liferay.commerce.account.service.persistence.CommerceAccountUserRelPK;
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CommerceAccountUserRel service. Represents a row in the &quot;CommerceAccountUserRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.account.model.impl.CommerceAccountUserRelModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.account.model.impl.CommerceAccountUserRelImpl</code>.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceAccountUserRel
 * @generated
 */
@ProviderType
public interface CommerceAccountUserRelModel
	extends AuditedModel, BaseModel<CommerceAccountUserRel>, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce account user rel model instance should use the {@link CommerceAccountUserRel} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce account user rel.
	 *
	 * @return the primary key of this commerce account user rel
	 */
	public CommerceAccountUserRelPK getPrimaryKey();

	/**
	 * Sets the primary key of this commerce account user rel.
	 *
	 * @param primaryKey the primary key of this commerce account user rel
	 */
	public void setPrimaryKey(CommerceAccountUserRelPK primaryKey);

	/**
	 * Returns the commerce account ID of this commerce account user rel.
	 *
	 * @return the commerce account ID of this commerce account user rel
	 */
	public long getCommerceAccountId();

	/**
	 * Sets the commerce account ID of this commerce account user rel.
	 *
	 * @param commerceAccountId the commerce account ID of this commerce account user rel
	 */
	public void setCommerceAccountId(long commerceAccountId);

	/**
	 * Returns the commerce account user ID of this commerce account user rel.
	 *
	 * @return the commerce account user ID of this commerce account user rel
	 */
	public long getCommerceAccountUserId();

	/**
	 * Sets the commerce account user ID of this commerce account user rel.
	 *
	 * @param commerceAccountUserId the commerce account user ID of this commerce account user rel
	 */
	public void setCommerceAccountUserId(long commerceAccountUserId);

	/**
	 * Returns the commerce account user uuid of this commerce account user rel.
	 *
	 * @return the commerce account user uuid of this commerce account user rel
	 */
	public String getCommerceAccountUserUuid();

	/**
	 * Sets the commerce account user uuid of this commerce account user rel.
	 *
	 * @param commerceAccountUserUuid the commerce account user uuid of this commerce account user rel
	 */
	public void setCommerceAccountUserUuid(String commerceAccountUserUuid);

	/**
	 * Returns the company ID of this commerce account user rel.
	 *
	 * @return the company ID of this commerce account user rel
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce account user rel.
	 *
	 * @param companyId the company ID of this commerce account user rel
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce account user rel.
	 *
	 * @return the user ID of this commerce account user rel
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce account user rel.
	 *
	 * @param userId the user ID of this commerce account user rel
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce account user rel.
	 *
	 * @return the user uuid of this commerce account user rel
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce account user rel.
	 *
	 * @param userUuid the user uuid of this commerce account user rel
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce account user rel.
	 *
	 * @return the user name of this commerce account user rel
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce account user rel.
	 *
	 * @param userName the user name of this commerce account user rel
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce account user rel.
	 *
	 * @return the create date of this commerce account user rel
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce account user rel.
	 *
	 * @param createDate the create date of this commerce account user rel
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce account user rel.
	 *
	 * @return the modified date of this commerce account user rel
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce account user rel.
	 *
	 * @param modifiedDate the modified date of this commerce account user rel
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	@Override
	public CommerceAccountUserRel cloneWithOriginalValues();

}