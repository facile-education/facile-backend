/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.weprode.nero.maintenance.service.impl;

import com.liferay.portal.aop.AopService;


import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.JSONProxy;
import com.weprode.nero.commons.constants.JSONConstants;
import org.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import com.weprode.nero.group.service.GroupUtilsLocalServiceUtil;
import com.weprode.nero.maintenance.GroupCleanupUtil;
import com.weprode.nero.maintenance.service.base.GroupsMaintenanceServiceBaseImpl;

import com.weprode.nero.organization.constants.OrgConstants;
import com.weprode.nero.organization.model.OrgDetails;
import com.weprode.nero.organization.service.OrgDetailsLocalServiceUtil;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=maintenance",
		"json.web.service.context.path=GroupsMaintenance"
	},
	service = AopService.class
)
public class GroupsMaintenanceServiceImpl
	extends GroupsMaintenanceServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(GroupsMaintenanceServiceImpl.class);

	@JSONWebService(value = "archive-groups", method = "POST")
	public JSONObject archiveGroups () {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		int nbArchivedOrgs = 0;
		try {
			// Compute last schoolYear
			Calendar cal = Calendar.getInstance();

			Date currentSchoolYearStartDate = new Date();
			cal.setTime(currentSchoolYearStartDate);
			cal.add(Calendar.YEAR, -1);
			Date lastSchoolYearStartDate = cal.getTime();

			SimpleDateFormat f = new SimpleDateFormat("yyyy");
			String archiveSuffix = f.format(lastSchoolYearStartDate) + "/" + f.format(currentSchoolYearStartDate);

			if (Validator.isNull(archiveSuffix)) {
				result.put(JSONConstants.SUCCESS, false);
			} else {
				// Loop over all schools
				List<Organization> schools = OrgUtilsLocalServiceUtil.getAllSchools();

				for (Organization school : schools) {
					logger.info("SCHOOL "	+ OrgUtilsLocalServiceUtil.formatOrgName(school.getName(), true));

					List<Integer> roles = new ArrayList<>();
					roles.add(OrgConstants.NO_ROLE);

					List<Integer> types = new ArrayList<>();
					types.add(OrgConstants.NO_TYPE);
					types.add(OrgConstants.CLASS_TYPE);
					types.add(OrgConstants.COURS_TYPE);
					// Do not include subject orgs nor volees nor school-level nor school orgs because they are not migrated

					List<Organization> classes = OrgUtilsLocalServiceUtil.getSchoolOrganizations(school.getOrganizationId(), types, roles, false);

					// Loop over classes
					for (Organization org : classes) {

						try {
							OrgDetails orgDetails = OrgDetailsLocalServiceUtil.getOrgDetails(org.getOrganizationId());
							// If not yet migrated
							if (!orgDetails.getIsArchive()) {

								logger.info("    Migrating org " + OrgUtilsLocalServiceUtil.formatOrgName(org.getName(), true) + " ...");

								// Suffix org's name
								org.setName(org.getName() + " " + archiveSuffix);
								OrganizationLocalServiceUtil.updateOrganization(org);

								// Suffix group's name and friendlyUrl
								Group orgGroup = org.getGroup();
								orgGroup.setName(orgGroup.getName() + " " + archiveSuffix);
								orgGroup.setFriendlyURL(orgGroup.getFriendlyURL() + "-" + archiveSuffix);
								GroupLocalServiceUtil.updateGroup(orgGroup);

								// Add archive flag and suffix org detail's name
								orgDetails.setIsArchive(true);
								orgDetails.setOrgName(org.getName());
								OrgDetailsLocalServiceUtil.updateOrgDetails(orgDetails);

								nbArchivedOrgs ++;

							}
						} catch (Exception e) {
							logger.error("Error archiving class", e);
						}

					}
				}
			}
			logger.info("Number of archived orgs : " + nbArchivedOrgs);
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error in archiving process", e);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	@JSONWebService(value = "delete-group", method = "POST")
	public JSONObject deleteGroup (long groupId) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		logger.warn("Param groupId = "+groupId);

		GroupUtilsLocalServiceUtil.groupCleanup(groupId);
		result.put(JSONConstants.SUCCESS, true);

		return result;
	}

	@JSONWebService(value = "delete-groups", method = "POST")
	public JSONObject deleteGroups (File file) {

		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		GroupCleanupUtil.multipleGroupCleanup(file);
		result.put(JSONConstants.SUCCESS, true);

		return result;
	}
}