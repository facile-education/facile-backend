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

import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.weprode.facile.document.service.FileUtilsLocalServiceUtil;
import com.weprode.facile.help.model.HelpItem;
import com.weprode.facile.help.model.HelpQuestion;
import com.weprode.facile.help.service.HelpItemLocalServiceUtil;
import com.weprode.facile.help.service.HelpLinkLocalServiceUtil;
import com.weprode.facile.help.service.HelpQuestionLocalServiceUtil;
import com.weprode.facile.help.service.HelpRelationLocalServiceUtil;
import com.weprode.facile.help.service.base.HelpItemLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.help.model.HelpItem",
        service = AopService.class
)
public class HelpItemLocalServiceImpl extends HelpItemLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(HelpItemLocalServiceImpl.class);

    public HelpItem createItem(long categoryId, String itemName, String videoUrl, String videoDescription, String manual, String language, boolean isManagement) {
        long itemId;

        try {
            itemId = counterLocalService.increment();
            final HelpItem helpItem = helpItemPersistence.create(itemId);
            helpItem.setCategoryId(categoryId);
            helpItem.setItemName(itemName);
            helpItem.setVideoURL(videoUrl);
            String sanitizedVideoDescription = FileUtilsLocalServiceUtil.sanitizeHTMLContent(videoDescription);
            helpItem.setVideoDescription(sanitizedVideoDescription);
            String sanitizedManual = FileUtilsLocalServiceUtil.sanitizeHTMLContent(manual);
            helpItem.setManual(sanitizedManual);
            helpItem.setLanguage(language);
            helpItem.setIsManagement(isManagement);

            // Calculate new position from existing maximum position + 1
            List<HelpItem> helpItems = getHelpItems(categoryId, StringPool.BLANK);
            int maxPosition = 0;
            if (helpItems != null) {
                for (HelpItem _helpItem : helpItems) {
                    if (_helpItem.getPosition() > maxPosition) {
                        maxPosition = _helpItem.getPosition();
                    }
                }
            }
            helpItem.setPosition(maxPosition + 1);
            helpItemPersistence.update(helpItem);

            return helpItem;
        } catch (SystemException e) {
            logger.debug(e);
        }

        return null;
    }

    public List<HelpItem> getHelpItems(long categoryId, String searchTerms) {
        List<HelpItem> filteredHelpItemList = new ArrayList<>();

        try {
            List<HelpItem> helpItemList = helpItemPersistence.findBycategoryId(categoryId);
            if (helpItemList != null) {
                for (HelpItem helpItem : helpItemList) {
                    if (searchTerms.equals("")
                            || helpItem.getItemName().toLowerCase().contains(searchTerms.toLowerCase())
                            || helpItem.getVideoDescription().toLowerCase().contains(searchTerms.toLowerCase())
                            || HtmlUtil.stripHtml(helpItem.getManual()).toLowerCase().contains(searchTerms.toLowerCase())
                            || hasMatchingQuestions(helpItem.getItemId(), searchTerms)) {
                        filteredHelpItemList.add(helpItem);
                    }
                }
            }
        } catch (Exception e) {
            logger.debug(e);
        }

        return filteredHelpItemList;
    }

    private boolean hasMatchingQuestions(long helpItemId, String searchTerms) {
        List<HelpQuestion> helpQuestions = HelpQuestionLocalServiceUtil.getHelpQuestions(helpItemId);

        if (helpQuestions != null) {
            for (HelpQuestion helpQuestion : helpQuestions) {
                if (helpQuestion.getQuestion().toLowerCase().contains(searchTerms.toLowerCase()) || helpQuestion.getAnswer().toLowerCase().contains(searchTerms.toLowerCase())) {
                    return true;
                }
            }
        }

        return false;
    }

    public void deleteItem(long itemId) throws Exception {
        // Remove associated questions, relations and links
        HelpQuestionLocalServiceUtil.removeQuestionsForHelpItem(itemId);
        HelpRelationLocalServiceUtil.removeRelationsForHelpItem(itemId);
        HelpLinkLocalServiceUtil.removeLinksForHelpItem(itemId);

        HelpItemLocalServiceUtil.deleteHelpItem(itemId);
    }

    public boolean removeItem(long helpItemId) {
        try {
            helpItemPersistence.remove(helpItemId);
            return true;
        } catch (Exception e) {
            logger.debug(e);
        }

        return false;
    }
}
