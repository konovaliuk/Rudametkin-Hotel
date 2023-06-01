package com.rudametkin.hotelsystem.controller;

import com.rudametkin.hotelsystem.dto.BookingRoomDto;
import com.rudametkin.hotelsystem.dto.RoomClassDto;
import com.rudametkin.hotelsystem.dto.UserDto;
import com.rudametkin.hotelsystem.services.ApartmentsService;
import com.rudametkin.hotelsystem.services.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;

@Controller
@SessionAttributes({"user", "bookingRoom"})
public class BookingController {
    private final ApartmentsService apartmentsService;

    @Autowired
    public BookingController(ApartmentsService apartmentsService) {
        this.apartmentsService = apartmentsService;
    }

    @PostMapping("/booking")
    public String booking(@ModelAttribute BookingRoomDto bookingRoomDto, Model model) {
        boolean exists = apartmentsService.checkIfExistsFreeRoomByClass(new RoomClassDto(bookingRoomDto),
                bookingRoomDto.getArrivalDate(),
                bookingRoomDto.getDepartureDate());
        if(!exists)
            return "redirect:/search";
        model.addAttribute("bookingRoom", bookingRoomDto);
        return "booking";
    }

    @PostMapping("/book")
    public String book(@ModelAttribute("bookingRoom") BookingRoomDto bookingRoomDto,
                       @ModelAttribute("user") UserDto user) {
        boolean bookSuccess = apartmentsService.tryBookApartments(new RoomClassDto(bookingRoomDto),
                bookingRoomDto.getArrivalDate(),
                bookingRoomDto.getDepartureDate(),
                user);

        return (!bookSuccess)? "error" : "redirect:/cabinet";
    }
}
