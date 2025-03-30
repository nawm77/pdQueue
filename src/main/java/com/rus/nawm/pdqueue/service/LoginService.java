package com.rus.nawm.pdqueue.service;

import com.rus.nawm.pdqueue.api.dto.LoginRequest;

public interface LoginService {
    String loginProcessor(LoginRequest loginRequest) throws Exception;
}