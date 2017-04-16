package com.mavenjavafxapp.crud.Impl;


import com.mavenjavafxapp.crud.ResultEntriesClasses.TaskTimeComment;
import com.mavenjavafxapp.crud.interfaces.LogService;
import com.mavenjavafxapp.entities.LogEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class LogServiceImpl implements LogService {

    public List<LogEntity> logsList;
    List<TaskTimeComment> logsTimeCommentList = new ArrayList<>();

    @PersistenceContext
    private EntityManager em = Persistence.createEntityManagerFactory("tm_persistenceUnit").createEntityManager();

    public List<LogEntity> getAll() {
        return em.createNamedQuery("LogEntity.getAll", LogEntity.class).getResultList();
    }

    public List<LogEntity> findAllByTitle(String logTitle) {
        TypedQuery<LogEntity> query = em.createNamedQuery("LogEntity.findAllByTitle", LogEntity.class);
        query.setParameter("logTitle", logTitle);
        return query.getResultList();
    }

    public List<TaskTimeComment> findAllByTitleShrink(String logTitle) {
        logsList = findAllByTitle(logTitle);
        for (LogEntity log : logsList) {
            logsTimeCommentList.add(new TaskTimeComment(log.getLogSpentTime(), log.getLogComment()));
        }
        return logsTimeCommentList;
    }

    public LogEntity get(int id) {
        return em.find(LogEntity.class, id);
    }
}