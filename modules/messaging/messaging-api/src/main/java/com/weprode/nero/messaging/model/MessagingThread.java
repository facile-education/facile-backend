package com.weprode.nero.messaging.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MessagingThread {
    private final long threadId;
    private final List<Message> messageList;

    public MessagingThread(long threadId, List<Message> messageList) {
        this.threadId = threadId;
        this.messageList = messageList;
    }

    public List<Message> getDisplayableMessages(boolean unreadOnly, long folderId) {
        List<Message> filteredList = new ArrayList<>();
        for (Message message : this.messageList) {
            if (folderId == -1 || message.getFolderId() == folderId) {
                if (!unreadOnly || message.getIsNew()) {
                    filteredList.add(message);
                }
            }
        }
        return filteredList;
    }

    public Message getLastMessage() {
        Comparator<Message> comparator = new Comparator<Message>() {
            @Override
            public int compare(Message message1, Message message2) {
                if (message1.getSendDate().before(message2.getSendDate())) {
                    return -1;
                } else if (message1.getSendDate().after(message2.getSendDate())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };

        return Collections.max(this.messageList, comparator);
    }

    // Getters
    public long getThreadId() {
        return threadId;
    }

    public List<Message> getMessageList() {
        return messageList;
    }
}
