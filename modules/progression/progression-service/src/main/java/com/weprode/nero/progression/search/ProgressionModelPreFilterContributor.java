package com.weprode.nero.progression.search;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.search.spi.model.query.contributor.ModelPreFilterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchSettings;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        property = "indexer.class.name=com.weprode.nero.progression.model.Progression",
        service = ModelPreFilterContributor.class
)
public class ProgressionModelPreFilterContributor
        implements ModelPreFilterContributor {

    private static final Log logger = LogFactoryUtil.getLog(ProgressionModelPreFilterContributor.class);

    @Override
    public void contribute(
            BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
            SearchContext searchContext) {
        
        /*TermsFilter authorFilter = new TermsFilter(Field.USER_ID);
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
            logger.error("Could not build progression filter", e);
            // Add restrictive filter to prevent access to unauthorized content
            populationFilter.addValue("-1");
        }
        BooleanFilter filter = new BooleanFilter();
        filter.add(authorFilter, BooleanClauseOccur.SHOULD);
        filter.add(populationFilter, BooleanClauseOccur.SHOULD);

        booleanFilter.add(filter, BooleanClauseOccur.MUST);*/

        logger.debug("ProgressionFilter is " + booleanFilter);
    }

    @Reference(target = "(model.pre.filter.contributor.id=WorkflowStatus)")
    protected ModelPreFilterContributor workflowStatusModelPreFilterContributor;

}
