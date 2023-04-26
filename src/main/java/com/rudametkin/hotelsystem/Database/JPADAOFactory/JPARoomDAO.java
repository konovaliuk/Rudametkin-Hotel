package com.rudametkin.hotelsystem.Database.JPADAOFactory;

import com.rudametkin.hotelsystem.DTO.RoomClassDto;
import com.rudametkin.hotelsystem.DTO.RoomSearchDto;
import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.Database.DAOFactory.IRoomDAO;
import com.rudametkin.hotelsystem.Entitys.Room;
import com.rudametkin.hotelsystem.Entitys.RoomRegister;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class JPARoomDAO implements IRoomDAO {
    private EntityManager entityManager = Persistence.createEntityManagerFactory("Hotel").createEntityManager();

    @Override
    public void update(Room room) throws DAOException {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(room);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public List<RoomClassDto> findFreeRoomClassesByParams(RoomSearchDto rp, int offset, int limit) throws DAOException {
        String typeCondition = (rp.getType() == Room.RoomType.Any) ? "" : "r.type = (:typeValue) AND ";
        TypedQuery<RoomClassDto> qry = entityManager.createQuery("SELECT DISTINCT NEW com.rudametkin.hotelsystem.DTO.RoomClassDto(r.type, r.roomsAmount, r.singleBedsAmount, r.doubleBedsAmount, r.miniBar, r.tv, r.dryer, r.price)  " +
                "FROM Room r WHERE " + typeCondition +
                "(2*r.doubleBedsAmount+r.singleBedsAmount) >= (:capacity) " +
                "AND (r.doubleBedsAmount+r.singleBedsAmount) >= (:bedsAmount) " +
                "AND (" +
                "    SELECT COUNT(r) FROM RoomRegister rr" +
                "    WHERE (rr.endDateTime > (:startDateTime)) AND (rr.startDateTime < (:endDateTime)) " +
                "    AND rr.roomNumber = r.number" +
                "    ) = 0 ", RoomClassDto.class).setMaxResults(limit).setFirstResult(offset);

        if(rp.getType() != Room.RoomType.Any)
            qry.setParameter("typeValue", rp.getType());
        qry.setParameter("capacity", rp.getCapacity());
        qry.setParameter("bedsAmount", rp.getBedsAmount());
        qry.setParameter("endDateTime", rp.getDepartureDate());
        qry.setParameter("startDateTime", rp.getArrivalDate());

        return qry.getResultList();
    }

    @Override
    public Room findFreeRoomByRoomClass(RoomClassDto roomclass, Timestamp arrivalTime, Timestamp departureTime) throws DAOException {
        TypedQuery<Room> query = entityManager.createQuery("SELECT r FROM Room r WHERE " +
                "type =: typeValue " +
                "AND r.roomsAmount =: roomsAmount " +
                "AND r.singleBedsAmount =: singleBedsAmount " +
                "AND r.doubleBedsAmount =: doubleBedsAmount " +
                "AND r.miniBar =: miniBar " +
                "AND r.tv =: tv " +
                "AND r.dryer =: dryer " +
                "AND r.price =: price " +
                "AND (" +
                "    SELECT COUNT(rr) FROM RoomRegister rr" +
                "    WHERE (rr.endDateTime > (:startDateTime)) AND (rr.startDateTime < (:endDateTime)) " +
                "    AND rr.roomNumber = r.number" +
                "    ) = 0 ", Room.class).setMaxResults(1);


        query.setParameter("typeValue", roomclass.getType());
        query.setParameter("roomsAmount", roomclass.getRoomsAmount());
        query.setParameter("singleBedsAmount", roomclass.getSingleBedsAmount());
        query.setParameter("doubleBedsAmount", roomclass.getDoubleBedsAmount());
        query.setParameter("miniBar", roomclass.isMiniBar());
        query.setParameter("tv", roomclass.isTv());
        query.setParameter("dryer", roomclass.isDryer());
        query.setParameter("price", roomclass.getPrice());
        query.setParameter("startDateTime", arrivalTime);
        query.setParameter("endDateTime", departureTime);

        List<Room> result = query.getResultList();
        return (result.size() == 0) ? null : result.get(0);
    }

    @Override
    public int countFreeRoomClassesByParams(RoomSearchDto rp) throws DAOException {
        String typeCondition = (rp.getType() == Room.RoomType.Any) ? "" : "r.type =: typeValue AND ";
        TypedQuery<Integer> qry = entityManager.createQuery("SELECT COUNT( DISTINCT NEW com.rudametkin.hotelsystem.DTO.RoomClassDto(r.type, r.roomsAmount, r.singleBedsAmount, r.doubleBedsAmount, r.miniBar, r.tv, r.dryer, r.price) ) " +
                "FROM Room r WHERE " + typeCondition +
                "(2*r.doubleBedsAmount+r.singleBedsAmount) >= (:capacity) " +
                "AND (r.doubleBedsAmount+r.singleBedsAmount) >= (:bedsAmount) " +
                "AND (" +
                "    SELECT COUNT(r) FROM RoomRegister rr" +
                "    WHERE (rr.endDateTime > (:startDateTime)) AND (rr.startDateTime < (:endDateTime)) " +
                "    AND rr.roomNumber = r.number" +
                "    ) = 0 ", Integer.class);

        if(rp.getType() != Room.RoomType.Any)
            qry.setParameter("typeValue", rp.getType().toString());
        qry.setParameter("capacity", rp.getCapacity());
        qry.setParameter("bedsAmount", rp.getBedsAmount());
        qry.setParameter("endDateTime", rp.getDepartureDate());
        qry.setParameter("startDateTime", rp.getArrivalDate());

        return qry.getSingleResult();
    }
}
