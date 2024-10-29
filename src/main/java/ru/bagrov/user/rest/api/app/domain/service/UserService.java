package ru.bagrov.user.rest.api.app.domain.service;

import org.springframework.stereotype.Service;
import ru.bagrov.user.rest.api.app.domain.dto.UpdateUserRequest;
import ru.bagrov.user.rest.api.app.domain.dto.UserDto;

@Service
public class UserService {

    public void updateUserFields(UserDto userForUpdating, UpdateUserRequest request) {
        userForUpdating.setUserName(request.getUserName());
        userForUpdating.setFirstName(request.getFirstName());
        userForUpdating.setSecondName(request.getSecondName());
        userForUpdating.setAge(request.getAge());
        userForUpdating.setGender(request.getGender());
    }
}
