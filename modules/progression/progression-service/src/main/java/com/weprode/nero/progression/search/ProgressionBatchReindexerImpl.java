package com.weprode.nero.progression.search;

import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.indexer.IndexerDocumentBuilder;
import com.liferay.portal.search.indexer.IndexerWriter;
import com.weprode.nero.progression.model.Progression;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = ProgressionBatchReindexer.class)
public class ProgressionBatchReindexerImpl implements ProgressionBatchReindexer {

    @Override
    public void reindex(long progressionId, long companyId) {
        BatchIndexingActionable batchIndexingActionable =
                indexerWriter.getBatchIndexingActionable();

        logger.debug("Re-indexing progression " + progressionId);
        batchIndexingActionable.setAddCriteriaMethod(dynamicQuery -> {
            Property progressionIdPropery = PropertyFactoryUtil.forName(
                    "progressionId");

            dynamicQuery.add(progressionIdPropery.eq(progressionId));
        });

        batchIndexingActionable.setCompanyId(companyId);

        batchIndexingActionable.setPerformActionMethod((Progression progression) -> {
            Document document = indexerDocumentBuilder.getDocument(progression);

            batchIndexingActionable.addDocuments(document);
        });

        batchIndexingActionable.performActions();

    }

    @Reference(target = "(indexer.class.name=com.weprode.nero.progression.model.Progression)")
    protected IndexerDocumentBuilder indexerDocumentBuilder;

    @Reference(target = "(indexer.class.name=com.weprode.nero.progression.model.Progression)")
    protected IndexerWriter<Progression> indexerWriter;

    private static final Log logger = LogFactoryUtil.getLog(ProgressionBatchReindexerImpl.class);
}