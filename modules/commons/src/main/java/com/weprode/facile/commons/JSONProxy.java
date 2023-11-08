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

package com.weprode.facile.commons;

import com.weprode.facile.commons.constants.JSONConstants;
import org.json.JSONObject;

public class JSONProxy {

    public static JSONObject getJSONReturnInErrorCase(String errorMessage){
        JSONObject errorReturn = new JSONObject();
        errorReturn.put(JSONConstants.ERROR, errorMessage);
        errorReturn.put(JSONConstants.SUCCESS, false);
        return errorReturn;
    }

}
