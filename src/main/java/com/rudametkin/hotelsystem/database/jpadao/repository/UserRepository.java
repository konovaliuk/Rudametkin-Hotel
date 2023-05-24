package com.rudametkin.hotelsystem.database.jpadao.repository;

import com.rudametkin.hotelsystem.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


    public interface UserRepository extends JpaRepository<User, Integer> {
        User findByLoginAndPassword(String login, String password);
        User findByLogin(String login);
        User findByEmail(String email);
        User findByPhone(String phone);
}
