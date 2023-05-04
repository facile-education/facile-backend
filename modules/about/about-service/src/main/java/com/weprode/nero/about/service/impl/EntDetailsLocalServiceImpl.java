package com.weprode.nero.about.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.weprode.nero.about.service.base.EntDetailsLocalServiceBaseImpl;
import com.weprode.nero.commons.properties.NeroSystemProperties;
import org.osgi.service.component.annotations.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@Component(
        service = AopService.class
)
public class EntDetailsLocalServiceImpl extends EntDetailsLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(EntDetailsLocalServiceImpl.class);

    public String getTermsOfUse() {
        StringBuilder chart = new StringBuilder();

        String fileUrl = PropsUtil.get(NeroSystemProperties.TERMS_OF_USE_FILE);

        // Read file and get content
        File chartFile = new File(fileUrl);
        try (BufferedReader br = new BufferedReader(new FileReader(chartFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                chart.append(line);
            }
        } catch (Exception e) {
            logger.error("Error reading file " + fileUrl);
        }

        return chart.toString();
    }
}
