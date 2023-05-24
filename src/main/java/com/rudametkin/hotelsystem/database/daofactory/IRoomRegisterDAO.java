package com.rudametkin.hotelsystem.database.daofactory;

import com.rudametkin.hotelsystem.entitys.RoomRegister;

import java.util.List;

public interface IRoomRegisterDAO {
    int save(RoomRegister roomRegister) throws DAOException;
    void removeById(int id) throws DAOException;
    RoomRegister findLastByRoomNumber(int number) throws DAOException;
    List<RoomRegister> findAllByClientId(int id) throws DAOException;
    List<RoomRegister> findActiveByClientId(int id) throws DAOException;
}
