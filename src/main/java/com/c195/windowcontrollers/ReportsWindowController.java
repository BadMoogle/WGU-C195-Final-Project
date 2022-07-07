/**
 * Sample Skeleton for 'ReportsWindow.fxml' Controller Class
 */

package com.c195.windowcontrollers;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.TreeSet;

import com.c195.datamodels.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 * Controller for the Reports Window
 *
 */
public class ReportsWindowController {

    private ObservableList<Appointment> appointmentsByContact; //List of appointments for the contact table view
    private ObservableList<Appointment> appointmentsByCustomer; //List of appointments for the customer report
    private ObservableList<AppointmentsByType> appointmentsByTypes; //Used to view the types of appointments
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm a"); //DateTimeFormatter

    private ObservableSet<LocalDate> selectedDates;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;


    @FXML // fx:id="dateTimePickerByMonth"
    private DatePicker dateTimePickerByMonth; // Value injected by FXMLLoader

    @FXML // fx:id="labelTotalAppointmentsByMonth"
    private Label labelTotalAppointmentsByMonth; // Value injected by FXMLLoader

    @FXML // fx:id="tableByMonthNumber"
    private TableColumn<AppointmentsByType, Integer> tableByMonthNumber; // Value injected by FXMLLoader

    @FXML // fx:id="tableByMonthType"
    private TableColumn<AppointmentsByType, String> tableByMonthType; // Value injected by FXMLLoad

    @FXML // fx:id="comboBoxAppointmentByContact"
    private ComboBox<Contact> comboBoxAppointmentByContact; // Value injected by FXMLLoader

    @FXML // fx:id="tableColumnCustomer"
    private TableColumn<Appointment, Customer> tableColumnCustomer; // Value injected by FXMLLoader

    @FXML // fx:id="tableColumnDescription"
    private TableColumn<Appointment, String> tableColumnDescription; // Value injected by FXMLLoader

    @FXML // fx:id="tableColumnEnd"
    private TableColumn<Appointment, LocalDateTime> tableColumnEnd; // Value injected by FXMLLoader

    @FXML // fx:id="tableColumnLocation"
    private TableColumn<Appointment, String> tableColumnLocation; // Value injected by FXMLLoader

    @FXML // fx:id="tableColumnStart"
    private TableColumn<Appointment, LocalDateTime> tableColumnStart; // Value injected by FXMLLoader

    @FXML // fx:id="tableColumnTitle"
    private TableColumn<Appointment, String> tableColumnTitle; // Value injected by FXMLLoader

    @FXML // fx:id="tableColumnID"
    private TableColumn<Appointment, Integer> tableColumnID; // Value injected by FXMLLoader

    @FXML // fx:id="tableColumnType"
    private TableColumn<Appointment, String> tableColumnType; // Value injected by FXMLLoader

    @FXML // fx:id="tableViewAppointments"
    private TableView<Appointment> tableViewAppointments; // Value injected by FXMLLoader

    @FXML // fx:id="tableViewAppointmentsOfTypeByMonth"
    private TableView<AppointmentsByType> tableViewAppointmentsOfTypeByMonth; // Value injected by FXMLLoader

    @FXML // fx:id="tableViewCustomerAppointments"
    private TableView<Appointment> tableViewCustomerAppointments; // Value injected by FXMLLoader

    @FXML // fx:id="tableColumnCustomerContact"
    private TableColumn<Appointment, Contact> tableColumnCustomerContact; // Value injected by FXMLLoader

    @FXML // fx:id="tableColumnCustomerEnd"
    private TableColumn<Appointment, LocalDateTime> tableColumnCustomerEnd; // Value injected by FXMLLoader

    @FXML // fx:id="tableColumnCustomerStart"
    private TableColumn<Appointment, LocalDateTime> tableColumnCustomerStart; // Value injected by FXMLLoader


    @FXML // fx:id="tableCustomerColumnDescription"
    private TableColumn<Appointment, String> tableCustomerColumnDescription; // Value injected by FXMLLoader

    @FXML // fx:id="tableCustomerColumnID"
    private TableColumn<Appointment, String> tableCustomerColumnID; // Value injected by FXMLLoader

    @FXML // fx:id="tableCustomerColumnLocation"
    private TableColumn<Appointment, String> tableCustomerColumnLocation; // Value injected by FXMLLoader

    @FXML // fx:id="tableCustomerColumnTitle"
    private TableColumn<Appointment, String> tableCustomerColumnTitle; // Value injected by FXMLLoader

    @FXML // fx:id="tableCustomerColumnType"
    private TableColumn<Appointment, String> tableCustomerColumnType; // Value injected by FXMLLoader

    @FXML // fx:id="comboBoxAppointmentByCustomer"
    private ComboBox<Customer> comboBoxAppointmentByCustomer; // Value injected by FXMLLoader

    @FXML
    void onButtonCloseClick(ActionEvent event) {
        Stage currentStage = (Stage) tableViewAppointments.getScene().getWindow();
        currentStage.close();
    }


    @FXML
    void onComboBoxAppointmentByContactChange(ActionEvent event) {
        Contact contact = comboBoxAppointmentByContact.getSelectionModel().getSelectedItem();
        if (contact != null){
            appointmentsByContact = DatabaseController.getAppointmentByContactId(contact.getContactId());
            tableViewAppointments.setItems(appointmentsByContact);
        }
    }

    @FXML
    void onComboBoxAppointmentByCustomerChange(ActionEvent event) {
        Customer customer = comboBoxAppointmentByCustomer.getSelectionModel().getSelectedItem();
        if (customer != null){
            appointmentsByCustomer = DatabaseController.getAppointmentByCustomerId(customer.getCustomerId());
            tableViewCustomerAppointments.setItems(appointmentsByCustomer);
        }
    }

    @FXML
    void onDateTimePickerByMonthAction(ActionEvent event) {
        selectedDates.clear();
        LocalDate date = dateTimePickerByMonth.getValue();
        if (date != null) {
            selectedDates.add(date);
            for (int i = 1; i <= date.getMonth().maxLength(); i++) {
                selectedDates.add(LocalDate.of(date.getYear(), date.getMonth(), i));
            }
            appointmentsByTypes.clear();
            ObservableList<Appointment> appointmentList = DatabaseController.getAppointmentsByMonth(
                    date.getYear(), date.getMonth());
            labelTotalAppointmentsByMonth.setText(Long.toString(appointmentList.stream().count()));
            for (Appointment appointment : appointmentList) {
                boolean added = false;
                for (AppointmentsByType type : appointmentsByTypes) {
                    if (appointment.getType().equals(type.getType())) {
                        type.setNumber(type.getNumber() + 1);
                        added = true;
                    }
                }
                if (!added) {
                    appointmentsByTypes.add(new AppointmentsByType(appointment.getType(), 1));
                }
            }
            tableViewAppointmentsOfTypeByMonth.setItems(appointmentsByTypes);
        }
    }

    /**
     * Called after the form is initialized.
     * Lambas to alter how the dates are formatted.
     *
     * Lamdas to select the entire month when the date is clicked.
     *
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        this.selectedDates = FXCollections.observableSet(new TreeSet<>());
        this.appointmentsByTypes = FXCollections.observableArrayList();
        appointmentsByCustomer = FXCollections.observableArrayList();
        comboBoxAppointmentByCustomer.setItems(DatabaseController.getAllCustomers());
        tableByMonthType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableByMonthNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        comboBoxAppointmentByContact.setItems(DatabaseController.getAllContacts());
        tableColumnID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
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

        tableCustomerColumnID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        tableCustomerColumnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableCustomerColumnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableCustomerColumnLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        tableCustomerColumnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableColumnCustomerContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        tableColumnCustomerStart.setCellValueFactory(start -> start.getValue().getStartDateTime());
        tableColumnCustomerStart.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {

                super.updateItem(item, empty);
                if (empty)
                    setText(null);
                else
                    setText(String.format(item.format(formatter)));
            }
        });
        tableColumnCustomerEnd.setCellValueFactory(end -> end.getValue().getEndDateTime());
        tableColumnCustomerEnd.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {

                super.updateItem(item, empty);
                if (empty)
                    setText(null);
                else
                    setText(String.format(item.format(formatter)));
            }
        });


        dateTimePickerByMonth.setDayCellFactory(callback -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (selectedDates.contains(item)) {
                    setStyle("-fx-background-color: rgba(3, 169, 244, 0.7);");
                }
                else {
                    setStyle(null);
                }
            }
        });
    }

}
