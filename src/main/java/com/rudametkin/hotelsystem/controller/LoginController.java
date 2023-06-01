package com.rudametkin.hotelsystem.controller;

import com.rudametkin.hotelsystem.dto.RegisterDto;
import com.rudametkin.hotelsystem.dto.UserDto;
import com.rudametkin.hotelsystem.entitys.User;
import com.rudametkin.hotelsystem.services.RoomsSearchingService;
import com.rudametkin.hotelsystem.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestParam("login") String login,
                        @RequestParam("password") String password,
                        Model model) {
        UserDto user = userService.authenticateUser(login, password);
        if(user == null)
            return "error";
        model.addAttribute("user", user);
        return "redirect:/home";
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User signupFormData) {
        return userService.tryRegisterUser(signupFormData)? "redirect:/login" : "error";
    }



    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }
}
