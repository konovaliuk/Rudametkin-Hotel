package com.rudametkin.hotelsystem.database.jpadao;

import com.rudametkin.hotelsystem.database.daofactory.DAOException;
import com.rudametkin.hotelsystem.database.daofactory.IRoomRegisterDAO;
import com.rudametkin.hotelsystem.database.jpadao.repository.RoomRegisterRepository;
import com.rudametkin.hotelsystem.entitys.RoomRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class JPARoomRegisterDAO implements IRoomRegisterDAO {
    private final RoomRegisterRepository roomRegisterRepository;

    public JPARoomRegisterDAO(RoomRegisterRepository roomRegisterRepository) {
        this.roomRegisterRepository = roomRegisterRepository;
    }

    @Override
    public int save(RoomRegister roomRegister) throws DAOException {
        roomRegisterRepository.save(roomRegister);
        return roomRegister.getId();
    }

    @Override
    public void removeById(int id) throws DAOException {
        roomRegisterRepository.deleteById(id);
    }

    @Override
    public RoomRegister findLastByRoomNumber(int number) throws DAOException {
        return roomRegisterRepository.findLastByRoomNumber(number);
    }

    @Override
    public List<RoomRegister> findAllByClientId(int id) throws DAOException {
        return roomRegisterRepository.findAllByClientId(id);
    }

    @Override
    public List<RoomRegister> findActiveByClientId(int id) throws DAOException {
        return roomRegisterRepository.findActiveByClientId(id, new Timestamp(System.currentTimeMillis()));
    }
}
