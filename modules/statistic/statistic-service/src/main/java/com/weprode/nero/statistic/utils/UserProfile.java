package com.weprode.nero.statistic.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Enum which represent the principal profiles for statistics use
 */
public enum UserProfile {

    STUDENT(1L, "El\u00E8ve"),
    TEACHER(2L, "Enseignant.te"),
    PARENT(3L, "Responsable l\u00e9gal.e"),
    PERSONAL(4L, "Personnel");

    final long statisticsId;
    final String name;

    UserProfile(long statisticsId, String name) {
        this.statisticsId = statisticsId;
        this.name = name;
    }

    public long getMatomoId() {
        return statisticsId;
    }

    public String getName() {
        return name;
    }

    public static List<Long> getAllStatProfileIds() {
        List<Long> ids = new ArrayList<>();
        ids.add(UserProfile.STUDENT.getMatomoId());
        ids.add(UserProfile.PARENT.getMatomoId());
        ids.add(UserProfile.TEACHER.getMatomoId());
        ids.add(UserProfile.PERSONAL.getMatomoId());
        return ids;
    }

}