package com.weprode.nero.preference.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.commons.properties.NeroSystemProperties;
import com.weprode.nero.messaging.service.MessageLocalServiceUtil;
import com.weprode.nero.preference.model.NotifyConfig;
import com.weprode.nero.preference.model.UserProperties;
import com.weprode.nero.preference.service.NotifyConfigLocalServiceUtil;
import com.weprode.nero.preference.service.UserPropertiesLocalServiceUtil;
import com.weprode.nero.preference.service.UserPropertiesService;
import com.weprode.nero.preference.service.base.UserPropertiesServiceBaseImpl;
import com.weprode.nero.user.service.UserUtilsLocalServiceUtil;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component(
        property = {
                "json.web.service.context.name=preference",
                "json.web.service.context.path=UserProperties"
        },
        service = UserPropertiesService.class
)
public class UserPropertiesServiceImpl extends UserPropertiesServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(UserPropertiesServiceImpl.class);

    private static final int NONE = 0;
    private static final int DAILY = 1;
    private static final int WEEKLY = 2;

    @JSONWebService(value = "update-user-picture", method = "POST")
    public JSONObject updateUserPicture(File picture) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            if (picture.length() == 0) {	// Add condition to avoid nullPointerException
                UserLocalServiceUtil.updatePortrait(user.getUserId(), new byte[0]);
            } else {
                UserLocalServiceUtil.updatePortrait(user.getUserId(), Files.readAllBytes(picture.toPath()));
            }

            result.put("imageUrl", UserConstants.getPortraitURL(PortalUtil.getPathImage(), user.isMale(),
                    user.getPortraitId(), user.getUserUuid()));
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Could not update picture for userId " + user.getUserId(), e);
        }

        return result;
    }

    @JSONWebService(value = "update-theme-color", method = "POST")
    public JSONObject updateThemeColor(String color) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            // Check color format
            Pattern pattern = Pattern.compile("([0-9a-f]{3}|[0-9a-f]{6})"); // ([0-9a-f]{3}|[0-9a-f]{6}|[0-9a-f]{8}) if transparency allowed
            Matcher matcher = pattern.matcher(color);
            if (matcher.matches()) {
                UserProperties userProperties = UserPropertiesLocalServiceUtil.getUserProperties(user.getUserId());
                userProperties.setThemeColor(color);
                UserPropertiesLocalServiceUtil.updateUserProperties(userProperties);

                result.put(JSONConstants.SUCCESS, true);
            } else {
                result.put(JSONConstants.ERROR, "FORMAT_EXCEPTION");
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

        } catch (Exception e) {
            logger.error("Could not update theme color for userId " + user.getUserId(), e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "update-report-frequency", method = "POST")
    public JSONObject updateReportFrequency(int frequency) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            // Frequency : NONE = 0, DAILY = 1, WEEKLY = 2
            NotifyConfig userNotificationConfig = NotifyConfigLocalServiceUtil.getOrCreateNotifyConfig(user.getUserId());

            userNotificationConfig.setActivate(frequency != NONE);
            userNotificationConfig.setDigestPeriod(frequency);
            userNotificationConfig.setUserId(user.getUserId());

            NotifyConfigLocalServiceUtil.updateNotifyConfig(userNotificationConfig);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Could not update notification frequency for userId " + user.getUserId(), e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "update-webdav-state", method = "POST")
    public JSONObject updateWebdavState(boolean isEnabled) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            UserProperties userProperties = UserPropertiesLocalServiceUtil.getUserProperties(user.getUserId());
            userProperties.setWebdavActivated(isEnabled);
            UserPropertiesLocalServiceUtil.updateUserProperties(userProperties);

            boolean isLocalUser = userProperties.isManualAccount();

            if (!isLocalUser && isEnabled) {
                // Regenerate random password when not an user manual
                String password = PwdGenerator.getPassword();

                logger.info("About to update password for webdav activation");
                try {
                    result = updateUserPasword(user, password, false, true);
                } catch (Exception e) {
                    logger.error("Could not set webdav password for userId " + user.getUserId(), e);
                    result.put(JSONConstants.SUCCESS, false);
                    return result;
                }
            }

            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Could not enable webdav for userId " + user.getUserId(), e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "update-webdav-password", method = "POST")
    public JSONObject updateWebdavPassword(String password, String confirmPassword) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            if (!password.isEmpty() && password.equals(confirmPassword)) {
                result = updateUserPasword(user, password, true, true);
            } else {
                result.put(JSONConstants.SUCCESS, false);
            }
        } catch (Exception e) {
            logger.error("Could not update webdav password for userId " + user.getUserId(), e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    private JSONObject updateUserPasword(User user, String password, boolean isUpdated, boolean isWebdav) throws PortalException, SystemException {
        JSONObject result = new JSONObject();

        String errorMessage = UserUtilsLocalServiceUtil.updateUserPassword(user, password, password, false);
        if (!errorMessage.equals("")) {
            result.put("portal_message", errorMessage);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        } else {
            result.put("screenname", user.getScreenName());
            if (!isUpdated) {
                result.put("password", password);
            }

            // Create the password with the first and last characters is display in clear and the rest is changed to *
            StringBuilder hiddenPwd = new StringBuilder();
            hiddenPwd.append(password.charAt(0));
            hiddenPwd.append("*".repeat(Math.max(0, (password.length() - 2))));
            hiddenPwd.append(password.substring(password.length()-1));

            // Send password in IM
            String defaultStyle = "padding: 8px; margin: 0px; font-family: \"Open sans\"; border-left: 2px solid #8b9ea8; border-right: 2px solid #8b9ea8; width: 95.5%;";
            String msg = "<p style='width: 95.5%;margin: 10px 0px 0px 0px;padding: 8px;font-size: 16px;border-radius: 5px 5px 0px 0px;color: #fff; background:#8b9ea8'>Compte d'acc&egrave;s "+ (isWebdav ? "webdav" : "ENT") + "</p>"+
                    "<p style='" + defaultStyle + "'>Bonjour "+user.getFullName()+", </p><br/>";

            if (isWebdav && !isUpdated) {
                msg += "<p style='" + defaultStyle + " margin-top: -21px;'>Vous venez d'activer votre compte webdav. </p><br/>" +
                        "<p style='" + defaultStyle + " margin-top: -32px;'>Vos codes d'acc&egrave;s sont: </p><br/>" +
                        "<p style='" + defaultStyle + " margin-top: -32px;'>Identifiant :" + user.getScreenName() + " </p><br/> "+
                        "<p style='" + defaultStyle + " margin-top: -32px;'>Mot de passe :" + password + " </p><br/>";
            } else if (isUpdated) {
                msg += "<p style='" + defaultStyle + " margin-top: -21px;'>Votre mot de passe "+ (isWebdav ? "webdav" : "ENT") + " vient d'&ecirc;tre modifi&eacute;.  </p><br/>" +
                        "<p style='" + defaultStyle + " margin-top: -32px;'>Nouveau mdp: "+hiddenPwd+" </p><br/>";
            }

            msg += "<p style='" + defaultStyle + " margin-top: -27px; border-bottom: 2px solid #8b9ea8; border-radius: 0px 0px 5px 5px;'>Cordialement</p>";

            long defaultSenderId = Long.parseLong(PropsUtil.get(NeroSystemProperties.CONFIRMATION_SENDER_ID));
            List<Long> dest =  new ArrayList<>();
            dest.add(user.getUserId());
            String subject = "Activation de webdav";
            // String subject = "Modification du mot de passe de " + (isWebdav ? "webdav" : "l'ENT");

            MessageLocalServiceUtil.sendMessage(defaultSenderId, dest, subject, msg);
            result.put(JSONConstants.SUCCESS, true);
        }

        return result;
    }

}
