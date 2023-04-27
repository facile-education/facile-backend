package com.weprode.nero.progression.service.impl;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.document.service.FileUtilsLocalServiceUtil;
import com.weprode.nero.progression.constants.ProgressionConstants;
import com.weprode.nero.progression.exception.UnauthorizedUrlException;
import com.weprode.nero.progression.model.ItemAssignment;
import com.weprode.nero.progression.model.ItemContent;
import com.weprode.nero.progression.model.ProgressionItem;
import com.weprode.nero.progression.service.ItemAssignmentLocalServiceUtil;
import com.weprode.nero.progression.service.ItemContentLocalServiceUtil;
import com.weprode.nero.progression.service.ProgressionItemLocalServiceUtil;
import com.weprode.nero.progression.service.base.ProgressionItemServiceBaseImpl;
import com.weprode.nero.progression.utils.ProgressionUtils;
import com.weprode.nero.schedule.model.Homework;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.HomeworkLocalServiceUtil;
import com.weprode.nero.schedule.service.SessionTeacherLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=progression",
                "json.web.service.context.path=ProgressionItem"
        },
        service = AopService.class
)
public class ProgressionItemServiceImpl extends ProgressionItemServiceBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(ProgressionItemServiceImpl.class);

    @JSONWebService(value = "add-item", method = "POST")
    public JSONObject addItem(long progressionId, long folderId, boolean isHomework) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        result.put(JSONConstants.SUCCESS, true);
        try {
            // Check ownership
            if (!ProgressionUtils.ownsProgression(user.getUserId(), progressionId) || !ProgressionUtils.ownsFolder(user.getUserId(), folderId)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            ProgressionItem item = ProgressionItemLocalServiceUtil.addItem(progressionId, user.getUserId(), folderId, isHomework);
            result.put(JSONConstants.ITEM, item.convertToJSON(user.getUserId(), true));
            logger.info("User "+user.getFullName()+" (id="+user.getUserId()+") has created progression itemId " + item.getProgressionItemId() + " in folder " + folderId);

        } catch (Exception e) {
            logger.error("Could not add progression item for "+user.getFullName()+" (id="+user.getUserId()+") " + "and progressionId = " + progressionId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "update-item", method = "POST")
    public JSONObject updateItem(long itemId, long folderId, String name, int type, String duration, int order) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        result.put(JSONConstants.SUCCESS, true);
        try {
            // Check ownership
            if (!ProgressionUtils.ownsProgressionItem(user.getUserId(), itemId) || !ProgressionUtils.ownsFolder(user.getUserId(), folderId)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            ProgressionItem updatedItem = ProgressionItemLocalServiceUtil.updateItem(itemId, folderId, name, type, duration, order);
            result.put(JSONConstants.ITEM, updatedItem.convertToJSON(user.getUserId(), true));
            logger.info("User "+user.getFullName()+" (id="+user.getUserId()+") has updated progression itemId "+itemId);

        } catch (Exception e) {
            logger.error("Could not update progression itemId = "+itemId+" for "+user.getFullName()+" (id="+user.getUserId()+")", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "delete-item", method = "DELETE")
    public JSONObject deleteItem(long itemId) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        result.put(JSONConstants.SUCCESS, true);
        try {
            // Check ownership
            if (!ProgressionUtils.ownsProgressionItem(user.getUserId(), itemId)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            ProgressionItemLocalServiceUtil.deleteItem(user.getUserId(), itemId);
            logger.info("User "+user.getFullName()+" (id="+user.getUserId()+") has deleted progression itemId "+itemId);

        } catch (Exception e) {
            logger.error("Could not delete progression itemId = "+itemId+" for"+user.getFullName()+" (id="+user.getUserId()+") ", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "add-item-content", method = "POST")
    public JSONObject addItemContent(long itemId, int contentType, String contentName, String contentValue, long fileEntryId, boolean isToBeCompleted) {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        result.put(JSONConstants.SUCCESS, true);
        try {
            // Check ownership
            if (!ProgressionUtils.ownsProgressionItem(user.getUserId(), itemId)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            ItemContent itemContent = ItemContentLocalServiceUtil.addContent(itemId, contentType, contentName, contentValue, fileEntryId, isToBeCompleted, user.getUserId());
            result.put(JSONConstants.CONTENT, itemContent.convertToJSON(true));
        } catch (UnauthorizedUrlException e) {
            logger.error(e.getMessage());
            result.put(JSONConstants.ERROR, e.getClass());
            result.put(JSONConstants.SUCCESS, false);
        } catch (Exception e) {
            logger.error("Could not add progression item content for " + user.getFullName() + " (id=" + user.getUserId()+") " + "and itemId=" + itemId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "add-item-content", method = "POST")
    public JSONObject addItemContent(long itemId, int contentType, String contentName, String fileName, File file) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        result.put(JSONConstants.SUCCESS, true);
        try {
            // Check ownership
            if (!ProgressionUtils.ownsProgressionItem(user.getUserId(), itemId)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            Folder progressionItemFolder = ProgressionItemLocalServiceUtil.getOrCreateDLFolder(itemId, user.getUserId());

            if (contentType == ProgressionConstants.TYPE_RECORD) {
                file = FileUtilsLocalServiceUtil.convertAudioToMP3(fileName, file);
            }

            try (FileInputStream inputStream = new FileInputStream(file)) {
                FileEntry uploadedFile = DLAppServiceUtil.addFileEntry(
                        StringPool.BLANK, // externalReferenceCode
                        progressionItemFolder.getGroupId(),
                        progressionItemFolder.getFolderId(),
                        fileName,
                        MimeTypesUtil.getContentType(fileName),
                        fileName,
                        StringPool.BLANK, // urlTitle
                        StringPool.BLANK, // description
                        StringPool.BLANK, // changeLog
                        inputStream,
                        file.length(),
                        null,
                        null,
                        new ServiceContext());

                long fileEntryId = uploadedFile.getFileEntryId();

                if (contentName.isEmpty()) {
                    contentName = fileName;
                }
                ItemContent itemContent = ItemContentLocalServiceUtil.addContent(itemId, contentType, contentName, "", fileEntryId, false, user.getUserId());
                result.put(JSONConstants.CONTENT, itemContent.convertToJSON(true));
            }

        } catch (UnauthorizedUrlException e) {
            logger.error(e.getMessage());
            result.put(JSONConstants.ERROR, e.getClass());
            result.put(JSONConstants.SUCCESS, false);
        } catch (Exception e) {
            logger.error("Could not add progression item content for " + user.getFullName() + " (id=" + user.getUserId()+") " + "and itemId=" + itemId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "update-item-content", method = "POST")
    public JSONObject updateItemContent(long contentId, String contentName, String contentValue, int order) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        result.put(JSONConstants.SUCCESS, true);
        try {
            // Check ownership
            if (!ProgressionUtils.ownsProgressionContent(user.getUserId(), contentId)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            ItemContent content = ItemContentLocalServiceUtil.updateContent(contentId, contentName, contentValue, order);
            ProgressionItem item = ProgressionItemLocalServiceUtil.getProgressionItem(content.getProgressionItemId());
            result.put(JSONConstants.ITEM, item.convertToJSON(user.getUserId()));

        } catch (UnauthorizedUrlException e) {
            logger.error(e.getMessage());
            result.put(JSONConstants.ERROR, e.getClass());
            result.put(JSONConstants.SUCCESS, false);
        } catch (Exception e) {
            logger.error("Could not update progression item content for " + user.getFullName() + " (id=" + user.getUserId() + ") " + "and contentId=" + contentId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "delete-item-content", method = "DELETE")
    public JSONObject deleteItemContent(long contentId) {

        JSONObject result = JSONFactoryUtil.createJSONObject();
        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        result.put(JSONConstants.SUCCESS, true);
        try {
            // Check ownership
            if (!ProgressionUtils.ownsProgressionContent(user.getUserId(), contentId)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            logger.info("User "+user.getFullName()+" (id="+user.getUserId()+") is about to delete contentId = " + contentId);
            ItemContentLocalServiceUtil.deleteContent(contentId);

        } catch (Exception e) {
            logger.error("Could not delete contentId = "+contentId+" for"+user.getFullName()+" (id="+user.getUserId()+") ", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "get-item-contents", method = "GET")
    public JSONObject getItemContents(long itemId) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        result.put(JSONConstants.SUCCESS, true);
        try {
            // Check ownership
            if (!ProgressionUtils.ownsProgressionItem(user.getUserId(), itemId)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            JSONArray jsonContents = JSONFactoryUtil.createJSONArray();
            List<ItemContent> itemContents = ItemContentLocalServiceUtil.getContentsByItemId(itemId);
            for (ItemContent itemContent : itemContents) {
                jsonContents.put(itemContent.convertToJSON(true));
            }
            result.put(JSONConstants.CONTENTS, jsonContents);

        } catch (Exception e) {
            logger.error("Could not get full content for "+user.getFullName()+" (id="+user.getUserId()+") " + "and itemId = "+itemId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "get-item-preview", method = "GET")
    public JSONObject getItemPreview(long itemId) {

        JSONObject result = JSONFactoryUtil.createJSONObject();
        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        result.put(JSONConstants.SUCCESS, true);
        try {
            // Check ownership
            if (!ProgressionUtils.ownsProgressionItem(user.getUserId(), itemId)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            String contentAsHtml = ProgressionItemLocalServiceUtil.convertContentAsHtml(itemId);
            result.put(JSONConstants.PREVIEW, contentAsHtml);

        } catch (Exception e) {
            logger.error("Could not get preview content for " + user.getFullName() + " (id="+user.getUserId()+") " + "and itemId = "+itemId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "get-session-specific-contents", method = "GET")
    public JSONObject getSessionSpecificContents(long sessionId) {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        result.put(JSONConstants.SUCCESS, true);
        try {
            // First check teacher
            if (!SessionTeacherLocalServiceUtil.hasTeacherSession(user.getUserId(), sessionId)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            // Is there a specific content for this session ?
            ProgressionItem item = ProgressionItemLocalServiceUtil.getSpecificSessionItem(sessionId);
            if (item != null) {
                logger.info("User " + user.getFullName() +" gets specific content for session " + sessionId + " : it already exists");
                // Specific content for this session
                result.put(JSONConstants.ITEM, item.convertToJSON(user.getUserId(), true));
            } else {
                logger.info("User " + user.getFullName() +" gets specific content for session " + sessionId + " : cloning it from source item");
                // Create specific content by copying it from its assigned progressionItemId
                long progressionItemId = ItemAssignmentLocalServiceUtil.getSessionAssignmentItemId(sessionId);
                ProgressionItem sessionItem = ProgressionItemLocalServiceUtil.cloneItemForSpecificSession(user.getUserId(), progressionItemId, sessionId);

                // Mark assigment as override	// TODO when content is save with modifications
                ItemAssignmentLocalServiceUtil.markAssigmentAsOverride(progressionItemId, sessionId);

                result.put(JSONConstants.ITEM, sessionItem.convertToJSON(user.getUserId(), true));
            }

        } catch (Exception e) {
            logger.error("Could not get content for "+user.getFullName()+" (id="+user.getUserId()+") " + "and sessionId = "+sessionId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "get-homework-specific-contents", method = "GET")
    public JSONObject getHomeworkSpecificContents(long homeworkId) {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        result.put(JSONConstants.SUCCESS, true);
        try {
            // First check teacher
            Homework homework = HomeworkLocalServiceUtil.getHomework(homeworkId);
            if (!SessionTeacherLocalServiceUtil.hasTeacherSession(user.getUserId(), homework.getSourceSessionId())) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            // Is there a specific content for this homework ?
            ProgressionItem item = ProgressionItemLocalServiceUtil.getSpecificHomeworkItem(homeworkId);
            if (item != null) {
                logger.info("User " + user.getFullName() +" gets specific content for homework " + homeworkId + " : it already exists");
                // Specific content for this homework
                result.put(JSONConstants.ITEM, item.convertToJSON(user.getUserId(), true));
            } else {
                logger.info("User " + user.getFullName() +" gets specific content for homework " + homeworkId + " : cloning it from source item");
                // Create specific content by copying it from its assigned progressionItemId
                long progressionItemId = ItemAssignmentLocalServiceUtil.getHomeworkAssignmentItemId(homeworkId);
                ProgressionItem homeworkItem = ProgressionItemLocalServiceUtil.cloneItemForSpecificHomework(user.getUserId(), progressionItemId, homeworkId);

                // Mark assigment as override	// TODO when content is save with modifications
                ItemAssignment itemAssignment = ItemAssignmentLocalServiceUtil.getByItemIdHomeworkId(progressionItemId, homeworkId);
                ItemAssignmentLocalServiceUtil.markAssigmentAsOverride(progressionItemId, itemAssignment.getSessionId());

                result.put(JSONConstants.ITEM, homeworkItem.convertToJSON(user.getUserId(), true));
            }

        } catch (Exception e) {
            logger.error("Could not get content for "+user.getFullName()+" (id="+user.getUserId()+") " + "and homeworkId = "+homeworkId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "save-session-specific-item", method = "POST")
    public JSONObject saveSessionSpecificItem(long sessionId) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        result.put(JSONConstants.SUCCESS, true);
        try {
            // Check that the user owns the given session
            if (!SessionTeacherLocalServiceUtil.hasTeacherSession(user.getUserId(), sessionId)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            logger.info("Propagate session specific content to sessionId " + sessionId);
            ProgressionItem sessionItem = ProgressionItemLocalServiceUtil.getSpecificSessionItem (sessionId);

            // Propagate in C&D
            CDTSessionLocalServiceUtil.assignSessionContent(sessionId, sessionItem.getProgressionItemId());

            result.put(JSONConstants.ITEM, sessionItem.convertToJSON(user.getUserId()));

        } catch (Exception e) {
            logger.error("Could not save specific session content for " + user.getFullName() + " (id=" + user.getUserId() + ") " + "and sessionId=" + sessionId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "save-homework-specific-item", method = "POST")
    public JSONObject saveHomeworkSpecificItem(long homeworkId) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        result.put(JSONConstants.SUCCESS, true);
        try {
            // Check that the user owns the given session
            Homework homework = HomeworkLocalServiceUtil.getHomework(homeworkId);
            if (!SessionTeacherLocalServiceUtil.hasTeacherSession(user.getUserId(), homework.getSourceSessionId())) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            logger.info("Propagate homework specific content to homeworkId " + homeworkId);

            ProgressionItem homeworkItem = ProgressionItemLocalServiceUtil.getSpecificHomeworkItem(homeworkId);

            // Propagate description + files
            HomeworkLocalServiceUtil.assignHomeworkContent(homework.getHomeworkId(), homeworkItem.getProgressionItemId());

            result.put(JSONConstants.ITEM, homeworkItem.convertToJSON(user.getUserId()));

        } catch (Exception e) {
            logger.error("Could not save specific session content for " + user.getFullName() + " (id=" + user.getUserId() + ") " + "and homeworkId=" + homeworkId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}
