package com.rus.nawm.pdqueue.api;

import com.rus.nawm.pdqueue.api.dto.LoginRequest;
import com.rus.nawm.pdqueue.api.dto.LoginResponse;
import com.rus.nawm.pdqueue.api.dto.RegistrationResponse;
import com.rus.nawm.pdqueue.domain.User;
import com.rus.nawm.pdqueue.exception.UserNotFoundException;
import com.rus.nawm.pdqueue.service.LoginService;
import com.rus.nawm.pdqueue.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final LoginService loginService;

    @Operation(summary = "Login exists user", description = "Метод принимает LoginRequest для аутентификации пользователя и выдает токен")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) throws Exception {
        return ResponseEntity.status(200).body(LoginResponse.builder()
                .token(loginService.loginProcessor(request))
                .build());
    }

    @PostMapping("/registration")
    public ResponseEntity<RegistrationResponse> registrationCustomer(@RequestBody User user) throws UserNotFoundException {
        String token = userService.createNewUser(user);
        return ResponseEntity.status(200).body(RegistrationResponse.builder()
                .token(token)
                .build());
    }
}