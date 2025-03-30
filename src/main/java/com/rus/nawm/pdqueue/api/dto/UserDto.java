package com.rus.nawm.pdqueue.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id; // Уникальный идентификатор пользователя
    private String username; // Имя пользователя
    private String fullName; // Email пользователя
    private Set<String> roles; // Роль пользователя (e.g., "student", "moderator", "instructor")
    private GroupDto group; // Название группы, в которой состоит пользователь
}