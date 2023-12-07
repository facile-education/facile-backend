package com.weprode.facile.commons;

import com.liferay.portal.kernel.model.User;
import org.apache.logging.log4j.ThreadContext;

public class FacileLogger {

    public static void registerUser(User user) {
        ThreadContext.clearAll();
        ThreadContext.put("userId", "" + user.getFullName());
    }
}
