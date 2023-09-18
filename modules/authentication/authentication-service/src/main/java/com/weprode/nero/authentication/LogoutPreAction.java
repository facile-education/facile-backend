package com.weprode.nero.authentication;

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
                    if (!cookie.getName().equals("cookiesAgreement")) {
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