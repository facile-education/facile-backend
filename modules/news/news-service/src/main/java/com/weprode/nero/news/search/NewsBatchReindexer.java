package com.weprode.nero.news.search;

public interface NewsBatchReindexer {

    public void reindex(long newsId, long companyId);

}