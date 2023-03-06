package com.weprode.nero.commons.wrapper;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalServiceWrapper;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.organization.service.OrgDetailsLocalServiceUtil;
import com.weprode.nero.organization.service.OrgMappingLocalServiceUtil;
import com.weprode.nero.organization.service.OrgMembershipLocalServiceUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


@Component(
		immediate = true,
		service = ServiceWrapper.class
)
public class OrganizationLocalServiceOverride extends OrganizationLocalServiceWrapper {

	private static final Log logger = LogFactoryUtil.getLog(OrganizationLocalServiceOverride.class);

	public OrganizationLocalServiceOverride() {
		super(null);
	}

	@Override
	public Organization deleteOrganization(long organizationId) throws PortalException {

		// Remove organization related data
		deleteOrganizationData(getOrganization(organizationId));

		return super.deleteOrganization(organizationId);
	}

	@Override
	public Organization deleteOrganization(Organization organization) throws PortalException {

		// Remove organization related content
		deleteOrganizationData(organization);

		return super.deleteOrganization(organization);
	}

	private void deleteOrganizationData (Organization organization) throws PortalException {

		logger.debug("Clean up community infos for organizationId " + organization.getOrganizationId());

		logger.info("Deleting organizationId "+ organization.getOrganizationId());

		boolean isSchool = OrgDetailsLocalServiceUtil.isSchool(organization.getOrganizationId());

		// Organization details
		try {
			OrgDetailsLocalServiceUtil.deleteOrgDetails(organization.getOrganizationId());
		} catch (Exception e) {
			logger.error("Could not remove orgDetails");
		}

		try {
			OrgMembershipLocalServiceUtil.removeOrgMemberships(organization.getGroupId());
		} catch (Exception e) {
			logger.error("Could not remove orgMemberships");
		}

		if (isSchool) {
			try {
				OrgMappingLocalServiceUtil.deleteOrgMapping(organization.getOrganizationId());
			} catch (Exception e) {
				logger.error("Could not remove orgMapping");
			}
		}

		// Remove organization members
		long[] userIds = UserLocalServiceUtil.getOrganizationUserIds(organization.getOrganizationId());
		if (userIds != null && userIds.length > 0) {
			logger.info("Removing "+userIds.length+" members from organizationId "+organization.getOrganizationId()+"...");
			UserLocalServiceUtil.unsetOrganizationUsers(organization.getOrganizationId(), userIds);
		}
	}

	// Add the following method to the bottom of your service wrapper
	// so it can find the appropriate service it’s overriding on deployment.
	@Reference(unbind = "-")
	private void serviceSetter(OrganizationLocalService organizationLocalService) {
		setWrappedService(organizationLocalService);
	}
}
