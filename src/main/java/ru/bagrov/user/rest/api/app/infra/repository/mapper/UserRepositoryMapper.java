package ru.bagrov.user.rest.api.app.infra.repository.mapper;

import org.mapstruct.Mapper;
import ru.bagrov.user.rest.api.app.domain.entity.User;
import ru.bagrov.user.rest.api.app.domain.dto.UserDto;
import ru.bagrov.user.rest.api.app.infra.repository.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserRepositoryMapper {

    UserEntity domainEntityToRepositoryEntity(User user);
    UserDto entityToDto(UserEntity entity);
    UserEntity dtoToEntity(UserDto dto);
}
