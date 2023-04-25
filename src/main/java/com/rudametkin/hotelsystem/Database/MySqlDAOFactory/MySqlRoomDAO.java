package com.rudametkin.hotelsystem.Database.MySqlDAOFactory;

import com.rudametkin.hotelsystem.DTO.RoomClassDto;
import com.rudametkin.hotelsystem.DTO.RoomSearchDto;
import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.Database.DAOFactory.IRoomDAO;
import com.rudametkin.hotelsystem.Database.SqlConnection.MySqlDataSource;
import com.rudametkin.hotelsystem.Entitys.Room;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlRoomDAO extends MySqlTransactionalDAO implements IRoomDAO {
    public MySqlRoomDAO() {
        connection = null;
    }
    public MySqlRoomDAO(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void update(Room room) throws DAOException {
        executeInTransaction(() -> {
            try(PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE rooms " +
                            "SET type = ?, mini_bar = ?, tv = ?, dryer = ?, price = ?, single_beds_amount = ?, double_beds_amount = ?, rooms_amount = ? " +
                            "WHERE number = ?;")) {
                stmt.setString(1, room.getType().toString());
                stmt.setBoolean(2, room.isMiniBar());
                stmt.setBoolean(3, room.isTv());
                stmt.setBoolean(4, room.isDryer());
                stmt.setFloat(5, room.getPrice());
                stmt.setInt(6, room.getSingleBedsAmount());
                stmt.setInt(7, room.getDoubleBedsAmount());
                stmt.setInt(8, room.getNumber());
                stmt.execute();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        });
    }

    @Override
    public List<RoomClassDto> findFreeRoomClassesByParams(RoomSearchDto rp, int offset, int limit) throws DAOException {
        return executeInTransaction(() -> {
            String typeCondition = (rp.getType() != Room.RoomType.Any)? "type = ? AND" : "";
            ResultSet resultSet = null;
            List<RoomClassDto> list = new ArrayList<>();
            try(CallableStatement stmt = connection.prepareCall(
                    "SELECT DISTINCT type, rooms_amount, single_beds_amount, double_beds_amount, mini_bar, tv, dryer, price FROM rooms WHERE " +
                            typeCondition +
                            "(2*double_beds_amount+single_beds_amount) >= ? " +
                            "AND (double_beds_amount+single_beds_amount) >= ? " +
                            "AND (" +
                            "    SELECT COUNT(*) FROM roomregisters " +
                            "    WHERE (end_date_time > ?) AND (start_date_time < ?) " +
                            "    AND room_number = rooms.number" +
                            "    ) = 0 " +
                            "LIMIT ? " +
                            "OFFSET ? ;")) {

                int startIndex = 1;
                if(typeCondition.equals("") == false) {
                    stmt.setString(1, rp.getType().toString());
                    startIndex += 1;
                }

                stmt.setInt(startIndex, rp.getCapacity());
                stmt.setInt(startIndex + 1, rp.getBedsAmount());
                stmt.setTimestamp(startIndex + 2, rp.getArrivalDate());
                stmt.setTimestamp(startIndex + 3, rp.getDepartureDate());
                stmt.setInt(startIndex + 4, limit);
                stmt.setInt(startIndex + 5, offset);

                resultSet = stmt.executeQuery();
                while(resultSet.next())
                    list.add(new RoomClassDto(Room.RoomType.valueOf(resultSet.getString(1)),
                            resultSet.getInt(2), resultSet.getInt(3),
                            resultSet.getInt(4), resultSet.getBoolean(5), resultSet.getBoolean(6),
                            resultSet.getBoolean(7), resultSet.getFloat(8)));
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
    @Override
    public int countFreeRoomClassesByParams(RoomSearchDto rp) throws DAOException {
        return executeInTransaction(() -> {
            String typeCondition = (rp.getType() != Room.RoomType.Any)? "type = ? AND" : "";
            ResultSet resultSet = null;
            int amount = 0;
            try(CallableStatement stmt = connection.prepareCall(
                    "SELECT COUNT(DISTINCT type, rooms_amount, single_beds_amount, double_beds_amount, mini_bar, tv, dryer, price) FROM rooms WHERE " +
                            typeCondition +
                            "(2*double_beds_amount+single_beds_amount) >= ? " +
                            "AND (double_beds_amount+single_beds_amount) >= ? " +
                            "AND (" +
                            "    SELECT COUNT(*) FROM roomregisters " +
                            "    WHERE (end_date_time > ?) AND (start_date_time < ?) " +
                            "    AND room_number = rooms.number" +
                            "    ) = 0 ;")) {

                int startIndex = 1;
                if(typeCondition.equals("") == false) {
                    stmt.setString(1, rp.getType().toString());
                    startIndex += 1;
                }

                stmt.setInt(startIndex, rp.getCapacity());
                stmt.setInt(startIndex + 1, rp.getBedsAmount());
                stmt.setTimestamp(startIndex + 2, rp.getArrivalDate());
                stmt.setTimestamp(startIndex + 3, rp.getDepartureDate());

                resultSet = stmt.executeQuery();
                if(resultSet.next())
                    amount = resultSet.getInt(1);
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            } finally {
                if(resultSet != null)
                    try {
                        resultSet.close();
                    } catch (SQLException ignore) {}
                return amount;
            }
        });
    }

    @Override
    public Room findFreeRoomByRoomClass(RoomClassDto roomclass, Timestamp arrivalTime, Timestamp departureTime) throws DAOException {
        return executeInTransaction(() -> {
            ResultSet resultSet = null;
            Room room = null;
            try(CallableStatement stmt = connection.prepareCall(
                    "SELECT * FROM rooms WHERE " +
                            "type = ? " +
                            "AND rooms_amount = ? " +
                            "AND single_beds_amount = ? " +
                            "AND double_beds_amount = ? " +
                            "AND mini_bar = ? " +
                            "AND tv = ? " +
                            "AND dryer = ? " +
                            "AND price = ? " +
                            "AND (" +
                            "    SELECT COUNT(*) FROM roomregisters " +
                            "    WHERE (end_date_time > ?) AND (start_date_time < ?) " +
                            "    AND room_number = rooms.number" +
                            "    ) = 0 " +
                            "LIMIT 1 ;")) {

                stmt.setString(1, roomclass.getType().toString());
                stmt.setInt(2, roomclass.getRoomsAmount());
                stmt.setInt(3, roomclass.getSingleBedsAmount());
                stmt.setInt(4, roomclass.getDoubleBedsAmount());
                stmt.setBoolean(5, roomclass.isMiniBar());
                stmt.setBoolean(6, roomclass.isTv());
                stmt.setBoolean(7, roomclass.isDryer());
                stmt.setFloat(8, roomclass.getPrice());
                stmt.setTimestamp(9, arrivalTime);
                stmt.setTimestamp(10, departureTime);

                resultSet = stmt.executeQuery();
                if(resultSet.next())
                    room = new Room(resultSet.getInt(1), roomclass.getType(),
                            roomclass.getRoomsAmount(), roomclass.getSingleBedsAmount(),
                            roomclass.getDoubleBedsAmount(), roomclass.isMiniBar(), roomclass.isTv(),
                            roomclass.isDryer(), roomclass.getPrice());
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            } finally {
                if(resultSet != null)
                    try {
                        resultSet.close();
                    } catch (SQLException ignore) {}
                return room;
            }
        });
    }
}
