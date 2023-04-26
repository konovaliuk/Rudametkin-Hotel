package com.rudametkin.hotelsystem.Database.JPADAOFactory;

import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.Database.DAOFactory.IBillDAO;
import com.rudametkin.hotelsystem.Entitys.Bill;
import com.rudametkin.hotelsystem.Entitys.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class JPABillDAO implements IBillDAO {
    private EntityManager entityManager = Persistence.createEntityManagerFactory("Hotel").createEntityManager();

    @Override
    public void save(Bill bill) throws DAOException {
        try {
            entityManager.persist(bill);
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public void update(Bill bill) throws DAOException {
        try {
            entityManager.merge(bill);
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public List<Bill> findByRegisterId(int registerId) throws DAOException {
        return entityManager.createQuery("SELECT b from Bill b WHERE b.registerId =: registerId", Bill.class)
                .setParameter("registerId", registerId)
                .getResultList();
    }
}
