package com.rudametkin.hotelsystem.Database.TransactionHandling;

import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.Database.SqlConnection.MySqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTransactionManager {
    @FunctionalInterface
    public interface JDBCTransactionalFunction<R> {
        R apply(Connection connection) throws DAOException;
    }

    @FunctionalInterface
    public interface JDBCTransactionalAction {
        void apply(Connection connection) throws DAOException;
    }
    private Connection connection;
    public JDBCTransactionManager() {}

    private void beginTransaction() throws SQLException {
        connection = MySqlDataSource.getInstance().getConnection();
        connection.setAutoCommit(false);
    }

    private void commitTransaction() {
        try {
            connection.commit();
        } catch (SQLException ignore) {}
        try {
            connection.close();
        } catch (SQLException ignore) {}
    }

    private void rollbackTransaction() {
        try {
            connection.rollback();
        } catch (SQLException ignore) {}
        try {
            connection.close();
        } catch (SQLException ignore) {}
    }

    public <R> R execute(JDBCTransactionalFunction<R> function) {
        R result = null;

        try {
            beginTransaction();
            result = function.apply(connection);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
        }

        return result;
    }

    public void execute(JDBCTransactionalAction action) {
        try {
            beginTransaction();
            action.apply(connection);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
        }
    }
}
