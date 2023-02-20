package com.example.bookingmanagementsystem.data;

import jakarta.persistence.*;

@Entity
@Table(name = "ROOM")
public class Room {
    @Id
    @Column(name = "ROOM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME", nullable = false, length = 16)
    private String name;

    @Column(name = "ROOM_NUMBER", nullable = false, length = 2, unique = true)
    private String roomNumber;

    @Column(name = "BED_INFO", nullable = false, length = 2)
    private Character bedInfo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getBedInfo() {
        return bedInfo;
    }

    public void setBedInfo(Character bedInfo) {
        this.bedInfo = bedInfo;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", bedInfo=" + bedInfo +
                '}';
    }
}
