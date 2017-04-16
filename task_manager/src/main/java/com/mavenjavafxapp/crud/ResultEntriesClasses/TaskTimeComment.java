package com.mavenjavafxapp.crud.ResultEntriesClasses;


public class TaskTimeComment {

    private Integer logSpentTime;
    private String logComment;

    public Integer getLogSpentTime() {
        return logSpentTime;
    }

    public TaskTimeComment(Integer logSpentTime, String logComment) {
        this.logSpentTime = logSpentTime;
        this.logComment = logComment;
    }

    public void setLogSpentTime(Integer logSpentTime) {
        this.logSpentTime = logSpentTime;
    }

    public String getLogComment() {
        return logComment;
    }

    public void setLogComment(String logComment) {
        this.logComment = logComment;
    }
}
