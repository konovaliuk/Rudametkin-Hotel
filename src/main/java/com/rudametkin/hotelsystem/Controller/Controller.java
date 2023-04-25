package com.rudametkin.hotelsystem.Controller;

import com.rudametkin.hotelsystem.Controller.Commands.Error;
import com.rudametkin.hotelsystem.Controller.Commands.ICommand;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet
public class Controller extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ICommand command = ControllerHelper.getInstance().getCommand(request);
            command.execute(request, response);
        } catch (Exception e) {
            System.out.println("Controller: " + e.getMessage());
            ICommand error = new Error(e.getMessage());
            try {
                error.execute(request, response);
            } catch (Exception ignore) {
            }
            ;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service(request, response);
    }
}