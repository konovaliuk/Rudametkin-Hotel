package com.rudametkin.hotelsystem.Database.DAOFactory;

import com.rudametkin.hotelsystem.DTO.RoomClassDto;
import com.rudametkin.hotelsystem.Entitys.Room;
import com.rudametkin.hotelsystem.DTO.RoomSearchDto;

import java.sql.Timestamp;
import java.util.List;

public interface IRoomDAO {
    void update(Room room) throws DAOException;
    List<RoomClassDto> findFreeRoomClassesByParams(RoomSearchDto rp, int offset, int limit) throws DAOException;
    Room findFreeRoomByRoomClass(RoomClassDto roomclass, Timestamp arrivalTime, Timestamp departureTime) throws DAOException;
    int countFreeRoomClassesByParams(RoomSearchDto rp) throws DAOException;
}
