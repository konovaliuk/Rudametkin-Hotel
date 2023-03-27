package com.rudametkin.hotelsystem.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Cabinet implements ICommand{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(request.getSession().isNew() == false)
            response.sendRedirect(request.getContextPath() + "/pages/cabinet.jsp");
        else
            response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
    }
}
