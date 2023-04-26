package com.rudametkin.hotelsystem.Database.JPADAOFactory;

import com.mysql.cj.Session;
import com.mysql.cj.xdevapi.SessionFactory;
import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.Database.DAOFactory.IUserDAO;
import com.rudametkin.hotelsystem.Entitys.User;

import javax.persistence.*;
import java.sql.*;
import java.util.List;

public class JPAUserDAO implements IUserDAO {

    private EntityManager entityManager = Persistence.createEntityManagerFactory("Hotel").createEntityManager();
    @Override
    public synchronized int save(User user) throws DAOException {
        try {
            entityManager.persist(user);
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
        return user.getId();
    }

    private User findUserByQueryWithParameter(String query, String param) {
        List<User> results = entityManager.createQuery(query, User.class)
                .setParameter("param", param)
                .getResultList();
        return (results.size() == 0) ? null : results.get(0);
    }

    @Override
    public synchronized User findByEmail(String email) throws DAOException {
        return findUserByQueryWithParameter("SELECT u from User u WHERE u.email =: param ", email);
    }

    @Override
    public synchronized User findByPhone(String phone) throws DAOException {
        return findUserByQueryWithParameter("SELECT u from User u WHERE u.phone =: param ", phone);
    }

    @Override
    public synchronized User findByLogin(String login) throws DAOException {
        return findUserByQueryWithParameter("SELECT u from User u WHERE u.login =: param ", login);
    }
    @Override
    public synchronized User findByLoginPassword(String login, String password) throws DAOException {
        List<User> results = entityManager.createQuery("SELECT u from User u WHERE u.login =: login AND u.password =: password", User.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .getResultList();
        return (results.size() == 0) ? null : results.get(0);
    }

    @Override
    public synchronized void update(User user) throws DAOException {
        try {
            entityManager.merge(user);
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public synchronized void removeById(int id) throws DAOException {
        User user = entityManager.find(User.class, id);
        if(user != null)
            entityManager.remove(user);
    }
}
