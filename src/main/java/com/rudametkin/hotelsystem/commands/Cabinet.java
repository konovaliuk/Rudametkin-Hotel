package com.rudametkin.hotelsystem.commands;

import com.rudametkin.hotelsystem.configs.Config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Cabinet implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Config pagePathConfig = new Config("resources/properties/", "pagepath.properties");
        if(request.getSession().isNew() == false)
            response.sendRedirect(request.getContextPath() + pagePathConfig.getProperty("cabinet"));
        else
            response.sendRedirect(request.getContextPath() + pagePathConfig.getProperty("login"));
    }
}
