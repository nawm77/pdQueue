package com.rus.nawm.pdqueue.service.impl;

import com.rus.nawm.pdqueue.domain.Role;
import com.rus.nawm.pdqueue.domain.User;
import com.rus.nawm.pdqueue.domain.UserRole;
import com.rus.nawm.pdqueue.exception.UserNotFoundException;
import com.rus.nawm.pdqueue.repository.RoleRepository;
import com.rus.nawm.pdqueue.repository.UserRepository;
import com.rus.nawm.pdqueue.service.JwtService;
import com.rus.nawm.pdqueue.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User getUserById(Long id) throws UserNotFoundException {
    return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
  }

  @Override
  public User createUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public User updateUser(Long id, User user) throws UserNotFoundException {
    User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    user.setId(id);
    return userRepository.save(user);
  }

  @Override
  public void deleteUser(Long id) throws UserNotFoundException {
    if (!userRepository.existsById(id)) {
      throw new UserNotFoundException("User with ID " + id + " not found");
    }
    userRepository.deleteById(id);
  }

  @Override
  public User getUserByUserName(String userName) {
    return userRepository.findByUsername(userName).orElseThrow(() -> new NoSuchElementException("No such user with username ".concat(userName)));
  }

  @Override
  public String createNewUser(User user) {
    if(userRepository.findByUsername(user.getUsername()).isPresent()){
      throw new IllegalArgumentException("User with username: " + user.getUsername() +" already exists in database");
    }
    user.setUserRole(Set.of(new UserRole(user, roleRepository.findById(1L).get())));

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.saveAndFlush(user);
    return jwtService.generateToken(user);
  }
}