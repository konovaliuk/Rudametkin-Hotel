package com.rudametkin.hotelsystem.database.jpadao;

import com.rudametkin.hotelsystem.database.daofactory.DAOException;
import com.rudametkin.hotelsystem.database.daofactory.IUserDAO;
import com.rudametkin.hotelsystem.database.jpadao.repository.UserRepository;
import com.rudametkin.hotelsystem.entitys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
public class JPAUserDAO implements IUserDAO {
    private final UserRepository userRepository;

    public JPAUserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int save(User user) throws DAOException {
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public User findByLogin(String login) throws DAOException {
        return userRepository.findByLogin(login);
    }

    @Override
    public User findByEmail(String email) throws DAOException {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByPhone(String phone) throws DAOException {
        return userRepository.findByPhone(phone);
    }

    @Override
    public User findByLoginPassword(String login, String password) throws DAOException {
        return userRepository.findByLoginAndPassword(login, password);
    }

    @Override
    public void update(User user) throws DAOException {
        userRepository.save(user);
    }

    @Override
    public void removeById(int id) throws DAOException {
        userRepository.deleteById(id);
    }
}
