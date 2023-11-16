/**
 * Copyright (c) 2022-present - Weprode
 * This file is part of FACILE ENT Project.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License (LGPL) as
 * published by the Free Software Foundation (version 2.1 of the License).
 * See LICENCE.txt file
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */

package com.weprode.facile.commons;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.apache.logging.log4j.ThreadContext;
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
        ThreadContext.clearAll();
        ThreadContext.put("userId", user.getFullName());
        log.info("API " + uri + " with params " + (query == null ? "null" : query));
        super.processFilter(request, response, filterChain);
    }

    @Override
    protected Log getLog() {
        return log;
    }

    private static final Log log = LogFactoryUtil.getLog(LogFilter.class);
}
