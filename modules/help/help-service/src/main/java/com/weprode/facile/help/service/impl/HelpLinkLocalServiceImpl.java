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
import com.weprode.facile.help.model.HelpLink;
import com.weprode.facile.help.service.base.HelpLinkLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.help.model.HelpLink",
        service = AopService.class
)
public class HelpLinkLocalServiceImpl extends HelpLinkLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(HelpLinkLocalServiceImpl.class);

    public HelpLink addHelpLink(long itemId, String linkUrl, String linkName) {
        HelpLink helpLink = null;

        try {
            long linkId = counterLocalService.increment();
            helpLink = helpLinkPersistence.create(linkId);
            helpLink.setItemId(itemId);
            helpLink.setLinkUrl(linkUrl);
            helpLink.setLinkName(linkName);
            helpLink = helpLinkPersistence.update(helpLink);
        } catch (Exception e) {
            logger.error("Error while creating help link", e);
        }

        return helpLink;
    }

    public List<HelpLink> getHelpLinks(long itemId) {
        List<HelpLink> helpLinks = new ArrayList<>();

        try {
            helpLinks = helpLinkPersistence.findByitemId(itemId);
        } catch (Exception e) {
            logger.error("Error while fetching help links for itemId "+itemId, e);
        }

        return helpLinks;
    }

    public boolean removeLinksForHelpItem(long itemId) {
        try {
            helpLinkPersistence.removeByitemId(itemId);
            return true;
        } catch (Exception e) {
            logger.error("Error while removing all links for itemId "+itemId, e);
        }

        return false;
    }
}
