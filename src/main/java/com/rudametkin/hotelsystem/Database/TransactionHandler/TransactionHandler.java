package com.rudametkin.hotelsystem.Database.TransactionHandler;

import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.Database.SqlConnection.MySqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionHandler {


    public TransactionHandler() {}

    public <T> T execute(DAOFunction<Connection, T> daoInteraction) {
        Connection con = null;
        T reutrnObject = null;
        try {
            con = MySqlDataSource.getInstance().getConnection();
            con.setAutoCommit(false);
        } catch (SQLException e) {
            return null;
        }

        try {
            reutrnObject = daoInteraction.apply(con);
        } catch (DAOException e) {
            try {
                con.rollback();
            } catch (Exception ignore) {}
        }


        try {
            con.commit();
        } catch (SQLException e) {
            try {
                con.close();
            } catch (SQLException ignore) {}
        }

        return reutrnObject;
    }

    public void execute(DAOConsumer<Connection> daoInteraction) {
        Connection con = null;
        try {
            con = MySqlDataSource.getInstance().getConnection();
            con.setAutoCommit(false);
        } catch (SQLException e) {
            return;
        }

        try {
            daoInteraction.apply(con);
        } catch (DAOException e) {
            try {
                con.rollback();
            } catch (Exception ignore) {}
        }


        try {
            con.commit();
        } catch (SQLException e) {
            try {
                con.close();
            } catch (SQLException ignore) {}
        }
    }
}
