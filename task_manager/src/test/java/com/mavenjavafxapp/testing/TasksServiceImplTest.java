package com.mavenjavafxapp.testing;

import com.mavenjavafxapp.crud.Impl.PersonServiceImpl;
import com.mavenjavafxapp.crud.Impl.TaskServiceImpl;
import com.mavenjavafxapp.crud.ResultEntriesClasses.PersonNumbersResult;
import com.mavenjavafxapp.crud.ResultEntriesClasses.PersonTasksWithStage;
import com.mavenjavafxapp.crud.ResultEntriesClasses.PersonsWithLongTasksResult;
import com.mavenjavafxapp.crud.interfaces.PersonService;
import com.mavenjavafxapp.entities.PersonEntity;
import com.mavenjavafxapp.entities.TaskEntity;
import org.junit.Test;

import java.util.List;

public class TasksServiceImplTest {

    TaskServiceImpl taskServiceImpl = new TaskServiceImpl();
    PersonServiceImpl personServiceImpl = new PersonServiceImpl();
    PersonService personService;

    @Test
    public void testSaveRecord() throws Exception {


        //Создаем сущность и записываем в БД
        TaskEntity task = new TaskEntity();
        task.setTaskTitle("Write the email");
        task.setTaskFio("Brown");
        task.setTaskStage(true);
        //taskServiceImpl.add(task);

        // Получаем выборки
        /*List<PersonEntity> p = personServiceImpl.getByRegData("Red", "re_d", "456789");
        System.out.println(p.get(0));
        List<PersonEntity> person = personServiceImpl.getAll();
        System.out.println(person.get(0));
        List<PersonNumbersResult> person1 = personServiceImpl.getAllWithNumbers();
        System.out.println(person1.get(0).getNumberTasks());*/
        List<PersonsWithLongTasksResult> person2 = personServiceImpl.findAllBySpentTime(100);
        System.out.println(person2.get(0).getPersonFIO());
        /*List<TaskEntity> persona2_5 = taskServiceImpl.findAllByFio("Red");
        System.out.println(persona2_5.get(0).getTaskTitle());
        List<PersonTasksWithStage> person3 = taskServiceImpl.findAllByFioShrink("Red");
        System.out.println(person3.get(0).getTaskTitle());*/


        //Вывели записанную в БД запись

    }
}
