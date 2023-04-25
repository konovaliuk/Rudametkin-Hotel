package com.rudametkin.hotelsystem.Services;

import com.rudametkin.hotelsystem.DTO.RoomClassDto;
import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.Database.DAOFactory.IRoomDAO;
import com.rudametkin.hotelsystem.Database.MySqlDAOFactory.MySqlDAOFactory;
import com.rudametkin.hotelsystem.DTO.RoomSearchDto;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class RoomsSearchingService {
    static int pagePart = 5;

    static public int getPagePart() {
        return pagePart;
    }

    public List<RoomClassDto> findRoomsByParams(RoomSearchDto rp, int page) {
        List<RoomClassDto> foundRooms = new ArrayList<>();

        try {
            IRoomDAO roomDAO = MySqlDAOFactory.getInstance().getRoomDAO();
            foundRooms = roomDAO.findFreeRoomClassesByParams(rp, (page - 1) * pagePart, pagePart);
        } catch (DAOException ignore) {}

        return foundRooms;
    }

    public int countFreeRoomClassesByParams(RoomSearchDto pr) {
        int amount = 0;
        try {
            IRoomDAO roomDAO = MySqlDAOFactory.getInstance().getRoomDAO();
            amount = roomDAO.countFreeRoomClassesByParams(pr);
        } catch (DAOException ignore) {}
        return amount;
    }
}

