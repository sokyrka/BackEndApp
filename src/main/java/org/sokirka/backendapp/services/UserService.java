package org.sokirka.backendapp.services;

import org.sokirka.backendapp.entities.User;

import java.util.List;

/**
 * @author Eugine Sokirka
 */
public interface UserService {

    /**
     * Returns user by name
     * @param userName input parameter
     * @return user entity
     */
    User getByName(String userName);

    /**
     * Returns all users from DB
     * @return list of users
     */
    List<User> getAllUsers();

    /**
     * Saves user entity to DB
     * @param user user entity
     */
    void save(User user);

    /**
     * Updates user entity in DB
     * @param user user entity
     */
    void update(User user);

    /**
     * Deletes user entity from DB
     * @param user user entity
     */
    void delete(User user);

    /**
     * Authenticates user
     * @param user input parameter
     * @return boolean value
     */
    Boolean authenticate(User user);
}
