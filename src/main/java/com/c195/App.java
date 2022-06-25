package com.c195;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class App extends Application {
    public static Locale currentLocale = Locale.getDefault();
    //public static Locale currentLocale = new Locale("en");
    private static int currentUserId;
    private static String currentUserName;

    public static String getCurrentUserName() {
        return currentUserName;
    }

    public static void setCurrentUserName(String currentUserName) {
        App.currentUserName = currentUserName;
    }

    public static int getCurrentUserId() {
        return currentUserId;
    }

    public static void setCurrentUserId(int currentUserId) {
        App.currentUserId = currentUserId;
    }

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

    public static void main(String[] args) {
        launch();
    }
}