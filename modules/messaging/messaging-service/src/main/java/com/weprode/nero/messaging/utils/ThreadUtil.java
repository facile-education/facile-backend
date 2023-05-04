package com.weprode.nero.messaging.utils;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.messaging.model.Message;
import com.weprode.nero.messaging.model.MessagingThread;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ThreadUtil {

    private ThreadUtil() {
        throw new IllegalStateException("Utility class");
    }
    private static final Log logger = LogFactoryUtil.getLog(MessagingUtil.class);

    /**
     * Format a ThreadList, but for each thread, only keep messages belonging to the given folder
     */
    public static JSONArray formatThreadList(List<MessagingThread> threadList, boolean unReadOnly, long folderId) {
        JSONArray jsonThreads = new JSONArray();

        for (MessagingThread thread : threadList) {
            try {
                jsonThreads.put(formatThread(thread, unReadOnly, folderId));
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        return jsonThreads;
    }

    public static JSONObject formatThread(MessagingThread thread, boolean unreadOnly, long folderId) throws Exception {
        JSONObject jsonThread = new JSONObject();
        DateFormat sdf = new SimpleDateFormat(MessagingUtil.messagingDateFormat);

        List<Message> displayableMessages = thread.getDisplayableMessages(unreadOnly, folderId);

        if (displayableMessages.size() > 0) {
            jsonThread.put("threadId", thread.getThreadId());

            // Main thread's message is the newest
            Message mainMessage = MessagingUtil.getLastMessageFromList(displayableMessages);
            jsonThread.put("mainMessageId", mainMessage.getMessageId());
            jsonThread.put("lastSendDate", sdf.format(mainMessage.getSendDate()));

            // Loop over messages
            JSONArray jsonMessages = new JSONArray();
            for (Message message : displayableMessages) {
                JSONObject jsonMessage = MessageUtil.convertMessageToJSON(message, false);
                jsonMessages.put(jsonMessage);
            }
            jsonThread.put("messages", jsonMessages);

            return jsonThread;
        } else {
            throw new Exception("No displayable messages for thread " + thread.getThreadId() + " to format");
        }
    }
}
