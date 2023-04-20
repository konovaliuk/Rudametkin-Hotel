package com.rudametkin.hotelsystem.Database.DAOFactory;

import com.rudametkin.hotelsystem.EntityObjects.Role;

import java.sql.Connection;

public interface IRoleDAO {
    void add(Role role, Connection connection) throws DAOException;
    void removeByName(String name, Connection connection) throws DAOException;
    Role findById(int id, Connection connection) throws DAOException;
    Role findByName(String name, Connection connection) throws DAOException;
}
