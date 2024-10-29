package ru.bagrov.user.rest.api.app.domain;

import ru.bagrov.user.rest.api.app.domain.entity.User;
import ru.bagrov.user.rest.api.app.domain.dto.UserDto;

import java.util.Optional;

public interface UserRepositoryGw {

    UserDto save(User user);
    Optional<UserDto> findById(long id);
    UserDto update(UserDto user);
    void deleteById(long id);
}
