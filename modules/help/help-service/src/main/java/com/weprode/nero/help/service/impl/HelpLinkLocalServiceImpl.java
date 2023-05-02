package com.weprode.nero.help.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.help.model.HelpLink;
import com.weprode.nero.help.service.base.HelpLinkLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.help.model.HelpLink",
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
