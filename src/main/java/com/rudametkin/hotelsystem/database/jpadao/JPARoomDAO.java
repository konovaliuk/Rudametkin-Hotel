package com.rudametkin.hotelsystem.database.jpadao;

import com.rudametkin.hotelsystem.database.daofactory.DAOException;
import com.rudametkin.hotelsystem.database.daofactory.IRoomDAO;
import com.rudametkin.hotelsystem.database.jpadao.repository.RoomRepository;
import com.rudametkin.hotelsystem.database.jpadao.repository.UserRoleRepository;
import com.rudametkin.hotelsystem.dto.RoomClassDto;
import com.rudametkin.hotelsystem.dto.RoomSearchDto;
import com.rudametkin.hotelsystem.entitys.Room;
import com.rudametkin.hotelsystem.entitys.RoomRegister;
import com.rudametkin.hotelsystem.services.RoomsSearchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class JPARoomDAO implements IRoomDAO {

    private final RoomRepository roomRepository;

    @Autowired
    public JPARoomDAO(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    @Override
    public void update(Room room) throws DAOException {
        roomRepository.save(room);
    }

    @Override
    public List<RoomClassDto> findFreeRoomClassesByParams(RoomSearchDto rp, int offset, int limit) throws DAOException {
        return roomRepository.findFreeRoomClassesByParams(rp, PageRequest.of(offset/RoomsSearchingService.getPagePart(), RoomsSearchingService.getPagePart(), Sort.unsorted()));
    }

    @Override
    public Room findFreeRoomByRoomClass(RoomClassDto roomclass, Timestamp arrivalTime, Timestamp departureTime) throws DAOException {
        List<Room> rooms = roomRepository.findFreeRoomsByRoomClass(roomclass, arrivalTime, departureTime, PageRequest.of(0, 1, Sort.unsorted()));
        return (rooms.size() == 1) ? rooms.get(0) : null;
    }

    @Override
    public int countFreeRoomClassesByParams(RoomSearchDto rp) throws DAOException {
        return roomRepository.countFreeRoomClassesByParams(rp);
    }
}
