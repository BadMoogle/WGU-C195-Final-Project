package com.c195.datamodels;

/**
 * Class used to display data in the reports.
 *
 */
public class AppointmentsByType {

    private String type; // Type of appointment
    private Integer number; // Total number of appointments of given type

    /**
     * Constructs a new instance of AppointmentsByType
     *
     * @param type Type of appointment
     * @param number Total number of appointment
     */
    public AppointmentsByType(String type, Integer number) {
        this.setType(type);
        this.setNumber(number);
    }

    /**
     * Gets the type of appointment
     *
     * @return Type of appointment
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of appointment
     *
     * @param type Type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the number of appointment of given type
     *
     * @return Number of appointments of a given type
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Gets the number of appointment of given type
     *
     * @param number Number of appointments of a given type
     */
    public void setNumber(Integer number) {
        this.number = number;
    }
}
