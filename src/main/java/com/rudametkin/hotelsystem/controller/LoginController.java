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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestParam("login") String login, @RequestParam("password") String password,
                        HttpSession session) {
        UserDto user = userService.authenticateUser(login, password);
        if(user == null)
            return "error";

        session.setAttribute("user", user);
        return "redirect:/home";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam("login") String login, @RequestParam("name") String name,
                         @RequestParam("surname") String surname, @RequestParam("email") String email,
                         @RequestParam("password") String password, @RequestParam("phone") String phone) {

        User signupFormData = new User();
        signupFormData.setLogin(login);
        signupFormData.setName(name);
        signupFormData.setSurname(surname);
        signupFormData.setEmail(email);
        signupFormData.setPassword(password);
        signupFormData.setPhone(phone);

        RegisterDto checkDataInfo = userService.checkRegisterData(signupFormData);
        if(checkDataInfo.isRegisterAvailable() == false) {
            /*
            String errorMsg = "Register data is not available. ";
            if(!checkDataInfo.isUniqueEmail())
                errorMsg += signupFormData.getEmail() + " is already taken. ";
            if(!checkDataInfo.isUniqueLogin())
                errorMsg += signupFormData.getLogin() + " is already taken. ";
            if(!checkDataInfo.isUniquePhone())
                errorMsg += signupFormData.getPhone() + " is already taken. ";
             */
            return "error";
        }

        boolean registerSuccess = userService.tryRegisterUser(signupFormData);
        if(registerSuccess == false)
            //new Error("Something went wrong. Please try again later.").execute(request, response);
            return "error";

        return "redirect:/login";
    }



    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }
}
