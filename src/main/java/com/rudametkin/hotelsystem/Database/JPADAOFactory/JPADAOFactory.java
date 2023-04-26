package com.rudametkin.hotelsystem.Database.JPADAOFactory;

import com.rudametkin.hotelsystem.Database.DAOFactory.*;
import com.rudametkin.hotelsystem.Database.MySqlDAOFactory.*;

public class JPADAOFactory extends DAOFactory {

    private static JPADAOFactory instance = null;

    private JPADAOFactory() {}

    public static JPADAOFactory getInstance()
    {
        if(instance == null)
            instance = new JPADAOFactory();
        return instance;
    }
    public IUserDAO getUserDAO() {
        return new JPAUserDAO();
    }

    public IRoomDAO getRoomDAO() {
        return new JPARoomDAO();
    }

    public IRoomRegisterDAO getRoomRegisterDAO() {
        return new JPARoomRegisterDAO();
    }

    public IBillDAO getBillDAO() {
        return new JPABillDAO();
    }

    public IRoleDAO getRoleDAO() {
        return new JPARoleDAO();
    }

    public IUserRoleDAO getUserRoleDAO() {
        return new JPAUserRoleDAO();
    }
}