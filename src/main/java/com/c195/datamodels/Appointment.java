package com.c195.datamodels;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Appointment {
    private final int appointmentID;
    private String description;
    private String title;
    private String location;
    private String type;
    private Instant startDate;
    private Instant endDate;
    private Instant createdByDate;
    private String createdBy;
    private Instant lastUpdateDate;
    private String lastUpdatedBy;
    private int customerId;
    private Customer customer;
    private int userId;
    private int contactId;
    private Contact contact;

    private Appointment() {
        appointmentID = -1;
    }

    public Appointment(int appointmentID) {
        this.appointmentID = appointmentID;
    }

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
        this.customer = DatabaseController.getCustomerById(customerId);
        this.contact = DatabaseController.getContactById(contactId);
    }

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

    public String toString() {
        return title;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Instant getCreatedByDate() {
        return createdByDate;
    }

    public void setCreatedByDate(Instant createdByDate) {
        this.createdByDate = createdByDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Instant lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
        this.customer = DatabaseController.getCustomerById(customerId);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
        this.contact = DatabaseController.getContactById(contactId);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Contact getContact() {
        return contact;
    }

    public ObjectProperty<LocalDateTime> getStartDateTime() {
        ObjectProperty<LocalDateTime> start = new SimpleObjectProperty<LocalDateTime>();
        start.setValue(LocalDateTime.ofInstant(startDate, ZoneId.systemDefault()));
        return start;
    }

    public ObjectProperty<LocalDateTime> getEndDateTime() {
        ObjectProperty<LocalDateTime> end = new SimpleObjectProperty<LocalDateTime>();
        end.setValue(LocalDateTime.ofInstant(endDate, ZoneId.systemDefault()));
        return end;    }

    public ObjectProperty<LocalDateTime> getCreatedByDateTime() {
        ObjectProperty<LocalDateTime> create = new SimpleObjectProperty<LocalDateTime>();
        create.setValue(LocalDateTime.ofInstant(createdByDate, ZoneId.systemDefault()));
        return create;
    }

    public LocalDateTime getLastUpdateDateTime() {
        return LocalDateTime.ofInstant(lastUpdateDate, ZoneId.systemDefault());
    }
}
