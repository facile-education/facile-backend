package com.weprode.nero.news.search;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;
import com.weprode.nero.news.model.News;
import com.weprode.nero.news.service.NewsLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        property = "indexer.class.name=com.weprode.nero.news.model.News",
        service = ModelIndexerWriterContributor.class
)
public class NewsModelIndexerWriterContributor
        implements ModelIndexerWriterContributor<News> {

    @Override
    public void customize(
            BatchIndexingActionable batchIndexingActionable,
            ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

        batchIndexingActionable.setPerformActionMethod((News news) -> {
            Document document = modelIndexerWriterDocumentHelper.getDocument(
                    news);

            batchIndexingActionable.addDocuments(document);
        });
    }

    @Override
    public BatchIndexingActionable getBatchIndexingActionable() {
        return dynamicQueryBatchIndexingActionableFactory.getBatchIndexingActionable(
                newsLocalService.getIndexableActionableDynamicQuery());
    }

    @Override
    public long getCompanyId(News news) {
        return news.getCompanyId();
    }

//    @Override
//    public void modelIndexed(News news) {
//        newsEntryBatchReindexer.reindex(
//                news.getNewsId(), news.getCompanyId());
//    }

    @Reference
    protected DynamicQueryBatchIndexingActionableFactory
            dynamicQueryBatchIndexingActionableFactory;

//    @Reference
//    protected NewsBatchReindexer newsEntryBatchReindexer;

    @Reference
    protected NewsLocalService newsLocalService;

}
