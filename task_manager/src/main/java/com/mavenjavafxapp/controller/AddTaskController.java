package com.mavenjavafxapp.controller;


import com.mavenjavafxapp.crud.Impl.TaskServiceImpl;
import com.mavenjavafxapp.crud.ResultEntriesClasses.PersonTasksWithStage;
import com.mavenjavafxapp.entities.TaskEntity;
import com.mavenjavafxapp.utils.DialogManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddTaskController {

    PersonTasksWithStage task;
    public TaskEntity taskEntity = new TaskEntity();
    public TaskServiceImpl taskServiceImpl = new TaskServiceImpl();

    @FXML
    private TextField txtField_FIO, txtField_Title;

    @FXML
    private ComboBox comBox_Stage;

    @FXML
    private void initialize() {
        comBox_Stage.getItems().addAll("Выполнена", "Невыполнена");
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
        taskEntity.setTaskTitle(txtField_Title.getText());
        taskEntity.setTaskFio(txtField_FIO.getText());

        Boolean stage = false;
        if (comBox_Stage.getSelectionModel().getSelectedItem().toString().equals("Выполнена"))
            stage = true;
        taskEntity.setTaskStage(stage);

        taskServiceImpl.add(taskEntity);

        txtField_FIO.clear();
        txtField_Title.clear();
        comBox_Stage.getSelectionModel().clearSelection();

        actionClose(actionEvent);
    }

    private boolean checkValues() {
        if (txtField_FIO.getText().trim().length() == 0 ||
                txtField_Title.getText().trim().length() == 0 ||
                comBox_Stage.getSelectionModel().getSelectedItem().toString().length() == 0) {
            DialogManager.showInfoDialog("Ошибка!", "Заполните все поля.");
            return false;
        }
        return true;
    }

}
