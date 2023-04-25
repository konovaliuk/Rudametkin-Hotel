package com.rudametkin.hotelsystem.Database.DAOFactory;

@FunctionalInterface
public interface DAOAction {
    void apply() throws DAOException;
}
