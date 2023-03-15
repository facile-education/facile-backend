package com.weprode.nero.eel.synchronization;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Tools used by the synchronization process
 * @author Cedric Lecarpentier
 *
 */
public class GVESynchronizationUtils {

    private GVESynchronizationUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final Log logger = LogFactoryUtil.getLog(GVESynchronizationUtils.class);

    /**
     * Adds or remove a given user from the given organization
     */
    public static Organization addOrRemoveUserFromOrg(User user, Organization org, Boolean mustHave) throws PortalException, SystemException {
        boolean hasUserOrganization = OrganizationLocalServiceUtil.hasUserOrganization(user.getUserId(), org.getOrganizationId());

        if (hasUserOrganization && Boolean.TRUE.equals(!mustHave)) {
            UserLocalServiceUtil.unsetOrganizationUsers(org.getOrganizationId(), new long[]{user.getUserId()});
        }
        if (!hasUserOrganization && Boolean.TRUE.equals(mustHave)) {
            UserLocalServiceUtil.addOrganizationUsers(org.getOrganizationId(), new long[]{user.getUserId()});
        }

        return org;
    }


    /**
     * Print the given list of users as a string, by removing last comma
     */
    public static String printAsString(List<User> userList) {
        StringBuilder result = new StringBuilder();

        for (int idx = 0 ; idx < userList.size() ; idx++) {
            User user = userList.get(idx);
            result.append(user.getFullName());
            if (idx != userList.size() - 1) {
                result.append(", ");
            }
        }

        return result.toString();
    }

    /**
     * Returns the list of users with unique users
     */
    public static List<User> unique(List<User> userList) {
        List<User> uniqueUserList = new ArrayList<>();

        for (User user : userList) {
            if (!uniqueUserList.contains(user)) {
                uniqueUserList.add(user);
            }
        }

        return uniqueUserList;
    }

    public static List<String> getFileContent(File file) {
        List<String> fileList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.ISO_8859_1))) {
            // File is encoded in ISO-8859-1
            String line;
            while ((line = br.readLine()) != null) {
                fileList.add(line);
            }
        } catch (FileNotFoundException e) {
            logger.error("File not found : "+file.getAbsolutePath());
        } catch (IOException e) {
            logger.error("IO Exception : "+file.getAbsolutePath());
        }

        logger.info("File has "+fileList.size()+" lines ...");

        return fileList;
    }

    // Keep spaces in name, uppercase the first letter and lowercase the others
    public static String formatName(String name) {
        StringBuilder formattedName = new StringBuilder();

        String[] nameTab = name.split(" ");
        for (int i = 0 ; i < nameTab.length ; i++) {
            String namePart = nameTab[i];
            // Skip double spaces
            if (namePart.equals("")) {
                continue;
            }
            formattedName.append(namePart.substring(0, 1).toUpperCase()).append(namePart.substring(1).toLowerCase());
            if (i != nameTab.length - 1) {
                formattedName.append(" ");
            }
        }

        return formattedName.toString();
    }

}

