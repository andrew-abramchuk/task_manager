package com.mavenjavafxapp.crud.interfaces;


import com.mavenjavafxapp.crud.ResultEntriesClasses.PersonsWithLongTasksResult;
import com.mavenjavafxapp.entities.PersonEntity;
import com.mavenjavafxapp.crud.ResultEntriesClasses.PersonNumbersResult;

import java.util.List;

public interface PersonService {

    // Получить список всех исполнителей.
    public List<PersonEntity> getAll();

    // Поиск по 3-м полям.
    public List<PersonEntity> getByRegData(String personFio, String personLogin, String personPassword);

    // Получить список пользователей с количеством задач
    public List<PersonNumbersResult> getAllWithNumbers();

    // Найти всех исполнителей с задачей > N часов затр. времени
    public List<PersonsWithLongTasksResult> findAllBySpentTime(int hours);

    // Добавить исполнителя.
    public PersonEntity add(PersonEntity person);

    // Обновить исполнителя.
    public PersonEntity update(PersonEntity person);

 }
