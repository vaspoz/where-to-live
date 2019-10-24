package ru.vaspoz.relo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.vaspoz.relo.auth.JwtResponse;
import ru.vaspoz.relo.auth.JwtTokenUtil;
import ru.vaspoz.relo.auth.JwtUserDetailsService;
import ru.vaspoz.relo.model.UserDTO;

@RestController
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDTO userLogin) throws Exception {
        authenticate(userLogin.getUsername(), userLogin.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(userLogin.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signupUser(@RequestBody UserDTO userDTO) throws Exception {
        boolean result = userDetailsService.save(userDTO);
        if (result) {
            return createAuthenticationToken(userDTO);
        } else {
            return ResponseEntity.badRequest().body(userDTO);
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
