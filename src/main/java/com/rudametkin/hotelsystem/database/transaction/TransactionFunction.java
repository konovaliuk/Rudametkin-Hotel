package com.rudametkin.hotelsystem.database.transaction;

import com.rudametkin.hotelsystem.database.daofactory.DAOException;

@FunctionalInterface
public interface TransactionFunction<R> {
    R apply(Object transaction) throws DAOException;
}