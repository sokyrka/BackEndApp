package org.sokirka.backendapp.services;

import org.sokirka.backendapp.entities.User;

/**
 * @author Eugine Sokirka
 */
public interface UserService {

    /**
     * Returns user by id
     * @param id unique identificator
     * @return user entity
     */
    User getById(Long id);

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
}
