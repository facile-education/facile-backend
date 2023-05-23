package com.weprode.nero.schedule.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.schedule.model.SubjectGroupColor;
import com.weprode.nero.schedule.service.SubjectGroupColorLocalServiceUtil;
import com.weprode.nero.schedule.service.base.SubjectGroupColorLocalServiceBaseImpl;

import com.weprode.nero.schedule.utils.CDTColorUtil;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
	property = "model.class.name=com.weprode.nero.schedule.model.SubjectGroupColor",
	service = AopService.class
)
public class SubjectGroupColorLocalServiceImpl
	extends SubjectGroupColorLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(SubjectGroupColorLocalServiceImpl.class);

	/**
	 * Returns the color for given groupId and subject
	 * Create it if it does not exist
	 * Not used in GVA mode, but may be used in France (where teachers have classes and not cours)
	 */
	public String getSubjectGroupColor(long groupId, String subject) {
		String color = "";

		try {
			List<SubjectGroupColor> subjectGroupColorList = subjectGroupColorPersistence.findBygroupId_subject(groupId, subject);
			if (subjectGroupColorList != null && !subjectGroupColorList.isEmpty()) {
				color = subjectGroupColorList.get(0).getColor();
			} else {
				// Create new one
				long subjectGroupColorId = counterLocalService.increment();
				SubjectGroupColor subjectGroupColor = SubjectGroupColorLocalServiceUtil.createSubjectGroupColor(subjectGroupColorId);
				subjectGroupColor.setGroupId(groupId);
				subjectGroupColor.setSubject(subject);

				// Get existing used colors for the group to get a fresh new one
				int nbExistingGroupColors = subjectGroupColorPersistence.countBygroupId(groupId);
				color = CDTColorUtil.getNewColor(nbExistingGroupColors);
				subjectGroupColor.setColor(color);

				SubjectGroupColorLocalServiceUtil.updateSubjectGroupColor(subjectGroupColor);
			}
		} catch (Exception e) {
			logger.error("Error when retrieving the list of colors for subject " + subject + " and groupId "+groupId);
		}

		return color;
	}
}