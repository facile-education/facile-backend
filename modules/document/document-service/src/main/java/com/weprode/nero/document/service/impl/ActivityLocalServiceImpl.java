package com.weprode.nero.document.service.impl;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.document.model.Activity;
import com.weprode.nero.document.service.base.ActivityLocalServiceBaseImpl;
import com.weprode.nero.group.service.GroupUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(
	property = "model.class.name=com.weprode.nero.document.model.Activity",
	service = AopService.class
)
public class ActivityLocalServiceImpl extends ActivityLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(ActivityLocalServiceImpl.class);

	public Activity addActivity(long fileEntryId, long folderId, long userId, long groupId,
								String fileName, String folderName, int type) {
		Activity activity = null;

		try {
			// Check if groupId is either a personal or an organization group
			Group group = GroupLocalServiceUtil.getGroup(groupId);

			if (group.isRegularSite() || group.isOrganization()) {
				long activityId = counterLocalService.increment();
				activity = activityPersistence.create(activityId);
				activity.setFileEntryId(fileEntryId);
				activity.setFolderId(folderId);
				activity.setUserId(userId);
				activity.setGroupId(groupId);
				activity.setFileName(fileName);
				activity.setFolderName(folderName);
				activity.setType(type);
				activity.setModificationDate(new Date());
				activity = activityPersistence.update(activity);
			} else {
				logger.debug("Operation done in personal space ("+group.getName()+") => not recorded");
			}

		} catch (Exception e) {
			logger.error("Error when creating new activity for user "+userId, e);
		}

		return activity;
	}

	public List<Activity> getActivity(List<Long> groupIdList, long creatorId, int start, int end) {
		if (groupIdList == null || groupIdList.isEmpty()) {
			return new ArrayList<>();
		}

		return activityFinder.getActivities(groupIdList, creatorId, start, end);
	}

	public List<Activity> getGroupsHistory(long userId, List<Long> groupIdList, Date minDate, Date maxDate) {
		return getGroupsActivity(userId, groupIdList, minDate, maxDate, true);
	}

	public List<Activity> getGroupsActivity(long userId, List<Long> groupIdList, Date minDate, Date maxDate,
											boolean fullHistory) {
		if (groupIdList == null || groupIdList.isEmpty()) {
			return new ArrayList<>();
		}

		return activityFinder.getGroupsActivities(userId, groupIdList, minDate, maxDate, fullHistory);
	}

	public boolean deleteGroupActivity(long groupId) {
		try {
			activityPersistence.removeBygroupId(groupId);
			return true;
		} catch (Exception e) {
			logger.error("Error deleting group activity for groupId " + groupId, e);
		}

		return false;
	}

	public JSONObject convertActivityToJson(Activity activity) {
		JSONObject jsonActivity = JSONFactoryUtil.createJSONObject();

		try {
			jsonActivity.put("activityId", activity.getActivityId());
			jsonActivity.put("userId", activity.getUserId());
			User user = UserLocalServiceUtil.getUser(activity.getUserId());
			jsonActivity.put("userName", user.getFullName());
			jsonActivity.put("author", user.getFullName());
			jsonActivity.put("fileName", activity.getFileName());
			jsonActivity.put("folderName", activity.getFolderName());
			jsonActivity.put("groupName", GroupUtilsLocalServiceUtil.getGroupName(activity.getGroupId()));

			boolean isFile = (activity.getType() < 5);
			jsonActivity.put("target", isFile ? activity.getFileName() : activity.getFolderName());
			if (isFile) {
				jsonActivity.put("fileLink", "/c/document_library/get_file?fileEntryId=" + activity.getFileEntryId());
				jsonActivity.put("fileId", activity.getFileEntryId());
			}
			boolean isFolder = (activity.getType() >= 5 && activity.getType() < 9);

			if (isFile || isFolder) {
				if (isFile) {
					jsonActivity.put("folderId", DLAppServiceUtil.getFileEntry(activity.getFileEntryId()).getFolderId());
				} else {
					jsonActivity.put("folderId", activity.getFolderId());
				}
			}

			// Group link redirects to the parent folder of the file, in the group document area
			// TODO link
			jsonActivity.put("folderLink", "/user//nero#/documents?folderId=" + activity.getFolderId());
			jsonActivity.put("modificationDate", new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT)
					.format(activity.getModificationDate()));
			jsonActivity.put("type", activity.getType());

		} catch (Exception e) {
			logger.error("Error converting activity " + activity.getActivityId() + " : " + e.getMessage());
			// Happened for deleted file or folder -> we currently don't want to display these activities as the link would be empty
			// Only displaying the "deleted" activity for this entry
			return null;
		}

		return jsonActivity;
	}

}