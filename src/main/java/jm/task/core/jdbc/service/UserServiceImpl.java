package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao ud = new UserDaoJDBCImpl();

    public void createUsersTable() throws SQLException, ClassNotFoundException {
        ud.createUsersTable();
    }

    public void dropUsersTable() throws SQLException, ClassNotFoundException {
        ud.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException, ClassNotFoundException {
        ud.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException, ClassNotFoundException {
        ud.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {

        return ud.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        ud.cleanUsersTable();
    }
}
