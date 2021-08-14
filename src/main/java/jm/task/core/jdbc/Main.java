package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;


import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao ud = new UserDaoJDBCImpl();
//        ud.dropUsersTable();
        ud.createUsersTable();
        System.out.println("Saving user in DB...");
        ud.saveUser("A", "AA", (byte) 10);
        ud.saveUser("B", "BB", (byte) 20);
        ud.saveUser("C", "CC", (byte) 30);
        ud.saveUser("D", "DD", (byte) 40);
        ud.getAllUsers();
        ud.cleanUsersTable();
        ud.dropUsersTable();

    }
}
