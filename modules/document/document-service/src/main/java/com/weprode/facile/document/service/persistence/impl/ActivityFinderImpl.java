package com.weprode.facile.document.service.persistence.impl;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.document.model.Activity;
import com.weprode.facile.document.service.ActivityLocalServiceUtil;
import com.weprode.facile.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.facile.document.service.persistence.ActivityFinder;
import com.weprode.facile.group.constants.ActivityConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(service = ActivityFinder.class)
public class ActivityFinderImpl extends ActivityFinderBaseImpl
        implements ActivityFinder {

    private static final Log logger = LogFactoryUtil.getLog(ActivityFinderImpl.class);

    @Reference
    private CustomSQL customSQL;

    private static final String COUNT_SCHOOL_ACTIVITIES = ActivityFinder.class.getName() + ".countSchoolActivities";

    public int countSchoolActivities(long schoolId, Date minDate, Date maxDate) {

        Session session = null;

        try {
            session = openSession();

            String sql = customSQL.get(getClass(), COUNT_SCHOOL_ACTIVITIES);
            if (schoolId != 0) {
                sql += " AND uo.organizationId = " + schoolId;
            }

            SQLQuery query = session.createSQLQuery(sql);
            QueryPos qPos = QueryPos.getInstance(query);

            qPos.add(CalendarUtil.getTimestamp(minDate));
            qPos.add(CalendarUtil.getTimestamp(maxDate));

            return ((BigInteger) query.uniqueResult()).intValue();
        } catch (Exception e) {
            logger.error("Error while counting school activities", e);
        }
        finally {
            closeSession(session);
        }

        return 0;
    }

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
                        FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(activity.getFileEntryId());
                        if (PermissionUtilsLocalServiceUtil.hasUserFilePermission(userId, fileEntry, ActionKeys.VIEW)) {
                            filteredActivityList.add(activity);
                        }

                        // Folders activity appear in full history only
                    } else if ((type == ActivityConstants.TYPE_FOLDER_CREATION && withFolderCreation) ||
                            (type == ActivityConstants.TYPE_FOLDER_MODIFICATION && withFolderModification) ||
                            (type == ActivityConstants.TYPE_FOLDER_MOVE && withFolderModification)) {

                        // Check that the user has the READ permission on the folder
                        Folder folder = DLAppLocalServiceUtil.getFolder(activity.getFolderId());
                        if (PermissionUtilsLocalServiceUtil.hasUserFolderPermission(userId, folder, ActionKeys.VIEW)) {
                            filteredActivityList.add(activity);
                        }
                    } else if (type == ActivityConstants.TYPE_FILE_DELETION || type == ActivityConstants.TYPE_FOLDER_DELETION) {
                        // No one can see them because it implies some accessibility issues
                        //filteredActivityList.add(activity);
                    }
                } catch (Exception e) {
                    logger.error("Error fetching activity " + activity.getActivityId() + " : may be missing file or folder : " + e.getMessage());
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
