package org.sokirka.backendapp.controllers;

import org.sokirka.backendapp.entities.User;
import org.sokirka.backendapp.services.UserService;
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
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public User getUser(@PathVariable("id") String id) {
        return userService.getById(Long.parseLong(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void newUser(@RequestBody User user) {
        userService.save(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody User user) {
        userService.update(user);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@RequestBody User user) {
        userService.delete(user);
    }
}
