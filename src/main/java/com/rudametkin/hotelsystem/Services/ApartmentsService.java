package com.rudametkin.hotelsystem.Services;

import com.rudametkin.hotelsystem.DTO.RoomClassDto;
import com.rudametkin.hotelsystem.DTO.RoomSearchDto;
import com.rudametkin.hotelsystem.DTO.UserDto;
import com.rudametkin.hotelsystem.Database.DAOFactory.*;
import com.rudametkin.hotelsystem.Database.MySqlDAOFactory.MySqlDAOFactory;
import com.rudametkin.hotelsystem.Database.TransactionHandling.JDBCTransactionManager;
import com.rudametkin.hotelsystem.Entitys.Bill;
import com.rudametkin.hotelsystem.Entitys.Room;
import com.rudametkin.hotelsystem.Entitys.RoomRegister;
import com.rudametkin.hotelsystem.Entitys.User;

import java.sql.Timestamp;

public class ApartmentsService {
    public boolean tryBookApartments(RoomClassDto roomClass, Timestamp arrivalTime, Timestamp departureTime, UserDto client) {
        Boolean success = new JDBCTransactionManager().execute(connection -> {
            IRoomDAO roomDAO = MySqlDAOFactory.getInstance().getRoomDAO(connection);
            Room freeRoom = roomDAO.findFreeRoomByRoomClass(roomClass, arrivalTime, departureTime);

            IUserDAO userDAO = MySqlDAOFactory.getInstance().getUserDAO(connection);
            User clientUser = userDAO.findByEmail(client.getEmail());

            IRoomRegisterDAO roomRegisterDAO = MySqlDAOFactory.getInstance().getRoomRegisterDAO(connection);
            RoomRegister roomRegister = new RoomRegister(freeRoom.getNumber(), RoomRegister.RoomStatus.Booked,
                    arrivalTime, departureTime, clientUser.getId());
            int registerId = roomRegisterDAO.save(roomRegister);

            Bill billForBooking = new Bill.BillBuilder(registerId).addRoomCharge(roomClass.getPrice()).addPaid(true).build();
            IBillDAO billDAO = MySqlDAOFactory.getInstance().getBillDAO(connection);
            billDAO.save(billForBooking);

            return true;
        });

        return (success == null) ? false : success;
    }
    public void unbookApartments(Room apartments) {
        try {
            IRoomRegisterDAO roomRegisterDAO = MySqlDAOFactory.getInstance().getRoomRegisterDAO();
            RoomRegister bookingRoomRegister = roomRegisterDAO.findLastByRoomNumber(apartments.getNumber());
            if(bookingRoomRegister.getStatus() == RoomRegister.RoomStatus.Booked)
                roomRegisterDAO.removeById(bookingRoomRegister.getId());
        } catch (Exception ignore) {}
    }

    public boolean checkIfExistsFreeRoomByClass(RoomClassDto roomClass, Timestamp arrivalTime, Timestamp departureTime) {
        boolean exists = false;
        try {
            IRoomDAO roomDAO = MySqlDAOFactory.getInstance().getRoomDAO();
            exists = roomDAO.findFreeRoomByRoomClass(roomClass, arrivalTime, departureTime) != null;
        } catch (DAOException ignore) {}
        return exists;
    }
}
