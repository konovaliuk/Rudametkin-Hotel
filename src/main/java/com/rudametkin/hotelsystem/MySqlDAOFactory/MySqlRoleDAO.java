package com.rudametkin.hotelsystem.MySqlDAOFactory;

import com.rudametkin.hotelsystem.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.DAOFactory.IRoleDAO;
import com.rudametkin.hotelsystem.SqlConnection.MySqlDataSource;
import com.rudametkin.hotelsystem.entityObjects.Role;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.*;

public class MySqlRoleDAO implements IRoleDAO {

    private void handleSQLException(SQLException e) throws DAOException {
        if(e.getMessage().equals("MySqlConnectionError"))
            throw new DAOException("MySqlConnectionError");
        else
            throw new DAOException("ExecuteStatementError|roles");
    }
    @Override
    public void add(Role role) throws DAOException {
        try {
            DataSource msds = MySqlDataSource.getInstance();
            Connection con = msds.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO roles (name) VALUES (?);");
            stmt.setString(1, role.getRoleName());
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    @Override
    public void removeByName(String name) throws DAOException {
        try {
            DataSource msds = MySqlDataSource.getInstance();;
            Connection con = msds.getConnection();
            PreparedStatement stmt = con.prepareStatement("DELETE FROM roles WHERE name = ?;");
            stmt.setString(1, name);
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    @Override
    public Role findByName(String roleName) throws DAOException {
        try {
            DataSource msds = MySqlDataSource.getInstance();;
            Connection con = msds.getConnection();
            CallableStatement stmt = con.prepareCall("SELECT * FROM roles WHERE name = ? ;");
            stmt.setString(1, roleName);
            ResultSet resultSet = stmt.executeQuery();
            Role role = null;
            if(resultSet.next())
                role = new Role(resultSet.getInt(1), resultSet.getString(2));
            stmt.close();
            resultSet.close();
            con.close();
            return role;
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }
}
