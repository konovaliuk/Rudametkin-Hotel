package MySqlDAOFactory;

import DAOFactory.DAOException;
import DAOFactory.IUserDAO;
import SqlConnection.MySqlDataSource;
import entityObjects.User;

import java.sql.*;

public class MySqlUserDAO implements IUserDAO {

    private void handleSQLException(SQLException e) throws DAOException {
        if(e.getMessage().equals("MySqlConnectionError"))
            throw new DAOException("MySqlConnectionError");
        else
            throw new DAOException("ExecuteStatementError|users");
    }

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
    public void add(User user) throws DAOException {
        try {
            MySqlDataSource msds = MySqlDataSource.getInstance();
            Connection con = msds.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO users (name, surname, phone, email, login, password, user_info) VALUES (?,?,?,?,?,?,?);");
            setStatementUserBasicParameters(stmt, user);
            stmt.execute();
            msds.releaseConnection(con);
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    private User findByStatementParameter(String statementString, String param) throws DAOException  {
        try {
            MySqlDataSource msds = MySqlDataSource.getInstance();
            Connection con = msds.getConnection();
            CallableStatement stmt = con.prepareCall(statementString);
            stmt.setString(1, param);
            ResultSet resultSet = stmt.executeQuery();
            User user = null;
            if(resultSet.next()) {
                user = new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7));
                user.setInfo(resultSet.getString(8));
            }
            msds.releaseConnection(con);
            return user;
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }
    @Override
    public User findByEmail(String email) throws DAOException
    {
        return findByStatementParameter("SELECT * FROM users WHERE email = ? ;", email);
    }

    @Override
    public User findByPhone(String phone) throws DAOException
    {
        return findByStatementParameter("SELECT * FROM users WHERE phone = ? ;", phone);
    }

    @Override
    public User findByLogin(String login) throws DAOException
    {
        return findByStatementParameter("SELECT * FROM users WHERE login = ? ;", login);
    }

    @Override
    public void update(User user) throws DAOException
    {
        try {
            MySqlDataSource msds = MySqlDataSource.getInstance();;
            Connection con = msds.getConnection();
            PreparedStatement stmt = con.prepareStatement("UPDATE users " +
                    "SET name = ?, surname = ?, phone = ?, email = ?, login = ?, password = ?, user_info = ? " +
                    "WHERE id = ?");
            setStatementUserBasicParameters(stmt, user);
            stmt.setInt(8, user.getId());
            stmt.execute();
            msds.releaseConnection(con);
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    @Override
    public void removeById(int id) throws DAOException
    {
        try {
            MySqlDataSource msds = MySqlDataSource.getInstance();;
            Connection con = msds.getConnection();
            PreparedStatement stmt = con.prepareStatement("DELETE FROM users WHERE id = ?;");
            stmt.setInt(1, id);
            stmt.execute();
            msds.releaseConnection(con);
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
}
