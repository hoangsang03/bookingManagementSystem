package com.example.bookingmanagementsystem.data;

import jakarta.persistence.*;

@Entity
@Table(name = "GUEST")
public class Guest {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long guestId;

    @Column(length = 64, name = "first_name")
    private String firstName;

    @Column(length = 64, name="last_name")
    private String lastName;

    @Column(length = 64, name = "email_address")
    private String email;

    @Column(length = 64, name="address")
    private String address;

    @Column(length = 32, name="country")
    private String country;

    @Column(length = 12, name="state")
    private String state;

    @Column(length = 24, name="phone_number")
    private String phoneNumber;

    public long getGuestId() {
        return guestId;
    }

    public void setGuestId(long guest_id) {
        this.guestId = guest_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "guestId=" + guestId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public Guest(String firstName, String lastName, String email,
                 String address, String country, String state,
                 String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.country = country;
        this.state = state;
        this.phoneNumber = phoneNumber;
    }

    public Guest() {
    }
}



