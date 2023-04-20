package com.rudametkin.hotelsystem.Services;

import com.rudametkin.hotelsystem.Database.DAOFactory.IRoomDAO;
import com.rudametkin.hotelsystem.Database.MySqlDAOFactory.MySqlDAOFactory;
import com.rudametkin.hotelsystem.EntityObjects.Room;

public class RoomViewService {
    private Room viewRoom;
    public RoomViewService() {
        viewRoom = null;
    }

    public void setViewRoomByNumber(int roomNumber) {
        IRoomDAO roomDAO = MySqlDAOFactory.getInstance().getRoomDAO();
    }
}
