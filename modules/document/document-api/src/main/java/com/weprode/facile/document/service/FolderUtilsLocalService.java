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
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

import org.json.JSONObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for FolderUtils. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see FolderUtilsLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface FolderUtilsLocalService extends BaseLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.facile.document.service.impl.FolderUtilsLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the folder utils local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link FolderUtilsLocalServiceUtil} if injection and service tracking are not available.
	 */
	public Folder copyFolder(
			long userId, long folderId, long destFolderId, int mode)
		throws PortalException, SystemException;

	public Folder createFolder(User user, long targetFolderId, String name)
		throws PortalException, SystemException;

	public void deleteFolder(long userId, long folderId)
		throws PortalException, SystemException;

	public JSONObject format(long userId, Folder folder)
		throws PortalException, SystemException;

	public JSONObject format(long userId, Folder folder, int space)
		throws PortalException, SystemException;

	public JSONObject format(
			long userId, Folder folder, int space, boolean withDetails)
		throws PortalException, SystemException;

	public JSONObject format(
		User user, Folder folder, int space, boolean withDetails);

	public JSONObject formatWithOnlyMandatoryFields(Folder folder);

	public JSONObject formatWithOnlyMandatoryFields(long folderId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Folder getFolderByName(Folder parentFolder, String name)
		throws PortalException;

	/**
	 * Return the path of a folder until the Root folder (folderId = 0 excluded) containing also the current folder
	 * Example: folder 'test' may have path 'monCartable / folder1 / test'
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Folder> getFolderPath(Folder folder)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Folder> getFolderPath(long folderId)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFolderSize(Folder folder)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Folder getGroupCourseFolder(long groupId)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Folder getGroupNewsFolder(long groupId)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Folder getOrCreateGroupRootFolder(long groupId)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Folder getThumbnailFolder() throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Folder getUserMessagingAttachedFilesFolder(long userId)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Folder getUserRootFolder(long userId)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Folder getUserTmpFolder(long userId)
		throws PortalException, SystemException;

	public void hideDLFolder(long folderId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isAllowedToAccessFolder(long userId, long folderId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isGroupFolder(Folder folder);

	public Folder moveFolder(
			long userId, Folder folder, long targetFolderId, int mode)
		throws PortalException, SystemException;

	public Folder renameFolder(long userId, Folder originFolder, String newName)
		throws PortalException, SystemException;

}