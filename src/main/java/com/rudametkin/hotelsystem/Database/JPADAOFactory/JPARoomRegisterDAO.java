package com.rudametkin.hotelsystem.Database.JPADAOFactory;

import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.Database.DAOFactory.IRoomRegisterDAO;
import com.rudametkin.hotelsystem.Entitys.RoomRegister;
import com.rudametkin.hotelsystem.Entitys.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class JPARoomRegisterDAO implements IRoomRegisterDAO {
    private EntityManager entityManager = Persistence.createEntityManagerFactory("Hotel").createEntityManager();

    @Override
    public int save(RoomRegister roomRegister) throws DAOException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(roomRegister);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DAOException(e.getMessage());
        }
        return roomRegister.getId();
    }
    @Override
    public void removeById(int id) throws DAOException {
        entityManager.getTransaction().begin();
        RoomRegister roomRegister = entityManager.find(RoomRegister.class, id);
        if(roomRegister != null)
            entityManager.remove(roomRegister);
        entityManager.getTransaction().commit();
    }
    @Override
    public RoomRegister findLastByRoomNumber(int number) throws DAOException {
        List<RoomRegister> registers = entityManager.createQuery("SELECT rr FROM RoomRegister rr WHERE rr.roomNumber =: roomNumber ORDER BY rr.startDateTime DESC LIMIT 1", RoomRegister.class)
                .setParameter("roomNumber", number)
                .getResultList();
        return (registers.size() == 0) ? null : registers.get(0);
    }
    @Override
    public List<RoomRegister> findAllByClientId(int id) throws DAOException {
        return entityManager.createQuery("SELECT rr FROM RoomRegister rr WHERE rr.clientId =: clientId ", RoomRegister.class)
                .setParameter("clientId", id)
                .getResultList();
    }
}
