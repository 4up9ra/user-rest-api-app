package ru.bagrov.user.rest.api.app.domain.entity;

import lombok.Data;

@Data
public class User {

    private String userName;
    private String firstName;
    private String secondName;
    private Integer age;
    private String gender;
}
