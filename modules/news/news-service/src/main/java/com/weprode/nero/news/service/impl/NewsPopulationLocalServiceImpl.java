package com.weprode.nero.news.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.contact.service.ContactLocalServiceUtil;
import com.weprode.nero.group.service.GroupUtilsLocalServiceUtil;
import com.weprode.nero.news.model.NewsPopulation;
import com.weprode.nero.news.service.base.NewsPopulationLocalServiceBaseImpl;
import com.weprode.nero.news.service.persistence.NewsPopulationPK;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.news.model.NewsPopulation",
        service = AopService.class
)
public class NewsPopulationLocalServiceImpl extends NewsPopulationLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(NewsPopulationLocalServiceImpl.class);

    public NewsPopulation addPopulation(long newsId, long groupId, long roleId) throws SystemException {
        NewsPopulationPK newsPopulationPK = new NewsPopulationPK(newsId, groupId, roleId);

        NewsPopulation population = newsPopulationPersistence.create(newsPopulationPK);

        return newsPopulationPersistence.update(population);
    }

    public List<NewsPopulation> getNewsPopulations(long newsId) throws SystemException {
        return newsPopulationPersistence.findBynewsId(newsId);
    }

    public JSONArray convertNewsPopulations(long newsId, long userId) throws SystemException {
        JSONArray jsonPopulations = new JSONArray();

        List<NewsPopulation> populations = newsPopulationPersistence.findBynewsId(newsId);
        if (populations != null) {
            for (NewsPopulation population : populations) {
                try {
                    JSONObject jsonPopulation = new JSONObject();
                    jsonPopulation.put(JSONConstants.GROUP_ID, population.getGroupId());
                    jsonPopulation.put(JSONConstants.ROLE_ID, population.getRoleId());
                    Group group = GroupLocalServiceUtil.getGroup(population.getGroupId());
                    if (group.isRegularSite()) {
                        jsonPopulation.put(JSONConstants.POPULATION_NAME, GroupUtilsLocalServiceUtil.getGroupName(population.getGroupId()));
                    } else {
                        jsonPopulation.put(JSONConstants.POPULATION_NAME, ContactLocalServiceUtil.getPopulationName(group.getClassPK(), population.getRoleId(), userId));
                    }
                    jsonPopulations.put(jsonPopulation);
                } catch (Exception e) {
                    logger.info("Error converting population for newsId " + newsId);
                }
            }
        }

        return jsonPopulations;
    }

    public void deleteByNewsId(long newsId) throws SystemException {
        logger.info("Deleting populations for news " + newsId);

        newsPopulationPersistence.removeBynewsId(newsId);
    }
}
