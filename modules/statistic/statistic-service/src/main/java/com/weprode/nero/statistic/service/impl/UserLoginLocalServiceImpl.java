package com.weprode.nero.statistic.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.statistic.model.UserLogin;
import com.weprode.nero.statistic.service.UserLoginLocalServiceUtil;
import com.weprode.nero.statistic.service.base.UserLoginLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.statistic.model.UserLogin",
        service = AopService.class
)
public class UserLoginLocalServiceImpl extends UserLoginLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(UserLoginLocalServiceImpl.class);

    public boolean addUserLogin(User user, boolean isMobileApp) {
        long userLoginId;

        try {
            userLoginId = counterLocalService.increment();
            UserLogin userLogin = userLoginLocalService.createUserLogin(userLoginId);
            userLogin.setUserId(user.getUserId());
            userLogin.setLoginDate(new Date());
            userLogin.setIsMobileApp(isMobileApp);

            // Role
            int role = getUserRole(user);
            userLogin.setRole(role);

            // Rattach school
            Organization rattachSchool = UserOrgsLocalServiceUtil.getEtabRatachement(user);
            userLogin.setSchoolId(rattachSchool.getOrganizationId());
            UserLoginLocalServiceUtil.updateUserLogin(userLogin);

            return true;
        } catch (Exception e) {
            logger.error("Error adding UserLogin for user " + user.getFullName() + " (id "+user.getUserId()+")", e);
        }

        return false;
    }

    public List<Long> getLoggedStudents(long schoolId, int nbDays) {
        List<Long> loggedUserIds = new ArrayList<>();

        // Threashold date
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -nbDays);
        Date thresholdDate = cal.getTime();

        try {
            List<UserLogin> userLoginList = userLoginPersistence.findByschoolId_role(schoolId, 1);
            if (userLoginList != null) {
                for (UserLogin userLogin : userLoginList) {
                    if (userLogin.getLoginDate().after(thresholdDate)) {
                        loggedUserIds.add(userLogin.getUserId());
                    }
                }
            }
        } catch (Exception e) {
            logger.debug(e);
        }

        return loggedUserIds;
    }

    private int getUserRole(User user) {
        if (RoleUtilsLocalServiceUtil.isStudent(user)) {
            return 1;
        } else if (RoleUtilsLocalServiceUtil.isParent(user)) {
            return 2;
        } else if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
            return 3;
        } else {
            return 4;
        }
    }
}
