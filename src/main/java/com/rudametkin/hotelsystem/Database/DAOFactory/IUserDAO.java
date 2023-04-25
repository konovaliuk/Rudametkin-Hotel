package com.rudametkin.hotelsystem.Database.DAOFactory;

import com.rudametkin.hotelsystem.Entitys.User;

import java.sql.Connection;

public interface IUserDAO {
    int save(User user) throws DAOException;
    User findByLogin(String login) throws DAOException;
    User findByEmail(String email) throws DAOException;
    User findByPhone(String phone) throws DAOException;
    User findByLoginPassword(String login, String password) throws DAOException;
    void update(User user) throws DAOException;
    void removeById(int id) throws DAOException;
}
