package com.rudametkin.hotelsystem.DAOFactory;

import com.rudametkin.hotelsystem.entityObjects.User;

public interface IUserDAO {
    void add(User user) throws DAOException;
    User findByEmail(String email) throws DAOException;
    User findByLogin(String login) throws DAOException;
    User findByPhone(String phone) throws DAOException;
    User findByLoginPassword(String login, String password) throws DAOException;
    void update(User user) throws DAOException;
    void removeById(int id) throws DAOException;
}
