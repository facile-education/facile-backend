/**
 * Copyright (c) 2022-present - Weprode
 * This file is part of FACILE ENT Project.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License (LGPL) as
 * published by the Free Software Foundation (version 2.1 of the License).
 * See LICENCE.txt file
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */

package com.weprode.facile.document.utils;

import com.liferay.document.library.kernel.exception.FileExtensionException;
import com.weprode.facile.document.constants.DocumentConstants;

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
