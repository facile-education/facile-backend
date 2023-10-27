package com.weprode.nero.authentication;

import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.statistic.service.UserLoginLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(
        immediate = true,
        property = "key=login.events.post",
        service = LifecycleAction.class
)
public class LoginPostAction implements LifecycleAction {

    private static final Log logger = LogFactoryUtil.getLog(LoginPostAction.class);

    @Override
    public void processLifecycleEvent(LifecycleEvent lifecycleEvent) {

        try {
            User identifiedUser = PortalUtil.getUser(lifecycleEvent.getRequest());
            logger.info("+++++ loginPostAction for user " + identifiedUser.getFullName());
            UserLoginLocalServiceUtil.addUserLogin(identifiedUser, false);

        } catch (PortalException e) {
            logger.error("Could not add login stat for logged user", e);
        }

    }

}
