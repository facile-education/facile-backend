package com.weprode.nero.document.utils;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.document.library.kernel.service.persistence.DLFileEntryUtil;
import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.ParamUtil;
import com.weprode.nero.document.model.LoolToken;
import com.weprode.nero.document.service.LoolTokenLocalServiceUtil;
import com.weprode.nero.statistic.service.LoolStatLocalServiceUtil;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Component(
    immediate=true,
        property={"path=/common/wopi/files"},
        service= StrutsAction.class
)
public class WopiInfoAction implements StrutsAction {

    final Log logger = LogFactoryUtil.getLog(WopiInfoAction.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // First check access_token that has previously been created in LoolToken table
        // Return code must be 401 if access_token is unknown on ENT side
        String accessToken = ParamUtil.getString(request, "access_token");

        LoolToken loolToken = null;
        try {
            loolToken = LoolTokenLocalServiceUtil.getLoolToken(accessToken);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
        if (loolToken == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
        long userId = loolToken.getUserId();
        if (userId == 0L) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }

        String requestUri = request.getRequestURI();
        String[] uriSplit = requestUri.split("/");
        String lastUriParam = uriSplit[uriSplit.length - 1];
        String wopiParam;
        if (lastUriParam.equals("contents")) {
            // Wopi's second call is asking for file content
            // Called url is /common/wopi/files/<id>+<version>+readOnly/contents
            wopiParam = uriSplit[uriSplit.length - 2];
            return getWopiContent(wopiParam, userId, request, response);
        } else {
            // Wopi's first call is asking for file informations
            // Called url is /common/wopi/files/<id>+<version>+readOnly
            wopiParam = uriSplit[uriSplit.length - 1];
            return getWopiInfos(wopiParam, userId, response);
        }
    }

    private String getWopiInfos(String wopiParam, long userId, HttpServletResponse response) throws PortalException, IOException {

        logger.info("Fetch Wopi infos for userId " + userId + " and param = " + wopiParam);

        User user = UserLocalServiceUtil.getUser(userId);
        JSONObject fileInfo = new JSONObject();
        fileInfo.put("OwnerId", user.getUserId());
        fileInfo.put("UserId", user.getUserId());
        fileInfo.put("UserInfo", user.getScreenName());
        fileInfo.put("UserFriendlyName", user.getFullName());

        // This parameter can have different forms
        // fileEntryId+version+isReadOnly
        // news+blogsEntryInfosId+fileName for news documents

        if (wopiParam.startsWith("news")) {

            // wopiParam value example is news+123456+fleches.odp
            String[] newsParamTab = wopiParam.split("\\+");
            if (newsParamTab.length > 2) {
                //Long blogEntryInfosId = Long.parseLong(newsParamTab[1]);
                String fileName = newsParamTab[2];
                fileInfo.put("BaseFileName", fileName);
                fileInfo.put("Version", "");
                fileInfo.put("UserCanWrite", false);
                fileInfo.put("SupportsUpdate", false);
            }

        } else {

            // Documents or messaging
            // wopiParam value example is 123456+1.0+true
            String[] otherParamTab = wopiParam.split("\\+");
            if (otherParamTab.length > 2) {
                try {
                    long fileEntryId = Long.parseLong(otherParamTab[0]);
                    String version = otherParamTab[1];
                    boolean isReadOnly = true;
                    if (otherParamTab[2] != null && !otherParamTab[2].equals("")) {
                        isReadOnly = Boolean.parseBoolean(otherParamTab[2]);
                    }
                    fileInfo.put("UserCanWrite", !isReadOnly);
                    fileInfo.put("SupportsUpdate", !isReadOnly);

                    DLFileVersion lastVersion;
                    try{
                        DLFileEntryUtil.clearCache(DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryId));
                        lastVersion = DLFileVersionLocalServiceUtil.getLatestFileVersion(fileEntryId, false);
                    } catch (Exception e){
                        logger.error( "Error on retrieve last dlFileVersion of file: " + fileEntryId, e);
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        return null;
                    }

                    if(version.isEmpty()){
                        version = lastVersion.getVersion();
                    }

                    DLFileEntry fileEntry;
                    try {
                        fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileEntryId);
                        fileInfo.put("BaseFileName", fileEntry.getTitle());
                        fileInfo.put("OwnerId", fileEntry.getUserName());
                        fileInfo.put("Size", fileEntry.getSize());

                        // Modified date must be UTC and formatted in "2009-06-15T13:45:30.0000000Z"
                        SimpleDateFormat wopiFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");

                        // To UTC TimeZone
                        TimeZone utcTz = TimeZone.getTimeZone("UTC");
                        wopiFormat.setTimeZone(utcTz);

                        Date date = fileEntry.getModifiedDate();

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.setTimeZone(utcTz);

                        fileInfo.put("LastModifiedTime", wopiFormat.format(calendar.getTime()));

                    } catch (Exception e){
                        logger.error( "Error on retrieve dlFileEntry : " + fileEntryId, e);
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        return null;
                    }
                    InputStream is = DLStoreUtil.getFileAsStream(fileEntry.getCompanyId(), fileEntry.getDataRepositoryId(), fileEntry.getName(), version);

                    fileInfo.put("SHA256", getHash256(is));
                    fileInfo.put("Version", version);

                } catch (Exception e){
                    logger.error( "Error on parsing wopi parameter ");
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            }

        }
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(fileInfo.toString());

        return null;
    }

    public String getHash256(InputStream fis) {
        String value = "";
        try {
            byte[] buffer = new byte[1024];
            int numRead;
            MessageDigest complete = MessageDigest.getInstance("SHA-256");
            do {
                numRead = fis.read(buffer);
                if (numRead > 0) {
                    complete.update(buffer, 0, numRead);
                }
            } while (numRead != -1);

            fis.close();
            value = Base64.encode(complete.digest());
        } catch (Exception e) {
            logger.error("Error getting hash", e);
        }
        return value;
    }

    private String getWopiContent(String wopiParam, long userId, HttpServletRequest request, HttpServletResponse response) {
        logger.info("Fetch Wopi content for userId = " + userId + " and param = " + wopiParam);
        try {
            String method = request.getMethod();
            String[] paramTab = wopiParam.split("\\+");
            if (paramTab.length >= 2) {
                long fileEntryId = Long.parseLong(paramTab[0]);
                String version = paramTab[1];
                DLFileEntryUtil.clearCache(DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryId));
                if (version.isEmpty()) {
                    DLFileVersion lastVersion = DLFileVersionLocalServiceUtil.getLatestFileVersion(fileEntryId, false);
                    version = lastVersion.getVersion();
                }

                if (method.equalsIgnoreCase("GET")) {
                    getFile(fileEntryId, version, userId, request, response);
                } else if (method.equalsIgnoreCase("POST")) {
                    saveFile(fileEntryId, version, userId, request);
                }
            }
        } catch (Exception e) {
            logger.error("Error fetching wopi content", e);
        }
        return "";
    }

    protected void getFile(Long fileEntryId, String version, Long userId, HttpServletRequest request, HttpServletResponse response)	{

        InputStream is = null;
        try {
            DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileEntryId);
            is = DLStoreUtil.getFileAsStream(fileEntry.getCompanyId(), fileEntry.getDataRepositoryId(), fileEntry.getName(), version);
            response.setHeader("X-WOPI-ItemVersion", version);
            User user = UserLocalServiceUtil.getUser(userId);
            long size = fileEntry.getSize();
            logger.info("User " + user.getFullName() + " is viewing cart LOOL document with size "+ size);
            LoolStatLocalServiceUtil.addLoolStat(fileEntryId, userId, false, 0);
            ServletResponseUtil.sendFile(request, response, fileEntry.getTitle(), is, (int) size, fileEntry.getMimeType(), "");
        } catch (Exception e) {
            logger.error("Error when opening schoolbag file for fileEntryId="+fileEntryId+ " and version="+version, e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    logger.error("Error closing input stream");
                }
            }
        }
    }

    protected void saveFile(Long fileEntryId, String version, Long userId, HttpServletRequest request) {

        logger.info("Start file saving : " + fileEntryId  + " with version " + version  + " for user : " + userId);

        try {
            // Convert to byte array for easier saving
            InputStream is = request.getInputStream();
            byte[] buff = new byte[8000];
            int bytesRead;
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            while ((bytesRead = is.read(buff)) != -1) {
                bao.write(buff, 0, bytesRead);
            }
            byte[] data = bao.toByteArray();
            FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);

            FileEntry feUpdate = DLAppLocalServiceUtil.updateFileEntry(userId, fileEntry.getFileEntryId(), fileEntry.getTitle(),
                    fileEntry.getMimeType(), fileEntry.getTitle(), fileEntry.getDescription(), "", "", DLVersionNumberIncrease.MINOR,
                    data, null, new Date(), new ServiceContext());

            logger.info("End file saving : " + fileEntryId + " to " + feUpdate.getVersion() + " for user : " + userId);
            LoolStatLocalServiceUtil.addLoolStat(fileEntryId, userId, true, 0);
        } catch (Exception e) {
            logger.error("Error when saving wopi content for fileEntryId="+fileEntryId+ " and version="+version);
        }
    }

}
