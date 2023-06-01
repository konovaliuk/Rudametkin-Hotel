package com.rudametkin.hotelsystem.services;

import com.rudametkin.hotelsystem.database.daofactory.DAOFactory;
import com.rudametkin.hotelsystem.database.transaction.TransactionManager;
import com.rudametkin.hotelsystem.dto.RoomClassDto;
import com.rudametkin.hotelsystem.dto.RoomSearchDto;
import com.rudametkin.hotelsystem.database.daofactory.DAOException;
import com.rudametkin.hotelsystem.database.daofactory.IRoomDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomsSearchingService {

    private final DAOFactory daoFactory;
    private final TransactionManager transactionManager;

    @Autowired
    public RoomsSearchingService(DAOFactory daoFactory, TransactionManager transactionManager) {
        this.daoFactory = daoFactory;
        this.transactionManager = transactionManager;
    }
    static int pagePart = 5;

    static public int getPagePart() {
        return pagePart;
    }

    public List<RoomClassDto> findRoomsByParams(RoomSearchDto rp) {
        List<RoomClassDto> foundRooms = new ArrayList<>();

        try {
            IRoomDAO roomDAO = daoFactory.getRoomDAO();
            foundRooms = roomDAO.findFreeRoomClassesByParams(rp, (rp.getPage() - 1) * pagePart, pagePart);
        } catch (DAOException ignore) {}

        return foundRooms;
    }

    public int countFreeRoomClassesByParams(RoomSearchDto pr) {
        int amount = 0;
        try {
            IRoomDAO roomDAO = daoFactory.getRoomDAO();
            amount = roomDAO.countFreeRoomClassesByParams(pr);
        } catch (DAOException ignore) {}
        return amount;
    }

    public int countPagesOfFreeRooms(RoomSearchDto roomSearchDto) {
        return (int) Math.ceil(countFreeRoomClassesByParams(roomSearchDto)/(float)RoomsSearchingService.getPagePart());
    }
}

