package ru.bagrov.user.rest.api.app.infra.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bagrov.user.rest.api.app.infra.repository.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
