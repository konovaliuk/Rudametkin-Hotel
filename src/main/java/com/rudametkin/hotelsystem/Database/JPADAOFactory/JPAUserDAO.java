package com.rudametkin.hotelsystem.Database.JPADAOFactory;

import com.mysql.cj.Session;
import com.mysql.cj.xdevapi.SessionFactory;
import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.Database.DAOFactory.IUserDAO;
import com.rudametkin.hotelsystem.Entitys.User;

import javax.persistence.*;
import java.sql.*;

public class JPAUserDAO implements IUserDAO {

    private EntityManager entityManager = Persistence.createEntityManagerFactory("Hotel").createEntityManager();
    @Override
    public synchronized int save(User user) throws DAOException {
        entityManager.getTransaction().isActive();
        return 1;
    }

    @Override
    public synchronized User findByEmail(String email, Connection con) throws DAOException {

    }

    @Override
    public synchronized User findByPhone(String phone, Connection con) throws DAOException {

    }

    @Override
    public synchronized User findByLogin(String login, Connection con) throws DAOException {

    }
    @Override
    public synchronized User findByLoginPassword(String login, String password, Connection con) throws DAOException {

    }

    @Override
    public synchronized void update(User user, Connection con) throws DAOException {

    }

    @Override
    public synchronized void removeById(int id, Connection con) throws DAOException {

    }
}
