package com.weprode.nero.document.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DocumentConstants {

    private DocumentConstants() {
        throw new IllegalStateException("Constants class");
    }

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

    public static final List<String> CONVERT_EXTENSIONS = Arrays.asList("wav","wma","avi","m4v","wav","mpg","mpeg","mov");
    public static final List<String> PDF_EXTENSIONS = Collections.singletonList("pdf");
    public static final List<String> GEOGEBRA_EXTENSIONS = Collections.singletonList("ggb");
    public static final List<String> MINDMAP_EXTENSIONS = Collections.singletonList("mind");
    public static final List<String> SCRATCH_EXTENSIONS = Collections.singletonList("sb3");
    public static final List<String> OFFICE_TEXT_EXTENSIONS = Arrays.asList("doc","odt","sxw","docx");
    public static final List<String> OFFICE_SPREADSHEET_EXTENSIONS = Arrays.asList("csv","ods","sxc","tsv","xls","xlsx");
    public static final List<String> OFFICE_PRESENTATION_EXTENSIONS = Arrays.asList("odp","ppt","sxi","pptx");
    public static final List<String> IMAGE_EXTENSIONS = Arrays.asList("gif","jpeg","jpg","png");
    public static final String[] IMAGE_MIME_TYPES = {"image/gif","image/jpeg","image/png"}; // Mime types corresponding to images supported extensions
    public static final List<String> AUDIO_EXTENSIONS = Arrays.asList("mp3","wav","wma","ogg","ogv");
    public static final List<String> VIDEO_EXTENSIONS = Arrays.asList("mp4","avi","m4v","mpg","mpeg","mov");

    public static final List<String> WISIWIG_EXTENSIONS = Collections.singletonList("html");
    public static final List<String> OTHER_EXTENSIONS = Arrays.asList(/*"epub","html",*/"txt","json");

    public static final List<String> OFFICE_EXTENSIONS = new ArrayList<>(
            concatStringLists(
                    OFFICE_TEXT_EXTENSIONS,
                    OFFICE_SPREADSHEET_EXTENSIONS,
                    OFFICE_PRESENTATION_EXTENSIONS
            )
    );

    public static final List<String> SUPPORTED_EXTENSIONS = new ArrayList<>(
            concatStringLists(
                    CONVERT_EXTENSIONS,
                    PDF_EXTENSIONS,
                    GEOGEBRA_EXTENSIONS,
                    MINDMAP_EXTENSIONS,
                    SCRATCH_EXTENSIONS,
                    OFFICE_EXTENSIONS,
                    IMAGE_EXTENSIONS,
                    AUDIO_EXTENSIONS,
                    VIDEO_EXTENSIONS,
                    WISIWIG_EXTENSIONS,
                    OTHER_EXTENSIONS
            )
    );

    @SafeVarargs
    private static List<String> concatStringLists (List<String>... listsToConcat) {
        List<String> newList = new ArrayList<>();

        for (List<String> list: listsToConcat) {
            newList.addAll(list);
        }

        return newList;
    }

}
