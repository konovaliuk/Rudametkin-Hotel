package com.rudametkin.hotelsystem.Database.DAOFactory;

import com.rudametkin.hotelsystem.Entitys.UserRole;

import java.sql.Connection;
import java.util.List;

public interface IUserRoleDAO {
    void save(UserRole userRole) throws DAOException;
    void removeById(int userRoleId) throws DAOException;
    List<UserRole> findByUserId(int userId) throws DAOException;
}
