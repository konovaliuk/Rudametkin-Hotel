package com.rudametkin.hotelsystem.controller;

import com.rudametkin.hotelsystem.dto.RoomClassDto;
import com.rudametkin.hotelsystem.dto.RoomSearchDto;
import com.rudametkin.hotelsystem.entitys.Room;
import com.rudametkin.hotelsystem.services.RoomsSearchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SearchController {
    private final RoomsSearchingService roomsSearchingService;

    @Autowired
    public SearchController(RoomsSearchingService roomsSearchingService) {
        this.roomsSearchingService = roomsSearchingService;
    }

    @RequestMapping("/search")
    public String search(@RequestParam("persons") Integer persons, @RequestParam("beds") Integer beds,
                         @RequestParam("room-type") String roomType, @RequestParam("arrival-date") String arrivalDate,
                         @RequestParam("departure-date") String departureDate, @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                         Model model) {

        RoomSearchDto formData = new RoomSearchDto();
        formData.setCapacity(persons);
        formData.setBedsAmount(beds);
        formData.setType(Room.RoomType.valueOf(roomType));

        Date arrivalDateUtil = null;
        Date departureDateUtil = null;

        try {
            arrivalDateUtil = new SimpleDateFormat("yyyy-MM-dd").parse(arrivalDate);
            departureDateUtil = new SimpleDateFormat("yyyy-MM-dd").parse(departureDate);
        } catch (Exception e) {
            arrivalDateUtil = new Date();
            departureDateUtil = new Date();
        }
        formData.setArrivalDate(new Timestamp(arrivalDateUtil.getTime()));
        formData.setDepartureDate(new Timestamp(departureDateUtil.getTime()));

        page = page != null ? page : 1;

        List<RoomClassDto> foundRooms = roomsSearchingService.findRoomsByParams(formData, page);
        int maxPages = (int) Math.ceil(roomsSearchingService.countFreeRoomClassesByParams(formData)/(float)RoomsSearchingService.getPagePart());

        System.out.printf("MAX PAGES: " + maxPages);
        model.addAttribute("foundRooms", foundRooms);
        model.addAttribute("maxPages", maxPages);

        return "search";
    }


}
