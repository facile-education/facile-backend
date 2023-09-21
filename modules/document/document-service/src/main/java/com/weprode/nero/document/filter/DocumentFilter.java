package com.weprode.nero.document.filter;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.nero.document.service.PermissionUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component(
        immediate = true,
        property = {
                "servlet-context-name=",
                "servlet-filter-name=Document Filter",
                "url-pattern=/documents/*"
        },
        service = Filter.class
)
public class DocumentFilter extends BaseFilter {

    private static final Log logger = LogFactoryUtil.getLog(DocumentFilter.class);

    @Override
    protected Log getLog() {
        return logger;
    }

    @Override
    protected void processFilter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws Exception {

        long userId = PortalUtil.getUserId(httpServletRequest);

        if (userId != 0 && userId != UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
            User user = UserLocalServiceUtil.getUser(userId);

            // URI split : /documents/{repositoryId}/{folderId}/{fileTitle}/...
            String[] splitURI = httpServletRequest.getRequestURI().split("/documents/");
            String[] splitPath = splitURI[1].split("/");
            long groupId = Long.parseLong(splitPath[0]);
            long folderId = Long.parseLong(splitPath[1]);
            String title = splitPath[2];

            // Permission checker initialization
            PermissionChecker permissionChecker;
            permissionChecker = PermissionCheckerFactoryUtil.create(user);
            PermissionThreadLocal.setPermissionChecker(permissionChecker);

            if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(userId, folderId)) {
                logger.info("Denying " + user.getFullName() + " to access (folder access) document URI " + httpServletRequest.getRequestURI());
                // Should be HttpServletResponse.SC_FORBIDDEN but liferay's sending 403 when session is ended so we need to differentiate it
                PortalUtil.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, new PrincipalException("Access denied."), httpServletRequest, httpServletResponse);
            } else if (!PermissionUtilsLocalServiceUtil.hasUserFolderPermission(userId, DLAppServiceUtil.getFolder(folderId), ActionKeys.VIEW)
                    || !PermissionUtilsLocalServiceUtil.hasUserFilePermission(userId, DLAppServiceUtil.getFileEntry(groupId, folderId, title), ActionKeys.VIEW)) {
                logger.info("Denying " + user.getFullName() + " to access document (view permission) with URI " + httpServletRequest.getRequestURI());
                PortalUtil.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, new PrincipalException("Access denied."), httpServletRequest, httpServletResponse);
            }
        }

        super.processFilter(httpServletRequest, httpServletResponse, filterChain);
    }
}
