package ru.bagrov.user.rest.api.app.domain.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ru.bagrov.user.rest.api.app.domain.UserInputBoundary;
import ru.bagrov.user.rest.api.app.domain.UserRepositoryGw;
import ru.bagrov.user.rest.api.app.domain.entity.User;
import ru.bagrov.user.rest.api.app.domain.entity.factory.UserFactory;
import ru.bagrov.user.rest.api.app.domain.exception.UserNotFoundException;
import ru.bagrov.user.rest.api.app.domain.mapper.UserMapper;
import ru.bagrov.user.rest.api.app.domain.dto.CreateUserRequest;
import ru.bagrov.user.rest.api.app.domain.dto.CreateUserResponse;
import ru.bagrov.user.rest.api.app.domain.dto.GetUserResponse;
import ru.bagrov.user.rest.api.app.domain.dto.UpdateUserRequest;
import ru.bagrov.user.rest.api.app.domain.dto.UpdateUserResponse;
import ru.bagrov.user.rest.api.app.domain.dto.UserDto;
import ru.bagrov.user.rest.api.app.domain.service.UserService;
import ru.bagrov.user.rest.api.app.domain.util.RequestValidator;

import java.util.Optional;

@Log4j2
@Component
@RequiredArgsConstructor
public class UserManager implements UserInputBoundary {

    private final RequestValidator validator;
    private final UserFactory factory;
    private final UserRepositoryGw repositoryGw;
    private final UserMapper mapper;
    private final UserService service;

    @Override
    public CreateUserResponse create(CreateUserRequest request) {
        log.debug("Incoming Create User request");
        validator.validate(request);

        log.debug("request is valid");
        User user = factory.create(request.getUserName(), request.getFirstName(), request.getSecondName(), request.getAge(), request.getGender());

        log.debug("Saving User to database");
        UserDto savedUser = repositoryGw.save(user);

        return mapper.dtoToCreateUserResponse(savedUser);
    }

    @Override
    public GetUserResponse get(long id) {
        log.debug("Incoming Get User request");

        Optional<UserDto> userOptional = repositoryGw.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(String.format("User with id '%d' not found", id));
        }

        log.debug("User with id '{}' found", id);
        return mapper.dtoToGetUserResponse(userOptional.get());
    }

    @Override
    public UpdateUserResponse update(long id, UpdateUserRequest request) {
        log.debug("Incoming Update User request");
        validator.validate(request);

        log.debug("request is valid");

        Optional<UserDto> userOptional = repositoryGw.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(String.format("User with id '%d' not found", id));
        }

        log.debug("User with id '{}' found", id);
        UserDto userForUpdating = userOptional.get();
        service.updateUserFields(userForUpdating, request);

        log.debug("Updating User in database");
        UserDto updatedUser = repositoryGw.update(userForUpdating);

        return mapper.dtoToUpdateUserResponse(updatedUser);
    }

    @Override
    public void delete(long id) {
        log.debug("Incoming Delete User request");

        Optional<UserDto> userOptional = repositoryGw.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(String.format("User with id '%d' not found", id));
        }

        log.debug("Deleting User with id '{}'", id);
        repositoryGw.deleteById(id);
    }
}
