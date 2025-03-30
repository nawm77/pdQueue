package com.rus.nawm.pdqueue.api.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
@Slf4j
public class ApiControllerAdvice {
  @ExceptionHandler(SecurityException.class)
  public ResponseEntity<?> onSecurityException(SecurityException exception) {
    log.info("Handle SecurityException {}", exception.getLocalizedMessage());
    return ResponseEntity.status(403).build();
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<?> onAccessDeniedException(AccessDeniedException accessDeniedException) {
    log.info("Handle AccessDeniedException {}", accessDeniedException.getLocalizedMessage());
    return ResponseEntity
            .status(403)
            .body("You don't have enough permissions for this action");
  }
}
