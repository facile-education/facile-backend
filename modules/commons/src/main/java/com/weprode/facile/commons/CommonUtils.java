package com.weprode.facile.commons;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class CommonUtils {

    private CommonUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final Log logger = LogFactoryUtil.getLog(CommonUtils.class);

    public static boolean isValidURI(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException e) {
            logger.error("Malformed url : " + url);
            return false;
        } catch (URISyntaxException e) {
            logger.error("URI syntax issue for url : " + url);
            return false;
        }
    }
}
