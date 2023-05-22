package com.weprode.nero.messaging.search;

import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.indexer.IndexerDocumentBuilder;
import com.liferay.portal.search.indexer.IndexerWriter;
import com.weprode.nero.messaging.model.Message;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = MessageBatchReindexer.class)
public class MessageBatchReindexerImpl implements MessageBatchReindexer {

    @Override
    public void reindex(long messageId, long companyId) {
        BatchIndexingActionable batchIndexingActionable =
                indexerWriter.getBatchIndexingActionable();

        logger.debug("Re-indexing message " + messageId);
        batchIndexingActionable.setAddCriteriaMethod(dynamicQuery -> {
            Property messageIdPropery = PropertyFactoryUtil.forName(
                    "messageId");

            dynamicQuery.add(messageIdPropery.eq(messageId));
        });

        batchIndexingActionable.setCompanyId(companyId);

        batchIndexingActionable.setPerformActionMethod((Message message) -> {
            Document document = indexerDocumentBuilder.getDocument(message);

            batchIndexingActionable.addDocuments(document);
        });

        batchIndexingActionable.performActions();

    }

    @Reference(target = "(indexer.class.name=com.weprode.nero.messaging.model.Message)")
    protected IndexerDocumentBuilder indexerDocumentBuilder;

    @Reference(target = "(indexer.class.name=com.weprode.nero.messaging.model.Message)")
    protected IndexerWriter<Message> indexerWriter;

    private static final Log logger = LogFactoryUtil.getLog(MessageBatchReindexerImpl.class);
}