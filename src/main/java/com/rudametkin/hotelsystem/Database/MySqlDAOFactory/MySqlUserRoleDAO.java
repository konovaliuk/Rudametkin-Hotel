package com.rudametkin.hotelsystem.Database.MySqlDAOFactory;

import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.Database.DAOFactory.IUserRoleDAO;
import com.rudametkin.hotelsystem.Database.SqlConnection.MySqlDataSource;
import com.rudametkin.hotelsystem.Entitys.UserRole;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserRoleDAO extends MySqlTransactionalDAO implements IUserRoleDAO {
    public MySqlUserRoleDAO() {
        connection = null;
    }
    public MySqlUserRoleDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(int userId, int roleId) throws DAOException {
        executeInTransaction(() -> {
            try(PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO userroles (user_id, role_id) VALUES (?, ?);")) {
                stmt.setInt(1, userId);
                stmt.setInt(2, roleId);
                stmt.execute();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        });

    }
    @Override
    public void remove(int userId, int roleId) throws DAOException {
        executeInTransaction(() -> {
            try(PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM userroles WHERE user_id = ? AND role_id = ? ;")) {
                stmt.setInt(1, userId);
                stmt.setInt(2, roleId);
                stmt.execute();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        });
    }
    @Override
    public List<UserRole> findByUserId(int userId) throws DAOException {
        return executeInTransaction(() -> {
            ResultSet resultSet = null;
            List<UserRole> list = new ArrayList<>();
            try(CallableStatement stmt = connection.prepareCall("SELECT * FROM userroles WHERE user_id = ? ;")) {
                stmt.setInt(1, userId);
                resultSet = stmt.executeQuery();
                while(resultSet.next())
                    list.add(new UserRole(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3)));
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            } finally {
                if(resultSet != null)
                    try {
                        resultSet.close();
                    } catch (SQLException ignore) {}
                return list;
            }
        });
    }
}
