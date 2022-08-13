package pl.michaldurlak.AssetsInventoryManagement.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.michaldurlak.AssetsInventoryManagement.users.UserDetailsServiceImpl;
import pl.michaldurlak.AssetsInventoryManagement.users.UserModel;
import pl.michaldurlak.AssetsInventoryManagement.users.UserRepo;
import pl.michaldurlak.AssetsInventoryManagement.users.UsersRoles;

import java.util.Collections;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private UserDetailsServiceImpl userDetailsService;
    private UserRepo userRepo;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, UserRepo userRepo) {
        this.userDetailsService = userDetailsService;
        this.userRepo = userRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/admin").hasRole(String.valueOf(UsersRoles.ADMIN))
                .antMatchers("/read").hasRole(String.valueOf(UsersRoles.READ))
                .antMatchers("/readwrite").hasRole(String.valueOf(UsersRoles.WRITE_READ))
                .and()
                .formLogin().permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createDefaultUsers(){
        UserModel userAdmin = new UserModel("admin", passwordEncoder().encode("admin"));
        userAdmin.setUserRole(UsersRoles.ADMIN);
        UserModel userRead = new UserModel("read", passwordEncoder().encode("read"));
        UserModel userReadWrite = new UserModel("readwrite", passwordEncoder().encode("readwrite"));
        userReadWrite.setUserRole(UsersRoles.WRITE_READ);
        userRepo.save(userAdmin);
        userRepo.save(userRead);
        userRepo.save(userReadWrite);
        System.out.println("--------> UZYTKOWNICY ZALOZENI <--------------");
    }
}
