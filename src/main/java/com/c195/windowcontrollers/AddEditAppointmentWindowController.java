/**
 * Sample Skeleton for 'AddEditAppointmentWindow.fxml' Controller Class
 */

package com.c195.windowcontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import com.c195.datamodels.Appointment;
import com.c195.datamodels.Contact;
import com.c195.datamodels.Customer;
import com.c195.datamodels.DatabaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddEditAppointmentWindowController {

    public static Appointment appointment;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="buttonCancel"
    private Button buttonCancel; // Value injected by FXMLLoader

    @FXML // fx:id="buttonSave"
    private Button buttonSave; // Value injected by FXMLLoader

    @FXML // fx:id="comboBoxContact"
    private ComboBox<Contact> comboBoxContact; // Value injected by FXMLLoader

    @FXML // fx:id="comboBoxCustomer"
    private ComboBox<Customer> comboBoxCustomer; // Value injected by FXMLLoader

    @FXML // fx:id="textBoxDescription"
    private TextField textBoxDescription; // Value injected by FXMLLoader

    @FXML // fx:id="textBoxLocation"
    private TextField textBoxLocation; // Value injected by FXMLLoader

    @FXML // fx:id="textBoxTitle"
    private TextField textBoxTitle; // Value injected by FXMLLoader

    @FXML // fx:id="textBoxType"
    private TextField textBoxType; // Value injected by FXMLLoader

    @FXML
    void onButtonCancelClick(ActionEvent event) {
        appointment = null;
        Stage currentStage = (Stage) buttonCancel.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void onButtonSaveClick(ActionEvent event) {
        appointment = null;
        Stage currentStage = (Stage) buttonSave.getScene().getWindow();
        currentStage.close();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        comboBoxContact.setItems(DatabaseController.getAllContacts());
        comboBoxCustomer.setItems(DatabaseController.getAllCustomers());
        if (appointment != null) {
            textBoxTitle.setText(appointment.getTitle());
            textBoxDescription.setText(appointment.getDescription());
            textBoxLocation.setText(appointment.getLocation());
            textBoxType.setText(appointment.getType());
            comboBoxCustomer.getSelectionModel().select(appointment.getCustomer());
            comboBoxContact.getSelectionModel().select(appointment.getContact());
        }
        assert buttonCancel != null : "fx:id=\"buttonCancel\" was not injected: check your FXML file 'AddEditAppointmentWindow.fxml'.";
        assert buttonSave != null : "fx:id=\"buttonSave\" was not injected: check your FXML file 'AddEditAppointmentWindow.fxml'.";
        assert comboBoxContact != null : "fx:id=\"comboBoxContact\" was not injected: check your FXML file 'AddEditAppointmentWindow.fxml'.";
        assert comboBoxCustomer != null : "fx:id=\"comboBoxCustomer\" was not injected: check your FXML file 'AddEditAppointmentWindow.fxml'.";
        assert textBoxDescription != null : "fx:id=\"textBoxDescription\" was not injected: check your FXML file 'AddEditAppointmentWindow.fxml'.";
        assert textBoxLocation != null : "fx:id=\"textBoxLocation\" was not injected: check your FXML file 'AddEditAppointmentWindow.fxml'.";
        assert textBoxTitle != null : "fx:id=\"textBoxTitle\" was not injected: check your FXML file 'AddEditAppointmentWindow.fxml'.";
        assert textBoxType != null : "fx:id=\"textBoxType\" was not injected: check your FXML file 'AddEditAppointmentWindow.fxml'.";

    }

}
