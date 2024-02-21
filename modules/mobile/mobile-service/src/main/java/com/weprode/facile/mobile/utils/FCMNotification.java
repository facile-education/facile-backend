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
import com.liferay.portal.kernel.util.PropsUtil;
import com.weprode.facile.commons.properties.NeroSystemProperties;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class FCMNotification {
    // Method to send Notifications from server to client end.

    private static final Log logger = LogFactoryUtil.getLog(FCMNotification.class);

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

        // Push JSON object
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

        try (OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())) {
            wr.write(message.toString());
            wr.flush();
        }
        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            logger.error("Error sending push to device " + deviceIdKey + " : responseCode = " + responseCode);
        }

        String inputLine;
        StringBuilder response = new StringBuilder();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }
        return new JSONObject(response.toString());
    }

}
