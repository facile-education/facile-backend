package com.weprode.facile.search.model;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.highlight.HighlightField;
import com.liferay.portal.search.hits.SearchHit;
import com.liferay.portal.search.searcher.SearchResponse;
import com.weprode.facile.agenda.model.Event;
import com.weprode.facile.agenda.service.EventLocalServiceUtil;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.document.constants.DocumentConstants;
import com.weprode.facile.document.service.FileUtilsLocalServiceUtil;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.facile.messaging.constants.MessagingConstants;
import com.weprode.facile.messaging.model.Message;
import com.weprode.facile.messaging.service.MessageLocalServiceUtil;
import com.weprode.facile.news.model.News;
import com.weprode.facile.news.service.NewsLocalServiceUtil;
import com.weprode.facile.search.constants.SearchConstants;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class SearchResults {

    private static final Log logger = LogFactoryUtil.getLog(SearchResults.class);

    private final List<SearchHit> hits;
    private final long count;
    private int page = 1;
    private int pageSize = 10;

    public SearchResults(SearchResponse response, int page, int pageSize) {
        this.hits = response.getSearchHits().getSearchHits();
        this.count = response.getCount();
        this.page = page;
        this.pageSize = pageSize;
    }

    public List<SearchHit> getHits() {
        return this.hits;
    }

    public long getCount() {
        return count;
    }

    // une icône illustrant le type de la ressource
    public static JSONObject convertHitToJson(SearchHit hit, long userId) {
        JSONObject result = new JSONObject();

        Document doc = hit.getDocument();

        long entityId = doc.getLong(Field.ENTRY_CLASS_PK);
        String highlightedContent = StringPool.BLANK;
        for (Map.Entry<String, HighlightField> entry : hit.getHighlightFieldsMap().entrySet()) {
            if (entry.getKey().equals(Field.CONTENT) && !entry.getValue().getFragments().isEmpty()) {
                highlightedContent = entry.getValue().getFragments().get(0).replace("liferay-hl", "strong");
            }
        }

        result.put(JSONConstants.ENTITY_ID, entityId);
        result.put(JSONConstants.TITLE, doc.getString(Field.TITLE));
        result.put(JSONConstants.CONTENT, highlightedContent.isBlank() ? doc.getString(Field.CONTENT) : highlightedContent);
        result.put(JSONConstants.DATE, doc.getDate(Field.DISPLAY_DATE));
        result.put(JSONConstants.AUTHOR, doc.getString(Field.USER_NAME));
        // Round score to units
        result.put(JSONConstants.SCORE, Math.round(hit.getScore()));

        // Message
        if (doc.getString(Field.ENTRY_CLASS_NAME).equals(Message.class.getName())) {
            result.put(JSONConstants.SERVICE, SearchConstants.TYPE_MESSAGE);

        // News
        } else if (doc.getString(Field.ENTRY_CLASS_NAME).equals(News.class.getName())) {
            result.put(JSONConstants.SERVICE, SearchConstants.TYPE_NEWS);

            try {
                JSONObject news = NewsLocalServiceUtil.convertNewsToJson(entityId, userId, true);
                result.put(JSONConstants.NEWS, news);
            } catch (PortalException e) {
                logger.error("Failed to get news with id="+entityId, e);
            }

        // Event
        } else if (doc.getString(Field.ENTRY_CLASS_NAME).equals(Event.class.getName())) {
            result.put(JSONConstants.SERVICE, SearchConstants.TYPE_EVENT);

            JSONObject event = EventLocalServiceUtil.convertEventToJson(userId, entityId, false);
            result.put(JSONConstants.EVENT, event);

        // File
        } else if (doc.getString(Field.ENTRY_CLASS_NAME).equals(DLFileEntry.class.getName())) {
            try {
                // Fetch user because Liferay indexes user name in lowercase
                User author = UserLocalServiceUtil.getUser(doc.getLong(Field.USER_ID));
                result.put(JSONConstants.AUTHOR, author.getFullName());
            } catch (Exception e) {
                result.put(JSONConstants.AUTHOR, doc.getString(Field.USER_NAME));
            }

            boolean isGroupFile = FileUtilsLocalServiceUtil.isGroupFile(entityId);
            result.put(JSONConstants.SERVICE, isGroupFile ? SearchConstants.TYPE_COLLABORATIVE_FILE : SearchConstants.TYPE_FILE);

            try {
                FileEntry fileEntry = DLAppServiceUtil.getFileEntry(entityId);
                boolean isDisplayable = DocumentConstants.SUPPORTED_EXTENSIONS.contains(fileEntry.getExtension());
                result.put(JSONConstants.DISPLAYABLE, isDisplayable);
                result.put(JSONConstants.SIZE, fileEntry.getSize());

                if (isGroupFile) {
                    Group group = GroupLocalServiceUtil.fetchGroup(fileEntry.getGroupId());
                    result.put(JSONConstants.GROUP_NAME, group.getName(LocaleUtil.getDefault()));
                }

                // Check if attachment
                Folder folder = DLAppServiceUtil.getFolder(fileEntry.getFolderId());
                List<Folder> path = FolderUtilsLocalServiceUtil.getFolderPath(folder);
                if (!path.isEmpty()) {
                    if (path.size() > 1 && path.get(1).getName().equals(DocumentConstants.NEWS_FOLDER_NAME)) {
                        // News attachment
                        long newsId = Long.parseLong(path.get(2).getName());

                        try {
                            JSONObject news = NewsLocalServiceUtil.convertNewsToJson(newsId, userId, true);
                            result.put(JSONConstants.NEWS, news);
                        } catch (PortalException e) {
                            logger.error("Failed to get news with id="+newsId, e);
                        }

                        result.put(JSONConstants.SERVICE, SearchConstants.TYPE_NEWS_FILE);
                    } else if (path.get(0).getName().equals(DocumentConstants.IM_BOX_FOLDER_NAME)) {
                        // Message attachment
                        long messageId = Long.parseLong(path.get(1).getName().replace(MessagingConstants.ATTACHED_FILES_FOLDER_PREFIX, ""));

                        Message message = MessageLocalServiceUtil.getMessage(messageId);
                        JSONObject messageJson = new JSONObject();
                        messageJson.put(JSONConstants.MESSAGE_ID, messageId);
                        messageJson.put(JSONConstants.SUBJECT, message.getMessageSubject());
                        result.put(JSONConstants.MESSAGE, messageJson);

                        result.put(JSONConstants.SERVICE, SearchConstants.TYPE_MESSAGE_FILE);
                    } else {
                        JSONObject folderJson = new JSONObject();
                        folderJson.put(JSONConstants.FOLDER_ID, folder.getFolderId());
                        folderJson.put(JSONConstants.FOLDER_NAME, folder.getName());
                        result.put(JSONConstants.FOLDER, folderJson);
                    }
                }
            } catch (Exception e) {
                logger.error("Could not check if file is an attachment", e);
            }

        // Folder
        } else if (doc.getString(Field.ENTRY_CLASS_NAME).equals(DLFolder.class.getName())) {
            boolean isGroupFolder = false;

            try {
                Folder folder = DLAppServiceUtil.getFolder(entityId);
                if (FolderUtilsLocalServiceUtil.isGroupFolder(folder)) {
                    isGroupFolder = true;
                    Group group = GroupLocalServiceUtil.fetchGroup(folder.getGroupId());
                    result.put(JSONConstants.GROUP_NAME, group.getName(LocaleUtil.getDefault()));
                }

            } catch (PortalException e) {
                logger.error("Could not fetch folder with id=" + entityId, e);
            }

            result.put(JSONConstants.SERVICE, isGroupFolder? SearchConstants.TYPE_COLLABORATIVE_FOLDER : SearchConstants.TYPE_FOLDER);
        }

        return result;
    }
}