package com.weprode.nero.messaging.search;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.search.spi.model.query.contributor.ModelPreFilterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchSettings;
import com.weprode.nero.messaging.model.MessageFolder;
import com.weprode.nero.messaging.service.MessageFolderLocalServiceUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        property = "indexer.class.name=com.weprode.nero.messaging.model.Message",
        service = ModelPreFilterContributor.class
)
public class MessageModelPreFilterContributor
        implements ModelPreFilterContributor {

    private static final Log logger = LogFactoryUtil.getLog(MessageModelPreFilterContributor.class);

    @Override
    public void contribute(
            BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
            SearchContext searchContext) {

        // Adding custom filters for message
        TermsFilter folderFilter = new TermsFilter(Field.FOLDER_ID);

        try {
            for (MessageFolder folder : MessageFolderLocalServiceUtil.getAllUserFolders(searchContext.getUserId())) {
                folderFilter.addValue(String.valueOf(folder.getFolderId()));
            }
        } catch (Exception e) {
            logger.error("Could not build message filter", e);
            // Add restrictive filter to prevent access to unauthorized content
            folderFilter.addValue("-1");
        }

        booleanFilter.add(folderFilter, BooleanClauseOccur.MUST);
        logger.debug("MessageFilter is " + booleanFilter);
    }

    @Reference(target = "(model.pre.filter.contributor.id=WorkflowStatus)")
    protected ModelPreFilterContributor workflowStatusModelPreFilterContributor;

}
