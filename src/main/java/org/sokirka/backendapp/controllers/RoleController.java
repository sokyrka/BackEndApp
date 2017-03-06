package org.sokirka.backendapp.controllers;

import org.sokirka.backendapp.entities.Role;
import org.sokirka.backendapp.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Eugine Sokirka
 */
@RestController
@RequestMapping(value = "/role/{id}")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public Role getRole(@PathVariable("id") String id) {
        return roleService.getById(Long.parseLong(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void newRole(@RequestBody Role role) {
        roleService.save(role);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateRole(@RequestBody Role role) {
        roleService.update(role);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteRole(@RequestBody Role role) {
        roleService.delete(role);
    }
}
