package com.smoothstack.december.orchestrationservice.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smoothstack.december.orchestrationservice.dao.RoleDAO;
import com.smoothstack.december.orchestrationservice.dao.UserDAO;
import com.smoothstack.december.orchestrationservice.entity.Role;
import com.smoothstack.december.orchestrationservice.entity.User;
import com.smoothstack.december.orchestrationservice.exception.CriticalDatabaseErrorException;
import com.smoothstack.december.orchestrationservice.security.config.RoleConstants;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void createAdmin(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>(
                Arrays.asList(new Role(RoleConstants.PREFIXED_BORROWER_ROLE, RoleConstants.BORROWER_DESCRIPTION),
                        new Role(RoleConstants.PREFIXED_LIBRARIAN_ROLE, RoleConstants.LIBRARIAN_DESCRIPTION),
                        new Role(RoleConstants.PREFIXED_ADMIN_ROLE, RoleConstants.ADMIN_DESCRIPTION)));
        this.findIdsForRoles(roles);
        user.setRoles(roles);
        this.userDAO.save(user);
    }

    public void createLibrarian(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>(
                Arrays.asList(new Role(RoleConstants.PREFIXED_BORROWER_ROLE, RoleConstants.BORROWER_DESCRIPTION),
                        new Role(RoleConstants.PREFIXED_LIBRARIAN_ROLE, RoleConstants.LIBRARIAN_DESCRIPTION)));
        this.findIdsForRoles(roles);
        user.setRoles(roles);
        this.userDAO.save(user);
    }

    public void createBorrower(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>(
                Arrays.asList(new Role(RoleConstants.PREFIXED_BORROWER_ROLE, RoleConstants.BORROWER_DESCRIPTION)));
        this.findIdsForRoles(roles);
        user.setRoles(roles);
        this.userDAO.save(user);
    }

    private void findIdsForRoles(Set<Role> roles) {
        for (Role role : roles) {
            Role tempRole = this.roleDAO.findByName(role.getName());

            if (tempRole == null) {
                throw new CriticalDatabaseErrorException(
                        "Unable to find role in database, contact your local administrator.");
            }
            role.setId(tempRole.getId());
        }
    }

    public List<User> findAllUsers() {
        return this.userDAO.findAll();
    }

}
