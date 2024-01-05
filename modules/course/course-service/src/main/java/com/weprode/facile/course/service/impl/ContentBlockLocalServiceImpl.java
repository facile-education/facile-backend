/**
 * Copyright (c) 2022-present - Weprode
 * This file is part of FACILE ENT Project.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License (LGPL) as
 * published by the Free Software Foundation (version 2.1 of the License).
 * See LICENCE.txt file
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */

package com.weprode.facile.course.service.impl;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.PropsUtil;
import com.weprode.facile.commons.properties.NeroSystemProperties;
import com.weprode.facile.commons.CommonUtils;
import com.weprode.facile.course.CourseConstants;
import com.weprode.facile.course.exception.UnauthorizedUrlException;
import com.weprode.facile.course.model.ContentBlock;
import com.weprode.facile.course.service.ContentBlockLocalServiceUtil;
import com.weprode.facile.course.service.CourseLocalServiceUtil;
import com.weprode.facile.course.service.HomeworkLocalServiceUtil;
import com.weprode.facile.course.service.SessionContentLocalServiceUtil;
import com.weprode.facile.course.service.base.ContentBlockLocalServiceBaseImpl;
import com.weprode.facile.document.service.FileUtilsLocalServiceUtil;
import com.weprode.facile.document.service.PermissionUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Cédric Lecarpentier
 */
@Component(
	property = "model.class.name=com.weprode.facile.course.model.ContentBlock",
	service = AopService.class
)
public class ContentBlockLocalServiceImpl extends ContentBlockLocalServiceBaseImpl {

	private final Log logger = LogFactoryUtil.getLog(ContentBlockLocalServiceImpl.class);

	// Add new empty content of given type
	// Order is the last position for the given itemId
	public ContentBlock addBlock(long userId, long itemId, int blockType, String blockName, String blockValue, long fileEntryId) throws SystemException, PortalException, IOException, UnauthorizedUrlException {

		ContentBlock block = contentBlockPersistence.create(counterLocalService.increment());
		block.setCourseItemId(itemId);
		block.setBlockType(blockType);
		block.setModificationDate(new Date());
		block.setBlockName(blockName);
		if (fileEntryId != 0) {
			Folder parentFolder;
			if (CourseLocalServiceUtil.isSessionItem(itemId)) {
				parentFolder = SessionContentLocalServiceUtil.getSessionFolder(itemId, true);
			} else {
				parentFolder = HomeworkLocalServiceUtil.getHomeworkFolder(itemId, true);
			}
			FileEntry contentFile = FileUtilsLocalServiceUtil.copyFileEntry(userId, fileEntryId, parentFolder.getFolderId(), true);
			block.setFileEntryId(contentFile.getFileEntryId());

			// Apply default permissions so that students can VIEW
			PermissionUtilsLocalServiceUtil.addDefaultPermissionsFile(contentFile);
		}

		// Content value is either the default one, or the provided one
		switch (blockType) {
			case CourseConstants.TYPE_TEXT:
				if (blockValue.isEmpty()) {
					block.setBlockValue(CourseConstants.DEFAULT_TEXT_NAME);
				} else {
					block.setBlockValue(blockValue);
				}
				break;
			case CourseConstants.TYPE_LINK:
				if (CommonUtils.isValidURI(blockValue)) {
					block.setBlockValue(blockValue);
				} else {
					throw new UnauthorizedUrlException("Url " + blockValue + " is not valid");
				}
				break;
			case CourseConstants.TYPE_VIDEO:
			case CourseConstants.TYPE_H5P:
				if (ContentBlockLocalServiceUtil.isEmbedUrlWhitelisted(blockValue)) {
					block.setBlockValue(blockValue);
				} else {
					throw new UnauthorizedUrlException("Url " + blockValue + " is not whiteListed for an embed content");
				}
				break;
			default:
				break;
		}

		return contentBlockPersistence.update(block);
	}

	public ContentBlock updateBlock (long blockId, String blockName, String blockValue, int order) throws SystemException, UnauthorizedUrlException {

		ContentBlock block = contentBlockPersistence.fetchByPrimaryKey(blockId);

		// Name
		block.setBlockName(blockName);

		// Content Value (url check on embed contents)
		if ((block.getBlockType() == CourseConstants.TYPE_VIDEO || block.getBlockType() == CourseConstants.TYPE_H5P)
				&& !ContentBlockLocalServiceUtil.isEmbedUrlWhitelisted(blockValue)) {
			throw new UnauthorizedUrlException("Url " + blockValue + " is not whiteListed as an authorized embed content");
		} else if (block.getBlockType() == CourseConstants.TYPE_LINK && !CommonUtils.isValidURI(blockValue)) {
			throw new UnauthorizedUrlException("Url " + blockValue + " is not valid");
		}

		block.setBlockValue(blockValue);

		// Order
		int oldOrder = block.getOrder();
		block.setOrder(order);
		block.setModificationDate(new Date());
		block = contentBlockPersistence.update(block);

		// Update the other content's orders
		if (oldOrder != order) {
			List<ContentBlock> itemBlocks = getContentsByItemId(block.getCourseItemId());
			for (ContentBlock subBlock : itemBlocks) {
				// New position is below the old one
				if (oldOrder < order && subBlock.getOrder() > oldOrder && subBlock.getOrder() <= order && subBlock.getBlockId() != blockId) {
					subBlock.setOrder(subBlock.getOrder() - 1);
					logger.info("Decremented order of content " + subBlock.getBlockName() + " of type " + subBlock.getBlockType());
				}
				// New position is after the old one
				if (oldOrder > order && subBlock.getOrder() >= order && subBlock.getOrder() < oldOrder  && subBlock.getBlockId() != blockId) {
					subBlock.setOrder(subBlock.getOrder() + 1);
					logger.info("Incremented order of content " + subBlock.getBlockName() + " of type " + subBlock.getBlockType());
				}
				contentBlockPersistence.update(subBlock);
			}
		}

		return block;
	}

	public List<ContentBlock> getContentsByItemId(long itemId) {
		List<ContentBlock> itemContents = new ArrayList<>();

		try {
			itemContents = contentBlockPersistence.findBycourseItemId(itemId);
		} catch (Exception e) {
			logger.error("Error getting all contents for itemId " + itemId, e);
		}

		return itemContents;
	}


	public List<Long> getFileIds (long itemId) {

		List<Long> fileIds = new ArrayList<>();
		try {
			List<ContentBlock> blocks = getContentsByItemId(itemId);
			for (ContentBlock block : blocks) {
				if (block.getBlockType() == CourseConstants.TYPE_FILE) {
					fileIds.add(block.getFileEntryId());
				}
			}
		} catch (Exception e) {
			logger.error("Error getting fileIds for itemId " + itemId, e);
		}

		return fileIds;
	}

	public List<Long> getAudioFileIds (long itemId) {
		List<Long> audioFileIds = new ArrayList<>();

		try {
			List<ContentBlock> blocks = getContentsByItemId(itemId);
			for (ContentBlock block : blocks) {
				if (block.getBlockType() == CourseConstants.TYPE_RECORD) {
					audioFileIds.add(block.getFileEntryId());
				}
			}
		} catch (Exception e) {
			logger.error("Error getting audioFileIds for itemId " + itemId, e);
		}

		return audioFileIds;
	}

	public boolean deleteBlocksByItemId(long itemId) {
		try {
			contentBlockPersistence.removeBycourseItemId(itemId);
			// Assume here that item content's folder is already deleted
			return true;
		} catch (Exception e) {
			logger.error("Error deleting all contents for itemId " + itemId, e);
		}

		return false;
	}

	public boolean deleteBlock(long blockId) {
		try {
			ContentBlock deletedBlock = contentBlockPersistence.fetchByPrimaryKey(blockId);

			if (deletedBlock.getFileEntryId() != 0) {
				try {
					DLAppServiceUtil.deleteFileEntry(deletedBlock.getFileEntryId());
				} catch (Exception e) {
					logger.error("Error when deleting fileEntry " + deletedBlock.getFileEntryId());
				}
			}
			int deletedOrder = deletedBlock.getOrder();

			contentBlockPersistence.remove(blockId);
			logger.info("Deleted blockId " + blockId);

			// Decrement following orders
			List<ContentBlock> blocks = getContentsByItemId(deletedBlock.getCourseItemId());
			for (ContentBlock block : blocks) {
				if (block.getOrder() > deletedOrder) {
					block.setOrder(block.getOrder() - 1);
					contentBlockPersistence.update(block);
					logger.info("Decremented order of following block " + block.getBlockId());
				}
			}

			return true;

		} catch (Exception e) {
			logger.error("Error when deleting blockId " + blockId, e);
			return false;
		}
	}

	public String convertBlockToHtml (long blockId) {
		String htmlBlock = "";

		try {
			ContentBlock content = contentBlockPersistence.fetchByPrimaryKey(blockId);

			switch (content.getBlockType()) {
				case CourseConstants.TYPE_TEXT:
					htmlBlock = content.getBlockValue();
					break;
				case CourseConstants.TYPE_RECORD:
					htmlBlock = "<audio controls=\"controls\" autobuffer=\"autobuffer\">";
					htmlBlock += "<source src=\"/c/document_library/get_file?fileEntryId=" +  content.getFileEntryId() + "\" />";
					htmlBlock += "</audio>";
					break;
				case CourseConstants.TYPE_LINK:
					htmlBlock = "<a href=\"" + content.getBlockValue() + "\">" + content.getBlockName() + "</a>";
					break;
				case CourseConstants.TYPE_VIDEO:
				case CourseConstants.TYPE_H5P:
					if (!content.getBlockName().isEmpty()) {
						htmlBlock = content.getBlockName() + "<br/>";
					}
					htmlBlock = "<iframe src=\"" + content.getBlockValue() + "\" style=\"border:none;width:100%;\" allow=\"fullscreen\"></iframe>";
					break;
				case CourseConstants.TYPE_FILE:
					htmlBlock = "";
					break;
				default:
					htmlBlock = "Erreur de récupération du contenu";
					break;
			}
		} catch (Exception e) {
			logger.error("Error fetching content " + blockId + " when converting it to HTML", e);
		}

		return htmlBlock;
	}

	public boolean isEmbedUrlWhitelisted (String url) throws UnauthorizedUrlException {
		if (!CommonUtils.isValidURI(url)) {
			throw new UnauthorizedUrlException("Url " + url + " is not valid");
		}

		List<String> domainWhitelist = List.of(
				PropsUtil.get(NeroSystemProperties.XSS_IFRAME_WHITELIST).split(","));

		for (String domain : domainWhitelist) {
			if (url.matches("^"+domain+"/.*$")) {
				return true;
			}
		}
		return false;
	}

}