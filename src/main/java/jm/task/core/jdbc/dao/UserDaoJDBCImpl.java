package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;

    public UserDaoJDBCImpl() {
        connection = Util.getConnection();
    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String SQL = "CREATE TABLE IF NOT EXISTS mydbtest.users " +
                    "(id BIGINT AUTO_INCREMENT," +
                    "name VARCHAR(50)," +
                    "lastName VARCHAR(50)," +
                    "age TINYINT," +
                    "PRIMARY KEY (id))";
            statement.execute(SQL);
        } catch (SQLException e) {
            System.out.println("UNABLE createUsersTable");
        }
    }

    public void dropUsersTable() {
        String SQL = "DROP TABLE IF EXISTS users";
        try (Statement statement = connection.createStatement()) {
            statement.execute(SQL);
        } catch (SQLException e) {
            System.out.println("Table users does not exist...");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User named - (" + name + ") has been added into table users...");
        } catch (SQLException e) {
            System.out.println("Unable save user - (" + name + ")");
        }
    }

    public void removeUserById(long id) {
        System.out.println("Removing user by id...");
        String SQL = "DROP FROM users WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.printf("Unable drop user by id = %d...\n", id);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String SQL = "SELECT * FROM users";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");

                User user = new User(name, lastName, age);
                System.out.println(user);
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Unable get all users...");
        }
        return users;
    }

    public void cleanUsersTable() {
        String SQL = "SELECT * FROM users";

        try (Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                resultSet.deleteRow();
            }
        } catch (SQLException e) {
            System.out.println("Unable delete all users...");
        }
    }
}
