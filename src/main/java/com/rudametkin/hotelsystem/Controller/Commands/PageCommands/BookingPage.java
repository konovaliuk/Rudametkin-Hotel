package com.rudametkin.hotelsystem.Controller.Commands.PageCommands;

import com.rudametkin.hotelsystem.Configs.PagePathConfig;
import com.rudametkin.hotelsystem.Controller.Commands.ICommand;
import com.rudametkin.hotelsystem.DTO.RoomClassDto;
import com.rudametkin.hotelsystem.Entitys.Room;
import com.rudametkin.hotelsystem.Services.ApartmentsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingPage implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PagePathConfig pagePathConfig = new PagePathConfig();

        try {
            int roomsAmount      = Integer.parseInt(request.getParameter("rooms"));
            int singleBedsAmount = Integer.parseInt(request.getParameter("sbeds"));
            int doubleBedsAmount = Integer.parseInt(request.getParameter("dbeds"));
            Room.RoomType type = Room.RoomType.valueOf(request.getParameter("type"));
            boolean tv = Boolean.parseBoolean(request.getParameter("tv"));
            boolean minibar = Boolean.parseBoolean(request.getParameter("minibar"));
            boolean dryer = Boolean.parseBoolean(request.getParameter("dryer"));
            float price = Float.parseFloat(request.getParameter("price"));

            Date arrivalDateUtil = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("arrival-date"));
            Date departureDateUtil = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("departure-date"));

            Timestamp arrivalTime = new Timestamp(arrivalDateUtil.getTime());
            Timestamp departureTime = new Timestamp(departureDateUtil.getTime());

            RoomClassDto roomClassDto = new RoomClassDto(type, roomsAmount, singleBedsAmount, doubleBedsAmount, minibar, tv, dryer, price);
            boolean exists = new ApartmentsService().checkIfExistsFreeRoomByClass(roomClassDto, arrivalTime, departureTime);

            request.getSession().setAttribute("exists", exists);
            request.getSession().setAttribute("arrivalDate", request.getParameter("arrival-date"));
            request.getSession().setAttribute("departureDate", request.getParameter("departure-date"));
            request.getSession().setAttribute("roomClass", roomClassDto);
        } catch (Exception e) {
            request.getRequestDispatcher(pagePathConfig.getProperty("cabinet")).forward(request, response);
            return;
        }


        request.getRequestDispatcher(pagePathConfig.getProperty("booking")).forward(request, response);
    }
}
