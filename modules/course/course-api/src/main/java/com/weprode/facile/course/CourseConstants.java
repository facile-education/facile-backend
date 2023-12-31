package com.weprode.facile.course;

public class CourseConstants {

    private CourseConstants() {
        throw new IllegalStateException("Constants class");
    }

    public static final int SESSION_TYPE = 1;
    public static final int HOMEWORK_TYPE = 2;

    public static final int TYPE_TEXT = 1;
    public static final int TYPE_RECORD = 2;
    public static final int TYPE_LINK = 3;
    public static final int TYPE_VIDEO = 4;
    public static final int TYPE_FILE = 5;
    public static final int TYPE_H5P = 6;
    public static final String DEFAULT_TEXT_NAME = "Texte";
    public static final String DEFAULT_RECORD_NAME = "Enregistrement";
    public static final String DEFAULT_LINK_NAME = "Lien";
    public static final String DEFAULT_VIDEO_NAME = "Vidéo";
    public static final String DEFAULT_FILE_NAME = "Fichier";
    public static final String DEFAULT_H5P_NAME = "H5P";

    public static final String DEFAULT_SECTION_NAME = "Section";
    public static final String DEFAULT_SUBSECTION_NAME = "Sous-section";
    public static final String DEFAULT_SESSION_NAME = "Contenu de s\u00e9ance";
    public static final String DEFAULT_HOMEWORK_NAME = "Devoir";

    public static final int HOMEWORK_TYPE_SIMPLE = 1;
    public static final int HOMEWORK_TYPE_DOCTOSEND = 2;
    public static final int HOMEWORK_TYPE_DOCTOCOMPLETE = 3;

    public static final String DROP_FOLDER = "Dépôts";

}
