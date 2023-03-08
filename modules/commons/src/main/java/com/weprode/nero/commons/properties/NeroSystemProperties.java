package com.weprode.nero.commons.properties;

public class NeroSystemProperties {

	private NeroSystemProperties() {
		throw new IllegalStateException("Constants class");
	}

	public static final String ROOT_ORGANIZATION_NAME = "root.organization.name";
	public static final String PORTAL_URL = "portal.url";

	public static final String GVE_VOLEES = "gve.volees";

	public static final String MAIL_NO_REPLY = "mail.no.reply";
	public static final String MAIL_NO_REPLY_USER_ID = "mail.no.reply.user.id";
	public static final String DEFAUT_MAIL_SUFFIX = "default.mail.suffix";
}
