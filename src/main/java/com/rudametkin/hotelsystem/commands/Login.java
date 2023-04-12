package com.rudametkin.hotelsystem.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rudametkin.hotelsystem.businessLogic.UserService;
import com.rudametkin.hotelsystem.configs.Config;

public class Login implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserService user = new UserService();
        user.authenticateUser(login, password);

        if(user.getIsAuthenticated()) {
            System.out.println(user.getSurname() + " | Correct!");
        } else {
            throw new Exception("Wrong credentials!");
        }

        request.getSession().setAttribute("user", user);
        Config pagePathConfig = new Config("resources/properties/", "pagepath.properties");
        response.sendRedirect(request.getContextPath() + pagePathConfig.getProperty("cabinet"));
    }
}
