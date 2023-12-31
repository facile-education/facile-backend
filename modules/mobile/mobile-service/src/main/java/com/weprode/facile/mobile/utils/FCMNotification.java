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

package com.weprode.facile.mobile.utils;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.weprode.facile.commons.properties.NeroSystemProperties;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class FCMNotification {
    // Method to send Notifications from server to client end.

    private static final Log logger = LogFactoryUtil.getLog(FCMNotification.class);

    public static JSONObject pushFCMNotification(String deviceIdKey, String title, String body) throws Exception {
        return pushFCMNotification(deviceIdKey, title, "", body, "");
    }

    public static JSONObject pushFCMNotification(String deviceIdKey, String title, String subtitle, String body, String redirectUrl) throws Exception {

        String authKey = PropsUtil.get(NeroSystemProperties.MOBILE_API_KEY);
        String fcmUrl = PropsUtil.get(NeroSystemProperties.MOBILE_API_URL);

        URL url = new URL(fcmUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "key=" + authKey);
        conn.setRequestProperty("Content-Type", "application/json");

        // Push JSON oject
        JSONObject message = new JSONObject();
        message.put("to", deviceIdKey.trim());
        JSONObject notification = new JSONObject();
        notification.put("title", title);
        if (!subtitle.isEmpty()) {
            notification.put("subtitle", subtitle);
        }
        notification.put("body", body);
        message.put("notification", notification);
        JSONObject data = new JSONObject();
        data.put("service", redirectUrl);
        message.put("data", data);

        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(message.toString());
        wr.flush();
        wr.close();

        int responseCode = conn.getResponseCode();
        logger.info("Sending push : responseCode = " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return new JSONObject(response.toString());
    }

    private static String sanitizeContent(String content, int maxSize) {
        String sanitized = new String(HtmlUtil.stripHtml(content).getBytes(), StandardCharsets.UTF_8).trim();
        if (sanitized.length() > maxSize) {
            sanitized = sanitized.substring(0, maxSize) + "...";
        }
        return sanitized;
    }
}
