package com.rudametkin.hotelsystem.services;

import com.rudametkin.hotelsystem.database.daofactory.DAOFactory;
import com.rudametkin.hotelsystem.database.transaction.TransactionManager;
import com.rudametkin.hotelsystem.dto.RegisterDto;
import com.rudametkin.hotelsystem.dto.UserDto;
import com.rudametkin.hotelsystem.database.daofactory.IRoleDAO;
import com.rudametkin.hotelsystem.database.daofactory.IUserDAO;
import com.rudametkin.hotelsystem.database.daofactory.IUserRoleDAO;
import com.rudametkin.hotelsystem.entitys.Role;
import com.rudametkin.hotelsystem.entitys.User;
import com.rudametkin.hotelsystem.entitys.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class UserService {

    private final DAOFactory daoFactory;
    private final TransactionManager transactionManager;

    @Autowired
    public UserService(DAOFactory daoFactory, TransactionManager transactionManager) {
        this.daoFactory = daoFactory;
        this.transactionManager = transactionManager;
    }
    public UserDto authenticateUser(String login, String password) {
        return transactionManager.execute(connection -> {
            IUserDAO userDAO = daoFactory.getUserDAO(connection);
            User user = userDAO.findByLoginPassword(login, password);
            if(user == null)
                return null;

            IUserRoleDAO userRoleDAO = daoFactory.getUserRoleDAO(connection);
            List<UserRole> userRoles = userRoleDAO.findByUserId(user.getId());

            IRoleDAO roleDAO = daoFactory.getRoleDAO(connection);
            List<Role> roles = new ArrayList<>();
            for(UserRole userRole : userRoles)
                roles.add(roleDAO.findById(userRole.getRoleId()));

            return new UserDto(user, roles);
        });
    }

    public RegisterDto checkRegisterData(User params) {
        RegisterDto dataCheckInfo = new RegisterDto();

        transactionManager.execute(connection -> {
            IUserDAO userDAO = daoFactory.getUserDAO(connection);
            dataCheckInfo.setUniqueEmail(userDAO.findByEmail(params.getEmail()) == null);
            dataCheckInfo.setUniqueLogin(userDAO.findByLogin(params.getLogin()) == null);
            dataCheckInfo.setUniquePhone(userDAO.findByPhone(params.getPhone()) == null);
        });

        return dataCheckInfo;
    }

    public boolean tryRegisterUser(User newUser) {
        Boolean success = transactionManager.execute(connection -> {
            IUserDAO userDAO = daoFactory.getUserDAO(connection);
            int id = userDAO.save(newUser);

            IRoleDAO roleDAO = daoFactory.getRoleDAO(connection);
            int clientRoleId = roleDAO.findByName("Client").getId();

            IUserRoleDAO userRoleDAO = daoFactory.getUserRoleDAO(connection);
            UserRole userRole = new UserRole(id, clientRoleId);
            userRoleDAO.save(userRole);

            return true;
        });

        return success != null && success;
    }
}
