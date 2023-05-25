package com.weprode.nero.progression.search;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;
import com.weprode.nero.progression.model.Progression;
import com.weprode.nero.progression.service.ProgressionLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        property = "indexer.class.name=com.weprode.nero.progression.model.Progression",
        service = ModelIndexerWriterContributor.class
)
public class ProgressionModelIndexerWriterContributor
        implements ModelIndexerWriterContributor<Progression> {

    @Override
    public void customize(
            BatchIndexingActionable batchIndexingActionable,
            ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

        batchIndexingActionable.setPerformActionMethod((Progression progression) -> {
            Document document = modelIndexerWriterDocumentHelper.getDocument(
                    progression);

            batchIndexingActionable.addDocuments(document);
        });
    }

    @Override
    public BatchIndexingActionable getBatchIndexingActionable() {
        return dynamicQueryBatchIndexingActionableFactory.getBatchIndexingActionable(
                progressionLocalService.getIndexableActionableDynamicQuery());
    }

    @Override
    public long getCompanyId(Progression progression) {
        return progression.getCompanyId();
    }

//    @Override
//    public void modelIndexed(Progression progression) {
//        progressionEntryBatchReindexer.reindex(
//                progression.getProgressionId(), progression.getCompanyId());
//    }

    @Reference
    protected DynamicQueryBatchIndexingActionableFactory
            dynamicQueryBatchIndexingActionableFactory;

//    @Reference
//    protected ProgressionBatchReindexer progressionEntryBatchReindexer;

    @Reference
    protected ProgressionLocalService progressionLocalService;

}
