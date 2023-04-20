package com.rudametkin.hotelsystem.Controller.Commands;

import com.rudametkin.hotelsystem.Configs.PagePathConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Error implements ICommand{

    private final String errorMessage;
    public Error() {
        errorMessage = "";
    }
    public Error(String errorMsg) {
        errorMessage = errorMsg;
    }
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.sendRedirect(request.getContextPath() + "/error?error=" + errorMessage);
    }
}
