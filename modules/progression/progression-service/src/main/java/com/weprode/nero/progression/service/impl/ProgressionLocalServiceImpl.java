package com.weprode.nero.progression.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.progression.model.Progression;
import com.weprode.nero.progression.model.ProgressionFolder;
import com.weprode.nero.progression.service.ProgressionFolderLocalServiceUtil;
import com.weprode.nero.progression.service.base.ProgressionLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.progression.model.Progression",
        service = AopService.class
)
public class ProgressionLocalServiceImpl extends ProgressionLocalServiceBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(ProgressionLocalServiceImpl.class);

    @Indexable(type = IndexableType.REINDEX)
    public Progression addProgression(long teacherId, String name,
                                      String description, long subjectId, String volee, String color)
            throws SystemException {
        Date now = new Date();

        long progressionId = counterLocalService.increment();

        Progression progression = progressionPersistence.create(progressionId);

        progression.setCompanyId(PortalUtil.getDefaultCompanyId());
        progression.setTeacherId(teacherId);
        progression.setCreateDate(now);
        progression.setModifiedDate(now);
        progression.setName(name);
        progression.setDescription(description);
        progression.setSubjectId(subjectId);
        progression.setVolee(volee);
        progression.setColor(color);

        return progressionPersistence.update(progression);
    }

    @Indexable(type = IndexableType.REINDEX)
    public Progression updateProgression(long progressionId, String name,
                                         String description, long subjectId, String volee, String color) throws SystemException {
        Progression progression = progressionPersistence.fetchByPrimaryKey(progressionId);

        progression.setModifiedDate(new Date());
        progression.setName(name);
        progression.setDescription(description);
        progression.setSubjectId(subjectId);
        progression.setVolee(volee);
        progression.setColor(color);

        progressionPersistence.update(progression);

        return progression;
    }

    public void updateProgressionModifiedDate(long progressionId) {
        try {
            Progression progression = progressionPersistence.fetchByPrimaryKey(progressionId);

            progression.setModifiedDate(new Date());

            progressionPersistence.update(progression);
        } catch (Exception e) {
            logger.error("Could not update modifiedDate for progressionId="+progressionId, e);
        }
    }

    @Indexable(type = IndexableType.DELETE)
    public void deleteProgressionById(long userId, long progressionId)
            throws PortalException, SystemException {
        // Remove progression folders
        List<ProgressionFolder> rootFolders = ProgressionFolderLocalServiceUtil.getProgressionRootFolders(progressionId);

        for (ProgressionFolder folder : rootFolders) {
            ProgressionFolderLocalServiceUtil.deleteFolder(userId, folder.getProgressionFolderId());
        }

        progressionPersistence.remove(progressionId);
    }

    public List<Progression> getTeacherProgressions(long teacherId) throws SystemException {
        return progressionPersistence.findByteacherId(teacherId);
    }
}
