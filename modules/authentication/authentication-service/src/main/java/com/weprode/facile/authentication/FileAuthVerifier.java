/**
 * Copyright (c) 2022-present - Weprode
 * This file is part of FACILE ENT Project.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License (LGPL) as
 * published by the Free Software Foundation (version 2.1 of the License).
 * See LICENCE.txt file
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */
package com.weprode.facile.authentication;

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
import org.apache.logging.log4j.ThreadContext;
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

        ThreadContext.clearAll();
        ThreadContext.push("====================");

        try {
            AuthVerifierResult authVerifierResult = new AuthVerifierResult();
            HttpServletRequest httpServletRequest = accessControlContext.getRequest();
            logger.debug("FileAuthVerifier requestUri = " + httpServletRequest.getRequestURI());

            User user = portal.getUser(httpServletRequest);

            if (httpServletRequest.getRequestURI().contains("/api/jsonws/mobile.mobiledevice/")) {
                String uri = httpServletRequest.getRequestURI();
                int userIdIndex = httpServletRequest.getRequestURI().indexOf("user-id") + 8;
                String [] uriTab = uri.substring(userIdIndex).split("/");
                long userId = Long.parseLong(uriTab[0]);
                logger.debug("File auth verifier : ignoring token for uri " + httpServletRequest.getRequestURI() + " and userId " + userId);
                authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
                authVerifierResult.setUserId(userId);
                return authVerifierResult;
            }

            if ((user == null) || user.isGuestUser()) {
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
