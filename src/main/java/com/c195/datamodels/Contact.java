package com.c195.datamodels;

public class Contact {
    private final int contactId;
    private String name;
    private String email;

    private Contact() {
        contactId = -1;
    }

    public Contact(int contactId) {
        this.contactId = contactId;
    }

    public Contact(int contactId, String name, String email) {
        this.contactId = contactId;
        this.name = name;
        this.email = email;
    }

    public Contact(Contact contact) {
        this.contactId = contact.contactId;
        this.name = contact.name;
        this.email = contact.email;
    }

    public String toString() {
        return name;
    }

    public int getContactId() {
        return contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
