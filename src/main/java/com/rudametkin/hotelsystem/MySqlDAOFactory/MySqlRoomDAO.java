package com.rudametkin.hotelsystem.MySqlDAOFactory;

import com.rudametkin.hotelsystem.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.DAOFactory.IRoomDAO;
import com.rudametkin.hotelsystem.SqlConnection.MySqlDataSource;
import com.rudametkin.hotelsystem.entityObjects.Room;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.*;
import java.util.ArrayList;

public class MySqlRoomDAO implements IRoomDAO {
    private void handleSQLException(SQLException e) throws DAOException {
        if(e.getMessage().equals("MySqlConnectionError"))
            throw new DAOException("MySqlConnectionError");
        else
            throw new DAOException("ExecuteStatementError|rooms");
    }
    @Override
    public void upadte(Room room) throws DAOException {
        try {
            DataSource msds = MySqlDataSource.getInstance();
            Connection con = msds.getConnection();
            PreparedStatement stmt = con.prepareStatement("UPDATE rooms " +
                    "SET type = ?, mini_bar = ?, tv = ?, dryer = ?, price = ?, single_beds_amount = ?, double_beds_amount = ?, rooms_amount = ? " +
                    "WHERE number = ?;");
            stmt.setString(1, room.getType().toString());
            stmt.setBoolean(2, room.getHasMiniBar());
            stmt.setBoolean(3, room.getHasTV());
            stmt.setBoolean(4, room.getHasDryer());
            stmt.setFloat(5, room.getPrice());
            stmt.setInt(6, room.getSingleBedsAmount());
            stmt.setInt(7, room.getDoubleBedsAmount());
            stmt.setInt(8, room.getNumber());
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    @Override
    public ArrayList<Room> findFreeRoomsByParams(Room.RoomType type, int capacity, int bedsAmount, float maxPrice) throws DAOException {
        try {
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            DataSource msds = MySqlDataSource.getInstance();
            Connection con = msds.getConnection();
            CallableStatement stmt = con.prepareCall(
                    "SELECT * FROM rooms WHERE type = ? AND (2*double_beds_amount+single_beds_amount) >= ? " +
                            "AND (double_beds_amount+single_beds_amount) >= ? AND price <= ? AND " +
                            "(SELECT COUNT(*) FROM roomregisters WHERE (end_date_time > ?) AND (start_date_time < ?) " +
                            "AND room_number = rooms.number) = 0 ;");
            stmt.setString(1, type.toString());
            stmt.setInt(2, capacity);
            stmt.setInt(3, bedsAmount);
            stmt.setFloat(4, maxPrice);
            stmt.setTimestamp(5, currentTimestamp);
            stmt.setTimestamp(6, currentTimestamp);
            ResultSet resultSet = stmt.executeQuery();
            ArrayList<Room> list = new ArrayList<>();
            while(resultSet.next())
                list.add(new Room(resultSet.getInt(1), Room.RoomType.valueOf(resultSet.getString(2)),
                        resultSet.getInt(3), resultSet.getInt(4),
                        resultSet.getInt(5), resultSet.getBoolean(6), resultSet.getBoolean(7),
                        resultSet.getBoolean(8), resultSet.getFloat(9)));
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
    public ArrayList<Room> findFreeRoomsByParams(Room.RoomType type, int capacity, int bedsAmount, float maxPrice, Timestamp arrivalDayTimestamp, int livingDays) throws DAOException {
        try {
            DataSource msds = MySqlDataSource.getInstance();
            Connection con = msds.getConnection();
            CallableStatement stmt = con.prepareCall(
                    "SELECT * FROM rooms WHERE " +
                            "type = ? " +
                            "AND (2*double_beds_amount+single_beds_amount) >= ? " +
                            "AND (double_beds_amount+single_beds_amount) >= ? " +
                            "AND price <= ? " +
                            "AND (" +
                            "   SELECT COUNT(*) FROM roomregisters WHERE " +
                            "   (room_number = rooms.number) " +
                            "   AND NOT (" +
                            "   (end_date_time <= ?) " +
                            "   AND (start_date_time >= SUBDATE(?, INTERVAL -? DAY))\n" +
                            "           )" +
                            "    ) = 0;");
            stmt.setString(1, type.toString());
            stmt.setInt(2, capacity);
            stmt.setInt(3, bedsAmount);
            stmt.setFloat(4, maxPrice);
            stmt.setTimestamp(5, arrivalDayTimestamp);
            stmt.setTimestamp(6, arrivalDayTimestamp);
            stmt.setInt(7, livingDays);
            ResultSet resultSet = stmt.executeQuery();
            ArrayList<Room> list = new ArrayList<>();
            while(resultSet.next())
                list.add(new Room(resultSet.getInt(1), Room.RoomType.valueOf(resultSet.getString(2)),
                        resultSet.getInt(3), resultSet.getInt(4),
                        resultSet.getInt(5), resultSet.getBoolean(6), resultSet.getBoolean(7),
                        resultSet.getBoolean(8), resultSet.getFloat(9)));
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
