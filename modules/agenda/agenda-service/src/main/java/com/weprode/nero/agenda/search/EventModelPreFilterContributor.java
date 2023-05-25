package com.weprode.nero.agenda.search;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.search.spi.model.query.contributor.ModelPreFilterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchSettings;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.List;

@Component(
        immediate = true,
        property = "indexer.class.name=com.weprode.nero.agenda.model.Event",
        service = ModelPreFilterContributor.class
)
public class EventModelPreFilterContributor
        implements ModelPreFilterContributor {

    private static final Log logger = LogFactoryUtil.getLog(EventModelPreFilterContributor.class);

    @Override
    public void contribute(
            BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
            SearchContext searchContext) {
        
        TermsFilter authorFilter = new TermsFilter(Field.USER_ID);
        authorFilter.addValue(String.valueOf(searchContext.getUserId()));

        TermsFilter populationFilter = new TermsFilter("populationIds");
        List<Long> userGroups = new ArrayList<>();

        try {
            User user = UserLocalServiceUtil.getUser(searchContext.getUserId());
            // Institutionnal groups
            for (Organization org : user.getOrganizations()) {
                userGroups.add(org.getGroupId());
            }

            // Pers. groups
            for (long groupId : user.getGroupIds()) {
                userGroups.add(groupId);
            }

            for (long groupId : userGroups) {
                populationFilter.addValue(groupId + "_" + 0);
                for (long roleId : user.getRoleIds()) {
                    populationFilter.addValue(groupId + "_" + roleId);
                }
            }
        } catch (Exception e) {
            logger.error("Could not build event filter", e);
            // Add restrictive filter to prevent access to unauthorized content
            populationFilter.addValue("-1");
        }
        BooleanFilter filter = new BooleanFilter();
        filter.add(authorFilter, BooleanClauseOccur.SHOULD);
        filter.add(populationFilter, BooleanClauseOccur.SHOULD);

        booleanFilter.add(filter, BooleanClauseOccur.MUST);

        logger.debug("EventFilter is " + booleanFilter);
    }

    @Reference(target = "(model.pre.filter.contributor.id=WorkflowStatus)")
    protected ModelPreFilterContributor workflowStatusModelPreFilterContributor;

}
