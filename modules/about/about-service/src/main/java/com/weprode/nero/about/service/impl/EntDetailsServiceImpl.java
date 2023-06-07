package com.weprode.nero.about.service.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.about.model.EntVersion;
import com.weprode.nero.about.service.EntDetailsLocalServiceUtil;
import com.weprode.nero.about.service.EntVersionLocalServiceUtil;
import com.weprode.nero.about.service.base.EntDetailsServiceBaseImpl;
import com.weprode.nero.commons.JSONProxy;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=about",
                "json.web.service.context.path=EntDetails"
        },
        service = AopService.class
)
public class EntDetailsServiceImpl extends EntDetailsServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(EntDetailsServiceImpl.class);

    @JSONWebService(value = "create-version", method = "POST")
    public JSONObject createVersion (String versionNumber, String versionDetails) {
        JSONObject result = new JSONObject();
        result.put(JSONConstants.SUCCESS, true);

        Date date = new Date();
        try {
            User user = getGuestOrUser();

            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
            if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
            }
            // Ensure the details are properly formatted
            JSONObject detailsJson = new JSONObject(versionDetails);
            // news array must exist (but may be empty)
            JSONArray news = detailsJson.getJSONArray(JSONConstants.NEWS);
            if (news == null) {
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
            // others string must not be empty
            String others = detailsJson.getString(JSONConstants.OTHERS);
            if (others == null || others.isEmpty()) {
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            EntVersionLocalServiceUtil.addVersion(versionNumber, versionDetails, date);
        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "get-version-list", method = "GET")
    public JSONObject getVersionList () {
        JSONObject result = new JSONObject();

        try {
            User user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
            JSONArray entVersionsJson = new JSONArray();
            List<EntVersion> entVersions = EntVersionLocalServiceUtil.getAllEntVersion();

            // Parse all versions
            for (EntVersion entVersion : entVersions) {

                JSONObject entVersionJson = new JSONObject();
                entVersionJson.put(JSONConstants.VERSION_ID, entVersion.getEntVersionId());
                entVersionJson.put(JSONConstants.VERSION_NUMBER, entVersion.getVersion());
                entVersionJson.put(JSONConstants.VERSION_DATE, entVersion.getVersionDate());
                entVersionJson.put(JSONConstants.LATEST, entVersion.getIsLast());
                entVersionsJson.put(entVersionJson);
            }
            result.put(JSONConstants.VERSIONS, entVersionsJson);
        } catch (Exception e) {
            logger.error(e);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }
        result.put(JSONConstants.SUCCESS, true);

        return result;
    }

    @JSONWebService(value = "get-version-details", method = "GET")
    public JSONObject getVersionDetails (Long versionId) {
        JSONObject result = new JSONObject();

        result.put(JSONConstants.SUCCESS, true);
        StringBuilder versionDetailsHtml = new StringBuilder();
        try {
            User user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
            EntVersion entVersion = EntVersionLocalServiceUtil.getEntVersion(versionId);
            versionDetailsHtml.append("Version ").append(entVersion.getVersion()).append(" du ").append(new SimpleDateFormat(JSONConstants.FRENCH_FORMAT).format(entVersion.getVersionDate()));
            versionDetailsHtml.append("</br></br>");

            // Important news
            versionDetailsHtml.append("<ul>");
            JSONObject detailsJson = new JSONObject(entVersion.getDetails());
            JSONArray news = new JSONArray();
            if (detailsJson.has(JSONConstants.NEW)) {
                news = detailsJson.getJSONArray(JSONConstants.NEW);
            }

            if (news != null && news.length() > 0) {
                for (int i = 0 ; i < news.length() ; i++) {
                    JSONObject newsJson = news.getJSONObject(i);
                    versionDetailsHtml.append("<li><strong>Nouveaut&eacute; : ")
                            .append(newsJson.getString(JSONConstants.TITLE))
                            .append(" - </strong>")
                            .append(newsJson.getString(JSONConstants.DESCRIPTION))
                            .append("</li>");
                }
            }
            versionDetailsHtml.append("</ul>");
            versionDetailsHtml.append("</br>");

            // Other minor updates
            String others = JSONConstants.getStringValue(detailsJson, JSONConstants.OTHERS, StringPool.BLANK);
            versionDetailsHtml.append(others);

        } catch (Exception e) {
            logger.error("Error when getting version details", e);
            result.put(JSONConstants.SUCCESS, false);
        }
        result.put(JSONConstants.VERSION_DETAILS, versionDetailsHtml.toString());

        return result;
    }

    @JSONWebService(value = "get-terms-of-use", method = "GET")
    public JSONObject getTermsOfUse() {
        JSONObject result = new JSONObject();

        result.put(JSONConstants.SUCCESS, false);
        try {
            User user = getGuestOrUser();

            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }

            result.put(JSONConstants.TERMS_OF_USE, EntDetailsLocalServiceUtil.getTermsOfUse());
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error(e);
        }

        return result;
    }

}
