package com.rudametkin.hotelsystem;

import com.rudametkin.hotelsystem.DTO.RoomClassDto;
import com.rudametkin.hotelsystem.DTO.RoomSearchDto;
import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.Database.DAOFactory.IRoomDAO;
import com.rudametkin.hotelsystem.Database.DAOFactory.IUserDAO;
import com.rudametkin.hotelsystem.Database.JPADAOFactory.JPADAOFactory;
import com.rudametkin.hotelsystem.Entitys.Room;
import com.rudametkin.hotelsystem.Entitys.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void Test1_FindUserByLoginPassword(String testName, String login, String password) {
        try {
            IUserDAO userDAO = JPADAOFactory.getInstance().getUserDAO();
            User user = userDAO.findByLoginPassword(login, password);
            System.out.println(testName);
            System.out.println(user);
        } catch (DAOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void Test2_FindFreeRoomClassesByParams(String testName, RoomSearchDto rsd) {
        try {
            IRoomDAO roomDAO = JPADAOFactory.getInstance().getRoomDAO();
            List<RoomClassDto> classDtos = roomDAO.findFreeRoomClassesByParams(rsd, 0, 20);
            System.out.println(testName);
            for(RoomClassDto rc : classDtos)
                System.out.println(rc);
        } catch (DAOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void Test3_FindFreeRoomByClassName(String testName, RoomClassDto rcd, Timestamp arrivalDate, Timestamp departureDate) {
        try {
            IRoomDAO roomDAO = JPADAOFactory.getInstance().getRoomDAO();
            Room freeRoom = roomDAO.findFreeRoomByRoomClass(rcd, arrivalDate, departureDate);
            System.out.println(testName);
            System.out.println(freeRoom);
        } catch (DAOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void Test4_UpdateUserPassword(String testName, String login, String newPassword) {
        try {
            IUserDAO userDAO = JPADAOFactory.getInstance().getUserDAO();
            User user = userDAO.findByLogin(login);
            user.setPassword(newPassword);
            userDAO.update(user);

            User updatedUser = userDAO.findByLogin(login);
            System.out.println(testName);
            System.out.println(updatedUser);
        } catch (DAOException e) {
            System.out.println(e.getMessage());
        }
    }



    public static void main(String[] args) {
        Test1_FindUserByLoginPassword("Test #1.","VyaSik", "12345");
        Test1_FindUserByLoginPassword("Test #2. [Expected: null]","VyaSik", "1111");

        RoomSearchDto rsd = new RoomSearchDto();
        rsd.setType(Room.RoomType.Suite);
        rsd.setCapacity(2);

        Test2_FindFreeRoomClassesByParams("Test #3.", rsd);
        Test3_FindFreeRoomByClassName("Test #4.", new RoomClassDto(Room.RoomType.Suite, 2, 0, 2, true, true, false, 25), rsd.getArrivalDate(), rsd.getDepartureDate());

        Test4_UpdateUserPassword("Test #5.", "VyaSik", "11111");
    }
}
