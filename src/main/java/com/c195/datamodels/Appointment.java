package com.c195.datamodels;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Appointment object representing an appointment with a customer
 */
public class Appointment {
    private final int appointmentID; // ID of the appointment
    private String description; // Description of the appointment
    private String title; // Title of the appointment
    private String location; // Location of the appointment
    private String type; // Type of appointment
    private Instant startDate; // Start date of the appointment
    private Instant endDate; // End date of the appointment
    private Instant createdByDate; // Date the appointment was created
    private String createdBy; // Username that created the appointment
    private Instant lastUpdateDate; // Instant the appointment was last updated
    private String lastUpdatedBy; // Username that last updated the appointment
    private int customerId; // CustomerID associated with the appointment
    private Customer customer; // Customer associated with the appointment
    private int userId; // UserID that created the appointment
    private int contactId; // Contact ID associated with the appointment
    private Contact contact; // Contact associated with the appointment

    /**
     * Private constructor to prevent instantiation without parameters
     */
    private Appointment() {
        appointmentID = -1;
    }

    /**
     * Basic constructor with preset ID.  -1 is a placeholder.
     *
     * @param appointmentID Appointment ID.  -1 is a placeholder
     */
    public Appointment(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * Fully parameterized constructor
     *
     * @param appointmentID AppointmentID of the appointment
     * @param title Title of the appointment
     * @param description Description of the appointment
     * @param location Location of the appointment
     * @param type Type of appointment
     * @param startDate Start date of the appointment
     * @param endDate End date of the appointment
     * @param createdByDate Date the appointment was created
     * @param createdBy Username who created the appointment
     * @param lastUpdateDate Date the appointment was last updated
     * @param lastUpdatedBy Username who last updated it
     * @param customerId CustomerID associated with the appointment
     * @param userId UserID who created the appointment
     * @param contactId ContactID associated with the appointment
     */
    public Appointment(int appointmentID, String title, String description, String location, String type,
                       Instant startDate, Instant endDate, Instant createdByDate, String createdBy, Instant lastUpdateDate,
                       String lastUpdatedBy, int customerId, int userId, int contactId) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdByDate = createdByDate;
        this.createdBy = createdBy;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        // Get the objects associated with the contact and customer ID.
        this.customer = DatabaseController.getCustomerById(customerId);
        this.contact = DatabaseController.getContactById(contactId);
    }

    /**
     * Copy constructor
     *
     * @param appointment Object to copy
     */
    public Appointment(Appointment appointment) {
        this.appointmentID = appointment.appointmentID;
        this.title = appointment.title;
        this.description = appointment.description;
        this.location = appointment.location;
        this.type = appointment.type;
        this.startDate = appointment.startDate;
        this.endDate = appointment.endDate;
        this.createdByDate = appointment.createdByDate;
        this.createdBy = appointment.createdBy;
        this.lastUpdateDate = appointment.lastUpdateDate;
        this.lastUpdatedBy = appointment.lastUpdatedBy;
        this.customerId = appointment.customerId;
        this.userId = appointment.userId;
        this.contactId = appointment.contactId;
        this.customer = DatabaseController.getCustomerById(customerId);
        this.contact = DatabaseController.getContactById(contactId);
    }

    /**
     * Returns the title of the appointment.  Used for display purposes.
     *
     * @return The title of the appointment
     */
    public String toString() {
        return title;
    }

    /**
     * Returns the appointmentID.  -1 is a placeholder.
     *
     * @return The appointmentID
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * Gets the title of the appointment.
     *
     * @return The title of the appointment
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the appointment.
     *
     * @param title Sets the title of the appointment
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Gets the location of the appointment.
     *
     * @return Gets the location of the appointment
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the appointment.
     *
     * @param location Location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the type of the appointment.
     *
     * @return Gets the type of the appointment
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the appointment.
     *
     * @param type Sets the type of the appointment
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets an Instant of the start date of the appointment.
     *
     * @return The Instant of the start date of the appointment
     */
    public Instant getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the appointment.
     *
     * @param startDate Sets the Instant of the start date of the appointment
     */
    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date of the appointment.
     *
     * @return Returns the Instant of the End date of the appointment
     */
    public Instant getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the appointment.
     *
     * @param endDate Sets the End date of the appointment
     */
    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the Instant of the object creation.
     *
     * @return Returns the Instant of the objection creation.
     */
    public Instant getCreatedByDate() {
        return createdByDate;
    }

    /**
     * Sets the created by date of the appointment
     *
     * @param createdByDate Sets the created by date
     */
    public void setCreatedByDate(Instant createdByDate) {
        this.createdByDate = createdByDate;
    }

    /**
     * Gets the username of the user who created the appointment
     *
     * @return Gets the username who created the appointment
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the username of the user who created the appointment
     *
     * @param createdBy Sets the username who created the appointment
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the Instant that the object was last updated.
     *
     * @return Gets the Instant that the object was last updated
     */
    public Instant getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * Sets the last update date of the appointment
     *
     * @param lastUpdateDate Sets the last update date.
     */
    public void setLastUpdateDate(Instant lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * Gets the customerID associated with the appointment
     *
     * @return Gets the customerID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customerID associated with the appointment.  Also updates the customer object.
     *
     * @param customerId Customer ID to associate with the appointment
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
        this.customer = DatabaseController.getCustomerById(customerId);
    }

    /**
     * Gets the userID associated with the appointment
     *
     * @return Gets the userID who created the appointment
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the userID associated with the appointment
     *
     * @param userId Sets the userID associated with the appointment
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the customerID associated with the appointment
     *
     * @return The contactID associated with the appointment
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Sets the contactID associated with the appointment
     *
     * @param contactId Sets the contactID associated with the appointment
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
        this.contact = DatabaseController.getContactById(contactId);
    }

    /**
     * Gets the description associated with the appointment.
     *
     * @return The Description of the appointment
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description associated with the appointment.
     *
     * @param description Description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the customer object associated with the appointment.
     *
     * @return The customer object associated with the appointment
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Gets a contact object derived from the contact ID associated with the object
     *
     * @return a Contact object
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Returns the local date and time of when the appointment starts
     *
     * @return A LocalDateTime of when the appointment starts
     */
    public ObjectProperty<LocalDateTime> getStartDateTime() {
        ObjectProperty<LocalDateTime> start = new SimpleObjectProperty<LocalDateTime>();
        start.setValue(LocalDateTime.ofInstant(startDate, ZoneId.systemDefault()));
        return start;
    }

    /**
     * Returns the date and time of when the appointment ends
     *
     * @return A LocalDateTime of when the appointment ends.
     */
    public ObjectProperty<LocalDateTime> getEndDateTime() {
        ObjectProperty<LocalDateTime> end = new SimpleObjectProperty<LocalDateTime>();
        end.setValue(LocalDateTime.ofInstant(endDate, ZoneId.systemDefault()));
        return end;    }

    /**
     * Returns the LocalDateTime of when the object was created
     *
     * @return ObjectProperty LocalDateTime of the created Date Time
     */
    public ObjectProperty<LocalDateTime> getCreatedByDateTime() {
        ObjectProperty<LocalDateTime> create = new SimpleObjectProperty<LocalDateTime>();
        create.setValue(LocalDateTime.ofInstant(createdByDate, ZoneId.systemDefault()));
        return create;
    }

    /**
     * Getter to get the LastUpdateDateTime
     *
     * @return LocalDateTime of the LastUpdateDateTime
     */
    public LocalDateTime getLastUpdateDateTime() {
        return LocalDateTime.ofInstant(lastUpdateDate, ZoneId.systemDefault());
    }
}
