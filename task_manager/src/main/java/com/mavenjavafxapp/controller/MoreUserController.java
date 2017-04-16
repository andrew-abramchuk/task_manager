package com.mavenjavafxapp.controller;


import com.mavenjavafxapp.crud.Impl.PersonServiceImpl;
import com.mavenjavafxapp.crud.ResultEntriesClasses.PersonsWithLongTasksResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MoreUserController {

    private PersonServiceImpl personServiceImpl = new PersonServiceImpl();
    private ObservableList<PersonsWithLongTasksResult> personsTableList;

    @FXML
    private TableView table_users;

    @FXML
    private TableColumn column_user_nameMore;

    @FXML
    private void initialize() {

            column_user_nameMore.setCellValueFactory(new PropertyValueFactory<PersonsWithLongTasksResult, String>("personFio"));
            personsTableList = FXCollections.observableList(personServiceImpl.findAllBySpentTime(100));
            table_users.setItems(personsTableList);
    }
}
