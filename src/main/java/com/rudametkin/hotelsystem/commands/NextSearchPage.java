package com.rudametkin.hotelsystem.commands;

import com.rudametkin.hotelsystem.businessLogic.RoomsService;
import com.rudametkin.hotelsystem.configs.Config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NextSearchPage implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Config pagePathConfig = new Config("/resources/properties/", "pagepath.properties");
        RoomsService rs = (RoomsService) request.getSession().getAttribute("searchRooms");
        rs.setPage(rs.getPage() + 1);
        request.getSession().setAttribute("searchRooms", rs);
        response.sendRedirect(request.getContextPath() + pagePathConfig.getProperty("search"));
    }
}
