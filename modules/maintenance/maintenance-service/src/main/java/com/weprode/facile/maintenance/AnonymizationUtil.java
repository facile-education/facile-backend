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

package com.weprode.facile.maintenance;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.weprode.facile.messaging.model.Message;
import com.weprode.facile.messaging.model.MessageContent;
import com.weprode.facile.messaging.service.MessageContentLocalServiceUtil;
import com.weprode.facile.messaging.service.MessageLocalServiceUtil;
import com.weprode.facile.news.model.News;
import com.weprode.facile.news.service.NewsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.user.model.UserContact;
import com.weprode.facile.user.service.UserContactLocalServiceUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AnonymizationUtil {

    private static final Log logger = LogFactoryUtil.getLog(AnonymizationUtil.class);
    private static final Map<String, String> userNameMap = new HashMap<>();

    // Anonymize all sensible data: lastName, email, phones, screenname
    // Anonymize dlfileentries, dlfileversions, dlfolders
    // Lorem ipsum blog entries, messages
    public void anonymize() {

        // Check if portal-ext variable is present, for more safety
        if (!PrefsPropsUtil.getString("anonymization.enabled").equals("true")) {
            logger.info("Anonymization could not run because a property is not set");
            return;
        }

        // Do as administrator, to get rid of permission issues
        List<User> adminUsers = UserLocalServiceUtil.getRoleUsers(RoleUtilsLocalServiceUtil.getAdministratorRole().getRoleId());
        PrincipalThreadLocal.setName(adminUsers.get(0).getUserId());
        PermissionChecker permissionChecker = PermissionCheckerFactoryUtil.create(adminUsers.get(0));
        PermissionThreadLocal.setPermissionChecker(permissionChecker);

        anonymizeUsers();
        anonymizeNews();
        anonymizeMessages();
        anonymizeDlFileEntries();
        anonymizeDlFileVersions();
        anonymizeDlFolders();
        logger.info("END Anonymization");
    }

    private void anonymizeUsers () {
        try {
            int nbUsers = 0;
            List<User> allUsers = UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            int nbTotalUsers = allUsers.size();
            for (User user : allUsers) {
                logger.info("Processing user " + user.getFullName() + " (id " + user.getUserId() + ")");

                try {
                    // Skip default user, tech user and do-not-reply user
                    if (user.getUserId() == 10205 || user.getUserId() == 11105 || user.getUserId() == 8042904) {
                        continue;
                    }

                    String oldUserName = user.getFirstName() + " " + user.getLastName();
                    // Lastname
                    String newLastName = anonymizeLastName(user.getLastName());
                    //logger.info("  > lastName " + user.getLastName() + " -> " + newLastName);
                    user.setLastName(newLastName);

                    //String newFirstName = renameFirstName(user.getFirstName());
                    int otherUserIndex = (this.rand.nextInt(nbTotalUsers - 1));
                    String newFirstName = allUsers.get(otherUserIndex).getFirstName();

                    //logger.info("  > firstName " + user.getFirstName() + " -> " + newFirstName);
                    user.setFirstName(newFirstName);

                    userNameMap.put(oldUserName, newFirstName + " " + newLastName);

                    // Screenname
                    // firstletter of firstName + 5 first letters of lastName + optional incremental index
                    String newScreenName = generateScreenName(user.getCompanyId(), newLastName, user.getFirstName());
                    //logger.info("  > screenName " + user.getScreenName() + " -> " + newScreenName);
                    user.setScreenName(newScreenName);

                    // Email
                    // screenname + "@pentila.com"
                    String newEmail = newScreenName + "@weprode.com";
                    //logger.info("  > email " + user.getEmailAddress()+ " -> " + newEmail);
                    user.setEmailAddress(newEmail);

                    // Greetings
                    user.setGreeting("Bienvenue " + user.getFullName());

                    // Update user
                    UserLocalServiceUtil.updateUser(user);

                    UserContact userContact = UserContactLocalServiceUtil.getUserContactByUserId(user.getUserId());
                    if (userContact != null) {
                        userContact.setMobilePhone("06.11.22.33.44");
                        userContact.setHomePhone("04.76.55.22.88");
                        userContact.setProPhone("01.33.77.00.66");
                        userContact.setAddress("10 rue des écoles GENEVE");
                        UserContactLocalServiceUtil.updateUserContact(userContact);
                    }

                } catch (Exception e) {
                    logger.error("Error processing user " + user.getUserId(), e);
                }
                nbUsers++;
                if (nbUsers % 100 == 0) {
                    logger.info("Processed " + nbUsers + " / " + allUsers.size() + " users");
                }
            }
            logger.info("userNameMap has " + userNameMap.size() + " elements");
        } catch (Exception e) {
            logger.error("Error anonymizing users", e);
        }

    }

    private void anonymizeNews () {
        try {
            logger.info("Start anonymizing news ...");
            List<News> newsList = NewsLocalServiceUtil.getNewses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            for (News news : newsList) {
                news.setContent(anonymizeContent(news.getContent()));
                news.setTitle(anonymizeContent(news.getTitle()));
                NewsLocalServiceUtil.updateNews(news);
            }
            logger.info("End anonymizing news");
        } catch (Exception e) {
            logger.error("Error anonymizing blog entries", e);
        }
    }

    private void anonymizeMessages () {
        try {
            logger.info("Start anonymizing messages ...");
            int nbMessages = 0;
            List<Message> messages = MessageLocalServiceUtil.getMessages(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            for (Message message : messages) {
                try {
                    message.setMessageSubject(anonymizeContent(message.getMessageSubject()));
                    MessageLocalServiceUtil.updateMessage(message);
                    MessageContent messageContent = MessageContentLocalServiceUtil.getMessageContent(message.getMessageId());
                    messageContent.setMessageContent(anonymizeContent(messageContent.getMessageContent()));
                    MessageContentLocalServiceUtil.updateMessageContent(messageContent);
                } catch (Exception e) {
                    logger.error("Error anonymizing message " + message.getMessageId(), e);
                }
                nbMessages++;
                if (nbMessages % 100 == 0) {
                    logger.info("Processed " + nbMessages + " / " + messages.size() + " messages");
                }
            }
            logger.info("End anonymizing messages");
        } catch (Exception e) {
            logger.error("Error anonymizing messages", e);
        }
    }

    private void anonymizeDlFileEntries () {
        try {
            logger.info("Start anonymizing file entries ...");
            List<DLFileEntry> dlFileEntries = DLFileEntryLocalServiceUtil.getDLFileEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            int nbFileEntries = 0;
            for (DLFileEntry dlFileEntry : dlFileEntries) {
                try {
                    dlFileEntry.setUserName(userNameMap.getOrDefault(dlFileEntry.getUserName(), "Marc Assin"));
                    DLFileEntryLocalServiceUtil.updateDLFileEntry(dlFileEntry);
                    nbFileEntries++;
                } catch (Exception e) {
                    logger.error("Error anonymizing file entry " + dlFileEntry.getFileEntryId(), e);
                }
                if (nbFileEntries % 100 == 0) {
                    logger.info("Processed " + nbFileEntries + " / " + dlFileEntries.size() + " file entries");
                }
            }
        } catch (Exception e) {
            logger.error("Error anonymizing DlFileEntries", e);
        }
    }

    private void anonymizeDlFileVersions () {
        try {
            logger.info("Start anonymizing file versions ...");
            List<DLFileVersion> dlFileVersions = DLFileVersionLocalServiceUtil.getDLFileVersions(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            int nbFileVersions = 0;
            for (DLFileVersion dlFileVersion : dlFileVersions) {
                if (userNameMap.containsKey(dlFileVersion.getUserName())) {
                    dlFileVersion.setUserName(userNameMap.get(dlFileVersion.getUserName()));
                    dlFileVersion.setStatusByUserName(userNameMap.get(dlFileVersion.getUserName()));
                } else {
                    dlFileVersion.setUserName("Marc Assin");
                    dlFileVersion.setStatusByUserName("Marc Assin");
                }
                DLFileVersionLocalServiceUtil.updateDLFileVersion(dlFileVersion);
                nbFileVersions++;
                if (nbFileVersions % 100 == 0) {
                    logger.info("Processed " + nbFileVersions + " / " + dlFileVersions.size() + " file versions");
                }
            }
        } catch (Exception e) {
            logger.error("Error anonymizing dlfileversions", e);
        }
    }

    private void anonymizeDlFolders () {
        try {
            logger.info("Start anonymizing folders ...");
            List<DLFolder> dlFolders = DLFolderLocalServiceUtil.getDLFolders(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            int nbFolders = 0;
            for (DLFolder dlFolder : dlFolders) {
                dlFolder.setUserName(userNameMap.getOrDefault(dlFolder.getUserName(), "Marc Assin"));
                DLFolderLocalServiceUtil.updateDLFolder(dlFolder);
                nbFolders++;
                if (nbFolders % 100 == 0) {
                    logger.info("Processed " + nbFolders + " / " + dlFolders.size() + " folders");
                }
            }
        } catch (Exception e) {
            logger.error("Error anonymizing dl folders", e);
        }
    }


    private String anonymizeContent (String inputString) {
        StringBuilder anonymizedContent = new StringBuilder();
        // Pick up in Lorem Ipsum for the length of the input string
        while (anonymizedContent.length() < inputString.length()) {
            anonymizedContent.append(LOREM_IPSUM, 0, Math.min(LOREM_IPSUM.length(), inputString.length() - anonymizedContent.length()));
        }
        return anonymizedContent.toString();
    }

    // Remove first letter
    // Move last letter at the beginning
    // Add random letter every 3 letters
    private String anonymizeLastName(String lastName) {

        if (lastName.length() <= 2) {
            return "Zz";
        }
        char[] chars = lastName.toLowerCase().toCharArray();

        // Move last letter at the beginning
        String newLastName = "" + chars[chars.length - 1];
        newLastName = newLastName.toUpperCase();

        // Remove first letter
        for (int i = 1; i < chars.length - 1; i++ ) {
            newLastName = newLastName + chars[i];
            // Add random letter every 3 letters
            if (i % 3 == 0) {
                char randomChar = (char)(this.rand.nextInt(26) + 'a');
                newLastName += randomChar;
            }
        }
        return newLastName;
    }


    private String generateScreenName(long companyId, String lastname, String firstname) {

        String formattedFirstName = quoteString(firstname);
        String formattedLastName = quoteString(lastname);

        if (formattedLastName.length() > 5) {
            formattedLastName = formattedLastName.substring(0, 5);
        }

        String preLogin = "";

        if (formattedFirstName.length() > 0) {
            char firstLetter = formattedFirstName.toCharArray()[0];
            preLogin = firstLetter + formattedLastName;
        } else {
            preLogin = formattedLastName;
        }

        preLogin = preLogin.toLowerCase();
        String finalLogin = preLogin;

        boolean found = true;

        while (found) {
            User user = null;
            try {
                user = UserLocalServiceUtil.fetchUserByScreenName(companyId, finalLogin);
            } catch (Exception e) {
                // Nothing
            }
            if (user == null) {
                found = false;
                break;
            }
            finalLogin = preLogin + this.rand.nextInt(99);
        }

        return finalLogin;
    }

    private static String quoteString(String s) {
        StringBuilder res = new StringBuilder();
        final char[] chars = s.toCharArray();
        for (final char c : chars) {
            if (((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z'))) {
                res.append(c);
            }
        }
        return res.toString();
    }

    private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis ac ipsum erat. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aenean bibendum libero a varius pellentesque. Nulla mollis aliquet lorem, ut aliquam elit commodo sit amet. Fusce convallis varius nisl, eget pretium sem. Proin sit amet pretium orci, sed finibus tortor. Sed nec massa ante. Vivamus id nibh hendrerit, blandit nulla sit amet, scelerisque nisi. Morbi pulvinar rutrum urna vitae cursus.\n" +
            "\n" +
            "Sed est erat, fringilla a convallis quis, dapibus non velit. Mauris laoreet molestie orci, at pellentesque mauris euismod lacinia. Curabitur nisi nulla, mollis eu nulla commodo, aliquam posuere ligula. Phasellus aliquam tempor neque, ac suscipit dui semper vel. Pellentesque tincidunt elementum velit, sit amet rhoncus odio ornare vitae. Aenean interdum turpis quis mauris venenatis varius. Nunc fringilla, leo a commodo commodo, lacus tellus imperdiet nisi, sed eleifend velit ante auctor tortor. Integer lobortis faucibus nunc quis iaculis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Duis at cursus quam. Nullam blandit accumsan enim. Nulla molestie enim eget nibh placerat convallis. In sed cursus elit.\n" +
            "\n" +
            "Suspendisse vitae est eu nulla condimentum scelerisque sollicitudin in elit. Pellentesque risus tortor, rhoncus eget pretium in, pharetra ac risus. Nulla varius dui congue nisi congue iaculis sit amet vel justo. Donec a tortor eget risus molestie aliquet ut sit amet neque. Donec semper sodales ornare. Nulla eget enim efficitur, rutrum dolor et, vulputate nulla. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Sed aliquam mattis ligula vehicula semper. In rhoncus venenatis risus lobortis pulvinar. In mattis, nulla a placerat mollis, ligula orci elementum augue, sit amet euismod erat lorem ac erat. Donec et tellus ex.\n" +
            "\n" +
            "Ut sit amet justo felis. Phasellus est ligula, ultricies sed ornare non, congue a odio. Nunc ut sollicitudin nisi. Phasellus molestie libero sit amet sollicitudin vestibulum. Curabitur quis mollis nisi, ut euismod dolor. Duis blandit ante ante, a fermentum erat semper ac. Mauris vel porttitor nisi, non mattis leo. Cras eleifend convallis enim ut porta. Vivamus quis purus malesuada, vehicula neque sed, lobortis justo. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Curabitur varius rhoncus nunc eget congue. Fusce fringilla ipsum at leo pulvinar consequat. Nullam sit amet efficitur massa. Suspendisse potenti. Cras ut molestie est, vitae tristique lectus. Morbi id commodo mi.\n" +
            "\n" +
            "Morbi est nunc, sagittis at maximus ut, lacinia at urna. Morbi consectetur, augue sed dictum dapibus, augue augue aliquam velit, ut aliquam nunc leo ut arcu. Nulla nulla lacus, volutpat eu aliquam nec, lacinia sit amet purus. Vestibulum nec consequat nisi. Aliquam congue vel quam ut fringilla. Etiam ullamcorper lectus eget sem aliquam, vel vulputate sapien egestas. Aliquam erat volutpat.";

    private final Random rand = new Random();
}
