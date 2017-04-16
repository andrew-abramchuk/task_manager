package com.mavenjavafxapp.crud.Impl;

import com.mavenjavafxapp.crud.ResultEntriesClasses.PersonTasksWithStage;
import com.mavenjavafxapp.crud.interfaces.TaskService;
import com.mavenjavafxapp.entities.TaskEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


public class TaskServiceImpl implements TaskService {

    public List<TaskEntity> tasksList;
    public List<PersonTasksWithStage> tasksWithStagesList = new ArrayList<>();

    @PersistenceContext
    private EntityManager em = Persistence.createEntityManagerFactory("tm_persistenceUnit").createEntityManager();

    public List<TaskEntity> getAll() {
        tasksList = em.createNamedQuery("TaskEntity.getAll", TaskEntity.class).getResultList();
        return tasksList;
    }

    public List<TaskEntity> findAllByFio(String taskFio) {
        TypedQuery<TaskEntity> query = em.createNamedQuery("TaskEntity.findAllByFio", TaskEntity.class);
        query.setParameter("taskFio", taskFio);
        return query.getResultList();
    }

    public List<PersonTasksWithStage> findAllByFioShrink(String taskFio) {
        tasksList = findAllByFio(taskFio);
        for (TaskEntity task : tasksList) {
            tasksWithStagesList.add(new PersonTasksWithStage(task.getTaskTitle(), task.getTaskStage()));
        }
        return tasksWithStagesList;
    }

    public TaskEntity get(int id) {
        return em.find(TaskEntity.class, id);
    }

    public TaskEntity add(TaskEntity task) {
        em.getTransaction().begin();
        TaskEntity taskFromDB = em.merge(task);
        em.getTransaction().commit();
        return taskFromDB;
    }

    public TaskEntity update(TaskEntity task) {
        em.getTransaction().begin();
        TaskEntity taskFromDB = em.merge(task);
        em.getTransaction().commit();
        return taskFromDB;
    }


}
