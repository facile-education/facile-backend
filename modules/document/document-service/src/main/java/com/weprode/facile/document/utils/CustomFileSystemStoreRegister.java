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

package com.weprode.facile.document.utils;

import com.liferay.document.library.kernel.store.Store;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

import java.util.Map;

@Component(
        configurationPid = "com.liferay.portal.store.file.system.configuration.FileSystemStoreConfiguration",
        service = {}
)public class CustomFileSystemStoreRegister {

    @Activate
    protected void activate(BundleContext bundleContext, Map<String, Object> properties) {

        CustomFileSystemStore customFileSystemStore = new CustomFileSystemStore();

        _serviceRegistration = bundleContext.registerService(
                Store.class, _wrapStore(customFileSystemStore),
                HashMapDictionaryBuilder.<String, Object>put(
                        "rootDir", customFileSystemStore.getRootDir()
                ).put(
                        "store.type", CustomFileSystemStore.class.getName()
                ).build());
    }

    @Deactivate
    protected void deactivate() {
        _serviceRegistration.unregister();
    }

    private Store _wrapStore(Store store) {
        return store;
    }

    private ServiceRegistration<Store> _serviceRegistration;
}
