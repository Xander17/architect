package ru.geekbrains.atmclient.db;

import org.springframework.stereotype.Component;
import ru.geekbrains.atmclient.model.User;

import java.sql.SQLException;

@Component
public class UserRepository {

    private UserDataMapper userDataMapper;
    private IdentifyMap<Integer, User> identifyMap;

    public User findById(int id) throws SQLException {
        if (identifyMap.contains(id)) {
            return identifyMap.get(id);
        }
        User user = userDataMapper.findById(id);
        identifyMap.add(user);
        return user;
    }

    public void insert(User user) throws SQLException {
        userDataMapper.insert(user);
        identifyMap.add(user);
    }

    public void update(User user) throws SQLException {
        userDataMapper.update(user);
        identifyMap.add(user);
    }

    public void delete(User user) throws SQLException {
        userDataMapper.delete(user);
        identifyMap.delete(user);
    }
}
