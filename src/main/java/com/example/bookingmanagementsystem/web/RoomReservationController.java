package com.example.bookingmanagementsystem.web;

import com.example.bookingmanagementsystem.business.ReservationService;
import com.example.bookingmanagementsystem.business.RoomReservation;
import com.example.bookingmanagementsystem.business.RoomService;
import com.example.bookingmanagementsystem.data.Reservation;
import com.example.bookingmanagementsystem.data.Room;
import com.example.bookingmanagementsystem.dto.ReservationDTO;
import com.example.bookingmanagementsystem.dto.RoomReservationDTO;
import com.example.bookingmanagementsystem.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reservations")
public class RoomReservationController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    private final RoomService roomService;

    public RoomReservationController(DateUtils dateUtils, ReservationService reservationService, RoomService roomService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
        this.roomService = roomService;
    }

    @RequestMapping (method = RequestMethod.GET)
    public String getReservations(Model model) {
        List<RoomReservation> roomReservations = reservationService.getBookedReservation();
        model.addAttribute("roomReservations",roomReservations);
        return "roomres";
    }

    @GetMapping("/booking/{id}")
    public String addNewReservationRoom(@PathVariable("id") Integer roomId, Model model) {
        Room room = roomService.verifyRoom(roomId);
        model.addAttribute("room",room);
        model.addAttribute("reservationDto",new ReservationDTO(roomId));
        model.addAttribute("pageTitle","Booking Room");
        return "booking-room";
    }

    @PostMapping("/save")
    public String saveReservation(ReservationDTO reservationDTO, RedirectAttributes ra) {
        Reservation reservation = new Reservation();
        reservation.setRoomId(reservationDTO.getRoomId());
        reservation.setGuestId(reservationDTO.getGuestId());
        reservation.setReservationDate(dateUtils.
                createDateFromDateString(reservationDTO.getDateString()));
        reservationService.save(reservation);
        ra.addFlashAttribute("message","Reservation with id "
        + reservation.getReservationId() + " has been saved.");
        return "redirect:/reservations";
    }

    @PostMapping("/update")
    public String updateReservation(RoomReservationDTO roomReservationDTO) {
        Long reservationId = roomReservationDTO.getId();
        Optional<Reservation> reservationOptional = reservationService.findById(reservationId);
        Reservation reservation = reservationOptional.get();

        long roomId = roomReservationDTO.getRoomId();
        String date = roomReservationDTO.getDate();

        reservation.setRoomId(roomId);
        reservation.setReservationDate(dateUtils.createDateFromDateString(date));
        reservationService.save(reservation);
        return "redirect:/reservations";
    }


    @GetMapping("/edit/{id}")
    public String updateRoomReservation(@PathVariable("id") Integer id, Model model) {
        Optional<Reservation> reservationOptional = reservationService.findById(Long.valueOf(id));
        RoomReservation roomReservation =
                reservationService.createRoomReservation(reservationOptional.get());
        RoomReservationDTO roomReservationDTO = reservationService.createRoomReservationDTO(roomReservation);
        model.addAttribute("roomReservationDTO",roomReservationDTO);
        model.addAttribute("pageTitle","Updating Reservation");
        return "update-reservation-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoomReservation(@PathVariable("id") Integer id, RedirectAttributes ra){
        reservationService.deleteById(id);
        ra.addFlashAttribute("message","Reservation with " + id +
                " has been deleted.");
        return "redirect:/reservations";
    }

}
