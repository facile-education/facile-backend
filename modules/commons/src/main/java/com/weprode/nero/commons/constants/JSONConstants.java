package com.weprode.nero.commons.constants;

public class JSONConstants {

    private JSONConstants() {
        throw new IllegalStateException("Constants class");
    }

    // Global
    public static final String DISPLAY_TEXT = "displayText";
    public static final String LABEL = "label";
    public static final String CREATION_DATE = "creationDate";
    public static final String EXPIRATION_DATE = "expirationDate";
    public static final String DESCRIPTION = "description";
    public static final String COLOR = "color";
    public static final String STATUS = "status";
    public static final String TYPE = "type";
    public static final String RESULTS = "results";

    // User
    public static final String USER_ID = "userId";
    public static final String USER_NAME = "userName";

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String SCREEN_NAME = "screenName";
    public static final String IS_ADMIN = "isAdmin";
    public static final String IS_TEACHER = "isTeacher";
    public static final String IS_PARENT = "isParent";
    public static final String IS_STUDENT = "isStudent";

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

    // Role
    public static final String ROLE_ID = "roleId";
    public static final String ROLE_CODE = "roleCode";
    public static final String ROLES = "roles";
    public static final String IS_FOR_CLASS = "isForClass";

    // School
    public static final String SCHOOL_ID = "schoolId";
    public static final String SCHOOL_NAME = "schoolName";
    public static final String SCHOOLS = "schools";

    // Volee
    public static final String VOLEES = "volees";

    // Activity
    public static final String ACTIVITIES = "activities";

    // Success
    public static final String SUCCESS = "success";

    // Errors
    public static final String ERROR = "error";
    public static final String ERROR_CODE = "errorCode";
    public static final String AUTH_EXCEPTION = "AUTH_EXCEPTION";
    public static final String NOT_ALLOWED_EXCEPTION = "NOT_ALLOWED_EXCEPTION";

}