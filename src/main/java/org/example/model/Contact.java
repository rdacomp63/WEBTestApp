package org.example.model;

public class Contact {
    private int Id;
    private String name;
    private String family;
    private String phoneNumber;

    public int getContactId() {
        return Id;
    }

    public void setContactId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String lastName) {
        this.family = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Contact [Id=" + Id + ", Name=" + name + ", Family=" + family + ", phoneNumber="
                + phoneNumber + "]";
    }
}
