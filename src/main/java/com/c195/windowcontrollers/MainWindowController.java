/**
 * Sample Skeleton for 'MainWindow.fxml' Controller Class
 */

package com.c195.windowcontrollers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.c195.App;
import com.c195.datamodels.Appointment;
import com.c195.datamodels.Contact;
import com.c195.datamodels.Customer;
import com.c195.datamodels.DatabaseController;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainWindowController {

    private static ObservableList<Appointment> appointmentList;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm a");


    @FXML // fx:id="datePickerAppointment"
    private DatePicker datePickerAppointment; // Value injected by FXMLLoader


    @FXML // fx:id="radioButtonByMonth"
    private RadioButton radioButtonByMonth; // Value injected by FXMLLoader

    @FXML // fx:id="radioButtonByWeek"
    private RadioButton radioButtonByWeek; // Value injected by FXMLLoader

    @FXML // fx:id="buttonAdd"
    private Button buttonAdd; // Value injected by FXMLLoader

    @FXML // fx:id="buttonDelete"
    private Button buttonDelete; // Value injected by FXMLLoader

    @FXML // fx:id="buttonEdit"
    private Button buttonEdit; // Value injected by FXMLLoader

    @FXML
    private TableColumn<Appointment, Contact> tableColumnContact;

    @FXML
    private TableColumn<Appointment, Customer> tableColumnCustomer;

    @FXML
    private TableColumn<Appointment, String> tableColumnDescription;

    @FXML
    private TableColumn<Appointment, LocalDateTime> tableColumnEnd;

    @FXML
    private TableColumn<Appointment, String> tableColumnLocation;

    @FXML
    private TableColumn<Appointment, LocalDateTime> tableColumnStart;

    @FXML
    private TableColumn<Appointment, String> tableColumnTitle;

    @FXML
    private TableColumn<Appointment, String> tableColumnType;

    @FXML
    private TableView<Appointment> tableViewAppointments;


    @FXML
    void onDatePickerAction(ActionEvent event) {

    }

    @FXML
    void onButtonAddClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AddEditAppointmentWindow.fxml"));
        ResourceBundle localizationBundle = ResourceBundle.getBundle("com.c195.localization", App.currentLocale);
        fxmlLoader.setResources(localizationBundle);
        Scene scene = new Scene(fxmlLoader.load(), 401, 297);
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        Stage currentStage = (Stage) tableViewAppointments.getScene().getWindow();
        stage.initOwner(currentStage );
        stage.setTitle(localizationBundle.getString("addAppointment"));
        stage.setScene(scene);
        stage.showAndWait();
        appointmentList = DatabaseController.getAllAppointments();
        tableViewAppointments.setItems(appointmentList);
    }

    @FXML
    void onButtonDeleteClick(ActionEvent event) {
        Appointment appointment = tableViewAppointments.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ResourceBundle localizationBundle = ResourceBundle.getBundle("com.c195.localization", App.currentLocale);
        alert.setTitle(localizationBundle.getString("deleteAppointment"));
        alert.setContentText(localizationBundle.getString("deleteAppointment") + " " + appointment + "?" );
        ButtonType yesButton = new ButtonType(localizationBundle.getString("yes"), ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType(localizationBundle.getString("no"), ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesButton, noButton);
        alert.showAndWait().ifPresent(type -> {
            if (type == yesButton) {
                try {
                    DatabaseController.deleteAppointment(appointment);
                    appointmentList.remove(appointment);
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }

    @FXML
    void onButtonEditClick(ActionEvent event) throws IOException {
        AddEditAppointmentWindowController.appointment = tableViewAppointments.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AddEditAppointmentWindow.fxml"));
        ResourceBundle localizationBundle = ResourceBundle.getBundle("com.c195.localization", App.currentLocale);
        fxmlLoader.setResources(localizationBundle);
        Scene scene = new Scene(fxmlLoader.load(), 401, 297);
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        Stage currentStage = (Stage) tableViewAppointments.getScene().getWindow();
        stage.initOwner(currentStage );
        stage.setTitle(localizationBundle.getString("edit") + " " + AddEditAppointmentWindowController.appointment.getTitle());
        stage.setScene(scene);
        stage.showAndWait();
        appointmentList = DatabaseController.getAllAppointments();
        tableViewAppointments.setItems(appointmentList);
    }

    @FXML
    void onFileManageCustomersClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ManageCustomersWindow.fxml"));
        ResourceBundle localizationBundle = ResourceBundle.getBundle("com.c195.localization", App.currentLocale);
        fxmlLoader.setResources(localizationBundle);
        Scene scene = new Scene(fxmlLoader.load(), 951, 528);
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        Stage currentStage = (Stage) tableViewAppointments.getScene().getWindow();
        stage.initOwner(currentStage );
        stage.setTitle(localizationBundle.getString("manageCustomers"));
        stage.setScene(scene);
        stage.showAndWait();
        appointmentList = DatabaseController.getAllAppointments();
        tableViewAppointments.setItems(appointmentList);
    }

    @FXML
    void onFileExitClick(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void onCustomerNewCustomerClick(ActionEvent event) throws IOException {
        AddEditCustomerWindowController.customer = null;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AddEditCustomerWindow.fxml"));
        ResourceBundle localizationBundle = ResourceBundle.getBundle("com.c195.localization", App.currentLocale);
        fxmlLoader.setResources(localizationBundle);
        Scene scene = new Scene(fxmlLoader.load(), 382, 314);
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        Stage currentStage = (Stage) tableViewAppointments.getScene().getWindow();
        stage.initOwner(currentStage );
        stage.setTitle(localizationBundle.getString("newCustomer"));
        stage.setScene(scene);
        stage.showAndWait();
        appointmentList = DatabaseController.getAllAppointments();
        tableViewAppointments.setItems(appointmentList);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        tableColumnContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        tableColumnCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        tableColumnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableColumnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableColumnLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        tableColumnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableColumnStart.setCellValueFactory(start -> start.getValue().getStartDateTime());
        tableColumnStart.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {

                super.updateItem(item, empty);
                if (empty)
                    setText(null);
                else
                    setText(String.format(item.format(formatter)));
            }
        });
        tableColumnEnd.setCellValueFactory(end -> end.getValue().getEndDateTime());
        tableColumnEnd.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {

                super.updateItem(item, empty);
                if (empty)
                    setText(null);
                else
                    setText(String.format(item.format(formatter)));
            }
        });
        appointmentList = DatabaseController.getAllAppointments();
        tableViewAppointments.setItems(appointmentList);
        // Lambda to create the callback function required to get a row level event listener
        tableViewAppointments.setRowFactory(rowFactory -> {
            TableRow<Appointment> row = new TableRow<>();

            // Lambdaception to create an anonymous event listener because we only use it here and nowhere else.
            row.setOnMouseClicked(mouseEvent -> {
                Appointment appointment = tableViewAppointments.getSelectionModel().getSelectedItem();
                if (appointment != null) {
                    buttonDelete.setDisable(false);
                    buttonEdit.setDisable(false);
                }
            });
            return row;
        });
    }

}