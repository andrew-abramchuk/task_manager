package com.mavenjavafxapp.entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "log", schema = "task_manager")
@NamedQueries({
        @NamedQuery(name = "LogEntity.getAll", query = "SELECT l from LogEntity l"),
        @NamedQuery(name = "LogEntity.findAllByTitle",
                query = "SELECT l from LogEntity l WHERE l.logTitle = :logTitle")
})
public class LogEntity implements Serializable {

    private int logId;
    private String logTitle;
    private Integer logSpentTime;
    private String logComment;

    public LogEntity() {

    }

    public LogEntity(int logId, String logTitle, Integer logSpentTime, String logComment) {
        this.logId = logId;
        this.logTitle = logTitle;
        this.logSpentTime = logSpentTime;
        this.logComment = logComment;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id", updatable = false, nullable = false)
    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    @Basic
    @Column(name = "log_title", nullable = false, length = 30)
    public String getLogTitle() {
        return logTitle;
    }

    public void setLogTitle(String logTitle) {
        this.logTitle = logTitle;
    }

    @Basic
    @Column(name = "log_spent_time", nullable = false)
    public Integer getLogSpentTime() {
        return logSpentTime;
    }

    public void setLogSpentTime(Integer logSpentTime) {
        this.logSpentTime = logSpentTime;
    }

    @Basic
    @Column(name = "log_comment", nullable = false, length = 45)
    public String getLogComment() {
        return logComment;
    }

    public void setLogComment(String logComment) {
        this.logComment = logComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogEntity that = (LogEntity) o;

        if (logId != that.logId) return false;
        if (logSpentTime != that.logSpentTime) return false;
        if (logTitle != null ? !logTitle.equals(that.logTitle) : that.logTitle != null) return false;
        if (logComment != null ? !logComment.equals(that.logComment) : that.logComment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = logId;
        result = 31 * result + (logTitle != null ? logTitle.hashCode() : 0);
        result = 31 * result + logSpentTime;
        result = 31 * result + (logComment != null ? logComment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Log{" +
                "Id =" + logId +
                ", Task Id =" + getTask().getTaskId() +
                ", Title =" + logTitle +
                ", Spent_time ='" + logSpentTime +
                ", Comment =" + logComment +
                '}';
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskEntity task;

    @JoinColumn(name = "task_fk", referencedColumnName = "task_id", nullable = false)
    public TaskEntity getTask() {
        return this.task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }
}
