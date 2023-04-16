package com.rudametkin.hotelsystem.businessLogic;

import com.rudametkin.hotelsystem.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.DAOFactory.IRoomDAO;
import com.rudametkin.hotelsystem.MySqlDAOFactory.MySqlDAOFactory;
import com.rudametkin.hotelsystem.entityObjects.Room;

public class RoomViewService {
    private Room viewRoom;
    public RoomViewService() {
        viewRoom = null;
    }

    public void setViewRoomByNumber(int roomNumber) {
        IRoomDAO roomDAO = MySqlDAOFactory.getInstance().getRoomDAO();
    }
}
