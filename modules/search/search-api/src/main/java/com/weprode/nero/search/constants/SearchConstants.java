package com.weprode.nero.search.constants;

public class SearchConstants {

    private SearchConstants() {
        throw new IllegalStateException("Constants class");
    }

    public static final String HREF_ACTU_ENTRY_PARAM_NAME = "_33_entryId";
    public static final int NB_QUERIES = 3;

    public static final int TYPE_NEWS = 1;
    public static final int TYPE_NEWS_FILE = 2;
    public static final int TYPE_MESSAGE = 3;
    public static final int TYPE_MESSAGE_FILE = 4;
    public static final int TYPE_FOLDER = 5;
    public static final int TYPE_FILE = 6;
    public static final int TYPE_COLLABORATIVE_FILE = 7;
    public static final int TYPE_COLLABORATIVE_FOLDER = 8;
    public static final int TYPE_PROGRESSION = 9;
    public static final int TYPE_PROGRESSION_COURSE = 10;
    public static final int TYPE_PROGRESSION_HOMEWORK = 11;
    public static final int TYPE_PROGRESSION_FILE = 12;
    public static final int TYPE_EVENT = 18;

    // Future
    public static final int TYPE_USER = 13;
    public static final int TYPE_SESSION = 14;
    public static final int TYPE_SESSION_FILE = 15;
    public static final int TYPE_HOMEWORK = 16;
    public static final int TYPE_HOMEWORK_FILE = 17;
}
