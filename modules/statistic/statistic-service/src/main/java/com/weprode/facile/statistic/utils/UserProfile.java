/**
 * Copyright (c) 2022-present - Weprode
 * This file is part of FACILE ENT Project.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License (LGPL) as
 * published by the Free Software Foundation (version 2.1 of the License).
 * See LICENCE.txt file
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */

package com.weprode.facile.statistic.utils;

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