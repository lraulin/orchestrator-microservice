package com.smoothstack.december.orchestrationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.december.orchestrationservice.entity.User;
import com.smoothstack.december.orchestrationservice.security.service.MyUserDetailsService;
import com.smoothstack.december.orchestrationservice.security.util.JwtUtil;

@RestController
@RequestMapping("/lms/authenticate")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user) throws Exception {
        try {
            this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password", e);
        }

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(user.getUsername());
        System.out.println("Athorities");
        String jwt = this.jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(jwt);
    }

}
