package com.weprode.nero.statistic.service.impl;

import com.liferay.portal.aop.AopService;
import com.weprode.nero.statistic.service.base.GeneralStatLocalServiceBaseImpl;
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
