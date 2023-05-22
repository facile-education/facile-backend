package com.weprode.nero.messaging.search;

public interface MessageBatchReindexer {

    public void reindex(long messageId, long companyId);

}