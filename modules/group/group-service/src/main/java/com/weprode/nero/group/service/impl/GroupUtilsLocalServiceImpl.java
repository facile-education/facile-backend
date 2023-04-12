package com.weprode.nero.group.service.impl;

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
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.document.service.ActivityLocalServiceUtil;
import com.weprode.nero.group.service.MembershipActivityLocalServiceUtil;
import com.weprode.nero.group.service.base.GroupUtilsLocalServiceBaseImpl;
import com.weprode.nero.organization.service.OrgDetailsLocalServiceUtil;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.group.model.GroupUtils",
        service = AopService.class
)
public class GroupUtilsLocalServiceImpl extends GroupUtilsLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(GroupUtilsLocalServiceImpl.class);

    public List<Group> getGroupsFromOrganizations(List<Organization> orgsToSearchFor) {
        List<Group> groupToReturn = new ArrayList<>();

        try {
            if (orgsToSearchFor != null && !orgsToSearchFor.isEmpty()) {
                groupToReturn.addAll(groupUtilsFinder.findGroupsByOrganizationId(orgsToSearchFor));
            }
        } catch (Exception e) {
            logger.error("Error while getting the group list for given list of organizations", e);
        }

        return groupToReturn;
    }

    public String getGroupName(long groupId) {
        String groupName = "";

        try {
            Group group = GroupLocalServiceUtil.getGroup(groupId);

            if (group.isRegularSite()) {
                // Personal group
                groupName = group.getName();
            } else {
                // Institutional group
                Organization organization = OrganizationLocalServiceUtil.getOrganization(group.getOrganizationId());
                groupName = OrgUtilsLocalServiceUtil.formatOrgName(organization.getName(), true);
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
        Group group = null;
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
        logger.info("Cleaning up group "+group.getName());

        deleteNews(groupId);
        deleteDLFiles(groupId);
        deleteCDTEvents(groupId);
        deleteDocumentActivity(groupId);
        deleteMembershipActivity(groupId);

        // Liferay group deletion manages:

        // Group
        // GroupInfos
        // Layout
        // LayoutSet
        // UserGroupRole
        // MembershipRequest
        // Blogs
        // MBCategory
        // MBStatsUser
        // MBThread + MBMessages
        // WikiNode
        // on BeforeRemove : communityInfos

        try {
            if (group.isOrganization()) {

                long orgId = group.getOrganizationId();
                logger.info("Deleting organizationId "+orgId);

                // Organization details
                try {
                    OrgDetailsLocalServiceUtil.deleteOrgDetails(orgId);
                } catch (Exception e) {
                    logger.debug(e);
                }

                // Remove organization members
                long[] userIds = UserLocalServiceUtil.getOrganizationUserIds(orgId);
                if (userIds != null && userIds.length > 0) {
                    logger.info("Removing "+userIds.length+" members from organizationId "+orgId+"...");
                    UserLocalServiceUtil.unsetOrganizationUsers(orgId, userIds);
                }

                // Organization itself and the group itself
                logger.info("Deleting organization and group ...");
                OrganizationLocalServiceUtil.deleteOrganization(orgId);

            } else {
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
            // TODO news
            // BlogsStatsUserLocalServiceUtil.deleteStatsUserByGroupId(groupId);
            // BlogEntryInfosLocalServiceUtil.deleteByGroupId(groupId);
            // BlogsEntryLocalServiceUtil.deleteEntries(groupId);
        } catch (Exception e) {
            logger.info("No blog stats user for groupId "+groupId);
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
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Start date is in the very past
            Date startDate = dateFormat.parse("2010-01-01");

            // End data is in the very future
            Date endDate = dateFormat.parse("2030-01-01");


            // TODO cdt
            // List<CDTSession> sessionList = CDTSessionLocalServiceUtil.getGroupSessions(groupId, startDate, endDate, false);
            // if (sessionList != null) {
            //     for (CDTSession session : sessionList) {
            //         try {
            //             CDTSessionLocalServiceUtil.deleteSessionAndDependancies(session.getSessionId());
            //             nbDeletedSessions++;
            //         } catch (Exception e) {
            //         }
            //     }
            // }

            logger.info("Deleted "+nbDeletedSessions+" CDT events ...");
        } catch (Exception e) {
            logger.error("Could not delete CDT events for groupId "+groupId);
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
