package com.weprode.nero.menu.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.weprode.nero.menu.enums.MenuEntry;
import com.weprode.nero.menu.service.base.SideMenuLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        service = AopService.class
)
public class SideMenuLocalServiceImpl extends SideMenuLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(SideMenuLocalServiceImpl.class);

    public List<MenuEntry> getUserMenu(User user) {

        // TODO Application manager
        //  get available apps for user and filter full menu
        // List<Service> userApplications = ServiceLocalServiceUtil.getUserServices(currUser);

        return MenuEntry.getFullMenu();
    }
}