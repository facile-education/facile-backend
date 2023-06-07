package com.weprode.nero.user.service.impl;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import com.weprode.nero.commons.JSONProxy;
import org.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.commons.properties.NeroSystemProperties;
import com.weprode.nero.user.service.UserPasswordService;
import com.weprode.nero.user.service.base.UserPasswordServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import javax.mail.internet.InternetAddress;

@Component(
        property = {
                "json.web.service.context.name=user",
                "json.web.service.context.path=UserPassword"
        },
        service = UserPasswordService.class
)
public class UserPasswordServiceImpl extends UserPasswordServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(UserPasswordServiceImpl.class);

    @JSONWebService(value = "send-password-reset-link", method = "POST")
    public JSONObject sendPasswordResetLink(String email) throws SystemException {
        logger.info("Start sendPasswordResetLink for email="+email);
        
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        try {
            User targetUser = UserLocalServiceUtil.getUserByEmailAddress(PortalUtil.getDefaultCompanyId(), email);
            ServiceContext serviceContext = new ServiceContext();
            serviceContext.setPortalURL(PropsUtil.get(NeroSystemProperties.PORTAL_URL));
            serviceContext.setPathMain("/c/");

            UserLocalServiceUtil.sendPassword(targetUser.getCompanyId(), email, "Equipe technique",
                    "assistance@pentila.com", "", "", serviceContext);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Not found any user with email "+email);
        }

        return result;
    }

    @JSONWebService(value = "send-screenname", method = "POST")
    public JSONObject sendScreenname(String email) throws SystemException {
        logger.info("Start sendScreenname for email="+email);
        
        JSONObject result = new JSONObject();
        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        try {
            User targetUser = UserLocalServiceUtil.getUserByEmailAddress(PortalUtil.getDefaultCompanyId(), email);

            String subject = "Envoi d'identifiant ENT";
            String content = "Bonjour,<br/><br/>Votre identifiant ENT est " + targetUser.getScreenName() + ".<br/><br/>L'équipe technique";

            MailMessage mailMessage = new MailMessage();
            mailMessage.setHTMLFormat(true);

            mailMessage.setFrom(new InternetAddress(PropsUtil.get(NeroSystemProperties.MAIL_NO_REPLY)));
            mailMessage.setTo(new InternetAddress(email));

            mailMessage.setSubject(subject);
            mailMessage.setBody(content);

            try {
                MailServiceUtil.sendEmail(mailMessage);
                result.put(JSONConstants.SUCCESS, true);
            } catch (Exception e) {
                result.put(JSONConstants.SUCCESS, false);
                logger.error("Error sending screenName reminder mail to address "+targetUser.getEmailAddress(), e);
            }

        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Not found any user with email " + email);
        }

        return result;
    }
    
}
