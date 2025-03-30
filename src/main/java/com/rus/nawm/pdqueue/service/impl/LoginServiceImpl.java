package com.rus.nawm.pdqueue.service.impl;

import com.rus.nawm.pdqueue.api.dto.LoginRequest;
import com.rus.nawm.pdqueue.domain.User;
import com.rus.nawm.pdqueue.service.JwtService;
import com.rus.nawm.pdqueue.service.LoginService;
import com.rus.nawm.pdqueue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    public LoginServiceImpl(UserService userService, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public String loginProcessor(LoginRequest loginRequest) {
        User user = userService.getUserByUserName(loginRequest.getUsername());
        if(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return jwtService.generateToken(user);
        } else{
            throw new IllegalArgumentException("Invalid password");
        }
    }
}