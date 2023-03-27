package com.rudametkin.hotelsystem.DAOFactory;

import com.rudametkin.hotelsystem.entityObjects.Role;

public interface IRoleDAO {
    void add(Role role) throws DAOException;
    void removeByName(String name) throws DAOException;
    Role findByName(String roleName) throws DAOException;
}
