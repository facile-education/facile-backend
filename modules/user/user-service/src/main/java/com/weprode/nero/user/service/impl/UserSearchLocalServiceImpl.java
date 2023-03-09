package com.weprode.nero.user.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.weprode.nero.user.service.base.UserSearchLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.user.model.UserSearch",
        service = AopService.class
)
public class UserSearchLocalServiceImpl extends UserSearchLocalServiceBaseImpl {

    public final List<User> searchUsers(String query, List<Long> organizationIds, List<Long> groupIds, List<Long> roleIds,
                                        List<Long> subjectIds, int start, int stop, OrderByComparator obc) throws SystemException {

        return userUtilsFinder.getUserFromContactSearch(query, organizationIds, groupIds, roleIds, subjectIds,
                false, start, stop, obc);
    }

    public final List<User> searchUsers(String query, List<Long> organizationIds, List<Long> groupIds, List<Long> roleIds,
                                        List<Long> subjectIds, boolean localUsersOnly, int start, int stop, OrderByComparator obc) throws SystemException {

        return userUtilsFinder.getUserFromContactSearch(query, organizationIds, groupIds, roleIds, subjectIds,
                localUsersOnly, start, stop, obc);
    }

    public final Long countUsers(String query, List<Long> organizationIds, List<Long> groupIds, List<Long> roleIds,
                                 List<Long> subjectIds) throws SystemException {

        return userUtilsFinder.getUserCountFromContactSearch(query, organizationIds, groupIds, roleIds, subjectIds, false);
    }

    public final Long countUsers(String query, List<Long> organizationIds, List<Long> groupIds, List<Long> roleIds,
                                 List<Long> subjectIds, boolean localUsersOnly) throws SystemException {

        return userUtilsFinder.getUserCountFromContactSearch(query, organizationIds, groupIds, roleIds, subjectIds, localUsersOnly);
    }
}
