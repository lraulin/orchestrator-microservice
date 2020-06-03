package com.smoothstack.lms.orchestratorservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.lms.orchestratorservice.entity.User;
import com.smoothstack.lms.orchestratorservice.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("lms/users")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @PostMapping("/register/admin")
    public void createAdmin(@RequestBody @Valid User user) {
        logger.debug("request: {}", user.toString());
        this.userService.createAdmin(user);
    }

    @PostMapping("/register/librarian")
    public void createLibrarian(@RequestBody @Valid User user) {
        logger.debug("request: {}", user);
        this.userService.createLibrarian(user);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> getUsers() {
        List<User> users = this.userService.getAllUsers();
        logger.debug("response: {}", users.toString());
        return users;
    }

}
