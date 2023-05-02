package com.weprode.nero.help.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.help.model.HelpRelation;
import com.weprode.nero.help.service.base.HelpRelationLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.help.model.HelpRelation",
        service = AopService.class
)
public class HelpRelationLocalServiceImpl extends HelpRelationLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(HelpRelationLocalServiceImpl.class);

    public HelpRelation addHelpRelation(long itemId, long relatedItemId) {
        HelpRelation helpRelation = null;

        try {
            long relationId = counterLocalService.increment();
            helpRelation = helpRelationPersistence.create(relationId);
            helpRelation.setItemId(itemId);
            helpRelation.setRelatedItemId(relatedItemId);
            helpRelation = helpRelationPersistence.update(helpRelation);
        } catch (Exception e) {
            logger.error("Error while creating help relation", e);
        }

        return helpRelation;
    }

    public List<HelpRelation> getHelpRelations(long itemId) {
        List<HelpRelation> helpRelations = new ArrayList<>();

        try {
            helpRelations = helpRelationPersistence.findByitemId(itemId);
        } catch (Exception e) {
            logger.error("Error while fetching help related items for itemId "+itemId, e);
        }

        return helpRelations;
    }

    public boolean removeRelationsForHelpItem(long itemId) {
        try {
            helpRelationPersistence.removeByitemId(itemId);
            return true;
        } catch (Exception e) {
            logger.error("Error while removing all relations for itemId "+itemId, e);
        }

        return false;
    }
}
