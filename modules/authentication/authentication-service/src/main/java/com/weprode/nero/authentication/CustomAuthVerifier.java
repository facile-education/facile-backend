package com.weprode.nero.authentication;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AccessControlContext;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifier;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifierResult;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

@Component(
        immediate = true,
        service = AuthVerifier.class,
        property = {
                "auth.verifier.CustomAuthVerifier.urls.includes=*"
        }
)
public class CustomAuthVerifier implements AuthVerifier {

    private static final Log logger = LogFactoryUtil.getLog(CustomAuthVerifier.class);

    @Override
    public String getAuthType() {
        return HttpServletRequest.FORM_AUTH;
    }

    @Override
    public AuthVerifierResult verify(AccessControlContext accessControlContext, Properties properties) {

//        try {
//            AuthVerifierResult authVerifierResult = new AuthVerifierResult();
//
//            HttpServletRequest httpServletRequest =
//                    accessControlContext.getRequest();
//
//            User user = PortalUtil.getUser(httpServletRequest);
//
//            boolean checkCSRFToken = GetterUtil.getBoolean(properties.get("check.csrf.token"), true);
//
//            if (checkCSRFToken) {
//                HttpServletRequest originalHttpServletRequest = PortalUtil.getOriginalServletRequest(httpServletRequest);
//                String requestURI = originalHttpServletRequest.getRequestURI();
//
//                try {
//                    AuthTokenUtil.checkCSRFToken(originalHttpServletRequest, requestURI);
//                } catch (PrincipalException principalException) {
//                    if (logger.isDebugEnabled()) {
//                        logger.error(
//                                StringBundler.concat(
//                                        "Unable to verify CSRF token for ", requestURI,
//                                        ": ", principalException.getMessage()));
//                    }
//
////                    return authVerifierResult;
//                }
//            }
//
//            if (user == null) {
//                //System.out.println("CustomAuthVerifier: Session user is null");
//            } else if (user.isDefaultUser()) {
//                //System.out.println("CustomAuthVerifier: Session user is default");
//            }
//
//            String sessionId = accessControlContext.getRequest().getSession().getId();
//            String contextPath = accessControlContext.getRequest().getContextPath();
//            String secure = "";
//            if (accessControlContext.getRequest().isSecure()) {
//                secure = "; Secure";
//            }
            //logger.info("CustomAuthVerifier : Session id is : " + sessionId);
//            accessControlContext.getResponse().setHeader("SET-COOKIE", "JSESSIONID=" + sessionId
//                    + "; Path=" + contextPath + secure + "; HttpOnly");
//
//            if ((user != null) && !user.isDefaultUser()) {
//                logger.info("CustomAuthVerifier: identify " + user.getScreenName() + " with session");
//                authVerifierResult.setPasswordBasedAuthentication(true);
//                authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
//                authVerifierResult.setUserId(user.getUserId());
//
//                return authVerifierResult;
//            }
//
//        } catch (Exception e) {
//            logger.error(e);
//        }

        logger.info("CustomAuthVerifier for URI " + accessControlContext.getRequest().getRequestURI());

        try {
            long userId = 0;
            String[] credentials = new ShibbolethAutoLogin().login(accessControlContext.getRequest(), accessControlContext.getResponse());
            AuthVerifierResult authVerifierResult = new AuthVerifierResult();
            if (credentials != null) {
                userId = Long.parseLong(credentials[0]);
                User user = UserLocalServiceUtil.getUser(userId);
                logger.debug("Connected user is " + user.getFullName());
                authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
                authVerifierResult.setPasswordBasedAuthentication(true);
                authVerifierResult.setUserId(userId);
                authVerifierResult.setPassword(credentials[1]);
            } else {
                credentials = new MobileApplicationAutoLogin().login(accessControlContext.getRequest(), accessControlContext.getResponse());
                if (credentials != null) {
                    userId = Long.parseLong(credentials[0]);
                    User user = UserLocalServiceUtil.getUser(userId);
                    logger.debug("Connected user on mobile app is " + user.getFullName());
                    authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
                    authVerifierResult.setPasswordBasedAuthentication(true);
                    authVerifierResult.setUserId(userId);
                    authVerifierResult.setPassword(credentials[1]);
                }
            }
//            if (credentials != null && userId != 0) {
//                try {
//                    User user = UserLocalServiceUtil.getUser(userId);
//                    logger.info("companyId = " + PortalUtil.getCompany(accessControlContext.getRequest()));
//                    logger.info(" > found user " + user.getScreenName() + ", password=" + user.getPassword() + ", authType=" + PrefsPropsUtil.getString(PropsKeys.COMPANY_SECURITY_AUTH_TYPE, CompanyConstants.AUTH_TYPE_EA));
//                    logger.info("Before AuthenticatedSessionManagerUtil session=" + accessControlContext.getRequest().getSession().getId());
//                    AuthenticatedSessionManagerUtil.renewSession(accessControlContext.getRequest(), accessControlContext.getRequest().getSession());
//                    //AuthenticatedSessionManagerUtil.login(accessControlContext.getRequest(), accessControlContext.getResponse(), user.getScreenName(), "WeProde73!", false, PrefsPropsUtil.getString(PropsKeys.COMPANY_SECURITY_AUTH_TYPE, CompanyConstants.AUTH_TYPE_EA));
//                    logger.info("After AuthenticatedSessionManagerUtil session=" + accessControlContext.getRequest().getSession().getId());
//                } catch (Exception e) {
//                    logger.error("Error in AuthenticatedSessionManagerUtil", e);
//                }
//            }

            return authVerifierResult;
        } catch (Exception e) {
            logger.error("Exception in AuthVerifier");
        }
        return null;
    }
}