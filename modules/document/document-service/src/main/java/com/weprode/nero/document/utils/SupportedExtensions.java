package com.weprode.nero.document.utils;

import com.liferay.document.library.kernel.exception.FileExtensionException;
import com.weprode.nero.document.constants.DocumentConstants;

public class SupportedExtensions {

    private SupportedExtensions() {
        throw new IllegalStateException("Utility class");
    }



    public static String getTypeOfView (String extension) throws FileExtensionException {

        String trueExtension = extension.toLowerCase();

        if (DocumentConstants.IMAGE_EXTENSIONS.contains(trueExtension)) {
            return "Image";
        } else if (DocumentConstants.AUDIO_EXTENSIONS.contains(trueExtension)) {
            return "Audio";
        } else if (DocumentConstants.VIDEO_EXTENSIONS.contains(trueExtension)) {
            return "Video";
        } else if (DocumentConstants.PDF_EXTENSIONS.contains(trueExtension)) {
            return "PDF";
        } else if (DocumentConstants.OFFICE_EXTENSIONS.contains(trueExtension)) {
            return "Office";
        } else if (DocumentConstants.GEOGEBRA_EXTENSIONS.contains(trueExtension)) {
            return "Geogebra";
        } else if (DocumentConstants.MINDMAP_EXTENSIONS.contains(trueExtension)) {
            return "MindMap";
        } else if (DocumentConstants.SCRATCH_EXTENSIONS.contains(trueExtension)) {
            return "Scratch";
        } else if (DocumentConstants.WISIWIG_EXTENSIONS.contains(trueExtension)) {
            return "WISIWIG";
        } else if (DocumentConstants.OTHER_EXTENSIONS.contains(trueExtension)) {
            return "Other";
        }  else {
            throw new FileExtensionException("Unsupported file extension " + extension);
        }
    }

}
