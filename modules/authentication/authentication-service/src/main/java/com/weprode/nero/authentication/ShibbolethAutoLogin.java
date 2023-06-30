package com.weprode.nero.authentication;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auto.login.AutoLogin;
import com.liferay.portal.kernel.security.auto.login.AutoLoginException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component(immediate = true)
public class ShibbolethAutoLogin implements AutoLogin {

    private static final Log logger = LogFactoryUtil.getLog(ShibbolethAutoLogin.class);

    public String[] handleException(
            HttpServletRequest request, HttpServletResponse response,
            Exception e)
            throws AutoLoginException {
        // Taken from BaseAutoLogin
        if (Validator.isNull(request.getAttribute(AutoLogin.AUTO_LOGIN_REDIRECT))) {
            throw new AutoLoginException(e);
        }
        logger.error("Error in Shibboleth Auto Login", e);
        return null;
    }

    public String[] login(
            HttpServletRequest request, HttpServletResponse response)
            throws AutoLoginException {

        User user;
        String[] credentials = null;
        HttpSession session = request.getSession(false);
        long companyId = PortalUtil.getCompanyId(request);


        try {
            logger.info("Shibboleth Autologin");

            user = loginFromSession(companyId, session);
            if (Validator.isNotNull(user)) {
                credentials = new String[3];
                credentials[0] = String.valueOf(user.getUserId());
                credentials[1] = user.getPassword();
                credentials[2] = Boolean.TRUE.toString();
            }

        } catch (NoSuchUserException e) {
            logger.error("User not found in DB");
        } catch (Exception e) {
            logger.error("Error occurred while login", e);
            throw new AutoLoginException(e);
        }

        return credentials;
    }

    private User loginFromSession(long companyId, HttpSession session) throws PortalException {

        User user = null;
        String authType = PrefsPropsUtil.getString(PropsKeys.COMPANY_SECURITY_AUTH_TYPE, CompanyConstants.AUTH_TYPE_EA);

        try {
            if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
                String login = (String) session.getAttribute(SHIBBOLETH_LOGIN);
                if (Validator.isNull(login)) {
                    return null;
                }
                logger.info("Trying to find user with screen name: " + login);
                user = UserLocalServiceUtil.getUserByScreenName(companyId, login);

            } else if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {

                String emailAddress = (String) session.getAttribute(SHIBBOLETH_HEADER_EMAIL);
                if (Validator.isNull(emailAddress)) {
                    return null;
                }
                logger.info("Trying to find user with email: " + emailAddress);
                user = UserLocalServiceUtil.getUserByEmailAddress(companyId, emailAddress);
            }

            logger.info("User found: " + user.getScreenName() + " (" + user.getEmailAddress() + ")");

        } catch (NoSuchUserException e) {
            logger.error("User not found", e);
        }

        return user;
    }

    public static final String SHIBBOLETH_HEADER_EMAIL = "mail";
    public static final String SHIBBOLETH_LOGIN = "shibboleth.login";

}