package com.rudametkin.hotelsystem.controller;

import com.rudametkin.hotelsystem.dto.OrderDto;
import com.rudametkin.hotelsystem.dto.UserDto;
import com.rudametkin.hotelsystem.entitys.Bill;
import com.rudametkin.hotelsystem.services.ApartmentsService;
import com.rudametkin.hotelsystem.services.BillsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"user","clientLogin"})
public class CabinetController {

    private final BillsService billsService;

    @Autowired
    public CabinetController(BillsService billsService) {
        this.billsService = billsService;
    }

    @RequestMapping("/cabinet")
    public String cabinet(Model model, @ModelAttribute("user") UserDto userDto) {
        if(userDto.hasRole("Client")) {
            List<OrderDto> orders = billsService.getAllUserBills(userDto);
            model.addAttribute("orders", orders);
        } else {
            List<OrderDto> clientBills = billsService.getActiveUserBills((String) model.getAttribute("clientLogin"));
            model.addAttribute("clientOrders", clientBills);
        }
        return "cabinet";
    }

    @PostMapping("/admin-search")
    public String clientsSearch(@RequestParam("client-login") String clientLogin, Model model) {
        model.addAttribute("clientLogin", clientLogin);
        return "redirect:/cabinet";
    }

    @PostMapping("/cancelOrder")
    public String cancelOrder(@RequestParam("room-register-id") Integer roomRegisterId,
                              @ModelAttribute("user") UserDto userDto) {
        if(userDto.getEmail() == null || !userDto.hasRole("Administrator"))
            return "redirect:/login";
        billsService.cancelOrder(roomRegisterId);
        return "redirect:/cabinet";
    }
}
