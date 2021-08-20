package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;

public class Util {
    private static final String DATABASE_URL = "jdbc:mysql://127.0.0.1/mydbtest";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String NAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;
    private static SessionFactory sessionFactory;

    public Util() {
    }
        //Соединение через JDBS напрямую
    public static Connection getConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, NAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
        //Соединение через Hibernate
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, JDBC_DRIVER);
                settings.put(Environment.URL, DATABASE_URL);
                settings.put(Environment.USER, NAME);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
                return sessionFactory;
        } else {
            return sessionFactory;
        }
    }
}
