package com.weprode.nero.news.service.impl;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.HtmlParserUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.contact.constants.ContactConstants;
import com.weprode.nero.contact.service.ContactLocalServiceUtil;
import com.weprode.nero.document.service.DocumentUtilsLocalServiceUtil;
import com.weprode.nero.document.service.FileUtilsLocalServiceUtil;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.nero.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.nero.group.constants.ActivityConstants;
import com.weprode.nero.group.service.CommunityInfosLocalServiceUtil;
import com.weprode.nero.group.service.GroupUtilsLocalServiceUtil;
import com.weprode.nero.news.model.News;
import com.weprode.nero.news.model.NewsPopulation;
import com.weprode.nero.news.service.NewsAttachedFileLocalServiceUtil;
import com.weprode.nero.news.service.NewsLocalServiceUtil;
import com.weprode.nero.news.service.NewsPopulationLocalServiceUtil;
import com.weprode.nero.news.service.NewsReadLocalServiceUtil;
import com.weprode.nero.news.service.base.NewsLocalServiceBaseImpl;
import com.weprode.nero.organization.constants.OrgConstants;
import com.weprode.nero.organization.service.OrgDetailsLocalServiceUtil;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.role.constants.NeroRoleConstants;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.user.service.NewsAdminLocalServiceUtil;
import com.weprode.nero.user.service.UserUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(
        property = "model.class.name=com.weprode.nero.news.model.News",
        service = AopService.class
)
public class NewsLocalServiceImpl extends NewsLocalServiceBaseImpl {

    static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:sss";

    private static final Log logger = LogFactoryUtil.getLog(NewsLocalServiceImpl.class);

    @Indexable(type = IndexableType.REINDEX)
    public News addNews(long authorId, String title, String content, boolean isSchoolNews, boolean isImportant,
                        long imageId, Date publicationDate, Date expirationDate, JSONArray populations, List<Long> attachFileIds) {
        logger.info("User " + authorId + " creates a news named " + title + " for " + populations.length() + " groups and with " + (attachFileIds != null ? attachFileIds.size() : 0) + " attached files");

        try {
            News news = newsPersistence.create(counterLocalService.increment());
            news.setAuthorId(authorId);
            news.setTitle(title);
            news.setContent(content);
            news.setIsSchoolNews(isSchoolNews);
            news.setIsImportant(isImportant);
            news.setPublicationDate(publicationDate);
            news.setExpirationDate(expirationDate);
            news.setModificationDate(new Date());
            news = newsPersistence.updateImpl(news);

            // Create populations
            for (int idx = 0 ; idx < populations.length() ; idx++) {
                JSONObject population = populations.getJSONObject(idx);
                NewsPopulationLocalServiceUtil.addPopulation(news.getNewsId(), population.getLong(JSONConstants.GROUP_ID), population.getLong(JSONConstants.ROLE_ID));
            }

            // Thumbnail
            createNewsThumbnail(authorId, news, imageId);

            // Attached files
            manageAttachedFiles(news.getNewsId(), populations, attachFileIds, true);

            // Mark the news as read for the author
            NewsReadLocalServiceUtil.setNewsRead(authorId, news.getNewsId());

            return news;
        } catch (Exception e) {
            logger.error("Error creating news", e);
        }

        return null;
    }

    @Indexable(type = IndexableType.REINDEX)
    public News editNews(long newsId, String title, String content, boolean isImportant,
                         long imageId, Date publicationDate, Date expirationDate, JSONArray populations, List<Long> attachFileIds) {
        logger.info("Author edits news " + newsId + " named " + title + " for " + populations.length() + " groups and with " + (attachFileIds != null ? attachFileIds.size() : 0) + " attached files");

        try {
            News news = newsPersistence.fetchByPrimaryKey(newsId);
            news.setTitle(title);
            news.setContent(content);
            news.setIsImportant(isImportant);
            news.setPublicationDate(publicationDate);
            news.setExpirationDate(expirationDate);
            news.setModificationDate(new Date());
            news = newsPersistence.updateImpl(news);

            // Delete and re-create populations
            newsPopulationPersistence.removeBynewsId(newsId);
            for (int idx = 0 ; idx < populations.length() ; idx++) {
                JSONObject population = populations.getJSONObject(idx);
                NewsPopulationLocalServiceUtil.addPopulation(news.getNewsId(), population.getLong(JSONConstants.GROUP_ID), population.getLong(JSONConstants.ROLE_ID));
            }

            // Thumbnail
            updateNewsThumbnail(news.getAuthorId(), news, imageId);

            manageAttachedFiles(news.getNewsId(), populations, attachFileIds, false);

            return news;
        } catch (Exception e) {
            logger.error("Error editing news", e);
        }

        return null;
    }


    @Indexable(type = IndexableType.DELETE)
    public News deleteNewsAndDependencies(News news) throws SystemException {
        // Delete attached files before populations because we need populations here
        NewsAttachedFileLocalServiceUtil.deleteByNewsId(news.getNewsId());

        try {
            deleteNewsThumbnail(news);
        } catch (Exception e) {
            logger.error("Cannot remove properly news thumbnail for newsId " + news.getNewsId(), e);
        }

        // Delete populations
        NewsPopulationLocalServiceUtil.deleteByNewsId(news.getNewsId());

        // Delete news read
        NewsReadLocalServiceUtil.deleteByNewsId(news.getNewsId());

        return newsPersistence.remove(news);
    }

    public List<News> getNews(User user, long groupId, Date maxDate, int nbNews, boolean groupNews, boolean importantOnly, boolean unreadOnly) throws SystemException {
        // Get user role ids
        List<Role> roles = RoleLocalServiceUtil.getUserRoles(user.getUserId());

        List<Long> roleIds = new ArrayList<>();
        for (Role role : roles) {
            roleIds.add(role.getRoleId());
        }
        // For communities and one role school level orgs (Enseignants, Personnels...)
        roleIds.add((long) 0);

        // Get user groupIds
        List<Long> groupIds = new ArrayList<>();
        if (groupId == 0) {
            groupIds.addAll(UserUtilsLocalServiceUtil.getUserGroupIds(user.getUserId()));
        } else {
            groupIds.add(groupId);
        }

        return newsFinder.getNews(user.getUserId(), groupIds, roleIds, maxDate, nbNews, groupNews, importantOnly, unreadOnly);
    }

    public int getNewsCount(User user, long groupId, boolean groupNews, boolean importantOnly, boolean unreadOnly) throws SystemException {

        // Get user role ids
        List<Role> roles = RoleLocalServiceUtil.getUserRoles(user.getUserId());
        List<Long> roleIds = new ArrayList<>();
        for (Role role : roles) {
            roleIds.add(role.getRoleId());
        }
        // For communities and one role school level orgs (Enseignants, Personnels...)
        roleIds.add((long) 0);

        // Get user groupIds
        List<Long> groupIds = new ArrayList<>();
        if (groupId == 0) {
            groupIds.addAll(UserUtilsLocalServiceUtil.getUserGroupIds(user.getUserId()));
        } else {
            groupIds.add(groupId);
        }

        return newsFinder.getNewsCount(user.getUserId(), groupIds, roleIds, groupNews, importantOnly, unreadOnly);
    }

    public List<News> getNewsActivities(User user, long groupId, Date minDate, Date maxDate, int nbNews) throws SystemException {
        // Get user role ids
        List<Role> roles = RoleLocalServiceUtil.getUserRoles(user.getUserId());

        List<Long> roleIds = new ArrayList<>();
        for (Role role : roles) {
            roleIds.add(role.getRoleId());
        }
        // For communities and one role school level orgs (Enseignants, Personnels...)
        roleIds.add((long) 0);

        // Get user groupIds
        List<Long> groupIds = new ArrayList<>();
        if (groupId == 0) {
            groupIds.addAll(UserUtilsLocalServiceUtil.getUserGroupIds(user.getUserId()));
        } else {
            groupIds.add(groupId);
        }

        return newsFinder.getNewsActivities(user.getUserId(), groupIds, roleIds, minDate, maxDate, nbNews);
    }


    public JSONObject getGroupNewsBroadcastGroups(User user) {
        JSONObject result = new JSONObject();

        try {
            // First, loop over user's schools
            JSONObject broadcastTree = new JSONObject();

            List<Organization> userSchools = UserOrgsLocalServiceUtil.getUserSchools(user);
            JSONArray jsonSchools = new JSONArray();
            for (Organization userSchool : userSchools) {

                JSONObject jsonSchool = new JSONObject();
                jsonSchool.put(JSONConstants.SCHOOL_NAME, OrgUtilsLocalServiceUtil.formatOrgName(userSchool.getName(), true));
                jsonSchool.put(JSONConstants.SCHOOL_ORG_ID, userSchool.getOrganizationId());

                List<Integer> types = new ArrayList<>();

                // Teachers can write news to theirs cours, classes and subjects only
                if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
                    types.add(OrgConstants.COURS_TYPE);
                    List<Organization> schoolCours = UserOrgsLocalServiceUtil.getUserOrganizations(user.getUserId(), types, null, false, userSchool.getOrganizationId());
                    if (!schoolCours.isEmpty()) {
                        jsonSchool.put(JSONConstants.COURS, getOrgTypePopulations(schoolCours, user.getUserId()));
                    }

                    types.add(OrgConstants.CLASS_TYPE);
                    List<Organization> schoolClasses = UserOrgsLocalServiceUtil.getUserOrganizations(user.getUserId(), types, null, false, userSchool.getOrganizationId());
                    if (!schoolClasses.isEmpty()) {
                        jsonSchool.put(JSONConstants.CLASSES, getOrgTypePopulations(schoolClasses, user.getUserId()));
                    }

                    types = new ArrayList<>();
                    types.add(OrgConstants.SUBJECT_TYPE);
                    List<Organization> schoolSubjects = UserOrgsLocalServiceUtil.getUserOrganizations(user.getUserId(), types, null, false, userSchool.getOrganizationId());
                    if (!schoolSubjects.isEmpty()) {
                        jsonSchool.put(JSONConstants.SUBJECTS, getOrgTypePopulations(schoolSubjects, user.getUserId()));
                    }
                }

                // Classes

                List<Organization> schoolClasses = new ArrayList<>();
                // Personals can write news to no class
//                if (RoleUtilsLocalServiceUtil.isConseillerOrientation(user) ||
//                        RoleUtilsLocalServiceUtil.isSecretariat(user) ||
//                        RoleUtilsLocalServiceUtil.isAdministrative(user) ||
//                        RoleUtilsLocalServiceUtil.isInfirmiere(user) ||
//                        RoleUtilsLocalServiceUtil.isBibliothecaire(user)) {
//                    types.add(OrgConstants.CLASS_TYPE);
//                    schoolClasses.addAll(OrgUtilsLocalServiceUtil.getSchoolOrganizations(userSchool.getOrganizationId(), types, null, false));
//                }
                // Main teachers
                if (RoleUtilsLocalServiceUtil.isMainTeacher(user)) {
                    schoolClasses.addAll(UserOrgsLocalServiceUtil.getAffectedClasses(user, RoleUtilsLocalServiceUtil.getMainTeacherRole().getRoleId()));
                }
                // Doyens, psychologues and conseiller social
                schoolClasses.addAll(UserOrgsLocalServiceUtil.getRoleAffectedClasses(user));

                if (!schoolClasses.isEmpty()) {
                    jsonSchool.put(JSONConstants.CLASSES, getOrgTypePopulations(schoolClasses, user.getUserId()));
                }
                jsonSchools.put(jsonSchool);
            }
            broadcastTree.put(JSONConstants.SCHOOLS, jsonSchools);

            // Communities
            JSONArray jsonCommunities = new JSONArray();
            List<Group> userGroups = CommunityInfosLocalServiceUtil.getUserCommunities(user.getUserId(), false, true);
            for (Group userGroup : userGroups) {
                JSONObject jsonCommunity = new JSONObject();
                jsonCommunity.put(JSONConstants.GROUP_NAME, userGroup.getName());
                jsonCommunity.put(JSONConstants.POPULATION_NAME, userGroup.getName());
                jsonCommunity.put(JSONConstants.GROUP_ID, userGroup.getGroupId());
                jsonCommunity.put(JSONConstants.ROLE_ID, 0);
                jsonCommunity.put(JSONConstants.IS_COMMUNITY, true);
                jsonCommunities.put(jsonCommunity);
            }
            broadcastTree.put(ContactConstants.COMMUNITIES, jsonCommunities);

            result.put(JSONConstants.SCHOOLS_GROUPS, broadcastTree);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error when fetching groups to broadcast a group news", e);
            result.put(JSONConstants.SUCCESS, false);
        }
        return result;
    }

    /**
     * Get groups available to be broadcasted a news for school
     */
    public JSONObject getSchoolNewsBroadcastGroups(User user) {
        JSONObject result = new JSONObject();

        try {
            // First, loop over user's schools
            JSONArray broadcastTree = new JSONArray();
            List<Organization> userSchools = UserOrgsLocalServiceUtil.getUserSchools(user);
            for (Organization userSchool : userSchools) {

                if (RoleUtilsLocalServiceUtil.isDirectionMember(user)
                        || RoleUtilsLocalServiceUtil.isSchoolAdmin(user, userSchool.getOrganizationId())
                        || NewsAdminLocalServiceUtil.isUserSchoolDelegate(user, userSchool.getOrganizationId())) {

                    JSONObject jsonSchool = new JSONObject();
                    jsonSchool.put(JSONConstants.SCHOOL_NAME, OrgUtilsLocalServiceUtil.formatOrgName(userSchool.getName(), true));
                    jsonSchool.put(JSONConstants.SCHOOL_ORG_ID, userSchool.getOrganizationId());
                    jsonSchool.put(JSONConstants.POPULATIONS, getSchoolPopulations(userSchool.getGroupId(), userSchool.getOrganizationId(), user.getUserId()));

                    // Volées
                    List<Integer> types = new ArrayList<>();
                    types.add(OrgConstants.VOLEE_TYPE);
                    List<Organization> schoolVolees = OrgUtilsLocalServiceUtil.getSchoolOrganizations(userSchool.getOrganizationId(), types, null, false);
                    if (!schoolVolees.isEmpty()) {
                        jsonSchool.put(JSONConstants.VOLEES, getOrgTypePopulations(schoolVolees, user.getUserId()));
                    }

                    // Classes
                    types = new ArrayList<>();
                    types.add(OrgConstants.CLASS_TYPE);
                    List<Organization> schoolClasses = OrgUtilsLocalServiceUtil.getSchoolOrganizations(userSchool.getOrganizationId(), types, null, false);
                    if (!schoolClasses.isEmpty()) {
                        jsonSchool.put(JSONConstants.CLASSES, getOrgTypePopulations(schoolClasses, user.getUserId()));
                    }

                    // Subjects
                    types = new ArrayList<>();
                    types.add(OrgConstants.SUBJECT_TYPE);
                    List<Organization> schoolSubjects = OrgUtilsLocalServiceUtil.getSchoolOrganizations(userSchool.getOrganizationId(), types, null, false);
                    if (!schoolSubjects.isEmpty()) {
                        jsonSchool.put(JSONConstants.SUBJECTS, getOrgTypePopulations(schoolSubjects, user.getUserId()));
                    }
                    broadcastTree.put(jsonSchool);
                }
            }

            result.put(JSONConstants.SCHOOLS_GROUPS, broadcastTree);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error when fetching group to broadcast a news", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    private JSONArray getOrgTypePopulations(List<Organization> orgs, long userId) {
        JSONArray jsonGroups = new JSONArray();

        for (Organization org : orgs) {
            try {
                JSONObject jsonGroup = new JSONObject();
                jsonGroup.put(JSONConstants.GROUP_NAME, OrgUtilsLocalServiceUtil.formatOrgName(org.getName(), false));
                jsonGroup.put(JSONConstants.GROUP_ID, org.getGroupId());

                boolean isSubject = OrgDetailsLocalServiceUtil.isSubject(org.getOrganizationId());
                long teacherRoleId = RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId();
                jsonGroup.put(JSONConstants.ROLE_ID, isSubject ? teacherRoleId : 0);
                jsonGroup.put(JSONConstants.IS_SELECTABLE, isSubject);
                // No sub-population for subjects
                if (!isSubject) {
                    jsonGroup.put(JSONConstants.POPULATIONS, getOrgPopulations(org, userId));
                } else {
                    String populationName = OrgUtilsLocalServiceUtil.formatOrgName(org.getName(), false);
                    jsonGroup.put(JSONConstants.POPULATION_NAME, populationName);
                }
                jsonGroups.put(jsonGroup);
            } catch (Exception e) {
                logger.error("Error building news populations for user " + userId, e);
            }
        }

        return jsonGroups;
    }

    // For classes, cours and volees: students, parents, teachers
    // For volees : main teachers
    private JSONArray getOrgPopulations(Organization org, long userId) {
        JSONArray rolePopulations = new JSONArray();

        try {
            int type = OrgDetailsLocalServiceUtil.getOrgDetails(org.getOrganizationId()).getType();
            if (type == OrgConstants.CLASS_TYPE
                    || type == OrgConstants.COURS_TYPE
                    || type == OrgConstants.VOLEE_TYPE) {

                rolePopulations.put(getJsonPopulation(org.getGroupId(), org.getOrganizationId(), RoleUtilsLocalServiceUtil.getStudentRole().getRoleId(), NeroRoleConstants.STUDENT_INCLUSIVE, userId));
                rolePopulations.put(getJsonPopulation(org.getGroupId(), org.getOrganizationId(), RoleUtilsLocalServiceUtil.getParentRole().getRoleId(), NeroRoleConstants.PARENT_INCLUSIVE, userId));
                rolePopulations.put(getJsonPopulation(org.getGroupId(), org.getOrganizationId(), RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId(), NeroRoleConstants.TEACHER_INCLUSIVE, userId));

                if (type == OrgConstants.VOLEE_TYPE) {
                    rolePopulations.put(getJsonPopulation(org.getGroupId(), org.getOrganizationId(), RoleUtilsLocalServiceUtil.getMainTeacherRole().getRoleId(), NeroRoleConstants.MAIN_TEACHER_INCLUSIVE, userId));
                }
            }
        } catch (Exception e) {
            logger.error("Error building news populations", e);
        }

        return rolePopulations;
    }

    // For schools: students, parents, teachers and 4 PATs
    private JSONArray getSchoolPopulations(long groupId, long orgId, long userId) {
        JSONArray rolePopulations = new JSONArray();

        try {
            rolePopulations.put(getJsonPopulation(groupId, orgId, RoleUtilsLocalServiceUtil.getStudentRole().getRoleId(), NeroRoleConstants.STUDENT_INCLUSIVE, userId));
            rolePopulations.put(getJsonPopulation(groupId, orgId, RoleUtilsLocalServiceUtil.getParentRole().getRoleId(), NeroRoleConstants.PARENT_INCLUSIVE, userId));
            rolePopulations.put(getJsonPopulation(groupId, orgId, RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId(), NeroRoleConstants.TEACHER_INCLUSIVE, userId));
            rolePopulations.put(getJsonPopulation(groupId, orgId, RoleUtilsLocalServiceUtil.getAssistantTechniqueRole().getRoleId(), NeroRoleConstants.ASSISTANT_TECHNIQUE_INCLUSIVE, userId));
            rolePopulations.put(getJsonPopulation(groupId, orgId, RoleUtilsLocalServiceUtil.getCaissierComptableRole().getRoleId(), NeroRoleConstants.CAISSIER_COMPTABLE_INCLUSIVE, userId));
            rolePopulations.put(getJsonPopulation(groupId, orgId, RoleUtilsLocalServiceUtil.getBibliothecaireRole().getRoleId(), NeroRoleConstants.BIBLIOTHECAIRE_INCLUSIVE, userId));
            rolePopulations.put(getJsonPopulation(groupId, orgId, RoleUtilsLocalServiceUtil.getSecretariatRole().getRoleId(), NeroRoleConstants.SECRETAIRE_INCLUSIVE, userId));
        } catch (Exception e) {
            logger.error("Error building news populations for school", e);
        }

        return rolePopulations;
    }

    private JSONObject getJsonPopulation(long groupId, long orgId, long roleId, String groupName, long userId) {

        JSONObject population = new JSONObject();
        population.put(JSONConstants.GROUP_ID, groupId);
        population.put(JSONConstants.ORG_ID, orgId);
        population.put(JSONConstants.GROUP_NAME, groupName);
        population.put(JSONConstants.ROLE_ID, roleId);
        population.put(JSONConstants.POPULATION_NAME, ContactLocalServiceUtil.getPopulationName(orgId, roleId, userId));

        return population;
    }

    public boolean hasUserNews(long userId, long newsId) {
        try {
            // Return true if the user is the author
            News news = NewsLocalServiceUtil.getNews(newsId);
            if (news.getAuthorId() == userId) {
                return true;
            }

            // Get user group ids
            List<Long> userGroupIds = UserUtilsLocalServiceUtil.getUserGroupIds(userId);

            // Get user role ids
            List<Role> roles = RoleLocalServiceUtil.getUserRoles(userId);
            List<Long> roleIds = new ArrayList<>();
            for (Role role : roles) {
                roleIds.add(role.getRoleId());
            }

            // Loop over populations and match both groupId and roleId
            List<NewsPopulation> newsPopulations = newsPopulationPersistence.findBynewsId(newsId);
            if (newsPopulations != null) {
                for (NewsPopulation newsPopulation : newsPopulations) {
                    if ((newsPopulation.getRoleId() == 0 || roleIds.contains(newsPopulation.getRoleId())) && userGroupIds.contains(newsPopulation.getGroupId())) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error checking if user " + userId + " has the view on newsId " + newsId, e);
        }

        return false;
    }

    public JSONObject convertNewsToJson(long newsId, long userId, boolean withDetails) throws SystemException, PortalException {
        JSONObject jsonNews = new JSONObject();

        News news = NewsLocalServiceUtil.getNews(newsId);
        jsonNews.put(JSONConstants.AUTHOR_ID, news.getAuthorId());
        User author = UserLocalServiceUtil.getUser(news.getAuthorId());
        jsonNews.put(JSONConstants.AUTHOR_NAME, author.getFullName());
        jsonNews.put(JSONConstants.NEWS_ID, news.getNewsId());
        jsonNews.put(JSONConstants.TITLE, news.getTitle());
        if (withDetails) {
            jsonNews.put(JSONConstants.CONTENT, news.getContent());
        } else {
            String text = HtmlParserUtil.extractText(news.getContent());
            jsonNews.put(JSONConstants.SHORT_CONTENT, text.substring(0, Math.min(200, text.length())));
        }
        jsonNews.put(JSONConstants.TYPE, ActivityConstants.TYPE_NEWS);
        jsonNews.put(JSONConstants.IS_IMPORTANT, news.isIsImportant());
        jsonNews.put(JSONConstants.PUBLICATION_DATE, new SimpleDateFormat(DATE_FORMAT).format(news.getPublicationDate()));
        jsonNews.put(JSONConstants.EXPIRATION_DATE, new SimpleDateFormat(DATE_FORMAT).format(news.getExpirationDate()));
        jsonNews.put(JSONConstants.HAS_READ, NewsReadLocalServiceUtil.hasUserReadNews(userId, newsId));
        jsonNews.put(JSONConstants.HAS_ATTACHED_FILES, NewsAttachedFileLocalServiceUtil.hasAttachedFiles(newsId));
        jsonNews.put(JSONConstants.IS_SCHOOL_NEWS, news.getIsSchoolNews());
        // Author and direction can edit/delete the event
        User user = UserLocalServiceUtil.getUser(userId);
        if (news.getIsSchoolNews()) {
            jsonNews.put(JSONConstants.IS_EDITABLE, news.getAuthorId() == userId || RoleUtilsLocalServiceUtil.isDirectionMember(user));
        } else {
            jsonNews.put(JSONConstants.IS_EDITABLE, news.getAuthorId() == userId);
        }
        // Thumbnail
        if (news.getImageId() != 0) {
            try {
                FileEntry thumbnailFileEntry = DLAppServiceUtil.getFileEntry(news.getImageId());
                jsonNews.put(JSONConstants.THUMBNAIL_URL, FileUtilsLocalServiceUtil.getDisplayUrl(thumbnailFileEntry, 0, "", user.getUserId(), true));
            } catch (Exception e) {
                logger.error("Cannot retrieve thumbnail for news " + news.getNewsId() + ", thumbnail fileId = " + news.getImageId(), e);
            }

        } else {
            if (news.getIsSchoolNews()) {
                jsonNews.put(JSONConstants.THUMBNAIL_URL, JSONConstants.SCHOOL_NEWS_DEFAULT_THUMBNAIL);
            } else {
                jsonNews.put(JSONConstants.THUMBNAIL_URL, JSONConstants.NEWS_DEFAULT_THUMBNAIL);
            }
        }

        // Group name is the first found in the news group list to which belongs the user
        List<Long> userGroupIds = UserUtilsLocalServiceUtil.getUserGroupIds(userId);
        List<NewsPopulation> populations = NewsPopulationLocalServiceUtil.getNewsPopulations(newsId);
        for (NewsPopulation population : populations) {
            if (userGroupIds.contains(population.getGroupId()) && (population.getRoleId() == 0 || RoleLocalServiceUtil.hasUserRole(userId, population.getRoleId()))) {
                jsonNews.put(JSONConstants.GROUP_NAME, GroupUtilsLocalServiceUtil.getGroupName(population.getGroupId()));
                break;
            }
        }

        if (withDetails) {
            jsonNews.put(JSONConstants.THUMBNAIL_ID, news.getImageId());
            jsonNews.put(JSONConstants.ATTACHED_FILES, NewsAttachedFileLocalServiceUtil.convertNewsFiles(newsId, userId));
            if (news.getAuthorId() == userId) {
                jsonNews.put(JSONConstants.POPULATIONS, NewsPopulationLocalServiceUtil.convertNewsPopulations(newsId, userId));
                jsonNews.put(JSONConstants.READ_INFOS, NewsReadLocalServiceUtil.getNewsReadStatus(newsId));
            }
        }

        return jsonNews;
    }

    private void createNewsThumbnail(long userId, News news, long fileId) {
        try {
            if (fileId > 0) {
                // Get original file
                FileEntry originalPicture = DLAppServiceUtil.getFileEntry(fileId);

                // Get or create news thumbnail folder
                Folder thumbnailFolder = FolderUtilsLocalServiceUtil.getThumbnailFolder(userId);

                // Copy (or move if original file belong to user tempFolder) file to thumbnail folder
                FileEntry thumbnail;
                if (DocumentUtilsLocalServiceUtil.belongToTmpFolder(originalPicture, userId)) {
                    thumbnail = FileUtilsLocalServiceUtil.moveFileEntry(
                            userId,
                            fileId,
                            thumbnailFolder.getFolderId()
                    );
                } else {
                    thumbnail = FileUtilsLocalServiceUtil.copyFileEntry(
                            userId,
                            originalPicture.getFileEntryId(),
                            thumbnailFolder.getFolderId(),
                            true
                    );
                }
                thumbnail = FileUtilsLocalServiceUtil.renameFile(userId, thumbnail, String.valueOf(news.getNewsId()));  // Rename thumbnail with the newsId value
                PermissionUtilsLocalServiceUtil.setViewPermissionOnResource(thumbnail); // All ent users can view any thumbnail file

                // Set news imageId to the new file Id
                news.setImageId(thumbnail.getFileEntryId());
            } else {
                news.setImageId(fileId);
            }
            newsPersistence.updateImpl(news);

        } catch (Exception e) {
            logger.error("Cannot set thumbnail file correctly for newsId " + news.getNewsId(), e);
        }
    }

    private void deleteNewsThumbnail(News news) throws PortalException {
        if (news.getImageId() > 0) {
            // Get thumbnail file
            DLAppServiceUtil.deleteFileEntry(news.getImageId());
        }

        // Set news imageId to 0
        news.setImageId(0);
        newsPersistence.updateImpl(news);
    }

    private void updateNewsThumbnail(long userId, News news, long fileId) {
        if (fileId != news.getImageId()) {  // Update image only if the provided id is different from the current
            try {
                deleteNewsThumbnail(news);
                createNewsThumbnail(userId, news, fileId);
            } catch (Exception e) {
                logger.error("Cannot update thumbnail for newsId " + news.getNewsId(), e);
            }
        }
    }


    private void manageAttachedFiles(long newsId, JSONArray populations, List<Long> attachFileIds, boolean isCreation) throws SystemException {

        if (!isCreation) {
            // Delete previous attached files
            NewsAttachedFileLocalServiceUtil.deleteByNewsId(newsId);
        }

        // Build a map to group populations by groupId
        Map<Long, List<Long>> populationsMap = new HashMap<>();
        for (int idx = 0 ; idx < populations.length() ; idx++) {
            JSONObject population = populations.getJSONObject(idx);
            long groupId = population.getLong(JSONConstants.GROUP_ID);
            long roleId = population.getLong(JSONConstants.ROLE_ID);
            populationsMap.computeIfAbsent(groupId, k -> new ArrayList<>()).add(roleId);
        }

        // Loop over groups
        for (Map.Entry<Long, List<Long>> entry : populationsMap.entrySet()) {
            long groupId = entry.getKey();
            logger.info("Manage attached files for groupId " + groupId);
            try {
                Folder groupNewsFolder = FolderUtilsLocalServiceUtil.getGroupNewsFolder(groupId);
                // Check subFolder newsId
                List<Folder> folderList = DLAppServiceUtil.getFolders(groupId, groupNewsFolder.getFolderId());
                Folder newsIdFolder = null;
                for (Folder folder : folderList) {
                    if (folder.getName().equals("" + newsId)) {
                        newsIdFolder = folder;
                        logger.info("Folder newsId " + newsId + "already exists -> this is an edit");
                        // Delete existing attached files
                        List<FileEntry> fileList = DLAppServiceUtil.getFileEntries(groupId, folder.getFolderId());
                        for (FileEntry fileEntry : fileList) {
                            logger.info("Deleting existing attached file " + fileEntry.getTitle());
                            DLAppServiceUtil.deleteFileEntry(fileEntry.getFileEntryId());
                        }
                    }
                }

                // Create newsId folder if it does not exist
                if (newsIdFolder == null) {
                    logger.info("Creating news folder for newsId " + newsId + " and groupId " + groupId);
                    newsIdFolder = DLAppLocalServiceUtil.addFolder(
                            UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()),
                            groupId,
                            groupNewsFolder.getFolderId(),
                            "" + newsId,
                            "Dossier PJ de la news " + newsId,
                            new ServiceContext());
                    FolderUtilsLocalServiceUtil.hideDLFolder(newsIdFolder.getFolderId());
                }

                List<Long> agentRoleIds = RoleUtilsLocalServiceUtil.getAgentsRoleIds();

                // Delete all permissions on newsId folder
                ResourcePermissionLocalServiceUtil.deleteResourcePermissions(newsIdFolder.getCompanyId(), DLFolder.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, newsIdFolder.getFolderId());

                // Set VIEW and DELETE permissions for the owner (for search and removal)
                Role ownerRole = RoleLocalServiceUtil.getRole(PortalUtil.getDefaultCompanyId(), RoleConstants.OWNER);
                ResourcePermissionLocalServiceUtil.setOwnerResourcePermissions(newsIdFolder.getCompanyId(), DLFolder.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, ""+newsIdFolder.getFolderId(), ownerRole.getRoleId(), newsIdFolder.getUserId(), new String[]{ActionKeys.VIEW, ActionKeys.DELETE});

                // Set VIEW permissions for the destination roles
                for (long roleId : entry.getValue()) {
                    ResourcePermissionLocalServiceUtil.setResourcePermissions(newsIdFolder.getCompanyId(), DLFolder.class.getName(), ResourceConstants.SCOPE_GROUP, ""+newsIdFolder.getFolderId(), roleId, new String[]{ActionKeys.VIEW});
                }
                // Set permissions to all agents
                for (long agentRoleId : agentRoleIds) {
                    ResourcePermissionLocalServiceUtil.setResourcePermissions(newsIdFolder.getCompanyId(), DLFolder.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, "" + newsIdFolder.getFolderId(), agentRoleId, new String[]{ActionKeys.VIEW, ActionKeys.ADD_DOCUMENT, ActionKeys.UPDATE, ActionKeys.DELETE});
                }

                // Add attached files
                for (Long attachedFileId : attachFileIds) {
                    logger.info("Adding fileEntryId " + attachedFileId + " to folder " + newsIdFolder.getFolderId());
                    FileEntry copiedFileEntry = FileUtilsLocalServiceUtil.copyFileEntry(newsIdFolder.getUserId(), attachedFileId, newsIdFolder.getFolderId(), true);

                    // Set VIEW permissions to all agents
                    for (long agentRoleId : agentRoleIds) {
                        ResourcePermissionLocalServiceUtil.setResourcePermissions(copiedFileEntry.getCompanyId(), DLFileEntry.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, ""+copiedFileEntry.getFileEntryId(), agentRoleId, new String[]{ActionKeys.VIEW});
                    }

                    // Set VIEW permissions for the destination roles
                    for (long roleId : entry.getValue()) {
                        ResourcePermissionLocalServiceUtil.setResourcePermissions(copiedFileEntry.getCompanyId(), DLFileEntry.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, ""+copiedFileEntry.getFileEntryId(), roleId, new String[]{ActionKeys.VIEW});
                    }

                    // Create the NewsAttachedFiles
                    NewsAttachedFileLocalServiceUtil.addFile(newsId, groupId, copiedFileEntry.getFileEntryId());
                }

            } catch (Exception e) {
                logger.error("Error adding attached files for newsId " + newsId + " and groupId " + entry.getKey(), e);
            }
        }

    }
}
