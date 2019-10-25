package ru.vaspoz.relo.auth;

import ru.vaspoz.relo.model.UserDTO;

import java.io.Serializable;

public class AuthResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private String jwttoken;
    private UserDTO userDTO;
    private String errorMessage;
    private String errorCode;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setJwttoken(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getJwttoken() {
        return jwttoken;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }
}