package com.smoothstack.lms.orchestratorservice.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.smoothstack.lms.orchestratorservice.security.filter.JwtRequestFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService myUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    private static final String AUTHENTICATE_URI = "/lms/authenticate";
    private static final String ADMIN_URI = "/lms/admin/**";
    private static final String LIBRARIAN_URI = "/lms/librarian/**";
    private static final String BORROWER_URI = "/lms/borrower/**";

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.myUserDetailsService);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
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

        httpSecurity.authorizeRequests().antMatchers("/**").permitAll();

        /*
         * httpSecurity.authorizeRequests().antMatchers(AUTHENTICATE_URI).permitAll().
         * antMatchers(BORROWER_URI)
         * .hasRole("BORROWER").antMatchers(LIBRARIAN_URI).hasRole("LIBRARIAN").
         * antMatchers(ADMIN_URI) .hasRole("ADMIN");
         */

        // httpSecurity.exceptionHandling().accessDeniedPage("/login");
        // httpSecurity.addFilterBefore(this.jwtRequestFilter,
        // UsernamePasswordAuthenticationFilter.class);
    }

}
