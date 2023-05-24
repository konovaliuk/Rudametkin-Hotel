package com.rudametkin.hotelsystem.database.daofactory;

@FunctionalInterface
public interface DAOSupplier<R> {
    R apply() throws DAOException;
}
