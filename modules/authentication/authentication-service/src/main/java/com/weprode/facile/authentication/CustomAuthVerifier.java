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
package com.weprode.facile.authentication;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.cookies.CookiesManagerUtil;
import com.liferay.portal.kernel.cookies.constants.CookiesConstants;
import com.liferay.portal.kernel.encryptor.EncryptorException;
import com.liferay.portal.kernel.encryptor.EncryptorUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AccessControlContext;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifier;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifierResult;
import com.liferay.portal.kernel.security.auto.login.AutoLoginException;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.CookieKeys;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.UnicodeFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;
import com.weprode.facile.mobile.model.UserMobileToken;
import com.weprode.facile.mobile.service.UserMobileTokenLocalServiceUtil;
import com.weprode.facile.statistic.service.UserLoginLocalServiceUtil;
import org.apache.logging.log4j.ThreadContext;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Properties;

import static com.liferay.portal.kernel.service.UserLocalServiceUtil.isPasswordExpired;

@Component(
        immediate = true,
        service = AuthVerifier.class,
        property = {
                "auth.verifier.CustomAuthVerifier.urls.includes=*",
                "auth.verifier.CustomAuthVerifier.urls.excludes=/api/jsonws/mobile.mobiledevice/*"
        }
)
public class CustomAuthVerifier implements AuthVerifier {

    private static final Log logger = LogFactoryUtil.getLog(CustomAuthVerifier.class);

    private static final String FACILE_PASSWORD = "FACILE_PASSWORD";

    @Override
    public String getAuthType() {
        return HttpServletRequest.FORM_AUTH;
    }

    @Override
    public AuthVerifierResult verify(AccessControlContext accessControlContext, Properties properties) {
        logger.debug("CustomAuthVerifier for URI " + accessControlContext.getRequest().getRequestURI());

        ThreadContext.clearAll();
        ThreadContext.push("====================");

        try {
            AuthVerifierResult authVerifierResult = new AuthVerifierResult();
            long userId;

            // To remove after handling p_auth properly for third party tools
            if (accessControlContext.getRequest().getRequestURI().contains("/api/jsonws/document.mindmap/")
                    || accessControlContext.getRequest().getRequestURI().contains("/api/jsonws/document.scratch/")
                    || accessControlContext.getRequest().getRequestURI().contains("/api/jsonws/document.geogebra/")) {
                return authVerifierResult;
            }

            AuthVerifierResult portalAuthVerifierResult = checkPortalSession(accessControlContext.getRequest(), properties);

            if (portalAuthVerifierResult != null && portalAuthVerifierResult.getState() == AuthVerifierResult.State.SUCCESS) {
                return portalAuthVerifierResult;
            }

            String[] credentials = checkShibbolethSession(accessControlContext.getRequest(), accessControlContext.getResponse());
            if (credentials != null) {
                userId = Long.parseLong(credentials[0]);
                User user = UserLocalServiceUtil.getUser(userId);
                logger.debug("Connected user is " + user.getFullName());
                authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
                authVerifierResult.setPasswordBasedAuthentication(true);
                authVerifierResult.setUserId(userId);
                authVerifierResult.setPassword(credentials[1]);

                UserLoginLocalServiceUtil.addUserLogin(user, false);

                return authVerifierResult;
            }

            credentials = checkMobileAppSession(accessControlContext.getRequest(), accessControlContext.getResponse());
            if (credentials != null) {
                userId = Long.parseLong(credentials[0]);
                User user = UserLocalServiceUtil.getUser(userId);
                logger.debug("Connected user on mobile app is " + user.getFullName());
                authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
                authVerifierResult.setPasswordBasedAuthentication(true);
                authVerifierResult.setUserId(userId);
                authVerifierResult.setPassword(credentials[1]);

                UserLoginLocalServiceUtil.addUserLogin(user, true);

                return authVerifierResult;
            }

            credentials = checkRememberMeSession(accessControlContext.getRequest(), accessControlContext.getResponse());
            if (credentials != null) {
                userId = Long.parseLong(credentials[0]);
                User user = UserLocalServiceUtil.getUser(userId);
                logger.debug("Connected user is " + user.getFullName());
                authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
                authVerifierResult.setPasswordBasedAuthentication(true);
                authVerifierResult.setUserId(userId);
                authVerifierResult.setPassword(credentials[1]);

                // Should we add UserLogin here ? -> UserLoginLocalServiceUtil.addUserLogin(identifiedUser, true)

                return authVerifierResult;
            }

            return authVerifierResult;
        } catch (Exception e) {
            logger.error("Exception in AuthVerifier", e);
        }

        return null;
    }

    private AuthVerifierResult checkPortalSession(HttpServletRequest httpServletRequest, Properties properties) {
        try {
            AuthVerifierResult authVerifierResult = new AuthVerifierResult();

            User user = PortalUtil.getUser(httpServletRequest);

            boolean checkCSRFToken = GetterUtil.getBoolean(properties.get("check.csrf.token"), true);

            if (checkCSRFToken) {
                HttpServletRequest originalHttpServletRequest = PortalUtil.getOriginalServletRequest(httpServletRequest);
                String requestURI = originalHttpServletRequest.getRequestURI();

                try {
                    AuthTokenUtil.checkCSRFToken(originalHttpServletRequest, requestURI);
                } catch (PrincipalException principalException) {
                    if (logger.isDebugEnabled()) {
                        logger.error(
                                StringBundler.concat(
                                        "Unable to verify CSRF token for ", requestURI,
                                        ": ", principalException.getMessage()));
                    }

                    return authVerifierResult;
                }
            }

            if ((user != null) && !user.isDefaultUser()) {
                logger.debug("PortalSession: authenticated user is " + user.getScreenName());
                authVerifierResult.setPasswordBasedAuthentication(true);
                authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
                authVerifierResult.setUserId(user.getUserId());

                return authVerifierResult;
            }

        } catch (Exception e) {
            logger.error(e);
        }

        return null;
    }

    private String[] checkRememberMeSession(HttpServletRequest httpServletRequest,
                                            HttpServletResponse httpServletResponse) throws EncryptorException, PortalException {

        String autoUserId = CookiesManagerUtil.getCookieValue(
                CookiesConstants.NAME_ID, httpServletRequest, false);
        String autoPassword = CookiesManagerUtil.getCookieValue(
                FACILE_PASSWORD, httpServletRequest, false);
        String autoPasswordLfr = CookiesManagerUtil.getCookieValue(
                CookiesConstants.NAME_PASSWORD, httpServletRequest, false);
        String rememberMe = CookiesManagerUtil.getCookieValue(
                CookiesConstants.NAME_REMEMBER_ME, httpServletRequest, false);

        // TODO remove and understand why we have null values from above code
        if (httpServletRequest.getCookies() != null) {
            for (Cookie cookie : httpServletRequest.getCookies()) {
                logger.debug("Cookie : " + cookie.getName() + "=" + cookie.getValue());
                switch (cookie.getName()) {
                    case CookiesConstants.NAME_ID:
                        autoUserId = cookie.getValue();
                        break;
                    case CookiesConstants.NAME_PASSWORD:
                        autoPasswordLfr = cookie.getValue();
                        break;
                    case FACILE_PASSWORD:
                        autoPassword = cookie.getValue();
                        break;
                    case CookiesConstants.NAME_REMEMBER_ME:
                        rememberMe = cookie.getValue();
                        break;
                    default:
                        break;
                }
            }
        }

        String proxyPath = PortalUtil.getPathProxy();
        String contextPath = PortalUtil.getPathContext();

        if (proxyPath.equals(contextPath)) {
            if (Validator.isNotNull(httpServletRequest.getContextPath())) {
                rememberMe = Boolean.TRUE.toString();
            }
        }
        else {
            if (!contextPath.equals(httpServletRequest.getContextPath())) {
                rememberMe = Boolean.TRUE.toString();
            }
        }

        String[] credentials = getRememberMeCredentials(httpServletRequest, httpServletResponse,
                autoUserId, autoPassword, autoPasswordLfr, rememberMe);

        if (credentials != null) {
            Company company = CompanyLocalServiceUtil.getCompany(PortalUtil.getDefaultCompanyId());

            User guestUser = UserLocalServiceUtil.getDefaultUser(
                    company.getCompanyId());

            long userId = GetterUtil.getLong(credentials[0]);

            if (guestUser.getUserId() == userId) {
                removeCookies(httpServletRequest, httpServletResponse);

                return null;
            }
        }

        return credentials;
    }

    private String[] getRememberMeCredentials(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
              String autoUserId, String autoPassword, String autoPasswordLfr, String rememberMe) throws PortalException, EncryptorException {
        String[] credentials = null;

        if (Validator.isNotNull(autoUserId) &&
                (Validator.isNotNull(autoPassword) || Validator.isNotNull(autoPasswordLfr)) &&
                Validator.isNotNull(rememberMe)) {

            Company company = CompanyLocalServiceUtil.getCompany(PortalUtil.getDefaultCompanyId());

            if (company.isAutoLogin()) {
                KeyValuePair kvp;

                if (Validator.isNotNull(autoPassword)) {
                    kvp = decryptUserId(
                            company,
                            new String(UnicodeFormatter.hexToBytes(autoUserId)),
                            new String(UnicodeFormatter.hexToBytes(autoPassword)));
                } else {
                    kvp = UserLocalServiceUtil.decryptUserId(
                            company.getCompanyId(),
                            new String(UnicodeFormatter.hexToBytes(autoUserId)),
                            new String(UnicodeFormatter.hexToBytes(autoPasswordLfr)));
                }

                String tokenKey = WebKeys.AUTHENTICATION_TOKEN.concat("#CSRF");
                String currentToken = httpServletRequest.getParameter("p_auth");
                logger.debug("Set session attribute " + tokenKey + " with value " + currentToken);
                httpServletRequest.getSession().setAttribute(tokenKey, currentToken);

                User user = UserLocalServiceUtil.getUser(Long.parseLong(kvp.getKey()));
                logger.info("User authenticated with RememberMe : " + user.getScreenName() + " (" + user.getEmailAddress() + "), uri=" + httpServletRequest.getRequestURI());
                credentials = new String[3];
                credentials[0] = kvp.getKey();
                credentials[1] = kvp.getValue();
                credentials[2] = Boolean.FALSE.toString();

                setSessionCookies(httpServletRequest, httpServletResponse, user, user.getScreenName(), user.getPassword(), true);
            }
        }

        return credentials;
    }

    protected void removeCookies(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        String domain = CookiesManagerUtil.getDomain(httpServletRequest);

        CookiesManagerUtil.deleteCookies(
                domain, httpServletRequest, httpServletResponse,
                CookiesConstants.NAME_ID);
        CookiesManagerUtil.deleteCookies(
                domain, httpServletRequest, httpServletResponse,
                CookiesConstants.NAME_PASSWORD);
        CookiesManagerUtil.deleteCookies(
                domain, httpServletRequest, httpServletResponse,
                FACILE_PASSWORD);
    }

    private void setSessionCookies (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                   User user, String login, String password, boolean rememberMe) throws PortalException, EncryptorException {
        logger.debug("Set session cookies for " + login + " - rememberMe=" + rememberMe);

        httpServletRequest = PortalUtil.getOriginalServletRequest(
                httpServletRequest);

        HttpSession httpSession = httpServletRequest.getSession();

        Company company = PortalUtil.getCompany(httpServletRequest);

        String domain = CookiesManagerUtil.getDomain(httpServletRequest);

        if (Validator.isNull(domain)) {
            domain = null;
        }

        String userIdString = String.valueOf(user.getUserId());

        httpSession.setAttribute("j_username", userIdString);
        httpSession.setAttribute("j_password", user.getPassword());
        httpSession.setAttribute("j_remoteuser", userIdString);

        Cookie companyIdCookie = new Cookie(
                CookiesConstants.NAME_COMPANY_ID,
                String.valueOf(company.getCompanyId()));

        companyIdCookie.setSecure(true);
        companyIdCookie.setHttpOnly(true);

        if (domain != null) {
            companyIdCookie.setDomain(domain);
        }

        companyIdCookie.setPath(StringPool.SLASH);

        Cookie idCookie = new Cookie(
                CookiesConstants.NAME_ID,
                UnicodeFormatter.bytesToHex(
                    EncryptorUtil.encrypt(company.getKeyObj(), userIdString).getBytes()));

        idCookie.setSecure(true);
        idCookie.setHttpOnly(true);

        if (domain != null) {
            idCookie.setDomain(domain);
        }

        idCookie.setPath(StringPool.SLASH);

        int loginMaxAge = PropsValues.COMPANY_SECURITY_AUTO_LOGIN_MAX_AGE;

        if (rememberMe) {
            companyIdCookie.setMaxAge(loginMaxAge);
            idCookie.setMaxAge(loginMaxAge);
        }
        else {

            // This was explicitly changed from 0 to -1 so that the cookie lasts
            // as long as the browser. This allows an external servlet wrapped
            // in AutoLoginFilter to work throughout the client connection. The
            // cookies ARE removed on an actual logout, so there is no security
            // issue. See LEP-4678 and LEP-5177.

            companyIdCookie.setMaxAge(-1);
            idCookie.setMaxAge(-1);
        }

        httpServletResponse.addCookie(companyIdCookie);
        httpServletResponse.addCookie(idCookie);

        if (rememberMe) {
            setRememberMeCookies(httpServletRequest, httpServletResponse, company, domain, login, password, loginMaxAge);
        }
    }

    private void setRememberMeCookies(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
              Company company, String domain, String login, String password, int loginMaxAge) throws EncryptorException {
        Cookie loginCookie = new Cookie(CookiesConstants.NAME_LOGIN, UnicodeFormatter.bytesToHex(login.getBytes()));

        loginCookie.setSecure(true);
        loginCookie.setHttpOnly(true);

        if (domain != null) {
            loginCookie.setDomain(domain);
        }

        loginCookie.setMaxAge(loginMaxAge);
        loginCookie.setPath(StringPool.SLASH);

        httpServletResponse.addCookie(loginCookie);


        boolean hasLfrPasswordCookie = false;
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(CookiesConstants.NAME_PASSWORD)) {
                hasLfrPasswordCookie = true;
                break;
            }
        }

        if (!hasLfrPasswordCookie) {
            Cookie passwordCookie = new Cookie(
                    FACILE_PASSWORD,
                    UnicodeFormatter.bytesToHex(
                            EncryptorUtil.encrypt(company.getKeyObj(), password).getBytes()));

            passwordCookie.setSecure(true);
            passwordCookie.setHttpOnly(true);

            if (domain != null) {
                passwordCookie.setDomain(domain);
            }

            passwordCookie.setMaxAge(loginMaxAge);
            passwordCookie.setPath(StringPool.SLASH);

            httpServletResponse.addCookie(passwordCookie);
        }

        Cookie rememberMeCookie = new Cookie(
                CookiesConstants.NAME_REMEMBER_ME, Boolean.TRUE.toString());

        rememberMeCookie.setSecure(true);
        rememberMeCookie.setHttpOnly(true);

        if (domain != null) {
            rememberMeCookie.setDomain(domain);
        }

        rememberMeCookie.setMaxAge(loginMaxAge);
        rememberMeCookie.setPath(StringPool.SLASH);

        httpServletResponse.addCookie(rememberMeCookie);
    }

    private KeyValuePair decryptUserId(
            Company company, String name, String password)
            throws PortalException {

        try {
            logger.info("decryptUserId name=" + name);
            name = EncryptorUtil.decrypt(company.getKeyObj(), name);
        }
        catch (EncryptorException encryptorException) {
            throw new SystemException(encryptorException);
        }

        try {
            logger.info("decryptUserId pass=" + password);
            password = EncryptorUtil.decrypt(company.getKeyObj(), password);
        }
        catch (EncryptorException encryptorException) {
            throw new SystemException(encryptorException);
        }

        long userId = GetterUtil.getLong(name);

        User user = UserLocalServiceUtil.getUser(userId);

        String userPassword = user.getPassword();

        if (userPassword.equals(password)) {
            if (isPasswordExpired(user)) {
                user.setPasswordReset(true);

                UserLocalServiceUtil.updateUser(user);
            }

            return new KeyValuePair(name, password);
        }

        throw new PrincipalException.MustBeAuthenticated(userId);
    }

    private String[] checkShibbolethSession(
                HttpServletRequest request, HttpServletResponse response) throws AutoLoginException {

        try {
            String authType = PrefsPropsUtil.getString(PropsKeys.COMPANY_SECURITY_AUTH_TYPE, CompanyConstants.AUTH_TYPE_SN);
            long companyId = PortalUtil.getCompanyId(request);
            User user = null;

            if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
                String login = request.getHeader(SHIBBOLETH_NAME_ID);
                logger.debug("AuthType is SN : login=" + login);
                if (Validator.isNull(login)) {
                    return null;
                }
                logger.debug("Trying to find user with screen name: " + login);
                user = UserLocalServiceUtil.getUserByScreenName(companyId, login);

            } else if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {

                String emailAddress = request.getHeader(SHIBBOLETH_EMAIL);
                logger.debug("AuthType is Email : mail=" + emailAddress);
                if (Validator.isNull(emailAddress)) {
                    return null;
                }
                logger.debug("Trying to find user with email: " + emailAddress);
                user = UserLocalServiceUtil.getUserByEmailAddress(companyId, emailAddress);
            }

            if (user != null) {
                if (!user.isActive()) {
                    logger.info("User authenticated by Shibboleth but is inactive : " + user.getScreenName() + " (" + user.getEmailAddress() + ")");
                    return null;
                }
                logger.info("User authenticated with Shibboleth : " + user.getScreenName() + " (" + user.getEmailAddress() + "), uri=" + request.getRequestURI());
                String[] credentials = new String[3];
                credentials[0] = String.valueOf(user.getUserId());
                credentials[1] = user.getPassword();
                credentials[2] = String.valueOf(user.isPasswordEncrypted());

                boolean isMobileApp = (CookieKeys.getCookie(request, "isMobileApp") != null && CookieKeys.getCookie(request, "isMobileApp").equals("true"));
                setSessionCookies(request, response, user, user.getScreenName(), user.getPassword(), isMobileApp);
                return credentials;
            }

        } catch (NoSuchUserException e) {
            logger.error("User not found in DB");
        } catch (Exception e) {
            logger.error("Error occurred while login", e);
            throw new AutoLoginException(e);
        }

        logger.debug("ShibAutoLogin returns null for uri=" + request.getRequestURI());
        return null;
    }

    public static final String SHIBBOLETH_NAME_ID = "NameID";
    public static final String SHIBBOLETH_EMAIL = "mail";

    private String[] checkMobileAppSession (
                HttpServletRequest request,
                HttpServletResponse response)
            throws AutoLoginException {

        String[] credentials;

        try {
            String url = request.getHeader("Referer");
            if (url == null) {
                url = request.getRequestURI();
            }

            String mobileToken = getTokenFromUrl(url);
            long userId = getUserIdFromUrl(url);

            String service = ParamUtil.getString(request, "service", "");

            if (!mobileToken.isEmpty()) {
                logger.info("MobileApplicationAutoLogin : mobileToken=" + mobileToken + ", userId=" + userId + ", service=" + service);
                UserMobileToken userMobileToken = UserMobileTokenLocalServiceUtil.getTokenUser(mobileToken);

                if (userMobileToken != null) {
                    User user = UserLocalServiceUtil.getUserById(userMobileToken.getUserId());
                    if (!user.isActive()) {
                        logger.info("User authenticated by mobileApp token but is inactive : " + user.getScreenName() + " (" + user.getEmailAddress() + ")");
                        return null;
                    }

                    credentials = new String[3];
                    credentials[0] = String.valueOf(user.getUserId());
                    credentials[1] = user.getPassword();
                    credentials[2] = Boolean.TRUE.toString();

                    setSessionCookies(request, response, user, user.getScreenName(), user.getPassword(), true);
                    logger.info("User " + user.getFullName() + " (" + user.getUserId() + ") authenticated on mobile application.");
                    logger.debug("================ User " + user.getFullName() + " (" + user.getUserId() + ") logs in =======================");

                    return credentials;
                } else {
                    logger.debug("Mobile token not found in database");
                    return null;
                }

            } else {
                logger.debug("No mobile token is empty or null");
                return null;
            }

        }
        catch (NoSuchUserException nsue) {
            throw new AutoLoginException(nsue);
        }
        catch (Exception e) {
            logger.error("Error in MobileApplicationAutoLogin", e);
            throw new AutoLoginException(e);
        }
    }

    private String getTokenFromUrl(String url) {
        try {
            if (url != null) {
                //logger.info("url = " + url);

                String mobileTokenStr = "mobile_token=";
                if (url.contains(mobileTokenStr)) {
                    String mobileToken = url.substring(url.indexOf(mobileTokenStr) + mobileTokenStr.length());
                    int maxIndex = mobileToken.length();
                    int andIndex = mobileToken.indexOf("&");
                    int percentIndex = mobileToken.indexOf("%");

                    if (andIndex != -1) {
                        maxIndex = andIndex;
                    }
                    if (percentIndex != -1 && percentIndex < maxIndex) {
                        maxIndex = percentIndex;
                    }
                    mobileToken = mobileToken.substring(0, maxIndex);

                    return mobileToken;
                }
            }
        } catch (Exception e) {
            logger.error("Error while extracting mobileToken from referer url", e);
        }
        return "";
    }

    private long getUserIdFromUrl(String url) {

        try {
            String userIdStr = "user_id=";
            if (url != null && url.contains(userIdStr)) {
                String userIdValue = url.substring(url.indexOf(userIdStr) + userIdStr.length());

                if (userIdValue.contains("&")) {
                    userIdValue = userIdValue.substring(0, userIdValue.indexOf("&"));
                }

                return Long.parseLong(userIdValue);
            }
        } catch (Exception e) {
            logger.error("Error while extracting userId from referer url", e);
        }
        return 0;
    }
}