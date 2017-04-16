package com.mavenjavafxapp.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "task", schema = "task_manager")
@NamedQueries({
        @NamedQuery(name = "TaskEntity.getAll", query = "SELECT t from TaskEntity t"),
        @NamedQuery(name = "TaskEntity.findAllByFio",
                query = "SELECT t from TaskEntity t WHERE t.taskFio = :taskFio")
})
public class TaskEntity implements Serializable {

    private int taskId;
    private String taskFio;
    private String taskTitle;
    private Boolean taskStage;

    public TaskEntity() {

    }

    public TaskEntity(int taskId, String taskFio, String taskTitle, Boolean taskStage) {
        this.taskId = taskId;
        this.taskFio = taskFio;
        this.taskTitle = taskTitle;
        this.taskStage = taskStage;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", updatable = false, nullable = false)
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "task_fio", nullable = false, length = 45)
    public String getTaskFio() {
        return taskFio;
    }

    public void setTaskFio(String taskFio) {
        this.taskFio = taskFio;
    }

    @Basic
    @Column(name = "task_title", nullable = false, length = 30)
    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    @Basic
    @Column(name = "task_stage", nullable = false)
    public Boolean getTaskStage() {
        return taskStage;
    }

    public void setTaskStage(Boolean taskStage) {
        this.taskStage = taskStage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskEntity that = (TaskEntity) o;

        if (taskId != that.taskId) return false;
        if (taskStage != that.taskStage) return false;
        if (taskFio != null ? !taskFio.equals(that.taskFio) : that.taskFio != null) return false;
        if (taskTitle != null ? !taskTitle.equals(that.taskTitle) : that.taskTitle != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taskId;
        result = 31 * result + (taskFio != null ? taskFio.hashCode() : 0);
        result = 31 * result + (taskTitle != null ? taskTitle.hashCode() : 0);
        result = 31 * result + (taskStage ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "Id=" + taskId +
                ", Person Id =" + getPerson().getPersonId() +
                ", Fio='" + taskFio +
                ", Title=" + taskTitle +
                ", Stage=" + (taskStage ? 1 : 0) +
                '}';
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private PersonEntity person;

    @JoinColumn(name = "person_fk", referencedColumnName = "person_id", nullable = false)
    public PersonEntity getPerson() {
        return this.person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }


    private Set<LogEntity> taskLogs = new HashSet<LogEntity>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    public Set<LogEntity> getTaskLogs() {
        return this.taskLogs;
    }

    public void setTaskLogs(Set<LogEntity> taskLogs) {
        this.taskLogs = taskLogs;
    }

    public void addTaskLog(LogEntity taskLog) {
        taskLog.setTask(this);
        getTaskLogs().add(taskLog);
    }

    public void removeTaskLog(LogEntity taskLog) {
        getTaskLogs().remove(taskLog);
    }

}
