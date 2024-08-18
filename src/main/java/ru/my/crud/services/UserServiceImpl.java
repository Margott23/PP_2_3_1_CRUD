package ru.my.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.my.crud.dao.UserDAO;
import ru.my.crud.models.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> findAll() {
        return userDAO.getAllUsers();
    }

    @Override
    public User findById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public void save(User user) {
        userDAO.addUser(user);
    }

    @Override
    public void update(User updateUser) {
        userDAO.updateUser(updateUser);
    }

    @Override
    public void delete(User user) {
        userDAO.deleteUser(user.getId());
    }
}
