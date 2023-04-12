package com.weprode.nero.document.constants;

public class MindMapConstants {

    private MindMapConstants() {
        throw new IllegalStateException("Constants class");
    }

    public static final String URL_MINDMAP = "/resources/mindmap/html";

    public static final String MINDMAP_NEW_FILE_START = "<map name=\"";
    public static final String MINDMAP_NEW_FILE_END = "\" version=\"tango\"><topic central=\"true\" id=\"0\"/></map>";

}
