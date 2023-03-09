package com.weprode.nero.role.service.impl;

import com.liferay.portal.kernel.exception.RolePermissionsException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.role.constants.NeroRoleConstants;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsService;
import com.weprode.nero.role.service.base.RoleUtilsServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import static com.liferay.portal.kernel.security.auth.GuestOrUserUtil.getGuestOrUser;

@Component(
		property = {
				"json.web.service.context.name=role",
				"json.web.service.context.path=RoleUtils"
		},
		service = RoleUtilsService.class
)
public class RoleUtilsServiceImpl extends RoleUtilsServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(RoleUtilsServiceImpl.class);

	@JSONWebService(value = "get-main-roles", method = "GET")
	public JSONObject getMainRoles() {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		User agent;
		try {
			agent = getGuestOrUser();
			if (agent.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				throw new AuthException();
			}
			if (!RoleUtilsLocalServiceUtil.isTeacher(agent) && 
                    !RoleUtilsLocalServiceUtil.isPersonal(agent)) {
				throw new RolePermissionsException();
			}
		} catch (Exception e) {
			result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
			result.put(JSONConstants.SUCCESS, false);
			return result;
		}

		try {
			JSONArray roles = JSONFactoryUtil.createJSONArray();
			for (Role role : RoleUtilsLocalServiceUtil.getUserSearchableRoles(agent)) {
				JSONObject jsonRole = getRoleAsJSON(role, agent);
				roles.put(jsonRole);
			}

			result.put(JSONConstants.ROLES, roles);
			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error("Error while fetching schools for manual user creation", e);
			result.put(JSONConstants.SUCCESS, false);
		}
		
		return result;
	}

	@JSONWebService(value = "get-local-user-roles", method = "GET")
	public JSONObject getLocalUserRoles() {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		User adminUser;
		try {
			adminUser = getGuestOrUser();
			if (adminUser.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				throw new AuthException();
			}
			if (!(RoleUtilsLocalServiceUtil.isAdministrator(adminUser) || RoleUtilsLocalServiceUtil.isDirectionMember(adminUser) ||
					RoleUtilsLocalServiceUtil.isSchoolAdmin(adminUser) || RoleUtilsLocalServiceUtil.isENTAdmin(adminUser))) {
				throw new RolePermissionsException();
			}
		} catch (Exception e) {
			result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
			result.put(JSONConstants.SUCCESS, false);
			return result;
		}

		try {
			JSONArray localUsersRoles = JSONFactoryUtil.createJSONArray();

			for (Role role : RoleUtilsLocalServiceUtil.getAvailableRolesForLocalUser()) {
				JSONObject jsonRole = getRoleAsJSON(role, adminUser);
				localUsersRoles.put(jsonRole);
			}
			result.put(JSONConstants.ROLES, localUsersRoles);
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error while fetching schools for manual user creation", e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	// Used in app manager (includes admin roles and if role can be apply to a class org)
	@JSONWebService(value = "get-broadcast-roles", method = "GET")
	public JSONObject getBroadcastRoles() {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		User user;
		try {
			user = getGuestOrUser();

			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				throw new AuthException();
			}
			if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) && !RoleUtilsLocalServiceUtil.isSchoolAdmin(user)) {
				throw new RolePermissionsException();
			}
		} catch (Exception e) {
			result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
			result.put(JSONConstants.SUCCESS, false);
			return result;
		}

		try {
			JSONArray roleList = JSONFactoryUtil.createJSONArray();

			for (Role role : RoleUtilsLocalServiceUtil.getUserSearchableRoles(user)) {
				final JSONObject curr = JSONFactoryUtil.createJSONObject();
				curr.put(JSONConstants.ROLE_ID, role.getRoleId());
				curr.put(JSONConstants.DISPLAY_TEXT, LanguageUtil.get(user.getLocale(), role.getName()));
				curr.put(JSONConstants.IS_FOR_CLASS, (role.getName().equals(NeroRoleConstants.NATIONAL_1) || role.getName().equals(NeroRoleConstants.NATIONAL_2) ||
					role.getName().equals(NeroRoleConstants.NATIONAL_3) || role.getName().equals(NeroRoleConstants.MAIN_TEACHER)));
				roleList.put(curr);
			}

			Role schoolAdmin = RoleUtilsLocalServiceUtil.getSchoolAdminRole();
			JSONObject schoolAdminRole = JSONFactoryUtil.createJSONObject();
			schoolAdminRole.put(JSONConstants.ROLE_ID, schoolAdmin.getRoleId());
			schoolAdminRole.put(JSONConstants.DISPLAY_TEXT, "Administrateur local");
			schoolAdminRole.put(JSONConstants.IS_FOR_CLASS, false);
			roleList.put(schoolAdminRole);

			Role entAdmin = RoleUtilsLocalServiceUtil.getEntAdminRole();
			JSONObject entAdminRole = JSONFactoryUtil.createJSONObject();
			entAdminRole.put(JSONConstants.ROLE_ID, entAdmin.getRoleId());
			entAdminRole.put(JSONConstants.DISPLAY_TEXT, "Administrateur ENT");
			entAdminRole.put(JSONConstants.IS_FOR_CLASS, false);
			roleList.put(entAdminRole);
			
			result.put(JSONConstants.SUCCESS, true);
			result.put(JSONConstants.ROLES, roleList);
		} catch (Exception e) {
			logger.error("Error fetching schools", e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	private JSONObject getRoleAsJSON(Role role, User user) {
		JSONObject jsonRole = JSONFactoryUtil.createJSONObject();

		jsonRole.put(JSONConstants.ROLE_ID, role.getRoleId());
		jsonRole.put(JSONConstants.ROLE_CODE, role.getName());
		jsonRole.put(JSONConstants.LABEL, role.getTitle(user.getLocale()));

		return jsonRole;
	}
}
