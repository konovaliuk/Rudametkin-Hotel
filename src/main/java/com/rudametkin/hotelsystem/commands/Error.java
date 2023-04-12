package com.rudametkin.hotelsystem.commands;

import com.rudametkin.hotelsystem.configs.Config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Error implements ICommand{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Config pagePathConfig = new Config("/resources/properties/", "pagepath.properties");
        response.sendRedirect(request.getContextPath() + pagePathConfig.getProperty("error"));
    }
}
