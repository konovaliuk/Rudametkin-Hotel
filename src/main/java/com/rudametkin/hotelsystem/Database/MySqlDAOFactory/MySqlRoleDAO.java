package com.rudametkin.hotelsystem.Database.MySqlDAOFactory;

import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.Database.DAOFactory.IRoleDAO;
import com.rudametkin.hotelsystem.Database.SqlConnection.MySqlDataSource;
import com.rudametkin.hotelsystem.EntityObjects.Role;

import java.sql.*;

public class MySqlRoleDAO implements IRoleDAO {

    @Override
    public void add(Role role, Connection con) throws DAOException {
        try(PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO roles (name) VALUES (?);")) {
            stmt.setString(1, role.getRoleName());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }
    @Override
    public void removeByName(String name, Connection con) throws DAOException {
        try(PreparedStatement stmt = con.prepareStatement("DELETE FROM roles WHERE name = ?;")) {
            stmt.setString(1, name);
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }
    @Override
    public Role findById(int id, Connection con) throws DAOException {
        ResultSet resultSet = null;
        Role role = null;
        try(CallableStatement stmt = con.prepareCall("SELECT * FROM roles WHERE id = ? ;")) {
            stmt.setInt(1, id);
            resultSet = stmt.executeQuery();
            if(resultSet.next())
                role = new Role(resultSet.getInt(1), resultSet.getString(2));
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            if(resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException ignore) {}
            return role;
        }
    }

    @Override
    public Role findByName(String name, Connection con) throws DAOException {
        ResultSet resultSet = null;
        Role role = null;
        try(CallableStatement stmt = con.prepareCall("SELECT * FROM roles WHERE name = ? ;")) {
            stmt.setString(1, name);
            resultSet = stmt.executeQuery();
            if(resultSet.next())
                role = new Role(resultSet.getInt(1), resultSet.getString(2));
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            if(resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException ignore) {}
            return role;
        }
    }
}
