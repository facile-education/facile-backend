package com.weprode.nero.statistic.utils;

import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Enum which represent the principal profile type for inscription
 */
// TODO use roleId for statistics ?
public enum UserProfile {
    ALL(0L, "Tous", RoleUtilsLocalServiceUtil.getStudentRole().getRoleId()),
    STUDENT(1L, "El\u00E8ve", RoleUtilsLocalServiceUtil.getStudentRole().getRoleId()),
    TEACHER(2L, "Enseignant.te", RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId()),
    PARENT(3L, "Responsable l\u00e9gal", RoleUtilsLocalServiceUtil.getParentRole().getRoleId()),
    DIRECTOR(4L, "Personnel de direction", RoleUtilsLocalServiceUtil.getDirectionRole().getRoleId()),
    SCHOOL_LIFE(5L, "Personnel de vie scolaire", RoleUtilsLocalServiceUtil.getSchoolLifeRole().getRoleId()),
    ADMINISTRATIVE(6L, "Personnel administratif", RoleUtilsLocalServiceUtil.getAdministrativeRole().getRoleId()),
    // COLLECTIVITY(7L, "Personnel de la collectivit\u00e9", RoleUtilsLocalServiceUtil.getCollectivityRole().getRoleId()),
    OTHER(20L, "Autre", 0L);

    final long statisticsId;
    final String name;
    final long roleId;

    /**
     * init user profile with it's database ID, define in initiation database script
     * @param statisticsId the stat id
     */
    UserProfile(long statisticsId, String name, long roleId) {
        this.statisticsId = statisticsId;
        this.name = name;
        this.roleId = roleId;
    }

    /**
     * get the database id
     * @return the id
     */
    public long getMatomoId() {
        return statisticsId;
    }

    public String getName() {
        return name;
    }

    public long getRoleId() {
        return roleId;
    }

    public static UserProfile getRoleUserProfile(long roleId) {
        for (UserProfile userProfile : UserProfile.values()) {
            if (userProfile.getRoleId() == roleId) {
                return userProfile;
            }
        }

        return UserProfile.OTHER;
    }

    public static List<Long> getAllStatProfileIds() {
        List<Long> ids = new ArrayList<>();
        for (UserProfile userProfile : getStatProfiles()) {
            ids.add(userProfile.getMatomoId());
        }

        return ids;
    }

    public static List<UserProfile> getStatProfiles() {
        return Arrays.asList(UserProfile.STUDENT, UserProfile.PARENT, UserProfile.TEACHER,
                UserProfile.DIRECTOR, UserProfile.SCHOOL_LIFE, UserProfile.ADMINISTRATIVE,
                // UserProfile.COLLECTIVITY,
                UserProfile.OTHER);
    }

}