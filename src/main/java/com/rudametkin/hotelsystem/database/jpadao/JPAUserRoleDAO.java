package com.rudametkin.hotelsystem.database.jpadao;

import com.rudametkin.hotelsystem.database.daofactory.DAOException;
import com.rudametkin.hotelsystem.database.daofactory.IUserRoleDAO;
import com.rudametkin.hotelsystem.database.jpadao.repository.UserRepository;
import com.rudametkin.hotelsystem.database.jpadao.repository.UserRoleRepository;
import com.rudametkin.hotelsystem.entitys.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JPAUserRoleDAO implements IUserRoleDAO {
    private UserRoleRepository userRoleRepository;

    @Autowired
    public JPAUserRoleDAO(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void save(UserRole userRole) throws DAOException {
        userRoleRepository.save(userRole);
    }

    @Override
    public void removeById(int userRoleId) throws DAOException {
        userRoleRepository.deleteById(userRoleId);
    }

    @Override
    public List<UserRole> findByUserId(int userId) throws DAOException {
        return userRoleRepository.findAllByUserId(userId);
    }
}
