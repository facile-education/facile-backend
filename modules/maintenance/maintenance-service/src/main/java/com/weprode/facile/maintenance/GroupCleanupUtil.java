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

package com.weprode.facile.maintenance;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.facile.group.service.GroupUtilsLocalServiceUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GroupCleanupUtil {

    private static final Log logger = LogFactoryUtil.getLog(GroupCleanupUtil.class);

    GroupCleanupUtil() {
    }

    public static void multipleGroupCleanup(File file) {

        List<String> fileContent = getFileList(file);
        logger.info("MultipleGroupCleanup for "+fileContent.size()+" groups");
        int idx = 0;
        for (String line : fileContent) {
            try {
                idx++;
                logger.info("========================================== Processing group "+idx+"/"+fileContent.size());
                Long groupId = Long.parseLong(line);
                GroupUtilsLocalServiceUtil.groupCleanup(groupId);
            } catch (Exception e) {
                logger.error("Error processing group "+line);
            }
        }
    }

    public static List<String> getFileList(File file) {
        List<String> fileList = new ArrayList<>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                fileList.add(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            logger.error("File not found : "+file.getAbsolutePath());
        } catch (IOException e) {
            logger.error("IO Exception : "+file.getAbsolutePath());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                logger.error("Error closing reader", e);
            }
        }
        logger.info("File has "+fileList.size()+" group ids to cleanup ...");
        return fileList;
    }


}
