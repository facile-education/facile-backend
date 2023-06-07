package com.weprode.nero.eel.synchronization.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.JSONProxy;
import com.weprode.nero.commons.constants.JSONConstants;
import org.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.weprode.nero.eel.synchronization.service.SubjectLocalServiceUtil;
import com.weprode.nero.eel.synchronization.service.base.SubjectServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

@Component(
        property = {
                "json.web.service.context.name=eel-synchronization",
                "json.web.service.context.path=Subject"
        },
        service = AopService.class
)
public class SubjectServiceImpl extends SubjectServiceBaseImpl {

    @JSONWebService(value = "get-subjects", method = "GET")
    public JSONObject getSubjects() {
        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        return SubjectLocalServiceUtil.getSubjectsJSON();
    }

}
