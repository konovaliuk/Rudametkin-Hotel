package com.rudametkin.hotelsystem.controller;

import com.rudametkin.hotelsystem.dto.RoomClassDto;
import com.rudametkin.hotelsystem.dto.RoomSearchDto;
import com.rudametkin.hotelsystem.dto.RoomSearchRawDto;
import com.rudametkin.hotelsystem.entitys.Room;
import com.rudametkin.hotelsystem.services.RoomsSearchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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
    public String search(@ModelAttribute RoomSearchRawDto rawFormData, Model model) {
        List<RoomClassDto> foundRooms = roomsSearchingService.findRoomsByParams(new RoomSearchDto(rawFormData));
        int maxPages = roomsSearchingService.countPagesOfFreeRooms(new RoomSearchDto(rawFormData));
        model.addAttribute("foundRooms", foundRooms);
        model.addAttribute("maxPages", maxPages);
        return "search";
    }


}
