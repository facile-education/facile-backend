package com.weprode.nero.progression.search;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchRegistrarHelper;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;
import com.weprode.nero.progression.model.Progression;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true)
public class ProgressionSearchRegistrar {

    @Activate
    protected void activate(BundleContext bundleContext) {
        logger.debug("Activate ProgressionSearchRegistrar");

        serviceRegistration = modelSearchRegistrarHelper.register(
                Progression.class, bundleContext, modelSearchDefinition -> {

                    modelSearchDefinition.setDefaultSelectedFieldNames(
                            Field.COMPANY_ID, Field.CONTENT,
                            Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
                            Field.GROUP_ID, Field.MODIFIED_DATE, Field.SCOPE_GROUP_ID,
                            Field.TITLE, Field.UID);

                    modelSearchDefinition.setModelIndexWriteContributor(
                            modelIndexWriterContributor);
                    modelSearchDefinition.setModelSummaryContributor(
                            modelSummaryContributor);
                    modelSearchDefinition.setSelectAllLocales(true);
                });
    }

    @Deactivate
    protected void deactivate() {
        serviceRegistration.unregister();
    }

    @Reference(target = "(indexer.class.name=com.weprode.nero.progression.model.Progression)")
    protected ModelIndexerWriterContributor<Progression> modelIndexWriterContributor;

    @Reference
    protected ModelSearchRegistrarHelper modelSearchRegistrarHelper;

    @Reference(target = "(indexer.class.name=com.weprode.nero.progression.model.Progression)")
    protected ModelSummaryContributor modelSummaryContributor;

    private ServiceRegistration<?> serviceRegistration;

    private static final Log logger = LogFactoryUtil.getLog(ProgressionSearchRegistrar.class);

}
