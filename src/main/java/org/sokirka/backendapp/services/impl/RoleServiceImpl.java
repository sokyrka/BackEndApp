package org.sokirka.backendapp.services.impl;

import org.sokirka.backendapp.dao.RoleDao;
import org.sokirka.backendapp.entities.Role;
import org.sokirka.backendapp.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eugine Sokirka
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getAllRoles() {
        List<Role> roleList = new ArrayList<>();
        roleDao.findAll().forEach(roleList::add);
        return roleList;
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public void update(Role role) {
        roleDao.save(role);
    }

    @Override
    public void delete(Role role) {
        roleDao.delete(role);
    }
}
