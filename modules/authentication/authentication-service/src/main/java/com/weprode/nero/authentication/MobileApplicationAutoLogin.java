package com.weprode.nero.authentication;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.encryptor.EncryptorUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auto.login.AutoLogin;
import com.liferay.portal.kernel.security.auto.login.AutoLoginException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.CookieKeys;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;
import com.weprode.nero.mobile.service.UserMobileTokenLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component(immediate = true)
public class MobileApplicationAutoLogin implements AutoLogin {

    private static final Log logger = LogFactoryUtil.getLog(MobileApplicationAutoLogin.class);

    public String[] login (
            HttpServletRequest request, HttpServletResponse response) throws AutoLoginException{

        HttpSession session = request.getSession();
        String[] credentials = null;

        try {
            long userId = ParamUtil.getLong(request, "user_id", 0);
            boolean mobileApp = ParamUtil.getBoolean(request, "mobile_app", false);
            String mobileToken = ParamUtil.getString(request, "mobile_token", "");
            String service = ParamUtil.getString(request, "service", "");
            logger.info("MobileApplicationAutoLogin : userId="+userId+", mobileToken="+mobileToken + ", mobile_app=" + mobileApp + ", service="+service);

            if (!mobileToken.isEmpty() && userId > 0) {
                if (UserMobileTokenLocalServiceUtil.hasUserMobileToken(userId, mobileToken)) {
                    User user = UserLocalServiceUtil.getUserById(userId);
                    logger.info("Logged user " + user.getFullName() + " with mobile token !");

                    credentials = new String[3];
                    credentials[0] = String.valueOf(user.getUserId());
                    credentials[1] = user.getPassword();
                    credentials[2] = Boolean.TRUE.toString();

                    //setCookies(request, response, user.getUserId(), PortalUtil.getCompany(request),
                    //        String.valueOf(user.getUserId()), user.getPassword());
                    logger.info("User  " + user.getFullName() + "(" + user.getUserId() + ") authenticated on mobile application.");

//                    String redirect = "/tableau-de-bord";
//                    request.setAttribute(AutoLogin.AUTO_LOGIN_REDIRECT, redirect);

                    return credentials;
                } else {
                    logger.error("Unknown token " + mobileToken + " for userId " + userId + " when logging on mobile application.");
                    Object noSuchUserException = session.getAttribute(WebKeys.CAS_NO_SUCH_USER_EXCEPTION);

                    if (noSuchUserException == null) {
                        return credentials;
                    }

                    session.removeAttribute(WebKeys.CAS_NO_SUCH_USER_EXCEPTION);
                    session.setAttribute(WebKeys.CAS_FORCE_LOGOUT, Boolean.TRUE);

                    String redirect = "/login";
                    request.setAttribute(AutoLogin.AUTO_LOGIN_REDIRECT, redirect);

                    return credentials;
                }
            } else {
                return credentials;
            }

            // Redirect
//            String redirect = "/login"; //?mobile_app=" + mobileApp + "&mobile_token=" + mobileToken;
//            if (!service.isEmpty()) {
//                redirect += "&service=" + URLEncoder.encode(service, StandardCharsets.UTF_8);
//            }
//            logger.info("Redirecting to " + redirect);
//            response.sendRedirect(redirect);

//            return credentials;

        }
        catch (NoSuchUserException nsue) {
            session.removeAttribute(WebKeys.CAS_LOGIN);
            session.setAttribute(WebKeys.CAS_NO_SUCH_USER_EXCEPTION, Boolean.TRUE);
            throw new AutoLoginException(nsue);
        }
        catch (Exception e) {
            logger.error(e, e);
            throw new AutoLoginException(e);
        }
    }

    // Set user session cookies
    public void setCookies (HttpServletRequest request, HttpServletResponse response, long userId,
                                   Company company, String login, String password) throws Exception {
        // Set cookies
        logger.info("LoginUtil : set cookies, userId = " + userId + ", companyId = " + company.getCompanyId() + ", login = " +login +
                ", password = " + password);
        HttpSession session = request.getSession();

        String domain = CookieKeys.getDomain(request);

        User user = UserLocalServiceUtil.getUserById(userId);

        String userIdString = String.valueOf(userId);

        session.setAttribute("j_username", userIdString);
        session.setAttribute("j_password", user.getPassword());
        session.setAttribute("j_remoteuser", userIdString);

        if (PropsValues.SESSION_STORE_PASSWORD) {
            session.setAttribute(WebKeys.USER_PASSWORD, password);
        }

        Cookie companyIdCookie = new Cookie(
                CookieKeys.COMPANY_ID, String.valueOf(company.getCompanyId()));

        if (Validator.isNotNull(domain)) {
            companyIdCookie.setDomain(domain);
        }

        companyIdCookie.setPath(StringPool.SLASH);

        Cookie idCookie = new Cookie(
                CookieKeys.ID,
                EncryptorUtil.encrypt(company.getKeyObj(), userIdString));

        if (Validator.isNotNull(domain)) {
            idCookie.setDomain(domain);
        }

        idCookie.setPath(StringPool.SLASH);

        Cookie passwordCookie = new Cookie(
                CookieKeys.PASSWORD,
                EncryptorUtil.encrypt(company.getKeyObj(), password));

        if (Validator.isNotNull(domain)) {
            passwordCookie.setDomain(domain);
        }

        passwordCookie.setPath(StringPool.SLASH);

        Cookie rememberMeCookie = new Cookie(
                CookieKeys.REMEMBER_ME, Boolean.TRUE.toString());

        if (Validator.isNotNull(domain)) {
            rememberMeCookie.setDomain(domain);
        }

        rememberMeCookie.setPath(StringPool.SLASH);

        int loginMaxAge = PropsValues.COMPANY_SECURITY_AUTO_LOGIN_MAX_AGE;

//        String userUUID = userIdString.concat(StringPool.PERIOD).concat(
//                String.valueOf(System.nanoTime()));
//
//        Cookie userUUIDCookie = new Cookie(
//                CookieKeys.USER_UUID,
//                Encryptor.encrypt(company.getKeyObj(), userUUID));
//
//        userUUIDCookie.setPath(StringPool.SLASH);
//
//        session.setAttribute(WebKeys.USER_UUID, userUUID);

        companyIdCookie.setMaxAge(-1);
        idCookie.setMaxAge(-1);
        passwordCookie.setMaxAge(-1);
        rememberMeCookie.setMaxAge(0);
        //userUUIDCookie.setMaxAge(-1);

        Cookie loginCookie = new Cookie(CookieKeys.LOGIN, login);

        if (Validator.isNotNull(domain)) {
            loginCookie.setDomain(domain);
        }

        loginCookie.setMaxAge(loginMaxAge);
        loginCookie.setPath(StringPool.SLASH);

//        Cookie screenNameCookie = new Cookie(
//                CookieKeys.SCREEN_NAME,
//                EncryptorUtil.encrypt(company.getKeyObj(), user.getScreenName()));
//
//        if (Validator.isNotNull(domain)) {
//            screenNameCookie.setDomain(domain);
//        }
//
//        screenNameCookie.setMaxAge(loginMaxAge);
//        screenNameCookie.setPath(StringPool.SLASH);

        boolean secure = request.isSecure();

        if (secure) {
            Boolean httpsInitial = (Boolean)session.getAttribute(
                    WebKeys.HTTPS_INITIAL);

            if ((httpsInitial == null) || !httpsInitial.booleanValue()) {
                secure = false;
            }
        }

        CookieKeys.addCookie(request, response, companyIdCookie, secure);
        CookieKeys.addCookie(request, response, idCookie, secure);
        //CookieKeys.addCookie(request, response, userUUIDCookie, secure);
        CookieKeys.addCookie(request, response, loginCookie, secure);
        CookieKeys.addCookie(request, response, passwordCookie, secure);
        CookieKeys.addCookie(request, response, rememberMeCookie, secure);
        //CookieKeys.addCookie(request, response, screenNameCookie, secure);

        //AuthenticatedUserUUIDStoreUtil.register(userUUID);
    }

}