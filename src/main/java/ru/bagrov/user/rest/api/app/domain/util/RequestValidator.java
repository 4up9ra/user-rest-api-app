package ru.bagrov.user.rest.api.app.domain.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.bagrov.user.rest.api.app.domain.exception.RequestValidationException;

import java.util.Set;


@Component
@RequiredArgsConstructor
public class RequestValidator {

    private final Validator validator;

    public <T> void validate(T request) {
        Set<ConstraintViolation<T>> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            throw new RequestValidationException(String.format("Incoming request not valid: %s", validationErrors.stream()
                    .map(ConstraintViolation::getMessage)
                    .toList()));
        }
    }
}
