package com.rudametkin.hotelsystem.businessLogic;

import com.rudametkin.hotelsystem.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.DAOFactory.DAOFactory;
import com.rudametkin.hotelsystem.DAOFactory.IUserDAO;
import com.rudametkin.hotelsystem.MySqlDAOFactory.MySqlDAOFactory;
import com.rudametkin.hotelsystem.entityObjects.User;

import java.util.ArrayList;

public class UserLogic {
    private User userEntity;

    public UserLogic() {
        userEntity = null;
    }

    public void authenticateUser(String login, String password) {
        IUserDAO userDAO = MySqlDAOFactory.getInstance().getUserDAO();
        try {
            userEntity = userDAO.findByLoginPassword(login, password);
        } catch (DAOException ignore) {
            userEntity = null;
        }
    }

    public boolean getIsAuthenticated() {
        return userEntity != null;
    }

    public String getName() {
        return (userEntity != null) ? userEntity.getName() : "Error";
    }

    public String getSurname() {
        return (userEntity != null) ? userEntity.getSurname() : "Error";
    }

    public String getEmail() {
        return (userEntity != null) ? userEntity.getEmail() : "Error";
    }

    public String getPhone() {
        return (userEntity != null) ? userEntity.getPhone() : "Error";
    }

    public String getLogin() {
        return (userEntity != null) ? userEntity.getLogin() : "Error";
    }

    public String getInfo() {
        String result = (userEntity != null) ? userEntity.getInfo() : "Error";
        return result.equals("") ? "No info" : result;
    }
}
