package com.c195.datamodels;

import java.sql.Date;

/**
 * Customer class representing a customer
 */
public class Customer {
    private final Integer customerId;
    private String name;
    private String  address;
    private String  postalCode;
    private String  phoneNumber;
    private Date createDate;
    private String  createdByUsername;
    private Date lastUpdateDate;
    private String  lastUpdatedByUsername;
    private int divisionId;

    private Division division;
    private String divisionName;

    /**
     * Customer constructor with no parameters.  Private to force set a customer ID.
     */
    private Customer(){
        customerId = -1;
    }

    /**
     * Customer constructor with a preset customer id.
     * @param customerId customer identifier.  Readonly after set.
     */
    public Customer(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @param customerId
     * @param name
     * @param address
     * @param postalCode
     * @param phoneNumber
     * @param createDate
     * @param createdByUsername
     * @param lastUpdateDate
     * @param lastUpdatedByUsername
     * @param divisionId
     */
    public Customer(int customerId, String name, String address, String postalCode, String phoneNumber, Date createDate,
                    String createdByUsername, Date lastUpdateDate, String lastUpdatedByUsername, int divisionId) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.createDate = createDate;
        this.createdByUsername = createdByUsername;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatedByUsername = lastUpdatedByUsername;
        this.divisionId = divisionId;
        this.division = DatabaseController.getDivisionById(divisionId);
        if (this.division != null) {
            this.divisionName = division.getName();
        }
    }

    /**
     * Copy constructor for Customer object
     * @param customer Customer to be copied
     */
    public Customer(Customer customer) {
        this.customerId = customer.customerId;
        this.name = customer.name;
        this.address = customer.address;
        this.postalCode = customer.postalCode;
        this.phoneNumber = customer.phoneNumber;
        this.createDate = customer.createDate;
        this.createdByUsername = customer.createdByUsername;
        this.lastUpdateDate = customer.lastUpdateDate;
        this.lastUpdatedByUsername = customer.lastUpdatedByUsername;
        this.divisionId = customer.divisionId;
        this.division = DatabaseController.getDivisionById(divisionId);
        if (this.division != null) {
            this.divisionName = division.getName();
        }
    }

    public String toString() {
        return name;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreatedByUsername() {
        return createdByUsername;
    }

    public void setCreatedByUsername(String createdByUsername) {
        this.createdByUsername = createdByUsername;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdatedByUsername() {
        return lastUpdatedByUsername;
    }

    public void setLastUpdatedByUsername(String lastUpdatedByUsername) {
        this.lastUpdatedByUsername = lastUpdatedByUsername;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
        this.division = DatabaseController.getDivisionById(divisionId);
    }

    public Division getDivision() {
        return division;
    }

    public String getDivisionName() {
        return divisionName;
    }
}
