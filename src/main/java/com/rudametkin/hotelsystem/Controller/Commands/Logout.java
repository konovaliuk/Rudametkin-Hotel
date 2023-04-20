package com.rudametkin.hotelsystem.Controller.Commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/home");
    }
}
