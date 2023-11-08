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

package com.weprode.facile.help.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.facile.help.model.HelpRelation;
import com.weprode.facile.help.service.base.HelpRelationLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.help.model.HelpRelation",
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
