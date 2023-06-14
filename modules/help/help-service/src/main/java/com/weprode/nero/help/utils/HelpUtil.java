package com.weprode.nero.help.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.weprode.nero.application.model.Application;
import com.weprode.nero.application.service.ApplicationLocalServiceUtil;
import com.weprode.nero.application.service.BroadcastLocalServiceUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.help.model.HelpCategory;
import com.weprode.nero.help.model.HelpItem;
import com.weprode.nero.help.model.HelpLink;
import com.weprode.nero.help.model.HelpQuestion;
import com.weprode.nero.help.model.HelpRelation;
import com.weprode.nero.help.service.HelpCategoryLocalServiceUtil;
import com.weprode.nero.help.service.HelpItemLocalServiceUtil;
import com.weprode.nero.help.service.HelpItemRoleLocalServiceUtil;
import com.weprode.nero.help.service.HelpLinkLocalServiceUtil;
import com.weprode.nero.help.service.HelpQuestionLocalServiceUtil;
import com.weprode.nero.help.service.HelpRelationLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class HelpUtil {

    private HelpUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final Log logger = LogFactoryUtil.getLog(HelpUtil.class);

    // Returns the whole help tree with categories and help items, with filtering if search terms are provided
    public static JSONArray getHelpTree(User user, String searchTerms) {
        JSONArray helpTree = new JSONArray();

        // First fetch all categories
        List<HelpCategory> categories = HelpCategoryLocalServiceUtil.getAllCategories();
        if (categories != null) {
            for (HelpCategory category : categories) {

                if (isUserAllowedToSeeCategory(user, category)) {

                    JSONObject jsonCategory = convertCategoryToJson(category);
                    List<HelpItem> helpItems;

                    // If search and category name matches, return the whole category with all items
                    if (!searchTerms.equals("") && category.getCategoryName().toLowerCase().contains(searchTerms.toLowerCase())) {
                        helpItems = HelpItemLocalServiceUtil.getHelpItems(category.getCategoryId(), "");
                    } else {
                        // Get associated help items
                        helpItems = HelpItemLocalServiceUtil.getHelpItems(category.getCategoryId(), searchTerms);
                    }

                    JSONArray jsonHelpItems = new JSONArray();
                    for (HelpItem helpItem : helpItems) {
                        if (HelpItemRoleLocalServiceUtil.isUserAllowedToSeeItem(user, helpItem.getItemId())) {
                            JSONObject jsonHelpItem = new JSONObject();
                            jsonHelpItem.put(JSONConstants.ITEM_ID, helpItem.getItemId());
                            jsonHelpItem.put(JSONConstants.ITEM_NAME, helpItem.getItemName());
                            jsonHelpItem.put(JSONConstants.POSITION, helpItem.getPosition());
                            jsonHelpItem.put(JSONConstants.IS_MANAGEMENT, helpItem.getIsManagement());
                            jsonHelpItem.put(JSONConstants.IS_FETCHED, false); // Specific further call will fill heavy descriptions and manual

                            jsonHelpItems.put(jsonHelpItem);
                        }
                    }
                    if (jsonHelpItems.length() > 0 || RoleUtilsLocalServiceUtil.isAdministrator(user)) {
                        jsonCategory.put(JSONConstants.ITEMS, jsonHelpItems);
                        helpTree.put(jsonCategory);
                    }
                }

            }
        }

        return helpTree;
    }
    
    // User is allowed to see category if he has the associated service broadcasted
    private static boolean isUserAllowedToSeeCategory(User user, HelpCategory category) {
        try {
            if (RoleUtilsLocalServiceUtil.isAdministrator(user)) {
                return true;
            }

            Application entService = ApplicationLocalServiceUtil.getById(category.getServiceId());
            if (entService != null) {
                return BroadcastLocalServiceUtil.isApplicationBroadcastedToUser(user.getUserId(), entService.getApplicationKey());
            }
        } catch (Exception e) {
            logger.error("Error when finding if user has the help category's service available", e);
        }
        return true;
    }

    public static JSONObject getHelpItemDetails(long itemId) {
        JSONObject jsonHelpItem = new JSONObject();

        HelpItem helpItem = null;
        try {
            helpItem = HelpItemLocalServiceUtil.getHelpItem(itemId);
        } catch (Exception e) {
            logger.error("Error when fetching help item id with id "+itemId, e);
        }

        if (helpItem != null) {
            jsonHelpItem.put(JSONConstants.ITEM_ID, helpItem.getItemId());
            jsonHelpItem.put(JSONConstants.ITEM_NAME, helpItem.getItemName());
            jsonHelpItem.put(JSONConstants.CATEGORY_ID, helpItem.getCategoryId());
            jsonHelpItem.put(JSONConstants.VIDEO_URL, helpItem.getVideoURL());
            jsonHelpItem.put(JSONConstants.VIDEO_DESCRIPTION, helpItem.getVideoDescription());
            jsonHelpItem.put(JSONConstants.MANUAL, helpItem.getManual());
            jsonHelpItem.put(JSONConstants.POSITION, helpItem.getPosition());
            jsonHelpItem.put(JSONConstants.LANGUAGE, helpItem.getLanguage());
            jsonHelpItem.put(JSONConstants.IS_MANAGEMENT, helpItem.getIsManagement());

            // Questions
            JSONArray jsonQuestions = new JSONArray();
            List<HelpQuestion> helpQuestions = HelpQuestionLocalServiceUtil.getHelpQuestions(helpItem.getItemId());
            if (helpQuestions != null) {
                for (HelpQuestion helpQuestion : helpQuestions) {
                    jsonQuestions.put(convertQuestionToJson(helpQuestion));
                }
            }
            jsonHelpItem.put(JSONConstants.QUESTIONS, jsonQuestions);

            // Related items
            JSONArray jsonRelations = new JSONArray();
            List<HelpRelation> helpRelations = HelpRelationLocalServiceUtil.getHelpRelations(helpItem.getItemId());
            if (helpRelations != null) {
                for (HelpRelation helpRelation : helpRelations) {
                    jsonRelations.put(convertRelationToJson(helpRelation));
                }
            }
            jsonHelpItem.put(JSONConstants.RELATIONS, jsonRelations);

            // Links
            List<HelpLink> helpLinks = HelpLinkLocalServiceUtil.getHelpLinks(helpItem.getItemId());
            if (helpLinks != null) {
                JSONArray jsonLinks = new JSONArray();
                for (HelpLink helpLink : helpLinks) {
                    jsonLinks.put(convertLinkToJson(helpLink));
                }
                jsonHelpItem.put(JSONConstants.LINKS, jsonLinks);
            }
        }

        return jsonHelpItem;
    }

    public static void saveHelpItemPosition(long categoryId, JSONObject jsonHelpItem) throws PortalException, SystemException {
        long itemId = JSONConstants.getLongValue(jsonHelpItem, JSONConstants.ITEM_ID, 0);
        int position = jsonHelpItem.getInt(JSONConstants.POSITION);

        if (itemId > 0) {
            // Get other item that is switched
            HelpItem switchedItem = null;
            List<HelpItem> categoryItems = HelpItemLocalServiceUtil.getHelpItems(categoryId, StringPool.BLANK);
            for (HelpItem categoryItem : categoryItems) {
                if (categoryItem.getPosition() == position) {
                    switchedItem = categoryItem;
                }
            }

            if (switchedItem != null) {
                HelpItem movedItem = HelpItemLocalServiceUtil.getHelpItem(itemId);
                switchedItem.setPosition(movedItem.getPosition());
                logger.info("Set position " + movedItem.getPosition() + " to item " + switchedItem.getItemName());
                HelpItemLocalServiceUtil.updateHelpItem(switchedItem);

                movedItem.setPosition(position);
                HelpItemLocalServiceUtil.updateHelpItem(movedItem);
                logger.info("Set position " + position + " to item " + movedItem.getItemName());
            }
        }
    }

    // Create new help item and save it to the DB
    public static JSONObject saveHelpItem(long categoryId, JSONObject jsonHelpItem) {
        try {
            long itemId = JSONConstants.getLongValue(jsonHelpItem, JSONConstants.ITEM_ID, 0);
            String itemName = jsonHelpItem.getString(JSONConstants.ITEM_NAME);
            String videoUrl = JSONConstants.getStringValue(jsonHelpItem, JSONConstants.VIDEO_URL, StringPool.BLANK);
            String videoDescription = JSONConstants.getStringValue(jsonHelpItem, JSONConstants.VIDEO_DESCRIPTION, StringPool.BLANK);
            String textualHelp = JSONConstants.getStringValue(jsonHelpItem, JSONConstants.MANUAL, StringPool.BLANK);
            int position = JSONConstants.getIntValue(jsonHelpItem, JSONConstants.POSITION, 0);
            String language = jsonHelpItem.getString(JSONConstants.LANGUAGE);

            JSONArray roles = new JSONArray();
            if (jsonHelpItem.has(JSONConstants.ROLES)) {
                roles = jsonHelpItem.getJSONArray(JSONConstants.ROLES);
            }

            boolean isManagement = JSONConstants.getBoolValue(jsonHelpItem, JSONConstants.IS_MANAGEMENT, false);

            HelpItem helpItem;
            if (itemId == 0) {
                // Creation
                helpItem = HelpItemLocalServiceUtil.createItem(categoryId, itemName, videoUrl, videoDescription, textualHelp, language, isManagement);

                // Update json with created position and id
                jsonHelpItem.put(JSONConstants.ITEM_ID, helpItem.getItemId());
                jsonHelpItem.put(JSONConstants.POSITION, helpItem.getPosition());

                // Add roles if any
                if (roles != null) {
                    for (int idx = 0 ; idx < roles.length() ; idx++) {
                        long roleId = roles.getJSONObject(idx).getLong(JSONConstants.ROLE_ID);
                        logger.info("Associating help item " + helpItem.getItemId() + " to roleId " + roleId);
                        HelpItemRoleLocalServiceUtil.addItemRole(helpItem.getItemId(), roleId);
                    }
                }

            } else {
                // Update
                helpItem = HelpItemLocalServiceUtil.getHelpItem(itemId);
                helpItem.setItemName(itemName);
                helpItem.setVideoURL(videoUrl);
                helpItem.setVideoDescription(videoDescription);
                helpItem.setManual(textualHelp);
                helpItem.setPosition(position);
                helpItem.setLanguage(language);
                helpItem.setIsManagement(isManagement);
                HelpItemLocalServiceUtil.updateHelpItem(helpItem);
            }

            // Questions
            JSONArray questions = new JSONArray();
            if (jsonHelpItem.has(JSONConstants.QUESTIONS)) {
                questions = jsonHelpItem.getJSONArray(JSONConstants.QUESTIONS);
            }
            JSONArray updatedQuestions = updateQuestions(itemId, questions);
            jsonHelpItem.put(JSONConstants.QUESTIONS, updatedQuestions);

            return jsonHelpItem;
        } catch (Exception e) {
            logger.error("Error while saving help item", e);
        }
        return null;
    }

    public static JSONObject saveRelation(JSONObject relation) throws PortalException, SystemException {
        int relationId = JSONConstants.getIntValue(relation, JSONConstants.RELATION_ID, 0);
        int itemId = relation.getInt(JSONConstants.ITEM_ID);
        int relatedItemId = relation.getInt(JSONConstants.RELATED_ITEM_ID);
        HelpRelation helpRelation;

        if (relationId == 0) {
            helpRelation = HelpRelationLocalServiceUtil.addHelpRelation(itemId, relatedItemId);
        } else {
            helpRelation = HelpRelationLocalServiceUtil.getHelpRelation(relationId);
            helpRelation.setRelatedItemId(relatedItemId);
            HelpRelationLocalServiceUtil.updateHelpRelation(helpRelation);
        }

        return convertRelationToJson(helpRelation);
    }

    public static JSONObject saveLink(JSONObject link) throws PortalException, SystemException {
        int linkId = JSONConstants.getIntValue(link, JSONConstants.RELATION_ID, 0);
        int itemId = link.getInt(JSONConstants.ITEM_ID);
        String linkUrl = link.getString(JSONConstants.LINK_URL);
        String linkName = link.getString(JSONConstants.LINK_NAME);
        HelpLink helpLink;

        if (linkId == 0) {
            helpLink = HelpLinkLocalServiceUtil.addHelpLink(itemId, linkUrl, linkName);
        } else {
            helpLink = HelpLinkLocalServiceUtil.getHelpLink(linkId);
            helpLink.setLinkUrl(linkUrl);
            helpLink.setLinkName(linkName);
            HelpLinkLocalServiceUtil.updateHelpLink(helpLink);
        }

        return convertLinkToJson(helpLink);
    }

    // Update a list of questions, create new ones, remove obsolete ones
    private static JSONArray updateQuestions(long itemId, JSONArray questions) {

        // Output
        JSONArray updatedJsonQuestions = new JSONArray();

        // Remove all existing questions
        HelpQuestionLocalServiceUtil.removeQuestionsForHelpItem(itemId);

        // Loop over new questions and add them
        if (questions != null) {
            for (int i = 0 ; i < questions.length() ; i++) {
                JSONObject jsonQuestion = questions.getJSONObject(i);
                String question = jsonQuestion.getString(JSONConstants.QUESTION);
                String answer = jsonQuestion.getString(JSONConstants.ANSWER);

                // Create new question
                HelpQuestion helpQuestion = HelpQuestionLocalServiceUtil.addHelpQuestion(itemId, question, answer);
                logger.info("Created new question for itemId "+itemId+" with id "+helpQuestion.getQuestionId());
                updatedJsonQuestions.put(convertQuestionToJson(helpQuestion));
            }
        }

        return updatedJsonQuestions;
    }


    public static JSONObject saveHelpCategory(String categoryName, long serviceId) {
        try {
            HelpCategory helpCategory = HelpCategoryLocalServiceUtil.createCategory(categoryName, serviceId);

            // Build updated category in JSON
            return convertCategoryToJson(helpCategory);
        } catch (Exception e) {
            logger.error("Error while saving help category", e);
        }

        return null;
    }

    private static JSONObject convertQuestionToJson(HelpQuestion helpQuestion) {
        JSONObject jsonQuestion = new JSONObject();

        jsonQuestion.put(JSONConstants.QUESTION_ID, helpQuestion.getQuestionId());
        jsonQuestion.put(JSONConstants.QUESTION, helpQuestion.getQuestion());
        jsonQuestion.put(JSONConstants.ANSWER, helpQuestion.getAnswer());

        return jsonQuestion;
    }


    private static JSONObject convertRelationToJson(HelpRelation helpRelation) {
        JSONObject jsonRelation = new JSONObject();

        jsonRelation.put(JSONConstants.RELATION_ID, helpRelation.getRelationId());
        jsonRelation.put(JSONConstants.RELATED_ITEM_ID, helpRelation.getRelatedItemId());

        // Get related item name
        HelpItem relatedItem;
        try {
            relatedItem = HelpItemLocalServiceUtil.getHelpItem(helpRelation.getRelatedItemId());
            jsonRelation.put(JSONConstants.RELATED_ITEM_NAME, relatedItem.getItemName());
        } catch (Exception e) {
            logger.debug(e);
        }

        return jsonRelation;
    }

    private static JSONObject convertCategoryToJson(HelpCategory category) {
        JSONObject jsonCategory = new JSONObject();

        jsonCategory.put(JSONConstants.CATEGORY_ID, category.getCategoryId());
        jsonCategory.put(JSONConstants.CATEGORY_NAME, category.getCategoryName());
        jsonCategory.put(JSONConstants.POSITION, category.getPosition());
        jsonCategory.put(JSONConstants.APPLICATION_ID, category.getServiceId());
        try {
            Application application = ApplicationLocalServiceUtil.getById(category.getServiceId());
            // App menu entry Id
            jsonCategory.put(JSONConstants.MENU_ENTRY_ID, application.getMenuEntryId());
        } catch (Exception e) {
            logger.error("Failed to get service from ID=" + category.getServiceId());
        }

        return jsonCategory;
    }

    private static JSONObject convertLinkToJson(HelpLink helpLink) {
        JSONObject jsonLink = new JSONObject();

        jsonLink.put(JSONConstants.LINK_ID, helpLink.getLinkId());
        jsonLink.put(JSONConstants.LINK_URL, helpLink.getLinkUrl());
        jsonLink.put(JSONConstants.LINK_NAME, helpLink.getLinkName());

        return jsonLink;
    }
}
