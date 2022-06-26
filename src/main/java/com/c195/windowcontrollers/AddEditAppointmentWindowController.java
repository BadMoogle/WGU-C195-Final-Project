package com.c195.windowcontrollers;

import java.net.URL;
import java.time.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import com.c195.App;
import com.c195.datamodels.Appointment;
import com.c195.datamodels.Contact;
import com.c195.datamodels.Customer;
import com.c195.datamodels.DatabaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Window controller for the Add/Edit Appointment Window.
 */
public class AddEditAppointmentWindowController {

    public static Appointment appointment; // Static field to pass the existing appointment to the controller
    public ObservableList<String> amPm; // List to populate the spinner
    public ObservableList<String> zones; // List to populate the comboBoxZoneList

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private DatePicker datePickerEnd;

    @FXML
    private DatePicker datePickerStart;

    @FXML
    private Spinner<String> selectorEmdAMPM;

    @FXML
    private Spinner<Integer> selectorEndHours;

    @FXML
    private Spinner<Integer> selectorEndMinutes;

    @FXML
    private Spinner<String> selectorStartAMPM;

    @FXML
    private Spinner<Integer> selectorStartHour;

    @FXML
    private Spinner<Integer> selectorStartMinutes;

    @FXML
    private TextField textBoxID;


    @FXML // fx:id="buttonCancel"
    private Button buttonCancel; // Value injected by FXMLLoader

    @FXML // fx:id="buttonSave"
    private Button buttonSave; // Value injected by FXMLLoader

    @FXML // fx:id="comboBoxContact"
    private ComboBox<Contact> comboBoxContact; // Value injected by FXMLLoader

    @FXML // fx:id="comboBoxCustomer"
    private ComboBox<Customer> comboBoxCustomer; // Value injected by FXMLLoader


    @FXML // fx:id="comboBoxTimeZone"
    private ComboBox<String> comboBoxTimeZone; // Value injected by FXMLLoader

    @FXML // fx:id="textBoxDescription"
    private TextField textBoxDescription; // Value injected by FXMLLoader

    @FXML // fx:id="textBoxLocation"
    private TextField textBoxLocation; // Value injected by FXMLLoader

    @FXML // fx:id="textBoxTitle"
    private TextField textBoxTitle; // Value injected by FXMLLoader

    @FXML // fx:id="textBoxType"
    private TextField textBoxType; // Value injected by FXMLLoader

    /**
     * Event handler for when the Cancel button is clicked.
     *
     * @param event Event raised.  Not used
     */
    @FXML
    void onButtonCancelClick(ActionEvent event) {
        appointment = null;
        Stage currentStage = (Stage) buttonCancel.getScene().getWindow();
        currentStage.close();
    }

    /**
     * Event handler for when the save button is clicked.
     *
     * @param event Event raised.  Not used.
     */
    @FXML
    void onButtonSaveClick(ActionEvent event) {
        if (textBoxType.getText() == "" || textBoxTitle.getText() == ""
                || textBoxLocation.getText() == "" ||textBoxDescription.getText() == ""
                || comboBoxContact.getSelectionModel().getSelectedItem() == null
                || comboBoxCustomer.getSelectionModel().getSelectedItem() == null
                || datePickerStart.getValue() == null || datePickerEnd.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, resources.getString("fillOutAllFields"));
            alert.showAndWait();
            return;
        }
        try {
            int startHour = selectorStartHour.getValue();
            if (selectorStartAMPM.getValue() == "PM"){ // Convert to 24 hour format.
                startHour += 12;
                if (startHour == 24) {
                    startHour = 0;
                }
            }
            ZonedDateTime startTime = ZonedDateTime.of(datePickerStart.getValue().getYear(),
                    datePickerStart.getValue().getMonth().getValue(),
                    datePickerStart.getValue().getDayOfMonth(), startHour,
                    selectorStartMinutes.getValue(), 0, 0, ZoneId.of(comboBoxTimeZone.getValue()));
            int endHour = selectorEndHours.getValue();
            if (selectorEmdAMPM.getValue() == "PM"){ // Convert to 24 hour format.
                endHour += 12;
                if (endHour == 24) {
                    endHour = 0;
                }
            }
            ZonedDateTime endTime = ZonedDateTime.of(datePickerEnd.getValue().getYear(),
                    datePickerEnd.getValue().getMonth().getValue(),
                    datePickerEnd.getValue().getDayOfMonth(), endHour,
                    selectorEndMinutes.getValue(), 0, 0, ZoneId.of(comboBoxTimeZone.getValue()));
            if (appointment == null){ // If new appointment
                Appointment newAppointment = new Appointment(-1, textBoxTitle.getText(), textBoxDescription.getText(),
                        textBoxLocation.getText(), textBoxType.getText(), startTime.toInstant(), endTime.toInstant(), Instant.now(),
                        App.getCurrentUserName(), Instant.now(), App.getCurrentUserName(), comboBoxCustomer.getValue().getCustomerId(),
                        App.getCurrentUserId(), comboBoxContact.getValue().getContactId());
                if (DatabaseController.checkAppointmentConflict(newAppointment, newAppointment.getCustomer())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, resources.getString("conflictingAppointment"));
                    alert.showAndWait();
                }
                else if (checkIfAppointmentOutsideWorkHours(newAppointment)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, resources.getString("appointOutsideWorkHours"));
                    alert.showAndWait();
                }
                else {
                    DatabaseController.addAppointment(newAppointment);
                    Stage currentStage = (Stage) buttonSave.getScene().getWindow();
                    appointment = null;
                    currentStage.close();
                }

            }
            else { // Update the existing appointment.
                appointment.setTitle(textBoxTitle.getText());
                appointment.setDescription(textBoxDescription.getText());
                appointment.setLocation(textBoxLocation.getText());
                appointment.setType(textBoxType.getText());
                appointment.setStartDate(startTime.toInstant());
                appointment.setEndDate(endTime.toInstant());
                appointment.setCustomerId(comboBoxCustomer.getValue().getCustomerId());
                appointment.setContactId(comboBoxContact.getValue().getContactId());
                if (DatabaseController.checkAppointmentConflict(appointment, appointment.getCustomer())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, resources.getString("conflictingAppointment"));
                    alert.showAndWait();
                }
                else if (checkIfAppointmentOutsideWorkHours(appointment)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, resources.getString("appointOutsideWorkHours"));
                    alert.showAndWait();
                }
                else {
                    DatabaseController.updateAppointment(appointment);
                    Stage currentStage = (Stage) buttonSave.getScene().getWindow();
                    appointment = null;
                    currentStage.close();
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Checks if an appointment is outside work hours
     *
     * @param app Appointment to check
     * @return True if the appointment is outside of work hours and false otherwise
     */
    public boolean checkIfAppointmentOutsideWorkHours(Appointment app) {
        ZonedDateTime startDate = ZonedDateTime.of(app.getStartDateTime().getValue(), ZoneId.of("America/New_York"));
        ZonedDateTime endDate = ZonedDateTime.of(app.getEndDateTime().getValue(), ZoneId.of("America/New_York"));
        if (startDate.getDayOfWeek() == DayOfWeek.SATURDAY || startDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return true;
        }
        if (endDate.getDayOfWeek() == DayOfWeek.SATURDAY || endDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return true;
        }
        if (startDate.getHour() < 8 || startDate.getHour() > 22) {
            return true;
        }
        return endDate.getHour() < 8 || endDate.getHour() > 22;
    }

    /**
     * Initialize the form.  Sets the data if this is an existing appointment and prefills the comboboxes.
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        amPm = FXCollections.observableList(new ArrayList<>());
        zones = FXCollections.observableList(new ArrayList<>());
        amPm.add("AM");
        amPm.add("PM");
        //Disable weekends on the date selectors
        datePickerStart.setDayCellFactory(callback -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.getDayOfWeek() == DayOfWeek.SATURDAY || item.getDayOfWeek() == DayOfWeek.SUNDAY)
                {
                    setDisable(true);
                }
            }
        });
        datePickerEnd.setDayCellFactory(callback -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.getDayOfWeek() == DayOfWeek.SATURDAY || item.getDayOfWeek() == DayOfWeek.SUNDAY)
                {
                    setDisable(true);
                }
            }
        });
        // Fill comboboxes and selectors with preset data
        comboBoxContact.setItems(DatabaseController.getAllContacts());
        comboBoxCustomer.setItems(DatabaseController.getAllCustomers());
        selectorStartHour.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12));
        selectorEndHours.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12));
        selectorStartMinutes.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        selectorEndMinutes.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        selectorStartAMPM.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<>(amPm));
        selectorEmdAMPM.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<>(amPm));
        for (String zone : ZoneId.getAvailableZoneIds())
        {
            zones.add(zone);
        }
        Collections.sort(zones);
        comboBoxTimeZone.setItems(zones);
        comboBoxTimeZone.getSelectionModel().select(ZoneId.systemDefault().getId());
        if (appointment != null) { //If this is editing an existing appointment, prefill the data
            textBoxID.setText(Integer.toString(appointment.getAppointmentID()));
            textBoxTitle.setText(appointment.getTitle());
            textBoxDescription.setText(appointment.getDescription());
            textBoxLocation.setText(appointment.getLocation());
            textBoxType.setText(appointment.getType());
            datePickerStart.setValue(appointment.getStartDateTime().getValue().toLocalDate());
            datePickerEnd.setValue(appointment.getEndDateTime().getValue().toLocalDate());
            int hour = appointment.getStartDateTime().getValue().getHour();
            if (hour > 12) { // changing the hour to 12 hour format
                selectorStartAMPM.getValueFactory().setValue("PM");
                hour = hour - 12;
            }
            else {
                selectorStartAMPM.getValueFactory().setValue("AM");
            }
            selectorStartHour.getValueFactory().setValue(hour);
            selectorStartMinutes.getValueFactory().setValue(appointment.getStartDateTime().getValue().getMinute());
            hour = appointment.getEndDateTime().getValue().getHour();
            if (hour > 12) { // changing the hour to 12 hour format
                selectorEmdAMPM.getValueFactory().setValue("PM");
                hour = hour - 12;
            }
            else {
                selectorEmdAMPM.getValueFactory().setValue("AM");
            }
            selectorEndHours.getValueFactory().setValue(hour);
            selectorEndMinutes.getValueFactory().setValue(appointment.getEndDateTime().getValue().getMinute());
            comboBoxCustomer.getSelectionModel().select(appointment.getCustomer());
            comboBoxContact.getSelectionModel().select(appointment.getContact());
        }
    }

}
