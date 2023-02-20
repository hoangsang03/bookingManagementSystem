package com.example.bookingmanagementsystem.business;

import com.example.bookingmanagementsystem.data.Room;
import com.example.bookingmanagementsystem.data.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Iterable<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room createNewRoom (Room room) {
        return roomRepository.save(room);
    }

    public void deleteRoom (Integer roomId) {
        roomRepository.deleteById(Long.valueOf(roomId));
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room verifyRoom(Integer id) {
        Optional<Room> optionalRoom = roomRepository.findById(Long.valueOf(id));
        return optionalRoom.orElseThrow(() -> new NoSuchElementException("No Room " +
                "has room id " + id));
    }
}
