package com.weprode.nero.user.comparator;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.comparator.UserLastNameComparator;

public class UserLastNameCaseInsensitiveComparator extends UserLastNameComparator {

    public UserLastNameCaseInsensitiveComparator(boolean reverse) {
        super(reverse);
    }

    @Override
    public int compare(User user1, User user2) {

        int value = user1.getLastName().compareToIgnoreCase(user2.getLastName());

        if (value == 0) {
            value = user1.getFirstName().compareToIgnoreCase(user2.getFirstName());
        }

        if (value == 0) {
            value = user1.getMiddleName().compareToIgnoreCase(user2.getMiddleName());
        }

        if (super.isAscending()) {
            return value;
        }
        else {
            return -value;
        }
    }
}
