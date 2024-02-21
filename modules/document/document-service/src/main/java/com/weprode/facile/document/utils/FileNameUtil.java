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

import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.util.Validator;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class FileNameUtil {

    private FileNameUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String[] splitFileName(String fname) {
        String[] result = new String[2];

        int dotIndex = fname.lastIndexOf(".");

        if (dotIndex != -1) {
            result[0] = fname.substring(0, dotIndex);
            result[1] = fname.substring(dotIndex+1);
        } else {
            result[0] = fname;
            result[1] = "___";
        }

        return result;
    }

    // Validator for name/title
    static char[] INVALID_CHARACTERS = new char[] {
            CharPool.AMPERSAND, CharPool.APOSTROPHE, CharPool.AT,
            CharPool.BACK_SLASH, CharPool.CLOSE_BRACKET, CharPool.CLOSE_CURLY_BRACE,
            CharPool.COLON, CharPool.COMMA, CharPool.EQUAL, CharPool.GREATER_THAN,
            CharPool.FORWARD_SLASH, CharPool.LESS_THAN, CharPool.NEW_LINE,
            CharPool.OPEN_BRACKET, CharPool.OPEN_CURLY_BRACE, CharPool.PERCENT,
            CharPool.PIPE, CharPool.PLUS, CharPool.POUND, CharPool.QUESTION,
            CharPool.QUOTE, CharPool.RETURN, CharPool.SEMICOLON, CharPool.SLASH,
            CharPool.STAR, CharPool.TILDE, '\u00b0' // This last one is the degree sign
    };

    public static String replaceAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    public static String getValidName(String str) {
        // valid le nom et remplace les caractere interdits par des '_' au cas ou
        StringBuilder result = new StringBuilder();

        if (Validator.isNull(str)) {
            return "objet";
        }
        else {
            str = str.trim();
            char[] wordCharArray = str.toCharArray();

            boolean invalidFlag;
            boolean firstChar = true;
            for (char c : wordCharArray) {
                invalidFlag = false;
                for (char invalidChar : INVALID_CHARACTERS) {
                    if (c == invalidChar) {
                        invalidFlag = true;
                        break;
                    }
                }

                result.append(firstChar && invalidFlag ? "" : (invalidFlag ? "_" : c));
                firstChar = firstChar && invalidFlag;
            }
        }

        if(result.length() == 0){
            result = new StringBuilder("objet");
        }

        return result.toString();
    }

    public static String getValidName(String str, boolean replaceSpaces, boolean replaceAccent){
        str = getValidName(str);

        if(replaceSpaces){
            str = replaceAccent(str.trim().replaceAll("\\s+", "_"));
        }
        if(replaceAccent){
            str = replaceAccent(str);
        }
        // Check that the file name does not end with '_' characters
        while (str.endsWith("_")) {
            str = str.substring(0, str.length() - 1);
        }

        return str;
    }

}
