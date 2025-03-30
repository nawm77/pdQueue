package com.rus.nawm.pdqueue.api.docs;

import com.rus.nawm.pdqueue.api.dto.UserDto;
import com.rus.nawm.pdqueue.api.dto.UserRegistrationDto;
import com.rus.nawm.pdqueue.api.dto.UserUpdateDto;
import com.rus.nawm.pdqueue.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/users")
public interface UserApi {

  @Operation(summary = "Получить список всех пользователей")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Список пользователей успешно получен",
                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
          @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                  content = @Content(mediaType = "application/json"))
  })
  @GetMapping()
  ResponseEntity<List<User>> getAllUsers();

  @Operation(summary = "Получить информацию о пользователе по ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Пользователь найден",
                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
          @ApiResponse(responseCode = "404", description = "Пользователь не найден",
                  content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                  content = @Content(mediaType = "application/json"))
  })
  @GetMapping("/{id}")
  ResponseEntity<User> getUserById(@PathVariable Long id);

  @Operation(summary = "Создать нового пользователя")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Пользователь успешно создан",
                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
          @ApiResponse(responseCode = "400", description = "Неверные данные для создания пользователя",
                  content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                  content = @Content(mediaType = "application/json"))
  })
  @PostMapping("")
  ResponseEntity<User> createUser(@RequestBody User userRegistrationDto);

  @Operation(summary = "Обновить данные пользователя")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлен",
                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
          @ApiResponse(responseCode = "404", description = "Пользователь не найден",
                  content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "400", description = "Неверные данные для обновления",
                  content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                  content = @Content(mediaType = "application/json"))
  })
  @PutMapping("/{id}")
  ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userUpdateDto);

  @Operation(summary = "Удалить пользователя")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "204", description = "Пользователь успешно удален"),
          @ApiResponse(responseCode = "404", description = "Пользователь не найден",
                  content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                  content = @Content(mediaType = "application/json"))
  })
  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteUser(@PathVariable Long id);
}