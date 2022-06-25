/**
 * Sample Skeleton for 'ManageCustomersWindow.fxml' Controller Class
 */

package com.c195.windowcontrollers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.c195.App;
import com.c195.datamodels.Customer;
import com.c195.datamodels.DatabaseController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ManageCustomersWindowController {

    public ObservableList<Customer> customers;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="buttonAdd"
    private Button buttonAdd; // Value injected by FXMLLoader

    @FXML // fx:id="buttonClose"
    private Button buttonClose; // Value injected by FXMLLoader

    @FXML // fx:id="buttonDelete"
    private Button buttonDelete; // Value injected by FXMLLoader

    @FXML // fx:id="buttonEdit"
    private Button buttonEdit; // Value injected by FXMLLoader

    @FXML // fx:id="tableCustomerCustAddressColumn"
    private TableColumn<Customer, String> tableCustomerCustAddressColumn; // Value injected by FXMLLoader

    @FXML
    private TableColumn<Customer, Integer> tableCustomerCustIDColumn;

    @FXML // fx:id="tableCustomerCustDivisionColumn"
    private TableColumn<Customer, Integer> tableCustomerCustDivisionColumn; // Value injected by FXMLLoader

    @FXML // fx:id="tableCustomerCustNameColumn"
    private TableColumn<Customer, String> tableCustomerCustNameColumn; // Value injected by FXMLLoader

    @FXML // fx:id="tableCustomerCustPostalCodeColumn"
    private TableColumn<Customer, String> tableCustomerCustPostalCodeColumn; // Value injected by FXMLLoader

    @FXML // fx:id="tableCustomerCustPhoneColumn"
    private TableColumn<Customer, String> tableCustomerCustPhoneColumn; // Value injected by FXMLLoader

    @FXML // fx:id="tableViewCustomerList"
    private TableView<Customer> tableViewCustomerList; // Value injected by FXMLLoader


    @FXML
    void onButtonAddClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AddEditCustomerWindow.fxml"));
        ResourceBundle localizationBundle = ResourceBundle.getBundle("com.c195.localization", App.currentLocale);
        fxmlLoader.setResources(localizationBundle);
        Scene scene = new Scene(fxmlLoader.load(), 382, 314);
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        Stage currentStage = (Stage) buttonAdd.getScene().getWindow();
        stage.initOwner(currentStage );
        stage.setTitle(localizationBundle.getString("newCustomer"));
        stage.setScene(scene);
        stage.showAndWait();
        customers = DatabaseController.getAllCustomers();
        tableViewCustomerList.setItems(customers);
    }

    @FXML
    void onButtonCloseClick(ActionEvent event) {
        Stage currentStage = (Stage) buttonAdd.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void onButtonDeleteClick(ActionEvent event) {
        Customer customer = tableViewCustomerList.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ResourceBundle localizationBundle = ResourceBundle.getBundle("com.c195.localization", App.currentLocale);
        alert.setTitle(localizationBundle.getString("deleteCustomer"));
        alert.setContentText(localizationBundle.getString("deleteCustomer") + " " + customer + "?" );
        ButtonType yesButton = new ButtonType(localizationBundle.getString("yes"), ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType(localizationBundle.getString("no"), ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesButton, noButton);
        alert.showAndWait().ifPresent(type -> {
            if (type == yesButton) {
                try {
                    DatabaseController.deleteCustomer(customer);
                    customers.remove(customer);
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

    }

    @FXML
    void onButtonEditClick(ActionEvent event) throws IOException {
        AddEditCustomerWindowController.customer = tableViewCustomerList.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AddEditCustomerWindow.fxml"));
        ResourceBundle localizationBundle = ResourceBundle.getBundle("com.c195.localization", App.currentLocale);
        fxmlLoader.setResources(localizationBundle);
        Scene scene = new Scene(fxmlLoader.load(), 382, 314);
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        Stage currentStage = (Stage) buttonAdd.getScene().getWindow();
        stage.setTitle(localizationBundle.getString("edit") + " " + AddEditCustomerWindowController.customer);
        stage.initOwner(currentStage);
        stage.setScene(scene);
        stage.showAndWait();
        customers = DatabaseController.getAllCustomers();
        tableViewCustomerList.setItems(customers);
    }

    /**
     * initialize the scene.  Sets the datasource for the TableView.
     * The double lambda is needed.  The first to create the anonymous callback function.
     * The second lambda is there to create an anonymous event listener.
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        customers = DatabaseController.getAllCustomers();
        tableCustomerCustIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tableCustomerCustNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableCustomerCustAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        tableCustomerCustPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        tableCustomerCustPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tableCustomerCustDivisionColumn.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
        tableViewCustomerList.setItems(customers);
        // Lambda to create the callback function required to get a row level event listener
        tableViewCustomerList.setRowFactory(rowFactory -> {
            TableRow<Customer> row = new TableRow<>();

            // Lambdaception to create an anonymous event listener because we only use it here and nowhere else.
            row.setOnMouseClicked(mouseEvent -> {
                Customer customer = tableViewCustomerList.getSelectionModel().getSelectedItem();
                if (customer != null) {
                    buttonDelete.setDisable(false);
                    buttonEdit.setDisable(false);
                }
            });

            return row;
        });
        assert buttonAdd != null : "fx:id=\"buttonAdd\" was not injected: check your FXML file 'ManageCustomersWindow.fxml'.";
        assert buttonClose != null : "fx:id=\"buttonClose\" was not injected: check your FXML file 'ManageCustomersWindow.fxml'.";
        assert buttonDelete != null : "fx:id=\"buttonDelete\" was not injected: check your FXML file 'ManageCustomersWindow.fxml'.";
        assert buttonEdit != null : "fx:id=\"buttonEdit\" was not injected: check your FXML file 'ManageCustomersWindow.fxml'.";
        assert tableViewCustomerList != null : "fx:id=\"tableViewCustomerList\" was not injected: check your FXML file 'ManageCustomersWindow.fxml'.";

    }

}
