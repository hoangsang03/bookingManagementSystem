package com.example.bookingmanagementsystem.web;

import com.example.bookingmanagementsystem.business.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/booking")
public class BookingController {
    ReservationService reservationService;
}
