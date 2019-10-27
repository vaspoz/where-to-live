package ru.vaspoz.relo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ru.vaspoz.relo.auth.AuthResponse;
import ru.vaspoz.relo.auth.JwtTokenUtil;
import ru.vaspoz.relo.auth.JwtUserDetailsService;
import ru.vaspoz.relo.model.UserDTO;
import ru.vaspoz.relo.model.UserInfo;
import ru.vaspoz.relo.utils.UserInfoDTOUtils;

@RestController
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<AuthResponse> createAuthenticationToken(@RequestBody UserDTO userLogin) {

        AuthResponse response = new AuthResponse();
        try {
            authenticate(userLogin.getUsername(), userLogin.getPassword());
        } catch (BadCredentialsException bce) {
            response.setErrorCode("5"); //todo: create variables
            response.setError("Bad credentials");
            return ResponseEntity.badRequest().body(response);
        }

        final UserInfo userInfo = userDetailsService.loadUserByUsername(userLogin.getUsername());
        final String token = jwtTokenUtil.generateToken(userInfo);
        response.setJwttoken(token);
        response.setUserDTO(UserInfoDTOUtils.infoToDTO(userInfo));

        return ResponseEntity.ok(response);

    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<AuthResponse> signupUser(@RequestBody UserDTO userDTO) throws Exception {

        AuthResponse response = new AuthResponse();
        boolean result = userDetailsService.save(userDTO);
        if (result) {
            return createAuthenticationToken(userDTO);
        } else {
            response.setErrorCode("4");
            response.setError("User already exists");
            return ResponseEntity.badRequest().body(response);
        }

    }

    private void authenticate(String username, String password) throws BadCredentialsException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
