package com.rudametkin.hotelsystem.controller;

import com.rudametkin.hotelsystem.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class LogoutController {
    @RequestMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("user", new UserDto());
        return "redirect:/home";
    }
}
