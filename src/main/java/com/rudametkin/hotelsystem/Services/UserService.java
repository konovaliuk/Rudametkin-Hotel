package com.rudametkin.hotelsystem.Services;

import com.rudametkin.hotelsystem.Database.DAOFactory.IRoleDAO;
import com.rudametkin.hotelsystem.Database.DAOFactory.IUserDAO;
import com.rudametkin.hotelsystem.Database.DAOFactory.IUserRoleDAO;
import com.rudametkin.hotelsystem.Database.MySqlDAOFactory.MySqlDAOFactory;
import com.rudametkin.hotelsystem.Database.MySqlDAOFactory.MySqlUserRoleDAO;
import com.rudametkin.hotelsystem.Database.TransactionHandler.TransactionHandler;
import com.rudametkin.hotelsystem.Services.ServiceHelpClasses.RegisterDataCheckInfo;
import com.rudametkin.hotelsystem.EntityObjects.Role;
import com.rudametkin.hotelsystem.EntityObjects.User;
import com.rudametkin.hotelsystem.EntityObjects.UserRole;
import com.rudametkin.hotelsystem.EntityObjects.UserWithRoles;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    public UserWithRoles authenticateUser(String login, String password) {
        return new TransactionHandler().execute(connection -> {
            IUserDAO userDAO = MySqlDAOFactory.getInstance().getUserDAO();
            User user = userDAO.findByLoginPassword(login, password, connection);
            if(user == null)
                return null;

            IUserRoleDAO userRoleDAO = MySqlDAOFactory.getInstance().getUserRoleDAO();
            List<UserRole> userRoles = userRoleDAO.findByUserId(user.getId(), connection);

            IRoleDAO roleDAO = MySqlDAOFactory.getInstance().getRoleDAO();
            List<Role> roles = new ArrayList<>();
            for(UserRole userRole : userRoles)
                roles.add(roleDAO.findById(userRole.getRoleId(), connection));

            return new UserWithRoles(user, roles);
        });
    }

    public RegisterDataCheckInfo checkRegisterData(User params) {
        RegisterDataCheckInfo dataCheckInfo = new RegisterDataCheckInfo();
        new TransactionHandler().execute(connection -> {
            IUserDAO userDAO = MySqlDAOFactory.getInstance().getUserDAO();
            dataCheckInfo.setIsUniqueEmail(userDAO.findByEmail(params.getEmail(), connection) == null);
            dataCheckInfo.setIsUniqueLogin(userDAO.findByLogin(params.getLogin(), connection) == null);
            dataCheckInfo.setIsUniquePhone(userDAO.findByPhone(params.getPhone(), connection) == null);
        });
        return dataCheckInfo;
    }

    public boolean tryRegisterUser(User params) {
        Boolean success = new TransactionHandler().<Boolean>execute(connection -> {
            IUserDAO userDAO = MySqlDAOFactory.getInstance().getUserDAO();
            int id = userDAO.add(params, connection);

            IRoleDAO roleDAO = MySqlDAOFactory.getInstance().getRoleDAO();
            int clientRoleId = roleDAO.findByName("Client", connection).getId();

            IUserRoleDAO userRoleDAO = MySqlDAOFactory.getInstance().getUserRoleDAO();
            userRoleDAO.add(id, clientRoleId, connection);

            return true;
        });
        return (success != null) ? success : false;
    }
}
