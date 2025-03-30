package com.rus.nawm.pdqueue.service;

import com.rus.nawm.pdqueue.domain.User;
import com.rus.nawm.pdqueue.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

  /**
   * Получить список всех пользователей.
   *
   * @return Список объектов User.
   */
  List<User> getAllUsers();

  /**
   * Найти пользователя по ID.
   *
   * @param id ID пользователя.
   * @return Объект User с данными пользователя.
   */
  User getUserById(Long id) throws UserNotFoundException;

  /**
   * Создать нового пользователя.
   *
   * @param userRegistrationDto DTO с данными для регистрации пользователя.
   * @return Объект User с созданным пользователем.
   */
  User createUser(User userRegistrationDto);

  /**
   * Обновить данные пользователя.
   *
   * @param id              ID пользователя.
   * @param userUpdateDto   DTO с новыми данными пользователя.
   * @return Объект User с обновленными данными.
   */
  User updateUser(Long id, User userUpdateDto) throws UserNotFoundException;

  /**
   * Удалить пользователя по ID.
   *
   * @param id ID пользователя.
   */
  void deleteUser(Long id) throws UserNotFoundException;

  User getUserByUserName(String userName);
  String createNewUser(User user);
}