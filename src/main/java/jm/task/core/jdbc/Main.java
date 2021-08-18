package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Александр", "Сидиров", (byte)25);
        userService.saveUser("Владимир", "Винокур", (byte) 70);
        userService.saveUser("Сергей", "Малахов", (byte) 43);
        userService.saveUser("Екатерина", "Петросян", (byte) 19);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
