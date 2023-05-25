package com.weprode.nero.progression.search;

import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.indexer.IndexerDocumentBuilder;
import com.liferay.portal.search.indexer.IndexerWriter;
import com.weprode.nero.progression.model.ProgressionItem;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = ProgressionItemBatchReindexer.class)
public class ProgressionItemBatchReindexerImpl implements ProgressionItemBatchReindexer {

    @Override
    public void reindex(long progressionItemId, long companyId) {
        BatchIndexingActionable batchIndexingActionable =
                indexerWriter.getBatchIndexingActionable();

        logger.debug("Re-indexing progression " + progressionItemId);
        batchIndexingActionable.setAddCriteriaMethod(dynamicQuery -> {
            Property progressionIdPropery = PropertyFactoryUtil.forName(
                    "progressionItemId");

            dynamicQuery.add(progressionIdPropery.eq(progressionItemId));
        });

        batchIndexingActionable.setCompanyId(companyId);

        batchIndexingActionable.setPerformActionMethod((ProgressionItem progressionItem) -> {
            Document document = indexerDocumentBuilder.getDocument(progressionItem);

            batchIndexingActionable.addDocuments(document);
        });

        batchIndexingActionable.performActions();

    }

    @Reference(target = "(indexer.class.name=com.weprode.nero.progression.model.ProgressionItem)")
    protected IndexerDocumentBuilder indexerDocumentBuilder;

    @Reference(target = "(indexer.class.name=com.weprode.nero.progression.model.ProgressionItem)")
    protected IndexerWriter<ProgressionItem> indexerWriter;

    private static final Log logger = LogFactoryUtil.getLog(ProgressionItemBatchReindexerImpl.class);
}