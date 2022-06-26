package com.c195.datamodels;

import java.sql.Date;

/**
 * Object representing a country in the database.
 */
public class Country {
    private final int countryId; // Database ID of the country
    private String countryName; // Country name
    private Date createDate; // Date object created
    private String createdBy; // username of who created the object
    private Date lastUpdateDate; // Date the object was last updated
    private String lastUpdatedBy; // username of who last updated the object

    /**
     * Private constructor to prevent instantiation
     */
    private Country(){
        countryId = -1;
    }

    /**
     * Basic constructor allowing only the ID to be provided
     *
     * @param countryId ID of the country
     */
    public Country(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Fully parameterized constructor
     *
     * @param countryId  ID of the country
     * @param countryName Name of the country
     * @param createDate Object created date
     * @param createdBy Username of the created object
     * @param lastUpdateDate Date of last update object
     * @param lastUpdatedBy Username of the last updated object
     */
    public Country(int countryId, String countryName, Date createDate, String createdBy, Date lastUpdateDate, String lastUpdatedBy) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Copy constructor
     *
     * @param country Country to be copied
     */
    public Country(Country country) {
        this.countryId = country.countryId;
        this.countryName = country.countryName;
        this.createDate = country.createDate;
        this.createdBy = country.createdBy;
        this.lastUpdateDate = country.lastUpdateDate;
        this.lastUpdatedBy = country.lastUpdatedBy;
    }

    /**
     * A toString method returning the name.  Used mostly for visualization
     *
     * @return the country name
     */
    public String toString() {
        return countryName;
    }

    /**
     * A getter returning the country ID
     *
     * @return the ID of the country
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * A getter returning the country name
     *
     * @return The country Name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * A setter that sets the country name
     *
     * @param countryName Sets the country name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Returns the date the country was created
     *
     * @return The Date the object was created
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Sets the date the country was created
     *
     * @param createDate sets the date the object was created
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the username of the user who created the country
     *
     * @return username of the user who created the country
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the date the country was created in the database
     *
     * @param createdBy Sets the date the country was created in the database
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Returns the date the country was last updated in the database
     *
     * @return The date the country was last updated
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * Sets the date the country was last updated in the database
     *
     * @param lastUpdateDate Sets the date the country was last updated in the database
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * Gets the username of the user who last updated the country
     *
     * @return The user name of the user who last updated the object
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the username of the user who last updated the country
     *
     * @param lastUpdatedBy Sets the user name of the user who last updated the object
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
