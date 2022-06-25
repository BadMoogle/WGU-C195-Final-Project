package com.c195.datamodels;

import java.sql.Date;

public class Country {
    private final int countryId;
    private String countryName;
    private Date createDate;
    private String createdBy;
    private Date lastUpdateDate;
    private String lastUpdatedBy;

    private Country(){
        countryId = -1;
    }

    public Country(int countryId) {
        this.countryId = countryId;
    }

    public Country(int countryId, String countryName, Date createDate, String createdBy, Date lastUpdateDate, String lastUpdatedBy) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Country(Country country) {
        this.countryId = country.countryId;
        this.countryName = country.countryName;
        this.createDate = country.createDate;
        this.createdBy = country.createdBy;
        this.lastUpdateDate = country.lastUpdateDate;
        this.lastUpdatedBy = country.lastUpdatedBy;
    }

    public String toString() {
        return countryName;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
