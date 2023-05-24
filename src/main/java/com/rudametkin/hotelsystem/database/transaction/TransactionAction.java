package com.rudametkin.hotelsystem.database.transaction;

import com.rudametkin.hotelsystem.database.daofactory.DAOException;

import java.sql.Connection;

@FunctionalInterface
public interface TransactionAction {
    void apply(Object transaction) throws DAOException;
}