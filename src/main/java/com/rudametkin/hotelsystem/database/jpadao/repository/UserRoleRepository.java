package com.rudametkin.hotelsystem.database.jpadao.repository;

import com.rudametkin.hotelsystem.entitys.User;
import com.rudametkin.hotelsystem.entitys.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    List<UserRole> findAllByUserId(int userId);
}
