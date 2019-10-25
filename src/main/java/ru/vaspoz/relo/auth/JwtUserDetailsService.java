package ru.vaspoz.relo.auth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.vaspoz.relo.model.UserInfo;
import ru.vaspoz.relo.model.UserDTO;
import ru.vaspoz.relo.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class JwtUserDetailsService implements UserDetailsService{

    protected final Log logger = LogFactory.getLog(this.getClass());


    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private UsersRepository usersRepository;

    public UserInfo loadUserByUsername(String username) {
        List<UserInfo> users = usersRepository.findByUsername(username);
        UserInfo user = users.get(0);
        if (user == null) {
            throw new UsernameNotFoundException("UserInfo not found with username: " + username);
        }
        return user;
    }

    public boolean save(UserDTO user) {
        UserInfo newUser = new UserInfo();

        if (usersRepository.findByUsername(user.getUsername()).size() > 0) {
            logger.error("User with the name [" + user.getUsername() + "] already exists.");
            return false;
        }

        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setCountryOrigin(user.getCountryOrigin());
        newUser.setEmail(user.getEmail());
        newUser.setAccountNonExpired(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setEnabled(true);

        try {
            usersRepository.save(newUser);
        } catch (Exception e) {
            logger.warn("Could not save new user: " + newUser.getUsername());
            return false;
        }
        return true;
    }

}
