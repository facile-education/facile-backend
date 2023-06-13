package com.weprode.nero.progression.utils;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.course.model.Homework;
import com.weprode.nero.course.service.HomeworkLocalServiceUtil;
import com.weprode.nero.progression.model.ItemContent;
import com.weprode.nero.progression.model.Progression;
import com.weprode.nero.progression.model.ProgressionFolder;
import com.weprode.nero.progression.model.ProgressionItem;
import com.weprode.nero.progression.service.ItemContentLocalServiceUtil;
import com.weprode.nero.progression.service.ProgressionFolderLocalServiceUtil;
import com.weprode.nero.progression.service.ProgressionItemLocalServiceUtil;
import com.weprode.nero.progression.service.ProgressionLocalServiceUtil;
import com.weprode.nero.schedule.service.SessionTeacherLocalServiceUtil;

public class ProgressionUtils {

    private ProgressionUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final Log logger = LogFactoryUtil.getLog(ProgressionUtils.class);

    public static boolean ownsProgression(long userId, long progressionId) {
        try {
            Progression progression = ProgressionLocalServiceUtil.getProgression(progressionId);
            return progression.getTeacherId() == userId;
        } catch (Exception e) {
            logger.debug(e);
        }

        return false;
    }

    public static boolean ownsFolder(long userId, long folderId) {
        try {
            ProgressionFolder progressionFolder = ProgressionFolderLocalServiceUtil.getProgressionFolder(folderId);
            Progression progression = ProgressionLocalServiceUtil.getProgression(progressionFolder.getProgressionId());
            return progression.getTeacherId() == userId;
        } catch (Exception e) {
            logger.debug(e);
        }

        return false;
    }

    public static boolean ownsProgressionItem(long userId, long itemId) {
        try {
            ProgressionItem progressionItem = ProgressionItemLocalServiceUtil.getProgressionItem(itemId);
            if (progressionItem.getProgressionId() != 0) {
                // Classic progression item -> check parent progression
                Progression progression = ProgressionLocalServiceUtil.getProgression(progressionItem.getProgressionId());
                return progression.getTeacherId() == userId;

            } else if (progressionItem.getSessionId() != 0) {
                // Specific session
                return SessionTeacherLocalServiceUtil.hasTeacherSession(userId, progressionItem.getSessionId());

            } else {
                // Specific homework
                Homework homework = HomeworkLocalServiceUtil.getHomework(progressionItem.getHomeworkId());
                return SessionTeacherLocalServiceUtil.hasTeacherSession(userId, homework.getSourceSessionId());
            }

        } catch (Exception e) {
            logger.debug(e);
        }

        return false;
    }

    public static boolean ownsProgressionContent(long userId, long contentId) {
        try {
            ItemContent content = ItemContentLocalServiceUtil.getItemContent(contentId);
            return ownsProgressionItem(userId, content.getProgressionItemId());

        } catch (Exception e) {
            logger.debug(e);
        }

        return false;
    }
}
