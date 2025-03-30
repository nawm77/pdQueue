package com.rus.nawm.pdqueue.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {
    private String name; // Имя пользователя
    private String email; // Email пользователя
    private String password; // Пароль для нового пользователя
    private String group; // Группа пользователя
}