package com.weprode.nero.menu.enums;

import com.liferay.petra.string.StringPool;

import java.util.*;

public class MenuEntry {
    public static final MenuEntry DASHBOARD = new MenuEntry(0, "icon-nav-tableau-de-bord.svg", "dashboard", true, 0, "Dashboard");
    public static final MenuEntry GROUPS = new MenuEntry(1, "icon-nav-espace-collab.svg", "groups", 1, "Groups");
    public static final MenuEntry DOCUMENTS = new MenuEntry(2, "icon-nav-documents.svg", "documents", 2, "Documents");

    // TODO vue component
    public static final MenuEntry CDT = new MenuEntry(3, StringPool.BLANK, "cdt", 0, "Horaires");
    public static final MenuEntry HORAIRES = new MenuEntry(4, StringPool.BLANK, "horaires", 1, "Horaires");
    public static final MenuEntry HHC = new MenuEntry(5, StringPool.BLANK, "horaires-hors-cadre", 2, "NotUsualSlotsManager");
    public static final MenuEntry PROGRESSION = new MenuEntry(6, StringPool.BLANK, "progression", 3, "Progression");

    public static final MenuEntry MESSAGING = new MenuEntry(7, StringPool.BLANK, "messaging", 0, "Messaging");
    // TODO vue component
    public static final MenuEntry CONTACTS = new MenuEntry(8, StringPool.BLANK, "contacts", 1, "Messaging");

    public static final MenuEntry UNIVERSALIS = new MenuEntry(9, StringPool.BLANK, "universalis", 0, "ExternalResource");
    public static final MenuEntry UNIVERSALIS_JUNIOR = new MenuEntry(10, StringPool.BLANK, "universalis-junior", 1, "ExternalResource");
    public static final MenuEntry CORTEX = new MenuEntry(11, StringPool.BLANK, "cortex", 2, "ExternalResource");
    public static final MenuEntry DISCIPLINES_CO = new MenuEntry(12, StringPool.BLANK, "disciplines-co", 3, "ExternalResource");
    public static final MenuEntry H5P = new MenuEntry(13, StringPool.BLANK, "h5p", 4, "ExternalResource");
    public static final MenuEntry GRR = new MenuEntry(14, StringPool.BLANK, "grr", 5, "ExternalResource");

    public static final MenuEntry USER_ADMIN = new MenuEntry(15, StringPool.BLANK, "user-admin", 0, "UserManagement");
    public static final MenuEntry APPLICATION_ADMIN = new MenuEntry(16, StringPool.BLANK, "application-admin", 1, "ApplicationManager");
    // TODO vue component
    public static final MenuEntry HORAIRES_ADMIN = new MenuEntry(17, StringPool.BLANK, "horaires-admin", 2, "ApplicationManager");
    public static final MenuEntry DASHBOARD_ADMIN = new MenuEntry(18, StringPool.BLANK, "dashboard-admin", 3, "DashboardManager");
    public static final MenuEntry STATISTICS = new MenuEntry(19, StringPool.BLANK, "statistics", 4, "Statistics");
    public static final MenuEntry MAINTENANCE = new MenuEntry(20, StringPool.BLANK, "maintenance", 5, "Maintenance");

    public static final MenuEntry SCHOOL_LIFE = new MenuEntry("icon-nav-vie-scolaire.svg", "school-life", 3, Arrays.asList(CDT, HORAIRES, HHC, PROGRESSION));
    public static final MenuEntry COMMUNICATION = new MenuEntry("icon-nav-communication.svg", "communication", 4, Arrays.asList(MESSAGING, CONTACTS));
    public static final MenuEntry RESOURCES = new MenuEntry("icon-nav-ressources.svg", "resources", 5, Arrays.asList(UNIVERSALIS, UNIVERSALIS_JUNIOR, CORTEX, DISCIPLINES_CO, H5P, GRR));
    public static final MenuEntry ADMINISTRATION = new MenuEntry("icon-nav-admin.svg", "administration", 6, Arrays.asList(USER_ADMIN, APPLICATION_ADMIN, HORAIRES_ADMIN, DASHBOARD_ADMIN, STATISTICS, MAINTENANCE));

    final long id;
    final int position;
    final String icon;
    final String key;
    boolean isLandingPage = false;
    List<MenuEntry> entries;
    String component;

    MenuEntry(long id, String icon, String key, int position, String component) {
        this.id = id;
        this.position = position;
        this.icon = icon;
        this.key = key;
        this.component = component;
    }

    MenuEntry(long id, String icon, String key, boolean isLandingPage, int position, String component) {
        this.id = id;
        this.position = position;
        this.icon = icon;
        this.key = key;
        this.isLandingPage = isLandingPage;
        this.component = component;
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
        menu.add(SCHOOL_LIFE.clone());
        menu.add(COMMUNICATION.clone());
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
