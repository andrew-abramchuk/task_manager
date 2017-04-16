package com.mavenjavafxapp.controller;


import com.mavenjavafxapp.crud.Impl.PersonServiceImpl;
import com.mavenjavafxapp.crud.ResultEntriesClasses.PersonNumbersResult;
import com.mavenjavafxapp.entities.PersonEntity;
import com.mavenjavafxapp.utils.DialogManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddUserController {

    public PersonNumbersResult person;
    public PersonEntity personEntity = new PersonEntity();
    public PersonServiceImpl personServiceImpl = new PersonServiceImpl();

    @FXML
    private TextField txtField_FIO, txtField_Login, txtField_Password;

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void actionSave(ActionEvent actionEvent) {
        if (!checkValues()) {
            return;
        }
        personEntity.setPersonFio(txtField_FIO.getText());
        personEntity.setPersonLogin(txtField_Login.getText());
        personEntity.setPersonPassword(txtField_Password.getText());
        personServiceImpl.add(personEntity);

        txtField_FIO.clear();
        txtField_Login.clear();
        txtField_Password.clear();

        actionClose(actionEvent);
    }

    private boolean checkValues() {
        if (txtField_FIO.getText().trim().length() == 0 ||
                txtField_Login.getText().trim().length() == 0 ||
                txtField_Password.getText().trim().length() == 0) {
            DialogManager.showInfoDialog("Ошибка!", "Заполните все поля.");
            return false;
        }
        return true;
    }
}


