package ru.vaspoz.relo.auth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.h2.message.DbException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.vaspoz.relo.model.UserAuthenticated;
import ru.vaspoz.relo.model.UserDTO;
import ru.vaspoz.relo.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    protected final Log logger = LogFactory.getLog(this.getClass());


    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private UsersRepository usersRepository;


    public UserDetails loadUserByUsername(String username) {
        List<UserAuthenticated> users = usersRepository.findByUsername(username);
        UserAuthenticated user = users.get(0);
        if (user == null) {
            throw new UsernameNotFoundException("UserAuthenticated not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public boolean save(UserDTO user) {
        UserAuthenticated newUser = new UserAuthenticated();

        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setCountryOrigin(user.getCountryOrigin());
        newUser.setEmail(user.getEmail());

        try {
            usersRepository.save(newUser);
        } catch (Exception e) {
            logger.warn("Could not save new user: " + newUser.getUsername());
            return false;
        }
        return true;
    }

}
