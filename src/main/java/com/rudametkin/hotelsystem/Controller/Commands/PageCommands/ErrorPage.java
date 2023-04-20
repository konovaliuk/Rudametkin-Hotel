package com.rudametkin.hotelsystem.Controller.Commands.PageCommands;

import com.rudametkin.hotelsystem.Configs.PagePathConfig;
import com.rudametkin.hotelsystem.Controller.Commands.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorPage implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PagePathConfig pagePathConfig = new PagePathConfig();
        request.getRequestDispatcher(pagePathConfig.getProperty("error")).forward(request, response);
    }
}
