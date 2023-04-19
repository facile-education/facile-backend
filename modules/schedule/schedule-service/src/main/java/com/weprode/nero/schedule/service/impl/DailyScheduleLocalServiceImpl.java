package com.weprode.nero.schedule.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.schedule.model.DailySchedule;
import com.weprode.nero.schedule.service.base.DailyScheduleLocalServiceBaseImpl;

import com.weprode.nero.schedule.service.persistence.DailySchedulePK;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
	property = "model.class.name=com.weprode.nero.schedule.model.DailySchedule",
	service = AopService.class
)
public class DailyScheduleLocalServiceImpl
	extends DailyScheduleLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(DailyScheduleLocalServiceImpl.class);

	/**
	 * Delete daily school configuration
	 */
	public void deleteSchoolDailySchedule(long schoolId) {
		try {
			List<DailySchedule> dailyScheduleList = dailySchedulePersistence.findByschoolId(schoolId);
			if (dailyScheduleList != null) {
				for (DailySchedule dailySchedule : dailyScheduleList) {
					dailySchedulePersistence.remove(dailySchedule);
				}
			}
		} catch (Exception e) {
			logger.error("Error while deleting the daily schedule configuration for schoolId "+schoolId, e);
		}
	}

	/**
	 * Add school daily configuration for 1 given session Id
	 */
	public void addSchoolDailySchedule(long schoolId, int sessionId, String sessionStartHour, String sessionEndHour) {
		try {
			DailySchedule dailySchedule = dailySchedulePersistence.create(new DailySchedulePK(schoolId, sessionId));
			dailySchedule.setSessionStartHour(sessionStartHour);
			dailySchedule.setSessionEndHour(sessionEndHour);
			dailySchedulePersistence.update(dailySchedule);
		} catch (Exception e) {
			logger.error("Error while creating the daily schedule configuration for schoolId "+schoolId + "and sessionId "+sessionId, e);
		}
	}
}