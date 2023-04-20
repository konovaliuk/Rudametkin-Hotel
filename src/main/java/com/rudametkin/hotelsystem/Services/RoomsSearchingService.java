package com.rudametkin.hotelsystem.Services;

import com.rudametkin.hotelsystem.Database.DAOFactory.IRoomDAO;
import com.rudametkin.hotelsystem.Database.MySqlDAOFactory.MySqlDAOFactory;
import com.rudametkin.hotelsystem.EntityObjects.Room;
import com.rudametkin.hotelsystem.Services.ServiceHelpClasses.RoomParameters;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class RoomsSearchingService {
    private RoomParameters roomParameters;
    private List<Room> foundRooms;
    private int page;
    private int maxPages;
    static int pagePart = 5;
    public RoomsSearchingService() {
        foundRooms = new ArrayList<>();
        page = 1;
        roomParameters = new RoomParameters();
    }
    public List<Room> findRoomsByParams(RoomParameters rp) {
        IRoomDAO roomDAO = MySqlDAOFactory.getInstance().getRoomDAO();
        try {
            foundRooms = roomDAO.findFreeRoomsByParams(rp);
            page = 1;
            maxPages = (int) Math.ceil(Float.valueOf(foundRooms.size()) / pagePart);
            roomParameters = rp;
        } catch (Exception ignore) {}
        return foundRooms;
    }

    public List<Room> getFoundRooms() {
        if((page - 1) * pagePart <= foundRooms.size() - 1)
            return foundRooms.subList(min((page - 1) * pagePart, foundRooms.size() - 1),
                                      min(page * pagePart, foundRooms.size()));
        else
            return new ArrayList<>();
    }

    public void sortFoundByPrice() {

    }

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = max(1, page);
    }

    public int getMaxPages() {
        return maxPages;
    }

    public int getCapacity() {
        return roomParameters.getCapacity();
    }

    public int getBedsAmount() {
        return roomParameters.getBedsAmount();
    }
}
