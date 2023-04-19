package com.weprode.nero.schedule.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.schedule.model.TeacherGroupColor;
import com.weprode.nero.schedule.service.TeacherGroupColorLocalServiceUtil;
import com.weprode.nero.schedule.service.base.TeacherGroupColorLocalServiceBaseImpl;

import com.weprode.nero.schedule.utils.CDTColorUtil;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
	property = "model.class.name=com.weprode.nero.schedule.model.TeacherGroupColor",
	service = AopService.class
)
public class TeacherGroupColorLocalServiceImpl
	extends TeacherGroupColorLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(TeacherGroupColorLocalServiceImpl.class);

	/**
	 * Returns the color for given teacherId and groupId
	 * Create it if it does not exist
	 * groupId can also be a schoollife type in case of schoollife sessions
	 */
	public String getTeacherGroupColor(long teacherId, long groupId) {

		String color = "";
		try {
			List<TeacherGroupColor> teacherGroupColorList = teacherGroupColorPersistence.findByteacherId_groupId(teacherId, groupId);
			if (teacherGroupColorList != null && !teacherGroupColorList.isEmpty()) {
				color = teacherGroupColorList.get(0).getColor();
			} else {
				// Create new one
				long teacherGroupColorId = counterLocalService.increment();
				TeacherGroupColor teacherGroupColor = TeacherGroupColorLocalServiceUtil.createTeacherGroupColor(teacherGroupColorId);
				teacherGroupColor.setGroupId(groupId);
				teacherGroupColor.setTeacherId(teacherId);

				// Get existing used colors for the teacher to get a fresh new one
				int nbExistingTeacherColors = teacherGroupColorPersistence.countByteacherId(teacherId);
				color = CDTColorUtil.getNewColor(nbExistingTeacherColors);
				teacherGroupColor.setColor(color);

				TeacherGroupColorLocalServiceUtil.updateTeacherGroupColor(teacherGroupColor);
			}
		} catch (Exception e) {
			logger.error("Error when retrieving the list of colors for teacherId "+teacherId + " and groupId "+groupId);
		}

		return color;
	}
}