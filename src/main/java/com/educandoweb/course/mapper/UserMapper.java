package com.educandoweb.course.mapper;

import com.educandoweb.course.dto.UserPostRequestBodyDTO;
import com.educandoweb.course.dto.UserResponseDTO;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.dto.UserPutRequestBodyDTO;

public final class UserMapper {

    private UserMapper() {
    }

    public static User toSave(UserPutRequestBodyDTO user) {
        return new User(user.id(), user.name(), user.email(), user.phone(), user.password());
    }

    public static User toSave(UserPostRequestBodyDTO user) {
        return new User(null, user.name(), user.email(), user.phone(), user.password());
    }

    public static UserResponseDTO toResponse(User user){
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getPhone());
    }
}
