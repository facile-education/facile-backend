package com.weprode.nero.commons.properties;

public class NeroSystemProperties {

	private NeroSystemProperties() {
		throw new IllegalStateException("Constants class");
	}

	// General
	public static final String PORTAL_URL = "absolute.url";
	public static final String LIFERAY_CONTEXT = "liferay.context";
	public static final String ROOT_ORGANIZATION_NAME = "orgRoot.name";
	public static final String TERMS_OF_USE_FILE = "chart.file.path";

	// Documents
	public static final String MAX_UPLOAD_SIZE = "documents.max.upload.size";
	public static final String XSS_IFRAME_WHITELIST = "xss.iframe.whitelist";

	// Messaging
	public static final String MESSAGING_NOREPLY_USER_ID = "messaging.noreply.userId";

	// Mail
	public static final String MAIL_NO_REPLY_ADDRESS = "mail.noreply.address";
	public static final String MAIL_FORWARD_ENABLED = "mail.forward.enabled";
	public static final String MAIL_FORWARD_ADDCONTENT = "mail.forward.addcontent";
	public static final String MAIL_DEFAULT_SUFFIX = "mail.default.suffix";

	// Mobile
	public static final String MOBILE_NOTIFICATIONS_ENABLED = "mobile.notifications.enabled";
	public static final String MOBILE_API_URL = "mobile.api.url";
	public static final String MOBILE_API_KEY = "mobile.api.key";

	// Support
	public static final String SUPPORT_GLOBAL_RECIPIENTS = "support.global.recipients";
	public static final String SUPPORT_LOCAL_ADMINS_ENABLE = "support.local.admins.enable";
	public static final String SUPPORT_DIRECTION_MEMBERS_ENABLE = "support.direction.members.enable";

	// Synchronizations
	public static final String SYNCHRO_PARENT_DROP_FOLDER = "synchro.parent.drop.folder";
	public static final String SYNCHRO_SCHEDULE_DROP_FOLDER = "synchro.schedule.drop.folder";
	public static final String SYNCHRO_SCHOOLS = "synchro.schools";
	public static final String SYNCHRO_VOLEES = "synchro.volees";
	public static final String SYNCHRO_LDAP_BASE_PROVIDER_URL = "ldap.base.provider.url";
	public static final String SYNCHRO_LDAP_SECURITY_PRINCIPAL = "ldap.security.principal";
	public static final String SYNCHRO_LDAP_SECURITY_CREDENTIALS = "ldap.security.credentials";
	public static final String SYNCHRO_LDAP_BASE_DN_GROUPS = "ldap.base.dn.groups";

	// Matomo
	public static final String MATOMO_ENABLED = "matomo.enabled";
	public static final String MATOMO_API_URL = "matomo.api_url";
	public static final String MATOMO_AUTH_TOKEN = "matomo.token_auth";
	public static final String MATOMO_SITE_ID = "matomo.siteId";

}
