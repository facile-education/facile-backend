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

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.Cookie;

@Component(
        immediate = true, property = "key=logout.events.pre",
        service = LifecycleAction.class
)
public class LogoutPreAction implements LifecycleAction {

    private static final Log logger = LogFactoryUtil.getLog(LoginPostAction.class);

    @Override
    public void processLifecycleEvent(LifecycleEvent lifecycleEvent)
            throws ActionException {

        try {
            Cookie[] cookies = lifecycleEvent.getRequest().getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    // Preserve agreement cookie
                    // Do not remove shibsession cookie : the logout will not be propagated to IDP
                    if (!cookie.getName().equals("cookiesAgreement")
                            && !cookie.getName().contains("shibsession")) {
                        cookie.setValue("");
                        cookie.setPath("/");
                        cookie.setMaxAge(0);
                        lifecycleEvent.getResponse().addCookie(cookie);
                    }
                }
            }

            if (logger.isDebugEnabled()) {
                User identifiedUser = PortalUtil.getUser(lifecycleEvent.getRequest());
                logger.info("LogoutPreAction (emptying cookies) for user " + identifiedUser.getFullName());
            }
        } catch (PortalException e) {
            logger.error("Could not add login stat for logged user", e);
        }

    }
}