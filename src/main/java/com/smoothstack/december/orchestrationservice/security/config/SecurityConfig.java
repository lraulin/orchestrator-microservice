package com.smoothstack.december.orchestrationservice.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.smoothstack.december.orchestrationservice.security.filter.JwtRequestFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService myUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    private static final String authenticateUri = "/lms/authenticate";
    private static final String adminUri = "/lms/admin/**";
    private static final String librarianUri = "/lms/librarian/**";
    private static final String borrowerUri = "/lms/borrower/**";
    private static final String adminRole = "ADMIN";
    private static final String librarianRole = "LIBRARIAN";
    private static final String borrowerRole = "BORROWER";

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.myUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//        httpSecurity.authorizeRequests().antMatchers(authenticateUri).permitAll().antMatchers(adminUri)
//                .hasRole(adminRole).antMatchers(librarianUri).hasRole(librarianRole).antMatchers(borrowerUri)
//                .hasRole(borrowerRole);

        httpSecurity.authorizeRequests().antMatchers(authenticateUri).permitAll().antMatchers(librarianUri)
                .hasRole(librarianRole).anyRequest().authenticated();

        httpSecurity.exceptionHandling().accessDeniedPage("/login");
        httpSecurity.addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
