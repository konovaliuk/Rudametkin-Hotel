package com.rudametkin.hotelsystem.Database.MySqlDAOFactory;

import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.Database.DAOFactory.IRoleDAO;
import com.rudametkin.hotelsystem.Database.SqlConnection.MySqlDataSource;
import com.rudametkin.hotelsystem.Entitys.Role;

import java.sql.*;

public class MySqlRoleDAO extends MySqlTransactionalDAO implements IRoleDAO {
    public MySqlRoleDAO() {
        connection = null;
    }

    public MySqlRoleDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Role role) throws DAOException {
        executeInTransaction(() -> {
            try(PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO roles (name) VALUES (?);")) {
                stmt.setString(1, role.getName());
                stmt.execute();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        });
    }
    @Override
    public void removeByName(String name) throws DAOException {
        executeInTransaction(() -> {
            try(PreparedStatement stmt = connection.prepareStatement("DELETE FROM roles WHERE name = ?;")) {
                stmt.setString(1, name);
                stmt.execute();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        });
    }
    @Override
    public Role findById(int id) throws DAOException {
        return executeInTransaction(() -> {
            ResultSet resultSet = null;
            Role role = null;
            try(CallableStatement stmt = connection.prepareCall("SELECT * FROM roles WHERE id = ? ;")) {
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
        });
    }

    @Override
    public Role findByName(String name) throws DAOException {
        return executeInTransaction(() -> {
            ResultSet resultSet = null;
            Role role = null;
            try(CallableStatement stmt = connection.prepareCall("SELECT * FROM roles WHERE name = ? ;")) {
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
        });
    }
}
