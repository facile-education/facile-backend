package com.weprode.nero.schedule.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.weprode.nero.schedule.model.Homework;
import com.weprode.nero.schedule.model.SessionTeacher;
import com.weprode.nero.schedule.service.HomeworkLocalService;
import com.weprode.nero.schedule.service.persistence.HomeworkFinder;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Component(service = HomeworkFinder.class)
public class HomeworkFinderImpl extends HomeworkFinderBaseImpl
        implements HomeworkFinder {

    private static final Log logger = LogFactoryUtil.getLog(HomeworkFinderImpl.class);

    @BeanReference(type = HomeworkLocalService.class)
    private HomeworkLocalService homeworkLocalService;

    public List<Homework> getTeacherHomeworks(User teacher, Date minDate, long groupId) throws SystemException {
        List<Homework> homeworkList = new ArrayList<>();

        // Global criterion is
        // WHERE teacherId = <teacherId>
        // AND targetDate >= minDate
        ClassLoader classLoader = getClass().getClassLoader();

        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Homework.class, "homework", classLoader)
                .add(PropertyFactoryUtil.forName("sourceSessionId")
                        .in(DynamicQueryFactoryUtil.forClass(SessionTeacher.class, classLoader)
                                .add(PropertyFactoryUtil.forName("teacherId").eq(teacher.getUserId()))
                                .setProjection(ProjectionFactoryUtil.property("sessionId"))
                        )
                );

        // Set maxDate to far away in the future
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, 100);
        Date maxDate = cal.getTime();

        Criterion betweenDatesCriterion = RestrictionsFactoryUtil.between("homework.targetDate", minDate, maxDate);

        if (betweenDatesCriterion != null) {
            dynamicQuery.add(betweenDatesCriterion);

            Criterion groupIdCriterion = RestrictionsFactoryUtil.eq("homework.groupId", groupId);

            if (groupId > 0 && groupIdCriterion != null) {
                dynamicQuery.add(groupIdCriterion);
            }

            // Run the dynamic query
            return homeworkLocalService.dynamicQuery(dynamicQuery);
        }

        return homeworkList;
    }

    public List<Homework> getStudentHomeworksFromGroupIds (List<Long> studentGroupIdList, Date minDate, Date maxDate) {
        List<Homework> homeworkList = new ArrayList<>();

        // Global criterion is
        // WHERE groupId in <groupIdList>
        // AND targetDate >= minDate
        // AND target Date <= maxDate
        ClassLoader classLoader = getClass().getClassLoader();

        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Homework.class, "homework", classLoader);
        Criterion criterion;

        // GroupId list criterion
        Criterion groupIdCriterion = null;
        for (Long groupId : studentGroupIdList){
            if(groupIdCriterion != null){
                groupIdCriterion = RestrictionsFactoryUtil.or(groupIdCriterion , RestrictionsFactoryUtil.eq("homework.groupId",groupId));
            }
            else{
                groupIdCriterion = RestrictionsFactoryUtil.eq("homework.groupId",groupId);
            }
        }

        Criterion betweenDatesCriterion = RestrictionsFactoryUtil.between("homework.targetDate", minDate, maxDate);

        criterion = RestrictionsFactoryUtil.and(groupIdCriterion, betweenDatesCriterion);

        if (criterion != null) {
            dynamicQuery.add(criterion);

            // Run the dynamic query
            homeworkList.addAll(homeworkLocalService.dynamicQuery(dynamicQuery));
        }

        return homeworkList;
    }
}
