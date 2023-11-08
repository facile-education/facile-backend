package com.weprode.facile.document.constants;

import com.liferay.portal.kernel.security.permission.ActionKeys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermissionConstants {

    private PermissionConstants() {
        throw new IllegalStateException("Constants class");
    }

    // Better use class com.liferay.portal.security.permission.ActionKeys
    public static final String ADD_OBJECT = "ADD_OBJECT";

    public static final List<String> FOLDER_PERMISSIONS = Arrays.asList(ActionKeys.VIEW,ADD_OBJECT, ActionKeys.UPDATE, ActionKeys.DELETE, ActionKeys.PERMISSIONS);

}
