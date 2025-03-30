package com.rus.nawm.pdqueue.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {
    private String name; // Обновленное имя пользователя
    private String email; // Обновленный email пользователя
    private String role; // Обновленная роль пользователя
    private String group; // Обновленная группа пользователя
}