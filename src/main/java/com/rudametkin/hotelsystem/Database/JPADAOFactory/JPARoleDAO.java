package com.rudametkin.hotelsystem.Database.JPADAOFactory;

import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.Database.DAOFactory.IRoleDAO;
import com.rudametkin.hotelsystem.Entitys.Role;
import com.rudametkin.hotelsystem.Entitys.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class JPARoleDAO implements IRoleDAO {
    private EntityManager entityManager = Persistence.createEntityManagerFactory("Hotel").createEntityManager();

    @Override
    public void save(Role role) throws DAOException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(role);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DAOException(e.getMessage());
        }
    }
    @Override
    public void removeById(int id) throws DAOException {
        entityManager.getTransaction().begin();
        Role role = entityManager.find(Role.class, id);
        if(role != null)
            entityManager.remove(role);
        entityManager.getTransaction().commit();
    }
    @Override
    public Role findById(int id) throws DAOException {
        return entityManager.find(Role.class, id);
    }
    @Override
    public Role findByName(String name) throws DAOException {
        List<Role> results = entityManager.createQuery("SELECT r from Role r WHERE r.name =: name ", Role.class)
                .setParameter("name", name)
                .getResultList();
        return (results.size() == 0) ? null : results.get(0);
    }
}
