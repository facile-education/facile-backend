package com.weprode.nero.authentication;

import com.liferay.portal.kernel.exception.NoSuchUserException;
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

@Component(immediate = true)
public class ShibbolethAutoLogin implements AutoLogin {

    private static final Log logger = LogFactoryUtil.getLog(ShibbolethAutoLogin.class);

    public String[] login(
            HttpServletRequest request, HttpServletResponse response)
            throws AutoLoginException {

        logger.debug("ShibbolethAutoLogin : URI = " + request.getRequestURI());

        try {
            String authType = PrefsPropsUtil.getString(PropsKeys.COMPANY_SECURITY_AUTH_TYPE, CompanyConstants.AUTH_TYPE_EA);
            long companyId = PortalUtil.getCompanyId(request);
            User user = null;

            if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
                String login = request.getHeader(SHIBBOLETH_NAME_ID);
                logger.debug("AuthType is SN : login=" + login);
                if (Validator.isNull(login)) {
                    return null;
                }
                logger.debug("Trying to find user with screen name: " + login);
                user = UserLocalServiceUtil.getUserByScreenName(companyId, login);

            } else if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {

                String emailAddress = request.getHeader(SHIBBOLETH_EMAIL);
                logger.debug("AuthType is Email : mail=" + emailAddress);
                if (Validator.isNull(emailAddress)) {
                    return null;
                }
                logger.debug("Trying to find user with email: " + emailAddress);
                user = UserLocalServiceUtil.getUserByEmailAddress(companyId, emailAddress);
            }

            if (user != null) {
                logger.info("User authenticated: " + user.getScreenName() + " (" + user.getEmailAddress() + ")");
                //logger.info("================ User " + user.getFullName() + " (" + user.getUserId() + ") logs in =======================");
                String[] credentials = new String[3];
                credentials[0] = String.valueOf(user.getUserId());
                credentials[1] = user.getPassword();
                credentials[2] = String.valueOf(user.isPasswordEncrypted());
                return credentials;
            }

        } catch (NoSuchUserException e) {
            logger.error("User not found in DB");
        } catch (Exception e) {
            logger.error("Error occurred while login", e);
            throw new AutoLoginException(e);
        }

        return null;
    }

    public static final String SHIBBOLETH_NAME_ID = "NameID";
    public static final String SHIBBOLETH_EMAIL = "mail";

}