package com.weprode.nero.organization.constants;

public class OrgConstants {

    // Role
    public static final int NO_ROLE = 0;
    public static final int STUDENT_ROLE = 1;
    public static final int PARENT_ROLE = 2;	// Not used anymore
    public static final int TEACHER_ROLE = 3;
    public static final int PERSONAL_ROLE = 4;
    public static final int PAT_ROLE = 5;		// Administrative personals

    // Type
    public static final int NO_TYPE = 0;
    public static final int CLASS_TYPE = 1;
    public static final int GROUP_TYPE = 2; // Not used for GVE
    public static final int SCHOOL_LEVEL_TYPE = 3;
    public static final int SCHOOL_TYPE = 4;
    public static final int ENT_TYPE = 5;
    public static final int SUBJECT_TYPE = 6;
    public static final int VOLEE_TYPE = 7;	// For GVE only : volee is the school level
    public static final int COURS_TYPE = 8;	// For GVE only

    public static final long ALL_SCHOOLS_ID = -1;

    public final static String ORG_SUFFIX_STUDENTS   = " - El\u00e8ves";
    public final static String ORG_SUFFIX_PARENTS    = " - Responsables l\u00e9gaux";
    public final static String ORG_SUFFIX_PERSONNELS = " - Personnels";
    public final static String ORG_SUFFIX_PATS = " - PAT";
    public final static String ORG_SUFFIX_TEACHERS   = " - Enseignants\u00B7tes";
    public final static String ORG_SUFFIX_MAIN_TEACHERS   = " - Ma\u00eetres de classe";
}
