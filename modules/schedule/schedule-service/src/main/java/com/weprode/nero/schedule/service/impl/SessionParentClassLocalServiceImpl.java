package com.weprode.nero.schedule.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.schedule.model.SessionParentClass;
import com.weprode.nero.schedule.service.base.SessionParentClassLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
	property = "model.class.name=com.weprode.nero.schedule.model.SessionParentClass",
	service = AopService.class
)
public class SessionParentClassLocalServiceImpl
	extends SessionParentClassLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(SessionParentClassLocalServiceImpl.class);

	public SessionParentClass addSessionParentClass(long sessionId, long groupId) {
		try {
			List<SessionParentClass> sessionParentClasses = sessionParentClassPersistence.findBysessionId_groupId(sessionId, groupId);

			if (sessionParentClasses != null && sessionParentClasses.size() > 0) {
				return sessionParentClasses.get(0);
			} else {
				long sessionParentClassId = counterLocalService.increment();
				SessionParentClass sessionParentClass = sessionParentClassPersistence.create(sessionParentClassId);
				sessionParentClass.setSessionId(sessionId);
				sessionParentClass.setGroupId(groupId);
				return sessionParentClassPersistence.update(sessionParentClass);
			}
		} catch (Exception e) {
			logger.error("Error when creating a SessionParentClass with sessionId=" + sessionId + " and groupId=" + groupId, e);
		}

		return null;
	}

	public List<Long> getSessionParentGroupIds(long sessionId) {
		List<Long> groupIds = new ArrayList<>();

		try {
			List<SessionParentClass> sessionParentClassList = sessionParentClassPersistence.findBysessionId(sessionId);
			if (sessionParentClassList != null) {
				for (SessionParentClass sessionParentClass : sessionParentClassList) {
					groupIds.add(sessionParentClass.getGroupId());
				}
			}
		} catch (Exception e) {
			logger.error("Error when fetching parent groupIds for sessionId=" + sessionId, e);
		}

		return groupIds;
	}

	public List<Long> getGroupSessions(long groupId) {
		List<Long> sessionIds = new ArrayList<>();

		try {
			List<SessionParentClass> sessionParentClassList = sessionParentClassPersistence.findBygroupId(groupId);
			if (sessionParentClassList != null) {
				for (SessionParentClass sessionParentClass : sessionParentClassList) {
					sessionIds.add(sessionParentClass.getSessionId());
				}
			}
		} catch (Exception e) {
			logger.error("Error when fetching sessions for parent groupId=" + groupId, e);
		}

		return sessionIds;
	}

	public String getParentClassName(long sessionId) {
		StringBuilder className = new StringBuilder();

		List<Long> sessionGroupIds = getSessionParentGroupIds(sessionId);
		for (Long sessionGroupId : sessionGroupIds) {
			try {
				Group parentGroup = GroupLocalServiceUtil.getGroup(sessionGroupId);
				Organization parentClass = OrganizationLocalServiceUtil.getOrganization(parentGroup.getClassPK());
				className.append("<").append(OrgUtilsLocalServiceUtil.formatOrgName(parentClass.getName(), false)).append(">").append(" - ");
			} catch (Exception e) {
				logger.debug(e);
			}
		}
		if (!className.toString().equals("") && className.length() > 2) {
			className = new StringBuilder(className.substring(0, className.length() - 2));
		}

		return className.toString();
	}

	public boolean deleteBySessionId(long sessionId) {
		try {
			sessionParentClassPersistence.removeBysessionId(sessionId);
			return true;
		} catch (Exception e) {
			logger.error("Error when deleting all SessionParentGroups for sessionId=" + sessionId, e);
		}

		return false;
	}

	public boolean deleteByGroupId(long groupId) {
		try {
			sessionParentClassPersistence.removeBygroupId(groupId);
			return true;
		} catch (Exception e) {
			logger.error("Error when deleting all SessionParentGroups for groupId=" + groupId, e);
		}

		return false;
	}
}