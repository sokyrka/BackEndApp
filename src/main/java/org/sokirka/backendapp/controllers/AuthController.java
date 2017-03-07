package org.sokirka.backendapp.controllers;

import org.sokirka.backendapp.entities.User;
import org.sokirka.backendapp.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eugine Sokirka
 */
@RestController(value = "/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @RequestMapping(method = RequestMethod.POST)
    public String getToken(@RequestBody final UserLogin userLogin) {
        User user = new User();
        String token = null;
        if (userLogin != null) {
            user.setUserName(userLogin.userName);
            user.setPassWord(userLogin.pass);
            token = jwtService.generateToken(user);
        }
        return token;
    }

    @SuppressWarnings("unused")
    private static class UserLogin {
        private String userName;
        private String pass;
    }
}
