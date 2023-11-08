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

package com.weprode.facile.application.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Application}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Application
 * @generated
 */
public class ApplicationWrapper
	extends BaseModelWrapper<Application>
	implements Application, ModelWrapper<Application> {

	public ApplicationWrapper(Application application) {
		super(application);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("applicationId", getApplicationId());
		attributes.put("applicationName", getApplicationName());
		attributes.put("applicationKey", getApplicationKey());
		attributes.put("categoryName", getCategoryName());
		attributes.put("image", getImage());
		attributes.put("hasCustomUrl", isHasCustomUrl());
		attributes.put("hasGlobalUrl", isHasGlobalUrl());
		attributes.put("globalUrl", getGlobalUrl());
		attributes.put("exportUser", isExportUser());
		attributes.put("exportParent", isExportParent());
		attributes.put("exportStudent", isExportStudent());
		attributes.put("exportTeacher", isExportTeacher());
		attributes.put("exportOther", isExportOther());
		attributes.put("menuEntryId", getMenuEntryId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long applicationId = (Long)attributes.get("applicationId");

		if (applicationId != null) {
			setApplicationId(applicationId);
		}

		String applicationName = (String)attributes.get("applicationName");

		if (applicationName != null) {
			setApplicationName(applicationName);
		}

		String applicationKey = (String)attributes.get("applicationKey");

		if (applicationKey != null) {
			setApplicationKey(applicationKey);
		}

		String categoryName = (String)attributes.get("categoryName");

		if (categoryName != null) {
			setCategoryName(categoryName);
		}

		String image = (String)attributes.get("image");

		if (image != null) {
			setImage(image);
		}

		Boolean hasCustomUrl = (Boolean)attributes.get("hasCustomUrl");

		if (hasCustomUrl != null) {
			setHasCustomUrl(hasCustomUrl);
		}

		Boolean hasGlobalUrl = (Boolean)attributes.get("hasGlobalUrl");

		if (hasGlobalUrl != null) {
			setHasGlobalUrl(hasGlobalUrl);
		}

		String globalUrl = (String)attributes.get("globalUrl");

		if (globalUrl != null) {
			setGlobalUrl(globalUrl);
		}

		Boolean exportUser = (Boolean)attributes.get("exportUser");

		if (exportUser != null) {
			setExportUser(exportUser);
		}

		Boolean exportParent = (Boolean)attributes.get("exportParent");

		if (exportParent != null) {
			setExportParent(exportParent);
		}

		Boolean exportStudent = (Boolean)attributes.get("exportStudent");

		if (exportStudent != null) {
			setExportStudent(exportStudent);
		}

		Boolean exportTeacher = (Boolean)attributes.get("exportTeacher");

		if (exportTeacher != null) {
			setExportTeacher(exportTeacher);
		}

		Boolean exportOther = (Boolean)attributes.get("exportOther");

		if (exportOther != null) {
			setExportOther(exportOther);
		}

		Long menuEntryId = (Long)attributes.get("menuEntryId");

		if (menuEntryId != null) {
			setMenuEntryId(menuEntryId);
		}
	}

	@Override
	public Application cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the application ID of this application.
	 *
	 * @return the application ID of this application
	 */
	@Override
	public long getApplicationId() {
		return model.getApplicationId();
	}

	/**
	 * Returns the application key of this application.
	 *
	 * @return the application key of this application
	 */
	@Override
	public String getApplicationKey() {
		return model.getApplicationKey();
	}

	/**
	 * Returns the application name of this application.
	 *
	 * @return the application name of this application
	 */
	@Override
	public String getApplicationName() {
		return model.getApplicationName();
	}

	/**
	 * Returns the category name of this application.
	 *
	 * @return the category name of this application
	 */
	@Override
	public String getCategoryName() {
		return model.getCategoryName();
	}

	/**
	 * Returns the export other of this application.
	 *
	 * @return the export other of this application
	 */
	@Override
	public boolean getExportOther() {
		return model.getExportOther();
	}

	/**
	 * Returns the export parent of this application.
	 *
	 * @return the export parent of this application
	 */
	@Override
	public boolean getExportParent() {
		return model.getExportParent();
	}

	/**
	 * Returns the export student of this application.
	 *
	 * @return the export student of this application
	 */
	@Override
	public boolean getExportStudent() {
		return model.getExportStudent();
	}

	/**
	 * Returns the export teacher of this application.
	 *
	 * @return the export teacher of this application
	 */
	@Override
	public boolean getExportTeacher() {
		return model.getExportTeacher();
	}

	/**
	 * Returns the export user of this application.
	 *
	 * @return the export user of this application
	 */
	@Override
	public boolean getExportUser() {
		return model.getExportUser();
	}

	/**
	 * Returns the global url of this application.
	 *
	 * @return the global url of this application
	 */
	@Override
	public String getGlobalUrl() {
		return model.getGlobalUrl();
	}

	/**
	 * Returns the has custom url of this application.
	 *
	 * @return the has custom url of this application
	 */
	@Override
	public boolean getHasCustomUrl() {
		return model.getHasCustomUrl();
	}

	/**
	 * Returns the has global url of this application.
	 *
	 * @return the has global url of this application
	 */
	@Override
	public boolean getHasGlobalUrl() {
		return model.getHasGlobalUrl();
	}

	/**
	 * Returns the image of this application.
	 *
	 * @return the image of this application
	 */
	@Override
	public String getImage() {
		return model.getImage();
	}

	/**
	 * Returns the menu entry ID of this application.
	 *
	 * @return the menu entry ID of this application
	 */
	@Override
	public long getMenuEntryId() {
		return model.getMenuEntryId();
	}

	/**
	 * Returns the primary key of this application.
	 *
	 * @return the primary key of this application
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns <code>true</code> if this application is export other.
	 *
	 * @return <code>true</code> if this application is export other; <code>false</code> otherwise
	 */
	@Override
	public boolean isExportOther() {
		return model.isExportOther();
	}

	/**
	 * Returns <code>true</code> if this application is export parent.
	 *
	 * @return <code>true</code> if this application is export parent; <code>false</code> otherwise
	 */
	@Override
	public boolean isExportParent() {
		return model.isExportParent();
	}

	/**
	 * Returns <code>true</code> if this application is export student.
	 *
	 * @return <code>true</code> if this application is export student; <code>false</code> otherwise
	 */
	@Override
	public boolean isExportStudent() {
		return model.isExportStudent();
	}

	/**
	 * Returns <code>true</code> if this application is export teacher.
	 *
	 * @return <code>true</code> if this application is export teacher; <code>false</code> otherwise
	 */
	@Override
	public boolean isExportTeacher() {
		return model.isExportTeacher();
	}

	/**
	 * Returns <code>true</code> if this application is export user.
	 *
	 * @return <code>true</code> if this application is export user; <code>false</code> otherwise
	 */
	@Override
	public boolean isExportUser() {
		return model.isExportUser();
	}

	/**
	 * Returns <code>true</code> if this application is has custom url.
	 *
	 * @return <code>true</code> if this application is has custom url; <code>false</code> otherwise
	 */
	@Override
	public boolean isHasCustomUrl() {
		return model.isHasCustomUrl();
	}

	/**
	 * Returns <code>true</code> if this application is has global url.
	 *
	 * @return <code>true</code> if this application is has global url; <code>false</code> otherwise
	 */
	@Override
	public boolean isHasGlobalUrl() {
		return model.isHasGlobalUrl();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the application ID of this application.
	 *
	 * @param applicationId the application ID of this application
	 */
	@Override
	public void setApplicationId(long applicationId) {
		model.setApplicationId(applicationId);
	}

	/**
	 * Sets the application key of this application.
	 *
	 * @param applicationKey the application key of this application
	 */
	@Override
	public void setApplicationKey(String applicationKey) {
		model.setApplicationKey(applicationKey);
	}

	/**
	 * Sets the application name of this application.
	 *
	 * @param applicationName the application name of this application
	 */
	@Override
	public void setApplicationName(String applicationName) {
		model.setApplicationName(applicationName);
	}

	/**
	 * Sets the category name of this application.
	 *
	 * @param categoryName the category name of this application
	 */
	@Override
	public void setCategoryName(String categoryName) {
		model.setCategoryName(categoryName);
	}

	/**
	 * Sets whether this application is export other.
	 *
	 * @param exportOther the export other of this application
	 */
	@Override
	public void setExportOther(boolean exportOther) {
		model.setExportOther(exportOther);
	}

	/**
	 * Sets whether this application is export parent.
	 *
	 * @param exportParent the export parent of this application
	 */
	@Override
	public void setExportParent(boolean exportParent) {
		model.setExportParent(exportParent);
	}

	/**
	 * Sets whether this application is export student.
	 *
	 * @param exportStudent the export student of this application
	 */
	@Override
	public void setExportStudent(boolean exportStudent) {
		model.setExportStudent(exportStudent);
	}

	/**
	 * Sets whether this application is export teacher.
	 *
	 * @param exportTeacher the export teacher of this application
	 */
	@Override
	public void setExportTeacher(boolean exportTeacher) {
		model.setExportTeacher(exportTeacher);
	}

	/**
	 * Sets whether this application is export user.
	 *
	 * @param exportUser the export user of this application
	 */
	@Override
	public void setExportUser(boolean exportUser) {
		model.setExportUser(exportUser);
	}

	/**
	 * Sets the global url of this application.
	 *
	 * @param globalUrl the global url of this application
	 */
	@Override
	public void setGlobalUrl(String globalUrl) {
		model.setGlobalUrl(globalUrl);
	}

	/**
	 * Sets whether this application is has custom url.
	 *
	 * @param hasCustomUrl the has custom url of this application
	 */
	@Override
	public void setHasCustomUrl(boolean hasCustomUrl) {
		model.setHasCustomUrl(hasCustomUrl);
	}

	/**
	 * Sets whether this application is has global url.
	 *
	 * @param hasGlobalUrl the has global url of this application
	 */
	@Override
	public void setHasGlobalUrl(boolean hasGlobalUrl) {
		model.setHasGlobalUrl(hasGlobalUrl);
	}

	/**
	 * Sets the image of this application.
	 *
	 * @param image the image of this application
	 */
	@Override
	public void setImage(String image) {
		model.setImage(image);
	}

	/**
	 * Sets the menu entry ID of this application.
	 *
	 * @param menuEntryId the menu entry ID of this application
	 */
	@Override
	public void setMenuEntryId(long menuEntryId) {
		model.setMenuEntryId(menuEntryId);
	}

	/**
	 * Sets the primary key of this application.
	 *
	 * @param primaryKey the primary key of this application
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected ApplicationWrapper wrap(Application application) {
		return new ApplicationWrapper(application);
	}

}