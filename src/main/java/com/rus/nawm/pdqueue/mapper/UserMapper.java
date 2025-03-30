//package com.rus.nawm.pdqueue.mapper;
//
//import com.rus.nawm.pdqueue.domain.Group;
//import com.rus.nawm.pdqueue.domain.Role;
//import com.rus.nawm.pdqueue.domain.User;
//import com.rus.nawm.pdqueue.api.dto.UserDto;
//import com.rus.nawm.pdqueue.api.dto.UserRegistrationDto;
//import com.rus.nawm.pdqueue.api.dto.UserUpdateDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Component
//@RequiredArgsConstructor
//public class UserMapper {
//
//
//    /**
//     * Преобразовать сущность User в DTO UserDto.
//     *
//     * @param user Сущность User.
//     * @return DTO UserDto.
//     */
//    public UserDto toDto(User user) {
//        if (user == null) {
//            return null;
//        }
//
//        return UserDto.builder()
//                .id(user.getId())
//                .username(user.getUsername())
//                .fullName(user.getFullName())
//                .group(user.getGroup() != null ? user.getGroup().getName() : null) // Имя группы
//                .roles(user.getRoles().stream()
//                        .map(Role::getName) // Получение имени роли
//                        .collect(Collectors.toSet()))
//                .build();
//    }
//
//    /**
//     * Преобразовать DTO UserRegistrationDto в сущность User.
//     *
//     * @param registrationDto DTO с данными регистрации пользователя.
//     * @return Сущность User.
//     */
//    public User toEntity(UserRegistrationDto registrationDto, Group group, Set<Role> roles) {
//        if (registrationDto == null) {
//            return null;
//        }
//
//        User user = new User();
//        user.setUsername(registrationDto.getUsername());
//        user.setFullName(registrationDto.getFullName());
//        user.setPassword(registrationDto.getPassword());
//        user.setGroup(group); // Установить группу
//        user.setRoles(roles); // Установить роли
//
//        return user;
//    }
//
//    /**
//     * Обновить данные сущности User на основе DTO UserUpdateDto.
//     *
//     * @param user      Сущность User.
//     * @param updateDto DTO с обновленными данными.
//     * @param group     Обновленная группа.
//     * @param roles     Обновленные роли.
//     */
//    public void updateEntity(User user, UserUpdateDto updateDto, Group group, Set<Role> roles) {
//        if (updateDto == null) {
//            return;
//        }
//
//        if (updateDto.getUsername() != null) {
//            user.setUsername(updateDto.getUsername());
//        }
//        if (updateDto.getFullName() != null) {
//            user.setFullName(updateDto.getFullName());
//        }
//        if (updateDto.getPassword() != null) {
//            user.setPassword(updateDto.getPassword());
//        }
//        if (group != null) {
//            user.setGroup(group);
//        }
//        if (roles != null) {
//            user.setRoles(roles);
//        }
//    }
//}