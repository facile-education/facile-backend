package com.weprode.nero.menu.enums;

import com.liferay.petra.string.StringPool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum MenuEntry {
    DASHBOARD("icon-nav-tableau-de-bord.svg", "dashboard", true, 0, "Dashboard"),
    GROUPS("icon-nav-espace-collab.svg", "groups", 1, "Groups"),
    DOCUMENTS("icon-nav-documents.svg", "documents", 2, "Documents"),

    // TODO vue component
    CDT(StringPool.BLANK, "cdt", 0, "Horaires"),
    HORAIRES(StringPool.BLANK, "horaires", 1, "Horaires"),
    HHC(StringPool.BLANK, "horaires-hors-cadre", 2, "NotUsualSlotsManager"),
    PROGRESSION(StringPool.BLANK, "progression", 3, "Progression"),

    MESSAGING(StringPool.BLANK, "messaging", 0, "Messaging"),
    // TODO vue component
    CONTACTS(StringPool.BLANK, "contacts", 1, "Messaging"),

    UNIVERSALIS(StringPool.BLANK, "universalis", 0, "ExternalResource"),
    UNIVERSALIS_JUNIOR(StringPool.BLANK, "universalis-junior", 1, "ExternalResource"),
    CORTEX(StringPool.BLANK, "cortex", 2, "ExternalResource"),
    DISCIPLINES_CO(StringPool.BLANK, "disciplines-co", 3, "ExternalResource"),
    H5P(StringPool.BLANK, "h5p", 4, "ExternalResource"),
    GRR(StringPool.BLANK, "grr", 5, "ExternalResource"),

    USER_ADMIN(StringPool.BLANK, "user-admin", 0, "UserManagement"),
    APPLICATION_ADMIN(StringPool.BLANK, "application-admin", 1, "ApplicationManager"),
    // TODO vue component
    HORAIRES_ADMIN(StringPool.BLANK, "horaires-admin", 2, "ApplicationManager"),
    DASHBOARD_ADMIN(StringPool.BLANK, "dashboard-admin", 3, "DashboardManager"),
    STATISTICS(StringPool.BLANK, "statistics", 4, "Statistics"),
    MAINTENANCE(StringPool.BLANK, "maintenance", 5, "Maintenance"),

    SCHOOL_LIFE("icon-nav-vie-scolaire.svg", "school-life", 3, Arrays.asList(CDT, HORAIRES, HHC, PROGRESSION)),
    COMMUNICATION("icon-nav-communication.svg", "communication", 4, Arrays.asList(MESSAGING, CONTACTS)),
    RESOURCES("icon-nav-ressources.svg", "resources", 5, Arrays.asList(UNIVERSALIS, UNIVERSALIS_JUNIOR, CORTEX, DISCIPLINES_CO, H5P, GRR)),
    ADMINISTRATION("icon-nav-admin.svg", "administration", 6, Arrays.asList(USER_ADMIN, APPLICATION_ADMIN, HORAIRES_ADMIN, DASHBOARD_ADMIN, STATISTICS, MAINTENANCE));

    final int position;
    final String icon;
    final String key;
    boolean isLandingPage = false;
    List<MenuEntry> entries;
    String component;

    MenuEntry(String icon, String key, int position, String component) {
        this.position = position;
        this.icon = icon;
        this.key = key;
        this.component = component;
    }

    MenuEntry(String icon, String key, boolean isLandingPage, int position, String component) {
        this.position = position;
        this.icon = icon;
        this.key = key;
        this.isLandingPage = isLandingPage;
        this.component = component;
    }

    MenuEntry(String icon, String key, int position, List<MenuEntry> entries) {
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
        menu.add(SCHOOL_LIFE);
        menu.add(COMMUNICATION);
        menu.add(RESOURCES);
        menu.add(ADMINISTRATION);

        return menu;
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

    public List<MenuEntry> getEntries() {
        return entries;
    }

    public String getComponent() {
        return component;
    }
}
