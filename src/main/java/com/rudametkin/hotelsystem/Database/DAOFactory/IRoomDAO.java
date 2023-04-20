package com.rudametkin.hotelsystem.Database.DAOFactory;

import com.rudametkin.hotelsystem.EntityObjects.Room;
import com.rudametkin.hotelsystem.Services.ServiceHelpClasses.RoomParameters;

import java.util.List;

public interface IRoomDAO {
    void update(Room room) throws DAOException;
    public List<Room> findFreeRoomsByParams(RoomParameters rp) throws DAOException;
}
