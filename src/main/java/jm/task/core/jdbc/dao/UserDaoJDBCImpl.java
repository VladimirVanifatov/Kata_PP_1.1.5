package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String query1 = "CREATE TABLE IF NOT EXISTS users(id int auto_increment primary key, name varchar(100), lastname varchar(100), age int)";
            statement.executeUpdate(query1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String query1 = "DROP TABLE IF EXISTS users";
            statement.executeUpdate(query1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Statement statement = connection.createStatement();
            String query1 = "INSERT INTO users(name, lastname, age) values ('"+ name + "', '" + lastName +"', "+ age +" )";
            statement.executeUpdate(query1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try {
            Statement statement = connection.createStatement();
            String query1 = "DELETE FROM users WHERE id = " + id;
            statement.executeUpdate(query1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query1 = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query1);

            while(resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String query1 = "DELETE FROM users";
            statement.executeUpdate(query1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
