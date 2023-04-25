package com.rudametkin.hotelsystem.Database.TransactionHandling;

import com.rudametkin.hotelsystem.Database.SqlConnection.MySqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCTransactionManager {
    private Connection connection;
    JDBCTransactionHandler() {
        try {
            connection = MySqlDataSource.getInstance().getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException ignore) {}
    }

    public <R> R execute() {

    }
}
