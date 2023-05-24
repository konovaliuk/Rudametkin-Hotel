package com.rudametkin.hotelsystem.controller;

import com.rudametkin.hotelsystem.dto.RoomClassDto;
import com.rudametkin.hotelsystem.dto.UserDto;
import com.rudametkin.hotelsystem.entitys.Room;
import com.rudametkin.hotelsystem.services.ApartmentsService;
import com.rudametkin.hotelsystem.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class BookingController {

    private final ApartmentsService apartmentsService;

    @Autowired
    public BookingController(ApartmentsService apartmentsService) {
        this.apartmentsService = apartmentsService;
    }

    @PostMapping("/booking")
    public String booking(@RequestParam("sbeds") Integer sbeds, @RequestParam("dbeds") Integer dbeds,
                       @RequestParam("rooms") Integer rooms, @RequestParam("tv") Boolean tv,
                       @RequestParam("dryer") Boolean dryer, @RequestParam("minibar") Boolean minibar,
                       @RequestParam("price") Float price, @RequestParam("type") String roomType,
                       @RequestParam("arrival-date") String arrivalDate, @RequestParam("departure-date") String departureDate,
                       HttpSession session) {
        try {
            Room.RoomType type = Room.RoomType.valueOf(roomType);

            Date arrivalDateUtil = new SimpleDateFormat("yyyy-MM-dd").parse(arrivalDate);
            Date departureDateUtil = new SimpleDateFormat("yyyy-MM-dd").parse(departureDate);

            Timestamp arrivalTime = new Timestamp(arrivalDateUtil.getTime());
            Timestamp departureTime = new Timestamp(departureDateUtil.getTime());

            RoomClassDto roomClassDto = new RoomClassDto(type, rooms, sbeds, dbeds, minibar, tv, dryer, price);
            boolean exists = apartmentsService.checkIfExistsFreeRoomByClass(roomClassDto, arrivalTime, departureTime);

            session.setAttribute("exists", exists);
            session.setAttribute("arrivalDate", arrivalDate);
            session.setAttribute("departureDate", departureDate);
            session.setAttribute("roomClass", roomClassDto);
        } catch (Exception e) {
            return "cabinet";
        }
        return "booking";
    }

    private Timestamp parseDateTime(String datetime) {
        Date dateUtil = null;
        Timestamp timestamp = null;
        try {
            dateUtil = new SimpleDateFormat("yyyy-MM-dd").parse(datetime);
            timestamp = new Timestamp(dateUtil.getTime());
        } catch (Exception ignore) {}

        return timestamp;
    }

    @PostMapping("/book")
    public String book(HttpSession session) {
        if(session.isNew())
            return "redirect:/login";

        RoomClassDto roomClass = (RoomClassDto) session.getAttribute("roomClass");

        Timestamp arrivalTime = parseDateTime((String) session.getAttribute("arrivalDate"));
        Timestamp departureTime = parseDateTime((String) session.getAttribute("departureDate"));

        UserDto user = (UserDto) session.getAttribute("user");
        boolean bookSuccess = apartmentsService.tryBookApartments(roomClass, arrivalTime, departureTime, user);

        if(bookSuccess == false)
            return "error";

        return "redirect:/cabinet";
    }

}
