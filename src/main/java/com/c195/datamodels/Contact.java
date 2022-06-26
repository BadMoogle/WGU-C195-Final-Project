package com.c195.datamodels;

/**
 * Object representing a company contact
 */
public class Contact {
    private final int contactId; //ID number of the contact
    private String name; //Full name of the contact
    private String email; //Email address of the contact

    /**
     * Private constructor to prevent empty initialization
     */
    private Contact() {
        contactId = -1;
    }

    /**
     * Basic constructor that only initializes the ID
     *
     * @param contactId ID of the contact
     */
    public Contact(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Fully parameterized constructor of the contact object
     *
     * @param contactId ID of the contact
     * @param name Full name of the contact
     * @param email Email address of the contact
     */
    public Contact(int contactId, String name, String email) {
        this.contactId = contactId;
        this.name = name;
        this.email = email;
    }

    /**
     * Copy constructor
     *
     * @param contact Contact object to copy
     */
    public Contact(Contact contact) {
        this.contactId = contact.contactId;
        this.name = contact.name;
        this.email = contact.email;
    }

    /**
     * toString method.  Mostly for data visualization
     *
     * @return The Contact's full name
     */
    public String toString() {
        return name;
    }

    /**
     * Getter returning the contact ID.
     *
     * @return the contactID
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Getter to return the full name of the contact
     *
     * @return The full name of the contact
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the full name of the contact
     *
     * @param name Full name ot set of the contact
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the contact's email address
     *
     * @return The email address of the contact
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the contact's email address'
     *
     * @param email email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
