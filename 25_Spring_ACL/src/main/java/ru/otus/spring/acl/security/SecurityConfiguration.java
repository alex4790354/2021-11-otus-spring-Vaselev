package ru.otus.spring.acl.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/", "/books", "/books/editBook", "/notes/bookNotes").permitAll()
                .and().authorizeRequests().antMatchers("/notes/editBookNote**", "/notes/delete**").authenticated()
                .and().authorizeRequests().antMatchers("/books/saveBook**", "/books/delete**").hasAnyRole("ADMIN")
                .and().authorizeRequests().antMatchers("/**").denyAll()
                .and().formLogin().defaultSuccessUrl("/books")
                .and().logout().logoutSuccessUrl("/books").permitAll()
                .and().exceptionHandling().accessDeniedPage("/error403");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
