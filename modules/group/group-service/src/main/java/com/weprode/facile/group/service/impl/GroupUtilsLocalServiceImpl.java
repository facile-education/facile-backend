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

package com.weprode.facile.group.service.impl;

import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.document.service.ActivityLocalServiceUtil;
import com.weprode.facile.group.service.CommunityInfosLocalServiceUtil;
import com.weprode.facile.group.service.GroupMembershipLocalServiceUtil;
import com.weprode.facile.group.service.MembershipActivityLocalServiceUtil;
import com.weprode.facile.group.service.base.GroupUtilsLocalServiceBaseImpl;
import com.weprode.facile.news.service.NewsPopulationLocalServiceUtil;
import com.weprode.facile.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.facile.schedule.model.CDTSession;
import com.weprode.facile.schedule.service.CDTSessionLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.group.model.GroupUtils",
        service = AopService.class
)
public class GroupUtilsLocalServiceImpl extends GroupUtilsLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(GroupUtilsLocalServiceImpl.class);

    public String getGroupName(long groupId) {
        String groupName = "";

        try {
            Group group = GroupLocalServiceUtil.getGroup(groupId);

            if (group.isRegularSite()) {
                // Personal group
                groupName = group.getDescriptiveName();
            } else {
                // Institutional group
                Organization organization = OrganizationLocalServiceUtil.getOrganization(group.getOrganizationId());
                groupName = OrgUtilsLocalServiceUtil.formatOrgName(organization.getName(), false);
            }
        } catch (Exception e) {
            logger.debug(e);
        }

        return groupName;
    }

    /**
     * Delete a whole group and its associated objects
     */
    public void groupCleanup(Long groupId) {
        long start = System.currentTimeMillis();

        logger.info("Cleaning group id "+groupId);

        // First check if group id exists
        Group group;
        try {
            group = GroupLocalServiceUtil.getGroup(groupId);
        } catch (Exception e) {
            logger.error("Group id "+groupId+ " does not exist !");
            return;
        }
        if (group == null) {
            logger.error("Group id "+groupId+ " does not exist !");
            return;
        }
        logger.info("Cleaning up group "+group.getName(LocaleUtil.getDefault()));

        deleteNews(groupId);
        deleteDLFiles(groupId);
        deleteCDTEvents(groupId);
        deleteDocumentActivity(groupId);
        deleteMembershipActivity(groupId);

        // Liferay group deletion manages: Group, Layout stuff,  UserGroupRole, MembershipRequest, Blogs, MBCategory, MBThread + MBMessages, etc

        try {
            if (group.isOrganization()) {

                long orgId = group.getOrganizationId();
                logger.info("Deleting organizationId "+orgId);
                // In OrganizationLocalServiceOverride, we manage org members and orgDetails
                OrganizationLocalServiceUtil.deleteOrganization(orgId);

            } else {
                CommunityInfosLocalServiceUtil.deleteCommunityInfos(groupId);
                GroupMembershipLocalServiceUtil.removeGroupMemberships(groupId);
                logger.info("Deleting group itself ...");
                GroupLocalServiceUtil.deleteGroup(groupId);
            }
        } catch (Exception e) {
            logger.error("Error in deleting organization and/or groupId "+group.getGroupId(), e);
        }

        long end = System.currentTimeMillis();
        logger.info("Cleanup done for groupId "+groupId+" in "+(end - start)+" ms");
    }

    private void deleteNews(Long groupId) {
        logger.info("Deleting news ...");

        try {
            NewsPopulationLocalServiceUtil.deleteByGroupId(groupId);
        } catch (Exception e) {
            logger.info("Error deleting news for groupId "+groupId);
        }
    }

    private void deleteDLFiles(Long groupId) {
        logger.info("Deleting DL folders and files ...");

        try {
            // DLFolder
            List<DLFolder> dlFolderList = DLFolderLocalServiceUtil.getFolders(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);
            for (DLFolder dlFolder : dlFolderList) {

                logger.info("Processing DL folder "+dlFolder.getName()+" (id="+dlFolder.getFolderId()+")");

                // DLFileEntry
                DLFileEntryLocalServiceUtil.deleteFileEntries(groupId, dlFolder.getFolderId());

                DLFolderLocalServiceUtil.deleteDLFolder(dlFolder);
            }

        } catch (Exception e) {
            logger.error("Could not delete DL files for groupId "+groupId);
        }
    }

    private void deleteCDTEvents(Long groupId) {
        logger.info("Deleting CDT events ...");

        try {
            int nbDeletedSessions = 0;
            DateFormat dateFormat = new SimpleDateFormat(JSONConstants.ENGLISH_FORMAT);

            // Start date is in the very past
            Date startDate = dateFormat.parse("2010-01-01");

            // End data is in the very future
            Date endDate = dateFormat.parse("2030-01-01");

            List<CDTSession> sessionList = CDTSessionLocalServiceUtil.getGroupSessions(groupId, startDate, endDate, false);
            if (sessionList != null) {
                for (CDTSession session : sessionList) {
                    try {
                        CDTSessionLocalServiceUtil.deleteSessionAndDependencies(session.getSessionId());
                        nbDeletedSessions++;
                    } catch (Exception e) {
                        logger.debug(e);
                    }
                }
            }

            logger.info("Deleted "+nbDeletedSessions+" sessions ...");
        } catch (Exception e) {
            logger.error("Could not delete CDT sessions for groupId "+groupId);
        }
    }

    private void deleteDocumentActivity(long groupId) {
        logger.info("Deleting document activity ...");

        try {
            ActivityLocalServiceUtil.deleteGroupActivity(groupId);
        } catch (Exception e) {
            logger.error("Could not delete document activity for groupId "+groupId, e);
        }
    }

    private void deleteMembershipActivity(long groupId) {
        logger.info("Deleting membership activity ...");

        try {
            MembershipActivityLocalServiceUtil.deleteGroupActivity(groupId);
        } catch (Exception e) {
            logger.error("Could not membership activity for groupId "+groupId, e);
        }
    }

}
