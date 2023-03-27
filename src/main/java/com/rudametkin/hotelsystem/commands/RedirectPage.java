package com.rudametkin.hotelsystem.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectPage implements ICommand {
    private final String pagePath;

    public RedirectPage(String pagePath) {
        this.pagePath = pagePath;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.sendRedirect(request.getContextPath() + pagePath);
    }
}
