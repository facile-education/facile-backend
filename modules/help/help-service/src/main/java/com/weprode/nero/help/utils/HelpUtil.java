package com.weprode.nero.help.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ParamUtil;
import com.weprode.nero.application.model.Application;
import com.weprode.nero.application.service.ApplicationLocalServiceUtil;
import com.weprode.nero.application.service.BroadcastLocalServiceUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.help.model.*;
import com.weprode.nero.help.service.*;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;

import javax.portlet.ResourceRequest;
import java.util.ArrayList;
import java.util.List;

public class HelpUtil {

    private HelpUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final Log logger = LogFactoryUtil.getLog(HelpUtil.class);

    // Returns the whole help tree with categories and help items, with filtering if search terms are provided
    public static JSONArray getHelpTree(User user, String searchTerms) {
        JSONArray helpTree = JSONFactoryUtil.createJSONArray();

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

                    JSONArray jsonHelpItems = JSONFactoryUtil.createJSONArray();
                    for (HelpItem helpItem : helpItems) {
                        if (HelpItemRoleLocalServiceUtil.isUserAllowedToSeeItem(user, helpItem.getItemId())) {
                            JSONObject jsonHelpItem = JSONFactoryUtil.createJSONObject();
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
        JSONObject jsonHelpItem = JSONFactoryUtil.createJSONObject();

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
            JSONArray jsonQuestions = JSONFactoryUtil.createJSONArray();
            List<HelpQuestion> helpQuestions = HelpQuestionLocalServiceUtil.getHelpQuestions(helpItem.getItemId());
            if (helpQuestions != null) {
                for (HelpQuestion helpQuestion : helpQuestions) {
                    jsonQuestions.put(convertQuestionToJson(helpQuestion));
                }
            }
            jsonHelpItem.put(JSONConstants.QUESTIONS, jsonQuestions);

            // Related items
            JSONArray jsonRelations = JSONFactoryUtil.createJSONArray();
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
                JSONArray jsonLinks = JSONFactoryUtil.createJSONArray();
                for (HelpLink helpLink : helpLinks) {
                    jsonLinks.put(convertLinkToJson(helpLink));
                }
                jsonHelpItem.put(JSONConstants.LINKS, jsonLinks);
            }
        }

        return jsonHelpItem;
    }

    public static void saveHelpItemPosition(long categoryId, JSONObject jsonHelpItem) throws PortalException, SystemException {
        long itemId = jsonHelpItem.getLong(JSONConstants.ITEM_ID, 0);
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
            long itemId = jsonHelpItem.getLong(JSONConstants.ITEM_ID, 0);
            String itemName = jsonHelpItem.getString(JSONConstants.ITEM_NAME);
            String videoUrl = jsonHelpItem.getString(JSONConstants.VIDEO_URL);
            String videoDescription = jsonHelpItem.getString(JSONConstants.VIDEO_DESCRIPTION);
            String textualHelp = jsonHelpItem.getString(JSONConstants.MANUAL);
            int position = jsonHelpItem.getInt(JSONConstants.POSITION);
            String language = jsonHelpItem.getString(JSONConstants.LANGUAGE);
            JSONArray roles = jsonHelpItem.getJSONArray(JSONConstants.ROLES);
            boolean isManagement = jsonHelpItem.getBoolean(JSONConstants.IS_MANAGEMENT);

            HelpItem helpItem = null;
            if (itemId == 0) {
                // Creation
                helpItem = HelpItemLocalServiceUtil.createItem(categoryId, itemName, videoUrl, videoDescription, textualHelp, language, isManagement);

                // Update json with created position and id
                jsonHelpItem.put(JSONConstants.ITEM_ID, helpItem.getItemId());
                jsonHelpItem.put(JSONConstants.POSITION, helpItem.getPosition());

                // Add roles if any
                if (roles != null) {
                    for (int idx = 0 ; idx < roles.length() ; idx++) {
                        long roleId = roles.getJSONObject(idx).getLong("roleId");
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
            JSONArray questions = jsonHelpItem.getJSONArray(JSONConstants.QUESTIONS);
            JSONArray updatedQuestions = updateQuestions(itemId, questions);
            jsonHelpItem.put(JSONConstants.QUESTIONS, updatedQuestions);

            // Related items
            // JSONArray relations = jsonHelpItem.getJSONArray(JSONConstants.HELP_RELATIONS);
            // JSONArray updatedRelations = updateRelations(itemId, relations);
            // jsonHelpItem.put(JSONConstants.HELP_RELATIONS, updatedRelations);

            // Links
            // JSONArray links = jsonHelpItem.getJSONArray(JSONConstants.HELP_LINKS);
            // JSONArray updatedLinks = updateLinks(itemId, links);
            // jsonHelpItem.put(JSONConstants.HELP_LINKS, updatedLinks);

            return jsonHelpItem;
        } catch (Exception e) {
            logger.error("Error while saving help item", e);
        }
        return null;
    }

    public static JSONObject saveRelation(JSONObject relation) throws PortalException, SystemException {
        int relationId = relation.getInt(JSONConstants.RELATION_ID, 0);
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
        int linkId = link.getInt(JSONConstants.RELATION_ID, 0);
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
        JSONArray updatedJsonQuestions = JSONFactoryUtil.createJSONArray();

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

    // Update a list of relations, create new ones, remove obsolete ones
    private static JSONArray updateRelations(long itemId, JSONArray relations) {
        // Output
        JSONArray updatedJsonRelations = JSONFactoryUtil.createJSONArray();

        // Remove all existing relations
        HelpRelationLocalServiceUtil.removeRelationsForHelpItem(itemId);

        // Loop over new relations and add them
        if (relations != null) {
            for (int i = 0 ; i < relations.length() ; i++) {
                JSONObject jsonRelation = relations.getJSONObject(i);
                long relatedItemId = jsonRelation.getLong(JSONConstants.RELATED_ITEM_ID);

                // Create new relation
                HelpRelation helpRelation = HelpRelationLocalServiceUtil.addHelpRelation(itemId, relatedItemId);
                logger.info("Created new relation for itemId "+itemId+" with id "+helpRelation.getRelationId());
                updatedJsonRelations.put(convertRelationToJson(helpRelation));
            }
        }

        return updatedJsonRelations;
    }

    // Update a list of links, create new ones, remove obsolete ones
    private static JSONArray updateLinks(long itemId, JSONArray links) {
        // Output
        JSONArray updatedJsonLinks = JSONFactoryUtil.createJSONArray();

        // Remove all existing links
        HelpLinkLocalServiceUtil.removeLinksForHelpItem(itemId);

        // Loop over new links and add them
        if (links != null) {
            for (int i = 0 ; i < links.length() ; i++) {
                JSONObject jsonLink = links.getJSONObject(i);
                String linkUrl = jsonLink.getString(JSONConstants.LINK_URL);
                String linkName = jsonLink.getString(JSONConstants.LINK_NAME);

                // Create new link
                HelpLink helpLink = HelpLinkLocalServiceUtil.addHelpLink(itemId, linkUrl, linkName);
                logger.info("Created new link for itemId "+itemId+" with id "+helpLink.getLinkId());
                updatedJsonLinks.put(convertLinkToJson(helpLink));
            }
        }

        return updatedJsonLinks;
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

    public static JSONArray getEntServices() {
        JSONArray jsonServices = JSONFactoryUtil.createJSONArray();

        try {
            List<Application> entApps = ApplicationLocalServiceUtil.getAllApplications();
            if (entApps != null) {
                for (Application app : entApps) {
                    JSONObject jsonService = JSONFactoryUtil.createJSONObject();
                    jsonService.put(JSONConstants.APPLICATION_ID, app.getApplicationId());
                    jsonService.put(JSONConstants.APPLICATION_NAME, app.getApplicationName());
                    jsonService.put(JSONConstants.APPLICATION_KEY, app.getApplicationKey());
                    jsonServices.put(jsonService);
                }
            }
        } catch (Exception e) {
            logger.error("Error when fetching all ENT services");
        }

        return jsonServices;
    }

    public static boolean deleteCategory(ResourceRequest resourceRequest) {
        try {
            long categoryId = ParamUtil.getLong(resourceRequest, JSONConstants.CATEGORY_ID, 0);
            // TODO Delete all items HelpItemLocalServiceUtil.deleteHelpItem(itemId)
            HelpCategoryLocalServiceUtil.deleteHelpCategory(categoryId);

            return true;
        } catch (Exception e) {
            logger.error("Error when deleting help item", e);
        }

        return false;
    }

    private static JSONObject convertQuestionToJson(HelpQuestion helpQuestion) {
        JSONObject jsonQuestion = JSONFactoryUtil.createJSONObject();

        jsonQuestion.put(JSONConstants.QUESTION_ID, helpQuestion.getQuestionId());
        jsonQuestion.put(JSONConstants.QUESTION, helpQuestion.getQuestion());
        jsonQuestion.put(JSONConstants.ANSWER, helpQuestion.getAnswer());

        return jsonQuestion;
    }


    private static JSONObject convertRelationToJson(HelpRelation helpRelation) {
        JSONObject jsonRelation = JSONFactoryUtil.createJSONObject();

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
        JSONObject jsonCategory = JSONFactoryUtil.createJSONObject();

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
        JSONObject jsonLink = JSONFactoryUtil.createJSONObject();

        jsonLink.put(JSONConstants.LINK_ID, helpLink.getLinkId());
        jsonLink.put(JSONConstants.LINK_URL, helpLink.getLinkUrl());
        jsonLink.put(JSONConstants.LINK_NAME, helpLink.getLinkName());

        return jsonLink;
    }
}
