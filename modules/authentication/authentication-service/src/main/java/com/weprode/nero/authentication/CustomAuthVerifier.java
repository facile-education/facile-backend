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
        try {
            String[] credentials = new ShibbolethAutoLogin().login(accessControlContext.getRequest(), accessControlContext.getResponse());
            AuthVerifierResult authVerifierResult = new AuthVerifierResult();
            if (credentials != null) {
                long userId = Long.parseLong(credentials[0]);
                User user = UserLocalServiceUtil.getUser(userId);
                logger.info("USER connected is " + user.getFullName());
                authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
                authVerifierResult.setPasswordBasedAuthentication(true);
                authVerifierResult.setUserId(userId);
                authVerifierResult.setPassword(credentials[1]);
            } else {
                logger.info("User is not connected through Shibboleth -> test mobile app");
                credentials = new MobileApplicationAutoLogin().login(accessControlContext.getRequest(), accessControlContext.getResponse());
                if (credentials != null) {
                    logger.info("User seems to be authenticated on mobile app");
                    long userId = Long.parseLong(credentials[0]);
                    User user = UserLocalServiceUtil.getUser(userId);
                    logger.info("USER connected on mobile app is " + user.getFullName());
                    authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
                    authVerifierResult.setPasswordBasedAuthentication(true);
                    authVerifierResult.setUserId(userId);
                    authVerifierResult.setPassword(credentials[1]);
                }
            }

            return authVerifierResult;
        } catch (Exception e) {
            logger.error("Exception in AuthVerifier");
        }
        return null;
    }
}