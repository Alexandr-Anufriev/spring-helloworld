package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    List<User> users;

    public UserDaoJDBCImpl() {
        connection = Util.getConnection();
    }

    public void createUsersTable() {
        System.out.println("Creating table...");
        try {
            statement = connection.createStatement();
            String SQL = "CREATE TABLE mydbtest.users " +
                    "(id BIGINT AUTO_INCREMENT," +
                    "name VARCHAR(50)," +
                    "lastName VARCHAR(50)," +
                    "age TINYINT," +
                    "PRIMARY KEY (id))";

            statement.execute(SQL);
            System.out.println("Table users successfully created...");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Table users has already exist...");
        }
    }

    public void dropUsersTable() {
        System.out.println("Dropping table...");
        String SQL = "DROP TABLE users";
        try {
            statement = connection.createStatement();
            statement.execute(SQL);
            System.out.println("Table users successfully dropped...");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Table users does not exist...");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

            System.out.println("User named - (" + name + ") has been added into table users...");
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Unable save user - (" + name + ")");
        }
    }

    public void removeUserById(long id) {
        System.out.println("Removing user by id...");
        String SQL = "DROP FROM users WHERE id = ?";

        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.printf("User by id = %d has dropped...\n", id);
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.printf("Unable drop user by id = %d...\n", id);
        }
    }

    public List<User> getAllUsers() {
        users = new ArrayList<>();

        System.out.println("Getting users from DB...");
        String SQL = "SELECT * FROM users";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");

                User user = new User(name, lastName, age);
                System.out.println(user);
                users.add(user);
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Unable get all users...");
        }
        return users;
    }

    public void cleanUsersTable() {
        System.out.println("Dropping users from DB...");
        String SQL = "SELECT * FROM users";

        try {
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                resultSet.deleteRow();
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable delete all users...");
        }
    }
}
