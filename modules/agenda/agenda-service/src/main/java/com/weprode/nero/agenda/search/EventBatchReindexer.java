package com.weprode.nero.agenda.search;

public interface EventBatchReindexer {

    public void reindex(long eventId, long companyId);

}