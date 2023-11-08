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

package com.weprode.facile.schedule.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.weprode.facile.schedule.model.Subject;
import com.weprode.facile.schedule.model.TeacherSubject;
import com.weprode.facile.schedule.service.SubjectLocalServiceUtil;
import com.weprode.facile.schedule.service.TeacherSubjectLocalServiceUtil;
import com.weprode.facile.schedule.service.base.TeacherSubjectLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.weprode.facile.schedule.model.TeacherSubject",
	service = AopService.class
)
public class TeacherSubjectLocalServiceImpl extends TeacherSubjectLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(TeacherSubjectLocalServiceImpl.class);

	/**
	 * Returns true if the mapping has been created, false if it already exists
	 */
	public TeacherSubject addTeacherSubjectInSchool(long teacherId, long subjectId, long schoolId) {

		try {
			List<TeacherSubject> teacherSubjectList = teacherSubjectPersistence.findByteacherId(teacherId);
			for (TeacherSubject teacherSubject : teacherSubjectList) {
				if (teacherSubject.getSchoolId() == schoolId && teacherSubject.getSubjectId() == subjectId) {
					return teacherSubject;
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}

		// Create if needed
		try {
			long teacherSubjectId = counterLocalService.increment();
			TeacherSubject teacherSubject = teacherSubjectPersistence.create(teacherSubjectId);
			teacherSubject.setTeacherId(teacherId);
			teacherSubject.setSubjectId(subjectId);
			teacherSubject.setSchoolId(schoolId);
			teacherSubject = teacherSubjectPersistence.update(teacherSubject);

			return teacherSubject;
		} catch (Exception e) {
			logger.debug(e);
		}

		return null;
	}

	public List<String> getTeacherSubjects(long teacherId) {
		List<String> teacherSubjects = new ArrayList<>();

		try {
			List<TeacherSubject> teacherSubjectList = teacherSubjectPersistence.findByteacherId(teacherId);
			if (teacherSubjectList != null) {
				for (TeacherSubject teacherSubject : teacherSubjectList) {
					Subject subject = SubjectLocalServiceUtil.getSubject(teacherSubject.getSubjectId());
					teacherSubjects.add(subject.getName());
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}

		return teacherSubjects;
	}

	public String getTeacherSubjectList(User teacher) {
		List<String> subjectList = TeacherSubjectLocalServiceUtil.getTeacherSubjects(teacher.getUserId());

		// Generate the string
		StringBuilder subjectsAsString = new StringBuilder();
		for (String subjectStr : subjectList) {
			subjectsAsString.append(subjectStr).append(", ");
		}
		if (subjectsAsString.length() > 1) {
			subjectsAsString = new StringBuilder(subjectsAsString.substring(0, subjectsAsString.length() - 2));
		}

		return subjectsAsString.toString();
	}
}