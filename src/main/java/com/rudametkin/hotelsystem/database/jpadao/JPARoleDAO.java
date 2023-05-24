package com.rudametkin.hotelsystem.database.jpadao;

import com.rudametkin.hotelsystem.database.daofactory.DAOException;
import com.rudametkin.hotelsystem.database.daofactory.IRoleDAO;
import com.rudametkin.hotelsystem.database.jpadao.repository.RoleRepository;
import com.rudametkin.hotelsystem.database.jpadao.repository.UserRepository;
import com.rudametkin.hotelsystem.entitys.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JPARoleDAO implements IRoleDAO {

    private final RoleRepository roleRepository;

    @Autowired
    public JPARoleDAO(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(Role role) throws DAOException {
        roleRepository.save(role);
    }

    @Override
    public void removeById(int id) throws DAOException {
        roleRepository.deleteById(id);
    }

    @Override
    public Role findById(int id) throws DAOException {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Role findByName(String name) throws DAOException {
        return roleRepository.findByName(name);
    }
}
