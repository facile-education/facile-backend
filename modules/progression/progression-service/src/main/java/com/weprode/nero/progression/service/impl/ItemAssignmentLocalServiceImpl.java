package com.weprode.nero.progression.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.progression.model.ItemAssignment;
import com.weprode.nero.progression.service.ItemAssignmentLocalServiceUtil;
import com.weprode.nero.progression.service.base.ItemAssignmentLocalServiceBaseImpl;
import com.weprode.nero.progression.service.persistence.ItemAssignmentPK;
import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.progression.model.ItemAssignment",
        service = AopService.class
)
public class ItemAssignmentLocalServiceImpl extends ItemAssignmentLocalServiceBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(ItemAssignmentLocalServiceImpl.class);

    public ItemAssignment assignSession (long itemId, long sessionId) throws SystemException {
        ItemAssignment itemAssignment = null;

        ItemAssignmentPK pk = new ItemAssignmentPK(itemId, sessionId);
        // Check if an assignment with same itemId and sessionId already exists
        try {
            itemAssignment = itemAssignmentPersistence.fetchByPrimaryKey(pk);
        } catch (Exception e) {
            logger.debug(e);
        }

        if (itemAssignment == null) {
            itemAssignment = itemAssignmentPersistence.create(pk);

            itemAssignment.setAssignedDate(new Date());
            itemAssignment = ItemAssignmentLocalServiceUtil.updateItemAssignment(itemAssignment);
        }

        return itemAssignment;
    }

    public ItemAssignment assignHomework (long itemId, long sessionId, long homeworkId) throws SystemException {
        ItemAssignment itemAssignment = null;

        ItemAssignmentPK pk = new ItemAssignmentPK(itemId, sessionId);
        // Check if an assignment with same itemId and sessionId already exists
        try {
            itemAssignment = itemAssignmentPersistence.fetchByPrimaryKey(pk);
        } catch (Exception e) {
            logger.debug(e);
        }

        if (itemAssignment == null) {
            itemAssignment = itemAssignmentPersistence.create(pk);

            itemAssignment.setAssignedDate(new Date());
            itemAssignment.setHomeworkId(homeworkId);
            itemAssignment = ItemAssignmentLocalServiceUtil.updateItemAssignment(itemAssignment);
        }

        return itemAssignment;
    }

    public ItemAssignment markAssigmentAsOverride (long itemId, long sessionId) throws SystemException {
        ItemAssignmentPK pk = new ItemAssignmentPK(itemId, sessionId);
        logger.info("fetch itemAssigment by itemId, sessionId" + itemId + ", " + sessionId);
        ItemAssignment itemAssignment = itemAssignmentPersistence.fetchByPrimaryKey(pk);
        itemAssignment.setModifiedDate(new Date());
        return ItemAssignmentLocalServiceUtil.updateItemAssignment(itemAssignment);
    }

    public ItemAssignment getByItemIdHomeworkId (long itemId, long homeworkId) throws SystemException {
        return itemAssignmentPersistence.fetchByprogressionItemId_homeworkId(itemId, homeworkId);
    }

    public void deleteItemAssigment(ItemAssignment itemAssignment) throws SystemException {
        itemAssignmentPersistence.remove(itemAssignment);
    }

    public List<ItemAssignment> getItemAssignments(long itemId) throws SystemException {
        return itemAssignmentPersistence.findByprogressionItemId(itemId);
    }

    // Returns the progression itemId of type session that has been affected to the given CDT sessionId
    public long getSessionAssignmentItemId(long sessionId) {
        List<ItemAssignment> assignments;

        try {
            assignments = itemAssignmentPersistence.findBysessionId(sessionId);
            if (assignments != null && assignments.size() > 0) {
                for (ItemAssignment assignment : assignments) {
                    if (assignment.getHomeworkId() == 0) {
                        return assignment.getProgressionItemId();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error when fetching progression assignments for sessionId " + sessionId, e);
        }

        return 0;
    }

    public long getHomeworkAssignmentItemId(long homeworkId) {
        List<ItemAssignment> assignments;

        try {
            assignments = itemAssignmentPersistence.findByhomeworkId(homeworkId);
            if (assignments != null && assignments.size() > 0) {
                return assignments.get(0).getProgressionItemId();
            }
        } catch (Exception e) {
            logger.error("Error when fetching progression assignments for homeworkId " + homeworkId, e);
        }

        return 0;
    }

    public boolean isSessionAffected(long sessionId) {
        try {
            List<ItemAssignment> assignments = itemAssignmentPersistence.findBysessionId(sessionId);
            if (assignments != null && assignments.size() > 0) {
                return true;
            }
        } catch (Exception e) {
            logger.error("Error when fetching progression assignments for sessionId " + sessionId, e);
        }

        return false;
    }
}
