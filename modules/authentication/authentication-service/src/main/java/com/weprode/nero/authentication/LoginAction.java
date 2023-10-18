package com.weprode.nero.authentication;

import com.liferay.portal.kernel.exception.UserLockoutException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.session.AuthenticatedSessionManagerUtil;
import com.liferay.portal.kernel.service.PasswordPolicyLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component(
    immediate=true,
        property={"path=/common/login"},
        service= StrutsAction.class
)
public class LoginAction implements StrutsAction {

    final Log logger = LogFactoryUtil.getLog(LoginAction.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String login = ParamUtil.getString(request, "login");
        String password = ParamUtil.getString(request, "password");
        boolean rememberMe = ParamUtil.getBoolean(request, "rememberMe");
        logger.info("Called login with screenName = " + login);

        String authType = PrefsPropsUtil.getString(PropsKeys.COMPANY_SECURITY_AUTH_TYPE, CompanyConstants.AUTH_TYPE_SN);

        JSONObject userStatus = new JSONObject();
        try {
            AuthenticatedSessionManagerUtil.login(request, response, login, password, rememberMe, authType);
            userStatus.put("success", true);
        } catch (UserLockoutException ule) {
            userStatus.put("success", false);
            logger.error("User is locked");
            userStatus.put("isLocked", true);
            long lockoutDuration = PasswordPolicyLocalServiceUtil.getDefaultPasswordPolicy(PortalUtil.getDefaultCompanyId()).getLockoutDuration();
            userStatus.put("lockoutDuration", lockoutDuration);

        } catch (AuthException e) {
            userStatus.put("success", false);
            logger.error("Login failed", e);
            // Get nb remaining tries
            int nbRemainingTries = getNbRemainingTries(login);
            if (nbRemainingTries >= 0) {
                userStatus.put("nbRemainingTries", nbRemainingTries);
                if (nbRemainingTries == 0) {
                    logger.error("User is locked");
                    userStatus.put("isLocked", true);
                    long lockoutDuration = PasswordPolicyLocalServiceUtil.getDefaultPasswordPolicy(PortalUtil.getDefaultCompanyId()).getLockoutDuration();
                    userStatus.put("lockoutDuration", lockoutDuration);
                }
            }
        }

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(userStatus.toString());

        return null;
    }

    private int getNbRemainingTries(String login) {
        try {
            User user = UserLocalServiceUtil.getUserByScreenName(PortalUtil.getDefaultCompanyId(), login);
            int nbMaxFailures = PasswordPolicyLocalServiceUtil.getDefaultPasswordPolicy(PortalUtil.getDefaultCompanyId()).getMaxFailure();
            int nbFailures = user.getFailedLoginAttempts();
            if (nbMaxFailures > nbFailures) {
                return nbMaxFailures - nbFailures;
            }
        } catch (Exception e) {
            // User does not exist -> wrong login : we do not give any indication
            return -1;
        }
        return 0;
    }

}
