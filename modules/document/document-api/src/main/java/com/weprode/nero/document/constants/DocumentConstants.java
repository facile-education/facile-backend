package com.weprode.nero.document.constants;

public class DocumentConstants {

    private DocumentConstants() {
        throw new IllegalStateException("Constants class");
    }

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

    public static final int NB_RENAMED_VERSIONS = 500;

    public static final String SENDING_BOX_FOLDER_NAME = "._SENDING_BOX_";
    public static final String DROP_BOX_FOLDER_NAME = "._CASIER_";
    public static final String IM_BOX_FOLDER_NAME = "._IM_BOX_";
    public static final String CDT_EVENT_FOLDER_NAME = "._CDT_EVENTS_";
    public static final String SCHOOL_BAG_FOLDER_NAME = "Mon cartable";
    public static final String GROUP_FOLDER_NAME = "Atelier du groupe";
    public static final String PROGRESSION_FOLDER_NAME = "._PROGRESSION_";
    public static final String NEWS_FOLDER_NAME = "._NEWS_";

    public static final String TMP_IMG_FOLDER_NAME = "._TEMPORARY_IMG_";
    public static final String ACTU_IMG_FOLDER_NAME = "._ACTUALITES_IMG_";
    public static final String MENU_IMG_FOLDER_NAME = "._MENU_IMG_";

    public static final String TMP_FILE_FOLDER_NAME = "._TMP_FILE_FOLDER_";
    public static final String FEEDBACK_FOLDER_NAME = "._FEEDBACK_";
    public static final String HOMEWORK_FOLDER_NAME = "Devoirs rendus";

    public static final int PRIVATE = 1;
    public static final int DROP_BOX = 2;
    public static final int SENDING_BOX = 3;
    public static final int TRASH = 4;
    public static final int COLLABORATIVE = 5;

    public static final int MODE_NORMAL = 0;
    public static final int MODE_RENAME = 1;
    public static final int MODE_REPLACE = 2;
    public static final int MODE_MERGE = 3;

}
