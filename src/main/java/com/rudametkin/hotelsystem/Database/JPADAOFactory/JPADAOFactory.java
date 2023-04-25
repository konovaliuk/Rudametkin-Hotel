package com.rudametkin.hotelsystem.Database.JPADAOFactory;

import com.rudametkin.hotelsystem.Database.DAOFactory.*;
import com.rudametkin.hotelsystem.Database.MySqlDAOFactory.*;

public class JPADAOFactory extends DAOFactory {

    private static JPADAOFactory instance = null;
    private static IUserDAO userDAOInstance = null;
    private static IRoomDAO roomDAOInstance = null;
    private static IRoomRegisterDAO roomResisterDAOInstance = null;
    private static IBillDAO billDAOInstance = null;
    private static IRoleDAO roleDAOInstance = null;
    private static IUserRoleDAO userRoleDAOInstance = null;

    private JPADAOFactory() {}

    public static JPADAOFactory getInstance()
    {
        if(instance == null)
            instance = new JPADAOFactory();
        return instance;
    }
    public IUserDAO getUserDAO()
    {
        if(userDAOInstance == null)
            userDAOInstance = new JPAUserDAO();
        return userDAOInstance;
    }

    public IRoomDAO getRoomDAO()
    {
        if(roomDAOInstance == null)
            roomDAOInstance = new JPARoomDAO();
        return roomDAOInstance;
    }

    public IRoomRegisterDAO getRoomRegisterDAO()
    {
        if(roomResisterDAOInstance == null)
            roomResisterDAOInstance = new JPARoomRegisterDAO();
        return roomResisterDAOInstance;
    }

    public IBillDAO getBillDAO()
    {
        if(billDAOInstance == null)
            billDAOInstance = new JPABillDAO();
        return billDAOInstance;
    }

    public IRoleDAO getRoleDAO()
    {
        if(roleDAOInstance == null)
            roleDAOInstance = new JPARoleDAO();
        return roleDAOInstance;
    }

    public IUserRoleDAO getUserRoleDAO()
    {
        if(userRoleDAOInstance == null)
            userRoleDAOInstance = new JPAUserRoleDAO();
        return userRoleDAOInstance;
    }
}