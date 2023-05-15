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

package com.liferay.dynamic.data.mapping.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the DDMFormInstanceReport service. Represents a row in the &quot;DDMFormInstanceReport&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceReportModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceReportImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMFormInstanceReport
 * @generated
 */
@ProviderType
public interface DDMFormInstanceReportModel
	extends BaseModel<DDMFormInstanceReport>, CTModel<DDMFormInstanceReport>,
			MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a ddm form instance report model instance should use the {@link DDMFormInstanceReport} interface instead.
	 */

	/**
	 * Returns the primary key of this ddm form instance report.
	 *
	 * @return the primary key of this ddm form instance report
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this ddm form instance report.
	 *
	 * @param primaryKey the primary key of this ddm form instance report
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this ddm form instance report.
	 *
	 * @return the mvcc version of this ddm form instance report
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this ddm form instance report.
	 *
	 * @param mvccVersion the mvcc version of this ddm form instance report
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this ddm form instance report.
	 *
	 * @return the ct collection ID of this ddm form instance report
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this ddm form instance report.
	 *
	 * @param ctCollectionId the ct collection ID of this ddm form instance report
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the form instance report ID of this ddm form instance report.
	 *
	 * @return the form instance report ID of this ddm form instance report
	 */
	public long getFormInstanceReportId();

	/**
	 * Sets the form instance report ID of this ddm form instance report.
	 *
	 * @param formInstanceReportId the form instance report ID of this ddm form instance report
	 */
	public void setFormInstanceReportId(long formInstanceReportId);

	/**
	 * Returns the group ID of this ddm form instance report.
	 *
	 * @return the group ID of this ddm form instance report
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this ddm form instance report.
	 *
	 * @param groupId the group ID of this ddm form instance report
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this ddm form instance report.
	 *
	 * @return the company ID of this ddm form instance report
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this ddm form instance report.
	 *
	 * @param companyId the company ID of this ddm form instance report
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the create date of this ddm form instance report.
	 *
	 * @return the create date of this ddm form instance report
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this ddm form instance report.
	 *
	 * @param createDate the create date of this ddm form instance report
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this ddm form instance report.
	 *
	 * @return the modified date of this ddm form instance report
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this ddm form instance report.
	 *
	 * @param modifiedDate the modified date of this ddm form instance report
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the form instance ID of this ddm form instance report.
	 *
	 * @return the form instance ID of this ddm form instance report
	 */
	public long getFormInstanceId();

	/**
	 * Sets the form instance ID of this ddm form instance report.
	 *
	 * @param formInstanceId the form instance ID of this ddm form instance report
	 */
	public void setFormInstanceId(long formInstanceId);

	/**
	 * Returns the data of this ddm form instance report.
	 *
	 * @return the data of this ddm form instance report
	 */
	@AutoEscape
	public String getData();

	/**
	 * Sets the data of this ddm form instance report.
	 *
	 * @param data the data of this ddm form instance report
	 */
	public void setData(String data);

	@Override
	public DDMFormInstanceReport cloneWithOriginalValues();

}