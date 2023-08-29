package com.weprode.nero.commons;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component(
        immediate = true,
        property = {
                "servlet-context-name=",
                "servlet-filter-name=Log Filter",
                "url-pattern=/api/jsonws/*"
        },
        service = Filter.class
)
public class LogFilter extends BaseFilter {

    @Override
    protected void processFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws Exception {

        User user = PortalUtil.getUser(request);
        String uri = (String)request.getAttribute(WebKeys.INVOKER_FILTER_URI);
        String query = request.getQueryString();
        log.info("User " + (user != null ? user.getFullName() : "null") + " called API " + uri + " with params " + (query == null ? "null" : query));
        super.processFilter(request, response, filterChain);
    }

    @Override
    protected Log getLog() {
        return log;
    }

    private static final Log log = LogFactoryUtil.getLog(LogFilter.class);
}
