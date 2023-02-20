package com.example.bookingmanagementsystem.webservice;


import com.example.bookingmanagementsystem.business.ReservationService;
import com.example.bookingmanagementsystem.business.RoomReservation;
import com.example.bookingmanagementsystem.data.Guest;
import com.example.bookingmanagementsystem.data.Room;
import com.example.bookingmanagementsystem.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebServiceController {
    private final ReservationService reservationService;
    private final DateUtils dateUtils;

    public WebServiceController(ReservationService reservationService, DateUtils dateUtils) {
        this.reservationService = reservationService;
        this.dateUtils = dateUtils;
    }

    @RequestMapping(path = "/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getGuests(@RequestParam(value="date", required = false) String dateString) {
        Date date = dateUtils.createDateFromDateString(dateString);
        return reservationService.getRoomReservationsForDate(date);
    }

    @GetMapping(path ="/guests")
    public List<Guest> getGuests() {
        return reservationService.getGuests();
    }

    @PostMapping(path ="/guests")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGuests(@RequestBody Guest guest) {
        reservationService.addGuest(guest);
    }

    @GetMapping(path ="/rooms")
    public List<Room> getRooms() {
        return reservationService.getRooms();
    }


}
