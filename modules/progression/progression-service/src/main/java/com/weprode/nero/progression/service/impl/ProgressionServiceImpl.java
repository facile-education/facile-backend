package com.weprode.nero.progression.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.progression.model.Progression;
import com.weprode.nero.progression.model.ProgressionFolder;
import com.weprode.nero.progression.model.ProgressionItem;
import com.weprode.nero.progression.service.ProgressionFolderLocalServiceUtil;
import com.weprode.nero.progression.service.ProgressionItemLocalServiceUtil;
import com.weprode.nero.progression.service.ProgressionLocalServiceUtil;
import com.weprode.nero.progression.service.base.ProgressionServiceBaseImpl;
import com.weprode.nero.progression.utils.ProgressionUtils;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=progression",
                "json.web.service.context.path=Progression"
        },
        service = AopService.class
)
public class ProgressionServiceImpl extends ProgressionServiceBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(ProgressionServiceImpl.class);

    @JSONWebService(value = "get-progression-list", method = "GET")
    public JSONObject getProgressionList() {
        JSONObject result = new JSONObject();

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
            // Students and parent should not have access to progression service
            if (RoleUtilsLocalServiceUtil.isStudent(user) || RoleUtilsLocalServiceUtil.isParent(user)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
            List<Progression> teacherProgressions = ProgressionLocalServiceUtil.getTeacherProgressions(user.getUserId());

            JSONArray progressionArray = new JSONArray();
            for (Progression progression : teacherProgressions) {
                progressionArray.put(progression.convertToJSON());
            }
            result.put("progressions", progressionArray);

        } catch (SystemException e) {
            logger.error("Could not get progression list for teacher "+user.getFullName()+" (id="+user.getUserId()+")", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "get-progression-content", method = "GET")
    public JSONObject getProgressionTree(long progressionId) {
        JSONObject result = new JSONObject();

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
            if (!ProgressionUtils.ownsProgression(user.getUserId(), progressionId)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            Progression progression = ProgressionLocalServiceUtil.getProgression(progressionId);
            result.put(JSONConstants.PROGRESSION, progression.convertToJSON());

            List<ProgressionFolder> sections = ProgressionFolderLocalServiceUtil.getProgressionRootFolders(progressionId);
            JSONArray sectionsArray = new JSONArray();
            for (ProgressionFolder section : sections) {
                JSONObject jsonSection = section.convertToJSON();

                // Get sub-sections
                List<ProgressionFolder> subSections = ProgressionFolderLocalServiceUtil.getSubFolders(section.getProgressionFolderId());
                JSONArray subSectionsArray = new JSONArray();
                for (ProgressionFolder subSection : subSections) {
                    JSONObject jsonSubSection = subSection.convertToJSON();

                    // Get sub-section items without content
                    List<ProgressionItem> subSectionItems = ProgressionItemLocalServiceUtil.getFolderItems(subSection.getProgressionFolderId());
                    JSONArray subSectionItemsArray = new JSONArray();
                    for (ProgressionItem subSectionItem : subSectionItems) {
                        subSectionItemsArray.put(subSectionItem.convertToJSON(user.getUserId(), false));
                    }
                    jsonSubSection.put(JSONConstants.ITEMS, subSectionItemsArray);

                    subSectionsArray.put(jsonSubSection);
                }
                jsonSection.put(JSONConstants.SUB_SECTIONS, subSectionsArray);

                // Get section items without content
                List<ProgressionItem> sectionItems = ProgressionItemLocalServiceUtil.getFolderItems(section.getProgressionFolderId());
                JSONArray sectionItemsArray = new JSONArray();
                for (ProgressionItem sectionItem : sectionItems) {
                    sectionItemsArray.put(sectionItem.convertToJSON(user.getUserId(), false));
                }
                jsonSection.put(JSONConstants.ITEMS, sectionItemsArray);

                sectionsArray.put(jsonSection);
            }
            result.put(JSONConstants.SECTIONS, sectionsArray);

        } catch (Exception e) {
            logger.error("Could not get progression tree for progressionId="+progressionId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "add-progression", method = "POST")
    public JSONObject addProgression(String name, String description, long subjectId, String volee, String color) {
        JSONObject result = new JSONObject();

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
            // Agents only are allowed to create a progression
            if (RoleUtilsLocalServiceUtil.isStudent(user) || RoleUtilsLocalServiceUtil.isParent(user)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            Progression progression = ProgressionLocalServiceUtil.addProgression(user.getUserId(), name, description, subjectId, volee, color);
            result.put("progression", progression.convertToJSON());
        } catch (Exception e) {
            logger.error("Could not add progression for "+user.getFullName()+" (id="+user.getUserId()+")", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "update-progression", method = "POST")
    public JSONObject updateProgression(long progressionId, String name, String description, long subjectId, String volee, String color) {
        JSONObject result = new JSONObject();

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
            // Check owner
            if (!ProgressionUtils.ownsProgression(user.getUserId(), progressionId)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
            ProgressionLocalServiceUtil.updateProgression(progressionId, name, description, subjectId, volee, color);
        } catch (Exception e) {
            logger.error("Could not update progression for "+user.getFullName()+" (id="+user.getUserId()+")", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "delete-progression", method = "DELETE")
    public JSONObject deleteProgression(long progressionId) {
        JSONObject result = new JSONObject();

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
            // Check owner
            if (!ProgressionUtils.ownsProgression(user.getUserId(), progressionId)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            ProgressionLocalServiceUtil.deleteProgressionById(user.getUserId(), progressionId);
            logger.info("User "+user.getFullName()+" (id="+user.getUserId()+") has deleted progressionId "+progressionId);

        } catch (Exception e) {
            logger.error("Error deleting progressionId "+progressionId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}
