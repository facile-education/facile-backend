package com.weprode.nero.application.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.application.model.AuthorizedSchool;
import com.weprode.nero.application.service.base.AuthorizedSchoolLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.application.model.AuthorizedSchool",
        service = AopService.class
)
public class AuthorizedSchoolLocalServiceImpl extends AuthorizedSchoolLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(AuthorizedSchoolLocalServiceImpl.class.getName());

    public boolean addAuthorizedSchool(long applicationId, long schoolId) throws SystemException {
        final long authorizedSchoolId = counterLocalService.increment();

        AuthorizedSchool authorizedSchool = authorizedSchoolPersistence.create(authorizedSchoolId);
        authorizedSchool.setSchoolId(schoolId);
        authorizedSchool.setApplicationId(applicationId);
        authorizedSchoolPersistence.update(authorizedSchool);

        return true;
    }

    public List<Long> getAuthorizedSchoolIds(long applicationId) throws SystemException {
        List<Long> authorizedSchoolIds = new ArrayList<>();

        List<AuthorizedSchool> list = authorizedSchoolPersistence.findByapplicationId(applicationId);
        if (list != null) {
            for (AuthorizedSchool filter : list) {
                authorizedSchoolIds.add(filter.getSchoolId());
            }
        }

        return authorizedSchoolIds;
    }

    public boolean deleteByApplicationId(long applicationId) throws SystemException {
        authorizedSchoolPersistence.removeByapplicationId(applicationId);

        return true;
    }

    public boolean deleteByApplicationIdSchoolId(long applicationId, long schoolId) throws SystemException {
        try {
            authorizedSchoolPersistence.removeByapplicationId_schoolId(applicationId, schoolId);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting application authorized school for applicationId " + applicationId + " and schoolId " + schoolId, e);
        }

        return false;
    }

    public boolean isSchoolAuthorized(long applicationId, long schoolId) throws SystemException {
        return authorizedSchoolPersistence.countByapplicationId(applicationId) <= 0
                || authorizedSchoolPersistence.fetchByapplicationId_schoolId(applicationId, schoolId) != null;
    }

}
