package com.weprode.nero.schedule.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.SystemException;
import org.json.JSONArray;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.schedule.model.WeeklySchedule;
import com.weprode.nero.schedule.service.base.WeeklyScheduleLocalServiceBaseImpl;

import com.weprode.nero.schedule.service.persistence.WeeklySchedulePK;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
	property = "model.class.name=com.weprode.nero.schedule.model.WeeklySchedule",
	service = AopService.class
)
public class WeeklyScheduleLocalServiceImpl
	extends WeeklyScheduleLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(WeeklyScheduleLocalServiceImpl.class);

	public List<WeeklySchedule> getWeeklyScheduleBySchoolId(Long schoolId) {
		List<WeeklySchedule> weeklySchedules = null;

		try {
			weeklySchedules = weeklySchedulePersistence.findByschoolId(schoolId);
		} catch (Exception e) {
			logger.debug(e);
		}

		if (weeklySchedules == null || weeklySchedules.size() == 0) {
			weeklySchedules = createDefaultWeeklySchedule(schoolId);
		}

		return weeklySchedules;
	}

	/**
	 * Delete all weekly schedule for etab thne create new from list of DayiD in parameter
	 * Sunday = 0 / Monday = 1 ...... / Saturday = 6
	 */
	public List<WeeklySchedule> replaceWeeklyScheduleBySchoolId(JSONArray dayIdList, Long schoolId) throws SystemException {
		List<WeeklySchedule> schedules = new ArrayList<>();

		weeklySchedulePersistence.removeByschoolId(schoolId);

		for (int i=0; i<dayIdList.length(); i++){
			WeeklySchedule schedule = weeklySchedulePersistence.create(new WeeklySchedulePK(schoolId, dayIdList.getInt(i)));
			schedule = weeklyScheduleLocalService.updateWeeklySchedule(schedule);
			schedules.add(schedule);
		}

		return schedules;
	}

	public List<WeeklySchedule> createDefaultWeeklySchedule(long schoolId) {
		List<WeeklySchedule> weeklyScheduleList = new ArrayList<>();

		for (int i = 1 ; i <= 6 ; i++) {
			try {
				WeeklySchedule weeklySchedule = weeklySchedulePersistence.create(new WeeklySchedulePK(schoolId, i));
				weeklySchedule = weeklySchedulePersistence.update(weeklySchedule);
				weeklyScheduleList.add(weeklySchedule);
			} catch (Exception e) {
				logger.debug(e);
			}
		}

		return weeklyScheduleList;
	}
}