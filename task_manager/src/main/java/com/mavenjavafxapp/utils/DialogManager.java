package com.mavenjavafxapp.utils;

import javafx.scene.control.Alert;

public class DialogManager {

    public static void showInfoDialog(String title, String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        applyAlertSittings(alert, title, text);
    }

    public static void showErrorDialog(String title, String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        applyAlertSittings(alert, title, text);
    }

    public static void applyAlertSittings(Alert alert, String title, String text){
        alert.setTitle(title);
        alert.setContentText(text);
        alert.setHeaderText("");

        alert.showAndWait();
    }
}
