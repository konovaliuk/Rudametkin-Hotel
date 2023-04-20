package com.rudametkin.hotelsystem.Database.MySqlDAOFactory;

import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.Database.DAOFactory.IRoomRegisterDAO;
import com.rudametkin.hotelsystem.Database.SqlConnection.MySqlDataSource;
import com.rudametkin.hotelsystem.EntityObjects.RoomRegister;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.*;
import java.util.ArrayList;

public class MySqlRoomRegisterDAO implements IRoomRegisterDAO {
    private void handleSQLException(SQLException e) throws DAOException {
        if(e.getMessage().equals("MySqlConnectionError"))
            throw new DAOException("MySqlConnectionError");
        else
            throw new DAOException("ExecuteStatementError|roomregisters");
    }
    @Override
    public void add(RoomRegister roomRegister) throws DAOException {
        try {
            DataSource msds = MySqlDataSource.getInstance();
            Connection con = msds.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO roomregisters (room_number, status, start_date_time, end_date_time, client_id) " +
                            "VALUES (?,?,?,?,?);");
            stmt.setInt(1, roomRegister.getRoomNumber());
            stmt.setString(2, roomRegister.getStatus().toString());
            stmt.setTimestamp(3, roomRegister.getStartDateTime());
            stmt.setTimestamp(4, roomRegister.getEndDateTime());
            if(roomRegister.getClientId() != -1)
                stmt.setInt(5, roomRegister.getClientId());
            else
                stmt.setNull(5, java.sql.Types.INTEGER);

            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    @Override
    public ArrayList<RoomRegister> findByRoomNumber(int number) throws DAOException {
        try {
            DataSource msds = MySqlDataSource.getInstance();
            Connection con = msds.getConnection();
            CallableStatement stmt = con.prepareCall("SELECT * FROM roomregisters WHERE room_number = ? ;");
            stmt.setInt(1, number);
            ResultSet resultSet = stmt.executeQuery();
            ArrayList<RoomRegister> list = new ArrayList<>();
            while (resultSet.next())
            {
                int client_id = -1;
                if(resultSet.getInt(6) != 0)
                    client_id = resultSet.getInt(6);

                list.add(new RoomRegister(resultSet.getInt(1), resultSet.getInt(2), RoomRegister.RoomStatus.valueOf(resultSet.getString(3)),
                        resultSet.getTimestamp(4),resultSet.getTimestamp(5), client_id));
            }
            stmt.close();
            resultSet.close();
            con.close();
            return list;
        } catch (SQLException e) {
            handleSQLException(e);
            return new ArrayList<>();
        }
    }
    @Override
    public ArrayList<RoomRegister> findByRoomNumberAtCurrentTime(int number) throws DAOException {
        try {
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            DataSource msds = MySqlDataSource.getInstance();
            Connection con = msds.getConnection();
            CallableStatement stmt = con.prepareCall("SELECT * FROM roomregisters WHERE room_number = ? " +
                    "AND start_date_time > ? AND end_date_time < ? ;");
            stmt.setInt(1, number);
            stmt.setTimestamp(2, currentTimestamp);
            stmt.setTimestamp(3, currentTimestamp);

            ResultSet resultSet = stmt.executeQuery();
            ArrayList<RoomRegister> list = new ArrayList<>();
            while (resultSet.next())
            {
                int client_id = -1;
                if(resultSet.getInt(6) != 0)
                    client_id = resultSet.getInt(6);

                list.add(new RoomRegister(resultSet.getInt(1), resultSet.getInt(2), RoomRegister.RoomStatus.valueOf(resultSet.getString(3)),
                        resultSet.getTimestamp(4),resultSet.getTimestamp(5), client_id));
            }
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
