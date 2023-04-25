package com.rudametkin.hotelsystem.Controller.Commands.PageCommands;

import com.rudametkin.hotelsystem.Controller.Commands.ICommand;
import com.rudametkin.hotelsystem.DTO.RoomClassDto;
import com.rudametkin.hotelsystem.Services.RoomsSearchingService;

import com.rudametkin.hotelsystem.Entitys.Room;
import com.rudametkin.hotelsystem.DTO.RoomSearchDto;
import com.rudametkin.hotelsystem.Configs.PagePathConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class SearchPage implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RoomSearchDto formData = new RoomSearchDto();
        formData.setCapacity(Integer.parseInt(request.getParameter("persons")));
        formData.setBedsAmount(Integer.parseInt(request.getParameter("beds")));
        formData.setType(Room.RoomType.valueOf(request.getParameter("room-type")));

        Date arrivalDateUtil = new SimpleDateFormat("yyyy-MM-dd").parse((String) request.getParameter("arrival-date"));
        Date departureDateUtil = new SimpleDateFormat("yyyy-MM-dd").parse((String) request.getParameter("departure-date"));

        formData.setArrivalDate(new Timestamp(arrivalDateUtil.getTime()));
        formData.setDepartureDate(new Timestamp(departureDateUtil.getTime()));

        RoomsSearchingService roomsService = new RoomsSearchingService();
        int page = request.getParameter("page") != null ?
                Integer.parseInt(request.getParameter("page")) : 1;

        List<RoomClassDto> foundRooms = roomsService.findRoomsByParams(formData, page);

        int maxPages = (int) Math.ceil(
                roomsService.countFreeRoomClassesByParams(formData)/(float)RoomsSearchingService.getPagePart());

        request.getSession().setAttribute("foundRooms", foundRooms);
        request.getSession().setAttribute("maxPages", maxPages);

        PagePathConfig pagePathConfig = new PagePathConfig();
        request.getRequestDispatcher(pagePathConfig.getProperty("search")).forward(request, response);
    }
}
