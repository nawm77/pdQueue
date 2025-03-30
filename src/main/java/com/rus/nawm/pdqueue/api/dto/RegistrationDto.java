package com.rus.nawm.pdqueue.api.dto;

import lombok.Data;

@Data
public class RegistrationDto {
    private String username;
    private String fullName;
    private String password;
    private String groupName;
}