package com.rudametkin.hotelsystem.Controller.Commands.PageCommands;

import com.rudametkin.hotelsystem.Controller.Commands.ICommand;
import com.rudametkin.hotelsystem.Services.RoomsSearchingService;

import com.rudametkin.hotelsystem.EntityObjects.Room;
import com.rudametkin.hotelsystem.Services.ServiceHelpClasses.RoomParameters;
import com.rudametkin.hotelsystem.Configs.PagePathConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;

public class SearchPage implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RoomParameters formData = new RoomParameters();
        formData.setCapacity(Integer.parseInt(request.getParameter("persons")));
        formData.setBedsAmount(Integer.parseInt(request.getParameter("beds")));
        formData.setType(Room.RoomType.valueOf(request.getParameter("room-type")));


        Date arrivalDateUtil = new SimpleDateFormat("yyyy-MM-dd").parse((String) request.getParameter("arrival-date"));
        Date departureDateUtil = new SimpleDateFormat("yyyy-MM-dd").parse((String) request.getParameter("departure-date"));

        formData.setArrivalDate(new Timestamp(arrivalDateUtil.getTime()));
        formData.setDepartureDate(new Timestamp(departureDateUtil.getTime()));

        RoomsSearchingService roomsService = new RoomsSearchingService();
        roomsService.findRoomsByParams(formData);

        request.getSession().setAttribute("searchRooms", roomsService);

        PagePathConfig pagePathConfig = new PagePathConfig();
        request.getRequestDispatcher(pagePathConfig.getProperty("search")).forward(request, response);
    }
}
