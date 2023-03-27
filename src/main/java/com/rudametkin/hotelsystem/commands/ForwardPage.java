package com.rudametkin.hotelsystem.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardPage implements ICommand{
    private final String pagePath;

    public ForwardPage(String pagePath) {
        this.pagePath = pagePath;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher(pagePath).forward(request, response);
    }
}
