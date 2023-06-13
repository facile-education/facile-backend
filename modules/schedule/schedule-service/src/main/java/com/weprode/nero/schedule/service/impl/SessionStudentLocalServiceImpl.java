package com.weprode.nero.schedule.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.model.SessionStudent;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.base.SessionStudentLocalServiceBaseImpl;
import com.weprode.nero.user.service.UserUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
	property = "model.class.name=com.weprode.nero.schedule.model.SessionStudent",
	service = AopService.class
)
public class SessionStudentLocalServiceImpl
	extends SessionStudentLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(SessionStudentLocalServiceImpl.class);

	/**
	 * Add student to a session
	 */
	public SessionStudent addStudentToSession(long sessionId, long studentId) throws SystemException {

		List<SessionStudent> sessionStudentsList = sessionStudentPersistence.findBysessionId_studentId(sessionId, studentId);
		if (sessionStudentsList != null && !sessionStudentsList.isEmpty()) {
			return sessionStudentsList.get(0);
		} else {
			long sessionStudentId = counterLocalService.increment();
			SessionStudent sessionStudent = sessionStudentPersistence.create(sessionStudentId);
			sessionStudent.setSessionId(sessionId);
			sessionStudent.setStudentId(studentId);
			return sessionStudentPersistence.update(sessionStudent);
		}
	}

	/**
	 * Remove a student from a session
	 */
	public boolean removeStudentFromSession(long sessionId, long studentId) throws SystemException {

		sessionStudentPersistence.removeBysessionId_studentId(sessionId, studentId);
		return true;
	}

	/**
	 * Returns all students involved in a given sessionId
	 */
	public List<User> getStudentsBySession(long sessionId) {
		List<User> studentList = new ArrayList<>();
		try {
			List<SessionStudent> sessionStudentsList = sessionStudentPersistence.findBysessionId(sessionId);
			if (sessionStudentsList != null) {
				for (SessionStudent sessionStudent : sessionStudentsList) {
					try {
						User student = UserLocalServiceUtil.getUser(sessionStudent.getStudentId());
						studentList.add(student);
					} catch (Exception e) {
						// Prevent from obsolete users
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error while getting students attached to sessionId "+sessionId, e);
		}

		logger.debug("Returning "+studentList.size()+" students for sessionId "+sessionId);

		return studentList;
	}

	/**
	 * Returns all students involved in a given sessionId
	 */
	public List<Long> getStudentIdsBySession(long sessionId) {
		List<Long> studentIdList = new ArrayList<>();

		try {
			List<SessionStudent> sessionStudentsList = sessionStudentPersistence.findBysessionId(sessionId);
			if (sessionStudentsList != null) {
				for (SessionStudent sessionStudent : sessionStudentsList) {
					studentIdList.add(sessionStudent.getStudentId());
				}
			}
		} catch (Exception e) {
			logger.error("Error while getting students attached to sessionId "+sessionId, e);
		}

		return studentIdList;
	}

	public boolean hasStudentSession (long studentId, long sessionId) {
		try {
			CDTSession session = CDTSessionLocalServiceUtil.getCDTSession(sessionId);
			if (UserUtilsLocalServiceUtil.getUserGroupIds(studentId).contains(session.getGroupId())) {
				return true;
			}
			List<SessionStudent> sessionStudentsList = sessionStudentPersistence.findBysessionId_studentId(sessionId, studentId);
			if (sessionStudentsList != null && !sessionStudentsList.isEmpty()) {
				return true;
			}

		} catch (Exception e) {
			logger.error("Error determining if student " + studentId + " has session " + sessionId, e);
		}
		return false;
	}

	public boolean removeBySessionId(long sessionId) {
		try {
			sessionStudentPersistence.removeBysessionId(sessionId);
			return true;
		} catch (Exception e) {
			logger.debug(e);
		}

		return false;
	}
}