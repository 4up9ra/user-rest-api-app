package ru.bagrov.user.rest.api.app.domain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.bagrov.user.rest.api.app.domain.util.GenderValue;

@Data
public class CreateUserRequest {

    @NotBlank(message = "userName must not be null or empty")
    private String userName;
    @NotBlank(message = "firstName must not be null or empty")
    private String firstName;
    @NotBlank(message = "secondName must not be null or empty")
    private String secondName;
    @NotNull(message = "age must not be null or empty")
    @Min(value = 6, message = "age must be > 6")
    @Max(value = 90, message = "age must be < 90")
    private Integer age;
    @NotBlank(message = "gender must not be null or empty")
    @GenderValue(allowedValues = {"M", "F"},  message = "gender must be 'M' or 'F'")
    private String gender;
}
