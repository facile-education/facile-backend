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

package com.weprode.nero.document.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.io.File;
import java.io.IOException;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for FileUtils. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see FileUtilsLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface FileUtilsLocalService extends BaseLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.nero.document.service.impl.FileUtilsLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the file utils local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link FileUtilsLocalServiceUtil} if injection and service tracking are not available.
	 */
	public File convertAudioToMP3(String fileName, File audioFile)
		throws IOException, SystemException;

	public FileEntry copyFileEntry(
			long userId, long fileId, long destFolderId,
			boolean copyFileContent)
		throws IOException, PortalException, SystemException;

	public FileEntry copyFileEntry(
			long userId, long fileId, long destFolderId,
			boolean copyFileContent, int mode)
		throws IOException, PortalException, SystemException;

	public FileEntry createGeogebraFile(User user, long folderId, String name)
		throws PortalException, SystemException;

	public FileEntry createHtmlFile(User user, long folderId, String name)
		throws PortalException, SystemException;

	public FileEntry createLoolFile(
			User user, long folderId, String name, String type)
		throws Exception;

	public FileEntry createMindMapFile(User user, long folderId, String name)
		throws IOException, PortalException, SystemException;

	public FileEntry createScratchFile(User user, long folderId, String name)
		throws PortalException, SystemException;

	public void deleteFile(long userId, long fileId)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String getDisplayUrl(
			FileEntry file, long versionId, long userId, boolean readOnly)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String getDownloadUrl(FileEntry file);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getSizeInMegaOctet(long pSize);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isGroupFile(long fileEntryId);

	public FileEntry moveFileEntry(long userId, long fileId, long destFolderId)
		throws PortalException, SystemException;

	public FileEntry moveFileEntry(
			long userId, long fileId, long destFolderId, int mode)
		throws PortalException, SystemException;

	public FileEntry renameFile(
			long userId, FileEntry originFile, String newName)
		throws PortalException, SystemException;

	public String sanitizeHTMLContent(String content);

}