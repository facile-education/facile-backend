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

package com.weprode.facile.help.service.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.facile.help.model.HelpCategory;
import com.weprode.facile.help.model.HelpItem;
import com.weprode.facile.help.service.HelpItemLocalServiceUtil;
import com.weprode.facile.help.service.base.HelpCategoryLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.help.model.HelpCategory",
        service = AopService.class
)
public class HelpCategoryLocalServiceImpl extends HelpCategoryLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(HelpCategoryLocalServiceImpl.class);

    public HelpCategory createCategory(String categoryName, long serviceId) {
        long categoryId;

        try {
            categoryId = counterLocalService.increment();
            final HelpCategory category = helpCategoryPersistence.create(categoryId);
            category.setCategoryName(categoryName);
            category.setServiceId(serviceId);

            // Calculate position from existing maximum position + 1
            List<HelpCategory> categories = getAllCategories();
            int maxPosition = 0;
            if (categories != null) {
                for (HelpCategory _category : categories) {
                    if (_category.getPosition() > maxPosition) {
                        maxPosition = _category.getPosition();
                    }
                }
            }
            maxPosition += 1;
            category.setPosition(maxPosition);
            helpCategoryPersistence.update(category);

            return category;
        } catch (SystemException e) {
            logger.debug(e);
        }

        return null;
    }

    public List<HelpCategory> getAllCategories() {
        List<HelpCategory> helpCategoryList = new ArrayList<>();

        try {
            helpCategoryList = helpCategoryPersistence.findAll();
        } catch (Exception e) {
            logger.debug(e);
        }

        return helpCategoryList;
    }

    public void removeCategory(long categoryId) throws Exception {
        // Remove items
        for (HelpItem item : HelpItemLocalServiceUtil.getHelpItems(categoryId, StringPool.BLANK)) {
            HelpItemLocalServiceUtil.deleteItem(item.getItemId());
        }

        helpCategoryPersistence.remove(categoryId);
    }
}
