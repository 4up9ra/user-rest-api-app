package ru.bagrov.user.rest.api.app.infra.repository.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bagrov.user.rest.api.app.domain.UserRepositoryGw;
import ru.bagrov.user.rest.api.app.domain.entity.User;
import ru.bagrov.user.rest.api.app.domain.dto.UserDto;
import ru.bagrov.user.rest.api.app.infra.repository.UserRepository;
import ru.bagrov.user.rest.api.app.infra.repository.mapper.UserRepositoryMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserRepositoryService implements UserRepositoryGw {

    private final UserRepositoryMapper mapper;
    private final UserRepository repository;

    @Override
    @Transactional
    public UserDto save(User user) {
        return mapper.entityToDto(repository.save(mapper.domainEntityToRepositoryEntity(user)));
    }

    @Override
    public Optional<UserDto> findById(long id) {
        return repository.findById(id).map(mapper::entityToDto);
    }

    @Override
    @Transactional
    public UserDto update(UserDto user) {
        return mapper.entityToDto(repository.save(mapper.dtoToEntity(user)));
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
