package ru.bagrov.user.rest.api.app.infra.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bagrov.user.rest.api.app.domain.UserInputBoundary;
import ru.bagrov.user.rest.api.app.domain.dto.CreateUserRequest;
import ru.bagrov.user.rest.api.app.domain.dto.CreateUserResponse;
import ru.bagrov.user.rest.api.app.domain.dto.GetUserResponse;
import ru.bagrov.user.rest.api.app.domain.dto.UpdateUserRequest;
import ru.bagrov.user.rest.api.app.domain.dto.UpdateUserResponse;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "201", description = "Created"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
})
public class UserController {

    private final UserInputBoundary inputBoundary;

    @Operation(summary = "Создание пользователя")
    @PostMapping("/create")
    public ResponseEntity<CreateUserResponse> create(@RequestBody CreateUserRequest request) {
        return ResponseEntity.status(CREATED).body(inputBoundary.create(request));
    }

    @Operation(summary = "Поиск пользователя по id")
    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> get(@PathVariable("id") long id) {
        return ResponseEntity.status(OK).body(inputBoundary.get(id));
    }

    @Operation(summary = "Изменение пользователя по id")
    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserResponse> update(@PathVariable("id") long id,
                                                     @RequestBody UpdateUserRequest request) {
        return ResponseEntity.status(OK).body(inputBoundary.update(id, request));
    }

    @Operation(summary = "Удаление пользователя по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        inputBoundary.delete(id);
        return ResponseEntity.noContent().build();
    }
}
