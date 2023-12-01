package com.weprode.facile.messaging.constants;

public class MessagingConstants {

    private MessagingConstants() {
        throw new IllegalStateException("Constants class");
    }

    // Folders
    public static final int INBOX_FOLDER_TYPE = 1;
    public static final int DRAFT_FOLDER_TYPE = 2;
    public static final int SENT_FOLDER_TYPE = 3;
    public static final int TRASH_FOLDER_TYPE = 4;
    public static final int PERSONAL_FOLDER_TYPE = 5;

    // Types of messages
    public static final int TYPE_MANUAL = 0;
    public static final int TYPE_SUPPORT = 1;
    public static final int TYPE_HHC = 2;
    public static final int TYPE_REPORT = 3;
    public static final int TYPE_AUTO_REPLY = 4;
    public static final int TYPE_OTHER = 5;

    public static final String ATTACHED_FILES_FOLDER_PREFIX = "PJ du message ";
}
