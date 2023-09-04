package com.weprode.nero.authentication;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.cookies.CookiesManagerUtil;
import com.liferay.portal.kernel.cookies.constants.CookiesConstants;
import com.liferay.portal.kernel.encryptor.EncryptorUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
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
import com.weprode.nero.mobile.model.UserMobileToken;
import com.weprode.nero.mobile.service.UserMobileTokenLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Properties;

@Component(
        immediate = true,
        service = AuthVerifier.class,
        property = {
                "auth.verifier.CustomAuthVerifier.urls.includes=*"
        }
)
public class CustomAuthVerifier implements AuthVerifier {

    private static final Log logger = LogFactoryUtil.getLog(CustomAuthVerifier.class);

    @Override
    public String getAuthType() {
        return HttpServletRequest.FORM_AUTH;
    }

    @Override
    public AuthVerifierResult verify(AccessControlContext accessControlContext, Properties properties) {
        logger.info("CustomAuthVerifier for URI " + accessControlContext.getRequest().getRequestURI());

        try {
            AuthVerifierResult authVerifierResult = new AuthVerifierResult();
            long userId;

            AuthVerifierResult portalAuthVerifierResult = checkPortalSession(accessControlContext.getRequest(), accessControlContext.getResponse(), properties);

            if (portalAuthVerifierResult != null && portalAuthVerifierResult.getState() == AuthVerifierResult.State.SUCCESS) {
                return portalAuthVerifierResult;
            }

//            String[] credentials = checkRememberMeSession(accessControlContext.getRequest(), accessControlContext.getResponse());
//            if (credentials != null) {
//                userId = Long.parseLong(credentials[0]);
//                User user = UserLocalServiceUtil.getUser(userId);
//                logger.info("User has been authenticated with remember me login " + user.getFullName());
//                authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
//                authVerifierResult.setPasswordBasedAuthentication(true);
//                authVerifierResult.setUserId(userId);
//                authVerifierResult.setPassword(credentials[1]);
//
//                return authVerifierResult;
//            }

            String[] credentials = checkShibbolethSession(accessControlContext.getRequest(), accessControlContext.getResponse());
            if (credentials != null) {
                userId = Long.parseLong(credentials[0]);
                User user = UserLocalServiceUtil.getUser(userId);
                logger.debug("Connected user is " + user.getFullName());
                authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
                authVerifierResult.setPasswordBasedAuthentication(true);
                authVerifierResult.setUserId(userId);
                authVerifierResult.setPassword(credentials[1]);

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

                return authVerifierResult;
            }

            return authVerifierResult;
        } catch (Exception e) {
            logger.error("Exception in AuthVerifier", e);
        }

        return null;
    }

    private AuthVerifierResult checkPortalSession(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Properties properties) {
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
                logger.info("PortalSession: authenticated user is " + user.getScreenName());
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
                                            HttpServletResponse httpServletResponse)
            throws Exception {

        String autoUserId = CookiesManagerUtil.getCookieValue(
                CookiesConstants.NAME_ID, httpServletRequest, false);
        String autoPassword = CookiesManagerUtil.getCookieValue(
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

        String[] credentials = null;

        if (Validator.isNotNull(autoUserId) &&
                Validator.isNotNull(autoPassword) &&
                Validator.isNotNull(rememberMe)) {
            logger.info("RememberMe userId processed : " + new String(UnicodeFormatter.hexToBytes(autoUserId)));

            Company company = CompanyLocalServiceUtil.getCompany(PortalUtil.getDefaultCompanyId());
            // Company company = PortalUtil.getCompany(httpServletRequest);

            if (company.isAutoLogin()) {
                KeyValuePair kvp = UserLocalServiceUtil.decryptUserId(
                        company.getCompanyId(),
                        new String(UnicodeFormatter.hexToBytes(autoUserId)),
                        new String(UnicodeFormatter.hexToBytes(autoPassword)));

                credentials = new String[3];

                credentials[0] = kvp.getKey();
                credentials[1] = kvp.getValue();
                credentials[2] = Boolean.FALSE.toString();

                User user = UserLocalServiceUtil.getUser(Long.parseLong(credentials[0]));
                setSessionCookies(httpServletRequest, httpServletResponse, user, user.getScreenName(), kvp.getValue(), true);
            }
        }

        if (credentials != null) {
            Company company = CompanyLocalServiceUtil.getCompany(PortalUtil.getDefaultCompanyId());
            // Company company = PortalUtil.getCompany(httpServletRequest);

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
    }

    public void setSessionCookies (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                   User user, String login, String password, boolean rememberMe) throws Exception {
        logger.debug("Set session cookie for " + login + " - rememberMe=" + rememberMe);

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

        if (PropsValues.PORTAL_JAAS_PLAIN_PASSWORD) {
            httpSession.setAttribute("j_password", password);
        }
        else {
            httpSession.setAttribute("j_password", user.getPassword());
        }

        httpSession.setAttribute("j_remoteuser", userIdString);

        if (PropsValues.SESSION_STORE_PASSWORD) {
            httpSession.setAttribute(WebKeys.USER_PASSWORD, password);
        }

        Cookie companyIdCookie = new Cookie(
                CookiesConstants.NAME_COMPANY_ID,
                String.valueOf(company.getCompanyId()));

        if (domain != null) {
            companyIdCookie.setDomain(domain);
        }

        companyIdCookie.setPath(StringPool.SLASH);

        Cookie idCookie = new Cookie(
                CookiesConstants.NAME_ID,
                UnicodeFormatter.bytesToHex(
                    EncryptorUtil.encrypt(company.getKeyObj(), userIdString).getBytes()));

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
//        CookiesManagerUtil.addCookie(
//                CookiesConstants.CONSENT_TYPE_NECESSARY, companyIdCookie,
//                httpServletRequest, httpServletResponse);
        httpServletResponse.addCookie(idCookie);
//        CookiesManagerUtil.addCookie(
//                CookiesConstants.CONSENT_TYPE_NECESSARY, idCookie,
//                httpServletRequest, httpServletResponse);

        if (rememberMe) {
            Cookie loginCookie = new Cookie(CookiesConstants.NAME_LOGIN, UnicodeFormatter.bytesToHex(login.getBytes()));

            if (domain != null) {
                loginCookie.setDomain(domain);
            }

            loginCookie.setMaxAge(loginMaxAge);
            loginCookie.setPath(StringPool.SLASH);

            httpServletResponse.addCookie(loginCookie);
//            CookiesManagerUtil.addCookie(
//                    CookiesConstants.CONSENT_TYPE_FUNCTIONAL, loginCookie,
//                    httpServletRequest, httpServletResponse);

            Cookie passwordCookie = new Cookie(
                    CookiesConstants.NAME_PASSWORD,
                    UnicodeFormatter.bytesToHex(
                        EncryptorUtil.encrypt(company.getKeyObj(), password).getBytes()));

            if (domain != null) {
                passwordCookie.setDomain(domain);
            }

            passwordCookie.setMaxAge(loginMaxAge);
            passwordCookie.setPath(StringPool.SLASH);

            httpServletResponse.addCookie(passwordCookie);
//            CookiesManagerUtil.addCookie(
//                    CookiesConstants.CONSENT_TYPE_FUNCTIONAL, passwordCookie,
//                    httpServletRequest, httpServletResponse);

            Cookie rememberMeCookie = new Cookie(
                    CookiesConstants.NAME_REMEMBER_ME, Boolean.TRUE.toString());

            if (domain != null) {
                rememberMeCookie.setDomain(domain);
            }

            rememberMeCookie.setMaxAge(loginMaxAge);
            rememberMeCookie.setPath(StringPool.SLASH);

            httpServletResponse.addCookie(rememberMeCookie);
//            CookiesManagerUtil.addCookie(
//                    CookiesConstants.CONSENT_TYPE_FUNCTIONAL, rememberMeCookie,
//                    httpServletRequest, httpServletResponse);
        }
    }

    private String[] checkShibbolethSession(
                HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {
            String authType = PrefsPropsUtil.getString(PropsKeys.COMPANY_SECURITY_AUTH_TYPE, CompanyConstants.AUTH_TYPE_EA);
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
                logger.info("User authenticated with Shibboleth : " + user.getScreenName() + " (" + user.getEmailAddress() + "), uri=" + request.getRequestURI());
                //logger.info("================ User " + user.getFullName() + " (" + user.getUserId() + ") logs in =======================");
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

        logger.info("ShibAutoLogin returns null for uri=" + request.getRequestURI());
        return null;
    }

    public static final String SHIBBOLETH_NAME_ID = "NameID";
    public static final String SHIBBOLETH_EMAIL = "mail";

    private String[] checkMobileAppSession (
                HttpServletRequest request,
                HttpServletResponse response)
            throws Exception{

        String[] credentials;

        try {
            String url = request.getHeader("Referer");
            if (url == null) {
                url = request.getRequestURI();
            }
            String mobileToken = getTokenFromUrl(url);
            long userId = getUserIdFromUrl(url);

            // If no mobile_token found in url, look into cookies
            if (mobileToken.isEmpty()) {
                mobileToken = CookieKeys.getCookie(request, "mobileToken");
                if (mobileToken != null && !mobileToken.isEmpty()) {
                    logger.info("MobileApplicationAutoLogin : mobileToken in cookies is " + mobileToken);
                }
            }

            String service = ParamUtil.getString(request, "service", "");
            if (mobileToken != null && !mobileToken.isEmpty()) {
                logger.info("MobileApplicationAutoLogin : mobileToken=" + mobileToken + ", userId=" + userId + ", service=" + service);
            }

            if (mobileToken != null && !mobileToken.isEmpty()) {
                UserMobileToken userMobileToken = UserMobileTokenLocalServiceUtil.getTokenUser(mobileToken);
//                if (userMobileToken == null && userId != 0) {
//                    userMobileToken = UserMobileTokenLocalServiceUtil.addMobileToken(userId, mobileToken);
//                    logger.info("Created userToken");
//                }
                if (userMobileToken != null) {
                    User user = UserLocalServiceUtil.getUserById(userMobileToken.getUserId());

                    credentials = new String[3];
                    credentials[0] = String.valueOf(user.getUserId());
                    credentials[1] = user.getPassword();
                    credentials[2] = Boolean.TRUE.toString();

                    setSessionCookies(request, response, user, user.getScreenName(), user.getPassword(), true);
                    logger.info("User " + user.getFullName() + " (" + user.getUserId() + ") authenticated on mobile application.");
                    logger.debug("================ User " + user.getFullName() + " (" + user.getUserId() + ") logs in =======================");

                    return credentials;
                } else {
                    return null;
                }

            } else {
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
                logger.info("url = " + url);

                String mobileTokenStr = "mobile_token=";
                if (url.contains(mobileTokenStr)) {
                    String mobileToken = url.substring(url.indexOf(mobileTokenStr) + mobileTokenStr.length());
                    //logger.info("mobile_token long = " + mobileToken);
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
                    //logger.info("mobile_token short = " + mobileToken);
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
                //logger.info("user_id = " + userIdValue);
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