package com.rudametkin.hotelsystem.Database.MySqlDAOFactory;

import com.rudametkin.hotelsystem.DTO.RoomRegisterDto;
import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.Database.DAOFactory.IRoomRegisterDAO;
import com.rudametkin.hotelsystem.Database.SqlConnection.MySqlDataSource;
import com.rudametkin.hotelsystem.Entitys.RoomRegister;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlRoomRegisterDAO extends MySqlTransactionalDAO implements IRoomRegisterDAO {
    public MySqlRoomRegisterDAO() {
        connection = null;
    }
    public MySqlRoomRegisterDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int save(RoomRegister roomRegister) throws DAOException {
        return executeInTransaction(() -> {
            int key = -1;
            ResultSet resultSet = null;
            try(PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO roomregisters (room_number, status, start_date_time, end_date_time, client_id) " +
                            "VALUES (?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS)) {

                stmt.setInt(1, roomRegister.getRoomNumber());
                stmt.setString(2, roomRegister.getStatus().toString());
                stmt.setTimestamp(3, roomRegister.getStartDateTime());
                stmt.setTimestamp(4, roomRegister.getEndDateTime());
                stmt.setInt(5, roomRegister.getClientId());

                stmt.executeUpdate();
                resultSet = stmt.getGeneratedKeys();
                resultSet.next();
                key = resultSet.getInt(1);
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            } finally {
                if(resultSet != null)
                    try {
                        resultSet.close();
                    } catch (SQLException ignore) {}
                return key;
            }
        });
    }
    @Override
    public RoomRegister findLastByRoomNumber(int number) throws DAOException {
        return executeInTransaction(() -> {
            ResultSet resultSet = null;
            RoomRegister roomRegister = null;
            try(CallableStatement stmt = connection.prepareCall("SELECT * FROM roomregisters WHERE room_number = ? ORDER BY start_date_time DESC LIMIT 1;")) {
                stmt.setInt(1, number);
                resultSet = stmt.executeQuery();
                if(resultSet.next())
                    roomRegister = new RoomRegister(resultSet.getInt(1), resultSet.getInt(2), RoomRegister.RoomStatus.valueOf(resultSet.getString(3)),
                            resultSet.getTimestamp(4),resultSet.getTimestamp(5), resultSet.getInt(6));
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            } finally {
                if(resultSet != null)
                    try {
                        resultSet.close();
                    } catch (SQLException ignore) {}
                return roomRegister;
            }
        });
    }

    @Override
    public void removeById(int id) throws DAOException {
        executeInTransaction(() -> {
            try(PreparedStatement stmt = connection.prepareStatement("DELETE FROM roomregisters WHERE id = ?;")) {
                stmt.setInt(1, id);
                stmt.execute();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        });
    }

    @Override
    public List<RoomRegister> findAllByClientId(int clientId) throws DAOException {
        return executeInTransaction(() -> {
            ResultSet resultSet = null;
            List<RoomRegister> list = new ArrayList<>();
            try(CallableStatement stmt = connection.prepareCall("SELECT * FROM roomregisters WHERE client_id = ?; ")) {
                stmt.setInt(1, clientId);
                resultSet = stmt.executeQuery();
                while(resultSet.next())
                    list.add(new RoomRegister(resultSet.getInt(1), resultSet.getInt(2), RoomRegister.RoomStatus.valueOf(resultSet.getString(3)),
                            resultSet.getTimestamp(4),resultSet.getTimestamp(5), resultSet.getInt(6)));
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
