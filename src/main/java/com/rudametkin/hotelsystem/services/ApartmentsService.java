package com.rudametkin.hotelsystem.services;

import com.rudametkin.hotelsystem.database.jpadao.repository.UserRepository;
import com.rudametkin.hotelsystem.database.transaction.TransactionFunction;
import com.rudametkin.hotelsystem.database.transaction.TransactionManager;
import com.rudametkin.hotelsystem.dto.RoomClassDto;
import com.rudametkin.hotelsystem.dto.UserDto;
import com.rudametkin.hotelsystem.database.daofactory.*;
import com.rudametkin.hotelsystem.database.jpadao.*;

import com.rudametkin.hotelsystem.entitys.Bill;
import com.rudametkin.hotelsystem.entitys.Room;
import com.rudametkin.hotelsystem.entitys.RoomRegister;
import com.rudametkin.hotelsystem.entitys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ApartmentsService {

    private final DAOFactory daoFactory;
    private final TransactionManager transactionManager;

    @Autowired
    public ApartmentsService(DAOFactory daoFactory, TransactionManager transactionManager) {
        this.daoFactory = daoFactory;
        this.transactionManager = transactionManager;
    }

    public boolean tryBookApartments(RoomClassDto roomClass, Timestamp arrivalTime, Timestamp departureTime, UserDto client) {
        Boolean success = transactionManager.execute(transaction -> {
            IRoomDAO roomDAO = daoFactory.getRoomDAO(transaction);

            Room freeRoom = roomDAO.findFreeRoomByRoomClass(roomClass, arrivalTime, departureTime);
            if(freeRoom == null)
                return false;

            IUserDAO userDAO = daoFactory.getUserDAO(transaction);
            User clientUser = userDAO.findByEmail(client.getEmail());

            IRoomRegisterDAO roomRegisterDAO = daoFactory.getRoomRegisterDAO(transaction);
            RoomRegister roomRegister = new RoomRegister(freeRoom.getNumber(), RoomRegister.RoomStatus.Booked,
                    arrivalTime, departureTime, clientUser.getId());
            int registerId = roomRegisterDAO.save(roomRegister);

            Bill billForBooking = new Bill.BillBuilder(registerId).addRoomCharge(roomClass.getPrice()).addPaid(true).build();
            IBillDAO billDAO = daoFactory.getBillDAO(transaction);
            billDAO.save(billForBooking);

            return true;
        });

        return success != null && success;
    }
    public void unbookApartments(Room apartments) {
        try {
            IRoomRegisterDAO roomRegisterDAO = daoFactory.getRoomRegisterDAO();
            RoomRegister bookingRoomRegister = roomRegisterDAO.findLastByRoomNumber(apartments.getNumber());
            if(bookingRoomRegister.getStatus() == RoomRegister.RoomStatus.Booked)
                roomRegisterDAO.removeById(bookingRoomRegister.getId());
        } catch (Exception ignore) {}
    }

    public boolean checkIfExistsFreeRoomByClass(RoomClassDto roomClass, Timestamp arrivalTime, Timestamp departureTime) {
        boolean exists = false;
        try {
            IRoomDAO roomDAO = daoFactory.getRoomDAO();
            exists = roomDAO.findFreeRoomByRoomClass(roomClass, arrivalTime, departureTime) != null;
        } catch (DAOException ignore) {}
        return exists;
    }
}
