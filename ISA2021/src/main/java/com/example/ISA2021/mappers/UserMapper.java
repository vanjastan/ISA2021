package com.example.ISA2021.mappers;

import com.example.ISA2021.dto.UserDTO;
import com.example.ISA2021.dto.UserRegistrationDTO;
import com.example.ISA2021.model.User;

public class UserMapper {
    public static User toUserEntity(UserRegistrationDTO userInfo) {
        User user = new User();
        user.setUsername(userInfo.getUsername());
        user.setEmail(userInfo.getEmail());
        return user;
    }

    public static UserDTO toDto(User user) {
        return new UserDTO(user);
    }

    private UserMapper() {
    }
}
