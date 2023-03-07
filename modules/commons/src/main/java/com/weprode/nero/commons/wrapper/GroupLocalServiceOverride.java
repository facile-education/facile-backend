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
import com.weprode.nero.group.service.GroupMembershipLocalServiceUtil;
import com.weprode.nero.group.service.MembershipActivityLocalServiceUtil;
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

	@Override
	public Group deleteGroup(long groupId) throws PortalException {

		// Remove group related data
		deleteGroupData(getGroup(groupId));

		return super.deleteGroup(groupId);
	}

	@Override
	public Group deleteGroup(Group group) throws PortalException {

		// Remove group related content
		deleteGroupData(group);

		return super.deleteGroup(group);
	}

	private void deleteGroupData (Group group) {

		if (group.getName().equals("Control Panel") || group.getName().equals("Guest")
				|| (!group.isOrganization() && !group.isRegularSite())) {
			return;
		}

		long groupId = group.getGroupId();

		try {
			logger.debug("Clean up community infos for groupId " + groupId);
			CommunityInfosLocalServiceUtil.deleteCommunityInfos(groupId);

		} catch (Exception e) {
			logger.error("Could not delete community infos for groupId " + groupId);
		}

		try {
			logger.debug("Clean up group memberships for groupId " + groupId);
			GroupMembershipLocalServiceUtil.removeGroupMemberships(groupId);
		} catch (Exception e) {
			logger.error("Could not delete group memberships for groupId " + groupId);
		}

		// TODO clean up news, cdt events, doc activities ?
//		deleteNews(groupId);
//		deleteDLFiles(groupId);
//		deleteCDTEvents(groupId);
//		deleteDocumentActivity(groupId);

		try {
			logger.debug("Clean up membership activities for groupId " + groupId);
			MembershipActivityLocalServiceUtil.deleteGroupActivity(groupId);
		} catch (Exception e) {
			logger.error("Could not delete membership activity for groupId " + groupId, e);
		}
	}

	// Add the following method to the bottom of your service wrapper
	// so it can find the appropriate service it’s overriding on deployment.
	@Reference(unbind = "-")
	private void serviceSetter(GroupLocalService groupLocalService) {
		setWrappedService(groupLocalService);
	}

}
