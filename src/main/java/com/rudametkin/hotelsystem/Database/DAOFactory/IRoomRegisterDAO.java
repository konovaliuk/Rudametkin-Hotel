package com.rudametkin.hotelsystem.Database.DAOFactory;

import com.rudametkin.hotelsystem.DTO.RoomRegisterDto;
import com.rudametkin.hotelsystem.Entitys.RoomRegister;

import java.util.ArrayList;
import java.util.List;

public interface IRoomRegisterDAO {
    int save(RoomRegister roomRegister) throws DAOException;
    void removeById(int id) throws DAOException;
    RoomRegister findLastByRoomNumber(int number) throws DAOException;
    List<RoomRegister> findAllByClientId(int id) throws DAOException;
}
