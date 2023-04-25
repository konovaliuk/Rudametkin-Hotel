package com.rudametkin.hotelsystem.Controller.Commands.PageCommands;

import com.rudametkin.hotelsystem.Configs.PagePathConfig;
import com.rudametkin.hotelsystem.Controller.Commands.ICommand;
import com.rudametkin.hotelsystem.DTO.OrderDto;
import com.rudametkin.hotelsystem.DTO.UserDto;
import com.rudametkin.hotelsystem.Entitys.Bill;
import com.rudametkin.hotelsystem.Entitys.User;
import com.rudametkin.hotelsystem.Services.ApartmentsService;
import com.rudametkin.hotelsystem.Services.BillsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CabinetPage implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PagePathConfig pagePathConfig = new PagePathConfig();

        if(request.getSession().getAttribute("user")  == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        else if(request.getSession().getAttribute("orders") == null) {
            UserDto user = (UserDto) request.getSession().getAttribute("user");
            List<OrderDto> orders = new BillsService().getAllUserBills(user);
            request.getSession().setAttribute("orders", orders);
        }

        request.getRequestDispatcher(pagePathConfig.getProperty("cabinet")).forward(request, response);
    }
}
