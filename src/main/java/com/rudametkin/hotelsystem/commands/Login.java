package com.rudametkin.hotelsystem.commands;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rudametkin.hotelsystem.DAOFactory.IUserDAO;
import com.rudametkin.hotelsystem.MySqlDAOFactory.MySqlDAOFactory;
import com.rudametkin.hotelsystem.entityObjects.User;

public class Login implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        IUserDAO userDAO = MySqlDAOFactory.getInstance().getUserDAO();
        User user = userDAO.findByLoginPassword(login, password);
        if(user != null) {
            System.out.println(user.getSurname() + " | Correct!");
        } else {
            throw new Exception("Wrong credentials!");
        }
        request.getSession().setAttribute("login", login);
        response.sendRedirect(request.getContextPath() + "/pages/cabinet.jsp");
    }
}
