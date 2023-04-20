package com.rudametkin.hotelsystem.Controller.Commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rudametkin.hotelsystem.EntityObjects.UserWithRoles;
import com.rudametkin.hotelsystem.Services.UserService;

public class Login implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserService userService = new UserService();
        UserWithRoles user = userService.authenticateUser(login, password);

        if(user == null) {
            new Error("Wrong Credentials").execute(request, response);
            return;
        }



        request.getSession().setAttribute("user", user);
        response.sendRedirect(request.getContextPath() + "/cabinet");
    }
}
