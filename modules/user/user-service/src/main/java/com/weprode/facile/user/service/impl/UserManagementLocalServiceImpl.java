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

package com.weprode.facile.user.service.impl;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.PasswordPolicy;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.security.RandomUtil;
import com.liferay.portal.kernel.security.SecureRandom;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.PasswordPolicyLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.Validator;
import com.weprode.facile.commons.properties.NeroSystemProperties;
import com.weprode.facile.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.facile.preference.model.UserProperties;
import com.weprode.facile.preference.service.UserPropertiesLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.user.service.UserManagementLocalServiceUtil;
import com.weprode.facile.user.service.UserUtilsLocalServiceUtil;
import com.weprode.facile.user.service.base.UserManagementLocalServiceBaseImpl;
import com.liferay.portal.util.PropsValues;
import org.osgi.service.component.annotations.Component;

import javax.mail.internet.InternetAddress;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Component(
        property = "model.class.name=com.weprode.facile.user.model.UserManagement",
        service = AopService.class
)
public class UserManagementLocalServiceImpl extends UserManagementLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(UserManagementLocalServiceImpl.class);

    public User createUser(long companyId, String lastName, String firstName, String email, Date birthday) {
        return UserManagementLocalServiceUtil.createUser(companyId, lastName, firstName, email, birthday, false);
    }

    public User createUser(long companyId, String lastName, String firstName, String email, Date birthday, boolean sendUserLogin) {
        return UserManagementLocalServiceUtil.createUser(companyId, lastName, firstName, email, true, birthday, sendUserLogin, null);
    }

    public User createUser(long companyId, String lastName, String firstName, String email, boolean isMale, Date birthday,
                           boolean sendUserLogin, String password) {
        int birthdayMonth = Calendar.JANUARY;
        int birthdayDay = 1;
        int birthdayYear = 1970;

        if (!Validator.isNull(birthday)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(birthday);
            birthdayMonth = cal.get(Calendar.MONTH);
            birthdayDay = cal.get(Calendar.DAY_OF_MONTH);
            birthdayYear = cal.get(Calendar.YEAR);
        }

        try {
            logger.info("Adding user "+firstName+" "+lastName+ " to portal.");
            ServiceContext serviceContext = new ServiceContext();

            if (password == null) {
                password = generatePassword();
                logger.info("generated password = " + password);
            }

            String screenName = UserUtilsLocalServiceUtil.generateLogin(lastName, firstName);
            if (Validator.isNull(email)) {
                email = screenName + PropsUtil.get(NeroSystemProperties.MAIL_DEFAULT_SUFFIX);
            }

            boolean autoScreenName = Validator.isNull(screenName);
            User user = UserLocalServiceUtil.addUser(0, companyId, false, password, password, autoScreenName,
                    screenName, email, LocaleUtil.getDefault(), firstName, StringPool.BLANK, lastName, 0,
                    0, isMale, birthdayMonth, birthdayDay, birthdayYear, StringPool.BLANK, UserConstants.TYPE_REGULAR, null,
                    null, null, null, false, serviceContext);

            Date today = new Date();
            user.setLastLoginDate(today);
            user.setModifiedDate(today);
            user.setNew(false);
            user.setPasswordModified(true);
            user.setPasswordReset(true);
            user.setReminderQueryQuestion("@new@");
            user.setReminderQueryAnswer("@new@");
            UserLocalServiceUtil.updateUser(user);

            if (sendUserLogin) {
                sendLoginPasswordToUser(email, screenName, password);
            }

            return user;

        } catch (Exception e) {
            logger.error("Error while creating new user with lastName=" + lastName+", firstName=" + firstName + " and email=" + email, e);
        }

        return null;
    }

    public User createManualUser(String lastName, String firstName, String email, Date birthday, long roleId, long schoolId) {
        return UserManagementLocalServiceUtil.createManualUser(lastName, firstName, email, birthday, roleId, schoolId, true, null);
    }

    public User createManualUser(String lastName, String firstName, String email, Date birthday, long roleId, long schoolId, boolean sendUserLogin) {
        return UserManagementLocalServiceUtil.createManualUser(lastName, firstName, email, birthday, roleId, schoolId, sendUserLogin, null);
    }

    public User createManualUser(String lastName, String firstName, String email, Date birthday, long roleId, long schoolId,
                                 boolean sendUserLogin, String password) {

        long companyId = PortalUtil.getDefaultCompanyId();
        User createdUser = UserManagementLocalServiceUtil.createUser(companyId, lastName, firstName, email, true, birthday, sendUserLogin, password);

        if (createdUser == null) {
            return null;
        }

        try {
            // Set user as manually created
            UserProperties userProperties = UserPropertiesLocalServiceUtil.getUserProperties(createdUser.getUserId());
            userProperties.setManualAccount(true);
            UserPropertiesLocalServiceUtil.updateUserProperties(userProperties);

            RoleLocalServiceUtil.addUserRole(createdUser.getUserId(), roleId);
            // Add personal role if needed
            if (RoleUtilsLocalServiceUtil.getAgentsRoleIds().contains(roleId)) {
                RoleLocalServiceUtil.addUserRole(createdUser.getUserId(), RoleUtilsLocalServiceUtil.getPersonalRole().getRoleId());
            }

            if (schoolId > 0) {
                Organization school = OrganizationLocalServiceUtil.getOrganization(schoolId);
                synchronizeUserSchool(createdUser.getUserId(), school.getOrganizationId());
            }
        } catch (Exception e) {
            logger.error("Could not finalize creation for new userId=" + createdUser.getUserId(), e);
            return null;
        }

        return createdUser;
    }

    public void synchronizeUserSchool (long userId, long schoolId) {

        try {
            // Add user to root organization
            User user = UserLocalServiceUtil.getUser(userId);
            Organization rootOrg = OrgUtilsLocalServiceUtil.getOrCreateRootOrg(user.getCompanyId());
            if (!UserLocalServiceUtil.hasOrganizationUser(rootOrg.getOrganizationId(), userId) ) {
                UserLocalServiceUtil.addOrganizationUsers(rootOrg.getOrganizationId(), new long[]{userId});
            }

            // Add user to this etab if not present
            if (!OrganizationLocalServiceUtil.hasUserOrganization(userId, schoolId)) {
                UserLocalServiceUtil.addOrganizationUsers(schoolId, new long[]{user.getUserId()});
            }

            // Update rattach school in user properties
            UserProperties userProp = UserPropertiesLocalServiceUtil.getUserProperties(userId);
            userProp.setEtabId(schoolId);
            UserPropertiesLocalServiceUtil.updateUserProperties(userProp);

        } catch (Exception e) {
            logger.error("Error when synchronizing school-level organizations for userId " + userId, e);
        }
    }

    /**
     * Send login and password to the created user
     */
    private static void sendLoginPasswordToUser(String recipientEmail, String login, String password) {
        String fromAdress = PropsUtil.get(NeroSystemProperties.MAIL_NO_REPLY_ADDRESS);
        String portalURL = PropsUtil.get(NeroSystemProperties.PORTAL_URL);

        String emailSubject = "Bienvenue dans l'ENT";
        String emailContent = "Bonjour,<br/>Un compte vient d'&ecirc;tre cr&eacute;&eacute; dans l'ENT avec votre adresse e-mail.<br/>"
                + "Connectez-vous d&egrave;s &agrave; pr&eacute;sent sur <a href=\""+ portalURL +"\">" + portalURL + "</a> (acc&egrave;s personnel r&eacute;gion et autres profils) &agrave; l'aide des identifiants suivants :<br/><br/>"
                + "Identifiant : " + login + "<br/>"
                + "Mot de passe : " + password + "<br/><br/>"
                + "Meilleurs messages,<br/>"
                + "L'&eacute;quipe ENT";

        try {
            MailMessage mailMessage = new MailMessage();
            mailMessage.setHTMLFormat(true);
            mailMessage.setFrom(new InternetAddress(fromAdress));
            mailMessage.setTo(new InternetAddress(recipientEmail));
            mailMessage.setSubject(emailSubject);
            mailMessage.setBody(emailContent);

            MailServiceUtil.sendEmail(mailMessage);
        } catch (Exception e) {
            logger.error("Could not send email to " + recipientEmail, e);
        }
    }

    public String generatePassword() {
        PasswordPolicy passwordPolicy;

        try {
            passwordPolicy = PasswordPolicyLocalServiceUtil.getDefaultPasswordPolicy(PortalUtil.getDefaultCompanyId());
        } catch (Exception e) {
            logger.debug(e);
            return null;
        }

        final String _generatorCompleteCharset = StringBundler.concat(
                PropsValues.PASSWORDS_PASSWORDPOLICYTOOLKIT_GENERATOR_CHARSET_LOWERCASE,
                PropsValues.PASSWORDS_PASSWORDPOLICYTOOLKIT_GENERATOR_CHARSET_NUMBERS,
                PropsValues.PASSWORDS_PASSWORDPOLICYTOOLKIT_GENERATOR_CHARSET_SYMBOLS,
                PropsValues.PASSWORDS_PASSWORDPOLICYTOOLKIT_GENERATOR_CHARSET_UPPERCASE);

        final char[] generatorLowerCaseCharsetArray = getSortedCharArray(PropsValues.PASSWORDS_PASSWORDPOLICYTOOLKIT_GENERATOR_CHARSET_LOWERCASE);
        final char[] generatorNumbersCharsetArray = getSortedCharArray(PropsValues.PASSWORDS_PASSWORDPOLICYTOOLKIT_GENERATOR_CHARSET_NUMBERS);
        final char[] generatorSymbolsCharsetArray = getSortedCharArray(PropsValues.PASSWORDS_PASSWORDPOLICYTOOLKIT_GENERATOR_CHARSET_SYMBOLS);
        final char[] generatorUpperCaseCharsetArray = getSortedCharArray(PropsValues.PASSWORDS_PASSWORDPOLICYTOOLKIT_GENERATOR_CHARSET_UPPERCASE);

        char[] generatorAlphanumericCharsetArray = ArrayUtil.append(
                generatorLowerCaseCharsetArray, generatorUpperCaseCharsetArray,
                generatorNumbersCharsetArray);

        Arrays.sort(generatorAlphanumericCharsetArray);

        int alphanumericActualMinLength = passwordPolicy.getMinLowerCase() + passwordPolicy.getMinNumbers() + passwordPolicy.getMinUpperCase();
        int alphanumericMinLength = Math.max(passwordPolicy.getMinAlphanumeric(), alphanumericActualMinLength);

        int passwordMinLength = Math.max(
                passwordPolicy.getMinLength(),
                alphanumericMinLength + passwordPolicy.getMinSymbols());

        StringBundler sb = new StringBundler(6);

        if (passwordPolicy.getMinLowerCase() > 0) {
            sb.append(
                    getRandomString(passwordPolicy.getMinLowerCase(), generatorLowerCaseCharsetArray));
        }

        if (passwordPolicy.getMinNumbers() > 0) {
            sb.append(
                    getRandomString(passwordPolicy.getMinNumbers(), generatorNumbersCharsetArray));
        }

        if (passwordPolicy.getMinSymbols() > 0) {
            sb.append(
                    getRandomString(passwordPolicy.getMinSymbols(),generatorSymbolsCharsetArray));
        }

        if (passwordPolicy.getMinUpperCase() > 0) {
            sb.append(
                    getRandomString(passwordPolicy.getMinUpperCase(),generatorUpperCaseCharsetArray));
        }

        if (alphanumericMinLength > alphanumericActualMinLength) {
            int count = alphanumericMinLength - alphanumericActualMinLength;

            sb.append(
                    getRandomString(count, generatorAlphanumericCharsetArray));
        }

        if (passwordMinLength >
                (alphanumericMinLength + passwordPolicy.getMinSymbols())) {

            int count =
                    passwordMinLength -
                            (alphanumericMinLength + passwordPolicy.getMinSymbols());

            sb.append(
                    PwdGenerator.getPassword(_generatorCompleteCharset, count));
        }

        if (sb.index() == 0) {
            sb.append(
                    PwdGenerator.getPassword(
                            _generatorCompleteCharset,
                            PropsValues.PASSWORDS_DEFAULT_POLICY_MIN_LENGTH));
        }

        return RandomUtil.shuffle(new SecureRandom(), sb.toString());
    }

    private static String getRandomString(int count, char[] chars) {
        Random random = new SecureRandom();

        StringBundler sb = new StringBundler(count);

        for (int i = 0; i < count; i++) {
            int index = random.nextInt(chars.length);

            sb.append(chars[index]);
        }

        return sb.toString();
    }

    private static char[] getSortedCharArray(String s) {
        char[] chars = s.toCharArray();

        Arrays.sort(chars);

        return chars;
    }


}
