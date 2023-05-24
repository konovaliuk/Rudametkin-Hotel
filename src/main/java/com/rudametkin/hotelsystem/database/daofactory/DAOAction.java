package com.rudametkin.hotelsystem.database.daofactory;

@FunctionalInterface
public interface DAOAction {
    void apply() throws DAOException;
}
