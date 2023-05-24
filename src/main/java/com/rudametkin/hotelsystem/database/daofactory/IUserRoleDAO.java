package com.rudametkin.hotelsystem.database.daofactory;

import com.rudametkin.hotelsystem.entitys.UserRole;

import java.util.List;

public interface IUserRoleDAO {
    void save(UserRole userRole) throws DAOException;
    void removeById(int userRoleId) throws DAOException;
    List<UserRole> findByUserId(int userId) throws DAOException;
}
