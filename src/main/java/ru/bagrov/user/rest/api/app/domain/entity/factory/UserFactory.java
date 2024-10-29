package ru.bagrov.user.rest.api.app.domain.entity.factory;

import ru.bagrov.user.rest.api.app.domain.entity.User;

public interface UserFactory {

    User create(String userName, String firstName, String secondName, Integer age, String gender);
}
