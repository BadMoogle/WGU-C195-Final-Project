package com.c195;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class MainWindowController {

    @FXML
    private Button button_exit;

    @FXML
    void onButtonExitClick(ActionEvent event) {
        Platform.exit();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert button_exit != null : "fx:id=\"button_exit\" was not injected: check your FXML file 'MainWindow.fxml'.";
        Locale currentLocale = Locale.getDefault();
        currentLocale = new Locale("fr"); // For testing different locales
        ResourceBundle localizationBundle = ResourceBundle.getBundle("com.c195.localization", currentLocale);
        button_exit.setText(localizationBundle.getString("exit"));
    }
}
