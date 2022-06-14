module com.example.c195 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.c195 to javafx.fxml;
    exports com.c195;
}