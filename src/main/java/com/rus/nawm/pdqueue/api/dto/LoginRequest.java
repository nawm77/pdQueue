package com.rus.nawm.pdqueue.api.dto;

import lombok.*;

@Getter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
}