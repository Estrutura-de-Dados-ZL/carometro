package com.carometro.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.carometro.dto.ApiRespostaDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiRespostaDto<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    List<String> mensagens = ex.getBindingResult().getAllErrors().stream()
        .map(error -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
        .toList();

    ApiRespostaDto<Object> response = new ApiRespostaDto<>(mensagens);

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}
