package com.weprode.nero.application.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.application.service.BroadcastLocalServiceUtil;
import com.weprode.nero.application.service.base.BroadcastServiceBaseImpl;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(
        property = {
                "json.web.service.context.name=application",
                "json.web.service.context.path=Broadcast"
        },
        service = AopService.class
)
public class BroadcastServiceImpl extends BroadcastServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(BroadcastServiceImpl.class);

    @JSONWebService(value = "update-broadcast", method = "POST")
    public JSONObject updateBroadcast(long applicationId, long schoolId, boolean isBroadcasted, String applicationUrl) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            if (!RoleUtilsLocalServiceUtil.isAdministrator(user)
                    && !RoleUtilsLocalServiceUtil.isDirectionMember(user)
                    && !RoleUtilsLocalServiceUtil.isSchoolAdmin(user, schoolId)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
            logger.info("User " + user.getFullName() + " updates broadcast for applicationId " +
                    applicationId + " and schoolId " + schoolId + ": isBroadcasted is " +
                    isBroadcasted + " and applicationUrl = " + applicationUrl);
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            BroadcastLocalServiceUtil.updateBroadcast(applicationId, schoolId, isBroadcasted, applicationUrl);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error fetching schools", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

}
