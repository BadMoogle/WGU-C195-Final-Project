/**
 * Sample Skeleton for 'LoginForm.fxml' Controller Class
 */

package com.c195.windowcontrollers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TimeZone;

import com.c195.App;
import com.c195.datamodels.DatabaseController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Login from controller
 */
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

    /**
     * Event handler for the exist button.
     *
     * @param event Ignored
     */
    @FXML
    void onButtonExitClick(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Event handler for the login button
     *
     * @param event Ignored
     */
    @FXML
    void onButtonLoginClick(ActionEvent event) throws IOException {
        String username = text_box_login.getText();
        String password = text_box_password.getText();
        ResourceBundle localizationBundle = ResourceBundle.getBundle("com.c195.localization", App.currentLocale);
        // Verify login
        if (DatabaseController.validateLogin(username, password)) {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainWindow.fxml"));
            fxmlLoader.setResources(localizationBundle);
            Scene scene = new Scene(fxmlLoader.load(), 1006, 443);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle(localizationBundle.getString("customerAppointments"));
            stage.show();
            Stage currentStage = (Stage) button_exit.getScene().getWindow();
            currentStage.close();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, localizationBundle.getString("incorrectPasswordError"));
            alert.showAndWait();
        }
    }

    /**
     * Called after the form is initialized
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        label_timezone.setText(TimeZone.getDefault().getDisplayName());
    }

}
