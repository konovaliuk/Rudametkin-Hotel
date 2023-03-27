package DAOFactory;

import entityObjects.RoomRegister;

import java.util.ArrayList;

public interface IRoomRegisterDAO {
    void add(RoomRegister roomRegister) throws DAOException;
    ArrayList<RoomRegister> findByRoomNumber(int number) throws DAOException;
    ArrayList<RoomRegister> findByRoomNumberAtCurrentTime(int number) throws DAOException;
}
