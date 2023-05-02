package com.weprode.nero.commons.constants;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONConstants {

    private JSONConstants() {
        throw new IllegalStateException("Constants class");
    }

    // Date formats
    public static final String FRENCH_FORMAT = "dd/MM/yyyy";
    public static final String FULL_FRENCH_FORMAT = "dd/MM/yyyy HH:mm";
    public static final String ENGLISH_FORMAT = "yyyy-MM-dd";
    public static final String FULL_ENGLISH_FORMAT = "yyyy-MM-dd HH:mm";

    // Global
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DATE = "date";
    public static final String URL = "url";
    public static final String DISPLAY_TEXT = "displayText";
    public static final String DISPLAY_NAME = "displayName";
    public static final String LABEL = "label";
    public static final String CREATOR = "creator";
    public static final String CREATION_DATE = "creationDate";
    public static final String EXPIRATION_DATE = "expirationDate";
    public static final String LAST_MODIFIED_DATE = "lastModifiedDate";
    public static final String DESCRIPTION = "description";
    public static final String COLOR = "color";
    public static final String STATUS = "status";
    public static final String TYPE = "type";
    public static final String RESULTS = "results";
    public static final String AFFECTATION_DATE = "affectationDate";
    public static final String AFFECTATIONS = "affectations";
    public static final String MESSAGE = "message";
    public static final String PRIVATE = "private";
    public static final String IS_ALLOWED = "isAllowed";
    public static final String CONTENT = "content";
    public static final String CONFIGURATION = "configuration";

    // Applications
    public static final String SERVICE = "service";
    public static final String SERVICES = "services";
    public static final String APPLICATION_ID = "applicationId";
    public static final String APPLICATION_NAME = "applicationName";
    public static final String APPLICATION_URL = "applicationUrl";
    public static final String RULES = "rules";
    public static final String ORG_IDS = "orgIds";
    public static final String ROLE_IDS = "roleIds";
    public static final String GROUP_IDS = "groupIds";
    public static final String MENU_ENTRY_ID = "menuEntryId";
    public static final String PORTLET_ID = "portletId";
    public static final String PORTLETS = "portlets";
    public static final String APPLICATION_KEY = "applicationKey";
    public static final String CATEGORY = "category";
    public static final String IMAGE = "image";
    public static final String HAS_CUSTOM_URL = "hasCustomUrl";
    public static final String EXPORT_USER = "exportUser";
    public static final String EXPORT_PARENT = "exportParent";
    public static final String EXPORT_STUDENT = "exportStudent";
    public static final String EXPORT_TEACHER = "exportTeacher";
    public static final String EXPORT_OTHER = "exportOther";
    public static final String HAS_GLOBAL_URL = "hasGlobalUrl";
    public static final String GLOBAL_URL = "globalUrl";
    public static final String AUTHORIZED_SCHOOLS = "authorizedSchools";
    public static final String DEFAULT_ROLES = "defaultRoles";
    public static final String IS_BROADCASTED = "isBroadcasted";

    // User
    public static final String USER = "user";
    public static final String USERS = "users";
    public static final String USER_ID = "userId";
    public static final String EMAIL = "email";
    public static final String MAIL = "mail";
    public static final String USER_NAME = "userName";

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String FULL_NAME = "fullName";
    public static final String SCREEN_NAME = "screenName";
    public static final String ADMIN_NAME = "adminName";
    public static final String IS_ADMIN = "isAdmin";
    public static final String IS_SCHOOL_ADMIN = "isSchoolAdmin";
    public static final String ADMINS = "admins";
    public static final String IS_NEWS_DELEGATE = "isNewsDelegate";
    public static final String IS_TEACHER = "isTeacher";
    public static final String IS_DIRECTION = "isDirection";
    public static final String IS_PARENT = "isParent";
    public static final String IS_STUDENT = "isStudent";
    public static final String IS_ADMINISTRATOR = "isAdministrator";
    public static final String IS_ENT_ADMIN = "isENTAdmin";
    public static final String IS_LOCAL_ADMIN = "isLocalAdmin";
    public static final String IS_PERSONAL = "isPersonal";
    public static final String IS_DOYEN = "isDoyen";
    public static final String IS_DIRECTION_MEMBER = "isDirectionMember";
    public static final String IS_SECRETARIAT = "isSecretariat";
    public static final String TEACHER_ID = "teacherId";
    public static final String STUDENT_ID = "studentId";
    public static final String TEACHERS = "teachers";
    public static final String TEACHER = "teacher";
    public static final String STUDENTS = "students";
    public static final String NB_TOTAL_USERS = "nbTotalUsers";
    public static final String CANDIDATES = "candidates";
    public static final String CHILDREN = "children";
    public static final String PICTURE = "picture";
    public static final String THEME_COLOR = "themeColor";
    public static final String HAS_WEBDAV_ENABLED = "hasWebdavEnabled";
    public static final String MOBILE_PHONE = "mobilePhone";
    public static final String SMS_PHONE = "SMSPhone";
    public static final String HOME_PHONE = "homePhone";
    public static final String PRO_PHONE = "proPhone";
    public static final String ADDRESS = "address";
    public static final String REPORT_FREQUENCY = "reportFrequency";
    public static final String IS_LOCAL_USER = "isLocalUser";
    public static final String IS_WEBDAV_ENABLED = "isWebdavEnabled";
    public static final String WEBDAV_URL = "webdavUrl";


    // Organization
    public static final String ORG_ID = "orgId";
    public static final String ORG_NAME = "orgName";
    public static final String ORGS = "orgs";

    // Group
    public static final String GROUP_ID = "groupId";
    public static final String GROUP_NAME = "groupName";
    public static final String IS_PERSONAL_GROUP = "isPersonalGroup";
    public static final String GROUPS = "groups";
    public static final String IS_EXPIRED = "isExpired";
    public static final String IS_INSTITUTIONAL = "isInstitutional";
    public static final String IS_PEDAGOGICAL = "isPedagogical";
    public static final String IS_CLASS = "isClass";
    public static final String IS_SCHOOL = "isSchool";
    public static final String IS_SUBJECT = "isSubject";
    public static final String NB_MEMBERS = "nbMembers";
    public static final String MEMBERS = "members";

    // Role
    public static final String ROLE_ID = "roleId";
    public static final String ROLE_NAME = "roleName";
    public static final String ROLE_CODE = "roleCode";
    public static final String ROLES = "roles";

    // School
    public static final String SCHOOL_ID = "schoolId";
    public static final String SCHOOL_NAME = "schoolName";
    public static final String SCHOOLS = "schools";
    public static final String IS_PREFERED = "isPrefered";
    public static final String USER_SCHOOLS = "userSchools";

    // Volee
    public static final String VOLEES = "volees";

    // Classe
    public static final String IS_FOR_CLASS = "isForClass";
    public static final String CLASS_NAME = "className";

    // Subject
    public static final String SUBJECT_ID = "subjectId";
    public static final String SUBJECTS= "subjects";
    public static final String SUBJECT= "subject";

    // Activity
    public static final String ACTIVITIES = "activities";
    public static final String MODIFICATION_DATE = "modificationDate";
    public static final String AUTHOR = "author";
    public static final String TARGET = "target";
    public static final String ACTIVITY_ID = "activityId";
    public static final String FOLDER_NAME = "folderName";
    public static final String FILE_LINK = "fileLink";
    public static final String FILE_ID = "fileId";
    public static final String FOLDER_LINK = "folderLink";
    public static final String ACTION_USER_ID = "actionUserId";
    public static final String ACTION_USER_NAME = "actionUserName";
    public static final String TARGET_USER_NAMES = "targetUserNames";
    public static final String SHORT_TARGET_USER_NAMES = "shortTargetUserNames";
    public static final String GROUP_LINK = "groupLink";

    // Menu
    public static final String MENU = "menu";
    public static final String EXPANDED = "expanded";
    public static final String ICON = "icon";
    public static final String I18N_KEY = "i18nKey";
    public static final String IS_LANDING_PAGE = "isLandingPage";
    public static final String POSITION = "position";
    public static final String COMPONENT = "component";

    // Document library
    public static final String FILE_NAME = "fileName";
    public static final String SIZE = "size";
    public static final String PERMISSION_MATRIX = "permissionMatrix";
    public static final String PERMISSIONS = "permissions";
    public static final String IS_WRITABLE = "isWritable";
    public static final String EDITABLE = "editable";
    public static final String READ_ONLY = "readOnly";
    public static final String VERSION = "version";
    public static final String EXTENSION = "extension";
    public static final String UPLOADED_FILE = "uploadedFile";
    public static final String FIRST_CREATED_FOLDER = "firstCreatedFolder";
    public static final String IS_GROUP_ROOT_FOLDER = "isGroupRootFolder";
    public static final String IS_GROUP_FILE = "isGroupFile";
    public static final String IS_SUB_ENTITY = "isSubEntity";
    public static final String URL_WEBDAV = "urlWebdav";
    public static final String FAILED_ENTITY_LIST = "failedEntityList";
    public static final String FAILED_ENTITIES_LIST = "failedEntitiesList";
    public static final String FOLDERS_IN_CONFLICT = "foldersInConflict";
    public static final String FILES_IN_CONFLICT = "filesInConflict";
    public static final String ROOT_FOLDER_ID = "rootFolderId";
    public static final String BREADCRUMB = "breadcrumb";
    public static final String BREAD_CRUMB = "breadCrumb";
    public static final String FILE = "file";
    public static final String FILES = "files";
    public static final String FILE_ENTRY_ID = "fileEntryId";
    public static final String FILE_VERSIONS = "fileVersions";
    public static final String FILE_VERSION_ID = "fileVersionId";
    public static final String IS_CURRENT_VERSION = "isCurrentVersion";
    public static final String FOLDER = "folder";
    public static final String FOLDERS = "folders";
    public static final String CREATED_FOLDER = "createdFolder";
    public static final String SUB_FOLDERS = "subFolders";
    public static final String HAS_SUB_FOLDERS = "hasSubFolders";
    public static final String ZIP_URL = "zipUrl";
    public static final String MAX_UPLOAD_SIZE = "maxUploadSize";
    public static final String HAS_MINDMAP_BROADCASTED = "hasMindmapBroadcasted";
    public static final String HAS_GEOGEBRA_BROADCASTED = "hasGeogebraBroadcasted";
    public static final String HAS_SCRATCH_BROADCASTED = "hasScratchBroadcasted";
    public static final String SCRATCH_FILES = "scratchFiles";
    public static final String TYPE_OF_VIEW = "typeOfView";
    public static final String FILE_URL = "fileUrl";
    public static final String VIEW_COUNT = "viewCount";
    public static final String DOWNLOAD_COUNT = "downloadCount";
    public static final String STATUS_BY_USER_NAME = "statusByUserName";
    
    // WOPI
    public static final String WOPI_OWNER_ID = "OwnerId";
    public static final String WOPI_USER_ID = "UserId";
    public static final String WOPI_USER_INFO = "UserInfo";
    public static final String WOPI_USER_FRIENDLY_NAME = "UserFriendlyName";
    public static final String WOPI_USER_CAN_WRITE = "UserCanWrite";
    public static final String WOPI_SUPPORTS_UPDATE = "SupportsUpdate";
    public static final String WOPI_BASE_FILE_NAME = "BaseFileName";
    public static final String WOPI_SIZE = "Size";
    public static final String WOPI_LAST_MODIFIED_TIME = "LastModifiedTime";
    public static final String WOPI_SHA256 = "SHA256";
    public static final String WOPI_VERSION = "Version";

    // Session
    public static final String SESSION_ID = "sessionId";
    public static final String SESSIONS = "sessions";
    public static final String SCHOOLLIFE_SESSIONS = "schoollifeSessions";
    public static final String SESSION_DETAILS = "sessionDetails";
    public static final String START_DATE = "startDate";
    public static final String END_DATE = "endDate";
    public static final String ROOM = "room";
    public static final String HAS_DESCRIPTION = "hasDescription";
    public static final String IS_MANUAL = "isManual";
    public static final String IS_PUBLISH = "isPublish";
    public static final String TITLE = "title";
    public static final String IS_CURRENT_USER_TEACHER = "isCurrentUserTeacher";
    public static final String HAS_HOMEWORK = "hasHomework";
    public static final String SUBSTITUTE_ID = "substituteId";
    public static final String SUBSTITUTE = "substitute";
    public static final String IS_SAME_SLOT = "isSameSlot";
    public static final String NEXT_SESSIONS = "nextSessions";
    public static final String PREVIOUS_SESSIONS = "previousSessions";
    public static final String START_DAY_TIME = "startDayTime";
    public static final String END_DAY_TIME = "endDayTime";
    public static final String START_DATE_PROJECT = "startDateProject";
    public static final String START_DATE_SCHOOL = "startDateSchool";
    public static final String END_DATE_SCHOOL = "endDateSchool";
    public static final String SCHOOL_DAYS = "schoolDays";
    public static final String GIVEN_HOMEWORK = "givenHomework";
    public static final String TO_DO_HOMEWORK = "toDoHomework";

    // Homework
    public static final String HOMEWORK_ID = "homeworkId";
    public static final String ESTIMATED_TIME = "estimatedTime";
    public static final String FROM_DATE = "fromDate";
    public static final String TO_DATE = "toDate";
    public static final String SOURCE_SESSION_ID = "sourceSessionId";
    public static final String TARGET_SESSION_ID = "targetSessionId";
    public static final String IS_SENT = "isSent";
    public static final String SENT_DATE = "sentDate";
    public static final String SENT_FILE_ID = "sentFileId";
    public static final String IS_DONE = "isDone";
    public static final String SELECTED_STUDENTS = "selectedStudents";
    public static final String IS_WHOLE_CLASS = "isWholeClass";
    public static final String DONE_STUDENTS = "doneStudents";
    public static final String GROUP_STUDENTS = "groupStudents";

    // Progression
    public static final String PROGRESSION_ID = "progressionId";
    public static final String CREATED_DATE = "createdDate";
    public static final String MODIFIED_DATE = "modifiedDate";
    public static final String SUBJECT_NAME = "subjectName";
    public static final String VOLEE = "volee";
    public static final String ITEM_ID = "itemId";
    public static final String ITEMS = "items";
    public static final String ITEM = "item";
    public static final String FOLDER_ID = "folderId";
    public static final String IS_HOMEWORK = "isHomework";
    public static final String PARENT_ID = "parentId";
    public static final String DURATION = "duration";
    public static final String ORDER = "order";
    public static final String CONTENTS = "contents";
    public static final String ASSIGNMENTS = "assignments";
    public static final String ASSIGNMENT = "assignment";
    public static final String CONTENT_ID = "contentId";
    public static final String CONTENT_NAME = "contentName";
    public static final String CONTENT_TYPE = "contentType";
    public static final String LINK = "link";
    public static final String DOWNLOAD_URL = "downloadUrl";
    public static final String IS_TO_BE_COMPLETED = "isToBeCompleted";
    public static final String CONTENT_VALUE = "contentValue";
    public static final String ATTACHED_FILE_ID = "attachedFileId";
    public static final String IS_AUDIO_RECORDING = "isAudioRecording";
    public static final String ASSIGNED_DATE = "assignedDate";
    public static final String SESSION_START_DATE = "sessionStartDate";
    public static final String SESSION_END_DATE = "sessionEndDate";
    public static final String TARGET_DATE = "targetDate";
    public static final String PREVIEW = "preview";
    public static final String ASSIGNED_ITEM_ID = "assignedItemId";
    public static final String IS_PROGRESSION_DRIVEN = "isProgressionDriven";
    public static final String PROGRESSION_URL = "progressionUrl";
    public static final String PROGRESSION_OWNER = "progressionOwner";
    public static final String PROGRESSION = "progression";
    public static final String SUB_SECTIONS = "subSections";
    public static final String SECTIONS = "sections";

    // School life
    public static final String STUDENT_NAME = "studentName";
    public static final String SESSION_SUBJECT = "sessionSubject";
    public static final String SESSION_DATE = "sessionDate";
    public static final String TEACHER_NAME = "teacherName";
    public static final String SCHOOLLIFE_SESSION_ID = "schoollifeSessionId";
    public static final String SOURCE_SCHOOLLIFE_SESSION_ID = "sourceSchoollifeSessionId";
    public static final String CANDIDATE_SESSIONS = "candidateSessions";
    public static final String PENDING_RENVOIS = "pendingRenvois";
    public static final String RENVOI_DATE = "renvoiDate";
    public static final String STUDENT = "student";
    public static final String SOURCE_TEACHER_ID = "sourceTeacherId";
    public static final String CAPACITY = "capacity";
    public static final String NB_REGISTERED_STUDENTS = "nbRegisteredStudents";
    public static final String COMMENT = "comment";
    public static final String IS_PRESENT = "isPresent";
    public static final String NOT_REGISTERED_ARRAY = "notRegisteredArray";
    public static final String NB_REGISTERED = "nbRegistered";
    public static final String CDT_SESSION_ID = "cdtSessionId";
    public static final String REGISTERER_ID = "registererId";

    // Statistics
    public static final String DATASETS = "datasets";
    public static final String POINT_BORDER_COLOR = "pointBorderColor";
    public static final String POINT_BACKGROUND_COLOR = "pointBackgroundColor";
    public static final String BORDER_COLOR = "borderColor";
    public static final String BACKGROUND_COLOR = "backgroundColor";
    public static final String LABELS = "labels";
    public static final String COUNT = "count";
    public static final String TOTAL_COUNT = "totalCount";
    public static final String DATA = "data";
    public static final String SCHOOL_NEWS_COUNT = "schoolNewsCount";
    public static final String GROUP_NEWS_COUNT = "groupNewsCount";

    // Help
    public static final String HELP_TREE = "helpTree";
    public static final String HELP_ITEM = "helpItem";
    // Help category
    public static final String CATEGORY_ID = "categoryId";
    public static final String CATEGORY_NAME = "categoryName";
    // Help item
    public static final String ITEM_NAME = "itemName";
    public static final String VIDEO_URL = "videoUrl";
    public static final String VIDEO_DESCRIPTION = "videoDescription";
    public static final String MANUAL = "manual";
    public static final String LANGUAGE = "language";
    public static final String IS_FETCHED = "isFetched";
    public static final String IS_MANAGEMENT = "isManagement";
    // Help questions
    public static final String QUESTION_ID = "questionId";
    public static final String QUESTION = "question";
    public static final String ANSWER = "answer";
    public static final String QUESTIONS = "questions";
    // Help relations
    public static final String RELATION_ID = "relationId";
    public static final String RELATED_ITEM_ID = "relatedItemId";
    public static final String RELATED_ITEM_NAME = "relatedItemName";
    public static final String RELATIONS = "relations";
    public static final String RELATION = "relation";
    // Help links
    public static final String LINK_ID = "linkId";
    public static final String LINK_URL = "linkUrl";
    public static final String LINK_NAME = "linkName";
    public static final String LINKS = "links";

    // Success
    public static final String SUCCESS = "success";

    // Errors
    public static final String ERROR = "error";
    public static final String ERROR_MSG = "error_msg";
    public static final String ERROR_CODE = "errorCode";
    public static final String AUTH_EXCEPTION = "AUTH_EXCEPTION";
    public static final String NOT_ALLOWED_EXCEPTION = "NOT_ALLOWED_EXCEPTION";
    public static final String UNKNOWN = "unknown";
    public static final String FILE_SIZE_EXCEPTION = "fileSizeException";
    public static final String DUPLICATE_FILE_EXCEPTION = "DuplicateFileException";

    public static long getLongValue(JSONObject object, String property, long defaultValue) {
        try {
            return object.getLong(property);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    public static int getIntValue(JSONObject object, String property, int defaultValue) {
        try {
            return object.getInt(property);
        } catch (JSONException e) {
            return defaultValue;
        }
    }
}