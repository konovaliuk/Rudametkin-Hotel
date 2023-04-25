package com.rudametkin.hotelsystem.Database.DAOFactory;

import com.rudametkin.hotelsystem.Entitys.UserRole;

import java.sql.Connection;
import java.util.List;

public interface IUserRoleDAO {
    void save(int userId, int roleId) throws DAOException;
    void remove(int userId, int roleId) throws DAOException;
    List<UserRole> findByUserId(int userId) throws DAOException;
}
