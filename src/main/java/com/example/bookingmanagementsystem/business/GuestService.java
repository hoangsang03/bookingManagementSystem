package com.example.bookingmanagementsystem.business;

import com.example.bookingmanagementsystem.data.Guest;
import com.example.bookingmanagementsystem.data.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GuestService {
    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public Guest saveGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    public void deleteGuest(Integer guestId) {
        verifyGuest(guestId);
        guestRepository.deleteById(Long.valueOf(guestId));
    }

    public Guest verifyGuest(Integer id) {
        Optional<Guest> optionalGuest = guestRepository.findById(Long.valueOf(id));
        return optionalGuest.orElseThrow(() ->new NoSuchElementException("" +
                "Guest id " + id + " has not been found"));
    }

    public Optional<Guest> findById(Integer guestId) {
        return guestRepository.findById(Long.valueOf(guestId));
    }
}
