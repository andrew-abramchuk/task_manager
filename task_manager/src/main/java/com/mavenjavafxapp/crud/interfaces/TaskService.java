package com.mavenjavafxapp.crud.interfaces;


import com.mavenjavafxapp.crud.ResultEntriesClasses.PersonTasksWithStage;
import com.mavenjavafxapp.entities.TaskEntity;

import java.util.List;

public interface TaskService {

    // Получить список всех задач.
    public List<TaskEntity> getAll();

    // Получить список всех задач по исполнителю.
    public List<TaskEntity> findAllByFio(String taskFio);

    // Получить список всех задач по исполнителю без нек. колонок.
    public List<PersonTasksWithStage> findAllByFioShrink(String taskFio);

    // Добавить задачу.
    public TaskEntity add(TaskEntity task);

    // Обновить задачу.
    public TaskEntity update(TaskEntity task);

    // Получить задачу по id
    public TaskEntity get(int id);

}
