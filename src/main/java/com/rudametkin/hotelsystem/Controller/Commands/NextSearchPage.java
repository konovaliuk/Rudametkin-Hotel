package com.rudametkin.hotelsystem.Controller.Commands;

import com.rudametkin.hotelsystem.Services.RoomsSearchingService;
import com.rudametkin.hotelsystem.Configs.PagePathConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NextSearchPage implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PagePathConfig pagePathConfig = new PagePathConfig();
        RoomsSearchingService rs = (RoomsSearchingService) request.getSession().getAttribute("searchRooms");
        rs.setPage(rs.getPage() + 1);
        request.getSession().setAttribute("searchRooms", rs);
        response.sendRedirect(request.getContextPath() + pagePathConfig.getProperty("search"));
    }
}
