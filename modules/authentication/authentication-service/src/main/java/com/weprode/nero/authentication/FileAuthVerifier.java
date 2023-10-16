package com.weprode.nero.authentication;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AccessControlContext;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifier;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifierResult;
import com.liferay.portal.kernel.util.Portal;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

@Component(
        immediate = true,
        property = {
                "auth.verifier.FileAuthVerifier.urls.includes=*",
                "auth.verifier.FileAuthVerifier.urls.excludes=/api/jsonws/mobile.mobiledevice/*"
        },
        service = AuthVerifier.class
)
public class FileAuthVerifier implements AuthVerifier {

    public static final String AUTH_TYPE = HttpServletRequest.BASIC_AUTH;

    @Override
    public String getAuthType() {
        return AUTH_TYPE;
    }

    @Override
    public AuthVerifierResult verify(
            AccessControlContext accessControlContext, Properties properties)
            throws AuthException {

        try {
            AuthVerifierResult authVerifierResult = new AuthVerifierResult();

            HttpServletRequest httpServletRequest =
                    accessControlContext.getRequest();
            logger.error("FileAuthVerifier requestUri = " + httpServletRequest.getRequestURI());

            User user = portal.getUser(httpServletRequest);

            if ((user == null) || user.isDefaultUser()) {
                return authVerifierResult;
            }

            if (httpServletRequest.getRequestURI().contains("/api/jsonws/document.mindmap/")
                    || httpServletRequest.getRequestURI().contains("/api/jsonws/document.scratch/")
                    || httpServletRequest.getRequestURI().contains("/api/jsonws/document.geogebra/")) {

                logger.info("File auth verifier : ignoring token for uri " + httpServletRequest.getRequestURI());
                authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
                authVerifierResult.setUserId(user.getUserId());
            }

            return authVerifierResult;
        }
        catch (PortalException | SystemException portalException) {
            throw new AuthException(portalException);
        }
    }

    private static final Log logger = LogFactoryUtil.getLog(
            FileAuthVerifier.class);

    @Reference
    private Portal portal;
}
