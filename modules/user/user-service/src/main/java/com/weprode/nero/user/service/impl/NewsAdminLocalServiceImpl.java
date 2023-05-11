package com.weprode.nero.user.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.user.model.NewsAdmin;
import com.weprode.nero.user.service.NewsAdminLocalServiceUtil;
import com.weprode.nero.user.service.UserSearchLocalServiceUtil;
import com.weprode.nero.user.service.base.NewsAdminLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.user.model.NewsAdmin",
        service = AopService.class
)
public class NewsAdminLocalServiceImpl extends NewsAdminLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(NewsAdminLocalServiceImpl.class);

    public NewsAdmin addDelegate() throws SystemException {
        final long blogEntryDelegateId = counterLocalService.increment();

        return this.createNewsAdmin(blogEntryDelegateId);
    }

    public boolean addSchoolDelegate(long userId, long schoolId) {
        try {
            NewsAdmin newsAdmin = addDelegate();
            newsAdmin.setUserId(userId);
            newsAdmin.setSchoolId(schoolId);
            NewsAdminLocalServiceUtil.updateNewsAdmin(newsAdmin);

            return true;
        } catch (Exception e) {
            logger.error("Error when adding user " + userId + " news delegate for school " + schoolId, e);
        }

        return false;
    }

    public boolean removeSchoolDelegate(long userId, long schoolId) {
        try {
            List<NewsAdmin> userDelegations = newsAdminPersistence.findByuserId(userId);
            if (userDelegations != null) {
                for (NewsAdmin userDelegation : userDelegations) {
                    if (userDelegation.getSchoolId() == schoolId) {
                        NewsAdminLocalServiceUtil.deleteNewsAdmin(userDelegation.getNewsAdminId());
                        return true;
                    }
                }
            }

        } catch (Exception e) {
            logger.error("Error when removing user " + userId + " news delegate for school " + schoolId, e);
        }

        return false;
    }

    public List<User> getSchoolDelegates(Long schoolId) {
        List<User> schoolDelegates = new ArrayList<>();

        try {
            List<NewsAdmin> schoolDelegations = newsAdminPersistence.findByschoolId(schoolId);
            for (NewsAdmin schoolDelegation : schoolDelegations) {
                User delegateUser;

                try {
                    delegateUser = UserLocalServiceUtil.getUser(schoolDelegation.getUserId());
                }
                catch (Exception e) {
                    continue;
                }
                if (delegateUser != null) {
                    schoolDelegates.add(delegateUser);
                }
            }
        } catch (Exception e) {
            logger.error("Error when fetching school delegates for schoolId " + schoolId, e);
        }

        return schoolDelegates;
    }

    public boolean isUserDelegate(User user) {
        try {
            List<Organization> userSchools = UserOrgsLocalServiceUtil.getAllUserSchoolsIncludingSchoolComplex(user);
            List<NewsAdmin> userDelegations = newsAdminPersistence.findByuserId(user.getUserId());

            if (userDelegations != null) {
                for (NewsAdmin userDelegation : userDelegations) {
                    for (Organization userSchool : userSchools) {
                        if (userDelegation.getSchoolId() == userSchool.getOrganizationId()) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error when determining if user " + user.getFullName() + " is delegate for his schools", e);
        }

        return false;
    }

    public boolean isUserSchoolDelegate(User user, long schoolId) {
        try {
            List<NewsAdmin> userDelegations = newsAdminPersistence.findByuserId(user.getUserId());
            if (userDelegations != null) {
                for (NewsAdmin userDelegation : userDelegations) {
                    if (userDelegation.getSchoolId() == schoolId) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error when determining if user " + user.getFullName() + " is delegate for school " + schoolId, e);
        }

        return false;
    }

    public List<User> getSchoolDelegationCandidates(long schoolId, String filter) {
        try {
            // People eligible to delegation: teachers, doyen, secretariat
            List<Long> roleIds = new ArrayList<>();
            roleIds.add(RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId());
            roleIds.add(RoleUtilsLocalServiceUtil.getDoyenRole().getRoleId());
            roleIds.add(RoleUtilsLocalServiceUtil.getSecretariatRole().getRoleId());

            List<Long> orgIds = new ArrayList<>();
            orgIds.add(schoolId);

            return UserSearchLocalServiceUtil.searchUsers(filter, orgIds, null, roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
        } catch (Exception e) {
            logger.error("Error when getting delegation candidates for schoolId " + schoolId, e);
        }

        return new ArrayList<>();
    }

}
