package com.rudametkin.hotelsystem.controller;

import com.rudametkin.hotelsystem.dto.OrderDto;
import com.rudametkin.hotelsystem.dto.UserDto;
import com.rudametkin.hotelsystem.entitys.Bill;
import com.rudametkin.hotelsystem.services.ApartmentsService;
import com.rudametkin.hotelsystem.services.BillsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CabinetController {

    private final BillsService billsService;

    @Autowired
    public CabinetController(BillsService billsService) {
        this.billsService = billsService;
    }

    @RequestMapping("/cabinet")
    public String cabinet(Model model, HttpSession session) {
        if(session.getAttribute("user") == null)
            return "redirect:/login";

        UserDto user = (UserDto) session.getAttribute("user");
        List<OrderDto> orders = billsService.getAllUserBills(user);
        model.addAttribute("orders", orders);

        return "cabinet";
    }

    @PostMapping("/admin-search")
    public String clientsSearch(@RequestParam("client-login") String clientLogin, HttpSession session) {
        if(session.getAttribute("user") == null)
            return "redirect:/login";

        UserDto user = (UserDto) session.getAttribute("user");
        if(user.hasRole("Administrator") == false)
            return "redirect:/home";

        List<OrderDto> clientBills = billsService.getActiveUserBills(clientLogin);

        session.setAttribute("clientOrders", clientBills);
        session.setAttribute("clientLogin", clientLogin);

        return "redirect:/cabinet";
    }

    @PostMapping("/cancelOrder")
    public String cancelOrder(@RequestParam("room-register-id") Integer roomRegisterId, HttpSession session) {
        if(session.getAttribute("user") == null)
            return "redirect:/login";

        UserDto user = (UserDto) session.getAttribute("user");
        if(user.hasRole("Administrator") == false)
            return "redirect:/home";

        billsService.cancelOrder(roomRegisterId);
        List<OrderDto> clientBills = billsService.getActiveUserBills((String) session.getAttribute("clientLogin"));

        session.setAttribute("clientOrders", clientBills);

        return "redirect:/cabinet";
    }
}
