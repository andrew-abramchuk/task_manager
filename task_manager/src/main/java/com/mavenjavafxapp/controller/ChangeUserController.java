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

public class ChangeUserController {

    public PersonNumbersResult person;
    public PersonEntity personEntity = new PersonEntity();
    public PersonServiceImpl personServiceImpl = new PersonServiceImpl();

    @FXML
    private TextField txtField_FIO,
            txtField_OldLogin, txtField_NewLogin,
            txtField_OldPassword, txtField_NewPassword;


    public PersonNumbersResult getPerson() {
        return person;
    }

    public void setPerson(PersonNumbersResult person) {
        if (person == null) {
            return;
        }
        this.person = person;
        txtField_FIO.setText(person.getPersonFio());

    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void actionSave(ActionEvent actionEvent) {
        if (!checkValues()) {
            return;
        }
        int id = personServiceImpl.getByRegData(person.getPersonFio(),
                txtField_OldLogin.getText(), txtField_OldPassword.getText()).get(0).getPersonId();
        if (id > 0) {
            personEntity.setPersonId(id);
            personEntity.setPersonFio(txtField_FIO.getText());
            personEntity.setPersonLogin(txtField_NewLogin.getText());
            personEntity.setPersonPassword(txtField_NewPassword.getText());
            personServiceImpl.update(personEntity);

            txtField_FIO.clear();
            txtField_OldLogin.clear();
            txtField_NewLogin.clear();
            txtField_OldPassword.clear();
            txtField_NewPassword.clear();

            actionClose(actionEvent);
        } else
            DialogManager.showInfoDialog("Ошибка!", "Неверные старые аутентификационнаые данные.");
    }

    private boolean checkValues() {
        if (txtField_FIO.getText().trim().length() == 0 ||
                txtField_OldLogin.getText().trim().length() == 0 ||
                txtField_NewLogin.getText().trim().length() == 0 ||
                txtField_OldPassword.getText().trim().length() == 0 ||
                txtField_NewPassword.getText().trim().length() == 0) {
            DialogManager.showInfoDialog("Ошибка!", "Заполните все поля.");
            return false;
        }
        return true;
    }

}
