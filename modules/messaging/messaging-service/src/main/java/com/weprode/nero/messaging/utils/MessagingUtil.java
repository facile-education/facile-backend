package com.weprode.nero.messaging.utils;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.messaging.model.Message;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MessagingUtil {

    private MessagingUtil() {
        throw new IllegalStateException("Utility class");
    }
    private static final Log logger = LogFactoryUtil.getLog(MessagingUtil.class);

    public static final String messagingDateFormat = "yyyy/MM/dd HH:mm:ss SSS";
    
    public static List<Long> jsonArrayStringToList(String idArray) {
        List<Long> idList = new ArrayList<>();
        try {
            JSONArray fileIdArray = new JSONArray(idArray);

            for (int i=0 ; i<fileIdArray.length() ; ++i) {
                idList.add(fileIdArray.getLong(i));
            }
        } catch (Exception e) {
            logger.error(e);
        }

        return idList;
    }

    public static List<User> UserIdsStringToUserList (String idArray) {
        List<User> userList = new ArrayList<>();

        List<Long> userIds = jsonArrayStringToList(idArray);
        for (Long userId : userIds) {
            try {
                userList.add(UserLocalServiceUtil.getUser(userId));
            } catch (Exception e) {
                logger.debug(e);
            }
        }

        return userList;
    }

    public static Message getLastMessageFromList(List<Message> messageList) {
        Comparator<Message> comparator = (message1, message2) -> {
            if (message1.getSendDate().before(message2.getSendDate())) {
                return -1;
            } else if (message1.getSendDate().after(message2.getSendDate())){
                return 1;
            } else {
                return 0;
            }
        };

        return Collections.max(messageList, comparator);
    }
}
