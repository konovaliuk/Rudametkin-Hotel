package DAOFactory;

import entityObjects.UserRole;

import java.util.ArrayList;

public interface IUserRoleDAO {
    void add(int userId, int roleId) throws DAOException;
    void remove(int userId, int roleId) throws DAOException;
    ArrayList<UserRole> findByUserId(int userId) throws DAOException;
}
