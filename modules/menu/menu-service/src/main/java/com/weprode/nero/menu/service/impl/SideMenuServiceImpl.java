package com.weprode.nero.menu.service.impl;

import org.json.JSONArray;

import org.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.menu.enums.MenuEntry;
import com.weprode.nero.menu.service.SideMenuLocalServiceUtil;
import com.weprode.nero.menu.service.SideMenuService;
import com.weprode.nero.menu.service.base.SideMenuServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=menu",
                "json.web.service.context.path=SideMenu"
        },
        service = SideMenuService.class
)
public class SideMenuServiceImpl extends SideMenuServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(SideMenuServiceImpl.class);

    @JSONWebService(value = "get-side-menu", method = "GET")
    public JSONObject getSideMenu() {
        JSONObject result = new JSONObject();

        logger.info("User fetching side menu.");

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        List<MenuEntry> userMenu = SideMenuLocalServiceUtil.getUserMenu(user);

        result.put(JSONConstants.MENU, getMenuAsJSON(userMenu));
        result.put(JSONConstants.EXPANDED, false);
        result.put(JSONConstants.SUCCESS, true);

        return result;
    }

    private JSONArray getMenuAsJSON(List<MenuEntry> menuEntries) {
        JSONArray menu = new JSONArray();

        for (MenuEntry menuEntry : menuEntries) {
            menu.put(getEntryAsJSON(menuEntry));
        }

        return menu;
    }

    private JSONObject getEntryAsJSON(MenuEntry menuEntry) {
        JSONObject entry = new JSONObject();

        entry.put(JSONConstants.ID, menuEntry.getId());
        entry.put(JSONConstants.ICON, menuEntry.getIcon());
        entry.put(JSONConstants.I18N_KEY, menuEntry.getKey());
        entry.put(JSONConstants.POSITION, menuEntry.getPosition());
        entry.put(JSONConstants.COMPONENT, menuEntry.getComponent());
        // TODO
        // entry.put(JSONConstants.NOTIFICATIONS);
        if (menuEntry.isLandingPage()) {
            entry.put(JSONConstants.IS_LANDING_PAGE, menuEntry.isLandingPage());
        }
        if (menuEntry.getEntries() != null) {
            entry.put(JSONConstants.MENU, getMenuAsJSON(menuEntry.getEntries()));
        }

        return entry;
    }
}