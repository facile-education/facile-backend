package com.weprode.nero.document.utils;

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
