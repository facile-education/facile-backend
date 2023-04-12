package com.weprode.nero.commons.constants;

public class JSONConstants {

    private JSONConstants() {
        throw new IllegalStateException("Constants class");
    }

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

    // Activity
    public static final String ACTIVITIES = "activities";

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

}