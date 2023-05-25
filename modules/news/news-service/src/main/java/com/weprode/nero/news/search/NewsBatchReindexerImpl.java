package com.weprode.nero.news.search;

import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.indexer.IndexerDocumentBuilder;
import com.liferay.portal.search.indexer.IndexerWriter;
import com.weprode.nero.news.model.News;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = NewsBatchReindexer.class)
public class NewsBatchReindexerImpl implements NewsBatchReindexer {

    @Override
    public void reindex(long newsId, long companyId) {
        BatchIndexingActionable batchIndexingActionable =
                indexerWriter.getBatchIndexingActionable();

        logger.debug("Re-indexing news " + newsId);
        batchIndexingActionable.setAddCriteriaMethod(dynamicQuery -> {
            Property newsIdPropery = PropertyFactoryUtil.forName(
                    "newsId");

            dynamicQuery.add(newsIdPropery.eq(newsId));
        });

        batchIndexingActionable.setCompanyId(companyId);

        batchIndexingActionable.setPerformActionMethod((News news) -> {
            Document document = indexerDocumentBuilder.getDocument(news);

            batchIndexingActionable.addDocuments(document);
        });

        batchIndexingActionable.performActions();

    }

    @Reference(target = "(indexer.class.name=com.weprode.nero.news.model.News)")
    protected IndexerDocumentBuilder indexerDocumentBuilder;

    @Reference(target = "(indexer.class.name=com.weprode.nero.news.model.News)")
    protected IndexerWriter<News> indexerWriter;

    private static final Log logger = LogFactoryUtil.getLog(NewsBatchReindexerImpl.class);
}