package com.mavenjavafxapp.entities;

import com.mavenjavafxapp.crud.ResultEntriesClasses.PersonsWithLongTasksResult;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "person", schema = "task_manager")
@NamedQueries({
        @NamedQuery(name = "PersonEntity.getAll", query = "SELECT p FROM PersonEntity p"),
        @NamedQuery(name = "PersonEntity.getByRegData",
                query = "SELECT p FROM PersonEntity p WHERE p.personFio = :personFio AND p.personLogin = :personLogin AND p.personPassword = :personPassword"),
        @NamedQuery(name = "PersonEntity.getAllWithNumbers",
                query = "SELECT person.personFio, CAST((SELECT COUNT(*) FROM TaskEntity task WHERE person.personFio = task.taskFio)AS integer)" +
                        " FROM PersonEntity person, TaskEntity task GROUP BY person.personFio")
        //SELECT person_fio, (SELECT COUNT(*) FROM task_manager.task WHERE person_fio = task_fio) FROM task_manager.person, task_manager.task GROUP BY person_fio
})
@NamedNativeQuery(name = "PersonEntity.findAllBySpentTime",
        query = "SELECT task.task_fio AS person_fio FROM task_manager.task WHERE task.task_title IN " +
                "(SELECT log_title FROM task_manager.log WHERE log_spent_time > :hours GROUP BY log_title) GROUP BY task_fio",
        resultSetMapping = "PersonValueMapping")
//SELECT task.task_fio FROM task_manager.task WHERE task.task_title IN (SELECT log_title FROM task_manager.log WHERE log_spent_time > 100 GROUP BY log_title) GROUP BY task_fio
@SqlResultSetMapping(
        name = "PersonValueMapping",
        classes = @ConstructorResult(
                targetClass = PersonsWithLongTasksResult.class,
                columns = {
                        @ColumnResult(name = "person_fio", type = String.class)
                }))
public class PersonEntity implements Serializable {

    private int personId;
    private String personFio;
    private String personLogin;
    private String personPassword;

    public PersonEntity() {

    }

    public PersonEntity(int personId, String personFio, String personLogin, String personPassword) {
        this.personId = personId;
        this.personFio = personFio;
        this.personLogin = personLogin;
        this.personPassword = personPassword;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", updatable = false, nullable = false)
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "person_fio", nullable = false, length = 45)
    public String getPersonFio() {
        return personFio;
    }

    public void setPersonFio(String personFio) {
        this.personFio = personFio;
    }

    @Basic
    @Column(name = "person_login", nullable = false, length = 20)
    public String getPersonLogin() {
        return personLogin;
    }

    public void setPersonLogin(String personLogin) {
        this.personLogin = personLogin;
    }

    @Basic
    @Column(name = "person_password", nullable = false, length = 12)
    public String getPersonPassword() {
        return personPassword;
    }

    public void setPersonPassword(String personPassword) {
        this.personPassword = personPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonEntity that = (PersonEntity) o;

        if (personId != that.personId) return false;
        if (personFio != null ? !personFio.equals(that.personFio) : that.personFio != null) return false;
        if (personLogin != null ? !personLogin.equals(that.personLogin) : that.personLogin != null) return false;
        if (personPassword != null ? !personPassword.equals(that.personPassword) : that.personPassword != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = personId;
        result = 31 * result + (personFio != null ? personFio.hashCode() : 0);
        result = 31 * result + (personLogin != null ? personLogin.hashCode() : 0);
        result = 31 * result + (personPassword != null ? personPassword.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "Id=" + personId +
                ", Fio='" + personFio +
                '}';
    }

    private Set<TaskEntity> personTasks = new HashSet<TaskEntity>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    public Set<TaskEntity> getPersonTasks() {
        return this.personTasks;
    }

    public void setPersonTasks(Set<TaskEntity> personTasks) {
        this.personTasks = personTasks;
    }

    public void addPersonTask(TaskEntity personTask) {
        personTask.setPerson(this);
        getPersonTasks().add(personTask);
    }

    public void removePersonTask(TaskEntity personTask) {
        getPersonTasks().remove(personTask);
    }

}
