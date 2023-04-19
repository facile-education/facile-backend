package com.weprode.nero.schedule.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.model.ScheduleConfiguration;
import com.weprode.nero.schedule.service.ScheduleConfigurationLocalServiceUtil;
import com.weprode.nero.user.service.AffectationLocalServiceUtil;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.model.SessionTeacher;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.SessionTeacherLocalServiceUtil;
import com.weprode.nero.schedule.service.base.SessionTeacherServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component(
	property = {
		"json.web.service.context.name=schedule",
		"json.web.service.context.path=SessionTeacher"
	},
	service = AopService.class
)
public class SessionTeacherServiceImpl extends SessionTeacherServiceBaseImpl {
	private static final Log logger = LogFactoryUtil.getLog(SessionTeacherServiceImpl.class);

	@JSONWebService(value = "get-session-teachers-and-substitutes", method = "GET")
	public JSONObject getSessionTeachersAndSubstitutes (long sessionId) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				throw new AuthException();
			} else if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
					!RoleUtilsLocalServiceUtil.isDoyen(user) && !RoleUtilsLocalServiceUtil.isSecretariat(user)) {
				result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
				result.put(JSONConstants.SUCCESS, false);
				return result;
			}
		} catch (Exception e) {
			result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
			result.put(JSONConstants.SUCCESS, false);
			return result;
		}

		logger.info(user.getScreenName() + " is fetching teacher infos for sessionId "+sessionId);

		List<SessionTeacher> sessionTeachers = SessionTeacherLocalServiceUtil.getSessionTeachers(sessionId);
		JSONArray teacherArray = JSONFactoryUtil.createJSONArray();

		for (SessionTeacher sessionTeacher : sessionTeachers) {

			if (sessionTeacher.getStatus() == SessionTeacherLocalServiceImpl.SUBSTITUTE) {
				// Skip substitutes
				continue;
			}

			JSONObject teacherJSON = JSONFactoryUtil.createJSONObject();

			User teacher;
			try {
				teacher = UserLocalServiceUtil.getUser(sessionTeacher.getTeacherId());
			} catch (Exception e) {
				logger.error("Could not get user with id = " + sessionTeacher.getTeacherId(), e);
				result.put(JSONConstants.SUCCESS, false);
				return result;
			}
			boolean isSubstituted = (sessionTeacher.getStatus() == SessionTeacherLocalServiceImpl.SUBSTITUTED);

			// Get substitute teacher
			if (isSubstituted) {
				User substitute;
				try {
					substitute = UserLocalServiceUtil.getUser(sessionTeacher.getSubstituteId());
				} catch (Exception e) {
					logger.error("Could not get substitute with userId = " + sessionTeacher.getSubstituteId(), e);
					result.put(JSONConstants.SUCCESS, false);
					return result;
				}

				JSONObject substituteJSON = JSONFactoryUtil.createJSONObject();
				substituteJSON.put(JSONConstants.TEACHER_ID, substitute.getUserId());
				substituteJSON.put(JSONConstants.DISPLAY_NAME, substitute.getFullName());
				substituteJSON.put(JSONConstants.FIRST_NAME, substitute.getFirstName());
				substituteJSON.put(JSONConstants.LAST_NAME, substitute.getLastName());
				teacherJSON.put(JSONConstants.SUBSTITUTE, substituteJSON);
			}

			// Get teacher sessions for the coming month
			JSONArray sessionArray = JSONFactoryUtil.createJSONArray();
			CDTSession session;
			try {
				session = CDTSessionLocalServiceUtil.getCDTSession(sessionId);
			} catch (Exception e) {
				logger.error("Could not get session with id = " + sessionId, e);
				result.put(JSONConstants.SUCCESS, false);
				return result;
			}

			Calendar sessionCal = Calendar.getInstance();
			sessionCal.setTime(session.getSessionStart());

			List<CDTSession> sessions = new ArrayList<>();
			sessions.add(session);
			sessions.addAll(session.getNextSessions(teacher));
			for (CDTSession nextSession : sessions) {
				// Check that teacher is indeed affected to the session
				if (SessionTeacherLocalServiceUtil.hasTeacherSession(sessionTeacher.getTeacherId(), nextSession.getSessionId())) {
					JSONObject nextSessionJSON = nextSession.convertToJSON();

					Calendar nextCal = Calendar.getInstance();
					nextCal.setTime(nextSession.getSessionStart());
					boolean isSameSlot = (sessionCal.get(Calendar.DAY_OF_WEEK) == nextCal.get(Calendar.DAY_OF_WEEK) &&
							sessionCal.get(Calendar.HOUR) == nextCal.get(Calendar.HOUR) &&
							sessionCal.get(Calendar.MINUTE) == nextCal.get(Calendar.MINUTE));

					if (isSameSlot) {
						nextSessionJSON.put(JSONConstants.IS_SAME_SLOT, true);
					}

					sessionArray.put(nextSessionJSON);
				}
			}

			teacherJSON.put(JSONConstants.TEACHER_ID, teacher.getUserId());
			teacherJSON.put(JSONConstants.FIRST_NAME, teacher.getFirstName());
			teacherJSON.put(JSONConstants.LAST_NAME, teacher.getLastName());
			teacherJSON.put(JSONConstants.NEXT_SESSIONS, sessionArray);

			teacherArray.put(teacherJSON);
		}

		result.put(JSONConstants.SUCCESS, true);
		result.put(JSONConstants.TEACHERS, teacherArray);

		return result;
	}

	@JSONWebService(value = "save-teacher-substitutes", method = "POST")
	public JSONObject saveTeacherSubstitutes(long sessionId, String teacherArray) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				throw new AuthException();
			} else if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
					!RoleUtilsLocalServiceUtil.isDoyen(user) && !RoleUtilsLocalServiceUtil.isSecretariat(user)) {
				result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
				result.put(JSONConstants.SUCCESS, false);
				return result;
			}
		} catch (Exception e) {
			result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
			result.put(JSONConstants.SUCCESS, false);
			return result;
		}

		JSONArray teachers;
		try {
			teachers = JSONFactoryUtil.createJSONArray(teacherArray);
		} catch (JSONException e) {
			logger.error("Could not convert string into JSONArray", e);
			result.put(JSONConstants.SUCCESS, false);
			return result;
		}

		// For each teacher in json
		for (int i = 0 ; i<teachers.length() ; i++) {
			JSONObject teacher = teachers.getJSONObject(i);
			long teacherId = teacher.getLong(JSONConstants.TEACHER_ID);
			long substituteId = teacher.getLong(JSONConstants.SUBSTITUTE_ID, 0);
			User teacherUser = null;
			try {
				teacherUser = UserLocalServiceUtil.getUser(teacherId);
			} catch (Exception e) {
				logger.error("Unknown teacher " + teacherId, e);
				result.put(JSONConstants.SUCCESS, false);
				return result;
			}

			// Removing substitute
			SessionTeacher sessionTeacher = SessionTeacherLocalServiceUtil.getSessionTeacher(sessionId, teacherId);
			boolean isSubstituted = (sessionTeacher.getStatus() == SessionTeacherLocalServiceImpl.SUBSTITUTED);
			if (isSubstituted && substituteId != sessionTeacher.getSubstituteId()) {
				logger.info(user.getScreenName() + " is about to remove substitute "+sessionTeacher.getSubstituteId()+" for sessionId "+sessionId+" and teacherId "+teacherId);
				try {
					SessionTeacherLocalServiceUtil.removeSubstitute(sessionId, sessionTeacher.getSubstituteId());
				} catch (SystemException e) {
					logger.error("Could not remove substitute with session id = " + sessionId + " and substituteId =" + sessionTeacher.getSubstituteId(), e);
					result.put(JSONConstants.SUCCESS, false);
					return result;
				}
			}

			// Adding substitute for current and next sessions
			long lastSessionId = teacher.getLong("lastSessionId", 0);
			if (substituteId > 0 && lastSessionId > 0) {
				boolean allSlot = teacher.getBoolean("allSlots", false);
				CDTSession session;
				try {
					session = CDTSessionLocalServiceUtil.getCDTSession(sessionId);
				} catch (Exception e) {
					logger.error("Could not get session with sessionId = " + sessionId, e);
					result.put(JSONConstants.SUCCESS, false);
					return result;
				}

				List<CDTSession> sessions = new ArrayList<>();
				sessions.add(session);
				sessions.addAll(session.getNextSessions(teacherUser));

				Calendar sessionCal = Calendar.getInstance();
				sessionCal.setTime(session.getSessionStart());

				for (CDTSession nextSession : sessions) {

					Calendar nextCal = Calendar.getInstance();
					nextCal.setTime(nextSession.getSessionStart());
					boolean isSameSlot = (sessionCal.get(Calendar.DAY_OF_WEEK) == nextCal.get(Calendar.DAY_OF_WEEK) &&
							sessionCal.get(Calendar.HOUR) == nextCal.get(Calendar.HOUR) &&
							sessionCal.get(Calendar.MINUTE) == nextCal.get(Calendar.MINUTE));

					// Check that teacher is indeed affected to the session
					boolean hasTeacherSession = SessionTeacherLocalServiceUtil.hasTeacherSession(teacherId, nextSession.getSessionId());

					// If slot is targeted handle substitution
					if (hasTeacherSession && (allSlot || isSameSlot)) {
						SessionTeacher nextSessionTeacher = SessionTeacherLocalServiceUtil.getSessionTeacher(nextSession.getSessionId(), teacherId);

						if(nextSessionTeacher != null) {
							isSubstituted = (nextSessionTeacher.getStatus() == SessionTeacherLocalServiceImpl.SUBSTITUTED);

							if (isSubstituted && substituteId != nextSessionTeacher.getSubstituteId()) {
								logger.info(user.getScreenName() + " is about to remove substitute "+nextSessionTeacher.getSubstituteId()+" for sessionId "+nextSession.getSessionId()+" and teacherId "+teacherId);
								try {
									SessionTeacherLocalServiceUtil.removeSubstitute(nextSession.getSessionId(), nextSessionTeacher.getSubstituteId());
								} catch (SystemException e) {
									logger.error("Could not remove substitute with session id = "+nextSession.getSessionId()+" and substituteId =" + nextSessionTeacher.getSubstituteId(), e);
									result.put(JSONConstants.SUCCESS, false);
									return result;
								}
							}
							if (!isSubstituted || substituteId != nextSessionTeacher.getSubstituteId()) {
								logger.info(user.getScreenName() + " is about to add substitute "+substituteId+" for sessionId "+nextSession.getSessionId()+" and teacherId "+teacherId);
								try {
									SessionTeacherLocalServiceUtil.substituteTeacher(teacherId, nextSession.getSessionId(), substituteId);

									// Add substitute to class org and group if not already a member
									if (!GroupLocalServiceUtil.hasUserGroup(substituteId, nextSession.getGroupId())) {
										logger.info("Add substitute "+substituteId+" to groupId "+nextSession.getSessionId());

										Group group = GroupLocalServiceUtil.getGroup(nextSession.getGroupId());
										if (group.isOrganization()) {
											Organization org = OrganizationLocalServiceUtil.getOrganization(group.getClassPK());
											// Add affectation for teacher to organization, with expiration date

											Date expirationDate = null;
											if (allSlot) {
												// Expiration date is the school year end date
												ScheduleConfiguration cdtConfig = ScheduleConfigurationLocalServiceUtil.getSchoolConfiguration(nextSession.getSchoolId());
												expirationDate = cdtConfig.getEndSessionsDate();
											} else {
												// Expiration date is the last session's date
												CDTSession lastSession = CDTSessionLocalServiceUtil.getCDTSession(lastSessionId);
												expirationDate = lastSession.getSessionEnd();
											}
											AffectationLocalServiceUtil.addUserAffectation(substituteId, org.getOrganizationId(), user.getUserId(), expirationDate);
										}
									}
								} catch (Exception e) {
									logger.error("Could not add substitute with session id = and substituteId =" + sessionTeacher.getSessionTeacherId(), e);
									result.put(JSONConstants.SUCCESS, false);
									return result;
								}
							}
						}
					}

					if (lastSessionId == nextSession.getSessionId()) {
						break;
					}
				}
			}
		}

		result.put(JSONConstants.SUCCESS, true);

		return result;
	}
}