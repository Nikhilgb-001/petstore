package com.javaproject1.petstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.javaproject1.petstore.services.AuthUserService;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(
    prePostEnabled = true,
    jsr250Enabled = true,
    securedEnabled = true
)
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain configure(HttpSecurity http)
            throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/products/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/registeredUsers").permitAll()
                .requestMatchers("/register").permitAll()

                // role based authentication
                .requestMatchers("/users/**").hasRole("HR")
                .requestMatchers("/categories/**").hasAnyRole("IT", "SALES")

                // mehod level authentication
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .authenticationProvider(daoAuthenticationProvider());

                return http.build();
    }

    @Autowired
    private AuthUserService authUserService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.authUserService);
        provider.setPasswordEncoder(this.passwordEncoder());
        return provider;
    }

    // password encoder
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //creating Dummy User's
    @Bean
    public UserDetailsService users() {
        // while creating user we provide 3 things
        // 1.username
        // 2.password
        // 3.role
        UserDetails user1 = User.builder().username("harry")
                    .password(passwordEncoder().encode("abc@123"))
                    .roles("HR")
                    .build();

        UserDetails user2 = User.builder()
                    .username("mike")
                    .password(passwordEncoder().encode("xyz@123"))
                    .roles("IT")
                    .build();

        UserDetails user3 = User.builder()
                    .username("scott")
                    .password(passwordEncoder().encode("scott@123"))
                    .roles("SALES", "HR")
                    .build();

        return new InMemoryUserDetailsManager(user1, user2, user3);
    }
}