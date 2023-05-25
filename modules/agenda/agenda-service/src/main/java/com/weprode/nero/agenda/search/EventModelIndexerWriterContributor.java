package com.weprode.nero.agenda.search;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;
import com.weprode.nero.agenda.model.Event;
import com.weprode.nero.agenda.service.EventLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        property = "indexer.class.name=com.weprode.nero.agenda.model.Event",
        service = ModelIndexerWriterContributor.class
)
public class EventModelIndexerWriterContributor
        implements ModelIndexerWriterContributor<Event> {

    @Override
    public void customize(
            BatchIndexingActionable batchIndexingActionable,
            ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

        batchIndexingActionable.setPerformActionMethod((Event event) -> {
            Document document = modelIndexerWriterDocumentHelper.getDocument(
                    event);

            batchIndexingActionable.addDocuments(document);
        });
    }

    @Override
    public BatchIndexingActionable getBatchIndexingActionable() {
        return dynamicQueryBatchIndexingActionableFactory.getBatchIndexingActionable(
                eventLocalService.getIndexableActionableDynamicQuery());
    }

    @Override
    public long getCompanyId(Event event) {
        return event.getCompanyId();
    }

//    @Override
//    public void modelIndexed(Event event) {
//        eventEntryBatchReindexer.reindex(
//                event.getEventId(), event.getCompanyId());
//    }

    @Reference
    protected DynamicQueryBatchIndexingActionableFactory
            dynamicQueryBatchIndexingActionableFactory;

//    @Reference
//    protected EventBatchReindexer eventEntryBatchReindexer;

    @Reference
    protected EventLocalService eventLocalService;

}
