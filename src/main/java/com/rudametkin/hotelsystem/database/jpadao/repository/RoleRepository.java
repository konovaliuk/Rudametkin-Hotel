package com.rudametkin.hotelsystem.database.jpadao.repository;

import com.rudametkin.hotelsystem.entitys.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
