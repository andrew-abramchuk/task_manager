package com.mavenjavafxapp.crud.ResultEntriesClasses;


public class PersonTasksWithStage {
    private String taskTitle;
    private Boolean taskStage;
    private String taskStageString;

    public PersonTasksWithStage(String taskTitle, Boolean taskStage) {
        this.taskTitle = taskTitle;
        this.taskStage = taskStage;
        if (taskStage)
            this.taskStageString = "Выполнена";
        else
            this.taskStageString = "Не выполнена";
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Boolean getTaskStage() {
        return taskStage;
    }

    public void setTaskStage(Boolean taskStage) {
        this.taskStage = taskStage;
    }

    public String getTaskStageString() {
        return taskStageString;
    }

    public void setTaskStageString(String taskStageString) {
        this.taskStageString = taskStageString;
    }
}
