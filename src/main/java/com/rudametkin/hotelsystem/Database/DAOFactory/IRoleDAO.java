package com.rudametkin.hotelsystem.Database.DAOFactory;

import com.rudametkin.hotelsystem.Entitys.Role;

import java.sql.Connection;

public interface IRoleDAO {
    void save(Role role) throws DAOException;
    void removeById(int id) throws DAOException;
    Role findById(int id) throws DAOException;
    Role findByName(String name) throws DAOException;
}
