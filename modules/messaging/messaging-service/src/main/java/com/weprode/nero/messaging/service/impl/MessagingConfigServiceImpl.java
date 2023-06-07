package com.weprode.nero.messaging.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.JSONProxy;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.messaging.model.MessagingConfig;
import com.weprode.nero.messaging.service.MessagingConfigLocalServiceUtil;
import com.weprode.nero.messaging.service.base.MessagingConfigServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

@Component(
        property = {
                "json.web.service.context.name=messaging",
                "json.web.service.context.path=MessagingConfig"
        },
        service = AopService.class
)
public class MessagingConfigServiceImpl extends MessagingConfigServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(MessagingConfigServiceImpl.class);

    @JSONWebService(value = "get-messaging-configuration", method = "GET")
    public JSONObject getMessagingConfiguration() {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        result.put(JSONConstants.SUCCESS, false);
        
        JSONObject configuration = new JSONObject();
        try {
            MessagingConfig messagingConfig = MessagingConfigLocalServiceUtil.getOrCreateMessagingConfig(user.getUserId());

            // Signature
            JSONObject signature = new JSONObject();
            signature.put(JSONConstants.IS_ACTIVE, messagingConfig.getIsSignatureActive());
            signature.put(JSONConstants.CONTENT, messagingConfig.getSignature());
            configuration.put(JSONConstants.SIGNATURE, signature);

            // Forwarding
            JSONObject forwarding = new JSONObject();
            forwarding.put(JSONConstants.IS_ACTIVE, messagingConfig.getIsForwardActive());
            JSONArray jsonAddresses = new JSONArray();
            String [] addresses = messagingConfig.getForwardMail().split(",");
            int idx = 1;
            for (String address : addresses) {
                if (!address.equals("")) {
                    JSONObject jsonAddress = new JSONObject();
                    jsonAddress.put(JSONConstants.TEXT, address);
                    jsonAddress.put(JSONConstants.ID, idx);
                    jsonAddresses.put(jsonAddress);
                    idx++;
                }
            }
            forwarding.put(JSONConstants.ADDRESSES, jsonAddresses);
            configuration.put(JSONConstants.FORWARD, forwarding);

            // Auto reply
            JSONObject autoResponse = new JSONObject();
            autoResponse.put(JSONConstants.IS_ACTIVE, messagingConfig.getIsAutoReplyActive());
            autoResponse.put(JSONConstants.CONTENT, messagingConfig.getAutoReplyContent());
            configuration.put(JSONConstants.AUTO_REPLY, autoResponse);

            result.put(JSONConstants.CONFIGURATION, configuration);
            result.put(JSONConstants.SUCCESS, true);

        } catch(Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Error when fetching messaging configuration for user " + user.getUserId(), e);
        }

        return result;
    }

    /**
     * Update messaging configuration
     */
    @JSONWebService(value = "update-messaging-configuration", method = "POST")
    public JSONObject updateMessagingConfiguration(String configuration) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            JSONObject jsonConfiguration = new JSONObject(configuration);

            // Signature
            JSONObject jsonSignature = jsonConfiguration.getJSONObject(JSONConstants.SIGNATURE);
            boolean isSignatureActive = JSONConstants.getBoolValue(jsonSignature, JSONConstants.IS_ACTIVE, false);
            String signatureContent = jsonSignature.getString(JSONConstants.CONTENT);

            // Forward
            JSONObject jsonForward = jsonConfiguration.getJSONObject(JSONConstants.FORWARD);
            boolean isForwardActive = JSONConstants.getBoolValue(jsonForward, JSONConstants.IS_ACTIVE, false);
            JSONArray addresses = jsonForward.getJSONArray(JSONConstants.ADDRESSES);
            StringBuilder forwardMails = new StringBuilder();
            for (int i = 0 ; i < addresses.length() ; i++) {
                forwardMails.append(addresses.getJSONObject(i).getString(JSONConstants.TEXT)).append(",");
            }

            // Auto response
            JSONObject jsonAutoResponse = jsonConfiguration.getJSONObject(JSONConstants.AUTO_REPLY);
            boolean isAutoReplyActive = jsonAutoResponse.getBoolean(JSONConstants.IS_ACTIVE);
            String autoReplyContent = jsonAutoResponse.getString(JSONConstants.CONTENT);

            MessagingConfig messagingConfig = MessagingConfigLocalServiceUtil.getMessagingConfig(user.getUserId());
            messagingConfig.setIsForwardActive(isForwardActive);
            messagingConfig.setForwardMail(forwardMails.toString());
            messagingConfig.setIsSignatureActive(isSignatureActive);
            messagingConfig.setSignature(signatureContent);
            messagingConfig.setIsAutoReplyActive(isAutoReplyActive);
            messagingConfig.setAutoReplyContent(autoReplyContent);
            MessagingConfigLocalServiceUtil.updateMessagingConfig(messagingConfig);

            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Cannot create configuration JSON for user " + user.getUserId(), e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}
