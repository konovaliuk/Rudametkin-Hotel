package com.rudametkin.hotelsystem.Database.MySqlDAOFactory;

import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.Database.DAOFactory.IRoomDAO;
import com.rudametkin.hotelsystem.Database.SqlConnection.MySqlDataSource;
import com.rudametkin.hotelsystem.EntityObjects.Room;
import com.rudametkin.hotelsystem.Services.ServiceHelpClasses.RoomParameters;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlRoomDAO implements IRoomDAO {
    private void handleSQLException(SQLException e) throws DAOException {
        if(e.getMessage().equals("MySqlConnectionError"))
            throw new DAOException("MySqlConnectionError");
        else
            throw new DAOException("ExecuteStatementError|rooms");
    }
    @Override
    public void update(Room room) throws DAOException {
        DataSource msds = MySqlDataSource.getInstance();
        try(Connection con = msds.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "UPDATE rooms " +
                    "SET type = ?, mini_bar = ?, tv = ?, dryer = ?, price = ?, single_beds_amount = ?, double_beds_amount = ?, rooms_amount = ? " +
                    "WHERE number = ?;");)
        {
            stmt.setString(1, room.getType().toString());
            stmt.setBoolean(2, room.getHasMiniBar());
            stmt.setBoolean(3, room.getHasTV());
            stmt.setBoolean(4, room.getHasDryer());
            stmt.setFloat(5, room.getPrice());
            stmt.setInt(6, room.getSingleBedsAmount());
            stmt.setInt(7, room.getDoubleBedsAmount());
            stmt.setInt(8, room.getNumber());
            stmt.execute();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    @Override
    public List<Room> findFreeRoomsByParams(RoomParameters rp) throws DAOException {
        DataSource msds = MySqlDataSource.getInstance();
        CallableStatement stmt = null;
        try(Connection con = msds.getConnection())
        {
            String typeCondition = (rp.getType() != Room.RoomType.Any)? "type = ? AND" : "";
            stmt = con.prepareCall(
                    "SELECT DISTINCT type, rooms_amount, single_beds_amount, double_beds_amount, mini_bar, tv, dryer, price FROM rooms WHERE " +
                             typeCondition +
                            "(2*double_beds_amount+single_beds_amount) >= ? " +
                            "AND (double_beds_amount+single_beds_amount) >= ? " +
                            "AND (" +
                            "    SELECT COUNT(*) FROM roomregisters " +
                            "    WHERE (end_date_time > ?) AND (start_date_time < ?) " +
                            "    AND room_number = rooms.number" +
                            "    ) = 0 ;");

            if(typeCondition.equals("") == false) {
                stmt.setString(1, rp.getType().toString());
                stmt.setInt(2, rp.getCapacity());
                stmt.setInt(3, rp.getBedsAmount());
                stmt.setTimestamp(4, rp.getArrivalDate());
                stmt.setTimestamp(5, rp.getDepartureDate());
            } else {
                stmt.setInt(1, rp.getCapacity());
                stmt.setInt(2, rp.getBedsAmount());
                stmt.setTimestamp(3, rp.getArrivalDate());
                stmt.setTimestamp(4, rp.getDepartureDate());
            }

            ResultSet resultSet = stmt.executeQuery();
            List<Room> list = new ArrayList<>();
            while(resultSet.next())
                list.add(new Room(Room.RoomType.valueOf(resultSet.getString(1)),
                        resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getInt(4), resultSet.getBoolean(5), resultSet.getBoolean(6),
                        resultSet.getBoolean(7), resultSet.getFloat(8)));
            resultSet.close();
            return list;
        } catch (SQLException e) {
            handleSQLException(e);
            return new ArrayList<>();
        }
    }
}
