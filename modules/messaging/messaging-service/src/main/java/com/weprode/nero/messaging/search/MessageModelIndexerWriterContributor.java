package com.weprode.nero.messaging.search;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;
import com.weprode.nero.messaging.model.Message;
import com.weprode.nero.messaging.service.MessageLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        property = "indexer.class.name=com.weprode.nero.messaging.model.Message",
        service = ModelIndexerWriterContributor.class
)
public class MessageModelIndexerWriterContributor
        implements ModelIndexerWriterContributor<Message> {

    @Override
    public void customize(
            BatchIndexingActionable batchIndexingActionable,
            ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

        batchIndexingActionable.setPerformActionMethod((Message message) -> {
            Document document = modelIndexerWriterDocumentHelper.getDocument(
                    message);

            batchIndexingActionable.addDocuments(document);
        });
    }

    @Override
    public BatchIndexingActionable getBatchIndexingActionable() {
        return dynamicQueryBatchIndexingActionableFactory.getBatchIndexingActionable(
                messageLocalService.getIndexableActionableDynamicQuery());
    }

    @Override
    public long getCompanyId(Message message) {
        return message.getCompanyId();
    }

//    @Override
//    public void modelIndexed(Message message) {
//        messageEntryBatchReindexer.reindex(
//                message.getMessageId(), message.getCompanyId());
//    }

    @Reference
    protected DynamicQueryBatchIndexingActionableFactory
            dynamicQueryBatchIndexingActionableFactory;

//    @Reference
//    protected MessageBatchReindexer messageEntryBatchReindexer;

    @Reference
    protected MessageLocalService messageLocalService;

}
