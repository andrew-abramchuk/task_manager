package com.mavenjavafxapp.controller;

import com.mavenjavafxapp.crud.Impl.LogServiceImpl;
import com.mavenjavafxapp.crud.Impl.PersonServiceImpl;
import com.mavenjavafxapp.crud.Impl.TaskServiceImpl;
import com.mavenjavafxapp.crud.ResultEntriesClasses.PersonNumbersResult;
import com.mavenjavafxapp.crud.ResultEntriesClasses.PersonTasksWithStage;
import com.mavenjavafxapp.crud.ResultEntriesClasses.TaskTimeComment;
import com.mavenjavafxapp.utils.DialogManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MainController {

    Boolean statusTasksEmpty = true, statusLogsEmpty = true;

    private Parent addTaskParent, addUserParent, changeUserParent, moreUserParent;
    private FXMLLoader addTaskLoader = new FXMLLoader(),
            addUserLoader = new FXMLLoader(),
            changeUserLoader = new FXMLLoader(),
            moreUserLoader = new FXMLLoader();
    private Stage mainStage,
            addUserDalogStage, changeUserDalogStage, moreUserDalogStage,
            addTaskDalogStage;

    private AddTaskController addTaskController;
    private AddUserController addUserController;
    private ChangeUserController changeUserController;
    private MoreUserController moreUserController;

    private PersonServiceImpl personServiceImpl = new PersonServiceImpl();
    private TaskServiceImpl taskServiceImpl = new TaskServiceImpl();
    private LogServiceImpl logServiceImpl = new LogServiceImpl();

    private ObservableList<PersonNumbersResult> personsTableList;
    private ObservableList<PersonTasksWithStage> tasksTableList;
    private ObservableList<TaskTimeComment> logsTableList;

    @FXML
    private TableView
            table_users,
            table_tasks,
            table_logs;

    @FXML
    private TableColumn
            column_user_name, column_user_tasksnumber,
            column_task_name, column_task_stage,
            column_log_time, column_log_comment;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    private void initialize() {
        initTableColums();

        personsTableList = FXCollections.observableList(personServiceImpl.getAllWithNumbers());
        table_users.setItems(personsTableList);

        initLoaders();
        initListeners();
    }

    private void initTableColums() {
        column_user_name.setCellValueFactory(new PropertyValueFactory<PersonNumbersResult, String>("personFio"));
        column_user_tasksnumber.setCellValueFactory(new PropertyValueFactory<PersonNumbersResult, Integer>("numberTasks"));
        column_task_name.setCellValueFactory(new PropertyValueFactory<PersonTasksWithStage, String>("taskTitle"));
        column_task_stage.setCellValueFactory(new PropertyValueFactory<PersonTasksWithStage, String>("taskStageString"));
        column_log_time.setCellValueFactory(new PropertyValueFactory<TaskTimeComment, Integer>("logSpentTime"));
        column_log_comment.setCellValueFactory(new PropertyValueFactory<TaskTimeComment, String>("logComment"));

        table_tasks.setPlaceholder(new Label("Выбирите запись в таблице \"ПОЛЬЗОВАТЕЛИ\""));
        table_logs.setPlaceholder(new Label("Выбирите запись в таблице \"ЗАДАЧИ\""));
    }

    private void initLoaders() {
        try {
            addTaskLoader.setLocation(getClass().getResource("/fxml/addTask.fxml"));
            addTaskParent = addTaskLoader.load();
            addTaskController = addTaskLoader.getController();

            addUserLoader.setLocation(getClass().getResource("/fxml/addUser.fxml"));
            addUserParent = addUserLoader.load();
            addUserController = addUserLoader.getController();

            changeUserLoader.setLocation(getClass().getResource("/fxml/changeUser.fxml"));
            changeUserParent = changeUserLoader.load();
            changeUserController = changeUserLoader.getController();

            moreUserLoader.setLocation(getClass().getResource("/fxml/moreUser.fxml"));
            moreUserParent = moreUserLoader.load();
            moreUserController = moreUserLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void initListeners() {
        table_users.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    updateTasksTableView();
                }
            }
        });

        table_tasks.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    updateLogsTableView();
                }
            }
        });
    }

    private void updateTasksTableView() {
        if (!statusTasksEmpty) {
            tasksTableList.clear();
            if (!statusLogsEmpty)
                logsTableList.clear();
        }
        PersonNumbersResult selectedItem = (PersonNumbersResult) table_users.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String selectedFio = selectedItem.getPersonFio();
            tasksTableList = FXCollections.observableList(taskServiceImpl.findAllByFioShrink(selectedFio));
            table_tasks.setItems(tasksTableList);

            statusTasksEmpty = false;
        }
    }

    private void updateLogsTableView() {
        if (!statusLogsEmpty) logsTableList.clear();

        PersonTasksWithStage selectedItem = (PersonTasksWithStage) table_tasks.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String selectedTitle = selectedItem.getTaskTitle();
            logsTableList = FXCollections.observableList(logServiceImpl.findAllByTitleShrink(selectedTitle));
            table_logs.setItems(logsTableList);

            statusLogsEmpty = false;
        }
    }

    public void actionButtonPressed(ActionEvent actionEvent) {

        Object source = actionEvent.getSource();
        // если нажата не кнопка - выходим из метода
        if (!(source instanceof Button)) {
            return;
        }

        PersonNumbersResult selectedPerson = (PersonNumbersResult) table_users.getSelectionModel().getSelectedItem();
        PersonTasksWithStage selectedTask = (PersonTasksWithStage) table_tasks.getSelectionModel().getSelectedItem();
        Button clickedButton = (Button) source;

        switch (clickedButton.getId()) {
            case "btnAdd_user":
                if (selectedPerson != null) {
                    statusTasksEmpty = true;
                    statusLogsEmpty = true;
                }
                showDialog(addUserDalogStage, addUserParent);
                personsTableList = FXCollections.observableList(personServiceImpl.getAllWithNumbers());
                table_users.setItems(personsTableList);
                break;
            case "btnAdd_task":
                if (!personIsSelected(selectedPerson)) {
                    return;
                }
                if (selectedTask != null) {
                    statusLogsEmpty = true;
                    if (!statusLogsEmpty) logsTableList.clear();
                }
                showDialog(addTaskDalogStage, addTaskParent);
                updateTasksTableView();
                break;
            case "btnChange_user":
                if (!personIsSelected(selectedPerson)) {
                    return;
                }
                changeUserController.setPerson((PersonNumbersResult) table_users.getSelectionModel().getSelectedItem());
                showDialog(changeUserDalogStage, changeUserParent);

                personsTableList = FXCollections.observableList(personServiceImpl.getAllWithNumbers());
                table_users.setItems(personsTableList);

                Optional<PersonNumbersResult> value = personsTableList.stream()
                        .filter(a -> a.getPersonFio().equals(changeUserController.getPerson().getPersonFio()))
                        .findFirst();
                table_users.getSelectionModel().select(value.get());
                break;
            case "btnMore_user":
                showDialog(moreUserDalogStage, moreUserParent);
                break;
        }

    }

    private boolean personIsSelected(PersonNumbersResult selectedPerson) {
        if (selectedPerson == null) {
            DialogManager.showInfoDialog("Ошибка!", "Выбирите запись.");
            return false;
        }
        return true;
    }

    private void showDialog(Stage dialogStage, Parent parent) {
        String dialogTitle;
        if (parent.equals(addTaskParent))
            dialogTitle = "Новая задача";
        else if (parent.equals(addUserParent))
            dialogTitle = "Новый исполнитель";
        else if (parent.equals(changeUserParent))
            dialogTitle = "Изменение профиля";
        else
            dialogTitle = "Подробнее";
        if (dialogStage == null) {
            dialogStage = new Stage();
            dialogStage.setTitle(dialogTitle);
            dialogStage.setResizable(false);

            if (parent.getScene() == null)
                dialogStage.setScene(new Scene(parent));
            else
                dialogStage.setScene(parent.getScene());

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainStage);
        }

        dialogStage.showAndWait(); // для ожидания закрытия окна
    }
}
