package com.rudametkin.hotelsystem.Database.JPADAOFactory;

import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.Database.DAOFactory.IUserRoleDAO;
import com.rudametkin.hotelsystem.Entitys.User;
import com.rudametkin.hotelsystem.Entitys.UserRole;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class JPAUserRoleDAO implements IUserRoleDAO {
    private EntityManager entityManager = Persistence.createEntityManagerFactory("Hotel").createEntityManager();
    @Override
    public void save(UserRole userRole) throws DAOException {
        try {
            entityManager.persist(userRole);
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
    }
    @Override
    public void removeById(int userRoleId) throws DAOException {
        UserRole userRole = entityManager.find(UserRole.class, userRoleId);
        if(userRole != null)
            entityManager.remove(userRole);
    }
    @Override
    public List<UserRole> findByUserId(int userId) throws DAOException {
        return entityManager.createQuery("SELECT ur FROM UserRole ur WHERE ur.userId =: userId", UserRole.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
