package org.sokirka.backendapp.dao;

import org.sokirka.backendapp.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Eugine Sokirka
 */
public interface UserDao extends CrudRepository<User, Long> {
}
