package org.sokirka.backendapp.services;

import org.sokirka.backendapp.entities.Role;

import java.util.List;

/**
 * @author Eugine Sokirka
 */
public interface RoleService {

    /**
     * Returns all roles from DB
     * @return list of roles
     */
    List<Role> getAllRoles();

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
