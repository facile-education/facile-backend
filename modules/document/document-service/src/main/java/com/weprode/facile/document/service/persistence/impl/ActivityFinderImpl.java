package com.weprode.facile.document.service.persistence.impl;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.document.model.Activity;
import com.weprode.facile.document.service.ActivityLocalServiceUtil;
import com.weprode.facile.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.facile.document.service.persistence.ActivityFinder;
import com.weprode.facile.group.constants.ActivityConstants;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(service = ActivityFinder.class)
public class ActivityFinderImpl extends ActivityFinderBaseImpl
        implements ActivityFinder {

    private static final Log logger = LogFactoryUtil.getLog(ActivityFinderImpl.class);

    /*@Reference
    private CustomSQL customSQL;

    public static final String FIND_ACTIVITIES_BY_GROUP_IDS =
            ActivityFinder.class.getName() + ".findActivitiesByGroupIds";

    public List<Activity> getActivity(List<Long> groupIds, int start, int end) {

        Session session = null;

        try {
            session = openSession();

            // Build group id list
            StringBuilder groupIdsStr = new StringBuilder();
            for (int i = 0 ; i < groupIds.size() ; i++) {
                groupIdsStr.append(groupIds.get(i));
                if (i != (groupIds.size() - 1)) {
                    groupIdsStr.append(",");
                }
            }

            // Here the only way to inject arrays into an 'IN' clause is to replace a given string
            // Else hibernate embraces the given parameter with quotes and the query doesn't run
            String sql = customSQL.get(getClass(), FIND_ACTIVITIES_BY_GROUP_IDS);
            sql = StringUtil.replace(sql, "[$GROUP_IDS$]", groupIdsStr.toString());
            logger.debug("ActivityFinderImpl : sql="+sql);

            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity("Activity", Activity.class);

            return (List<Activity>) QueryUtil.list(query, getDialect(), start, end);
        } catch (Exception e) {
            logger.error("Error while fetching user groups activities", e);
        }
        finally {
            closeSession(session);
        }

        return null;
    }*/

    public List<Activity> getActivities(List<Long> groupIdList, long creatorId, int start, int end) {
        List<Activity> activityList = new ArrayList<>();

        Session session = null;
        try {
            session = openSession();
            ClassLoader classLoader = getClass().getClassLoader();

            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Activity.class, classLoader);
            dynamicQuery.add(PropertyFactoryUtil.forName("userId").ne(creatorId));

            // Using 'in' is much faster than disjunctions
            Long[] groupIds = new Long[groupIdList.size()];
            int idx = 0;
            for (Long groupId : groupIdList) {
                groupIds[idx] = groupId;
                idx++;
            }
            dynamicQuery.add(PropertyFactoryUtil.forName("groupId").in(groupIds));

            // Order by modification date
            Order modificationDateOrder = OrderFactoryUtil.desc("modificationDate");
            dynamicQuery.addOrder(modificationDateOrder);

            // Execute the query
            activityList = ActivityLocalServiceUtil.dynamicQuery(dynamicQuery, start, end);
        } catch (Exception e) {
            logger.error(e);
        } finally {
            closeSession(session);
        }

        return activityList;
    }

    public List<Activity> getGroupsActivities(long userId, List<Long> groupIdList, Date minDate, Date maxDate,
                                              boolean includeUserActivity, boolean withFileCreation,
                                              boolean withFileModification, boolean withFolderCreation,
                                              boolean withFolderModification) {
        List<Activity> activityList = new ArrayList<>();

        Session session = null;
        try {
            session = openSession();

            ClassLoader classLoader = getClass().getClassLoader();

            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Activity.class, classLoader);

            // Full history contains current user's activity
            if (!includeUserActivity) {
                dynamicQuery.add(PropertyFactoryUtil.forName("userId").ne(userId));
            }

            // Skip default user (news attached files)
            dynamicQuery.add(PropertyFactoryUtil.forName("userId").ne(UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())));

            Long[] groupIds = groupIdList.toArray(new Long[0]);
            dynamicQuery.add(PropertyFactoryUtil.forName("groupId").in(groupIds));
            dynamicQuery.add(RestrictionsFactoryUtil.between("modificationDate", minDate, maxDate));

            // Execute the query
            activityList = ActivityLocalServiceUtil.dynamicQuery(dynamicQuery);

            // Loop over activities to filter those on which current user has no permission
            List<Activity> filteredActivityList = new ArrayList<>();

            for (Activity activity : activityList) {
                try {
                    int type = activity.getType();
                    if ((type == ActivityConstants.TYPE_FILE_CREATION && withFileCreation) ||
                            (type == ActivityConstants.TYPE_FILE_MODIFICATION && withFileModification) ||
                            (type == ActivityConstants.TYPE_FILE_MOVE && withFileModification)) {

                        // Check that the user has the READ permission on the file
                        FileEntry fileEntry = DLAppServiceUtil.getFileEntry(activity.getFileEntryId());
                        if (PermissionUtilsLocalServiceUtil.hasUserFilePermission(userId, fileEntry, ActionKeys.VIEW)) {
                            filteredActivityList.add(activity);
                        }

                        // Folders activity appear in full history only
                    } else if ((type == ActivityConstants.TYPE_FOLDER_CREATION && withFolderCreation) ||
                            (type == ActivityConstants.TYPE_FOLDER_MODIFICATION && withFolderModification) ||
                            (type == ActivityConstants.TYPE_FOLDER_MOVE && withFolderModification)) {

                        // Check that the user has the READ permission on the folder
                        Folder folder = DLAppServiceUtil.getFolder(activity.getFolderId());
                        if (PermissionUtilsLocalServiceUtil.hasUserFolderPermission(userId, folder, ActionKeys.VIEW)) {
                            filteredActivityList.add(activity);
                        }
                    } else if (type == ActivityConstants.TYPE_FILE_DELETION || type == ActivityConstants.TYPE_FOLDER_DELETION) {
                        // No one can see them because it implies some accessibility issues
                        //filteredActivityList.add(activity);
                    }
                } catch (Exception e) {
                    logger.error("Error fetching activity " + activity.getActivityId() + " : may be missing file ou folder", e);
                }
            }
            return filteredActivityList;

        } catch (Exception e) {
            logger.error("Error fetching activities for userId " + userId, e);
        } finally {
            closeSession(session);
        }

        return activityList;
    }

}
