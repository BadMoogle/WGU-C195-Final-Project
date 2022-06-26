package com.c195.datamodels;

import java.time.Instant;

/**
 * Division object representing a database division
 */
public class Division {
    private final int divisionId; // ID of the division
    private String name; // Name of the division
    private Instant createDate; // Date the division was created
    private String createdBy; // Username of the created division
    private Instant lastUpdateDate; // Date the division was last updated
    private String lastUpdatedBy; // Username of the last updated division
    private int countryId; // CountryID associated with the division
    private Country country; // Country object derived from the divisionID

    /**
     * Provate constructor to prevent instantiation without certain fields.
     */
    private Division() {
        divisionId = -1;
    }


    /**
     * Fully parameterized constructor
     *
     * @param divisionId ID of the division
     * @param name Name of the division
     * @param createDate Date the division was created
     * @param createdBy Username of the created division
     * @param lastUpdateDate Date the division was last updated
     * @param lastUpdatedBy Username of the user who last updated the division
     * @param countryId CountryID associated with the division
     */
    public Division(int divisionId, String name, Instant createDate, String createdBy, Instant lastUpdateDate, String lastUpdatedBy, int countryId) {
        this.divisionId = divisionId;
        this.setName(name);
        this.setCreateDate(createDate);
        this.setCreatedBy(createdBy);
        this.setLastUpdateDate(lastUpdateDate);
        this.setLastUpdatedBy(lastUpdatedBy);
        this.setCountryId(countryId);
        this.country = DatabaseController.getCountryById(countryId);
    }

    /**
     * Copy constructor
     *
     * @param division Object to copy
     */
    public Division(Division division) {
        this.divisionId = division.getDivisionId();
        this.setName(division.getName());
        this.setCreateDate(division.getCreateDate());
        this.setCreatedBy(division.getCreatedBy());
        this.setLastUpdateDate(division.getLastUpdateDate());
        this.setLastUpdatedBy(division.getLastUpdatedBy());
        this.setCountryId(division.getCountryId());
        this.country = DatabaseController.getCountryById(countryId);
    }

    /**
     * Returns the divisionID associated with this division
     *
     * @return The divisionID associated with this division
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Returns the name associated with this division. Used for table and combobox displays.
     *
     * @return The name of the division
     */
    public String toString() {
        return name;
    }

    /**
     * Getter to return the name associated with this division
     *
     * @return The name of the division
     */
    public String getName() {
        return name;
    }

    /**
     * Setter to set the name associated with this division
     *
     * @param name name to set on the division
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the instant the division is created
     *
     * @return The Instant the division was created
     */
    public Instant getCreateDate() {
        return createDate;
    }

    /**
     * Setter for the instant the division is created
     *
     * @param createDate sets the instant the division is created
     */
    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    /**
     * Getter for the username of the user who created the division
     *
     * @return username of the user who created the division
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Setter for the username of the user who created the division
     *
     * @param createdBy sets the username of the user who created the division
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Getter for the Instant that the division was updated
     *
     * @return Returns the Instant the division was updated
     */
    public Instant getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * Sets the date that the division was updated
     *
     * @param lastUpdateDate Sets the date of the last update
     */
    public void setLastUpdateDate(Instant lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * Gets the username of the user who last updated the division
     *
     * @return the username of the user who last updated the division
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the username of the user who last updated the division
     *
     * @param lastUpdatedBy Sets the username of the user who last updated the division
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Gets the countryID associated with the division
     *
     * @return Gets the countryID associated with which the division
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Sets the countryID associated with the division.  Also updates the country object.
     *
     * @param countryId Sets the countryID associated with the division
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Gets the country object associated with the division
     *
     * @return gets the country object associated with the division
     */
    public Country getCountry() {
        return country;
    }
}

