package com.example.bookingmanagementsystem.web;

import com.example.bookingmanagementsystem.business.RoomService;
import com.example.bookingmanagementsystem.data.Room;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public String getRooms(Model model) {
        List<Room> allRooms = (List<Room>) roomService.getAllRooms();
        model.addAttribute("rooms",allRooms);
        return "all-rooms";
    }

    @GetMapping("/new")
    public String createNewRoom(Model model) {
        model.addAttribute("room",new Room());
        model.addAttribute("pageTitle","ADD NEW ROOM");
        return "room-form";
    }

    @PostMapping("/save")
    public String saveRoom(Room room, RedirectAttributes ra){
        Room saveRoom = roomService.saveRoom(room);
        ra.addFlashAttribute("message","Room with room number "
        + saveRoom.getRoomNumber() + " has been saved.");
        return "redirect:/rooms";
    }

    @GetMapping("/delete/{id}")
    public String deleteRooms(@PathVariable("id") Integer id, RedirectAttributes ra) {
        roomService.deleteRoom(id);
        ra.addFlashAttribute("message","Room with id " +
                id + " has been deleted.");
        return "redirect:/rooms";
    }

    @GetMapping("/edit/{id}")
    public String updateRoom(@PathVariable("id") Integer id, Model model) {
        Room room = roomService.verifyRoom(id);
        model.addAttribute("room",room);
        return "room-form";
    }

}
