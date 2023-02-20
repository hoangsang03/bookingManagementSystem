package com.example.bookingmanagementsystem.business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RoomReservation {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long roomId;
    private long guestId;
    private String roomName;
    private String roomNumber;
    private String firstName;
    private String lastName;
    private Date date;


    public RoomReservation(long id,long roomId, long guestId, String roomName, String roomNumber, String firstName, String lastName, Date date) {
        this.id = id;
        this.roomId = roomId;
        this.guestId = guestId;
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
    }

    public RoomReservation() {

    }


    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getGuestId() {
        return guestId;
    }

    public void setGuestId(long guestId) {
        this.guestId = guestId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RoomReservation{" +
                "roomId=" + roomId +
                ", guestId=" + guestId +
                ", roomName='" + roomName + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", date=" + date +
                '}';
    }


    public String getDateString() {
        String pattern = "MM/dd/yyyy";

// Create an instance of SimpleDateFormat used for formatting
// the string representation of date according to the chosen pattern
        DateFormat df = new SimpleDateFormat(pattern);

// Using DateFormat format method we can create a string
// representation of a date with the defined format.
        String dateString = df.format(this.date);
        return dateString;
    }

}
