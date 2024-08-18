package ru.my.crud.dao;

import ru.my.crud.models.User;

import java.util.List;

public interface UserDAO {

    public List<User> getAllUsers();

    public User getUserById(int id);

    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUser(int id);
}
