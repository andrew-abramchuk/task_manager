package com.mavenjavafxapp.crud.ResultEntriesClasses;


public class PersonsWithLongTasksResult {
    private String personFio;

    public void setpersonFio(String personFio) {
        this.personFio = personFio;
    }

    public String getPersonFIO() {
        return personFio;
    }

    public PersonsWithLongTasksResult(String personFio) {
        this.personFio = personFio;
    }
}
