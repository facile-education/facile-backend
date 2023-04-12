package com.weprode.nero.document.utils;

import com.liferay.document.library.kernel.exception.FileExtensionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SupportedExtensions {

    private SupportedExtensions() {
        throw new IllegalStateException("Utility class");
    }

    protected static final List<String> CONVERT_EXTENSIONS = Arrays.asList("wav","wma","avi","m4v","wav","mpg","mpeg","mov");
    public static final List<String> PDF_EXTENSIONS = Collections.singletonList("pdf");
    public static final List<String> GEOGEBRA_EXTENSIONS = Collections.singletonList("ggb");
    public static final List<String> MINDMAP_EXTENSIONS = Collections.singletonList("mind");
    public static final List<String> SCRATCH_EXTENSIONS = Collections.singletonList("sb3");
    protected static final List<String> OFFICE_TEXT_EXTENSIONS = Arrays.asList("doc","odt","sxw","docx");
    protected static final List<String> OFFICE_SPREADSHEET_EXTENSIONS = Arrays.asList("csv","ods","sxc","tsv","xls","xlsx");
    protected static final List<String> OFFICE_PRESENTATION_EXTENSIONS = Arrays.asList("odp","ppt","sxi","pptx");
    protected static final List<String> IMAGE_EXTENSIONS = Arrays.asList("gif","jpeg","jpg","png");
    protected static final List<String> AUDIO_EXTENSIONS = Arrays.asList("mp3","wav","wma","ogg","ogv");
    protected static final List<String> VIDEO_EXTENSIONS = Arrays.asList("mp4","avi","m4v","mpg","mpeg","mov");

    public static final List<String> WISIWIG_EXTENSIONS = Collections.singletonList("html");
    protected static final List<String> OTHER_EXTENSIONS = Arrays.asList(/*"epub","html",*/"txt","json");

    protected static final List<String> OFFICE_EXTENSIONS = new ArrayList<>(
            concatStringLists(
                    OFFICE_TEXT_EXTENSIONS,
                    OFFICE_SPREADSHEET_EXTENSIONS,
                    OFFICE_PRESENTATION_EXTENSIONS
            )
    );

    protected static final List<String> SUPPORTED_EXTENSIONS = new ArrayList<>(
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

    public static String getTypeOfView (String extension) throws FileExtensionException {

        String trueExtension = extension.toLowerCase();

        if (IMAGE_EXTENSIONS.contains(trueExtension)) {
            return "Image";
        } else if (AUDIO_EXTENSIONS.contains(trueExtension)) {
            return "Audio";
        } else if (VIDEO_EXTENSIONS.contains(trueExtension)) {
            return "Video";
        } else if (PDF_EXTENSIONS.contains(trueExtension)) {
            return "PDF";
        } else if (OFFICE_EXTENSIONS.contains(trueExtension)) {
            return "Office";
        } else if (GEOGEBRA_EXTENSIONS.contains(trueExtension)) {
            return "Geogebra";
        } else if (MINDMAP_EXTENSIONS.contains(trueExtension)) {
            return "MindMap";
        } else if (SCRATCH_EXTENSIONS.contains(trueExtension)) {
            return "Scratch";
        } else if (WISIWIG_EXTENSIONS.contains(trueExtension)) {
            return "WISIWIG";
        } else if (OTHER_EXTENSIONS.contains(trueExtension)) {
            return "Other";
        }  else {
            throw new FileExtensionException("Unsupported file extension " + extension);
        }
    }

    @SafeVarargs
    private static List<String> concatStringLists (List<String>... listsToConcat) {
        List<String> newList = new ArrayList<>();

        for (List<String> list: listsToConcat) {
            newList.addAll(list);
        }

        return newList;
    }

}
