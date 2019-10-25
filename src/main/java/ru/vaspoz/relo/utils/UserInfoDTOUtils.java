package ru.vaspoz.relo.utils;

import ru.vaspoz.relo.model.UserDTO;
import ru.vaspoz.relo.model.UserInfo;

public class UserInfoDTOUtils {

    public static UserDTO infoToDTO(UserInfo userInfo) {
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword(userInfo.getPassword());
        userDTO.setUsername(userInfo.getUsername());
        userDTO.setCountryOrigin(userInfo.getCountryOrigin());
        userDTO.setEmail(userInfo.getEmail());

        return userDTO;
    }

    public static UserInfo DTOtoInfo(UserDTO userDTO) {
        UserInfo userInfo = new UserInfo();
        userInfo.setCountryOrigin(userDTO.getCountryOrigin());
        userInfo.setEmail(userDTO.getEmail());
        userInfo.setPassword(userDTO.getPassword());
        userInfo.setUsername(userDTO.getUsername());

        return userInfo;
    }
}
