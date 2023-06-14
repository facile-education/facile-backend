package com.weprode.nero.commons.wrapper;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.GroupLocalServiceWrapper;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.weprode.nero.group.service.CommunityInfosLocalServiceUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Locale;
import java.util.Map;


@Component(
		immediate = true,
		service = ServiceWrapper.class
)
public class GroupLocalServiceOverride extends GroupLocalServiceWrapper {

	private final Log logger = LogFactoryUtil.getLog(GroupLocalServiceOverride.class);

	public GroupLocalServiceOverride() {
		super(null);
	}

	@Override
	public Group addGroup(
			long userId, long parentGroupId, String className, long classPK,
			long liveGroupId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, int type,
			boolean manualMembership, int membershipRestriction,
			String friendlyURL, boolean site, boolean inheritContent,
			boolean active, ServiceContext serviceContext)
		throws PortalException {

		Group group = super.addGroup(userId, parentGroupId, className, classPK,
		liveGroupId, nameMap, descriptionMap, type, manualMembership, membershipRestriction,
		friendlyURL, site, inheritContent, active, serviceContext);

		if (group.getName().equals("Control Panel") || group.getName().equals("Guest")
				|| (!group.isOrganization() && !group.isRegularSite())) {
			return group;
		}

		try {
			logger.debug("Create community infos for new groupId = " + group.getGroupId());
			CommunityInfosLocalServiceUtil.createCommunity(group.getGroupId(), userId);
		} catch (SystemException e) {
			logger.error("Failed to create community info for groupId = " + group.getGroupId(), e);
		}

		return group;
	}

	// Add the following method to the bottom of your service wrapper
	// so it can find the appropriate service it’s overriding on deployment.
	@Reference(unbind = "-")
	private void serviceSetter(GroupLocalService groupLocalService) {
		setWrappedService(groupLocalService);
	}

}
