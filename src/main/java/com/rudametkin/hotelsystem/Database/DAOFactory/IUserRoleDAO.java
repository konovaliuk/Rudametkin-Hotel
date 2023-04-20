package com.rudametkin.hotelsystem.Database.DAOFactory;

import com.rudametkin.hotelsystem.EntityObjects.UserRole;

import java.sql.Connection;
import java.util.List;

public interface IUserRoleDAO {
    void add(int userId, int roleId, Connection con) throws DAOException;
    void remove(int userId, int roleId, Connection con) throws DAOException;
    List<UserRole> findByUserId(int userId, Connection con) throws DAOException;
}
