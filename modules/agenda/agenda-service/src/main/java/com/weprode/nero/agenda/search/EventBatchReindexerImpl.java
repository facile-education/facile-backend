package com.weprode.nero.agenda.search;

import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.indexer.IndexerDocumentBuilder;
import com.liferay.portal.search.indexer.IndexerWriter;
import com.weprode.nero.agenda.model.Event;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = EventBatchReindexer.class)
public class EventBatchReindexerImpl implements EventBatchReindexer {

    @Override
    public void reindex(long eventId, long companyId) {
        BatchIndexingActionable batchIndexingActionable =
                indexerWriter.getBatchIndexingActionable();

        logger.debug("Re-indexing event " + eventId);
        batchIndexingActionable.setAddCriteriaMethod(dynamicQuery -> {
            Property eventIdPropery = PropertyFactoryUtil.forName(
                    "eventId");

            dynamicQuery.add(eventIdPropery.eq(eventId));
        });

        batchIndexingActionable.setCompanyId(companyId);

        batchIndexingActionable.setPerformActionMethod((Event event) -> {
            Document document = indexerDocumentBuilder.getDocument(event);

            batchIndexingActionable.addDocuments(document);
        });

        batchIndexingActionable.performActions();

    }

    @Reference(target = "(indexer.class.name=com.weprode.nero.agenda.model.Event)")
    protected IndexerDocumentBuilder indexerDocumentBuilder;

    @Reference(target = "(indexer.class.name=com.weprode.nero.agenda.model.Event)")
    protected IndexerWriter<Event> indexerWriter;

    private static final Log logger = LogFactoryUtil.getLog(EventBatchReindexerImpl.class);
}