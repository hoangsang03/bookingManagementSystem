package com.example.bookingmanagementsystem.web;

import com.example.bookingmanagementsystem.business.GuestService;
import com.example.bookingmanagementsystem.business.ReservationService;
import com.example.bookingmanagementsystem.data.Guest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/guests")
public class GuestController {
    private final ReservationService reservationService;
    private final GuestService guestService;


    public GuestController(ReservationService reservationService, GuestService guestService) {
        this.reservationService = reservationService;
        this.guestService = guestService;
    }

    @RequestMapping (method = RequestMethod.GET)
    public String getGuests(Model model) {
        List<Guest> guests = reservationService.getGuests();
        model.addAttribute("guests",guests);
        return "hotel-guests";
    }

    @GetMapping("/new")
    public String addNewGuest(Model model){
        model.addAttribute("guest", new Guest());
        model.addAttribute("pageTitle","ADD NEW Guest");
        return "guest-form";
    }

    @PostMapping("/save")
    public String saveGuest(Guest guest, RedirectAttributes ra) {
        Guest savedGuest = guestService.saveGuest(guest);
        ra.addFlashAttribute("message","Guest Id "
        + savedGuest.getGuestId() + " has been saved.");
        return "redirect:/guests";
    }

    @GetMapping("/delete/{id}")
    public String deleteGuest(@PathVariable("id") Integer guestId,
                              RedirectAttributes ra) {
        guestService.deleteGuest(guestId);
        ra.addFlashAttribute("message","Guest id "+
                guestId + " has been deleted.");
        return "redirect:/guests";
    }
    @GetMapping("/edit/{id}")
    public String updateGuest(@PathVariable("id") Integer guestId,
                              RedirectAttributes ra, Model model) {
        Optional<Guest> guest = guestService.findById(guestId);
        if(guest.isPresent()){
            Guest updateGuest = new Guest();
            updateGuest.setGuestId(guestId);
            model.addAttribute("guest",guest.get());
            model.addAttribute("pageTitle","Update Guest");
            ra.addFlashAttribute("message","Updating Guest Id "+
                    guestId);
            return "guest-form";
        }
        else {
            ra.addFlashAttribute("message","Guest id "+
                    guestId + " was not existed .");
            return "redirect:/guests";
        }
    }


}
