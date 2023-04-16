package com.rudametkin.hotelsystem.commands;

import com.rudametkin.hotelsystem.businessLogic.RoomsService;
import com.rudametkin.hotelsystem.configs.Config;
import com.rudametkin.hotelsystem.entityObjects.Room;
import com.rudametkin.hotelsystem.entityObjects.RoomParameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;

public class StartSearching implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RoomParameters formData = new RoomParameters();
        formData.setCapacity(Integer.parseInt(request.getParameter("persons-amount")));
        formData.setBedsAmount(Integer.parseInt(request.getParameter("beds-amount")));
        formData.setType(Room.RoomType.valueOf(request.getParameter("room-type")));


        Date arrivalDateUtil = new SimpleDateFormat("yyyy-MM-dd").parse((String) request.getParameter("arrival-date"));
        Date departureDateUtil = new SimpleDateFormat("yyyy-MM-dd").parse((String) request.getParameter("departure-date"));

        formData.setArrivalDate(new Timestamp(arrivalDateUtil.getTime()));
        formData.setDepartureDate(new Timestamp(departureDateUtil.getTime()));

        RoomsService roomsService = new RoomsService();
        roomsService.findRoomsByParams(formData);

        request.getSession().setAttribute("searchRooms", roomsService);

        Config pagePathConfig = new Config("resources/properties/", "pagepath.properties");
        response.sendRedirect(request.getContextPath() + pagePathConfig.getProperty("search"));
    }
}
