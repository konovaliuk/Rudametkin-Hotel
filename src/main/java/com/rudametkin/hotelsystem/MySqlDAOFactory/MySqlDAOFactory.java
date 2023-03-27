package com.rudametkin.hotelsystem.MySqlDAOFactory;

import com.rudametkin.hotelsystem.DAOFactory.*;
import com.rudametkin.hotelsystem.SqlConnection.MySqlDataSource;


public class MySqlDAOFactory extends DAOFactory {

    private static MySqlDAOFactory instance = null;
    private static IUserDAO userDAOInstance = null;
    private static IRoomDAO roomDAOInstance = null;
    private static IRoomRegisterDAO roomResisterDAOInstance = null;
    private static IBillDAO billDAOInstance = null;
    private static IRoleDAO roleDAOInstance = null;
    private static IUserRoleDAO userRoleDAOInstance = null;

    private MySqlDAOFactory() {}

    public static MySqlDAOFactory getInstance()
    {
        if(instance == null)
            instance = new MySqlDAOFactory();
        return instance;
    }
    public IUserDAO getUserDAO()
    {
        if(userDAOInstance == null)
            userDAOInstance = new MySqlUserDAO();
        return userDAOInstance;
    }

    public IRoomDAO getRoomDAO()
    {
        if(roomDAOInstance == null)
            roomDAOInstance = new MySqlRoomDAO();
        return roomDAOInstance;
    }

    public IRoomRegisterDAO getRoomRegisterDAO()
    {
        if(roomResisterDAOInstance == null)
            roomResisterDAOInstance = new MySqlRoomRegisterDAO();
        return roomResisterDAOInstance;
    }

    public IBillDAO getBillDAO()
    {
        if(billDAOInstance == null)
            billDAOInstance = new MySqlBillDAO();
        return billDAOInstance;
    }

    public IRoleDAO getRoleDAO()
    {
        if(roleDAOInstance == null)
            roleDAOInstance = new MySqlRoleDAO();
        return roleDAOInstance;
    }

    public IUserRoleDAO getUserRoleDAO()
    {
        if(userRoleDAOInstance == null)
            userRoleDAOInstance = new MySqlUserRoleDAO();
        return userRoleDAOInstance;
    }
}
