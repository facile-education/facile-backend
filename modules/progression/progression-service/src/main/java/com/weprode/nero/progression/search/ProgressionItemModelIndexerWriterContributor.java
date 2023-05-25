package com.weprode.nero.progression.search;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;
import com.weprode.nero.progression.model.ProgressionItem;
import com.weprode.nero.progression.service.ProgressionItemLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        property = "indexer.class.name=com.weprode.nero.progression.model.ProgressionItem",
        service = ModelIndexerWriterContributor.class
)
public class ProgressionItemModelIndexerWriterContributor
        implements ModelIndexerWriterContributor<ProgressionItem> {

    @Override
    public void customize(
            BatchIndexingActionable batchIndexingActionable,
            ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

        batchIndexingActionable.setPerformActionMethod((ProgressionItem progressionItem) -> {
            Document document = modelIndexerWriterDocumentHelper.getDocument(
                    progressionItem);

            batchIndexingActionable.addDocuments(document);
        });
    }

    @Override
    public BatchIndexingActionable getBatchIndexingActionable() {
        return dynamicQueryBatchIndexingActionableFactory.getBatchIndexingActionable(
                progressionItemLocalService.getIndexableActionableDynamicQuery());
    }

    @Override
    public long getCompanyId(ProgressionItem progressionItem) {
        return progressionItem.getCompanyId();
    }

//    @Override
//    public void modelIndexed(ProgressionItem progressionItem) {
//        progressionItemEntryBatchReindexer.reindex(
//                progressionItem.getProgressionItemId(), progressionItem.getCompanyId());
//    }

    @Reference
    protected DynamicQueryBatchIndexingActionableFactory
            dynamicQueryBatchIndexingActionableFactory;

//    @Reference
//    protected ProgressionItemBatchReindexer progressionItemEntryBatchReindexer;

    @Reference
    protected ProgressionItemLocalService progressionItemLocalService;

}
