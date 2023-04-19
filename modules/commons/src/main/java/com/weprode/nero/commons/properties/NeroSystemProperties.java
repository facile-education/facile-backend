package com.weprode.nero.commons.properties;

public class NeroSystemProperties {

	private NeroSystemProperties() {
		throw new IllegalStateException("Constants class");
	}

	public static final String ROOT_ORGANIZATION_NAME = "ent.orgRoot.name";
	public static final String PORTAL_URL = "absolute.url";
	public static final String NOTIFICATIONS_ENABLED = "ent.notifications.enable";

	public static final String MAX_UPLOAD_SIZE = "com.liferay.portal.upload.UploadServletRequestImpl.max.size";
	public static final String XSS_IFRAME_WHITELIST = "xss.iframe.whitelist";

	public static final String GVE_VOLEES = "gve.volees";

	public static final String MAIL_NO_REPLY = "ent.mail.noreply";
	public static final String MAIL_NO_REPLY_USER_ID = "ent.mail.noreply.userId";
	public static final String DEFAUT_MAIL_SUFFIX = "ent.mail.academic.suffix";
	public static final String CONFIRMATION_SENDER_ID = "ent.mailSender.use.forward";

	public static final String PARENT_SYNCHRO_FOLDER = "synchro.drop.folder";
	public static final String SCHEDULE_SYNCHRO_FOLDER = "synchro.schedule.drop.folder";
}
