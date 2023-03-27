package com.rudametkin.hotelsystem.MySqlDAOFactory;

import com.rudametkin.hotelsystem.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.DAOFactory.IUserRoleDAO;
import com.rudametkin.hotelsystem.SqlConnection.MySqlDataSource;
import com.rudametkin.hotelsystem.entityObjects.Role;
import com.rudametkin.hotelsystem.entityObjects.User;
import com.rudametkin.hotelsystem.entityObjects.UserRole;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.*;
import java.util.ArrayList;

public class MySqlUserRoleDAO implements IUserRoleDAO {

    private void handleSQLException(SQLException e) throws DAOException {
        if(e.getMessage().equals("MySqlConnectionError"))
            throw new DAOException("MySqlConnectionError");
        else
            throw new DAOException("ExecuteStatementError|userroles");
    }
    @Override
    public void add(int userId, int roleId) throws DAOException {
        try {
            DataSource msds = MySqlDataSource.getInstance();
            Connection con = msds.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO userroles (user_id, role_id) VALUES (?, ?);");
            stmt.setInt(1, userId);
            stmt.setInt(2, roleId);
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    @Override
    public void remove(int userId, int roleId) throws DAOException {
        try {
            DataSource msds = MySqlDataSource.getInstance();;
            Connection con = msds.getConnection();
            PreparedStatement stmt = con.prepareStatement("DELETE FROM userroles WHERE user_id = ? AND role_id = ? ;");
            stmt.setInt(1, userId);
            stmt.setInt(2, roleId);
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    @Override
    public ArrayList<UserRole> findByUserId(int userId) throws DAOException {
        try {
            DataSource msds = MySqlDataSource.getInstance();
            Connection con = msds.getConnection();
            CallableStatement stmt = con.prepareCall("SELECT * FROM userroles WHERE user_id = ? ;");
            stmt.setInt(1, userId);
            ResultSet resultSet = stmt.executeQuery();
            ArrayList<UserRole> list = new ArrayList<>();
            while(resultSet.next())
                list.add(new UserRole(resultSet.getInt(1), resultSet.getInt(2)));
            stmt.close();
            resultSet.close();
            con.close();
            return list;
        } catch (SQLException e) {
            handleSQLException(e);
            return new ArrayList<>();
        }
    }
}
