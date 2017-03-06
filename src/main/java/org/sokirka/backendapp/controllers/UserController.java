package org.sokirka.backendapp.controllers;

import org.sokirka.backendapp.dao.UserDao;
import org.sokirka.backendapp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Eugine Sokirka
 */
@RestController
@RequestMapping(value = "/user/{id}")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(method = RequestMethod.GET)
    public User getUser(@PathVariable("id") String id) {
        return userDao.findOne(Long.parseLong(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void newUser(@RequestBody User user) {
        userDao.save(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody User user) {
        userDao.save(user);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@RequestBody User user) {
        userDao.delete(user);
    }
}
