package com.c195.datamodels;

import java.sql.Date;

/**
 * Customer class representing a customer
 */
public class Customer {
    private final Integer customerId; // Database ID of the customer
    private String name; // Full name of the customer
    private String  address; // Address of the customer
    private String  postalCode; // Postal code of the customer
    private String  phoneNumber; // Phone number of the customer
    private Date createDate; // Date of the customer created
    private String  createdByUsername; // User name of the user who created the customer
    private Date lastUpdateDate; // Date of the customer last updated
    private String  lastUpdatedByUsername; // User name of the user who last updated the customer
    private int divisionId; // ID of the division

    private Division division; // Division derived from the divisionID

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
     * Fully parameterized constructor of the customer
     *
     * @param customerId ID of the customer
     * @param name Full name of the customer
     * @param address Address of the customer
     * @param postalCode Postal code of the customer
     * @param phoneNumber Phone number of the customer
     * @param createDate Date the object was created
     * @param createdByUsername Username of the user who created the object
     * @param lastUpdateDate Date the object was last updated
     * @param lastUpdatedByUsername Username of the user who last updated the object
     * @param divisionId ID of the division associated with the customer
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
    }

    /**
     * Returns the full name of the customer.  Used for display purposes
     *
     * @return The full name of the customer
     */
    public String toString() {

        return "(ID:" + customerId.toString() + ") " +  name;
    }

    /**
     * Getter to return the ID of the customer.  -1 is used as a placeholder.
     *
     * @return the customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Getter to return the name of the customer.
     *
     * @return Returns the name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the customer
     *
     * @param name Name to set on the customer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the address of the customer
     *
     * @return The address of the customer
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the customer
     *
     * @param address Address to set on the customer
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the postal code of the customer
     *
     * @return the postal code of the customer
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code of the customer
     *
     * @param postalCode Sets the postal code of the customer
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets the postal code of the customer
     *
     * @return returns the postal code of the customer
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the customer
     *
     * @param phoneNumber sets the phone number of the customer
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the date the customer was created
     *
     * @return Returns the date the customer was created
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Sets the date the customer was created
     *
     * @param createDate Sets the date the customer was created
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the username of the user who created the customer
     *
     * @return Gets the username of the user who created the customer
     */
    public String getCreatedByUsername() {
        return createdByUsername;
    }

    /**
     * Sets the username of the user who created the customer
     *
     * @param createdByUsername sets the username of the user who created the customer
     */
    public void setCreatedByUsername(String createdByUsername) {
        this.createdByUsername = createdByUsername;
    }

    /**
     * Gets the date the customer was last updated
     *
     * @return Gets the date the object was last updated
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * Sets the date the customer was last updated
     *
     * @param lastUpdateDate Sets the date the customer was last updated
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * Gets the username of the user who last updated the customer
     *
     * @return gets the username of the user who last updated the customer
     */
    public String getLastUpdatedByUsername() {
        return lastUpdatedByUsername;
    }

    /**
     * Sets the username of the user who last updated the customer
     *
     * @param lastUpdatedByUsername sets the username of the user who last updated the customer
     */
    public void setLastUpdatedByUsername(String lastUpdatedByUsername) {
        this.lastUpdatedByUsername = lastUpdatedByUsername;
    }

    /**
     * Gets the division ID associated with the customer
     *
     * @return the divisionID associated with the customer
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Sets the division ID associated with the customer.  Also updates the division object.
     *
     * @param divisionId Sets the divisionID associated with the customer
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
        this.division = DatabaseController.getDivisionById(divisionId);
    }

    /**
     * Gets a division object associated with the customer
     *
     * @return A division object derived from divisionID
     */
    public Division getDivision() {
        return division;
    }
}
