package com.rudametkin.hotelsystem.database.daofactory;

import java.util.Optional;

public abstract class DAOFactory {
    public abstract IUserDAO getUserDAO();
    public abstract IRoomDAO getRoomDAO();
    public abstract IRoomRegisterDAO getRoomRegisterDAO();
    public abstract IBillDAO getBillDAO();
    public abstract IRoleDAO getRoleDAO();
    public abstract IUserRoleDAO getUserRoleDAO();

    public IUserDAO getUserDAO(Object transaction) { return getUserDAO(); }
    public IRoomDAO getRoomDAO(Object transaction) { return getRoomDAO(); }
    public IRoomRegisterDAO getRoomRegisterDAO(Object transaction) { return getRoomRegisterDAO(); }
    public IBillDAO getBillDAO(Object transaction) { return getBillDAO(); }
    public IRoleDAO getRoleDAO(Object transaction) { return getRoleDAO(); }
    public IUserRoleDAO getUserRoleDAO(Object transaction) { return getUserRoleDAO(); }
}
