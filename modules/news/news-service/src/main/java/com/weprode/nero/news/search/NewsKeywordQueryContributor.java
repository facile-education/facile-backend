package com.weprode.nero.news.search;

import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.search.query.QueryHelper;
import com.liferay.portal.search.spi.model.query.contributor.KeywordQueryContributor;
import com.liferay.portal.search.spi.model.query.contributor.helper.KeywordQueryContributorHelper;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        property = "indexer.class.name=com.nero.news.model.News",
        service = KeywordQueryContributor.class
)
public class NewsKeywordQueryContributor
        implements KeywordQueryContributor {

    @Override
    public void contribute(
            String keywords, BooleanQuery booleanQuery,
            KeywordQueryContributorHelper keywordQueryContributorHelper) {

        SearchContext searchContext =
                keywordQueryContributorHelper.getSearchContext();

        // We define here which field to search against the keyword
        // title, content, attachment names, author's name
        queryHelper.addSearchTerm(
                booleanQuery, searchContext, Field.TITLE, false);
        queryHelper.addSearchTerm(
                booleanQuery, searchContext, Field.CONTENT, false);
        queryHelper.addSearchTerm(
                booleanQuery, searchContext, Field.USER_NAME, false);
        queryHelper.addSearchTerm(
                booleanQuery, searchContext, Field.ASSET_TAG_NAMES, false);
    }

    @Reference
    protected QueryHelper queryHelper;
}
