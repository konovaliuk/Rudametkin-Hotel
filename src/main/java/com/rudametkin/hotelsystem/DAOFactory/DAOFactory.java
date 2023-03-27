package com.rudametkin.hotelsystem.DAOFactory;

public abstract class DAOFactory {
    public abstract IUserDAO getUserDAO();
    public abstract IRoomDAO getRoomDAO();
    public abstract IRoomRegisterDAO getRoomRegisterDAO();
    public abstract IBillDAO getBillDAO();
    public abstract IRoleDAO getRoleDAO();
    public abstract IUserRoleDAO getUserRoleDAO();
}
