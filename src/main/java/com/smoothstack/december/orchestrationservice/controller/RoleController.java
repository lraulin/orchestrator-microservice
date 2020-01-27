package com.smoothstack.december.orchestrationservice.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.december.orchestrationservice.entity.Role;
import com.smoothstack.december.orchestrationservice.security.config.RoleConstants;
import com.smoothstack.december.orchestrationservice.service.RoleService;

@RestController
@RequestMapping("/lms/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    private static final Logger logger = LogManager.getLogger(RoleController.class);

    @GetMapping
    @PreAuthorize("hasAuthority('" + RoleConstants.PREFIXED_ADMIN_ROLE + "')")
    public List<Role> getRoles() {
        List<Role> roles = this.roleService.findAllRoles();
        logger.debug("response: {}", roles.toString());
        return roles;
    }

}
