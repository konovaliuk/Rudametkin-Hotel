package com.rudametkin.hotelsystem.DAOFactory;

import com.rudametkin.hotelsystem.entityObjects.UserRole;

import java.util.ArrayList;

public interface IUserRoleDAO {
    void add(int userId, int roleId) throws DAOException;
    void remove(int userId, int roleId) throws DAOException;
    ArrayList<UserRole> findByUserId(int userId) throws DAOException;
}
