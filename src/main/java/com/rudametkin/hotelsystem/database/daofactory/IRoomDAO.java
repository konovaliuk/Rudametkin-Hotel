package com.rudametkin.hotelsystem.database.daofactory;

import com.rudametkin.hotelsystem.dto.RoomClassDto;
import com.rudametkin.hotelsystem.dto.RoomSearchDto;
import com.rudametkin.hotelsystem.entitys.Room;

import java.sql.Timestamp;
import java.util.List;

public interface IRoomDAO {
    void update(Room room) throws DAOException;
    List<RoomClassDto> findFreeRoomClassesByParams(RoomSearchDto rp, int offset, int limit) throws DAOException;
    Room findFreeRoomByRoomClass(RoomClassDto roomclass, Timestamp arrivalTime, Timestamp departureTime) throws DAOException;
    int countFreeRoomClassesByParams(RoomSearchDto rp) throws DAOException;
}
