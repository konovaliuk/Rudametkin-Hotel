package running;

import DAOFactory.*;
import MySqlDAOFactory.MySqlDAOFactory;
import SqlConnection.MySqlDataSource;
import entityObjects.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        DAOFactory daoFactory = MySqlDAOFactory.getInstance();
        IRoomRegisterDAO roomRegisterDAO = daoFactory.getRoomRegisterDAO();
        ArrayList<RoomRegister> list1 = roomRegisterDAO.findByRoomNumber(2);
        for(RoomRegister rr : list1) {
            System.out.println(rr.toString());
        }
        System.out.println();
        IRoomDAO roomDAO = daoFactory.getRoomDAO();
        ArrayList<Room> list2 = roomDAO.findFreeRoomsByParams(Room.RoomType.Standard, 1, 1, 100);
        for(Room r : list2)
            System.out.println(r.toString());
    }
}