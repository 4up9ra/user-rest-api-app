package ru.bagrov.user.rest.api.app.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {

    private Long userId;
    private String userName;
    private String firstName;
    private String secondName;
    private Integer age;
    private String gender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
