/**
 * Sample Skeleton for 'LoginForm.fxml' Controller Class
 */

package com.c195;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginFormController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="button_exit"
    private Button button_exit; // Value injected by FXMLLoader

    @FXML // fx:id="button_login"
    private Button button_login; // Value injected by FXMLLoader

    @FXML // fx:id="label_timezone"
    private Label label_timezone; // Value injected by FXMLLoader

    @FXML // fx:id="label_password"
    private Label label_password; // Value injected by FXMLLoader

    @FXML // fx:id="label_status"
    private Label label_status; // Value injected by FXMLLoader

    @FXML // fx:id="label_user_name"
    private Label label_user_name; // Value injected by FXMLLoader

    @FXML // fx:id="text_box_login"
    private TextField text_box_login; // Value injected by FXMLLoader

    @FXML // fx:id="text_box_password"
    private PasswordField text_box_password; // Value injected by FXMLLoader

    @FXML
    void onButtonExitClick(ActionEvent event) {
        Platform.exit();
    }



    @FXML
    void onButtonLoginClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 809, 527);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage) button_exit.getScene().getWindow();
        currentStage.close();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        label_timezone.setText(TimeZone.getDefault().getDisplayName());
        Locale currentLocale = Locale.getDefault();
        currentLocale = new Locale("fr"); // For testing different locales
        ResourceBundle localizationBundle = ResourceBundle.getBundle("com.c195.localization", currentLocale);
        label_user_name.setText(localizationBundle.getString("loginLabel"));
        text_box_login.setPromptText(localizationBundle.getString("loginPromptText"));
        label_password.setText(localizationBundle.getString("passwordLabel"));
        text_box_password.setPromptText(localizationBundle.getString("passwordPromptText"));
        button_login.setText(localizationBundle.getString("login"));
        button_exit.setText(localizationBundle.getString("exit"));
        assert button_exit != null : "fx:id=\"button_exit\" was not injected: check your FXML file 'LoginForm.fxml'.";
        assert button_login != null : "fx:id=\"button_login\" was not injected: check your FXML file 'LoginForm.fxml'.";
        assert label_status != null : "fx:id=\"label_status\" was not injected: check your FXML file 'LoginForm.fxml'.";
        assert text_box_password != null : "fx:id=\"text_box_password\" was not injected: check your FXML file 'LoginForm.fxml'.";
        assert text_box_login != null : "fx:id=\"text_field_login\" was not injected: check your FXML file 'LoginForm.fxml'.";

    }

}
