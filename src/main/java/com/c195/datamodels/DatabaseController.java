package com.c195.datamodels;

import com.c195.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.*;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Database controller.  Used for any calls to the database.
 */
public class DatabaseController {
    //DB Connection information.
    private static final String dbConnectionString = "jdbc:mysql://192.168.1.2:3306/client_schedule?serverTimezone=UTC";
    private static final String dbConnectionUser = "sqlUser";
    private static final String dbConnectionPassword = "Passw0rd!";

    /**
     * Adds a new appointment to the database.
     *
     * @param appointment Appointment to add
     */
    public static void addAppointment(Appointment appointment) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO appointments (Title, " +
                    "Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, " +
                    "Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);"); // Using a PreparedStatement to avoid injection
            ps.setString(1, appointment.getTitle());
            ps.setString(2, appointment.getDescription());
            ps.setString(3, appointment.getLocation());
            ps.setString(4, appointment.getType());
            ps.setTimestamp(5, Timestamp.from(appointment.getStartDate()), Calendar.getInstance(TimeZone.getTimeZone("UTC")));
            ps.setTimestamp(6, Timestamp.from(appointment.getEndDate()), Calendar.getInstance(TimeZone.getTimeZone("UTC")));
            ps.setTimestamp(7, Timestamp.from(Instant.now()), Calendar.getInstance(TimeZone.getTimeZone("UTC")));
            ps.setString(8, App.getCurrentUserName());
            ps.setTimestamp(9, Timestamp.from(Instant.now()), Calendar.getInstance(TimeZone.getTimeZone("UTC")));
            ps.setString(10, App.getCurrentUserName());
            ps.setInt(11, appointment.getCustomerId());
            ps.setInt(12, appointment.getUserId());
            ps.setInt(13, appointment.getContactId());
            ps.execute();
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Adds a new contact to the database.
     *
     * @param contact Contact to add to the database
     */
    public static void addContact(Contact contact) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO contacts (" +
                    "Contact_Name, Email) VALUES (?,?);"); // Using a PreparedStatement to avoid injection
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getEmail());
            ps.execute();
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Adds a new customer to the database.
     *
     * @param customer Customer to add to the database
     */
    public static void addCustomer(Customer customer) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO customers (Customer_Name, " +
                    "Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, " +
                    "Division_ID) VALUES (?,?,?,?,?,?,?,?,?);"); // Using a PreparedStatement to avoid injection
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getPostalCode());
            ps.setString(4, customer.getPhoneNumber());
            ps.setDate(5, new Date(System.currentTimeMillis()));
            ps.setString(6, App.getCurrentUserName());
            ps.setDate(7,  new Date(System.currentTimeMillis()));
            ps.setString(8, App.getCurrentUserName());
            ps.setInt(9, customer.getDivisionId());
            ps.execute();
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Adds a new division to the database
     *
     * @param division Division to add to the database
     */
    public static void addDivision(Division division) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO first_level_divisions (Division, " +
                    "Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES (?,?,?,?,?,?);"); // Using a PreparedStatement to avoid injection
            ps.setString(1, division.getName());
            ps.setDate(2, new Date(System.currentTimeMillis()));
            ps.setString(3, App.getCurrentUserName());
            ps.setDate(4, new Date(System.currentTimeMillis()));
            ps.setString(5, App.getCurrentUserName());
            ps.setInt(6, division.getCountryId());
            ps.execute();
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Checks if there is a conflict in an appointment with one in the database.
     *
     * @param appointment Appointment to check against existing in the database
     * @param customer Customer to check
     * @return True if there is a conflict, false if not
     */
    public static boolean checkAppointmentConflict(Appointment appointment, Customer customer) {
        ObservableList<Appointment> appointmentList = getAppointmentByCustomerId(customer.getCustomerId());
        boolean hasConflict = false;
        for (Appointment forAppointment : appointmentList) {
            if (forAppointment.getAppointmentID() != appointment.getAppointmentID()) {
                // Put into local variables for my sanity
                LocalDateTime forAppStartDate = forAppointment.getStartDateTime().getValue();
                LocalDateTime custAppStartDate = appointment.getStartDateTime().getValue();
                LocalDateTime forAppEndDate = forAppointment.getEndDateTime().getValue();
                LocalDateTime custAppEndDate = appointment.getEndDateTime().getValue();
                if (forAppStartDate.isBefore(custAppStartDate) && forAppEndDate.isAfter(custAppStartDate)) {
                    hasConflict = true;
                } else if (forAppEndDate.isAfter(custAppStartDate) && forAppEndDate.isBefore(custAppEndDate)) {
                    hasConflict = true;
                }
            }
        }
        return hasConflict;
    }

    /**
     * Deletes an appointment from the database
     *
     * @param appointment Appointment to delete from the database
     */
    public static void deleteAppointment(Appointment appointment) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            // Remove any appointments for the customer
            PreparedStatement ps = connection.prepareStatement("DELETE FROM appointments WHERE Appointment_ID=?;"); // Using a PreparedStatement to avoid injection
            ps.setInt(1, appointment.getAppointmentID());
            ps.execute();
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Deletes a contact from the database
     *
     * @param contact Contact to delete from the database
     */
    public static void deleteContact(Contact contact)  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            // Remove any appointments for the customer
            PreparedStatement ps = connection.prepareStatement("DELETE FROM contacts WHERE Contact_ID=?;"); // Using a PreparedStatement to avoid injection
            ps.setInt(1, contact.getContactId());
            ps.execute();
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Deletes a customer and all appointments associated that customer from the database.
     *
     * @param customer Customer to delete from the database
     */
    public static void deleteCustomer(Customer customer) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            // Remove any appointments for the customer
            PreparedStatement ps = connection.prepareStatement("DELETE FROM appointments WHERE Customer_ID=?;"); // Using a PreparedStatement to avoid injection
            ps.setInt(1, customer.getCustomerId());
            ps.execute();
            // Delete the customer from the database
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM customers WHERE Customer_ID=?;"); // Using a PreparedStatement to avoid injection
            ps1.setInt(1, customer.getCustomerId());
            ps1.execute();
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Gets all the appointments for the contact
     *
     * @param contactId ContactId to get appointments for.
     * @return An ObservableList of appointments
     */
    public static ObservableList<Appointment> getAppointmentByContactId(int contactId) {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            PreparedStatement ps = connection.prepareStatement("select * from appointments where Contact_ID=?;"); // Using a PreparedStatement to avoid injection
            ps.setInt(1, contactId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                appointmentList.add(new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        rs.getTimestamp("Start", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getTimestamp("End", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getTimestamp("Create_Date", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getString("Created_By"),
                        rs.getTimestamp("Last_Update", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID")));
            }
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return appointmentList;
    }

    /**
     * Gets all the appointments associated with the specified customerID
     *
     * @param customerId CustomerID to get the appointment's of
     * @return an ObservableList of appointments associated with the specified customerID
     */
    public static ObservableList<Appointment> getAppointmentByCustomerId(int customerId) {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            PreparedStatement ps = connection.prepareStatement("select * from appointments where Customer_ID=?;"); // Using a PreparedStatement to avoid injection
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                appointmentList.add(new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        rs.getTimestamp("Start", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getTimestamp("End", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getTimestamp("Create_Date", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getString("Created_By"),
                        rs.getTimestamp("Last_Update", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID")));
            }
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return appointmentList;
    }

    /**
     * Gets all the appointments in the database
     *
     * @return An ObservableList Appointment  of all appointments in the database
     */
    public static ObservableList<Appointment> getAllAppointments() {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            PreparedStatement ps = connection.prepareStatement("select * from appointments;"); // Using a PreparedStatement to avoid injection
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                appointmentList.add(new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        rs.getTimestamp("Start", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getTimestamp("End", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getTimestamp("Create_Date", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getString("Created_By"),
                        rs.getTimestamp("Last_Update", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID")));
            }
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return appointmentList;
    }

    /**
     * Returns all the appointments starting in the last 15 minutes.
     *
     * @return An ObservableList Appointment  of all appointments in the last 15 minutes.
     */
    public static ObservableList<Appointment> getAppointmentsNearNow() {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM appointments WHERE " +
                    "Start BETWEEN ? AND ?;"); // Using a PreparedStatement to avoid injection
            OffsetDateTime odt = Instant.now().atOffset( ZoneOffset.UTC ) ;
            ps.setObject(1, odt);
            ps.setObject(2, odt.plusMinutes(15));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                appointmentList.add(new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        rs.getTimestamp("Start", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getTimestamp("End", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getTimestamp("Create_Date", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getString("Created_By"),
                        rs.getTimestamp("Last_Update", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID")));
            }
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return appointmentList;
    }

    /**
     * Returns all the appointments between the two dates.
     *
     * @param start Start date to check
     * @param end End date to check
     * @return An ObservableList Appointment  of all the appointments between the start date and the end date
     */
    public static ObservableList<Appointment> getAppointmentsBetweenTwoDates(LocalDate start, LocalDate end) {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM appointments WHERE " +
                    "Start BETWEEN ? AND ?;"); // Using a PreparedStatement to avoid injection
            ps.setDate(1, Date.valueOf(LocalDate.of(start.getYear(), start.getMonth(), start.getDayOfMonth())));
            ps.setDate(2, Date.valueOf(LocalDate.of(end.getYear(), end.getMonth().getValue(), end.getDayOfMonth())));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                appointmentList.add(new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        rs.getTimestamp("Start", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getTimestamp("End", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getTimestamp("Create_Date", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getString("Created_By"),
                        rs.getTimestamp("Last_Update", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID")));
            }
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return appointmentList;
    }

    /**
     * Checks for appointments in the given month and year.
     *
     * @param year Year to check
     * @param month Month to check
     * @return An ObservableList of Appointments
     */
    public static ObservableList<Appointment> getAppointmentsByMonth(int year, Month month) {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM appointments WHERE " +
                    "Start BETWEEN ? AND ?;"); // Using a PreparedStatement to avoid injection
            ps.setDate(1, Date.valueOf(LocalDate.of(year, month.getValue(), 1)));
            ps.setDate(2, Date.valueOf(LocalDate.of(year, month.getValue(), month.maxLength())));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                appointmentList.add(new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        rs.getTimestamp("Start", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getTimestamp("End", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getTimestamp("Create_Date", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getString("Created_By"),
                        rs.getTimestamp("Last_Update", Calendar.getInstance(TimeZone.getTimeZone("UTC"))).toInstant(),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID"))
                );
            }
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return appointmentList;
    }

    /**
     * Gets a contact from the database based on the ID.
     *
     * @param id ID to get the contact
     * @return a contact object if found
     */
    public static Contact getContactById(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            PreparedStatement ps = connection.prepareStatement("select * from contacts where Contact_ID=?;"); // Using a PreparedStatement to avoid injection
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contact contact = new Contact(rs.getInt("Contact_ID"), rs.getString("Contact_Name"),
                        rs.getString("Email"));
                connection.close();
                return contact;
            }
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Gets all the contacts from the database.
     *
     * @return An ObservableList Contact of all contacts
     */
    public static ObservableList<Contact> getAllContacts() {
        ObservableList<Contact> contactList = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            PreparedStatement ps = connection.prepareStatement("select * from contacts;"); // Using a PreparedStatement to avoid injection
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                contactList.add(new Contact(
                        rs.getInt("Contact_ID"),
                        rs.getString("Contact_Name"),
                        rs.getString("Email")));
            }
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return contactList;
    }

    /**
     * Gets the Country object based on the ID
     *
     * @param id Id of the country to look up in the database
     * @return A country object if found.
     */
    public static Country getCountryById(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            PreparedStatement ps = connection.prepareStatement("select * from countries where Country_ID=?;"); // Using a PreparedStatement to avoid injection
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Country country = new Country(
                        rs.getInt("Country_ID"),
                        rs.getString("Country"),
                        rs.getDate("Create_Date"),
                        rs.getString("Created_By"),
                        rs.getDate("Last_Update"),
                        rs.getString("Last_Updated_By"));
                connection.close();
                return country;
            }
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Gets all Countries in the database
     *
     * @return An ObservableList Country of all countries in the database
     */
    public static ObservableList<Country> getAllCountries() {
        ObservableList<Country> countryList = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            String query = "select * from countries;";
            PreparedStatement ps = connection.prepareStatement(query); // Using a PreparedStatement to avoid injection
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                countryList.add(new Country(
                        rs.getInt("Country_ID"),
                        rs.getString("Country"),
                        rs.getDate("Create_Date"),
                        rs.getString("Created_By"),
                        rs.getDate("Last_Update"),
                        rs.getString("Last_Updated_By")));
            }
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return countryList;
    }

    /**
     * Gets a customer from the database based on ID
     *
     * @param id ID of the customer to lookup in the database
     * @return A customer object if found
     */
    public static Customer getCustomerById(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            PreparedStatement ps = connection.prepareStatement("select * from customers where Customer_ID=?;"); // Using a PreparedStatement to avoid injection
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"),
                        rs.getString("Address"),
                        rs.getString("Postal_Code"),
                        rs.getString("Phone"),
                        rs.getDate("Create_Date"),
                        rs.getString("Created_By"),
                        rs.getDate("Last_Update"),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Division_ID"));
                connection.close();
                return customer;
            }
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Gets all the customers in the database
     *
     * @return An ObservableList Customer  of all customers in the database
     */
    public static ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            PreparedStatement ps = connection.prepareStatement("select * from customers;"); // Using a PreparedStatement to avoid injection
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customerList.add(new Customer(
                        rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"),
                        rs.getString("Address"),
                        rs.getString("Postal_Code"),
                        rs.getString("Phone"),
                        rs.getDate("Create_Date"),
                        rs.getString("Created_By"),
                        rs.getDate("Last_Update"),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Division_ID")));
            }
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return customerList;
    }

    /**
     * Gets a division based on the ID.
     *
     * @param id ID of the division to lookup.
     * @return The division object if found
     */
    public static Division getDivisionById(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            PreparedStatement ps = connection.prepareStatement("select * from first_level_divisions " +
                    "where Division_ID=?;"); // Using a PreparedStatement to avoid injection
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Division division = new Division(
                        rs.getInt("Division_ID"),
                        rs.getString("Division"),
                        rs.getTimestamp("Create_Date").toInstant(),
                        rs.getString("Created_By"),
                        rs.getTimestamp("Last_Update").toInstant(),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("COUNTRY_ID"));
                connection.close();
                return division;
            }
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Gets all the divisions for a specified country.
     *
     * @param id Country ID to fetch all the divisions for.
     * @return An ObservableList Division  of all the divisions for the specified country
     */
    public static ObservableList<Division> getDivisionsByCountryId(int id) {
        ObservableList<Division> divisionList = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            PreparedStatement ps = connection.prepareStatement("select * from first_level_divisions WHERE COUNTRY_ID=?;"); // Using a PreparedStatement to avoid injection
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                divisionList.add(new Division(
                        rs.getInt("Division_ID"),
                        rs.getString("Division"),
                        rs.getTimestamp("Create_Date").toInstant(),
                        rs.getString("Created_By"),
                        rs.getTimestamp("Last_Update").toInstant(),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("COUNTRY_ID"))
                );
            }
            connection.close();
            return divisionList;
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Gets all the divisions in the database.
     *
     * @return An ObservableList Division  of all divisions in the database.
     */
    public static ObservableList<Division> getAllDivisions() {
        ObservableList<Division> divisionList = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            PreparedStatement ps = connection.prepareStatement("select * from first_level_divisions;"); // Using a PreparedStatement to avoid injection
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                divisionList.add(new Division(
                        rs.getInt("Division_ID"),
                        rs.getString("Division"),
                        rs.getTimestamp("Create_Date").toInstant(),
                        rs.getString("Created_By"),
                        rs.getTimestamp("Last_Update").toInstant(),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("COUNTRY_ID")));
            }
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return divisionList;
    }

    /**
     * Updates an appointment in the database.
     *
     * @param appointment Appointment to update
     */
    public static void updateAppointment(Appointment appointment) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            PreparedStatement ps = connection.prepareStatement("UPDATE appointments SET Title=?, " +
                    "Description=?, Location=?, Type=?, Start=?, End=?, Last_Update=?, " +
                    "Last_Updated_By=?, Customer_ID=?, User_ID=?, Contact_ID=? WHERE Appointment_ID=?;"); // Using a PreparedStatement to avoid injection
            ps.setString(1, appointment.getTitle());
            ps.setString(2, appointment.getDescription());
            ps.setString(3, appointment.getLocation());
            ps.setString(4, appointment.getType());
            ps.setTimestamp(5, Timestamp.from(appointment.getStartDate()), Calendar.getInstance(TimeZone.getTimeZone("UTC")));
            ps.setTimestamp(6, Timestamp.from(appointment.getEndDate()), Calendar.getInstance(TimeZone.getTimeZone("UTC")));
            ps.setTimestamp(7, Timestamp.from(Instant.now()), Calendar.getInstance(TimeZone.getTimeZone("UTC")));
            ps.setString(8, App.getCurrentUserName());
            ps.setInt(9, appointment.getCustomerId());
            ps.setInt(10, appointment.getUserId());
            ps.setInt(11, appointment.getContactId());
            ps.setInt(12, appointment.getAppointmentID());
            ps.execute();
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Updates a contact in the database.
     *
     * @param contact Contact to update in the database
     */
    public static void updateContact(Contact contact) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            PreparedStatement ps = connection.prepareStatement("UPDATE contacts SET" +
                    " Contact_Name=?, Email=? WHERE Contact_ID=?;"); // Using a PreparedStatement to avoid injection
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getEmail());
            ps.setInt(3, contact.getContactId());
            ps.execute();
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Updates an existing customer object in the database
     *
     * @param customer Customer to update in the database
     */
    public static void updateCustomer(Customer customer) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString,dbConnectionUser,dbConnectionPassword);
            PreparedStatement ps = connection.prepareStatement("UPDATE customers SET Customer_Name=?, " +
                    "Address=?, Postal_Code=?, Phone=?, Last_Update=?, Last_Updated_By=?, " +
                    "Division_ID=? WHERE Customer_ID=?;"); // Using a PreparedStatement to avoid injection
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getPostalCode());
            ps.setString(4, customer.getPhoneNumber());
            ps.setDate(5,  new Date(System.currentTimeMillis()));
            ps.setString(6, App.getCurrentUserName());
            ps.setInt(7, customer.getDivisionId());
            ps.setInt(8, customer.getCustomerId());
            ps.execute();
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Validates the login to the database.  If it succeeds, it sets the username and ID for the application.
     *
     * @param userName Username to test
     * @param password Password to test
     * @return True if successful.
     */
    public static boolean validateLogin(String userName, String password) {
        try {
            Logger logger = Logger.getLogger("C195");
            logger.setUseParentHandlers(false);
            FileHandler fileHandler = new FileHandler("login_activity.txt", 999999999, 1, true);
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            boolean isAuthenticated = false;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    dbConnectionString, dbConnectionUser, dbConnectionPassword);
            String query = "select User_ID, User_Name, Password from users where User_Name=? and Password=?;";
            PreparedStatement ps = connection.prepareStatement(query); // Using a PreparedStatement to avoid injection
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { // If the SQL server returns a result, then there is a user/password record in the database.
                isAuthenticated = true;
                App.setCurrentUserName(userName);
                App.setCurrentUserId(rs.getInt("User_ID"));
                logger.info("User '" + userName + "' (User ID: " + App.getCurrentUserId() +
                        ") successfully logged on.");
            } else {
                logger.severe("User '" + userName + "' had a failed login attempt.");
            }
            fileHandler.close();
            connection.close();
            return isAuthenticated;
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
