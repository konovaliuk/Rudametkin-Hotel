package com.rudametkin.hotelsystem.Services;

import com.rudametkin.hotelsystem.DTO.RegisterDto;
import com.rudametkin.hotelsystem.DTO.UserDto;
import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.Database.DAOFactory.IRoleDAO;
import com.rudametkin.hotelsystem.Database.DAOFactory.IUserDAO;
import com.rudametkin.hotelsystem.Database.DAOFactory.IUserRoleDAO;
import com.rudametkin.hotelsystem.Database.MySqlDAOFactory.MySqlDAOFactory;
import com.rudametkin.hotelsystem.Database.MySqlDAOFactory.MySqlTransactionalDAO;
import com.rudametkin.hotelsystem.Database.SqlConnection.MySqlDataSource;
import com.rudametkin.hotelsystem.Database.TransactionHandling.*;
import com.rudametkin.hotelsystem.Entitys.Role;
import com.rudametkin.hotelsystem.Entitys.User;
import com.rudametkin.hotelsystem.Entitys.UserRole;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    public UserDto authenticateUser(String login, String password) {
        return new JDBCTransactionManager().execute(connection -> {
            IUserDAO userDAO = MySqlDAOFactory.getInstance().getUserDAO(connection);
            User user = userDAO.findByLoginPassword(login, password);
            if(user == null)
                return null;

            IUserRoleDAO userRoleDAO = MySqlDAOFactory.getInstance().getUserRoleDAO(connection);
            List<UserRole> userRoles = userRoleDAO.findByUserId(user.getId());

            IRoleDAO roleDAO = MySqlDAOFactory.getInstance().getRoleDAO(connection);
            List<Role> roles = new ArrayList<>();
            for(UserRole userRole : userRoles)
                roles.add(roleDAO.findById(userRole.getRoleId()));

            return new UserDto(user, roles);
        });
    }

    public RegisterDto checkRegisterData(User params) {
        RegisterDto dataCheckInfo = new RegisterDto();

        new JDBCTransactionManager().execute(connection -> {
            IUserDAO userDAO = MySqlDAOFactory.getInstance().getUserDAO(connection);
            dataCheckInfo.setUniqueEmail(userDAO.findByEmail(params.getEmail()) == null);
            dataCheckInfo.setUniqueLogin(userDAO.findByLogin(params.getLogin()) == null);
            dataCheckInfo.setUniquePhone(userDAO.findByPhone(params.getPhone()) == null);
        });

        return dataCheckInfo;
    }

    public boolean tryRegisterUser(User newUser) {
        Boolean success = new JDBCTransactionManager().execute(connection -> {
            IUserDAO userDAO = MySqlDAOFactory.getInstance().getUserDAO(connection);
            int id = userDAO.save(newUser);

            IRoleDAO roleDAO = MySqlDAOFactory.getInstance().getRoleDAO(connection);
            int clientRoleId = roleDAO.findByName("Client").getId();

            IUserRoleDAO userRoleDAO = MySqlDAOFactory.getInstance().getUserRoleDAO(connection);
            userRoleDAO.save(id, clientRoleId);

            return true;
        });

        return (success == null) ? false : success;
    }
}
