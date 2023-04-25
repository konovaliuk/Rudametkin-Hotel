package com.rudametkin.hotelsystem.Database.DAOFactory;

@FunctionalInterface
public interface DAOSupplier<R> {
    R apply() throws DAOException;
}
