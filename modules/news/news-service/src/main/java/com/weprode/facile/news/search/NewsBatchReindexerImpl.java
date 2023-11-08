/**
 * Copyright (c) 2022-present - Weprode
 * This file is part of FACILE ENT Project.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License (LGPL) as
 * published by the Free Software Foundation (version 2.1 of the License).
 * See LICENCE.txt file
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */

package com.weprode.facile.news.search;

import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.indexer.IndexerDocumentBuilder;
import com.liferay.portal.search.indexer.IndexerWriter;
import com.weprode.facile.news.model.News;
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

    @Reference(target = "(indexer.class.name=com.weprode.facile.news.model.News)")
    protected IndexerDocumentBuilder indexerDocumentBuilder;

    @Reference(target = "(indexer.class.name=com.weprode.facile.news.model.News)")
    protected IndexerWriter<News> indexerWriter;

    private static final Log logger = LogFactoryUtil.getLog(NewsBatchReindexerImpl.class);
}