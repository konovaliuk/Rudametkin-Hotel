package DAOFactory;

import entityObjects.User;

public interface IUserDAO {
    void add(User user) throws DAOException;
    User findByEmail(String email) throws DAOException;
    User findByLogin(String login) throws DAOException;
    User findByPhone(String phone) throws DAOException;
    void update(User user) throws DAOException;
    void removeById(int id) throws DAOException;
}
