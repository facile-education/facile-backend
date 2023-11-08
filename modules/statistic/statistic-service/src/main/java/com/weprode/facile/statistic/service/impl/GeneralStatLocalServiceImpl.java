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

package com.weprode.facile.statistic.service.impl;

import com.liferay.portal.aop.AopService;
import com.weprode.facile.statistic.service.base.GeneralStatLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.Map;

@Component(
        service = AopService.class
)
public class GeneralStatLocalServiceImpl extends GeneralStatLocalServiceBaseImpl {

    public int countActiveUsers(Date startDate, Date endDate, long schoolId) {
        return generalStatFinder.countActiveUsers(startDate, endDate, schoolId);
    }

    public Map<String, Integer> countFiles(Date startDate, Date endDate, long schoolId) {
        return generalStatFinder.countFiles(startDate, endDate, schoolId);
    }

    public Map<Integer, Integer> countHomeworks(Date startDate, Date endDate, long schoolId) {
        return generalStatFinder.countHomeworks(startDate, endDate, schoolId);
    }


    public int countNews(Date startDate, Date endDate, long schoolId, boolean isSchoolNewsType) {
        return generalStatFinder.countNews(startDate, endDate, schoolId, isSchoolNewsType);
    }


    public int countMessages(Date startDate, Date endDate, long schoolId) {
        return generalStatFinder.countMessages(startDate, endDate, schoolId);
    }
}
