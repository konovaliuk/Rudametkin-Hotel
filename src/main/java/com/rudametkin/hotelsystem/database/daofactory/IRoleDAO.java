package com.rudametkin.hotelsystem.database.daofactory;

import com.rudametkin.hotelsystem.entitys.Role;

public interface IRoleDAO {
    void save(Role role) throws DAOException;
    void removeById(int id) throws DAOException;
    Role findById(int id) throws DAOException;
    Role findByName(String name) throws DAOException;
}
