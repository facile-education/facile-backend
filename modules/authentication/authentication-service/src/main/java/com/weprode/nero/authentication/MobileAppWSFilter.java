package com.weprode.nero.authentication;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component(
        immediate = true,
        property = {
                "servlet-context-name=",
                "servlet-filter-name=Mobile App Filter",
                "url-pattern=/api/jsonws/mobile.mobiledevice/*"
        },
        service = Filter.class
)
public class MobileAppWSFilter extends BaseFilter {

    private static final Log logger = LogFactoryUtil.getLog(MobileAppWSFilter.class);

    @Override
    protected Log getLog() {
        return logger;
    }

    @Override
    protected void processFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws Exception {

        logger.debug("Receiving call for mobile app webservice, checking auth token...");

        /*String expectedBearer = "Bearer " + SystemProperties.get(NeroSystemProperties.MOBILE_WS_TOKEN);
        String authHeader = request.getHeader("Authorization");
        logger.debug("Request token is : " + authHeader);
        logger.debug("Expecting : " + expectedBearer);

        if (authHeader == null || !authHeader.equals(expectedBearer)) {
            logger.error("Authentication tokens doesn't match. Throwing 403 error.");
            PortalUtil.sendError(403, new PrincipalException("Access denied."), request, response);
        }*/

        super.processFilter(request, response, filterChain);
    }
}
