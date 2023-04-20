package com.rudametkin.hotelsystem.Database.MySqlDAOFactory;

import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.Database.DAOFactory.IUserDAO;
import com.rudametkin.hotelsystem.Database.SqlConnection.MySqlDataSource;
import com.rudametkin.hotelsystem.EntityObjects.User;

import java.sql.*;

public class MySqlUserDAO implements IUserDAO {

    private void setStatementUserBasicParameters(PreparedStatement stmt, User user) throws SQLException {
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getSurname());
        stmt.setString(3, user.getPhone());
        stmt.setString(4, user.getEmail());
        stmt.setString(5, user.getLogin());
        stmt.setString(6, user.getPassword());
        stmt.setString(7, user.getInfo());
    }
    @Override
    public int add(User user, Connection con) throws DAOException {
        int generatedKey = -1;
        ResultSet generatedKeys = null;
        try(PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO users (name, surname, phone, email, login, password, user_info) VALUES (?,?,?,?,?,?,?);",
                     Statement.RETURN_GENERATED_KEYS)) {
            setStatementUserBasicParameters(stmt, user);

            stmt.executeUpdate();
            generatedKeys = stmt.getGeneratedKeys();
            generatedKeys.next();
            generatedKey = generatedKeys.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            if(generatedKeys != null)
                try {
                    generatedKeys.close();
                } catch (SQLException ignore) {}
            return generatedKey;
        }
    }

    private User findByStatementParameter(String statementString, String param, Connection con) throws DAOException  {
        ResultSet resultSet = null;
        User user = null;
        try(CallableStatement stmt = con.prepareCall(statementString);) {
            stmt.setString(1, param);
            resultSet = stmt.executeQuery();
            if(resultSet.next()) {
                user = new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7));
                user.setInfo(resultSet.getString(8));
            }
        } catch (SQLException e) {
           throw new DAOException(e.getMessage());
        } finally {
            if(resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException ignore) {}
            return user;
        }
    }
    @Override
    public User findByEmail(String email, Connection con) throws DAOException {
        return findByStatementParameter("SELECT * FROM users WHERE email = ? ;", email, con);
    }

    @Override
    public User findByPhone(String phone, Connection con) throws DAOException {
        return findByStatementParameter("SELECT * FROM users WHERE phone = ? ;", phone, con);
    }

    @Override
    public User findByLogin(String login, Connection con) throws DAOException {
        return findByStatementParameter("SELECT * FROM users WHERE login = ? ;", login, con);
    }
    @Override
    public User findByLoginPassword(String login, String password, Connection con) throws DAOException {
        User user = null;
        ResultSet resultSet = null;
        try(CallableStatement stmt = con.prepareCall("SELECT * FROM users WHERE login = ? AND password = ? ;")) {
            stmt.setString(1, login);
            stmt.setString(2, password);
            resultSet = stmt.executeQuery();
            if(resultSet.next()) {
                user = new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7));
                user.setInfo(resultSet.getString(8));
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            if(resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException ignore) {}
            return user;
        }
    }

    @Override
    public void update(User user, Connection con) throws DAOException {
        try(PreparedStatement stmt = con.prepareStatement("UPDATE users " +
                "SET name = ?, surname = ?, phone = ?, email = ?, login = ?, password = ?, user_info = ? " +
                "WHERE id = ?")) {
            setStatementUserBasicParameters(stmt, user);
            stmt.setInt(8, user.getId());
            stmt.execute();
        } catch (SQLException e) {
           throw new DAOException(e.getMessage());
        }
    }

    @Override
    public void removeById(int id, Connection con) throws DAOException {
        try(PreparedStatement stmt = con.prepareStatement("DELETE FROM users WHERE id = ?;")) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }
}
