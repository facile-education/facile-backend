package com.weprode.nero.schedule.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.schedule.exception.NoSuchSessionTeacherException;
import com.weprode.nero.schedule.model.SessionTeacher;
import com.weprode.nero.schedule.service.SessionTeacherLocalServiceUtil;
import com.weprode.nero.schedule.service.base.SessionTeacherLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(
	property = "model.class.name=com.weprode.nero.schedule.model.SessionTeacher",
	service = AopService.class
)
public class SessionTeacherLocalServiceImpl
	extends SessionTeacherLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(SessionTeacherLocalServiceImpl.class);

	public static final int SUBSTITUTE = 1;
	public static final int SUBSTITUTED = 2;

	public SessionTeacher createSessionteacher(long sessionId, long teacherId) throws SystemException {
		return createSessionteacher(sessionId, teacherId, 0);
	}

	public SessionTeacher createSessionteacher(long sessionId, long teacherId, int status) throws SystemException {
		long sessionTeacherId = counterLocalService.increment();

		SessionTeacher sessionTeacher = sessionTeacherPersistence.create(sessionTeacherId);
		sessionTeacher.setSessionId(sessionId);
		sessionTeacher.setTeacherId(teacherId);
		sessionTeacher.setStatus(status);
		sessionTeacher.setSubstituteId(0);
		sessionTeacherPersistence.update(sessionTeacher);

		return sessionTeacher;
	}

	public SessionTeacher getSessionTeacher(long sessionId, long teacherId) {
		try {
			return sessionTeacherPersistence.findBysessionId_teacherId(sessionId, teacherId);
		} catch (Exception e) {
			logger.warn("No SessionTeacher for sessionId = " + sessionId + " and teacherId = " + teacherId);
		}

		return null;
	}

	public List<User> getTeachers(long sessionId, boolean includeSubstitutedTeachers) {
		List<User> teachers = new ArrayList<>();

		try {
			List<SessionTeacher> sessionTeachers = sessionTeacherPersistence.findBysessionId(sessionId);
			if (sessionTeachers != null) {
				for (SessionTeacher sessionTeacher : sessionTeachers) {
					if (includeSubstitutedTeachers || sessionTeacher.getStatus() != SUBSTITUTED) {
						teachers.add(UserLocalServiceUtil.getUser(sessionTeacher.getTeacherId()));
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error fetching teachers for sessionId " + sessionId, e);
		}

		return teachers;
	}

	public List<User> getTeachers(long sessionId) {
		return getTeachers(sessionId, true);
	}

	public List<SessionTeacher> getSessionTeachers(long sessionId) {
		List<SessionTeacher> sessionTeachers = new ArrayList<>();

		try {
			sessionTeachers.addAll(sessionTeacherPersistence.findBysessionId(sessionId));
		} catch (SystemException e) {
			logger.error("Could not fetch sessionteachers for sessionId = " + sessionId, e);
		}

		return sessionTeachers;
	}

	public List<Long> getTeacherIds(long sessionId) {
		List<Long> teacherIds = new ArrayList<>();

		try {
			List<SessionTeacher> sessionTeachers = sessionTeacherPersistence.findBysessionId(sessionId);
			if (sessionTeachers != null) {
				for (SessionTeacher sessionTeacher : sessionTeachers) {
					// Do not return substitute teacher preventing them from removal
					if (sessionTeacher.getStatus() != SUBSTITUTE) {
						teacherIds.add(sessionTeacher.getTeacherId());
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error fetching teachers for sessionId " + sessionId, e);
		}

		return teacherIds;
	}

	/**
	 * Returns true if the given teacher teaches the given session
	 */
	public boolean hasTeacherSession(long teacherId, long sessionId) {
		try {
			List<SessionTeacher> sessionTeachers = sessionTeacherPersistence.findBysessionId(sessionId);
			if (sessionTeachers != null) {
				for (SessionTeacher sessionTeacher : sessionTeachers) {
					if (sessionTeacher.getTeacherId() == teacherId) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error fetching if teacherId " + teacherId + " has sessionId " + sessionId, e);
		}

		return false;
	}

	/**
	 * Update the list of teachers for a given session
	 */
	public boolean updateTeacherListForSession(long sessionId, List<Long> newTeacherIdList) throws SystemException {
		// Get the list of existing teacher ids
		List<SessionTeacher> existingTeacherSessionList = sessionTeacherPersistence.findBysessionId(sessionId);

		// Loop over the existing teachers for the given session
		if (existingTeacherSessionList != null) {
			for (SessionTeacher existingSessionTeacher : existingTeacherSessionList) {

				boolean isSubstitute = (existingSessionTeacher.getStatus() == SUBSTITUTE);
				boolean isSubstituted = (existingSessionTeacher.getStatus() == SUBSTITUTED);
				// If they are not in the 'new' teacher list, then we'll have to remove them
				if (!isSubstitute && !isSubstituted &&
						!newTeacherIdList.contains(existingSessionTeacher.getTeacherId())) {
					sessionTeacherPersistence.remove(existingSessionTeacher);
					logger.info("Removing teacherId "+existingSessionTeacher.getTeacherId()+" from sessionId "+sessionId);
				}
			}
		}

		// Loop over the new teachers and see if we need to add them
		for (Long newTeacherId : newTeacherIdList) {

			boolean isFound = false;
			assert existingTeacherSessionList != null;
			for (SessionTeacher existingTeacherSession : existingTeacherSessionList) {
				if (existingTeacherSession.getTeacherId() == newTeacherId) {
					isFound = true;
				}
			}
			if (!isFound) {
				createSessionteacher(sessionId, newTeacherId);
			}
		}

		return true;
	}

	public boolean removeBySessionId(long sessionId) throws SystemException {
		sessionTeacherPersistence.removeBysessionId(sessionId);

		return true;
	}

	public SessionTeacher updateSessionTeacher(SessionTeacher sessionTeacher, int status, long substituteId) throws SystemException {
		sessionTeacher.setStatus(status);
		sessionTeacher.setSubstituteId(substituteId);

		return sessionTeacherPersistence.update(sessionTeacher);
	}

	// Add substitute teacher and update main teacher status
	public SessionTeacher substituteTeacher(long teacherId, long sessionId, long substituteId) throws SystemException {
		SessionTeacher sessionTeacher = sessionTeacherPersistence.fetchBysessionId_teacherId(sessionId, teacherId);
		updateSessionTeacher(sessionTeacher, sessionTeacher.getStatus() + SUBSTITUTED, substituteId);

		return createSessionteacher(sessionId, substituteId, SUBSTITUTE);
	}

	// Remove substitute teacher and rollback main teacher status
	public SessionTeacher removeSubstitute(long sessionId, long substituteId) throws SystemException {
		SessionTeacher sessionTeacher = sessionTeacherPersistence.fetchBysessionId_substituteId(sessionId, substituteId);
		updateSessionTeacher(sessionTeacher, sessionTeacher.getStatus() - SUBSTITUTED, substituteId);

		try {
			return sessionTeacherPersistence.removeBysessionId_teacherId(sessionId, substituteId);
		} catch (NoSuchSessionTeacherException e) {
			logger.warn("Could not find SessionTeacher with sessionId = " + sessionId + " and teacherId = " + substituteId);
		}

		return null;
	}

	public boolean isSubstituted (long teacherId, long sessionId) throws SystemException, NoSuchSessionTeacherException {
		return sessionTeacherPersistence.findBysessionId_teacherId(sessionId, teacherId).getStatus() == SUBSTITUTED;
	}

	// Used for session activity
	public Date getLastModificationDate(long sessionId, Date minDate, Date maxDate) {
		Date lastModificationDate = null;

		for (SessionTeacher sessionTeacher : SessionTeacherLocalServiceUtil.getSessionTeachers(sessionId)) {
			if (sessionTeacher.getModificationDate().after(minDate)
					&& sessionTeacher.getModificationDate().before(maxDate)
					&& (lastModificationDate == null ||	(sessionTeacher.getModificationDate().after(lastModificationDate)))) {
				lastModificationDate = sessionTeacher.getModificationDate();
			}
		}

		return lastModificationDate;
	}

	// Used for session activity
	public User getLastEditor(long sessionId, Date modifiedDate) throws SystemException, PortalException {
		for (SessionTeacher sessionTeacher : SessionTeacherLocalServiceUtil.getSessionTeachers(sessionId)) {
			if (sessionTeacher.getModificationDate().equals(modifiedDate)) {
				return UserLocalServiceUtil.getUser(sessionTeacher.getTeacherId());
			}
		}

		return null;
	}
}