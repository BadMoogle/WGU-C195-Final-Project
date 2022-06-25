module com.example.c195 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;
    requires mysql.connector.java;


    opens com.c195 to javafx.fxml;
    exports com.c195;
    opens com.c195.windowcontrollers to javafx.fxml;
    exports com.c195.windowcontrollers;
    opens com.c195.datamodels to javafx.fxml;
    exports com.c195.datamodels;
}