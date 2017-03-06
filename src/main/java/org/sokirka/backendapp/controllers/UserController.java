package org.sokirka.backendapp.controllers;

import org.sokirka.backendapp.dao.RoleDao;
import org.sokirka.backendapp.dao.UserDao;
import org.sokirka.backendapp.entities.Role;
import org.sokirka.backendapp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eugine Sokirka
 */
@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public User addUser(@RequestParam("name") String userName,
                        @RequestParam("pass") String passWord,
                        @RequestParam("isActive") int isActive) {

        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setName("Admin");
        roleDao.save(role);
        roles.add(role);

        User user = new User();
        user.setUserName(userName);
        user.setPassWord(passWord);
        user.setIsActive(isActive == 1);
        user.setRoles(roles);

        userDao.save(user);

        return user;
    }
}
