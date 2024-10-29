package ru.bagrov.user.rest.api.app.domain.mapper;

import org.mapstruct.Mapper;
import ru.bagrov.user.rest.api.app.domain.dto.CreateUserResponse;
import ru.bagrov.user.rest.api.app.domain.dto.GetUserResponse;
import ru.bagrov.user.rest.api.app.domain.dto.UpdateUserResponse;
import ru.bagrov.user.rest.api.app.domain.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    CreateUserResponse dtoToCreateUserResponse(UserDto dto);
    GetUserResponse dtoToGetUserResponse(UserDto dto);
    UpdateUserResponse dtoToUpdateUserResponse(UserDto dto);
}
