package com.rudametkin.hotelsystem.Services;

import com.rudametkin.hotelsystem.DTO.OrderDto;
import com.rudametkin.hotelsystem.DTO.UserDto;
import com.rudametkin.hotelsystem.Database.DAOFactory.IBillDAO;
import com.rudametkin.hotelsystem.Database.DAOFactory.IRoomRegisterDAO;
import com.rudametkin.hotelsystem.Database.DAOFactory.IUserDAO;
import com.rudametkin.hotelsystem.Database.MySqlDAOFactory.MySqlDAOFactory;
import com.rudametkin.hotelsystem.Database.TransactionHandling.JDBCTransactionManager;
import com.rudametkin.hotelsystem.Entitys.Bill;
import com.rudametkin.hotelsystem.Entitys.RoomRegister;
import com.rudametkin.hotelsystem.Entitys.User;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BillsService {
    public List<OrderDto> getAllUserBills(UserDto user) {
        JDBCTransactionManager transactionManager = new JDBCTransactionManager();
        ArrayList<OrderDto> list = transactionManager.execute(connection -> {
            IUserDAO userDAO = MySqlDAOFactory.getInstance().getUserDAO(connection);
            User client = userDAO.findByEmail(user.getEmail());

            IRoomRegisterDAO roomRegisterDAO = MySqlDAOFactory.getInstance().getRoomRegisterDAO(connection);
            List<RoomRegister> roomRegisters = roomRegisterDAO.findAllByClientId(client.getId());

            IBillDAO billDAO = MySqlDAOFactory.getInstance().getBillDAO(connection);
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
}
