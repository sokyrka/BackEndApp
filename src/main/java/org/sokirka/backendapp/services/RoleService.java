package org.sokirka.backendapp.services;

import org.sokirka.backendapp.entities.Role;

/**
 * @author Eugine Sokirka
 */
public interface RoleService {

    /**
     * Returns role by id
     * @param id unique identificator
     * @return role entity
     */
    Role getById(Long id);

    /**
     * Saves role entity to DB
     * @param role role entity
     */
    void save(Role role);

    /**
     * Updates role entity in DB
     * @param role role entity
     */
    void update(Role role);

    /**
     * Deletes role entity from DB
     * @param role role entity
     */
    void delete(Role role);
}
