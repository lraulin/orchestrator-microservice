package com.smoothstack.lms.orchestratorservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.lms.orchestratorservice.entity.User;
import com.smoothstack.lms.orchestratorservice.security.service.MyUserDetailsService;
import com.smoothstack.lms.orchestratorservice.security.util.JwtUtil;
import com.smoothstack.lms.orchestratorservice.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/lms/authenticate")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user) {
        logger.debug("request: {}", user.toString());

        try {
            this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password", e);
        }

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(user.getEmail());
        String jwt = this.jwtTokenUtil.generateToken(userDetails);
        Map<String, String> response = new HashMap<>();

        user = this.userService.getUser(user.getEmail());
        response.put("token", jwt);
        response.put("firstName", user.getFirstName());
        response.put("lastName", user.getLastName());
        response.put("role", user.getRole().toString());
        logger.debug("response: {}", response.toString());
        return ResponseEntity.ok(response);
    }

}
