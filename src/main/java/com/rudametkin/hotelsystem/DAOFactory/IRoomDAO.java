package com.rudametkin.hotelsystem.DAOFactory;

import com.rudametkin.hotelsystem.entityObjects.Room;
import com.rudametkin.hotelsystem.entityObjects.RoomParameters;

import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;

public interface IRoomDAO {
    void upadte(Room room) throws DAOException;
    public List<Room> findFreeRoomsByParams(RoomParameters rp) throws DAOException;
}
