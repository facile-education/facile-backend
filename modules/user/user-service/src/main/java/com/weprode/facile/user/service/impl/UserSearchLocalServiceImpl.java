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

package com.weprode.facile.user.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.weprode.facile.user.service.base.UserSearchLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.user.model.UserSearch",
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
