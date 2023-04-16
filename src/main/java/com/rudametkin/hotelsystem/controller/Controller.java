package com.rudametkin.hotelsystem.controller;

import com.rudametkin.hotelsystem.commands.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/Controller")
public class Controller extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String action = request.getParameter("command");
            ICommand command = ControllerHelper.getInstance().getCommand(action);
            command.execute(request, response);
        } catch (Exception e) {
            System.out.println("[ERROR] | " + e.getMessage());
            ICommand command = ControllerHelper.getInstance().getCommand("handle-error");
            try {
                request.getSession().setAttribute("error", e.getMessage());
                command.execute(request, response);
            } catch (Exception ignore) {};
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service(request, response);
    }
}