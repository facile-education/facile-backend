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

package com.weprode.facile.document.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.*;

import org.json.JSONArray;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for PermissionUtils. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see PermissionUtilsLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface PermissionUtilsLocalService extends BaseLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.facile.document.service.impl.PermissionUtilsLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the permission utils local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link PermissionUtilsLocalServiceUtil} if injection and service tracking are not available.
	 */
	public void addDefaultPermissionsFile(FileEntry fileEntry)
		throws PortalException, SystemException;

	public void addDefaultPermissionsFolder(Folder folder)
		throws PortalException, SystemException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<String> getPermissionRoles(long groupId)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasRoleFilePermission(
		Role role, FileEntry fileEntry, String actionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasRoleFolderPermission(
		Role role, Folder folder, String actionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasUserFilePermission(
		long userId, FileEntry fileEntry, String actionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasUserFolderPermission(
		long userId, Folder folder, String actionId);

	public void setParentPermissionToFile(FileEntry fileEntry)
		throws PortalException, SystemException;

	public void setParentPermissionToFolder(Folder folder)
		throws PortalException, SystemException;

	public void setThumbnailFolderPermissions(long thumbnailFolderId)
		throws PortalException, SystemException;

	/**
	 * Set the VIEW permission to a FileEntry or a Folder for ALL users
	 */
	public void setViewPermissionOnResource(Object resource)
		throws PortalException, SystemException;

	public void validateFullPermission(
			User user, long objectId, String type, JSONArray permissions,
			boolean applyToChild, long scopeGrpId)
		throws PortalException, SystemException;

}