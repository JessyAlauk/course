package com.educandoweb.course.dto;

public record UserPutRequestBodyDTO(Long id,
                                    String name,
                                    String email,
                                    String phone,
                                    String password) {
}
