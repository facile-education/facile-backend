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
    public static final String TICKET_KEY = "ticketKey";

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
    public static final String IMAGE_URL = "imageUrl";
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
    public static final String PASSWORD = "password";
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
    public static final String PARENTS = "parents";
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
    public static final String PROFILE_ID = "profileId";
    public static final String INFOS = "infos";


    // Organization
    public static final String ORG_ID = "orgId";
    public static final String ORG_NAME = "orgName";
    public static final String ORGS = "orgs";
    public static final String SCHOOL_UAI = "schoolUai";

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

    // Default image keys
    public static final String NEWS_DEFAULT_THUMBNAIL= "default_news_0";
    public static final String SCHOOL_NEWS_DEFAULT_THUMBNAIL= "default_school_news_0";
    public static final String ACCESS_DEFAULT_THUMBNAIL= "default_access_0";


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
    public static final String PARENT_FOLDER_ID = "parentFolderId";
    public static final String IS_FOR_ALL_STUDENTS = "isForAllStudents";
    public static final String NB_STUDENTS = "nbStudents";

    // Menu
    public static final String MENU = "menu";
    public static final String EXPANDED = "expanded";
    public static final String ICON = "icon";
    public static final String I18N_KEY = "i18nKey";
    public static final String IS_LANDING_PAGE = "isLandingPage";
    public static final String POSITION = "position";
    public static final String COMPONENT = "component";
    public static final String NOTIFICATIONS = "notifications";
    public static final String MESSAGING = "messaging";
    public static final String SCHOOLLIFE = "schoollife";
    public static final String PARAM = "param";

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
    public static final String SESSION = "session";
    public static final String SESSIONS = "sessions";
    public static final String SCHOOLLIFE_SESSIONS = "schoollifeSessions";

    public static final String LAST_SESSION_DATE = "lastSessionDate";
    public static final String SESSION_DETAILS = "sessionDetails";
    public static final String START_DATE = "startDate";
    public static final String END_DATE = "endDate";
    public static final String ROOM = "room";
    public static final String HAS_DESCRIPTION = "hasDescription";
    public static final String IS_MANUAL = "isManual";
    public static final String IS_DRAFT = "isDraft";
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
    public static final String GIVEN_HOMEWORKS = "givenHomeworks";
    public static final String SESSION_HOMEWORKS = "sessionHomeworks";
    public static final String TO_DO_HOMEWORKS = "toDoHomeworks";
    public static final String LAST_SESSION_ID = "lastSessionId";
    public static final String ALL_SLOTS = "allSlots";
    public static final String PRIVATE_NOTES = "privateNotes";
    public static final String COURSES = "courses";

    // Homework
    public static final String HOMEWORK_ID = "homeworkId";
    public static final String HOMEWORKS = "homeworks";
    public static final String ESTIMATED_TIME = "estimatedTime";
    public static final String FROM_DATE = "fromDate";
    public static final String TO_DATE = "toDate";
    public static final String SOURCE_SESSION_ID = "sourceSessionId";
    public static final String TARGET_SESSION_ID = "targetSessionId";
    public static final String TARGET_SLOT_NUMBER = "targetSlotNumber";
    public static final String IS_SENT = "isSent";
    public static final String SENT_DATE = "sentDate";
    public static final String SENT_FILE_ID = "sentFileId";
    public static final String IS_DONE = "isDone";
    public static final String SELECTED_STUDENTS = "selectedStudents";
    public static final String IS_WHOLE_CLASS = "isWholeClass";
    public static final String DONE_STUDENTS = "doneStudents";
    public static final String GROUP_STUDENTS = "groupStudents";
    public static final String SENT_FILE = "sentFile";
    public static final String NB_UNDONE_HOMEWORKS = "nbUndoneHomeworks";
    public static final String NB_HOMEWORKS_TO_CORRECT = "nbHomeworksToCorrect";

    // Progression
    public static final String COURSE_ID = "courseId";
    public static final String PROGRESSION_ID = "progressionId";
    public static final String CREATED_DATE = "createdDate";
    public static final String MODIFIED_DATE = "modifiedDate";
    public static final String SUBJECT_NAME = "subjectName";
    public static final String VOLEE = "volee";
    public static final String ITEM_ID = "itemId";
    public static final String ITEMS = "items";
    public static final String ITEM = "item";
    public static final String SESSION_CONTENT = "sessionContent";
    public static final String FOLDER_ID = "folderId";
    public static final String IS_HOMEWORK = "isHomework";
    public static final String PARENT_ID = "parentId";
    public static final String ORDER = "order";
    public static final String CONTENTS = "contents";
    public static final String BLOCK = "block";
    public static final String BLOCKS = "blocks";
    public static final String CONTENT_ID = "contentId";
    public static final String CONTENT_NAME = "contentName";
    public static final String CONTENT_TYPE = "contentType";
    public static final String LINK = "link";
    public static final String DOWNLOAD_URL = "downloadUrl";
    public static final String CONTENT_VALUE = "contentValue";
    public static final String ATTACHED_FILE_ID = "attachedFileId";
    public static final String CORRECTION_DATE = "correctionDate";
    public static final String IS_AUDIO_RECORDING = "isAudioRecording";
    public static final String SESSION_START_DATE = "sessionStartDate";
    public static final String SESSION_END_DATE = "sessionEndDate";
    public static final String TARGET_DATE = "targetDate";
    public static final String SENT_FILES = "sentFiles";
    public static final String PREVIEW = "preview";
    public static final String ASSIGNED_ITEM_ID = "assignedItemId";
    public static final String ASSIGNED_DATE = "assignedDate";
    public static final String IS_PROGRESSION_DRIVEN = "isProgressionDriven";
    public static final String PROGRESSION_URL = "progressionUrl";
    public static final String PROGRESSION_OWNER = "progressionOwner";
    public static final String PROGRESSION = "progression";
    public static final String SUB_SECTIONS = "subSections";
    public static final String SECTIONS = "sections";
    public static final String ASSIGNMENTS = "assignments";

    // School life
    public static final String STUDENT_NAME = "studentName";
    public static final String SESSION_SUBJECT = "sessionSubject";
    public static final String SESSION_DATE = "sessionDate";
    public static final String TEACHER_NAME = "teacherName";
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
    public static final String SESSION_NAME = "sessionName";

    // Messaging
    public static final String RECIPIENTS = "recipients";
    public static final String NB_RECIPIENTS = "nbRecipients";
    public static final String READ_DATE = "readDate";
    public static final String HAS_READ = "hasRead";
    public static final String ATTACHMENTS = "attachments";
    public static final String HAS_ATTACH_FILES = "hasAttachFiles";
    public static final String IS_FORWARDED = "isForwarded";
    public static final String IS_ANSWERED = "isAnswered";
    public static final String FULL_CONTENT = "fullContent";
    public static final String PREVIEW_CONTENT = "previewContent";
    public static final String SENDER_ID = "senderId";
    public static final String SENDER_NAME = "senderName";
    public static final String IS_NEW = "isNew";
    public static final String SEND_DATE = "sendDate";
    public static final String THREAD_ID = "threadId";
    public static final String MESSAGE_ID = "messageId";
    public static final String NB_UNREAD = "nbUnread";
    public static final String RENAMED_FOLDER = "renamedFolder";
    public static final String IS_SENDER_DELETED = "isSenderDeleted";
    public static final String MESSAGE_FOLDER_ID = "messageFolderId";
    public static final String THREADS = "threads";
    public static final String THREAD = "thread";
    public static final String MESSAGES = "messages";
    public static final String NB_MESSAGES = "nbMessages";
    public static final String ATTACHED_FILES = "attachedFiles";
    public static final String NB_MOVED_MESSAGES = "nbMovedMessages";
    // Messaging configuration
    public static final String SIGNATURE = "signature";
    public static final String IS_ACTIVE = "isActive";
    public static final String FORWARD = "forward";
    public static final String ADDRESSES = "addresses";
    public static final String TEXT = "text";
    public static final String AUTO_REPLY = "autoReply";

    // Contacts
    public static final String SCHOOL_ORG_ID = "schoolOrgId";
    public static final String POPULATIONS = "populations";
    public static final String POPULATION_NAME = "populationName";
    public static final String CLASSES = "classes";
    public static final String COURS = "cours";
    public static final String IS_EXPANDED = "isExpanded";
    public static final String IS_SELECTABLE = "isSelectable";
    public static final String IS_COMMUNITY = "isCommunity";
    public static final String CATEGORIES = "categories";
    public static final String PERSONALS = "Personnels";

    // News
    public static final String SCHOOLS_GROUPS = "schoolsGroups";
    public static final String AUTHOR_ID = "authorId";
    public static final String AUTHOR_NAME = "authorName";
    public static final String NEWS_ID = "newsId";
    public static final String SHORT_CONTENT = "shortContent";
    public static final String IS_IMPORTANT = "isImportant";
    public static final String PUBLICATION_DATE = "publicationDate";
    public static final String HAS_ATTACHED_FILES = "hasAttachedFiles";
    public static final String IS_EDITABLE = "isEditable";
    public static final String IS_SCHOOL_NEWS = "isSchoolNews";
    public static final String THUMBNAIL_ID = "thumbnailId";
    public static final String THUMBNAIL_URL = "thumbnailUrl";
    public static final String READ_INFOS = "readInfos";
    public static final String CREATED_NEWS = "createdNews";
    public static final String EDITED_NEWS = "editedNews";
    public static final String NB_UNREAD_NEWS = "nbUnreadNews";

    // Agenda
    public static final String EVENT = "event";
    public static final String EVENTS = "events";
    public static final String EVENT_ID = "eventId";
    public static final String NB_UNREAD_EVENTS = "nbUnreadEvents";
    public static final String CREATED_EVENT = "createdEvent";
    public static final String LOCATION = "location";

    // Dashboard
    public static final String HAS_ACTIVITY_THREAD_WIDGET = "hasActivityThreadWidget";
    public static final String HAS_SCHOOL_NEWS_WIDGET = "hasSchoolNewsWidget";
    public static final String HAS_DIARY_WIDGET = "hasDiaryWidget";
    public static final String HAS_HOMEWORK_WIDGET = "hasHomeworkWidget";
    public static final String HAS_EDT_WIDGET = "hasEDTWidget";
    public static final String HAS_STATISTIC_WIDGET = "hasStatisticWidget";
    public static final String IS_DELEGATE = "isDelegate";
    public static final String CAN_ADD_GROUP_NEWS = "canAddGroupNews";
    public static final String CAN_ADD_SCHOOL_NEWS = "canAddSchoolNews";
    public static final String CAN_ADD_EVENTS = "canAddEvents";
    public static final String IMPORTANT_NEWS = "importantNews";
    public static final String SESSION_URL = "sessionUrl";
    public static final String EVENT_LIST = "eventList";
    public static final String LAST_DASHBOARD_ACCESS_DATE = "lastDashboardAccessDate";
    public static final String NB_NEW_ACTIVITIES = "nbNewActivities";

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
    public static final String PREVIOUS_GROUP_NEWS_COUNT = "previousGroupNewsCount";
    public static final String NB_CONNEXIONS = "nbConnexions";
    public static final String PREVIOUS_NB_CONNEXIONS = "previousNbConnexions";
    public static final String ACTIVE_USERS_COUNT = "activeUsersCount";
    public static final String PREVIOUS_ACTIVE_USERS_COUNT = "previousActiveUsersCount";

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

    // About
    public static final String NEW = "new";
    public static final String NEWS = "news";
    public static final String OTHERS = "others";
    public static final String VERSION_ID = "versionId";
    public static final String VERSION_NUMBER = "versionNumber";
    public static final String VERSION_DATE = "versionDate";
    public static final String LATEST = "latest";
    public static final String VERSIONS = "versions";
    public static final String VERSION_DETAILS = "versionDetails";
    public static final String TERMS_OF_USE = "termsOfUse";
    public static final String AGREED_TERMS_OF_USE = "agreedTermsOfUse";
    public static final String PASSWORD_CHANGE = "passwordChange";

    // Search Engine
    public static final String HISTORY_ID = "historyId";
    public static final String QUERY = "query";
    public static final String ENTITY_ID = "entityId";
    public static final String DISPLAYABLE = "displayable";
    public static final String MESSAGING_FOLDER = "messagingFolder";
    public static final String RESULT = "result";
    public static final String SECTION = "section";
    public static final String SCORE = "score";

    // Schedule configuration
    public static final String SCHOOL_YEAR_START_DATE = "schoolYearStartDate";
    public static final String SCHOOL_YEAR_SEMESTER_DATE = "schoolYearSemesterDate";
    public static final String SCHOOL_YEAR_END_DATE = "schoolYearEndDate";
    public static final String HOLIDAYS = "holidays";
    public static final String H1_WEEKS = "h1Weeks";
    public static final String H2_WEEKS = "h2Weeks";
    public static final String SLOT_NUMBER = "slotNumber";
    public static final String SLOT_START_HOUR = "slotStartHour";
    public static final String SLOT_END_HOUR = "slotEndHour";

    // Session
    public static final String IS_VALID = "isValid";
    public static final String SESSION_TIMEOUT = "sessionTimeout";
    public static final String SESSION_TIMEOUT_WARNING = "sessionTimeoutWarning";
    public static final String IS_SESSION_EXPIRED = "isSessionExpired";
    public static final String IS_SESSION_WARNING = "isSessionWarning";
    public static final String CAN_SAVE_TEACHER_SUBSTITUTE = "canSaveTeacherSubstitute";
    public static final String CAN_REGISTER_STUDENT = "canRegisterStudent";
    public static final String CAN_UPDATE_SLOT = "canUpdateSlot";

    // Mobile
    public static final String REFRESH_TOKEN = "refreshToken";

    // Success
    public static final String SUCCESS = "success";

    // Errors
    public static final String ERROR = "error";
    public static final String ERROR_MSG = "error_msg";
    public static final String ERROR_CODE = "errorCode";
    public static final String AUTH_EXCEPTION = "AUTH_EXCEPTION";
    public static final String NOT_ALLOWED_EXCEPTION = "NOT_ALLOWED_EXCEPTION";
    public static final String UNAUTHORIZED_URL_EXCEPTION = "UNAUTHORIZED_URL_EXCEPTION";

    public static final String UNKNOWN = "unknown";
    public static final String FILE_SIZE_EXCEPTION = "fileSizeException";
    public static final String DUPLICATE_FILE_EXCEPTION = "DuplicateFileException";
    public static final String FILE_EXTENSION_EXCEPTION = "FileExtensionException";

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

    public static boolean getBoolValue(JSONObject object, String property, boolean defaultValue) {
        try {
            return object.getBoolean(property);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    public static String getStringValue(JSONObject object, String property, String defaultValue) {
        try {
            return object.getString(property);
        } catch (JSONException e) {
            return defaultValue;
        }
    }
}