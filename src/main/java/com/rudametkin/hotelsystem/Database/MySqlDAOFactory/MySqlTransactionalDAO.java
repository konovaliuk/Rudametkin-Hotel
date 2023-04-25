package com.rudametkin.hotelsystem.Database.MySqlDAOFactory;

import com.rudametkin.hotelsystem.Database.DAOFactory.DAOAction;
import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.Database.DAOFactory.DAOSupplier;
import com.rudametkin.hotelsystem.Database.SqlConnection.MySqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class MySqlTransactionalDAO {
    protected Connection connection = null;

    protected <R> R executeInTransaction(DAOSupplier<R> supplier) throws DAOException {
        boolean isContinueOfTransaction = true;
        try {
            if(connection == null || connection.isClosed()) {
                connection = MySqlDataSource.getInstance().getConnection();
                connection.setAutoCommit(false);
                isContinueOfTransaction = false;
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
        R result = null;
        try {
            result = supplier.apply();
        } catch (DAOException e) {
            if(isContinueOfTransaction == false)
                rollbackOwnTransaction();

            throw e;
        } finally {
            if(isContinueOfTransaction == false)
                commitOwnTransaction();
        }

        return result;
    }


    protected void executeInTransaction(DAOAction action) throws DAOException {
        boolean isContinueOfTransaction = true;
        try {
            if(connection == null || connection.isClosed()) {
                connection = MySqlDataSource.getInstance().getConnection();
                connection.setAutoCommit(false);
                isContinueOfTransaction = false;
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

        try {
            action.apply();
        } catch (DAOException e) {
            if(isContinueOfTransaction == false)
                rollbackOwnTransaction();

            throw e;
        } finally {
            if(isContinueOfTransaction == false)
                commitOwnTransaction();
        }
    }

    private void rollbackOwnTransaction() {
        try {
            connection.rollback();
        } catch (SQLException ignore) {}
        try {
            connection.close();
        } catch (SQLException ignore) {}
    }

    private void commitOwnTransaction() {
        try {
            connection.commit();
        } catch (SQLException ignore) {}
        try {
            connection.close();
        } catch (SQLException ignore) {}
    }
}
