package com.rudametkin.hotelsystem.Database.TransactionHandler;

import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;

@FunctionalInterface
public interface DAOConsumer <T> {
    void apply(T t) throws DAOException;
}
