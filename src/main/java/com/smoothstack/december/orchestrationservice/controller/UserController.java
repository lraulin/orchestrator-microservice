package com.smoothstack.december.orchestrationservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.december.orchestrationservice.entity.User;
import com.smoothstack.december.orchestrationservice.security.config.RoleConstants;
import com.smoothstack.december.orchestrationservice.service.UserService;

@RestController
@RequestMapping("lms/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register/admin")
    @PreAuthorize("hasAuthority('" + RoleConstants.PREFIXED_ADMIN_ROLE + "')")
    public void createAdmin(@RequestBody @Valid User user) {
        this.userService.createAdmin(user);
    }

    @PostMapping("/register/librarian")
    @PreAuthorize("hasAnyAuthority('" + RoleConstants.PREFIXED_ADMIN_ROLE + "', '"
            + RoleConstants.PREFIXED_LIBRARIAN_ROLE + "')")
    public void createLibrarian(@RequestBody @Valid User user) {
        this.userService.createLibrarian(user);
    }

    @PostMapping("/register/borrower")
    @PreAuthorize("permitAll()")
    public void createBorrower(@RequestBody @Valid User user) {
        this.userService.createBorrower(user);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('" + RoleConstants.PREFIXED_ADMIN_ROLE + "')")
    public List<User> getUsers() {
        return this.userService.findAllUsers();
    }

}
