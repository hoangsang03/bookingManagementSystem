package com.example.bookingmanagementsystem.dto;

public class ReservationDTO {
    private long roomId;
    private long guestId;

    private String dateString;

    public ReservationDTO(long roomId, long guestId, String dateString) {
        this.roomId = roomId;
        this.guestId = guestId;
        this.dateString = dateString;
    }

    public ReservationDTO() {
    }

    public ReservationDTO(long roomId) {
        this.roomId = roomId;
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

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
}
