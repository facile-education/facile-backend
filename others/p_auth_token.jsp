<%
String allowedOrigin = "https://dev-ent-gve.com";
String allowedReferer = "https://dev-ent-gve.com/nero/";
String origin = request.getHeader("Origin");
String referer = request.getHeader("Referer");

// System.out.println("origin = " + orgin);
// System.out.println("referer = " + referer);
String p_auth = "";

// Origin can be null when doing same domain requests
if ((origin == null || allowedOrigin.equals(origin)) && (referer != null) && referer.startsWith(allowedReferer)) {
// if ((referer != null) && referer.startsWith(allowedReferer)) {
    response.setHeader("Access-Control-Allow-Origin", allowedOrigin);
    p_auth = com.liferay.portal.kernel.security.auth.AuthTokenUtil.getToken(request);
}
%>

<%= p_auth %>
