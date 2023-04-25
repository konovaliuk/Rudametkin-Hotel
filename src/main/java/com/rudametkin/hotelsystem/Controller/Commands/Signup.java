package com.rudametkin.hotelsystem.Controller.Commands;

import com.rudametkin.hotelsystem.Entitys.User;
import com.rudametkin.hotelsystem.DTO.RegisterDto;
import com.rudametkin.hotelsystem.Services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Signup implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User signupFormData = new User();
        signupFormData.setLogin(request.getParameter("login"));
        signupFormData.setName(request.getParameter("name"));
        signupFormData.setSurname(request.getParameter("surname"));
        signupFormData.setEmail(request.getParameter("email"));
        signupFormData.setPassword(request.getParameter("password"));
        signupFormData.setPhone(request.getParameter("phone"));

        UserService userService = new UserService();
        RegisterDto checkDataInfo = userService.checkRegisterData(signupFormData);
        if(checkDataInfo.isRegisterAvailable() == false) {
            String errorMsg = "Register data is not available. ";
            if(!checkDataInfo.isUniqueEmail())
                errorMsg += signupFormData.getEmail() + " is already taken. ";
            if(!checkDataInfo.isUniqueLogin())
                errorMsg += signupFormData.getLogin() + " is already taken. ";
            if(!checkDataInfo.isUniquePhone())
                errorMsg += signupFormData.getPhone() + " is already taken. ";
            new Error(errorMsg).execute(request, response);
            return;
        }

        boolean registerSuccess = userService.tryRegisterUser(signupFormData);
        if(registerSuccess == false) {
            new Error("Something went wrong. Please try again later.").execute(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/login");
    }
}
