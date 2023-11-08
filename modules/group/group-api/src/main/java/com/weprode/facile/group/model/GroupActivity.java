package com.weprode.facile.group.model;

import java.util.Date;

public class GroupActivity implements Comparable<GroupActivity> {

    // This class aims at proxying group activities for easier sorting and pagination
    private long activityId;
    private long studentId; // For renvois
    private Date activityDate;
    private int activityType;

    public GroupActivity(long activityId, long studentId, Date activityDate, int activityType) {
        this.activityId = activityId;
        this.studentId = studentId;
        this.activityDate = activityDate;
        this.activityType = activityType;
    }

    public long getActivityId() {
        return activityId;
    }

    public long getStudentId() {
        return studentId;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public int getActivityType() {
        return activityType;
    }

    public int compareTo(GroupActivity groupActivity) {
        return this.getActivityDate().compareTo(groupActivity.getActivityDate());
    }
}
