package com.rudametkin.hotelsystem.Controller.Commands;

import com.rudametkin.hotelsystem.DTO.OrderDto;
import com.rudametkin.hotelsystem.DTO.RoomClassDto;
import com.rudametkin.hotelsystem.DTO.UserDto;
import com.rudametkin.hotelsystem.Services.ApartmentsService;
import com.rudametkin.hotelsystem.Services.BillsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Book implements ICommand{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(request.getSession().getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }


        RoomClassDto roomClass = (RoomClassDto) request.getSession().getAttribute("roomClass");
        Date arrivalDateUtil = new SimpleDateFormat("yyyy-MM-dd").parse((String) request.getSession().getAttribute("arrivalDate"));
        Date departureDateUtil = new SimpleDateFormat("yyyy-MM-dd").parse((String) request.getSession().getAttribute("departureDate"));

        Timestamp arrivalTime = new Timestamp(arrivalDateUtil.getTime());
        Timestamp departureTime = new Timestamp(departureDateUtil.getTime());

        ApartmentsService apartmentsService = new ApartmentsService();
        UserDto user = (UserDto) request.getSession().getAttribute("user");
        boolean bookSuccess = apartmentsService.tryBookApartments(roomClass, arrivalTime, departureTime, user);

        if(bookSuccess == false) {
            new Error("Some error occurred while booking. Please check if all data correct and try later.").execute(request, response);
            return;
        }

        request.getSession().setAttribute("orders", new BillsService().getAllUserBills(user));
        response.sendRedirect(request.getContextPath() + "/cabinet");
    }
}
