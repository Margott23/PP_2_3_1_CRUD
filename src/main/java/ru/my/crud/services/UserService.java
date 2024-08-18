package ru.my.crud.services;

import ru.my.crud.models.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public User findById(int id);

    public void save(User user);

    public void update(int id, User updateUser);

    public void delete(User user);

}
