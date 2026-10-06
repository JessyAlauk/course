package com.educandoweb.course.dto;

public record UserPostRequestBodyDTO(String name,
                                     String email,
                                     String phone,
                                     String password) {
}
