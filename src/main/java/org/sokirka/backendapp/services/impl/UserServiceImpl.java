package org.sokirka.backendapp.services.impl;

import org.sokirka.backendapp.dao.UserDao;
import org.sokirka.backendapp.entities.User;
import org.sokirka.backendapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eugine Sokirka
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getByName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        userDao.findAll().forEach(userList::add);
        return userList;
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void update(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public Boolean authenticate(User user) {
        User userInDB = getByName(user.getUserName());
        return userInDB.getPassWord().equals(user.getPassWord());
    }
}
