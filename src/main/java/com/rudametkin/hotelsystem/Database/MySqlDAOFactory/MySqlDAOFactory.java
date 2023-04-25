package com.rudametkin.hotelsystem.Database.MySqlDAOFactory;

import com.rudametkin.hotelsystem.Database.DAOFactory.*;

import java.sql.Connection;


public class MySqlDAOFactory extends DAOFactory {

    private static MySqlDAOFactory instance = null;

    private MySqlDAOFactory() {}

    public static MySqlDAOFactory getInstance()
    {
        if(instance == null)
            instance = new MySqlDAOFactory();
        return instance;
    }
    public IUserDAO getUserDAO() {
        return new MySqlUserDAO();
    }

    public IUserDAO getUserDAO(Connection connection) {
        return new MySqlUserDAO(connection);
    }

    public IRoomDAO getRoomDAO() {
        return new MySqlRoomDAO();
    }

    public IRoomDAO getRoomDAO(Connection connection) {
        return new MySqlRoomDAO(connection);
    }

    public IRoomRegisterDAO getRoomRegisterDAO() {
        return new MySqlRoomRegisterDAO();
    }

    public IRoomRegisterDAO getRoomRegisterDAO(Connection connection) {
        return new MySqlRoomRegisterDAO(connection);
    }

    public IBillDAO getBillDAO() {
        return new MySqlBillDAO();
    }

    public IBillDAO getBillDAO(Connection connection) {
        return new MySqlBillDAO(connection);
    }

    public IRoleDAO getRoleDAO() {
        return new MySqlRoleDAO();
    }

    public IRoleDAO getRoleDAO(Connection connection) {
        return new MySqlRoleDAO(connection);
    }

    public IUserRoleDAO getUserRoleDAO() {
        return new MySqlUserRoleDAO();
    }

    public IUserRoleDAO getUserRoleDAO(Connection connection) {
        return new MySqlUserRoleDAO(connection);
    }
}
