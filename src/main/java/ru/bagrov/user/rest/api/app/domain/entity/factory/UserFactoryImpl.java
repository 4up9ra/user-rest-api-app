package ru.bagrov.user.rest.api.app.domain.entity.factory;

import org.springframework.stereotype.Component;
import ru.bagrov.user.rest.api.app.domain.entity.User;

@Component
public class UserFactoryImpl implements UserFactory {

    @Override
    public User create(String userName, String firstName, String secondName, Integer age, String gender) {
        User user = new User();
        user.setUserName(userName);
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setAge(age);
        user.setGender(gender);
        return user;
    }
}
