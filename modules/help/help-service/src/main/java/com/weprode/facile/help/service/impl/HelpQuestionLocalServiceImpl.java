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
import com.weprode.facile.help.model.HelpQuestion;
import com.weprode.facile.help.service.base.HelpQuestionLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.help.model.HelpQuestion",
        service = AopService.class
)
public class HelpQuestionLocalServiceImpl extends HelpQuestionLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(HelpQuestionLocalServiceImpl.class);

    public HelpQuestion addHelpQuestion(long itemId, String question, String answer) {
        HelpQuestion helpQuestion = null;

        try {
            long questionId = counterLocalService.increment();
            helpQuestion = helpQuestionPersistence.create(questionId);
            helpQuestion.setItemId(itemId);
            helpQuestion.setQuestion(question);
            helpQuestion.setAnswer(answer);
            helpQuestion = helpQuestionPersistence.update(helpQuestion);
        } catch (Exception e) {
            logger.error("Error while creating help question", e);
        }

        return helpQuestion;
    }

    public List<HelpQuestion> getHelpQuestions(long itemId) {
        List<HelpQuestion> helpQuestions = new ArrayList<>();

        try {
            helpQuestions = helpQuestionPersistence.findByitemId(itemId);
        } catch (Exception e) {
            logger.error("Error while fetching help questions for itemId "+itemId, e);
        }

        return helpQuestions;
    }

    public boolean removeQuestionsForHelpItem(long itemId) {
        try {
            helpQuestionPersistence.removeByitemId(itemId);
            return true;
        } catch (Exception e) {
            logger.error("Error while removing all questions for itemId "+itemId, e);
        }

        return false;
    }
}
