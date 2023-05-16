package com.weprode.nero.commons.properties;

public class NeroSystemProperties {

	private NeroSystemProperties() {
		throw new IllegalStateException("Constants class");
	}

	public static final String ROOT_ORGANIZATION_NAME = "ent.orgRoot.name";
	public static final String PORTAL_URL = "absolute.url";
	public static final String TERMS_OF_USE_FILE = "ent.chart";
	public static final String NOTIFICATIONS_ENABLED = "ent.notifications.enable";

	public static final String MAX_UPLOAD_SIZE = "data.limit.dl.storage.max.size";
	public static final String XSS_IFRAME_WHITELIST = "xss.iframe.whitelist";

	public static final String GVE_VOLEES = "gve.volees";

	public static final String MAIL_NO_REPLY = "ent.mail.noreply"; // "mail.noreply.address" ?
	public static final String MAIL_NO_REPLY_USER_ID = "ent.mail.noreply.userId";
	public static final String MAIL_FORWARD_ENABLED = "mail.forward.enabled";
	public static final String MAIL_FORWARD_ADDCONTENT = "mail.forward.addcontent";
	public static final String DEFAUT_MAIL_SUFFIX = "ent.mail.academic.suffix";
	public static final String CONFIRMATION_SENDER_ID = "ent.mailSender.use.forward";
	public static final String MESSAGING_URL = "portlet.url.messaging";
	public static final String MOBILE_NOTIFICATION_ENABLED = "ent.mobile.notification.enable";

	// Assistance
	public static final String ENT_CONFIRMATION_SENDER_ID = "ent.confirmation.sender.id";
	public static final String ENT_INCIDENTS_USERS_NOTIFICATION = "ent.incidents.users.notication";
	public static final String SUPPORT_LOCAL_ADMINS_ENABLE = "support.local.admins.enable";
	public static final String SUPPORT_DIRECTION_MEMBERS_ENABLE = "support.direction.members.enable";


	public static final String PARENT_SYNCHRO_FOLDER = "synchro.drop.folder";
	public static final String SCHEDULE_SYNCHRO_FOLDER = "synchro.schedule.drop.folder";

	public static final String MATOMO_ENABLED = "piwik.enabled";
	public static final String MATOMO_API_URL = "piwik.api_url";
	public static final String MATOMO_AUTH_TOKEN = "piwik.token_auth";
	public static final String MATOMO_SITE_ID = "piwik.siteId";

}
