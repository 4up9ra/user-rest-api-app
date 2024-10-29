package ru.bagrov.user.rest.api.app.domain;

import ru.bagrov.user.rest.api.app.domain.dto.CreateUserRequest;
import ru.bagrov.user.rest.api.app.domain.dto.CreateUserResponse;
import ru.bagrov.user.rest.api.app.domain.dto.GetUserResponse;
import ru.bagrov.user.rest.api.app.domain.dto.UpdateUserRequest;
import ru.bagrov.user.rest.api.app.domain.dto.UpdateUserResponse;

public interface UserInputBoundary {

    CreateUserResponse create(CreateUserRequest request);
    GetUserResponse get(long id);
    UpdateUserResponse update(long id, UpdateUserRequest request);
    void delete(long id);
}
