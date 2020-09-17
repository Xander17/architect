package ru.geekbrains.atmclient.db;

import org.springframework.stereotype.Component;
import ru.geekbrains.atmclient.exception.RecordNotFoundException;
import ru.geekbrains.atmclient.model.Role;
import ru.geekbrains.atmclient.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserDataMapper {
    private final Connection connection;

    public UserDataMapper(Connection connection) {
        this.connection = connection;
    }

    public User findById(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id, name, role FROM user WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt(1));
                    user.setName(resultSet.getString(2));
                    user.setRole(Role.valueOf(resultSet.getString(3)));
                    return user;
                }
            }
        }
        throw new RecordNotFoundException(id);
    }

    public void insert(User user) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO user(`name`,`role`) VALUES(?,?)")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getRole().name());
            statement.execute();
        }
    }

    public void update(User user) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE user SET name=?, role=? WHERE id=?")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getRole().name());
            statement.setInt(3, user.getId());
            statement.execute();
        }
    }

    public void delete(User user) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM user WHERE id=?")) {
            statement.setInt(1, user.getId());
            statement.execute();
        }
    }

}
