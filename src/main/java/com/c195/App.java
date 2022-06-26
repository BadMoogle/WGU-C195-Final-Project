package com.c195;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Application object that runs the application
 */
public class App extends Application {

    // Current locale.  Sets it for the entire application.  Change for testing.
    public static Locale currentLocale = Locale.getDefault();
    //public static Locale currentLocale = new Locale("fr");
    private static int currentUserId; // current userid logged on to the application
    private static String currentUserName; // current user name logged on to the application

    /**
     * Returns the current username logged on to the application
     *
     * @return the current username logged on to the application
     */
    public static String getCurrentUserName() {
        return currentUserName;
    }

    /**
     * Sets the current username logged on to the application
     *
     * @param currentUserName Sets the current username logged on to the application
     */
    public static void setCurrentUserName(String currentUserName) {
        App.currentUserName = currentUserName;
    }

    /**
     * Gets the current userID logged on to the application
     *
     * @return The userID of the user logged on to the application
     */
    public static int getCurrentUserId() {
        return currentUserId;
    }

    /**
     * Sets the current userID logged on to the application
     *
     * @param currentUserId sets the current userID of the user to the application
     */
    public static void setCurrentUserId(int currentUserId) {
        App.currentUserId = currentUserId;
    }

    /**
     * Starts the initial window to the application
     *
     * @param stage Stage to start
     * @throws IOException any issues loading the fxml files
     */
    @Override
    public void start(Stage stage) throws IOException {
        ResourceBundle localizationBundle = ResourceBundle.getBundle("com.c195.localization", currentLocale);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("LoginForm.fxml"));
        fxmlLoader.setResources(localizationBundle);
        Scene scene = new Scene(fxmlLoader.load(), 361, 272);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle(localizationBundle.getString("appName"));
        stage.show();
    }

    /**
     * Application entry point
     *
     * @param args Argumentlist to the application
     */
    public static void main(String[] args) {
        launch();
    }
}