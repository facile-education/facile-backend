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

package com.weprode.facile.commons.wrapper;

import com.liferay.login.web.constants.LoginPortletKeys;
import com.liferay.portal.kernel.captcha.CaptchaException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.RequiredReminderQueryException;
import com.liferay.portal.kernel.exception.SendPasswordException;
import com.liferay.portal.kernel.exception.UserActiveException;
import com.liferay.portal.kernel.exception.UserEmailAddressException;
import com.liferay.portal.kernel.exception.UserLockoutException;
import com.liferay.portal.kernel.exception.UserReminderQueryException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;
import java.util.Iterator;
import java.util.Set;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + LoginPortletKeys.FAST_LOGIN,
                "javax.portlet.name=" + LoginPortletKeys.LOGIN,
                "mvc.command.name=/login/forgot_password",
                "service.ranking:Integer=1000"
        },
        service = MVCActionCommand.class
)

public class ForgotPasswordActionCommand extends BaseMVCActionCommand {

    private static final Log logger = LogFactoryUtil.getLog(ForgotPasswordActionCommand.class);

    @Override
    protected void doProcessAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
            throws Exception {

        ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
                WebKeys.THEME_DISPLAY);

        Company company = themeDisplay.getCompany();

        if (!company.isSendPasswordResetLink()) {
            throw new PrincipalException.MustBeEnabled(
                    company.getCompanyId(),
                    PropsKeys.COMPANY_SECURITY_SEND_PASSWORD_RESET_LINK);
        }

        try {
            // logger.info("Checking captcha");
            // _checkCaptcha(actionRequest);

            _sendPassword(actionRequest, actionResponse);
        }
        catch (Exception exception) {

            if (exception instanceof CaptchaException ||
                    exception instanceof UserEmailAddressException ||
                    exception instanceof UserReminderQueryException) {

                SessionErrors.add(actionRequest, exception.getClass());
            }
            else if (exception instanceof NoSuchUserException ||
                    exception instanceof RequiredReminderQueryException ||
                    exception instanceof SendPasswordException ||
                    exception instanceof UserActiveException ||
                    exception instanceof UserLockoutException) {

                if (PropsValues.LOGIN_SECURE_FORGOT_PASSWORD) {
                    SessionMessages.add(
                            _portal.getHttpServletRequest(actionRequest),
                            "forgotPasswordSent");

                    sendRedirect(actionRequest, actionResponse, null);
                }
                else {
                    SessionErrors.add(
                            actionRequest, exception.getClass(), exception);
                }
            }
            else {
                _portal.sendError(exception, actionRequest, actionResponse);
            }
        }
    }

    @Reference(unbind = "-")
    protected void setUserLocalService(UserLocalService userLocalService) {
        _userLocalService = userLocalService;
    }

    private User _getUser(ActionRequest actionRequest) throws Exception {
        try {
            User user = null;

            ThemeDisplay themeDisplay =
                    (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

            String authType = null;

            PortletPreferences portletPreferences =
                    actionRequest.getPreferences();

            if (portletPreferences != null) {
                authType = portletPreferences.getValue("authType", null);
            }

            if (Validator.isNull(authType)) {
                Company company = themeDisplay.getCompany();

                authType = company.getAuthType();
            }

            PortletSession portletSession = actionRequest.getPortletSession();

            String login = (String)portletSession.getAttribute(
                    WebKeys.FORGOT_PASSWORD_REMINDER_USER_EMAIL_ADDRESS);

            if (Validator.isNull(login)) {
                login = ParamUtil.getString(actionRequest, "login");
            }
            logger.info("Received forgotten password change for screenName " + login);

            if (authType != null) {
                if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
                    user = _userLocalService.getUserByEmailAddress(
                            themeDisplay.getCompanyId(), login);
                }
                else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
                    user = _userLocalService.getUserByScreenName(
                            themeDisplay.getCompanyId(), login);
                }
                else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
                    user = _userLocalService.getUserById(GetterUtil.getLong(login));
                }
                else {
                    throw new NoSuchUserException("User does not exist");
                }
                if (!user.isActive()) {
                    throw new UserActiveException("Inactive user " + user.getUuid());
                }

                _userLocalService.checkLockout(user);

                return user;
            }

        }
        catch (Exception exception) {
            if (!PropsValues.LOGIN_SECURE_FORGOT_PASSWORD) {
                throw exception;
            }
        }

        ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        User defaultUser = _userLocalService.getDefaultUser(themeDisplay.getCompanyId());
        Set<String> reminderQueryQuestions =defaultUser.getReminderQueryQuestions();

        if (!reminderQueryQuestions.isEmpty()) {
            Iterator<String> iterator = reminderQueryQuestions.iterator();
            defaultUser.setReminderQueryQuestion(iterator.next());
        }
        else {
            defaultUser.setReminderQueryQuestion("what-is-your-library-card-number");
        }
        defaultUser.setReminderQueryAnswer(defaultUser.getReminderQueryQuestion());

        return defaultUser;
    }

    private void _sendPassword(
            ActionRequest actionRequest, ActionResponse actionResponse)
            throws Exception {

        User user = _getUser(actionRequest);
        ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        Company company = themeDisplay.getCompany();

        logger.info("Sending password recovery link by email to user " + user.getFullName() + " (id " + user.getUserId() + ")");

        String fromName = "Equipe technique";
        String fromAddress = "no-reply@ent.eduge.ch";
        String toAddress = user.getEmailAddress();

        String subject = "[ENTA Genève] Changement de mot de passe";
        String body = "<p>Bonjour [$TO_NAME$],<br /><br />Cliquez sur <a href=\"[$PASSWORD_RESET_URL$]\" >ce lien</a> pour changer votre mot de passe de l&#39;ENT.<br /><br />Meilleurs messages,<br />L'équipe technique<br /></p>";

        ServiceContext serviceContext = ServiceContextFactory.getInstance(User.class.getName(), actionRequest);

        UserLocalServiceUtil.sendPassword(
                company.getCompanyId(), toAddress, fromName, fromAddress, subject,
                body, serviceContext);

        SessionMessages.add(_portal.getHttpServletRequest(actionRequest), "forgotPasswordSent");

        sendRedirect(actionRequest, actionResponse, null);
    }

    private static final Log _log = LogFactoryUtil.getLog(ForgotPasswordActionCommand.class);

    @Reference
    private Portal _portal;

    private UserLocalService _userLocalService;

}
