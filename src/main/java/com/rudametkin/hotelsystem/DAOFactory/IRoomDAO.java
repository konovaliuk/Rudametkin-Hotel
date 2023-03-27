package com.rudametkin.hotelsystem.DAOFactory;

import com.rudametkin.hotelsystem.entityObjects.Room;

import java.util.ArrayList;
import java.sql.Timestamp;

public interface IRoomDAO {
    void upadte(Room room) throws DAOException;
    ArrayList<Room> findFreeRoomsByParams(Room.RoomType type, int capacity, int bedsAmount, float maxPrice) throws DAOException;
    ArrayList<Room> findFreeRoomsByParams(Room.RoomType type, int capacity, int bedsAmount, float maxPrice, Timestamp arrivalTimestamp, int livingDays) throws DAOException;
}
