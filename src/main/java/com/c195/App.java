package com.c195;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("LoginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 361, 272);
        stage.setResizable(false);
        stage.setScene(scene);
        Locale currentLocale = Locale.getDefault();
        ResourceBundle localizationBundle = ResourceBundle.getBundle("com.c195.localization", currentLocale);
        stage.setTitle(localizationBundle.getString("appName"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}