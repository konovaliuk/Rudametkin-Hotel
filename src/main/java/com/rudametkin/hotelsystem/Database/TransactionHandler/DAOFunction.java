package com.rudametkin.hotelsystem.Database.TransactionHandler;

import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;

@FunctionalInterface
public interface DAOFunction<T, R> {
    R apply(T t) throws DAOException;
}