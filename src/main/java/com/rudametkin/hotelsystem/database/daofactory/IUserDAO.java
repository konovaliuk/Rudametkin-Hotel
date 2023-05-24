package com.rudametkin.hotelsystem.database.daofactory;

import com.rudametkin.hotelsystem.entitys.User;

public interface IUserDAO {
    int save(User user) throws DAOException;
    User findByLogin(String login) throws DAOException;
    User findByEmail(String email) throws DAOException;
    User findByPhone(String phone) throws DAOException;
    User findByLoginPassword(String login, String password) throws DAOException;
    void update(User user) throws DAOException;
    void removeById(int id) throws DAOException;
}
