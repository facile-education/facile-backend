package com.weprode.nero.menu.enums;

import com.liferay.petra.string.StringPool;

import java.util.*;

public class MenuEntry {
    public static final MenuEntry DASHBOARD = new MenuEntry(0, "icon-home", "dashboard", true, 0, "Dashboard", ":dashboardId(\\d+)?");
    public static final MenuEntry GROUPS = new MenuEntry(1, "icon-collab-workspace", "groups", 1, "Groups");
    public static final MenuEntry CDT = new MenuEntry(4, "icon-school-life", "cdt", 2, "Course");
    public static final MenuEntry HORAIRES = new MenuEntry(5, "icon-calendar", "horaires", 3, "Horaires");
    public static final MenuEntry HHC = new MenuEntry(6, "icon-clock", "horaires-hors-cadre", 4, "NotUsualSlotsManager");
    public static final MenuEntry DOCUMENTS = new MenuEntry(2, "icon-folder", "documents", 5, "Documents", ":folderId(\\d+)?");
    public static final MenuEntry MESSAGING = new MenuEntry(3, "icon-langues", "messaging", 6, "Messaging", ":messageId(\\d+)?");

    public static final MenuEntry UNIVERSALIS = new MenuEntry(8, StringPool.BLANK, "universalis", 0, "ExternalResource");
    public static final MenuEntry UNIVERSALIS_JUNIOR = new MenuEntry(9, StringPool.BLANK, "universalis-junior", 1, "ExternalResource");
    public static final MenuEntry CORTEX = new MenuEntry(10, StringPool.BLANK, "cortex", 2, "ExternalResource");
    public static final MenuEntry DISCIPLINES_CO = new MenuEntry(11, StringPool.BLANK, "disciplines-co", 3, "ExternalResource");
    public static final MenuEntry H5P = new MenuEntry(12, StringPool.BLANK, "h5p", 4, "ExternalResource");
    public static final MenuEntry GRR = new MenuEntry(13, StringPool.BLANK, "grr", 5, "ExternalResource");

    public static final MenuEntry RESOURCES = new MenuEntry("icon-resource", "resources", 7, Arrays.asList(UNIVERSALIS, UNIVERSALIS_JUNIOR, CORTEX, DISCIPLINES_CO, H5P, GRR));

    public static final MenuEntry USER_ADMIN = new MenuEntry(14, StringPool.BLANK, "user-admin", 0, "UserManagement");
    public static final MenuEntry APPLICATION_ADMIN = new MenuEntry(15, StringPool.BLANK, "application-admin", 1, "ApplicationManager");
    public static final MenuEntry HORAIRES_ADMIN = new MenuEntry(16, StringPool.BLANK, "horaires-admin", 2, "ScheduleManager");
    public static final MenuEntry ACCESS_ADMIN = new MenuEntry(17, StringPool.BLANK, "access-admin", 3, "AccessManager");
    public static final MenuEntry STATISTICS = new MenuEntry(18, StringPool.BLANK, "statistics", 4, "Statistics");
    public static final MenuEntry MAINTENANCE = new MenuEntry(19, StringPool.BLANK, "maintenance", 5, "Maintenance");

    public static final MenuEntry ADMINISTRATION = new MenuEntry("icon-admin", "administration", 8, Arrays.asList(USER_ADMIN, APPLICATION_ADMIN, HORAIRES_ADMIN, ACCESS_ADMIN, STATISTICS, MAINTENANCE));

    final long id;
    final int position;
    final String icon;
    final String key;
    boolean isLandingPage = false;
    List<MenuEntry> entries;
    String param;
    String component;

    MenuEntry(long id, String icon, String key, int position, String component) {
        this.id = id;
        this.position = position;
        this.icon = icon;
        this.key = key;
        this.component = component;
    }

    MenuEntry(long id, String icon, String key, int position, String component, String param) {
        this.id = id;
        this.position = position;
        this.icon = icon;
        this.key = key;
        this.component = component;
        this.param = param;
    }

    MenuEntry(long id, String icon, String key, boolean isLandingPage, int position, String component, String param) {
        this.id = id;
        this.position = position;
        this.icon = icon;
        this.key = key;
        this.isLandingPage = isLandingPage;
        this.component = component;
        this.param = param;
    }

    MenuEntry(String icon, String key, int position, List<MenuEntry> entries) {
        this.id = -1;
        this.position = position;
        this.icon = icon;
        this.key = key;
        this.entries = entries;
    }

    public static List<MenuEntry> getFullMenu() {
        List<MenuEntry> menu = new ArrayList<>();

        menu.add(DASHBOARD);
        menu.add(GROUPS);
        menu.add(DOCUMENTS);
        menu.add(MESSAGING);
        menu.add(CDT);
        menu.add(HORAIRES);
        menu.add(HHC);
        menu.add(RESOURCES.clone());
        menu.add(ADMINISTRATION.clone());

        return menu;
    }

    public long getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public String getIcon() {
        return icon;
    }

    public String getKey() {
        return key;
    }

    public boolean isLandingPage() {
        return isLandingPage;
    }

    public String getParam() {
        return param;
    }

    public List<MenuEntry> getEntries() {
        return this.entries;
    }

    public String getComponent() {
        return component;
    }

    public void setEntries(List<MenuEntry> entries) {
        this.entries = entries;
    }

    protected MenuEntry clone() {
        return new MenuEntry(this.icon, this.key, this.position, this.entries);
    }
}
