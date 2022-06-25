package com.c195.datamodels;

import java.sql.Date;
import java.time.Instant;

public class Division {
    private final int divisionId;
    private String name;
    private Instant createDate;
    private String createdBy;
    private Instant lastUpdateDate;
    private String lastUpdatedBy;
    private int countryId;
    private Country country;

    private Division() {
        divisionId = -1;
    }

    public Division(String name){
        this.divisionId = -1;
        this.name = name;
    }

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

    public int getDivisionId() {
        return divisionId;
    }

    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
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

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public Country getCountry() {
        return country;
    }
}

