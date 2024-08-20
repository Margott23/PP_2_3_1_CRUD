package ru.my.crud.services;

import ru.my.crud.models.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(int id);

    void save(User user);

    void update(int id, User updateUser);

    void delete(User user);

}
