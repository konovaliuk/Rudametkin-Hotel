package com.rudametkin.hotelsystem.services;

import com.rudametkin.hotelsystem.database.daofactory.DAOFactory;
import com.rudametkin.hotelsystem.database.transaction.TransactionManager;
import com.rudametkin.hotelsystem.dto.OrderDto;
import com.rudametkin.hotelsystem.dto.UserDto;
import com.rudametkin.hotelsystem.database.daofactory.IBillDAO;
import com.rudametkin.hotelsystem.database.daofactory.IRoomRegisterDAO;
import com.rudametkin.hotelsystem.database.daofactory.IUserDAO;
import com.rudametkin.hotelsystem.entitys.Bill;
import com.rudametkin.hotelsystem.entitys.RoomRegister;
import com.rudametkin.hotelsystem.entitys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class BillsService {

    private final DAOFactory daoFactory;
    private final TransactionManager transactionManager;

    @Autowired
    public BillsService(DAOFactory daoFactory, TransactionManager transactionManager) {
        this.daoFactory = daoFactory;
        this.transactionManager = transactionManager;
    }
    public List<OrderDto> getAllUserBills(UserDto user) {
        ArrayList<OrderDto> list = transactionManager.execute(connection -> {
            IUserDAO userDAO = daoFactory.getUserDAO(connection);
            User client = userDAO.findByEmail(user.getEmail());

            IRoomRegisterDAO roomRegisterDAO = daoFactory.getRoomRegisterDAO(connection);
            List<RoomRegister> roomRegisters = roomRegisterDAO.findAllByClientId(client.getId());

            IBillDAO billDAO = daoFactory.getBillDAO(connection);
            ArrayList<OrderDto> orders = new ArrayList<>();

            for(RoomRegister rr : roomRegisters) {
                List<Bill> bills = billDAO.findByRegisterId(rr.getId());
                for(Bill b : bills)
                    orders.add(new OrderDto(b, rr));
            }
            Collections.reverse(orders);
            return orders;
        });

        return (list == null) ? new ArrayList<>() : list;
    }

    public List<OrderDto> getActiveUserBills(String login) {
        ArrayList<OrderDto> list = transactionManager.execute(connection -> {
            IUserDAO userDAO = daoFactory.getUserDAO(connection);
            User client = userDAO.findByLogin(login);
            if(client == null)
                return null;

            IRoomRegisterDAO roomRegisterDAO = daoFactory.getRoomRegisterDAO(connection);
            List<RoomRegister> roomRegisters = roomRegisterDAO.findActiveByClientId(client.getId());

            IBillDAO billDAO = daoFactory.getBillDAO(connection);
            ArrayList<OrderDto> orders = new ArrayList<>();

            for(RoomRegister rr : roomRegisters) {
                List<Bill> bills = billDAO.findByRegisterId(rr.getId());
                for(Bill b : bills)
                    orders.add(new OrderDto(b, rr));
            }
            Collections.reverse(orders);
            return orders;
        });

        return (list == null) ? new ArrayList<>() : list;
    }

    public void cancelOrder(int roomRegisterId) {
        try {
            IRoomRegisterDAO roomRegisterDAO = daoFactory.getRoomRegisterDAO();
            roomRegisterDAO.removeById(roomRegisterId);
        } catch (Exception ignore) {}
    }
}
