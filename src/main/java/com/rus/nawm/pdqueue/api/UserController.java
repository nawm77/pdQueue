package com.rus.nawm.pdqueue.api;

import com.rus.nawm.pdqueue.api.docs.UserApi;
import com.rus.nawm.pdqueue.domain.User;
import com.rus.nawm.pdqueue.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

  private final UserService userService;

  @Override
  public ResponseEntity<List<User>> getAllUsers() {
    return null;
  }

  @Override
  public ResponseEntity<User> getUserById(Long id) {
    return null;
  }

  @Override
  public ResponseEntity<User> createUser(User userRegistrationDto) {
    return null;
  }

  @Override
  public ResponseEntity<User> updateUser(Long id, User userUpdateDto) {
    return null;
  }

  @Override
  public ResponseEntity<Void> deleteUser(Long id) {
    return null;
  }
}
