package com.weprode.nero.school.life.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.weprode.nero.school.life.model.Notification;
import com.weprode.nero.school.life.service.base.NotificationLocalServiceBaseImpl;
import com.weprode.nero.school.life.service.persistence.NotificationPK;
import com.weprode.nero.user.service.UserRelationshipLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.school.life.model.Notification",
        service = AopService.class
)
public class NotificationLocalServiceImpl extends NotificationLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(NotificationLocalServiceImpl.class);

    public boolean addStudentAndParentsNotification(long studentId, long schoollifeSessionId) {
        NotificationPK pk = new NotificationPK(schoollifeSessionId, studentId);
        notificationPersistence.create(pk);

        // Parents
        List<User> parents = UserRelationshipLocalServiceUtil.getParents(studentId);
        for (User parent : parents) {
            NotificationPK parentPk = new NotificationPK(schoollifeSessionId, parent.getUserId());
            notificationPersistence.create(parentPk);
        }

        return true;
    }

    public List<Long> getUserUnreadSchoollifeSessionIds(long userId) {
        List<Long> unreadSessionIds = new ArrayList<>();

        try {
            List<Notification> notifications = notificationPersistence.findByuserId(userId);
            if (notifications != null) {
                for (Notification notification : notifications) {
                    unreadSessionIds.add(notification.getSchoollifeSessionId());
                }
            }
        } catch (Exception e) {
            logger.debug(e);
        }

        return unreadSessionIds;
    }

    // Marking read means delete the row
    public boolean markNotificationRead(long studentId, long schoollifeSessionId) {
        try {
            NotificationPK pk = new NotificationPK(schoollifeSessionId, studentId);
            Notification notification = notificationPersistence.findByPrimaryKey(pk);
            notificationPersistence.remove(notification);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
