package com.mavenjavafxapp.crud.interfaces;


import com.mavenjavafxapp.crud.ResultEntriesClasses.TaskTimeComment;
import com.mavenjavafxapp.entities.LogEntity;

import java.util.List;

public interface LogService {

    // Получить список всех логов.
    public List<LogEntity> getAll();

    // Получить список логов по ФИО.
    public List<LogEntity> findAllByTitle(String logTitle);

    // Получить список логов по ФИО без неск. колонок.
    public List<TaskTimeComment> findAllByTitleShrink(String logTitle);

    // Получить лог по id
    public LogEntity get(int id);
}
