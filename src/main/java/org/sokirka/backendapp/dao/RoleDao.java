package org.sokirka.backendapp.dao;

import org.sokirka.backendapp.entities.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Eugine Sokirka
 */
public interface RoleDao extends CrudRepository<Role, Long> {
}
