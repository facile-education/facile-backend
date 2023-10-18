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

package com.weprode.nero.document.service.impl;

import com.liferay.document.library.kernel.exception.DuplicateFolderNameException;
import com.liferay.document.library.kernel.exception.FileNameException;
import com.liferay.document.library.kernel.exception.NoSuchFolderException;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.NoSuchResourcePermissionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.document.constants.DocumentConstants;
import com.weprode.nero.document.constants.PermissionConstants;
import com.weprode.nero.document.service.ActivityLocalServiceUtil;
import com.weprode.nero.document.service.FileUtilsLocalServiceUtil;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.nero.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.nero.document.service.base.FolderUtilsLocalServiceBaseImpl;
import com.weprode.nero.document.utils.DLAppUtil;
import com.weprode.nero.document.utils.FileNameUtil;
import com.weprode.nero.document.utils.ZipUtil;
import com.weprode.nero.group.constants.ActivityConstants;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.role.constants.NeroRoleConstants;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component(
	property = "model.class.name=com.weprode.nero.document.model.FolderUtils",
	service = AopService.class
)
public class FolderUtilsLocalServiceImpl extends FolderUtilsLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(FolderUtilsLocalServiceImpl.class);

	public Folder getUserRootFolder(long userId) throws PortalException, SystemException {
		User user = UserLocalServiceUtil.getUser(userId);

		Folder rootFolder;
		try {
			rootFolder = DLAppServiceUtil.getFolder(user.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, DocumentConstants.SCHOOL_BAG_FOLDER_NAME);
		} catch (NoSuchFolderException e) {
			logger.info("Root folder does not exists for user " + userId + ". Creating it...");
			rootFolder = DLAppServiceUtil.addFolder(
					UUID.randomUUID().toString(),
					user.getGroupId(),
					DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
					DocumentConstants.SCHOOL_BAG_FOLDER_NAME,
					"Dossier personnel de l'utilsateur",
					new ServiceContext()
			);
		}

		return rootFolder;
	}

	public Folder getSendingBox(long userId) throws PortalException, SystemException {
		final User user = UserLocalServiceUtil.getUser(userId);

		Folder folder;
		try {
			folder = DLAppServiceUtil.getFolder(user.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, DocumentConstants.SENDING_BOX_FOLDER_NAME);
		} catch (NoSuchFolderException e) {
			folder = DLAppServiceUtil.addFolder(
					UUID.randomUUID().toString(),
					user.getGroupId(),
					DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
					DocumentConstants.SENDING_BOX_FOLDER_NAME,
					"Boite d envoi tmp",
					new ServiceContext());
			hideDLFolder(folder.getFolderId());
			PermissionUtilsLocalServiceUtil.setViewPermissionOnResource(folder);
		}

		return folder;
	}

	public Folder getIMBox(long userId) throws PortalException, SystemException {
		final User user = UserLocalServiceUtil.getUser(userId);

		Folder folder;
		try {
			folder = DLAppServiceUtil.getFolder(user.getGroup().getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, DocumentConstants.IM_BOX_FOLDER_NAME);
		} catch (NoSuchFolderException e) {
			folder = DLAppServiceUtil.addFolder(
					UUID.randomUUID().toString(),
					user.getGroup().getGroupId(),
					DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
					DocumentConstants.IM_BOX_FOLDER_NAME,
					"PJs de messagerie",
					new ServiceContext());
			hideDLFolder(folder.getFolderId());
			PermissionUtilsLocalServiceUtil.setViewPermissionOnResource(folder);
		}

		return folder;
	}

	public Folder getTmpFolder(long userId) throws PortalException, SystemException {
		final User user = UserLocalServiceUtil.getUser(userId);

		Folder tmpFolder;
		try {
			tmpFolder = DLAppServiceUtil.getFolder(user.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, DocumentConstants.TMP_FILE_FOLDER_NAME);
		} catch (NoSuchFolderException e) {
			tmpFolder = DLAppServiceUtil.addFolder(
					UUID.randomUUID().toString(),
					user.getGroupId(),
					DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
					DocumentConstants.TMP_FILE_FOLDER_NAME,
					"Fichiers temporaires",
					new ServiceContext());
			hideDLFolder(tmpFolder.getFolderId());
		}

		return tmpFolder;
	}

	public Folder getProgressionFolder(long userId) throws PortalException, SystemException {
		Folder folder;

		final User user = UserLocalServiceUtil.getUser(userId);
		try {
			folder = DLAppServiceUtil.getFolder(user.getGroup().getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, DocumentConstants.PROGRESSION_FOLDER_NAME);
		} catch (NoSuchFolderException e) {
			folder = DLAppServiceUtil.addFolder(
					UUID.randomUUID().toString(),
					user.getGroup().getGroupId(),
					DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
					DocumentConstants.PROGRESSION_FOLDER_NAME,
					"Dossier pour les documents des progressions",
					new ServiceContext());
			hideDLFolder(folder.getFolderId());
		}

		return folder;
	}

	public Folder getThumbnailFolder(long userId) throws PortalException, SystemException {
		final long companyId = UserLocalServiceUtil.getUser(userId).getCompanyId();
		Organization rootOrg = OrgUtilsLocalServiceUtil.getOrCreateRootOrg(companyId);

		Folder tumbnailFolder;
		try {
			tumbnailFolder = DLAppServiceUtil.getFolder(rootOrg.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, DocumentConstants.THUMBNAILS_FOLDER_NAME);
		} catch (NoSuchFolderException e) {
			tumbnailFolder = DLAppLocalServiceUtil.addFolder(
					UUID.randomUUID().toString(),
					UserLocalServiceUtil.getDefaultUser(companyId).getUserId(),
					rootOrg.getGroupId(),
					DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
					DocumentConstants.THUMBNAILS_FOLDER_NAME,
					"Dossier global contenant des thumbnails visibles par tous",
					new ServiceContext());
			hideDLFolder(tumbnailFolder.getFolderId());

			// Set View permission to all
			PermissionUtilsLocalServiceUtil.setViewPermissionOnResource(tumbnailFolder);

			// Set Update permission for roles that can create news  /!\ Assume isUserDelegate have one of the following roles
			List<Role> updateThumbnailsRoles = new ArrayList<>();
			// Direction members
			updateThumbnailsRoles.add(RoleUtilsLocalServiceUtil.getDirectionRole());
			// Secretariat
			updateThumbnailsRoles.add(RoleUtilsLocalServiceUtil.getSecretariatRole());
			// Personals
			updateThumbnailsRoles.add(RoleUtilsLocalServiceUtil.getRole(NeroRoleConstants.DIRECTION));
			updateThumbnailsRoles.add(RoleUtilsLocalServiceUtil.getRole(NeroRoleConstants.DOYEN));
			updateThumbnailsRoles.add(RoleUtilsLocalServiceUtil.getRole(NeroRoleConstants.CONSEILLER_ORIENTATION));
			updateThumbnailsRoles.add(RoleUtilsLocalServiceUtil.getRole(NeroRoleConstants.CONSEILLER_SOCIAL));
			updateThumbnailsRoles.add(RoleUtilsLocalServiceUtil.getRole(NeroRoleConstants.INFIRMIERE));
			updateThumbnailsRoles.add(RoleUtilsLocalServiceUtil.getRole(NeroRoleConstants.PSYCHOLOGUE));
			updateThumbnailsRoles.add(RoleUtilsLocalServiceUtil.getRole(NeroRoleConstants.ASSISTANT_TECHNIQUE));
			updateThumbnailsRoles.add(RoleUtilsLocalServiceUtil.getRole(NeroRoleConstants.CAISSIER_COMPTABLE));
			updateThumbnailsRoles.add(RoleUtilsLocalServiceUtil.getRole(NeroRoleConstants.BIBLIOTHECAIRE));
			updateThumbnailsRoles.add(RoleUtilsLocalServiceUtil.getRole(NeroRoleConstants.SECRETAIRE));
			// Teachers
			updateThumbnailsRoles.add(RoleUtilsLocalServiceUtil.getTeacherRole());
			PermissionUtilsLocalServiceUtil.setUpdatePermissionForRolesOnResource(tumbnailFolder, updateThumbnailsRoles);

		}

		return tumbnailFolder;
	}

	/**
	 * Return the path of a folder until the Root folder (folderId = 0 excluded) containing also the current folder
	 * Example: folder 'test' may have path 'monCartable / folder1 / test'
	 */
	public List<Folder> getFolderPath(Folder folder) throws SystemException, PortalException {
		List<Folder> breadcrumb = new ArrayList<>();

		breadcrumb.add(folder);

		while (folder.getParentFolderId() != 0) {
			folder = folder.getParentFolder();
			breadcrumb.add(folder);
		}

		// Revert list to have the right path
		Collections.reverse(breadcrumb);

		return breadcrumb;
	}

	public List<Folder> getFolderPath(long folderId) throws SystemException, PortalException {
		Folder folder = DLAppServiceUtil.getFolder(folderId);
		return getFolderPath(folder);
	}

	public String downloadFolder(Folder folder, User user) throws PortalException, SystemException, IOException {
		String result = "";

		// Set default permissions
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setAddGroupPermissions(true);

		String zipName = folder.getName();

		Folder temporaryFF = getTmpFolder(user.getUserId());

		long[] fileIdArray = new long[0];
		long[] folderIdArray = new long[1];
		folderIdArray[0] = folder.getFolderId();

		ByteArrayOutputStream baos = null;

		try {
			baos = ZipUtil.createZipStream(folderIdArray, fileIdArray);
			if (zipName.toLowerCase().endsWith(".zip")) {
				zipName = zipName.substring(0, zipName.length() - 4);
			}

			FileEntry zipFile = DLAppUtil.addFileEntry(user, temporaryFF, zipName + ".zip", baos.toByteArray(), DocumentConstants.MODE_RENAME);

			assert zipFile != null;
			result = FileUtilsLocalServiceUtil.getDownloadUrl(zipFile);
		} finally {
			try {
				assert baos != null;
				baos.close();
			} catch (Exception e) {
				logger.error(e);
			}
		}

		return result;
	}

	public int getFolderSize(Folder folder) throws SystemException, PortalException {
		int size = 0;

		// Add size of all subFolders
		List<Folder> subFolders = DLAppServiceUtil.getFolders(folder.getGroupId(), folder.getFolderId());
		for (Folder subFolder: subFolders){
			size += getFolderSize(subFolder);
		}

		// Add size of all presents files in the folder
		List<FileEntry> files = DLAppServiceUtil.getFileEntries(folder.getGroupId(), folder.getFolderId());
		for (FileEntry file: files){
			size += file.getSize();
		}

		return size;
	}

	public Folder getFolderByName(Folder parentFolder, String name) throws PortalException {
		List<Folder> subFolders = DLAppServiceUtil.getFolders(parentFolder.getGroupId(), parentFolder.getFolderId());

		for (Folder subFolder : subFolders) {
			if (subFolder.getName().equals(name)) {
				return subFolder;
			}
		}

		throw new NoSuchFolderException("Cannot find folder " + name + " in folder " + parentFolder.getFolderId());
	}

	public Folder createFolder (User user, long targetFolderId, String name) throws SystemException, PortalException {
		Folder parentFolder = DLAppServiceUtil.getFolder(targetFolderId);

		return DLAppUtil.addFolder(user.getUserId(), parentFolder.getGroupId(), targetFolderId, name, DocumentConstants.MODE_NORMAL);
	}

	public Folder renameFolder (long userId, Folder originFolder, String newName) throws SystemException, PortalException {
		if (PermissionUtilsLocalServiceUtil.hasUserFolderPermission(userId, originFolder, PermissionConstants.ADD_OBJECT)) {    // handle this permission as the RENAME permission (update)

			boolean finished = false;
			int count = 0;
			String suffix = "";
			String folderTitle = FileNameUtil.getValidName(newName);
			Folder renamedFolder = null;

			while (!finished && count < DocumentConstants.NB_RENAMED_VERSIONS) {
				try {
					renamedFolder = DLAppServiceUtil.updateFolder(
							originFolder.getFolderId(),
							folderTitle + suffix,
							originFolder.getDescription(),
							new ServiceContext()
					);
					finished = true;
				} catch (DuplicateFolderNameException e) {
					count++;
					suffix = " (" + count + ")";
				}
			}

			if (renamedFolder == null) {
				throw new PortalException("Reach max renamed versions allowed");
			}

			// Register activity
			ActivityLocalServiceUtil.addActivity(0, renamedFolder.getFolderId(), userId, renamedFolder.getGroupId(), "", renamedFolder.getName(), ActivityConstants.TYPE_FOLDER_MODIFICATION);

			return renamedFolder;
		} else {
			throw new NoSuchResourcePermissionException();
		}
	}

	public Folder moveFolder(long userId, Folder folder, long targetFolderId, int mode) throws PortalException, SystemException {

		logger.info("User " + userId + " moves folder " + folder.getName() + " from folder " + folder.getParentFolderId() + " to destination folder " + targetFolderId + " in mode " + mode);
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setScopeGroupId(folder.getGroupId());
		final Folder destFolder = DLAppServiceUtil.getFolder(targetFolderId);

		if (PermissionUtilsLocalServiceUtil.hasUserFolderPermission(userId, folder, ActionKeys.DELETE)
				&& PermissionUtilsLocalServiceUtil.hasUserFolderPermission(userId, destFolder, PermissionConstants.ADD_OBJECT)) {

			boolean success = false;
			int nbTry = 0;
			String originalTitle = folder.getName();
			String suffix = "";

			// rename folder if needed
			while (!success && nbTry < DocumentConstants.NB_RENAMED_VERSIONS) {
				try {
					nbTry++;
					folder = DLAppServiceUtil.moveFolder(folder.getFolderId(), targetFolderId, serviceContext);
					logger.info("Successfully moved folder " + folder.getName() + " (id " + folder.getFolderId() + ")");
					success = true;

					// Apply parent folder permissions
					PermissionUtilsLocalServiceUtil.setParentPermissionToFolder(folder);

					// Register activity
					ActivityLocalServiceUtil.addActivity(0, folder.getFolderId(), userId, folder.getGroupId(), "", folder.getName(), ActivityConstants.TYPE_FOLDER_MOVE);

				} catch (Exception e) {
					logger.error("An error happened during folder move at nbTry=" + nbTry, e);

					if (mode == DocumentConstants.MODE_NORMAL) {
						List<DLFolder> dlFolders = DLFolderLocalServiceUtil.getFolders(destFolder.getGroupId(), targetFolderId); // Search him by name
						for (DLFolder dlFolder : dlFolders) {
							Folder subFolder = DLAppServiceUtil.getFolder(dlFolder.getFolderId());
							if (subFolder.getName().equals(folder.getName())) {
								if (isParentFolder(subFolder, folder)) {
									mode = DocumentConstants.MODE_RENAME;
								} else {
									throw new FileNameException();
								}
							}
						}
					} else if (mode == DocumentConstants.MODE_RENAME) {
						suffix = " (" + nbTry + ")";
						DLAppServiceUtil.updateFolder(
								folder.getFolderId(),
								originalTitle + suffix,
								folder.getDescription(),
								serviceContext
						);
					} else if (mode == DocumentConstants.MODE_REPLACE) {
						List<DLFolder> dlFolders = DLFolderLocalServiceUtil.getFolders(destFolder.getGroupId(), targetFolderId); // Search him by name
						for (DLFolder dlFolder : dlFolders) {
							Folder subFolder = DLAppServiceUtil.getFolder(dlFolder.getFolderId());
							if (subFolder.getName().equals(folder.getName())) {
								FolderUtilsLocalServiceUtil.deleteFolder(userId, subFolder.getFolderId());
							}
						}
					} else if (mode == DocumentConstants.MODE_MERGE) {
						// Return the folder which already exist
						List<DLFolder> dlFolders = DLFolderLocalServiceUtil.getFolders(destFolder.getGroupId(), targetFolderId); // Search him by name
						for (DLFolder dlFolder : dlFolders) {
							Folder subFolder = DLAppServiceUtil.getFolder(dlFolder.getFolderId());
							if (subFolder.getName().equals(folder.getName())) {
								FolderUtilsLocalServiceUtil.copyFolder(userId, folder.getFolderId(), targetFolderId, DocumentConstants.MODE_MERGE);
								FolderUtilsLocalServiceUtil.deleteFolder(userId, folder.getFolderId());
							}
						}
					} else {
						logger.error("No mode existing with value " + mode);
					}
				}
			}

			return null;
		} else {
			throw new NoSuchResourcePermissionException();
		}
	}

	public Folder copyFolder(long userId, long folderId, long destFolderId, int mode) throws PortalException, SystemException {

		final Folder folder = DLAppServiceUtil.getFolder(folderId);
		final Folder destFolder = DLAppServiceUtil.getFolder(destFolderId);

		if (PermissionUtilsLocalServiceUtil.hasUserFolderPermission(userId, destFolder, PermissionConstants.ADD_OBJECT)) {

			long currFolderId = destFolder.getFolderId();
			while (currFolderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
				if (currFolderId == folderId)
					return null;
				currFolderId = DLAppServiceUtil.getFolder(currFolderId).getParentFolderId();
			}

			// Add permissions
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setAddGroupPermissions(true);

			Folder newFolder = DLAppUtil.addFolder(userId, destFolder.getGroupId(), destFolder.getFolderId(), folder.getName(), mode);

			List<DLFolder> dlFolders = DLFolderLocalServiceUtil.getFolders(folder.getGroupId(), folderId);
			for (DLFolder dlFolder : dlFolders) {
				copyFolder(userId, dlFolder.getFolderId(), newFolder.getFolderId(), DocumentConstants.MODE_RENAME);
			}
			List<FileEntry> fileEntries = DLAppServiceUtil.getFileEntries(folder.getGroupId(), folderId);
			for (FileEntry fileEntry : fileEntries) {
				try {
					FileUtilsLocalServiceUtil.copyFileEntry(userId, fileEntry.getFileEntryId(), newFolder.getFolderId(), true, DocumentConstants.MODE_RENAME);
				} catch (IOException e) {
					logger.error(e);
				}
			}

			// Apply parent folder permissions
			PermissionUtilsLocalServiceUtil.setParentPermissionToFolder(newFolder);

			return newFolder;
		} else {
			throw new NoSuchResourcePermissionException();
		}
	}

	public void deleteFolder(long userId, long folderId) throws PortalException, SystemException {

		// Recursively delete all files and folder to delete all linked stuff (like dropbox entry, indexes, etc..)
		Folder folder = DLAppServiceUtil.getFolder(folderId);
		if (PermissionUtilsLocalServiceUtil.hasUserFolderPermission(userId, folder, ActionKeys.DELETE)) {

			List<FileEntry> fileList = DLAppServiceUtil.getFileEntries(folder.getGroupId(), folderId);
			for (FileEntry file : fileList) {
				FileUtilsLocalServiceUtil.deleteFile(userId, file.getFileEntryId());
			}

			List<Folder> subFolders = DLAppServiceUtil.getFolders(folder.getGroupId(), folderId);
			for (Folder subFolder : subFolders) {
				deleteFolder(userId, subFolder.getFolderId());
			}

			Folder parentFolder = folder.getParentFolder();

			// Delete linked objects
			// indexers, favorite, others...??

			// delete folder
			DLAppServiceUtil.deleteFolder(folderId);

			// Update parentFolder lastPostDate because it lost a subFolder (liferay doesn't handle that behaviour)
			DLFolderLocalServiceUtil.updateLastPostDate(parentFolder.getFolderId(), new Date());

			// Delete previous activity
			ActivityLocalServiceUtil.deleteFolderActivity(folderId);
		} else {
			throw new NoSuchResourcePermissionException();
		}
	}

	private boolean isParentFolder (Folder potentialParentFolder, Folder potentialChildFolder) {
		if (potentialChildFolder.getParentFolderId() == potentialParentFolder.getFolderId()) {
			return true;
		} else {
			if (potentialChildFolder.getParentFolderId() == 0) {
				return false;
			} else {
				try {
					return isParentFolder(potentialParentFolder, potentialChildFolder.getParentFolder());
				} catch (Exception e) {
					return false;
				}
			}
		}
	}

	public Folder getOrCreateGroupRootFolder(long groupId) throws PortalException {
		Folder groupRootFolder = null;

		try {
			groupRootFolder = DLAppServiceUtil.getFolder(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, DocumentConstants.GROUP_FOLDER_NAME);
		} catch (NoSuchFolderException noSuchFolder) {
			try {
				Group group = GroupLocalServiceUtil.getGroup(groupId);
				logger.info("Creating root folder for group " + group.getName(LocaleUtil.getDefault()));
				Folder createdFolder = DLAppLocalServiceUtil.addFolder(
						UUID.randomUUID().toString(),
						UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()),
						groupId,
						DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
						DocumentConstants.GROUP_FOLDER_NAME,
						"Espace de groupe.",
						new ServiceContext());
				PermissionUtilsLocalServiceUtil.addDefaultPermissionsFolder(createdFolder);

				return createdFolder;
			} catch (Exception e) {
				logger.error("Error creating root folder for group " + groupId, e);
			}
		}

		return groupRootFolder;
	}

	public Folder getGroupNewsFolder(long groupId) throws PortalException, SystemException {
		Folder folder;

		Folder groupRootFolder = FolderUtilsLocalServiceUtil.getOrCreateGroupRootFolder(groupId);
		try {
			folder = DLAppServiceUtil.getFolder(groupId, groupRootFolder.getFolderId(), DocumentConstants.NEWS_FOLDER_NAME);
		} catch (NoSuchFolderException e) {
			folder = DLAppLocalServiceUtil.addFolder(
					UUID.randomUUID().toString(),
					UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()),
					groupId,
					groupRootFolder.getFolderId(),
					DocumentConstants.NEWS_FOLDER_NAME,
					"Dossier pour les pièces jointes d'actualités",
					new ServiceContext());

			// Add permissions for all agent roleIds
			List<Long> allRoleIds = new ArrayList<>(RoleUtilsLocalServiceUtil.getAgentsRoleIds());
			allRoleIds.add(RoleUtilsLocalServiceUtil.getStudentRole().getRoleId());
			allRoleIds.add(RoleUtilsLocalServiceUtil.getParentRole().getRoleId());
			for (long agentRoleId : allRoleIds) {
				logger.info("Add view perm on folder " + folder.getFolderId() + " for roleId " + agentRoleId);
				ResourcePermissionLocalServiceUtil.setResourcePermissions(folder.getCompanyId(), DLFolder.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, ""+folder.getFolderId(), agentRoleId, new String[]{"VIEW", "ADD_DOCUMENT", "UPDATE", "DELETE"});
			}
			hideDLFolder(folder.getFolderId());
		}

		return folder;
	}

	public Folder getGroupCourseFolder(long groupId) throws PortalException, SystemException {
		Folder folder;

		Folder groupRootFolder = FolderUtilsLocalServiceUtil.getOrCreateGroupRootFolder(groupId);
		try {
			folder = DLAppServiceUtil.getFolder(groupId, groupRootFolder.getFolderId(), DocumentConstants.COURSE_FOLDER_NAME);
		} catch (NoSuchFolderException e) {
			folder = DLAppLocalServiceUtil.addFolder(
					UUID.randomUUID().toString(),
					UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()),
					groupId,
					groupRootFolder.getFolderId(),
					DocumentConstants.COURSE_FOLDER_NAME,
					"Dossier pour les pièces jointes du cours",
					new ServiceContext());

			// Add permissions for all agent roleIds
			List<Long> allRoleIds = new ArrayList<>(RoleUtilsLocalServiceUtil.getAgentsRoleIds());
			allRoleIds.add(RoleUtilsLocalServiceUtil.getStudentRole().getRoleId());
			allRoleIds.add(RoleUtilsLocalServiceUtil.getParentRole().getRoleId());
			for (long agentRoleId : allRoleIds) {
				logger.info("Add view perm on folder " + folder.getFolderId() + " for roleId " + agentRoleId);
				ResourcePermissionLocalServiceUtil.setResourcePermissions(folder.getCompanyId(), DLFolder.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, ""+folder.getFolderId(), agentRoleId, new String[]{"VIEW", "ADD_DOCUMENT", "UPDATE", "DELETE"});
			}
			hideDLFolder(folder.getFolderId());
		}

		return folder;
	}

	public boolean isGroupFolder (Folder folder) {
		try {
			Group folderGroup = GroupLocalServiceUtil.getGroup(folder.getGroupId());
			return folderGroup.isOrganization() || folderGroup.isRegularSite();
		} catch (Exception e) {
			logger.error("Error determining if folder " + folder.getFolderId() + " belongs to a group or not : " + e.getMessage());
		}

		return false;
	}

	// Returns true if the user is member of the group or org, in case of group folder
	public boolean isAllowedToAccessFolder(long userId, long folderId) {
		try {
			User user = UserLocalServiceUtil.getUser(userId);
			Folder folder = DLAppServiceUtil.getFolder(folderId);
			Group folderGroup = GroupLocalServiceUtil.getGroup(folder.getGroupId());
			if (folderGroup.isOrganization()) {
				if ( !OrganizationLocalServiceUtil.hasUserOrganization(user.getUserId(), folderGroup.getClassPK())
					&& !RoleUtilsLocalServiceUtil.isDirectionMember(user)
					&& !RoleUtilsLocalServiceUtil.isDoyen(user, folderGroup.getClassPK())
					&& !RoleUtilsLocalServiceUtil.isPsychologue(user, folderGroup.getClassPK())
					&& !RoleUtilsLocalServiceUtil.isConseillerSocial(user, folderGroup.getClassPK())) {
					logger.info("User " + user.getUserId() + " tries to access folder " + folderId + " of org group " + folderGroup.getGroupId() + " but does not belong to it");
					return false;
				}
			} else if (folderGroup.isRegularSite()) {
				if (!GroupLocalServiceUtil.hasUserGroup(user.getUserId(), folderGroup.getGroupId())
					&& !RoleUtilsLocalServiceUtil.isDirectionMember(user)) {
					logger.info("User " + user.getUserId() + " tries to access folder " + folderId + " of group " + folderGroup.getGroupId() + " but does not belong to it");
					return false;
				}
			} else if (folderGroup.isUser()) {
				if (folder.getGroupId() != user.getGroupId()) {
					logger.info("User " + user.getUserId() + " tries to access user's folder " + folderId + " of group " + folderGroup.getGroupId() + " but it is not his");
					return false;
				}
			} else {
				logger.info("isAllowedToAccessFolder on folderId " + folderId + " that is of different type");
			}
		} catch (Exception e) {
			logger.error("Error when determining if user " + userId + " is allowed to access folder " + folderId + " " + e.getMessage());
		}

		return true;
	}

	public void hideDLFolder (long folderId) throws PortalException {
		// Hide folder
		DLFolder dlFolder = DLFolderLocalServiceUtil.getFolder(folderId);
		dlFolder.setHidden(true);
		DLFolderLocalServiceUtil.updateDLFolder(dlFolder);
	}
}