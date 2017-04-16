package com.mavenjavafxapp.crud.ResultEntriesClasses;


public class PersonNumbersResult {
    private String personFio;
    private int numberTasks;

    public String getPersonFio() {
        return personFio;
    }

    public PersonNumbersResult(String personFio, int numberTasks) {
        this.personFio = personFio;
        this.numberTasks = numberTasks;
    }

    public int getNumberTasks() {
        return numberTasks;
    }
}
