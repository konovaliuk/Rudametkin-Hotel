package com.rudametkin.hotelsystem.Database.DAOFactory;

import com.rudametkin.hotelsystem.EntityObjects.User;

import java.sql.Connection;

public interface IUserDAO {
    int add(User user, Connection connection) throws DAOException;
    User findByLogin(String login, Connection connection) throws DAOException;
    User findByEmail(String email, Connection connection) throws DAOException;
    User findByPhone(String phone, Connection connection) throws DAOException;
    User findByLoginPassword(String login, String password, Connection connection) throws DAOException;
    void update(User user, Connection connection) throws DAOException;
    void removeById(int id, Connection connection) throws DAOException;
}
