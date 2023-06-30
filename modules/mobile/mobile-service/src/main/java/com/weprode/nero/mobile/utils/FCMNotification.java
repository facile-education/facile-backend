package com.weprode.nero.mobile.utils;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.weprode.nero.commons.properties.NeroSystemProperties;
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
        notification.put("title", sanitizeContent(title, 70));
        if (!subtitle.isEmpty()) {
            notification.put("subtitle", sanitizeContent(subtitle, 70));
        }
        notification.put("body", sanitizeContent(body, 200));
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
