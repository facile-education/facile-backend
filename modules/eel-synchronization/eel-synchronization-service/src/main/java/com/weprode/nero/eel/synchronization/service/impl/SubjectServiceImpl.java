package com.weprode.nero.eel.synchronization.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.json.JSONObject;
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
        return SubjectLocalServiceUtil.getSubjectsJSON();
    }

}
