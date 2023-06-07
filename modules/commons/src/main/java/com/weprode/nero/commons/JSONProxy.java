package com.weprode.nero.commons;

import com.weprode.nero.commons.constants.JSONConstants;
import org.json.JSONObject;

public class JSONProxy {

    public static JSONObject getJSONReturnInErrorCase(String errorMessage){
        JSONObject errorReturn = new JSONObject();
        errorReturn.put(JSONConstants.ERROR, errorMessage);
        errorReturn.put(JSONConstants.SUCCESS, false);
        return errorReturn;
    }

}
