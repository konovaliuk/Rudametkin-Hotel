package com.rudametkin.hotelsystem.businessLogic;

import com.rudametkin.hotelsystem.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.DAOFactory.IUserDAO;
import com.rudametkin.hotelsystem.MySqlDAOFactory.MySqlDAOFactory;
import com.rudametkin.hotelsystem.entityObjects.User;

public class UserService {
    private User userEntity;

    public UserService() {
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
        return (userEntity != null) ? userEntity.getName() : "Null";
    }

    public String getSurname() {
        return (userEntity != null) ? userEntity.getSurname() : "Null";
    }

    public String getEmail() {
        return (userEntity != null) ? userEntity.getEmail() : "Null";
    }

    public String getPhone() {
        return (userEntity != null) ? userEntity.getPhone() : "Null";
    }

    public String getLogin() {
        return (userEntity != null) ? userEntity.getLogin() : "Null";
    }

    public String getInfo() {
        String result = (userEntity != null) ? userEntity.getInfo() : "Null";
        return result.equals("") ? "No info" : result;
    }
}
